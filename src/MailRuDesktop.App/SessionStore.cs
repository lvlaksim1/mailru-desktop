using System.Security.Cryptography;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

internal sealed record PersistedMailRuSession(
    int SchemaVersion,
    string Login,
    string AccessToken,
    string? RefreshToken,
    DateTimeOffset SavedAtUtc);

internal static class SessionStore
{
    private const int CurrentSchemaVersion = 1;
    private static readonly byte[] Entropy =
        Encoding.UTF8.GetBytes("MailRuDesktop/session/v1");

    public static string DataRoot =>
        Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop");

    private static string SessionPath => Path.Combine(DataRoot, "session.dat");

    public static PersistedMailRuSession? Load()
    {
        try
        {
            if (!File.Exists(SessionPath))
                return null;

            var protectedBytes = File.ReadAllBytes(SessionPath);
            var clearBytes = ProtectedData.Unprotect(
                protectedBytes,
                Entropy,
                DataProtectionScope.CurrentUser);

            var session = JsonSerializer.Deserialize<PersistedMailRuSession>(clearBytes);
            if (session is null ||
                session.SchemaVersion != CurrentSchemaVersion ||
                string.IsNullOrWhiteSpace(session.Login) ||
                string.IsNullOrWhiteSpace(session.AccessToken))
            {
                return null;
            }

            return session;
        }
        catch (CryptographicException)
        {
            return null;
        }
        catch (IOException)
        {
            return null;
        }
        catch (UnauthorizedAccessException)
        {
            return null;
        }
        catch (JsonException)
        {
            return null;
        }
    }

    public static void Save(string login, string accessToken, string? refreshToken)
    {
        if (string.IsNullOrWhiteSpace(login))
            throw new ArgumentException("Login is required.", nameof(login));
        if (string.IsNullOrWhiteSpace(accessToken))
            throw new ArgumentException("Access token is required.", nameof(accessToken));

        Directory.CreateDirectory(DataRoot);

        var session = new PersistedMailRuSession(
            CurrentSchemaVersion,
            login,
            accessToken,
            refreshToken,
            DateTimeOffset.UtcNow);

        var clearBytes = JsonSerializer.SerializeToUtf8Bytes(session);
        var protectedBytes = ProtectedData.Protect(
            clearBytes,
            Entropy,
            DataProtectionScope.CurrentUser);

        var tempPath = SessionPath + ".tmp";
        try
        {
            File.WriteAllBytes(tempPath, protectedBytes);
            File.Move(tempPath, SessionPath, true);
        }
        finally
        {
            try
            {
                if (File.Exists(tempPath))
                    File.Delete(tempPath);
            }
            catch
            {
                // Best-effort cleanup only.
            }
        }
    }

    public static void Clear()
    {
        try
        {
            if (File.Exists(SessionPath))
                File.Delete(SessionPath);
        }
        catch
        {
            // A stale session file must not prevent application shutdown or re-login.
        }
    }
}
