using System.Windows;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private bool _bulkOperationInProgress;
    private void BulkRowCheckBox_PreviewMouseLeftButtonDown(
        object sender, MouseButtonEventArgs e)
    {
        var source = e.OriginalSource as DependencyObject;
        CheckBox? checkbox = null;
        while (source is not null && !ReferenceEquals(source, MessagesGrid))
        {
            if (source is CheckBox candidate &&
                candidate.Name == "BulkSelectCheckBox")
            {
                checkbox = candidate;
                break;
            }
            source = source is Visual
                ? VisualTreeHelper.GetParent(source)
                : LogicalTreeHelper.GetParent(source);
        }

        if (checkbox is null)
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
        PreviewSendNowButton.Visibility = CurrentFolderIsOutbox
            ? Visibility.Visible : Visibility.Collapsed;
        PreviewSendNowButton.IsEnabled = false;

        BulkTrashButton.IsEnabled = count > 0 && !_bulkOperationInProgress;
        BulkArchiveButton.IsEnabled = count > 0 && !_bulkOperationInProgress;
        BulkReadButton.IsEnabled = count > 0 && !_bulkOperationInProgress;
        BulkDeletePermanentlyButton.IsEnabled = count > 0 && !_bulkOperationInProgress;
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

    private async void BulkTrashButton_Click(object sender, RoutedEventArgs e)
    {
        var selected = SelectedForBulk();
        if (selected.Count == 0 || CurrentFolderIsTrash || _bulkOperationInProgress)
            return;

        await ExecuteBulkMoveAsync(selected, 500002, "Перемещение писем в корзину");
    }

    private async void BulkArchiveButton_Click(object sender, RoutedEventArgs e)
    {
        var selected = SelectedForBulk();
        if (selected.Count == 0 || CurrentFolderIsTrash || _bulkOperationInProgress)
            return;

        var archive = FolderListBox.Items.OfType<MailRuFolderSummary>()
            .FirstOrDefault(folder =>
                folder.Type.Equals("archive", StringComparison.OrdinalIgnoreCase) ||
                folder.Name.Contains("Архив", StringComparison.OrdinalIgnoreCase));
        await ExecuteBulkMoveAsync(selected, archive?.Id ?? 500010,
            "Перемещение писем в архив");
    }

    private async Task ExecuteBulkMoveAsync(
        IReadOnlyList<MailRuMessageSummary> selected, int destination, string description)
    {
        _bulkOperationInProgress = true;
        UpdateBulkToolbar();
        try
        {
            await MoveMessagesAsync(selected.Select(item => item.Id).ToArray(),
                destination, $"{description}: {selected.Count}");
        }
        finally
        {
            _bulkOperationInProgress = false;
            UpdateBulkToolbar();
        }
    }

    private async void BulkReadButton_Click(object sender, RoutedEventArgs e)
    {
        var selected = SelectedForBulk();
        if (selected.Count == 0 || _bulkOperationInProgress ||
            string.IsNullOrWhiteSpace(_accessToken) ||
            string.IsNullOrWhiteSpace(_activeLogin))
            return;

        _bulkOperationInProgress = true;
        UpdateBulkToolbar();
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
            _bulkOperationInProgress = false;
            UpdateBulkToolbar();
        }
    }

    private async void BulkDeletePermanentlyButton_Click(object sender, RoutedEventArgs e)
    {
        var selected = SelectedForBulk();
        if (selected.Count == 0 || _bulkOperationInProgress || !CurrentFolderIsTrash ||
            string.IsNullOrWhiteSpace(_accessToken) ||
            string.IsNullOrWhiteSpace(_activeLogin))
            return;

        var count = selected.Count;
        if (!AppDialog.Confirm(this, "Окончательное удаление",
                $"Удалить {count} писем навсегда? Отменить это действие нельзя.",
                "Удалить навсегда", "Отмена"))
            return;

        _bulkOperationInProgress = true;
        UpdateBulkToolbar();
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
            _bulkOperationInProgress = false;
            UpdateBulkToolbar();
        }
    }
}
