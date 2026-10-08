namespace MailRuDesktop.App;

public partial class MainWindow
{
    private RichComposeEditor? _composeRichEditor;
    private RichComposeEditor? _previewRichEditor;

    private void InitializeRichComposeEditors()
    {
        _composeRichEditor = RichComposeEditor.Attach(ComposeBodyTextBox, this);
        _previewRichEditor = RichComposeEditor.Attach(PreviewComposeBodyTextBox, this, resizable: true);
    }
}
