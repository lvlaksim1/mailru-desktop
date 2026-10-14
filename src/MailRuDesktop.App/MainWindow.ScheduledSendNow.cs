using System.IO;
using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
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

        var confirmed = AppDialog.Confirm(
            this,
            "Отправить сейчас",
            selected.Count == 1
                ? "Подготовить копию письма, удалить исходную отложенную отправку и отправить копию немедленно? После удаления расписания операция необратима."
                : $"Для {selected.Count} писем: сначала подготовить копию, затем удалить старое расписание и отправить копию. При первой ошибке обработка остановится. Продолжить?",
            "Отправить", "Отмена");
        if (!confirmed || !IsCurrent())
            return;

        _bulkOperationInProgress = true;
        UpdateBulkToolbar();
        var sentCount = 0;
        try
        {
            foreach (var message in selected)
            {
                if (!IsCurrent())
                {
                    FolderStatusText.Text = "Операция остановлена: изменён аккаунт или папка.";
                    break;
                }

                // Once the message is removed, a failed or unconfirmed send
                // must never automatically retry. Even a failed HTTP reply may
                // conceal a completed delivery.
                var token = _accessToken!;
                var originalId = message.Id;
                var originalDeleted = false;
                FolderStatusText.Text =
                    $"Подготовка копии письма {sentCount + 1} из {selected.Count}...";

                var outcome = await ScheduledSendNowExecution.RunAsync(
                    async () =>
                    {
                        var full = await _mailRu.GetFullMessageAsync(
                            token, originalId, markRead: false);
                        if (!string.Equals(full.Id, originalId, StringComparison.Ordinal) ||
                            full.To.Count == 0 ||
                            (string.IsNullOrWhiteSpace(full.Html) &&
                             string.IsNullOrWhiteSpace(full.Text)))
                            throw new InvalidOperationException("Невозможно подготовить полную копию письма.");

                        // Inline CID images require a separate content-ID map.
                        // Never delete the original if we cannot clone them intact.
                        if (full.Html.Contains("cid:", StringComparison.OrdinalIgnoreCase))
                            throw new InvalidOperationException("Встроенные изображения CID нельзя клонировать без потери связи.");

                        // All attachments are downloaded and uploaded BEFORE
                        // deleting the scheduled original. No local temp files.
                        var attachmentIds = new List<string>();
                        foreach (var attachment in full.Attachments)
                        {
                            var bytes = await _mailRu.DownloadIncomingAttachmentAsync(
                                token, originalId, attachment.Id);
                            if (bytes.Length == 0)
                                throw new InvalidOperationException("Не удалось прочитать вложение.");
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
                    async () =>
                    {
                        if (!IsCurrent())
                            return false;
                        FolderStatusText.Text = "Удаление первоначальной отложенной отправки...";
                        originalDeleted = (await _mailRu.RemoveMessagesAsync(
                            token, account, [originalId])).Success;
                        return originalDeleted;
                    },
                    async () =>
                    {
                        // Fail closed if we cannot independently observe that
                        // the former schedule is absent in the same folder.
                        var raw = await _mailRu.GetFolderThreadsAsync(
                            token, folder, limit: 1000, lastModified: 1);
                        var snapshot = MailRuThreadStatusParser.Parse(raw, folder);
                        return snapshot.SelectedFolderId == folder &&
                            snapshot.MessagesTotal is >= 0 and <= 1000 &&
                            snapshot.Messages.All(x =>
                                !string.Equals(x.Id, originalId, StringComparison.Ordinal));
                    },
                    async outgoing =>
                    {
                        if (!IsCurrent())
                            return false;
                        FolderStatusText.Text = "Отправка подготовленной копии...";
                        return (await _mailRu.SendMessageAsync(token, outgoing)).Success;
                    },
                    IsCurrent);

                if (outcome != ScheduledSendNowOutcome.Sent)
                {
                    FolderStatusText.Text = outcome switch
                    {
                        ScheduledSendNowOutcome.PreparationFailed =>
                            "Копия не подготовлена. Исходное письмо не удалялось.",
                        ScheduledSendNowOutcome.OriginalNotDeleted =>
                            "Сервер не подтвердил удаление исходного расписания. Отправка отменена.",
                        ScheduledSendNowOutcome.RemovalUncertain =>
                            "Статус удаления неизвестен. Копия НЕ отправлялась. Проверьте папку вручную.",
                        ScheduledSendNowOutcome.ContextChanged => originalDeleted
                            ? "Исходное письмо уже удалено, но аккаунт или папка изменились. Копия не отправлялась; проверьте папки вручную."
                            : "Изменён аккаунт или папка. Операция остановлена до удаления исходного письма.",
                        _ =>
                            "Исходное расписание удалено, но результат отправки не подтверждён. НЕ повторяйте действие до проверки папки «Отправленные»."
                    };
                    // In bulk mode never advance past the first uncertain stage.
                    AppDialog.Info(this, "Операция остановлена",
                        FolderStatusText.Text +
                        "\n\nНе запускайте отправку повторно, пока не проверите фактическое состояние.");
                    break;
                }

                sentCount++;
                if (IsCurrent())
                    RemoveConfirmedMailRows([originalId]);
                FolderStatusText.Text = $"Отправлено немедленно: {sentCount} из {selected.Count}.";
            }
        }
        catch (Exception ex)
        {
            FolderStatusText.Text =
                "Операция прервана. Проверьте «Исходящие» и «Отправленные», не нажимая повторно.";
            DiagnosticLog.Write("scheduled_send_now", ex.GetType().Name);
        }
        finally
        {
            _bulkOperationInProgress = false;
            UpdateBulkToolbar();
        }
    }
}
