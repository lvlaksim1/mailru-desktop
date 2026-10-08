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
    private const int DwmCaptionColor = 35;
    private const int DwmTextColor = 36;
    private static bool _initialized;

    public static AppThemeMode CurrentMode { get; private set; } = AppThemeMode.System;
    public static bool IsDarkEffective { get; private set; }
    public static event EventHandler? ThemeChanged;

    private static IReadOnlyDictionary<string, string> _currentColors =
        ThemePalette.Defaults(dark: true);

    public static string GetHex(string key) => _currentColors[key];

    public static string ReaderBackgroundHtml => GetHex("AppReaderBrush");
    public static string ReaderForegroundHtml => GetHex("AppTextBrush");
    public static string ReaderMutedHtml => GetHex("AppMutedTextBrush");
    public static string ReaderLinkHtml => GetHex("AppLinkBrush");

    public static string ReaderScrollbarCss
    {
        get
        {
            var track = GetHex("AppScrollTrackBrush");
            var thumb = GetHex("AppScrollThumbBrush");
            var arrow = GetHex("AppScrollArrowBrush");
            var up = $"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 12 12'%3E%3Cpath d='M2 8L6 4L10 8' stroke='%23{arrow[1..]}' stroke-width='1.5' fill='none'/%3E%3C/svg%3E";
            var down = $"data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 12 12'%3E%3Cpath d='M2 4L6 8L10 4' stroke='%23{arrow[1..]}' stroke-width='1.5' fill='none'/%3E%3C/svg%3E";
            return $"::-webkit-scrollbar{{width:12px;height:12px;background:{track};}}" +
                   $"::-webkit-scrollbar-track{{background:{track};}}" +
                   $"::-webkit-scrollbar-thumb{{background:{thumb};border:2px solid {track};border-radius:7px;}}" +
                   "::-webkit-scrollbar-thumb:hover{filter:brightness(1.2);}" +
                   $"::-webkit-scrollbar-button{{background-color:{track};height:12px;width:12px;}}" +
                   $"::-webkit-scrollbar-button:vertical:decrement{{background-image:url(\"{up}\");background-size:10px 10px;background-position:center;background-repeat:no-repeat;}}" +
                   $"::-webkit-scrollbar-button:vertical:increment{{background-image:url(\"{down}\");background-size:10px 10px;background-position:center;background-repeat:no-repeat;}}";
        }
    }

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

        // All 26 resources are bound by an immutable role catalog. A user can
        // change only each role's hexadecimal value, never its mapped controls.
        var resources = Application.Current.Resources;
        _currentColors = ThemePalette.Merge(IsDarkEffective,
            new AppSettingsStore().LoadPaletteOverrides(IsDarkEffective));

        foreach (var role in ThemePalette.Roles)
            SetBrush(resources, role.Key, _currentColors[role.Key]);

        ApplyFontSize(new AppSettingsStore().LoadInterfaceFontSize());

        ApplySystemBrushAliases(resources);

        foreach (Window window in Application.Current.Windows)
        {
            window.FontSize = (double)resources["AppFontBodySize"];
            ApplyNativeWindowTheme(window);
        }

        ThemeChanged?.Invoke(null, EventArgs.Empty);
    }

    public static void ApplyFontSize(int requested)
    {
        if (Application.Current is null)
            return;

        var resources = Application.Current.Resources;
        var sizes = ThemeTypography.Resolve(requested);
        foreach (var (key, size) in sizes)
            resources[key] = size;
        // Portraits scale with typography, without changing their source URLs.
        resources["AppAvatarSize"] = ThemeTypography.AvatarSize(requested);
        foreach (Window window in Application.Current.Windows)
            window.FontSize = sizes["AppFontBodySize"];
    }

    private static void OnWindowLoaded(object sender, RoutedEventArgs e)
    {
        if (sender is Window window)
        {
            if (Application.Current?.Resources["AppFontBodySize"] is double size)
                window.FontSize = size;
            ApplyNativeWindowTheme(window);
        }
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
        var control = (Brush)resources["AppInputBrush"];
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

    private static readonly DependencyProperty ChromeAttachedProperty =
        DependencyProperty.RegisterAttached(
            "ChromeAttached", typeof(bool), typeof(ThemeManager),
            new PropertyMetadata(false));

    // Attach before Show/ShowDialog: Windows can otherwise paint a white
    // native caption on the first activation before the Loaded event.
    public static void AttachWindowChrome(Window window)
    {
        if ((bool)window.GetValue(ChromeAttachedProperty))
            return;
        window.SetValue(ChromeAttachedProperty, true);
        window.SourceInitialized += (_, _) => RefreshWindowChrome(window);
        window.Loaded += (_, _) => RefreshWindowChrome(window);
        window.Activated += (_, _) => window.Dispatcher.BeginInvoke(
            new Action(() => RefreshWindowChrome(window)));
    }

    public static void RefreshWindowChrome(Window window) =>
        ApplyNativeWindowTheme(window);

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

            // Native titlebars are part of the same user-customizable palette.
            var caption = ToColorRef(GetHex("AppDialogBrush"));
            var text = ToColorRef(GetHex("AppTextBrush"));

            DwmSetWindowAttribute(
                handle,
                DwmCaptionColor,
                ref caption,
                Marshal.SizeOf<int>());
            DwmSetWindowAttribute(
                handle,
                DwmTextColor,
                ref text,
                Marshal.SizeOf<int>());
        }
        catch
        {
            // Older Windows versions may not support immersive title bars.
        }
    }

    private static int ToColorRef(string hex)
    {
        var r = Convert.ToInt32(hex.Substring(1, 2), 16);
        var g = Convert.ToInt32(hex.Substring(3, 2), 16);
        var b = Convert.ToInt32(hex.Substring(5, 2), 16);
        return r | (g << 8) | (b << 16);
    }

    [DllImport("dwmapi.dll")]
    private static extern int DwmSetWindowAttribute(
        IntPtr hwnd,
        int dwAttribute,
        ref int pvAttribute,
        int cbAttribute);
}
