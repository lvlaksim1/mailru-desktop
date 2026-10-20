using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

/// <summary>
/// Address policy for replies to verified grouped correspondence:
/// only recipients explicitly present in To/Cc of individual messages.
/// Never add From or Bcc, and never infer participants from quoted text.
/// </summary>
public static class ConversationReplyAddresses
{
    public static string[] Collect(IEnumerable<MailRuFullMessage> messages,
        string? currentMailbox)
    {
        var addresses = new List<string>();
        var seen = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
        var self = Normalize(currentMailbox);
        foreach (var message in messages)
        {
            foreach (var raw in message.To.Concat(message.Cc))
            {
                var address = Normalize(raw);
                if (address is not null &&
                    !string.Equals(address, self, StringComparison.OrdinalIgnoreCase) &&
                    seen.Add(address))
                    addresses.Add(address);
            }
        }
        return addresses.ToArray();
    }

    // If the selected (possibly last) letter was sent by this mailbox, its
    // From address is never a recipient. Use only verified To/Cc; when the
    // full-message response omitted them, reuse summary To/Cc metadata.
    public static string[] CollectSelected(
        MailRuMessageSummary source, MailRuFullMessage? full, string? currentMailbox)
    {
        var to = full is { To.Count: > 0 } ? full.To : source.ToAddresses;
        var cc = full is { Cc.Count: > 0 } ? full.Cc : source.CcAddresses;
        var seen = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
        var own = Normalize(currentMailbox);
        return to.Concat(cc).Select(Normalize)
            .Where(address => address is not null &&
                !string.Equals(address, own, StringComparison.OrdinalIgnoreCase) &&
                seen.Add(address))
            .Select(address => address!).ToArray();
    }

    public static bool IsOwnSender(string? sender, string? currentMailbox)
    {
        var address = Normalize(sender);
        var own = Normalize(currentMailbox);
        return address is not null && own is not null &&
               string.Equals(address, own, StringComparison.OrdinalIgnoreCase);
    }

    private static string? Normalize(string? raw) =>
        !string.IsNullOrWhiteSpace(raw) &&
        System.Net.Mail.MailAddress.TryCreate(raw.Trim(), out var parsed)
            ? parsed.Address : null;
}
