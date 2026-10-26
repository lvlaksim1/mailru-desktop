using System;
using System.Diagnostics;
using System.IO;
using System.Text.Json;
using System.Windows;
using Microsoft.Win32;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private bool _restartingAfterSettingsImport;

    private void ExportPortableSettingsButton_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            var target = new SaveFileDialog
            {
                Title = "Экспорт настроек MailRu Desktop",
                Filter = "Зашифрованная резервная копия (*.mrbackup)|*.mrbackup",
                DefaultExt = ".mrbackup",
                AddExtension = true,
                FileName = "MailRuDesktop-" + DateTime.Now.ToString("yyyy-MM-dd") + ".mrbackup",
                OverwritePrompt = true
            };
            if (target.ShowDialog(this) != true) return;
            var passwordWindow = new BackupPasswordWindow(this, export: true);
            if (passwordWindow.ShowDialog() != true) return;
            SaveUserInterfaceState();
            var service = new PortableSettingsService(_authStore, _settingsStore);
            service.Export(target.FileName, passwordWindow.Password);
            MessageBox.Show(this,
                "Зашифрованная резервная копия создана.\n\n" +
                "Она содержит пароли аккаунтов, шаблоны, подписи и профили рассылки, " +
                "но не содержит токенов и браузерных сеансов. Храните пароль к архиву отдельно.",
                "Экспорт завершён", MessageBoxButton.OK, MessageBoxImage.Information);
        }
        catch (Exception ex)
        {
            MessageBox.Show(this, "Не удалось создать резервную копию:\n" + ex.Message,
                "Ошибка экспорта", MessageBoxButton.OK, MessageBoxImage.Error);
        }
    }

    private void ImportPortableSettingsButton_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            var source = new OpenFileDialog
            {
                Title = "Импорт настроек MailRu Desktop",
                Filter = "Зашифрованная резервная копия (*.mrbackup)|*.mrbackup",
                CheckFileExists = true
            };
            if (source.ShowDialog(this) != true) return;
            var passwordWindow = new BackupPasswordWindow(this, export: false);
            if (passwordWindow.ShowDialog() != true) return;
            var package = PortableSettingsService.Read(source.FileName, passwordWindow.Password);
            var selectionWindow = new BackupImportSelectionWindow(this, package);
            if (selectionWindow.ShowDialog() != true) return;
            var answer = MessageBox.Show(this,
                "Перед импортом программа создаст полную местную резервную копию.\n\n" +
                "Затем прежние настройки будут сброшены. Восстановятся только отмеченные " +
                "данные. Уже авторизованные аккаунты сохранят свои местные ключи и не " +
                "будут входить повторно.\n\nПродолжить?",
                "Подтверждение замены настроек",
                MessageBoxButton.YesNo, MessageBoxImage.Warning);
            if (answer != MessageBoxResult.Yes) return;

            if (!TryCloseSettingsWindowForImport())
                return;
            // Include current, still unsaved column/window sizes in the mandatory
            // local rollback backup before the configuration is replaced.
            SaveUserInterfaceState();
            var service = new PortableSettingsService(_authStore, _settingsStore);
            var pending = service.Apply(package, selectionWindow.Selection);
            MessageBox.Show(this,
                "Импорт завершён. Резервная копия прежних настроек сохранена " +
                "на этом компьютере в папке Backups.\n\n" +
                "После перезапуска новые аккаунты будут авторизованы по очереди. " +
                "Уже авторизованные аккаунты сохранят свои сеансы.\n\n" +
                "Новых аккаунтов для входа: " + pending.Count,
                "Импорт настроек", MessageBoxButton.OK, MessageBoxImage.Information);

            var executable = Environment.ProcessPath;
            if (string.IsNullOrWhiteSpace(executable) ||
                !Path.GetFileName(executable).Equals("MailRuDesktop.App.exe",
                    StringComparison.OrdinalIgnoreCase))
            {
                MessageBox.Show(this, "Перезапустите MailRu Desktop вручную, " +
                    "чтобы применились настройки.", "Импорт завершён",
                    MessageBoxButton.OK, MessageBoxImage.Information);
                _restartingAfterSettingsImport = true;
                Application.Current.Shutdown();
                return;
            }

            // The app is single-instance. Launching a second copy before exit
            // would only activate the first copy and abort the restart.
            // A detached short-lived cmd child launches AFTER mutex release.
            var restart = new ProcessStartInfo(Path.Combine(Environment.SystemDirectory, "cmd.exe"))
            {
                CreateNoWindow = true,
                WindowStyle = ProcessWindowStyle.Hidden,
                UseShellExecute = false,
                WorkingDirectory = Path.GetDirectoryName(executable)!
            };
            restart.ArgumentList.Add("/d");
            restart.ArgumentList.Add("/c");
            restart.ArgumentList.Add("ping 127.0.0.1 -n 4 >nul && start \"\" \"" + executable + "\"");
            Process.Start(restart);
            _restartingAfterSettingsImport = true;
            Application.Current.Shutdown();
        }
        catch (Exception ex)
        {
            MessageBox.Show(this, "Импорт не завершён:\n" + ex.Message +
                "\n\nПри ошибке записи программа восстанавливает настройки " +
                "из автоматически созданной местной резервной копии.",
                "Ошибка импорта", MessageBoxButton.OK, MessageBoxImage.Error);
        }
    }

    private bool TryCloseSettingsWindowForImport()
    {
        // Compose and mailing windows may save their old settings on close.
        // The settings window is safe to close now, before replacing the files.
        var settingsWindow = _settingsWindow;
        if (Application.Current.Windows.Cast<Window>()
            .Any(IsWindowBlockingSettingsImport))
        {
            MessageBox.Show(this,
                "Перед импортом закройте открытые окна писем и рассылки Excel, " +
                "затем повторите импорт.",
                "Импорт настроек", MessageBoxButton.OK, MessageBoxImage.Warning);
            return false;
        }

        // Closing Settings persists its geometry. Do this before taking the
        // rollback snapshot and importing, never during the final app shutdown.
        settingsWindow?.Close();
        if (IsSettingsWindowOpenForImport(settingsWindow))
        {
            MessageBox.Show(this,
                "Не удалось закрыть окно настроек. Закройте его и повторите импорт.",
                "Импорт настроек", MessageBoxButton.OK, MessageBoxImage.Warning);
            return false;
        }
        return true;
    }

    // WPF keeps IsLoaded true even after Window.Close(). Only a window still
    // registered in Application.Windows can block the import.
    private static bool IsSettingsWindowOpenForImport(Window? settingsWindow) =>
        settingsWindow is not null && Application.Current.Windows.Cast<Window>()
            .Any(window => ReferenceEquals(window, settingsWindow));

    private bool IsWindowBlockingSettingsImport(Window window) =>
        window != this && window != _settingsWindow && window.IsLoaded;

    // A one-shot queue: no passwords are serialized here, and only accounts
    // without computer-specific AJ tokens are submitted to Mail.ru.
    private async System.Threading.Tasks.Task AuthenticateNewlyImportedAccountsAsync()
    {
        var queueFile = Path.Combine(Environment.GetFolderPath(
            Environment.SpecialFolder.LocalApplicationData), "MailRuDesktop",
            "pending-import-auth.json");
        if (!File.Exists(queueFile)) return;
        string[] accounts;
        try
        {
            accounts = JsonSerializer.Deserialize<string[]>(File.ReadAllText(queueFile)) ?? [];
            File.Delete(queueFile);
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("import_auth_queue", ex.GetType().Name);
            return;
        }
        foreach (var login in accounts.Distinct(StringComparer.OrdinalIgnoreCase))
        {
            if (_authStore.TryRestore(login, out var restored) &&
                !string.IsNullOrWhiteSpace(restored?.AccessToken))
                continue;
            if (!_authStore.TryGetPassword(login, out var password) ||
                string.IsNullOrWhiteSpace(password))
                continue;
            await AuthenticateAccountAsync(login, password);
            await System.Threading.Tasks.Task.Delay(3000);
        }
        RefreshSavedLogins();
    }
}
