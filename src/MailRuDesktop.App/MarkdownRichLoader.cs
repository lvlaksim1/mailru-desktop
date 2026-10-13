using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Documents;
using System.Windows.Media;

namespace MailRuDesktop.App;

/// <summary>
/// Renders the subset of Markdown offered by the template toolbar as editable
/// WPF text. HTML is later produced by RichComposeEditor, not sent as raw input.
/// </summary>
internal static class MarkdownRichLoader
{
    private static readonly Regex Inline = new(
        @"\*\*(?<bold>[^*\r\n]+)\*\*|\*(?<italic>[^*\r\n]+)\*|<u>(?<under>.*?)</u>|\[(?<label>[^\]]+)\]\((?<url>https://[^\s)]+)\)",
        RegexOptions.Compiled | RegexOptions.IgnoreCase | RegexOptions.CultureInvariant);

    public static void Load(FlowDocument document, string markdown)
    {
        document.Blocks.Clear();
        Append(document, markdown);
    }

    public static string ToPlain(string markdown)
    {
        var temp = new FlowDocument();
        Load(temp, markdown);
        return new TextRange(temp.ContentStart, temp.ContentEnd)
            .Text.TrimEnd('\r', '\n');
    }

    public static void Append(FlowDocument document, string markdown)
    {
        var lines = (markdown ?? "").Replace("\r\n", "\n", StringComparison.Ordinal)
            .Split('\n');
        foreach (var line in lines)
        {
            var text = line;
            var paragraph = new Paragraph { Margin = new Thickness(0) };
            if (text.StartsWith("# ", StringComparison.Ordinal))
            {
                paragraph.FontSize = 20;
                paragraph.FontWeight = FontWeights.Bold;
                text = text[2..];
            }
            else if (text.StartsWith("- ", StringComparison.Ordinal))
            {
                paragraph.Inlines.Add(new Run("• "));
                text = text[2..];
            }
            var cursor = 0;
            foreach (Match match in Inline.Matches(text))
            {
                if (match.Index > cursor)
                    paragraph.Inlines.Add(new Run(text[cursor..match.Index]));
                if (match.Groups["bold"].Success)
                    paragraph.Inlines.Add(new Bold(new Run(match.Groups["bold"].Value)));
                else if (match.Groups["italic"].Success)
                    paragraph.Inlines.Add(new Italic(new Run(match.Groups["italic"].Value)));
                else if (match.Groups["under"].Success)
                    paragraph.Inlines.Add(new Underline(new Run(match.Groups["under"].Value)));
                else if (match.Groups["label"].Success &&
                         Uri.TryCreate(match.Groups["url"].Value, UriKind.Absolute, out var link) &&
                         link.Scheme == Uri.UriSchemeHttps)
                    paragraph.Inlines.Add(new Hyperlink(new Run(match.Groups["label"].Value))
                        { NavigateUri = link });
                else paragraph.Inlines.Add(new Run(match.Value));
                cursor = match.Index + match.Length;
            }
            if (cursor < text.Length)
                paragraph.Inlines.Add(new Run(text[cursor..]));
            document.Blocks.Add(paragraph);
        }
        if (document.Blocks.Count == 0)
            document.Blocks.Add(new Paragraph { Margin = new Thickness(0) });
    }
}
