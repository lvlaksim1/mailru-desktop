using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private Window? _composeWindow;
    // An outgoing letter must never silently change its sender when the
    // selected mailbox in the main window changes.
    private string? _composeSenderLogin;
    private string? _composeSenderToken;

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
            dialog.SourceInitialized += (_, _) => ThemeManager.RefreshWindowChrome(dialog);
            dialog.Loaded += (_, _) =>
            {
                ThemeManager.RefreshWindowChrome(dialog);
                search.Focus();
            };
            dialog.Activated += (_, _) => ThemeManager.RefreshWindowChrome(dialog);
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
