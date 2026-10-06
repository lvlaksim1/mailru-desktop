using System.IO.Compression;
using System.Net;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Text.RegularExpressions;

const string UserAgent =
    "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X) AppleWebKit/604.1.34 " +
    "(KHTML, like Gecko) GSA/50.0.197507736 Mobile/17D50 Safari/604.1";

var login = Environment.GetEnvironmentVariable("MAILRU_TEST_LOGIN");
var password = Environment.GetEnvironmentVariable("MAILRU_TEST_PASSWORD");

if (string.IsNullOrWhiteSpace(login) || string.IsNullOrWhiteSpace(password))
{
    Console.WriteLine("credentials: missing");
    return 2;
}

Console.WriteLine($"probe_utc: {DateTimeOffset.UtcNow:O}");
Console.WriteLine("mode: web-api-only");
Console.WriteLine("mobile_access_token: disabled");
Console.WriteLine("request_spacing_seconds: 6");

var cookies = new FlatCookieJar();
using var transport = new PacedTransport(UserAgent, cookies, TimeSpan.FromSeconds(6));

var auth = await HackusLoginAsync(transport, login!, password!);
Console.WriteLine($"hackus_auth: {auth.Classification}");
Console.WriteLine($"hackus_auth_http: {auth.StatusCode}");
Console.WriteLine($"hackus_auth_location: {auth.LocationSummary}");
Console.WriteLine($"cookies_after_hackus_auth: {cookies.Names}");

if (auth.Classification is "recaptcha" or "captcha" or "two_factor" or "blocked" or "invalid_credentials" or "unknown")
{
    if (auth.Classification == "captcha_or_two_factor")
    {
        var verification = await ClassifyCopperAsync(transport);
        Console.WriteLine($"challenge_classification: {verification}");
        Console.WriteLine($"cookies_after_challenge_probe: {cookies.Names}");
    }

    Console.WriteLine("result: auth_challenge_not_completed_in_ci");
    return 10;
}

if (auth.Classification != "ok")
{
    Console.WriteLine("result: hackus_auth_not_ok");
    return 11;
}

var direct = await ProbeInboxAsync(transport, login!);
Console.WriteLine($"direct_inbox_http: {direct.StatusCode}");
Console.WriteLine($"direct_inbox_marker_user_short: {Bool(direct.UserShortMarker)}");
Console.WriteLine($"direct_web_token_found: {Bool(direct.Token is not null)}");
Console.WriteLine($"cookies_after_direct_inbox: {cookies.Names}");

string? webToken = direct.Token;
var tokenPath = webToken is null ? "none" : "hackus_cookies_direct";

if (webToken is null)
{
    var sdc = await RunSdcBridgeAsync(transport);
    Console.WriteLine($"sdc_http: {sdc.StatusCode}");
    Console.WriteLine($"sdc_location: {sdc.LocationSummary}");
    Console.WriteLine($"sdc_follow_http: {sdc.FollowStatusCode?.ToString() ?? "none"}");
    Console.WriteLine($"sdc_follow_location: {sdc.FollowLocationSummary}");
    Console.WriteLine($"cookies_after_sdc: {cookies.Names}");

    var afterSdc = await ProbeInboxAsync(transport, login!);
    Console.WriteLine($"post_sdc_inbox_http: {afterSdc.StatusCode}");
    Console.WriteLine($"post_sdc_inbox_marker_user_short: {Bool(afterSdc.UserShortMarker)}");
    Console.WriteLine($"post_sdc_web_token_found: {Bool(afterSdc.Token is not null)}");
    Console.WriteLine($"cookies_after_post_sdc_inbox: {cookies.Names}");

    webToken = afterSdc.Token;
    if (webToken is not null)
        tokenPath = "hackus_cookies_plus_sdc";
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
    ["last_modified"] = "1",
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

static async Task<AuthProbe> HackusLoginAsync(PacedTransport transport, string login, string password)
{
    using var request = new HttpRequestMessage(HttpMethod.Post, "https://aj-https.mail.ru/cgi-bin/auth");
    request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
    {
        ["Login"] = login,
        ["Password"] = password
    });

    using var response = await transport.SendAsync(request);
    var body = await response.Content.ReadAsStringAsync();
    var location = response.Headers.Location?.ToString() ?? string.Empty;
    var combined = location + "\n" + body;

    string classification;
    if (location.Contains("user/login?login", StringComparison.OrdinalIgnoreCase))
        classification = "captcha_or_two_factor";
    else if (combined.Contains("recaptcha", StringComparison.OrdinalIgnoreCase))
        classification = "recaptcha";
    else if (location.Contains("fail", StringComparison.OrdinalIgnoreCase) ||
             combined.Contains("invalid username or password", StringComparison.OrdinalIgnoreCase))
        classification = "invalid_credentials";
    else if (location.Contains("recovery", StringComparison.OrdinalIgnoreCase) ||
             location.Contains("ukey", StringComparison.OrdinalIgnoreCase) ||
             combined.Contains("blocked", StringComparison.OrdinalIgnoreCase))
        classification = "blocked";
    else if (location.Contains("inbox", StringComparison.OrdinalIgnoreCase))
        classification = "ok";
    else
        classification = "unknown";

    return new AuthProbe((int)response.StatusCode, classification, SafeLocation(location));
}

static async Task<string> ClassifyCopperAsync(PacedTransport transport)
{
    using var request = new HttpRequestMessage(HttpMethod.Get, "https://account.mail.ru/api/v1/user/copper");
    using var response = await transport.SendAsync(request);
    var body = await response.Content.ReadAsStringAsync();

    if (body.Contains("captcha", StringComparison.OrdinalIgnoreCase))
        return "captcha";
    return response.IsSuccessStatusCode ? "two_factor_or_other" : $"http_{(int)response.StatusCode}";
}

static async Task<InboxProbe> ProbeInboxAsync(PacedTransport transport, string login)
{
    using var request = new HttpRequestMessage(HttpMethod.Get, "https://e.mail.ru/inbox/");
    using var response = await transport.SendAsync(request);
    var body = await response.Content.ReadAsStringAsync();

    var markerIndex = body.IndexOf("/api/v1/user/short", StringComparison.OrdinalIgnoreCase);
    var token = ExtractWebToken(body, markerIndex);

    return new InboxProbe(
        (int)response.StatusCode,
        markerIndex >= 0,
        token);
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
    using var request = new HttpRequestMessage(HttpMethod.Get, "https://auth.mail.ru/sdc");
    using var response = await transport.SendAsync(request);
    var location = response.Headers.Location?.ToString() ?? string.Empty;

    int? followStatus = null;
    string followLocation = "none";

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

    return new JsonProbe(
        name,
        (int)response.StatusCode,
        validJson,
        apiStatus,
        folders,
        threads,
        messages);
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
                .Select(pair =>
                    $"{Uri.EscapeDataString(pair.Key)}={Uri.EscapeDataString(pair.Value!)}"))
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
    private readonly Dictionary<string, string> _cookies =
        new(StringComparer.OrdinalIgnoreCase);

    public string Names =>
        _cookies.Count == 0
            ? "none"
            : string.Join(",", _cookies.Keys.OrderBy(x => x, StringComparer.OrdinalIgnoreCase));

    public void Apply(HttpRequestMessage request)
    {
        if (_cookies.Count == 0)
            return;

        var header = string.Join("; ", _cookies.Select(pair => $"{pair.Key}={pair.Value}"));
        request.Headers.TryAddWithoutValidation("Cookie", header);
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

        _http = new HttpClient(handler)
        {
            Timeout = TimeSpan.FromSeconds(30)
        };
        _http.DefaultRequestHeaders.UserAgent.ParseAdd(userAgent);
        _http.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("*/*"));
    }

    public async Task<HttpResponseMessage> SendAsync(HttpRequestMessage request)
    {
        var elapsed = DateTimeOffset.UtcNow - _lastDispatch;
        var wait = _minimumSpacing - elapsed;
        if (wait > TimeSpan.Zero)
            await Task.Delay(wait);

        _cookies.Apply(request);
        _lastDispatch = DateTimeOffset.UtcNow;

        HttpResponseMessage response;
        try
        {
            response = await _http.SendAsync(
                request,
                HttpCompletionOption.ResponseContentRead);
        }
        catch
        {
            Console.WriteLine("transport_error: request_failed_without_sensitive_details");
            throw;
        }

        _cookies.Capture(response);
        return response;
    }

    public void Dispose() => _http.Dispose();
}

readonly record struct AuthProbe(
    int StatusCode,
    string Classification,
    string LocationSummary);

readonly record struct InboxProbe(
    int StatusCode,
    bool UserShortMarker,
    string? Token);

readonly record struct SdcProbe(
    int StatusCode,
    string LocationSummary,
    int? FollowStatusCode,
    string FollowLocationSummary);

readonly record struct JsonProbe(
    string Name,
    int HttpStatus,
    bool ValidJson,
    string ApiStatus,
    int? Folders,
    int? Threads,
    int? Messages);
