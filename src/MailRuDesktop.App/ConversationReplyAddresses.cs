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

    private static string? Normalize(string? raw) =>
        !string.IsNullOrWhiteSpace(raw) &&
        System.Net.Mail.MailAddress.TryCreate(raw.Trim(), out var parsed)
            ? parsed.Address : null;
}
