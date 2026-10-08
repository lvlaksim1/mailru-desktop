using System.Net;
using System.Text.Json;

namespace MailRuDesktop.Protocol;

public sealed record MailRuFolderSummary(
    int Id,
    string Type,
    string Name,
    long MessagesUnread,
    long MessagesTotal,
    bool IsSystem)
{
    public string DisplayName => MessagesUnread > 0
        ? $"{Name} ({MessagesUnread})"
        : Name;

    public override string ToString() => Name;
}

public sealed record MailRuMessageSummary(
    string Id,
    string Subject,
    string Snippet,
    string SenderName,
    string SenderEmail,
    long? DateUnix,
    long? SizeBytes,
    int? FolderId,
    bool Unread,
    bool Flagged,
    bool HasAttachment,
    bool Pinned = false)
{
    public string SenderDisplay =>
        !string.IsNullOrWhiteSpace(SenderName)
            ? SenderName
            : SenderEmail;

    public string? AvatarUrl => MailRuAvatarUrls.ForEmail(SenderEmail);

    public string SenderInitials
    {
        get
        {
            var source = SenderDisplay.Trim();
            if (source.Length == 0)
                return "?";

            var words = source
                .Replace('@', ' ')
                .Replace('.', ' ')
                .Replace('_', ' ')
                .Replace('-', ' ')
                .Split(
                    ' ',
                    StringSplitOptions.RemoveEmptyEntries |
                    StringSplitOptions.TrimEntries);

            if (words.Length >= 2)
            {
                return (
                    char.ToUpperInvariant(words[0][0]).ToString() +
                    char.ToUpperInvariant(words[1][0]))
                    .Trim();
            }

            return char.ToUpperInvariant(source[0]).ToString();
        }
    }

    public string DateDisplay
    {
        get
        {
            if (DateUnix is null)
                return string.Empty;

            try
            {
                return DateTimeOffset
                    .FromUnixTimeSeconds(DateUnix.Value)
                    .ToLocalTime()
                    .ToString("dd.MM.yyyy HH:mm");
            }
            catch (ArgumentOutOfRangeException)
            {
                return DateUnix.Value.ToString();
            }
        }
    }

    public string SizeDisplay
    {
        get
        {
            if (SizeBytes is null)
                return string.Empty;

            var value = (double)SizeBytes.Value;
            if (value < 1024)
                return $"{value:0} Б";
            if (value < 1024 * 1024)
                return $"{value / 1024:0.#} КБ";
            return $"{value / (1024 * 1024):0.#} МБ";
        }
    }

    public string UnreadMark => Unread ? "●" : string.Empty;
    public string AttachmentMark => HasAttachment ? "📎" : string.Empty;
    public string FlagMark => Flagged ? "★" : string.Empty;
    public string PinMark => Pinned ? "📌" : string.Empty;
}

public sealed record MailRuFolderSnapshot(
    int? SelectedFolderId,
    long? MessagesTotal,
    long? MessagesUnread,
    IReadOnlyList<MailRuFolderSummary> Folders,
    IReadOnlyList<MailRuMessageSummary> Messages,
    string RawJson);

public static class MailRuThreadStatusParser
{
    public static MailRuFolderSnapshot Parse(string payload, int? requestedFolderId = null)
    {
        if (string.IsNullOrWhiteSpace(payload))
            throw new MailRuProtocolException("Thread-status response is empty.");

        try
        {
            using var document = JsonDocument.Parse(payload);
            var root = document.RootElement;

            var body = root.ValueKind == JsonValueKind.Object &&
                       root.TryGetProperty("body", out var bodyElement)
                ? bodyElement
                : root;

            var folders = ParseFolders(body);
            var selectedFolderId = requestedFolderId;
            JsonElement? selectedContent = null;

            if (body.ValueKind == JsonValueKind.Object &&
                body.TryGetProperty("folders_content", out var foldersContent) &&
                foldersContent.ValueKind == JsonValueKind.Array)
            {
                foreach (var content in foldersContent.EnumerateArray())
                {
                    if (content.ValueKind != JsonValueKind.Object)
                        continue;

                    var contentId = ReadInteger(content, "id");
                    if (selectedFolderId is null && contentId is not null)
                        selectedFolderId = checked((int)contentId.Value);

                    if (selectedFolderId is not null &&
                        contentId == selectedFolderId.Value)
                    {
                        selectedContent = content;
                        break;
                    }

                    selectedContent ??= content;
                }
            }

            var messages = selectedContent is null
                ? new List<MailRuMessageSummary>()
                : ParseThreads(selectedContent.Value, selectedFolderId);

            if (messages.Count == 0)
            {
                CollectThreads(
                    body,
                    messages,
                    new HashSet<string>(StringComparer.Ordinal),
                    selectedFolderId);
            }

            messages.Sort((left, right) =>
                Nullable.Compare(right.DateUnix, left.DateUnix));

            var selectedFolder = selectedFolderId is null
                ? null
                : folders.FirstOrDefault(folder => folder.Id == selectedFolderId.Value);

            var total = selectedFolder?.MessagesTotal ??
                        (selectedContent is null
                            ? FindInteger(body, "messages_total")
                            : FindInteger(selectedContent.Value, "messages_total"));
            var unread = selectedFolder?.MessagesUnread ??
                         (selectedContent is null
                             ? FindInteger(body, "messages_unread")
                             : FindInteger(selectedContent.Value, "messages_unread"));

            if (folders.Count == 0 && selectedFolderId is not null)
            {
                folders.Add(new MailRuFolderSummary(
                    selectedFolderId.Value,
                    selectedFolderId.Value == 0 ? "inbox" : "folder",
                    selectedFolderId.Value == 0 ? "Входящие" : $"Папка {selectedFolderId.Value}",
                    unread ?? 0,
                    total ?? messages.Count,
                    true));
            }

            return new MailRuFolderSnapshot(
                selectedFolderId,
                total,
                unread,
                folders,
                messages,
                payload);
        }
        catch (JsonException ex)
        {
            throw new MailRuProtocolException($"Thread-status response is not valid JSON: {ex.Message}");
        }
    }

    private static List<MailRuFolderSummary> ParseFolders(JsonElement body)
    {
        var folders = new List<MailRuFolderSummary>();

        if (body.ValueKind != JsonValueKind.Object ||
            !body.TryGetProperty("folders", out var array) ||
            array.ValueKind != JsonValueKind.Array)
        {
            return folders;
        }

        foreach (var folder in array.EnumerateArray())
        {
            if (folder.ValueKind != JsonValueKind.Object)
                continue;

            var id = ReadInteger(folder, "id");
            if (id is null || id < int.MinValue || id > int.MaxValue)
                continue;

            folders.Add(new MailRuFolderSummary(
                checked((int)id.Value),
                Decode(ReadString(folder, "type") ?? string.Empty),
                Decode(ReadString(folder, "name") ?? id.Value.ToString()),
                ReadInteger(folder, "messages_unread") ?? 0,
                ReadInteger(folder, "messages_total") ?? 0,
                ReadBoolean(folder, "system") ?? false));
        }

        return folders;
    }

    private static List<MailRuMessageSummary> ParseThreads(
        JsonElement content,
        int? requestedFolderId)
    {
        var result = new List<MailRuMessageSummary>();

        if (!content.TryGetProperty("threads", out var threads) ||
            threads.ValueKind != JsonValueKind.Array)
        {
            return result;
        }

        var seenIds = new HashSet<string>(StringComparer.Ordinal);
        foreach (var thread in threads.EnumerateArray())
            TryAddThread(thread, result, seenIds, requestedFolderId);

        return result;
    }

    private static void CollectThreads(
        JsonElement element,
        List<MailRuMessageSummary> result,
        HashSet<string> seenIds,
        int? requestedFolderId)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            if (element.TryGetProperty("threads", out var threads) &&
                threads.ValueKind == JsonValueKind.Array)
            {
                foreach (var thread in threads.EnumerateArray())
                    TryAddThread(thread, result, seenIds, requestedFolderId);
            }

            foreach (var property in element.EnumerateObject())
                CollectThreads(property.Value, result, seenIds, requestedFolderId);
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
                CollectThreads(item, result, seenIds, requestedFolderId);
        }
    }

    private static void TryAddThread(
        JsonElement thread,
        List<MailRuMessageSummary> result,
        HashSet<string> seenIds,
        int? requestedFolderId)
    {
        if (thread.ValueKind != JsonValueKind.Object)
            return;

        var threadId = TryReadId(thread, out var parsedThreadId)
            ? parsedThreadId
            : string.Empty;

        if (thread.TryGetProperty("base_message", out var baseMessage) &&
            baseMessage.ValueKind == JsonValueKind.Object &&
            TryResolveMessageId(baseMessage, threadId, out var baseMessageId))
        {
            AddParsedMessage(baseMessageId, baseMessage, result, seenIds);
            return;
        }

        if (thread.TryGetProperty("messages", out var messages) &&
            messages.ValueKind == JsonValueKind.Array)
        {
            foreach (var message in messages.EnumerateArray())
            {
                if (message.ValueKind != JsonValueKind.Object ||
                    !TryResolveMessageId(message, threadId, out var messageId))
                {
                    continue;
                }

                AddParsedMessage(messageId, message, result, seenIds);
                return;
            }
        }

        if (thread.TryGetProperty("representations", out var representations) &&
            representations.ValueKind == JsonValueKind.Array)
        {
            var representation = SelectRepresentation(representations, requestedFolderId);
            if (representation is not null &&
                TryResolveMessageId(representation.Value, threadId, out var representationId))
            {
                AddParsedMessage(representationId, representation.Value, result, seenIds);
                return;
            }
        }

        if ((thread.TryGetProperty("subject", out _) ||
             thread.TryGetProperty("snippet", out _)) &&
            !string.IsNullOrWhiteSpace(threadId))
        {
            AddParsedMessage(threadId, thread, result, seenIds);
        }
    }

    private static JsonElement? SelectRepresentation(
        JsonElement representations,
        int? requestedFolderId)
    {
        JsonElement? selected = null;
        long selectedDate = long.MinValue;
        var objectCount = 0;
        JsonElement? onlyObject = null;

        foreach (var representation in representations.EnumerateArray())
        {
            if (representation.ValueKind != JsonValueKind.Object)
                continue;

            objectCount++;
            onlyObject = representation;

            var folder = ReadInteger(representation, "folder");
            if (requestedFolderId is not null && folder != requestedFolderId.Value)
                continue;

            var date = ReadInteger(representation, "date") ?? long.MinValue;
            if (selected is null || date > selectedDate)
            {
                selected = representation;
                selectedDate = date;
            }
        }

        if (selected is not null)
            return selected;

        // Preserve the v0.3.4 fallback behavior: if the server gives exactly one
        // representation, accept it even when folder metadata is absent/different.
        return objectCount == 1 ? onlyObject : null;
    }

    private static bool TryResolveMessageId(
        JsonElement message,
        string fallbackId,
        out string id)
    {
        id = ReadString(message, "message_id_last") ?? string.Empty;
        if (!string.IsNullOrWhiteSpace(id))
            return true;

        if (TryReadId(message, out id))
            return true;

        id = fallbackId;
        return !string.IsNullOrWhiteSpace(id);
    }

    private static void AddParsedMessage(
        string id,
        JsonElement message,
        List<MailRuMessageSummary> result,
        HashSet<string> seenIds)
    {
        if (!seenIds.Add(id))
            return;

        result.Add(ParseBaseMessage(id, message));
    }

    private static MailRuMessageSummary ParseBaseMessage(string id, JsonElement message)
    {
        var subject = Decode(ReadString(message, "subject") ?? "(без темы)");
        var snippet = Decode(ReadString(message, "snippet") ?? string.Empty);
        var date = ReadInteger(message, "date");
        var size = ReadInteger(message, "size");
        var folder = ReadInteger(message, "folder");
        var unread = ReadBoolean(message, "unread") ?? ReadFlag(message, "unread") ?? false;
        var flagged = ReadBoolean(message, "flagged") ?? ReadFlag(message, "flagged") ?? false;
        var attachmentsCount = ReadInteger(message, "attachments_count") ?? 0;
        var attach = attachmentsCount > 0 ||
                     ReadBoolean(message, "attach") == true ||
                     ReadFlag(message, "attach") == true;
        var pinned =
            ReadBoolean(message, "pinned") ??
            ReadBoolean(message, "pin") ??
            ReadFlag(message, "pinned") ??
            ReadFlag(message, "pin") ??
            false;

        var (senderName, senderEmail) = ReadSender(message);

        return new MailRuMessageSummary(
            id,
            subject,
            snippet,
            senderName,
            senderEmail,
            date,
            size,
            folder is null ? null : checked((int)folder.Value),
            unread,
            flagged,
            attach,
            pinned);
    }

    private static (string Name, string Email) ReadSender(JsonElement element)
    {
        if (!element.TryGetProperty("correspondents", out var correspondents) ||
            correspondents.ValueKind != JsonValueKind.Object ||
            !correspondents.TryGetProperty("from", out var from) ||
            from.ValueKind != JsonValueKind.Array)
        {
            return (string.Empty, string.Empty);
        }

        foreach (var sender in from.EnumerateArray())
        {
            if (sender.ValueKind != JsonValueKind.Object)
                continue;

            var name = Decode(ReadString(sender, "name") ?? string.Empty);
            var email = Decode(ReadString(sender, "email") ?? string.Empty);
            return (name, email);
        }

        return (string.Empty, string.Empty);
    }

    private static string Decode(string value) =>
        WebUtility.HtmlDecode(value).Replace("&nbsp;", " ", StringComparison.Ordinal);

    private static bool? ReadFlag(JsonElement element, string name)
    {
        if (!element.TryGetProperty("flags", out var flags) ||
            flags.ValueKind != JsonValueKind.Object)
        {
            return null;
        }

        return ReadBoolean(flags, name);
    }

    private static bool TryReadId(JsonElement element, out string id)
    {
        id = string.Empty;
        if (!element.TryGetProperty("id", out var idElement))
            return false;

        switch (idElement.ValueKind)
        {
            case JsonValueKind.String:
                id = idElement.GetString() ?? string.Empty;
                return id.Length > 0;
            case JsonValueKind.Number:
                id = idElement.GetRawText();
                return id.Length > 0;
            default:
                return false;
        }
    }

    private static string? ReadString(JsonElement element, string name)
    {
        if (!element.TryGetProperty(name, out var value))
            return null;

        return value.ValueKind switch
        {
            JsonValueKind.String => value.GetString(),
            JsonValueKind.Number => value.GetRawText(),
            _ => null
        };
    }

    private static long? ReadInteger(JsonElement element, string name)
    {
        if (!element.TryGetProperty(name, out var value))
            return null;

        if (value.ValueKind == JsonValueKind.Number && value.TryGetInt64(out var number))
            return number;

        if (value.ValueKind == JsonValueKind.String &&
            long.TryParse(value.GetString(), out number))
        {
            return number;
        }

        return null;
    }

    private static bool? ReadBoolean(JsonElement element, string name)
    {
        if (!element.TryGetProperty(name, out var value))
            return null;

        return value.ValueKind switch
        {
            JsonValueKind.True => true,
            JsonValueKind.False => false,
            JsonValueKind.Number when value.TryGetInt32(out var number) => number != 0,
            JsonValueKind.String when bool.TryParse(value.GetString(), out var parsed) => parsed,
            JsonValueKind.String when value.GetString() == "1" => true,
            JsonValueKind.String when value.GetString() == "0" => false,
            _ => null
        };
    }

    private static long? FindInteger(JsonElement element, string name)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            var direct = ReadInteger(element, name);
            if (direct is not null)
                return direct;

            foreach (var property in element.EnumerateObject())
            {
                var found = FindInteger(property.Value, name);
                if (found is not null)
                    return found;
            }
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
            {
                var found = FindInteger(item, name);
                if (found is not null)
                    return found;
            }
        }

        return null;
    }
}
