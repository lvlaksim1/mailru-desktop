using System.IO;
using System.Globalization;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using MailRuDesktop.Protocol;
using Microsoft.Web.WebView2.Core;
using Microsoft.Win32;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly List<string> _previewAttachmentPaths = [];
    private PreviewComposeMode _previewComposeMode;
    private string? _previewAutoReadMessageId;
    private bool _previewEnhancementsInitialized;

    private void PreviewEnhancements_Loaded(object sender, RoutedEventArgs e)
    {
        if (_previewEnhancementsInitialized)
            return;

        _previewEnhancementsInitialized = true;
        // Message metadata now updates on the same browser-shell commit
        // that publishes the new body, not on top-level navigation events.
    }

    private void UpdatePreviewSelectionFields(MailRuMessageSummary message)
    {
        PreviewComposePanel.Visibility = Visibility.Collapsed;
        PreviewComposeStatusText.Text = string.Empty;
        // The pending message's header is published with the prepared body.
        SelectedSenderNameText.Text = string.Empty;
        SelectedSenderText.Text = string.Empty;
        SelectedToText.Text = string.Empty;
        _previewAutoReadMessageId = message.Unread ? message.Id : null;
    }

    private void ApplyLoadedPreviewFields()
    {
        if (_currentFullMessage is not null &&
            ActivePreviewMessage is MailRuMessageSummary selected &&
            string.Equals(_currentFullMessage.Id, selected.Id, StringComparison.Ordinal))
        {
            SelectedSenderNameText.Text = _currentFullMessage.FromName;
            SelectedSenderText.Text = string.IsNullOrWhiteSpace(_currentFullMessage.FromEmail)
                ? selected.SenderEmail
                : _currentFullMessage.FromEmail;
            SelectedToText.Text = string.Join(", ", _currentFullMessage.To);

            if (string.Equals(_previewAutoReadMessageId, selected.Id, StringComparison.Ordinal) &&
                !selected.Unread)
            {
                AdjustActiveInboxUnread(-1);
                _previewAutoReadMessageId = null;
            }
        }
    }

    private void PreviewAddress_MouseDoubleClick(object sender, MouseButtonEventArgs e)
    {
        if (sender is not TextBox textBox || string.IsNullOrWhiteSpace(textBox.Text))
            return;

        textBox.SelectAll();
        try
        {
            Clipboard.SetText(textBox.Text.Trim());
            FolderStatusText.Text = "Адрес скопирован в буфер обмена.";
        }
        catch
        {
            FolderStatusText.Text = "Не удалось скопировать адрес.";
        }

        e.Handled = true;
    }

    private async void MessageUnreadWithCounterButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not MailRuMessageSummary message ||
            string.IsNullOrWhiteSpace(_accessToken))
            return;

        var makeUnread = !message.Unread;
        try
        {
            var result = await _mailRu.SetUnreadAsync(
                _accessToken,
                _activeLogin ?? string.Empty,
                message.Id,
                makeUnread);

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил изменение статуса.";
                return;
            }

            ReplaceMessage(message, message with { Unread = makeUnread });
            if (_currentFolderId == 0)
                AdjustActiveInboxUnread(makeUnread ? 1 : -1);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка изменения статуса.";
            DiagnosticLog.Write("message_marks", ex.GetType().Name + ": " + ex.Message);
        }

        e.Handled = true;
    }

    private async void MessageArchiveWithCounterButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not MailRuMessageSummary message)
            return;

        await ArchiveMessageFromPreviewAsync(message);
        e.Handled = true;
    }

    private async void PreviewArchiveButton_Click(object sender, RoutedEventArgs e)
    {
        if (ActivePreviewMessage is MailRuMessageSummary message)
            await ArchiveMessageFromPreviewAsync(message);
    }

    private async Task ArchiveMessageFromPreviewAsync(MailRuMessageSummary clicked)
    {
        var targets = ResolveActionMessages();
        if (targets.Count == 0)
            return;

        var folders = (FolderListBox.ItemsSource as IEnumerable<MailRuFolderSummary>)?.ToArray()
            ?? Array.Empty<MailRuFolderSummary>();
        var archive = folders.FirstOrDefault(folder =>
            folder.Type.Equals("archive", StringComparison.OrdinalIgnoreCase) ||
            folder.Name.Equals("Архив", StringComparison.CurrentCultureIgnoreCase) ||
            folder.Id == 500010);

        await ExecuteBulkMoveAsync(targets, archive?.Id ?? 500010,
            "Перемещение в архив");
    }

    private void AdjustActiveInboxUnread(long delta)
    {
        if (_currentFolderId != 0)
            return;

        var item = _accountRailItems.FirstOrDefault(account =>
            string.Equals(account.Login, _activeLogin, StringComparison.OrdinalIgnoreCase));
        if (item is null)
            return;

        var next = Math.Max(0, (item.Unread ?? 0) + delta);
        item.Unread = next;
        item.Status = $"Непрочитанных во Входящих: {next}";
    }

    private void UnifiedSearchTextBox_TextChanged(object sender, TextChangedEventArgs e)
    {
        if (!IsLoaded)
            return;

        ApplyFilters();
    }

    private void PreviewReplyButton_Click(object sender, RoutedEventArgs e)
    {
        if (ActivePreviewMessage is not MailRuMessageSummary message)
            return;

        _previewComposeMode = PreviewComposeMode.Reply;
        ResetPreviewSendOptions();
        ResetPreviewTemplateSelectors();
        PreviewComposePanel.Visibility = Visibility.Visible;
        PreviewComposeToTextBox.Text = !string.IsNullOrWhiteSpace(_currentFullMessage?.FromEmail)
            ? _currentFullMessage.FromEmail
            : message.SenderEmail;
        PreviewComposeSubjectTextBox.Text = PrefixPreviewSubject(
            "Re:",
            _currentFullMessage?.Subject ?? message.Subject);
        PreviewComposeBodyTextBox.Clear();
        _previewAttachmentPaths.Clear();
        RefreshPreviewAttachments();
        PreviewComposeStatusText.Text = string.Empty;
        _previewRichEditor?.Focus();
    }

    private void PreviewForwardButton_Click(object sender, RoutedEventArgs e)
    {
        if (ActivePreviewMessage is not MailRuMessageSummary message)
            return;

        _previewComposeMode = PreviewComposeMode.Forward;
        ResetPreviewSendOptions();
        ResetPreviewTemplateSelectors();
        PreviewComposePanel.Visibility = Visibility.Visible;
        PreviewComposeToTextBox.Clear();
        PreviewComposeSubjectTextBox.Text = PrefixPreviewSubject(
            "Fwd:",
            _currentFullMessage?.Subject ?? message.Subject);

        var full = _currentFullMessage;
        PreviewComposeBodyTextBox.Text = full is null
            ? string.Empty
            : "\n\n---------- Пересланное сообщение ----------\n" +
              $"От: {full.SenderDisplay}\n" +
              $"Кому: {string.Join(", ", full.To)}\n" +
              $"Дата: {full.DateDisplay}\n" +
              $"Тема: {full.Subject}\n\n" +
              full.Text;

        _previewAttachmentPaths.Clear();
        RefreshPreviewAttachments();
        PreviewComposeStatusText.Text = string.Empty;
        PreviewComposeToTextBox.Focus();
    }

    private sealed record PreviewAttachmentItem(string Path, string Name);

    private void PreviewComposeAttachButton_Click(object sender, RoutedEventArgs e)
    {
        var dialog = new OpenFileDialog
        {
            Multiselect = true,
            CheckFileExists = true,
            Title = "Выберите вложения"
        };

        if (dialog.ShowDialog(this) != true)
            return;

        foreach (var path in dialog.FileNames)
        {
            if (!_previewAttachmentPaths.Contains(path, StringComparer.OrdinalIgnoreCase))
                _previewAttachmentPaths.Add(path);
        }

        RefreshPreviewAttachments();
    }

    private void PreviewRemoveAttachmentButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not string path)
            return;

        _previewAttachmentPaths.RemoveAll(item =>
            string.Equals(item, path, StringComparison.OrdinalIgnoreCase));
        RefreshPreviewAttachments();
    }

    private void RefreshPreviewAttachments()
    {
        PreviewComposeAttachmentsItemsControl.ItemsSource = _previewAttachmentPaths
            .Select(path => new PreviewAttachmentItem(path, Path.GetFileName(path)))
            .ToArray();
    }

    private void ResetPreviewSendOptions()
    {
        PreviewReadReceiptCheckBox.IsChecked = false;
        PreviewScheduleCheckBox.IsChecked = false;
        PreviewScheduleDatePicker.SelectedDate = DateTime.Today.AddDays(1);
        PreviewScheduleTimeTextBox.Text = "09:00";
    }

    private bool TryGetPreviewSchedule(out string? sendDate, out DateTimeOffset? scheduledFor)
    {
        sendDate = null;
        scheduledFor = null;

        if (PreviewScheduleCheckBox.IsChecked != true)
            return true;

        if (PreviewScheduleDatePicker.SelectedDate is not DateTime date)
        {
            PreviewComposeStatusText.Text = "Выберите дату отложенной отправки.";
            return false;
        }

        if (!DateTime.TryParseExact(
                PreviewScheduleTimeTextBox.Text.Trim(), new[] { "H:mm", "HH:mm" },
                CultureInfo.InvariantCulture, DateTimeStyles.None, out var time))
        {
            PreviewComposeStatusText.Text = "Время должно быть в формате ЧЧ:ММ.";
            return false;
        }

        var local = DateTime.SpecifyKind(date.Date.Add(time.TimeOfDay), DateTimeKind.Local);
        scheduledFor = new DateTimeOffset(local);
        if (scheduledFor <= DateTimeOffset.Now.AddMinutes(1))
        {
            PreviewComposeStatusText.Text = "Время отложенной отправки должно быть в будущем.";
            return false;
        }

        sendDate = scheduledFor.Value.ToUnixTimeSeconds().ToString(CultureInfo.InvariantCulture);
        return true;
    }

    private async void PreviewSendComposeButton_Click(object sender, RoutedEventArgs e)
    {
        PreviewComposeStatusText.SetResourceReference(TextBlock.ForegroundProperty, "AppMutedTextBrush");
        if (ActivePreviewMessage is not MailRuMessageSummary message)
            return;

        if (string.IsNullOrWhiteSpace(PreviewComposeToTextBox.Text))
        {
            PreviewComposeStatusText.Text = "Укажите получателя.";
            return;
        }

        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            PreviewComposeStatusText.Text = "Нет access_token. Выполните авторизацию заново.";
            return;
        }

        if (!TryGetPreviewSchedule(out var sendDate, out var scheduledFor))
            return;

        PreviewSendComposeButton.IsEnabled = false;
        try
        {
            var messageId = MailRuClient.KnownWorkingMessageId;
            var attachmentIds = new List<string>();

            for (var i = 0; i < _previewAttachmentPaths.Count; i++)
            {
                var path = _previewAttachmentPaths[i];
                PreviewComposeStatusText.Text =
                    $"Загрузка вложения {i + 1}/{_previewAttachmentPaths.Count}...";

                await using var stream = File.OpenRead(path);
                attachmentIds.Add(await _mailRu.UploadAttachmentAsync(
                    _accessToken,
                    stream,
                    Path.GetFileName(path),
                    messageId));
            }

            PreviewComposeStatusText.Text = "Отправка...";
            var result = await _mailRu.SendMessageAsync(
                _accessToken,
                new MailRuOutgoingMessage(
                    To: PreviewComposeToTextBox.Text.Trim(),
                    Subject: PreviewComposeSubjectTextBox.Text,
                    Text: PreviewComposeBodyTextBox.Text,
                    Html: _previewRichEditor?.ToHtml(),
                    ReplyToId: _previewComposeMode == PreviewComposeMode.Reply ? message.Id : null,
                    SendDate: sendDate,
                    RequestReadReceipt: PreviewReadReceiptCheckBox.IsChecked == true,
                    AttachmentIds: attachmentIds,
                    MessageId: messageId));

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                PreviewComposeStatusText.SetResourceReference(TextBlock.ForegroundProperty, "AppDangerBrush");
                PreviewComposeStatusText.Text = scheduledFor is null
                    ? "Mail.ru отклонил отправку."
                    : "Mail.ru отклонил отложенную отправку.";
                return;
            }

            PreviewComposeStatusText.SetResourceReference(TextBlock.ForegroundProperty, "AppSuccessBrush");
            PreviewComposeStatusText.Text = scheduledFor is null
                ? "Отправлено."
                : $"Запланировано на {scheduledFor.Value.LocalDateTime:dd.MM.yyyy HH:mm}.";
            ResetPreviewSendOptions();
            PreviewComposeBodyTextBox.Clear();
            _previewAttachmentPaths.Clear();
            RefreshPreviewAttachments();
        }
        catch (Exception ex)
        {
            PreviewComposeStatusText.SetResourceReference(TextBlock.ForegroundProperty, "AppDangerBrush");
            PreviewComposeStatusText.Text = ex.Message;
            DiagnosticLog.Write("preview_send", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            PreviewSendComposeButton.IsEnabled = true;
        }
    }

    private void PreviewCancelComposeButton_Click(object sender, RoutedEventArgs e)
    {
        PreviewComposePanel.Visibility = Visibility.Collapsed;
        PreviewComposeStatusText.Text = string.Empty;
    }

    private static string PrefixPreviewSubject(string prefix, string subject) =>
        subject.StartsWith(prefix, StringComparison.OrdinalIgnoreCase)
            ? subject
            : prefix + " " + subject;

    private enum PreviewComposeMode
    {
        Reply,
        Forward
    }
}
