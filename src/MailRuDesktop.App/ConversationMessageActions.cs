using System.Text.Json;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

// All browser action IDs are untrusted input until checked against the
// server-verified conversation membership by the native reader.
internal static class ConversationMessageActions
{
    internal static bool TryParse(string json, out string action,
        out string id, out long? revision)
    {
        action = "";
        id = "";
        revision = null;
        try
        {
            using var doc = JsonDocument.Parse(json);
            var root = doc.RootElement;
            if (root.ValueKind != JsonValueKind.Object ||
                !root.TryGetProperty("kind", out var kind) ||
                kind.GetString() != "conversation-action" ||
                !root.TryGetProperty("action", out var value) ||
                !root.TryGetProperty("id", out var member))
                return false;
            action = value.GetString() ?? "";
            id = member.GetString() ?? "";
            if (action is not ("reply" or "forward") ||
                id.Length is < 1 or > 512) return false;
            if (root.TryGetProperty("revision", out var rev))
            {
                if (!rev.TryGetInt64(out var valueRev)) return false;
                revision = valueRev;
            }
            return true;
        }
        catch (Exception ex) when (ex is JsonException or InvalidOperationException)
        {
            return false;
        }
    }

    internal static bool IsVerifiedMember(MailRuConversation? conversation, string id) =>
        conversation is { VerifiedCount: > 1 } &&
        conversation.Members.Any(member =>
            string.Equals(member.Id, id, StringComparison.Ordinal));
}
