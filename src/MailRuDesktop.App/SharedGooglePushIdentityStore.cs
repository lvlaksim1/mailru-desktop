using System.IO;
using System.Security.Cryptography;
using System.Text.RegularExpressions;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

/// <summary>
/// One durable Google recipient per protected, independently named slot.
/// Device credentials, registration token and subscribed mailbox identifiers are
/// protected with the same current-user DPAPI boundary as AuthorizationStore.
/// </summary>
internal sealed class SharedGooglePushIdentityStore
{
    internal sealed record State(
        ulong DeviceId, ulong SecurityToken, string RegistrationToken,
        string[] SubscribedAccounts, string? PushMeCommonId = null);

    // Original APK: DeviceIdProviderImpl.getDeviceId() delegates to
    // CommonIdProvider. That ID is android_id + ":" +
    // MD5(concatenated Android Build.PRODUCT, BOARD, ... TAGS).
    //
    // Windows cannot read Settings.Secure.android_id or Android Build fields.
    // For the virtual Android sender used by this desktop app, generate its
    // own 16-hex installation ID and stable virtual Android Build fingerprint.
    // Persist the result under DPAPI; NEVER derive it from Google MCS ID.
    // This is a platform adaptation, not a claim to possess real Android IDs.
    private const string VirtualAndroidBuild =
        "MailRuDesktopVirtualAndroid13CompatibilityBuild-v1";

    internal static string ComposePushMeCommonId(string androidId, string buildProperties)
    {
        if (!Regex.IsMatch(androidId, "^[a-f0-9]{16}$", RegexOptions.CultureInvariant))
            throw new ArgumentException("Android-compatible ID must be 16 lowercase hex chars.");
        var md5 = Convert.ToHexString(
            MD5.HashData(Encoding.UTF8.GetBytes(buildProperties))).ToLowerInvariant();
        return androidId + ":" + md5;
    }

    internal static string GeneratePushMeCommonId() =>
        ComposePushMeCommonId(
            Convert.ToHexString(RandomNumberGenerator.GetBytes(8)).ToLowerInvariant(),
            VirtualAndroidBuild);

    internal static bool IsValidPushMeCommonId(string? value) =>
        value is not null &&
        Regex.IsMatch(value, "^[a-f0-9]{16}:[a-f0-9]{32}$",
            RegexOptions.CultureInvariant);

    internal static string AndroidIdFromCommonId(string commonId)
    {
        if (!IsValidPushMeCommonId(commonId))
            throw new ArgumentException("Not a valid PushMe CommonId.");
        return commonId[..16];
    }

    internal const string PrimaryRecipientId = "primary";
    private readonly string _file;

    internal static bool ValidRecipientId(string id) =>
        id == PrimaryRecipientId ||
        Regex.IsMatch(id, @"\A[a-f0-9]{12}\z", RegexOptions.CultureInvariant);

    private static string DataDirectory => Path.Combine(
        Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
        "MailRuDesktop");

    internal SharedGooglePushIdentityStore(
        string? directoryOverride = null, string recipientId = PrimaryRecipientId)
    {
        if (!ValidRecipientId(recipientId))
            throw new ArgumentException("Invalid Google recipient slot.", nameof(recipientId));
        var directory = directoryOverride ?? DataDirectory;
        _file = Path.Combine(directory, recipientId == PrimaryRecipientId
            ? "google-push-receiver.dat"
            : "google-push-receiver-" + recipientId + ".dat");
    }

    internal static string[] ListRecipientIds(string? directoryOverride = null)
    {
        var directory = directoryOverride ?? DataDirectory;
        if (!Directory.Exists(directory)) return [];
        var ids = new List<string>();
        if (File.Exists(Path.Combine(directory, "google-push-receiver.dat")))
            ids.Add(PrimaryRecipientId);
        foreach (var file in Directory.EnumerateFiles(directory,
                     "google-push-receiver-*.dat", SearchOption.TopDirectoryOnly))
        {
            var name = Path.GetFileNameWithoutExtension(file);
            var id = name["google-push-receiver-".Length..];
            if (ValidRecipientId(id) && id != PrimaryRecipientId)
                ids.Add(id);
        }
        return ids.Distinct(StringComparer.Ordinal).OrderBy(x =>
            x == PrimaryRecipientId ? "" : x, StringComparer.Ordinal).ToArray();
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
            state.SubscribedAccounts is null ||
            (state.PushMeCommonId is not null &&
             !IsValidPushMeCommonId(state.PushMeCommonId)))
            throw new InvalidDataException("Не удалось восстановить регистрацию Google.");
        return state;
    }

    internal void Save(State state)
    {
        if (state.DeviceId == 0 || state.SecurityToken == 0 ||
            string.IsNullOrWhiteSpace(state.RegistrationToken) ||
            (state.PushMeCommonId is not null &&
             !IsValidPushMeCommonId(state.PushMeCommonId)))
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
