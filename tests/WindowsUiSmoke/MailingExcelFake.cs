// A deliberately small late-bound Excel model for testing COM reader logic
// without Microsoft Office installed on the Windows CI agent.
public sealed class FakeExcelApplication
{
    public int Hwnd { get; set; } = 43001;
    public FakeExcelWorkbook? ActiveWorkbook { get; set; }
    public FakeExcelSheet? ActiveSheet { get; set; }
    public FakeExcelCell ActiveCell { get; set; } = null!;
}

public sealed class FakeExcelWorkbook
{
    public string Name { get; init; } = "mailing.xlsx";
    public string FullName { get; init; } = @"C:\fixture\mailing.xlsx";
    public FakeExcelApplication Application { get; set; } = null!;
    public FakeExcelWorksheets Worksheets { get; set; } = null!;
}

public sealed class FakeExcelWorksheets
{
    public FakeExcelSheet? Sheet { get; set; }
    public FakeExcelSheetIndexer Item => new(this);
}
public sealed class FakeExcelSheetIndexer(FakeExcelWorksheets owner)
{
    public FakeExcelSheet this[string name] =>
        owner.Sheet is { } sheet && sheet.Name == name
            ? sheet : throw new ArgumentException("No such Excel worksheet");
}

public sealed class FakeExcelSheet
{
    private readonly Dictionary<(int Row, int Col), object> _values = [];
    private readonly HashSet<int> _hidden = [];
    public string Name { get; set; } = "Рабочий лист";
    public FakeExcelCells Cells => new(this);
    public FakeExcelRows Rows => new(this);
    public FakeExcelColumns Columns => new();
    public FakeExcelRanges Range => new(this);

    public void Put(int row, int col, object value) => _values[(row, col)] = value;
    public void Hide(int row) => _hidden.Add(row);
    internal bool Hidden(int row) => _hidden.Contains(row);
    internal object? Get(int row, int col) =>
        _values.TryGetValue((row, col), out var value) ? value : null;
    internal int LastColInRow(int row) =>
        _values.Keys.Where(x => x.Row == row).Select(x => x.Col)
            .DefaultIfEmpty(1).Max();
    internal int LastRowInCol(int col) =>
        _values.Keys.Where(x => x.Col == col).Select(x => x.Row)
            .DefaultIfEmpty(1).Max();
}
public sealed class FakeExcelColumns
{
    public int Count => 16384;
}
public sealed class FakeExcelRows(FakeExcelSheet sheet)
{
    public int Count => 1048576;
    public FakeExcelRow this[int row] => new(sheet.Hidden(row));
}
public sealed class FakeExcelRow(bool hidden)
{
    public bool Hidden => hidden;
}
public sealed class FakeExcelCells(FakeExcelSheet sheet)
{
    public FakeExcelCell this[int row, int column] => new(sheet, row, column);
}
public sealed class FakeExcelCell(FakeExcelSheet sheet, int row, int col)
{
    public int Row => row;
    public int Column => col;
    public object? Value2 => sheet.Get(row, col);
    public string Text => Convert.ToString(Value2,
        System.Globalization.CultureInfo.InvariantCulture) ?? "";
    public FakeExcelCell End(int direction) => direction switch
    {
        -4159 => new FakeExcelCell(sheet, row, sheet.LastColInRow(row)),
        -4162 => new FakeExcelCell(sheet, sheet.LastRowInCol(col), col),
        _ => throw new ArgumentException("Unexpected Excel End direction")
    };
}
public sealed class FakeExcelRanges(FakeExcelSheet sheet)
{
    public FakeExcelRange this[FakeExcelCell first, FakeExcelCell last] =>
        new(sheet, first.Row, first.Column, last.Row, last.Column);
}
public sealed class FakeExcelRange(
    FakeExcelSheet sheet, int firstRow, int firstCol,
    int lastRow, int lastCol)
{
    public object? Value2
    {
        get
        {
            if (firstRow == lastRow && firstCol == lastCol)
                return sheet.Get(firstRow, firstCol);
            var values = new object?[lastRow - firstRow + 1, lastCol - firstCol + 1];
            for (var r = firstRow; r <= lastRow; r++)
                for (var c = firstCol; c <= lastCol; c++)
                    values[r - firstRow, c - firstCol] = sheet.Get(r, c);
            return values;
        }
    }
}
