using System.IO.Compression;
using System.Net;
using System.Net.Http.Headers;
using System.Text.Json;
using System.Text.RegularExpressions;

const string UserAgent =
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
    "(KHTML, like Gecko) Chrome/146.0.0.0 YaBrowser/26.4.0.0 Safari/537.36";

var login = Environment.GetEnvironmentVariable("MAILRU_TEST_LOGIN");
var cookieHeader = Environment.GetEnvironmentVariable("MAILRU_TEST_WEB_COOKIES");

if (string.IsNullOrWhiteSpace(login))
{
    Console.WriteLine("login_secret: missing");
    return 2;
}

if (string.IsNullOrWhiteSpace(cookieHeader))
{
    Console.WriteLine("web_cookies_secret: missing");
    return 3;
}

Console.WriteLine($"probe_utc: {DateTimeOffset.UtcNow:O}");
Console.WriteLine("mode: authenticated-web-cookie-only");
Console.WriteLine("mobile_access_token: disabled");
Console.WriteLine("touch_token: disabled");
Console.WriteLine("request_spacing_seconds: 6");
Console.WriteLine("web_cookies_secret: present");

using var transport = new PacedTransport(UserAgent, cookieHeader!, TimeSpan.FromSeconds(6));

var inbox = await ProbeInboxAsync(transport);
Console.WriteLine($"inbox_http: {inbox.StatusCode}");
Console.WriteLine($"inbox_marker_user_short: {Bool(inbox.UserShortMarker)}");
Console.WriteLine($"web_token_found: {Bool(inbox.Token is not null)}");

if (inbox.Token is null)
{
    Console.WriteLine("result: authenticated_cookie_did_not_yield_web_token");
    return 20;
}

var webToken = inbox.Token;

var userShort = await ProbeJsonGetAsync(
    transport,
    "user_short",
    BuildUri(
        "https://e.mail.ru/api/v1/user/short",
        new Dictionary<string, string?>
        {
            ["email"] = login,
            ["htmlencoded"] = "false",
            ["token"] = webToken
        }));
PrintJsonProbe(userShort);

var common = new Dictionary<string, string?>
{
    ["ajax_call"] = "1",
    ["x-email"] = login,
    ["email"] = login,
    ["sort"] = "{\"type\":\"date\",\"order\":\"desc\"}",
    ["offset"] = "0",
    ["limit"] = "25",
    ["folder"] = "0",
    ["htmlencoded"] = "false",
    ["last_modified"] = "-1",
    ["filters"] = "{}",
    ["nolog"] = "0",
    ["sortby"] = "D",
    ["api"] = "1",
    ["token"] = webToken
};

var smart = await ProbeJsonGetAsync(
    transport,
    "threads_status_smart",
    BuildUri("https://e.mail.ru/api/v1/threads/status/smart", common));
PrintJsonProbe(smart);

var golang = await ProbeJsonGetAsync(
    transport,
    "threads_status_golang",
    BuildUri("https://e.mail.ru/api/v1/threads/status/golang", common));
PrintJsonProbe(golang);

Console.WriteLine("result: web_token_obtained_and_mailbox_read_probed");
return 0;

static async Task<InboxProbe> ProbeInboxAsync(PacedTransport transport)
{
    using var request = new HttpRequestMessage(HttpMethod.Get, "https://e.mail.ru/inbox/");
    using var response = await transport.SendAsync(request);
    var body = await response.Content.ReadAsStringAsync();

    var markerIndex = body.IndexOf("/api/v1/user/short", StringComparison.OrdinalIgnoreCase);
    var token = ExtractWebToken(body, markerIndex);

    return new InboxProbe((int)response.StatusCode, markerIndex >= 0, token);
}

static string? ExtractWebToken(string payload, int markerIndex)
{
    if (markerIndex >= 0)
    {
        var local = payload.Substring(markerIndex, Math.Min(20000, payload.Length - markerIndex));
        var patterns = new[]
        {
            "token(?:\\\\\"|\")\\s*:\\s*(?:\\\\\"|\")(?<token>[^\"\\\\]+)",
            "\\\"token\\\"\\s*:\\s*\\\"(?<token>[^\\\"]+)\\\""
        };

        foreach (var pattern in patterns)
        {
            var match = Regex.Match(local, pattern, RegexOptions.IgnoreCase);
            if (match.Success && match.Groups["token"].Value.Length >= 8)
                return match.Groups["token"].Value;
        }
    }

    var fallback = Regex.Match(
        payload,
        "token(?:\\\\\"|\")\\s*:\\s*(?:\\\\\"|\")(?<token>[A-Za-z0-9._-]{16,})",
        RegexOptions.IgnoreCase);

    return fallback.Success ? fallback.Groups["token"].Value : null;
}

static async Task<JsonProbe> ProbeJsonGetAsync(PacedTransport transport, string name, Uri uri)
{
    using var request = new HttpRequestMessage(HttpMethod.Get, uri);
    using var response = await transport.SendAsync(request);
    var body = await response.Content.ReadAsStringAsync();

    string apiStatus = "unparsed";
    int? folders = null;
    int? threads = null;
    int? messages = null;
    var validJson = false;

    try
    {
        using var document = JsonDocument.Parse(body);
        validJson = true;
        apiStatus = FindScalar(document.RootElement, "status") ?? "not_found";
        folders = FindArrayLength(document.RootElement, "folders");
        threads = FindArrayLength(document.RootElement, "threads");
        messages = FindArrayLength(document.RootElement, "messages");
    }
    catch (JsonException)
    {
        apiStatus = body.Contains("<html", StringComparison.OrdinalIgnoreCase) ? "html" : "non_json";
    }

    return new JsonProbe(name, (int)response.StatusCode, validJson, apiStatus, folders, threads, messages);
}

static void PrintJsonProbe(JsonProbe probe)
{
    Console.WriteLine($"{probe.Name}_http: {probe.HttpStatus}");
    Console.WriteLine($"{probe.Name}_json: {Bool(probe.ValidJson)}");
    Console.WriteLine($"{probe.Name}_api_status: {probe.ApiStatus}");
    Console.WriteLine($"{probe.Name}_folders: {probe.Folders?.ToString() ?? "not_found"}");
    Console.WriteLine($"{probe.Name}_threads: {probe.Threads?.ToString() ?? "not_found"}");
    Console.WriteLine($"{probe.Name}_messages: {probe.Messages?.ToString() ?? "not_found"}");
}

static string? FindScalar(JsonElement element, string propertyName)
{
    if (element.ValueKind == JsonValueKind.Object)
    {
        foreach (var property in element.EnumerateObject())
        {
            if (property.NameEquals(propertyName))
            {
                return property.Value.ValueKind switch
                {
                    JsonValueKind.String => property.Value.GetString(),
                    JsonValueKind.Number => property.Value.GetRawText(),
                    JsonValueKind.True => "true",
                    JsonValueKind.False => "false",
                    _ => property.Value.ValueKind.ToString()
                };
            }

            var nested = FindScalar(property.Value, propertyName);
            if (nested is not null)
                return nested;
        }
    }
    else if (element.ValueKind == JsonValueKind.Array)
    {
        foreach (var item in element.EnumerateArray())
        {
            var nested = FindScalar(item, propertyName);
            if (nested is not null)
                return nested;
        }
    }

    return null;
}

static int? FindArrayLength(JsonElement element, string propertyName)
{
    if (element.ValueKind == JsonValueKind.Object)
    {
        foreach (var property in element.EnumerateObject())
        {
            if (property.NameEquals(propertyName) && property.Value.ValueKind == JsonValueKind.Array)
                return property.Value.GetArrayLength();

            var nested = FindArrayLength(property.Value, propertyName);
            if (nested is not null)
                return nested;
        }
    }
    else if (element.ValueKind == JsonValueKind.Array)
    {
        foreach (var item in element.EnumerateArray())
        {
            var nested = FindArrayLength(item, propertyName);
            if (nested is not null)
                return nested;
        }
    }

    return null;
}

static Uri BuildUri(string baseUrl, IReadOnlyDictionary<string, string?> query)
{
    var builder = new UriBuilder(baseUrl)
    {
        Query = string.Join(
            "&",
            query
                .Where(pair => pair.Value is not null)
                .Select(pair => $"{Uri.EscapeDataString(pair.Key)}={Uri.EscapeDataString(pair.Value!)}"))
    };
    return builder.Uri;
}

static string Bool(bool value) => value ? "yes" : "no";

sealed class PacedTransport : IDisposable
{
    private readonly HttpClient _http;
    private readonly string _cookieHeader;
    private readonly TimeSpan _minimumSpacing;
    private DateTimeOffset _lastDispatch = DateTimeOffset.MinValue;

    public PacedTransport(string userAgent, string cookieHeader, TimeSpan minimumSpacing)
    {
        _cookieHeader = cookieHeader;
        _minimumSpacing = minimumSpacing;

        var handler = new HttpClientHandler
        {
            AllowAutoRedirect = false,
            UseCookies = false,
            AutomaticDecompression =
                DecompressionMethods.GZip |
                DecompressionMethods.Deflate |
                DecompressionMethods.Brotli
        };

        _http = new HttpClient(handler) { Timeout = TimeSpan.FromSeconds(30) };
        _http.DefaultRequestHeaders.UserAgent.ParseAdd(userAgent);
        _http.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("*/*"));
    }

    public async Task<HttpResponseMessage> SendAsync(HttpRequestMessage request)
    {
        var elapsed = DateTimeOffset.UtcNow - _lastDispatch;
        var wait = _minimumSpacing - elapsed;
        if (wait > TimeSpan.Zero)
            await Task.Delay(wait);

        request.Headers.TryAddWithoutValidation("Cookie", _cookieHeader);
        _lastDispatch = DateTimeOffset.UtcNow;

        try
        {
            return await _http.SendAsync(request, HttpCompletionOption.ResponseContentRead);
        }
        catch
        {
            Console.WriteLine("transport_error: request_failed_without_sensitive_details");
            throw;
        }
    }

    public void Dispose() => _http.Dispose();
}

readonly record struct InboxProbe(int StatusCode, bool UserShortMarker, string? Token);
readonly record struct JsonProbe(string Name, int HttpStatus, bool ValidJson, string ApiStatus, int? Folders, int? Threads, int? Messages);
