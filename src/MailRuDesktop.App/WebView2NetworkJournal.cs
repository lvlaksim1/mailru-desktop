using System.IO;
using System.Text;
using System.Text.Json;
using Microsoft.Web.WebView2.Core;

namespace MailRuDesktop.App;

internal sealed class WebView2NetworkJournal : IDisposable
{
    private readonly CoreWebView2 _browser;
    private readonly string _login;
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

    internal WebView2NetworkJournal(CoreWebView2 browser, string login)
    {
        _browser = browser;
        _login = login;
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
                time = now, account = _login, category, raw
            });
            lock (Sync)
            {
                Directory.CreateDirectory(folder);
                File.AppendAllText(file, line + "\n", new UTF8Encoding(false));
            }
        }
        catch { }
    }

    public void Dispose()
    {
        _closed = true;
        foreach (var (receiver, handler) in _hooks)
            receiver.DevToolsProtocolEventReceived -= handler;
        _hooks.Clear();
    }
}
