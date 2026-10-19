using System.Globalization;
using System.Runtime.InteropServices;
using System.Runtime.InteropServices.ComTypes;

namespace MailRuDesktop.App.Mailing;

internal sealed class OpenExcelBook
{
    internal required object Workbook { get; init; }
    internal required int ApplicationHwnd { get; init; }
    public required string Name { get; init; }
    public required string FullName { get; init; }
    public required List<string> Sheets { get; init; }
    public string DisplayName => Name + " — " + FullName;
}

internal sealed class ExcelSheetSnapshot
{
    internal required List<MailingExcelRow> Rows { get; init; }
    internal required Dictionary<string, int> Columns { get; init; }
    internal required string WorkbookPath { get; init; }
    internal required string SheetName { get; init; }
}

/// <summary>
/// Reads a live Excel workbook through the Windows running-object table.
/// This class MUST be called from the caller's STA/WPF dispatcher only.
/// Never creates/quits an Excel process or saves/modifies the user's workbook.
/// </summary>
internal static class ExcelInteropService
{
    [DllImport("ole32.dll", PreserveSig = true)]
    private static extern int GetRunningObjectTable(uint reserved,
        out IRunningObjectTable table);
    [DllImport("ole32.dll", PreserveSig = true)]
    private static extern int CreateBindCtx(uint reserved, out IBindCtx ctx);

    internal static List<OpenExcelBook> Discover()
    {
        var books = new List<OpenExcelBook>();
        var identities = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
        Marshal.ThrowExceptionForHR(GetRunningObjectTable(0, out var rot));
        Marshal.ThrowExceptionForHR(CreateBindCtx(0, out var ctx));
        try
        {
            rot.EnumRunning(out var enumerator);
            var item = new IMoniker[1];
            while (enumerator.Next(1, item, IntPtr.Zero) == 0)
            {
                string display;
                try { item[0].GetDisplayName(ctx, null, out display!); }
                catch (COMException) { continue; }
                // Excel registers open workbooks in ROT; never touch unrelated apps.
                if (display.IndexOf(".xls", StringComparison.OrdinalIgnoreCase) < 0)
                    continue;
                try
                {
                    rot.GetObject(item[0], out var comObject);
                    dynamic workbook = comObject;
                    string name = workbook.Name;
                    string path = workbook.FullName;
                    dynamic excel = workbook.Application;
                    int hwnd = Convert.ToInt32(excel.Hwnd, CultureInfo.InvariantCulture);
                    var key = hwnd.ToString(CultureInfo.InvariantCulture) + "|" + path;
                    if (!identities.Add(key)) continue;
                    int count = Convert.ToInt32(workbook.Worksheets.Count,
                        CultureInfo.InvariantCulture);
                    var sheets = new List<string>();
                    for (var i = 1; i <= count; i++)
                    {
                        dynamic sheet = workbook.Worksheets.Item[i];
                        sheets.Add((string)sheet.Name);
                    }
                    books.Add(new OpenExcelBook
                    {
                        Workbook = comObject, Name = name, FullName = path,
                        ApplicationHwnd = hwnd, Sheets = sheets
                    });
                }
                catch (Exception ex) when (ex is COMException or
                    Microsoft.CSharp.RuntimeBinder.RuntimeBinderException)
                {
                    DiagnosticLog.Write("mailing_excel_discovery", ex.GetType().Name);
                }
            }
        }
        finally
        {
            Marshal.ReleaseComObject(ctx);
            Marshal.ReleaseComObject(rot);
        }
        return books.OrderBy(x => x.DisplayName, StringComparer.CurrentCultureIgnoreCase).ToList();
    }

    private static readonly string[] Required =
    [
        "E-mail работодателя", "ИНН работодателя", "Регион",
        "рассылка по СОУТ", "исх электронка"
    ];

    private static string NormalizeHeader(string? text) =>
        (text ?? "").Trim().Replace('\u00a0', ' ').ToUpperInvariant();

    internal static ExcelSheetSnapshot Read(OpenExcelBook book, string sheetName,
        Action<int, int>? progress = null)
    {
        if (book is null || string.IsNullOrWhiteSpace(sheetName))
            throw new InvalidOperationException("Выберите книгу Excel и лист.");
        dynamic wb = book.Workbook;
        dynamic sheet = wb.Worksheets.Item[sheetName];
        dynamic used = sheet.UsedRange;
        var lastColumn = checked(Convert.ToInt32(used.Column) +
            Convert.ToInt32(used.Columns.Count) - 1);
        var lastRow = checked(Convert.ToInt32(used.Row) +
            Convert.ToInt32(used.Rows.Count) - 1);
        if (lastColumn < 1 || lastColumn > 300)
            throw new InvalidOperationException("Неподдерживаемое число столбцов Excel (1–300).");
        if (lastRow < 1 || lastRow > 100000)
            throw new InvalidOperationException("Слишком много строк Excel (более 100 000).");
        // One bulk COM read preserves unsaved changes and formula results.
        dynamic range = sheet.Range[sheet.Cells[1, 1], sheet.Cells[lastRow, lastColumn]];
        object? value = range.Value2;
        string Cell(int row, int col)
        {
            object? raw = value is Array arr && arr.Rank == 2
                ? arr.GetValue(row - 1 + arr.GetLowerBound(0),
                               col - 1 + arr.GetLowerBound(1))
                : row == 1 && col == 1 ? value : null;
            return raw switch
            {
                null => "",
                string str => str.Trim(),
                double number => number.ToString("0.################", CultureInfo.InvariantCulture),
                _ => Convert.ToString(raw, CultureInfo.InvariantCulture)?.Trim() ?? ""
            };
        }
        var columns = new Dictionary<string, int>(StringComparer.OrdinalIgnoreCase);
        for (var col = 1; col <= lastColumn; col++)
        {
            var label = NormalizeHeader(Cell(1, col));
            if (string.IsNullOrEmpty(label)) continue;
            if (columns.ContainsKey(label))
                throw new InvalidOperationException("Повторяющийся заголовок Excel: " + Cell(1, col));
            columns[label] = col;
        }
        foreach (var required in Required)
            if (!columns.ContainsKey(NormalizeHeader(required)))
                throw new InvalidOperationException("Не найден обязательный столбец: " + required);
        int Idx(string header) => columns[NormalizeHeader(header)];
        var hasName = columns.TryGetValue(NormalizeHeader("Наименование работодателя"),
            out var nameIndex);
        var results = new List<MailingExcelRow>();
        for (var row = 2; row <= lastRow; row++)
        {
            // EntireRow.Hidden also reflects AutoFilter, unlike checking only
            // that the row is currently within the UsedRange.
            bool visible = !Convert.ToBoolean(sheet.Rows[row].Hidden,
                CultureInfo.InvariantCulture);
            var inn = Cell(row, Idx("ИНН работодателя"));
            // With a numeric INN formatted as 000..., Value2 has no leading
            // zeroes; read the rendered cell text for that column only.
            if (inn.Length is not (10 or 12))
            {
                try
                {
                    var rendered = (string)sheet.Cells[row, Idx("ИНН работодателя")].Text;
                    if (rendered.All(char.IsAsciiDigit) && rendered.Length is 10 or 12)
                        inn = rendered;
                }
                catch (COMException) { /* Keep raw number; validation will reject it. */ }
            }
            results.Add(new MailingExcelRow(row, visible,
                Cell(row, Idx("E-mail работодателя")), inn,
                Cell(row, Idx("Регион")),
                Cell(row, Idx("рассылка по СОУТ")),
                hasName ? Cell(row, nameIndex) : "",
                Cell(row, Idx("исх электронка"))));
            if (row % 250 == 0) progress?.Invoke(row, lastRow);
        }
        progress?.Invoke(lastRow, lastRow);
        return new ExcelSheetSnapshot
        {
            Rows = results, Columns = columns,
            WorkbookPath = book.FullName, SheetName = sheetName
        };
    }
}
