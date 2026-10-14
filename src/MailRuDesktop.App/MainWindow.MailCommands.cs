using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private enum MailMarkKind { Unread, Flagged, Pinned }

    // All mark, batch and move operations use the same confirmation and
    // account/folder guard. The transport methods themselves remain unchanged.
    private Task<MailCommandOutcome> ExecuteMailCommandAsync(
        Func<string, string, Task<MailRuCommandResult>> send,
        Action applyConfirmed)
    {
        var token = _accessToken;
        if (string.IsNullOrWhiteSpace(token))
            throw new InvalidOperationException("Нет access_token. Выполните авторизацию заново.");

        var account = _activeLogin ?? string.Empty;
        var folder = _currentFolderId;
        return MailCommandExecution.RunAsync(
            () => send(token, account),
            () => string.Equals(_activeLogin ?? string.Empty, account,
                      StringComparison.OrdinalIgnoreCase) &&
                  string.Equals(_accessToken, token, StringComparison.Ordinal) &&
                  _currentFolderId == folder,
            raw => ResponseTextBox.Text = raw,
            applyConfirmed);
    }

    private async Task ChangeMessageMarkAsync(
        MailRuMessageSummary message,
        MailMarkKind mark,
        bool enabled,
        bool showSuccess = true)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
            return;

        var operationFolder = message.FolderId ?? _currentFolderId;
        var category = mark switch
        {
            MailMarkKind.Flagged => "message_flag",
            MailMarkKind.Pinned => "message_pin_context",
            _ => "message_marks"
        };
        var rejected = mark switch
        {
            MailMarkKind.Flagged => "Mail.ru отклонил изменение флажка.",
            MailMarkKind.Pinned => "Mail.ru отклонил изменение закрепления.",
            _ => "Mail.ru отклонил изменение статуса."
        };
        var failed = mark switch
        {
            MailMarkKind.Flagged => "Ошибка изменения флажка.",
            MailMarkKind.Pinned => "Ошибка изменения закрепления.",
            _ => "Ошибка изменения статуса."
        };

        try
        {
            var outcome = await ExecuteMailCommandAsync(
                (token, account) => mark switch
                {
                    MailMarkKind.Unread => _mailRu.SetUnreadAsync(
                        token, account, message.Id, enabled),
                    MailMarkKind.Flagged => _mailRu.SetFlaggedAsync(
                        token, account, message.Id, operationFolder, enabled),
                    MailMarkKind.Pinned => _mailRu.SetPinnedAsync(
                        token, account, message.Id, operationFolder, enabled),
                    _ => throw new ArgumentOutOfRangeException(nameof(mark))
                },
                () =>
                {
                    var current = _currentMessages.FirstOrDefault(row =>
                        string.Equals(row.Id, message.Id, StringComparison.Ordinal));
                    if (current is null)
                        return;

                    var updated = mark switch
                    {
                        MailMarkKind.Unread => current with { Unread = enabled },
                        MailMarkKind.Flagged => current with { Flagged = enabled },
                        MailMarkKind.Pinned => current with { Pinned = enabled },
                        _ => throw new ArgumentOutOfRangeException(nameof(mark))
                    };

                    if (mark == MailMarkKind.Unread && current.Unread != enabled)
                    {
                        if (enabled)
                            _previewAutoReadMessageId = null;
                        if (_currentFolderId == 0)
                            AdjustActiveInboxUnread(enabled ? 1 : -1);
                    }
                    ReplaceMessage(current, updated);
                });

            if (outcome == MailCommandOutcome.Rejected)
            {
                FolderStatusText.Text = rejected;
                return;
            }
            if (outcome != MailCommandOutcome.Confirmed || !showSuccess)
                return;

            if (mark == MailMarkKind.Pinned)
                FolderStatusText.Text = enabled ? "Письмо закреплено." : "Письмо откреплено.";
            else if (mark == MailMarkKind.Unread)
                FolderStatusText.Text = enabled
                    ? "Письмо помечено непрочитанным."
                    : "Письмо помечено прочитанным.";
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = failed;
            DiagnosticLog.Write(category, ex.GetType().Name + ": " + ex.Message);
        }
    }
}
