using System.Windows;
using System.Windows.Media;
using Microsoft.Win32;

namespace MailRuDesktop.App;

internal static class ThemeManager
{
    public static AppThemeMode CurrentMode { get; private set; } = AppThemeMode.System;
    public static bool IsDarkEffective { get; private set; }

    public static void Apply(AppThemeMode mode)
    {
        CurrentMode = mode;
        IsDarkEffective = mode switch
        {
            AppThemeMode.Dark => true,
            AppThemeMode.Light => false,
            _ => IsSystemDark()
        };

        var resources = Application.Current.Resources;

        if (IsDarkEffective)
        {
            resources["AppWindowBrush"] = Brush("#1E1E1E");
            resources["AppPanelBrush"] = Brush("#252526");
            resources["AppControlBrush"] = Brush("#2D2D30");
            resources["AppTextBrush"] = Brush("#F2F2F2");
            resources["AppMutedTextBrush"] = Brush("#B7B7B7");
            resources["AppBorderBrush"] = Brush("#4A4A4A");
            resources["AppSelectionBrush"] = Brush("#3A5F8A");
        }
        else
        {
            resources["AppWindowBrush"] = Brush("#FFFFFF");
            resources["AppPanelBrush"] = Brush("#FFFFFF");
            resources["AppControlBrush"] = Brush("#FFFFFF");
            resources["AppTextBrush"] = Brush("#202124");
            resources["AppMutedTextBrush"] = Brush("#70757A");
            resources["AppBorderBrush"] = Brush("#D6DCE5");
            resources["AppSelectionBrush"] = Brush("#DCEBFA");
        }
    }

    private static SolidColorBrush Brush(string hex) =>
        new((Color)ColorConverter.ConvertFromString(hex));

    private static bool IsSystemDark()
    {
        try
        {
            using var key = Registry.CurrentUser.OpenSubKey(
                @"Software\Microsoft\Windows\CurrentVersion\Themes\Personalize");
            var value = key?.GetValue("AppsUseLightTheme");
            return value is int intValue && intValue == 0;
        }
        catch
        {
            return false;
        }
    }
}
