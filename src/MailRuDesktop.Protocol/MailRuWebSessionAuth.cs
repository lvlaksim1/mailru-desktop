using System.Net;
using System.Text.Json;
using System.Text.RegularExpressions;

namespace MailRuDesktop.Protocol;

internal sealed record MailRuWebSessionResult(
    bool Success,
    MailRuAuthState State,
    string? WebToken,
    string? SearchToken,
    string? WebCookieHeader,
    string? TouchCookieHeader,
    string? ErrorCode,
    MailRuAuthChallenge? Challenge,
    string? DiagnosticReason)
{
    public static MailRuWebSessionResult Failed(
        MailRuAuthState state,
        string code,
        string? diagnostic = null,
        MailRuAuthChallenge? challenge = null) =>
        new(false, state, null, null, null, null, code, challenge, diagnostic);
}

internal static class MailRuWebSessionAuthenticator
{
    private static readonly Uri AjAuthUri = new("https://aj-https.mail.ru/cgi-bin/auth");
    private static readonly Uri AccountCopperUri = new("https://account.mail.ru/api/v1/user/copper");
    private static readonly Uri InboxUri = new("https://e.mail.ru/inbox/");
    private static readonly Uri TouchTokensUri = new("https://touch.mail.ru/api/v1/tokens");

    public static async Task<MailRuWebSessionResult> AuthenticateAsync(
        string login,
        string password,
        TimeSpan timeout,
        CancellationToken cancellationToken)
    {
        // Deliberately start every login attempt with a brand-new cookie jar.
        // A failed challenge must never contaminate a later login attempt.
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
        authRequest.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);
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
        catch (Exception ex) when (ex is HttpRequestException or TaskCanceledException)
        {
            return MailRuWebSessionResult.Failed(
                MailRuAuthState.NetworkError,
                "web_auth_http_error",
                ex.GetType().Name);
        }

        string location;
        string payload;
        int statusCode;

        using (authResponse)
        {
            statusCode = (int)authResponse.StatusCode;
            location = authResponse.Headers.Location?.ToString() ?? string.Empty;
            payload = await authResponse.Content
                .ReadAsStringAsync(cancellationToken)
                .ConfigureAwait(false);
        }

        var combined = location + "\n" + payload;

        if (combined.Contains("user is blocked", StringComparison.OrdinalIgnoreCase) ||
            combined.Contains("blocked", StringComparison.OrdinalIgnoreCase) ||
            location.Contains("ukey", StringComparison.OrdinalIgnoreCase))
        {
            return MailRuWebSessionResult.Failed(
                MailRuAuthState.Blocked,
                "account_blocked",
                $"blocked; http={statusCode}; redirect={SanitizeLocation(location)}");
        }

        if (location.Contains("recovery", StringComparison.OrdinalIgnoreCase))
        {
            return MailRuWebSessionResult.Failed(
                MailRuAuthState.RecoveryRequired,
                "account_recovery_required",
                $"recovery; http={statusCode}; redirect={SanitizeLocation(location)}");
        }

        if (combined.Contains("recaptcha", StringComparison.OrdinalIgnoreCase))
        {
            var challengeUrl = NormalizeChallengeUrl(location, "https://account.mail.ru/");
            var diagnostic =
                $"recaptcha; http={statusCode}; redirect={SanitizeLocation(challengeUrl)}";
            var challenge = new MailRuAuthChallenge(
                MailRuChallengeKind.ReCaptcha,
                challengeUrl,
                BuildSeedCookieHeader(cookies, challengeUrl),
                diagnostic);

            return MailRuWebSessionResult.Failed(
                MailRuAuthState.ReCaptcha,
                "recaptcha_required",
                diagnostic,
                challenge);
        }

        if (location.Contains("user/login?login", StringComparison.OrdinalIgnoreCase))
        {
            var kind = await ClassifyChallengeAsync(http, cancellationToken).ConfigureAwait(false);
            var state = kind == MailRuChallengeKind.Captcha
                ? MailRuAuthState.Captcha
                : MailRuAuthState.TwoFactor;
            var code = kind == MailRuChallengeKind.Captcha
                ? "captcha_required"
                : "two_factor_required";
            var challengeUrl = NormalizeChallengeUrl(location, "https://account.mail.ru/");
            var diagnostic =
                $"{kind}; http={statusCode}; redirect={SanitizeLocation(challengeUrl)}";
            var challenge = new MailRuAuthChallenge(
                kind,
                challengeUrl,
                BuildSeedCookieHeader(cookies, challengeUrl),
                diagnostic);

            return MailRuWebSessionResult.Failed(
                state,
                code,
                diagnostic,
                challenge);
        }

        // Mail.ru auth pages can contain a generic "invalid password" string even
        // while the real redirect is a CAPTCHA/challenge. Only classify invalid
        // credentials after all challenge/blocked/recovery states were excluded.
        if (combined.Contains("invalid username or password", StringComparison.OrdinalIgnoreCase) ||
            location.Contains("fail", StringComparison.OrdinalIgnoreCase))
        {
            return MailRuWebSessionResult.Failed(
                MailRuAuthState.InvalidCredentials,
                "invalid_credentials",
                $"credentials_rejected; http={statusCode}; redirect={SanitizeLocation(location)}");
        }

        // Mail.ru variants do not always use exactly the same successful redirect.
        // The actual token probes are therefore authoritative after the known
        // challenge/failure states have been removed.
        var tokenResult = await DeriveTokensAsync(
            http,
            login,
            cookies.GetCookieHeader(InboxUri),
            cookies.GetCookieHeader(TouchTokensUri),
            cancellationToken).ConfigureAwait(false);

        if (tokenResult.Success)
            return tokenResult;

        if (location.Contains("inbox", StringComparison.OrdinalIgnoreCase))
        {
            return MailRuWebSessionResult.Failed(
                MailRuAuthState.ProtocolError,
                "web_session_token_missing",
                $"inbox_session_without_api_token; http={statusCode}; redirect={SanitizeLocation(location)}");
        }

        // Preserve an actionable, sanitized reason for reverse-engineering.
        return MailRuWebSessionResult.Failed(
            MailRuAuthState.Unknown,
            "unknown_auth_result",
            $"unknown_auth_result; http={statusCode}; redirect={SanitizeLocation(location)}; body={SanitizeBodyHint(payload)}");
    }

    public static async Task<MailRuWebSessionResult> CompleteFromCookieHeadersAsync(
        string login,
        string webCookieHeader,
        string touchCookieHeader,
        TimeSpan timeout,
        CancellationToken cancellationToken)
    {
        using var handler = new HttpClientHandler
        {
            AllowAutoRedirect = true,
            UseCookies = false,
            AutomaticDecompression = DecompressionMethods.All
        };
        using var http = new HttpClient(handler)
        {
            Timeout = timeout
        };

        return await DeriveTokensAsync(
            http,
            login,
            webCookieHeader,
            touchCookieHeader,
            cancellationToken).ConfigureAwait(false);
    }

    private static async Task<MailRuWebSessionResult> DeriveTokensAsync(
        HttpClient http,
        string login,
        string webCookieHeader,
        string touchCookieHeader,
        CancellationToken cancellationToken)
    {
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
            return MailRuWebSessionResult.Failed(
                MailRuAuthState.ProtocolError,
                "web_session_token_missing",
                "cookie_session_present_but_no_web_or_touch_token");
        }

        return new MailRuWebSessionResult(
            true,
            MailRuAuthState.Success,
            webToken,
            searchToken,
            webCookieHeader,
            touchCookieHeader,
            null,
            null,
            null);
    }

    private static async Task<MailRuChallengeKind> ClassifyChallengeAsync(
        HttpClient http,
        CancellationToken cancellationToken)
    {
        try
        {
            using var request = new HttpRequestMessage(HttpMethod.Get, AccountCopperUri);
            request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);
            using var response = await http.SendAsync(request, cancellationToken).ConfigureAwait(false);
            var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

            return payload.Contains("captcha", StringComparison.OrdinalIgnoreCase)
                ? MailRuChallengeKind.Captcha
                : MailRuChallengeKind.TwoFactor;
        }
        catch
        {
            // Hackus falls back to TwoFactor when the copper probe cannot
            // positively identify the legacy image CAPTCHA.
            return MailRuChallengeKind.TwoFactor;
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
            request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);
            if (!string.IsNullOrWhiteSpace(cookieHeader))
                request.Headers.TryAddWithoutValidation("Cookie", cookieHeader);

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
            request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);
            if (!string.IsNullOrWhiteSpace(cookieHeader))
                request.Headers.TryAddWithoutValidation("Cookie", cookieHeader);

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

    private static string BuildSeedCookieHeader(CookieContainer cookies, string challengeUrl)
    {
        var parts = new HashSet<string>(StringComparer.Ordinal);

        foreach (var candidate in new[]
                 {
                     challengeUrl,
                     "https://account.mail.ru/",
                     "https://mail.ru/",
                     "https://e.mail.ru/",
                     "https://touch.mail.ru/",
                     "https://aj-https.mail.ru/"
                 })
        {
            if (!Uri.TryCreate(candidate, UriKind.Absolute, out var uri))
                continue;

            var header = cookies.GetCookieHeader(uri);
            foreach (var part in header.Split(';', StringSplitOptions.RemoveEmptyEntries | StringSplitOptions.TrimEntries))
            {
                if (!string.IsNullOrWhiteSpace(part))
                    parts.Add(part);
            }
        }

        return string.Join("; ", parts);
    }

    private static string NormalizeChallengeUrl(string location, string fallback)
    {
        if (Uri.TryCreate(location, UriKind.Absolute, out var absolute))
            return absolute.ToString();

        if (Uri.TryCreate(new Uri(fallback), location, out var combined))
            return combined.ToString();

        return fallback;
    }

    private static string SanitizeLocation(string location)
    {
        if (!Uri.TryCreate(location, UriKind.Absolute, out var uri))
            return string.IsNullOrWhiteSpace(location) ? "(none)" : "(relative)";

        return $"{uri.Scheme}://{uri.Host}{uri.AbsolutePath}";
    }

    private static string SanitizeBodyHint(string payload)
    {
        if (string.IsNullOrWhiteSpace(payload))
            return "(empty)";

        var lowered = payload.ToLowerInvariant();
        foreach (var marker in new[]
                 {
                     "recaptcha", "captcha", "twofactor", "two_factor",
                     "recovery", "blocked", "invalid", "error", "inbox"
                 })
        {
            if (lowered.Contains(marker, StringComparison.Ordinal))
                return marker;
        }

        return $"len:{payload.Length}";
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
