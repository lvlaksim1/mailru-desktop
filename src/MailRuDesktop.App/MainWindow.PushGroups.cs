using System.Windows;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly PushGroupRegistryStore _pushGroups = new();
    private readonly SemaphoreSlim _groupChangeGate = new(1, 1);
    private PushSubscriptionManagerWindow? _pushManagerWindow;
    private string _googleMcsState = "Остановлен";
    private readonly Dictionary<string, MailRuPushBackgroundService> _supplementalReceivers = new();
    private readonly Dictionary<string, string> _mcsStates = new(StringComparer.Ordinal);

    private static string[] RegisteredGoogleIds() =>
        SharedGooglePushIdentityStore.ListRecipientIds();

    private static string LabelGoogle(string recipientId) =>
        recipientId == SharedGooglePushIdentityStore.PrimaryRecipientId
            ? "Google №1" : "Google " + recipientId;

    private async Task StopSupplementalGroupWorkersAsync()
    {
        var workers = _supplementalReceivers.Values.ToArray();
        await Task.WhenAll(workers.Select(w => w.StopAsync()));
        foreach (var worker in workers) worker.Dispose();
        _supplementalReceivers.Clear();
    }

    private MailRuPushBackgroundService? GetGroupReceiver(string recipientId)
    {
        if (recipientId == SharedGooglePushIdentityStore.PrimaryRecipientId)
            return _pushBackground;
        if (_supplementalReceivers.TryGetValue(recipientId, out var existing))
            return existing;
        var worker = new MailRuPushBackgroundService(
            (login, state) =>
            {
                if (_pushShuttingDown || Dispatcher.HasShutdownStarted) return;
                _ = Dispatcher.BeginInvoke(new Action(
                    () => ShowBackgroundPushState(login, state)));
            },
            login =>
            {
                if (_pushShuttingDown || Dispatcher.HasShutdownStarted) return;
                _ = Dispatcher.BeginInvoke(new Action(() => OnPushNewMail(login)));
            },
            phase => SetGoogleMcsState(phase, recipientId),
            recipientId);
        _supplementalReceivers.Add(recipientId, worker);
        return worker;
    }

    private bool HasGooglePushIdentity()
    {
        try { return RegisteredGoogleIds().Length > 0; }
        catch (Exception error)
        {
            PushDiagnostics.Failure("GOOGLE_IDENTITY_READ", error);
            return false;
        }
    }

    private void SetGoogleMcsState(string phase,
        string recipientId = SharedGooglePushIdentityStore.PrimaryRecipientId)
    {
        if (!Dispatcher.CheckAccess())
        {
            if (!_pushShuttingDown && !Dispatcher.HasShutdownStarted)
                _ = Dispatcher.BeginInvoke(new Action(() => SetGoogleMcsState(phase, recipientId)));
            return;
        }
        if (_pushShuttingDown) return;
        var status = phase switch
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
        _mcsStates[recipientId] = status;
        _googleMcsState = string.Join("; ", _mcsStates.OrderBy(x => x.Key)
            .Select(x => LabelGoogle(x.Key) + ": " + x.Value));
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
            GoogleRecipientStatusText.Text = RegisteredGoogleIds().Length == 0
                ? "Google: регистрация отсутствует."
                : "Google: сохранённых регистраций " + RegisteredGoogleIds().Length +
                  "; токены защищены.";
            if (groups.ReceiveEnabled && RegisteredGoogleIds().Length > 0)
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
        var registry = _pushGroups.Load();
        var running = new HashSet<string>(StringComparer.Ordinal);
        var total = 0;
        foreach (var groupByRecipient in registry.Groups.GroupBy(x => x.RecipientId))
        {
            var recipientId = groupByRecipient.Key;
            var identity = new SharedGooglePushIdentityStore(
                recipientId: recipientId).Load();
            if (identity is null)
            {
                PushDiagnostics.Record("GOOGLE", "ASSIGNED_IDENTITY_MISSING");
                continue;
            }
            var confirmed = new HashSet<string>(identity.SubscribedAccounts,
                StringComparer.OrdinalIgnoreCase);
            var accounts = new List<(string Login, string Token)>();
            foreach (var login in groupByRecipient.SelectMany(g => g.Accounts)
                         .Distinct(StringComparer.OrdinalIgnoreCase))
            {
                if (!confirmed.Contains(login)) continue;
                if (_authStore.TryRestore(login, out var auth) &&
                    !string.IsNullOrWhiteSpace(auth?.AccessToken))
                    accounts.Add((login, auth.AccessToken));
            }
            if (accounts.Count == 0) continue;
            running.Add(recipientId);
            total += accounts.Count;
            SetGoogleMcsState("CONNECTING", recipientId);
            GetGroupReceiver(recipientId)?.Reconcile(accounts, enabled: true,
                retryOnFailure: true, preserveOtherAccounts: true, listenOnly: true);
        }
        // Closing a retired reader never unregisters that Google identity.
        if (!running.Contains(SharedGooglePushIdentityStore.PrimaryRecipientId))
            _pushBackground.StopListeningOnly();
        foreach (var (id, worker) in _supplementalReceivers)
            if (!running.Contains(id)) worker.StopListeningOnly();
        if (total == 0)
        {
            BackgroundPushStatusText.Text =
                "Нет подтверждённых групп с действующей авторизацией почты.";
            return false;
        }
        _pushGroups.Save(registry with { ReceiveEnabled = true });
        PushDiagnostics.Record("UI", "MCS_RECEIVE_ENABLED", total);
        BackgroundPushStatusText.Text = "Google MCS: подключение " +
            running.Count + " независимых получателей для " + total + " аккаунтов.";
        return true;
    }

    private void StopMcsListening()
    {
        var current = _pushGroups.Load();
        _pushGroups.Save(current with { ReceiveEnabled = false });
        PushDiagnostics.Record("UI", "MCS_RECEIVE_DISABLED_ONLY");
        _pushBackground?.StopListeningOnly();
        foreach (var worker in _supplementalReceivers.Values)
            worker.StopListeningOnly();
        _pushConnectedAccounts.Clear();
        foreach (var id in RegisteredGoogleIds())
            SetGoogleMcsState("STOPPED", id);
        BackgroundPushStatusText.Text =
            "Приём MCS остановлен. Все Google-регистрации и группы PushMe сохранены.";
    }

    private async Task<string> RegisterGooglePushAsync()
    {
        if (!await _groupChangeGate.WaitAsync(TimeSpan.FromSeconds(1)))
            return "Другая операция ещё выполняется.";
        try
        {
            using var probe = new MailRuPushProbe();
            var knownIds = RegisteredGoogleIds();
            var recipientId = knownIds.Length == 0
                ? SharedGooglePushIdentityStore.PrimaryRecipientId
                : Guid.NewGuid().ToString("N")[..12];
            using var timeout = new CancellationTokenSource(TimeSpan.FromMinutes(2));
            var state = await probe.EnsureGoogleRecipientAsync(
                message => PushDiagnostics.Record("GOOGLE", "STAGE1_PROGRESS"),
                timeout.Token, recipientId);
            GoogleRecipientStatusText.Text =
                "Google: сохранённых регистраций " + RegisteredGoogleIds().Length +
                "; токены защищены.";
            PushDiagnostics.Record("GOOGLE", "STAGE1_COMPLETE");
            _pushManagerWindow?.UpdateView();
            return LabelGoogle(recipientId) +
                " зарегистрирован. Токены сохранены независимо от остальных получателей.";
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
            var allocated = new HashSet<string>(
                registry.Groups.Select(g => g.RecipientId), StringComparer.Ordinal);
            var freeRecipient = RegisteredGoogleIds()
                .FirstOrDefault(id => !allocated.Contains(id));
            if (freeRecipient is null)
                return "Нет свободного Google-получателя. Зарегистрируйте ещё один Google.";
            if (selected.Count == 0 || selected.Count > 30 ||
                selected.Distinct(StringComparer.OrdinalIgnoreCase).Count() != selected.Count)
                return "Выберите от 1 до 30 уникальных аккаунтов.";
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
                accounts, timeout.Token, newGroupId, freeRecipient);
            if (result.Error is not null)
                return "PushMe отклонил запрос для " + accounts.Count +
                    " аккаунтов. Причина и код — в обезличенной диагностике. " +
                    "Существующие группы не изменены.";
            var updated = PushGroupRegistryStore.Register(registry,
                selected.ToArray(), result.Accepted, newGroupId, freeRecipient);
            _pushGroups.Save(updated);
            PushDiagnostics.Record("PUSHME", "GROUP_SUBSCRIBE_CONFIRMED", result.Accepted.Count);
            if (updated.Groups.Length != registry.Groups.Length &&
                registry.ReceiveEnabled)
                StartMcsListening(); // Reopen MCS, NEVER resubscribe in PushMe.
            return "PushMe принял " + result.Accepted.Count + " из " +
                accounts.Count + ". Назначен " + LabelGoogle(freeRecipient) +
                "; остальные получатели не изменены.";
        }
        finally { _groupChangeGate.Release(); }
    }

    private async Task<string> DeletePushGroupAsync(string groupId,
        Action<int, int, string, string>? progress = null)
    {
        if (!await _groupChangeGate.WaitAsync(TimeSpan.FromSeconds(1)))
            return "Другая операция ещё выполняется.";
        try
        {
            var registry = _pushGroups.Load();
            var group = registry.Groups.FirstOrDefault(x => x.Id == groupId);
            if (group is null) return "Группа отсутствует.";
            var shouldResume = registry.ReceiveEnabled;
            var recipientId = group.RecipientId;
            progress?.Invoke(0, group.Accounts.Length, "", "Начало отписки");
            PushDiagnostics.Record("PUSHME", "GROUP_UNSUBSCRIBE_START", group.Accounts.Length);
            foreach (var account in group.Accounts)
                PushDiagnostics.RecordAccount("PUSHME", "GROUP_MEMBER_BEFORE_REMOVAL",
                    account, group.Id);
            // Close only MCS, never revoke Google's stored device/tokens.
            GetGroupReceiver(recipientId)?.StopListeningOnly();
            SetGoogleMcsState("STOPPED", recipientId);
            var removed = new List<string>();
            using var probe = new MailRuPushProbe();
            for (var index = 0; index < group.Accounts.Length; index++)
            {
                var account = group.Accounts[index];
                var outcome = "Не подтверждено";
                try
                {
                    using var timeout = new CancellationTokenSource(
                        TimeSpan.FromSeconds(35));
                    if (await probe.UnsubscribeAccountAsync(
                            account, timeout.Token, group.Id,
                            index + 1, group.Accounts.Length,
                            recipientId: recipientId))
                    {
                        outcome = "Удалено";
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
                finally
                {
                    progress?.Invoke(index + 1, group.Accounts.Length, account, outcome);
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

    private async Task<string> DeleteGooglePushAsync(string recipientId)
    {
        if (!await _groupChangeGate.WaitAsync(TimeSpan.FromSeconds(1)))
            return "Другая операция ещё выполняется.";
        try
        {
            var registry = _pushGroups.Load();
            var identity = new SharedGooglePushIdentityStore(
                recipientId: recipientId).Load();
            if (registry.Groups.Any(g => g.RecipientId == recipientId) ||
                identity?.SubscribedAccounts.Length > 0)
                return "Сначала удалите все группы и подтверждённые подписки PushMe. " +
                    "Google-регистрация не затронута.";
            GetGroupReceiver(recipientId)?.StopListeningOnly();
            using var probe = new MailRuPushProbe();
            using var timeout = new CancellationTokenSource(TimeSpan.FromSeconds(45));
            if (!await probe.UnsubscribeSharedAsync(timeout.Token, recipientId))
                return "Удаление сервером не подтверждено. Google-регистрация сохранена.";
            GoogleRecipientStatusText.Text = "Google: сохранённых регистраций " +
                RegisteredGoogleIds().Length + ".";
            return LabelGoogle(recipientId) + " удалён после подтверждения PushMe.";
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
            _authStore.Logins, () => _pushGroups.Load(), RegisteredGoogleIds,
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
