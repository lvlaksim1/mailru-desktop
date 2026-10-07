using System.IO;
using System.Security.Cryptography;
using System.Text;

namespace MailRuDesktop.App;

internal static class MailRuAuthProfileStore
{
    private static readonly string RootPath = Path.Combine(
        Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
        "MailRuDesktop",
        "AuthProfiles");

    public static string GetProfilePath(string login)
    {
        ArgumentException.ThrowIfNullOrWhiteSpace(login);

        Directory.CreateDirectory(RootPath);

        var normalized = login.Trim().ToLowerInvariant();
        var hash = SHA256.HashData(Encoding.UTF8.GetBytes(normalized));
        var key = Convert.ToHexString(hash)[..32].ToLowerInvariant();

        return Path.Combine(RootPath, key);
    }

    public static async Task DeleteProfileAsync(string login)
    {
        if (string.IsNullOrWhiteSpace(login))
            return;

        var path = GetProfilePath(login);
        for (var attempt = 0; attempt < 12; attempt++)
        {
            try
            {
                if (Directory.Exists(path))
                    Directory.Delete(path, recursive: true);
                return;
            }
            catch
            {
                await Task.Delay(350);
            }
        }

        DiagnosticLog.Write(
            "auth_profile_cleanup",
            "persistent-profile-delete-deferred");
    }
}
