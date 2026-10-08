using System.Net;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;

namespace MailRuDesktop.App;

/// <summary>
/// A rich mail editor that keeps the legacy plain TextBox synchronized so
/// existing replies, signatures, templates and drafts preserve their behavior.
/// Outgoing requests include both plain text and generated safe HTML.
/// </summary>
internal sealed class RichComposeEditor
{
    private readonly TextBox _plain;
    private readonly RichTextBox _rich;
    private bool _syncing;

    private RichComposeEditor(TextBox plain, RichTextBox rich)
    {
        _plain = plain;
        _rich = rich;
        _plain.TextChanged += (_, _) => SyncFromPlain();
        _rich.TextChanged += (_, _) => SyncFromRich();
        SyncFromPlain();
    }

    public static RichComposeEditor Attach(TextBox plain, Window owner, bool resizable = false)
    {
        if (plain.Parent is not Grid parent)
            throw new InvalidOperationException("Mail body needs a grid container.");

        var host = new Grid { Margin = plain.Margin };
        host.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        host.RowDefinitions.Add(new RowDefinition { Height = new GridLength(1, GridUnitType.Star) });
        if (resizable)
            host.RowDefinitions.Add(new RowDefinition { Height = new GridLength(9) });

        Grid.SetRow(host, Grid.GetRow(plain));
        Grid.SetColumn(host, Grid.GetColumn(plain));
        Grid.SetRowSpan(host, Grid.GetRowSpan(plain));
        Grid.SetColumnSpan(host, Grid.GetColumnSpan(plain));
        Panel.SetZIndex(host, Panel.GetZIndex(plain) + 1);

        var toolbar = new WrapPanel { Margin = new Thickness(0, 0, 0, 4) };
        var rich = new RichTextBox
        {
            Document = new FlowDocument(new Paragraph()),
            VerticalScrollBarVisibility = ScrollBarVisibility.Auto,
            AcceptsTab = true,
            Padding = new Thickness(8),
            MinHeight = Math.Max(90, plain.MinHeight > 0 ? plain.MinHeight - 36 : 90)
        };
        rich.SetResourceReference(Control.BackgroundProperty, "AppInputBrush");
        rich.SetResourceReference(Control.ForegroundProperty, "AppTextBrush");
        rich.SetResourceReference(Control.BorderBrushProperty, "AppBorderBrush");
        rich.Document.PagePadding = new Thickness(0);
        // Normal Enter creates a paragraph. All paragraphs, including ones
        // inserted through the keyboard, must have zero outside spacing.
        var paragraphStyle = new Style(typeof(Paragraph));
        paragraphStyle.Setters.Add(new Setter(Block.MarginProperty, new Thickness(0)));
        rich.Document.Resources[typeof(Paragraph)] = paragraphStyle;
        foreach (var paragraph in rich.Document.Blocks.OfType<Paragraph>())
            paragraph.Margin = new Thickness(0);

        var editor = new RichComposeEditor(plain, rich);
        editor.CreateToolbar(toolbar, owner);
        host.Children.Add(toolbar);
        Grid.SetRow(rich, 1);
        host.Children.Add(rich);

        if (resizable)
        {
            // Resize the actual editor Grid row instead of moving or overlaying
            // the reply toolbar and the existing send/attachment controls.
            var grip = new Thumb
            {
                Height = 8,
                Cursor = Cursors.SizeNS,
                ToolTip = "Изменить высоту области текста",
                HorizontalAlignment = HorizontalAlignment.Stretch,
                VerticalAlignment = VerticalAlignment.Center
            };
            grip.SetResourceReference(Control.BackgroundProperty, "AppBorderBrush");
            Grid.SetRow(grip, 2);
            grip.DragDelta += (_, e) =>
            {
                var rowIndex = Grid.GetRow(plain);
                if (rowIndex >= parent.RowDefinitions.Count)
                    return;
                var row = parent.RowDefinitions[rowIndex];
                var next = Math.Clamp(row.ActualHeight + e.VerticalChange, 150, 550);
                row.Height = new GridLength(next, GridUnitType.Pixel);
            };
            host.Children.Add(grip);
        }

        plain.Visibility = Visibility.Collapsed;
        parent.Children.Add(host);
        return editor;
    }

    public void Focus() => _rich.Focus();

    public string ToHtml()
    {
        var builder = new StringBuilder();
        builder.Append("<div style=\"font-family:Segoe UI,Arial,sans-serif;white-space:normal;\">");
        RenderBlocks(_rich.Document.Blocks, builder);
        builder.Append("</div>");
        return builder.ToString();
    }

    private void CreateToolbar(Panel toolbar, Window owner)
    {
        Add(toolbar, "Ж", "Полужирный", () => Toggle(TextElement.FontWeightProperty,
            FontWeights.Bold, FontWeights.Normal));
        Add(toolbar, "К", "Курсив", () => Toggle(TextElement.FontStyleProperty,
            FontStyles.Italic, FontStyles.Normal));
        Add(toolbar, "Ч", "Подчёркивание", () =>
            EditingCommands.ToggleUnderline.Execute(null, _rich));
        Add(toolbar, "̶S", "Зачёркивание", () => Toggle(Inline.TextDecorationsProperty,
            TextDecorations.Strikethrough, new TextDecorationCollection()));
        Add(toolbar, "•", "Маркированный список", () =>
            EditingCommands.ToggleBullets.Execute(null, _rich));
        Add(toolbar, "1.", "Нумерованный список", () =>
            EditingCommands.ToggleNumbering.Execute(null, _rich));
        Add(toolbar, "≡", "Выравнивание слева", () =>
            EditingCommands.AlignLeft.Execute(null, _rich));
        Add(toolbar, "≡·", "По центру", () =>
            EditingCommands.AlignCenter.Execute(null, _rich));
        Add(toolbar, "·≡", "Выравнивание справа", () =>
            EditingCommands.AlignRight.Execute(null, _rich));
        Add(toolbar, "↗", "Вставить ссылку", () =>
        {
            if (_rich.Selection.IsEmpty)
            {
                AppDialog.Info(owner, "Ссылка", "Сначала выделите текст для ссылки.");
                return;
            }

            var dialog = new TextPromptWindow(owner, "Вставить ссылку",
                "Адрес ссылки (https:// или mailto:)", "https://");
            if (dialog.ShowDialog() != true)
                return;

            if (!Uri.TryCreate(dialog.Value, UriKind.Absolute, out var uri) ||
                uri.Scheme is not ("https" or "http" or "mailto"))
            {
                AppDialog.Info(owner, "Ссылка", "Допустимы только http, https и mailto.");
                return;
            }

            var link = new Hyperlink(_rich.Selection.Start, _rich.Selection.End)
            {
                NavigateUri = uri
            };
            _rich.Focus();
        });
        Add(toolbar, "A▾", "Цвет текста", () =>
        {
            var chooser = new PaletteColorPickerWindow(owner,
                "Цвет выделенного текста", ThemeManager.GetHex("AppTextBrush"));
            if (chooser.ShowDialog() != true)
                return;
            _rich.Selection.ApplyPropertyValue(TextElement.ForegroundProperty,
                new SolidColorBrush((Color)ColorConverter.ConvertFromString(chooser.SelectedHex)));
            _rich.Focus();
        });
    }

    private static void Add(Panel toolbar, string caption, string tip, Action action)
    {
        var button = new Button
        {
            Content = caption, ToolTip = tip, Padding = new Thickness(7, 3, 7, 3),
            MinWidth = 26, Height = 27, Margin = new Thickness(0, 0, 3, 0),
            FontSize = 12
        };
        button.Click += (_, _) => action();
        toolbar.Children.Add(button);
    }

    private void Toggle(DependencyProperty property, object enabled, object? disabled)
    {
        var value = _rich.Selection.GetPropertyValue(property);
        _rich.Selection.ApplyPropertyValue(property,
            Equals(value, enabled) ? disabled ?? enabled : enabled);
        _rich.Focus();
    }

    private void SyncFromPlain()
    {
        if (_syncing) return;
        _syncing = true;
        try
        {
            _rich.Document.Blocks.Clear();
            _rich.Document.Blocks.Add(new Paragraph(new Run(_plain.Text ?? ""))
            {
                Margin = new Thickness(0)
            });
        }
        finally { _syncing = false; }
    }

    private void SyncFromRich()
    {
        if (_syncing) return;
        _syncing = true;
        try
        {
            _plain.Text = new TextRange(_rich.Document.ContentStart, _rich.Document.ContentEnd)
                .Text.TrimEnd('\r', '\n');
        }
        finally { _syncing = false; }
    }

    private static void RenderBlocks(BlockCollection blocks, StringBuilder output)
    {
        foreach (Block block in blocks)
        {
            switch (block)
            {
                case Paragraph paragraph:
                    var align = paragraph.TextAlignment switch
                    {
                        TextAlignment.Center => "center",
                        TextAlignment.Right => "right",
                        TextAlignment.Justify => "justify",
                        _ => "left"
                    };
                    output.Append("<p style=\"margin:0;line-height:normal;text-align:")
                        .Append(align).Append(";\">");
                    RenderInlines(paragraph.Inlines, output);
                    output.Append("</p>");
                    break;
                case System.Windows.Documents.List list:
                    var ordered = list.MarkerStyle is TextMarkerStyle.Decimal or
                        TextMarkerStyle.LowerLatin or TextMarkerStyle.UpperLatin;
                    var tag = ordered ? "ol" : "ul";
                    output.Append('<').Append(tag).Append('>');
                    foreach (var item in list.ListItems)
                    {
                        output.Append("<li>");
                        RenderBlocks(item.Blocks, output);
                        output.Append("</li>");
                    }
                    output.Append("</").Append(tag).Append('>');
                    break;
                case Section section:
                    RenderBlocks(section.Blocks, output);
                    break;
            }
        }
    }

    private static void RenderInlines(InlineCollection inlines, StringBuilder output)
    {
        foreach (Inline inline in inlines)
        {
            string? tag = inline switch
            {
                Bold => "strong",
                Italic => "em",
                Underline => "u",
                Hyperlink => "a",
                Span => "span",
                _ => null
            };
            if (inline is Run run)
            {
                var text = WebUtility.HtmlEncode(run.Text)
                    .Replace("\r\n", "<br>", StringComparison.Ordinal)
                    .Replace("\n", "<br>", StringComparison.Ordinal);
                var styles = new List<string>();
                if (run.FontWeight == FontWeights.Bold) styles.Add("font-weight:bold");
                if (run.FontStyle == FontStyles.Italic) styles.Add("font-style:italic");
                if (run.TextDecorations?.Contains(TextDecorations.Strikethrough[0]) == true)
                    styles.Add("text-decoration:line-through");
                if (run.ReadLocalValue(TextElement.ForegroundProperty) is SolidColorBrush brush)
                    styles.Add($"color:#{brush.Color.R:X2}{brush.Color.G:X2}{brush.Color.B:X2}");
                if (styles.Count > 0)
                    output.Append("<span style=\"").Append(string.Join(";", styles)).Append("\">");
                output.Append(text);
                if (styles.Count > 0) output.Append("</span>");
            }
            else if (inline is LineBreak)
                output.Append("<br>");
            else if (inline is Span span)
            {
                var url = inline is Hyperlink link && link.NavigateUri is not null
                    ? link.NavigateUri.ToString() : null;
                if (tag == "a" && url is not null)
                    output.Append("<a href=\"").Append(WebUtility.HtmlEncode(url)).Append("\">");
                else
                    output.Append('<').Append(tag ?? "span").Append('>');
                RenderInlines(span.Inlines, output);
                output.Append("</").Append(tag ?? "span").Append('>');
            }
        }
    }
}
