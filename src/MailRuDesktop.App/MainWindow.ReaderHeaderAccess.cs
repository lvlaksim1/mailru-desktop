using System.Windows.Controls;

namespace MailRuDesktop.App;

// Keep callers and their observable state unchanged while relocating only
// their visual ownership. The persistent WebView2 remains in MainWindow.xaml.
public partial class MainWindow
{
    private TextBlock SelectedSubjectText => ReaderHeader.SubjectText;
    private TextBlock SelectedSenderNameText => ReaderHeader.SenderNameText;
    private TextBox SelectedSenderText => ReaderHeader.SenderAddressText;
    private TextBox SelectedToText => ReaderHeader.RecipientAddressText;
    private TextBlock SelectedDateText => ReaderHeader.DateText;
    private TextBlock SelectedMetaText => ReaderHeader.MetaText;
}
