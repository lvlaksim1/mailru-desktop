using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace MailRuDesktop.App;

/// <summary>
/// Explicit manual experiment: exactly nineteen user-selected accounts,
/// a single shared Google MCS receiver, and one PushMe batch.
/// Opening the window NEVER starts a network operation.
/// </summary>
internal sealed class Group19PushProbeWindow : Window
{
    internal const int RequiredCount = 19;
    private readonly Func<IReadOnlyList<string>, bool> _onStart;
    private readonly Action _onStop;
    private readonly List<CheckBox> _accounts = new();
    private readonly TextBlock _selectionStatus = new();
    private readonly TextBox _trace = new()
    {
        Height = 180, IsReadOnly = true, TextWrapping = TextWrapping.Wrap,
        VerticalScrollBarVisibility = ScrollBarVisibility.Auto,
        Padding = new Thickness(9)
    };
    private readonly Button _start = new()
    {
        Content = "Зарегистрировать 19 аккаунтов", Padding = new Thickness(12, 7)
    };
    private readonly Button _stop = new()
    {
        Content = "Остановить приём", IsEnabled = false,
        Padding = new Thickness(12, 7), Margin = new Thickness(10, 0, 0, 0)
    };
    private readonly CheckBox _confirmed = new()
    {
        Content = "Подтверждаю регистрацию только выбранных 19 аккаунтов",
        Margin = new Thickness(0, 10, 0, 10)
    };
    private bool _started;
    private string? _lastState;

    internal Group19PushProbeWindow(
        IEnumerable<string> availableLogins,
        Func<IReadOnlyList<string>, bool> onStart,
        Action onStop)
    {
        _onStart = onStart;
        _onStop = onStop;
        Title = "MailRu Desktop — Проверка 19 аккаунтов";
        Width = 740;
        Height = 675;
        MinWidth = 610;
        MinHeight = 480;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        SetResourceReference(BackgroundProperty, "AppWindowBrush");
        SetResourceReference(ForegroundProperty, "AppTextBrush");
        FontFamily = new FontFamily("Segoe UI");

        var body = new StackPanel { Margin = new Thickness(18) };
        body.Children.Add(new TextBlock
        {
            Text = "Регистрация 19 аккаунтов с одним Google-получателем",
            FontSize = 17, FontWeight = FontWeights.SemiBold,
            TextWrapping = TextWrapping.Wrap
        });
        var description = new TextBlock
        {
            Text = "Выберите ровно 19 почтовых ящиков. Один общий Google-токен " +
                   "и один запрос PushMe для этих ящиков. " +
                   "Регистрация запускается только после нажатия кнопки. " +
                   "Остальные аккаунты в запрос не попадут. " +
                   "Трёхминутная проверка одного аккаунта доступна отдельно.",
            TextWrapping = TextWrapping.Wrap, Margin = new Thickness(0, 9, 0, 12)
        };
        description.SetResourceReference(TextBlock.ForegroundProperty, "AppMutedTextBrush");
        body.Children.Add(description);
        body.Children.Add(_selectionStatus);

        var accountsPanel = new StackPanel();
        var available = availableLogins.Distinct(StringComparer.OrdinalIgnoreCase).ToArray();
        for (var i = 0; i < available.Length; i++)
        {
            var checkbox = new CheckBox
            {
                Content = available[i], Tag = available[i],
                IsChecked = i < RequiredCount,
                Margin = new Thickness(0, 3, 0, 3)
            };
            checkbox.Checked += (_, _) => UpdateSelection();
            checkbox.Unchecked += (_, _) => UpdateSelection();
            _accounts.Add(checkbox);
            accountsPanel.Children.Add(checkbox);
        }
        var scrollAccounts = new ScrollViewer
        {
            Content = accountsPanel, Height = 180,
            VerticalScrollBarVisibility = ScrollBarVisibility.Auto
        };
        scrollAccounts.SetResourceReference(BackgroundProperty, "AppPanelBrush");
        body.Children.Add(scrollAccounts);
        body.Children.Add(_confirmed);

        var controls = new StackPanel { Orientation = Orientation.Horizontal };
        controls.Children.Add(_start);
        controls.Children.Add(_stop);
        body.Children.Add(controls);
        body.Children.Add(new TextBlock
        {
            Text = "Состояние регистрации и получения уведомлений",
            Margin = new Thickness(0, 15, 0, 7),
            FontWeight = FontWeights.SemiBold
        });
        _trace.SetResourceReference(Control.BackgroundProperty, "AppPanelBrush");
        _trace.SetResourceReference(Control.ForegroundProperty, "AppTextBrush");
        body.Children.Add(_trace);
        Content = new ScrollViewer
        {
            Content = body, VerticalScrollBarVisibility = ScrollBarVisibility.Auto
        };

        _confirmed.Checked += (_, _) => UpdateSelection();
        _confirmed.Unchecked += (_, _) => UpdateSelection();
        _start.Click += (_, _) =>
        {
            var selected = _accounts.Where(x => x.IsChecked == true)
                .Select(x => (string)x.Tag).ToArray();
            if (selected.Length != RequiredCount || _confirmed.IsChecked != true)
                return;
            if (!_onStart(selected))
            {
                ReportStatus("Запуск не выполнен: проверьте доступность выбранных аккаунтов.");
                return;
            }
            _started = true;
            foreach (var cb in _accounts) cb.IsEnabled = false;
            _confirmed.IsEnabled = false;
            _stop.IsEnabled = true;
            UpdateSelection();
            ReportStatus("Запущена регистрация ровно 19 выбранных аккаунтов.");
        };
        _stop.Click += (_, _) => Stop();
        Closed += (_, _) => Stop();
        UpdateSelection();
        ReportStatus("Ожидание ручного запуска.");
    }

    private void Stop()
    {
        if (!_started) return;
        _started = false;
        _stop.IsEnabled = false;
        _onStop();
        foreach (var cb in _accounts) cb.IsEnabled = true;
        _confirmed.IsEnabled = true;
        UpdateSelection();
        ReportStatus("Приём остановлен. Адресные подписки снимаются без отзыва общего Google-токена.");
    }

    private void UpdateSelection()
    {
        var count = _accounts.Count(cb => cb.IsChecked == true);
        _selectionStatus.Text = "Выбрано: " + count + " из " + RequiredCount +
            ". Доступно аккаунтов: " + _accounts.Count + ".";
        _start.IsEnabled = !_started && count == RequiredCount &&
            _confirmed.IsChecked == true;
    }

    internal void ReportStatus(string status)
    {
        if (!Dispatcher.CheckAccess())
        {
            _ = Dispatcher.BeginInvoke(new Action(() => ReportStatus(status)));
            return;
        }
        if (!IsVisible && !IsLoaded) return;
        if (status == _lastState) return; // same status can arrive for 19 accounts
        _lastState = status;
        _trace.AppendText(DateTime.Now.ToString("HH:mm:ss") + "  " +
            status + Environment.NewLine);
        _trace.ScrollToEnd();
    }
}
