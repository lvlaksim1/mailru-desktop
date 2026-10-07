using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace MailRuDesktop.App;

public partial class MessageWindow
{
    protected override void OnContentRendered(EventArgs e)
    {
        base.OnContentRendered(e);
        FromText.PreviewMouseDoubleClick -= AddressTextBox_PreviewMouseDoubleClick;
        ToText.PreviewMouseDoubleClick -= AddressTextBox_PreviewMouseDoubleClick;
        FromText.PreviewMouseDoubleClick += AddressTextBox_PreviewMouseDoubleClick;
        ToText.PreviewMouseDoubleClick += AddressTextBox_PreviewMouseDoubleClick;
    }

    private void AddressTextBox_PreviewMouseDoubleClick(object sender, MouseButtonEventArgs e)
    {
        if (sender is not TextBox textBox || string.IsNullOrWhiteSpace(textBox.Text))
            return;

        textBox.SelectAll();
        var value = StripAddressLabel(textBox.Text);
        try
        {
            Clipboard.SetText(value);
            StatusText.Text = "Адрес скопирован в буфер обмена.";
        }
        catch
        {
            StatusText.Text = "Не удалось скопировать адрес.";
        }

        e.Handled = true;
    }

    private static string StripAddressLabel(string value)
    {
        var trimmed = value.Trim();
        if (trimmed.StartsWith("От:", StringComparison.CurrentCultureIgnoreCase))
            return trimmed[3..].Trim();
        if (trimmed.StartsWith("Кому:", StringComparison.CurrentCultureIgnoreCase))
            return trimmed[5..].Trim();
        return trimmed;
    }
}
