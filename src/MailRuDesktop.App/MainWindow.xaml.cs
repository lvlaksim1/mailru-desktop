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
    private List<MailRuMessageSummary> _currentMessages = [];

    private string? _accessToken;
    private string? _refreshToken;
    private string? _activeLogin;

    private int _currentFolderId;
    private bool _loadingFolder;
    private bool _updatingFolderSelection;
    private bool _updatingAccountSelection;
    private bool _readerReady;
    private long _messageLoadGeneration;
    private MailRuFullMessage? _currentFullMessage;
    private bool _serverSearchMode;

    public MainWindow()
    {
        InitializeComponent();

        ThemeManager.Apply(_settingsStore.LoadTheme());
        SelectThemeComboBox(ThemeManager.CurrentMode);
        ThemeManager.ThemeChanged += ThemeManager_ThemeChanged;

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
        Closed += (_, _) =>
        {
            ThemeManager.ThemeChanged -= ThemeManager_ThemeChanged;
            _mailRu.Dispose();
        };
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
            authorization is null ||
            string.IsNullOrWhiteSpace(authorization.AccessToken))
        {
            return false;
        }

        _accessToken = authorization.AccessToken;
        _refreshToken = authorization.RefreshToken;
        _activeLogin = authorization.Login;

        LoginComboBox.Text = authorization.Login;
        AuthStatusText.Text = "Авторизация активна · access_token";
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
        AuthStatusText.Text = "Авторизация через aj-https.mail.ru...";
        ResponseTextBox.Clear();

        ClearRuntimeAuthorization();

        try
        {
            var result = await _mailRu.AuthenticateAsync(login, password);

            if (!string.IsNullOrWhiteSpace(result.DiagnosticReason))
                DiagnosticLog.Write("auth", result.DiagnosticReason);

            if (!result.Success)
            {
                if (result.State is MailRuAuthState.Captcha or MailRuAuthState.ReCaptcha ||
                    string.Equals(result.ErrorCode, "captcha_required", StringComparison.OrdinalIgnoreCase) ||
                    string.Equals(result.ErrorCode, "additional_verification_required", StringComparison.OrdinalIgnoreCase))
                {
                    AuthStatusText.Text = "Авторизация не выполнена · требуется CAPTCHA";
                    MessageBox.Show(
                        this,
                        "Mail.ru требует CAPTCHA или дополнительную проверку. " +
                        "В текущем режиме приложение CAPTCHA не проходит, поэтому авторизация не выполнена.",
                        "MailRu Desktop",
                        MessageBoxButton.OK,
                        MessageBoxImage.Information);
                    return;
                }

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

            var snapshot = MailRuThreadStatusParser.Parse(raw, folderId);

            _currentFolderId = snapshot.SelectedFolderId ?? folderId;
            _currentMessages = snapshot.Messages.ToList();
            _serverSearchMode = false;
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
                AuthStatusText.Text = "Авторизация активна · access_token";
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

    private async void MessagesGrid_MouseDoubleClick(object sender, MouseButtonEventArgs e)
    {
        if (MessagesGrid.SelectedItem is not MailRuMessageSummary message ||
            string.IsNullOrWhiteSpace(_activeLogin))
        {
            return;
        }

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
        if (message.Pinned) markers.Add("закреплено");
        if (message.HasAttachment) markers.Add("есть вложения");

        SelectedMetaText.Text =
            $"ID: {message.Id}" +
            (message.FolderId is null ? string.Empty : $" · папка: {message.FolderId}") +
            (string.IsNullOrWhiteSpace(message.SizeDisplay) ? string.Empty : $" · {message.SizeDisplay}") +
            (markers.Count == 0 ? string.Empty : $" · {string.Join(", ", markers)}");

        ReadStateButton.Content = message.Unread ? "Прочитано" : "Непрочитано";
        FlagMessageButton.Content = message.Flagged ? "Снять флажок" : "Флажок";
        PinMessageButton.Content = message.Pinned ? "Открепить" : "Закрепить";

        IncomingAttachmentsListBox.ItemsSource = null;
        _currentFullMessage = null;
        ShowReaderText(string.IsNullOrWhiteSpace(message.Snippet)
            ? "Загрузка полного письма..."
            : message.Snippet);
    }

    private async Task LoadFullMessageAsync(MailRuMessageSummary message)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
            return;

        var generation = ++_messageLoadGeneration;
        _currentFullMessage = null;
        IncomingAttachmentsListBox.ItemsSource = null;

        try
        {
            var full = await _mailRu.GetFullMessageAsync(
                _accessToken,
                message.Id,
                markRead: false);

            if (generation != _messageLoadGeneration ||
                MessagesGrid.SelectedItem is not MailRuMessageSummary selected ||
                selected.Id != message.Id)
            {
                return;
            }

            _currentFullMessage = full;
            IncomingAttachmentsListBox.ItemsSource = full.Attachments;
            ResponseTextBox.Text = full.RawJson;

            if (!string.IsNullOrWhiteSpace(full.Html))
                ShowReaderHtml(full.Html);
            else if (!string.IsNullOrWhiteSpace(full.Text))
                ShowReaderText(full.Text);
            else
                ShowReaderText(message.Snippet);
        }
        catch (Exception ex)
        {
            if (generation != _messageLoadGeneration)
                return;

            ShowReaderText(string.IsNullOrWhiteSpace(message.Snippet)
                ? "Не удалось загрузить полное письмо."
                : message.Snippet);
            DiagnosticLog.Write("full_message", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async void DownloadAttachmentButton_Click(object sender, RoutedEventArgs e)
    {
        if (_currentFullMessage is null ||
            IncomingAttachmentsListBox.SelectedItem is not MailRuIncomingAttachment attachment ||
            string.IsNullOrWhiteSpace(_accessToken))
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

    private async void ReadStateButton_Click(object sender, RoutedEventArgs e)
    {
        if (MessagesGrid.SelectedItem is not MailRuMessageSummary message ||
            string.IsNullOrWhiteSpace(_accessToken))
        {
            FolderStatusText.Text = "Выберите письмо.";
            return;
        }

        ReadStateButton.IsEnabled = false;
        var makeUnread = !message.Unread;
        FolderStatusText.Text = makeUnread
            ? "Отмечаем непрочитанным..."
            : "Отмечаем прочитанным...";

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

            await LoadFolderAsync(_currentFolderId);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка изменения статуса.";
            DiagnosticLog.Write("message_marks", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            ReadStateButton.IsEnabled = true;
        }
    }

    private async void FlagMessageButton_Click(object sender, RoutedEventArgs e)
    {
        if (MessagesGrid.SelectedItem is not MailRuMessageSummary message ||
            string.IsNullOrWhiteSpace(_accessToken))
        {
            FolderStatusText.Text = "Выберите письмо.";
            return;
        }

        FlagMessageButton.IsEnabled = false;
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

            await LoadFolderAsync(_currentFolderId);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка изменения флажка.";
            DiagnosticLog.Write("message_flag", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            FlagMessageButton.IsEnabled = true;
        }
    }

    private async void PinMessageButton_Click(object sender, RoutedEventArgs e)
    {
        if (MessagesGrid.SelectedItem is not MailRuMessageSummary message ||
            string.IsNullOrWhiteSpace(_accessToken))
        {
            FolderStatusText.Text = "Выберите письмо.";
            return;
        }

        PinMessageButton.IsEnabled = false;
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

            await LoadFolderAsync(_currentFolderId);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка изменения закрепления.";
            DiagnosticLog.Write("message_pin", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            PinMessageButton.IsEnabled = true;
        }
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
            MessagesGrid.ItemsSource = _currentMessages;
            ResponseTextBox.Text = result.RawResponse;
            FilterStatusText.Text =
                $"Серверный поиск: найдено {result.Found}, показано {_currentMessages.Count} · {result.Source}";

            if (_currentMessages.Count > 0)
                MessagesGrid.SelectedIndex = 0;
            else
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

    private void ApplyFilterButton_Click(object sender, RoutedEventArgs e)
    {
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
        MessagesGrid.ItemsSource = list;
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
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            FolderStatusText.Text = "Нет access_token. Выполните авторизацию заново.";
            return;
        }

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

            await LoadFolderAsync(_currentFolderId);
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

        var answer = MessageBox.Show(
            this,
            $"Удалить письмо «{message.Subject}» навсегда? Это действие нельзя отменить.",
            "MailRu Desktop",
            MessageBoxButton.YesNo,
            MessageBoxImage.Warning);

        if (answer != MessageBoxResult.Yes)
            return;

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

            await LoadFolderAsync(_currentFolderId);
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
        ReadStateButton.Content = "Прочитано";
        FlagMessageButton.Content = "Флажок";
        PinMessageButton.Content = "Закрепить";
        ShowReaderText("Выберите письмо.");
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

        AttachmentSummaryText.Text = _attachmentPaths.Count == 0
            ? "Вложений нет"
            : $"Вложений: {_attachmentPaths.Count} · {string.Join(", ", _attachmentPaths.Select(Path.GetFileName))}";
    }

    private async void SaveDraftButton_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(_accessToken) || string.IsNullOrWhiteSpace(_activeLogin))
        {
            ComposeStatusText.Text = "Для черновика нужен access_token.";
            return;
        }

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
                    _accessToken,
                    stream,
                    Path.GetFileName(path),
                    messageId));
            }

            var result = await _mailRu.SaveDraftAsync(
                _accessToken,
                _activeLogin,
                new MailRuOutgoingMessage(
                    To: ComposeToTextBox.Text.Trim(),
                    Subject: ComposeSubjectTextBox.Text,
                    Text: ComposeBodyTextBox.Text,
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
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            ComposeStatusText.Text =
                "Для отправки нужен access_token. Выполните обычный вход Mail.ru.";
            return;
        }

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
                    SendDate: sendDate,
                    AttachmentIds: attachmentIds,
                    MessageId: messageId));

            ResponseTextBox.Text = result.RawResponse;

            if (!result.Success)
            {
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
            _attachmentPaths.Clear();
            AttachmentSummaryText.Text = "Вложений нет";

            if (scheduledFor is null)
            {
                ComposeStatusText.Text = "Отправлено.";
            }
            else
            {
                ComposeStatusText.Text =
                    $"Запланировано на {scheduledFor.Value.LocalDateTime:dd.MM.yyyy HH:mm}.";
                ScheduleSendCheckBox.IsChecked = false;
                ScheduleDatePicker.SelectedDate = null;
            }
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
            "<meta http-equiv=\"Content-Security-Policy\" content=\"default-src 'none'; img-src data:; style-src 'unsafe-inline';\">" +
            "<style>" +
            $"html, body {{ background-color: {background} !important; color: {foreground} !important; }}" +
            "body { font-family: Segoe UI, Arial, sans-serif; font-size: 14px; margin: 14px; overflow-wrap: anywhere; }" +
            "img { max-width: 100%; height: auto; }" +
            "pre { white-space: pre-wrap; }" +
            $"blockquote {{ border-left: 3px solid {muted}; margin-left: 8px; padding-left: 10px; color: {muted}; }}" +
            $"a {{ color: {link}; text-decoration: none; }}" +
            darkMailOverrides +
            "</style></head><body>" +
            body +
            "</body></html>";

        MessageWebView.NavigateToString(document);
    }

    private void ThemeManager_ThemeChanged(object? sender, EventArgs e)
    {
        if (!Dispatcher.CheckAccess())
        {
            Dispatcher.BeginInvoke(() => ThemeManager_ThemeChanged(sender, e));
            return;
        }

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
