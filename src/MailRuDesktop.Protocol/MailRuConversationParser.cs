using System.Globalization;
using System.Text.Json;

namespace MailRuDesktop.Protocol;

/// <summary>
/// Reads actual individual message IDs from an existing smart-thread response.
/// Thread representations are alternate folder views of the same message,
/// NOT independent correspondence, and quoted text is never parsed as mail.
/// </summary>
public sealed record MailRuConversationMember(
    string Id, string Sender, string SenderEmail, string Subject,
    string Snippet, long? DateUnix, int? FolderId);

public sealed record MailRuConversation(
    string CurrentId, string ThreadId, int? ServerCount,
    IReadOnlyList<MailRuConversationMember> Members)
{
    public int? VerifiedCount => ServerCount ?? (Members.Count > 1 ? Members.Count : null);
}

public static class MailRuConversationParser
{
    public static IReadOnlyDictionary<string, MailRuConversation> Parse(string raw)
    {
        using var document = JsonDocument.Parse(raw);
        var result = new Dictionary<string, MailRuConversation>(StringComparer.Ordinal);
        Scan(document.RootElement, result);
        return result;
    }

    private static void Scan(JsonElement node, Dictionary<string, MailRuConversation> result)
    {
        if (node.ValueKind == JsonValueKind.Object)
        {
            if (node.TryGetProperty("threads", out var threads) &&
                threads.ValueKind == JsonValueKind.Array)
            {
                foreach (var thread in threads.EnumerateArray())
                    ParseThread(thread, result);
            }
            foreach (var prop in node.EnumerateObject())
                if (!prop.NameEquals("threads"))
                    Scan(prop.Value, result);
        }
        else if (node.ValueKind == JsonValueKind.Array)
            foreach (var item in node.EnumerateArray()) Scan(item, result);
    }

    private static void ParseThread(
        JsonElement thread, Dictionary<string, MailRuConversation> index)
    {
        if (thread.ValueKind != JsonValueKind.Object) return;
        var threadId = Value(thread, "id") ?? "";
        var byId = new Dictionary<string, MailRuConversationMember>(StringComparer.Ordinal);

        if (thread.TryGetProperty("base_message", out var baseMessage) &&
            baseMessage.ValueKind == JsonValueKind.Object)
            AddMessage(baseMessage, byId);

        if (thread.TryGetProperty("messages", out var messages) &&
            messages.ValueKind == JsonValueKind.Array)
            foreach (var message in messages.EnumerateArray())
                AddMessage(message, byId);

        // A representation is an alternate view of an existing member.
        // Only enrich an already recognized ID; never count it as a new mail.
        if (thread.TryGetProperty("representations", out var reps) &&
            reps.ValueKind == JsonValueKind.Array)
            foreach (var item in reps.EnumerateArray())
            {
                var id = Value(item, "id");
                if (id is not null && byId.TryGetValue(id, out var existing))
                    byId[id] = Merge(existing, Extract(item, id));
            }

        // message_id_last is a verifiable message ID, even when the compact
        // response omits the body or metadata of that last message.
        var lastId = Value(thread, "message_id_last");
        if (!string.IsNullOrWhiteSpace(lastId) && !byId.ContainsKey(lastId))
            byId[lastId] = new MailRuConversationMember(
                lastId, "", "", "", "", null, null);

        if (byId.Count == 0) return;
        int? declared = null;
        foreach (var key in new[] { "messages_count", "message_count",
                                    "messages_total", "count" })
        {
            if (ReadPositive(thread, key) is int value)
            {
                declared = value;
                break;
            }
        }
        if (declared is null && thread.TryGetProperty("base_message", out var b) &&
            b.ValueKind == JsonValueKind.Object)
            declared = ReadPositive(b, "messages_count") ??
                       ReadPositive(b, "message_count");

        var ordered = byId.Values
            .OrderBy(m => m.DateUnix ?? long.MinValue)
            .ThenBy(m => m.Id, StringComparer.Ordinal).ToArray();
        foreach (var item in ordered)
        {
            var data = new MailRuConversation(item.Id, threadId,
                Math.Max(declared ?? 0, ordered.Length), ordered);
            if (!index.TryGetValue(item.Id, out var previous) ||
                (previous.VerifiedCount ?? 0) < (data.VerifiedCount ?? 0))
                index[item.Id] = data;
        }
    }

    private static void AddMessage(
        JsonElement element, Dictionary<string, MailRuConversationMember> members)
    {
        if (element.ValueKind != JsonValueKind.Object) return;
        var id = Value(element, "id");
        if (string.IsNullOrWhiteSpace(id)) return;
        var candidate = Extract(element, id);
        members[id] = members.TryGetValue(id, out var existing)
            ? Merge(existing, candidate) : candidate;
    }

    private static MailRuConversationMember Merge(
        MailRuConversationMember previous, MailRuConversationMember candidate) =>
        previous with
        {
            Sender = previous.Sender.Length > 0 ? previous.Sender : candidate.Sender,
            SenderEmail = previous.SenderEmail.Length > 0
                ? previous.SenderEmail : candidate.SenderEmail,
            Subject = previous.Subject.Length > 0 ? previous.Subject : candidate.Subject,
            Snippet = previous.Snippet.Length > 0 ? previous.Snippet : candidate.Snippet,
            DateUnix = previous.DateUnix ?? candidate.DateUnix,
            FolderId = previous.FolderId ?? candidate.FolderId
        };

    private static MailRuConversationMember Extract(JsonElement item, string id)
    {
        var senderName = "";
        var senderEmail = "";
        if (item.TryGetProperty("correspondents", out var correspondents) &&
            correspondents.ValueKind == JsonValueKind.Object &&
            correspondents.TryGetProperty("from", out var from))
            ReadSender(from, ref senderName, ref senderEmail);
        if (senderEmail.Length == 0 && item.TryGetProperty("from", out var direct))
            ReadSender(direct, ref senderName, ref senderEmail);

        var date = ReadLong(item, "date");
        var folder = ReadLong(item, "folder");
        return new MailRuConversationMember(id, senderName, senderEmail,
            Value(item, "subject") ?? "", Value(item, "snippet") ?? "",
            date,
            folder is >= int.MinValue and <= int.MaxValue ? (int?)folder.Value : null);
    }

    private static void ReadSender(
        JsonElement from, ref string name, ref string email)
    {
        if (from.ValueKind == JsonValueKind.Array)
        {
            foreach (var item in from.EnumerateArray())
            {
                ReadSender(item, ref name, ref email);
                if (email.Length > 0) return;
            }
        }
        else if (from.ValueKind == JsonValueKind.Object)
        {
            name = Value(from, "name") ?? name;
            email = Value(from, "email") ?? email;
        }
        else if (from.ValueKind == JsonValueKind.String)
            email = from.GetString() ?? email;
    }

    private static string? Value(JsonElement item, string key)
    {
        if (item.ValueKind != JsonValueKind.Object ||
            !item.TryGetProperty(key, out var value)) return null;
        return value.ValueKind switch
        {
            JsonValueKind.String => value.GetString(),
            JsonValueKind.Number => value.GetRawText(),
            _ => null
        };
    }

    private static long? ReadLong(JsonElement item, string key) =>
        long.TryParse(Value(item, key), NumberStyles.Integer,
            CultureInfo.InvariantCulture, out var number) ? number : null;

    private static int? ReadPositive(JsonElement item, string key) =>
        int.TryParse(Value(item, key), NumberStyles.Integer,
            CultureInfo.InvariantCulture, out var number) && number > 0
                ? number : null;
}
