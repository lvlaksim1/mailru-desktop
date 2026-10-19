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

        var value = EmailAddressText.ForClipboard(textBox.Text);
        if (value.Length == 0) return;
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

}
