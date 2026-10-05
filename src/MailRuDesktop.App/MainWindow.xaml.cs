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
    private readonly List<string> _attachmentPaths = [];
    private string? _accessToken;

    public MainWindow()
    {
        InitializeComponent();

        var version = Assembly.GetExecutingAssembly().GetName().Version;
        var displayVersion = version is null
            ? "dev"
            : $"{version.Major}.{version.Minor}.{Math.Max(version.Build, 0)}";
        Title = $"MailRu Desktop v{displayVersion}";

        Closed += (_, _) => _mailRu.Dispose();
    }

    private async void AuthenticateButton_Click(object sender, RoutedEventArgs e)
    {
        AuthenticateButton.IsEnabled = false;
        AuthStatusText.Text = "Авторизация...";
        ResponseTextBox.Clear();

        try
        {
            var result = await _mailRu.AuthenticateAsync(
                LoginTextBox.Text.Trim(),
                PasswordBox.Password);

            PasswordBox.Clear();

            if (!result.Success || string.IsNullOrWhiteSpace(result.AccessToken))
            {
                _accessToken = null;
                AuthStatusText.Text = $"Ошибка: {result.ErrorCode ?? "unknown"}";
                return;
            }

            _accessToken = result.AccessToken;
            AuthStatusText.Text = "Авторизация успешна";
        }
        catch (Exception ex)
        {
            _accessToken = null;
            PasswordBox.Clear();
            AuthStatusText.Text = "Ошибка";
            ResponseTextBox.Text = ex.Message;
        }
        finally
        {
            AuthenticateButton.IsEnabled = true;
        }
    }

    private async void LoadFolderButton_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            AuthStatusText.Text = "Сначала выполните вход";
            return;
        }

        if (!int.TryParse(FolderIdTextBox.Text, out var folderId))
        {
            ResponseTextBox.Text = "Некорректный ID папки.";
            return;
        }

        LoadFolderButton.IsEnabled = false;
        FolderStatusText.Text = "Загрузка...";
        MessagesGrid.ItemsSource = null;
        ClearSelectedMessage();

        try
        {
            var raw = await _mailRu.GetFolderThreadsAsync(_accessToken, folderId);
            ResponseTextBox.Text = raw;

            var snapshot = MailRuThreadStatusParser.Parse(raw);
            MessagesGrid.ItemsSource = snapshot.Messages;

            var total = snapshot.MessagesTotal?.ToString() ?? "?";
            var unread = snapshot.MessagesUnread?.ToString() ?? "?";
            FolderStatusText.Text =
                $"Всего: {total} · непрочитанных: {unread} · разобрано: {snapshot.Messages.Count}";

            if (snapshot.Messages.Count > 0)
                MessagesGrid.SelectedIndex = 0;
            else
                SelectedSubjectText.Text = "Письма не распознаны — смотрите raw JSON";
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка";
            ResponseTextBox.Text = ex.Message;
        }
        finally
        {
            LoadFolderButton.IsEnabled = true;
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
}
