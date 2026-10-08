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

        var message = container.DataContext as MailRuMessageSummary;
        if (message is null)
            return;

        // Shift-click marks the inclusive visible range. Ctrl is not needed:
        // each plain checkbox click toggles one arbitrary message.
        var old = _suppressMessageSelectionChanged;
        _suppressMessageSelectionChanged = true;
        try
        {
            if ((Keyboard.Modifiers & ModifierKeys.Shift) != 0 &&
                _checkboxRangeAnchorId is { } anchorId)
            {
                var rows = _visibleMessages.ToArray();
                var from = Array.FindIndex(rows, m => m.Id == anchorId);
                var to = Array.FindIndex(rows, m => m.Id == message.Id);
                if (from >= 0 && to >= 0)
                {
                    for (var i = Math.Min(from, to); i <= Math.Max(from, to); i++)
                        if (!MessagesGrid.SelectedItems.Contains(rows[i]))
                            MessagesGrid.SelectedItems.Add(rows[i]);
                }
                else
                    container.IsSelected = true;
            }
            else
                container.IsSelected = !container.IsSelected;
        }
        finally
        {
            _suppressMessageSelectionChanged = old;
        }

        _checkboxRangeAnchorId = message.Id;
        RememberSelectedMailIds();
        UpdateBulkToolbar();
        e.Handled = true;
    }

    private List<MailRuMessageSummary> SelectedForBulk() =>
        ResolveActionMessages().ToList();

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
        var count = _selectedMailIds.Count;
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

        BulkTrashButton.IsEnabled = !_bulkOperationInProgress;
        BulkArchiveButton.IsEnabled = !_bulkOperationInProgress;
        BulkReadButton.IsEnabled = !_bulkOperationInProgress;
        BulkDeletePermanentlyButton.IsEnabled = !_bulkOperationInProgress;
    }

    private void MessagesGrid_PreviewKeyDown(object sender, KeyEventArgs e)
    {
        // Keyboard Ctrl-based selection is deliberately not part of the model.
        if ((Keyboard.Modifiers & ModifierKeys.Control) != 0 &&
            e.Key is Key.A or Key.Space)
            e.Handled = true;
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

        var runAccount = _activeLogin;
        var runFolder = _currentFolderId;
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
            if (!string.Equals(_activeLogin, runAccount, StringComparison.OrdinalIgnoreCase)
                || _currentFolderId != runFolder)
                return;
            var changed = selected.Where(item => item.Unread).ToArray();
            foreach (var item in changed)
                ReplaceMessage(item, item with { Unread = false });
            if (_currentFolderId == 0 && changed.Length > 0)
                AdjustActiveInboxUnread(-changed.Length);
            FolderStatusText.Text = $"Отмечено прочитанными: {changed.Length}.";
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

        var removedFromAccount = _activeLogin;
        var removedFromFolder = _currentFolderId;
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
            if (!string.Equals(_activeLogin, removedFromAccount, StringComparison.OrdinalIgnoreCase)
                || _currentFolderId != removedFromFolder)
                return;
            RemoveConfirmedMailRows(selected.Select(x => x.Id).ToArray());
            FolderStatusText.Text = $"Удалено: {selected.Count}.";
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
