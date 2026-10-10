using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly SemaphoreSlim _pushNotificationOpenGate = new(1, 1);

    private void OnPushMailEvent(PushMailEvent mail)
    {
        OnPushNewMail(mail.Account, mail);
    }

    private async Task ShowPushNotificationAsync(PushMailEvent incoming)
    {
        var mail = incoming;
        // Event fields are optional. For a message with a stable ID, resolve
        // missing presentation details through the existing mailbox reader.
        if ((!string.IsNullOrWhiteSpace(mail.MessageId)) &&
            (string.IsNullOrWhiteSpace(mail.Subject) ||
             string.IsNullOrWhiteSpace(mail.Sender) ||
             string.IsNullOrWhiteSpace(mail.Snippet)))
        {
            try
            {
                if (_authStore.TryRestore(mail.Account, out var auth) &&
                    !string.IsNullOrWhiteSpace(auth?.AccessToken))
                {
                    MailRuMessageSummary? found = null;
                    if (mail.FolderId is int folder && folder >= 0)
                    {
                        var raw = await _mailRu.GetFolderThreadsAsync(auth.AccessToken, folder);
                        found = MailRuThreadStatusParser.Parse(raw, folder).Messages
                            .FirstOrDefault(m => m.Id == mail.MessageId);
                    }
                    if (found is not null)
                        mail = FillPushFromSummary(mail, found);
                }
            }
            catch (Exception error)
            {
                DiagnosticLog.Write("push_notification_details", error.GetType().Name);
            }
        }

        if (_pushShuttingDown || TaskbarNotificationsEnabledCheckBox.IsChecked != true)
            return;
        if (!PushMailToastService.Show(mail))
        {
            // Fallback to the legacy notification area, keeping the mailbox as
            // its title, not the redundant application/new-mail headline.
            _pushNotificationArea?.ShowBalloonTip(
                5000, mail.Account,
                PushMailEvent.SenderAndSubject(mail) + "\n" +
                PushMailEvent.PreviewLine(mail),
                System.Windows.Forms.ToolTipIcon.Info);
        }
    }

    private static PushMailEvent FillPushFromSummary(
        PushMailEvent mail, MailRuMessageSummary summary) =>
        mail with
        {
            FolderId = summary.FolderId ?? mail.FolderId,
            Sender = string.IsNullOrWhiteSpace(mail.Sender)
                ? summary.SenderDisplay : mail.Sender,
            Subject = string.IsNullOrWhiteSpace(mail.Subject)
                ? summary.Subject : mail.Subject,
            Snippet = string.IsNullOrWhiteSpace(mail.Snippet)
                ? summary.Snippet : mail.Snippet,
            HasAttachment = mail.HasAttachment ?? summary.HasAttachment,
            Important = mail.Important ?? summary.Flagged,
            ReceivedAt = mail.ReceivedAt ??
                (summary.DateUnix is long seconds
                    ? DateTimeOffset.FromUnixTimeSeconds(seconds) : null)
        };

    private async Task NavigateToPushMailAsync(PushMailEvent mail)
    {
        await _pushNotificationOpenGate.WaitAsync();
        try
        {
            if (_pushShuttingDown) return;
            RestoreFromTray();
            ShowWorkspace(MailWorkspace);

            // Reuse the normal serialized account-switch procedure instead of
            // raising a synthetic click which would race with folder loading.
            var generation = Interlocked.Increment(ref _accountSwitchGeneration);
            await _accountSwitchGate.WaitAsync();
            try
            {
                while (_loadingFolder)
                    await Task.Delay(50);
                if (generation != Volatile.Read(ref _accountSwitchGeneration))
                    return;
                if (!RestoreSavedAuthorization(mail.Account))
                {
                    FolderStatusText.Text = "Не удалось открыть письмо: " +
                        "нужна авторизация аккаунта " + mail.Account + ".";
                    return;
                }
                RefreshAccountRail(mail.Account);
                var requestedFolder = mail.FolderId is int id && id >= 0 ? id : 0;
                await LoadFolderAsync(requestedFolder, generation);
            }
            finally { _accountSwitchGate.Release(); }

            if (generation != Volatile.Read(ref _accountSwitchGeneration))
                return;
            var message = FindPushTargetInCurrentList(mail);
            if (message is null)
            {
                // The letter may have been moved after the event. Search across
                // server-side folders, but NEVER open a merely similar message.
                var query = !string.IsNullOrWhiteSpace(mail.Subject) ? mail.Subject :
                    !string.IsNullOrWhiteSpace(mail.Sender) ? mail.Sender : null;
                if (!string.IsNullOrWhiteSpace(query))
                {
                    try
                    {
                        var result = await _mailRu.SearchMessagesAsync(
                            _accessToken!, mail.Account, query, limit: 200);
                        if (generation != Volatile.Read(ref _accountSwitchGeneration))
                            return;
                        message = FindPushTarget(mail, result.Messages);
                        if (message is not null)
                        {
                            if (message.FolderId is int actualFolder &&
                                actualFolder >= 0 && actualFolder != _currentFolderId)
                            {
                                await LoadFolderAsync(actualFolder, generation);
                                message = FindPushTargetInCurrentList(mail) ?? message;
                            }

                            if (!_currentMessages.Any(m => m.Id == message.Id))
                            {
                                // Server results can include a message outside
                                // the first loaded folder page. Display it in the
                                // same existing search-results list.
                                _currentMessages = result.Messages.ToList();
                                _serverSearchMode = true;
                                ClearPushNavigationFilters();
                                ApplyFilters();
                            }
                        }
                    }
                    catch (Exception error)
                    {
                        DiagnosticLog.Write("push_notification_search", error.GetType().Name);
                    }
                }
            }

            if (message is null)
            {
                FolderStatusText.Text =
                    "Почтовый аккаунт открыт, но конкретное письмо не найдено. " +
                    "Возможно, оно перемещено или удалено.";
                return;
            }
            ClearPushNavigationFilters();
            ApplyFilters();
            MessagesGrid.SelectedItem = message;
            MessagesGrid.ScrollIntoView(message);
            await ActivateMailPreviewAsync(message);
            FolderStatusText.Text = "Открыто письмо из уведомления.";
        }
        catch (Exception error)
        {
            DiagnosticLog.Write("push_notification_open", error.GetType().Name);
            FolderStatusText.Text = "Не удалось открыть письмо из уведомления.";
        }
        finally { _pushNotificationOpenGate.Release(); }
    }

    private MailRuMessageSummary? FindPushTargetInCurrentList(PushMailEvent mail) =>
        FindPushTarget(mail, _currentMessages);

    private static MailRuMessageSummary? FindPushTarget(
        PushMailEvent mail, IEnumerable<MailRuMessageSummary> messages)
    {
        // Only the PushMe id can identify the exact letter. Sender + subject
        // are display/search hints, never permission to open another message.
        if (string.IsNullOrWhiteSpace(mail.MessageId))
            return null;
        return messages.FirstOrDefault(m =>
            string.Equals(m.Id, mail.MessageId, StringComparison.Ordinal));
    }

    private void ClearPushNavigationFilters()
    {
        FilterSenderTextBox.Clear();
        FilterSubjectTextBox.Clear();
        FilterQueryTextBox.Clear();
        FilterAttachmentsCheckBox.IsChecked = false;
        FilterFromDatePicker.SelectedDate = null;
        FilterToDatePicker.SelectedDate = null;
    }
}
