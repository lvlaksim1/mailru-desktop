using System.Runtime.InteropServices;
using System.Windows;
using System.Windows.Interop;
using System.Windows.Media;
using Microsoft.Win32;

namespace MailRuDesktop.App;

internal static class ThemeManager
{
    private const int DwmUseImmersiveDarkMode = 20;
    private const int DwmUseImmersiveDarkModeLegacy = 19;
    private static bool _initialized;

    public static AppThemeMode CurrentMode { get; private set; } = AppThemeMode.System;
    public static bool IsDarkEffective { get; private set; }

    public static string ReaderBackgroundHtml => IsDarkEffective ? "#1E1E1E" : "#FFFFFF";
    public static string ReaderForegroundHtml => IsDarkEffective ? "#F2F2F2" : "#202124";
    public static string ReaderMutedHtml => IsDarkEffective ? "#B7B7B7" : "#70757A";
    public static string ReaderLinkHtml => IsDarkEffective ? "#6CB6FF" : "#0B57D0";

    public static void Initialize()
    {
        if (_initialized)
            return;

        _initialized = true;

        EventManager.RegisterClassHandler(
            typeof(Window),
            FrameworkElement.LoadedEvent,
            new RoutedEventHandler(OnWindowLoaded));

        SystemParameters.StaticPropertyChanged += (_, _) =>
        {
            if (CurrentMode == AppThemeMode.System &&
                Application.Current?.Dispatcher is { } dispatcher)
            {
                dispatcher.BeginInvoke(() => Apply(AppThemeMode.System));
            }
        };

        if (Application.Current is { } app)
        {
            app.Activated += (_, _) =>
            {
                if (CurrentMode == AppThemeMode.System)
                    Apply(AppThemeMode.System);
            };
        }
    }

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
            SetBrush(resources, "AppWindowBrush", "#1E1E1E");
            SetBrush(resources, "AppPanelBrush", "#252526");
            SetBrush(resources, "AppControlBrush", "#2D2D30");
            SetBrush(resources, "AppControlHoverBrush", "#3A3A3D");
            SetBrush(resources, "AppControlPressedBrush", "#454548");
            SetBrush(resources, "AppTextBrush", "#F2F2F2");
            SetBrush(resources, "AppMutedTextBrush", "#B7B7B7");
            SetBrush(resources, "AppDisabledTextBrush", "#7F7F7F");
            SetBrush(resources, "AppBorderBrush", "#4A4A4A");
            SetBrush(resources, "AppSelectionBrush", "#365F91");
            SetBrush(resources, "AppSelectionTextBrush", "#FFFFFF");
            SetBrush(resources, "AppAccentBrush", "#4EA1FF");
            SetBrush(resources, "AppAccentTextBrush", "#FFFFFF");
        }
        else
        {
            SetBrush(resources, "AppWindowBrush", "#FFFFFF");
            SetBrush(resources, "AppPanelBrush", "#FFFFFF");
            SetBrush(resources, "AppControlBrush", "#FFFFFF");
            SetBrush(resources, "AppControlHoverBrush", "#F3F5F7");
            SetBrush(resources, "AppControlPressedBrush", "#E8EBEF");
            SetBrush(resources, "AppTextBrush", "#202124");
            SetBrush(resources, "AppMutedTextBrush", "#70757A");
            SetBrush(resources, "AppDisabledTextBrush", "#9AA0A6");
            SetBrush(resources, "AppBorderBrush", "#D6DCE5");
            SetBrush(resources, "AppSelectionBrush", "#DCEBFA");
            SetBrush(resources, "AppSelectionTextBrush", "#202124");
            SetBrush(resources, "AppAccentBrush", "#0D6EFD");
            SetBrush(resources, "AppAccentTextBrush", "#FFFFFF");
        }

        ApplySystemBrushAliases(resources);

        foreach (Window window in Application.Current.Windows)
            ApplyNativeWindowTheme(window);
    }

    private static void OnWindowLoaded(object sender, RoutedEventArgs e)
    {
        if (sender is Window window)
            ApplyNativeWindowTheme(window);
    }

    private static void SetBrush(ResourceDictionary resources, string key, string hex)
    {
        var brush = new SolidColorBrush((Color)ColorConverter.ConvertFromString(hex));
        brush.Freeze();
        resources[key] = brush;
    }
    private static void ApplySystemBrushAliases(ResourceDictionary resources)
    {
        // WPF's built-in templates still consult SystemColors. Redirect those
        // keys to the application palette so controls added later inherit the
        // theme even when they do not yet have an explicit MailRu style.
        var window = (Brush)resources["AppWindowBrush"];
        var panel = (Brush)resources["AppPanelBrush"];
        var control = (Brush)resources["AppControlBrush"];
        var text = (Brush)resources["AppTextBrush"];
        var muted = (Brush)resources["AppDisabledTextBrush"];
        var border = (Brush)resources["AppBorderBrush"];
        var selection = (Brush)resources["AppSelectionBrush"];
        var selectionText = (Brush)resources["AppSelectionTextBrush"];

        resources[SystemColors.WindowBrushKey] = window;
        resources[SystemColors.WindowTextBrushKey] = text;
        resources[SystemColors.ControlBrushKey] = control;
        resources[SystemColors.ControlTextBrushKey] = text;
        resources[SystemColors.ControlLightBrushKey] = panel;
        resources[SystemColors.ControlDarkBrushKey] = border;
        resources[SystemColors.ControlDarkDarkBrushKey] = border;
        resources[SystemColors.GrayTextBrushKey] = muted;
        resources[SystemColors.HighlightBrushKey] = selection;
        resources[SystemColors.HighlightTextBrushKey] = selectionText;
        resources[SystemColors.ActiveBorderBrushKey] = border;
        resources[SystemColors.InactiveBorderBrushKey] = border;
        resources[SystemColors.MenuBrushKey] = panel;
        resources[SystemColors.MenuTextBrushKey] = text;
    }


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

    private static void ApplyNativeWindowTheme(Window window)
    {
        try
        {
            var handle = new WindowInteropHelper(window).Handle;
            if (handle == IntPtr.Zero)
                return;

            var enabled = IsDarkEffective ? 1 : 0;
            if (DwmSetWindowAttribute(
                    handle,
                    DwmUseImmersiveDarkMode,
                    ref enabled,
                    Marshal.SizeOf<int>()) != 0)
            {
                DwmSetWindowAttribute(
                    handle,
                    DwmUseImmersiveDarkModeLegacy,
                    ref enabled,
                    Marshal.SizeOf<int>());
            }
        }
        catch
        {
            // Older Windows versions may not support immersive title bars.
        }
    }

    [DllImport("dwmapi.dll")]
    private static extern int DwmSetWindowAttribute(
        IntPtr hwnd,
        int dwAttribute,
        ref int pvAttribute,
        int cbAttribute);
}
