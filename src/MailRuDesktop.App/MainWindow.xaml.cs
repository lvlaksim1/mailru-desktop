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
        AuthStatusText.Text = "Авторизация активна · AJ API";
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
                    "AJ auth succeeded without access_token."));
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

            AuthStatusText.Text = "Авторизация активна · AJ API";
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
                AuthStatusText.Text = "Авторизация активна · AJ API";
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
            _activeLogin,
            _accessToken,
            null,
            null,
            null,
            null,
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

    private Task LoadFullMessageAsync(MailRuMessageSummary message)
    {
        _currentFullMessage = null;
        IncomingAttachmentsListBox.ItemsSource = null;
        ShowReaderText(
            string.IsNullOrWhiteSpace(message.Snippet)
                ? "Полное содержимое письма пока недоступно: для него ещё не подтверждён endpoint aj-https.mail.ru."
                : message.Snippet +
                  "\n\nПолное содержимое пока не загружается: используется только aj-https.mail.ru.");
        return Task.CompletedTask;
    }

    private void DownloadAttachmentButton_Click(object sender, RoutedEventArgs e)
    {
        MessageBox.Show(
            this,
            "Скачивание входящих вложений временно отключено: " +
            "для него ещё не подтверждён endpoint на aj-https.mail.ru.",
            "MailRu Desktop",
            MessageBoxButton.OK,
            MessageBoxImage.Information);
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

    private void LoadContactsButton_Click(object sender, RoutedEventArgs e)
    {
        var contacts = _currentMessages
            .Select(message => message.SenderEmail)
            .Where(email => !string.IsNullOrWhiteSpace(email))
            .Distinct(StringComparer.OrdinalIgnoreCase)
            .OrderBy(email => email, StringComparer.CurrentCultureIgnoreCase)
            .ToArray();

        ContactsListBox.ItemsSource = contacts;
        ContactsStatusText.Text = $"Контактов из загруженных писем: {contacts.Length}";
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

    private Task MoveMessagesAsync(
        IReadOnlyCollection<string> ids,
        int destinationFolderId,
        string operationName)
    {
        FolderStatusText.Text =
            "Операция временно отключена: для перемещения ещё не подтверждён endpoint aj-https.mail.ru.";
        return Task.CompletedTask;
    }

    private Task DeleteSelectedPermanentlyAsync(MailRuMessageSummary message)
    {
        FolderStatusText.Text =
            "Окончательное удаление временно отключено: для него ещё не подтверждён endpoint aj-https.mail.ru.";
        return Task.CompletedTask;
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
                "Нет AJ access_token. Выполните авторизацию заново.");
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
