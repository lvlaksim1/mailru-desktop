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

    private sealed class SettingsState
    {
        public string Theme { get; set; } = AppThemeMode.Dark.ToString();
        public UserInterfaceState? UserInterface { get; set; }
    }
}
