using System.IO;
using System.Text;

namespace MailRuDesktop.App;

internal static class DiagnosticLog
{
    private static readonly object Sync = new();

    public static void Write(string category, string? reason)
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

    private static string Sanitize(string value) =>
        value
            .Replace("\r", " ", StringComparison.Ordinal)
            .Replace("\n", " ", StringComparison.Ordinal)
            .Replace("\t", " ", StringComparison.Ordinal)
            .Trim();
}
