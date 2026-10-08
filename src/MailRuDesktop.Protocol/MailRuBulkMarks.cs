using System.Text.Json;

namespace MailRuDesktop.Protocol;

public sealed partial class MailRuClient
{
    /// <summary>One Mail.ru marks request for all selected messages, grouped by folder.</summary>
    public async Task<MailRuCommandResult> MarkMessagesReadBatchAsync(
        string accessToken, string email,
        IReadOnlyCollection<MailRuMessageSummary> messages, int fallbackFolderId,
        CancellationToken cancellationToken = default)
    {
        RequireToken(accessToken);
        ArgumentNullException.ThrowIfNull(messages);

        var unread = messages.Where(message => message.Unread)
            .GroupBy(message => message.FolderId ?? fallbackFolderId)
            .Select(group => new
            {
                name = "unread",
                set = Array.Empty<string>(),
                unset = group.Select(message => message.Id)
                    .Distinct(StringComparer.Ordinal).ToArray(),
                folder = group.Key
            }).ToArray();

        if (unread.Length == 0)
            return new MailRuCommandResult(true, "{\"status\":200,\"skipped\":true}");

        return await PostFormCommandAsync("/api/v1/messages/marks",
            accessToken, email,
            new Dictionary<string, string>
            {
                ["marks"] = JsonSerializer.Serialize(unread)
            }, cancellationToken).ConfigureAwait(false);
    }
}
