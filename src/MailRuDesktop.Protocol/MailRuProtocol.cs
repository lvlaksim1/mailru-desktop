using System.Globalization;
using System.Net;
using System.Net.Http.Headers;
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
        new("threads.status.smart", "GET", "aj-https.mail.ru", "/api/v1/m/threads/status/smart", EndpointEvidence.VerifiedLocal, "Folder/thread status"),
        new("messages.attach.add", "POST", "aj-https.mail.ru", "/api/v1/messages/attaches/add", EndpointEvidence.VerifiedLocal, "Upload attachment"),
        new("messages.send", "POST", "aj-https.mail.ru", "/api/v1/messages/send", EndpointEvidence.VerifiedLocal, "Send message"),
        new("messages.schedule", "POST", "aj-https.mail.ru", "/api/v1/messages/schedule", EndpointEvidence.VerifiedLocal, "Server-side scheduled send")
    ];
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
    // AJ-only mode never populates or consumes these credentials.
    public string? WebToken { get; init; }
    public string? SearchToken { get; init; }
    public string? WebCookieHeader { get; init; }
    public string? TouchCookieHeader { get; init; }
    public MailRuAuthChallenge? Challenge { get; init; }
    public string? DiagnosticReason { get; init; }

    public bool HasMailboxCredential =>
        !string.IsNullOrWhiteSpace(AccessToken);

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
    private readonly SemaphoreSlim _requestGate = new(1, 1);
    private DateTimeOffset _lastRequestAt = DateTimeOffset.MinValue;

    public MailRuClient(MailRuClientOptions? options = null, HttpClient? httpClient = null)
    {
        _options = options ?? new MailRuClientOptions();
        _ownsHttpClient = httpClient is null;

        if (httpClient is null)
        {
            var handler = new HttpClientHandler
            {
                AllowAutoRedirect = false,
                AutomaticDecompression = DecompressionMethods.All
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

    public async Task<MailRuAuthResult> AuthenticateAsync(
        string login,
        string password,
        CancellationToken cancellationToken = default)
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
        request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
        {
            ["Password"] = password,
            ["Login"] = login,
            ["oauth2"] = "1",
            ["useragent"] = "android",
            ["mobile"] = "1",
            ["mob_json"] = "1",
            ["simple"] = "1"
        });

        using var response = await SendPacedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);
        var location = response.Headers.Location?.ToString() ?? string.Empty;

        if (LooksLikeCaptcha(payload, location))
        {
            return MailRuAuthResult.Failed(
                "captcha_required",
                payload.Contains("recaptcha", StringComparison.OrdinalIgnoreCase) ||
                location.Contains("recaptcha", StringComparison.OrdinalIgnoreCase)
                    ? MailRuAuthState.ReCaptcha
                    : MailRuAuthState.Captcha,
                "aj_mobile_auth_requires_captcha");
        }

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

            var error =
                TryFindString(document.RootElement, "error", out var parsedError) &&
                !string.IsNullOrWhiteSpace(parsedError)
                    ? parsedError!
                    : "token_missing";

            var state = LooksLikeInvalidCredentials(error, payload)
                ? MailRuAuthState.InvalidCredentials
                : MailRuAuthState.Unknown;

            return MailRuAuthResult.Failed(
                error,
                state,
                "aj_mobile_auth_returned_no_access_token");
        }
        catch (JsonException)
        {
            if ((int)response.StatusCode is >= 300 and < 400)
            {
                return MailRuAuthResult.Failed(
                    "additional_verification_required",
                    MailRuAuthState.Captcha,
                    "aj_mobile_auth_redirected_to_additional_verification");
            }

            return MailRuAuthResult.Failed(
                response.IsSuccessStatusCode ? "malformed_json" : $"http_{(int)response.StatusCode}",
                MailRuAuthState.ProtocolError,
                "aj_mobile_auth_response_not_json");
        }
        finally
        {
            document?.Dispose();
        }
    }

    public Task<MailRuAuthResult> CompleteChallengeAsync(
        string login,
        MailRuChallengeCompletion completion,
        CancellationToken cancellationToken = default) =>
        Task.FromResult(MailRuAuthResult.Failed(
            "captcha_not_supported",
            MailRuAuthState.Captcha,
            "AJ-only policy: CAPTCHA is reported to the user and authorization stops."));

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
        using var response = await SendPacedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Thread status request failed with HTTP {(int)response.StatusCode}.");

        return payload;
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

        using var response = await SendPacedAsync(request, cancellationToken).ConfigureAwait(false);
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

        using var response = await SendPacedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        return new MailRuCommandResult(
            response.IsSuccessStatusCode && HasStatus200(payload),
            payload);
    }

    private Task<HttpResponseMessage> SendPacedAsync(
        HttpRequestMessage request,
        CancellationToken cancellationToken) =>
        SendPacedAsync(request, HttpCompletionOption.ResponseContentRead, cancellationToken);

    private async Task<HttpResponseMessage> SendPacedAsync(
        HttpRequestMessage request,
        HttpCompletionOption completionOption,
        CancellationToken cancellationToken)
    {
        var requestUri = request.RequestUri
            ?? throw new MailRuProtocolException("Request URI is missing.");

        if (!string.Equals(
                requestUri.Host,
                _options.BaseUri.Host,
                StringComparison.OrdinalIgnoreCase))
        {
            throw new MailRuProtocolException(
                $"AJ-only policy blocked request to host '{requestUri.Host}'.");
        }

        await _requestGate.WaitAsync(cancellationToken).ConfigureAwait(false);
        try
        {
            var elapsed = DateTimeOffset.UtcNow - _lastRequestAt;
            var remaining = TimeSpan.FromSeconds(5) - elapsed;

            if (remaining > TimeSpan.Zero)
                await Task.Delay(remaining, cancellationToken).ConfigureAwait(false);

            _lastRequestAt = DateTimeOffset.UtcNow;
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
        IReadOnlyDictionary<string, string?> query)
    {
        var builder = new UriBuilder(new Uri(_options.BaseUri, relativePath));
        builder.Query = string.Join("&", query
            .Where(pair => pair.Value is not null)
            .Select(pair =>
                $"{Uri.EscapeDataString(pair.Key)}={Uri.EscapeDataString(pair.Value!)}"));

        return builder.Uri;
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
        _requestGate.Dispose();

        if (_ownsHttpClient)
            _http.Dispose();
    }
}
