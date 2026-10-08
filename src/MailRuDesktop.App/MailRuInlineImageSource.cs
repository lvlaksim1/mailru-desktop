using System.Text.RegularExpressions;

namespace MailRuDesktop.App;

/// <summary>
/// Validates Mail.ru's inline message-image URLs before an authenticated
/// attachment request. Rejects other hosts, mailbox identities and messages.
/// This parses URLs only; no network request or credential is involved.
/// </summary>
internal static class MailRuInlineImageSource
{
    private static readonly Regex HostPattern = new(
        @"^af[0-9]+\.mail\.ru$", RegexOptions.IgnoreCase | RegexOptions.Compiled);

    public static bool TryParse(
        Uri uri, string? currentMessageId, string? currentMailbox,
        out string attachmentId)
    {
        attachmentId = string.Empty;
        if (uri.Scheme != Uri.UriSchemeHttps ||
            !HostPattern.IsMatch(uri.Host) ||
            !uri.AbsolutePath.Equals("/cgi-bin/readmsg", StringComparison.OrdinalIgnoreCase) ||
            string.IsNullOrWhiteSpace(currentMessageId) ||
            string.IsNullOrWhiteSpace(currentMailbox))
            return false;

        var query = new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase);
        foreach (var field in uri.Query.TrimStart('?').Split('&'))
        {
            var pair = field.Split('=', 2);
            if (pair.Length != 2)
                continue;
            var key = Uri.UnescapeDataString(pair[0]);
            var value = Uri.UnescapeDataString(pair[1]);
            if (!query.TryAdd(key, value))
                return false;
        }

        if (!query.TryGetValue("mode", out var mode) ||
            !mode.Equals("attachment", StringComparison.OrdinalIgnoreCase) ||
            !query.TryGetValue("id", out var reference) ||
            !query.TryGetValue("email", out var mailbox) ||
            !mailbox.Equals(currentMailbox, StringComparison.OrdinalIgnoreCase))
            return false;

        var segments = reference.Split(';');
        if (segments.Length < 3 || segments.Length > 5 ||
            !segments[0].Equals(currentMessageId, StringComparison.Ordinal) ||
            segments.Skip(1).Any(segment => segment.Length == 0 ||
                segment.Any(ch => ch is < '0' or > '9')))
            return false;

        attachmentId = string.Join(';', segments.Skip(1));
        return true;
    }
}
