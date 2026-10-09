using System.Diagnostics;
using System.IO;
using System.IO.Pipes;
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

    private void InstallUpdateButton_Click(object sender, RoutedEventArgs e)
    {
        var release = _availableUpdate;
        if (release is null)
        {
            UpdateStatusText.Text = "Сначала проверьте наличие обновлений.";
            return;
        }

        CheckForUpdatesButton.IsEnabled = false;
        InstallUpdateButton.IsEnabled = false;
        try
        {
            // A separate single-file Windows process owns the progress window
            // throughout download -> app exit -> Inno install -> app restart.
            var installedHelper = Path.Combine(AppContext.BaseDirectory,
                "UpdateAgent", "MailRuDesktop.UpdateAgent.exe");
            if (!File.Exists(installedHelper))
                throw new FileNotFoundException("Не найден модуль обновления.");

            var helperDirectory = Path.Combine(Path.GetTempPath(),
                "MailRuDesktop-UpdateAgents");
            Directory.CreateDirectory(helperDirectory);
            foreach (var oldHelper in Directory.EnumerateFiles(helperDirectory,
                         "MailRuDesktop.UpdateAgent-*.exe"))
            {
                try { File.Delete(oldHelper); }
                catch (IOException) { /* Another helper is still running. */ }
                catch (UnauthorizedAccessException) { }
            }
            var helperCopy = Path.Combine(helperDirectory,
                "MailRuDesktop.UpdateAgent-" + Guid.NewGuid().ToString("N") + ".exe");
            File.Copy(installedHelper, helperCopy);
            var pipeName = "MailRuDesktop-Update-" + Guid.NewGuid().ToString("N");
            var pipe = new NamedPipeServerStream(pipeName, PipeDirection.In,
                1, PipeTransmissionMode.Byte, PipeOptions.Asynchronous);
            var start = new ProcessStartInfo(helperCopy)
            {
                UseShellExecute = false
            };
            start.ArgumentList.Add(Environment.ProcessId.ToString());
            start.ArgumentList.Add(release.UpdateDownloadUrl);
            start.ArgumentList.Add(Environment.ProcessPath ??
                Path.Combine(AppContext.BaseDirectory, "MailRuDesktop.App.exe"));
            start.ArgumentList.Add(pipeName);

            if (Process.Start(start) is null)
            {
                pipe.Dispose();
                throw new InvalidOperationException("Модуль обновления не запущен.");
            }
            UpdateStatusText.Text =
                "Окно обновления открыто. Программа закроется после скачивания.";
            _ = Task.Run(async () =>
            {
                using (pipe)
                using (var timeout = new CancellationTokenSource(TimeSpan.FromMinutes(15)))
                {
                    try
                    {
                        await pipe.WaitForConnectionAsync(timeout.Token);
                        using var reader = new StreamReader(pipe);
                        var command = await reader.ReadLineAsync(timeout.Token);
                        if (command == "UPDATE_READY")
                            await Dispatcher.InvokeAsync(() =>
                            {
                                // Normal WPF Shutdown is intercepted by the tray:
                                // explicitly take the controlled exit path instead.
                                _pushExitRequested = true;
                                Close();
                            });
                    }
                    catch (OperationCanceledException) { }
                    catch (IOException) { }
                }
            });
        }
        catch (Exception error)
        {
            UpdateStatusText.Text = "Не удалось запустить обновление: " +
                error.GetType().Name + ".";
            DiagnosticLog.Write("github_update_install", error.GetType().Name);
            CheckForUpdatesButton.IsEnabled = true;
            InstallUpdateButton.IsEnabled = true;
        }
    }
}
