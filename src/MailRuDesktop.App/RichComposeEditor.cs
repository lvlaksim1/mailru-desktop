using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Threading;
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
    private bool _autoLinking;
    private static readonly Regex UrlPattern = new(
        @"(?<![\w@])(?:https?://|www\.)[^\s<>]+",
        RegexOptions.IgnoreCase | RegexOptions.CultureInvariant | RegexOptions.Compiled);
    // A reply owns three independent document regions. Template/signature
    // commands are directed at their regions, never at the current caret.
    private Section? _replyBody;
    private Section? _replySignature;
    private Section? _replyHistory;

    // Formatting changes must mark a template dirty even when the plain
    // text does not change (e.g. applying bold or a text color).
    public event EventHandler? Changed;

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
            // The visible outline belongs to the shared editor frame.
            BorderThickness = new Thickness(0),
            FocusVisualStyle = null,
            Padding = new Thickness(8),
            MinHeight = Math.Max(90, plain.MinHeight > 0 ? plain.MinHeight - 36 : 90)
        };
        // Use the same typography as the other MailRu Desktop editing controls.
        // The body-size resource follows the user's saved interface font size.
        rich.FontFamily = new FontFamily("Segoe UI");
        rich.SetResourceReference(Control.FontSizeProperty, "AppFontBodySize");
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

        // A single frame is used in new mail, replies, forwarding and
        // the standalone template editor, including both visual themes.
        var textFrame = new Border
        {
            CornerRadius = new CornerRadius(5),
            BorderThickness = new Thickness(1),
            ClipToBounds = true,
            Child = rich
        };
        textFrame.SetResourceReference(Border.BorderBrushProperty, "AppBorderBrush");
        Grid.SetRow(textFrame, 1);

        ComposeSpellChecking.Register(rich);
        var editor = new RichComposeEditor(plain, rich);
        editor.CreateToolbar(toolbar, owner);
        // Finish a typed URL at a word boundary, or after a paste. Never
        // rewrite an incomplete address while the user is still typing it.
        rich.PreviewKeyDown += (_, args) =>
        {
            if (args.Key is Key.Space or Key.Return or Key.Tab)
                _ = rich.Dispatcher.BeginInvoke(new Action(editor.AutoLinkUrls),
                    DispatcherPriority.Background);
        };
        DataObject.AddPastingHandler(rich, (_, _) =>
            _ = rich.Dispatcher.BeginInvoke(new Action(editor.AutoLinkUrls),
                DispatcherPriority.Background));
        rich.LostKeyboardFocus += (_, _) => editor.AutoLinkUrls();
        host.Children.Add(toolbar);
        host.Children.Add(textFrame);

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

    // Exposed internally for UI regression checks; the shared editor remains
    // the only owner of this control in production.
    internal RichTextBox TextControl => _rich;

    public void Focus() => _rich.Focus();

    public void PlaceCaretAtStart()
    {
        _rich.CaretPosition = _rich.Document.Blocks.FirstBlock?.ContentStart
            ?? _rich.Document.ContentStart;
    }

    public void LoadMarkdown(string markdown)
    {
        _replyBody = null;
        _replySignature = null;
        _replyHistory = null;
        _syncing = true;
        try
        {
            MarkdownRichLoader.Load(_rich.Document, markdown);
            _plain.Text = new TextRange(_rich.Document.ContentStart,
                _rich.Document.ContentEnd).Text.TrimEnd('\r', '\n');
        }
        finally { _syncing = false; }
    }

    public bool IsReplyMode => _replyBody is not null;

    /// <summary>
    /// The previous correspondence lives in a separate trailing document
    /// section. A template can only replace _replyBody, while a signature
    /// can only replace _replySignature, regardless of caret position.
    /// </summary>
    public void LoadReplyQuotedText(string quotedText)
    {
        _syncing = true;
        try
        {
            _rich.Document.Blocks.Clear();
            _replyBody = new Section(new Paragraph { Margin = new Thickness(0) })
            {
                Margin = new Thickness(0)
            };
            _replySignature = null;
            _replyHistory = new Section { Margin = new Thickness(0) };
            _rich.Document.Blocks.Add(_replyBody);

            if (!string.IsNullOrWhiteSpace(quotedText))
            {
                _replyHistory.Blocks.Add(new Paragraph { Margin = new Thickness(0) });
                foreach (var line in quotedText.Replace("\r\n", "\n", StringComparison.Ordinal)
                             .TrimStart('\r', '\n').Split('\n'))
                    _replyHistory.Blocks.Add(new Paragraph(new Run(line))
                        { Margin = new Thickness(0) });
                _rich.Document.Blocks.Add(_replyHistory);
            }
        }
        finally { _syncing = false; }
        SyncFromRich();
        _rich.CaretPosition = _replyBody!.Blocks.FirstBlock!.ContentStart;
    }

    /// <summary>
    /// Replace only this reply's text, preserving historical messages and any
    /// separately chosen signature. An embedded known template signature is
    /// moved into the signature region rather than duplicated.
    /// </summary>
    public void ApplyTemplateMarkdown(string markdown, IEnumerable<string> knownSignatures)
    {
        if (_replyBody is null)
        {
            LoadMarkdown(markdown);
            return;
        }

        var available = knownSignatures.Where(s => !string.IsNullOrWhiteSpace(s)).ToArray();
        var staged = new FlowDocument();
        MarkdownRichLoader.Load(staged, markdown);
        string? templateSignature = null;
        foreach (var signature in available
                     .OrderByDescending(s => MarkdownRichLoader.ToPlain(s).Length))
        {
            var lines = MarkdownRichLoader.ToPlain(signature)
                .Replace("\r\n", "\n", StringComparison.Ordinal)
                .TrimEnd('\r', '\n').Split('\n');
            var paragraphs = staged.Blocks.OfType<Paragraph>().ToList();
            if (paragraphs.Count < lines.Length) continue;
            var tail = paragraphs.Skip(paragraphs.Count - lines.Length).ToArray();
            if (!tail.Select(p => new TextRange(p.ContentStart, p.ContentEnd)
                    .Text.TrimEnd('\r', '\n')).SequenceEqual(lines, StringComparer.Ordinal))
                continue;
            foreach (var paragraph in tail) staged.Blocks.Remove(paragraph);
            if (staged.Blocks.LastBlock is Paragraph blank &&
                string.IsNullOrWhiteSpace(new TextRange(blank.ContentStart, blank.ContentEnd).Text))
                staged.Blocks.Remove(blank);
            templateSignature = signature;
            break;
        }

        _syncing = true;
        try
        {
            _replyBody.Blocks.Clear();
            MoveBlocks(staged.Blocks, _replyBody.Blocks);
            if (_replyBody.Blocks.FirstBlock is null)
                _replyBody.Blocks.Add(new Paragraph { Margin = new Thickness(0) });
        }
        finally { _syncing = false; }

        if (templateSignature is not null)
            ApplyMarkdownSignature(templateSignature, available);
        else
            SyncFromRich();
        _rich.CaretPosition = _replyBody.Blocks.LastBlock!.ContentEnd;
    }

    private static void MoveBlocks(BlockCollection from, BlockCollection to)
    {
        while (from.FirstBlock is Block block)
        {
            from.Remove(block);
            to.Add(block);
        }
    }

    public void ClearReplyMode()
    {
        if (_replyBody is not null)
            LoadMarkdown("");
    }

    /// <summary>
    /// Keep existing character formatting intact when changing the trailing
    /// signature; append or replace document paragraphs instead of rewriting
    /// the hidden plain-text companion and losing WPF formatting.
    /// </summary>
    public void ApplyMarkdownSignature(string markdown, IEnumerable<string> knownMarkdown)
    {
        if (_replyBody is not null)
        {
            _syncing = true;
            try
            {
                if (_replySignature is null)
                {
                    _replySignature = new Section { Margin = new Thickness(0) };
                    _rich.Document.Blocks.InsertAfter(_replyBody, _replySignature);
                }
                _replySignature.Blocks.Clear();
                if (!string.IsNullOrWhiteSpace(markdown))
                {
                    var staging = new FlowDocument();
                    MarkdownRichLoader.Load(staging, markdown);
                    _replySignature.Blocks.Add(new Paragraph { Margin = new Thickness(0) });
                    MoveBlocks(staging.Blocks, _replySignature.Blocks);
                }
            }
            finally { _syncing = false; }
            SyncFromRich();
            _rich.CaretPosition = _replyBody.Blocks.LastBlock!.ContentEnd;
            return;
        }
        var candidates = knownMarkdown
            .Where(value => !string.IsNullOrWhiteSpace(value))
            .Select(MarkdownRichLoader.ToPlain)
            .Distinct(StringComparer.Ordinal)
            .OrderByDescending(value => value.Length);

        _syncing = true;
        try
        {
            foreach (var candidate in candidates)
            {
                var lines = candidate.Replace("\r\n", "\n", StringComparison.Ordinal)
                    .Split('\n');
                var blocks = _rich.Document.Blocks.OfType<Paragraph>().ToList();
                if (blocks.Count < lines.Length) continue;
                var tail = blocks.Skip(blocks.Count - lines.Length).ToArray();
                var matching = tail.Select(p =>
                    new TextRange(p.ContentStart, p.ContentEnd)
                    .Text.TrimEnd('\r', '\n'))
                    .SequenceEqual(lines, StringComparer.Ordinal);
                if (!matching) continue;
                foreach (var block in tail) _rich.Document.Blocks.Remove(block);
                if (_rich.Document.Blocks.LastBlock is Paragraph separator &&
                    string.IsNullOrWhiteSpace(new TextRange(
                        separator.ContentStart, separator.ContentEnd).Text))
                    _rich.Document.Blocks.Remove(separator);
                break;
            }

            var preceding = new TextRange(_rich.Document.ContentStart,
                _rich.Document.ContentEnd).Text.TrimEnd('\r', '\n');
            if (preceding.Length > 0)
                _rich.Document.Blocks.Add(new Paragraph { Margin = new Thickness(0) });
            else
                _rich.Document.Blocks.Clear();
            MarkdownRichLoader.Append(_rich.Document, markdown);
        }
        finally { _syncing = false; }
        SyncFromRich();
        _rich.CaretPosition = _rich.Document.ContentEnd;
    }

    public string ToMarkdown()
    {
        AutoLinkUrls();
        return MarkdownRichWriter.Write(_rich.Document);
    }

    public string ToHtml()
    {
        AutoLinkUrls();
        var builder = new StringBuilder();
        builder.Append("<div style=\"font-family:Segoe UI,Arial,sans-serif;white-space:normal;\">");
        RenderBlocks(_rich.Document.Blocks, builder);
        builder.Append("</div>");
        return builder.ToString();
    }

    private void AutoLinkUrls()
    {
        if (_syncing || _autoLinking) return;
        _autoLinking = true;
        try
        {
            foreach (var paragraph in EnumerateParagraphs(_rich.Document.Blocks))
                LinkifyInlines(paragraph.Inlines);
        }
        finally { _autoLinking = false; }
    }

    private static void LinkifyInlines(InlineCollection inlines)
    {
        foreach (Inline inline in inlines.ToArray())
        {
            if (inline is Hyperlink)
                continue; // Never modify an existing explicit hyperlink.
            if (inline is Span span)
            {
                LinkifyInlines(span.Inlines);
                continue;
            }
            if (inline is not Run run || string.IsNullOrEmpty(run.Text))
                continue;

            var original = run.Text;
            var previousEnd = 0;
            var changed = false;
            foreach (Match match in UrlPattern.Matches(original))
            {
                var address = match.Value.TrimEnd('.', ',', ';', ':', '!', '?', ')', ']');
                if (address.Length == 0) continue;
                var uriText = address.StartsWith("www.", StringComparison.OrdinalIgnoreCase)
                    ? "https://" + address : address;
                if (!Uri.TryCreate(uriText, UriKind.Absolute, out var target) ||
                    target.Scheme is not ("http" or "https"))
                    continue;

                if (match.Index > previousEnd)
                    inlines.InsertBefore(run,
                        new Run(original.Substring(previousEnd, match.Index - previousEnd)));
                inlines.InsertBefore(run, new Hyperlink(new Run(address))
                {
                    NavigateUri = target
                });
                previousEnd = match.Index + address.Length;
                changed = true;
            }
            if (changed)
                run.Text = original[previousEnd..];
        }
    }

    private void CreateToolbar(Panel toolbar, Window owner)
    {
        Add(toolbar, "H", "Заголовок", () =>
        {
            var paragraph = _rich.Selection.Start.Paragraph;
            if (paragraph is not null)
            {
                paragraph.FontSize = 20;
                paragraph.FontWeight = FontWeights.Bold;
            }
            _rich.Focus();
        });
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
        Add(toolbar, "Tx", "Удалить форматирование", () =>
        {
            if (_rich.Selection.IsEmpty)
            {
                AppDialog.Info(owner, "Форматирование",
                    "Выделите текст, с которого нужно удалить форматирование.");
                return;
            }

            // Clear local character/paragraph styles without replacing content:
            // paragraph boundaries, repeated empty Enter lines and Shift+Enter
            // breaks must survive byte-for-byte.  Reapply this editor's own
            // default typography, not a Windows RichTextBox fallback font.
            var selection = _rich.Selection;
            selection.ClearAllProperties();
            selection.ApplyPropertyValue(TextElement.FontFamilyProperty, _rich.FontFamily);
            selection.ApplyPropertyValue(TextElement.FontSizeProperty, _rich.FontSize);
            selection.ApplyPropertyValue(TextElement.FontWeightProperty, FontWeights.Normal);
            selection.ApplyPropertyValue(TextElement.FontStyleProperty, FontStyles.Normal);
            selection.ApplyPropertyValue(Inline.TextDecorationsProperty,
                new TextDecorationCollection());
            // Normalise selected paragraph-level heading/alignment properties
            // as well as inline formatting; preserve each paragraph and Enter.
            foreach (var paragraph in EnumerateParagraphs(_rich.Document.Blocks))
            {
                if (selection.Start.CompareTo(paragraph.ContentEnd) > 0 ||
                    selection.End.CompareTo(paragraph.ContentStart) < 0)
                    continue;
                paragraph.ClearValue(TextElement.FontSizeProperty);
                paragraph.ClearValue(TextElement.FontWeightProperty);
                paragraph.ClearValue(Block.TextAlignmentProperty);
                paragraph.TextAlignment = TextAlignment.Left;
            }
            // Foreground remains inherited from the themed editor.
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

    private void Add(Panel toolbar, string caption, string tip, Action action)
    {
        var button = new Button
        {
            Content = caption, ToolTip = tip, Padding = new Thickness(7, 3, 7, 3),
            MinWidth = 26, Height = 27, Margin = new Thickness(0, 0, 3, 0),
            FontSize = 12
        };
        button.Click += (_, _) =>
        {
            action();
            Changed?.Invoke(this, EventArgs.Empty);
        };
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
        Changed?.Invoke(this, EventArgs.Empty);
    }

    private static IEnumerable<Paragraph> EnumerateParagraphs(BlockCollection blocks)
    {
        foreach (Block block in blocks)
        {
            if (block is Paragraph paragraph)
                yield return paragraph;
            else if (block is Section section)
            {
                foreach (var inner in EnumerateParagraphs(section.Blocks))
                    yield return inner;
            }
            else if (block is System.Windows.Documents.List list)
            {
                foreach (var item in list.ListItems)
                    foreach (var inner in EnumerateParagraphs(item.Blocks))
                        yield return inner;
            }
        }
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
                        .Append(align);
                    if (paragraph.ReadLocalValue(TextElement.FontSizeProperty) is double fontSize)
                        output.Append(";font-size:").Append(fontSize.ToString(
                            System.Globalization.CultureInfo.InvariantCulture)).Append("px");
                    if (paragraph.FontWeight == FontWeights.Bold)
                        output.Append(";font-weight:bold");
                    output.Append(";\">");
                    // Empty WPF paragraphs are real Enter keystrokes.  HTML
                    // <p></p> has no line box in many mail clients, so each
                    // visually empty paragraph needs an explicit <br>.
                    var paragraphHtml = new StringBuilder();
                    RenderInlines(paragraph.Inlines, paragraphHtml);
                    output.Append(paragraphHtml.Length == 0 ? "<br>" : paragraphHtml.ToString());
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

    private static void RenderInlines(InlineCollection inlines, StringBuilder output,
        bool inheritedUnderline = false)
    {
        foreach (Inline inline in inlines)
        {
            if (inline is Run run)
            {
                var value = WebUtility.HtmlEncode(run.Text)
                    .Replace("\r\n", "<br>", StringComparison.Ordinal)
                    .Replace("\n", "<br>", StringComparison.Ordinal);
                var styles = new List<string>();
                if (run.FontWeight == FontWeights.Bold) styles.Add("font-weight:bold");
                if (run.FontStyle == FontStyles.Italic) styles.Add("font-style:italic");
                var explicitDecorations = run.ReadLocalValue(Inline.TextDecorationsProperty)
                    != DependencyProperty.UnsetValue;
                if (run.TextDecorations?.Contains(TextDecorations.Underline[0]) == true ||
                    (inheritedUnderline && !explicitDecorations))
                    styles.Add("text-decoration:underline");
                if (run.TextDecorations?.Contains(TextDecorations.Strikethrough[0]) == true)
                    styles.Add("text-decoration:line-through");
                if (run.ReadLocalValue(TextElement.ForegroundProperty) is SolidColorBrush brush)
                    styles.Add($"color:#{brush.Color.R:X2}{brush.Color.G:X2}{brush.Color.B:X2}");
                if (styles.Count > 0)
                    output.Append("<span style=\"").Append(string.Join(";", styles)).Append("\">");
                output.Append(value);
                if (styles.Count > 0) output.Append("</span>");
            }
            else if (inline is LineBreak)
                output.Append("<br>");
            else if (inline is Hyperlink link && link.NavigateUri is not null)
            {
                var url = link.NavigateUri.ToString();
                output.Append("<a href=\"").Append(WebUtility.HtmlEncode(url)).Append("\">");
                RenderInlines(link.Inlines, output);
                output.Append("</a>");
            }
            else if (inline is Span span)
            {
                // Structural Bold/Italic/Underline can outlive a formatting
                // reset; effective child properties are the source of truth.
                RenderInlines(span.Inlines, output,
                    inheritedUnderline || span is Underline);
            }
        }
    }
}
