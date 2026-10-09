using System.Diagnostics;
using System.Reflection;
using System.Windows;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private GitHubReleaseInfo? _availableUpdate;

    private async void CheckForUpdatesButton_Click(object sender, RoutedEventArgs e)
    {
        CheckForUpdatesButton.IsEnabled = false;
        InstallUpdateButton.Visibility = Visibility.Collapsed;
        _availableUpdate = null;
        UpdateStatusText.Text = "Проверка последнего выпуска GitHub...";

        try
        {
            var latest = await GitHubUpdateService.GetLatestReleaseAsync();
            var current = Assembly.GetExecutingAssembly().GetName().Version ??
                          new Version(0, 0, 0, 0);

            if (latest.Version <= current)
            {
                UpdateStatusText.Text =
                    $"Установлена актуальная версия {current.Major}.{current.Minor}.{Math.Max(current.Build, 0)}.";
                return;
            }

            _availableUpdate = latest;
            UpdateStatusText.Text =
                $"Доступна новая версия {latest.Version}.";
            InstallUpdateButton.Content = $"Обновить до {latest.Version}";
            InstallUpdateButton.Visibility = Visibility.Visible;
        }
        catch (Exception ex)
        {
            // The updater first checks api.github.com, then github.com.
            // Display safe diagnostic categories instead of silently failing.
            UpdateStatusText.Text =
                "Не удалось проверить обновления. " + ex.Message +
                " Нажмите «Открыть страницу выпусков» для обновления через браузер.";
            DiagnosticLog.Write(
                "github_update_check",
                ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            CheckForUpdatesButton.IsEnabled = true;
        }
    }

    private void OpenReleasesButton_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            Process.Start(new ProcessStartInfo(GitHubUpdateService.LatestReleasePage)
            {
                UseShellExecute = true
            });
        }
        catch (Exception ex)
        {
            UpdateStatusText.Text = "Не удалось открыть страницу выпусков в браузере.";
            DiagnosticLog.Write(
                "github_update_browser",
                ex.GetType().Name);
        }
    }

    private async void InstallUpdateButton_Click(object sender, RoutedEventArgs e)
    {
        var release = _availableUpdate;
        if (release is null)
        {
            UpdateStatusText.Text = "Сначала проверьте наличие обновлений.";
            return;
        }

        CheckForUpdatesButton.IsEnabled = false;
        InstallUpdateButton.IsEnabled = false;
        UpdateStatusText.Text = $"Скачивание обновления {release.Version} с GitHub...";

        try
        {
            var installerPath = await GitHubUpdateService.DownloadUpdateAsync(release);
            UpdateStatusText.Text = "Обновление скачано. Запускаю установщик...";

            // Inno /SILENT shows its installation progress, without the
            // wizard dialogs. /SUPPRESSMSGBOXES avoids modal prompts. The
            // updater itself starts the new app after replacing files.
            // Desktop shortcuts are not touched by installer/update.iss.
            var startInfo = new ProcessStartInfo(installerPath)
            {
                UseShellExecute = true
            };
            startInfo.ArgumentList.Add("/SILENT");
            startInfo.ArgumentList.Add("/SUPPRESSMSGBOXES");
            startInfo.ArgumentList.Add("/NORESTART");
            startInfo.ArgumentList.Add("/CLOSEAPPLICATIONS");
            if (Process.Start(startInfo) is null)
                throw new InvalidOperationException("Не удалось запустить установщик.");
            Application.Current.Shutdown();
        }
        catch (Exception ex)
        {
            UpdateStatusText.Text = "Не удалось скачать или запустить обновление.";
            DiagnosticLog.Write(
                "github_update_install",
                ex.GetType().Name + ": " + ex.Message);
            CheckForUpdatesButton.IsEnabled = true;
            InstallUpdateButton.IsEnabled = true;
        }
    }
}
