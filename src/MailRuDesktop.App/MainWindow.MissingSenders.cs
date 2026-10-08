using System.Threading;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private long _missingSenderLoadGeneration;

    private async Task PrefetchFirstMissingSendersAsync(
        List<MailRuMessageSummary> messages, string? token)
    {
        if (string.IsNullOrWhiteSpace(token))
            return;

        var missing = messages
            .Where(message => string.IsNullOrWhiteSpace(message.SenderEmail) &&
                              string.IsNullOrWhiteSpace(message.SenderName))
            .Take(4).ToArray();

        // A few missing correspondents can be populated before first paint.
        // Never hold the whole folder open for a large queue of slow requests.
        if (missing.Length is 0 or > 3)
            return;

        using var timeBudget = new CancellationTokenSource(TimeSpan.FromSeconds(3));
        foreach (var item in missing)
        {
            if (timeBudget.IsCancellationRequested)
                break;
            try
            {
                var full = await _mailRu.GetFullMessageAsync(token, item.Id,
                    markRead: false, cancellationToken: timeBudget.Token);
                if (!string.Equals(full.Id, item.Id, StringComparison.Ordinal))
                    continue;
                var index = messages.FindIndex(message => message.Id == item.Id);
                if (index >= 0)
                    messages[index] = messages[index] with
                    {
                        SenderName = full.FromName,
                        SenderEmail = full.FromEmail
                    };
            }
            catch (OperationCanceledException) when (timeBudget.IsCancellationRequested)
            {
                break; // Resume normal folder rendering; later retry is bounded.
            }
            catch (Exception ex)
            {
                DiagnosticLog.Write("first_paint_sender",
                    ex.GetType().Name + ": " + ex.Message);
            }
        }
    }


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
                await Task.Delay(TimeSpan.FromMilliseconds(1200));
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
