using System.Net;
using System.Text.RegularExpressions;
using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    // Reuses the same separate reply/new-mail editor, including signatures,
    // attachment uploads and subject editing. Unlike scheduled resend,
    // forwarding MUST NEVER archive the source message.
    private bool _forwardSourceOnly;

    // Convert the editable common formatting subset of incoming HTML; sender
    // scripts, style elements, tracking markup and arbitrary attributes are
    // never imported into the outgoing editor.
    internal static string BuildForwardMarkdown(MailRuFullMessage full)
    {
        var html = full.Html;
        html = Regex.Replace(html, @"(?is)<(script|style|iframe|object)\b[^>]*>.*?</\1>", "");
        // Keep the same explicit empty paragraphs that the ordinary reply
        // editor already preserves. An empty HTML div containing <br> is one
        // blank line, not two; non-empty <p> blocks have paragraph spacing.
        // Never collapse repeated blank lines from the original message.
        html = Regex.Replace(html,
            @"(?is)<\s*div\b[^>]*>\s*<\s*br\b[^>]*>\s*</\s*div\s*>", "\n");
        html = Regex.Replace(html, @"(?is)<\s*br\b[^>]*>", "\n");
        html = Regex.Replace(html, @"(?is)</\s*(p|section|article|h[1-6])\s*>", "\n\n");
        html = Regex.Replace(html, @"(?is)</\s*(div|tr|li)\s*>", "\n");
        html = Regex.Replace(html, @"(?is)<\s*li\b[^>]*>", "- ");
        html = Regex.Replace(html, @"(?is)<\s*(strong|b)\b[^>]*>", "**");
        html = Regex.Replace(html, @"(?is)</\s*(strong|b)\s*>", "**");
        html = Regex.Replace(html, @"(?is)<\s*(em|i)\b[^>]*>", "*");
        html = Regex.Replace(html, @"(?is)</\s*(em|i)\s*>", "*");
        html = Regex.Replace(html, @"(?is)<\s*u\b[^>]*>", "<u>");
        html = Regex.Replace(html, @"(?is)</\s*u\s*>", "</u>");
        html = Regex.Replace(html, @"(?is)<\s*(strike|del|s)\b[^>]*>", "<s>");
        html = Regex.Replace(html, @"(?is)</\s*(strike|del|s)\s*>", "</s>");
        html = Regex.Replace(html,
            @"(?is)<a\s+[^>]*href\s*=\s*[""'](?<url>(?:https?://|mailto:)[^""']+)[""'][^>]*>(?<title>[^<]*)</a>",
            m => "[" + m.Groups["title"].Value + "](" + m.Groups["url"].Value + ")");
        // Retain only the two explicitly supported safe inline tags; the
        // rich-text importer recognizes their exact forms.
        html = WebUtility.HtmlDecode(Regex.Replace(html,
            @"(?s)<(?!/?(?:u|s)\s*>)[^>]+>", ""));
        html = html.Trim('\r', '\n');
        var header = "---------- Пересланное сообщение ----------\n" +
            "От: " + full.SenderDisplay + "\n" +
            "Кому: " + string.Join(", ", full.To) + "\n" +
            "Дата: " + full.DateDisplay + "\n" +
            "Тема: " + full.Subject + "\n\n";
        return header + (string.IsNullOrWhiteSpace(html) ? full.Text : html);
    }

    private static string BuildForwardHeader(MailRuFullMessage full) =>
        "---------- Пересланное сообщение ----------\n" +
        "От: " + full.SenderDisplay + "\n" +
        "Кому: " + string.Join(", ", full.To) + "\n" +
        "Дата: " + full.DateDisplay + "\n" +
        "Тема: " + full.Subject;

    private void OpenForwardComposeWindow(
        MailRuMessageSummary selected, MailRuFullMessage? full)
    {
        if (full is null ||
            !string.Equals(full.Id, selected.Id, StringComparison.Ordinal))
        {
            FolderStatusText.Text =
                "Дождитесь загрузки полного выбранного письма перед пересылкой.";
            return;
        }
        if (_composeWindow is not null)
        {
            ShowComposeWindow();
            ComposeStatusText.Text =
                "Сначала завершите или закройте открытое письмо. Черновик не изменён.";
            return;
        }
        if (string.IsNullOrWhiteSpace(_activeLogin) ||
            string.IsNullOrWhiteSpace(_accessToken))
        {
            FolderStatusText.Text = "Пересылка: аккаунт не авторизован.";
            return;
        }

        ShowComposeWindow(reply: true);
        _forwardSourceOnly = true;
        _composeReplyToId = null;
        _resendOriginalId = full.Id;
        _attachmentPaths.Clear();
        _resendSourceAttachments.Clear();
        for (var i = 0; i < full.Attachments.Count; i++)
            _resendSourceAttachments["source:" + i] = full.Attachments[i];

        ComposeHeaderText.Visibility = Visibility.Collapsed;
        ArchiveAfterReplyCheckBox.Visibility = Visibility.Collapsed;
        ResetComposeTemplateSelectors();
        ScheduleSendCheckBox.IsChecked = false;
        RequestReadReceiptCheckBox.IsChecked = false;
        ComposeToTextBox.Text = string.Join(", ", full.To);
        ComposeSubjectTextBox.Text = full.Subject.StartsWith(
            "Fwd:", StringComparison.OrdinalIgnoreCase)
            ? full.Subject : "Fwd: " + full.Subject;

        var body = full.Text;
        if (string.IsNullOrWhiteSpace(body) && !string.IsNullOrWhiteSpace(full.Html))
        {
            body = Regex.Replace(full.Html, @"<\s*br\s*/?\s*>", "\n",
                RegexOptions.IgnoreCase);
            body = Regex.Replace(body, @"</\s*(p|div|li|tr|h[1-6])\s*>", "\n",
                RegexOptions.IgnoreCase);
            body = WebUtility.HtmlDecode(Regex.Replace(body, "<[^>]+>", ""));
        }

        var quote = "\n\n---------- Пересланное сообщение ----------\n" +
                    "От: " + full.SenderDisplay + "\n" +
                    "Кому: " + string.Join(", ", full.To) + "\n" +
                    "Дата: " + full.DateDisplay + "\n" +
                    "Тема: " + full.Subject + "\n\n" + (body ?? "");
        if (_composeRichEditor is not null)
        {
            if (!string.IsNullOrWhiteSpace(full.Html))
            {
                _composeRichEditor.LoadReplyQuotedHtml(full.Html, BuildForwardHeader(full));
            }
            else
                _composeRichEditor.LoadReplyQuotedText(quote);
            _composeRichEditor.EnsureOpeningGreeting();
        }
        else ComposeBodyTextBox.Text = "Добрый день\n\n" + quote;

        ApplyDefaultComposeSignature();
        _composeRichEditor?.PrepareInitialTypingFormat();
        RefreshComposeAttachments();
        if (_composeWindow is not null)
            _composeWindow.Title = "Переслать: " + full.Subject;
        ComposeStatusText.Text = string.Empty;
        _composeRichEditor?.Focus();
    }
}
