using System.IO;
using System.Reflection;
using System.Windows;
using System.Windows.Controls;
using MailRuDesktop.Protocol;
using Microsoft.Win32;

namespace MailRuDesktop.App;

public partial class MainWindow : Window
{
    private readonly MailRuClient _mailRu = new();
    private readonly AuthorizationStore _authStore = new();
    private readonly List<string> _attachmentPaths = [];
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

    public MainWindow()
    {
        InitializeComponent();

        var version = Assembly.GetExecutingAssembly().GetName().Version;
        var displayVersion = version is null
            ? "dev"
            : $"{version.Major}.{version.Minor}.{Math.Max(version.Build, 0)}";
        Title = $"MailRu Desktop v{displayVersion}";

        RefreshSavedLogins();

        if (!string.IsNullOrWhiteSpace(_authStore.LastLogin))
        {
            LoginComboBox.Text = _authStore.LastLogin;
            RestoreSavedAuthorization(_authStore.LastLogin);
        }

        Loaded += MainWindow_Loaded;
        Closed += (_, _) => _mailRu.Dispose();
    }

    private async void MainWindow_Loaded(object sender, RoutedEventArgs e)
    {
        if (!string.IsNullOrWhiteSpace(_accessToken))
            await LoadFolderAsync(0);
    }

    private void RefreshSavedLogins()
    {
        var current = LoginComboBox.Text;
        _updatingAccountSelection = true;
        try
        {
            LoginComboBox.ItemsSource = _authStore.Logins;
            if (!string.IsNullOrWhiteSpace(current))
                LoginComboBox.Text = current;
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
        AuthStatusText.Text = "Сохранённая авторизация восстановлена";
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

    private async void AuthenticateButton_Click(object sender, RoutedEventArgs e)
    {
        var login = LoginComboBox.Text.Trim();
        if (login.Length == 0)
        {
            AuthStatusText.Text = "Введите логин";
            return;
        }

        if (PasswordBox.Password.Length == 0 && RestoreSavedAuthorization(login))
        {
            await LoadFolderAsync(0);
            return;
        }

        AuthenticateButton.IsEnabled = false;
        AuthStatusText.Text = "Авторизация...";
        ResponseTextBox.Clear();

        try
        {
            var result = await _mailRu.AuthenticateAsync(
                login,
                PasswordBox.Password);

            PasswordBox.Clear();

            if (!result.Success)
            {
                _accessToken = null;
                _refreshToken = null;
                _webToken = null;
                _searchToken = null;
                _webCookieHeader = null;
                _touchCookieHeader = null;
                _activeLogin = null;
                AuthStatusText.Text = $"Ошибка: {TranslateAuthError(result.ErrorCode)}";
                return;
            }

            _accessToken = result.AccessToken;
            _refreshToken = result.RefreshToken;
            _webToken = result.WebToken;
            _searchToken = result.SearchToken;
            _webCookieHeader = result.WebCookieHeader;
            _touchCookieHeader = result.TouchCookieHeader;
            _activeLogin = login;

            _authStore.Save(
                login,
                result.AccessToken,
                result.RefreshToken,
                result.WebToken,
                result.SearchToken,
                result.WebCookieHeader,
                result.TouchCookieHeader);
            RefreshSavedLogins();

            AuthStatusText.Text = string.IsNullOrWhiteSpace(_accessToken)
                ? "Авторизация успешна и сохранена · web/touch"
                : "Авторизация успешна и сохранена";
            await LoadFolderAsync(0);
        }
        catch (Exception ex)
        {
            _accessToken = null;
            _refreshToken = null;
            _webToken = null;
            _searchToken = null;
            _webCookieHeader = null;
            _touchCookieHeader = null;
            _activeLogin = null;
            PasswordBox.Clear();
            AuthStatusText.Text = "Ошибка";
            ResponseTextBox.Text = ex.Message;
        }
        finally
        {
            AuthenticateButton.IsEnabled = true;
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
        if (string.IsNullOrWhiteSpace(_accessToken) &&
            string.IsNullOrWhiteSpace(_webToken))
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
            MessagesGrid.ItemsSource = snapshot.Messages;

            if (snapshot.Folders.Count > 0)
            {
                _updatingFolderSelection = true;
                try
                {
                    FolderListBox.ItemsSource = snapshot.Folders;
                    FolderListBox.SelectedItem =
                        snapshot.Folders.FirstOrDefault(folder => folder.Id == _currentFolderId);
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

            if (snapshot.Messages.Count > 0)
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
            if (!string.IsNullOrWhiteSpace(_activeLogin))
                AuthStatusText.Text = "Сохранённая авторизация требует проверки";
        }
        finally
        {
            RefreshFolderButton.IsEnabled = true;
            _loadingFolder = false;
        }
    }

    private void MessagesGrid_SelectionChanged(object sender, SelectionChangedEventArgs e)
    {
        if (MessagesGrid.SelectedItem is not MailRuMessageSummary message)
        {
            ClearSelectedMessage();
            return;
        }

        SelectedSubjectText.Text = message.Subject;
        SelectedSenderText.Text = string.IsNullOrWhiteSpace(message.SenderEmail)
            ? message.SenderDisplay
            : $"{message.SenderDisplay} <{message.SenderEmail}>";
        SelectedDateText.Text = message.DateDisplay;
        SelectedSnippetText.Text = message.Snippet;

        var markers = new List<string>();
        if (message.Unread) markers.Add("непрочитано");
        if (message.Flagged) markers.Add("помечено");
        if (message.HasAttachment) markers.Add("есть вложения");

        SelectedMetaText.Text =
            $"ID: {message.Id}" +
            (message.FolderId is null ? string.Empty : $" · папка: {message.FolderId}") +
            (string.IsNullOrWhiteSpace(message.SizeDisplay) ? string.Empty : $" · {message.SizeDisplay}") +
            (markers.Count == 0 ? string.Empty : $" · {string.Join(", ", markers)}");
    }

    private void ClearSelectedMessage()
    {
        SelectedSubjectText.Text = "Выберите письмо";
        SelectedSenderText.Text = string.Empty;
        SelectedDateText.Text = string.Empty;
        SelectedSnippetText.Text = string.Empty;
        SelectedMetaText.Text = string.Empty;
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
            ComposeStatusText.Text = "Сначала выполните вход.";
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
        }
        finally
        {
            SendButton.IsEnabled = true;
            AttachButton.IsEnabled = true;
        }
    }

    private async Task<string> LoadFolderRawAsync(int folderId)
    {
        Exception? mobileFailure = null;

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
            return await _mailRu.GetFolderThreadsWebAsync(
                _webToken,
                _activeLogin,
                _webCookieHeader,
                folderId);
        }

        if (mobileFailure is not null)
            throw mobileFailure;

        throw new InvalidOperationException(
            "Для этой сохранённой сессии нет доступного токена списка писем. Выполните вход ещё раз.");
    }

    private static string TranslateAuthError(string? code) =>
        code switch
        {
            "captcha_required" => "требуется CAPTCHA",
            "recaptcha_required" => "требуется reCAPTCHA",
            "two_factor_required" => "требуется двухфакторная проверка",
            "account_recovery_required" => "Mail.ru требует восстановление/дополнительную проверку",
            "account_blocked" => "аккаунт заблокирован",
            "invalid_credentials" => "неверный логин или пароль",
            "web_session_token_missing" => "сессия создана, но API-token не найден",
            "token_missing" => "токен не получен",
            null or "" => "неизвестная ошибка",
            _ => code
        };

}
