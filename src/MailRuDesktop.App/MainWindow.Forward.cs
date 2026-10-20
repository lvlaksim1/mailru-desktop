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
