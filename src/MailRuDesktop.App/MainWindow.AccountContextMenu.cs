using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;

namespace MailRuDesktop.App;

public partial class MainWindow
{
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
            ItemsControl.ContainerFromElement(AccountRailListBox, source) is not ListBoxItem item ||
            item.DataContext is not AccountRailItem account)
        {
            return;
        }

        var menu = CreateCompactContextMenu();
        var deleteItem = CreateCompactMenuItem("Удалить");
        deleteItem.Click += async (_, _) => await RemoveSavedAccountAsync(account.Login);
        menu.Items.Add(deleteItem);

        item.ContextMenu = menu;
        menu.PlacementTarget = item;
        menu.IsOpen = true;
        e.Handled = true;
    }

    private async Task RemoveSavedAccountAsync(string login)
    {
        var answer = MessageBox.Show(
            this,
            $"Удалить аккаунт «{login}» из MailRu Desktop?\n\n" +
            "Сохранённая локальная авторизация этого аккаунта будет удалена. " +
            "Сам почтовый ящик Mail.ru не удаляется.",
            "MailRu Desktop",
            MessageBoxButton.YesNo,
            MessageBoxImage.Question);

        if (answer != MessageBoxResult.Yes)
            return;

        var wasActive = string.Equals(
            login,
            _activeLogin,
            StringComparison.OrdinalIgnoreCase);

        if (!_authStore.Remove(login))
            return;

        if (!wasActive)
        {
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
