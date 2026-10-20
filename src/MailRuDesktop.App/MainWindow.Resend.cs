using System.IO;
using System.Net;
using System.Text.RegularExpressions;
using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private string? _resendOriginalId;
    private readonly Dictionary<string, MailRuIncomingAttachment> _resendSourceAttachments =
        new(StringComparer.Ordinal);

    /// <summary>
    /// Called only after the modal source reader closes; reuse the full normal
    /// compose window and its ordinary immediate/scheduled send paths.
    /// </summary>
    private void OpenResendComposeWindow(
        MailRuMessageSummary original, MailRuFullMessage? full)
    {
        if (full is null ||
            !string.Equals(full.Id, original.Id, StringComparison.Ordinal))
        {
            FolderStatusText.Text = "Повторная отправка: полное письмо не загружено.";
            return;
        }
        if (_composeWindow is not null)
        {
            ShowComposeWindow();
            ComposeStatusText.Text =
                "Уже открыто другое письмо. Закройте его перед повторной отправкой.";
            return;
        }
        if (string.IsNullOrWhiteSpace(_accessToken) ||
            string.IsNullOrWhiteSpace(_activeLogin))
        {
            FolderStatusText.Text = "Повторная отправка: аккаунт не авторизован.";
            return;
        }

        if (!string.IsNullOrWhiteSpace(ComposeToTextBox.Text) ||
            !string.IsNullOrWhiteSpace(ComposeSubjectTextBox.Text) ||
            !string.IsNullOrWhiteSpace(ComposeBodyTextBox.Text) ||
            _attachmentPaths.Count != 0)
        {
            FolderStatusText.Text = "Сначала завершите или сохраните незавершённое новое письмо.";
            return;
        }
        ShowComposeWindow();
        _resendOriginalId = original.Id;
        _resendSourceAttachments.Clear();
        for (var i = 0; i < full.Attachments.Count; i++)
            _resendSourceAttachments["source:" + i] = full.Attachments[i];

        ResetComposeTemplateSelectors();
        ComposeToTextBox.Text = string.Join(", ", full.To);
        ComposeSubjectTextBox.Text = full.Subject;
        // The original plain-text body takes precedence. For HTML-only mail,
        // display readable text rather than passing raw tags to the editor.
        var text = full.Text;
        if (string.IsNullOrWhiteSpace(text) && !string.IsNullOrWhiteSpace(full.Html))
        {
            text = Regex.Replace(full.Html, @"<\s*br\s*/?\s*>", "\n",
                RegexOptions.IgnoreCase);
            text = Regex.Replace(text, @"</\s*(p|div|tr|li|h[1-6])\s*>", "\n",
                RegexOptions.IgnoreCase);
            text = WebUtility.HtmlDecode(Regex.Replace(text, "<[^>]+>", ""));
            ComposeStatusText.Text =
                "В исходном письме был только HTML. Проверьте текст перед отправкой.";
        }
        else
            ComposeStatusText.Text =
                "Редактируемая копия исходящего письма. Оригинал останется до подтверждения отправки.";

        ComposeBodyTextBox.Text = text ?? "";
        ScheduleSendCheckBox.IsChecked = false;
        RequestReadReceiptCheckBox.IsChecked = false;
        _attachmentPaths.Clear();
        RefreshComposeAttachments();
        _composeWindow!.Title = "Отправить повторно: " + full.Subject;
        _composeRichEditor?.Focus();
    }

    private async Task UploadResendSourceAttachmentsAsync(
        string token, string messageId, List<string> attachmentIds)
    {
        if (_resendOriginalId is null) return;
        foreach (var attachment in _resendSourceAttachments.Values.ToArray())
        {
            if (string.IsNullOrWhiteSpace(attachment.Id))
                throw new InvalidOperationException(
                    "Нет идентификатора исходного вложения. Отправка не начата.");
            ComposeStatusText.Text = "Подготовка вложения: " + attachment.DisplayName;
            var bytes = await _mailRu.DownloadIncomingAttachmentAsync(
                token, _resendOriginalId, attachment.Id);
            await using var stream = new MemoryStream(bytes, writable: false);
            attachmentIds.Add(await _mailRu.UploadAttachmentAsync(
                token, stream, attachment.DisplayName, messageId));
        }
    }
}
