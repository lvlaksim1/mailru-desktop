using System.IO;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

/// <summary>
/// Local, DPAPI-protected grouping of confirmed PushMe account registrations.
/// Groups are a Windows UI concept; the PushMe API registers per-account data,
/// not a server-side group object. Never persist OAuth or Google tokens here.
/// </summary>
internal sealed class PushGroupRegistryStore
{
    internal sealed record Group(
        string Id, string[] Accounts, DateTimeOffset ConfirmedUtc,
        string State = "CONFIRMED");
    internal sealed record Registry(
        bool ReceiveEnabled, Group[] Groups, bool LegacyImported = false);

    private readonly string _path;
    internal PushGroupRegistryStore(string? directoryOverride = null)
    {
        var directory = directoryOverride ?? Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop");
        _path = Path.Combine(directory, "push-groups.dat");
    }

    internal Registry Load()
    {
        if (!File.Exists(_path)) return new(false, []);
        var clear = AuthorizationStore.Dpapi.Unprotect(
            File.ReadAllText(_path, Encoding.UTF8));
        var registry = JsonSerializer.Deserialize<Registry>(clear)
            ?? throw new InvalidDataException("Реестр групп повреждён.");
        Validate(registry);
        return registry;
    }

    internal void Save(Registry registry)
    {
        Validate(registry);
        Directory.CreateDirectory(Path.GetDirectoryName(_path)!);
        var encrypted = AuthorizationStore.Dpapi.Protect(
            JsonSerializer.Serialize(registry));
        var temp = _path + ".tmp";
        File.WriteAllText(temp, encrypted, new UTF8Encoding(false));
        File.Move(temp, _path, true);
    }

    internal static void Validate(Registry registry)
    {
        if (registry.Groups is null) throw new InvalidDataException("Группы отсутствуют.");
        var uniqueIds = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
        var uniqueAccounts = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
        foreach (var group in registry.Groups)
        {
            if (string.IsNullOrWhiteSpace(group.Id) ||
                !uniqueIds.Add(group.Id) ||
                group.Accounts is null || group.Accounts.Length == 0 ||
                group.Accounts.Length > 50 ||
                (group.State != "CONFIRMED" && group.State != "IMPORTED" &&
                 group.State != "RECHECK_REQUIRED" && group.State != "EVENT_SEEN"))
                throw new InvalidDataException("Некорректная группа PushMe.");
            foreach (var login in group.Accounts)
                if (string.IsNullOrWhiteSpace(login) || !uniqueAccounts.Add(login))
                    throw new InvalidDataException("Аккаунт присутствует в нескольких группах.");
        }
    }

    internal static string[] Ungrouped(IEnumerable<string> authorized, Registry registry)
    {
        var used = new HashSet<string>(
            registry.Groups.SelectMany(g => g.Accounts),
            StringComparer.OrdinalIgnoreCase);
        return authorized.Where(a => !used.Contains(a)).ToArray();
    }

    internal static Registry Register(
        Registry prior, string[] selected, IReadOnlyCollection<string> accepted)
    {
        if (selected.Length == 0 ||
            selected.Distinct(StringComparer.OrdinalIgnoreCase).Count() != selected.Length)
            throw new ArgumentException("Некорректный состав группы.");
        var used = new HashSet<string>(
            prior.Groups.SelectMany(x => x.Accounts), StringComparer.OrdinalIgnoreCase);
        if (selected.Any(used.Contains))
            throw new InvalidOperationException("Выбранный аккаунт уже состоит в группе.");
        var confirmed = selected.Where(a => accepted.Contains(a,
            StringComparer.OrdinalIgnoreCase)).ToArray();
        if (confirmed.Length == 0) return prior;
        var group = new Group(Guid.NewGuid().ToString("N"), confirmed, DateTimeOffset.UtcNow);
        // A second batch may replace earlier server-side registrations;
        // until real events arrive for those groups, never present their
        // continuing delivery as verified.
        return prior with {
            Groups = prior.Groups.Select(old => old with {
                State = "RECHECK_REQUIRED"
            }).Append(group).ToArray(),
            LegacyImported = true
        };
    }

    /// <summary>
    /// Preserve a legacy v0.3.44 confirmed roster without silently
    /// overwriting or deleting its PushMe server subscriptions.
    /// The "IMPORTED" label indicates server confirmation predates this UI.
    /// </summary>
    internal Registry ImportLegacy(Registry prior, IEnumerable<string> existing)
    {
        // Run migration once only. Otherwise an unconfirmed write-ahead
        // network batch could be imported as a falsely confirmed group.
        if (prior.LegacyImported) return prior;
        var untracked = Ungrouped(existing, prior);
        if (untracked.Length == 0) return prior with { LegacyImported = true };
        var result = prior with
        {
            Groups = prior.Groups.Append(
                new Group(Guid.NewGuid().ToString("N"), untracked,
                    DateTimeOffset.UtcNow, "IMPORTED")).ToArray(),
            LegacyImported = true
        };
        Save(result);
        return result;
    }

    internal static Registry MarkObservedMail(Registry prior, string login)
    {
        return prior with {
            Groups = prior.Groups.Select(group =>
                group.Accounts.Contains(login, StringComparer.OrdinalIgnoreCase)
                    ? group with { State = "EVENT_SEEN" } : group).ToArray()
        };
    }

    internal static Registry RemoveAccounts(Registry prior, IEnumerable<string> removed)
    {
        var set = new HashSet<string>(removed, StringComparer.OrdinalIgnoreCase);
        var groups = prior.Groups.Select(g => g with
        {
            Accounts = g.Accounts.Where(a => !set.Contains(a)).ToArray()
        }).Where(g => g.Accounts.Length > 0).ToArray();
        return prior with { Groups = groups };
    }
}
