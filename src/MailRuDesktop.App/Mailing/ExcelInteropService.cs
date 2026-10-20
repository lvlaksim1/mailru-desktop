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

        void AddWorkbook(object candidate)
        {
            dynamic workbook = candidate;
            string name = workbook.Name;
            string path = workbook.FullName;
            dynamic excel = workbook.Application;
            int hwnd = Convert.ToInt32(excel.Hwnd, CultureInfo.InvariantCulture);
            var key = hwnd.ToString(CultureInfo.InvariantCulture) + "|" + path;
            if (!identities.Add(key)) return;
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
                Workbook = candidate, Name = name, FullName = path,
                ApplicationHwnd = hwnd, Sheets = sheets
            });
        }

        Marshal.ThrowExceptionForHR(GetRunningObjectTable(0, out var rot));
        Marshal.ThrowExceptionForHR(CreateBindCtx(0, out var ctx));
        try
        {
            rot.EnumRunning(out var enumerator);
            var item = new IMoniker[1];
            while (enumerator.Next(1, item, IntPtr.Zero) == 0)
            {
                string display;
                try { item[0].GetDisplayName(ctx, null, out display); }
                catch (COMException) { continue; }
                // Workbook monikers cover saved workbooks. Excel's application
                // CLSID additionally allows enumeration of unsaved Book1/Книга1
                // and separate Excel instances through their Workbooks collection.
                var appMoniker = display.Contains("00024500",
                    StringComparison.OrdinalIgnoreCase);
                var bookMoniker = display.Contains(".xls", StringComparison.OrdinalIgnoreCase)
                    || display.Contains("!Book", StringComparison.OrdinalIgnoreCase)
                    || display.Contains("!Книга", StringComparison.OrdinalIgnoreCase);
                if (!appMoniker && !bookMoniker) continue;
                try
                {
                    rot.GetObject(item[0], out var comObject);
                    if (appMoniker)
                    {
                        dynamic excel = comObject;
                        int count = Convert.ToInt32(excel.Workbooks.Count,
                            CultureInfo.InvariantCulture);
                        for (var i = 1; i <= count; i++)
                            AddWorkbook((object)excel.Workbooks.Item[i]);
                    }
                    else AddWorkbook(comObject);
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
        return books.OrderBy(x => x.DisplayName,
            StringComparer.CurrentCultureIgnoreCase).ToList();
    }

    private static readonly string[] Required =
    [
        "E-mail работодателя", "ИНН работодателя", "Регион",
        "рассылка по СОУТ", "исх электронка"
    ];

    private static string NormalizeHeader(string? text) =>
        (text ?? "").Trim().Replace('\u00a0', ' ').ToUpperInvariant();

    // xlUp / xlToLeft ignore formatting-only cells, unlike UsedRange.
    private const int XlUp = -4162;
    private const int XlToLeft = -4159;
    private const int RowsPerBatch = 192;

    internal static ExcelRowReader OpenReader(OpenExcelBook book, string sheetName)
    {
        ArgumentNullException.ThrowIfNull(book);
        if (string.IsNullOrWhiteSpace(sheetName))
            throw new InvalidOperationException("Выберите книгу Excel и лист.");

        dynamic wb = book.Workbook;
        dynamic excel = wb.Application;
        if (Convert.ToInt32(excel.Hwnd, CultureInfo.InvariantCulture) != book.ApplicationHwnd ||
            !string.Equals((string)wb.FullName, book.FullName,
                StringComparison.OrdinalIgnoreCase))
            throw new InvalidOperationException(
                "Выбранная книга Excel больше не соответствует открытому экземпляру. Найдите книги повторно.");

        // ActiveWorkbook/ActiveSheet/ActiveCell on Excel.Application refer to
        // the globally active window. With several open books this can be
        // another workbook, even though the chosen Workbook COM object is valid.
        // Resolve the selected cell from a window BELONGING TO that workbook.
        dynamic windows = wb.Windows;
        int windowCount = Convert.ToInt32(windows.Count, CultureInfo.InvariantCulture);
        if (windowCount < 1)
            throw new InvalidOperationException("У выбранной книги Excel нет открытого окна.");
        dynamic selectedWindow = windows.Item[1];
        for (var index = 1; index <= windowCount; index++)
        {
            dynamic candidate = windows.Item[index];
            if (Convert.ToBoolean(candidate.Active, CultureInfo.InvariantCulture))
            {
                selectedWindow = candidate;
                break;
            }
        }
        dynamic activeSheet = selectedWindow.ActiveSheet;
        if (activeSheet is null ||
            !string.Equals((string)activeSheet.Name, sheetName,
                StringComparison.OrdinalIgnoreCase))
            throw new InvalidOperationException(
                "Выделите ячейку на листе «" + sheetName +
                "» выбранной книги Excel и повторите подготовку.");

        dynamic activeCell = selectedWindow.ActiveCell;
        if (activeCell is null)
            throw new InvalidOperationException(
                "Не удалось получить активную ячейку выбранной книги Excel.");
        int startRow = Convert.ToInt32(activeCell.Row, CultureInfo.InvariantCulture);
        startRow = Math.Max(2, startRow); // Row 1 is the mandatory header.

        dynamic sheet = wb.Worksheets.Item[sheetName];
        int lastColumn = Convert.ToInt32(
            sheet.Cells[1, sheet.Columns.Count].End(XlToLeft).Column,
            CultureInfo.InvariantCulture);
        if (lastColumn < 1 || lastColumn > 300)
            throw new InvalidOperationException(
                "Не удалось определить заголовки: более 300 непустых столбцов.");

        // Only the header is read before determining actual data columns.
        dynamic headers = sheet.Range[sheet.Cells[1, 1], sheet.Cells[1, lastColumn]];
        var headerValues = headers.Value2;
        string HeaderCell(int col) => Convert.ToString(lastColumn == 1
                ? headerValues
                : ((Array)headerValues).GetValue(
                    ((Array)headerValues).GetLowerBound(0),
                    col - 1 + ((Array)headerValues).GetLowerBound(1)),
            CultureInfo.InvariantCulture)?.Trim() ?? "";
        var columns = new Dictionary<string, int>(StringComparer.OrdinalIgnoreCase);
        for (var col = 1; col <= lastColumn; col++)
        {
            var raw = HeaderCell(col);
            var label = NormalizeHeader(raw);
            if (label.Length == 0) continue;
            if (!columns.TryAdd(label, col))
                throw new InvalidOperationException(
                    "Повторяющийся заголовок Excel: " + raw);
        }
        foreach (var required in Required)
            if (!columns.ContainsKey(NormalizeHeader(required)))
                throw new InvalidOperationException(
                    "Не найден обязательный столбец: " + required);

        int Idx(string name) => columns[NormalizeHeader(name)];
        // The data boundary is based on actual INN / email cells, not the
        // million formatted-but-empty rows commonly present in Excel.
        int LastNonempty(int col) => Convert.ToInt32(
            sheet.Cells[sheet.Rows.Count, col].End(XlUp).Row,
            CultureInfo.InvariantCulture);
        var lastRow = Math.Max(LastNonempty(Idx("ИНН работодателя")),
                               LastNonempty(Idx("E-mail работодателя")));
        return new ExcelRowReader(sheet, columns, startRow, lastRow, lastColumn,
            RowsPerBatch);
    }

    /// <summary>
    /// Updates only the two agreed columns of an already server-accepted job.
    /// Must run on the same STA dispatcher as the live Excel reader.
    /// Never saves or closes the user's workbook.
    /// </summary>
    internal static void WriteConfirmedResult(
        OpenExcelBook book, string sheetName, MailingJobRecord job)
    {
        ArgumentNullException.ThrowIfNull(book);
        ArgumentNullException.ThrowIfNull(job);
        dynamic workbook = book.Workbook;
        dynamic excel = workbook.Application;
        if (Convert.ToInt32(excel.Hwnd, CultureInfo.InvariantCulture) !=
                book.ApplicationHwnd ||
            !string.Equals((string)workbook.FullName, book.FullName,
                StringComparison.OrdinalIgnoreCase))
            throw new InvalidOperationException(
                "Исходная книга Excel больше недоступна. Повторите подключение к книге.");

        dynamic sheet = workbook.Worksheets.Item[sheetName];
        int lastColumn = Convert.ToInt32(
            sheet.Cells[1, sheet.Columns.Count].End(XlToLeft).Column,
            CultureInfo.InvariantCulture);
        if (lastColumn < 1 || lastColumn > 300)
            throw new InvalidOperationException("Не удалось проверить заголовки Excel.");

        var cols = new Dictionary<string, int>(StringComparer.OrdinalIgnoreCase);
        for (var col = 1; col <= lastColumn; col++)
        {
            var header = NormalizeHeader(Convert.ToString(
                sheet.Cells[1, col].Value2, CultureInfo.InvariantCulture));
            if (header.Length > 0 && !cols.TryAdd(header, col))
                throw new InvalidOperationException("В Excel обнаружены одинаковые заголовки.");
        }
        foreach (var required in Required)
            if (!cols.ContainsKey(NormalizeHeader(required)))
                throw new InvalidOperationException("Не найден столбец Excel: " + required);
        int Index(string name) => cols[NormalizeHeader(name)];

        if (job.RowNumber < 2)
            throw new InvalidOperationException("Некорректный номер исходной строки.");
        dynamic emailCell = sheet.Cells[job.RowNumber, Index("E-mail работодателя")];
        dynamic innCell = sheet.Cells[job.RowNumber, Index("ИНН работодателя")];
        var email = Convert.ToString(emailCell.Value2, CultureInfo.InvariantCulture)?.Trim() ?? "";
        var inn = Convert.ToString(innCell.Text, CultureInfo.InvariantCulture)?.Trim() ?? "";
        if (!string.Equals(inn, job.Inn, StringComparison.Ordinal))
            inn = Convert.ToString(innCell.Value2, CultureInfo.InvariantCulture)?.Trim() ?? "";
        if (!string.Equals(email, job.Email, StringComparison.OrdinalIgnoreCase) ||
            !string.Equals(inn, job.Inn, StringComparison.Ordinal))
            throw new InvalidOperationException(
                "Данные исходной строки Excel изменились. Отметки не записаны.");

        dynamic dateCell = sheet.Cells[job.RowNumber, Index("рассылка по СОУТ")];
        dynamic senderCell = sheet.Cells[job.RowNumber, Index("исх электронка")];
        var dateText = job.ScheduledAt.ToString("dd.MM.yyyy", CultureInfo.InvariantCulture);
        var oldDate = Convert.ToString(dateCell.Value2, CultureInfo.InvariantCulture)?.Trim() ?? "";
        var oldSender = Convert.ToString(senderCell.Value2, CultureInfo.InvariantCulture)?.Trim() ?? "";
        if ((!string.IsNullOrWhiteSpace(oldDate) &&
             !string.Equals(oldDate, dateText, StringComparison.Ordinal)) ||
            (!string.IsNullOrWhiteSpace(oldSender) &&
             !string.Equals(oldSender, job.Sender, StringComparison.OrdinalIgnoreCase)))
            throw new InvalidOperationException(
                "В целевых ячейках Excel уже другие значения. Запись остановлена.");

        if (oldDate.Length == 0)
        {
            dateCell.NumberFormat = "@";
            dateCell.Value2 = dateText;
        }
        if (oldSender.Length == 0)
            senderCell.Value2 = job.Sender;
    }

    internal sealed class ExcelRowReader
    {
        private readonly dynamic _sheet;
        private readonly Dictionary<string, int> _columns;
        private readonly int _lastColumn;
        private readonly int _batchSize;
        private int _nextRow;
        internal int StartRow { get; }
        internal int LastRow { get; }
        internal int NextRow => _nextRow;
        internal bool HasMore => _nextRow <= LastRow;
        internal IReadOnlyDictionary<string, int> Columns => _columns;

        internal ExcelRowReader(object sheet, Dictionary<string, int> columns,
            int startRow, int lastRow, int lastColumn, int batchSize)
        {
            _sheet = sheet;
            _columns = columns;
            StartRow = startRow;
            LastRow = lastRow;
            _nextRow = startRow;
            _lastColumn = lastColumn;
            _batchSize = batchSize;
        }

        internal List<MailingExcelRow> ReadNext(CancellationToken cancellationToken = default)
        {
            var results = new List<MailingExcelRow>();
            if (!HasMore) return results;
            var first = _nextRow;
            var last = Math.Min(LastRow, first + _batchSize - 1);
            _nextRow = last + 1;
            dynamic values = _sheet.Range[
                _sheet.Cells[first, 1], _sheet.Cells[last, _lastColumn]].Value2;
            var source = values as Array;
            string Cell(int row, int col)
            {
                var raw = source is { Rank: 2 }
                    ? source.GetValue(row - first + source.GetLowerBound(0),
                        col - 1 + source.GetLowerBound(1))
                    : row == first && col == 1 ? values : null;
                return raw switch
                {
                    null => "",
                    string text => text.Trim(),
                    double number => number.ToString(
                        "0.################", CultureInfo.InvariantCulture),
                    _ => Convert.ToString(raw, CultureInfo.InvariantCulture)?.Trim() ?? ""
                };
            }
            int Idx(string header) => _columns[NormalizeHeader(header)];
            bool hasName = _columns.TryGetValue(
                NormalizeHeader("Наименование работодателя"), out var nameIndex);
            for (var row = first; row <= last; row++)
            {
                cancellationToken.ThrowIfCancellationRequested();
                // Excel EntireRow.Hidden includes AutoFilter and manual hiding;
                // hidden rows are never materialized as mailing candidates.
                if (Convert.ToBoolean(_sheet.Rows[row].Hidden,
                        CultureInfo.InvariantCulture))
                    continue;
                var inn = Cell(row, Idx("ИНН работодателя"));
                var email = Cell(row, Idx("E-mail работодателя"));
                var mark = Cell(row, Idx("рассылка по СОУТ"));
                if (inn.Length == 0 && email.Length == 0 && mark.Length == 0)
                    continue;
                if (inn.Length is not (10 or 12))
                {
                    // Excel numeric INNs may use a leading-zero number format.
                    try
                    {
                        string rendered = _sheet.Cells[row, Idx("ИНН работодателя")].Text;
                        if (rendered.All(char.IsAsciiDigit) &&
                            rendered.Length is 10 or 12) inn = rendered;
                    }
                    catch (COMException) { /* Validation rejects unrecognized values. */ }
                }
                results.Add(new MailingExcelRow(row, true, email, inn,
                    Cell(row, Idx("Регион")), mark,
                    hasName ? Cell(row, nameIndex) : "",
                    Cell(row, Idx("исх электронка"))));
            }
            return results;
        }
    }
}
