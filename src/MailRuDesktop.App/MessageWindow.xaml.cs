using System.IO;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using MailRuDesktop.Protocol;
using Microsoft.Web.WebView2.Core;
using Microsoft.Win32;

namespace MailRuDesktop.App;

public partial class MessageWindow : Window
{
    private readonly MailRuClient _mailRu;
    private readonly MailRuMessageSummary _summary;
    private readonly string? _accessToken;
    private readonly IReadOnlyList<MailRuFolderSummary> _folders;
    private readonly MailRuFullMessage? _initialFullMessage;

    private bool _readerReady;
    private MailRuFullMessage? _fullMessage;
    private ComposeMode _composeMode;

    public bool MailboxChanged { get; private set; }

    public MessageWindow(
        MailRuClient mailRu,
        MailRuMessageSummary summary,
        string? accessToken,
        int currentFolderId,
        IReadOnlyList<MailRuFolderSummary> folders,
        MailRuFullMessage? initialFullMessage = null)
    {
        _mailRu = mailRu;
        _summary = summary;
        _accessToken = accessToken;
        _folders = folders;
        _initialFullMessage = initialFullMessage?.Id == summary.Id
            ? initialFullMessage
            : null;

        InitializeComponent();

        Title = $"{summary.Subject} — MailRu Desktop";
        SubjectText.Text = summary.Subject;
        FromText.Text = "От: " + summary.SenderDisplay;
        DateText.Text = "Дата: " + summary.DateDisplay;
        MoveFolderComboBox.ItemsSource = folders;
        MoveFolderComboBox.SelectedItem =
            folders.FirstOrDefault(folder => folder.Id != currentFolderId);

        ThemeManager.ThemeChanged += ThemeManager_ThemeChanged;
        Loaded += MessageWindow_Loaded;
        Closed += MessageWindow_Closed;
    }

    private async void MessageWindow_Loaded(object sender, RoutedEventArgs e)
    {
        await InitializeReaderAsync();

        if (_initialFullMessage is not null)
        {
            ApplyFullMessage(_initialFullMessage);
            return;
        }

        await LoadFullMessageAsync();
    }

    private async Task InitializeReaderAsync()
    {
        try
        {
            var dataRoot = Path.Combine(
                Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
                "MailRuDesktop",
                "WebView2Message");
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
        }
        catch (Exception ex)
        {
            StatusText.Text = "Не удалось открыть просмотр письма: " + ex.Message;
        }
    }

    private async Task LoadFullMessageAsync()
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            StatusText.Text = "Нет access_token. Выполните авторизацию заново.";
            ShowBody(_summary.Snippet, html: false);
            return;
        }

        try
        {
            StatusText.Text = "Загрузка полного письма...";
            var full = await _mailRu.GetFullMessageAsync(
                _accessToken,
                _summary.Id,
                markRead: false);
            ApplyFullMessage(full);
            StatusText.Text = "Письмо загружено.";
        }
        catch (Exception ex)
        {
            StatusText.Text = "Не удалось загрузить полное письмо.";
            ShowBody(_summary.Snippet, html: false);
            DiagnosticLog.Write("message_window_full", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void ApplyFullMessage(MailRuFullMessage full)
    {
        _fullMessage = full;

        Title = $"{full.Subject} — MailRu Desktop";
        SubjectText.Text = full.Subject;
        FromText.Text = "От: " + full.SenderDisplay;
        ToText.Text = full.To.Count == 0
            ? "Кому: —"
            : "Кому: " + string.Join(", ", full.To);
        DateText.Text = "Дата: " + full.DateDisplay;

        AttachmentsItemsControl.ItemsSource = full.Attachments;
        AttachmentsItemsControl.Visibility =
            full.Attachments.Count == 0 ? Visibility.Collapsed : Visibility.Visible;

        if (!string.IsNullOrWhiteSpace(full.Html))
            ShowBody(full.Html, html: true);
        else if (!string.IsNullOrWhiteSpace(full.Text))
            ShowBody(full.Text, html: false);
        else
            ShowBody(_summary.Snippet, html: false);
    }

    private void ShowBody(string body, bool html)
    {
        if (!_readerReady || MessageWebView.CoreWebView2 is null)
            return;

        var background = ThemeManager.ReaderBackgroundHtml;
        var foreground = ThemeManager.ReaderForegroundHtml;
        var muted = ThemeManager.ReaderMutedHtml;
        var link = ThemeManager.ReaderLinkHtml;

        var content = html
            ? body
            : WebUtility.HtmlEncode(body ?? string.Empty)
                .Replace("\r\n", "<br>", StringComparison.Ordinal)
                .Replace("\n", "<br>", StringComparison.Ordinal);

        var darkOverrides = ThemeManager.IsDarkEffective
            ? $"body,body div,body table,body tbody,body thead,body tfoot,body tr,body td,body th,body p,body section,body article,body header,body footer,body main{{background-color:transparent!important;color:{foreground}!important;}}" +
              $"body span{{color:inherit!important;}}body a,body a *{{color:{link}!important;}}" +
              "body [bgcolor]{background-color:transparent!important;}body [style*='background']{background-color:transparent!important;}"
            : string.Empty;

        var document =
            "<!doctype html><html><head><meta charset='utf-8'>" +
            "<meta http-equiv='Content-Security-Policy' content=\"default-src 'none'; img-src data:; style-src 'unsafe-inline';\">" +
            "<style>" +
            $"html,body{{background:{background}!important;color:{foreground}!important;}}" +
            "body{font-family:'Segoe UI',Arial,sans-serif;font-size:14px;margin:16px;overflow-wrap:anywhere;}" +
            "img{max-width:100%;height:auto;}pre{white-space:pre-wrap;}" +
            $"blockquote{{border-left:3px solid {muted};margin-left:8px;padding-left:10px;color:{muted};}}" +
            $"a{{color:{link};}}" + darkOverrides +
            "</style></head><body>" + content + "</body></html>";

        MessageWebView.NavigateToString(document);
    }

    private void ReplyButton_Click(object sender, RoutedEventArgs e)
    {
        _composeMode = ComposeMode.Reply;
        ComposePanel.Visibility = Visibility.Visible;

        var target = _fullMessage?.FromEmail;
        if (string.IsNullOrWhiteSpace(target))
            target = _summary.SenderEmail;

        ComposeToTextBox.Text = target ?? string.Empty;
        ComposeSubjectTextBox.Text = PrefixSubject("Re:", _fullMessage?.Subject ?? _summary.Subject);
        ComposeBodyTextBox.Clear();
        ComposeStatusText.Text = string.Empty;
        ComposeBodyTextBox.Focus();
    }

    private void ForwardButton_Click(object sender, RoutedEventArgs e)
    {
        _composeMode = ComposeMode.Forward;
        ComposePanel.Visibility = Visibility.Visible;
        ComposeToTextBox.Clear();
        ComposeSubjectTextBox.Text = PrefixSubject("Fwd:", _fullMessage?.Subject ?? _summary.Subject);

        var full = _fullMessage;
        ComposeBodyTextBox.Text = full is null
            ? string.Empty
            : "\n\n---------- Пересланное сообщение ----------\n" +
              $"От: {full.SenderDisplay}\n" +
              $"Кому: {string.Join(", ", full.To)}\n" +
              $"Дата: {full.DateDisplay}\n" +
              $"Тема: {full.Subject}\n\n" +
              full.Text;

        ComposeToTextBox.Focus();
    }

    private async void SendComposeButton_Click(object sender, RoutedEventArgs e)
    {
        if (string.IsNullOrWhiteSpace(ComposeToTextBox.Text))
        {
            ComposeStatusText.Text = "Укажите получателя.";
            return;
        }

        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            ComposeStatusText.Text =
                "Для отправки нет AJ access_token. Выполните авторизацию заново.";
            return;
        }

        SendComposeButton.IsEnabled = false;
        ComposeStatusText.Text = "Отправка...";

        try
        {
            var result = await _mailRu.SendMessageAsync(
                _accessToken,
                new MailRuOutgoingMessage(
                    To: ComposeToTextBox.Text.Trim(),
                    Subject: ComposeSubjectTextBox.Text,
                    Text: ComposeBodyTextBox.Text,
                    ReplyToId: _composeMode == ComposeMode.Reply ? _summary.Id : null));

            if (!result.Success)
            {
                ComposeStatusText.Text = "Mail.ru отклонил отправку.";
                DiagnosticLog.Write("message_window_send", result.RawResponse);
                return;
            }

            ComposeStatusText.Text = "Отправлено.";
            ComposeBodyTextBox.Clear();
        }
        catch (Exception ex)
        {
            ComposeStatusText.Text = ex.Message;
            DiagnosticLog.Write("message_window_send", ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            SendComposeButton.IsEnabled = true;
        }
    }

    private void CancelComposeButton_Click(object sender, RoutedEventArgs e)
    {
        ComposePanel.Visibility = Visibility.Collapsed;
        ComposeStatusText.Text = string.Empty;
    }

    private async void ArchiveButton_Click(object sender, RoutedEventArgs e)
    {
        var archive = _folders.FirstOrDefault(folder =>
            folder.Type.Equals("archive", StringComparison.OrdinalIgnoreCase) ||
            folder.Name.Equals("Архив", StringComparison.CurrentCultureIgnoreCase) ||
            folder.Id == 500010);

        await MoveAsync(archive?.Id ?? 500010, "Перемещение в архив");
    }

    private async void MoveButton_Click(object sender, RoutedEventArgs e)
    {
        if (MoveFolderComboBox.SelectedItem is not MailRuFolderSummary folder)
        {
            StatusText.Text = "Выберите папку назначения.";
            return;
        }

        await MoveAsync(folder.Id, $"Перемещение в «{folder.Name}»");
    }

    private async Task MoveAsync(int folderId, string operation)
    {
        if (string.IsNullOrWhiteSpace(_accessToken))
        {
            StatusText.Text = "Нет access_token. Выполните авторизацию заново.";
            return;
        }

        StatusText.Text = operation + "...";

        try
        {
            var result = await _mailRu.MoveMessagesAsync(
                _accessToken,
                new[] { _summary.Id },
                folderId);

            if (!result.Success)
            {
                StatusText.Text = "Mail.ru отклонил перемещение.";
                DiagnosticLog.Write("message_window_move", result.RawResponse);
                return;
            }

            MailboxChanged = true;
            StatusText.Text = operation + " выполнено.";
        }
        catch (Exception ex)
        {
            StatusText.Text = "Ошибка перемещения.";
            DiagnosticLog.Write("message_window_move", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private async void AttachmentButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not MailRuIncomingAttachment attachment ||
            string.IsNullOrWhiteSpace(_accessToken))
        {
            StatusText.Text = "Не удалось определить вложение.";
            return;
        }

        if (string.IsNullOrWhiteSpace(attachment.Id))
        {
            StatusText.Text = "У вложения отсутствует идентификатор.";
            return;
        }

        var dialog = new SaveFileDialog
        {
            FileName = SanitizeFileName(attachment.DisplayName),
            Title = "Сохранить вложение"
        };

        if (dialog.ShowDialog(this) != true)
            return;

        StatusText.Text = $"Скачивание «{attachment.DisplayName}»...";

        try
        {
            var bytes = await _mailRu.DownloadIncomingAttachmentAsync(
                _accessToken,
                _summary.Id,
                attachment.Id);

            await File.WriteAllBytesAsync(dialog.FileName, bytes);
            StatusText.Text = $"Вложение сохранено: {Path.GetFileName(dialog.FileName)}";
        }
        catch (Exception ex)
        {
            StatusText.Text = "Ошибка скачивания вложения.";
            DiagnosticLog.Write("message_window_attachment", ex.GetType().Name + ": " + ex.Message);
        }
    }

    private static string PrefixSubject(string prefix, string subject) =>
        subject.StartsWith(prefix, StringComparison.OrdinalIgnoreCase)
            ? subject
            : prefix + " " + subject;

    private static string SanitizeFileName(string value)
    {
        var invalid = Path.GetInvalidFileNameChars();
        var cleaned = new string(value
            .Select(ch => invalid.Contains(ch) ? '_' : ch)
            .ToArray());

        return string.IsNullOrWhiteSpace(cleaned) ? "attachment" : cleaned;
    }

    private void ThemeManager_ThemeChanged(object? sender, EventArgs e)
    {
        if (_fullMessage is null)
            return;

        if (!Dispatcher.CheckAccess())
        {
            Dispatcher.BeginInvoke(() => ThemeManager_ThemeChanged(sender, e));
            return;
        }

        if (!string.IsNullOrWhiteSpace(_fullMessage.Html))
            ShowBody(_fullMessage.Html, html: true);
        else
            ShowBody(_fullMessage.Text, html: false);
    }

    private void MessageWindow_Closed(object? sender, EventArgs e)
    {
        ThemeManager.ThemeChanged -= ThemeManager_ThemeChanged;
        try
        {
            MessageWebView.Dispose();
        }
        catch
        {
        }
    }

    private enum ComposeMode
    {
        Reply,
        Forward
    }
}
