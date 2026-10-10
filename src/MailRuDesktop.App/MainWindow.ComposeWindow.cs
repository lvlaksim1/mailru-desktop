using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private Window? _composeWindow;
    // An outgoing letter must never silently change its sender when the
    // selected mailbox in the main window changes.
    private string? _composeSenderLogin;
    private string? _composeSenderToken;
    private string? _composeReplyToId;

    // Keep the existing compose controls and rich-editor state in a separate
    // owned window. Do not duplicate the send, draft or attachment logic.
    private void ShowComposeWindow()
    {
        if (_composeWindow is { } open)
        {
            if (open.WindowState == WindowState.Minimized)
                open.WindowState = WindowState.Normal;
            open.Activate();
            ComposeToTextBox.Focus();
            return;
        }

        if (ComposeWorkspace.Parent is not Panel originalParent)
            throw new InvalidOperationException("Compose workspace has no panel parent.");

        _composeReplyToId = null;
        _composeSenderLogin = _activeLogin;
        _composeSenderToken = _accessToken;
        ComposeFromTextBox.Text = _composeSenderLogin ?? "Не выбран аккаунт";
        originalParent.Children.Remove(ComposeWorkspace);
        ComposeWorkspace.Visibility = Visibility.Visible;
        var window = new Window
        {
            Owner = this,
            Title = "Новое письмо — MailRu Desktop",
            Icon = Icon,
            Width = 950,
            Height = 800,
            MinWidth = 740,
            MinHeight = 670,
            WindowStartupLocation = WindowStartupLocation.CenterOwner,
            Content = ComposeWorkspace
        };
        ThemeManager.AttachWindowChrome(window);
        window.SetResourceReference(BackgroundProperty, "AppWindowBrush");
        window.SetResourceReference(ForegroundProperty, "AppTextBrush");
        window.Closed += (_, _) =>
        {
            window.Content = null;
            ComposeWorkspace.Visibility = Visibility.Collapsed;
            originalParent.Children.Add(ComposeWorkspace);
            _composeWindow = null;
            _composeSenderLogin = null;
            _composeSenderToken = null;
            _composeReplyToId = null;
        };

        _composeWindow = window;
        if (ScheduleSendCheckBox.IsChecked != true)
        {
            ScheduleDatePicker.SelectedDate = DateTime.Today.AddDays(1);
            ScheduleTimeTextBox.Text = "09:00";
        }
        RefreshComposeAttachments();
        window.Show();
        ThemeManager.RefreshWindowChrome(window);
        ComposeToTextBox.Focus();
    }

    // Reply uses the same independent composition window as a new letter.
    // Existing drafts are never silently overwritten by another reply.
    private void OpenReplyComposeWindow(
        MailRuMessageSummary source, MailRuFullMessage? full)
    {
        if (_composeWindow is not null)
        {
            ShowComposeWindow();
            ComposeStatusText.Text =
                "Сначала отправьте или закройте открытое письмо. Черновик не изменён.";
            return;
        }

        var sender = !string.IsNullOrWhiteSpace(full?.FromEmail)
            ? full.FromEmail : source.SenderEmail;
        if (string.IsNullOrWhiteSpace(sender))
        {
            AppDialog.Info(this, "Ответить", "У исходного письма не найден адрес отправителя.");
            return;
        }

        ShowComposeWindow();
        _composeReplyToId = source.Id;
        if (_composeWindow is { } replyWindow)
            replyWindow.Title = "Ответ: " + (full?.Subject ?? source.Subject);
        _attachmentPaths.Clear();
        RefreshComposeAttachments();
        ResetComposeTemplateSelectors();
        ScheduleSendCheckBox.IsChecked = false;
        RequestReadReceiptCheckBox.IsChecked = false;
        ComposeToTextBox.Text = sender;
        ComposeSubjectTextBox.Text = ReplySubject(full?.Subject ?? source.Subject);
        ComposeBodyTextBox.Text = BuildQuotedReply(full, source);
        ComposeStatusText.Text = string.Empty;
        _composeRichEditor?.Focus();
    }

    private static string ReplySubject(string subject) =>
        subject.StartsWith("Re:", StringComparison.OrdinalIgnoreCase)
            ? subject : "Re: " + subject;

    private static string BuildQuotedReply(
        MailRuFullMessage? full, MailRuMessageSummary summary)
    {
        var text = full?.Text;
        if (string.IsNullOrWhiteSpace(text))
            text = summary.Snippet;
        if (string.IsNullOrWhiteSpace(text)) return string.Empty;
        var sender = full?.SenderDisplay ?? summary.SenderDisplay;
        return "\n\n----- Исходное письмо -----\n" +
               "От: " + sender + "\n" +
               "Тема: " + (full?.Subject ?? summary.Subject) + "\n\n" +
               string.Join("\n", text.Replace("\r\n", "\n")
                   .Split('\n').Select(line => "> " + line));
    }

    private sealed record ContactChoice(string Caption, string Email);

    private async void ChooseComposeContactButton_Click(object sender, RoutedEventArgs e)
    {
        var token = _composeSenderToken;
        var login = _composeSenderLogin;
        if (string.IsNullOrWhiteSpace(token) || string.IsNullOrWhiteSpace(login))
        {
            AppDialog.Info(_composeWindow ?? this, "Контакты",
                "Сначала авторизуйте почтовый аккаунт.");
            return;
        }

        ComposeStatusText.Text = "Загрузка контактов...";
        try
        {
            var contacts = await _mailRu.GetAddressBookAsync(token, login);
            if (!string.Equals(login, _composeSenderLogin, StringComparison.OrdinalIgnoreCase) ||
                token != _composeSenderToken || _composeWindow is null)
                return; // The chooser belongs to the sender shown in New Mail.

            var choices = contacts.Where(c => !string.IsNullOrWhiteSpace(c.Email))
                .Select(c => new ContactChoice(
                    string.IsNullOrWhiteSpace(c.Name) ? c.Email : c.Name + " — " + c.Email,
                    c.Email))
                .GroupBy(c => c.Email, StringComparer.OrdinalIgnoreCase)
                .Select(group => group.First())
                .OrderBy(c => c.Caption, StringComparer.CurrentCultureIgnoreCase)
                .ToArray();

            if (choices.Length == 0)
            {
                AppDialog.Info(_composeWindow, "Контакты", "Адресная книга пуста.");
                return;
            }

            var dialog = new Window
            {
                Owner = _composeWindow,
                Title = "Выбор получателя",
                Width = 570,
                Height = 520,
                MinWidth = 390,
                MinHeight = 320,
                WindowStartupLocation = WindowStartupLocation.CenterOwner
            };
            ThemeManager.AttachWindowChrome(dialog);
            dialog.SetResourceReference(BackgroundProperty, "AppDialogBrush");
            dialog.SetResourceReference(ForegroundProperty, "AppTextBrush");

            var panel = new DockPanel { Margin = new Thickness(14) };
            var search = new TextBox
            {
                Padding = new Thickness(8),
                Margin = new Thickness(0, 0, 0, 10),
                ToolTip = "Поиск по имени или адресу"
            };
            DockPanel.SetDock(search, Dock.Top);
            panel.Children.Add(search);

            var buttons = new StackPanel
            {
                Orientation = Orientation.Horizontal,
                HorizontalAlignment = HorizontalAlignment.Right,
                Margin = new Thickness(0, 10, 0, 0)
            };
            var pick = new Button { Content = "Выбрать", Padding = new Thickness(15, 7, 15, 7) };
            var cancel = new Button
            {
                Content = "Отмена",
                Padding = new Thickness(15, 7, 15, 7),
                Margin = new Thickness(8, 0, 0, 0)
            };
            buttons.Children.Add(pick);
            buttons.Children.Add(cancel);
            DockPanel.SetDock(buttons, Dock.Bottom);
            panel.Children.Add(buttons);

            var list = new ListBox
            {
                DisplayMemberPath = nameof(ContactChoice.Caption),
                ItemsSource = choices
            };
            panel.Children.Add(list);
            dialog.Content = panel;

            void Confirm()
            {
                if (list.SelectedItem is not ContactChoice choice)
                    return;
                var existing = ComposeToTextBox.Text.Trim().TrimEnd(',', ';');
                ComposeToTextBox.Text = existing.Length == 0
                    ? choice.Email
                    : existing + ", " + choice.Email;
                dialog.DialogResult = true;
            }

            search.TextChanged += (_, _) =>
            {
                var query = search.Text.Trim();
                list.ItemsSource = choices.Where(c =>
                    query.Length == 0 ||
                    c.Caption.Contains(query, StringComparison.CurrentCultureIgnoreCase))
                    .ToArray();
            };
            pick.Click += (_, _) => Confirm();
            cancel.Click += (_, _) => dialog.DialogResult = false;
            list.MouseDoubleClick += (_, _) => Confirm();
            dialog.PreviewKeyDown += (_, args) =>
            {
                if (args.Key == Key.Escape)
                {
                    dialog.DialogResult = false;
                    args.Handled = true;
                }
                else if (args.Key == Key.Enter)
                {
                    Confirm();
                    args.Handled = true;
                }
            };
            dialog.Loaded += (_, _) => search.Focus();
            dialog.ShowDialog();
            ComposeToTextBox.Focus();
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("compose_contacts", ex.GetType().Name + ": " + ex.Message);
            AppDialog.Info(_composeWindow ?? this, "Контакты",
                "Не удалось загрузить адресную книгу.");
        }
        finally
        {
            ComposeStatusText.Text = string.Empty;
        }
    }
}
