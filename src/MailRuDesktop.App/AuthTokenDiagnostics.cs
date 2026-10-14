using System.Globalization;

namespace MailRuDesktop.App;

/// <summary>
/// Builds safe, per-account authorization traces. No address, token, password,
/// URL, cookie or raw server body is included in the diagnostic event.
/// </summary>
internal static class AuthTokenDiagnostics
{
    public static string AccountLabel(string login, IReadOnlyList<string> savedLogins)
    {
        for (var i = 0; i < savedLogins.Count; i++)
        {
            if (string.Equals(savedLogins[i], login, StringComparison.OrdinalIgnoreCase))
                return "ACCOUNT_" + (i + 1).ToString("D3", CultureInfo.InvariantCulture);
        }

        return "ACCOUNT_UNKNOWN";
    }

    public static string Format(
        string accountLabel,
        string flow,
        string stage,
        string outcome,
        int? httpStatus = null,
        string? kind = null)
    {
        // Call sites must supply fixed identifiers only, never server strings.
        var result = "account=" + accountLabel +
                     "; flow=" + flow +
                     "; stage=" + stage +
                     "; result=" + outcome +
                     "; http=" + (httpStatus?.ToString(CultureInfo.InvariantCulture) ?? "not_available");
        return string.IsNullOrWhiteSpace(kind) ? result : result + "; kind=" + kind;
    }
}
