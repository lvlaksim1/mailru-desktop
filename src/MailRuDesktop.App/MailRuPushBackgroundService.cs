using System.IO;
using System.Net.Http;

namespace MailRuDesktop.App;

/// <summary>
/// Each worker owns one independent Google recipient and assigned group. Reconciliations are serialized: overlapping receivers
/// with the same persistent identity are never started.
/// </summary>
internal sealed class MailRuPushBackgroundService : IDisposable
{
    private readonly object _sync = new();
    private readonly string _recipientId;
    private readonly HashSet<string> _recentMessages = new(StringComparer.Ordinal);
    private readonly Queue<string> _recentOrder = new();
    private readonly Action<string, string> _onStatus;
    private readonly Action<string> _onNewMail;
    private readonly Action<PushMailEvent>? _onMailEvent;
    private readonly Action<string> _onMcsState;
    private readonly Func<bool> _suppressReplays;
    private Dictionary<string, string> _accounts = new(StringComparer.OrdinalIgnoreCase);
    private CancellationTokenSource? _currentCancellation;
    private Task _tail = Task.CompletedTask;
    private Task _removalTail = Task.CompletedTask;
    private bool _enabled;
    private bool _paused;
    private bool _stopping;
    private bool _retryOnFailure = true;
    private bool _preserveOtherAccounts;
    private bool _listenOnly;

    internal MailRuPushBackgroundService(
        Action<string, string> onStatus, Action<string> onNewMail,
        Action<string>? onMcsState = null,
        string recipientId = SharedGooglePushIdentityStore.PrimaryRecipientId,
        Action<PushMailEvent>? onMailEvent = null,
        Func<bool>? suppressReplays = null)
    {
        if (!SharedGooglePushIdentityStore.ValidRecipientId(recipientId))
            throw new ArgumentException("Invalid recipient slot.", nameof(recipientId));
        _recipientId = recipientId;
        _onStatus = onStatus;
        _onNewMail = onNewMail;
        _onMailEvent = onMailEvent;
        _onMcsState = onMcsState ?? (_ => { });
        _suppressReplays = suppressReplays ?? (() => true);
    }

    internal bool Enabled
    {
        get { lock (_sync) return _enabled && !_stopping; }
    }

    internal int ActiveAccountCount
    {
        get { lock (_sync) return _enabled && !_stopping ? _accounts.Count : 0; }
    }

    internal void Reconcile(IEnumerable<(string Login, string Token)> accounts, bool enabled,
        bool pauseForDiagnostics = false, bool retryOnFailure = true,
        bool preserveOtherAccounts = false, bool listenOnly = false)
    {
        var desired = enabled
            ? accounts
                .Where(x => !string.IsNullOrWhiteSpace(x.Login) &&
                            !string.IsNullOrWhiteSpace(x.Token))
                .GroupBy(x => x.Login, StringComparer.OrdinalIgnoreCase)
                .ToDictionary(x => x.Key, x => x.First().Token,
                    StringComparer.OrdinalIgnoreCase)
            : new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase);

        PushDiagnostics.Record("SERVICE", enabled ? "RECONCILE_ENABLED" : "RECONCILE_DISABLED", desired.Count);
        lock (_sync)
        {
            if (_stopping) return;
            if (_enabled == enabled && _paused == pauseForDiagnostics &&
                _retryOnFailure == retryOnFailure &&
                _preserveOtherAccounts == preserveOtherAccounts &&
                _listenOnly == listenOnly &&
                SameAccounts(_accounts, desired)) return;

            // Original SDK keeps the Firebase receiver when a mailbox is deleted.
            // Only remove these subscriptions; keep the active MCS channel, token,
            // and subscriptions of every retained mailbox untouched.
            if (!listenOnly && enabled && !pauseForDiagnostics && _enabled && !_paused &&
                CanRemoveWithoutReconnect(_accounts, desired))
            {
                var removed = _accounts.Keys.Except(
                    desired.Keys, StringComparer.OrdinalIgnoreCase).ToArray();
                _accounts = desired;
                PushDiagnostics.Record("SERVICE", "MAILBOX_REMOVED_WITHOUT_MCS_RESTART", removed.Length);
                QueueAccountRemoval(removed);
                return;
            }

            var previousAccounts = _accounts.Keys.ToArray();
            _accounts = desired;
            _enabled = enabled;
            _paused = pauseForDiagnostics;
            _retryOnFailure = retryOnFailure;
            _preserveOtherAccounts = preserveOtherAccounts;
            _listenOnly = listenOnly;
            // Cancels the active MCS reader, NOT the persisted Google identity.
            PushDiagnostics.Record("SERVICE", "RECEIVER_RESTART_REQUIRED");
            _currentCancellation?.Cancel();
            var current = new CancellationTokenSource();
            _currentCancellation = current;
            var previous = _tail;

            if (enabled && desired.Count > 0)
            {
                var snapshot = new Dictionary<string, string>(
                    desired, StringComparer.OrdinalIgnoreCase);
                _tail = Task.Run(async () =>
                {
                    await AwaitQuietly(previous);
                    if (!current.IsCancellationRequested)
                        await RunSharedWorkerAsync(snapshot, current.Token);
                });
            }
            else if (enabled && desired.Count == 0)
            {
                // Removing the last mailbox is not a global Google opt-out.
                // Keep the Google identity, unregister just that mailbox.
                var pending = previousAccounts;
                _tail = Task.Run(async () =>
                {
                    await AwaitQuietly(previous);
                    Task removal;
                    lock (_sync)
                    {
                        QueueAccountRemoval(pending);
                        removal = _removalTail;
                    }
                    await AwaitQuietly(removal);
                });
            }
            else if (pauseForDiagnostics)
            {
                // Manual three-minute test pauses the shared reader only.
                // Do NOT revoke its durable Google identity or server subscriptions.
                _tail = Task.Run(async () => await AwaitQuietly(previous));
            }
            else
            {
                // Opt-out or last-account removal: revoke the shared token only
                // AFTER the old MCS reader has exited, then drop local identity.
                _tail = Task.Run(async () =>
                {
                    await AwaitQuietly(previous);
                    try
                    {
                        using var probe = new MailRuPushProbe();
                        using var cleanup = new CancellationTokenSource(
                            TimeSpan.FromSeconds(40));
                        var revoked = await probe.UnsubscribeSharedAsync(cleanup.Token, _recipientId);
                        foreach (var login in previousAccounts)
                            _onStatus(login, revoked
                                ? "Получение уведомлений остановлено."
                                : "Удаление общей подписки не подтверждено.");
                    }
                    catch
                    {
                        foreach (var login in previousAccounts)
                            _onStatus(login, "Удаление общей подписки не подтверждено.");
                    }
                });
            }
        }
    }

    /// <summary>
    /// Close only the MCS TCP/TLS reader, keep Google device credentials,
    /// PushMe registrations, pending unsubscribes and all group assignments.
    /// Unlike Reconcile(enabled:false), this NEVER sends unsubscribe_by_token.
    /// </summary>
    internal void StopListeningOnly()
    {
        lock (_sync)
        {
            if (_stopping) return;
            _enabled = false;
            _accounts.Clear();
            _currentCancellation?.Cancel();
            PushDiagnostics.Record("SERVICE", "MCS_ONLY_STOP_REQUESTED");
        }
        _onMcsState("STOPPED");
    }

    // Called only while holding _sync. A serial queue prevents overlapping
    // removal requests from clearing each other's persisted subscriber roster.
    private void QueueAccountRemoval(string[] removed)
    {
        if (removed.Length == 0) return;
        var preceding = _removalTail;
        _removalTail = Task.Run(async () =>
        {
            await AwaitQuietly(preceding);
            using var probe = new MailRuPushProbe();
            foreach (var account in removed)
            {
                try
                {
                    using var timeout = new CancellationTokenSource(TimeSpan.FromSeconds(35));
                    PushDiagnostics.Record("PUSHME", "ACCOUNT_UNSUBSCRIBE_START");
                    var removedFromServer = await probe.UnsubscribeAccountAsync(
                        account, timeout.Token, recipientId: _recipientId);
                    PushDiagnostics.Record("PUSHME", removedFromServer ?
                        "ACCOUNT_UNSUBSCRIBE_OK" : "ACCOUNT_UNSUBSCRIBE_DEFERRED");
                    _onStatus(account, removedFromServer
                        ? "PushMe: адресная подписка удалена."
                        : "PushMe: адресная отписка ожидает повторной попытки.");
                }
                catch (Exception failure)
                {
                    PushDiagnostics.Failure("ACCOUNT_UNSUBSCRIBE", failure);
                    _onStatus(account, "PushMe: адресная отписка ожидает повторной попытки.");
                    // DPAPI roster remains unchanged; next connection retries.
                }
            }
        });
    }

    private async Task RunSharedWorkerAsync(
        IReadOnlyDictionary<string, string> snapshot, CancellationToken cancellationToken)
    {
        var failures = 0;
        var attempt = 0;
        IReadOnlyDictionary<string, string> current = snapshot;
        try
        {
            while (!cancellationToken.IsCancellationRequested)
            {
                // Account deletion is applied in-place without dropping MCS.
                // On the NEXT genuine reconnect use the current roster, not
                // the startup snapshot (which might include removed accounts).
                lock (_sync)
                {
                    if (_stopping || !_enabled || _paused)
                        break;
                    current = new Dictionary<string, string>(
                        _accounts, StringComparer.OrdinalIgnoreCase);
                }
                if (current.Count == 0) break;
                var started = DateTimeOffset.UtcNow;
                _onMcsState(attempt == 0 ? "CONNECTING" : "RECONNECTING");
                PushDiagnostics.BeginAttempt(++attempt);
                try
                {
                    using var probe = new MailRuPushProbe();
                    await probe.RunSharedAsync(current,
                        (account, text) => _onStatus(account, SanitizedState(text)),
                        account =>
                        {
                            lock (_sync)
                            {
                                if (!_enabled || !_accounts.ContainsKey(account) || _stopping)
                                    return;
                            }
                            _onNewMail(account);
                        },
                        AcceptMessage,
                        cancellationToken,
                        preserveOtherAccounts: _preserveOtherAccounts,
                        listenOnly: _listenOnly,
                        onMcsState: _onMcsState,
                        recipientId: _recipientId,
                        onMailEvent: _onMailEvent);
                }
                catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
                {
                    break;
                }
                catch (HttpRequestException error)
                {
                    PushDiagnostics.Failure("WORKER_HTTP", error);
                    // Original SDK distinguishes HTTP, TLS and transport failures.
                    // Do not expose exception.Message: URLs and tokens may leak.
                    var status = error.StatusCode is { } code
                        ? "PushMe: HTTP " + (int)code
                        : "Сетевая ошибка: " + MailRuPushProbe.ClassifyNetworkError(error);
                    foreach (var account in current.Keys)
                        _onStatus(account, status);
                }
                catch (System.Security.Authentication.AuthenticationException error)
                {
                    PushDiagnostics.Failure("WORKER_TLS", error);
                    foreach (var account in current.Keys)
                        _onStatus(account, "Google MCS: ошибка проверки сертификата TLS.");
                }
                catch (System.Net.Sockets.SocketException error)
                {
                    PushDiagnostics.Failure("WORKER_SOCKET", error);
                    foreach (var account in current.Keys)
                        _onStatus(account, "Google MCS: ошибка сетевого соединения.");
                }
                catch (IOException error)
                {
                    PushDiagnostics.Failure("WORKER_IO", error);
                    foreach (var account in current.Keys)
                        _onStatus(account, "Google MCS: защищённое соединение прервано.");
                }
                catch (InvalidOperationException error)
                {
                    PushDiagnostics.Failure("WORKER_PROTOCOL", error);
                    foreach (var account in current.Keys)
                        _onStatus(account, "Google/PushMe: сервер отклонил запрос; см. предыдущий статус.");
                }
                catch (Exception error)
                {
                    PushDiagnostics.Failure("WORKER_OTHER", error);
                    foreach (var account in current.Keys)
                        _onStatus(account, "Google/PushMe: непредвиденная ошибка обработки.");
                }
                if (cancellationToken.IsCancellationRequested) break;
                _onMcsState("ERROR");
                bool retry;
                lock (_sync) retry = _retryOnFailure;
                if (!retry)
                {
                    PushDiagnostics.Record("WORKER", "MANUAL_TEST_NO_AUTOMATIC_RETRY");
                    break;
                }
                failures = (DateTimeOffset.UtcNow - started) > TimeSpan.FromMinutes(15)
                    ? 0 : Math.Min(failures + 1, 5);
                var delay = RetryDelay(failures);
                PushDiagnostics.Record("WORKER", "RETRY_DELAY_SECONDS", (int)delay.TotalSeconds);
                var pushMeRejected = PushDiagnostics.LastFailure.StartsWith(
                    "PUSHME_RESPONSE_REJECTED:", StringComparison.Ordinal);
                foreach (var account in current.Keys)
                    _onStatus(account,
                        (pushMeRejected
                            ? "PushMe отклонил регистрацию. Повтор попытки через "
                            : "Соединение прервано. Повтор через ") +
                        (int)delay.TotalSeconds + " секунд.");
                await Task.Delay(delay, cancellationToken);
            }
        }
        catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
        {
            // Closing the app or changing the list only closes the MCS stream.
        }
        finally
        {
            _onMcsState("STOPPED");
            PushDiagnostics.Record("WORKER", "RECEIVER_STOPPED");
            foreach (var account in current.Keys)
                _onStatus(account, "Получение уведомлений остановлено.");
        }
    }

    // Pure reconciliation rule: only account removals; retained OAuth values
    // must be unchanged. Mirrors APK per-account unsubscribe without MCS reset.
    internal static bool CanRemoveWithoutReconnect(
        IReadOnlyDictionary<string, string> previous,
        IReadOnlyDictionary<string, string> wanted) =>
        wanted.Count > 0 && wanted.Count < previous.Count &&
        wanted.All(pair => previous.TryGetValue(pair.Key, out var token) &&
                           string.Equals(token, pair.Value, StringComparison.Ordinal));

    private static bool SameAccounts(
        IReadOnlyDictionary<string, string> oldAccounts,
        IReadOnlyDictionary<string, string> newAccounts) =>
        oldAccounts.Count == newAccounts.Count &&
        oldAccounts.All(item => newAccounts.TryGetValue(item.Key, out var value) &&
                                string.Equals(item.Value, value, StringComparison.Ordinal));

    private static async Task AwaitQuietly(Task previous)
    {
        try { await previous; }
        catch (Exception) { /* A retired generation must not stall its successor. */ }
    }

    internal static TimeSpan RetryDelay(int failures) => TimeSpan.FromSeconds(
        failures switch { <= 0 => 30, 1 => 45, 2 => 90, 3 => 180, 4 => 360, _ => 600 });

    internal bool AcceptMessage(string login, byte[] data)
    {
        var embeddedAccount = PushWire.NewMailAccount(data);
        if (!string.Equals(embeddedAccount, login, StringComparison.OrdinalIgnoreCase))
            return false;
        var id = login.ToLowerInvariant() + ":" + PushWire.MessageIdentifier(data);
        lock (_sync)
        {
            // Drop events for removed mailboxes even before server unsubscribe
            // has completed. Never apply an old MCS event to another account.
            if (!_enabled || !_accounts.ContainsKey(login) || _stopping) return false;
            // The user may disable the local workaround at runtime.
            // Both in-memory and on-disk replay suppression are bypassed;
            // transport acknowledgments are handled earlier by MCS.
            if (!_suppressReplays()) return true;
            if (_recentMessages.Contains(id))
            {
                PushDiagnostics.Record("MCS", "REPLAY_MEMORY_SUPPRESSED");
                return false;
            }
            string? messageId = null;
            try { messageId = PushMailEvent.Parse(data)?.MessageId; }
            catch (InvalidDataException) { /* MCS hash remains a fallback. */ }
            if (!PushProcessedEventStore.MarkDelivered(login, messageId, data,
                    out var recorded))
            {
                _recentMessages.Add(id);
                _recentOrder.Enqueue(id);
                PushDiagnostics.Record("MCS", "REPLAY_DISK_SUPPRESSED");
                return false;
            }
            if (recorded)
                PushDiagnostics.Record("MCS", "EVENT_DEDUP_PERSISTED");
            _recentMessages.Add(id);
            _recentOrder.Enqueue(id);
            while (_recentOrder.Count > 1024)
                _recentMessages.Remove(_recentOrder.Dequeue());
            return true;
        }
    }

    private static string SanitizedState(string text) =>
        text.Length > 220 ? text[..220] : text;

    internal async Task StopAsync()
    {
        Task pending;
        lock (_sync)
        {
            _stopping = true;
            _enabled = false;
            _currentCancellation?.Cancel();
            pending = Task.WhenAll(_tail, _removalTail);
            _accounts.Clear();
        }
        try { await pending.WaitAsync(TimeSpan.FromSeconds(90)); }
        catch (TimeoutException) { /* Do not block Windows shutdown forever. */ }
        catch (OperationCanceledException) { }
    }

    public void Dispose()
    {
        lock (_sync)
        {
            _stopping = true;
            _currentCancellation?.Cancel();
            _accounts.Clear();
        }
    }
}
