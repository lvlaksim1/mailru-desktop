using System.Globalization;
using System.Text;

namespace MailRuDesktop.App;

/// <summary>
/// A bounded, structured view of a confirmed event=4. Fields may be omitted
/// by PushMe; they are not proof that the remote mailbox still contains a mail.
/// </summary>
internal sealed record PushMailEvent(
    string Account, string? MessageId, int? FolderId, string? Sender,
    string? Subject, string? Snippet, DateTimeOffset? ReceivedAt,
    bool? HasAttachment, bool? Important)
{
    internal static PushMailEvent? Parse(byte[] payload)
    {
        if (!PushWire.IsMailNewMessage(payload)) return null;
        var fields = new Dictionary<string, string>(StringComparer.Ordinal);
        foreach (var record in PushWire.Parse(payload))
        {
            if (record.Field != 7 || record.Bytes is null) continue;
            var values = PushWire.Parse(record.Bytes).ToArray();
            var key = values.FirstOrDefault(x => x.Field == 1).Bytes;
            var value = values.FirstOrDefault(x => x.Field == 2).Bytes;
            if (key is null || value is null || key.Length is < 1 or > 64 ||
                value.Length > 4096) continue;
            var keyText = Encoding.UTF8.GetString(key);
            // Do not persist or log arbitrary fields, especially ack/open URLs.
            if (keyText is "account" or "id" or "folder_id" or "sender_orig" or
                "sender" or "text" or "snippet" or "uts" or "has_attachment" or
                "importance")
                fields[keyText] = Encoding.UTF8.GetString(value);
        }
        string? Read(string key, int max = 512) =>
            fields.TryGetValue(key, out var raw) && !string.IsNullOrWhiteSpace(raw)
                ? raw.Trim()[..Math.Min(max, raw.Trim().Length)] : null;
        var account = Read("account", 320);
        if (account is null || !account.Contains('@', StringComparison.Ordinal))
            return null;
        var folder = int.TryParse(Read("folder_id", 24), NumberStyles.Integer,
            CultureInfo.InvariantCulture, out var folderNumber)
                ? folderNumber : (int?)null;
        DateTimeOffset? when = null;
        if (long.TryParse(Read("uts", 24), NumberStyles.Integer,
            CultureInfo.InvariantCulture, out var seconds))
        {
            try { when = DateTimeOffset.FromUnixTimeSeconds(seconds); }
            catch (ArgumentOutOfRangeException) { }
        }
        return new PushMailEvent(account, Read("id", 200), folder,
            Read("sender_orig") ?? Read("sender"), Read("text", 320),
            Read("snippet", 500), when,
            fields.ContainsKey("has_attachment") ? Read("has_attachment") == "1" : null,
            fields.ContainsKey("importance") ? Read("importance") == "1" : null);
    }

    internal static string MailboxLabel(PushMailEvent mail) => mail.Account;

    internal static string SenderAndSubject(PushMailEvent mail)
    {
        var sender = string.IsNullOrWhiteSpace(mail.Sender)
            ? "Отправитель не указан" : mail.Sender;
        var subject = string.IsNullOrWhiteSpace(mail.Subject)
            ? "(без темы)" : mail.Subject;
        return sender + " — " + subject;
    }

    internal static string PreviewLine(PushMailEvent mail)
    {
        var detail = mail.Snippet ?? "";
        var tags = new List<string>();
        if (mail.HasAttachment == true) tags.Add("📎 Вложения");
        if (mail.Important == true) tags.Add("★ Важное");
        return tags.Count == 0 ? detail :
            string.IsNullOrEmpty(detail) ? string.Join(" · ", tags) :
            detail + " · " + string.Join(" · ", tags);
    }
}
