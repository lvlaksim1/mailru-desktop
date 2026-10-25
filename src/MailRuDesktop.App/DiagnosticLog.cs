using System.IO;
using System.Text;
using System.Text.RegularExpressions;

namespace MailRuDesktop.App;

internal static class DiagnosticLog
{
    private static readonly object Sync = new();

    public static void Write(string category, string? reason, string? account = null)
    {
        if (string.IsNullOrWhiteSpace(reason))
            return;

        try
        {
            var directory = Path.Combine(
                Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
                "MailRuDesktop");
            Directory.CreateDirectory(directory);

            var path = Path.Combine(directory, "diagnostics.log");
            var safeCategory = Sanitize(category);
            var safeReason = Sanitize(reason);
            // Store the verified operation account only when it is supplied
            // by the caller. Never infer it from the currently selected UI account.
            var safeAccount = account is not null &&
                Regex.IsMatch(account, @"\A[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}\z",
                    RegexOptions.CultureInvariant)
                ? account.ToLowerInvariant()
                : null;
            if (safeAccount is not null)
                safeReason += "; account=" + safeAccount;

            lock (Sync)
            {
                File.AppendAllText(
                    path,
                    $"{DateTimeOffset.Now:O}\t{safeCategory}\t{safeReason}{Environment.NewLine}",
                    new UTF8Encoding(false));
            }
        }
        catch
        {
            // Diagnostics must never break the mail client.
        }
    }

    private static string Sanitize(string value)
    {
        var normalized = value
            .Replace("\r", " ", StringComparison.Ordinal)
            .Replace("\n", " ", StringComparison.Ordinal)
            .Replace("\t", " ", StringComparison.Ordinal)
            .Trim();

        normalized = Regex.Replace(
            normalized,
            @"(?i)\b(access_token|refresh_token|token|password|cookie)=([^&;\s]+)",
            "$1=<redacted>");

        normalized = Regex.Replace(
            normalized,
            @"(?i)\b(Mpop|ssdc|sdcs)=([^;\s]+)",
            "$1=<redacted>");

        return normalized.Length <= 2000
            ? normalized
            : normalized[..2000] + "…";
    }
}
