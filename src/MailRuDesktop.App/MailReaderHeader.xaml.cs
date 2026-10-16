using System.Windows.Controls;
using System.Windows.Input;

namespace MailRuDesktop.App;

/// <summary>
/// Isolated visual header. No browser, network code or message state is owned here.
/// </summary>
public partial class MailReaderHeader : UserControl
{
    public MailReaderHeader() => InitializeComponent();

    public TextBox SubjectText => SelectedSubjectText;
    public TextBox SenderNameText => SelectedSenderNameText;
    public TextBox SenderAddressText => SelectedSenderText;
    public TextBox RecipientAddressText => SelectedToText;
    public TextBox DateText => SelectedDateText;
    public TextBox MetaText => SelectedMetaText;

    public event MouseButtonEventHandler? AddressDoubleClick;

    private void AddressTextBox_PreviewMouseDoubleClick(object sender, MouseButtonEventArgs e) =>
        AddressDoubleClick?.Invoke(sender, e);
}
