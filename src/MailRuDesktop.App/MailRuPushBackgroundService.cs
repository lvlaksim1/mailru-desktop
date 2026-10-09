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
    private bool _enabled;
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

    internal void Reconcile(IEnumerable<(string Login, string Token)> accounts, bool enabled)
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
            if (_enabled == enabled && SameAccounts(_accounts, desired)) return;

            var previousAccounts = _accounts.Keys.ToArray();
            _accounts = desired;
            _enabled = enabled;
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
                        account => _onNewMail(account),
                        AcceptMessage,
                        cancellationToken);
                }
                catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
                {
                    break;
                }
                catch
                {
                    foreach (var account in snapshot.Keys)
                        _onStatus(account, "Ошибка этапа: общий канал недоступен.");
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
            pending = _tail;
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
