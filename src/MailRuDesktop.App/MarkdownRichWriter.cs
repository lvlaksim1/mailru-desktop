using System.Text;
using System.Windows;
using System.Windows.Documents;
using System.Windows.Media;

namespace MailRuDesktop.App;

/// <summary>
/// Persist editable WPF formatting as Markdown, with safe inline HTML for
/// styles Markdown cannot express. This is also used by the compose editor
/// when authoring file-backed templates.
/// </summary>
internal static class MarkdownRichWriter
{
    public static string Write(FlowDocument document)
    {
        var result = new List<string>();
        foreach (Block block in document.Blocks)
            WriteBlock(block, result, null);
        return string.Join("\n", result).TrimEnd('\r', '\n');
    }

    private static void WriteBlock(Block block, List<string> lines, string? prefix)
    {
        if (block is Section section)
        {
            foreach (Block inner in section.Blocks)
                WriteBlock(inner, lines, prefix);
        }
        else if (block is System.Windows.Documents.List list)
        {
            var ordered = list.MarkerStyle is TextMarkerStyle.Decimal
                or TextMarkerStyle.LowerLatin or TextMarkerStyle.UpperLatin;
            var number = 1;
            foreach (var item in list.ListItems)
            {
                foreach (Block itemBlock in item.Blocks)
                    WriteBlock(itemBlock, lines,
                        ordered ? number.ToString(System.Globalization.CultureInfo.InvariantCulture) + ". " : "- ");
                number++;
            }
        }
        else if (block is Paragraph paragraph)
        {
            var text = new StringBuilder();
            foreach (Inline inline in paragraph.Inlines)
                WriteInline(inline, text, false, false);
            var line = text.ToString();
            if (line.StartsWith("• ", StringComparison.Ordinal))
                line = "- " + line[2..];
            if (paragraph.FontSize >= 18 && paragraph.FontWeight == FontWeights.Bold)
                line = "# " + line;
            if (prefix is not null)
                line = prefix + line;
            var alignment = paragraph.TextAlignment;
            if (alignment is TextAlignment.Center or TextAlignment.Right or TextAlignment.Justify)
            {
                var align = alignment switch
                {
                    TextAlignment.Center => "center",
                    TextAlignment.Right => "right",
                    _ => "justify"
                };
                line = "<div align=\"" + align + "\">" + line + "</div>";
            }
            lines.Add(line);
        }
    }

    private static void WriteInline(Inline inline, StringBuilder output, bool parentBold, bool parentItalic)
    {
        switch (inline)
        {
            case Hyperlink link when link.NavigateUri is not null:
                output.Append('[');
                foreach (Inline child in link.Inlines)
                    WriteInline(child, output, false, false);
                output.Append("](").Append(link.NavigateUri.AbsoluteUri).Append(')');
                break;
            case Bold bold:
                output.Append("**");
                foreach (Inline child in bold.Inlines)
                    WriteInline(child, output, true, parentItalic);
                output.Append("**");
                break;
            case Italic italic:
                output.Append('*');
                foreach (Inline child in italic.Inlines)
                    WriteInline(child, output, parentBold, true);
                output.Append('*');
                break;
            case Underline underline:
                output.Append("<u>");
                foreach (Inline child in underline.Inlines)
                    WriteInline(child, output, parentBold, parentItalic);
                output.Append("</u>");
                break;
            case Span span:
                foreach (Inline child in span.Inlines)
                    WriteInline(child, output, parentBold, parentItalic);
                break;
            case LineBreak:
                output.Append("\n");
                break;
            case Run run:
                var value = run.Text;
                var boldRun = !parentBold && run.ReadLocalValue(TextElement.FontWeightProperty)
                    is FontWeight weight && weight == FontWeights.Bold;
                var italicRun = !parentItalic && run.ReadLocalValue(TextElement.FontStyleProperty)
                    is FontStyle style && style == FontStyles.Italic;
                var strike = run.TextDecorations?.Contains(TextDecorations.Strikethrough[0]) == true;
                var color = run.ReadLocalValue(TextElement.ForegroundProperty) is SolidColorBrush brush
                    ? $"#{brush.Color.R:X2}{brush.Color.G:X2}{brush.Color.B:X2}" : null;
                if (boldRun) output.Append("**");
                if (italicRun) output.Append('*');
                if (strike) output.Append("<s>");
                if (color is not null) output.Append("<span style=\"color:").Append(color).Append("\">");
                output.Append(value);
                if (color is not null) output.Append("</span>");
                if (strike) output.Append("</s>");
                if (italicRun) output.Append('*');
                if (boldRun) output.Append("**");
                break;
        }
    }
}
