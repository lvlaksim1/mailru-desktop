using System.Windows.Documents;

namespace MailRuDesktop.App.Mailing;

/// <summary>
/// One interpretation of the saved Markdown template for on-screen preview,
/// plain text fallback and the HTML sent via aj-https.mail.ru.
/// Uses the same parser and renderer as the built-in rich mail composer.
/// All methods must execute on the WPF STA thread.
/// </summary>
internal static class MailingMarkdownFormatting
{
    internal sealed record Rendered(string PlainText, string Html, FlowDocument Document);

    internal static Rendered Render(string markdown)
    {
        var document = new FlowDocument();
        MarkdownRichLoader.Load(document, markdown);
        var plain = new TextRange(document.ContentStart, document.ContentEnd)
            .Text.TrimEnd('\r', '\n');
        var html = RichComposeEditor.RenderDocumentHtml(document);
        return new Rendered(plain, html, document);
    }
}
