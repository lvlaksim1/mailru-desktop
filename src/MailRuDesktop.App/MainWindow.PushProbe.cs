using System.ComponentModel;
using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private PushProbeWindow? _pushProbeWindow;
    private MailRuPushBackgroundService? _pushBackground;
    private readonly SemaphoreSlim _pushRefreshGate = new(1, 1);
    private readonly Dictionary<string, string> _pushStates =
        new(StringComparer.OrdinalIgnoreCase);
    private bool _pushSettingsInitialized;
    private bool _pushBackgroundReady;
    private bool _pushShuttingDown;
    private bool _pushShutdownComplete;
    private string? _pushWindowTitle;
    private System.Windows.Forms.NotifyIcon? _pushNotificationArea;
    private System.Drawing.Icon? _pushNotificationIcon;

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
                Visible = _settingsStore.LoadBackgroundPushEnabled()
            };
            _pushNotificationArea.DoubleClick += (_, _) =>
            {
                _ = Dispatcher.BeginInvoke(new Action(() =>
                {
                    WindowState = WindowState.Normal;
                    Show();
                    Activate();
                }));
            };
        }
        catch
        {
            _pushNotificationArea?.Dispose();
            _pushNotificationArea = null;
            _pushNotificationIcon?.Dispose();
            _pushNotificationIcon = null;
        }
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
        BackgroundPushEnabledCheckBox.IsChecked = _settingsStore.LoadBackgroundPushEnabled();
        _pushSettingsInitialized = true;
        BackgroundPushStatusText.Text = BackgroundPushEnabledCheckBox.IsChecked == true
            ? "Ожидание загрузки сохранённых аккаунтов…"
            : "Автоматическое получение уведомлений выключено.";
        Closing += MainWindow_PushClosing;
    }

    private void StartBackgroundPush()
    {
        _pushBackgroundReady = true;
        SyncBackgroundPush();
    }

    private void SyncBackgroundPush()
    {
        if (!_pushBackgroundReady || _pushShuttingDown || _pushBackground is null)
            return;
        var enabled = BackgroundPushEnabledCheckBox.IsChecked == true &&
                      _pushProbeWindow is not { IsVisible: true };
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
        _pushBackground.Reconcile(accounts, enabled);
        if (_pushNotificationArea is not null)
            _pushNotificationArea.Visible = enabled;
        if (!enabled)
            BackgroundPushStatusText.Text =
                _pushProbeWindow is { IsVisible: true }
                    ? "Автоматический приём временно остановлен для ручной проверки."
                    : "Автоматическое получение уведомлений выключено.";
        else if (accounts.Count == 0)
            BackgroundPushStatusText.Text = "Нет подключённых почтовых аккаунтов.";
        else
            BackgroundPushStatusText.Text = $"Подключение аккаунтов: {accounts.Count}.";
    }

    private void BackgroundPushEnabledCheckBox_Changed(object sender, RoutedEventArgs e)
    {
        if (!_pushSettingsInitialized || _pushShuttingDown) return;
        _settingsStore.SaveBackgroundPushEnabled(BackgroundPushEnabledCheckBox.IsChecked == true);
        SyncBackgroundPush();
    }

    private void ShowBackgroundPushState(string login, string state)
    {
        if (_pushShuttingDown || _pushBackground is null) return;
        _pushStates[login] = state;
        if (BackgroundPushEnabledCheckBox.IsChecked != true) return;
        var enabled = _pushBackground.ActiveAccountCount;
        var connected = _pushStates
            .Where(pair => pair.Value.Contains("Постоянный приём уведомлений включён",
                StringComparison.OrdinalIgnoreCase))
            .Count();
        BackgroundPushStatusText.Text =
            $"Подключено: {connected} из {enabled}. {state}";
    }

    private void OpenPushProbeButton_Click(object sender, RoutedEventArgs e)
    {
        if (_pushProbeWindow is { IsVisible: true })
        {
            _pushProbeWindow.Activate();
            return;
        }
        // The three-minute diagnostic remains available, but does not run
        // simultaneously with the continuous subscription.
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
        if (_pushShuttingDown) return;
        _pushShuttingDown = true;
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
            _pushNotificationIcon?.Dispose();
            _pushNotificationIcon = null;
            _pushShutdownComplete = true;
            Close();
        }
    }
}
