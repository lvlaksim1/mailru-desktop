using System.Net;
using System.Text.Json;
using System.Text.RegularExpressions;

namespace MailRuDesktop.Protocol;

internal sealed record MailRuWebSessionResult(
    bool Success,
    string? WebToken,
    string? SearchToken,
    string? WebCookieHeader,
    string? TouchCookieHeader,
    string? ErrorCode)
{
    public static MailRuWebSessionResult Failed(string code) =>
        new(false, null, null, null, null, code);
}

internal static class MailRuWebSessionAuthenticator
{
    private static readonly Uri AjAuthUri = new("https://aj-https.mail.ru/cgi-bin/auth");
    private static readonly Uri AccountCopperUri = new("https://account.mail.ru/api/v1/user/copper");
    private static readonly Uri InboxUri = new("https://e.mail.ru/inbox/");
    private static readonly Uri TouchTokensUri = new("https://touch.mail.ru/api/v1/tokens");

    private const string BrowserUserAgent =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/150.0.0.0 Safari/537.36";

    public static async Task<MailRuWebSessionResult> AuthenticateAsync(
        string login,
        string password,
        TimeSpan timeout,
        CancellationToken cancellationToken)
    {
        var cookies = new CookieContainer();
        using var handler = new HttpClientHandler
        {
            AllowAutoRedirect = false,
            UseCookies = true,
            CookieContainer = cookies,
            AutomaticDecompression = DecompressionMethods.All
        };
        using var http = new HttpClient(handler)
        {
            Timeout = timeout
        };

        using var authRequest = new HttpRequestMessage(HttpMethod.Post, AjAuthUri);
        authRequest.Headers.TryAddWithoutValidation("User-Agent", BrowserUserAgent);
        authRequest.Content = new FormUrlEncodedContent(new Dictionary<string, string>
        {
            ["Login"] = login,
            ["Password"] = password
        });

        HttpResponseMessage authResponse;
        try
        {
            authResponse = await http.SendAsync(
                authRequest,
                HttpCompletionOption.ResponseHeadersRead,
                cancellationToken).ConfigureAwait(false);
        }
        catch (HttpRequestException)
        {
            return MailRuWebSessionResult.Failed("web_auth_http_error");
        }

        using (authResponse)
        {
            var location = authResponse.Headers.Location?.ToString() ?? string.Empty;
            var payload = await authResponse.Content
                .ReadAsStringAsync(cancellationToken)
                .ConfigureAwait(false);

            var classified = ClassifyAuthResponse(location, payload);
            if (classified is not null)
            {
                if (classified == "two_factor_or_captcha")
                {
                    var challenge = await ClassifyChallengeAsync(http, cancellationToken)
                        .ConfigureAwait(false);
                    return MailRuWebSessionResult.Failed(challenge);
                }

                if (classified != "session_candidate")
                    return MailRuWebSessionResult.Failed(classified);
            }
        }

        var webCookieHeader = cookies.GetCookieHeader(InboxUri);
        var touchCookieHeader = cookies.GetCookieHeader(TouchTokensUri);

        var webToken = await TryGetWebTokenAsync(
            http,
            webCookieHeader,
            cancellationToken).ConfigureAwait(false);

        var searchToken = await TryGetSearchTokenAsync(
            http,
            login,
            touchCookieHeader,
            cancellationToken).ConfigureAwait(false);

        if (string.IsNullOrWhiteSpace(webToken) &&
            string.IsNullOrWhiteSpace(searchToken))
        {
            return MailRuWebSessionResult.Failed("web_session_token_missing");
        }

        return new MailRuWebSessionResult(
            true,
            webToken,
            searchToken,
            webCookieHeader,
            touchCookieHeader,
            null);
    }

    private static string? ClassifyAuthResponse(string location, string payload)
    {
        var combined = location + "\n" + payload;

        if (combined.Contains("recaptcha", StringComparison.OrdinalIgnoreCase))
            return "recaptcha_required";

        if (location.Contains("user/login?login", StringComparison.OrdinalIgnoreCase))
            return "two_factor_or_captcha";

        if (combined.Contains("recovery", StringComparison.OrdinalIgnoreCase) ||
            combined.Contains("ukey", StringComparison.OrdinalIgnoreCase))
        {
            return "account_recovery_required";
        }

        if (combined.Contains("fail", StringComparison.OrdinalIgnoreCase) ||
            combined.Contains("invalid username or password", StringComparison.OrdinalIgnoreCase))
        {
            return "invalid_credentials";
        }

        if (combined.Contains("blocked", StringComparison.OrdinalIgnoreCase))
            return "account_blocked";

        if (location.Contains("inbox", StringComparison.OrdinalIgnoreCase))
            return "session_candidate";

        // Some Mail.ru variants return a usable cookie session without the old inbox redirect.
        // The token probes below are authoritative for that case.
        return null;
    }

    private static async Task<string> ClassifyChallengeAsync(
        HttpClient http,
        CancellationToken cancellationToken)
    {
        try
        {
            using var request = new HttpRequestMessage(HttpMethod.Get, AccountCopperUri);
            request.Headers.TryAddWithoutValidation("User-Agent", BrowserUserAgent);
            using var response = await http.SendAsync(request, cancellationToken).ConfigureAwait(false);
            var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

            return payload.Contains("captcha", StringComparison.OrdinalIgnoreCase)
                ? "captcha_required"
                : "two_factor_required";
        }
        catch
        {
            return "two_factor_required";
        }
    }

    private static async Task<string?> TryGetSearchTokenAsync(
        HttpClient http,
        string login,
        string cookieHeader,
        CancellationToken cancellationToken)
    {
        try
        {
            var builder = new UriBuilder(TouchTokensUri)
            {
                Query = "email=" + Uri.EscapeDataString(login)
            };

            using var request = new HttpRequestMessage(HttpMethod.Get, builder.Uri);
            request.Headers.TryAddWithoutValidation("User-Agent", BrowserUserAgent);

            using var response = await http.SendAsync(request, cancellationToken).ConfigureAwait(false);
            var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

            using var document = JsonDocument.Parse(payload);
            return TryFindString(document.RootElement, "token", out var token)
                ? token
                : null;
        }
        catch
        {
            return null;
        }
    }

    private static async Task<string?> TryGetWebTokenAsync(
        HttpClient http,
        string cookieHeader,
        CancellationToken cancellationToken)
    {
        try
        {
            using var request = new HttpRequestMessage(HttpMethod.Get, InboxUri);
            request.Headers.TryAddWithoutValidation("User-Agent", BrowserUserAgent);

            using var response = await http.SendAsync(request, cancellationToken).ConfigureAwait(false);
            var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

            var markerIndex = payload.IndexOf(
                "/api/v1/user/short",
                StringComparison.OrdinalIgnoreCase);

            if (markerIndex >= 0)
            {
                var local = payload.Substring(markerIndex, Math.Min(12000, payload.Length - markerIndex));
                var match = Regex.Match(
                    local,
                    "token(?:\\\\\"|\")\\s*:\\s*(?:\\\\\"|\")(?<token>[^\"\\\\]+)",
                    RegexOptions.IgnoreCase);

                if (match.Success)
                    return match.Groups["token"].Value;
            }

            var fallback = Regex.Match(
                payload,
                "token(?:\\\\\"|\")\\s*:\\s*(?:\\\\\"|\")(?<token>[A-Za-z0-9._-]{16,})",
                RegexOptions.IgnoreCase);

            return fallback.Success ? fallback.Groups["token"].Value : null;
        }
        catch
        {
            return null;
        }
    }

    private static bool TryFindString(JsonElement element, string name, out string? value)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            foreach (var property in element.EnumerateObject())
            {
                if (property.NameEquals(name) &&
                    property.Value.ValueKind == JsonValueKind.String)
                {
                    value = property.Value.GetString();
                    return true;
                }

                if (TryFindString(property.Value, name, out value))
                    return true;
            }
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
            {
                if (TryFindString(item, name, out value))
                    return true;
            }
        }

        value = null;
        return false;
    }
}
