using System.Windows;

namespace MailRuDesktop.App;

/// <summary>
/// Stores normal bounds even if a window was maximized on exit.
/// A window moved off the current virtual desktop is brought back on-screen.
/// </summary>
internal static class WindowGeometryPersistence
{
    public static void Restore(Window window, AppSettingsStore settings, string key)
    {
        var saved = settings.LoadWindowGeometry(key);
        if (saved is null || !double.IsFinite(saved.Width) ||
            !double.IsFinite(saved.Height) || saved.Width <= 0 || saved.Height <= 0)
            return;

        var screenWidth = Math.Max(window.MinWidth, SystemParameters.VirtualScreenWidth);
        var screenHeight = Math.Max(window.MinHeight, SystemParameters.VirtualScreenHeight);
        var width = Math.Clamp(saved.Width, window.MinWidth, screenWidth);
        var height = Math.Clamp(saved.Height, window.MinHeight, screenHeight);
        var screenLeft = SystemParameters.VirtualScreenLeft;
        var screenTop = SystemParameters.VirtualScreenTop;
        var proposedLeft = double.IsFinite(saved.Left) ? saved.Left : screenLeft;
        var proposedTop = double.IsFinite(saved.Top) ? saved.Top : screenTop;
        // Keep enough of the title area visible to recover the window.
        var left = Math.Clamp(proposedLeft, screenLeft - width + 80,
            screenLeft + screenWidth - 80);
        var top = Math.Clamp(proposedTop, screenTop,
            screenTop + screenHeight - Math.Min(80, height));

        window.WindowStartupLocation = WindowStartupLocation.Manual;
        window.Width = width;
        window.Height = height;
        window.Left = left;
        window.Top = top;
        window.WindowState = saved.Maximized ? WindowState.Maximized : WindowState.Normal;
    }

    public static void Save(Window window, AppSettingsStore settings, string key)
    {
        var maximized = window.WindowState == WindowState.Maximized;
        var bounds = window.WindowState == WindowState.Normal
            ? new Rect(window.Left, window.Top, window.ActualWidth, window.ActualHeight)
            : window.RestoreBounds;
        if (!double.IsFinite(bounds.Left) || !double.IsFinite(bounds.Top) ||
            !double.IsFinite(bounds.Width) || !double.IsFinite(bounds.Height) ||
            bounds.Width <= 0 || bounds.Height <= 0)
            return;
        settings.SaveWindowGeometry(key, new SavedWindowGeometry
        {
            Left = bounds.Left, Top = bounds.Top,
            Width = bounds.Width, Height = bounds.Height,
            Maximized = maximized
        });
    }
}
