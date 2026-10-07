using System.IO;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

internal enum AppThemeMode
{
    System,
    Light,
    Dark
}

internal sealed class GridLengthSetting
{
    public double Value { get; set; }
    public string UnitType { get; set; } = "Pixel";
}

internal sealed class UserInterfaceState
{
    public double? WindowLeft { get; set; }
    public double? WindowTop { get; set; }
    public double? WindowWidth { get; set; }
    public double? WindowHeight { get; set; }
    public bool WindowMaximized { get; set; }

    public double NavigationPaneWidth { get; set; } = 230;
    public double AccountPaneWidth { get; set; } = 220;
    public double MailListPaneWidth { get; set; } = 455;
    public double ContactsListPaneWidth { get; set; } = 390;

    public bool FolderManageExpanded { get; set; }

    public GridLengthSetting TimeColumn { get; set; } = new() { Value = 58 };
    public GridLengthSetting FlagColumn { get; set; } = new() { Value = 28 };
    public GridLengthSetting UnreadColumn { get; set; } = new() { Value = 26 };
    public GridLengthSetting ThreadCountColumn { get; set; } = new() { Value = 32 };
    public GridLengthSetting AttachmentColumn { get; set; } = new() { Value = 28 };
    public GridLengthSetting SenderColumn { get; set; } = new() { Value = 1.15, UnitType = "Star" };
    public GridLengthSetting SubjectColumn { get; set; } = new() { Value = 2.15, UnitType = "Star" };
}

internal sealed class AccountRailLayoutEntryState
{
    public string Kind { get; set; } = "account";
    public string Id { get; set; } = Guid.NewGuid().ToString("N");
    public string? Login { get; set; }
    public string? Title { get; set; }
    public bool IsCollapsed { get; set; }
}

internal sealed class SavedSignature
{
    public string Id { get; set; } = Guid.NewGuid().ToString("N");
    public string Name { get; set; } = string.Empty;
    public string Body { get; set; } = string.Empty;

    public override string ToString() => Name;
}

internal sealed class SavedMailTemplate
{
    public string Id { get; set; } = Guid.NewGuid().ToString("N");
    public string Name { get; set; } = string.Empty;
    public string Subject { get; set; } = string.Empty;
    public string Body { get; set; } = string.Empty;
    public List<string> Attachments { get; set; } = [];

    public override string ToString() => Name;
}

internal sealed class AppSettingsStore
{
    private readonly string _path;
    private readonly object _sync = new();

    public AppSettingsStore()
    {
        var directory = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop");
        Directory.CreateDirectory(directory);
        _path = Path.Combine(directory, "settings.json");
    }

    public AppThemeMode LoadTheme()
    {
        var state = LoadState();
        return Enum.TryParse<AppThemeMode>(state.Theme, true, out var mode)
            ? mode
            : AppThemeMode.Dark;
    }

    public void SaveTheme(AppThemeMode mode)
    {
        lock (_sync)
        {
            var state = LoadStateCore();
            state.Theme = mode.ToString();
            SaveStateCore(state);
        }
    }

    public UserInterfaceState LoadUserInterfaceState() =>
        LoadState().UserInterface ?? new UserInterfaceState();

    public void SaveUserInterfaceState(UserInterfaceState userInterface)
    {
        ArgumentNullException.ThrowIfNull(userInterface);

        lock (_sync)
        {
            var state = LoadStateCore();
            state.UserInterface = userInterface;
            SaveStateCore(state);
        }
    }

    public List<AccountRailLayoutEntryState> LoadAccountRailLayout() =>
        LoadState().AccountRailLayout
            ?.Select(CloneAccountRailEntry)
            .ToList()
        ?? [];

    public void SaveAccountRailLayout(IEnumerable<AccountRailLayoutEntryState> entries)
    {
        ArgumentNullException.ThrowIfNull(entries);

        lock (_sync)
        {
            var state = LoadStateCore();
            state.AccountRailLayout = entries
                .Select(CloneAccountRailEntry)
                .ToList();
            SaveStateCore(state);
        }
    }

    public List<SavedSignature> LoadSignatures() =>
        LoadState().Signatures
            ?.Select(CloneSignature)
            .ToList()
        ?? [];

    public void SaveSignatures(IEnumerable<SavedSignature> signatures)
    {
        ArgumentNullException.ThrowIfNull(signatures);

        lock (_sync)
        {
            var state = LoadStateCore();
            state.Signatures = signatures
                .Select(CloneSignature)
                .ToList();
            SaveStateCore(state);
        }
    }

    public List<SavedMailTemplate> LoadMailTemplates() =>
        LoadState().MailTemplates
            ?.Select(CloneTemplate)
            .ToList()
        ?? [];

    public void SaveMailTemplates(IEnumerable<SavedMailTemplate> templates)
    {
        ArgumentNullException.ThrowIfNull(templates);

        lock (_sync)
        {
            var state = LoadStateCore();
            state.MailTemplates = templates
                .Select(CloneTemplate)
                .ToList();
            SaveStateCore(state);
        }
    }

    private SettingsState LoadState()
    {
        lock (_sync)
            return LoadStateCore();
    }

    private SettingsState LoadStateCore()
    {
        try
        {
            if (!File.Exists(_path))
                return new SettingsState();

            var json = File.ReadAllText(_path, Encoding.UTF8);
            return JsonSerializer.Deserialize<SettingsState>(json) ??
                   new SettingsState();
        }
        catch
        {
            return new SettingsState();
        }
    }

    private void SaveStateCore(SettingsState state)
    {
        var json = JsonSerializer.Serialize(
            state,
            new JsonSerializerOptions { WriteIndented = true });

        var temp = _path + ".tmp";
        File.WriteAllText(temp, json, new UTF8Encoding(false));
        File.Move(temp, _path, true);
    }

    private static AccountRailLayoutEntryState CloneAccountRailEntry(
        AccountRailLayoutEntryState value) =>
        new()
        {
            Kind = value.Kind,
            Id = value.Id,
            Login = value.Login,
            Title = value.Title,
            IsCollapsed = value.IsCollapsed
        };

    private static SavedSignature CloneSignature(SavedSignature value) =>
        new()
        {
            Id = value.Id,
            Name = value.Name,
            Body = value.Body
        };

    private static SavedMailTemplate CloneTemplate(SavedMailTemplate value) =>
        new()
        {
            Id = value.Id,
            Name = value.Name,
            Subject = value.Subject,
            Body = value.Body,
            Attachments = value.Attachments.ToList()
        };

    private sealed class SettingsState
    {
        public string Theme { get; set; } = AppThemeMode.Dark.ToString();
        public UserInterfaceState? UserInterface { get; set; }
        public List<AccountRailLayoutEntryState>? AccountRailLayout { get; set; }
        public List<SavedSignature>? Signatures { get; set; }
        public List<SavedMailTemplate>? MailTemplates { get; set; }
    }
}
