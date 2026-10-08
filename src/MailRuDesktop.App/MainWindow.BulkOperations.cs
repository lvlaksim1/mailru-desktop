using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private void BulkRowCheckBox_PreviewMouseLeftButtonDown(
        object sender, MouseButtonEventArgs e)
    {
        if (sender is not CheckBox checkbox)
            return;

        var container = ItemsControl.ContainerFromElement(MessagesGrid, checkbox) as ListBoxItem;
        if (container is null)
            return;

        // Checking an item is a selection action, not an invitation to open
        // and mark the message read. Keep all other checked rows selected.
        var old = _suppressMessageSelectionChanged;
        _suppressMessageSelectionChanged = true;
        try { container.IsSelected = !container.IsSelected; }
        finally { _suppressMessageSelectionChanged = old; }
        UpdateBulkToolbar();
        e.Handled = true;
    }

    private List<MailRuMessageSummary> SelectedForBulk() =>
        MessagesGrid.SelectedItems.Cast<MailRuMessageSummary>()
            .GroupBy(message => message.Id, StringComparer.Ordinal)
            .Select(group => group.First())
            .ToList();

    private bool CurrentFolderIsTrash =>
        _currentFolderId == 500002 ||
        (FolderListBox.SelectedItem is MailRuFolderSummary folder &&
         (folder.Type.Equals("trash", StringComparison.OrdinalIgnoreCase) ||
          folder.Name.Equals("Корзина", StringComparison.OrdinalIgnoreCase)));

    private bool CurrentFolderIsOutbox =>
        FolderListBox.SelectedItem is MailRuFolderSummary folder &&
        (folder.Type.Equals("outbox", StringComparison.OrdinalIgnoreCase) ||
         folder.Type.Equals("scheduled", StringComparison.OrdinalIgnoreCase) ||
         folder.Type.Equals("schedule", StringComparison.OrdinalIgnoreCase) ||
         folder.Name.Contains("Исходящие", StringComparison.OrdinalIgnoreCase));

    private void UpdateBulkToolbar()
    {
        var count = MessagesGrid.SelectedItems.Count;
        BulkSelectedCountText.Text = $"Выбрано: {count}";
        var trash = CurrentFolderIsTrash;
        BulkTrashButton.Visibility = trash ? Visibility.Collapsed : Visibility.Visible;
        BulkArchiveButton.Visibility = trash ? Visibility.Collapsed : Visibility.Visible;
        BulkDeletePermanentlyButton.Visibility = trash ? Visibility.Visible : Visibility.Collapsed;
        BulkSendNowButton.Visibility = CurrentFolderIsOutbox
            ? Visibility.Visible : Visibility.Collapsed;
        BulkSendNowButton.IsEnabled = false; // No proven safe send-now protocol yet.

        BulkTrashButton.IsEnabled = count > 0;
        BulkArchiveButton.IsEnabled = count > 0;
        BulkReadButton.IsEnabled = count > 0;
        BulkDeletePermanentlyButton.IsEnabled = count > 0;
    }

    private void MessagesGrid_PreviewKeyDown(object sender, KeyEventArgs e)
    {
        if (e.Key == Key.A && Keyboard.Modifiers == ModifierKeys.Control)
        {
            MessagesGrid.SelectAll();
            UpdateBulkToolbar();
            e.Handled = true;
        }
    }

    private void BulkTrashButton_Click(object sender, RoutedEventArgs e)
    {
        var selected = SelectedForBulk();
        if (selected.Count > 0 && !CurrentFolderIsTrash)
            _ = MoveMessagesAsync(selected.Select(item => item.Id).ToArray(),
                500002, $"Перемещение {selected.Count} писем в корзину");
    }

    private void BulkArchiveButton_Click(object sender, RoutedEventArgs e)
    {
        var selected = SelectedForBulk();
        if (selected.Count == 0 || CurrentFolderIsTrash)
            return;

        var archive = FolderListBox.Items.OfType<MailRuFolderSummary>()
            .FirstOrDefault(folder =>
                folder.Type.Equals("archive", StringComparison.OrdinalIgnoreCase) ||
                folder.Name.Contains("Архив", StringComparison.OrdinalIgnoreCase));
        _ = MoveMessagesAsync(selected.Select(item => item.Id).ToArray(),
            archive?.Id ?? 500010, $"Перемещение {selected.Count} писем в архив");
    }

    private async void BulkReadButton_Click(object sender, RoutedEventArgs e)
    {
        var selected = SelectedForBulk();
        if (selected.Count == 0 ||
            string.IsNullOrWhiteSpace(_accessToken) ||
            string.IsNullOrWhiteSpace(_activeLogin))
            return;

        BulkReadButton.IsEnabled = false;
        try
        {
            var result = await _mailRu.MarkMessagesReadBatchAsync(
                _accessToken, _activeLogin, selected, _currentFolderId);
            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Сервер отклонил групповую отметку.";
                return;
            }
            await LoadFolderAsync(_currentFolderId);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка групповой отметки прочитанного.";
            DiagnosticLog.Write("bulk_mark_read", ex.ToString());
        }
        finally
        {
            UpdateBulkToolbar();
        }
    }

    private async void BulkDeletePermanentlyButton_Click(object sender, RoutedEventArgs e)
    {
        var selected = SelectedForBulk();
        if (selected.Count == 0 || !CurrentFolderIsTrash ||
            string.IsNullOrWhiteSpace(_accessToken) ||
            string.IsNullOrWhiteSpace(_activeLogin))
            return;

        var count = selected.Count;
        if (!AppDialog.Confirm(this, "Окончательное удаление",
                $"Удалить {count} писем навсегда? Отменить это действие нельзя.",
                "Удалить навсегда", "Отмена"))
            return;

        BulkDeletePermanentlyButton.IsEnabled = false;
        try
        {
            var result = await _mailRu.RemoveMessagesAsync(
                _accessToken, _activeLogin, selected.Select(x => x.Id).ToArray());
            ResponseTextBox.Text = result.RawResponse;
            if (!result.Success)
            {
                FolderStatusText.Text = "Mail.ru отклонил окончательное удаление.";
                return;
            }
            await LoadFolderAsync(_currentFolderId);
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка окончательного удаления.";
            DiagnosticLog.Write("bulk_remove", ex.ToString());
        }
        finally
        {
            UpdateBulkToolbar();
        }
    }
}
