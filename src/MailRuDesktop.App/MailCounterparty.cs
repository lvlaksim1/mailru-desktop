using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

/// <summary>
/// The list shows correspondents, not the sender column. Original From/To/Cc
/// data remains intact for replies, search and server operations.
/// </summary>
internal static class MailCounterparty
{
    internal static MailRuMessageSummary Resolve(
        MailRuMessageSummary message, string? account, bool sentFolder,
        MailRuConversation? conversation = null)
    {
        var own = account?.Trim() ?? "";
        var sender = EmailAddressText.Extract(message.SenderEmail).FirstOrDefault()
            ?? message.SenderEmail.Trim();
        var fromSelf = own.Length > 0 &&
                       sender.Equals(own, StringComparison.OrdinalIgnoreCase);
        var outgoing = sentFolder || fromSelf;

        var parties = new List<(string Display, string Address)>();
        void Add(string? value)
        {
            if (string.IsNullOrWhiteSpace(value)) return;
            var address = EmailAddressText.Extract(value).FirstOrDefault();
            if (string.IsNullOrWhiteSpace(address)) return;
            if (parties.Any(x => x.Address.Equals(address,
                    StringComparison.OrdinalIgnoreCase))) return;
            parties.Add((value.Trim(), address));
        }

        if (outgoing)
        {
            foreach (var entry in message.ToAddresses.Concat(message.CcAddresses))
                Add(entry);
        }
        else
        {
            Add(string.IsNullOrWhiteSpace(message.SenderName)
                ? message.SenderEmail
                : message.SenderName + " <" + message.SenderEmail + ">");
        }

        // A grouped item represents a verified conversation: add actual
        // member senders and known recipients, never inferred/quoted addresses.
        if (conversation is { Members.Count: > 1 })
        {
            foreach (var member in conversation.Members)
                if (!string.IsNullOrWhiteSpace(member.SenderEmail))
                    Add(string.IsNullOrWhiteSpace(member.Sender)
                        ? member.SenderEmail
                        : member.Sender + " <" + member.SenderEmail + ">");
            foreach (var entry in message.ToAddresses.Concat(message.CcAddresses))
                Add(entry);
        }

        if (parties.Any(x => !x.Address.Equals(own,
                StringComparison.OrdinalIgnoreCase)))
            parties.RemoveAll(x => x.Address.Equals(own,
                StringComparison.OrdinalIgnoreCase));

        if (parties.Count == 0)
        {
            // Missing compact metadata must not be filled by a guessed
            // recipient. A sent item with an absent To is enriched separately.
            return message with
            {
                CorrespondentText = outgoing ? "Получатель не указан" : null,
                CorrespondentEmail = outgoing ? "" : message.SenderEmail
            };
        }

        return message with
        {
            CorrespondentText = string.Join(", ", parties.Select(x => x.Display)),
            CorrespondentEmail = parties[0].Address
        };
    }

    internal static bool NeedsOutgoingRecipients(
        MailRuMessageSummary message, string? account, bool sentFolder)
    {
        if (message.ToAddresses.Count > 0 || message.CcAddresses.Count > 0)
            return false;
        var sender = EmailAddressText.Extract(message.SenderEmail).FirstOrDefault()
            ?? message.SenderEmail.Trim();
        return sentFolder || (!string.IsNullOrWhiteSpace(account) &&
            sender.Equals(account, StringComparison.OrdinalIgnoreCase));
    }
}
