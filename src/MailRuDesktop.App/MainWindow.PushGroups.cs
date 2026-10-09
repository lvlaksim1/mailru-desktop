using System.Windows;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly PushGroupRegistryStore _pushGroups = new();
    private readonly SemaphoreSlim _groupChangeGate = new(1, 1);
    private PushSubscriptionManagerWindow? _pushManagerWindow;
    private string _googleMcsState = "Остановлен";

    private bool HasGooglePushIdentity()
    {
        try { return new SharedGooglePushIdentityStore().Load() is not null; }
        catch (Exception error)
        {
            PushDiagnostics.Failure("GOOGLE_IDENTITY_READ", error);
            return false;
        }
    }

    private void SetGoogleMcsState(string phase)
    {
        if (!Dispatcher.CheckAccess())
        {
            if (!_pushShuttingDown && !Dispatcher.HasShutdownStarted)
                _ = Dispatcher.BeginInvoke(new Action(() => SetGoogleMcsState(phase)));
            return;
        }
        if (_pushShuttingDown) return;
        _googleMcsState = phase switch
        {
            "CONNECTING" or "MCS_TCP_CONNECT" or "MCS_TCP_CONNECTED" or
                "MCS_TLS_HANDSHAKE" or "MCS_TLS_OK" or "MCS_LOGIN_SEND" =>
                "Подключается",
            "RECONNECTING" => "Переподключается",
            "MCS_LOGIN_OK" => "Подключён",
            "MCS_SERVER_CLOSE" or "MCS_KEEPALIVE_TIMEOUT" or
                "MCS_SERVER_LOGIN_ERROR" or "MCS_LOGIN_INVALID_RESPONSE" or
                "ERROR" => "Ошибка соединения",
            "STOPPED" => "Остановлен",
            _ => phase
        };
        if (GoogleMcsStatusText is not null)
            GoogleMcsStatusText.Text = "Google MCS: " + _googleMcsState;
        _pushManagerWindow?.SetMcsState(_googleMcsState);
    }

    private void InitializePersistedPushGroups()
    {
        try
        {
            var store = new SharedGooglePushIdentityStore();
            var identity = store.Load();
            var groups = _pushGroups.Load();
            // Import the existing confirmed roster without claiming that a
            // server group exists. The imported label is explicit in the UI.
            if (identity is not null)
                groups = _pushGroups.ImportLegacy(groups, identity.SubscribedAccounts);
            GoogleRecipientStatusText.Text = identity is null
                ? "Google: регистрация отсутствует."
                : "Google: регистрация сохранена; токены защищены.";
            if (groups.ReceiveEnabled && identity is not null)
                StartMcsListening();
            else
                SetGoogleMcsState("STOPPED");
        }
        catch (Exception error)
        {
            PushDiagnostics.Failure("PUSH_GROUP_STARTUP", error);
            GoogleRecipientStatusText.Text =
                "Не удалось прочитать сохранённые регистрации. Новая регистрация не запускается.";
            SetGoogleMcsState("ERROR");
        }
    }

    private bool StartMcsListening()
    {
        if (!_pushBackgroundReady || _pushShuttingDown || _pushBackground is null ||
            _pushProbeWindow is { IsVisible: true })
            return false;
        var identity = new SharedGooglePushIdentityStore().Load();
        if (identity is null) return false;
        var registry = _pushGroups.Load();
        var confirmed = new HashSet<string>(identity.SubscribedAccounts,
            StringComparer.OrdinalIgnoreCase);
        var accounts = new List<(string Login, string Token)>();
        foreach (var login in registry.Groups.SelectMany(x => x.Accounts)
                     .Distinct(StringComparer.OrdinalIgnoreCase))
        {
            if (!confirmed.Contains(login)) continue;
            if (_authStore.TryRestore(login, out var auth) &&
                !string.IsNullOrWhiteSpace(auth?.AccessToken))
                accounts.Add((login, auth.AccessToken));
        }
        if (accounts.Count == 0)
        {
            SetGoogleMcsState("STOPPED");
            BackgroundPushStatusText.Text =
                "Нет подтверждённых групп с действующей авторизацией почты.";
            return false;
        }
        _pushGroups.Save(registry with { ReceiveEnabled = true });
        PushDiagnostics.Record("UI", "MCS_RECEIVE_ENABLED", accounts.Count);
        SetGoogleMcsState("CONNECTING");
        _pushBackground.Reconcile(accounts, enabled: true,
            retryOnFailure: true, preserveOtherAccounts: true, listenOnly: true);
        BackgroundPushStatusText.Text = "Google MCS: подключение для " +
            accounts.Count + " сохранённых аккаунтов.";
        return true;
    }

    private void StopMcsListening()
    {
        var current = _pushGroups.Load();
        _pushGroups.Save(current with { ReceiveEnabled = false });
        PushDiagnostics.Record("UI", "MCS_RECEIVE_DISABLED_ONLY");
        _pushBackground?.StopListeningOnly();
        _pushConnectedAccounts.Clear();
        SetGoogleMcsState("STOPPED");
        BackgroundPushStatusText.Text =
            "Приём MCS остановлен. Google-регистрация и группы PushMe сохранены.";
    }

    private async Task<string> RegisterGooglePushAsync()
    {
        if (!await _groupChangeGate.WaitAsync(TimeSpan.FromSeconds(1)))
            return "Другая операция ещё выполняется.";
        try
        {
            using var probe = new MailRuPushProbe();
            var existing = new SharedGooglePushIdentityStore().Load();
            if (existing is not null)
                return "Google уже зарегистрирован. Существующие данные сохранены.";
            using var timeout = new CancellationTokenSource(TimeSpan.FromMinutes(2));
            var state = await probe.EnsureGoogleRecipientAsync(
                message => PushDiagnostics.Record("GOOGLE", "STAGE1_PROGRESS"),
                timeout.Token);
            GoogleRecipientStatusText.Text =
                "Google: регистрация сохранена; токены защищены.";
            PushDiagnostics.Record("GOOGLE", "STAGE1_COMPLETE");
            return "Google зарегистрирован: device_id и оба токена защищённо сохранены. " +
                "Повторная регистрация не требуется.";
        }
        finally { _groupChangeGate.Release(); }
    }

    private async Task<string> RegisterPushGroupAsync(IReadOnlyList<string> selected)
    {
        if (!await _groupChangeGate.WaitAsync(TimeSpan.FromSeconds(1)))
            return "Другая операция ещё выполняется.";
        try
        {
            if (_pushProbeWindow is { IsVisible: true })
                return "Сначала завершите отдельную проверку одного аккаунта.";
            var registry = _pushGroups.Load();
            if (new SharedGooglePushIdentityStore().Load() is null)
                return "Сначала зарегистрируйте Google на этапе 1.";
            if (selected.Count == 0 ||
                selected.Distinct(StringComparer.OrdinalIgnoreCase).Count() != selected.Count)
                return "Выберите хотя бы один уникальный аккаунт.";
            var busy = new HashSet<string>(registry.Groups.SelectMany(g => g.Accounts),
                StringComparer.OrdinalIgnoreCase);
            if (selected.Any(busy.Contains))
                return "Один или несколько аккаунтов уже входят в зарегистрированную группу.";
            var accounts = new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase);
            foreach (var login in selected)
            {
                if (!_authStore.TryRestore(login, out var auth) ||
                    string.IsNullOrWhiteSpace(auth?.AccessToken))
                    return "Выбранный аккаунт не авторизован. Регистрация не выполнена.";
                accounts[login] = auth.AccessToken;
            }
            var newGroupId = Guid.NewGuid().ToString("N");
            PushDiagnostics.Record("PUSHME", "GROUP_SUBSCRIBE_START", accounts.Count);
            using var probe = new MailRuPushProbe();
            using var timeout = new CancellationTokenSource(TimeSpan.FromSeconds(90));
            var result = await probe.RegisterGroupAsync(
                accounts, timeout.Token, newGroupId);
            if (result.Error is not null)
                return "PushMe отклонил запрос для " + accounts.Count +
                    " аккаунтов. Причина и код — в обезличенной диагностике. " +
                    "Существующие группы не изменены.";
            var updated = PushGroupRegistryStore.Register(registry,
                selected.ToArray(), result.Accepted, newGroupId);
            _pushGroups.Save(updated);
            PushDiagnostics.Record("PUSHME", "GROUP_SUBSCRIBE_CONFIRMED", result.Accepted.Count);
            if (updated.Groups.Length != registry.Groups.Length &&
                registry.ReceiveEnabled)
                StartMcsListening(); // Reopen MCS, NEVER resubscribe in PushMe.
            return "PushMe принял " + result.Accepted.Count + " из " +
                accounts.Count + ". Группа сохранена; существующие группы не затронуты.";
        }
        finally { _groupChangeGate.Release(); }
    }

    private async Task<string> DeletePushGroupAsync(string groupId)
    {
        if (!await _groupChangeGate.WaitAsync(TimeSpan.FromSeconds(1)))
            return "Другая операция ещё выполняется.";
        try
        {
            var registry = _pushGroups.Load();
            var group = registry.Groups.FirstOrDefault(x => x.Id == groupId);
            if (group is null) return "Группа отсутствует.";
            var shouldResume = registry.ReceiveEnabled;
            PushDiagnostics.Record("PUSHME", "GROUP_UNSUBSCRIBE_START", group.Accounts.Length);
            foreach (var account in group.Accounts)
                PushDiagnostics.RecordAccount("PUSHME", "GROUP_MEMBER_BEFORE_REMOVAL",
                    account, group.Id);
            // Close only MCS, never revoke Google's stored device/tokens.
            _pushBackground?.StopListeningOnly();
            SetGoogleMcsState("STOPPED");
            var removed = new List<string>();
            using var probe = new MailRuPushProbe();
            for (var index = 0; index < group.Accounts.Length; index++)
            {
                var account = group.Accounts[index];
                try
                {
                    using var timeout = new CancellationTokenSource(
                        TimeSpan.FromSeconds(35));
                    if (await probe.UnsubscribeAccountAsync(
                            account, timeout.Token, group.Id,
                            index + 1, group.Accounts.Length))
                    {
                        removed.Add(account);
                        PushDiagnostics.RecordAccount("PUSHME", "GROUP_MEMBER_REMOVED",
                            account, group.Id, index + 1, group.Accounts.Length);
                        // Commit every confirmed address-level removal. On a
                        // network error remaining members stay tracked.
                        registry = PushGroupRegistryStore.RemoveAccounts(registry, [account]);
                        _pushGroups.Save(registry);
                    }
                    else
                        PushDiagnostics.RecordAccount("PUSHME", "GROUP_MEMBER_REMOVE_DEFERRED",
                            account, group.Id, index + 1, group.Accounts.Length);
                }
                catch (Exception error)
                {
                    PushDiagnostics.RecordAccount("PUSHME", "GROUP_MEMBER_REMOVE_UNCONFIRMED",
                        account, group.Id, index + 1, group.Accounts.Length);
                    PushDiagnostics.Failure("GROUP_ACCOUNT_REMOVE", error);
                }
            }
            if (registry.Groups.Length == 0 && registry.ReceiveEnabled)
                _pushGroups.Save(registry with { ReceiveEnabled = false });
            if (shouldResume && registry.Groups.Length > 0)
                StartMcsListening();
            PushDiagnostics.Record("PUSHME", "GROUP_UNSUBSCRIBE_CONFIRMED", removed.Count);
            PushDiagnostics.Record("PUSHME", "GROUP_UNSUBSCRIBE_REMAINING",
                group.Accounts.Length - removed.Count);
            return "Подтверждено адресных отписок: " + removed.Count + " из " +
                group.Accounts.Length + ". Неподтверждённые остаются в реестре.";
        }
        finally { _groupChangeGate.Release(); }
    }

    private async Task<string> DeleteGooglePushAsync()
    {
        if (!await _groupChangeGate.WaitAsync(TimeSpan.FromSeconds(1)))
            return "Другая операция ещё выполняется.";
        try
        {
            var registry = _pushGroups.Load();
            var identity = new SharedGooglePushIdentityStore().Load();
            if (registry.Groups.Length > 0 ||
                identity?.SubscribedAccounts.Length > 0)
                return "Сначала удалите все группы и подтверждённые подписки PushMe. " +
                    "Google-регистрация не затронута.";
            StopMcsListening();
            using var probe = new MailRuPushProbe();
            using var timeout = new CancellationTokenSource(TimeSpan.FromSeconds(45));
            if (!await probe.UnsubscribeSharedAsync(timeout.Token))
                return "Удаление сервером не подтверждено. Google-регистрация сохранена.";
            GoogleRecipientStatusText.Text = "Google: регистрация отсутствует.";
            return "Регистрация Google удалена после отдельного подтверждения.";
        }
        finally { _groupChangeGate.Release(); }
    }

    // One real message proves that the selected group still delivers after
    // additional PushMe batches; it does not prove all its mailboxes delivered.
    private async Task RecordPushDeliveryAsync(string login)
    {
        await _groupChangeGate.WaitAsync();
        try
        {
            var registry = _pushGroups.Load();
            var updated = PushGroupRegistryStore.MarkObservedMail(registry, login);
            if (!updated.Groups.Select(g => g.State).SequenceEqual(
                    registry.Groups.Select(g => g.State)))
            {
                _pushGroups.Save(updated);
                _pushManagerWindow?.UpdateView();
                var matching = updated.Groups.FirstOrDefault(g =>
                    g.Accounts.Contains(login, StringComparer.OrdinalIgnoreCase));
                PushDiagnostics.RecordAccount("PUSHME", "GROUP_DELIVERY_OBSERVED",
                    login, matching?.Id);
            }
        }
        catch (Exception error)
        {
            PushDiagnostics.Failure("GROUP_DELIVERY_RECORD", error);
        }
        finally { _groupChangeGate.Release(); }
    }

    private void OpenPushGroupManagerButton_Click(object sender, RoutedEventArgs e)
    {
        if (_pushManagerWindow is { IsVisible: true })
        {
            _pushManagerWindow.Activate();
            return;
        }
        var window = new PushSubscriptionManagerWindow(
            _authStore.Logins, () => _pushGroups.Load(), HasGooglePushIdentity,
            RegisterGooglePushAsync, DeleteGooglePushAsync,
            RegisterPushGroupAsync, DeletePushGroupAsync,
            () => StartMcsListening(), StopMcsListening, () => _googleMcsState)
        {
            Owner = this
        };
        _pushManagerWindow = window;
        window.Closed += (_, _) => _pushManagerWindow = null;
        window.Show();
    }
}
