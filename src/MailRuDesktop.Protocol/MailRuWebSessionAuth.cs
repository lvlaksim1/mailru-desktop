using System.Collections.Concurrent;
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
    string? DiagnosticReason,
    string? PendingSessionId = null)
{
    public static MailRuWebSessionResult Failed(
        MailRuAuthState state,
        string code,
        string? diagnostic = null,
        MailRuAuthChallenge? challenge = null,
        string? pendingSessionId = null) =>
        new(false, state, null, null, null, null, code, challenge, diagnostic, pendingSessionId);
}

internal static class MailRuWebSessionAuthenticator
{
    private static readonly Uri AjAuthUri = new("https://aj-https.mail.ru/cgi-bin/auth");
    private static readonly Uri AccountCopperUri = new("https://account.mail.ru/api/v1/user/copper");
    private static readonly Uri CaptchaImageUri = new("https://c.mail.ru/c/6");
    private static readonly Uri InboxUri = new("https://e.mail.ru/inbox/");
    private static readonly Uri TouchTokensUri = new("https://touch.mail.ru/api/v1/tokens");

    private static readonly ConcurrentDictionary<string, PendingSession> Pending = new();
    private static readonly TimeSpan PendingLifetime = TimeSpan.FromMinutes(15);

    public static async Task<MailRuWebSessionResult> AuthenticateAsync(
        string login,
        string password,
        TimeSpan timeout,
        CancellationToken cancellationToken)
    {
        CleanupExpired();

        var sessionId = Guid.NewGuid().ToString("N");
        var session = new PendingSession(sessionId, login, password, timeout);
        Pending[sessionId] = session;

        var initial = await CreateSessionAsync(session, null, cancellationToken).ConfigureAwait(false);

        if (initial.Success)
        {
            var result = await DeriveTokensFromSameSessionAsync(session, cancellationToken).ConfigureAwait(false);
            ReleaseSession(sessionId);
            return result;
        }

        if (initial.Challenge is not null)
            return initial;

        return initial with { PendingSessionId = sessionId };
    }

    public static void ReleaseSession(string? sessionId)
    {
        if (string.IsNullOrWhiteSpace(sessionId))
            return;

        if (Pending.TryRemove(sessionId, out var session))
            session.Dispose();
    }

    public static async Task<MailRuWebSessionResult> CompleteChallengeAsync(
        string login,
        MailRuChallengeCompletion completion,
        CancellationToken cancellationToken)
    {
        CleanupExpired();

        if (!Pending.TryGetValue(completion.SessionId, out var session))
        {
            return MailRuWebSessionResult.Failed(
                MailRuAuthState.ProtocolError,
                "challenge_session_expired",
                "pending_auth_session_not_found");
        }

        if (!string.Equals(session.Login, login, StringComparison.OrdinalIgnoreCase))
        {
            ReleaseSession(completion.SessionId);
            return MailRuWebSessionResult.Failed(
                MailRuAuthState.ProtocolError,
                "challenge_session_mismatch",
                "pending_auth_login_mismatch");
        }

        if (session.Kind == MailRuChallengeKind.ReCaptcha)
        {
            var token = completion.Answer ?? completion.ReCaptchaResponse;

            if (string.IsNullOrWhiteSpace(token))
            {
                return MailRuWebSessionResult.Failed(
                    MailRuAuthState.ReCaptcha,
                    "recaptcha_response_missing",
                    "manual_solver_returned_empty_response",
                    session.LastChallenge,
                    session.Id);
            }

            var continued = await CreateSessionAsync(
                session,
                token,
                cancellationToken).ConfigureAwait(false);

            if (!continued.Success)
            {
                if (continued.Challenge is null)
                    ReleaseSession(session.Id);

                return continued;
            }

            var result = await DeriveTokensFromSameSessionAsync(
                session,
                cancellationToken).ConfigureAwait(false);

            ReleaseSession(session.Id);
            return result;
        }

        if (session.Kind == MailRuChallengeKind.Captcha)
        {
            var answer = completion.Answer;
            if (string.IsNullOrWhiteSpace(answer))
            {
                return MailRuWebSessionResult.Failed(
                    MailRuAuthState.Captcha,
                    "captcha_answer_missing",
                    "manual_captcha_answer_empty",
                    session.LastChallenge,
                    session.Id);
            }

            var submitted = await SubmitCaptchaAnswerAsync(
                session,
                answer,
                cancellationToken).ConfigureAwait(false);

            if (submitted.State == MailRuAuthState.Blocked)
            {
                ReleaseSession(session.Id);
                return MailRuWebSessionResult.Failed(
                    MailRuAuthState.Blocked,
                    "account_blocked",
                    submitted.Diagnostic);
            }

            if (!submitted.Success || string.IsNullOrWhiteSpace(submitted.Url))
            {
                if (submitted.RetryWholeLogin)
                {
                    session.ResetTransport();
                    var restarted = await CreateSessionAsync(
                        session,
                        null,
                        cancellationToken).ConfigureAwait(false);

                    if (restarted.Success)
                    {
                        var retryResult = await DeriveTokensFromSameSessionAsync(
                            session,
                            cancellationToken).ConfigureAwait(false);
                        ReleaseSession(session.Id);
                        return retryResult;
                    }

                    return restarted;
                }

                ReleaseSession(session.Id);
                return MailRuWebSessionResult.Failed(
                    MailRuAuthState.ProtocolError,
                    "captcha_submit_failed",
                    submitted.Diagnostic);
            }

            var linked = await CreateSessionByLinkAsync(
                session,
                submitted.Url,
                cancellationToken).ConfigureAwait(false);

            if (!linked.Success)
            {
                ReleaseSession(session.Id);
                return linked;
            }

            var result = await DeriveTokensFromSameSessionAsync(
                session,
                cancellationToken).ConfigureAwait(false);

            ReleaseSession(session.Id);
            return result;
        }

        ReleaseSession(session.Id);
        return MailRuWebSessionResult.Failed(
            MailRuAuthState.ProtocolError,
            "challenge_state_invalid",
            "unsupported_pending_challenge");
    }

    private static async Task<MailRuWebSessionResult> CreateSessionAsync(
        PendingSession session,
        string? reCaptchaToken,
        CancellationToken cancellationToken)
    {
        var attempts = reCaptchaToken is null ? 1 : 3;

        for (var attempt = 0; attempt < attempts; attempt++)
        {
            await session.WaitBeforeRequestAsync(cancellationToken).ConfigureAwait(false);

            try
            {
                using var request = new HttpRequestMessage(HttpMethod.Post, AjAuthUri);
                request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);

                var form = new Dictionary<string, string>
                {
                    ["Login"] = session.Login,
                    ["Password"] = session.Password
                };

                if (reCaptchaToken is not null)
                    form["g-recaptcha-response"] = reCaptchaToken;

                request.Content = new FormUrlEncodedContent(form);

                using var response = await session.Http.SendAsync(
                    request,
                    HttpCompletionOption.ResponseHeadersRead,
                    cancellationToken).ConfigureAwait(false);

                var location = response.Headers.Location?.ToString() ?? string.Empty;
                session.LastLocation = location;

                // Ordering intentionally mirrors Hackus CreateSession().
                if (location.Contains("user/login?login", StringComparison.OrdinalIgnoreCase))
                {
                    var kind = await ClassifyChallengeAsync(session, cancellationToken).ConfigureAwait(false);

                    if (kind == MailRuChallengeKind.TwoFactor)
                    {
                        return MailRuWebSessionResult.Failed(
                            MailRuAuthState.TwoFactor,
                            "two_factor_required",
                            $"two_factor; redirect={SanitizeLocation(location)}",
                            pendingSessionId: session.Id);
                    }

                    var challenge = await BuildImageCaptchaChallengeAsync(
                        session,
                        cancellationToken).ConfigureAwait(false);

                    if (challenge is null)
                    {
                        return MailRuWebSessionResult.Failed(
                            MailRuAuthState.ProtocolError,
                            "captcha_image_missing",
                            "hackus_get_captcha_image_failed",
                            pendingSessionId: session.Id);
                    }

                    session.Kind = MailRuChallengeKind.Captcha;
                    session.LastChallenge = challenge;

                    return MailRuWebSessionResult.Failed(
                        MailRuAuthState.Captcha,
                        "captcha_required",
                        $"captcha; redirect={SanitizeLocation(location)}",
                        challenge,
                        session.Id);
                }

                if (location.Contains("recaptcha", StringComparison.OrdinalIgnoreCase))
                {
                    var challengeUrl = NormalizeChallengeUrl(
                        location,
                        "https://account.mail.ru/");

                    var siteKey = await GetReCaptchaSiteKeyAsync(
                        session,
                        challengeUrl,
                        cancellationToken).ConfigureAwait(false);

                    if (string.IsNullOrWhiteSpace(siteKey))
                    {
                        return MailRuWebSessionResult.Failed(
                            MailRuAuthState.ProtocolError,
                            "recaptcha_sitekey_missing",
                            $"recaptcha_sitekey_missing; redirect={SanitizeLocation(challengeUrl)}",
                            pendingSessionId: session.Id);
                    }

                    var challenge = new MailRuAuthChallenge(
                        MailRuChallengeKind.ReCaptcha,
                        challengeUrl,
                        BuildSeedCookieHeader(session.Cookies, challengeUrl),
                        $"recaptcha; redirect={SanitizeLocation(challengeUrl)}",
                        session.Id,
                        siteKey);

                    session.Kind = MailRuChallengeKind.ReCaptcha;
                    session.LastChallenge = challenge;

                    return MailRuWebSessionResult.Failed(
                        MailRuAuthState.ReCaptcha,
                        "recaptcha_required",
                        challenge.DiagnosticReason,
                        challenge,
                        session.Id);
                }

                if (location.Contains("fail", StringComparison.OrdinalIgnoreCase))
                {
                    return MailRuWebSessionResult.Failed(
                        MailRuAuthState.InvalidCredentials,
                        "invalid_credentials",
                        $"location={SanitizeLocation(location)}",
                        pendingSessionId: session.Id);
                }

                if (location.Contains("recovery", StringComparison.OrdinalIgnoreCase) ||
                    location.Contains("ukey", StringComparison.OrdinalIgnoreCase))
                {
                    return MailRuWebSessionResult.Failed(
                        MailRuAuthState.Blocked,
                        "account_blocked",
                        $"location={SanitizeLocation(location)}",
                        pendingSessionId: session.Id);
                }

                if (location.Contains("inbox", StringComparison.OrdinalIgnoreCase))
                {
                    return new MailRuWebSessionResult(
                        true,
                        MailRuAuthState.Success,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        $"location={SanitizeLocation(location)}",
                        session.Id);
                }

                return MailRuWebSessionResult.Failed(
                    MailRuAuthState.ProtocolError,
                    "unknown_auth_result",
                    $"location={SanitizeLocation(location)}",
                    pendingSessionId: session.Id);
            }
            catch (Exception ex) when (ex is HttpRequestException or TaskCanceledException)
            {
                if (attempt + 1 >= attempts)
                {
                    return MailRuWebSessionResult.Failed(
                        MailRuAuthState.NetworkError,
                        "web_auth_http_error",
                        ex.GetType().Name,
                        pendingSessionId: session.Id);
                }
            }
        }

        return MailRuWebSessionResult.Failed(
            MailRuAuthState.NetworkError,
            "web_auth_http_error",
            "create_session_retry_exhausted",
            pendingSessionId: session.Id);
    }

    private static async Task<string?> GetReCaptchaSiteKeyAsync(
        PendingSession session,
        string location,
        CancellationToken cancellationToken)
    {
        for (var attempt = 0; attempt < 2; attempt++)
        {
            await session.WaitBeforeRequestAsync(cancellationToken).ConfigureAwait(false);

            try
            {
                using var request = new HttpRequestMessage(HttpMethod.Get, location);
                request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);

                using var response = await session.Http.SendAsync(
                    request,
                    cancellationToken).ConfigureAwait(false);

                var payload = await response.Content
                    .ReadAsStringAsync(cancellationToken)
                    .ConfigureAwait(false);

                var match = Regex.Match(
                    payload,
                    "recaptchaSitekey\\\":\\\"(.+?)\\\"",
                    RegexOptions.IgnoreCase);

                if (!match.Success)
                {
                    match = Regex.Match(
                        payload,
                        "recaptchaSitekey\"\\s*:\\s*\"(.+?)\"",
                        RegexOptions.IgnoreCase);
                }

                return match.Success ? match.Groups[1].Value : null;
            }
            catch (Exception ex) when (ex is HttpRequestException or TaskCanceledException)
            {
                if (attempt + 1 >= 2)
                    return null;
            }
        }

        return null;
    }

    private static async Task<MailRuAuthChallenge?> BuildImageCaptchaChallengeAsync(
        PendingSession session,
        CancellationToken cancellationToken)
    {
        var image = await GetCaptchaImageAsync(
            session,
            cancellationToken).ConfigureAwait(false);

        if (image is null || image.Length == 0)
            return null;

        return new MailRuAuthChallenge(
            MailRuChallengeKind.Captcha,
            string.Empty,
            string.Empty,
            "Hackus: GetCaptchaImage -> manual answer -> SubmitCaptchaAnswer -> CreateSessionByLink",
            session.Id,
            null,
            Convert.ToBase64String(image));
    }

    private static async Task<byte[]?> GetCaptchaImageAsync(
        PendingSession session,
        CancellationToken cancellationToken)
    {
        for (var attempt = 0; attempt < 2; attempt++)
        {
            await session.WaitBeforeRequestAsync(cancellationToken).ConfigureAwait(false);

            try
            {
                using var request = new HttpRequestMessage(HttpMethod.Get, CaptchaImageUri);
                request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);

                using var response = await session.Http.SendAsync(
                    request,
                    cancellationToken).ConfigureAwait(false);

                var bytes = await response.Content
                    .ReadAsByteArrayAsync(cancellationToken)
                    .ConfigureAwait(false);

                return bytes.Length == 0 ? null : bytes;
            }
            catch (Exception ex) when (ex is HttpRequestException or TaskCanceledException)
            {
                if (attempt + 1 >= 2)
                    return null;
            }
        }

        return null;
    }

    private static async Task<CaptchaSubmitResult> SubmitCaptchaAnswerAsync(
        PendingSession session,
        string answer,
        CancellationToken cancellationToken)
    {
        for (var attempt = 0; attempt < 2; attempt++)
        {
            await session.WaitBeforeRequestAsync(cancellationToken).ConfigureAwait(false);

            try
            {
                using var request = new HttpRequestMessage(HttpMethod.Post, AccountCopperUri);
                request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);
                request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
                {
                    ["fields"] = "{\"captcha\":\"" + answer.ToLowerInvariant() + "\"}",
                    ["htmlencoded"] = "false"
                });

                using var response = await session.Http.SendAsync(
                    request,
                    cancellationToken).ConfigureAwait(false);

                var payload = await response.Content
                    .ReadAsStringAsync(cancellationToken)
                    .ConfigureAwait(false);

                if (!payload.Contains("OK", StringComparison.OrdinalIgnoreCase))
                {
                    if (payload.Contains("429", StringComparison.OrdinalIgnoreCase))
                    {
                        return new CaptchaSubmitResult(
                            false,
                            MailRuAuthState.Blocked,
                            null,
                            false,
                            "captcha_submit_429");
                    }

                    if (payload.Contains("invalid", StringComparison.OrdinalIgnoreCase))
                    {
                        return new CaptchaSubmitResult(
                            false,
                            MailRuAuthState.ProtocolError,
                            null,
                            true,
                            "captcha_submit_invalid");
                    }

                    return new CaptchaSubmitResult(
                        false,
                        MailRuAuthState.ProtocolError,
                        null,
                        false,
                        "captcha_submit_unrecognized");
                }

                var match = Regex.Match(
                    payload,
                    "\"url\":\"(.+?)\"",
                    RegexOptions.IgnoreCase);

                return match.Success
                    ? new CaptchaSubmitResult(
                        true,
                        MailRuAuthState.Success,
                        match.Groups[1].Value,
                        false,
                        "captcha_submit_ok")
                    : new CaptchaSubmitResult(
                        false,
                        MailRuAuthState.ProtocolError,
                        null,
                        false,
                        "captcha_submit_url_missing");
            }
            catch (Exception ex) when (ex is HttpRequestException or TaskCanceledException)
            {
                if (attempt + 1 >= 2)
                {
                    return new CaptchaSubmitResult(
                        false,
                        MailRuAuthState.NetworkError,
                        null,
                        true,
                        ex.GetType().Name);
                }
            }
        }

        return new CaptchaSubmitResult(
            false,
            MailRuAuthState.NetworkError,
            null,
            true,
            "captcha_submit_retry_exhausted");
    }

    private static async Task<MailRuWebSessionResult> CreateSessionByLinkAsync(
        PendingSession session,
        string url,
        CancellationToken cancellationToken)
    {
        for (var attempt = 0; attempt < 2; attempt++)
        {
            await session.WaitBeforeRequestAsync(cancellationToken).ConfigureAwait(false);

            try
            {
                var decoded = WebUtility.UrlDecode(Regex.Unescape(url));

                using var request = new HttpRequestMessage(HttpMethod.Get, decoded);
                request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);

                using var response = await session.Http.SendAsync(
                    request,
                    HttpCompletionOption.ResponseHeadersRead,
                    cancellationToken).ConfigureAwait(false);

                var location = response.Headers.Location?.ToString() ?? string.Empty;

                if (location.Contains("inbox", StringComparison.OrdinalIgnoreCase))
                {
                    return new MailRuWebSessionResult(
                        true,
                        MailRuAuthState.Success,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        $"create_session_by_link={SanitizeLocation(location)}",
                        session.Id);
                }

                return MailRuWebSessionResult.Failed(
                    MailRuAuthState.ProtocolError,
                    "challenge_link_failed",
                    $"create_session_by_link={SanitizeLocation(location)}",
                    pendingSessionId: session.Id);
            }
            catch (Exception ex) when (ex is HttpRequestException or TaskCanceledException)
            {
                if (attempt + 1 >= 2)
                {
                    return MailRuWebSessionResult.Failed(
                        MailRuAuthState.NetworkError,
                        "challenge_link_http_error",
                        ex.GetType().Name,
                        pendingSessionId: session.Id);
                }
            }
        }

        return MailRuWebSessionResult.Failed(
            MailRuAuthState.NetworkError,
            "challenge_link_http_error",
            "retry_exhausted",
            pendingSessionId: session.Id);
    }

    private sealed record CaptchaSubmitResult(
        bool Success,
        MailRuAuthState State,
        string? Url,
        bool RetryWholeLogin,
        string Diagnostic);

    private static async Task<MailRuWebSessionResult> DeriveTokensFromSameSessionAsync(
        PendingSession session,
        CancellationToken cancellationToken)
    {
        var webToken = await TryGetWebTokenAsync(session, cancellationToken).ConfigureAwait(false);
        var searchToken = await TryGetSearchTokenAsync(session, cancellationToken).ConfigureAwait(false);

        var webCookieHeader = session.Cookies.GetCookieHeader(InboxUri);
        var touchCookieHeader = session.Cookies.GetCookieHeader(TouchTokensUri);

        if (string.IsNullOrWhiteSpace(webToken) &&
            string.IsNullOrWhiteSpace(searchToken))
        {
            return MailRuWebSessionResult.Failed(
                MailRuAuthState.ProtocolError,
                "web_session_token_missing",
                "same_cookie_session_present_but_no_web_or_touch_token",
                pendingSessionId: session.Id);
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
            "tokens_derived_from_original_cookie_session",
            session.Id);
    }

    private static async Task<MailRuChallengeKind> ClassifyChallengeAsync(
        PendingSession session,
        CancellationToken cancellationToken)
    {
        try
        {
            await session.WaitBeforeRequestAsync(cancellationToken).ConfigureAwait(false);

            using var request = new HttpRequestMessage(HttpMethod.Get, AccountCopperUri);
            request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);
            using var response = await session.Http.SendAsync(request, cancellationToken).ConfigureAwait(false);
            var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

            return payload.Contains("captcha", StringComparison.OrdinalIgnoreCase)
                ? MailRuChallengeKind.Captcha
                : MailRuChallengeKind.TwoFactor;
        }
        catch
        {
            return MailRuChallengeKind.TwoFactor;
        }
    }

    private static async Task<string?> TryGetSearchTokenAsync(
        PendingSession session,
        CancellationToken cancellationToken)
    {
        try
        {
            await session.WaitBeforeRequestAsync(cancellationToken).ConfigureAwait(false);

            var builder = new UriBuilder(TouchTokensUri)
            {
                Query = "email=" + Uri.EscapeDataString(session.Login)
            };

            using var request = new HttpRequestMessage(HttpMethod.Get, builder.Uri);
            request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);

            using var response = await session.Http.SendAsync(request, cancellationToken).ConfigureAwait(false);
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
        PendingSession session,
        CancellationToken cancellationToken)
    {
        try
        {
            await session.WaitBeforeRequestAsync(cancellationToken).ConfigureAwait(false);

            using var request = new HttpRequestMessage(HttpMethod.Get, InboxUri);
            request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);

            using var response = await session.Http.SendAsync(request, cancellationToken).ConfigureAwait(false);
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

    private static async Task FollowBrowserResultAsync(
        PendingSession session,
        Uri uri,
        CancellationToken cancellationToken)
    {
        var current = uri;

        for (var i = 0; i < 6; i++)
        {
            await session.WaitBeforeRequestAsync(cancellationToken).ConfigureAwait(false);

            using var request = new HttpRequestMessage(HttpMethod.Get, current);
            request.Headers.TryAddWithoutValidation("User-Agent", MailRuFixedProfile.UserAgent);

            using var response = await session.Http.SendAsync(
                request,
                HttpCompletionOption.ResponseHeadersRead,
                cancellationToken).ConfigureAwait(false);

            if (response.Headers.Location is null)
                return;

            current = response.Headers.Location.IsAbsoluteUri
                ? response.Headers.Location
                : new Uri(current, response.Headers.Location);
        }
    }

    private static void MergeBrowserCookies(
        CookieContainer cookies,
        MailRuChallengeCompletion completion)
    {
        ImportCookieHeader(cookies, new Uri("https://account.mail.ru/"), completion.AccountCookieHeader);
        ImportCookieHeader(cookies, new Uri("https://mail.ru/"), completion.MailCookieHeader);
        ImportCookieHeader(cookies, new Uri("https://e.mail.ru/"), completion.WebCookieHeader);
        ImportCookieHeader(cookies, new Uri("https://touch.mail.ru/"), completion.TouchCookieHeader);
        ImportCookieHeader(cookies, new Uri("https://aj-https.mail.ru/"), completion.AjCookieHeader);
    }

    private static void ImportCookieHeader(CookieContainer cookies, Uri uri, string header)
    {
        if (string.IsNullOrWhiteSpace(header))
            return;

        try
        {
            cookies.SetCookies(uri, header);
        }
        catch
        {
            foreach (var part in header.Split(
                         ';',
                         StringSplitOptions.RemoveEmptyEntries | StringSplitOptions.TrimEntries))
            {
                var separator = part.IndexOf('=');
                if (separator <= 0)
                    continue;

                try
                {
                    cookies.SetCookies(uri, part);
                }
                catch
                {
                }
            }
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
            foreach (var part in header.Split(
                         ';',
                         StringSplitOptions.RemoveEmptyEntries | StringSplitOptions.TrimEntries))
            {
                if (!string.IsNullOrWhiteSpace(part))
                    parts.Add(part);
            }
        }

        return string.Join("; ", parts);
    }

    private static void CleanupExpired()
    {
        var now = DateTimeOffset.UtcNow;

        foreach (var pair in Pending)
        {
            if (now - pair.Value.CreatedAtUtc > PendingLifetime &&
                Pending.TryRemove(pair.Key, out var expired))
            {
                expired.Dispose();
            }
        }
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

    private sealed class PendingSession : IDisposable
    {
        private DateTimeOffset _lastRequestAt = DateTimeOffset.MinValue;

        public PendingSession(
            string id,
            string login,
            string password,
            TimeSpan timeout)
        {
            Id = id;
            Login = login;
            Password = password;
            Timeout = timeout;
            CreatedAtUtc = DateTimeOffset.UtcNow;
            ResetTransport();
        }

        public string Id { get; }
        public string Login { get; }
        public string Password { get; }
        public TimeSpan Timeout { get; }
        public DateTimeOffset CreatedAtUtc { get; }
        public CookieContainer Cookies { get; private set; } = null!;
        public HttpClientHandler Handler { get; private set; } = null!;
        public HttpClient Http { get; private set; } = null!;
        public MailRuChallengeKind Kind { get; set; } = MailRuChallengeKind.ReCaptcha;
        public string? LastLocation { get; set; }
        public MailRuAuthChallenge? LastChallenge { get; set; }

        public void ResetTransport()
        {
            try
            {
                Http?.Dispose();
                Handler?.Dispose();
            }
            catch
            {
            }

            Cookies = new CookieContainer();
            Handler = new HttpClientHandler
            {
                AllowAutoRedirect = false,
                UseCookies = true,
                CookieContainer = Cookies,
                AutomaticDecompression = DecompressionMethods.All
            };

            Http = new HttpClient(Handler)
            {
                Timeout = Timeout
            };

            LastLocation = null;
            LastChallenge = null;
            _lastRequestAt = DateTimeOffset.MinValue;
        }

        public async Task WaitBeforeRequestAsync(CancellationToken cancellationToken)
        {
            var elapsed = DateTimeOffset.UtcNow - _lastRequestAt;
            var remaining = TimeSpan.FromSeconds(5) - elapsed;

            if (remaining > TimeSpan.Zero)
                await Task.Delay(remaining, cancellationToken).ConfigureAwait(false);

            _lastRequestAt = DateTimeOffset.UtcNow;
        }

        public void Dispose()
        {
            Http.Dispose();
            Handler.Dispose();
        }
    }

}
