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
    private List<MailRuMessageSummary> _currentMessages = [];

    private string? _accessToken;
    private string? _refreshToken;
    private string? _webToken;
    private string? _searchToken;
    private string? _webCookieHeader;
    private string? _touchCookieHeader;
    private string? _activeLogin;

    private int _currentFolderId;
    private bool _loadingFolder;
    private bool _updatingFolderSelection;
    private bool _updatingAccountSelection;
    private bool _lastFolderUsedTouchSearch;
    private bool _readerReady;
    private long _messageLoadGeneration;
    private MailRuFullMessage? _currentFullMessage;

    public MainWindow()
    {
        InitializeComponent();

        ThemeManager.Apply(_settingsStore.LoadTheme());
        SelectThemeComboBox(ThemeManager.CurrentMode);

        var version = Assembly.GetExecutingAssembly().GetName().Version;
        var displayVersion = version is null
            ? "dev"
            : $"{version.Major}.{version.Minor}.{Math.Max(version.Build, 0)}";
        Title = $"MailRu Desktop v{displayVersion}";

        RefreshSavedLogins();

        if (!string.IsNullOrWhiteSpace(_authStore.LastLogin))
        {
            RestoreSavedAuthorization(_authStore.LastLogin);
            RefreshSavedLogins();
        }

        Loaded += MainWindow_Loaded;
        Closed += (_, _) => _mailRu.Dispose();
    }

    private async void MainWindow_Loaded(object sender, RoutedEventArgs e)
    {
        await InitializeReaderAsync();

        if (HasMailboxTransport())
            await LoadFolderAsync(0);
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
            _readerReady = true;

            ShowReaderText("Выберите письмо.");
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
    }

    private bool RestoreSavedAuthorization(string? login)
    {
        if (string.IsNullOrWhiteSpace(login) ||
            !_authStore.TryRestore(login.Trim(), out var authorization) ||
            authorization is null)
        {
            return false;
        }

        _accessToken = authorization.AccessToken;
        _refreshToken = authorization.RefreshToken;
        _webToken = authorization.WebToken;
        _searchToken = authorization.SearchToken;
        _webCookieHeader = authorization.WebCookieHeader;
        _touchCookieHeader = authorization.TouchCookieHeader;
        _activeLogin = authorization.Login;

        LoginComboBox.Text = authorization.Login;
        AuthStatusText.Text = "Авторизация активна";
        _authStore.MarkLastUsed(authorization.Login);
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
        AuthStatusText.Text = "Авторизация...";
        ResponseTextBox.Clear();

        // Every explicit login starts from a clean runtime/cookie state.
        ClearRuntimeAuthorization();

        try
        {
            var result = await _mailRu.AuthenticateAsync(login, password);

            if (!string.IsNullOrWhiteSpace(result.DiagnosticReason))
                DiagnosticLog.Write("auth", result.DiagnosticReason);

            if (result.Challenge is not null)
            {
                AuthStatusText.Text = ChallengeStatus(result.Challenge.Kind);

                var challengeWindow = new ChallengeWindow(result.Challenge)
                {
                    Owner = this
                };

                var challengeAccepted = challengeWindow.ShowDialog() == true &&
                                        challengeWindow.Completion is not null;

                if (challengeAccepted)
                {
                    var completed = await _mailRu.CompleteChallengeAsync(
                        login,
                        challengeWindow.Completion!);

                    if (!string.IsNullOrWhiteSpace(completed.DiagnosticReason))
                        DiagnosticLog.Write("auth_challenge", completed.DiagnosticReason);

                    if (completed.Success)
                    {
                        result = new MailRuAuthResult(
                            true,
                            result.AccessToken,
                            result.RefreshToken,
                            null)
                        {
                            State = MailRuAuthState.Success,
                            WebToken = completed.WebToken,
                            SearchToken = completed.SearchToken,
                            WebCookieHeader = completed.WebCookieHeader,
                            TouchCookieHeader = completed.TouchCookieHeader
                        };
                    }
                    else if (!result.Success)
                    {
                        ShowAuthFailure(completed);
                        return;
                    }
                }
                else if (!result.Success)
                {
                    AuthStatusText.Text = "Авторизация отменена";
                    return;
                }
            }

            if (!result.Success)
            {
                ShowAuthFailure(result);
                return;
            }

            ApplyAuthorization(login, result);
            SaveAuthorization(login);
            RefreshSavedLogins();

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

            AuthStatusText.Text = "Авторизация активна";
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
        _webToken = result.WebToken;
        _searchToken = result.SearchToken;
        _webCookieHeader = result.WebCookieHeader;
        _touchCookieHeader = result.TouchCookieHeader;
        _activeLogin = login;
    }

    private void SaveAuthorization(string login)
    {
        _authStore.Save(
            login,
            _accessToken,
            _refreshToken,
            _webToken,
            _searchToken,
            _webCookieHeader,
            _touchCookieHeader);
    }

    private void ClearRuntimeAuthorization()
    {
        _accessToken = null;
        _refreshToken = null;
        _webToken = null;
        _searchToken = null;
        _webCookieHeader = null;
        _touchCookieHeader = null;
        _activeLogin = null;
        _currentFullMessage = null;
    }

    private void ShowAuthFailure(MailRuAuthResult result)
    {
        var translated = TranslateAuthError(result.ErrorCode, result.State);
        AuthStatusText.Text = $"Ошибка: {translated}";

        if (!string.IsNullOrWhiteSpace(result.DiagnosticReason))
        {
            ResponseTextBox.Text = result.DiagnosticReason;
            DiagnosticLog.Write("auth_failure", result.DiagnosticReason);
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
            FolderListBox.SelectedItem is not MailRuFolderSummary folder ||
            folder.Id == _currentFolderId)
        {
            return;
        }

        await LoadFolderAsync(folder.Id);
    }

    private async Task LoadFolderAsync(int folderId)
    {
        if (!HasMailboxTransport())
        {
            AuthStatusText.Text = "Сначала выполните вход";
            return;
        }

        if (_loadingFolder)
            return;

        _loadingFolder = true;
        RefreshFolderButton.IsEnabled = false;
        FolderStatusText.Text = "Загрузка...";
        MessagesGrid.ItemsSource = null;
        ClearSelectedMessage();

        try
        {
            var raw = await LoadFolderRawAsync(folderId);
            ResponseTextBox.Text = raw;

            var snapshot = _lastFolderUsedTouchSearch
                ? MailRuTouchSearchParser.Parse(raw)
                : MailRuThreadStatusParser.Parse(raw, folderId);

            _currentFolderId = snapshot.SelectedFolderId ?? folderId;
            _currentMessages = snapshot.Messages.ToList();
            ApplyFilters();
            UpdateTrashButtonMode();

            if (snapshot.Folders.Count > 0)
            {
                _updatingFolderSelection = true;
                try
                {
                    FolderListBox.ItemsSource = snapshot.Folders;
                    MoveFolderComboBox.ItemsSource = snapshot.Folders;

                    FolderListBox.SelectedItem =
                        snapshot.Folders.FirstOrDefault(folder => folder.Id == _currentFolderId);

                    MoveFolderComboBox.SelectedItem =
                        snapshot.Folders.FirstOrDefault(folder => folder.Id == 500002) ??
                        snapshot.Folders.FirstOrDefault(folder => folder.Id != _currentFolderId);
                }
                finally
                {
                    _updatingFolderSelection = false;
                }
            }

            var total = snapshot.MessagesTotal?.ToString() ?? "?";
            var unread = snapshot.MessagesUnread?.ToString() ?? "?";
            FolderStatusText.Text =
                $"Всего: {total} · непрочитанных: {unread} · показано: {snapshot.Messages.Count}";

            if (_currentMessages.Count > 0)
                MessagesGrid.SelectedIndex = 0;
            else
                SelectedSubjectText.Text = "В папке нет распознанных писем";

            if (!string.IsNullOrWhiteSpace(_activeLogin))
                AuthStatusText.Text = "Авторизация активна";
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка загрузки";
            ResponseTextBox.Text = ex.Message;
            DiagnosticLog.Write("folder_load", ex.GetType().Name + ": " + ex.Message);

            if (!string.IsNullOrWhiteSpace(_activeLogin))
                AuthStatusText.Text = "Сохранённая авторизация требует проверки";
        }
        finally
        {
            RefreshFolderButton.IsEnabled = true;
            _loadingFolder = false;
        }
    }

    private async void MessagesGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (MessagesGrid.SelectedItem is not MailRuMessageSummary message)
        {
            ClearSelectedMessage();
            return;
        }

        DisplaySummary(message);
        await LoadFullMessageAsync(message);
    }

    private void DisplaySummary(MailRuMessageSummary message)
    {
        SelectedSubjectText.Text = message.Subject;
        SelectedSenderText.Text = string.IsNullOrWhiteSpace(message.SenderEmail)
            ? message.SenderDisplay
            : $"{message.SenderDisplay} <{message.SenderEmail}>";
        SelectedDateText.Text = message.DateDisplay;

        var markers = new List<string>();
        if (message.Unread) markers.Add("непрочитано");
        if (message.Flagged) markers.Add("помечено");
        if (message.HasAttachment) markers.Add("есть вложения");

        SelectedMetaText.Text =
            $"ID: {message.Id}" +
            (message.FolderId is null ? string.Empty : $" · папка: {message.FolderId}") +
            (string.IsNullOrWhiteSpace(message.SizeDisplay) ? string.Empty : $" · {message.SizeDisplay}") +
            (markers.Count == 0 ? string.Empty : $" · {string.Join(", ", markers)}");

        IncomingAttachmentsListBox.ItemsSource = null;
        _currentFullMessage = null;
        ShowReaderText(string.IsNullOrWhiteSpace(message.Snippet)
            ? "Загрузка полного письма..."
            : message.Snippet);
    }

    private async Task LoadFullMessageAsync(MailRuMessageSummary message)
    {
        var generation = ++_messageLoadGeneration;

        if (string.IsNullOrWhiteSpace(_activeLogin))
        {
            ShowReaderText(message.Snippet);
            return;
        }

        Exception? webFailure = null;
        string? raw = null;

        if (!string.IsNullOrWhiteSpace(_webToken))
        {
            try
            {
                raw = await _mailRu.GetFullMessageWebAsync(
                    _webToken,
                    _activeLogin,
                    message.Id,
                    _webCookieHeader,
                    _currentFolderId);
            }
            catch (Exception ex)
            {
                webFailure = ex;
                DiagnosticLog.Write("full_message_web", ex.GetType().Name + ": " + ex.Message);
            }
        }

        if (raw is null && !string.IsNullOrWhiteSpace(_searchToken))
        {
            try
            {
                raw = await _mailRu.GetFullMessageTouchAsync(
                    _searchToken,
                    _activeLogin,
                    message.Id,
                    _touchCookieHeader);
            }
            catch (Exception ex)
            {
                DiagnosticLog.Write("full_message_fallback", ex.GetType().Name + ": " + ex.Message);
                if (webFailure is null)
                    webFailure = ex;
            }
        }

        if (generation != _messageLoadGeneration)
            return;

        if (raw is null)
        {
            ShowReaderText(
                (string.IsNullOrWhiteSpace(message.Snippet) ? string.Empty : message.Snippet + "\n\n") +
                "Полное содержимое сейчас недоступно для этой сохранённой авторизации. " +
                "Повторно добавьте аккаунт, если сессия устарела.");
            return;
        }

        try
        {
            var full = MailRuFullMessageParser.Parse(raw, message.Id);
            _currentFullMessage = full;
            ResponseTextBox.Text = raw;

            SelectedSubjectText.Text = full.Subject;
            if (!string.IsNullOrWhiteSpace(full.SenderDisplay))
                SelectedSenderText.Text = full.SenderDisplay;
            if (!string.IsNullOrWhiteSpace(full.DateDisplay))
                SelectedDateText.Text = full.DateDisplay;

            var recipients = full.To.Count == 0
                ? string.Empty
                : "Кому: " + string.Join(", ", full.To);
            var cc = full.Cc.Count == 0
                ? string.Empty
                : "CC: " + string.Join(", ", full.Cc);
            SelectedMetaText.Text = string.Join(
                " · ",
                new[] { $"ID: {full.Id}", recipients, cc }
                    .Where(x => !string.IsNullOrWhiteSpace(x)));

            IncomingAttachmentsListBox.ItemsSource = full.Attachments;

            if (!string.IsNullOrWhiteSpace(full.Html))
                ShowReaderHtml(full.Html);
            else if (!string.IsNullOrWhiteSpace(full.Text))
                ShowReaderText(full.Text);
            else
                ShowReaderText(message.Snippet);
        }
        catch (Exception ex)
        {
            ShowReaderText(
                (string.IsNullOrWhiteSpace(message.Snippet) ? string.Empty : message.Snippet + "\n\n") +
                "Не удалось разобрать полное письмо: " + ex.Message);
            ResponseTextBox.Text = raw;
            DiagnosticLog.Write("full_message_parse", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async void DownloadAttachmentButton_Click(object sender, RoutedEventArgs e)
    {
        if (IncomingAttachmentsListBox.SelectedItem is not MailRuIncomingAttachment attachment)
        {
            MessageBox.Show(this, "Выберите вложение.", "MailRu Desktop", MessageBoxButton.OK, MessageBoxImage.Information);
            return;
        }

        try
        {
            var dialog = new SaveFileDialog
            {
                FileName = SafeFileName(attachment.DisplayName),
                Title = $"Сохранить вложение · {attachment.ContentType}",
                Filter = "Все файлы|*.*"
            };

            if (dialog.ShowDialog(this) != true)
                return;

            DownloadAttachmentButton.IsEnabled = false;
            FolderStatusText.Text = $"Скачивание {attachment.DisplayName}...";

            var bytes = await _mailRu.DownloadIncomingAttachmentAsync(
                attachment,
                _webCookieHeader ?? _touchCookieHeader);

            await File.WriteAllBytesAsync(dialog.FileName, bytes);
            FolderStatusText.Text =
                $"Вложение сохранено: {Path.GetFileName(dialog.FileName)} · MIME {attachment.ContentType}";
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка скачивания вложения";
            ResponseTextBox.Text = ex.Message;
            DiagnosticLog.Write("attachment_download", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            DownloadAttachmentButton.IsEnabled = true;
        }
    }

    private void ApplyFilterButton_Click(object sender, RoutedEventArgs e)
    {
        ApplyFilters();
    }

    private void ResetFilterButton_Click(object sender, RoutedEventArgs e)
    {
        FilterSenderTextBox.Clear();
        FilterSubjectTextBox.Clear();
        FilterQueryTextBox.Clear();
        FilterAttachmentsCheckBox.IsChecked = false;
        FilterFromDatePicker.SelectedDate = null;
        FilterToDatePicker.SelectedDate = null;
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
        MessagesGrid.ItemsSource = list;
        FilterStatusText.Text = list.Count == _currentMessages.Count
            ? string.Empty
            : $"Показано по фильтру: {list.Count} из {_currentMessages.Count}";
    }

    private async void LoadContactsButton_Click(object sender, RoutedEventArgs e)
    {
        ContactsStatusText.Text = "Загрузка...";

        try
        {
            IReadOnlyList<string> contacts;

            if (!string.IsNullOrWhiteSpace(_searchToken) &&
                !string.IsNullOrWhiteSpace(_activeLogin))
            {
                var raw = await _mailRu.GetContactsTouchAsync(
                    _searchToken,
                    _activeLogin,
                    _touchCookieHeader);
                contacts = MailRuContactsParser.ParseEmails(raw);
            }
            else
            {
                contacts = _currentMessages
                    .Select(message => message.SenderEmail)
                    .Where(email => !string.IsNullOrWhiteSpace(email))
                    .Distinct(StringComparer.OrdinalIgnoreCase)
                    .OrderBy(email => email, StringComparer.CurrentCultureIgnoreCase)
                    .ToArray();
            }

            ContactsListBox.ItemsSource = contacts;
            ContactsStatusText.Text = $"Контактов: {contacts.Count}";
        }
        catch (Exception ex)
        {
            ContactsStatusText.Text = "Ошибка: " + ex.Message;
            DiagnosticLog.Write("contacts", ex.GetType().Name + ": " + ex.Message);
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
        if (ContactsListBox.SelectedItem is not string email)
        {
            ContactsStatusText.Text = "Выберите контакт.";
            return;
        }

        ComposeToTextBox.Text = email;
        MainTabs.SelectedItem = ComposeTab;
        ComposeSubjectTextBox.Focus();
    }

    private async void MoveMessageButton_Click(object sender, RoutedEventArgs e)
    {
        if (MessagesGrid.SelectedItem is not MailRuMessageSummary message ||
            MoveFolderComboBox.SelectedItem is not MailRuFolderSummary folder)
        {
            FolderStatusText.Text = "Выберите письмо и папку назначения.";
            return;
        }

        await MoveMessagesAsync([message.Id], folder.Id, $"Перемещение в «{folder.Name}»");
    }

    private async void TrashMessageButton_Click(object sender, RoutedEventArgs e)
    {
        if (MessagesGrid.SelectedItem is not MailRuMessageSummary message)
        {
            FolderStatusText.Text = "Выберите письмо.";
            return;
        }

        if (_currentFolderId == 500002)
            await DeleteSelectedPermanentlyAsync(message);
        else
            await MoveMessagesAsync([message.Id], 500002, "Перемещение в корзину");
    }

    private async Task MoveMessagesAsync(
        IReadOnlyCollection<string> ids,
        int destinationFolderId,
        string operationName)
    {
        if (string.IsNullOrWhiteSpace(_activeLogin))
        {
            AuthStatusText.Text = "Выберите или добавьте аккаунт.";
            return;
        }

        try
        {
            FolderStatusText.Text = operationName + "...";
            MailRuCommandResult result;

            if (!string.IsNullOrWhiteSpace(_webToken))
            {
                result = await _mailRu.MoveWebMessagesToFolderAsync(
                    _webToken,
                    _activeLogin,
                    ids,
                    destinationFolderId,
                    _webCookieHeader);
            }
            else if (!string.IsNullOrWhiteSpace(_searchToken))
            {
                result = await _mailRu.MoveTouchMessagesToFolderAsync(
                    _searchToken,
                    _activeLogin,
                    ids,
                    destinationFolderId,
                    _touchCookieHeader);
            }
            else
            {
                FolderStatusText.Text =
                    "Для операции требуется обновить авторизацию аккаунта.";
                return;
            }

            ResponseTextBox.Text = result.RawResponse;

            if (!result.Success)
            {
                FolderStatusText.Text = operationName + " отклонено Mail.ru";
                return;
            }

            FolderStatusText.Text = operationName + " выполнено";
            await LoadFolderAsync(_currentFolderId);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = operationName + ": ошибка";
            ResponseTextBox.Text = ex.Message;
            DiagnosticLog.Write("move", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async Task DeleteSelectedPermanentlyAsync(MailRuMessageSummary message)
    {
        if (string.IsNullOrWhiteSpace(_activeLogin))
            return;

        if (MessageBox.Show(
                this,
                "Удалить выбранное письмо окончательно? Это действие нельзя отменить.",
                "MailRu Desktop",
                MessageBoxButton.YesNo,
                MessageBoxImage.Warning) != MessageBoxResult.Yes)
        {
            return;
        }

        try
        {
            FolderStatusText.Text = "Окончательное удаление...";
            MailRuCommandResult result;

            if (!string.IsNullOrWhiteSpace(_webToken))
            {
                result = await _mailRu.DeleteWebMessagesAsync(
                    _webToken,
                    _activeLogin,
                    [message.Id],
                    _webCookieHeader);
            }
            else if (!string.IsNullOrWhiteSpace(_searchToken))
            {
                result = await _mailRu.RemoveTouchMessagesAsync(
                    _searchToken,
                    _activeLogin,
                    [message.Id],
                    _touchCookieHeader);
            }
            else
            {
                FolderStatusText.Text =
                    "Для операции требуется обновить авторизацию аккаунта.";
                return;
            }

            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил удаление.";
                return;
            }

            FolderStatusText.Text = "Письмо удалено.";
            await LoadFolderAsync(_currentFolderId);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка удаления.";
            ResponseTextBox.Text = ex.Message;
            DiagnosticLog.Write("delete", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void UpdateTrashButtonMode()
    {
        TrashMessageButton.Content =
            _currentFolderId == 500002 ? "Удалить навсегда" : "В корзину";
    }

    private void ClearSelectedMessage()
    {
        _messageLoadGeneration++;
        _currentFullMessage = null;
        SelectedSubjectText.Text = "Выберите письмо";
        SelectedSenderText.Text = string.Empty;
        SelectedDateText.Text = string.Empty;
        SelectedMetaText.Text = string.Empty;
        IncomingAttachmentsListBox.ItemsSource = null;
        ShowReaderText("Выберите письмо.");
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

        AttachmentSummaryText.Text = _attachmentPaths.Count == 0
            ? "Вложений нет"
            : $"Вложений: {_attachmentPaths.Count} · {string.Join(", ", _attachmentPaths.Select(Path.GetFileName))}";
    }

    private async void SendButton_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            ComposeStatusText.Text =
                "Отправка пока требует mobile access_token. Выполните обычный вход Mail.ru.";
            return;
        }

        var recipient = ComposeToTextBox.Text.Trim();
        if (recipient.Length == 0)
        {
            ComposeStatusText.Text = "Укажите получателя.";
            return;
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
                    _accessToken,
                    stream,
                    Path.GetFileName(path),
                    messageId);

                attachmentIds.Add(attachId);
            }

            ComposeStatusText.Text = "Отправка...";

            var result = await _mailRu.SendMessageAsync(
                _accessToken,
                new MailRuOutgoingMessage(
                    To: recipient,
                    Subject: ComposeSubjectTextBox.Text,
                    Text: ComposeBodyTextBox.Text,
                    AttachmentIds: attachmentIds,
                    MessageId: messageId));

            if (!result.Success)
            {
                ComposeStatusText.Text = "Mail.ru отклонил отправку.";
                return;
            }

            ComposeToTextBox.Clear();
            ComposeSubjectTextBox.Clear();
            ComposeBodyTextBox.Clear();
            _attachmentPaths.Clear();
            AttachmentSummaryText.Text = "Вложений нет";
            ComposeStatusText.Text = "Отправлено.";
        }
        catch (Exception ex)
        {
            ComposeStatusText.Text = ex.Message;
            DiagnosticLog.Write("send", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            SendButton.IsEnabled = true;
            AttachButton.IsEnabled = true;
        }
    }

    private async Task<string> LoadFolderRawAsync(int folderId)
    {
        _lastFolderUsedTouchSearch = false;
        Exception? mobileFailure = null;
        Exception? webFailure = null;

        if (!string.IsNullOrWhiteSpace(_accessToken))
        {
            try
            {
                return await _mailRu.GetFolderThreadsAsync(_accessToken, folderId);
            }
            catch (Exception ex)
            {
                mobileFailure = ex;
            }
        }

        if (!string.IsNullOrWhiteSpace(_webToken) &&
            !string.IsNullOrWhiteSpace(_activeLogin))
        {
            try
            {
                return await _mailRu.GetFolderThreadsWebAsync(
                    _webToken,
                    _activeLogin,
                    _webCookieHeader,
                    folderId);
            }
            catch (Exception ex)
            {
                webFailure = ex;
            }
        }

        if (!string.IsNullOrWhiteSpace(_searchToken) &&
            !string.IsNullOrWhiteSpace(_activeLogin))
        {
            _lastFolderUsedTouchSearch = true;
            return await _mailRu.SearchTouchAsync(
                _searchToken,
                _activeLogin,
                _touchCookieHeader,
                query: "*",
                count: 200);
        }

        throw webFailure ?? mobileFailure ?? new InvalidOperationException(
            "Для этой сохранённой сессии нет доступного транспорта списка писем. Выполните вход ещё раз.");
    }

    private bool HasMailboxTransport() =>
        !string.IsNullOrWhiteSpace(_accessToken) ||
        !string.IsNullOrWhiteSpace(_webToken) ||
        !string.IsNullOrWhiteSpace(_searchToken);

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

        var background = ThemeManager.IsDarkEffective ? "#202124" : "#FFFFFF";
        var foreground = ThemeManager.IsDarkEffective ? "#E8EAED" : "#202124";

        var document =
            "<!doctype html><html><head><meta charset=\"utf-8\">" +
            "<meta http-equiv=\"Content-Security-Policy\" content=\"default-src 'none'; img-src data:; style-src 'unsafe-inline';\">" +
            "<style>" +
            $"html, body {{ background-color: {background}; color: {foreground}; }}" +
            "body { font-family: Segoe UI, Arial, sans-serif; font-size: 14px; margin: 14px; overflow-wrap: anywhere; }" +
            "img { max-width: 100%; height: auto; }" +
            "pre { white-space: pre-wrap; }" +
            "blockquote { border-left: 3px solid #777; margin-left: 8px; padding-left: 10px; }" +
            "a { color: #4EA1FF; text-decoration: none; }" +
            "</style></head><body>" +
            body +
            "</body></html>";

        MessageWebView.NavigateToString(document);
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

        if (_currentFullMessage is not null)
        {
            if (!string.IsNullOrWhiteSpace(_currentFullMessage.Html))
                ShowReaderHtml(_currentFullMessage.Html);
            else
                ShowReaderText(_currentFullMessage.Text);
        }
        else
        {
            ShowReaderText(MessagesGrid.SelectedItem is MailRuMessageSummary message
                ? message.Snippet
                : "Выберите письмо.");
        }
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

    private static string SafeFileName(string value)
    {
        var invalid = Path.GetInvalidFileNameChars();
        var safe = new string(value.Select(ch => invalid.Contains(ch) ? '_' : ch).ToArray());
        return string.IsNullOrWhiteSpace(safe) ? "attachment" : safe;
    }

    private static string ChallengeStatus(MailRuChallengeKind kind) =>
        kind switch
        {
            MailRuChallengeKind.ReCaptcha => "Требуется reCAPTCHA — пройдите проверку в открывшемся окне",
            MailRuChallengeKind.Captcha => "Требуется CAPTCHA — пройдите проверку в открывшемся окне",
            MailRuChallengeKind.TwoFactor => "Требуется двухфакторная проверка — завершите её в открывшемся окне",
            _ => "Требуется дополнительная проверка Mail.ru"
        };

    private static string TranslateAuthError(string? code, MailRuAuthState state) =>
        state switch
        {
            MailRuAuthState.Blocked => "Blocked — Mail.ru заблокировал вход или требует разблокировку аккаунта",
            MailRuAuthState.RecoveryRequired => "Mail.ru требует восстановление/подтверждение аккаунта",
            MailRuAuthState.ReCaptcha => "требуется reCAPTCHA",
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
