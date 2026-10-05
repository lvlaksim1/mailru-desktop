using System.Net;
using System.Text.Json;

namespace MailRuDesktop.Protocol;

public sealed record MailRuIncomingAttachment(
    string Name,
    string ContentType,
    string DownloadUrl,
    long? SizeBytes)
{
    public string DisplayName => string.IsNullOrWhiteSpace(Name) ? "attachment" : Name;

    public string DisplayMeta
    {
        get
        {
            var size = SizeBytes is null
                ? string.Empty
                : SizeBytes.Value < 1024
                    ? $"{SizeBytes.Value} Б"
                    : SizeBytes.Value < 1024 * 1024
                        ? $"{SizeBytes.Value / 1024d:0.#} КБ"
                        : $"{SizeBytes.Value / (1024d * 1024d):0.#} МБ";

            return string.IsNullOrWhiteSpace(size)
                ? ContentType
                : $"{ContentType} · {size}";
        }
    }
}

public sealed record MailRuFullMessage(
    string Id,
    string Subject,
    string FromName,
    string FromEmail,
    IReadOnlyList<string> To,
    IReadOnlyList<string> Cc,
    long? DateUnix,
    string Html,
    string Text,
    IReadOnlyList<MailRuIncomingAttachment> Attachments,
    string RawJson)
{
    public string SenderDisplay =>
        string.IsNullOrWhiteSpace(FromName)
            ? FromEmail
            : string.IsNullOrWhiteSpace(FromEmail)
                ? FromName
                : $"{FromName} <{FromEmail}>";

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
            catch
            {
                return DateUnix.Value.ToString();
            }
        }
    }
}

public static class MailRuFullMessageParser
{
    public static MailRuFullMessage Parse(string payload, string fallbackId)
    {
        if (string.IsNullOrWhiteSpace(payload))
            throw new MailRuProtocolException("Full-message response is empty.");

        try
        {
            using var document = JsonDocument.Parse(payload);
            if (!TryFindMessageObject(document.RootElement, out var message))
                throw new MailRuProtocolException("Full-message response does not contain a recognizable message object.");

            var id = ReadString(message, "id") ?? fallbackId;
            var subject = Decode(ReadString(message, "subject") ?? "(без темы)");
            var html = DecodeJsonString(ReadString(message, "html") ?? string.Empty);
            var text = DecodeJsonString(ReadString(message, "text") ?? string.Empty);
            var date = ReadInteger(message, "date");
            var (fromName, fromEmail) = ReadSingleCorrespondent(message, "from");
            var to = ReadCorrespondentEmails(message, "to");
            var cc = ReadCorrespondentEmails(message, "cc");
            var attachments = ReadAttachments(message);

            return new MailRuFullMessage(
                id,
                subject,
                fromName,
                fromEmail,
                to,
                cc,
                date,
                html,
                text,
                attachments,
                payload);
        }
        catch (JsonException ex)
        {
            throw new MailRuProtocolException($"Full-message response is not valid JSON: {ex.Message}");
        }
    }

    private static bool TryFindMessageObject(JsonElement element, out JsonElement message)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            var hasSubject = element.TryGetProperty("subject", out _);
            var hasHtml = element.TryGetProperty("html", out _);
            var hasText = element.TryGetProperty("text", out _);
            var hasDate = element.TryGetProperty("date", out _);

            if ((hasSubject && (hasHtml || hasText)) || (hasDate && (hasHtml || hasText)))
            {
                message = element;
                return true;
            }

            foreach (var property in element.EnumerateObject())
            {
                if (TryFindMessageObject(property.Value, out message))
                    return true;
            }
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
            {
                if (TryFindMessageObject(item, out message))
                    return true;
            }
        }

        message = default;
        return false;
    }

    private static List<MailRuIncomingAttachment> ReadAttachments(JsonElement message)
    {
        var result = new List<MailRuIncomingAttachment>();
        var seen = new HashSet<string>(StringComparer.OrdinalIgnoreCase);

        if (message.TryGetProperty("attaches", out var attaches))
            CollectAttachments(attaches, result, seen);
        if (message.TryGetProperty("attachments", out var attachments))
            CollectAttachments(attachments, result, seen);

        return result;
    }

    private static void CollectAttachments(
        JsonElement element,
        List<MailRuIncomingAttachment> result,
        HashSet<string> seen)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            if (LooksLikeAttachment(element))
            {
                var name = Decode(ReadString(element, "name") ?? ReadString(element, "filename") ?? "attachment");
                var contentType =
                    ReadString(element, "content_type") ??
                    ReadString(element, "contentType") ??
                    ReadString(element, "mime") ??
                    "application/octet-stream";
                var size = ReadInteger(element, "size");

                var download = ReadString(element, "download");
                if (string.IsNullOrWhiteSpace(download) &&
                    element.TryGetProperty("href", out var href) &&
                    href.ValueKind == JsonValueKind.Object)
                {
                    download = ReadString(href, "download");
                }

                if (!string.IsNullOrWhiteSpace(download))
                {
                    download = NormalizeDownloadUrl(download);
                    var key = $"{name}\n{download}";
                    if (seen.Add(key))
                        result.Add(new MailRuIncomingAttachment(name, contentType, download, size));
                }
            }

            foreach (var property in element.EnumerateObject())
                CollectAttachments(property.Value, result, seen);
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
                CollectAttachments(item, result, seen);
        }
    }

    private static bool LooksLikeAttachment(JsonElement element) =>
        element.TryGetProperty("name", out _) &&
        (element.TryGetProperty("href", out _) ||
         element.TryGetProperty("download", out _) ||
         element.TryGetProperty("content_type", out _));

    private static string NormalizeDownloadUrl(string value)
    {
        value = DecodeJsonString(value);
        if (value.StartsWith("//", StringComparison.Ordinal))
            return "https:" + value;
        if (Uri.TryCreate(value, UriKind.Absolute, out _))
            return value;
        if (value.StartsWith("/", StringComparison.Ordinal))
            return "https://touch.mail.ru" + value;
        return value;
    }

    private static (string Name, string Email) ReadSingleCorrespondent(
        JsonElement message,
        string group)
    {
        var emails = ReadCorrespondents(message, group);
        return emails.Count == 0
            ? (string.Empty, string.Empty)
            : (emails[0].Name, emails[0].Email);
    }

    private static IReadOnlyList<string> ReadCorrespondentEmails(JsonElement message, string group) =>
        ReadCorrespondents(message, group)
            .Select(x => string.IsNullOrWhiteSpace(x.Name) ? x.Email : $"{x.Name} <{x.Email}>")
            .Where(x => !string.IsNullOrWhiteSpace(x))
            .ToArray();

    private static List<(string Name, string Email)> ReadCorrespondents(
        JsonElement message,
        string group)
    {
        var result = new List<(string Name, string Email)>();

        if (!message.TryGetProperty("correspondents", out var correspondents) ||
            correspondents.ValueKind != JsonValueKind.Object ||
            !correspondents.TryGetProperty(group, out var values) ||
            values.ValueKind != JsonValueKind.Array)
        {
            return result;
        }

        foreach (var item in values.EnumerateArray())
        {
            if (item.ValueKind != JsonValueKind.Object)
                continue;

            var name = Decode(ReadString(item, "name") ?? string.Empty);
            var email = Decode(ReadString(item, "email") ?? string.Empty);
            if (!string.IsNullOrWhiteSpace(name) || !string.IsNullOrWhiteSpace(email))
                result.Add((name, email));
        }

        return result;
    }

    private static string Decode(string value) =>
        WebUtility.HtmlDecode(DecodeJsonString(value));

    private static string DecodeJsonString(string value) =>
        value
            .Replace("\\/", "/", StringComparison.Ordinal)
            .Replace("\\n", "\n", StringComparison.Ordinal)
            .Replace("\\r", "\r", StringComparison.Ordinal)
            .Replace("\\t", "\t", StringComparison.Ordinal)
            .Replace("\\"", """, StringComparison.Ordinal);

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
}

public static class MailRuContactsParser
{
    public static IReadOnlyList<string> ParseEmails(string payload)
    {
        if (string.IsNullOrWhiteSpace(payload))
            return Array.Empty<string>();

        try
        {
            using var document = JsonDocument.Parse(payload);
            var result = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
            Collect(document.RootElement, result);
            return result.OrderBy(x => x, StringComparer.CurrentCultureIgnoreCase).ToArray();
        }
        catch (JsonException)
        {
            return Array.Empty<string>();
        }
    }

    private static void Collect(JsonElement element, HashSet<string> result)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            foreach (var property in element.EnumerateObject())
            {
                if (property.NameEquals("emails") &&
                    property.Value.ValueKind == JsonValueKind.Array)
                {
                    foreach (var email in property.Value.EnumerateArray())
                    {
                        if (email.ValueKind == JsonValueKind.String &&
                            !string.IsNullOrWhiteSpace(email.GetString()))
                        {
                            result.Add(email.GetString()!);
                        }
                    }
                }

                Collect(property.Value, result);
            }
        }
        else if (element.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in element.EnumerateArray())
                Collect(item, result);
        }
    }
}
