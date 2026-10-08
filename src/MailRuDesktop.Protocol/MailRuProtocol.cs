using System.Collections.Concurrent;
using System.Globalization;
using System.Net;
using System.Net.Http.Headers;
using System.Text.Json;

namespace MailRuDesktop.Protocol;

public enum EndpointEvidence
{
    VerifiedLocal,
    StaticOfficialClient,
    ExternalConfirmed,
    Candidate,
    RejectedOrObsolete
}

public sealed record EndpointDefinition(
    string Name,
    string Method,
    string Host,
    string Path,
    EndpointEvidence Evidence,
    string Purpose);

public static class MailRuEndpointCatalog
{
    public static IReadOnlyList<EndpointDefinition> All { get; } =
    [
        new("auth.mobile", "POST", "aj-https.mail.ru", "/cgi-bin/auth", EndpointEvidence.VerifiedLocal, "Mobile OAuth-style authentication"),
        new("threads.status.smart", "GET", "aj-https.mail.ru", "/api/v1/m/threads/status/smart", EndpointEvidence.VerifiedLocal, "Folder/thread status"),
        new("messages.message", "GET", "aj-https.mail.ru", "/api/v1/messages/message", EndpointEvidence.VerifiedLocal, "Full message"),
        new("messages.marks", "POST", "aj-https.mail.ru", "/api/v1/messages/marks", EndpointEvidence.VerifiedLocal, "Unread/read marks"),
        new("messages.move", "POST", "aj-https.mail.ru", "/api/v1/messages/move", EndpointEvidence.VerifiedLocal, "Move/archive/trash"),
        new("messages.attach.add", "POST", "aj-https.mail.ru", "/api/v1/messages/attaches/add", EndpointEvidence.VerifiedLocal, "Upload attachment"),
        new("messages.send", "POST", "aj-https.mail.ru", "/api/v1/messages/send", EndpointEvidence.VerifiedLocal, "Send message"),
        new("messages.schedule", "POST", "aj-https.mail.ru", "/api/v1/messages/schedule", EndpointEvidence.VerifiedLocal, "Server-side scheduled send"),
        new("attachments.readmsg", "GET", "af.attachmail.ru", "/cgi-bin/readmsg", EndpointEvidence.VerifiedLocal, "Download incoming attachment"),
        new("images.proxy", "GET", "proxy.imgsmail.ru", "/", EndpointEvidence.ExternalConfirmed, "Signed image proxy URLs observed in live Mail.ru message HTML"),
        new("contacts.avatar", "GET", "filin.mail.ru", "/pic", EndpointEvidence.StaticOfficialClient, "Public contact avatars: URL found in official APK strings; live photo rendering pending validation"),
        new("messages.remove", "POST", "aj-https.mail.ru", "/api/v1/messages/remove", EndpointEvidence.VerifiedLocal, "Permanent message removal"),
        new("messages.search", "GET", "aj-https.mail.ru", "/api/v1/messages/search", EndpointEvidence.VerifiedLocal, "Server-side message search"),
        new("messages.search.new", "GET", "go.mail.ru", "/api/v1/go/search/emails", EndpointEvidence.StaticOfficialClient, "New server-side message search"),
        new("addressbook.smart", "GET", "aj-https.mail.ru", "/api/v1/ab/smart", EndpointEvidence.VerifiedLocal, "Server address book"),
        new("addressbook.fast", "GET", "aj-https.mail.ru", "/api/v1/ab/fast", EndpointEvidence.VerifiedLocal, "Fast recipient lookup"),
        new("folders.list", "GET", "aj-https.mail.ru", "/api/v1/folders", EndpointEvidence.VerifiedLocal, "Folder list"),
        new("folders.add", "POST", "aj-https.mail.ru", "/api/v1/folders/add", EndpointEvidence.VerifiedLocal, "Create folder"),
        new("folders.edit", "POST", "aj-https.mail.ru", "/api/v1/folders/edit", EndpointEvidence.VerifiedLocal, "Rename folder"),
        new("folders.remove", "POST", "aj-https.mail.ru", "/api/v1/folders/remove", EndpointEvidence.VerifiedLocal, "Delete folder"),
        new("folders.clear", "POST", "aj-https.mail.ru", "/api/v1/folders/clear", EndpointEvidence.VerifiedLocal, "Clear folder"),
        new("messages.draft", "POST", "aj-https.mail.ru", "/api/v1/messages/draft", EndpointEvidence.VerifiedLocal, "Save draft"),
        new("oauth.refresh", "POST", "o2.mail.ru", "/token", EndpointEvidence.VerifiedLocal, "Refresh mailbox access token")
    ];

    public static bool IsRuntimeHostAllowed(string host) =>
        All.Any(endpoint =>
            endpoint.Evidence != EndpointEvidence.RejectedOrObsolete &&
            string.Equals(endpoint.Host, host, StringComparison.OrdinalIgnoreCase));
}

public sealed record MailRuClientOptions
{
    public Uri BaseUri { get; init; } = new("https://aj-https.mail.ru");
    public string UserAgent { get; init; } = MailRuFixedProfile.MobileUserAgent;
    public TimeSpan Timeout { get; init; } = TimeSpan.FromSeconds(30);
}

public sealed record MailRuAuthResult(
    bool Success,
    string? AccessToken,
    string? RefreshToken,
    string? ErrorCode)
{
    public MailRuAuthState State { get; init; } = MailRuAuthState.Unknown;

    // Kept only for backward binary/source compatibility with older app code.
    // The current access-token mode never populates or consumes these legacy credentials.
    public string? WebToken { get; init; }
    public string? SearchToken { get; init; }
    public string? WebCookieHeader { get; init; }
    public string? TouchCookieHeader { get; init; }
    public MailRuAuthChallenge? Challenge { get; init; }
    public string? DiagnosticReason { get; init; }
    public string? DiagnosticDetails { get; init; }

    public bool HasMailboxCredential =>
        !string.IsNullOrWhiteSpace(AccessToken);

    public static MailRuAuthResult Failed(
        string code,
        MailRuAuthState state = MailRuAuthState.Unknown,
        string? diagnosticReason = null,
        MailRuAuthChallenge? challenge = null,
        string? diagnosticDetails = null) =>
        new(false, null, null, code)
        {
            State = state,
            DiagnosticReason = diagnosticReason,
            Challenge = challenge,
            DiagnosticDetails = diagnosticDetails
        };
}

public sealed record MailRuOutgoingMessage(
    string To,
    string Subject,
    string Text,
    string? Html = null,
    string? ReplyToId = null,
    string? SendDate = null,
    IReadOnlyList<string>? AttachmentIds = null,
    int Priority = 3,
    string? MessageId = null,
    bool RequestReadReceipt = false);

public sealed record MailRuCommandResult(bool Success, string RawResponse);

public sealed class MailRuProtocolException : Exception
{
    public MailRuProtocolException(string message) : base(message) { }
}

public sealed partial class MailRuClient : IDisposable
{
    public const string KnownWorkingMessageId = "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR";

    private readonly MailRuClientOptions _options;
    private readonly HttpClient _http;
    private readonly bool _ownsHttpClient;
    private readonly SemaphoreSlim _requestGate = new(1, 1);
    private readonly ConcurrentDictionary<string, PendingAuthSession> _pendingAuthSessions =
        new(StringComparer.Ordinal);

    private sealed record PendingAuthSession(
        string Login,
        string Password,
        string CookieHeader,
        IReadOnlyList<MailRuAuthBrowserCookie> BrowserCookies,
        DateTimeOffset CreatedAtUtc);

    public MailRuClient(MailRuClientOptions? options = null, HttpClient? httpClient = null)
    {
        _options = options ?? new MailRuClientOptions();
        _ownsHttpClient = httpClient is null;

        if (httpClient is null)
        {
            var handler = new HttpClientHandler
            {
                AllowAutoRedirect = false,
                AutomaticDecompression = DecompressionMethods.All,
                UseCookies = false
            };

            _http = new HttpClient(handler, disposeHandler: true)
            {
                Timeout = _options.Timeout
            };
        }
        else
        {
            _http = httpClient;
        }
    }

    public Task<MailRuAuthResult> AuthenticateAsync(
        string login,
        string password,
        CancellationToken cancellationToken = default) =>
        AuthenticateCoreAsync(
            login,
            password,
            sessionCookieHeader: null,
            additionalParams: null,
            registerChallengeSession: true,
            cancellationToken);

    public Task<MailRuAuthResult> AuthenticateWithSessionCookiesAsync(
        string login,
        string password,
        string? sessionCookieHeader,
        CancellationToken cancellationToken = default) =>
        AuthenticateCoreAsync(
            login,
            password,
            sessionCookieHeader,
            additionalParams: null,
            registerChallengeSession: true,
            cancellationToken);

    private async Task<MailRuAuthResult> AuthenticateCoreAsync(
        string login,
        string password,
        string? sessionCookieHeader,
        IReadOnlyDictionary<string, string>? additionalParams,
        bool registerChallengeSession,
        CancellationToken cancellationToken)
    {
        if (string.IsNullOrWhiteSpace(login))
            throw new ArgumentException("Login is required.", nameof(login));
        if (string.IsNullOrEmpty(password))
            throw new ArgumentException("Password is required.", nameof(password));

        var uri = BuildUri("/cgi-bin/auth", new Dictionary<string, string?>
        {
            ["mp"] = "android",
            ["udid"] = "mailru_app"
        });

        using var request = CreateRequest(HttpMethod.Post, uri);
        if (!string.IsNullOrWhiteSpace(sessionCookieHeader))
        {
            request.Headers.TryAddWithoutValidation(
                "Cookie",
                sessionCookieHeader);
        }

        var form = new Dictionary<string, string>
        {
            ["Password"] = password,
            ["Login"] = login,
            ["oauth2"] = "1",
            ["useragent"] = "android",
            ["mobile"] = "1",
            ["mob_json"] = "1",
            ["simple"] = "1"
        };

        if (additionalParams is not null)
        {
            foreach (var pair in additionalParams)
            {
                if (!string.IsNullOrWhiteSpace(pair.Key))
                    form[pair.Key] = pair.Value ?? string.Empty;
            }
        }

        request.Content = new FormUrlEncodedContent(form);

        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);
        var location = response.Headers.Location?.ToString() ?? string.Empty;
        var mergedSessionCookieHeader = MergeCookieHeader(sessionCookieHeader, response);
        var responseBrowserCookies = ReadResponseCookies(response, uri);

        JsonDocument? document = null;
        try
        {
            document = JsonDocument.Parse(payload);

            if (TryFindString(document.RootElement, "access_token", out var accessToken) &&
                !string.IsNullOrWhiteSpace(accessToken))
            {
                TryFindString(document.RootElement, "refresh_token", out var refreshToken);

                return new MailRuAuthResult(true, accessToken, refreshToken, null)
                {
                    State = MailRuAuthState.Success,
                    DiagnosticReason = "aj_mobile_access_token_received"
                };
            }

            var statusText = FindStringByNamesIgnoreCase(
                document.RootElement,
                "Status",
                "status");
            var continueValue = FindStringByNamesIgnoreCase(
                document.RootElement,
                "Continue",
                "continue");

            var error =
                TryFindString(document.RootElement, "error", out var parsedError) &&
                !string.IsNullOrWhiteSpace(parsedError)
                    ? parsedError!
                    : "token_missing";

            var state = ClassifyAuthStatusValue(statusText);
            if (state == MailRuAuthState.Unknown)
                state = ClassifyAuthState(document.RootElement, error);

            MailRuAuthChallenge? challenge;
            string diagnosticReason;

            if (!string.IsNullOrWhiteSpace(continueValue))
            {
                var continueState = ClassifyContinueValue(continueValue);
                if (continueState != MailRuAuthState.Unknown)
                    state = continueState;
                else if (state == MailRuAuthState.Unknown)
                    state = MailRuAuthState.RecoveryRequired;

                var localSessionId =
                    registerChallengeSession
                        ? RegisterPendingAuthSession(
                            login,
                            password,
                            mergedSessionCookieHeader,
                            responseBrowserCookies)
                        : string.Empty;

                challenge = BuildContinueChallenge(
                    state,
                    continueValue,
                    _options.BaseUri,
                    localSessionId);
                diagnosticReason = "aj_mobile_auth_continue_required";
            }
            else
            {
                challenge = TryBuildAuthChallenge(
                    document.RootElement,
                    state,
                    location);

                if (state == MailRuAuthState.ReCaptcha &&
                    challenge is not null &&
                    registerChallengeSession)
                {
                    challenge = challenge with
                    {
                        SessionId = RegisterPendingAuthSession(
                            login,
                            password,
                            mergedSessionCookieHeader,
                            responseBrowserCookies)
                    };
                }

                diagnosticReason = "aj_mobile_auth_returned_no_access_token";
            }

            var diagnostic = BuildNoTokenDiagnostic(
                document.RootElement,
                statusText,
                continueValue);

            var normalizedError = state switch
            {
                MailRuAuthState.ReCaptcha => "recaptcha_required",
                MailRuAuthState.Captcha => "captcha_required",
                MailRuAuthState.TwoFactor => "two_factor_required",
                MailRuAuthState.Blocked => "account_blocked",
                MailRuAuthState.RecoveryRequired => "additional_verification_required",
                _ => error
            };

            return MailRuAuthResult.Failed(
                normalizedError,
                state,
                diagnosticReason,
                challenge,
                diagnostic);
        }
        catch (JsonException)
        {
            if (LooksLikeCaptcha(payload, location))
            {
                var state =
                    payload.Contains("recaptcha", StringComparison.OrdinalIgnoreCase) ||
                    location.Contains("recaptcha", StringComparison.OrdinalIgnoreCase)
                        ? MailRuAuthState.ReCaptcha
                        : MailRuAuthState.Captcha;

                return MailRuAuthResult.Failed(
                    state == MailRuAuthState.ReCaptcha
                        ? "recaptcha_required"
                        : "captcha_required",
                    state,
                    "aj_mobile_auth_requires_captcha",
                    BuildRedirectChallenge(
                        state,
                        location,
                        state == MailRuAuthState.ReCaptcha && registerChallengeSession
                            ? RegisterPendingAuthSession(
                                login,
                                password,
                                mergedSessionCookieHeader,
                                responseBrowserCookies)
                            : string.Empty),
                    $"http={(int)response.StatusCode}; response=non-json");
            }

            if ((int)response.StatusCode is >= 300 and < 400)
            {
                return MailRuAuthResult.Failed(
                    "additional_verification_required",
                    MailRuAuthState.RecoveryRequired,
                    "aj_mobile_auth_redirected_to_additional_verification",
                    challenge: null,
                    diagnosticDetails:
                        $"http={(int)response.StatusCode}; location-host={SafeHost(location)}");
            }

            return MailRuAuthResult.Failed(
                response.IsSuccessStatusCode
                    ? "malformed_json"
                    : $"http_{(int)response.StatusCode}",
                MailRuAuthState.ProtocolError,
                "aj_mobile_auth_response_not_json",
                diagnosticDetails:
                    $"http={(int)response.StatusCode}; response=non-json");
        }
        finally
        {
            document?.Dispose();
        }
    }

    public async Task<MailRuAuthResult> CompleteChallengeAsync(
        string login,
        MailRuChallengeCompletion completion,
        CancellationToken cancellationToken = default)
    {
        if (string.IsNullOrWhiteSpace(completion.SessionId) ||
            string.IsNullOrWhiteSpace(completion.Answer))
        {
            return MailRuAuthResult.Failed(
                "captcha_result_missing",
                MailRuAuthState.Captcha,
                "captcha_completion_missing");
        }

        if (!_pendingAuthSessions.TryRemove(completion.SessionId, out var session))
        {
            return MailRuAuthResult.Failed(
                "captcha_session_expired",
                MailRuAuthState.Captcha,
                "captcha_pending_session_not_found");
        }

        if (!string.Equals(
                login,
                session.Login,
                StringComparison.OrdinalIgnoreCase))
        {
            return MailRuAuthResult.Failed(
                "captcha_session_account_mismatch",
                MailRuAuthState.ProtocolError,
                "captcha_pending_session_account_mismatch");
        }

        return await AuthenticateCoreAsync(
            session.Login,
            session.Password,
            sessionCookieHeader: null,
            additionalParams: new Dictionary<string, string>
            {
                ["ludwig_token"] = completion.Answer
            },
            registerChallengeSession: false,
            cancellationToken).ConfigureAwait(false);
    }

    public async Task<MailRuAuthResult> CompleteSecondStepAsync(
        string login,
        string sessionId,
        string? tsaCookie,
        IReadOnlyDictionary<string, string> additionalParams,
        CancellationToken cancellationToken = default)
    {
        if (string.IsNullOrWhiteSpace(sessionId))
        {
            return MailRuAuthResult.Failed(
                "second_step_session_missing",
                MailRuAuthState.RecoveryRequired,
                "second_step_session_missing");
        }

        if (!_pendingAuthSessions.TryRemove(sessionId, out var session))
        {
            return MailRuAuthResult.Failed(
                "second_step_session_expired",
                MailRuAuthState.RecoveryRequired,
                "second_step_pending_session_not_found");
        }

        if (!string.Equals(
                login,
                session.Login,
                StringComparison.OrdinalIgnoreCase))
        {
            return MailRuAuthResult.Failed(
                "second_step_session_account_mismatch",
                MailRuAuthState.ProtocolError,
                "second_step_pending_session_account_mismatch");
        }

        // Official APK starts the browser second step with Set-Cookie from
        // the 808 response, then returns to password auth with the tsa cookie
        // and all query parameters from the mobile-auth/success redirect.
        var tsaHeader = string.IsNullOrWhiteSpace(tsaCookie)
            ? null
            : "tsa=" + tsaCookie;

        return await AuthenticateCoreAsync(
            session.Login,
            session.Password,
            tsaHeader,
            additionalParams,
            registerChallengeSession: false,
            cancellationToken).ConfigureAwait(false);
    }

    public async Task<string> GetFolderThreadsAsync(
        string accessToken,
        int folderId,
        int offset = 0,
        int limit = 200,
        long lastModified = 1,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        if (offset < 0) throw new ArgumentOutOfRangeException(nameof(offset));
        if (limit is < 1 or > 1000) throw new ArgumentOutOfRangeException(nameof(limit));

        var folders = JsonSerializer.Serialize(new[]
        {
            new { folder = folderId, offset, limit }
        });

        var uri = BuildUri("/api/v1/m/threads/status/smart", new Dictionary<string, string?>
        {
            ["folders"] = folders,
            ["last_modified"] = lastModified.ToString(CultureInfo.InvariantCulture),
            ["access_token"] = accessToken
        });

        using var request = CreateRequest(HttpMethod.Get, uri);
        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Thread status request failed with HTTP {(int)response.StatusCode}.");

        return payload;
    }

    public async Task<MailRuFullMessage> GetFullMessageAsync(
        string accessToken,
        string messageId,
        bool markRead = false,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        if (string.IsNullOrWhiteSpace(messageId))
            throw new ArgumentException("Message id is required.", nameof(messageId));

        var uri = BuildUri("/api/v1/messages/message", new Dictionary<string, string?>
        {
            ["id"] = messageId,
            ["mark_read"] = markRead ? "true" : "false",
            ["mp"] = "android",
            ["access_token"] = accessToken
        });

        using var request = CreateRequest(HttpMethod.Get, uri);
        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Full-message request failed with HTTP {(int)response.StatusCode}.");

        return MailRuFullMessageParser.Parse(payload, messageId);
    }

    public async Task<MailRuCommandResult> SetUnreadAsync(
        string accessToken,
        string email,
        string messageId,
        bool unread,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        if (string.IsNullOrWhiteSpace(messageId))
            throw new ArgumentException("Message id is required.", nameof(messageId));

        var marks = JsonSerializer.Serialize(new object[]
        {
            new
            {
                set = unread ? new[] { messageId } : Array.Empty<string>(),
                unset = unread ? Array.Empty<string>() : new[] { messageId },
                name = "unread"
            },
            new
            {
                set = Array.Empty<string>(),
                unset = Array.Empty<string>(),
                name = "flagged"
            }
        });

        var uri = BuildUri("/api/v1/messages/marks", new Dictionary<string, string?>
        {
            ["htmlencoded"] = "false",
            ["email"] = string.IsNullOrWhiteSpace(email) ? null : email,
            ["mp"] = "android",
            ["access_token"] = accessToken
        });

        using var request = CreateRequest(HttpMethod.Post, uri);
        request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
        {
            ["marks"] = marks
        });

        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        return new MailRuCommandResult(
            response.IsSuccessStatusCode && HasStatus200(payload),
            payload);
    }

    public async Task<MailRuCommandResult> MoveMessagesAsync(
        string accessToken,
        IReadOnlyCollection<string> messageIds,
        int destinationFolderId,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        ArgumentNullException.ThrowIfNull(messageIds);
        if (messageIds.Count == 0)
            throw new ArgumentException("At least one message id is required.", nameof(messageIds));

        var uri = BuildUri("/api/v1/messages/move", new Dictionary<string, string?>
        {
            ["htmlencoded"] = "false",
            ["mp"] = "android",
            ["access_token"] = accessToken
        });

        using var request = CreateRequest(HttpMethod.Post, uri);
        request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
        {
            ["folder"] = destinationFolderId.ToString(CultureInfo.InvariantCulture),
            ["ids"] = JsonSerializer.Serialize(messageIds)
        });

        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        return new MailRuCommandResult(
            response.IsSuccessStatusCode && HasStatus200(payload),
            payload);
    }

    public async Task<byte[]> DownloadIncomingAttachmentAsync(
        string accessToken,
        string messageId,
        string attachmentId,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        if (string.IsNullOrWhiteSpace(messageId))
            throw new ArgumentException("Message id is required.", nameof(messageId));
        if (string.IsNullOrWhiteSpace(attachmentId))
            throw new ArgumentException("Attachment id is required.", nameof(attachmentId));

        var uri = BuildAbsoluteUri(
            "https://af.attachmail.ru/cgi-bin/readmsg",
            new Dictionary<string, string?>
            {
                ["access_token"] = accessToken,
                ["id"] = messageId + ";" + attachmentId,
                ["notype"] = "1"
            });

        using var request = CreateRequest(HttpMethod.Get, uri);
        using var response = await SendSerializedAsync(
            request,
            HttpCompletionOption.ResponseHeadersRead,
            cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Incoming attachment download failed with HTTP {(int)response.StatusCode}.");

        return await response.Content.ReadAsByteArrayAsync(cancellationToken).ConfigureAwait(false);
    }

    public async Task<string> UploadAttachmentAsync(
        string accessToken,
        Stream stream,
        string fileName,
        string? messageId = null,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        ArgumentNullException.ThrowIfNull(stream);

        if (string.IsNullOrWhiteSpace(fileName))
            throw new ArgumentException("File name is required.", nameof(fileName));

        messageId ??= KnownWorkingMessageId;

        var uri = BuildUri("/api/v1/messages/attaches/add", new Dictionary<string, string?>
        {
            ["htmlencoded"] = "false",
            ["mp"] = "android",
            ["access_token"] = accessToken
        });

        using var multipart = new MultipartFormDataContent();
        multipart.Add(new StringContent(messageId), "message_id");

        using var fileContent = new StreamContent(stream);
        fileContent.Headers.ContentType = new MediaTypeHeaderValue("multipart/form-data");
        multipart.Add(fileContent, "file", fileName);

        using var request = CreateRequest(HttpMethod.Post, uri);
        request.Content = multipart;

        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (payload.Contains("filesize_limit_exceeded", StringComparison.OrdinalIgnoreCase))
            throw new MailRuProtocolException("Mail.ru rejected the attachment because the file-size limit was exceeded.");

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Attachment upload failed with HTTP {(int)response.StatusCode}.");

        try
        {
            using var document = JsonDocument.Parse(payload);
            if (TryFindAttachId(document.RootElement, out var id) && !string.IsNullOrWhiteSpace(id))
                return id!;
        }
        catch (JsonException)
        {
        }

        throw new MailRuProtocolException("Attachment upload response did not contain an attachment id.");
    }

    public async Task<MailRuCommandResult> SendMessageAsync(
        string accessToken,
        MailRuOutgoingMessage message,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        ArgumentNullException.ThrowIfNull(message);

        if (string.IsNullOrWhiteSpace(message.To))
            throw new ArgumentException("Recipient is required.", nameof(message));

        var messageId = string.IsNullOrWhiteSpace(message.MessageId)
            ? KnownWorkingMessageId
            : message.MessageId!;

        var attachmentIds = message.AttachmentIds ?? Array.Empty<string>();
        var attaches = JsonSerializer.Serialize(new
        {
            list = attachmentIds.Select(id => new { id, type = "attach" }).ToArray()
        });

        var html = message.Html ?? BuildSimpleHtml(message.Text);
        var body = JsonSerializer.Serialize(new { html, text = message.Text });
        var correspondents = JsonSerializer.Serialize(new { bcc = "", cc = "", to = message.To });
        var source = JsonSerializer.Serialize(new { reply = message.ReplyToId ?? "" });

        var subject = message.Subject ?? string.Empty;
        if (!string.IsNullOrWhiteSpace(message.ReplyToId) &&
            !subject.StartsWith("Re:", StringComparison.OrdinalIgnoreCase))
        {
            subject = "Re: " + subject;
        }

        var scheduled = !string.IsNullOrWhiteSpace(message.SendDate);
        var endpoint = scheduled ? "/api/v1/messages/schedule" : "/api/v1/messages/send";

        var uri = BuildUri(endpoint, new Dictionary<string, string?>
        {
            ["htmlencoded"] = "false",
            ["mp"] = "android",
            ["access_token"] = accessToken
        });

        using var request = CreateRequest(HttpMethod.Post, uri);
        var form = new Dictionary<string, string>
        {
            ["attaches"] = attaches,
            ["body"] = body,
            ["correspondents"] = correspondents,
            ["id"] = messageId,
            ["source"] = source,
            ["subject"] = subject,
            ["send_date"] = scheduled ? message.SendDate! : "0",
            ["priority"] = message.Priority.ToString(CultureInfo.InvariantCulture)
        };

        // TornadoSendParamsImpl: @Param POST "receipt" mReadVerify (boolean).
        // Keep the existing payload byte-for-byte compatible when unchecked.
        if (message.RequestReadReceipt)
            form["receipt"] = "true";

        request.Content = new FormUrlEncodedContent(form);

        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        return new MailRuCommandResult(
            response.IsSuccessStatusCode && HasStatus200(payload),
            payload);
    }

    public IReadOnlyList<MailRuAuthBrowserCookie> GetChallengeBrowserCookies(
        string sessionId)
    {
        if (string.IsNullOrWhiteSpace(sessionId) ||
            !_pendingAuthSessions.TryGetValue(sessionId, out var session))
        {
            return Array.Empty<MailRuAuthBrowserCookie>();
        }

        if (DateTimeOffset.UtcNow - session.CreatedAtUtc > TimeSpan.FromMinutes(10))
        {
            _pendingAuthSessions.TryRemove(sessionId, out _);
            return Array.Empty<MailRuAuthBrowserCookie>();
        }

        return session.BrowserCookies.ToArray();
    }

    private string RegisterPendingAuthSession(
        string login,
        string password,
        string cookieHeader,
        IReadOnlyList<MailRuAuthBrowserCookie> browserCookies)
    {
        var now = DateTimeOffset.UtcNow;

        foreach (var item in _pendingAuthSessions)
        {
            if (now - item.Value.CreatedAtUtc > TimeSpan.FromMinutes(10))
                _pendingAuthSessions.TryRemove(item.Key, out _);
        }

        var sessionId = Guid.NewGuid().ToString("N");
        _pendingAuthSessions[sessionId] = new PendingAuthSession(
            login,
            password,
            cookieHeader,
            browserCookies.ToArray(),
            now);

        return sessionId;
    }

    private static IReadOnlyList<MailRuAuthBrowserCookie> ReadResponseCookies(
        HttpResponseMessage response,
        Uri requestUri)
    {
        if (!response.Headers.TryGetValues("Set-Cookie", out var values))
            return Array.Empty<MailRuAuthBrowserCookie>();

        var result = new List<MailRuAuthBrowserCookie>();

        foreach (var header in values)
        {
            if (string.IsNullOrWhiteSpace(header))
                continue;

            var parts = header.Split(';');
            if (parts.Length == 0)
                continue;

            var first = parts[0].Trim();
            var separator = first.IndexOf('=');
            if (separator <= 0)
                continue;

            var name = first[..separator].Trim();
            var value = first[(separator + 1)..].Trim();
            if (name.Length == 0)
                continue;

            var domain = requestUri.Host;
            var path = "/";
            var secure = false;
            var httpOnly = false;

            for (var index = 1; index < parts.Length; index++)
            {
                var attribute = parts[index].Trim();
                if (attribute.Equals("Secure", StringComparison.OrdinalIgnoreCase))
                {
                    secure = true;
                    continue;
                }

                if (attribute.Equals("HttpOnly", StringComparison.OrdinalIgnoreCase))
                {
                    httpOnly = true;
                    continue;
                }

                var attributeSeparator = attribute.IndexOf('=');
                if (attributeSeparator <= 0)
                    continue;

                var attributeName = attribute[..attributeSeparator].Trim();
                var attributeValue = attribute[(attributeSeparator + 1)..].Trim();

                if (attributeName.Equals("Domain", StringComparison.OrdinalIgnoreCase) &&
                    !string.IsNullOrWhiteSpace(attributeValue))
                {
                    domain = attributeValue;
                }
                else if (attributeName.Equals("Path", StringComparison.OrdinalIgnoreCase) &&
                         !string.IsNullOrWhiteSpace(attributeValue))
                {
                    path = attributeValue;
                }
            }

            result.Add(new MailRuAuthBrowserCookie(
                name,
                value,
                domain,
                path,
                secure,
                httpOnly));
        }

        return result;
    }

    private static string MergeCookieHeader(
        string? currentCookieHeader,
        HttpResponseMessage response)
    {
        var cookies = new Dictionary<string, string>(
            StringComparer.OrdinalIgnoreCase);

        static void AddCookiePair(
            Dictionary<string, string> target,
            string? pair)
        {
            if (string.IsNullOrWhiteSpace(pair))
                return;

            var separator = pair.IndexOf('=');
            if (separator <= 0)
                return;

            var name = pair[..separator].Trim();
            var value = pair[(separator + 1)..].Trim();
            if (name.Length == 0)
                return;

            target[name] = value;
        }

        if (!string.IsNullOrWhiteSpace(currentCookieHeader))
        {
            foreach (var part in currentCookieHeader.Split(';'))
                AddCookiePair(cookies, part);
        }

        if (response.Headers.TryGetValues("Set-Cookie", out var setCookieValues))
        {
            foreach (var setCookie in setCookieValues)
            {
                var firstPart = setCookie.Split(';', 2)[0];
                AddCookiePair(cookies, firstPart);
            }
        }

        return string.Join(
            "; ",
            cookies.Select(pair => pair.Key + "=" + pair.Value));
    }

    private Task<HttpResponseMessage> SendSerializedAsync(
        HttpRequestMessage request,
        CancellationToken cancellationToken) =>
        SendSerializedAsync(request, HttpCompletionOption.ResponseContentRead, cancellationToken);

    private async Task<HttpResponseMessage> SendSerializedAsync(
        HttpRequestMessage request,
        HttpCompletionOption completionOption,
        CancellationToken cancellationToken)
    {
        var requestUri = request.RequestUri
            ?? throw new MailRuProtocolException("Request URI is missing.");

        if (!IsAllowedRuntimeHost(requestUri.Host))
        {
            throw new MailRuProtocolException(
                $"Mail.ru host policy blocked request to host '{requestUri.Host}'.");
        }

        await _requestGate.WaitAsync(cancellationToken).ConfigureAwait(false);
        try
        {
            return await _http.SendAsync(
                request,
                completionOption,
                cancellationToken).ConfigureAwait(false);
        }
        finally
        {
            _requestGate.Release();
        }
    }

    private HttpRequestMessage CreateRequest(HttpMethod method, Uri uri)
    {
        var request = new HttpRequestMessage(method, uri);
        request.Headers.TryAddWithoutValidation("User-Agent", _options.UserAgent);
        return request;
    }

    private Uri BuildUri(
        string relativePath,
        IReadOnlyDictionary<string, string?> query) =>
        BuildAbsoluteUri(new Uri(_options.BaseUri, relativePath).ToString(), query);

    private static Uri BuildAbsoluteUri(
        string absoluteUri,
        IReadOnlyDictionary<string, string?> query)
    {
        var builder = new UriBuilder(absoluteUri);
        builder.Query = string.Join("&", query
            .Where(pair => pair.Value is not null)
            .Select(pair =>
                $"{Uri.EscapeDataString(pair.Key)}={Uri.EscapeDataString(pair.Value!)}"));

        return builder.Uri;
    }

    private static bool IsAllowedRuntimeHost(string host) =>
        MailRuEndpointCatalog.IsRuntimeHostAllowed(host);

    private static MailRuAuthState ClassifyContinueValue(string? value)
    {
        if (string.IsNullOrWhiteSpace(value))
            return MailRuAuthState.Unknown;

        if (value.Contains("recaptcha", StringComparison.OrdinalIgnoreCase))
            return MailRuAuthState.ReCaptcha;

        if (value.Contains("captcha", StringComparison.OrdinalIgnoreCase))
            return MailRuAuthState.Captcha;

        if (value.Contains("2fa", StringComparison.OrdinalIgnoreCase) ||
            value.Contains("twofactor", StringComparison.OrdinalIgnoreCase) ||
            value.Contains("two_factor", StringComparison.OrdinalIgnoreCase) ||
            value.Contains("otp", StringComparison.OrdinalIgnoreCase))
        {
            return MailRuAuthState.TwoFactor;
        }

        return MailRuAuthState.Unknown;
    }

    private static MailRuAuthState ClassifyAuthStatusValue(string? status)
    {
        if (string.IsNullOrWhiteSpace(status))
            return MailRuAuthState.Unknown;

        if (status.Contains("recaptcha", StringComparison.OrdinalIgnoreCase))
            return MailRuAuthState.ReCaptcha;

        if (status.Contains("captcha", StringComparison.OrdinalIgnoreCase))
            return MailRuAuthState.Captcha;

        if (status.Contains("two", StringComparison.OrdinalIgnoreCase) &&
            status.Contains("factor", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("2fa", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("otp", StringComparison.OrdinalIgnoreCase))
        {
            return MailRuAuthState.TwoFactor;
        }

        if (status.Contains("block", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("lock", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("suspend", StringComparison.OrdinalIgnoreCase))
        {
            return MailRuAuthState.Blocked;
        }

        if (status.Contains("verify", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("verification", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("confirm", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("continue", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("challenge", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("recovery", StringComparison.OrdinalIgnoreCase))
        {
            return MailRuAuthState.RecoveryRequired;
        }

        if (status.Contains("invalid", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("password", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("credential", StringComparison.OrdinalIgnoreCase))
        {
            return MailRuAuthState.InvalidCredentials;
        }

        return MailRuAuthState.Unknown;
    }

    private static MailRuAuthChallenge BuildContinueChallenge(
        MailRuAuthState state,
        string continueValue,
        Uri baseUri,
        string sessionId)
    {
        var kind = state switch
        {
            MailRuAuthState.ReCaptcha => MailRuChallengeKind.ReCaptcha,
            MailRuAuthState.Captcha => MailRuChallengeKind.Captcha,
            MailRuAuthState.TwoFactor => MailRuChallengeKind.TwoFactor,
            _ => MailRuChallengeKind.AdditionalVerification
        };

        return new MailRuAuthChallenge(
            kind,
            sessionId,
            NormalizeHttpsContinueUrl(continueValue, baseUri),
            null,
            null,
            "auth_continue_field_detected");
    }

    private static string? NormalizeHttpsContinueUrl(
        string? value,
        Uri baseUri)
    {
        if (string.IsNullOrWhiteSpace(value))
            return null;

        value = value.Trim();

        Uri? uri = null;
        if (value.StartsWith("//", StringComparison.Ordinal))
        {
            Uri.TryCreate("https:" + value, UriKind.Absolute, out uri);
        }
        else if (Uri.TryCreate(value, UriKind.Absolute, out var absolute))
        {
            uri = absolute;
        }
        else if (Uri.TryCreate(baseUri, value, out var relative))
        {
            uri = relative;
        }

        if (uri is null ||
            uri.Scheme != Uri.UriSchemeHttps)
        {
            return null;
        }

        var builder = new UriBuilder(uri);
        var query = builder.Query.TrimStart('?');
        var hasClient = query
            .Split('&', StringSplitOptions.RemoveEmptyEntries)
            .Any(item => item.StartsWith("client=", StringComparison.OrdinalIgnoreCase));

        if (!hasClient)
        {
            builder.Query = string.IsNullOrEmpty(query)
                ? "client=mobile.app"
                : query + "&client=mobile.app";
        }

        return builder.Uri.ToString();
    }

    private static string BuildNoTokenDiagnostic(
        JsonElement root,
        string? status,
        string? continueValue)
    {
        return BuildJsonShapeDiagnostic(root) +
               $"; status-class={ClassifyStatusDiagnostic(status)}" +
               $"; continue-kind={DescribeContinueValue(continueValue)}";
    }

    private static string ClassifyStatusDiagnostic(string? status)
    {
        if (string.IsNullOrWhiteSpace(status))
            return "none";

        if (status.All(char.IsDigit))
            return "numeric";

        if (status.Contains("captcha", StringComparison.OrdinalIgnoreCase))
            return "captcha-like";

        if (status.Contains("verify", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("confirm", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("continue", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("challenge", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("recovery", StringComparison.OrdinalIgnoreCase))
        {
            return "verification-like";
        }

        if (status.Contains("ok", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("success", StringComparison.OrdinalIgnoreCase))
        {
            return "success-like";
        }

        if (status.Contains("error", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("fail", StringComparison.OrdinalIgnoreCase) ||
            status.Contains("invalid", StringComparison.OrdinalIgnoreCase))
        {
            return "error-like";
        }

        return "other-string";
    }

    private static string DescribeContinueValue(string? value)
    {
        if (string.IsNullOrWhiteSpace(value))
            return "none";

        value = value.Trim();

        if (value.StartsWith("/", StringComparison.Ordinal) &&
            !value.StartsWith("//", StringComparison.Ordinal))
        {
            return "relative-path";
        }

        if (value.StartsWith("//", StringComparison.Ordinal) &&
            Uri.TryCreate("https:" + value, UriKind.Absolute, out var protocolRelative))
        {
            return IsMailRuHost(protocolRelative.Host)
                ? $"protocol-relative-mailru:{protocolRelative.Host}"
                : $"protocol-relative-external:{protocolRelative.Host}";
        }

        if (Uri.TryCreate(value, UriKind.Absolute, out var absolute))
        {
            return IsMailRuHost(absolute.Host)
                ? $"absolute-mailru:{absolute.Host}"
                : $"absolute-external:{absolute.Host}";
        }

        return "opaque-string";
    }

    private static bool IsMailRuHost(string host) =>
        host.Equals("mail.ru", StringComparison.OrdinalIgnoreCase) ||
        host.EndsWith(".mail.ru", StringComparison.OrdinalIgnoreCase);

    private static string? FindStringByNamesIgnoreCase(
        JsonElement root,
        params string[] names)
    {
        foreach (var name in names)
        {
            if (TryFindStringIgnoreCase(root, name, out var value) &&
                !string.IsNullOrWhiteSpace(value))
            {
                return value;
            }
        }

        return null;
    }

    private static bool TryFindStringIgnoreCase(
        JsonElement element,
        string name,
        out string? value)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            foreach (var property in element.EnumerateObject())
            {
                if (property.Name.Equals(name, StringComparison.OrdinalIgnoreCase) &&
                    property.Value.ValueKind == JsonValueKind.String)
                {
                    value = property.Value.GetString();
                    return true;
                }

                if (TryFindStringIgnoreCase(property.Value, name, out value))
                    return true;
            }
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
            {
                if (TryFindStringIgnoreCase(item, name, out value))
                    return true;
            }
        }

        value = null;
        return false;
    }

    private static MailRuAuthState ClassifyAuthState(
        JsonElement root,
        string error)
    {
        if (LooksLikeInvalidCredentials(error, string.Empty))
            return MailRuAuthState.InvalidCredentials;

        if (ContainsPropertyFragment(root, "recaptcha"))
            return MailRuAuthState.ReCaptcha;

        if (ContainsPropertyFragment(root, "captcha"))
            return MailRuAuthState.Captcha;

        if (ContainsAnyPropertyFragment(root, "two_factor", "twofactor", "2fa", "otp"))
            return MailRuAuthState.TwoFactor;

        if (ContainsAnyPropertyFragment(root, "blocked", "lockout", "suspended"))
            return MailRuAuthState.Blocked;

        if (ContainsAnyPropertyFragment(
                root,
                "recovery",
                "verification",
                "verify",
                "challenge",
                "confirmation",
                "confirm"))
        {
            return MailRuAuthState.RecoveryRequired;
        }

        return MailRuAuthState.Unknown;
    }

    private static MailRuAuthChallenge? TryBuildAuthChallenge(
        JsonElement root,
        MailRuAuthState state,
        string location)
    {
        if (state is not (
            MailRuAuthState.Captcha or
            MailRuAuthState.ReCaptcha or
            MailRuAuthState.TwoFactor))
        {
            return null;
        }

        var kind = state switch
        {
            MailRuAuthState.ReCaptcha => MailRuChallengeKind.ReCaptcha,
            MailRuAuthState.TwoFactor => MailRuChallengeKind.TwoFactor,
            _ => MailRuChallengeKind.Captcha
        };

        var sessionId =
            FindStringByNames(
                root,
                "captcha_sid",
                "session_id",
                "sessionId",
                "challenge_id",
                "challengeId",
                "sid") ??
            string.Empty;

        var url =
            FindSafeHttpsUrlByNames(
                root,
                "captcha_url",
                "captchaUrl",
                "verification_url",
                "verificationUrl",
                "challenge_url",
                "challengeUrl",
                "url",
                "link") ??
            NormalizeSafeHttpsUrl(location);

        var siteKey = FindStringByNames(
            root,
            "sitekey",
            "site_key",
            "siteKey",
            "recaptcha_sitekey",
            "recaptcha_site_key");

        var image = FindStringByNames(
            root,
            "captcha_image_base64",
            "captchaImageBase64",
            "image_base64");

        return new MailRuAuthChallenge(
            kind,
            sessionId,
            url,
            siteKey,
            image,
            "interactive_verification_detected");
    }

    private static MailRuAuthChallenge? BuildRedirectChallenge(
        MailRuAuthState state,
        string location,
        string sessionId)
    {
        var url = NormalizeSafeHttpsUrl(location);
        if (url is null)
            return null;

        return new MailRuAuthChallenge(
            state == MailRuAuthState.ReCaptcha
                ? MailRuChallengeKind.ReCaptcha
                : MailRuChallengeKind.Captcha,
            sessionId,
            url,
            null,
            null,
            "interactive_verification_redirect");
    }

    private static string BuildJsonShapeDiagnostic(JsonElement root)
    {
        var paths = new List<string>(80);
        CollectJsonShape(root, "$", paths, 0);

        return paths.Count == 0
            ? "json-shape: empty"
            : "json-shape: " + string.Join("; ", paths);
    }

    private static void CollectJsonShape(
        JsonElement element,
        string path,
        List<string> output,
        int depth)
    {
        if (output.Count >= 80 || depth > 7)
            return;

        switch (element.ValueKind)
        {
            case JsonValueKind.Object:
                foreach (var property in element.EnumerateObject())
                {
                    if (output.Count >= 80)
                        break;

                    var childPath = path + "." + property.Name;
                    output.Add(childPath + ":" + JsonKindName(property.Value.ValueKind));
                    CollectJsonShape(property.Value, childPath, output, depth + 1);
                }
                break;

            case JsonValueKind.Array:
                var index = 0;
                foreach (var item in element.EnumerateArray())
                {
                    if (output.Count >= 80 || index >= 3)
                        break;

                    CollectJsonShape(item, path + "[]", output, depth + 1);
                    index++;
                }
                break;
        }
    }

    private static string JsonKindName(JsonValueKind kind) => kind switch
    {
        JsonValueKind.Object => "object",
        JsonValueKind.Array => "array",
        JsonValueKind.String => "string",
        JsonValueKind.Number => "number",
        JsonValueKind.True or JsonValueKind.False => "boolean",
        JsonValueKind.Null => "null",
        _ => "other"
    };

    private static bool ContainsAnyPropertyFragment(
        JsonElement root,
        params string[] fragments) =>
        fragments.Any(fragment => ContainsPropertyFragment(root, fragment));

    private static bool ContainsPropertyFragment(
        JsonElement element,
        string fragment)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            foreach (var property in element.EnumerateObject())
            {
                if (property.Name.Contains(fragment, StringComparison.OrdinalIgnoreCase) ||
                    ContainsPropertyFragment(property.Value, fragment))
                {
                    return true;
                }
            }
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
            {
                if (ContainsPropertyFragment(item, fragment))
                    return true;
            }
        }

        return false;
    }

    private static string? FindStringByNames(
        JsonElement root,
        params string[] names)
    {
        foreach (var name in names)
        {
            if (TryFindString(root, name, out var value) &&
                !string.IsNullOrWhiteSpace(value))
            {
                return value;
            }
        }

        return null;
    }

    private static string? FindSafeHttpsUrlByNames(
        JsonElement root,
        params string[] names)
    {
        foreach (var name in names)
        {
            if (!TryFindString(root, name, out var value) ||
                string.IsNullOrWhiteSpace(value))
            {
                continue;
            }

            var safe = NormalizeSafeHttpsUrl(value);
            if (safe is not null)
                return safe;
        }

        return null;
    }

    private static string? NormalizeSafeHttpsUrl(string? value)
    {
        if (string.IsNullOrWhiteSpace(value) ||
            !Uri.TryCreate(value, UriKind.Absolute, out var uri) ||
            uri.Scheme != Uri.UriSchemeHttps)
        {
            return null;
        }

        return uri.ToString();
    }

    private static string SafeHost(string? value)
    {
        if (string.IsNullOrWhiteSpace(value) ||
            !Uri.TryCreate(value, UriKind.Absolute, out var uri))
        {
            return "none";
        }

        return uri.Host;
    }

    private static bool LooksLikeCaptcha(string payload, string location) =>
        payload.Contains("captcha", StringComparison.OrdinalIgnoreCase) ||
        payload.Contains("recaptcha", StringComparison.OrdinalIgnoreCase) ||
        location.Contains("captcha", StringComparison.OrdinalIgnoreCase) ||
        location.Contains("recaptcha", StringComparison.OrdinalIgnoreCase);

    private static bool LooksLikeInvalidCredentials(string error, string payload) =>
        error.Contains("password", StringComparison.OrdinalIgnoreCase) ||
        error.Contains("credential", StringComparison.OrdinalIgnoreCase) ||
        error.Contains("invalid", StringComparison.OrdinalIgnoreCase) ||
        payload.Contains("invalid password", StringComparison.OrdinalIgnoreCase) ||
        payload.Contains("invalid login", StringComparison.OrdinalIgnoreCase);

    private static void RequireToken(string token)
    {
        if (string.IsNullOrWhiteSpace(token))
            throw new ArgumentException("Access token is required.", nameof(token));
    }

    private static string BuildSimpleHtml(string text)
    {
        var encoded = WebUtility.HtmlEncode(text ?? string.Empty)
            .Replace("\r\n", "<br> ", StringComparison.Ordinal)
            .Replace("\n", "<br> ", StringComparison.Ordinal);

        return $"<p style='margin-top: 0px;' dir=\"ltr\">{encoded}</p>\n";
    }

    private static bool HasStatus200(string payload)
    {
        try
        {
            using var document = JsonDocument.Parse(payload);

            if (TryFindInt(document.RootElement, "status", out var status))
                return status == 200;

            if (TryFindString(document.RootElement, "status", out var statusText))
                return string.Equals(statusText, "OK", StringComparison.OrdinalIgnoreCase) ||
                       string.Equals(statusText, "200", StringComparison.OrdinalIgnoreCase);
        }
        catch (JsonException)
        {
        }

        return payload.Contains("\"status\":200", StringComparison.OrdinalIgnoreCase) ||
               payload.Contains("\"status\": 200", StringComparison.OrdinalIgnoreCase) ||
               payload.Contains("\"status\":\"OK\"", StringComparison.OrdinalIgnoreCase);
    }

    private static bool TryFindAttachId(JsonElement element, out string? id)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            foreach (var property in element.EnumerateObject())
            {
                if (property.NameEquals("attach") &&
                    property.Value.ValueKind == JsonValueKind.Object &&
                    property.Value.TryGetProperty("id", out var idElement) &&
                    idElement.ValueKind == JsonValueKind.String)
                {
                    id = idElement.GetString();
                    return true;
                }

                if (TryFindAttachId(property.Value, out id))
                    return true;
            }
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
            {
                if (TryFindAttachId(item, out id))
                    return true;
            }
        }

        id = null;
        return false;
    }

    private static bool TryFindString(
        JsonElement element,
        string name,
        out string? value)
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

    private static bool TryFindInt(JsonElement element, string name, out int value)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            foreach (var property in element.EnumerateObject())
            {
                if (property.NameEquals(name) &&
                    property.Value.ValueKind == JsonValueKind.Number &&
                    property.Value.TryGetInt32(out value))
                {
                    return true;
                }

                if (TryFindInt(property.Value, name, out value))
                    return true;
            }
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
            {
                if (TryFindInt(item, name, out value))
                    return true;
            }
        }

        value = default;
        return false;
    }

    public void Dispose()
    {
        _pendingAuthSessions.Clear();
        _requestGate.Dispose();

        if (_ownsHttpClient)
            _http.Dispose();
    }
}
