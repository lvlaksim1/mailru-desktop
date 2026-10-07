using System.Net;
using System.Text.Json;

namespace MailRuDesktop.Protocol;

public sealed record MailRuIncomingAttachment(
    string Id,
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
                throw new MailRuProtocolException(
                    "Full-message response does not contain a recognizable message object.");

            var id = ReadString(message, "id") ?? fallbackId;
            var subject = Decode(ReadString(message, "subject") ?? "(без темы)");
            var date = ReadInteger(message, "date");

            ReadBody(message, out var html, out var text);

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
            throw new MailRuProtocolException(
                $"Full-message response is not valid JSON: {ex.Message}");
        }
    }

    private static bool TryFindMessageObject(JsonElement element, out JsonElement message)
    {
        if (element.ValueKind == JsonValueKind.Object)
        {
            if (LooksLikeMessage(element))
            {
                message = element;
                return true;
            }

            // Prefer actual entries from body.messages before recursively matching
            // a nested "body" object that only contains text/html.
            if (element.TryGetProperty("messages", out var messages) &&
                messages.ValueKind == JsonValueKind.Array)
            {
                foreach (var item in messages.EnumerateArray())
                {
                    if (item.ValueKind == JsonValueKind.Object && LooksLikeMessage(item))
                    {
                        message = item;
                        return true;
                    }
                }
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

    private static bool LooksLikeMessage(JsonElement element)
    {
        var hasIdentity =
            element.TryGetProperty("id", out _) ||
            element.TryGetProperty("subject", out _) ||
            element.TryGetProperty("date", out _) ||
            element.TryGetProperty("correspondents", out _);

        if (!hasIdentity)
            return false;

        if (element.TryGetProperty("html", out _) ||
            element.TryGetProperty("text", out _) ||
            element.TryGetProperty("attaches", out _) ||
            element.TryGetProperty("attachments", out _))
        {
            return true;
        }

        return element.TryGetProperty("body", out var body) &&
               body.ValueKind == JsonValueKind.Object &&
               (body.TryGetProperty("html", out _) ||
                body.TryGetProperty("text", out _));
    }

    private static void ReadBody(JsonElement message, out string html, out string text)
    {
        html = DecodeJsonString(ReadString(message, "html") ?? string.Empty);
        text = DecodeJsonString(ReadString(message, "text") ?? string.Empty);

        if (message.TryGetProperty("body", out var body) &&
            body.ValueKind == JsonValueKind.Object)
        {
            if (string.IsNullOrWhiteSpace(html))
                html = DecodeJsonString(ReadString(body, "html") ?? string.Empty);
            if (string.IsNullOrWhiteSpace(text))
                text = DecodeJsonString(ReadString(body, "text") ?? string.Empty);
        }
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
                var id = Decode(ReadString(element, "id") ?? string.Empty);
                var name = Decode(
                    ReadString(element, "ContentName") ??
                    ReadString(element, "content_name") ??
                    ReadString(element, "name") ??
                    ReadString(element, "filename") ??
                    "attachment");

                var contentType =
                    ReadString(element, "content_type") ??
                    ReadString(element, "contentType") ??
                    ReadString(element, "mime_type") ??
                    ReadString(element, "mime") ??
                    ReadString(element, "type") ??
                    "application/octet-stream";

                var size =
                    ReadInteger(element, "size") ??
                    ReadInteger(element, "filesize");

                var download =
                    ReadString(element, "download") ??
                    ReadString(element, "url");

                if (string.IsNullOrWhiteSpace(download) &&
                    element.TryGetProperty("href", out var href))
                {
                    if (href.ValueKind == JsonValueKind.Object)
                        download = ReadString(href, "download");
                    else if (href.ValueKind == JsonValueKind.String)
                        download = href.GetString();
                }

                if (!string.IsNullOrWhiteSpace(download))
                    download = NormalizeDownloadUrl(download);

                // The current verified download path requires Mail.ru's
                // attachment id. Objects without an id are metadata/MIME helper
                // entries from the full-message response and are not downloadable
                // by the application. Do not expose them as user attachments.
                if (string.IsNullOrWhiteSpace(id))
                    return;

                var key = $"{id}\n{name}\n{download}";
                if (seen.Add(key))
                {
                    result.Add(new MailRuIncomingAttachment(
                        id,
                        name,
                        contentType,
                        download ?? string.Empty,
                        size));

                    // Do not recursively treat MIME/service metadata nested inside
                    // an already-recognized attachment as additional user files.
                    return;
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

    private static bool LooksLikeAttachment(JsonElement element)
    {
        var hasName =
            element.TryGetProperty("ContentName", out _) ||
            element.TryGetProperty("content_name", out _) ||
            element.TryGetProperty("name", out _) ||
            element.TryGetProperty("filename", out _);

        if (!hasName)
            return false;

        return element.TryGetProperty("id", out _) ||
               element.TryGetProperty("href", out _) ||
               element.TryGetProperty("download", out _) ||
               element.TryGetProperty("url", out _) ||
               element.TryGetProperty("content_type", out _) ||
               element.TryGetProperty("mime_type", out _);
    }

    private static string NormalizeDownloadUrl(string value)
    {
        value = DecodeJsonString(value);

        if (value.StartsWith("//", StringComparison.Ordinal))
            return "https:" + value;

        if (Uri.TryCreate(value, UriKind.Absolute, out _))
            return value;

        // AJ-only mode does not synthesize external Mail.ru hosts for
        // relative attachment links. Such links remain unresolved until an
        // aj-https.mail.ru download endpoint is verified.
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

    private static IReadOnlyList<string> ReadCorrespondentEmails(
        JsonElement message,
        string group) =>
        ReadCorrespondents(message, group)
            .Select(x =>
                string.IsNullOrWhiteSpace(x.Name)
                    ? x.Email
                    : string.IsNullOrWhiteSpace(x.Email)
                        ? x.Name
                        : $"{x.Name} <{x.Email}>")
            .Where(x => !string.IsNullOrWhiteSpace(x))
            .ToArray();

    private static List<(string Name, string Email)> ReadCorrespondents(
        JsonElement message,
        string group)
    {
        var result = new List<(string Name, string Email)>();

        if (message.TryGetProperty("correspondents", out var correspondents) &&
            correspondents.ValueKind == JsonValueKind.Object &&
            correspondents.TryGetProperty(group, out var values))
        {
            CollectCorrespondents(values, result);
            if (result.Count > 0)
                return result;
        }

        if (message.TryGetProperty(group, out var direct))
            CollectCorrespondents(direct, result);

        return result;
    }

    private static void CollectCorrespondents(
        JsonElement values,
        List<(string Name, string Email)> result)
    {
        if (values.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in values.EnumerateArray())
                CollectCorrespondents(item, result);
            return;
        }

        if (values.ValueKind == JsonValueKind.String)
        {
            var email = Decode(values.GetString() ?? string.Empty);
            if (!string.IsNullOrWhiteSpace(email))
                result.Add((string.Empty, email));
            return;
        }

        if (values.ValueKind != JsonValueKind.Object)
            return;

        var name = Decode(ReadString(values, "name") ?? string.Empty);
        var emailAddress = Decode(ReadString(values, "email") ?? string.Empty);
        if (!string.IsNullOrWhiteSpace(name) || !string.IsNullOrWhiteSpace(emailAddress))
            result.Add((name, emailAddress));
    }

    private static string Decode(string value) =>
        WebUtility.HtmlDecode(DecodeJsonString(value));

    private static string DecodeJsonString(string value) =>
        value
            .Replace("\\/", "/", StringComparison.Ordinal)
            .Replace("\\n", "\n", StringComparison.Ordinal)
            .Replace("\\r", "\r", StringComparison.Ordinal)
            .Replace("\\t", "\t", StringComparison.Ordinal)
            .Replace("\\\"", "\"", StringComparison.Ordinal);

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

        if (value.ValueKind == JsonValueKind.Number &&
            value.TryGetInt64(out var number))
        {
            return number;
        }

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
            return result
                .OrderBy(x => x, StringComparer.CurrentCultureIgnoreCase)
                .ToArray();
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
                if (property.NameEquals("emails"))
                {
                    if (property.Value.ValueKind == JsonValueKind.Array)
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
                    else if (property.Value.ValueKind == JsonValueKind.String &&
                             !string.IsNullOrWhiteSpace(property.Value.GetString()))
                    {
                        result.Add(property.Value.GetString()!);
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
