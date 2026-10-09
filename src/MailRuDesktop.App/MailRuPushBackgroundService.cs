using System.Net;
using System.Security.Cryptography;

namespace MailRuDesktop.App;

/// <summary>
/// Independent long-running PushMe sessions for saved Mail.ru mailboxes.
/// There is no timer-based mailbox polling: only subscribed Google MCS events
/// cause a folder refresh. Every recipient is independent of the phone.
/// </summary>
internal sealed class MailRuPushBackgroundService : IDisposable
{
    private sealed class Session
    {
        public required string Login { get; init; }
        public required string AccessToken { get; init; }
        public required CancellationTokenSource Cancellation { get; init; }
        public Task Runner { get; set; } = Task.CompletedTask;
    }

    private readonly object _sync = new();
    private readonly Dictionary<string, Session> _sessions =
        new(StringComparer.OrdinalIgnoreCase);
    private readonly HashSet<string> _recentMessages = new(StringComparer.Ordinal);
    private readonly Queue<string> _recentOrder = new();
    private readonly List<Task> _retiredRunners = [];
    private readonly Action<string, string> _onStatus;
    private readonly Action<string> _onNewMail;
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
        get { lock (_sync) return _sessions.Count; }
    }

    /// <summary>
    /// Called on the WPF UI thread. Credentials stay in memory, and only
    /// accounts with locally saved access tokens are admitted.
    /// </summary>
    internal void Reconcile(IEnumerable<(string Login, string Token)> accounts, bool enabled)
    {
        var wanted = enabled
            ? accounts
                .Where(a => !string.IsNullOrWhiteSpace(a.Login) &&
                            !string.IsNullOrWhiteSpace(a.Token))
                .GroupBy(a => a.Login, StringComparer.OrdinalIgnoreCase)
                .Select(g => g.First())
                .ToDictionary(a => a.Login, a => a.Token,
                    StringComparer.OrdinalIgnoreCase)
            : new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase);

        lock (_sync)
        {
            if (_stopping) return;
            _enabled = enabled;
            _retiredRunners.RemoveAll(task => task.IsCompleted);
            foreach (var existing in _sessions.Values.ToArray())
            {
                if (wanted.TryGetValue(existing.Login, out var token) &&
                    string.Equals(token, existing.AccessToken, StringComparison.Ordinal))
                    continue;
                existing.Cancellation.Cancel();
                _retiredRunners.Add(existing.Runner);
                _sessions.Remove(existing.Login);
            }
            foreach (var item in wanted)
            {
                if (_sessions.ContainsKey(item.Key)) continue;
                var session = new Session
                {
                    Login = item.Key, AccessToken = item.Value,
                    Cancellation = new CancellationTokenSource()
                };
                _sessions.Add(item.Key, session);
                session.Runner = Task.Run(() => RunMailboxAsync(session));
            }
        }
    }

    private async Task RunMailboxAsync(Session session)
    {
        // A failed server session never triggers tight re-registration loops.
        // A successful MCS channel is kept open indefinitely.
        var failures = 0;
        try
        {
            while (!session.Cancellation.IsCancellationRequested)
            {
                var started = DateTimeOffset.UtcNow;
                try
                {
                    using var transport = new MailRuPushProbe();
                    // Multiple distinct mailboxes get independent receiver tokens
                    // and channels so the event always belongs to one account.
                    await transport.RunAsync(
                        session.Login, session.AccessToken,
                        text => _onStatus(session.Login, SanitizedState(text)),
                        () => OnNewMail(session.Login),
                        session.Cancellation.Token,
                        continuous: true,
                        shouldDeliver: message => AcceptMessage(session.Login, message));
                }
                catch (OperationCanceledException)
                    when (session.Cancellation.IsCancellationRequested)
                {
                    break;
                }
                catch
                {
                    _onStatus(session.Login, "Ожидание повторного подключения.");
                }
                if (session.Cancellation.IsCancellationRequested) break;
                // If a connection lived >15min, reset the retry count.
                failures = (DateTimeOffset.UtcNow - started) > TimeSpan.FromMinutes(15)
                    ? 0 : Math.Min(failures + 1, 5);
                var delay = RetryDelay(failures);
                _onStatus(session.Login,
                    "Соединение прервано. Повтор через " +
                    (int)delay.TotalSeconds + " секунд.");
                await Task.Delay(delay, session.Cancellation.Token);
            }
        }
        catch (OperationCanceledException) when (session.Cancellation.IsCancellationRequested)
        {
            // Expected when the user disables notifications or exits.
        }
        finally
        {
            _onStatus(session.Login, "Получение уведомлений остановлено.");
        }
    }

    internal static TimeSpan RetryDelay(int failures) => TimeSpan.FromSeconds(
        failures switch { <= 0 => 30, 1 => 45, 2 => 90, 3 => 180, 4 => 360, _ => 600 });

    internal bool AcceptMessage(string login, byte[] data)
    {
        if (!PushWire.IsMailNewMessage(data)) return false;
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

    private void OnNewMail(string login) => _onNewMail(login);

    private static string SanitizedState(string text)
    {
        // Probe state strings must never include tokens, addresses, or raw
        // server JSON. The current probe emits only pre-defined categories.
        return text.Length > 220 ? text[..220] : text;
    }

    internal async Task StopAsync()
    {
        Task[] runners;
        lock (_sync)
        {
            _stopping = true;
            _enabled = false;
            runners = _sessions.Values.Select(s =>
            {
                s.Cancellation.Cancel();
                return s.Runner;
            }).Concat(_retiredRunners).ToArray();
            _sessions.Clear();
            _retiredRunners.Clear();
        }
        try
        {
            await Task.WhenAll(runners).WaitAsync(TimeSpan.FromSeconds(90));
        }
        catch (TimeoutException)
        {
            // Preserve UI shutdown even if the remote server is unreachable.
        }
        catch (OperationCanceledException)
        {
        }
    }

    public void Dispose()
    {
        lock (_sync)
        {
            _stopping = true;
            foreach (var session in _sessions.Values)
                session.Cancellation.Cancel();
            _sessions.Clear();
        }
    }
}
