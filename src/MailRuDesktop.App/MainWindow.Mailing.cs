using System.Windows;
using MailRuDesktop.App.Mailing;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private ExcelMailingWindow? _excelMailingWindow;

    // Keep the active reader in sync when the Excel mailing window rotates
    // credentials via the same AuthorizationStore. Do not replace a newer
    // account selection or a token already changed by another operation.
    internal void ApplyVerifiedMailingToken(
        string login, string previousAccess, string updatedAccess, string? updatedRefresh)
    {
        if (!string.Equals(_activeLogin, login, StringComparison.OrdinalIgnoreCase) ||
            !string.Equals(_accessToken, previousAccess, StringComparison.Ordinal))
            return;
        _accessToken = updatedAccess;
        _refreshToken = updatedRefresh;
        SetAccountAuthorizationInvalid(login, false);
        AuthStatusText.Text = "Авторизация обновлена при рассылке";
        SyncBackgroundPush();
    }

    private void OpenExcelMailingButton_Click(object sender, RoutedEventArgs e)
    {
        if (_excelMailingWindow is { } existing)
        {
            if (existing.WindowState == WindowState.Minimized)
                existing.WindowState = WindowState.Normal;
            existing.Activate();
            return;
        }

        var window = new ExcelMailingWindow(this, _authStore, _settingsStore, _mailRu);
        WindowGeometryPersistence.Restore(window, _settingsStore, "excel-mailing");
        window.Closing += (_, _) =>
            WindowGeometryPersistence.Save(window, _settingsStore, "excel-mailing");
        _excelMailingWindow = window;
        window.Closed += (_, _) => _excelMailingWindow = null;
        window.Show();
        ThemeManager.RefreshWindowChrome(window);
    }
}
