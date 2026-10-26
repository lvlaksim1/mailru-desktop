using System.IO;
using System.Text;
using System.Text.Json;
using Microsoft.Web.WebView2.Core;

namespace MailRuDesktop.App;

internal sealed class WebView2NetworkJournal : IDisposable
{
    private readonly CoreWebView2 _browser;
    private readonly Func<string?> _login;
    private readonly Action<string>? _onPasswordRestorationSubmitted;
    private readonly List<(CoreWebView2DevToolsProtocolEventReceiver Receiver,
        EventHandler<CoreWebView2DevToolsProtocolEventReceivedEventArgs> Handler)> _hooks = [];
    private static readonly object Sync = new();
    private bool _closed;

    private static readonly string[] Events =
    [
        "requestWillBeSent", "requestWillBeSentExtraInfo",
        "responseReceived", "responseReceivedExtraInfo",
        "dataReceived", "loadingFinished", "loadingFailed",
        "requestServedFromCache", "webSocketCreated", "webSocketClosed",
        "webSocketWillSendHandshakeRequest", "webSocketHandshakeResponseReceived",
        "webSocketFrameSent", "webSocketFrameReceived",
        "webSocketFrameError", "eventSourceMessageReceived"
    ];

    internal WebView2NetworkJournal(CoreWebView2 browser, string login,
        Action<string>? onPasswordRestorationSubmitted = null)
        : this(browser, () => login, onPasswordRestorationSubmitted) { }

    internal WebView2NetworkJournal(CoreWebView2 browser, Func<string?> login)
        : this(browser, login, null) { }

    private WebView2NetworkJournal(CoreWebView2 browser, Func<string?> login,
        Action<string>? onPasswordRestorationSubmitted)
    {
        _browser = browser;
        _login = login;
        _onPasswordRestorationSubmitted = onPasswordRestorationSubmitted;
    }

    // Only the account.mail.ru password-reset confirmation is eligible.
    // Data is read from the already observed CDP event without changing or
    // filtering the full diagnostic journal. Never print the password to logs.
    internal static string? ExtractConfirmedPasswordSubmission(string eventJson)
    {
        try
        {
            using var json = JsonDocument.Parse(eventJson);
            if (!json.RootElement.TryGetProperty("request", out var request) ||
                !request.TryGetProperty("url", out var url) ||
                !Uri.TryCreate(url.GetString(), UriKind.Absolute, out var uri) ||
                uri.Scheme != Uri.UriSchemeHttps ||
                !uri.Host.Equals("account.mail.ru", StringComparison.OrdinalIgnoreCase) ||
                !uri.AbsolutePath.Equals("/api/v1/user/password/restore/confirm",
                    StringComparison.OrdinalIgnoreCase) ||
                !request.TryGetProperty("method", out var method) ||
                method.GetString() != "POST" ||
                !request.TryGetProperty("postData", out var postData))
                return null;

            var form = postData.GetString();
            if (string.IsNullOrEmpty(form)) return null;
            foreach (var field in form.Split('&'))
            {
                var separator = field.IndexOf('=');
                if (separator < 0) continue;
                var key = System.Net.WebUtility.UrlDecode(field[..separator]);
                if (!string.Equals(key, "password", StringComparison.Ordinal)) continue;
                var password = System.Net.WebUtility.UrlDecode(field[(separator + 1)..]);
                return string.IsNullOrEmpty(password) ? null : password;
            }
        }
        catch (Exception ex) when (ex is JsonException or ArgumentException or UriFormatException)
        {
            return null;
        }
        return null;
    }

    internal async Task StartAsync()
    {
        foreach (var name in Events)
        {
            var receiver = _browser.GetDevToolsProtocolEventReceiver("Network." + name);
            EventHandler<CoreWebView2DevToolsProtocolEventReceivedEventArgs> handler =
                (_, e) => Observe(name, e.ParameterObjectAsJson);
            receiver.DevToolsProtocolEventReceived += handler;
            _hooks.Add((receiver, handler));
        }
        try
        {
            await _browser.CallDevToolsProtocolMethodAsync("Network.enable", "{}");
            Write("capture-started", "{}");
        }
        catch (Exception ex)
        {
            Write("capture-start-failed", ex.ToString());
        }
    }

    private void Observe(string name, string json)
    {
        Write(name, json);
        if (_closed) return;
        try
        {
            if (name == "requestWillBeSent" &&
                _onPasswordRestorationSubmitted is not null)
            {
                var password = ExtractConfirmedPasswordSubmission(json);
                if (!string.IsNullOrEmpty(password))
                    _onPasswordRestorationSubmitted(password);
            }
            using var data = JsonDocument.Parse(json);
            var root = data.RootElement;
            if (!root.TryGetProperty("requestId", out var id)) return;
            var requestId = id.GetString();
            if (string.IsNullOrEmpty(requestId)) return;
            if (name == "loadingFinished")
                _ = ReadBodyAsync("Network.getResponseBody", requestId);
            else if (name == "requestWillBeSent" &&
                root.TryGetProperty("request", out var request) &&
                request.TryGetProperty("method", out var method) &&
                method.GetString() is not ("GET" or "HEAD"))
                _ = ReadBodyAsync("Network.getRequestPostData", requestId);
        }
        catch (Exception ex) { Write("event-error", ex.ToString()); }
    }

    private async Task ReadBodyAsync(string method, string id)
    {
        try
        {
            var response = await _browser.CallDevToolsProtocolMethodAsync(
                method, JsonSerializer.Serialize(new { requestId = id }));
            Write(method + "-data", JsonSerializer.Serialize(new
            {
                requestId = id,
                response
            }));
        }
        catch (Exception ex)
        {
            Write(method + "-unavailable", JsonSerializer.Serialize(new
            {
                requestId = id,
                error = ex.ToString()
            }));
        }
    }

    private void Write(string category, string raw)
    {
        try
        {
            var now = DateTimeOffset.Now;
            var folder = Path.Combine(Environment.GetFolderPath(
                Environment.SpecialFolder.LocalApplicationData),
                "MailRuDesktop", "http-traffic");
            var file = Path.Combine(folder, now.ToString("yyyyMMdd-HH") +
                "-webview2.jsonl");
            var line = JsonSerializer.Serialize(new
            {
                time = now, account = _login() ?? "(неизвестен)", category, raw
            });
            lock (Sync)
            {
                Directory.CreateDirectory(folder);
                File.AppendAllText(file, line + "\n", new UTF8Encoding(false));
            }
        }
        catch { }
    }

    internal static string CurrentHourReport()
    {
        var now = DateTimeOffset.Now;
        var folder = Path.Combine(Environment.GetFolderPath(
            Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop", "http-traffic");
        var file = Path.Combine(folder, now.ToString("yyyyMMdd-HH") +
            "-webview2.jsonl");
        var output = new StringBuilder();
        output.AppendLine().AppendLine("=== WebView2: сетевые события браузера за текущий час ===");
        try
        {
            if (!File.Exists(file))
                return output.AppendLine("Сетевых событий WebView2 пока нет.").ToString();

            foreach (var line in File.ReadLines(file, Encoding.UTF8))
            {
                try
                {
                    using var document = JsonDocument.Parse(line);
                    var e = document.RootElement;
                    output.AppendLine().Append("Время: ")
                        .Append(e.GetProperty("time").ToString())
                        .Append("; Аккаунт: ")
                        .Append(e.GetProperty("account").GetString())
                        .Append("; Событие: ")
                        .AppendLine(e.GetProperty("category").GetString());
                    output.AppendLine(e.GetProperty("raw").GetString());
                }
                catch (JsonException)
                {
                    output.AppendLine("Строка не разобрана: " + line);
                }
            }
        }
        catch (IOException error)
        {
            output.AppendLine("Ошибка чтения: " + error.Message);
        }
        catch (UnauthorizedAccessException error)
        {
            output.AppendLine("Нет доступа: " + error.Message);
        }
        return output.ToString();
    }

    public void Dispose()
    {
        _closed = true;
        foreach (var (receiver, handler) in _hooks)
            receiver.DevToolsProtocolEventReceived -= handler;
        _hooks.Clear();
    }
}
