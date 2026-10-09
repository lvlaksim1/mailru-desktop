using System.IO;
using System.Net.Http;

namespace MailRuDesktop.App;

/// <summary>
/// One background worker, one Google token and one MCS connection for ALL
/// authorized mailboxes. Reconciliations are serialized: overlapping receivers
/// with the same persistent identity are never started.
/// </summary>
internal sealed class MailRuPushBackgroundService : IDisposable
{
    private readonly object _sync = new();
    private readonly HashSet<string> _recentMessages = new(StringComparer.Ordinal);
    private readonly Queue<string> _recentOrder = new();
    private readonly Action<string, string> _onStatus;
    private readonly Action<string> _onNewMail;
    private Dictionary<string, string> _accounts = new(StringComparer.OrdinalIgnoreCase);
    private CancellationTokenSource? _currentCancellation;
    private Task _tail = Task.CompletedTask;
    private Task _removalTail = Task.CompletedTask;
    private bool _enabled;
    private bool _paused;
    private bool _stopping;

    internal MailRuPushBackgroundService(
        Action<string, string> onStatus, Action<string> onNewMail)
    {
        _onStatus = onStatus;
        _onNewMail = onNewMail;
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
        bool pauseForDiagnostics = false)
    {
        var desired = enabled
            ? accounts
                .Where(x => !string.IsNullOrWhiteSpace(x.Login) &&
                            !string.IsNullOrWhiteSpace(x.Token))
                .GroupBy(x => x.Login, StringComparer.OrdinalIgnoreCase)
                .ToDictionary(x => x.Key, x => x.First().Token,
                    StringComparer.OrdinalIgnoreCase)
            : new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase);

        lock (_sync)
        {
            if (_stopping) return;
            if (_enabled == enabled && _paused == pauseForDiagnostics &&
                SameAccounts(_accounts, desired)) return;

            // Original SDK keeps the Firebase receiver when a mailbox is deleted.
            // Only remove these subscriptions; keep the active MCS channel, token,
            // and subscriptions of every retained mailbox untouched.
            if (enabled && !pauseForDiagnostics && _enabled && !_paused &&
                desired.Count > 0 && desired.Count < _accounts.Count &&
                desired.All(pair => _accounts.TryGetValue(pair.Key, out var token) &&
                                   string.Equals(token, pair.Value, StringComparison.Ordinal)))
            {
                var removed = _accounts.Keys.Except(
                    desired.Keys, StringComparer.OrdinalIgnoreCase).ToArray();
                _accounts = desired;
                QueueAccountRemoval(removed);
                return;
            }

            var previousAccounts = _accounts.Keys.ToArray();
            _accounts = desired;
            _enabled = enabled;
            _paused = pauseForDiagnostics;
            // Cancels the active MCS reader, NOT the persisted Google identity.
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
                        var revoked = await probe.UnsubscribeSharedAsync(cleanup.Token);
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
                    var removedFromServer = await probe.UnsubscribeAccountAsync(
                        account, timeout.Token);
                    _onStatus(account, removedFromServer
                        ? "PushMe: адресная подписка удалена."
                        : "PushMe: адресная отписка ожидает повторной попытки.");
                }
                catch (Exception)
                {
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
        try
        {
            while (!cancellationToken.IsCancellationRequested)
            {
                var started = DateTimeOffset.UtcNow;
                try
                {
                    using var probe = new MailRuPushProbe();
                    await probe.RunSharedAsync(snapshot,
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
                        cancellationToken);
                }
                catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
                {
                    break;
                }
                catch (HttpRequestException error)
                {
                    // Original SDK distinguishes HTTP, TLS and transport failures.
                    // Do not expose exception.Message: URLs and tokens may leak.
                    var status = error.StatusCode is { } code
                        ? "PushMe: HTTP " + (int)code
                        : "Сетевая ошибка: " + MailRuPushProbe.ClassifyNetworkError(error);
                    foreach (var account in snapshot.Keys)
                        _onStatus(account, status);
                }
                catch (System.Security.Authentication.AuthenticationException)
                {
                    foreach (var account in snapshot.Keys)
                        _onStatus(account, "Google MCS: ошибка проверки сертификата TLS.");
                }
                catch (System.Net.Sockets.SocketException)
                {
                    foreach (var account in snapshot.Keys)
                        _onStatus(account, "Google MCS: ошибка сетевого соединения.");
                }
                catch (IOException)
                {
                    foreach (var account in snapshot.Keys)
                        _onStatus(account, "Google MCS: защищённое соединение прервано.");
                }
                catch (InvalidOperationException)
                {
                    foreach (var account in snapshot.Keys)
                        _onStatus(account, "Google/PushMe: сервер отклонил запрос; см. предыдущий статус.");
                }
                catch
                {
                    foreach (var account in snapshot.Keys)
                        _onStatus(account, "Google/PushMe: непредвиденная ошибка обработки.");
                }
                if (cancellationToken.IsCancellationRequested) break;
                failures = (DateTimeOffset.UtcNow - started) > TimeSpan.FromMinutes(15)
                    ? 0 : Math.Min(failures + 1, 5);
                var delay = RetryDelay(failures);
                foreach (var account in snapshot.Keys)
                    _onStatus(account,
                        "Соединение прервано. Повтор через " +
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
            foreach (var account in snapshot.Keys)
                _onStatus(account, "Получение уведомлений остановлено.");
        }
    }

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
            if (_recentMessages.Contains(id)) return false;
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
