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

internal sealed class AppSettingsStore
{
    private readonly string _path;

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
        try
        {
            if (!File.Exists(_path))
                return AppThemeMode.System;

            var json = File.ReadAllText(_path, Encoding.UTF8);
            var state = JsonSerializer.Deserialize<SettingsState>(json);
            return Enum.TryParse<AppThemeMode>(state?.Theme, true, out var mode)
                ? mode
                : AppThemeMode.System;
        }
        catch
        {
            return AppThemeMode.System;
        }
    }

    public void SaveTheme(AppThemeMode mode)
    {
        var json = JsonSerializer.Serialize(
            new SettingsState { Theme = mode.ToString() },
            new JsonSerializerOptions { WriteIndented = true });

        var temp = _path + ".tmp";
        File.WriteAllText(temp, json, new UTF8Encoding(false));
        File.Move(temp, _path, true);
    }

    private sealed class SettingsState
    {
        public string Theme { get; set; } = AppThemeMode.System.ToString();
    }
}
