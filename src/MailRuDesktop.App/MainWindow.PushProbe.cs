using System.IO;
using System.Text;
using System.ComponentModel;
using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    // Deliberate diagnostic release: never subscribe all saved accounts.
    // Re-enable automatic multi-account delivery only in a subsequent
    // source-verified release after the selected-account test is accepted.
    private const bool SingleAccountManualTestRelease = true;
    private PushProbeWindow? _pushProbeWindow;
    private MailRuPushBackgroundService? _pushBackground;
    private readonly SemaphoreSlim _pushRefreshGate = new(1, 1);
    private readonly Dictionary<string, string> _pushStates =
        new(StringComparer.OrdinalIgnoreCase);
    private readonly HashSet<string> _pushConnectedAccounts =
        new(StringComparer.OrdinalIgnoreCase);
    private bool _pushSettingsInitialized;
    private bool _pushBackgroundReady;
    private bool _pushShuttingDown;
    private bool _pushShutdownComplete;
    private string? _pushWindowTitle;
    private System.Windows.Forms.NotifyIcon? _pushNotificationArea;
    private System.Drawing.Icon? _pushNotificationIcon;
    private System.Windows.Forms.ContextMenuStrip? _pushTrayMenu;
    private bool _pushExitRequested;

    private void InitializeBackgroundPush()
    {
        _pushWindowTitle = Title;
        try
        {
            _pushNotificationIcon = Environment.ProcessPath is { Length: > 0 } exe
                ? System.Drawing.Icon.ExtractAssociatedIcon(exe)
                : null;
            _pushNotificationArea = new System.Windows.Forms.NotifyIcon
            {
                Icon = _pushNotificationIcon ?? System.Drawing.SystemIcons.Application,
                Text = "MailRu Desktop — новые письма",
                Visible = true
            };
            _pushTrayMenu = new System.Windows.Forms.ContextMenuStrip();
            var restoreItem = _pushTrayMenu.Items.Add("Развернуть");
            restoreItem.Click += (_, _) =>
                _ = Dispatcher.BeginInvoke(new Action(RestoreFromTray));
            var exitItem = _pushTrayMenu.Items.Add("Выход");
            exitItem.Click += (_, _) =>
                _ = Dispatcher.BeginInvoke(new Action(ExitFromTray));
            _pushNotificationArea.ContextMenuStrip = _pushTrayMenu;
            _pushNotificationArea.DoubleClick += (_, _) =>
                _ = Dispatcher.BeginInvoke(new Action(RestoreFromTray));
        }
        catch
        {
            _pushNotificationArea?.Dispose();
            _pushNotificationArea = null;
            _pushTrayMenu?.Dispose();
            _pushTrayMenu = null;
            _pushNotificationIcon?.Dispose();
            _pushNotificationIcon = null;
        }
        PushDiagnostics.Changed += OnPushDiagnosticsChanged;
        PushDiagnostics.Record("APP", "UI_INITIALIZED");
        RefreshPushDiagnosticsView();
        _pushBackground = new MailRuPushBackgroundService(
            (login, state) =>
            {
                if (_pushShuttingDown || Dispatcher.HasShutdownStarted) return;
                _ = Dispatcher.BeginInvoke(new Action(
                    () => ShowBackgroundPushState(login, state)));
            },
            login =>
            {
                if (_pushShuttingDown || Dispatcher.HasShutdownStarted) return;
                _ = Dispatcher.BeginInvoke(new Action(() => OnPushNewMail(login)));
            });
        // Keep the user's previous preference in settings for future versions,
        // but NEVER enable the multi-account receiver in this test release.
        BackgroundPushEnabledCheckBox.IsChecked = SingleAccountManualTestRelease
            ? false : _settingsStore.LoadBackgroundPushEnabled();
        BackgroundPushEnabledCheckBox.IsEnabled = !SingleAccountManualTestRelease;
        TaskbarNotificationsEnabledCheckBox.IsChecked =
            _settingsStore.LoadTaskbarNotificationsEnabled();
        _pushSettingsInitialized = true;
        BackgroundPushStatusText.Text = SingleAccountManualTestRelease
            ? "Проверочная версия: массовая подписка отключена. " +
              "Нажмите «Выбрать аккаунт для проверки»."
            : BackgroundPushEnabledCheckBox.IsChecked == true
                ? "Ожидание загрузки сохранённых аккаунтов…"
                : "Автоматическое получение уведомлений выключено.";
        Closing += MainWindow_PushClosing;
    }

    private void StartBackgroundPush()
    {
        _pushBackgroundReady = true;
        if (SingleAccountManualTestRelease)
        {
            // Do not call Reconcile(false): the normal opt-out path revokes
            // the common Google token, which this test MUST NOT touch.
            PushDiagnostics.Record("SERVICE", "MANUAL_SINGLE_ACCOUNT_ONLY");
            return;
        }
        SyncBackgroundPush();
    }

    private void SyncBackgroundPush()
    {
        // Explicit kill-switch: no accidental connection or token-wide
        // unsubscribe for any of the 32 saved accounts.
        if (SingleAccountManualTestRelease) return;
        if (!_pushBackgroundReady || _pushShuttingDown || _pushBackground is null)
            return;
        var userEnabled = BackgroundPushEnabledCheckBox.IsChecked == true;
        var pauseForDiagnostics = userEnabled &&
                                  _pushProbeWindow is { IsVisible: true };
        var enabled = userEnabled && !pauseForDiagnostics;
        var accounts = new List<(string Login, string Token)>();
        if (enabled)
        {
            foreach (var login in _authStore.Logins)
            {
                if (_authStore.TryRestore(login, out var auth) &&
                    !string.IsNullOrWhiteSpace(auth?.AccessToken))
                    accounts.Add((login, auth.AccessToken));
            }
        }
        // Remove no-longer-authorized mailboxes from the UI count immediately;
        // the common receiver is then reconciled as a single generation.
        _pushConnectedAccounts.RemoveWhere(login =>
            !accounts.Any(account =>
                string.Equals(account.Login, login, StringComparison.OrdinalIgnoreCase)));
        _pushBackground.Reconcile(accounts, enabled, pauseForDiagnostics);
        if (_pushNotificationArea is not null)
            _pushNotificationArea.Visible = true; // Tray icon is independent of push subscription.
        if (!enabled)
        {
            _pushConnectedAccounts.Clear();
            BackgroundPushStatusText.Text =
                _pushProbeWindow is { IsVisible: true }
                    ? "Автоматический приём временно остановлен для ручной проверки."
                    : "Автоматическое получение уведомлений выключено.";
        }
        else if (accounts.Count == 0)
            BackgroundPushStatusText.Text = "Нет подключённых почтовых аккаунтов.";
        else
            BackgroundPushStatusText.Text = $"Подключение аккаунтов: {accounts.Count}.";
    }

    private void TaskbarNotificationsEnabledCheckBox_Changed(object sender, RoutedEventArgs e)
    {
        if (!_pushSettingsInitialized || _pushShuttingDown) return;
        _settingsStore.SaveTaskbarNotificationsEnabled(
            TaskbarNotificationsEnabledCheckBox.IsChecked == true);
    }

    private void RestoreFromTray()
    {
        if (_pushShuttingDown) return;
        ShowInTaskbar = true;
        Show();
        if (WindowState == WindowState.Minimized)
            WindowState = WindowState.Normal;
        Activate();
    }

    private void ExitFromTray()
    {
        if (_pushShuttingDown) return;
        _pushExitRequested = true;
        Close();
    }

    private void BackgroundPushEnabledCheckBox_Changed(object sender, RoutedEventArgs e)
    {
        if (SingleAccountManualTestRelease ||
            !_pushSettingsInitialized || _pushShuttingDown) return;
        var selected = BackgroundPushEnabledCheckBox.IsChecked == true;
        PushDiagnostics.Record("UI", selected ? "AUTO_NOTIFICATIONS_ENABLED" :
            "AUTO_NOTIFICATIONS_DISABLED");
        _settingsStore.SaveBackgroundPushEnabled(selected);
        SyncBackgroundPush();
    }

    private void ShowBackgroundPushState(string login, string state)
    {
        if (_pushShuttingDown || _pushBackground is null) return;
        _pushStates[login] = state;
        if (state.Contains("Постоянный приём уведомлений включён",
            StringComparison.OrdinalIgnoreCase))
            _pushConnectedAccounts.Add(login);
        else if (state.Contains("PushMe отклонил регистрацию", StringComparison.OrdinalIgnoreCase) ||
                 state.Contains("Соединение прервано", StringComparison.OrdinalIgnoreCase) ||
                 state.Contains("Получение уведомлений остановлено", StringComparison.OrdinalIgnoreCase) ||
                 state.Contains("Аккаунт отклонён", StringComparison.OrdinalIgnoreCase) ||
                 state.Contains("Ошибка этапа", StringComparison.OrdinalIgnoreCase))
            _pushConnectedAccounts.Remove(login);
        if (BackgroundPushEnabledCheckBox.IsChecked != true) return;
        var enabled = _pushBackground.ActiveAccountCount;
        var connected = _pushConnectedAccounts.Count;
        var displayedState = state;
        if ((state.Contains("Соединение прервано", StringComparison.OrdinalIgnoreCase) ||
             state.Contains("PushMe отклонил регистрацию", StringComparison.OrdinalIgnoreCase)) &&
            PushDiagnostics.LastFailure != "NONE")
            displayedState += " Причина: " + PushDiagnostics.LastFailure +
                              ". Подробности — в журнале ниже.";
        BackgroundPushStatusText.Text =
            $"Подключено: {connected} из {enabled}. {displayedState}";
    }

    private void OnPushDiagnosticsChanged()
    {
        if (_pushShuttingDown || Dispatcher.HasShutdownStarted ||
            Dispatcher.HasShutdownFinished) return;
        try
        {
            _ = Dispatcher.BeginInvoke(new Action(RefreshPushDiagnosticsView));
        }
        catch (InvalidOperationException) { }
    }

    private void RefreshPushDiagnosticsView()
    {
        if (_pushShuttingDown || PushDiagnosticsLogTextBox is null) return;
        // Show the latest entries, but export the full bounded history.
        PushDiagnosticsLogTextBox.Text = PushDiagnostics.Report(140);
        PushDiagnosticsLogTextBox.ScrollToEnd();
    }

    private void RefreshPushDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        RefreshPushDiagnosticsView(); // Only reads a local log; no network requests.
        PushDiagnosticsActionStatusText.Text = "Журнал обновлён.";
    }

    private void CopyPushDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            Clipboard.SetText(PushDiagnostics.Report());
            PushDiagnosticsActionStatusText.Text = "Отчёт скопирован в буфер обмена.";
        }
        catch (System.Runtime.InteropServices.ExternalException)
        {
            PushDiagnosticsActionStatusText.Text = "Не удалось скопировать отчёт.";
        }
    }

    private void SavePushDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        var dialog = new Microsoft.Win32.SaveFileDialog
        {
            Title = "Сохранить диагностику Google и PushMe",
            Filter = "Текстовый отчёт (*.txt)|*.txt",
            FileName = "MailRuDesktop-PushMe-Diagnostics.txt",
            DefaultExt = ".txt",
            AddExtension = true
        };
        if (dialog.ShowDialog(this) != true) return;
        try
        {
            File.WriteAllText(dialog.FileName, PushDiagnostics.Report(),
                new UTF8Encoding(false));
            PushDiagnosticsActionStatusText.Text = "Отчёт сохранён в выбранный файл.";
        }
        catch (IOException)
        {
            PushDiagnosticsActionStatusText.Text = "Не удалось сохранить отчёт.";
        }
        catch (UnauthorizedAccessException)
        {
            PushDiagnosticsActionStatusText.Text = "Нет прав для сохранения отчёта.";
        }
    }

    private void ClearPushDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        PushDiagnostics.Clear();
        RefreshPushDiagnosticsView();
        PushDiagnosticsActionStatusText.Text = "Журнал очищен.";
    }

    private void OpenPushProbeButton_Click(object sender, RoutedEventArgs e)
    {
        if (_pushProbeWindow is { IsVisible: true })
        {
            _pushProbeWindow.Activate();
            return;
        }
        // The selected-account test creates a separate temporary recipient;
        // the 32-account worker is disabled in this release.
        var window = new PushProbeWindow(_authStore, _activeLogin, OnPushNewMail);
        window.Owner = this;
        _pushProbeWindow = window;
        SyncBackgroundPush(); // pause automatic recipients for the experiment
        window.Closed += (_, _) =>
        {
            _pushProbeWindow = null;
            SyncBackgroundPush();
        };
        window.Show();
        SyncBackgroundPush();
    }

    private void OnPushNewMail(string login)
    {
        if (_pushShuttingDown) return;
        _pushWindowTitle ??= Title;
        if (!Title.Contains("Новое письмо", StringComparison.Ordinal))
            Title = _pushWindowTitle + " • Новое письмо";
        BackgroundPushStatusText.Text = "Получено новое письмо. Обновление почтовых папок…";
        try
        {
            if (TaskbarNotificationsEnabledCheckBox.IsChecked == true)
                _pushNotificationArea?.ShowBalloonTip(
                    5000, "MailRu Desktop — новое письмо",
                    "Новое письмо: " + login,
                    System.Windows.Forms.ToolTipIcon.Info);
        }
        catch
        {
            // Notification-area availability must not break mail refresh.
        }
        _ = RefreshAfterPushAsync(login);
    }

    private async Task RefreshAfterPushAsync(string login)
    {
        try
        {
            await _pushRefreshGate.WaitAsync();
            try
            {
                if (_pushShuttingDown) return;
                // Do not replace an in-progress user-initiated folder operation.
                for (var retries = 0; _loadingFolder && retries < 20; retries++)
                    await Task.Delay(200);
                if (string.Equals(login, _activeLogin, StringComparison.OrdinalIgnoreCase) &&
                    !_loadingFolder)
                    await LoadFolderAsync(_currentFolderId);
                // Counter refresh includes all saved mailboxes and updates the
                // inactive account's unread badge as well.
                await RefreshAccountUnreadCountsAsync();
                BackgroundPushStatusText.Text = "Новое письмо получено, список обновлён.";
            }
            finally
            {
                _pushRefreshGate.Release();
            }
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("push_event_folder_refresh", ex.GetType().Name);
            BackgroundPushStatusText.Text = "Уведомление получено; ошибка обновления списка.";
        }
    }

    private async void MainWindow_PushClosing(object? sender, CancelEventArgs e)
    {
        if (_pushShutdownComplete) return;
        e.Cancel = true;
        // Closing the window hides it in the tray. Only explicit Exit
        // terminates the application and closes the MCS channel.
        if (!_pushExitRequested)
        {
            if (_pushNotificationArea is null)
            {
                // Never trap an invisible application without a tray icon.
                _pushExitRequested = true;
            }
            else
            {
                Hide();
                ShowInTaskbar = false;
                return;
            }
        }
        if (_pushShuttingDown) return;
        PushDiagnostics.Record("APP", "EXIT_REQUESTED");
        _pushShuttingDown = true;
        PushDiagnostics.Changed -= OnPushDiagnosticsChanged;
        _pushBackgroundReady = false;
        try
        {
            if (_pushBackground is not null)
                await _pushBackground.StopAsync();
        }
        finally
        {
            _pushBackground?.Dispose();
            _pushNotificationArea?.Dispose();
            _pushNotificationArea = null;
            _pushTrayMenu?.Dispose();
            _pushTrayMenu = null;
            _pushNotificationIcon?.Dispose();
            _pushNotificationIcon = null;
            _pushShutdownComplete = true;
            Close();
        }
    }
}
