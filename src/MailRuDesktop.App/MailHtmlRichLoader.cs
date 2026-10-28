using System.Globalization;
using System.Net;
using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Media;
using HtmlAgilityPack;

namespace MailRuDesktop.App;

/// <summary>
/// Safely converts real-world HTML mail to editable WPF blocks without
/// flattening inline styles, quotes, lists, tables or safe hyperlinks.
/// External images are represented by placeholders: composing never fetches
/// tracking resources; safe original image references remain in outgoing HTML.
/// </summary>
internal static class MailHtmlRichLoader
{
    internal sealed record ForwardedImage(string Source, string Alternative);

    private static readonly HashSet<string> Forbidden = new(StringComparer.OrdinalIgnoreCase)
    {
        "script", "style", "head", "title", "meta", "link", "iframe", "frame",
        "object", "embed", "video", "audio", "svg", "math", "form", "input",
        "button", "textarea", "select", "noscript", "canvas"
    };
    private static readonly HashSet<string> BlockTags = new(StringComparer.OrdinalIgnoreCase)
    {
        "p", "div", "section", "article", "blockquote", "ul", "ol", "table",
        "h1", "h2", "h3", "h4", "h5", "h6", "pre", "hr"
    };

    internal static void Append(BlockCollection blocks, string html)
    {
        if (string.IsNullOrWhiteSpace(html)) return;
        var document = new HtmlDocument { OptionFixNestedTags = true };
        document.LoadHtml(html);
        var root = document.DocumentNode.SelectSingleNode("//body") ?? document.DocumentNode;
        foreach (var child in root.ChildNodes) AddBlock(blocks, child);
    }

    private static void AddBlock(BlockCollection blocks, HtmlNode node)
    {
        if (node.NodeType == HtmlNodeType.Comment || Forbidden.Contains(node.Name)) return;
        if (node.NodeType == HtmlNodeType.Text && string.IsNullOrWhiteSpace(node.InnerText)) return;
        var name = node.Name.ToLowerInvariant();
        if (name == "blockquote")
        {
            var quote = new Section
            {
                Tag = "mail-html-quote",
                Margin = new Thickness(9, 5, 0, 5),
                Padding = new Thickness(9, 0, 0, 0),
                BorderBrush = Brushes.Gray,
                BorderThickness = new Thickness(2, 0, 0, 0)
            };
            foreach (var child in node.ChildNodes) AddBlock(quote.Blocks, child);
            if (quote.Blocks.FirstBlock is null) quote.Blocks.Add(new Paragraph());
            blocks.Add(quote);
            return;
        }
        if (name is "ul" or "ol")
        {
            var list = new System.Windows.Documents.List
            {
                MarkerStyle = name == "ol" ? TextMarkerStyle.Decimal : TextMarkerStyle.Disc,
                Margin = new Thickness(18, 0, 0, 0)
            };
            foreach (var child in node.ChildNodes.Where(x => x.Name == "li"))
            {
                var item = new ListItem();
                if (child.ChildNodes.Any(c => BlockTags.Contains(c.Name)))
                    foreach (var inner in child.ChildNodes) AddBlock(item.Blocks, inner);
                else item.Blocks.Add(CreateParagraph(child));
                if (item.Blocks.FirstBlock is null) item.Blocks.Add(new Paragraph());
                list.ListItems.Add(item);
            }
            if (list.ListItems.Count > 0) blocks.Add(list);
            return;
        }
        if (name == "table")
        {
            var table = new Table
            {
                CellSpacing = 0,
                BorderBrush = Brushes.Gray,
                BorderThickness = new Thickness(1),
                Margin = new Thickness(0, 3, 0, 3)
            };
            var group = new TableRowGroup();
            foreach (var rowNode in node.Descendants("tr"))
            {
                var row = new TableRow();
                foreach (var cellNode in rowNode.ChildNodes.Where(x => x.Name is "td" or "th"))
                {
                    var cell = new TableCell
                    {
                        BorderBrush = Brushes.Gray,
                        BorderThickness = new Thickness(0.5),
                        Padding = new Thickness(4)
                    };
                    if (int.TryParse(cellNode.GetAttributeValue("colspan", "1"), out var span))
                        cell.ColumnSpan = Math.Clamp(span, 1, 32);
                    if (cellNode.ChildNodes.Any(c => BlockTags.Contains(c.Name)))
                        foreach (var child in cellNode.ChildNodes) AddBlock(cell.Blocks, child);
                    else cell.Blocks.Add(CreateParagraph(cellNode));
                    if (cell.Blocks.FirstBlock is null) cell.Blocks.Add(new Paragraph());
                    if (cellNode.Name == "th")
                        foreach (var paragraph in cell.Blocks.OfType<Paragraph>())
                            paragraph.FontWeight = FontWeights.Bold;
                    row.Cells.Add(cell);
                }
                if (row.Cells.Count > 0) group.Rows.Add(row);
            }
            if (group.Rows.Count > 0)
            {
                table.RowGroups.Add(group);
                blocks.Add(table);
            }
            return;
        }
        if (name == "hr")
        {
            blocks.Add(new Paragraph(new Run("────────")) { Margin = new Thickness(0) });
            return;
        }
        if (name is "div" or "section" or "article" or "html" or "body" or "#document")
        {
            if (node.ChildNodes.Any(child => BlockTags.Contains(child.Name)))
            {
                foreach (var child in node.ChildNodes) AddBlock(blocks, child);
                return;
            }
        }
        blocks.Add(CreateParagraph(node));
    }

    private static Paragraph CreateParagraph(HtmlNode node)
    {
        var p = new Paragraph { Margin = new Thickness(0) };
        ApplyStyle(p, node);
        if (node.Name is "h1" or "h2" or "h3" or "h4" or "h5" or "h6")
        {
            p.FontWeight = FontWeights.Bold;
            if (p.ReadLocalValue(TextElement.FontSizeProperty) == DependencyProperty.UnsetValue)
                p.FontSize = node.Name is "h1" or "h2" ? 20 : 16;
        }
        if (node.NodeType == HtmlNodeType.Text) AddInline(p.Inlines, node);
        else foreach (var child in node.ChildNodes) AddInline(p.Inlines, child);
        return p;
    }

    private static void AddInline(InlineCollection inlines, HtmlNode node)
    {
        if (node.NodeType == HtmlNodeType.Comment || Forbidden.Contains(node.Name)) return;
        if (node.NodeType == HtmlNodeType.Text)
        {
            var value = HtmlEntity.DeEntitize(node.InnerText).Replace('\u00a0', ' ');
            if (value.Length > 0) inlines.Add(new Run(value));
            return;
        }
        if (node.Name == "br")
        {
            inlines.Add(new LineBreak());
            return;
        }
        if (node.Name == "img")
        {
            var source = WebUtility.HtmlDecode(node.GetAttributeValue("src", ""));
            var alt = HtmlEntity.DeEntitize(node.GetAttributeValue("alt", ""));
            if (Uri.TryCreate(source, UriKind.Absolute, out var uri) &&
                uri.Scheme is "https" or "http" or "cid")
                inlines.Add(new InlineUIContainer(new TextBlock
                {
                    Text = string.IsNullOrWhiteSpace(alt) ? "[Изображение]" : "[" + alt + "]"
                }) { Tag = new ForwardedImage(source, alt) });
            return;
        }
        if (BlockTags.Contains(node.Name))
        {
            foreach (var child in node.ChildNodes) AddInline(inlines, child);
            if (node.Name is "p" or "div") inlines.Add(new LineBreak());
            return;
        }
        Span span;
        if (node.Name == "a")
        {
            var href = WebUtility.HtmlDecode(node.GetAttributeValue("href", ""));
            if (Uri.TryCreate(href, UriKind.Absolute, out var uri) &&
                uri.Scheme is "http" or "https" or "mailto")
                span = new Hyperlink { NavigateUri = uri };
            else span = new Span();
        }
        else span = new Span();
        switch (node.Name)
        {
            case "strong": case "b": span.FontWeight = FontWeights.Bold; break;
            case "em": case "i": span.FontStyle = FontStyles.Italic; break;
            case "u": span.TextDecorations = TextDecorations.Underline; break;
            case "del": case "s": case "strike":
                span.TextDecorations = TextDecorations.Strikethrough; break;
        }
        ApplyStyle(span, node);
        foreach (var child in node.ChildNodes) AddInline(span.Inlines, child);
        inlines.Add(span);
    }

    private static void ApplyStyle(TextElement element, HtmlNode node)
    {
        foreach (var declaration in node.GetAttributeValue("style", "").Split(';'))
        {
            var split = declaration.IndexOf(':');
            if (split < 0) continue;
            var key = declaration[..split].Trim().ToLowerInvariant();
            var value = declaration[(split + 1)..].Trim().ToLowerInvariant();
            switch (key)
            {
                case "font-weight" when value is "bold" or "bolder" or "600" or "700" or "800" or "900":
                    element.FontWeight = FontWeights.Bold; break;
                case "font-style" when value is "italic" or "oblique":
                    element.FontStyle = FontStyles.Italic; break;
                case "text-decoration" or "text-decoration-line" when value.Contains("underline"):
                    if (element is Inline underline) underline.TextDecorations = TextDecorations.Underline;
                    break;
                case "text-decoration" or "text-decoration-line" when value.Contains("line-through"):
                    if (element is Inline strike) strike.TextDecorations = TextDecorations.Strikethrough;
                    break;
                case "font-size":
                    var size = Regex.Match(value, @"^(\d+(?:\.\d+)?)(px|pt)?$");
                    if (size.Success && double.TryParse(size.Groups[1].Value,
                            NumberStyles.Float, CultureInfo.InvariantCulture, out var number))
                        element.FontSize = Math.Clamp(number *
                            (size.Groups[2].Value == "pt" ? 96d / 72d : 1d), 8d, 72d);
                    break;
                case "color":
                    if (TryBrush(value, out var brush)) element.Foreground = brush;
                    break;
                case "background-color":
                    if (TryBrush(value, out var background)) element.Background = background;
                    break;
                case "text-align" when element is Paragraph paragraph:
                    paragraph.TextAlignment = value switch
                    {
                        "center" => TextAlignment.Center,
                        "right" => TextAlignment.Right,
                        "justify" => TextAlignment.Justify,
                        _ => TextAlignment.Left
                    };
                    break;
            }
        }
        if (node.Name == "font" && TryBrush(node.GetAttributeValue("color", ""), out var fontColor))
            element.Foreground = fontColor;
        if (element is Paragraph p)
        {
            var align = node.GetAttributeValue("align", "");
            if (align.Equals("center", StringComparison.OrdinalIgnoreCase))
                p.TextAlignment = TextAlignment.Center;
            else if (align.Equals("right", StringComparison.OrdinalIgnoreCase))
                p.TextAlignment = TextAlignment.Right;
        }
    }

    private static bool TryBrush(string text, out SolidColorBrush brush)
    {
        brush = Brushes.Transparent;
        var value = text.Trim();
        var rgb = Regex.Match(value, @"^rgb\(\s*(\d{1,3})\s*,\s*(\d{1,3})\s*,\s*(\d{1,3})\s*\)$");
        if (rgb.Success)
        {
            var channels = Enumerable.Range(1, 3)
                .Select(i => int.Parse(rgb.Groups[i].Value, CultureInfo.InvariantCulture)).ToArray();
            if (channels.All(x => x <= 255))
            {
                brush = new SolidColorBrush(Color.FromRgb(
                    (byte)channels[0], (byte)channels[1], (byte)channels[2]));
                return true;
            }
        }
        if (!Regex.IsMatch(value, @"^#[0-9a-fA-F]{6}$")) return false;
        brush = new SolidColorBrush((Color)ColorConverter.ConvertFromString(value));
        return true;
    }
}
