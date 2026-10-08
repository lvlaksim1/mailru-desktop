using System.ComponentModel;
using System.Globalization;
using System.Windows.Data;

namespace MailRuDesktop.App;

public sealed class MailPreviewState : INotifyPropertyChanged
{
    public static MailPreviewState Instance { get; } = new();
    private string? _activeId;

    public string? ActiveId
    {
        get => _activeId;
        set
        {
            if (string.Equals(_activeId, value, StringComparison.Ordinal))
                return;
            _activeId = value;
            PropertyChanged?.Invoke(this,
                new PropertyChangedEventArgs(nameof(ActiveId)));
        }
    }

    public event PropertyChangedEventHandler? PropertyChanged;
}

public sealed class MailActivePreviewConverter : IMultiValueConverter
{
    public object Convert(object[] values, Type targetType, object parameter,
        CultureInfo culture) =>
        values.Length >= 2 &&
        values[0] is string id && values[1] is string active &&
        string.Equals(id, active, StringComparison.Ordinal);

    public object[] ConvertBack(object value, Type[] targetTypes,
        object parameter, CultureInfo culture) =>
        targetTypes.Select(_ => Binding.DoNothing).ToArray();
}
