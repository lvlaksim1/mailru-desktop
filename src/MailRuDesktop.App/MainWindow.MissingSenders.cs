using System.Threading;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private long _missingSenderLoadGeneration;

    // Mail.ru may omit correspondents from the folder's compact smart-thread
    // result. Fetch only those missing senders, without marking letters read.
    // This starts automatically; opening the letter is no longer necessary.
    private void ScheduleMissingSenderResolution()
    {
        var generation = ++_missingSenderLoadGeneration;
        var account = _activeLogin;
        var token = _accessToken;
        var folder = _currentFolderId;
        if (string.IsNullOrWhiteSpace(account) || string.IsNullOrWhiteSpace(token))
            return;

        var lacking = _currentMessages
            .Where(item => string.IsNullOrWhiteSpace(item.SenderEmail) &&
                           string.IsNullOrWhiteSpace(item.SenderName))
            .Take(20)
            .Select(item => item.Id)
            .Distinct(StringComparer.Ordinal)
            .ToArray();

        if (lacking.Length == 0)
            return;

        _ = ResolveMissingSendersAsync(lacking, account, token, folder, generation);
    }

    private async Task ResolveMissingSendersAsync(
        IReadOnlyList<string> ids, string account, string token,
        int folder, long generation)
    {
        bool Stale() => generation != _missingSenderLoadGeneration ||
                        folder != _currentFolderId ||
                        !string.Equals(account, _activeLogin, StringComparison.OrdinalIgnoreCase);

        // The extra server requests are serialized, bounded and spaced apart.
        // A stale mailbox or account switch stops the remaining work.
        for (var index = 0; index < ids.Count; index++)
        {
            if (Stale())
                return;

            if (index > 0)
            {
                await Task.Delay(TimeSpan.FromSeconds(5));
                if (Stale()) return;
            }

            var id = ids[index];
            var current = _currentMessages.FirstOrDefault(message => message.Id == id);
            if (current is null || !string.IsNullOrWhiteSpace(current.SenderDisplay))
                continue;

            try
            {
                var full = await _mailRu.GetFullMessageAsync(token, id, markRead: false);
                if (Stale())
                    return;

                if (string.IsNullOrWhiteSpace(full.FromName) &&
                    string.IsNullOrWhiteSpace(full.FromEmail))
                    continue;

                var latest = _currentMessages.FirstOrDefault(message => message.Id == id);
                if (latest is null)
                    continue;

                ReplaceMessage(latest, latest with
                {
                    SenderName = full.FromName,
                    SenderEmail = full.FromEmail
                });
            }
            catch (Exception ex)
            {
                DiagnosticLog.Write("missing_sender_lookup",
                    ex.GetType().Name + ": " + ex.Message);
            }
        }
    }
}
