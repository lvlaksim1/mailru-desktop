using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace MailRuDesktop.App;

/// <summary>
/// Diagnostic view for native event delivery. Explicit opt-in; no startup job,
/// no periodic folder refresh and no second WebView2 instance.
/// </summary>
internal sealed class PushProbeWindow : Window
{
    private readonly AuthorizationStore _authorization;
    private readonly Action<string> _newMailCallback;
    private readonly ComboBox _accountSelect = new ComboBox() { MinWidth = 310, Height = 30 };
    private readonly Button _start = new Button() { Content = "Начать проверку", MinWidth = 155, Padding = new Thickness(12, 6, 12, 6) };
    private readonly Button _stop = new Button() { Content = "Остановить", MinWidth = 110, Padding = new Thickness(12, 6, 12, 6), IsEnabled = false };
    private readonly CheckBox _consent = new CheckBox()
    {
        Content = "Разрешаю временную подписку выбранного аккаунта на уведомления",
        Margin = new Thickness(0, 10, 0, 10)
    };
    private readonly TextBox _states = new TextBox()
    {
        IsReadOnly = true, AcceptsReturn = true,
        TextWrapping = TextWrapping.Wrap,
        VerticalScrollBarVisibility = ScrollBarVisibility.Auto,
        MinHeight = 230, Padding = new Thickness(9)
    };
    private CancellationTokenSource? _cancellation;
    private Task? _running;
    private bool _closed;

    public PushProbeWindow(AuthorizationStore authorization, string? preferredLogin,
        Action<string> onNewMail)
    {
        _authorization = authorization;
        _newMailCallback = onNewMail;

        Title = "MailRu Desktop — Проверка мгновенных уведомлений";
        Width = 650;
        Height = 535;
        MinWidth = 570;
        MinHeight = 440;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        SetResourceReference(BackgroundProperty, "AppWindowBrush");
        SetResourceReference(ForegroundProperty, "AppTextBrush");
        FontFamily = new FontFamily("Segoe UI");

        var panel = new StackPanel { Margin = new Thickness(18) };
        var heading = new TextBlock
        {
            Text = "Получение новых писем без периодического опроса",
            FontSize = 17, FontWeight = FontWeights.SemiBold, TextWrapping = TextWrapping.Wrap
        };
        panel.Children.Add(heading);
        var explanation = new TextBlock
        {
            Text = "Испытание встроенного .NET-приёмника по протоколу официального " +
                   "мобильного приложения. Проверка создаёт собственный токен Google, " +
                   "временно подписывает только выбранный ящик и ждёт событие нового письма " +
                   "(до 3 минут). После завершения удаляется только временный токен.",
            TextWrapping = TextWrapping.Wrap,
            Margin = new Thickness(0, 10, 0, 12)
        };
        explanation.SetResourceReference(TextBlock.ForegroundProperty, "AppMutedTextBrush");
        panel.Children.Add(explanation);
        panel.Children.Add(new TextBlock { Text = "Испытательный аккаунт", Margin = new Thickness(0, 0, 0, 6) });
        foreach (var login in authorization.Logins) _accountSelect.Items.Add(login);
        if (!string.IsNullOrWhiteSpace(preferredLogin) && _accountSelect.Items.Contains(preferredLogin))
            _accountSelect.SelectedItem = preferredLogin;
        else if (_accountSelect.Items.Count > 0)
            _accountSelect.SelectedIndex = 0;
        panel.Children.Add(_accountSelect);
        panel.Children.Add(_consent);

        var buttons = new StackPanel { Orientation = Orientation.Horizontal };
        buttons.Children.Add(_start);
        _stop.Margin = new Thickness(8, 0, 0, 0);
        buttons.Children.Add(_stop);
        panel.Children.Add(buttons);
        panel.Children.Add(new TextBlock
        {
            Text = "Технические состояния (без токенов и содержимого писем)",
            FontWeight = FontWeights.SemiBold, Margin = new Thickness(0, 16, 0, 8)
        });
        _states.SetResourceReference(Control.BackgroundProperty, "AppPanelBrush");
        _states.SetResourceReference(Control.ForegroundProperty, "AppTextBrush");
        panel.Children.Add(_states);

        var scroll = new ScrollViewer { VerticalScrollBarVisibility = ScrollBarVisibility.Auto };
        scroll.Content = panel;
        Content = scroll;
        _start.Click += StartClicked;
        _stop.Click += (_, _) =>
        {
            _stop.IsEnabled = false;
            _cancellation?.Cancel();
            ShowState("Запрошена остановка…");
        };
        _consent.Checked += (_, _) => Recalculate();
        _consent.Unchecked += (_, _) => Recalculate();
        _accountSelect.SelectionChanged += (_, _) => Recalculate();
        Closed += (_, _) =>
        {
            _closed = true;
            _cancellation?.Cancel();
        };
        ShowState("Не запущено. Выберите подключённый аккаунт и подтвердите проверку.");
        Recalculate();
    }

    private void Recalculate()
    {
        _start.IsEnabled = _running is null &&
                           _accountSelect.SelectedItem is string &&
                           _consent.IsChecked == true;
    }

    private void ShowState(string line)
    {
        if (_closed) return;
        if (!Dispatcher.CheckAccess())
        {
            _ = Dispatcher.BeginInvoke(new Action(() => ShowState(line)));
            return;
        }
        _states.AppendText($"{DateTime.Now:HH:mm:ss}  {line}{Environment.NewLine}");
        _states.ScrollToEnd();
    }

    private async void StartClicked(object sender, RoutedEventArgs e)
    {
        if (_running is not null || _consent.IsChecked != true ||
            _accountSelect.SelectedItem is not string login)
            return;
        if (!_authorization.TryRestore(login, out var auth) ||
            string.IsNullOrWhiteSpace(auth?.AccessToken))
        {
            ShowState("Нет действующего сохранённого токена этого аккаунта. Проверьте авторизацию.");
            return;
        }

        _cancellation = new CancellationTokenSource();
        _states.Clear();
        _stop.IsEnabled = true;
        _accountSelect.IsEnabled = false;
        _consent.IsEnabled = false;
        using var probe = new MailRuPushProbe();
        ShowState("Запуск. Телефонные подписки не изменяются.");
        _running = probe.RunAsync(
            login, auth.AccessToken,
            ShowState,
            () =>
            {
                if (_closed) return;
                _ = Dispatcher.BeginInvoke(new Action(() =>
                {
                    ShowState("Событие подтверждено, запрошено обновление списка писем.");
                    _newMailCallback(login);
                }));
            },
            _cancellation.Token);
        Recalculate();
        try
        {
            await _running;
        }
        finally
        {
            _running = null;
            _cancellation.Dispose();
            _cancellation = null;
            if (!_closed)
            {
                _accountSelect.IsEnabled = true;
                _consent.IsEnabled = true;
                _stop.IsEnabled = false;
                Recalculate();
            }
        }
    }
}
