using System.Globalization;
using System.Net;
using System.Text.Json;

namespace MailRuDesktop.Protocol;

public sealed record MailRuContactSummary(string Name, string Email)
{
    public string DisplayName =>
        string.IsNullOrWhiteSpace(Name) ? Email : $"{Name} <{Email}>";

    public override string ToString() => DisplayName;
}

public sealed record MailRuSearchResult(
    int Found,
    IReadOnlyList<MailRuMessageSummary> Messages,
    string RawResponse,
    string Source);

public sealed partial class MailRuClient
{
    public Task<MailRuCommandResult> SetFlaggedAsync(
        string accessToken,
        string email,
        string messageId,
        int folderId,
        bool flagged,
        CancellationToken cancellationToken = default) =>
        SetMessageMarkAsync(accessToken, email, messageId, folderId, "flagged", flagged, cancellationToken);

    public Task<MailRuCommandResult> SetPinnedAsync(
        string accessToken,
        string email,
        string messageId,
        int folderId,
        bool pinned,
        CancellationToken cancellationToken = default) =>
        SetMessageMarkAsync(accessToken, email, messageId, folderId, "pinned", pinned, cancellationToken);

    public async Task<MailRuCommandResult> SetMessageMarkAsync(
        string accessToken,
        string email,
        string messageId,
        int folderId,
        string markName,
        bool enabled,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        if (string.IsNullOrWhiteSpace(messageId))
            throw new ArgumentException("Message id is required.", nameof(messageId));
        if (markName is not ("unread" or "flagged" or "pinned"))
            throw new ArgumentOutOfRangeException(nameof(markName));

        var marks = JsonSerializer.Serialize(new[]
        {
            new
            {
                name = markName,
                set = enabled ? new[] { messageId } : Array.Empty<string>(),
                unset = enabled ? Array.Empty<string>() : new[] { messageId },
                folder = folderId
            }
        });

        return await PostFormCommandAsync(
            "/api/v1/messages/marks",
            accessToken,
            email,
            new Dictionary<string, string> { ["marks"] = marks },
            cancellationToken).ConfigureAwait(false);
    }

    public async Task<MailRuCommandResult> RemoveMessagesAsync(
        string accessToken,
        string email,
        IReadOnlyCollection<string> messageIds,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        ArgumentNullException.ThrowIfNull(messageIds);
        if (messageIds.Count == 0)
            throw new ArgumentException("At least one message id is required.", nameof(messageIds));

        return await PostFormCommandAsync(
            "/api/v1/messages/remove",
            accessToken,
            email,
            new Dictionary<string, string>
            {
                ["ids"] = JsonSerializer.Serialize(messageIds)
            },
            cancellationToken).ConfigureAwait(false);
    }

    public async Task<MailRuSearchResult> SearchMessagesAsync(
        string accessToken,
        string email,
        string query,
        int offset = 0,
        int limit = 100,
        bool preferNewSearch = false,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        if (string.IsNullOrWhiteSpace(query))
            return new MailRuSearchResult(0, Array.Empty<MailRuMessageSummary>(), string.Empty, "empty");

        if (preferNewSearch)
        {
            try
            {
                return await SearchMessagesNewAsync(
                    accessToken, email, query, offset, limit, cancellationToken).ConfigureAwait(false);
            }
            catch (Exception) when (!cancellationToken.IsCancellationRequested)
            {
                // The official client keeps both search families. Fall back to the
                // older mail search if the newer host is unavailable for the account.
            }
        }

        return await SearchMessagesClassicAsync(
            accessToken, email, query, offset, limit, cancellationToken).ConfigureAwait(false);
    }

    public async Task<MailRuSearchResult> SearchMessagesNewAsync(
        string accessToken,
        string email,
        string query,
        int offset = 0,
        int limit = 100,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        if (offset < 0) throw new ArgumentOutOfRangeException(nameof(offset));
        if (limit is < 1 or > 500) throw new ArgumentOutOfRangeException(nameof(limit));

        var uri = BuildAbsoluteUri(
            "https://go.mail.ru/api/v1/go/search/emails",
            new Dictionary<string, string?>
            {
                ["q"] = query,
                ["offset"] = offset.ToString(CultureInfo.InvariantCulture),
                ["limit"] = limit.ToString(CultureInfo.InvariantCulture),
                ["snippet_limit"] = "400",
                ["email"] = string.IsNullOrWhiteSpace(email) ? null : email,
                ["lang"] = "ru_RU",
                ["t"] = accessToken
            });

        using var request = CreateRequest(HttpMethod.Get, uri);
        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"New search failed with HTTP {(int)response.StatusCode}.");

        return MailRuFeatureParser.ParseSearch(payload, "go.mail.ru");
    }

    public async Task<MailRuSearchResult> SearchMessagesClassicAsync(
        string accessToken,
        string email,
        string query,
        int offset = 0,
        int limit = 100,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        if (offset < 0) throw new ArgumentOutOfRangeException(nameof(offset));
        if (limit is < 1 or > 500) throw new ArgumentOutOfRangeException(nameof(limit));

        var uri = BuildUri("/api/v1/messages/search", new Dictionary<string, string?>
        {
            ["query"] = query,
            ["offset"] = offset.ToString(CultureInfo.InvariantCulture),
            ["limit"] = limit.ToString(CultureInfo.InvariantCulture),
            ["snippet_limit"] = "400",
            ["with_threads"] = "false",
            ["htmlencoded"] = "false",
            ["email"] = string.IsNullOrWhiteSpace(email) ? null : email,
            ["lang"] = "ru_RU",
            ["access_token"] = accessToken
        });

        using var request = CreateRequest(HttpMethod.Get, uri);
        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Mail search failed with HTTP {(int)response.StatusCode}.");

        return MailRuFeatureParser.ParseSearch(payload, "aj-https.mail.ru");
    }

    public async Task<IReadOnlyList<MailRuContactSummary>> GetAddressBookAsync(
        string accessToken,
        string email,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);

        var uri = BuildUri("/api/v1/ab/smart", new Dictionary<string, string?>
        {
            ["limit"] = int.MaxValue.ToString(CultureInfo.InvariantCulture),
            ["email"] = string.IsNullOrWhiteSpace(email) ? null : email,
            ["lang"] = "ru_RU",
            ["access_token"] = accessToken
        });

        using var request = CreateRequest(HttpMethod.Get, uri);
        request.Headers.TryAddWithoutValidation("Accept-Encoding", "gzip");
        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Address book request failed with HTTP {(int)response.StatusCode}.");

        return MailRuFeatureParser.ParseContacts(payload);
    }

    public async Task<IReadOnlyList<MailRuContactSummary>> SearchPeopleAsync(
        string accessToken,
        string email,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);

        var uri = BuildUri("/api/v1/ab/fast", new Dictionary<string, string?>
        {
            ["email"] = string.IsNullOrWhiteSpace(email) ? null : email,
            ["lang"] = "ru_RU",
            ["access_token"] = accessToken
        });

        using var request = CreateRequest(HttpMethod.Get, uri);
        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Recipient lookup failed with HTTP {(int)response.StatusCode}.");

        return MailRuFeatureParser.ParseFastContacts(payload);
    }

    public async Task<IReadOnlyList<MailRuFolderSummary>> GetFoldersAsync(
        string accessToken,
        string email,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        var uri = BuildUri("/api/v1/folders", CommonQuery(accessToken, email));

        using var request = CreateRequest(HttpMethod.Get, uri);
        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        if (!response.IsSuccessStatusCode)
            throw new MailRuProtocolException($"Folder list request failed with HTTP {(int)response.StatusCode}.");

        return MailRuFeatureParser.ParseFolders(payload);
    }

    public async Task<MailRuCommandResult> CreateFolderAsync(
        string accessToken,
        string email,
        string name,
        CancellationToken cancellationToken = default)
    {
        if (string.IsNullOrWhiteSpace(name))
            throw new ArgumentException("Folder name is required.", nameof(name));

        var folders = JsonSerializer.Serialize(new[]
        {
            new { id = -1, name = name.Trim(), parent = "-1", only_web = false }
        });

        return await PostFormCommandAsync(
            "/api/v1/folders/add",
            accessToken,
            email,
            new Dictionary<string, string>
            {
                ["email"] = email,
                ["folders"] = folders
            },
            cancellationToken).ConfigureAwait(false);
    }

    public async Task<MailRuCommandResult> RenameFolderAsync(
        string accessToken,
        string email,
        int folderId,
        string name,
        CancellationToken cancellationToken = default)
    {
        if (string.IsNullOrWhiteSpace(name))
            throw new ArgumentException("Folder name is required.", nameof(name));

        var folders = JsonSerializer.Serialize(new[]
        {
            new { id = folderId, name = name.Trim() }
        });

        return await PostFormCommandAsync(
            "/api/v1/folders/edit",
            accessToken,
            email,
            new Dictionary<string, string>
            {
                ["email"] = email,
                ["folders"] = folders
            },
            cancellationToken).ConfigureAwait(false);
    }

    public async Task<MailRuCommandResult> DeleteFolderAsync(
        string accessToken,
        string email,
        int folderId,
        CancellationToken cancellationToken = default) =>
        await PostFormCommandAsync(
            "/api/v1/folders/remove",
            accessToken,
            email,
            new Dictionary<string, string>
            {
                ["email"] = email,
                ["ids"] = JsonSerializer.Serialize(new[] { folderId.ToString(CultureInfo.InvariantCulture) })
            },
            cancellationToken).ConfigureAwait(false);

    public async Task<MailRuCommandResult> ClearFolderAsync(
        string accessToken,
        string email,
        int folderId,
        CancellationToken cancellationToken = default) =>
        await PostFormCommandAsync(
            "/api/v1/folders/clear",
            accessToken,
            email,
            new Dictionary<string, string>
            {
                ["ids"] = JsonSerializer.Serialize(new[] { folderId })
            },
            cancellationToken).ConfigureAwait(false);

    public async Task<MailRuCommandResult> SaveDraftAsync(
        string accessToken,
        string email,
        MailRuOutgoingMessage message,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        ArgumentNullException.ThrowIfNull(message);

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
        var correspondents = JsonSerializer.Serialize(new { bcc = "", cc = "", to = message.To ?? string.Empty });
        var source = JsonSerializer.Serialize(new { draft = "", reply = message.ReplyToId ?? "", forward = "", schedule = "" });

        return await PostFormCommandAsync(
            "/api/v1/messages/draft",
            accessToken,
            email,
            new Dictionary<string, string>
            {
                ["attaches"] = attaches,
                ["body"] = body,
                ["correspondents"] = correspondents,
                ["id"] = messageId,
                ["source"] = source,
                ["subject"] = message.Subject ?? string.Empty,
                ["send_date"] = "0",
                ["priority"] = message.Priority.ToString(CultureInfo.InvariantCulture)
            },
            cancellationToken).ConfigureAwait(false);
    }

    private async Task<MailRuCommandResult> PostFormCommandAsync(
        string path,
        string accessToken,
        string email,
        IReadOnlyDictionary<string, string> form,
        CancellationToken cancellationToken)
    {
        RequireToken(accessToken);

        var uri = BuildUri(path, CommonQuery(accessToken, email));
        using var request = CreateRequest(HttpMethod.Post, uri);
        request.Content = new FormUrlEncodedContent(form);

        using var response = await SendSerializedAsync(request, cancellationToken).ConfigureAwait(false);
        var payload = await response.Content.ReadAsStringAsync(cancellationToken).ConfigureAwait(false);

        return new MailRuCommandResult(
            response.IsSuccessStatusCode && HasStatus200(payload),
            payload);
    }

    private static IReadOnlyDictionary<string, string?> CommonQuery(string accessToken, string email) =>
        new Dictionary<string, string?>
        {
            ["htmlencoded"] = "false",
            ["email"] = string.IsNullOrWhiteSpace(email) ? null : email,
            ["lang"] = "ru_RU",
            ["access_token"] = accessToken
        };
}

public static class MailRuFeatureParser
{
    public static IReadOnlyList<MailRuContactSummary> ParseContacts(string payload)
    {
        using var document = JsonDocument.Parse(payload);
        var result = new Dictionary<string, MailRuContactSummary>(StringComparer.OrdinalIgnoreCase);

        if (TryFindProperty(document.RootElement, "contacts", out var contacts) &&
            contacts.ValueKind == JsonValueKind.Array)
        {
            foreach (var contact in contacts.EnumerateArray())
            {
                if (contact.ValueKind != JsonValueKind.Object)
                    continue;

                var name = ReadName(contact);
                if (!contact.TryGetProperty("emails", out var emails))
                    continue;

                foreach (var email in EnumerateStrings(emails))
                {
                    if (!string.IsNullOrWhiteSpace(email))
                        result[email] = new MailRuContactSummary(name, email);
                }
            }
        }

        return result.Values
            .OrderBy(x => x.Name, StringComparer.CurrentCultureIgnoreCase)
            .ThenBy(x => x.Email, StringComparer.OrdinalIgnoreCase)
            .ToArray();
    }

    public static IReadOnlyList<MailRuContactSummary> ParseFastContacts(string payload)
    {
        using var document = JsonDocument.Parse(payload);
        var result = new List<MailRuContactSummary>();

        if (!TryFindProperty(document.RootElement, "body", out var body) ||
            body.ValueKind != JsonValueKind.Array)
            return result;

        foreach (var row in body.EnumerateArray())
        {
            if (row.ValueKind != JsonValueKind.Array)
                continue;

            var values = row.EnumerateArray().ToArray();
            if (values.Length < 2)
                continue;

            var name = values[0].ValueKind == JsonValueKind.String ? values[0].GetString() ?? "" : "";
            var emails = EnumerateStrings(values[1]).ToArray();
            foreach (var email in emails)
            {
                if (!string.IsNullOrWhiteSpace(email))
                    result.Add(new MailRuContactSummary(name == "null" ? "" : name, email));
            }
        }

        return result;
    }

    public static IReadOnlyList<MailRuFolderSummary> ParseFolders(string payload)
    {
        using var document = JsonDocument.Parse(payload);
        var result = new List<MailRuFolderSummary>();
        JsonElement folders = default;

        if (document.RootElement.TryGetProperty("body", out var body))
        {
            if (body.ValueKind == JsonValueKind.Array)
                folders = body;
            else if (body.ValueKind == JsonValueKind.Object && body.TryGetProperty("folders", out var nested))
                folders = nested;
        }

        if (folders.ValueKind != JsonValueKind.Array)
            return result;

        foreach (var folder in folders.EnumerateArray())
        {
            var id = ReadInt(folder, "id");
            if (id is null)
                continue;

            result.Add(new MailRuFolderSummary(
                id.Value,
                ReadString(folder, "type") ?? "folder",
                WebUtility.HtmlDecode(ReadString(folder, "name") ?? id.Value.ToString(CultureInfo.InvariantCulture)),
                ReadLong(folder, "messages_unread") ?? 0,
                ReadLong(folder, "messages_total") ?? 0,
                ReadBool(folder, "system") ?? false));
        }

        return result;
    }

    public static MailRuSearchResult ParseSearch(string payload, string source)
    {
        using var document = JsonDocument.Parse(payload);
        var messages = new List<MailRuMessageSummary>();
        var seen = new HashSet<string>(StringComparer.Ordinal);
        CollectMessages(document.RootElement, messages, seen);
        messages.Sort((a, b) => Nullable.Compare(b.DateUnix, a.DateUnix));

        var found = FindInt(document.RootElement, "count") ?? messages.Count;
        return new MailRuSearchResult(found, messages, payload, source);
    }

    private static void CollectMessages(JsonElement element, List<MailRuMessageSummary> result, HashSet<string> seen)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            if (LooksLikeMessage(element) && TryReadId(element, out var id) && seen.Add(id))
                result.Add(ParseMessage(element, id));

            foreach (var property in element.EnumerateObject())
                CollectMessages(property.Value, result, seen);
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
                CollectMessages(item, result, seen);
        }
    }

    private static bool LooksLikeMessage(JsonElement e) =>
        e.TryGetProperty("id", out _) &&
        (e.TryGetProperty("subject", out _) || e.TryGetProperty("snippet", out _)) &&
        (e.TryGetProperty("correspondents", out _) || e.TryGetProperty("from", out _));

    private static MailRuMessageSummary ParseMessage(JsonElement e, string id)
    {
        var senderName = "";
        var senderEmail = "";

        if (e.TryGetProperty("correspondents", out var corr) &&
            corr.ValueKind == JsonValueKind.Object &&
            corr.TryGetProperty("from", out var from))
            ReadSender(from, out senderName, out senderEmail);
        else if (e.TryGetProperty("from", out var directFrom))
            ReadSender(directFrom, out senderName, out senderEmail);

        var flags = e.TryGetProperty("flags", out var f) && f.ValueKind == JsonValueKind.Object ? f : default;

        return new MailRuMessageSummary(
            id,
            WebUtility.HtmlDecode(ReadString(e, "subject") ?? "(без темы)"),
            WebUtility.HtmlDecode(ReadString(e, "snippet") ?? ""),
            senderName,
            senderEmail,
            ReadLong(e, "date"),
            ReadLong(e, "size"),
            ReadInt(e, "folder"),
            ReadBool(e, "unread") ?? ReadBool(flags, "unread") ?? false,
            ReadBool(e, "flagged") ?? ReadBool(flags, "flagged") ?? false,
            (ReadLong(e, "attachments_count") ?? 0) > 0 ||
                ReadBool(e, "attach") == true ||
                ReadBool(flags, "attach") == true);
    }

    private static void ReadSender(JsonElement from, out string name, out string email)
    {
        name = "";
        email = "";

        var item = from;
        if (from.ValueKind == JsonValueKind.Array)
            item = from.EnumerateArray().FirstOrDefault();

        if (item.ValueKind == JsonValueKind.Object)
        {
            name = WebUtility.HtmlDecode(ReadString(item, "name") ?? "");
            email = WebUtility.HtmlDecode(ReadString(item, "email") ?? "");
        }
        else if (item.ValueKind == JsonValueKind.String)
        {
            email = item.GetString() ?? "";
        }
    }

    private static string ReadName(JsonElement contact)
    {
        if (!contact.TryGetProperty("name", out var name))
            return "";

        if (name.ValueKind == JsonValueKind.String)
            return WebUtility.HtmlDecode(name.GetString() ?? "");

        if (name.ValueKind != JsonValueKind.Object)
            return "";

        var first = ReadString(name, "first") ?? "";
        var last = ReadString(name, "last") ?? "";
        return WebUtility.HtmlDecode((first + " " + last).Trim());
    }

    private static IEnumerable<string> EnumerateStrings(JsonElement element)
    {
        if (element.ValueKind == JsonValueKind.String)
        {
            yield return element.GetString() ?? "";
            yield break;
        }

        if (element.ValueKind != JsonValueKind.Array)
            yield break;

        foreach (var item in element.EnumerateArray())
        {
            if (item.ValueKind == JsonValueKind.String)
                yield return item.GetString() ?? "";
            else if (item.ValueKind == JsonValueKind.Object)
            {
                var value = ReadString(item, "email") ?? ReadString(item, "value");
                if (!string.IsNullOrWhiteSpace(value))
                    yield return value;
            }
        }
    }

    private static bool TryFindProperty(JsonElement element, string name, out JsonElement value)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            foreach (var property in element.EnumerateObject())
            {
                if (property.NameEquals(name))
                {
                    value = property.Value;
                    return true;
                }

                if (TryFindProperty(property.Value, name, out value))
                    return true;
            }
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
                if (TryFindProperty(item, name, out value))
                    return true;
        }

        value = default;
        return false;
    }

    private static string? ReadString(JsonElement e, string name)
    {
        if (e.ValueKind != JsonValueKind.Object || !e.TryGetProperty(name, out var v))
            return null;

        return v.ValueKind switch
        {
            JsonValueKind.String => v.GetString(),
            JsonValueKind.Number => v.GetRawText(),
            _ => null
        };
    }

    private static long? ReadLong(JsonElement e, string name)
    {
        var value = ReadString(e, name);
        if (value is not null && long.TryParse(value, NumberStyles.Integer, CultureInfo.InvariantCulture, out var parsed))
            return parsed;

        if (e.ValueKind == JsonValueKind.Object && e.TryGetProperty(name, out var v) &&
            v.ValueKind == JsonValueKind.Number && v.TryGetInt64(out parsed))
            return parsed;

        return null;
    }

    private static int? ReadInt(JsonElement e, string name)
    {
        var value = ReadLong(e, name);
        return value is >= int.MinValue and <= int.MaxValue ? (int)value.Value : null;
    }

    private static bool? ReadBool(JsonElement e, string name)
    {
        if (e.ValueKind != JsonValueKind.Object || !e.TryGetProperty(name, out var v))
            return null;

        return v.ValueKind switch
        {
            JsonValueKind.True => true,
            JsonValueKind.False => false,
            JsonValueKind.Number when v.TryGetInt32(out var n) => n != 0,
            JsonValueKind.String when v.GetString() == "1" => true,
            JsonValueKind.String when v.GetString() == "0" => false,
            JsonValueKind.String when bool.TryParse(v.GetString(), out var b) => b,
            _ => null
        };
    }

    private static int? FindInt(JsonElement e, string name)
    {
        if (e.ValueKind == JsonValueKind.Object)
        {
            if (ReadInt(e, name) is int direct)
                return direct;
            foreach (var p in e.EnumerateObject())
                if (FindInt(p.Value, name) is int nested)
                    return nested;
        }
        else if (e.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in e.EnumerateArray())
                if (FindInt(item, name) is int nested)
                    return nested;
        }
        return null;
    }

    private static bool TryReadId(JsonElement e, out string id)
    {
        id = ReadString(e, "id") ?? "";
        return id.Length > 0;
    }
}
