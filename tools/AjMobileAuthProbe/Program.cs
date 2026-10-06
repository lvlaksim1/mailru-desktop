using System.Net;
using System.Text.Json;

var login = Environment.GetEnvironmentVariable("MAILRU_TEST_LOGIN");
var password = Environment.GetEnvironmentVariable("MAILRU_TEST_PASSWORD");

if (string.IsNullOrWhiteSpace(login) || string.IsNullOrWhiteSpace(password))
{
    Console.WriteLine("credentials: missing");
    return 2;
}

var handler = new HttpClientHandler
{
    AllowAutoRedirect = false,
    UseCookies = false,
    AutomaticDecompression = DecompressionMethods.All
};

using var http = new HttpClient(handler) { Timeout = TimeSpan.FromSeconds(30) };
http.DefaultRequestHeaders.TryAddWithoutValidation(
    "User-Agent",
    "mobmail android 11.13.0.29089 ru.mail.mailapp");

var uri = new Uri(
    "https://aj-https.mail.ru/cgi-bin/auth?mp=android&udid=mailru_app");

using var request = new HttpRequestMessage(HttpMethod.Post, uri);
request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
{
    ["Password"] = password!,
    ["Login"] = login!,
    ["oauth2"] = "1",
    ["useragent"] = "android",
    ["mobile"] = "1",
    ["mob_json"] = "1",
    ["simple"] = "1"
});

using var response = await http.SendAsync(request);
var payload = await response.Content.ReadAsStringAsync();

Console.WriteLine($"http: {(int)response.StatusCode}");
Console.WriteLine($"location: {SafeLocation(response.Headers.Location?.ToString())}");
Console.WriteLine($"content_type: {response.Content.Headers.ContentType?.MediaType ?? "none"}");
Console.WriteLine($"payload_length: {payload.Length}");
Console.WriteLine($"contains_captcha: {YesNo(payload.Contains("captcha", StringComparison.OrdinalIgnoreCase))}");
Console.WriteLine($"contains_recaptcha: {YesNo(payload.Contains("recaptcha", StringComparison.OrdinalIgnoreCase))}");

try
{
    using var doc = JsonDocument.Parse(payload);
    var root = doc.RootElement;

    var keys = root.ValueKind == JsonValueKind.Object
        ? root.EnumerateObject().Select(p => p.Name).OrderBy(x => x).ToArray()
        : Array.Empty<string>();

    Console.WriteLine($"json: yes");
    Console.WriteLine($"top_keys: {string.Join(",", keys)}");
    Console.WriteLine($"has_access_token: {YesNo(FindString(root, "access_token") is not null)}");
    Console.WriteLine($"has_refresh_token: {YesNo(FindString(root, "refresh_token") is not null)}");

    var error = FindString(root, "error");
    Console.WriteLine($"error_present: {YesNo(!string.IsNullOrWhiteSpace(error))}");
    if (!string.IsNullOrWhiteSpace(error))
        Console.WriteLine($"error_code: {Sanitize(error!)}");
}
catch (JsonException)
{
    Console.WriteLine("json: no");
}

return 0;

static string? FindString(JsonElement element, string name)
{
    if (element.ValueKind == JsonValueKind.Object)
    {
        foreach (var p in element.EnumerateObject())
        {
            if (p.NameEquals(name) && p.Value.ValueKind == JsonValueKind.String)
                return p.Value.GetString();

            var nested = FindString(p.Value, name);
            if (nested is not null)
                return nested;
        }
    }
    else if (element.ValueKind == JsonValueKind.Array)
    {
        foreach (var item in element.EnumerateArray())
        {
            var nested = FindString(item, name);
            if (nested is not null)
                return nested;
        }
    }
    return null;
}

static string SafeLocation(string? location)
{
    if (string.IsNullOrWhiteSpace(location)) return "none";
    if (!Uri.TryCreate(location, UriKind.Absolute, out var uri)) return "relative";
    return $"{uri.Scheme}://{uri.Host}{uri.AbsolutePath}";
}

static string Sanitize(string value)
{
    var safe = new string(value.Where(ch =>
        char.IsLetterOrDigit(ch) || ch is '_' or '-' or '.').Take(80).ToArray());
    return string.IsNullOrWhiteSpace(safe) ? "present" : safe;
}

static string YesNo(bool value) => value ? "yes" : "no";
