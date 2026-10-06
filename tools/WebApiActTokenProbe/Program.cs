using System.IO.Compression;
using System.Net;
using System.Net.Http.Headers;
using System.Text.Json;
using System.Text.RegularExpressions;

const string UserAgent =
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
    "(KHTML, like Gecko) Chrome/146.0.0.0 YaBrowser/26.4.0.0 Safari/537.36";

var login = Environment.GetEnvironmentVariable("MAILRU_TEST_LOGIN");
var password = Environment.GetEnvironmentVariable("MAILRU_TEST_PASSWORD");

if (string.IsNullOrWhiteSpace(login) || string.IsNullOrWhiteSpace(password))
{
    Console.WriteLine("credentials: missing");
    return 2;
}

var at = login!.IndexOf('@');
if (at <= 0 || at == login.Length - 1)
{
    Console.WriteLine("login_format: invalid");
    return 3;
}

var local = login[..at];
var domain = login[(at + 1)..];

Console.WriteLine($"probe_utc: {DateTimeOffset.UtcNow:O}");
Console.WriteLine("mode: act-token-web-auth-only");
Console.WriteLine("mobile_access_token: disabled");
Console.WriteLine("touch_token: disabled");
Console.WriteLine("request_spacing_seconds: 6");

var cookies = new FlatCookieJar();
using var transport = new PacedTransport(UserAgent, cookies, TimeSpan.FromSeconds(6));

var initUri = BuildUri(
    "https://account.mail.ru/login/",
    new Dictionary<string, string?>
    {
        ["mode"] = "simple",
        ["v"] = "2.0.13",
        ["type"] = "login",
        ["allow_external"] = "1",
        ["success_redirect"] = "https://e.mail.ru/messages/inbox?back=1",
        ["opener"] = "mail.login",
        ["modal"] = "1",
        ["parent_url"] = "https://e.mail.ru/login"
    });

string? actToken;
using (var initRequest = new HttpRequestMessage(HttpMethod.Get, initUri))
using (var initResponse = await transport.SendAsync(initRequest))
{
    var body = await initResponse.Content.ReadAsStringAsync();
    actToken = ExtractActToken(body);

    Console.WriteLine($"init_http: {(int)initResponse.StatusCode}");
    Console.WriteLine($"init_location: {SafeLocation(initResponse.Headers.Location?.ToString() ?? string.Empty)}");
    Console.WriteLine($"act_token_found: {Bool(actToken is not null)}");
    Console.WriteLine($"cookies_after_init: {cookies.Names}");
}

if (actToken is null)
{
    Console.WriteLine("result: act_token_not_found");
    return 10;
}

string? responseToken = null;
using (var authRequest = new HttpRequestMessage(HttpMethod.Post, "https://auth.mail.ru/cgi-bin/auth"))
{
    authRequest.Headers.TryAddWithoutValidation("Origin", "https://account.mail.ru");
    authRequest.Headers.TryAddWithoutValidation("Upgrade-Insecure-Requests", "1");

    authRequest.Content = new FormUrlEncodedContent(new Dictionary<string, string>
    {
        ["Login"] = local,
        ["Domain"] = domain,
        ["Password"] = password,
        ["saveauth"] = "1",
        ["new_auth_form"] = "1",
        ["FromAccount"] = "opener=mail.login&allow_external=1",
        ["act_token"] = actToken,
        ["page"] = "https://e.mail.ru/messages/inbox?back=1&back=1&from=mail.login",
        ["back"] = "1"
    });

    using var authResponse = await transport.SendAsync(authRequest);
    var body = await authResponse.Content.ReadAsStringAsync();
    responseToken = ExtractPatronToken(body);

    Console.WriteLine($"act_auth_http: {(int)authResponse.StatusCode}");
    Console.WriteLine($"act_auth_location: {SafeLocation(authResponse.Headers.Location?.ToString() ?? string.Empty)}");
    Console.WriteLine($"patron_token_found: {Bool(responseToken is not null)}");
    Console.WriteLine($"cookies_after_act_auth: {cookies.Names}");
    Console.WriteLine($"has_Mpop_after_act_auth: {Bool(cookies.Contains("Mpop"))}");
    Console.WriteLine($"has_ssdc_after_act_auth: {Bool(cookies.Contains("ssdc"))}");
    Console.WriteLine($"has_sdcs_after_act_auth: {Bool(cookies.Contains("sdcs"))}");
}

var directInbox = await ProbeInboxAsync(transport);
Console.WriteLine($"direct_inbox_http: {directInbox.StatusCode}");
Console.WriteLine($"direct_inbox_marker_user_short: {Bool(directInbox.UserShortMarker)}");
Console.WriteLine($"direct_inbox_web_token_found: {Bool(directInbox.Token is not null)}");
Console.WriteLine($"cookies_after_direct_inbox: {cookies.Names}");

var webToken = directInbox.Token ?? responseToken;
var tokenPath = directInbox.Token is not null
    ? "act_auth_direct_inbox"
    : responseToken is not null
        ? "patron_updateToken"
        : "none";

if (webToken is null)
{
    var sdc = await RunSdcBridgeAsync(transport);
    Console.WriteLine($"sdc_http: {sdc.StatusCode}");
    Console.WriteLine($"sdc_location: {sdc.LocationSummary}");
    Console.WriteLine($"sdc_follow_http: {sdc.FollowStatusCode?.ToString() ?? "none"}");
    Console.WriteLine($"sdc_follow_location: {sdc.FollowLocationSummary}");
    Console.WriteLine($"cookies_after_sdc: {cookies.Names}");
    Console.WriteLine($"has_Mpop_after_sdc: {Bool(cookies.Contains("Mpop"))}");
    Console.WriteLine($"has_ssdc_after_sdc: {Bool(cookies.Contains("ssdc"))}");
    Console.WriteLine($"has_sdcs_after_sdc: {Bool(cookies.Contains("sdcs"))}");

    var afterSdc = await ProbeInboxAsync(transport);
    Console.WriteLine($"post_sdc_inbox_http: {afterSdc.StatusCode}");
    Console.WriteLine($"post_sdc_marker_user_short: {Bool(afterSdc.UserShortMarker)}");
    Console.WriteLine($"post_sdc_web_token_found: {Bool(afterSdc.Token is not null)}");
    Console.WriteLine($"cookies_after_post_sdc_inbox: {cookies.Names}");

    if (afterSdc.Token is not null)
    {
        webToken = afterSdc.Token;
        tokenPath = "act_auth_plus_sdc";
    }
}

if (webToken is null)
{
    Console.WriteLine("result: web_token_not_found");
    return 20;
}

Console.WriteLine($"web_token_path: {tokenPath}");

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

Console.WriteLine("result: web_token_obtained_and_read_api_probed");
return 0;

static string? ExtractActToken(string payload)
{
    var patterns = new[]
    {
        "<input[^>]+name=[\"']act_token[\"'][^>]+value=[\"'](?<token>[^\"']+)[\"']",
        "<input[^>]+value=[\"'](?<token>[^\"']+)[\"'][^>]+name=[\"']act_token[\"']"
    };

    foreach (var pattern in patterns)
    {
        var match = Regex.Match(payload, pattern, RegexOptions.IgnoreCase);
        if (match.Success && match.Groups["token"].Value.Length >= 4)
            return WebUtility.HtmlDecode(match.Groups["token"].Value);
    }

    return null;
}

static string? ExtractPatronToken(string payload)
{
    var match = Regex.Match(
        payload,
        "patron\\.updateToken\\([\"'](?<token>[^\"']+)[\"']\\)",
        RegexOptions.IgnoreCase);

    return match.Success && match.Groups["token"].Value.Length >= 8
        ? match.Groups["token"].Value
        : null;
}

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
        var local = payload.Substring(markerIndex, Math.Min(16000, payload.Length - markerIndex));
        var match = Regex.Match(
            local,
            "token(?:\\\\\"|\")\\s*:\\s*(?:\\\\\"|\")(?<token>[^\"\\\\]+)",
            RegexOptions.IgnoreCase);

        if (match.Success && match.Groups["token"].Value.Length >= 8)
            return match.Groups["token"].Value;
    }

    var fallback = Regex.Match(
        payload,
        "token(?:\\\\\"|\")\\s*:\\s*(?:\\\\\"|\")(?<token>[A-Za-z0-9._-]{16,})",
        RegexOptions.IgnoreCase);

    return fallback.Success ? fallback.Groups["token"].Value : null;
}

static async Task<SdcProbe> RunSdcBridgeAsync(PacedTransport transport)
{
    const string sdcUrl =
        "https://auth.mail.ru/sdc?from=https%3A%2F%2Fe.mail.ru%2Fmessages%2Finbox%3Fback%3D1";

    using var request = new HttpRequestMessage(HttpMethod.Get, sdcUrl);
    using var response = await transport.SendAsync(request);
    _ = await response.Content.ReadAsByteArrayAsync();
    var location = response.Headers.Location?.ToString() ?? string.Empty;

    int? followStatus = null;
    var followLocation = "none";

    if (!string.IsNullOrWhiteSpace(location))
    {
        Uri? followUri = null;
        if (Uri.TryCreate(location, UriKind.Absolute, out var absolute))
            followUri = absolute;
        else if (Uri.TryCreate(new Uri("https://auth.mail.ru/"), location, out var relative))
            followUri = relative;

        if (followUri is not null &&
            followUri.Scheme == Uri.UriSchemeHttps &&
            followUri.Host.EndsWith("mail.ru", StringComparison.OrdinalIgnoreCase))
        {
            using var followRequest = new HttpRequestMessage(HttpMethod.Get, followUri);
            using var followResponse = await transport.SendAsync(followRequest);
            followStatus = (int)followResponse.StatusCode;
            followLocation = SafeLocation(followResponse.Headers.Location?.ToString() ?? string.Empty);
            _ = await followResponse.Content.ReadAsByteArrayAsync();
        }
    }

    return new SdcProbe(
        (int)response.StatusCode,
        SafeLocation(location),
        followStatus,
        followLocation);
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
        apiStatus = body.Contains("<html", StringComparison.OrdinalIgnoreCase)
            ? "html"
            : "non_json";
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

static string SafeLocation(string location)
{
    if (string.IsNullOrWhiteSpace(location))
        return "none";

    if (!Uri.TryCreate(location, UriKind.Absolute, out var uri))
        return "relative";

    return $"{uri.Scheme}://{uri.Host}{uri.AbsolutePath}";
}

static string Bool(bool value) => value ? "yes" : "no";

sealed class FlatCookieJar
{
    private readonly Dictionary<string, string> _cookies = new(StringComparer.OrdinalIgnoreCase);

    public bool Contains(string name) => _cookies.ContainsKey(name);

    public string Names =>
        _cookies.Count == 0
            ? "none"
            : string.Join(",", _cookies.Keys.OrderBy(x => x, StringComparer.OrdinalIgnoreCase));

    public void Apply(HttpRequestMessage request)
    {
        if (_cookies.Count == 0)
            return;

        request.Headers.TryAddWithoutValidation(
            "Cookie",
            string.Join("; ", _cookies.Select(pair => $"{pair.Key}={pair.Value}")));
    }

    public void Capture(HttpResponseMessage response)
    {
        if (!response.Headers.TryGetValues("Set-Cookie", out var values))
            return;

        foreach (var raw in values)
        {
            var first = raw.Split(';', 2)[0];
            var equals = first.IndexOf('=');
            if (equals <= 0)
                continue;

            var name = first[..equals].Trim();
            var value = first[(equals + 1)..].Trim();

            if (name.Length == 0)
                continue;

            if (value.Length == 0 || value.Equals("deleted", StringComparison.OrdinalIgnoreCase))
                _cookies.Remove(name);
            else
                _cookies[name] = value;
        }
    }
}

sealed class PacedTransport : IDisposable
{
    private readonly HttpClient _http;
    private readonly FlatCookieJar _cookies;
    private readonly TimeSpan _minimumSpacing;
    private DateTimeOffset _lastDispatch = DateTimeOffset.MinValue;

    public PacedTransport(string userAgent, FlatCookieJar cookies, TimeSpan minimumSpacing)
    {
        _cookies = cookies;
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

        _cookies.Apply(request);
        _lastDispatch = DateTimeOffset.UtcNow;

        try
        {
            var response = await _http.SendAsync(request, HttpCompletionOption.ResponseContentRead);
            _cookies.Capture(response);
            return response;
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
readonly record struct SdcProbe(int StatusCode, string LocationSummary, int? FollowStatusCode, string FollowLocationSummary);
readonly record struct JsonProbe(string Name, int HttpStatus, bool ValidJson, string ApiStatus, int? Folders, int? Threads, int? Messages);
