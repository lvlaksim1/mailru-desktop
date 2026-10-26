using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private bool _accountReauthInProgress;

    private ContextMenu CreateCompactContextMenu()
    {
        var menu = new ContextMenu();
        if (TryFindResource("CompactContextMenuStyle") is Style style)
            menu.Style = style;
        return menu;
    }

    private MenuItem CreateCompactMenuItem(string header)
    {
        var item = new MenuItem
        {
            Header = header
        };
        if (TryFindResource("CompactContextMenuItemStyle") is Style style)
            item.Style = style;
        return item;
    }

    private void AccountRailListBox_PreviewMouseRightButtonDown(
        object sender,
        MouseButtonEventArgs e)
    {
        if (e.OriginalSource is not DependencyObject source ||
            ItemsControl.ContainerFromElement(AccountRailListBox, source) is not ListBoxItem item)
        {
            return;
        }

        ContextMenu menu;
        if (item.DataContext is AccountRailItem account)
        {
            menu = CreateAccountContextMenu(account);
        }
        else if (item.DataContext is AccountSectionItem section)
        {
            menu = CreateCompactContextMenu();
            var renameItem = CreateCompactMenuItem("Переименовать раздел");
            renameItem.Click += (_, _) => RenameAccountSection(section);
            menu.Items.Add(renameItem);

            var deleteSectionItem = CreateCompactMenuItem("Удалить раздел");
            deleteSectionItem.Click += (_, _) => DeleteAccountSection(section);
            menu.Items.Add(deleteSectionItem);
        }
        else
        {
            return;
        }

        item.ContextMenu = menu;
        menu.PlacementTarget = item;
        menu.IsOpen = true;
        e.Handled = true;
    }

    private ContextMenu CreateAccountContextMenu(AccountRailItem account)
    {
        var menu = CreateCompactContextMenu();
        var copyItem = CreateCompactMenuItem("Копировать адрес");
        copyItem.Click += (_, _) =>
        {
            try { Clipboard.SetText(account.Login); }
            catch (Exception ex)
            {
                DiagnosticLog.Write("account_copy_address", ex.GetType().Name);
            }
        };
        menu.Items.Add(copyItem);

        var reauthItem = CreateCompactMenuItem("Повторная авторизация");
        reauthItem.IsEnabled = !_accountReauthInProgress;
        reauthItem.Click += async (_, _) => await ReauthenticateSavedAccountAsync(account.Login);
        menu.Items.Add(reauthItem);

        var deleteItem = CreateCompactMenuItem("Удалить");
        deleteItem.Click += async (_, _) => await RemoveSavedAccountAsync(account.Login);
        menu.Items.Add(deleteItem);
        return menu;
    }

    // A failed attempt must not clear active credentials or replace a saved
    // account. In particular, unlike the "add account" flow, this routine
    // never calls ClearRuntimeAuthorization before contacting Mail.ru.
    private async Task ReauthenticateSavedAccountAsync(string login)
    {
        if (_accountReauthInProgress ||
            !_authStore.Logins.Contains(login, StringComparer.OrdinalIgnoreCase))
            return;

        _authStore.TryGetPassword(login, out var knownPassword);
        var dialog = new AddAccountWindow(login, knownPassword) { Owner = this };
        if (dialog.ShowDialog() != true)
            return;

        _accountReauthInProgress = true;
        var previousGlobalStatus = AuthStatusText.Text;
        var item = _accountRailItems.FirstOrDefault(
            x => string.Equals(x.Login, login, StringComparison.OrdinalIgnoreCase));
        if (item is not null)
            item.Status = "Повторная авторизация...";
        TraceAuthToken(login, "manual_reauth", "begin", "requested");
        var credentialsUpdated = false;

        try
        {
            var passwordForClient = dialog.Password;
            var result = await _mailRu.AuthenticateAsync(login, passwordForClient);
            if (!result.Success && IsInteractiveAuthState(result))
            {
                var retry = await TryHandleInteractiveAuthAsync(login, passwordForClient, result);
                if (retry is not null)
                {
                    result = retry.Value.Result;
                    passwordForClient = retry.Value.Password;
                }
            }

            if (!result.Success || string.IsNullOrWhiteSpace(result.AccessToken))
            {
                WriteAuthDiagnostics(result, login);
                TraceAuthToken(login, "manual_reauth", "new_credentials", "not_received",
                    kind: result.State.ToString());
                if (item is not null)
                    item.Status = "Повторная авторизация не выполнена";
                AppDialog.Info(this, "Повторная авторизация",
                    "Mail.ru не подтвердил новый вход. Прежняя авторизация аккаунта сохранена.");
                return;
            }

            // A successful login response is not sufficient. Verify the
            // account with a real read-only folder request BEFORE saving it.
            try
            {
                await _mailRu.GetFolderThreadsAsync(result.AccessToken, 0, limit: 1);
                TraceAuthToken(login, "manual_reauth", "verify_new_access", "accepted");
            }
            catch (MailRuDesktop.Protocol.MailRuAuthorizationException rejected)
            {
                TraceAuthToken(login, "manual_reauth", "verify_new_access",
                    "rejected", rejected.StatusCode, rejected.DenialSource);
                throw;
            }

            if (!_authStore.UpdateTokens(login, result.AccessToken, result.RefreshToken,
                    passwordForClient))
            {
                TraceAuthToken(login, "manual_reauth", "store", "account_missing");
                if (item is not null)
                    item.Status = "Аккаунт не найден";
                return;
            }

            credentialsUpdated = true;
            SetAccountAuthorizationInvalid(login, false);
            TraceAuthToken(login, "manual_reauth", "store", "updated");
            if (item is not null)
                item.Status = "Авторизация восстановлена";

            if (string.Equals(_activeLogin, login, StringComparison.OrdinalIgnoreCase))
            {
                // An existing folder request must finish with its original
                // token; a later account switch must also take precedence.
                while (_loadingFolder)
                    await Task.Delay(50);

                if (string.Equals(_activeLogin, login, StringComparison.OrdinalIgnoreCase))
                {
                    _accessToken = result.AccessToken;
                    if (_authStore.TryRestore(login, out var saved) && saved is not null)
                        _refreshToken = saved.RefreshToken;
                    SyncBackgroundPush();
                    await LoadFolderAsync(0);
                }
            }
        }
        catch (Exception ex)
        {
            TraceAuthToken(login, "manual_reauth", "operation", "exception",
                kind: ex.GetType().Name);
            if (item is not null)
                item.Status = credentialsUpdated
                    ? "Авторизация обновлена · требуется проверить загрузку"
                    : "Повторная авторизация не выполнена";
            AppDialog.Info(this, "Повторная авторизация",
                credentialsUpdated
                    ? "Новые данные авторизации сохранены, но последующая операция завершилась ошибкой. " +
                      "Проверьте почту и диагностику."
                    : "Не удалось подтвердить новую авторизацию. Прежние сохранённые данные не изменены. " +
                      "Сведения об этапе ошибки записаны в диагностику.");
        }
        finally
        {
            _accountReauthInProgress = false;
            if (!string.Equals(_activeLogin, login, StringComparison.OrdinalIgnoreCase))
                AuthStatusText.Text = previousGlobalStatus;
        }
    }

    private async Task RemoveSavedAccountAsync(string login)
    {
        if (!AppDialog.Confirm(
                this,
                "Удаление аккаунта",
                $"Удалить аккаунт «{login}» из MailRu Desktop?\n\n" +
                "Сохранённая локальная авторизация и изолированный профиль проверки этого аккаунта будут удалены. " +
                "Сам почтовый ящик Mail.ru не удаляется.",
                "Удалить",
                "Отмена"))
        {
            return;
        }

        var wasActive = string.Equals(
            login,
            _activeLogin,
            StringComparison.OrdinalIgnoreCase);

        if (!_authStore.Remove(login))
            return;

        // Invalidate in-flight folder/search results immediately after the
        // confirmed local removal, before the asynchronous profile deletion.
        var removalGeneration = wasActive
            ? Interlocked.Increment(ref _accountSwitchGeneration)
            : Volatile.Read(ref _accountSwitchGeneration);
        if (wasActive)
            CancelServerSearch();

        RemoveAccountFromRailLayout(login);
        await MailRuAuthProfileStore.DeleteProfileAsync(login);

        if (!wasActive ||
            !string.Equals(_activeLogin, login, StringComparison.OrdinalIgnoreCase))
        {
            // Another account may have become active while the profile was removed.
            RefreshSavedLogins();
            RefreshAccountRail(_activeLogin);
            return;
        }

        ClearRuntimeAuthorization();
        _currentMessages.Clear();
        _visibleMessages.Clear();
        ClearSelectedMessage();

        FolderListBox.ItemsSource = null;
        MoveFolderComboBox.ItemsSource = null;
        AuthStatusText.Text = "Аккаунт удалён";
        FolderStatusText.Text = "Нет активного аккаунта";

        RefreshSavedLogins();

        // The old folder request must release its loading flag before the new
        // account starts. This is the same ordering as ordinary account switches.
        while (_loadingFolder)
            await Task.Delay(25);

        // Respect a newer explicit account choice made during that wait.
        if (removalGeneration != Volatile.Read(ref _accountSwitchGeneration) ||
            !string.IsNullOrWhiteSpace(_activeLogin))
        {
            RefreshAccountRail(_activeLogin);
            return;
        }

        var nextLogin = _authStore.Logins.FirstOrDefault();
        if (!string.IsNullOrWhiteSpace(nextLogin) &&
            RestoreSavedAuthorization(nextLogin))
        {
            RefreshSavedLogins();
            RefreshAccountRail(nextLogin);
            ShowWorkspace(MailWorkspace);
            await LoadFolderAsync(0);
            return;
        }

        RefreshAccountRail(null);
        AuthStatusText.Text = _authStore.Logins.Count == 0
            ? "Добавьте аккаунт"
            : "Сохранённые аккаунты требуют повторного входа";
    }
}
