using System.Globalization;
using System.Windows.Data;

namespace MailRuDesktop.App;

internal sealed class MailDateConverter : IValueConverter
{
    public object Convert(object value, Type targetType, object parameter, CultureInfo culture) =>
        Format(value, "dd.MM.yy");

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture) =>
        Binding.DoNothing;

    private static string Format(object value, string format)
    {
        if (value is not long unix)
            return string.Empty;

        try
        {
            return DateTimeOffset.FromUnixTimeSeconds(unix).ToLocalTime().ToString(format);
        }
        catch
        {
            return string.Empty;
        }
    }
}

internal sealed class MailTimeConverter : IValueConverter
{
    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        if (value is not long unix)
            return string.Empty;

        try
        {
            return DateTimeOffset.FromUnixTimeSeconds(unix).ToLocalTime().ToString("HH:mm");
        }
        catch
        {
            return string.Empty;
        }
    }

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture) =>
        Binding.DoNothing;
}

internal sealed class SenderLineConverter : IMultiValueConverter
{
    public object Convert(object[] values, Type targetType, object parameter, CultureInfo culture)
    {
        var email = values.Length > 0 ? values[0]?.ToString()?.Trim() ?? string.Empty : string.Empty;
        var name = values.Length > 1 ? values[1]?.ToString()?.Trim() ?? string.Empty : string.Empty;

        if (string.IsNullOrWhiteSpace(email))
            return name;
        if (string.IsNullOrWhiteSpace(name) || string.Equals(email, name, StringComparison.OrdinalIgnoreCase))
            return email;
        return $"{email}, {name}";
    }

    public object[] ConvertBack(object value, Type[] targetTypes, object parameter, CultureInfo culture) =>
        targetTypes.Select(_ => Binding.DoNothing).ToArray();
}

internal sealed class FirstLineConverter : IValueConverter
{
    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        var text = value?.ToString() ?? string.Empty;
        if (string.IsNullOrWhiteSpace(text))
            return string.Empty;

        foreach (var line in text.Replace("\r\n", "\n", StringComparison.Ordinal).Split('\n'))
        {
            var trimmed = line.Trim();
            if (trimmed.Length > 0)
                return trimmed;
        }

        return string.Empty;
    }

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture) =>
        Binding.DoNothing;
}
