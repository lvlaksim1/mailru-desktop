using System.Windows;
using MailRuDesktop.App.Mailing;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private ExcelMailingWindow? _excelMailingWindow;

    private void OpenExcelMailingButton_Click(object sender, RoutedEventArgs e)
    {
        if (_excelMailingWindow is { } existing)
        {
            if (existing.WindowState == WindowState.Minimized)
                existing.WindowState = WindowState.Normal;
            existing.Activate();
            return;
        }

        var window = new ExcelMailingWindow(this, _authStore, _settingsStore);
        WindowGeometryPersistence.Restore(window, _settingsStore, "excel-mailing");
        window.Closing += (_, _) =>
            WindowGeometryPersistence.Save(window, _settingsStore, "excel-mailing");
        _excelMailingWindow = window;
        window.Closed += (_, _) => _excelMailingWindow = null;
        window.Show();
        ThemeManager.RefreshWindowChrome(window);
    }
}
