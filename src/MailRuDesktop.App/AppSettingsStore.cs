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

    // Optional directory is used only by isolated offline regression tests.
    public AppSettingsStore(string? directoryOverride = null)
    {
        var directory = directoryOverride ?? Path.Combine(
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

    // Enabled by default after user approved the permanent notification feature.
    // The switch is stored with existing app settings, not in the registry.
    public bool LoadBackgroundPushEnabled() => LoadState().BackgroundPushEnabled;

    // Controls Windows notification popups only; never stops PushMe delivery.
    public bool LoadTaskbarNotificationsEnabled() =>
        LoadState().TaskbarNotificationsEnabled;

    // Local UI replay filter only. Never changes MCS acknowledgments,
    // PushMe subscriptions, or saved Google recipients.
    public bool LoadReplaySuppressionEnabled() =>
        LoadState().ReplaySuppressionEnabled;

    public void SaveReplaySuppressionEnabled(bool enabled)
    {
        lock (_sync)
        {
            var state = LoadStateCore();
            state.ReplaySuppressionEnabled = enabled;
            SaveStateCore(state);
        }
    }

    public void SaveTaskbarNotificationsEnabled(bool value)
    {
        lock (_sync)
        {
            var state = LoadStateCore();
            state.TaskbarNotificationsEnabled = value;
            SaveStateCore(state);
        }
    }

    public void SaveBackgroundPushEnabled(bool value)
    {
        lock (_sync)
        {
            var state = LoadStateCore();
            state.BackgroundPushEnabled = value;
            SaveStateCore(state);
        }
    }

    public int LoadInterfaceFontSize() =>
        ThemeTypography.Normalize(LoadState().InterfaceFontSize);

    public void SaveInterfaceFontSize(int size)
    {
        if (size < ThemeTypography.MinimumSize ||
            size > ThemeTypography.MaximumSize)
            throw new ArgumentOutOfRangeException(nameof(size));

        lock (_sync)
        {
            var state = LoadStateCore();
            state.InterfaceFontSize = size;
            SaveStateCore(state);
        }
    }

    public Dictionary<string, string> LoadPaletteOverrides(bool dark)
    {
        var state = LoadState();
        var stored = dark ? state.DarkPalette : state.LightPalette;
        return new Dictionary<string, string>(
            ThemePalette.Merge(dark, stored), StringComparer.Ordinal);
    }

    public void SavePaletteOverride(bool dark, string key, string hex)
    {
        if (!ThemePalette.Roles.Any(role => role.Key == key) ||
            !ThemePalette.TryNormalize(hex, out var normalized))
            throw new ArgumentException("Недопустимый цвет или цветовая роль.");

        lock (_sync)
        {
            var state = LoadStateCore();
            var current = dark
                ? state.DarkPalette ?? new Dictionary<string, string>()
                : state.LightPalette ?? new Dictionary<string, string>();
            current[key] = normalized;
            if (dark)
                state.DarkPalette = current;
            else
                state.LightPalette = current;
            SaveStateCore(state);
        }
    }

    public void ResetPaletteOverride(bool dark, string? key = null)
    {
        if (key is not null && !ThemePalette.Roles.Any(role => role.Key == key))
            throw new ArgumentException("Неизвестная цветовая роль.");

        lock (_sync)
        {
            var state = LoadStateCore();
            var current = dark ? state.DarkPalette : state.LightPalette;
            if (key is null)
            {
                if (dark) state.DarkPalette = null;
                else state.LightPalette = null;
            }
            else if (current is not null)
            {
                current.Remove(key);
            }
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

    public string LoadAttachmentDownloadDirectory() =>
        LoadState().AttachmentDownloadDirectory is { Length: > 0 } value
            ? value
            : Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.UserProfile),
                "Downloads");

    public void SaveAttachmentDownloadDirectory(string directory)
    {
        if (string.IsNullOrWhiteSpace(directory))
            throw new ArgumentException("Укажите папку скачивания.");

        var full = Path.GetFullPath(directory.Trim());
        Directory.CreateDirectory(full);
        lock (_sync)
        {
            var state = LoadStateCore();
            state.AttachmentDownloadDirectory = full;
            SaveStateCore(state);
        }
    }

    public string LoadTemplateDirectory() =>
        LoadState().TemplateDirectory is { Length: > 0 } directory
            ? directory
            : Path.Combine(Environment.GetFolderPath(
                Environment.SpecialFolder.LocalApplicationData), "MailRuDesktop", "Templates");

    public void SaveTemplateDirectory(string directory)
    {
        if (string.IsNullOrWhiteSpace(directory))
            throw new ArgumentException("Укажите папку шаблонов.", nameof(directory));

        lock (_sync)
        {
            var state = LoadStateCore();
            state.TemplateDirectory = Path.GetFullPath(directory);
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
        public bool BackgroundPushEnabled { get; set; } = true;
        public bool TaskbarNotificationsEnabled { get; set; } = true;
        public bool ReplaySuppressionEnabled { get; set; } = true;
        public int InterfaceFontSize { get; set; } = ThemeTypography.DefaultSize;
        public Dictionary<string, string>? DarkPalette { get; set; }
        public Dictionary<string, string>? LightPalette { get; set; }
        public UserInterfaceState? UserInterface { get; set; }
        public List<AccountRailLayoutEntryState>? AccountRailLayout { get; set; }
        public List<SavedSignature>? Signatures { get; set; }
        public List<SavedMailTemplate>? MailTemplates { get; set; }
        public string? TemplateDirectory { get; set; }
        public string? AttachmentDownloadDirectory { get; set; }
    }
}
