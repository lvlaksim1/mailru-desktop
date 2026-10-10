using System.Collections.ObjectModel;
using System.Globalization;
using System.IO;
using System.Net;
using System.Reflection;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using MailRuDesktop.Protocol;
using Microsoft.Web.WebView2.Core;
using Microsoft.Win32;

namespace MailRuDesktop.App;

public partial class MainWindow : Window
{
    private readonly MailRuClient _mailRu = new();
    private readonly AuthorizationStore _authStore = new();
    private readonly AppSettingsStore _settingsStore = new();
    private readonly List<string> _attachmentPaths = [];
    private readonly ObservableCollection<MailRuMessageSummary> _visibleMessages = [];
    private List<MailRuMessageSummary> _currentMessages = [];

    private string? _accessToken;
    private string? _refreshToken;
    private string? _activeLogin;

    private int _currentFolderId;
    private bool _loadingFolder;
    private bool _updatingFolderSelection;
    private bool _updatingAccountSelection;
    private bool _readerReady;
    private bool _readerWaitingForFullMessage;
    private long _messageLoadGeneration;
    private CancellationTokenSource? _messageLoadCancellation;
    private MailRuFullMessage? _currentFullMessage;
    private string? _currentPreparedHtml;
    private bool _serverSearchMode;
    private readonly List<AccountRailItem> _accountRailItems = [];
    private bool _updatingAccountRail;
    private long _accountSwitchGeneration;
    private readonly SemaphoreSlim _accountSwitchGate = new(1, 1);
    private bool _suppressMessageSelectionChanged;

    public MainWindow()
    {
        InitializeComponent();
        InitializeRichComposeEditors();

        // The default date is based on local time at the moment the app opens.
        // Date/time are kept visible but only become active when scheduled
        // delivery is selected by the user.
        ScheduleDatePicker.SelectedDate = DateTime.Today.AddDays(1);
        ScheduleTimeTextBox.Text = "09:00";

        RestoreUserInterfaceState();
        InitializeUserContentSettings();
        InitializeDownloadDirectorySettings();

        ThemeManager.Apply(_settingsStore.LoadTheme());
        InitializeInterfaceFontSettings();
        InitializePaletteEditor();
        MessagesGrid.ItemsSource = _visibleMessages;
        InitializeLiveFolderCounters();
        ConfigureModernMailList();
        MessagesGrid.PreviewMouseLeftButtonDown += BulkRowCheckBox_PreviewMouseLeftButtonDown;
        MessagesGrid.PreviewMouseLeftButtonDown += MailPreviewRow_PreviewMouseLeftButtonDown;
        SelectThemeComboBox(ThemeManager.CurrentMode);
        ThemeManager.ThemeChanged += ThemeManager_ThemeChanged;

        var version = Assembly.GetExecutingAssembly().GetName().Version;
        var displayVersion = version is null
            ? "dev"
            : $"{version.Major}.{version.Minor}.{Math.Max(version.Build, 0)}";
        Title = $"MailRu Desktop v{displayVersion}";
        InitializeBackgroundPush();

        RefreshSavedLogins();

        if (!string.IsNullOrWhiteSpace(_authStore.LastLogin))
        {
            RestoreSavedAuthorization(_authStore.LastLogin);
            RefreshSavedLogins();
        }

        Loaded += MainWindow_Loaded;
        Closed += (_, _) =>
        {
            ThemeManager.ThemeChanged -= ThemeManager_ThemeChanged;
            CancelReaderPresentation();
            _messageLoadCancellation?.Cancel();
            if (MessageWebView.CoreWebView2 is not null && _mailImageProxyConfigured)
                MessageWebView.CoreWebView2.WebResourceRequested -= MailImageProxy_WebResourceRequested;
            _mailImageHttp.Dispose();
            _mailRu.Dispose();
        };
    }

    private async void MainWindow_Loaded(object sender, RoutedEventArgs e)
    {
        await InitializeReaderAsync();
        await EnsureStartupAccountAsync();
        StartBackgroundPush();
        PushMailToastService.Attach(mail => _ = NavigateToPushMailAsync(mail));
    }

    private async Task InitializeReaderAsync()
    {
        try
        {
            var dataRoot = Path.Combine(
                Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
                "MailRuDesktop",
                "WebView2Reader");
            Directory.CreateDirectory(dataRoot);

            var environment = await CoreWebView2Environment.CreateAsync(
                browserExecutableFolder: null,
                userDataFolder: dataRoot);

            await MessageWebView.EnsureCoreWebView2Async(environment);
            MessageWebView.CoreWebView2.Settings.UserAgent = MailRuFixedProfile.UserAgent;
            MessageWebView.CoreWebView2.Settings.IsScriptEnabled = false;
            MessageWebView.CoreWebView2.Settings.AreDevToolsEnabled = false;
            MessageWebView.CoreWebView2.Settings.AreDefaultContextMenusEnabled = true;
            ConfigureMailImageProxy();

            // Navigate the top-level WebView2 ONCE. All future letters are
            // published inside this permanent browser document.
            var ready = new TaskCompletionSource<bool>(
                TaskCreationOptions.RunContinuationsAsynchronously);
            void ShellLoaded(object? _, CoreWebView2NavigationCompletedEventArgs nav)
            {
                ready.TrySetResult(nav.IsSuccess);
            }
            MessageWebView.NavigationCompleted += ShellLoaded;
            try
            {
                MessageWebView.NavigateToString(
                    ReaderShellScripts.CreateShell(ThemeManager.ReaderBackgroundHtml));
                if (!await ready.Task.WaitAsync(TimeSpan.FromSeconds(15)))
                    throw new InvalidOperationException("Не удалось создать область просмотра.");
            }
            finally
            {
                MessageWebView.NavigationCompleted -= ShellLoaded;
            }
            _readerReady = true;
        }
        catch (Exception ex)
        {
            _readerReady = false;
            DiagnosticLog.Write("reader_init", ex.Message);
        }
    }

    private void RefreshSavedLogins()
    {
        var current =
            _activeLogin ??
            LoginComboBox.SelectedItem as string ??
            LoginComboBox.Text;

        _updatingAccountSelection = true;
        try
        {
            var logins = _authStore.Logins;
            LoginComboBox.ItemsSource = logins;

            if (!string.IsNullOrWhiteSpace(current) &&
                logins.Contains(current, StringComparer.OrdinalIgnoreCase))
            {
                LoginComboBox.SelectedItem = logins.First(login =>
                    string.Equals(login, current, StringComparison.OrdinalIgnoreCase));
            }
        }
        finally
        {
            _updatingAccountSelection = false;
        }

        RefreshAccountRail(current);
        SyncBackgroundPush();
    }

    private void RefreshAccountRail(string? preferredLogin = null) =>
        RefreshAccountRailLayout(preferredLogin);

    private async Task EnsureStartupAccountAsync()
    {
        if (!HasMailboxTransport())
        {
            var candidates = new List<string>();
            if (!string.IsNullOrWhiteSpace(_authStore.LastLogin))
                candidates.Add(_authStore.LastLogin);

            foreach (var login in _authStore.Logins)
            {
                if (!candidates.Contains(login, StringComparer.OrdinalIgnoreCase))
                    candidates.Add(login);
            }

            foreach (var login in candidates)
            {
                if (RestoreSavedAuthorization(login))
                    break;
            }
        }

        RefreshAccountRail(_activeLogin);

        if (!HasMailboxTransport())
        {
            AuthStatusText.Text = _authStore.Logins.Count > 0
                ? "Сохранённые аккаунты требуют повторного входа"
                : "Добавьте аккаунт";
            FolderStatusText.Text = "Нет активного аккаунта";
            return;
        }

        ShowWorkspace(MailWorkspace);
        await LoadFolderAsync(0);
        await RefreshAccountUnreadCountsAsync();
    }

    private async Task RefreshAccountUnreadCountsAsync()
    {
        // Iterate a stable snapshot. Account switching can reconcile the visible rail
        // while these asynchronous requests are in flight.
        foreach (var item in _accountRailItems.ToArray())
        {
            if (string.Equals(item.Login, _activeLogin, StringComparison.OrdinalIgnoreCase) &&
                item.Unread is not null)
            {
                continue;
            }

            if (!_authStore.TryRestore(item.Login, out var authorization) ||
                authorization is null ||
                string.IsNullOrWhiteSpace(authorization.AccessToken))
            {
                item.Status = "Требуется повторный вход";
                // Keep the last known counter instead of flashing it away.
                continue;
            }

            item.Status = "Проверка входящих...";
            try
            {
                var raw = await LoadUnreadWithAccountRefreshAsync(authorization);

                var snapshot = MailRuThreadStatusParser.Parse(raw, 0);
                item.Unread = snapshot.MessagesUnread ?? item.Unread ?? 0;
                item.Status = $"Непрочитанных: {item.Unread}";
            }
            catch (MailRuAuthorizationException)
            {
                // An expired token is different from a transient network error.
                // Preserve the unread counter but mark ONLY this account invalid.
                item.Status = "Требуется повторный вход";
                DiagnosticLog.Write("account_auth_rejected", item.Login);
            }
            catch (Exception ex)
            {
                // A transient refresh failure must not erase a previously displayed
                // unread counter.
                item.Status = item.Unread is null
                    ? "Ошибка загрузки"
                    : $"Непрочитанных: {item.Unread} · обновление не удалось";
                DiagnosticLog.Write(
                    "account_unread_" + item.Login,
                    ex.GetType().Name + ": " + ex.Message);
            }
        }
    }

    private void UpdateAccountRailState(long? unread = null, string? status = null)
    {
        foreach (var item in _accountRailItems)
        {
            item.IsActive = string.Equals(item.Login, _activeLogin, StringComparison.OrdinalIgnoreCase);
            if (!item.IsActive)
                continue;

            if (unread is not null)
                item.Unread = unread;
            if (!string.IsNullOrWhiteSpace(status))
                item.Status = status;
        }
    }

    private bool RestoreSavedAuthorization(string? login)
    {
        if (string.IsNullOrWhiteSpace(login) ||
            !_authStore.TryRestore(login.Trim(), out var authorization) ||
            authorization is null ||
            string.IsNullOrWhiteSpace(authorization.AccessToken))
        {
            return false;
        }

        if (!string.Equals(_activeLogin, authorization.Login,
                StringComparison.OrdinalIgnoreCase))
            _selectedMailIds.Clear();
        _accessToken = authorization.AccessToken;
        _refreshToken = authorization.RefreshToken;
        _activeLogin = authorization.Login;

        LoginComboBox.Text = authorization.Login;
        AuthStatusText.Text = "Авторизация активна · access_token";
        _authStore.MarkLastUsed(authorization.Login);
        UpdateAccountRailState(status: "Активен");
        return true;
    }

    private async void LoginComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (_updatingAccountSelection ||
            LoginComboBox.SelectedItem is not string login)
        {
            return;
        }

        if (RestoreSavedAuthorization(login))
            await LoadFolderAsync(0);
    }

    private async void AccountRailListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (_updatingAccountRail || _accountPressHandled || _accountDragActive ||
            AccountRailListBox.SelectedItem is not AccountRailItem item)
        {
            return;
        }

        if (string.Equals(item.Login, _activeLogin, StringComparison.OrdinalIgnoreCase))
        {
            ShowWorkspace(MailWorkspace);
            return;
        }

        var requestedLogin = item.Login;
        var generation = Interlocked.Increment(ref _accountSwitchGeneration);
        item.Status = "Переключение...";

        // Collapse several rapid clicks into the last requested account.
        await Task.Delay(120);
        if (generation != Volatile.Read(ref _accountSwitchGeneration))
            return;

        await _accountSwitchGate.WaitAsync();
        try
        {
            if (generation != Volatile.Read(ref _accountSwitchGeneration))
                return;

            // Do not change the shared authorization while an older folder load is
            // still using it. A newer click may supersede us while we wait.
            while (_loadingFolder)
            {
                await Task.Delay(25);
                if (generation != Volatile.Read(ref _accountSwitchGeneration))
                    return;
            }

            if (!RestoreSavedAuthorization(requestedLogin))
            {
                item.Status = "Требуется повторный вход";
                AuthStatusText.Text = "Не удалось восстановить авторизацию";
                return;
            }

            // Reconcile without recreating AccountRailItem instances. Their last
            // known unread values therefore stay visible during the switch.
            RefreshAccountRail(requestedLogin);
            ShowWorkspace(MailWorkspace);

            await LoadFolderAsync(0, generation);

            if (generation == Volatile.Read(ref _accountSwitchGeneration))
                item.Status = "Активен";
        }
        catch (Exception ex)
        {
            if (generation == Volatile.Read(ref _accountSwitchGeneration))
            {
                item.Status = "Ошибка переключения";
                AuthStatusText.Text = "Не удалось переключить аккаунт";
            }

            DiagnosticLog.Write(
                "account_switch_" + requestedLogin,
                ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            _accountSwitchGate.Release();
        }
    }

    private async void AddAccountButton_Click(object sender, RoutedEventArgs e)
    {
        var dialog = new AddAccountWindow
        {
            Owner = this
        };

        if (dialog.ShowDialog() != true)
            return;

        await AuthenticateAccountAsync(dialog.Login, dialog.Password);
    }

    private async Task AuthenticateAccountAsync(string login, string password)
    {
        AddAccountButton.IsEnabled = false;
        AuthStatusText.Text = "Авторизация через aj-https.mail.ru...";
        ResponseTextBox.Clear();

        ClearRuntimeAuthorization();

        try
        {
            var result = await _mailRu.AuthenticateAsync(login, password);
            WriteAuthDiagnostics(result);

            if (!result.Success && IsInteractiveAuthState(result))
            {
                AuthStatusText.Text = "Mail.ru запросил дополнительную проверку";
                var retry = await TryHandleInteractiveAuthAsync(login, password, result);
                if (retry is not null)
                    result = retry;
            }

            if (!result.Success)
            {
                ShowAuthFailure(result);
                return;
            }

            if (string.IsNullOrWhiteSpace(result.AccessToken))
            {
                ShowAuthFailure(MailRuAuthResult.Failed(
                    "token_missing",
                    MailRuAuthState.ProtocolError,
                    "Authorization succeeded without access_token."));
                return;
            }

            ApplyAuthorization(login, result);
            SaveAuthorization(login);
            RefreshSavedLogins();
            RefreshAccountRail(login);

            _updatingAccountSelection = true;
            try
            {
                LoginComboBox.SelectedItem = login;
                LoginComboBox.Text = login;
            }
            finally
            {
                _updatingAccountSelection = false;
            }

            AuthStatusText.Text = "Авторизация активна · access_token";
            await LoadFolderAsync(0);
        }
        catch (Exception ex)
        {
            ClearRuntimeAuthorization();
            AuthStatusText.Text = "Ошибка авторизации";
            ResponseTextBox.Text = ex.Message;
            DiagnosticLog.Write("auth_exception", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            AddAccountButton.IsEnabled = true;
        }
    }

    private void ApplyAuthorization(string login, MailRuAuthResult result)
    {
        _accessToken = result.AccessToken;
        _refreshToken = result.RefreshToken;
        _activeLogin = login;
    }

    private void SaveAuthorization(string login)
    {
        _authStore.Save(
            login,
            _accessToken,
            _refreshToken,
            null,
            null,
            null,
            null);
    }

    private void ClearRuntimeAuthorization()
    {
        _accessToken = null;
        _refreshToken = null;
        _activeLogin = null;
        _currentFullMessage = null;
        _currentPreparedHtml = null;
    }

    private void ShowAuthFailure(MailRuAuthResult result)
    {
        var translated = TranslateAuthError(result.ErrorCode, result.State);
        AuthStatusText.Text = $"Ошибка: {translated}";
        WriteAuthDiagnostics(result);

        if (string.Equals(
                result.DiagnosticReason,
                "aj_mobile_auth_returned_no_access_token",
                StringComparison.Ordinal))
        {
            AppDialog.Info(
                this,
                "Авторизация Mail.ru",
                "Mail.ru ответил на запрос авторизации, но не выдал access_token. " +
                "Это не считается автоматически CAPTCHA. " +
                "Приложение сохранило обезличенную структуру ответа в диагностике, " +
                "чтобы определить точный вид дополнительной проверки без сохранения пароля или токена.");
        }
    }

    private async void RefreshFolderButton_Click(object sender, RoutedEventArgs e)
    {
        await LoadFolderAsync(_currentFolderId);
    }

    private async void FolderListBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (_updatingFolderSelection ||
            _loadingFolder ||
            FolderListBox.SelectedItem is not MailRuFolderSummary folder)
            return;

        if (SettingsWorkspace.Visibility == Visibility.Visible)
            ShowWorkspace(MailWorkspace);

        if (folder.Id != _currentFolderId)
            await LoadFolderAsync(folder.Id);
    }

    // SelectionChanged is not raised when clicking the already-selected
    // folder. That must still leave Settings and return to the mailbox.
    private void FolderListBox_PreviewMouseLeftButtonUp(
        object sender, MouseButtonEventArgs e)
    {
        if (SettingsWorkspace.Visibility != Visibility.Visible ||
            e.OriginalSource is not DependencyObject source)
            return;

        var item = ItemsControl.ContainerFromElement(FolderListBox, source)
            as ListBoxItem;
        if (item?.DataContext is not MailRuFolderSummary folder)
            return;

        ShowWorkspace(MailWorkspace);
        if (folder.Id != _currentFolderId && !_loadingFolder)
            _ = LoadFolderAsync(folder.Id);
    }

    private void FolderListBox_PreviewKeyDown(object sender, KeyEventArgs e)
    {
        if (e.Key != Key.Enter ||
            SettingsWorkspace.Visibility != Visibility.Visible ||
            FolderListBox.SelectedItem is not MailRuFolderSummary folder)
            return;
        ShowWorkspace(MailWorkspace);
        if (folder.Id != _currentFolderId && !_loadingFolder)
            _ = LoadFolderAsync(folder.Id);
        e.Handled = true;
    }

    private async Task LoadFolderAsync(int folderId, long? accountSwitchGeneration = null)
    {
        bool IsStaleAccountSwitch() =>
            accountSwitchGeneration is not null &&
            accountSwitchGeneration.Value != Volatile.Read(ref _accountSwitchGeneration);

        if (IsStaleAccountSwitch())
            return;

        if (!HasMailboxTransport())
        {
            AuthStatusText.Text = "Сначала выполните вход";
            return;
        }

        if (_loadingFolder)
            return;

        if (folderId != _currentFolderId || accountSwitchGeneration is not null)
        {
            _selectedMailIds.Clear();
            _checkboxRangeAnchorId = null;
        }
        _loadingFolder = true;
        ++_missingSenderLoadGeneration;
        RefreshFolderButton.IsEnabled = false;
        FolderStatusText.Text = "Загрузка...";
        _suppressMessageSelectionChanged = true;
        try
        {
            MessagesGrid.SelectedItem = null;
            _visibleMessages.Clear();
        }
        finally
        {
            _suppressMessageSelectionChanged = false;
        }
        ClearSelectedMessage();

        try
        {
            var raw = await LoadFolderWithRefreshAsync(folderId);

            // A newer account selection supersedes this result.
            if (IsStaleAccountSwitch())
                return;

            ResponseTextBox.Text = raw;
            RefreshThreadCountIndex();
            var snapshot = MailRuThreadStatusParser.Parse(raw, folderId);

            var completeRows = snapshot.Messages.ToList();
            await PrefetchFirstMissingSendersAsync(completeRows, _accessToken);
            if (IsStaleAccountSwitch())
                return;

            _currentFolderId = snapshot.SelectedFolderId ?? folderId;
            _currentMessages = completeRows;
            _serverSearchMode = false;
            ApplyFilters();
            ScheduleMissingSenderResolution();
            UpdateTrashButtonMode();

            if (snapshot.Folders.Count > 0)
                ReplaceFolderSummaries(snapshot.Folders);

            UpdateBulkToolbar();

            var total = snapshot.MessagesTotal?.ToString() ?? "?";
            var unread = snapshot.MessagesUnread?.ToString() ?? "?";
            FolderStatusText.Text =
                $"Всего: {total} · непрочитанных: {unread} · показано: {snapshot.Messages.Count}";

            if (_currentFolderId == 0)
                UpdateAccountRailState(
                    snapshot.MessagesUnread ?? 0,
                    $"Непрочитанных: {snapshot.MessagesUnread ?? 0}");

            if (_currentMessages.Count == 0)
                SelectedSubjectText.Text = "В папке нет распознанных писем";

            if (!string.IsNullOrWhiteSpace(_activeLogin))
                AuthStatusText.Text = "Авторизация активна · access_token";
        }
        catch (Exception ex)
        {
            if (IsStaleAccountSwitch())
                return;

            FolderStatusText.Text = "Ошибка загрузки: " + ex.Message;
            SelectedSubjectText.Text = "Не удалось загрузить почту";
            SelectedSenderText.Text = ex.Message;
            ResponseTextBox.Text = ex.ToString();
            var tokenDenied = ex is MailRuAuthorizationException;
            UpdateAccountRailState(status: tokenDenied
                ? "Требуется повторный вход"
                : "Ошибка загрузки");
            DiagnosticLog.Write("folder_load", ex.GetType().Name + ": " + ex.Message);

            if (!string.IsNullOrWhiteSpace(_activeLogin))
                AuthStatusText.Text = tokenDenied
                    ? "Mail.ru отклонил токен этого аккаунта · требуется повторный вход"
                    : "Не удалось проверить сохранённую авторизацию";
        }
        finally
        {
            RefreshFolderButton.IsEnabled = true;
            _loadingFolder = false;
        }
    }

    private async void MessagesGrid_MouseDoubleClick(object sender, MouseButtonEventArgs e)
    {
        if (ActivePreviewMessage is { } message)
            await OpenDetachedMailWindowAsync(message);
    }

    private async Task OpenDetachedMailWindowAsync(MailRuMessageSummary message)
    {
        if (string.IsNullOrWhiteSpace(_activeLogin)) return;

        var folders = (FolderListBox.ItemsSource as IEnumerable<MailRuFolderSummary>)?.ToArray()
            ?? Array.Empty<MailRuFolderSummary>();

        var window = new MessageWindow(
            _mailRu,
            message,
            _accessToken,
            _currentFolderId,
            folders,
            _currentFullMessage?.Id == message.Id ? _currentFullMessage : null)
        {
            Owner = this
        };

        window.ShowDialog();

        if (window.MailboxChanged)
            await LoadFolderAsync(_currentFolderId);
        if (window.ReplyRequested)
            OpenReplyComposeWindow(message, window.ReplySource);
    }

    private void MessagesGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (_suppressMessageSelectionChanged)
            return;
        RememberSelectedMailIds();
        UpdateBulkToolbar();
        // Checking boxes NEVER changes the current mail preview.
    }

    private void DisplaySummary(MailRuMessageSummary message)
    {
        BeginReaderTransition(message);

        IncomingAttachmentsListBox.ItemsSource = null;
        IncomingAttachmentsPanel.Visibility = Visibility.Collapsed;
        DownloadAttachmentButton.Visibility = Visibility.Collapsed;
        DownloadAllAttachmentsButton.Visibility = Visibility.Collapsed;
        _currentFullMessage = null;
        _currentPreparedHtml = null;
        // Cancel obsolete full-message HTTP requests instead of making the
        // newest selected letter wait behind the previous network response.
        _messageLoadCancellation?.Cancel();
        _messageLoadCancellation = new CancellationTokenSource();
    }

    private void UpdateSelectedMessageHeader(MailRuMessageSummary message)
    {
        SelectedSubjectText.Text = message.Subject;
        SelectedSenderText.Text = string.IsNullOrWhiteSpace(message.SenderEmail)
            ? message.SenderDisplay
            : $"{message.SenderDisplay} <{message.SenderEmail}>";
        SelectedDateText.Text = message.DateDisplay;

        var markers = new List<string>();
        if (message.Unread) markers.Add("непрочитано");
        if (message.Flagged) markers.Add("помечено");
        if (message.Pinned) markers.Add("закреплено");
        if (message.HasAttachment) markers.Add("есть вложения");

        SelectedMetaText.Text =
            $"ID: {message.Id}" +
            (message.FolderId is null ? string.Empty : $" · папка: {message.FolderId}") +
            (string.IsNullOrWhiteSpace(message.SizeDisplay) ? string.Empty : $" · {message.SizeDisplay}") +
            (markers.Count == 0 ? string.Empty : $" · {string.Join(", ", markers)}");
    }

    private async Task LoadFullMessageAsync(MailRuMessageSummary message)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
            return;

        var generation = ++_messageLoadGeneration;
        var requestCancellation = _messageLoadCancellation ??= new CancellationTokenSource();
        var stopwatch = System.Diagnostics.Stopwatch.StartNew();
        _currentFullMessage = null;
        _currentPreparedHtml = null;
        IncomingAttachmentsListBox.ItemsSource = null;

        try
        {
            var full = await _mailRu.GetFullMessageAsync(
                _accessToken,
                message.Id,
                markRead: message.Unread,
                cancellationToken: requestCancellation.Token);

            if (generation != _messageLoadGeneration ||
                !string.Equals(_activePreviewMailId, message.Id, StringComparison.Ordinal))
            {
                return;
            }

            var fetchElapsed = stopwatch.ElapsedMilliseconds;
            _currentFullMessage = full;
            IncomingAttachmentsListBox.ItemsSource = full.Attachments;
            IncomingAttachmentsPanel.Visibility =
                full.Attachments.Count > 0 ? Visibility.Visible : Visibility.Collapsed;
            DownloadAttachmentButton.Visibility =
                full.Attachments.Count > 0 ? Visibility.Visible : Visibility.Collapsed;
            DownloadAllAttachmentsButton.Visibility =
                full.Attachments.Count > 1 ? Visibility.Visible : Visibility.Collapsed;
            ResponseTextBox.Text = full.RawJson;

            // Some smart-thread summaries omit "correspondents.from", while
            // /messages/message still supplies the true sender address.
            // Enrich the selected list row from the verified full message,
            // rather than leaving a blank sender or guessing from the subject.
            var completeSender = message with
            {
                SenderName = string.IsNullOrWhiteSpace(message.SenderName)
                    ? full.FromName : message.SenderName,
                SenderEmail = string.IsNullOrWhiteSpace(message.SenderEmail)
                    ? full.FromEmail : message.SenderEmail,
                Unread = false
            };
            if (!Equals(message, completeSender))
                ReplaceMessage(message, completeSender);

            if (!string.IsNullOrWhiteSpace(full.Html))
            {
                // Images load independently from the WebView2 resource handler.
                // Never serialize image requests ahead of rendering the body.
                _currentPreparedHtml = full.Html;
                _readerWaitingForFullMessage = false;
                ShowReaderHtml(full.Html);
            }
            else if (!string.IsNullOrWhiteSpace(full.Text))
            {
                _readerWaitingForFullMessage = false;
                ShowReaderText(full.Text);
            }
            else
            {
                _readerWaitingForFullMessage = false;
                ShowReaderText(message.Snippet);
            }
            DiagnosticLog.Write("mail_render_timing",
                $"id-length={message.Id.Length}; fetch-ms={fetchElapsed}; " +
                $"body-ready-ms={stopwatch.ElapsedMilliseconds}; html={full.Html.Length}; " +
                $"attachment-count={full.Attachments.Count}");
        }
        catch (OperationCanceledException) when (
            requestCancellation.IsCancellationRequested ||
            generation != _messageLoadGeneration)
        {
            return;
        }
        catch (Exception ex)
        {
            if (generation != _messageLoadGeneration)
                return;

            _readerWaitingForFullMessage = false;
            ShowReaderText(string.IsNullOrWhiteSpace(message.Snippet)
                ? "Не удалось загрузить полное письмо."
                : message.Snippet);
            DiagnosticLog.Write("full_message", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            if (ReferenceEquals(_messageLoadCancellation, requestCancellation))
                _messageLoadCancellation = null;
            requestCancellation.Dispose();
        }
    }

    private async void DownloadAttachmentButton_Click(object sender, RoutedEventArgs e)
    {
        if (_currentFullMessage is null ||
            string.IsNullOrWhiteSpace(_accessToken))
        {
            FolderStatusText.Text = "Вложение недоступно.";
            return;
        }

        var attachment = _currentFullMessage.Attachments.Count == 1
            ? _currentFullMessage.Attachments[0]
            : IncomingAttachmentsListBox.SelectedItem as MailRuIncomingAttachment;

        if (attachment is null)
        {
            FolderStatusText.Text = "Выберите вложение.";
            return;
        }

        if (string.IsNullOrWhiteSpace(attachment.Id))
        {
            FolderStatusText.Text = "У вложения отсутствует идентификатор.";
            return;
        }

        var dialog = new SaveFileDialog
        {
            FileName = SafeFileName(attachment.DisplayName),
            InitialDirectory = _settingsStore.LoadAttachmentDownloadDirectory(),
            Title = "Сохранить вложение"
        };

        if (dialog.ShowDialog(this) != true)
            return;

        DownloadAttachmentButton.IsEnabled = false;
        FolderStatusText.Text = $"Скачивание «{attachment.DisplayName}»...";

        try
        {
            var bytes = await _mailRu.DownloadIncomingAttachmentAsync(
                _accessToken,
                _currentFullMessage.Id,
                attachment.Id);

            await File.WriteAllBytesAsync(dialog.FileName, bytes);
            FolderStatusText.Text = $"Вложение сохранено: {Path.GetFileName(dialog.FileName)}";
            AttachmentDownloadLocation.OpenAfterSaving(dialog.FileName);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка скачивания вложения.";
            DiagnosticLog.Write("incoming_attachment", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            DownloadAttachmentButton.IsEnabled = true;
        }
    }

    private async void MessageUnreadButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not MailRuMessageSummary message)
            return;

        await SetUnreadStateFromCardAsync(message, !message.Unread);
        e.Handled = true;
    }

    private async void MessageFlagButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not MailRuMessageSummary message ||
            string.IsNullOrWhiteSpace(_accessToken))
            return;

        try
        {
            var result = await _mailRu.SetFlaggedAsync(
                _accessToken,
                _activeLogin ?? string.Empty,
                message.Id,
                message.FolderId ?? _currentFolderId,
                !message.Flagged);

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил изменение флажка.";
                return;
            }

            ReplaceMessage(message, message with { Flagged = !message.Flagged });
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка изменения флажка.";
            DiagnosticLog.Write("message_flag", ex.GetType().Name + ": " + ex.Message);
        }

        e.Handled = true;
    }

    private async void MessagePinButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not MailRuMessageSummary message ||
            string.IsNullOrWhiteSpace(_accessToken))
            return;

        try
        {
            var result = await _mailRu.SetPinnedAsync(
                _accessToken,
                _activeLogin ?? string.Empty,
                message.Id,
                message.FolderId ?? _currentFolderId,
                !message.Pinned);

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил изменение закрепления.";
                return;
            }

            ReplaceMessage(message, message with { Pinned = !message.Pinned });
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка изменения закрепления.";
            DiagnosticLog.Write("message_pin", ex.GetType().Name + ": " + ex.Message);
        }

        e.Handled = true;
    }

    private async void MessageArchiveButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not MailRuMessageSummary message ||
            string.IsNullOrWhiteSpace(_accessToken))
            return;

        var folders = (FolderListBox.ItemsSource as IEnumerable<MailRuFolderSummary>)?.ToArray()
            ?? Array.Empty<MailRuFolderSummary>();
        var archive = folders.FirstOrDefault(folder =>
            folder.Type.Equals("archive", StringComparison.OrdinalIgnoreCase) ||
            folder.Name.Equals("Архив", StringComparison.CurrentCultureIgnoreCase) ||
            folder.Id == 500010);

        try
        {
            var result = await _mailRu.MoveMessagesAsync(
                _accessToken,
                new[] { message.Id },
                archive?.Id ?? 500010);

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил перенос в архив.";
                return;
            }

            _currentMessages.RemoveAll(item => item.Id == message.Id);
            ApplyFilters();
            ClearSelectedMessage();
            FolderStatusText.Text = "Письмо перемещено в архив.";
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка перемещения в архив.";
            DiagnosticLog.Write("message_archive", ex.GetType().Name + ": " + ex.Message);
        }

        e.Handled = true;
    }

    private async Task SetUnreadStateFromCardAsync(MailRuMessageSummary message, bool makeUnread)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
            return;

        try
        {
            var result = await _mailRu.SetUnreadAsync(
                _accessToken,
                _activeLogin ?? string.Empty,
                message.Id,
                makeUnread);

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил изменение статуса.";
                return;
            }

            ReplaceMessage(message, message with { Unread = makeUnread });
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка изменения статуса.";
            DiagnosticLog.Write("message_marks", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void ReplaceMessage(MailRuMessageSummary original, MailRuMessageSummary updated)
    {
        var selectedId = _activePreviewMailId;

        if (original.Unread != updated.Unread)
            ChangeFolderUnreadCount(original.FolderId ?? _currentFolderId,
                updated.Unread ? 1 : -1);

        var currentIndex = _currentMessages.FindIndex(item => item.Id == original.Id);
        if (currentIndex >= 0)
            _currentMessages[currentIndex] = updated;

        var visibleIndex = -1;
        for (var i = 0; i < _visibleMessages.Count; i++)
        {
            if (string.Equals(_visibleMessages[i].Id, original.Id, StringComparison.Ordinal))
            {
                visibleIndex = i;
                break;
            }
        }

        _suppressMessageSelectionChanged = true;
        try
        {
            if (visibleIndex >= 0)
                _visibleMessages[visibleIndex] = updated;

            // Only a pinned state change needs reordering. Refreshing all
            // items for sender/read-state changes destroyed multi-selection.
            if (original.Pinned != updated.Pinned)
                MessagesGrid.Items.Refresh();
            RestoreSelectedMailIds();
        }
        finally
        {
            _suppressMessageSelectionChanged = false;
        }

        if (string.Equals(selectedId, updated.Id, StringComparison.Ordinal))
            UpdateSelectedMessageHeader(updated);
    }

    private async void ServerSearchButton_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(_accessToken) || string.IsNullOrWhiteSpace(_activeLogin))
        {
            FilterStatusText.Text = "Сначала выполните вход.";
            return;
        }

        var query = FilterQueryTextBox.Text.Trim();
        if (string.IsNullOrWhiteSpace(query))
            query = FilterSubjectTextBox.Text.Trim();
        if (string.IsNullOrWhiteSpace(query))
            query = FilterSenderTextBox.Text.Trim();

        if (string.IsNullOrWhiteSpace(query))
        {
            FilterStatusText.Text = "Введите текст для поиска.";
            return;
        }

        ServerSearchButton.IsEnabled = false;
        FilterStatusText.Text = "Поиск на сервере...";

        try
        {
            var result = await _mailRu.SearchMessagesAsync(
                _accessToken,
                _activeLogin,
                query,
                limit: 200,
                preferNewSearch: false);

            _currentMessages = result.Messages.ToList();
            _serverSearchMode = true;
            ApplyFilters();
            ResponseTextBox.Text = result.RawResponse;
            FilterStatusText.Text =
                $"Серверный поиск: найдено {result.Found}, показано {_currentMessages.Count} · {result.Source}";

            if (_currentMessages.Count == 0)
                ClearSelectedMessage();
        }
        catch (Exception ex)
        {
            FilterStatusText.Text = "Ошибка серверного поиска.";
            DiagnosticLog.Write("server_search", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            ServerSearchButton.IsEnabled = true;
        }
    }

    private void InteractiveFilter_Changed(object sender, RoutedEventArgs e)
    {
        if (!IsLoaded)
            return;

        ApplyFilters();
    }

    private async void ResetFilterButton_Click(object sender, RoutedEventArgs e)
    {
        FilterSenderTextBox.Clear();
        FilterSubjectTextBox.Clear();
        FilterQueryTextBox.Clear();
        FilterAttachmentsCheckBox.IsChecked = false;
        FilterFromDatePicker.SelectedDate = null;
        FilterToDatePicker.SelectedDate = null;

        if (_serverSearchMode)
        {
            await LoadFolderAsync(_currentFolderId);
            return;
        }

        ApplyFilters();
    }

    private void ApplyFilters()
    {
        IEnumerable<MailRuMessageSummary> filtered = _currentMessages;

        var sender = FilterSenderTextBox.Text.Trim();
        if (!string.IsNullOrWhiteSpace(sender))
        {
            filtered = filtered.Where(message =>
                message.SenderDisplay.Contains(sender, StringComparison.CurrentCultureIgnoreCase) ||
                message.SenderEmail.Contains(sender, StringComparison.CurrentCultureIgnoreCase));
        }

        var subject = FilterSubjectTextBox.Text.Trim();
        if (!string.IsNullOrWhiteSpace(subject))
        {
            filtered = filtered.Where(message =>
                message.Subject.Contains(subject, StringComparison.CurrentCultureIgnoreCase));
        }

        var query = FilterQueryTextBox.Text.Trim();
        if (!string.IsNullOrWhiteSpace(query))
        {
            filtered = filtered.Where(message =>
                message.Subject.Contains(query, StringComparison.CurrentCultureIgnoreCase) ||
                message.Snippet.Contains(query, StringComparison.CurrentCultureIgnoreCase) ||
                message.SenderDisplay.Contains(query, StringComparison.CurrentCultureIgnoreCase) ||
                message.SenderEmail.Contains(query, StringComparison.CurrentCultureIgnoreCase));
        }

        if (FilterAttachmentsCheckBox.IsChecked == true)
            filtered = filtered.Where(message => message.HasAttachment);

        if (FilterFromDatePicker.SelectedDate is DateTime from)
        {
            var fromOffset = new DateTimeOffset(from.Date);
            filtered = filtered.Where(message =>
                message.DateUnix is null ||
                DateTimeOffset.FromUnixTimeSeconds(message.DateUnix.Value).ToLocalTime() >= fromOffset);
        }

        if (FilterToDatePicker.SelectedDate is DateTime to)
        {
            var toOffset = new DateTimeOffset(to.Date.AddDays(1));
            filtered = filtered.Where(message =>
                message.DateUnix is null ||
                DateTimeOffset.FromUnixTimeSeconds(message.DateUnix.Value).ToLocalTime() < toOffset);
        }

        var list = filtered.ToList();
        var selectedId = _activePreviewMailId;

        _suppressMessageSelectionChanged = true;
        try
        {
            _visibleMessages.Clear();
            foreach (var message in list)
                _visibleMessages.Add(message);

            RestoreSelectedMailIds();
        }
        finally
        {
            _suppressMessageSelectionChanged = false;
        }

        if (!string.IsNullOrWhiteSpace(selectedId) &&
            !_currentMessages.Any(item => item.Id == selectedId))
            ClearSelectedMessage();

        FilterStatusText.Text = list.Count == _currentMessages.Count
            ? string.Empty
            : $"Показано по фильтру: {list.Count} из {_currentMessages.Count}";
    }

    private async void LoadContactsButton_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(_accessToken) || string.IsNullOrWhiteSpace(_activeLogin))
        {
            ContactsStatusText.Text = "Сначала выполните вход.";
            return;
        }

        ContactsStatusText.Text = "Загрузка адресной книги...";
        try
        {
            var contacts = await _mailRu.GetAddressBookAsync(_accessToken, _activeLogin);
            ContactsListBox.ItemsSource = contacts;
            ContactsStatusText.Text = $"Контактов на сервере: {contacts.Count}";
        }
        catch (Exception ex)
        {
            ContactsStatusText.Text = "Не удалось загрузить адресную книгу.";
            DiagnosticLog.Write("contacts_load", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async void SearchContactsButton_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(_accessToken) || string.IsNullOrWhiteSpace(_activeLogin))
        {
            ContactsStatusText.Text = "Сначала выполните вход.";
            return;
        }

        ContactsStatusText.Text = "Загрузка быстрых адресатов...";
        try
        {
            var contacts = await _mailRu.SearchPeopleAsync(_accessToken, _activeLogin);
            var query = ContactSearchTextBox.Text.Trim();
            var filtered = string.IsNullOrWhiteSpace(query)
                ? contacts
                : contacts.Where(contact =>
                    contact.Name.Contains(query, StringComparison.CurrentCultureIgnoreCase) ||
                    contact.Email.Contains(query, StringComparison.OrdinalIgnoreCase)).ToArray();

            ContactsListBox.ItemsSource = filtered;
            ContactsStatusText.Text = $"Найдено: {filtered.Count}";
        }
        catch (Exception ex)
        {
            ContactsStatusText.Text = "Не удалось получить быстрый список адресатов.";
            DiagnosticLog.Write("contacts_fast", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void ContactsListBox_MouseDoubleClick(object sender, MouseButtonEventArgs e)
    {
        ComposeToSelectedContact();
    }

    private void ComposeToContactButton_Click(object sender, RoutedEventArgs e)
    {
        ComposeToSelectedContact();
    }

    private void ComposeToSelectedContact()
    {
        var email = ContactsListBox.SelectedItem switch
        {
            MailRuContactSummary contact => contact.Email,
            string value => value,
            _ => null
        };

        if (string.IsNullOrWhiteSpace(email))
        {
            ContactsStatusText.Text = "Выберите контакт.";
            return;
        }

        ComposeToTextBox.Text = email;
        ShowComposeWindow();
        ComposeSubjectTextBox.Focus();
    }

    private async void MoveMessageButton_Click(object sender, RoutedEventArgs e)
    {
        if (MoveFolderComboBox.SelectedItem is not MailRuFolderSummary folder)
        {
            FolderStatusText.Text = "Выберите папку назначения.";
            return;
        }

        var targets = ResolveActionMessages();
        if (targets.Count == 0) return;
        await MoveMessagesAsync(targets.Select(m => m.Id).ToArray(),
            folder.Id, $"Перемещение в «{folder.Name}»");
    }

    private async void TrashMessageButton_Click(object sender, RoutedEventArgs e)
    {
        var targets = ResolveActionMessages();
        if (targets.Count == 0) return;
        if (_currentFolderId == 500002)
        {
            FolderStatusText.Text = "Письмо уже находится в Корзине.";
            return;
        }

        await MoveMessagesAsync(targets.Select(m => m.Id).ToArray(),
            500002, "Перемещение в корзину");
    }

    private async Task MoveMessagesAsync(
        IReadOnlyCollection<string> ids,
        int destinationFolderId,
        string operationName)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            FolderStatusText.Text = "Нет access_token. Выполните авторизацию заново.";
            return;
        }

        var operationAccount = _activeLogin;
        var operationFolder = _currentFolderId;
        MoveMessageButton.IsEnabled = false;
        TrashMessageButton.IsEnabled = false;
        FolderStatusText.Text = operationName + "...";

        try
        {
            var result = await _mailRu.MoveMessagesAsync(
                _accessToken,
                ids,
                destinationFolderId);

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил перемещение.";
                return;
            }

            if (string.Equals(_activeLogin, operationAccount, StringComparison.OrdinalIgnoreCase)
                && _currentFolderId == operationFolder)
            {
                var unread = _currentMessages.Count(x => ids.Contains(x.Id) && x.Unread);
                if (unread > 0)
                    ChangeFolderUnreadCount(destinationFolderId, unread);
                RemoveConfirmedMailRows(ids);
                if (_currentFolderId == 0 && unread > 0)
                    AdjustActiveInboxUnread(-unread);
            }
            FolderStatusText.Text = "Перемещено писем: " + ids.Count;
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка перемещения.";
            DiagnosticLog.Write("message_move", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            MoveMessageButton.IsEnabled = true;
            TrashMessageButton.IsEnabled = true;
        }
    }

    private async Task DeleteSelectedPermanentlyAsync(MailRuMessageSummary message)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            FolderStatusText.Text = "Нет access_token. Выполните авторизацию заново.";
            return;
        }

        TrashMessageButton.IsEnabled = false;
        FolderStatusText.Text = "Окончательное удаление...";

        try
        {
            var result = await _mailRu.RemoveMessagesAsync(
                _accessToken,
                _activeLogin ?? string.Empty,
                new[] { message.Id });

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил удаление.";
                return;
            }

            RemoveConfirmedMailRows([message.Id]);
            FolderStatusText.Text = "Письмо удалено.";
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка окончательного удаления.";
            DiagnosticLog.Write("message_remove", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            TrashMessageButton.IsEnabled = true;
        }
    }

    private void UpdateTrashButtonMode()
    {
        TrashMessageButton.Content = "В корзину";
        TrashMessageButton.IsEnabled = _currentFolderId != 500002;
    }

    private void ClearSelectedMessage()
    {
        ClearActivePreviewId();
        _messageLoadCancellation?.Cancel();
        _messageLoadGeneration++;
        _currentFullMessage = null;
        SelectedSubjectText.Text = "Выберите письмо";
        SelectedSenderText.Text = string.Empty;
        SelectedDateText.Text = string.Empty;
        SelectedMetaText.Text = string.Empty;
        IncomingAttachmentsListBox.ItemsSource = null;
        IncomingAttachmentsPanel.Visibility = Visibility.Collapsed;
        DownloadAttachmentButton.Visibility = Visibility.Collapsed;
        DownloadAllAttachmentsButton.Visibility = Visibility.Collapsed;
        BeginReaderTransition(null, "Выберите письмо.");
        _ = SetReaderEmptyAsync("Выберите письмо.");
    }

    private void FolderManageToggleButton_Click(object sender, RoutedEventArgs e)
    {
        FolderManageExpander.IsExpanded = !FolderManageExpander.IsExpanded;
        if (FolderManageExpander.IsExpanded)
            FolderNameTextBox.Focus();
    }

    private async void CreateFolderButton_Click(object sender, RoutedEventArgs e)
    {
        var name = FolderNameTextBox.Text.Trim();
        if (string.IsNullOrWhiteSpace(name) ||
            string.IsNullOrWhiteSpace(_accessToken) ||
            string.IsNullOrWhiteSpace(_activeLogin))
        {
            FolderStatusText.Text = "Введите имя папки и выполните вход.";
            return;
        }

        try
        {
            var result = await _mailRu.CreateFolderAsync(_accessToken, _activeLogin, name);
            ResponseTextBox.Text = result.RawResponse;
            FolderStatusText.Text = result.Success ? "Папка создана." : "Mail.ru отклонил создание папки.";
            if (result.Success)
            {
                FolderNameTextBox.Clear();
                await LoadFolderAsync(_currentFolderId);
            }
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка создания папки.";
            DiagnosticLog.Write("folder_create", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async void RenameFolderButton_Click(object sender, RoutedEventArgs e)
    {
        if (FolderListBox.SelectedItem is not MailRuFolderSummary folder)
        {
            FolderStatusText.Text = "Выберите папку.";
            return;
        }
        if (folder.IsSystem)
        {
            FolderStatusText.Text = "Системную папку переименовывать нельзя.";
            return;
        }

        var name = FolderNameTextBox.Text.Trim();
        if (string.IsNullOrWhiteSpace(name) ||
            string.IsNullOrWhiteSpace(_accessToken) ||
            string.IsNullOrWhiteSpace(_activeLogin))
        {
            FolderStatusText.Text = "Введите новое имя папки.";
            return;
        }

        try
        {
            var result = await _mailRu.RenameFolderAsync(_accessToken, _activeLogin, folder.Id, name);
            ResponseTextBox.Text = result.RawResponse;
            FolderStatusText.Text = result.Success ? "Папка переименована." : "Mail.ru отклонил переименование.";
            if (result.Success)
            {
                FolderNameTextBox.Clear();
                await LoadFolderAsync(folder.Id);
            }
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка переименования папки.";
            DiagnosticLog.Write("folder_rename", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async void DeleteFolderButton_Click(object sender, RoutedEventArgs e)
    {
        if (FolderListBox.SelectedItem is not MailRuFolderSummary folder)
        {
            FolderStatusText.Text = "Выберите папку.";
            return;
        }
        if (folder.IsSystem)
        {
            FolderStatusText.Text = "Системную папку удалять нельзя.";
            return;
        }
        if (string.IsNullOrWhiteSpace(_accessToken) || string.IsNullOrWhiteSpace(_activeLogin))
            return;

        if (MessageBox.Show(
                this,
                $"Удалить папку «{folder.Name}»?",
                "MailRu Desktop",
                MessageBoxButton.YesNo,
                MessageBoxImage.Warning) != MessageBoxResult.Yes)
            return;

        try
        {
            var result = await _mailRu.DeleteFolderAsync(_accessToken, _activeLogin, folder.Id);
            ResponseTextBox.Text = result.RawResponse;
            FolderStatusText.Text = result.Success ? "Папка удалена." : "Mail.ru отклонил удаление папки.";
            if (result.Success)
                await LoadFolderAsync(0);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка удаления папки.";
            DiagnosticLog.Write("folder_delete", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async void ClearFolderButton_Click(object sender, RoutedEventArgs e)
    {
        if (FolderListBox.SelectedItem is not MailRuFolderSummary folder ||
            string.IsNullOrWhiteSpace(_accessToken) ||
            string.IsNullOrWhiteSpace(_activeLogin))
        {
            FolderStatusText.Text = "Выберите папку.";
            return;
        }

        if (MessageBox.Show(
                this,
                $"Удалить все письма из папки «{folder.Name}»?",
                "MailRu Desktop",
                MessageBoxButton.YesNo,
                MessageBoxImage.Warning) != MessageBoxResult.Yes)
            return;

        try
        {
            var result = await _mailRu.ClearFolderAsync(_accessToken, _activeLogin, folder.Id);
            ResponseTextBox.Text = result.RawResponse;
            FolderStatusText.Text = result.Success ? "Папка очищена." : "Mail.ru отклонил очистку папки.";
            if (result.Success)
                await LoadFolderAsync(folder.Id);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка очистки папки.";
            DiagnosticLog.Write("folder_clear", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void AttachButton_Click(object sender, RoutedEventArgs e)
    {
        var dialog = new OpenFileDialog
        {
            Multiselect = true,
            CheckFileExists = true,
            Title = "Выберите вложения"
        };

        if (dialog.ShowDialog(this) != true)
            return;

        foreach (var path in dialog.FileNames)
        {
            if (!_attachmentPaths.Contains(path, StringComparer.OrdinalIgnoreCase))
                _attachmentPaths.Add(path);
        }

        RefreshComposeAttachments();
    }

    private sealed record ComposeAttachmentItem(string Path, string Name);

    private void RefreshComposeAttachments()
    {
        AttachmentSummaryText.Text = _attachmentPaths.Count == 0
            ? "Вложений нет"
            : $"Вложений: {_attachmentPaths.Count}";

        ComposeAttachmentsItemsControl.ItemsSource = _attachmentPaths
            .Select(path => new ComposeAttachmentItem(path, Path.GetFileName(path)))
            .ToArray();
    }

    private void RemoveComposeAttachmentButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not string path)
            return;

        _attachmentPaths.RemoveAll(item =>
            string.Equals(item, path, StringComparison.OrdinalIgnoreCase));
        RefreshComposeAttachments();
    }

    private bool TryGetComposeSender(out string login, out string token)
    {
        login = _composeSenderLogin ?? string.Empty;
        token = _composeSenderToken ?? string.Empty;
        if (_composeWindow is not null &&
            !string.IsNullOrWhiteSpace(login) && !string.IsNullOrWhiteSpace(token))
            return true;

        ComposeStatusText.Text = "Укажите авторизованный аккаунт отправителя.";
        return false;
    }

    private async void SaveDraftButton_Click(object sender, RoutedEventArgs e)
    {
        if (!TryGetComposeSender(out var senderLogin, out var senderToken))
            return;

        SaveDraftButton.IsEnabled = false;
        AttachButton.IsEnabled = false;
        ComposeStatusText.Text = "Сохранение черновика...";

        try
        {
            var messageId = MailRuClient.KnownWorkingMessageId;
            var attachmentIds = new List<string>();

            for (var i = 0; i < _attachmentPaths.Count; i++)
            {
                var path = _attachmentPaths[i];
                ComposeStatusText.Text = $"Загрузка вложения {i + 1}/{_attachmentPaths.Count}...";

                await using var stream = File.OpenRead(path);
                attachmentIds.Add(await _mailRu.UploadAttachmentAsync(
                    senderToken,
                    stream,
                    Path.GetFileName(path),
                    messageId));
            }

            var result = await _mailRu.SaveDraftAsync(
                senderToken,
                senderLogin,
                new MailRuOutgoingMessage(
                    To: ComposeToTextBox.Text.Trim(),
                    Subject: ComposeSubjectTextBox.Text,
                    Text: ComposeBodyTextBox.Text,
                    Html: _composeRichEditor?.ToHtml(),
                    AttachmentIds: attachmentIds,
                    MessageId: messageId));

            ResponseTextBox.Text = result.RawResponse;
            ComposeStatusText.Text = result.Success
                ? "Черновик сохранён на сервере."
                : "Mail.ru отклонил сохранение черновика.";
        }
        catch (Exception ex)
        {
            ComposeStatusText.Text = "Ошибка сохранения черновика.";
            DiagnosticLog.Write("draft_save", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            SaveDraftButton.IsEnabled = true;
            AttachButton.IsEnabled = true;
        }
    }

    private async void SendButton_Click(object sender, RoutedEventArgs e)
    {
        ComposeStatusText.SetResourceReference(TextBlock.ForegroundProperty, "AppMutedTextBrush");
        if (!TryGetComposeSender(out var senderLogin, out var senderToken))
            return;

        var recipient = ComposeToTextBox.Text.Trim();
        if (recipient.Length == 0)
        {
            ComposeStatusText.Text = "Укажите получателя.";
            return;
        }

        string? sendDate = null;
        DateTimeOffset? scheduledFor = null;

        if (ScheduleSendCheckBox.IsChecked == true)
        {
            if (ScheduleDatePicker.SelectedDate is not DateTime selectedDate)
            {
                ComposeStatusText.Text = "Выберите дату отложенной отправки.";
                return;
            }

            var timeText = ScheduleTimeTextBox.Text.Trim();
            if (!DateTime.TryParseExact(
                    timeText,
                    new[] { "H:mm", "HH:mm" },
                    CultureInfo.InvariantCulture,
                    DateTimeStyles.None,
                    out var parsedTime))
            {
                ComposeStatusText.Text = "Время должно быть в формате ЧЧ:ММ.";
                return;
            }

            var localDateTime = DateTime.SpecifyKind(
                selectedDate.Date.Add(parsedTime.TimeOfDay),
                DateTimeKind.Local);
            scheduledFor = new DateTimeOffset(localDateTime);

            if (scheduledFor <= DateTimeOffset.Now.AddMinutes(1))
            {
                ComposeStatusText.Text = "Время отложенной отправки должно быть в будущем.";
                return;
            }

            // VBA passes дата_время_отправки straight into send_date and switches
            // endpoint from /send to /schedule. The Mail.ru mobile API uses the
            // scheduled instant as Unix time (seconds).
            sendDate = scheduledFor.Value
                .ToUnixTimeSeconds()
                .ToString(CultureInfo.InvariantCulture);
        }

        SendButton.IsEnabled = false;
        AttachButton.IsEnabled = false;
        ComposeStatusText.Text = "Подготовка письма...";

        try
        {
            var messageId = MailRuClient.KnownWorkingMessageId;
            var attachmentIds = new List<string>();

            for (var i = 0; i < _attachmentPaths.Count; i++)
            {
                var path = _attachmentPaths[i];
                ComposeStatusText.Text = $"Загрузка вложения {i + 1}/{_attachmentPaths.Count}...";

                await using var stream = File.OpenRead(path);
                var attachId = await _mailRu.UploadAttachmentAsync(
                    senderToken,
                    stream,
                    Path.GetFileName(path),
                    messageId);

                attachmentIds.Add(attachId);
            }

            ComposeStatusText.Text = "Отправка...";

            var result = await _mailRu.SendMessageAsync(
                senderToken,
                new MailRuOutgoingMessage(
                    To: recipient,
                    Subject: ComposeSubjectTextBox.Text,
                    Text: ComposeBodyTextBox.Text,
                    Html: _composeRichEditor?.ToHtml(),
                    SendDate: sendDate,
                    RequestReadReceipt: RequestReadReceiptCheckBox.IsChecked == true,
                    AttachmentIds: attachmentIds,
                    MessageId: messageId,
                    ReplyToId: _composeReplyToId));

            ResponseTextBox.Text = result.RawResponse;

            if (!result.Success)
            {
                ComposeStatusText.SetResourceReference(TextBlock.ForegroundProperty, "AppDangerBrush");
                ComposeStatusText.Text = scheduledFor is null
                    ? "Mail.ru отклонил отправку."
                    : "Mail.ru отклонил отложенную отправку.";
                DiagnosticLog.Write(
                    scheduledFor is null ? "send_rejected" : "schedule_rejected",
                    result.RawResponse);
                return;
            }

            ComposeToTextBox.Clear();
            ComposeSubjectTextBox.Clear();
            ComposeBodyTextBox.Clear();
            _composeReplyToId = null;
            ResetComposeTemplateSelectors();
            _attachmentPaths.Clear();
            RefreshComposeAttachments();
            RequestReadReceiptCheckBox.IsChecked = false;
            ScheduleSendCheckBox.IsChecked = false;
            ScheduleDatePicker.SelectedDate = DateTime.Today.AddDays(1);
            ScheduleTimeTextBox.Text = "09:00";

            ComposeStatusText.SetResourceReference(TextBlock.ForegroundProperty, "AppSuccessBrush");
            if (scheduledFor is null)
            {
                ComposeStatusText.Text = "Отправлено.";
            }
            else
            {
                ComposeStatusText.Text =
                    $"Запланировано на {scheduledFor.Value.LocalDateTime:dd.MM.yyyy HH:mm}.";
            }
        }
        catch (Exception ex)
        {
            ComposeStatusText.SetResourceReference(TextBlock.ForegroundProperty, "AppDangerBrush");
            ComposeStatusText.Text = ex.Message;
            DiagnosticLog.Write("send", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            SendButton.IsEnabled = true;
            AttachButton.IsEnabled = true;
        }
    }

    private async Task<string> LoadFolderWithRefreshAsync(int folderId)
    {
        try
        {
            return await LoadFolderRawAsync(folderId);
        }
        catch (MailRuAuthorizationException) when (
            !string.IsNullOrWhiteSpace(_refreshToken) &&
            !string.IsNullOrWhiteSpace(_activeLogin))
        {
            var account = _activeLogin!;
            var oldAccess = _accessToken!;
            var savedRefresh = _refreshToken!;
            AuthStatusText.Text = "Сервер отклонил токен · обновляем авторизацию...";

            var refreshed = await _mailRu.RefreshAccessTokenAsync(savedRefresh);
            if (!refreshed.Success || string.IsNullOrWhiteSpace(refreshed.AccessToken))
                throw new MailRuAuthorizationException(403);

            // First prove the new token really opens this mailbox. Invalid
            // replacement credentials must NEVER overwrite saved authorization.
            var raw = await _mailRu.GetFolderThreadsAsync(refreshed.AccessToken, folderId);

            if (string.Equals(_activeLogin, account, StringComparison.OrdinalIgnoreCase) &&
                string.Equals(_accessToken, oldAccess, StringComparison.Ordinal))
            {
                _accessToken = refreshed.AccessToken;
                _refreshToken = refreshed.RefreshToken ?? savedRefresh;
                _authStore.UpdateTokens(account, _accessToken, _refreshToken);
            SyncBackgroundPush();
                AuthStatusText.Text = "Авторизация проверена и обновлена";
                DiagnosticLog.Write("auth_refresh", account + ": refreshed and verified");
            }
            return raw;
        }
    }

    private async Task<string> LoadUnreadWithAccountRefreshAsync(RestoredAuthorization account)
    {
        try
        {
            return await _mailRu.GetFolderThreadsAsync(
                account.AccessToken!, 0, offset: 0, limit: 1);
        }
        catch (MailRuAuthorizationException) when (
            !string.IsNullOrWhiteSpace(account.RefreshToken))
        {
            var refreshed = await _mailRu.RefreshAccessTokenAsync(account.RefreshToken);
            if (!refreshed.Success || string.IsNullOrWhiteSpace(refreshed.AccessToken))
                throw new MailRuAuthorizationException(403);

            var raw = await _mailRu.GetFolderThreadsAsync(
                refreshed.AccessToken, 0, offset: 0, limit: 1);

            // No changes to LastLogin or other accounts. Refresh the active
            // runtime only when it still uses this exact stale token.
            _authStore.UpdateTokens(account.Login, refreshed.AccessToken,
                refreshed.RefreshToken ?? account.RefreshToken);
            SyncBackgroundPush();
            if (string.Equals(_activeLogin, account.Login, StringComparison.OrdinalIgnoreCase) &&
                string.Equals(_accessToken, account.AccessToken, StringComparison.Ordinal))
            {
                _accessToken = refreshed.AccessToken;
                _refreshToken = refreshed.RefreshToken ?? account.RefreshToken;
            }

            DiagnosticLog.Write("auth_refresh", account.Login + ": counter token updated");
            return raw;
        }
    }

    private async Task<string> LoadFolderRawAsync(int folderId)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            throw new InvalidOperationException(
                "Нет access_token. Выполните авторизацию заново.");
        }

        return await _mailRu.GetFolderThreadsAsync(_accessToken, folderId);
    }

    private bool HasMailboxTransport() =>
        !string.IsNullOrWhiteSpace(_accessToken);

    private void ShowReaderText(string? text)
    {
        var encoded = WebUtility.HtmlEncode(text ?? string.Empty)
            .Replace("\r\n", "<br>", StringComparison.Ordinal)
            .Replace("\n", "<br>", StringComparison.Ordinal);

        ShowReaderDocument($"<div class='plain'>{encoded}</div>");
    }

    private void ShowReaderHtml(string html)
    {
        ShowReaderDocument(html);
    }

    private void ShowReaderDocument(string body)
    {
        if (!_readerReady || MessageWebView.CoreWebView2 is null)
            return;

        var background = ThemeManager.ReaderBackgroundHtml;
        var foreground = ThemeManager.ReaderForegroundHtml;
        var muted = ThemeManager.ReaderMutedHtml;
        var link = ThemeManager.ReaderLinkHtml;

        var darkMailOverrides = ThemeManager.IsDarkEffective
            ? $"body, body div, body table, body tbody, body thead, body tfoot, body tr, body td, body th, body p, body section, body article, body header, body footer, body main {{ background-color: transparent !important; color: {foreground} !important; }}" +
              $"body span {{ color: inherit !important; }}" +
              $"body a, body a * {{ color: {link} !important; }}" +
              $"body [bgcolor] {{ background-color: transparent !important; }}" +
              $"body [style*='background'] {{ background-color: transparent !important; }}"
            : string.Empty;

        var document =
            "<!doctype html><html><head><meta charset=\"utf-8\">" +
            "<meta http-equiv=\"Content-Security-Policy\" content=\"default-src 'none'; img-src data: https: http:; style-src 'unsafe-inline';\">" +
            "<style>" +
            $"html, body {{ background-color: {background} !important; color: {foreground} !important; }}" +
            "body { font-family: Segoe UI, Arial, sans-serif; font-size: 14px; margin: 14px; overflow-wrap: anywhere; }" +
            "img { max-width: 100%; height: auto; }" +
            "pre { white-space: pre-wrap; }" +
            $"blockquote {{ border-left: 3px solid {muted}; margin-left: 8px; padding-left: 10px; color: {muted}; }}" +
            $"a {{ color: {link}; text-decoration: none; }}" +
            darkMailOverrides +
            ThemeManager.ReaderScrollbarCss +
            "</style></head><body>" +
            body +
            "</body></html>";

        // A staged iframe replaces only the browser's internal DOM content.
        // The WPF WebView2 control itself never disappears or navigates.
        _ = StageReaderDocumentAsync(_readerGeneration, document);
    }

    private void ThemeManager_ThemeChanged(object? sender, EventArgs e)
    {
        if (!Dispatcher.CheckAccess())
        {
            Dispatcher.BeginInvoke(() => ThemeManager_ThemeChanged(sender, e));
            return;
        }

        // A theme update while images or navigation are still pending must
        // never replace the new letter with the raw, unprepared HTML body.
        if (!_readerReady || _readerWaitingForFullMessage)
            return;

        if (_currentFullMessage is not null)
        {
            BeginReaderTransition(ActivePreviewMessage);
            _readerWaitingForFullMessage = false;
            if (!string.IsNullOrWhiteSpace(_currentPreparedHtml))
                ShowReaderHtml(_currentPreparedHtml);
            else if (!string.IsNullOrWhiteSpace(_currentFullMessage.Html))
                ShowReaderHtml(_currentFullMessage.Html);
            else
                ShowReaderText(_currentFullMessage.Text);
        }
        else if (!_readerWaitingForFullMessage &&
                 ActivePreviewMessage is not MailRuMessageSummary)
        {
            ShowReaderText("Выберите письмо.");
        }
    }

    private void ThemeComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (ThemeComboBox.SelectedItem is not ComboBoxItem item ||
            item.Tag is not string tag ||
            !Enum.TryParse<AppThemeMode>(tag, true, out var mode))
        {
            return;
        }

        ThemeManager.Apply(mode);
        _settingsStore.SaveTheme(mode);

        // ThemeChanged is the only place that re-renders a ready document.
        // Never put an interim snippet into the viewer while a mail loads.
    }

    private void SelectThemeComboBox(AppThemeMode mode)
    {
        foreach (var item in ThemeComboBox.Items.OfType<ComboBoxItem>())
        {
            if (string.Equals(item.Tag?.ToString(), mode.ToString(), StringComparison.OrdinalIgnoreCase))
            {
                ThemeComboBox.SelectedItem = item;
                break;
            }
        }
    }

    private void ShowMailButton_Click(object sender, RoutedEventArgs e) =>
        ShowWorkspace(MailWorkspace);

    private void ShowContactsButton_Click(object sender, RoutedEventArgs e) =>
        ShowWorkspace(ContactsWorkspace);

    private void ShowComposeButton_Click(object sender, RoutedEventArgs e) =>
        ShowComposeWindow();

    private void CopyDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            Clipboard.SetText(ResponseTextBox.Text ?? string.Empty);
        }
        catch (Exception ex)
        {
            AppDialog.Info(this, "Диагностика", "Не удалось скопировать: " + ex.Message);
        }
    }

    private void ShowSettingsButton_Click(object sender, RoutedEventArgs e)
    {
        // A second click returns to mail now that the redundant Mail nav item is gone.
        if (SettingsWorkspace.Visibility == Visibility.Visible)
        {
            ShowWorkspace(MailWorkspace);
            return;
        }
        RefreshTemplatesFromDisk();
        ShowWorkspace(SettingsWorkspace);
    }

    private void ShowWorkspace(FrameworkElement workspace)
    {
        // Refresh defaults on each new compose session, including when the
        // application has stayed open across midnight. Do not touch an active
        // scheduled draft that the user has already configured.
        if (ReferenceEquals(workspace, ComposeWorkspace) &&
            ScheduleSendCheckBox.IsChecked != true)
        {
            ScheduleDatePicker.SelectedDate = DateTime.Today.AddDays(1);
            ScheduleTimeTextBox.Text = "09:00";
        }

        MailWorkspace.Visibility = Visibility.Collapsed;
        ContactsWorkspace.Visibility = Visibility.Collapsed;
        if (_composeWindow is null)
            ComposeWorkspace.Visibility = Visibility.Collapsed;
        SettingsWorkspace.Visibility = Visibility.Collapsed;
        workspace.Visibility = Visibility.Visible;
    }

    private static string SafeFileName(string value)
    {
        var invalid = Path.GetInvalidFileNameChars();
        var safe = new string(value.Select(ch => invalid.Contains(ch) ? '_' : ch).ToArray());
        return string.IsNullOrWhiteSpace(safe) ? "attachment" : safe;
    }

    
    private static string TranslateAuthError(string? code, MailRuAuthState state) =>
        state switch
        {
            MailRuAuthState.Blocked => "Blocked — Mail.ru заблокировал вход или требует разблокировку аккаунта",
            MailRuAuthState.RecoveryRequired => "Mail.ru требует восстановление/подтверждение аккаунта",
            MailRuAuthState.ReCaptcha => string.Equals(code, "recaptcha_rejected", StringComparison.OrdinalIgnoreCase)
                ? "Mail.ru отклонил ответ reCAPTCHA; причина сохранена в диагностике"
                : "требуется reCAPTCHA",
            MailRuAuthState.Captcha => "требуется CAPTCHA",
            MailRuAuthState.TwoFactor => "требуется двухфакторная проверка",
            MailRuAuthState.InvalidCredentials => "неверный логин или пароль",
            _ => code switch
            {
                "captcha_required" => "требуется CAPTCHA",
                "recaptcha_required" => "требуется reCAPTCHA",
                "two_factor_required" => "требуется двухфакторная проверка",
                "account_recovery_required" => "Mail.ru требует восстановление/дополнительную проверку",
                "account_blocked" => "Blocked — аккаунт заблокирован",
                "invalid_credentials" => "неверный логин или пароль",
                "web_session_token_missing" => "сессия создана, но API-token не найден",
                "unknown_auth_result" => "неизвестный ответ Mail.ru; причина сохранена в диагностике",
                "token_missing" => "токен не получен",
                null or "" => "неизвестная ошибка",
                _ => code
            }
        };
}
