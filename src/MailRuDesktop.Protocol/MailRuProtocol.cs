using System.Globalization;
using System.Net;
using System.Net.Http.Headers;
using System.Net.Http.Json;
using System.Text.Json;

namespace MailRuDesktop.Protocol;

public enum EndpointEvidence
{
    VerifiedLocal,
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
        new("auth.web-session", "POST", "aj-https.mail.ru", "/cgi-bin/auth", EndpointEvidence.ExternalConfirmed, "Hackus cookie-session authentication fallback"),
        new("auth.challenge.copper", "GET/POST", "account.mail.ru", "/api/v1/user/copper", EndpointEvidence.ExternalConfirmed, "Classify or submit account challenge"),
        new("auth.captcha.image", "GET", "c.mail.ru", "/c/6", EndpointEvidence.ExternalConfirmed, "Legacy image CAPTCHA payload"),
        new("threads.status.smart", "GET", "aj-https.mail.ru", "/api/v1/m/threads/status/smart", EndpointEvidence.VerifiedLocal, "Folder/thread status"),
        new("messages.attach.add", "POST", "aj-https.mail.ru", "/api/v1/messages/attaches/add", EndpointEvidence.VerifiedLocal, "Upload attachment"),
        new("messages.send", "POST", "aj-https.mail.ru", "/api/v1/messages/send", EndpointEvidence.VerifiedLocal, "Send message"),
        new("messages.schedule", "POST", "aj-https.mail.ru", "/api/v1/messages/schedule", EndpointEvidence.VerifiedLocal, "Server-side scheduled send"),

        new("touch.tokens", "GET", "touch.mail.ru", "/api/v1/tokens", EndpointEvidence.ExternalConfirmed, "Acquire search/API token"),
        new("touch.search", "GET", "touch.mail.ru", "/cgi-bin/gosearch", EndpointEvidence.ExternalConfirmed, "Server-side search"),
        new("touch.message", "GET", "touch.mail.ru", "/api/v1/messages/message", EndpointEvidence.ExternalConfirmed, "Fetch full message"),
        new("touch.messages.move", "POST", "touch.mail.ru", "/api/v1 -> /messages/move", EndpointEvidence.ExternalConfirmed, "Move messages"),
        new("touch.messages.remove", "POST", "touch.mail.ru", "/api/v1 -> /messages/remove", EndpointEvidence.ExternalConfirmed, "Remove messages"),
        new("touch.addressbook.smart", "POST", "touch.mail.ru", "/api/v1 -> /k8s/ab/smart", EndpointEvidence.ExternalConfirmed, "Address-book lookup"),

        new("web.threads.golang", "GET", "e.mail.ru", "/api/v1/threads/status/golang", EndpointEvidence.ExternalConfirmed, "Thread listing"),
        new("web.threads.thread", "GET", "e.mail.ru", "/api/v1/threads/thread", EndpointEvidence.ExternalConfirmed, "Fetch thread"),
        new("web.messages.message", "GET", "e.mail.ru", "/api/v1/messages/message", EndpointEvidence.ExternalConfirmed, "Fetch full message without touch token"),
        new("web.messages.search", "GET/POST", "e.mail.ru", "/api/v1/messages/search", EndpointEvidence.ExternalConfirmed, "Message search/filter"),
        new("web.messages.move", "POST", "e.mail.ru", "/api/v1/messages/move", EndpointEvidence.ExternalConfirmed, "Move messages using web session"),
        new("web.messages.delete", "POST", "e.mail.ru", "/api/v1/messages/delete", EndpointEvidence.ExternalConfirmed, "Delete messages using web session"),
        new("web.folders.add", "POST", "e.mail.ru", "/api/v1/folders/add", EndpointEvidence.ExternalConfirmed, "Create folder"),
        new("web.folders.clear", "POST", "e.mail.ru", "/api/v1/folders/clear", EndpointEvidence.ExternalConfirmed, "Clear folder"),
        new("web.k8s.send", "POST", "e.mail.ru", "/api/v1/k8s/messages/send", EndpointEvidence.ExternalConfirmed, "Richer compose/send API"),

        new("candidate.messages.flags", "POST", "e.mail.ru", "/api/v1/messages/flags", EndpointEvidence.Candidate, "Read/star flags"),
        new("candidate.messages.list", "POST", "e.mail.ru", "/api/v1/messages/list", EndpointEvidence.Candidate, "Message listing")
    ];
}

public sealed record MailRuClientOptions
{
    public Uri BaseUri { get; init; } = new("https://aj-https.mail.ru");
    public string UserAgent { get; init; } = MailRuFixedProfile.UserAgent;
    public TimeSpan Timeout { get; init; } = TimeSpan.FromSeconds(30);
}

public sealed record MailRuAuthResult(
    bool Success,
    string? AccessToken,
    string? RefreshToken,
    string? ErrorCode)
{
    public MailRuAuthState State { get; init; } = MailRuAuthState.Unknown;
    public string? WebToken { get; init; }
    public string? SearchToken { get; init; }
    public string? WebCookieHeader { get; init; }
    public string? TouchCookieHeader { get; init; }
    public MailRuAuthChallenge? Challenge { get; init; }
    public string? DiagnosticReason { get; init; }

    public bool HasMailboxCredential =>
        !string.IsNullOrWhiteSpace(AccessToken) ||
        !string.IsNullOrWhiteSpace(WebToken) ||
        !string.IsNullOrWhiteSpace(SearchToken);

    public static MailRuAuthResult Failed(
        string code,
        MailRuAuthState state = MailRuAuthState.Unknown,
        string? diagnosticReason = null,
        MailRuAuthChallenge? challenge = null) =>
        new(false, null, null, code)
        {
            State = state,
            DiagnosticReason = diagnosticReason,
            Challenge = challenge
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
    string? MessageId = null);

public sealed record MailRuCommandResult(bool Success, string RawResponse);

public sealed class MailRuProtocolException : Exception
{
    public MailRuProtocolException(string message) : base(message) { }
}

public sealed class MailRuClient : IDisposable
{
    public const string KnownWorkingMessageId = "RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR";

    private readonly MailRuClientOptions _options;
    private readonly HttpClient _http;
    private readonly bool _ownsHttpClient;

    public MailRuClient(MailRuClientOptions? options = null, HttpClient? httpClient = null)
    {
        _options = options ?? new MailRuClientOptions();
        _ownsHttpClient = httpClient is null;
        _http = httpClient ?? new HttpClient();
        if (_ownsHttpClient)
        {
            _http.Timeout = _options.Timeout;
        }
    }

    public async Task<MailRuAuthResult> AuthenticateAsync(
        string login,
        string password,
        CancellationToken cancellationToken = default)
    {
        if (string.IsNullOrWhiteSpace(login))
            throw new ArgumentException("Login is required.", nameof(login));
        if (string.IsNullOrEmpty(password))
            throw new ArgumentException("Password is required.", nameof(password));

        // Authentication/challenge intentionally starts exactly from the
        // Hackus-style Reset() -> CreateSession() path. There is no preliminary
        // mobile/OAuth probe here: it changes the request sequence and can
        // provoke a different Mail.ru challenge state before the verified flow.
        var web = await MailRuWebSessionAuthenticator.AuthenticateAsync(
            login,
            password,
            _options.Timeout,
            cancellationToken).ConfigureAwait(false);

        if (web.Success)
        {
            return new MailRuAuthResult(true, null, null, null)
            {
                State = MailRuAuthState.Success,
                WebToken = web.WebToken,
                SearchToken = web.SearchToken,
                WebCookieHeader = web.WebCookieHeader,
                TouchCookieHeader = web.TouchCookieHeader,
                DiagnosticReason = web.DiagnosticReason
            };
        }

        if (web.Challenge is null)
            MailRuWebSessionAuthenticator.ReleaseSession(web.PendingSessionId);

        return MailRuAuthResult.Failed(
            web.ErrorCode ?? "unknown_auth_result",
            web.State,
            web.DiagnosticReason,
            web.Challenge);
    }

    public async Task<MailRuAuthResult> CompleteChallengeAsync(
        string login,
        MailRuChallengeCompletion completion,
        CancellationToken cancellationToken = default)
    {
        if (string.IsNullOrWhiteSpace(login))
            throw new ArgumentException("Login is required.", nameof(login));

        var web = await MailRuWebSessionAuthenticator.CompleteChallengeAsync(
            login,
            completion,
            cancellationToken).ConfigureAwait(false);

        if (!web.Success)
        {
            return MailRuAuthResult.Failed(
                web.ErrorCode ?? "web_session_token_missing",
                web.State,
                web.DiagnosticReason,
                web.Challenge);
        }

        return new MailRuAuthResult(true, web.AccessToken, web.RefreshToken, null)
        {
            State = MailRuAuthState.Success,
            WebToken = web.WebToken,
            SearchToken = web.SearchToken,
            WebCookieHeader = web.WebCookieHeader,
            TouchCookieHeader = web.TouchCookieHeader,
            DiagnosticReason = web.DiagnosticReason
        };
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
        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Thread status request failed with HTTP {(int)response.StatusCode}.");

        return payload;
    }

    public async Task<string> GetFolderThreadsWebAsync(
        string webToken,
        string email,
        string? cookieHeader,
        int folderId,
        int offset = 0,
        int limit = 200,
        CancellationToken cancellationToken = default)
    {
        RequireToken(webToken);
        if (string.IsNullOrWhiteSpace(email))
            throw new ArgumentException("Email is required.", nameof(email));
        if (offset < 0) throw new ArgumentOutOfRangeException(nameof(offset));
        if (limit is < 1 or > 1000) throw new ArgumentOutOfRangeException(nameof(limit));

        var uri = BuildAbsoluteUri(
            new Uri("https://e.mail.ru/api/v1/threads/status/golang"),
            new Dictionary<string, string?>
            {
                ["ajax_call"] = "1",
                ["x-email"] = email,
                ["email"] = email,
                ["sort"] = "{\"type\":\"date\",\"order\":\"desc\"}",
                ["offset"] = offset.ToString(CultureInfo.InvariantCulture),
                ["limit"] = limit.ToString(CultureInfo.InvariantCulture),
                ["folder"] = folderId.ToString(CultureInfo.InvariantCulture),
                ["htmlencoded"] = "false",
                ["last_modified"] = "-1",
                ["filters"] = "{}",
                ["nolog"] = "0",
                ["sortby"] = "D",
                ["api"] = "1",
                ["token"] = webToken
            });

        using var request = CreateBrowserRequest(HttpMethod.Get, uri, cookieHeader);
        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Web thread status request failed with HTTP {(int)response.StatusCode}.");

        return payload;
    }

    public async Task<string> GetFullMessageWebAsync(
        string webToken,
        string email,
        string messageId,
        string? cookieHeader,
        int folderId = 0,
        CancellationToken cancellationToken = default)
    {
        RequireToken(webToken);
        if (string.IsNullOrWhiteSpace(email))
            throw new ArgumentException("Email is required.", nameof(email));
        if (string.IsNullOrWhiteSpace(messageId))
            throw new ArgumentException("Message id is required.", nameof(messageId));

        // This is the same web-family route used by older working Mail.ru clients.
        // read=0 + mark_read=false deliberately requests the body without mutating
        // the unread state.
        var messageUri = BuildAbsoluteUri(
            new Uri("https://e.mail.ru/api/v1/messages/message"),
            new Dictionary<string, string?>
            {
                ["ajax_call"] = "1",
                ["x-email"] = email,
                ["email"] = email,
                ["htmlencoded"] = "false",
                ["multi_msg_prev"] = "0",
                ["multi_msg_past"] = "0",
                ["sortby"] = "D",
                ["NewAttachViewer"] = "1",
                ["AvStatusBar"] = "1",
                ["let_body_type"] = "let_body_plain",
                ["log"] = "0",
                ["bulk_show_images"] = "0",
                ["folder"] = folderId.ToString(CultureInfo.InvariantCulture),
                ["wrap_body"] = "0",
                ["id"] = messageId,
                ["NoMSG"] = "true",
                ["read"] = "0",
                ["mark_read"] = "false",
                ["api"] = "1",
                ["token"] = webToken
            });

        using (var request = CreateBrowserRequest(HttpMethod.Get, messageUri, cookieHeader))
        using (var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false))
        {
            var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);
            if (response.IsSuccessStatusCode && !string.IsNullOrWhiteSpace(payload))
                return payload;
        }

        // Some web generations expose the same data through threads/thread.
        var threadUri = BuildAbsoluteUri(
            new Uri("https://e.mail.ru/api/v1/threads/thread"),
            new Dictionary<string, string?>
            {
                ["ajax_call"] = "1",
                ["offset"] = "0",
                ["limit"] = "50",
                ["htmlencoded"] = "false",
                ["cache"] = "false",
                ["api"] = "1",
                ["token"] = webToken,
                ["id"] = messageId,
                ["email"] = email,
                ["x-email"] = email
            });

        using var fallbackRequest = CreateBrowserRequest(HttpMethod.Get, threadUri, cookieHeader);
        using var fallbackResponse = await _http.SendAsync(fallbackRequest, cancellationToken).ConfigureAwait(false);
        var fallbackPayload = await fallbackResponse.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!fallbackResponse.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Web full-message request failed with HTTP {(int)fallbackResponse.StatusCode}.");

        return fallbackPayload;
    }

    public async Task<MailRuCommandResult> MoveWebMessagesToFolderAsync(
        string webToken,
        string email,
        IReadOnlyCollection<string> ids,
        int destinationFolderId,
        string? cookieHeader,
        CancellationToken cancellationToken = default)
    {
        RequireToken(webToken);
        if (string.IsNullOrWhiteSpace(email))
            throw new ArgumentException("Email is required.", nameof(email));
        if (ids is null || ids.Count == 0)
            throw new ArgumentException("At least one message id is required.", nameof(ids));

        var uri = BuildAbsoluteUri(
            new Uri("https://e.mail.ru/api/v1/messages/move"),
            new Dictionary<string, string?>
            {
                ["email"] = email,
                ["token"] = webToken
            });

        using var request = CreateBrowserRequest(HttpMethod.Post, uri, cookieHeader);
        request.Content = JsonContent.Create(new
        {
            email_ids = ids,
            folder_id = destinationFolderId.ToString(CultureInfo.InvariantCulture)
        });

        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);
        return new MailRuCommandResult(
            response.IsSuccessStatusCode && (HasStatus200(payload) || string.IsNullOrWhiteSpace(payload)),
            payload);
    }

    public async Task<MailRuCommandResult> DeleteWebMessagesAsync(
        string webToken,
        string email,
        IReadOnlyCollection<string> ids,
        string? cookieHeader,
        CancellationToken cancellationToken = default)
    {
        RequireToken(webToken);
        if (string.IsNullOrWhiteSpace(email))
            throw new ArgumentException("Email is required.", nameof(email));
        if (ids is null || ids.Count == 0)
            throw new ArgumentException("At least one message id is required.", nameof(ids));

        var uri = BuildAbsoluteUri(
            new Uri("https://e.mail.ru/api/v1/messages/delete"),
            new Dictionary<string, string?>
            {
                ["email"] = email,
                ["token"] = webToken
            });

        using var request = CreateBrowserRequest(HttpMethod.Post, uri, cookieHeader);
        request.Content = JsonContent.Create(new { email_ids = ids });

        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);
        return new MailRuCommandResult(
            response.IsSuccessStatusCode && (HasStatus200(payload) || string.IsNullOrWhiteSpace(payload)),
            payload);
    }

    public async Task<string> GetFullMessageTouchAsync(
        string searchToken,
        string email,
        string messageId,
        string? cookieHeader,
        CancellationToken cancellationToken = default)
    {
        RequireToken(searchToken);
        if (string.IsNullOrWhiteSpace(email))
            throw new ArgumentException("Email is required.", nameof(email));
        if (string.IsNullOrWhiteSpace(messageId))
            throw new ArgumentException("Message id is required.", nameof(messageId));

        var uri = BuildAbsoluteUri(
            new Uri("https://touch.mail.ru/api/v1/messages/message"),
            new Dictionary<string, string?>
            {
                ["id"] = messageId,
                ["email"] = email,
                ["token"] = searchToken
            });

        using var request = CreateBrowserRequest(HttpMethod.Get, uri, cookieHeader);
        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Full-message request failed with HTTP {(int)response.StatusCode}.");

        return payload;
    }

    public async Task<string> SearchTouchAsync(
        string searchToken,
        string email,
        string? cookieHeader,
        string? query = null,
        string? sender = null,
        string? subject = null,
        bool attachmentsOnly = false,
        int count = 100,
        DateTime? dateFrom = null,
        DateTime? dateTo = null,
        CancellationToken cancellationToken = default)
    {
        RequireToken(searchToken);
        if (string.IsNullOrWhiteSpace(email))
            throw new ArgumentException("Email is required.", nameof(email));
        if (count is < 1 or > 1000)
            throw new ArgumentOutOfRangeException(nameof(count));

        var parameters = new Dictionary<string, string?>
        {
            ["token"] = searchToken,
            ["json"] = "1",
            ["ajax_call"] = "1",
            ["page"] = "1",
            ["q_folder"] = "all",
            ["count"] = count.ToString(CultureInfo.InvariantCulture),
            ["x-email"] = email,
            ["q_query"] = string.IsNullOrWhiteSpace(query) ? null : query,
            ["q_from"] = string.IsNullOrWhiteSpace(sender) ? null : sender,
            ["q_subj"] = string.IsNullOrWhiteSpace(subject) ? null : subject,
            ["q_attach"] = attachmentsOnly ? "1" : null,
            ["ddb"] = dateFrom?.Day.ToString(CultureInfo.InvariantCulture),
            ["dmb"] = dateFrom?.Month.ToString(CultureInfo.InvariantCulture),
            ["dyb"] = dateFrom?.Year.ToString(CultureInfo.InvariantCulture),
            ["dde"] = dateTo?.Day.ToString(CultureInfo.InvariantCulture),
            ["dme"] = dateTo?.Month.ToString(CultureInfo.InvariantCulture),
            ["dye"] = dateTo?.Year.ToString(CultureInfo.InvariantCulture)
        };

        var uri = BuildAbsoluteUri(new Uri("https://touch.mail.ru/cgi-bin/gosearch"), parameters);
        using var request = CreateBrowserRequest(HttpMethod.Get, uri, cookieHeader);
        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Search request failed with HTTP {(int)response.StatusCode}.");

        return payload;
    }

    public async Task<MailRuCommandResult> MoveTouchMessagesToFolderAsync(
        string searchToken,
        string email,
        IReadOnlyCollection<string> ids,
        int destinationFolderId,
        string? cookieHeader,
        CancellationToken cancellationToken = default)
    {
        RequireToken(searchToken);
        if (string.IsNullOrWhiteSpace(email))
            throw new ArgumentException("Email is required.", nameof(email));
        if (ids is null || ids.Count == 0)
            throw new ArgumentException("At least one message id is required.", nameof(ids));

        using var request = CreateBrowserRequest(
            HttpMethod.Post,
            new Uri("https://touch.mail.ru/api/v1"),
            cookieHeader);

        request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
        {
            ["__urlp"] = "/messages/move",
            ["ids"] = JsonSerializer.Serialize(ids),
            ["folder"] = destinationFolderId.ToString(CultureInfo.InvariantCulture),
            ["email"] = email,
            ["htmlencoded"] = "false",
            ["token"] = searchToken
        });

        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);
        return new MailRuCommandResult(response.IsSuccessStatusCode && HasStatus200(payload), payload);
    }

    public async Task<MailRuCommandResult> RemoveTouchMessagesAsync(
        string searchToken,
        string email,
        IReadOnlyCollection<string> ids,
        string? cookieHeader,
        CancellationToken cancellationToken = default)
    {
        RequireToken(searchToken);
        if (string.IsNullOrWhiteSpace(email))
            throw new ArgumentException("Email is required.", nameof(email));
        if (ids is null || ids.Count == 0)
            throw new ArgumentException("At least one message id is required.", nameof(ids));

        using var request = CreateBrowserRequest(
            HttpMethod.Post,
            new Uri("https://touch.mail.ru/api/v1"),
            cookieHeader);

        request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
        {
            ["__urlp"] = "/messages/remove",
            ["ids"] = JsonSerializer.Serialize(ids),
            ["folder"] = "500002",
            ["email"] = email,
            ["htmlencoded"] = "false",
            ["token"] = searchToken
        });

        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);
        return new MailRuCommandResult(response.IsSuccessStatusCode && HasStatus200(payload), payload);
    }

    public async Task<byte[]> DownloadIncomingAttachmentAsync(
        MailRuIncomingAttachment attachment,
        string? cookieHeader,
        CancellationToken cancellationToken = default)
    {
        ArgumentNullException.ThrowIfNull(attachment);

        if (!Uri.TryCreate(attachment.DownloadUrl, UriKind.Absolute, out var uri) ||
            uri.Scheme != Uri.UriSchemeHttps)
        {
            throw new MailRuProtocolException("Attachment download URL is not a valid HTTPS URL.");
        }

        using var request = CreateBrowserRequest(HttpMethod.Get, uri, cookieHeader);
        using var response = await _http.SendAsync(
            request,
            HttpCompletionOption.ResponseHeadersRead,
            cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Attachment download failed with HTTP {(int)response.StatusCode}.");

        return await response.Content.ReadAsByteArrayAsync(cancellationToken).ConfigureAwait(false);
    }

    public Task<MailRuCommandResult> MoveTouchMessagesAsync(
        string searchToken,
        string email,
        IReadOnlyCollection<string> ids,
        string? cookieHeader,
        bool permanentlyDelete = false,
        CancellationToken cancellationToken = default) =>
        permanentlyDelete
            ? RemoveTouchMessagesAsync(searchToken, email, ids, cookieHeader, cancellationToken)
            : MoveTouchMessagesToFolderAsync(searchToken, email, ids, 500002, cookieHeader, cancellationToken);

    public async Task<string> GetContactsTouchAsync(
        string searchToken,
        string email,
        string? cookieHeader,
        CancellationToken cancellationToken = default)
    {
        RequireToken(searchToken);
        if (string.IsNullOrWhiteSpace(email))
            throw new ArgumentException("Email is required.", nameof(email));

        var route =
            "/k8s/ab/smart?fields=[\"emails\"]&filter={\"flags\":{\"has_mailbox\":null}}" +
            "&email=" + Uri.EscapeDataString(email) +
            "&htmlencoded=false&token=" + Uri.EscapeDataString(searchToken);

        using var request = CreateBrowserRequest(
            HttpMethod.Post,
            new Uri("https://touch.mail.ru/api/v1"),
            cookieHeader);
        request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
        {
            ["__urlp"] = route
        });

        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        return await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);
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

        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
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
            // Converted to a stable protocol error below.
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
        request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
        {
            ["attaches"] = attaches,
            ["body"] = body,
            ["correspondents"] = correspondents,
            ["id"] = messageId,
            ["source"] = source,
            ["subject"] = subject,
            ["send_date"] = scheduled ? message.SendDate! : "0",
            ["priority"] = message.Priority.ToString(CultureInfo.InvariantCulture)
        });

        using var response = await _http.SendAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        var success = response.IsSuccessStatusCode && HasStatus200(payload);
        return new MailRuCommandResult(success, payload);
    }

    private HttpRequestMessage CreateRequest(HttpMethod method, Uri uri)
    {
        var request = new HttpRequestMessage(method, uri);
        request.Headers.TryAddWithoutValidation("User-Agent", _options.UserAgent);
        return request;
    }

    private HttpRequestMessage CreateBrowserRequest(
        HttpMethod method,
        Uri uri,
        string? cookieHeader)
    {
        var request = new HttpRequestMessage(method, uri);
        request.Headers.TryAddWithoutValidation("User-Agent", _options.UserAgent);

        if (!string.IsNullOrWhiteSpace(cookieHeader))
            request.Headers.TryAddWithoutValidation("Cookie", cookieHeader);

        return request;
    }

    private static Uri BuildAbsoluteUri(
        Uri baseUri,
        IReadOnlyDictionary<string, string?> query)
    {
        var builder = new UriBuilder(baseUri);
        builder.Query = string.Join("&", query
            .Where(pair => pair.Value is not null)
            .Select(pair => $"{Uri.EscapeDataString(pair.Key)}={Uri.EscapeDataString(pair.Value!)}"));
        return builder.Uri;
    }

    private Uri BuildUri(string relativePath, IReadOnlyDictionary<string, string?> query)
    {
        var builder = new UriBuilder(new Uri(_options.BaseUri, relativePath));
        builder.Query = string.Join("&", query
            .Where(pair => pair.Value is not null)
            .Select(pair => $"{Uri.EscapeDataString(pair.Key)}={Uri.EscapeDataString(pair.Value!)}"));
        return builder.Uri;
    }

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
            return TryFindInt(document.RootElement, "status", out var status) && status == 200;
        }
        catch (JsonException)
        {
            return payload.Contains("\"status\":200", StringComparison.OrdinalIgnoreCase) ||
                   payload.Contains("\"status\": 200", StringComparison.OrdinalIgnoreCase);
        }
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

    private static bool TryFindString(JsonElement element, string name, out string? value)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            foreach (var property in element.EnumerateObject())
            {
                if (property.NameEquals(name) && property.Value.ValueKind == JsonValueKind.String)
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
        if (_ownsHttpClient)
            _http.Dispose();
    }
}
