using System.Text.Json;

namespace MailRuDesktop.Protocol;

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
    bool HasAttachment)
{
    public string SenderDisplay =>
        !string.IsNullOrWhiteSpace(SenderName)
            ? SenderName
            : SenderEmail;

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
}

public sealed record MailRuFolderSnapshot(
    long? MessagesTotal,
    long? MessagesUnread,
    IReadOnlyList<MailRuMessageSummary> Messages,
    string RawJson);

public static class MailRuThreadStatusParser
{
    public static MailRuFolderSnapshot Parse(string payload)
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

            var total = FindInteger(body, "messages_total");
            var unread = FindInteger(body, "messages_unread");

            var messages = new List<MailRuMessageSummary>();
            var seenIds = new HashSet<string>(StringComparer.Ordinal);
            CollectMessages(body, messages, seenIds);

            messages.Sort((left, right) =>
                Nullable.Compare(right.DateUnix, left.DateUnix));

            return new MailRuFolderSnapshot(total, unread, messages, payload);
        }
        catch (JsonException ex)
        {
            throw new MailRuProtocolException($"Thread-status response is not valid JSON: {ex.Message}");
        }
    }

    private static void CollectMessages(
        JsonElement element,
        List<MailRuMessageSummary> messages,
        HashSet<string> seenIds)
    {
        switch (element.ValueKind)
        {
            case JsonValueKind.Object:
                if (LooksLikeMessage(element) &&
                    TryReadId(element, out var id) &&
                    seenIds.Add(id))
                {
                    messages.Add(ParseMessage(element, id));
                }

                foreach (var property in element.EnumerateObject())
                    CollectMessages(property.Value, messages, seenIds);
                break;

            case JsonValueKind.Array:
                foreach (var item in element.EnumerateArray())
                    CollectMessages(item, messages, seenIds);
                break;
        }
    }

    private static bool LooksLikeMessage(JsonElement element)
    {
        if (element.ValueKind != JsonValueKind.Object ||
            !element.TryGetProperty("id", out _))
        {
            return false;
        }

        var hasText = element.TryGetProperty("subject", out _) ||
                      element.TryGetProperty("snippet", out _);

        var hasMessageShape = element.TryGetProperty("correspondents", out _) ||
                              element.TryGetProperty("date", out _) ||
                              element.TryGetProperty("folder", out _) ||
                              element.TryGetProperty("size", out _);

        return hasText && hasMessageShape;
    }

    private static MailRuMessageSummary ParseMessage(JsonElement element, string id)
    {
        var subject = ReadString(element, "subject") ?? "(без темы)";
        var snippet = ReadString(element, "snippet") ?? string.Empty;
        var date = ReadInteger(element, "date");
        var size = ReadInteger(element, "size");
        var folder = ReadInteger(element, "folder");
        var unread = ReadBoolean(element, "unread") ?? ReadFlag(element, "unread") ?? false;
        var flagged = ReadBoolean(element, "flagged") ?? ReadFlag(element, "flagged") ?? false;
        var attach = ReadBoolean(element, "attach") ?? ReadFlag(element, "attach") ?? false;

        var (senderName, senderEmail) = ReadSender(element);

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
            attach);
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

            var name = ReadString(sender, "name") ?? string.Empty;
            var email = ReadString(sender, "email") ?? string.Empty;
            return (name, email);
        }

        return (string.Empty, string.Empty);
    }

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
