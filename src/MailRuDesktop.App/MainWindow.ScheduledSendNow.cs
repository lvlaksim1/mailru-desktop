using System.IO;
using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    // A lost server reply can conceal a completed send. Never dispatch that
    // same clone twice from a single application session.
    private readonly HashSet<string> _sendNowAttemptedSends =
        new(StringComparer.OrdinalIgnoreCase);

    private async void PreviewSendNowButton_Click(object sender, RoutedEventArgs e)
    {
        if (ActivePreviewMessage is { } message)
            await SendScheduledCopiesAsync([message]);
    }

    private async void BulkSendNowButton_Click(object sender, RoutedEventArgs e) =>
        await SendScheduledCopiesAsync(SelectedForBulk());

    private async Task SendScheduledCopiesAsync(IReadOnlyList<MailRuMessageSummary> selected)
    {
        if (_bulkOperationInProgress || !CurrentFolderIsOutbox ||
            _loadingFolder || selected.Count == 0 ||
            string.IsNullOrWhiteSpace(_activeLogin) ||
            string.IsNullOrWhiteSpace(_accessToken))
            return;

        var account = _activeLogin;
        var folder = _currentFolderId;
        var generation = Volatile.Read(ref _accountSwitchGeneration);
        bool IsCurrent() =>
            generation == Volatile.Read(ref _accountSwitchGeneration) &&
            string.Equals(account, _activeLogin, StringComparison.OrdinalIgnoreCase) &&
            folder == _currentFolderId &&
            CurrentFolderIsOutbox;

        // No confirmation dialogs: the button performs the requested action.
        // Errors and progress appear only in the existing status and diagnostics.
        _bulkOperationInProgress = true;
        UpdateBulkToolbar();
        var sentCount = 0;
        try
        {
            foreach (var message in selected)
            {
                if (!IsCurrent())
                {
                    FolderStatusText.Text = "Отправка остановлена: изменён аккаунт или папка.";
                    break;
                }

                var token = _accessToken!;
                var originalId = message.Id;
                var operationId = account.ToUpperInvariant() + ":" + originalId;
                if (_sendNowAttemptedSends.Contains(operationId))
                {
                    FolderStatusText.Text =
                        "Письмо уже обрабатывалось. Повторная отправка заблокирована.";
                    DiagnosticLog.Write("scheduled_send_now", "repeated_send_prevented");
                    break;
                }

                FolderStatusText.Text =
                    $"Подготовка письма {sentCount + 1} из {selected.Count}...";

                var outcome = await ScheduledSendNowExecution.RunAsync(
                    async () =>
                    {
                        var full = await _mailRu.GetFullMessageAsync(
                            token, originalId, markRead: false);
                        if (!string.Equals(full.Id, originalId, StringComparison.Ordinal) ||
                            full.To.Count == 0 ||
                            (string.IsNullOrWhiteSpace(full.Html) &&
                             string.IsNullOrWhiteSpace(full.Text)))
                            throw new InvalidOperationException("Incomplete message for cloning.");

                        // Inline CID images cannot be cloned safely without
                        // remapping their content IDs to the new attachment IDs.
                        if (full.Html.Contains("cid:", StringComparison.OrdinalIgnoreCase))
                            throw new InvalidOperationException("Unsupported inline CID image.");

                        // Prepare every attachment for the clone before send.
                        var attachmentIds = new List<string>();
                        foreach (var attachment in full.Attachments)
                        {
                            var bytes = await _mailRu.DownloadIncomingAttachmentAsync(
                                token, originalId, attachment.Id);
                            if (bytes.Length == 0)
                                throw new InvalidOperationException("Missing attachment data.");
                            using var stream = new MemoryStream(bytes, writable: false);
                            attachmentIds.Add(await _mailRu.UploadAttachmentAsync(
                                token, stream, attachment.DisplayName));
                        }

                        return new MailRuOutgoingMessage(
                            To: string.Join(", ", full.To),
                            Subject: full.Subject,
                            Text: full.Text,
                            Html: string.IsNullOrWhiteSpace(full.Html) ? null : full.Html,
                            SendDate: null,
                            AttachmentIds: attachmentIds,
                            Priority: full.Priority,
                            Cc: string.Join(", ", full.Cc),
                            Bcc: string.Join(", ", full.Bcc));
                    },
                    async outgoing =>
                    {
                        FolderStatusText.Text = "Отправка копии...";
                        return (await _mailRu.SendMessageAsync(token, outgoing)).Success;
                    },
                    async () =>
                    {
                        // The send already succeeded: move the original using
                        // the captured credentials, even if the UI switched
                        // accounts while the send request was in flight.
                        FolderStatusText.Text = "Перемещение оригинала в Корзину...";
                        return (await _mailRu.MoveMessagesAsync(
                            token, [originalId], 500002)).Success;
                    },
                    IsCurrent,
                    () => _sendNowAttemptedSends.Add(operationId));

                if (outcome != ScheduledSendNowOutcome.SentAndTrashed)
                {
                    FolderStatusText.Text = outcome switch
                    {
                        ScheduledSendNowOutcome.PreparationFailed =>
                            "Не удалось подготовить копию. Отложенное письмо не изменено.",
                        ScheduledSendNowOutcome.ContextChanged =>
                            "Аккаунт или папка изменены. Отправка не начата.",
                        ScheduledSendNowOutcome.SendUnconfirmed =>
                            "Результат отправки копии неизвестен. Оригинал оставлен в Исходящих.",
                        ScheduledSendNowOutcome.SentTrashRejected =>
                            "Копия отправлена, но оригинал остался в Исходящих.",
                        ScheduledSendNowOutcome.SentTrashUnconfirmed =>
                            "Копия отправлена. Перемещение оригинала в Корзину не подтверждено.",
                        _ => "Операция остановлена."
                    };
                    DiagnosticLog.Write("scheduled_send_now", outcome.ToString());
                    // Never automatically retry the clone; in bulk mode stop
                    // at the first failed or uncertain stage.
                    break;
                }

                sentCount++;
                if (IsCurrent())
                    RemoveConfirmedMailRows([originalId]);
                FolderStatusText.Text =
                    $"Копий отправлено: {sentCount} из {selected.Count}. Оригиналы в Корзине.";
                DiagnosticLog.Write("scheduled_send_now", "sent_and_moved_to_trash");
            }
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Операция остановлена. Проверьте Исходящие и Отправленные.";
            DiagnosticLog.Write("scheduled_send_now", ex.GetType().Name);
        }
        finally
        {
            _bulkOperationInProgress = false;
            UpdateBulkToolbar();
        }
    }
}
