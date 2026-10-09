using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace MailRuDesktop.App;

/// <summary>
/// User-managed stages: durable Google recipient, independent PushMe batches,
/// and the independent MCS receiving connection. Closing this window changes
/// none of those three states.
/// </summary>
internal sealed class PushSubscriptionManagerWindow : Window
{
    private readonly string[] _authorized;
    private readonly Func<PushGroupRegistryStore.Registry> _readGroups;
    private readonly Func<string[]> _googleIds;
    private readonly Func<Task<string>> _registerGoogle;
    private readonly Func<string, Task<string>> _removeGoogle;
    private readonly Func<IReadOnlyList<string>, Task<string>> _registerBatch;
    private readonly Func<string, Action<int, int, string, string>, Task<string>> _deleteGroup;
    private readonly Action _startMcs;
    private readonly Action _stopMcs;
    private readonly Func<string> _mcsState;
    private readonly Action<string> _startGroupMcs;
    private readonly Action<string> _stopGroupMcs;
    private readonly List<(string Login, CheckBox Box)> _accountBoxes = [];
    private readonly TextBlock _googleStatus = new();
    private readonly ComboBox _googleRecipients = new()
    {
        MinWidth = 340, Margin = new Thickness(0, 5, 0, 8)
    };
    private readonly ProgressBar _deleteProgress = new()
    {
        Minimum = 0, Maximum = 100, Height = 17,
        Visibility = Visibility.Collapsed
    };
    private readonly TextBlock _deleteProgressText = new()
    {
        TextWrapping = TextWrapping.Wrap,
        Visibility = Visibility.Collapsed
    };
    private readonly TextBlock _mcsStatus = new();
    private readonly TextBlock _summary = new();
    private readonly TextBlock _selectionCount = new();
    private readonly TextBlock _actionStatus = new() { TextWrapping = TextWrapping.Wrap };
    private readonly TextBox _operationTrace = new()
    {
        IsReadOnly = true,
        IsUndoEnabled = false,
        Height = 165,
        TextWrapping = TextWrapping.NoWrap,
        FontFamily = new FontFamily("Consolas"),
        FontSize = 11,
        VerticalScrollBarVisibility = ScrollBarVisibility.Auto,
        HorizontalScrollBarVisibility = ScrollBarVisibility.Auto
    };
    private readonly StackPanel _accounts = new();
    private readonly ListBox _groups = new() { Height = 125 };
    private readonly Button _registerGoogleButton = new()
    {
        Content = "Зарегистрировать Google", Padding = new Thickness(10, 6, 10, 6)
    };
    private readonly Button _deleteGoogleButton = new()
    {
        Content = "Удалить регистрацию Google", Padding = new Thickness(10, 6, 10, 6)
    };
    private readonly Button _startMcsButton = new()
    {
        Content = "Подключить MCS", Padding = new Thickness(10, 6, 10, 6)
    };
    private readonly Button _stopMcsButton = new()
    {
        Content = "Остановить приём", Padding = new Thickness(10, 6, 10, 6)
    };
    private readonly Button _registerGroupButton = new()
    {
        Content = "Зарегистрировать выбранные аккаунты",
        Padding = new Thickness(10, 6, 10, 6)
    };
    private readonly Button _startGroupMcsButton = new()
    {
        Content = "Подключить MCS группы", Padding = new Thickness(10, 6, 10, 6)
    };
    private readonly Button _stopGroupMcsButton = new()
    {
        Content = "Остановить MCS группы", Padding = new Thickness(10, 6, 10, 6)
    };
    private readonly Button _deleteGroupButton = new()
    {
        Content = "Удалить выбранную группу", Padding = new Thickness(10, 6, 10, 6)
    };
    private bool _busy;
    private int _deleteConfirmed;
    private int _deleteUnconfirmed;

    internal PushSubscriptionManagerWindow(
        IEnumerable<string> authorized,
        Func<PushGroupRegistryStore.Registry> readGroups,
        Func<string[]> googleIds,
        Func<Task<string>> registerGoogle,
        Func<string, Task<string>> removeGoogle,
        Func<IReadOnlyList<string>, Task<string>> registerBatch,
        Func<string, Action<int, int, string, string>, Task<string>> deleteGroup,
        Action startMcs, Action stopMcs, Func<string> mcsState,
        Action<string> startGroupMcs, Action<string> stopGroupMcs)
    {
        _authorized = authorized.Distinct(StringComparer.OrdinalIgnoreCase)
            .OrderBy(x => x, StringComparer.CurrentCultureIgnoreCase).ToArray();
        _readGroups = readGroups;
        _googleIds = googleIds;
        _registerGoogle = registerGoogle;
        _removeGoogle = removeGoogle;
        _registerBatch = registerBatch;
        _deleteGroup = deleteGroup;
        _startMcs = startMcs;
        _stopMcs = stopMcs;
        _mcsState = mcsState;
        _startGroupMcs = startGroupMcs;
        _stopGroupMcs = stopGroupMcs;
        Title = "MailRu Desktop — Google и группы PushMe";
        Width = 900;
        Height = 780;
        MinWidth = 710;
        MinHeight = 550;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        FontFamily = new FontFamily("Segoe UI");
        SetResourceReference(BackgroundProperty, "AppWindowBrush");
        SetResourceReference(ForegroundProperty, "AppTextBrush");

        var content = new StackPanel { Margin = new Thickness(18) };
        content.Children.Add(Heading("Этап 1. Независимые получатели Google"));
        content.Children.Add(_googleStatus);
        content.Children.Add(_googleRecipients);
        content.Children.Add(Info("Кнопка регистрации создаёт нового независимого получателя. " +
            "Каждая группа использует отдельный свободный Google-токен; " +
            "повторное подключение MCS не создаёт новую регистрацию."));
        content.Children.Add(Buttons(_registerGoogleButton, _deleteGoogleButton));

        content.Children.Add(Heading("Google MCS — состояние соединения"));
        content.Children.Add(_mcsStatus);
        content.Children.Add(Buttons(_startMcsButton, _stopMcsButton));
        content.Children.Add(Info("«Остановить приём» закрывает только соединение. " +
            "Регистрация Google и все группы PushMe остаются сохранёнными."));

        content.Children.Add(Heading("Этап 2. Группы PushMe"));
        content.Children.Add(_summary);
        content.Children.Add(Info("Выберите не более 30 свободных аккаунтов. " +
            "Один запрос = одна группа с отдельным свободным получателем Google. " +
            "Другие группы и их Google-получатели не затрагиваются."));
        _groups.SelectionChanged += (_, _) => UpdateControls();
        _googleRecipients.SelectionChanged += (_, _) => UpdateControls();
        content.Children.Add(_groups);
        content.Children.Add(Buttons(_startGroupMcsButton,
            _stopGroupMcsButton, _deleteGroupButton));
        content.Children.Add(Heading("Аккаунты и принадлежность к группам"));
        var accountScroll = new ScrollViewer
        {
            Content = _accounts, Height = 230,
            VerticalScrollBarVisibility = ScrollBarVisibility.Auto
        };
        accountScroll.SetResourceReference(BackgroundProperty, "AppPanelBrush");
        content.Children.Add(accountScroll);
        content.Children.Add(_selectionCount);
        content.Children.Add(Buttons(_registerGroupButton));
        content.Children.Add(_actionStatus);
        content.Children.Add(_deleteProgressText);
        content.Children.Add(_deleteProgress);
        content.Children.Add(Heading("Журнал действий с аккаунтами"));
        content.Children.Add(Info(
            "Локальный подробный журнал: аккаунт, группа, номер операции, ответ. " +
            "Копирование и сохранение полного или обезличенного отчёта доступны в настройках."));
        content.Children.Add(_operationTrace);
        Content = new ScrollViewer
        {
            Content = content, VerticalScrollBarVisibility = ScrollBarVisibility.Auto
        };

        _registerGoogleButton.Click += async (_, _) => await ExecuteAsync(_registerGoogle);
        _deleteGoogleButton.Click += async (_, _) =>
        {
            if (MessageBox.Show(this,
                "Удалить сохранённую регистрацию Google? Перед этим необходимо удалить " +
                "все группы PushMe. Это отдельное действие, не остановка приёма.",
                "Удаление Google", MessageBoxButton.YesNo, MessageBoxImage.Warning)
                != MessageBoxResult.Yes) return;
            if (_googleRecipients.SelectedItem is not RecipientEntry selected) return;
            await ExecuteAsync(() => _removeGoogle(selected.Id));
        };
        _registerGroupButton.Click += async (_, _) =>
        {
            var chosen = _accountBoxes.Where(x => x.Box.IsChecked == true)
                .Select(x => x.Login).ToArray();
            if (chosen.Length == 0) return;
            if (MessageBox.Show(this,
                "Зарегистрировать выбранные " + chosen.Length +
                " аккаунтов одним запросом PushMe?",
                "Подтвердить группу", MessageBoxButton.YesNo, MessageBoxImage.Question)
                != MessageBoxResult.Yes) return;
            await ExecuteAsync(() => _registerBatch(chosen));
        };
        _startGroupMcsButton.Click += (_, _) =>
        {
            if (_groups.SelectedItem is GroupEntry group)
                _startGroupMcs(group.Id);
            UpdateView();
        };
        _stopGroupMcsButton.Click += (_, _) =>
        {
            if (_groups.SelectedItem is GroupEntry group)
                _stopGroupMcs(group.Id);
            UpdateView();
        };
        _deleteGroupButton.Click += async (_, _) =>
        {
            if (_groups.SelectedItem is not GroupEntry selected) return;
            if (MessageBox.Show(this,
                "Снять подписки " + selected.Count + " аккаунтов группы №" +
                selected.Position + "? Google-токен сохранится. " +
                "Отписка выполняется адресно и может занять несколько минут.",
                "Удалить группу", MessageBoxButton.YesNo, MessageBoxImage.Warning)
                != MessageBoxResult.Yes) return;
            _deleteProgress.Visibility = Visibility.Visible;
            _deleteProgressText.Visibility = Visibility.Visible;
            _deleteConfirmed = 0;
            _deleteUnconfirmed = 0;
            _deleteProgress.Value = 0;
            _deleteProgressText.Text = "Удаление: 0 из " + selected.Count;
            await ExecuteAsync(() => _deleteGroup(selected.Id, (done, total, login, result) =>
            {
                if (!Dispatcher.CheckAccess())
                {
                    _ = Dispatcher.BeginInvoke(new Action(() =>
                        SetDeleteProgress(done, total, login, result)));
                    return;
                }
                SetDeleteProgress(done, total, login, result);
            }));
        };
        _startMcsButton.Click += (_, _) => { _startMcs(); UpdateView(); };
        _stopMcsButton.Click += (_, _) => { _stopMcs(); UpdateView(); };
        PushDiagnostics.Changed += RefreshOperationTrace;
        Closed += (_, _) => PushDiagnostics.Changed -= RefreshOperationTrace;
        RefreshOperationTrace();
        UpdateView();
    }

    private void SetDeleteProgress(int done, int total, string login, string result)
    {
        _deleteProgress.Value = total == 0 ? 0 : done * 100.0 / total;
        if (login.Length > 0)
        {
            if (result == "Удалено") _deleteConfirmed++;
            else _deleteUnconfirmed++;
        }
        _deleteProgressText.Text = "Обработано " + done + " из " + total +
            ". Подтверждено: " + _deleteConfirmed +
            ". Не подтверждено: " + _deleteUnconfirmed +
            (login.Length == 0 ? "" : ". Последний: " + login + " — " + result);
    }

    private void RefreshOperationTrace()
    {
        if (!Dispatcher.CheckAccess())
        {
            if (!Dispatcher.HasShutdownStarted)
                _ = Dispatcher.BeginInvoke(new Action(RefreshOperationTrace));
            return;
        }
        _operationTrace.Text = PushDiagnostics.Report(42);
        _operationTrace.ScrollToEnd();
    }

    private sealed record RecipientEntry(string Id, int Position, bool InUse)
    {
        public override string ToString() => "Google №" + Position +
            (InUse ? " — занят" : " — свободен") +
            (Id == SharedGooglePushIdentityStore.PrimaryRecipientId ? " (исходный)" : "");
    }

    private sealed record GroupEntry(string Id, int Position, int Count,
        string State, string RecipientId, bool ReceiveEnabled)
    {
        public override string ToString() =>
            "Группа №" + Position + " — " + Count +
            " аккаунтов, Google " + RecipientId +
            (ReceiveEnabled ? ", MCS включён" : ", MCS остановлен") + " (" + (State switch
            {
                "IMPORTED" => "состав восстановлен, доставка не проверена",
                "RECHECK_REQUIRED" => "доставка после другой группы не проверена",
                "EVENT_SEEN" => "событие нового письма получено",
                _ => "регистрация принята PushMe"
            }) + ")";
    }

    private static TextBlock Heading(string value) => new()
    {
        Text = value, FontSize = 16, FontWeight = FontWeights.SemiBold,
        Margin = new Thickness(0, 15, 0, 7)
    };

    private static TextBlock Info(string value)
    {
        var text = new TextBlock
        {
            Text = value, TextWrapping = TextWrapping.Wrap,
            Margin = new Thickness(0, 6, 0, 8)
        };
        text.SetResourceReference(TextBlock.ForegroundProperty, "AppMutedTextBrush");
        return text;
    }

    private static StackPanel Buttons(params Button[] buttons)
    {
        var panel = new StackPanel { Orientation = Orientation.Horizontal,
            Margin = new Thickness(0, 8, 0, 4) };
        foreach (var button in buttons)
        {
            button.Margin = new Thickness(0, 0, 9, 0);
            panel.Children.Add(button);
        }
        return panel;
    }

    private async Task ExecuteAsync(Func<Task<string>> action)
    {
        if (_busy) return;
        _busy = true;
        UpdateControls();
        try { _actionStatus.Text = await action(); }
        catch (Exception ex)
        {
            // Avoid raw server messages, OAuth and account secrets in GUI.
            _actionStatus.Text = "Операция завершилась ошибкой: " + ex.GetType().Name +
                ". Подробности в обезличенном журнале.";
            PushDiagnostics.Failure("GROUP_MANAGER", ex);
        }
        finally
        {
            _busy = false;
            UpdateView();
        }
    }

    internal void SetMcsState(string text)
    {
        if (!Dispatcher.CheckAccess())
        {
            _ = Dispatcher.BeginInvoke(new Action(() => SetMcsState(text)));
            return;
        }
        _mcsStatus.Text = "MCS: " + text;
        UpdateControls();
    }

    internal void Report(string text)
    {
        if (!Dispatcher.CheckAccess())
        {
            _ = Dispatcher.BeginInvoke(new Action(() => Report(text)));
            return;
        }
        _actionStatus.Text = text;
        UpdateView();
    }

    internal void UpdateView()
    {
        if (!Dispatcher.CheckAccess())
        {
            _ = Dispatcher.BeginInvoke(new Action(UpdateView));
            return;
        }
        PushGroupRegistryStore.Registry registry;
        try { registry = _readGroups(); }
        catch (Exception ex)
        {
            _actionStatus.Text = "Не удалось прочитать реестр: " + ex.GetType().Name;
            return;
        }
        var ids = _googleIds();
        var previousId = (_googleRecipients.SelectedItem as RecipientEntry)?.Id;
        _googleRecipients.Items.Clear();
        var occupied = new HashSet<string>(registry.Groups.Select(g => g.RecipientId),
            StringComparer.Ordinal);
        for (var i = 0; i < ids.Length; i++)
        {
            var entry = new RecipientEntry(ids[i], i + 1, occupied.Contains(ids[i]));
            _googleRecipients.Items.Add(entry);
            if (entry.Id == previousId) _googleRecipients.SelectedItem = entry;
        }
        if (_googleRecipients.SelectedItem is null && ids.Length > 0)
            _googleRecipients.SelectedIndex = 0;
        _googleStatus.Text = ids.Length == 0
            ? "Регистрация Google отсутствует. Сначала выполните этап 1."
            : "Сохранено Google-регистраций: " + ids.Length +
              ". Свободно: " + ids.Count(id => !occupied.Contains(id)) + ".";
        _mcsStatus.Text = "MCS: " + _mcsState();

        var lastId = (_groups.SelectedItem as GroupEntry)?.Id;
        _groups.Items.Clear();
        var mapping = new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase);
        for (var i = 0; i < registry.Groups.Length; i++)
        {
            var group = registry.Groups[i];
            var display = new GroupEntry(group.Id, i + 1,
                group.Accounts.Length, group.State, group.RecipientId,
                group.ReceiveEnabled ?? registry.ReceiveEnabled);
            _groups.Items.Add(display);
            if (group.Id == lastId) _groups.SelectedItem = display;
            foreach (var login in group.Accounts)
                mapping[login] = "Группа №" + (i + 1);
        }
        _accounts.Children.Clear();
        _accountBoxes.Clear();
        var free = 0;
        foreach (var login in _authorized)
        {
            var registered = mapping.TryGetValue(login, out var groupName);
            if (!registered) free++;
            var check = new CheckBox
            {
                Content = login + (registered ? "  —  " + groupName : "  —  без группы"),
                Tag = login, IsEnabled = !registered && !_busy,
                Margin = new Thickness(3, 4, 0, 4)
            };
            check.Checked += (_, _) => UpdateControls();
            check.Unchecked += (_, _) => UpdateControls();
            _accountBoxes.Add((login, check));
            _accounts.Children.Add(check);
        }
        _summary.Text = "Групп: " + registry.Groups.Length +
            ". Аккаунтов в группах: " + mapping.Count +
            ". Свободных: " + free + ".";
        UpdateControls();
    }

    private void UpdateControls()
    {
        var count = _accountBoxes.Count(x => x.Box.IsChecked == true);
        _selectionCount.Text = "Выбрано для следующей группы: " + count +
            " из 30 максимально допустимых.";
        var groups = _readGroups().Groups;
        var selectedGoogle = _googleRecipients.SelectedItem as RecipientEntry;
        var hasFreeGoogle = _googleIds().Any(id =>
            !groups.Any(g => g.RecipientId == id));
        _registerGoogleButton.IsEnabled = !_busy;
        _deleteGoogleButton.IsEnabled = !_busy && selectedGoogle is not null &&
            !selectedGoogle.InUse;
        _registerGroupButton.IsEnabled = !_busy && hasFreeGoogle &&
            count is > 0 and <= 30;
        _deleteGroupButton.IsEnabled = !_busy && _groups.SelectedItem is GroupEntry;
        var chosen = _groups.SelectedItem as GroupEntry;
        _startGroupMcsButton.IsEnabled = !_busy && chosen is { ReceiveEnabled: false };
        _stopGroupMcsButton.IsEnabled = !_busy && chosen is { ReceiveEnabled: true };
        // The persisted receiving intent, not merely an instantaneous network
        // status, controls Start/Stop. A user must be able to press Stop even
        // while the worker reports ERROR and awaits its reconnect delay.
        var receiveEnabled = _readGroups().ReceiveEnabled;
        _startMcsButton.IsEnabled = !_busy && _googleIds().Length > 0 &&
            _readGroups().Groups.Length > 0 && !receiveEnabled;
        _stopMcsButton.IsEnabled = !_busy && receiveEnabled;
    }
}
