using System.Windows;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private void RestoreUserInterfaceState()
    {
        var state = _settingsStore.LoadUserInterfaceState();

        RestoreWindowBounds(state);

        NavigationPaneColumn.Width = new GridLength(
            ClampFinite(state.NavigationPaneWidth, 160, 520, 230));
        AccountPaneColumn.Width = new GridLength(
            ClampFinite(state.AccountPaneWidth, 140, 520, 220));
        MailListPaneColumn.Width = new GridLength(
            ClampFinite(state.MailListPaneWidth, 260, 1200, 455));
        ContactsListPaneColumn.Width = new GridLength(
            ClampFinite(state.ContactsListPaneWidth, 220, 900, 390));

        FolderManageExpander.IsExpanded = state.FolderManageExpanded;

        var columns = MailColumnLayout.Instance;
        columns.Time = RestoreGridLength(state.TimeColumn, new GridLength(58), 44);
        columns.Flag = RestoreGridLength(state.FlagColumn, new GridLength(28), 24);
        columns.Unread = RestoreGridLength(state.UnreadColumn, new GridLength(26), 24);
        columns.ThreadCount = RestoreGridLength(state.ThreadCountColumn, new GridLength(32), 26);
        columns.Attachment = RestoreGridLength(state.AttachmentColumn, new GridLength(28), 24);
        columns.Sender = RestoreGridLength(
            state.SenderColumn,
            new GridLength(1.15, GridUnitType.Star),
            90);
        columns.Subject = RestoreGridLength(
            state.SubjectColumn,
            new GridLength(2.15, GridUnitType.Star),
            140);
    }

    private void SaveUserInterfaceState()
    {
        try
        {
            var bounds = WindowState == WindowState.Normal
                ? new Rect(Left, Top, ActualWidth, ActualHeight)
                : RestoreBounds;

            var columns = MailColumnLayout.Instance;

            _settingsStore.SaveUserInterfaceState(new UserInterfaceState
            {
                WindowLeft = IsFinite(bounds.Left) ? bounds.Left : null,
                WindowTop = IsFinite(bounds.Top) ? bounds.Top : null,
                WindowWidth = IsFinite(bounds.Width) ? bounds.Width : null,
                WindowHeight = IsFinite(bounds.Height) ? bounds.Height : null,
                WindowMaximized = WindowState == WindowState.Maximized,

                NavigationPaneWidth = NavigationPaneColumn.ActualWidth,
                AccountPaneWidth = AccountPaneColumn.ActualWidth,
                MailListPaneWidth = MailListPaneColumn.ActualWidth,
                ContactsListPaneWidth = ContactsListPaneColumn.ActualWidth,

                FolderManageExpanded = FolderManageExpander.IsExpanded,

                TimeColumn = SaveGridLength(columns.Time),
                FlagColumn = SaveGridLength(columns.Flag),
                UnreadColumn = SaveGridLength(columns.Unread),
                ThreadCountColumn = SaveGridLength(columns.ThreadCount),
                AttachmentColumn = SaveGridLength(columns.Attachment),
                SenderColumn = SaveGridLength(columns.Sender),
                SubjectColumn = SaveGridLength(columns.Subject)
            });
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write(
                "settings_save",
                ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void RestoreWindowBounds(UserInterfaceState state)
    {
        if (state.WindowWidth is not { } savedWidth ||
            state.WindowHeight is not { } savedHeight ||
            !IsFinite(savedWidth) ||
            !IsFinite(savedHeight))
        {
            return;
        }

        var virtualLeft = SystemParameters.VirtualScreenLeft;
        var virtualTop = SystemParameters.VirtualScreenTop;
        var virtualRight = virtualLeft + SystemParameters.VirtualScreenWidth;
        var virtualBottom = virtualTop + SystemParameters.VirtualScreenHeight;

        var width = Math.Clamp(
            savedWidth,
            MinWidth,
            Math.Max(MinWidth, SystemParameters.VirtualScreenWidth));
        var height = Math.Clamp(
            savedHeight,
            MinHeight,
            Math.Max(MinHeight, SystemParameters.VirtualScreenHeight));

        var left = state.WindowLeft is { } savedLeft && IsFinite(savedLeft)
            ? savedLeft
            : virtualLeft + (SystemParameters.VirtualScreenWidth - width) / 2;
        var top = state.WindowTop is { } savedTop && IsFinite(savedTop)
            ? savedTop
            : virtualTop + (SystemParameters.VirtualScreenHeight - height) / 2;

        // Keep at least a usable part of the title bar on the current desktop,
        // including after a monitor has been disconnected.
        const double visibleEdge = 80;
        left = Math.Clamp(
            left,
            virtualLeft - width + visibleEdge,
            virtualRight - visibleEdge);
        top = Math.Clamp(
            top,
            virtualTop,
            virtualBottom - visibleEdge);

        Width = width;
        Height = height;
        Left = left;
        Top = top;
        WindowStartupLocation = WindowStartupLocation.Manual;

        if (state.WindowMaximized)
            WindowState = WindowState.Maximized;
    }

    private static GridLength RestoreGridLength(
        GridLengthSetting? setting,
        GridLength fallback,
        double minimumPixels)
    {
        if (setting is null ||
            !IsFinite(setting.Value) ||
            setting.Value <= 0)
        {
            return fallback;
        }

        if (setting.UnitType.Equals(
                GridUnitType.Star.ToString(),
                StringComparison.OrdinalIgnoreCase))
        {
            return new GridLength(
                Math.Max(0.05, setting.Value),
                GridUnitType.Star);
        }

        if (setting.UnitType.Equals(
                GridUnitType.Auto.ToString(),
                StringComparison.OrdinalIgnoreCase))
        {
            return GridLength.Auto;
        }

        return new GridLength(Math.Max(minimumPixels, setting.Value));
    }

    private static GridLengthSetting SaveGridLength(GridLength value) =>
        new()
        {
            Value = value.IsAuto ? 1 : value.Value,
            UnitType = value.GridUnitType.ToString()
        };

    private static double ClampFinite(
        double value,
        double minimum,
        double maximum,
        double fallback) =>
        IsFinite(value)
            ? Math.Clamp(value, minimum, maximum)
            : fallback;

    private static bool IsFinite(double value) =>
        !double.IsNaN(value) && !double.IsInfinity(value);
}
