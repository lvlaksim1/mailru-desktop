using System.Windows.Controls;

namespace MailRuDesktop.App;

// Keep callers and their observable state unchanged while relocating only
// their visual ownership. The persistent WebView2 remains in MainWindow.xaml.
public partial class MainWindow
{
    private TextBox SelectedSubjectText => ReaderHeader.SubjectText;
    private TextBox SelectedSenderNameText => ReaderHeader.SenderNameText;
    private TextBox SelectedSenderText => ReaderHeader.SenderAddressText;
    private TextBox SelectedToText => ReaderHeader.RecipientAddressText;
    private TextBox SelectedDateText => ReaderHeader.DateText;
    private TextBox SelectedMetaText => ReaderHeader.MetaText;
}
