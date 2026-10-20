using System.Net;

namespace MailRuDesktop.App;

/// <summary>
/// Links in the app-authored conversation wrapper are inert Internet URLs.
/// Native WebView2 intercepts navigation BEFORE it leaves the current frame.
/// This permits clicks with JavaScript disabled for all incoming mail.
/// A fresh per-view nonce keeps links within untrusted mail bodies ineligible.
/// </summary>
internal static class ConversationActionNavigation
{
    private const string Host = "actions.mailru-desktop.invalid";
    private const string Path = "/conversation-action";

    internal static string Create(string action, string id, string nonce)
    {
        if (action is not ("reply" or "forward") ||
            string.IsNullOrWhiteSpace(id) || id.Length > 512 ||
            string.IsNullOrWhiteSpace(nonce))
            throw new ArgumentException("Неверные параметры команды письма.");
        return "https://" + Host + Path +
               "?action=" + Uri.EscapeDataString(action) +
               "&id=" + Uri.EscapeDataString(id) +
               "&nonce=" + Uri.EscapeDataString(nonce);
    }

    internal static bool IsCommandUrl(string? raw)
    {
        if (!Uri.TryCreate(raw, UriKind.Absolute, out var uri)) return false;
        return uri.Scheme == Uri.UriSchemeHttps &&
               string.Equals(uri.Host, Host, StringComparison.OrdinalIgnoreCase) &&
               string.Equals(uri.AbsolutePath, Path, StringComparison.Ordinal) &&
               uri.Port == 443;
    }

    internal static bool TryParse(string? raw, string? expectedNonce,
        out string action, out string id)
    {
        action = "";
        id = "";
        if (!IsCommandUrl(raw) || string.IsNullOrWhiteSpace(expectedNonce))
            return false;
        try
        {
            var uri = new Uri(raw!);
            var parts = uri.Query.TrimStart('?').Split('&',
                StringSplitOptions.RemoveEmptyEntries);
            if (parts.Length != 3 || uri.Fragment.Length != 0) return false;
            var values = new Dictionary<string, string>(StringComparer.Ordinal);
            foreach (var part in parts)
            {
                var at = part.IndexOf('=');
                if (at <= 0) return false;
                var key = Uri.UnescapeDataString(part[..at]);
                var value = Uri.UnescapeDataString(part[(at + 1)..]);
                if (!values.TryAdd(key, value)) return false;
            }
            if (!values.TryGetValue("action", out var candidateAction) ||
                !values.TryGetValue("id", out var candidateId) ||
                !values.TryGetValue("nonce", out var nonce) ||
                !string.Equals(nonce, expectedNonce, StringComparison.Ordinal) ||
                candidateAction is not ("reply" or "forward") ||
                candidateId.Length is < 1 or > 512)
                return false;
            action = candidateAction;
            id = candidateId;
            return true;
        }
        catch (Exception ex) when (ex is ArgumentException or UriFormatException)
        {
            return false;
        }
    }
}
