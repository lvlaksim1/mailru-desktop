using System.IO;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

/// <summary>
/// Exactly one durable Google recipient per Windows user/application installation.
/// Device credentials, registration token and subscribed mailbox identifiers are
/// protected with the same current-user DPAPI boundary as AuthorizationStore.
/// </summary>
internal sealed class SharedGooglePushIdentityStore
{
    internal sealed record State(
        ulong DeviceId, ulong SecurityToken, string RegistrationToken,
        string[] SubscribedAccounts);

    private readonly string _file;

    internal SharedGooglePushIdentityStore(string? directoryOverride = null)
    {
        var directory = directoryOverride ?? Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop");
        _file = Path.Combine(directory, "google-push-receiver.dat");
    }

    internal State? Load()
    {
        if (!File.Exists(_file)) return null;
        // A corrupt or foreign-user DPAPI payload MUST NOT silently result in a
        // second registration; manual remediation is safer than orphaning tokens.
        var value = AuthorizationStore.Dpapi.Unprotect(
            File.ReadAllText(_file, Encoding.UTF8));
        var state = JsonSerializer.Deserialize<State>(value);
        if (state is null || state.DeviceId == 0 || state.SecurityToken == 0 ||
            string.IsNullOrWhiteSpace(state.RegistrationToken) ||
            state.SubscribedAccounts is null)
            throw new InvalidDataException("Не удалось восстановить регистрацию Google.");
        return state;
    }

    internal void Save(State state)
    {
        if (state.DeviceId == 0 || state.SecurityToken == 0 ||
            string.IsNullOrWhiteSpace(state.RegistrationToken))
            throw new ArgumentException("Недействительная регистрация Google.");
        var directory = Path.GetDirectoryName(_file)!;
        Directory.CreateDirectory(directory);
        var protectedText = AuthorizationStore.Dpapi.Protect(
            JsonSerializer.Serialize(state));
        var temporary = _file + ".tmp";
        File.WriteAllText(temporary, protectedText, new UTF8Encoding(false));
        File.Move(temporary, _file, true);
    }

    internal void Delete()
    {
        if (File.Exists(_file)) File.Delete(_file);
    }

    internal static string[] PendingAccountUnsubscriptions(
        IEnumerable<string> previouslySubscribed, IEnumerable<string> currentlyWanted)
    {
        var wanted = new HashSet<string>(currentlyWanted, StringComparer.OrdinalIgnoreCase);
        return previouslySubscribed.Where(login => !wanted.Contains(login))
            .Distinct(StringComparer.OrdinalIgnoreCase).ToArray();
    }
}
