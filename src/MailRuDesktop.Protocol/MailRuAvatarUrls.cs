namespace MailRuDesktop.Protocol;

/// <summary>
/// The public contact-picture endpoint is present in the official Mail.ru
/// Android client's extracted URL inventory. The image view falls back to
/// initials when this URL has no decodable picture.
/// </summary>
public static class MailRuAvatarUrls
{
    public static string? ForEmail(string? email)
    {
        if (string.IsNullOrWhiteSpace(email))
            return null;

        var value = email.Trim();
        if (value.Contains(' ') || value.Contains('\r') || value.Contains('\n') ||
            value.Count(c => c == '@') != 1)
            return null;

        return "https://filin.mail.ru/pic?email=" + Uri.EscapeDataString(value);
    }
}
