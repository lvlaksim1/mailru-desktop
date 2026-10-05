using System.Net;
using System.Text.Json;

namespace MailRuDesktop.Protocol;

public static class MailRuTouchSearchParser
{
    public static MailRuFolderSnapshot Parse(string payload)
    {
        if (string.IsNullOrWhiteSpace(payload))
            throw new MailRuProtocolException("Touch search response is empty.");

        try
        {
            using var document = JsonDocument.Parse(payload);
            var messages = new List<MailRuMessageSummary>();
            var seen = new HashSet<string>(StringComparer.Ordinal);
            Collect(document.RootElement, messages, seen);

            messages.Sort((left, right) =>
                Nullable.Compare(right.DateUnix, left.DateUnix));

            var total = FindInteger(document.RootElement, "count") ?? messages.Count;
            var unread = messages.LongCount(message => message.Unread);

            return new MailRuFolderSnapshot(
                -100,
                total,
                unread,
                [
                    new MailRuFolderSummary(
                        -100,
                        "touch-search",
                        "Все письма",
                        unread,
                        total,
                        true)
                ],
                messages,
                payload);
        }
        catch (JsonException ex)
        {
            throw new MailRuProtocolException($"Touch search response is not valid JSON: {ex.Message}");
        }
    }

    private static void Collect(
        JsonElement element,
        List<MailRuMessageSummary> messages,
        HashSet<string> seen)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            if (TryReadId(element, out var id) &&
                seen.Add(id) &&
                LooksLikeMessage(element))
            {
                messages.Add(ParseMessage(element, id));
            }

            foreach (var property in element.EnumerateObject())
                Collect(property.Value, messages, seen);
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
                Collect(item, messages, seen);
        }
    }

    private static bool LooksLikeMessage(JsonElement element) =>
        element.TryGetProperty("subject", out _) ||
        element.TryGetProperty("snippet", out _) ||
        element.TryGetProperty("date", out _) ||
        element.TryGetProperty("correspondents", out _) ||
        element.TryGetProperty("from", out _);

    private static MailRuMessageSummary ParseMessage(JsonElement element, string id)
    {
        var subject = Decode(ReadString(element, "subject") ?? "(без темы)");
        var snippet = Decode(ReadString(element, "snippet") ?? string.Empty);
        var date = ReadInteger(element, "date");
        var size = ReadInteger(element, "size");
        var folder = ReadInteger(element, "folder");
        var unread = ReadBoolean(element, "unread") ?? ReadFlag(element, "unread") ?? false;
        var flagged = ReadBoolean(element, "flagged") ?? ReadFlag(element, "flagged") ?? false;
        var attach = (ReadInteger(element, "attachments_count") ?? 0) > 0 ||
                     ReadBoolean(element, "attach") == true ||
                     ReadFlag(element, "attach") == true;

        var (senderName, senderEmail) = ReadSender(element);

        return new MailRuMessageSummary(
            id,
            subject,
            snippet,
            senderName,
            senderEmail,
            date,
            size,
            folder is null || folder < int.MinValue || folder > int.MaxValue
                ? null
                : checked((int)folder.Value),
            unread,
            flagged,
            attach);
    }

    private static (string Name, string Email) ReadSender(JsonElement element)
    {
        if (element.TryGetProperty("correspondents", out var correspondents) &&
            correspondents.ValueKind == JsonValueKind.Object &&
            correspondents.TryGetProperty("from", out var from) &&
            from.ValueKind == JsonValueKind.Array)
        {
            foreach (var sender in from.EnumerateArray())
            {
                if (sender.ValueKind != JsonValueKind.Object)
                    continue;

                return (
                    Decode(ReadString(sender, "name") ?? string.Empty),
                    Decode(ReadString(sender, "email") ?? string.Empty));
            }
        }

        if (element.TryGetProperty("from", out var directFrom))
        {
            if (directFrom.ValueKind == JsonValueKind.String)
                return (string.Empty, Decode(directFrom.GetString() ?? string.Empty));

            if (directFrom.ValueKind == JsonValueKind.Object)
            {
                return (
                    Decode(ReadString(directFrom, "name") ?? string.Empty),
                    Decode(ReadString(directFrom, "email") ?? string.Empty));
            }
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
        if (!element.TryGetProperty("id", out var value))
            return false;

        id = value.ValueKind switch
        {
            JsonValueKind.String => value.GetString() ?? string.Empty,
            JsonValueKind.Number => value.GetRawText(),
            _ => string.Empty
        };
        return id.Length > 0;
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
            return number;

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
