using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows;
using System.Windows.Controls.Primitives;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly HashSet<string> _selectedMailIds = new(StringComparer.Ordinal);
    private string? _activePreviewMailId;
    private string? _checkboxRangeAnchorId;
    private bool _clickOnMailRow;

    private MailRuMessageSummary? ActivePreviewMessage =>
        string.IsNullOrWhiteSpace(_activePreviewMailId)
            ? null
            : _currentMessages.FirstOrDefault(item =>
                string.Equals(item.Id, _activePreviewMailId, StringComparison.Ordinal));

    private IReadOnlyList<MailRuMessageSummary> ResolveActionMessages(bool notify = true)
    {
        var ids = MailTargetResolver.Resolve(_selectedMailIds, _activePreviewMailId);
        var result = ids.Select(id => _currentMessages.FirstOrDefault(message =>
                string.Equals(message.Id, id, StringComparison.Ordinal)))
            .Where(message => message is not null)
            .Cast<MailRuMessageSummary>()
            .ToArray();

        if (result.Length == 0 && notify)
            AppDialog.Info(this, "Выбор письма", "Выберите письмо для выполнения действия.");
        return result;
    }

    private void MailPreviewRow_PreviewMouseLeftButtonDown(
        object sender, MouseButtonEventArgs e)
    {
        var source = e.OriginalSource as DependencyObject;
        if (source is null)
            return;

        // Buttons, checkboxes, resizing grips and input fields retain their
        // ordinary actions; only a plain row click activates the preview.
        for (var cursor = source; cursor is not null &&
             !ReferenceEquals(cursor, MessagesGrid); cursor =
             cursor is Visual ? VisualTreeHelper.GetParent(cursor) :
             LogicalTreeHelper.GetParent(cursor))
        {
            if (cursor is ButtonBase or GridSplitter or TextBox)
                return;
        }

        if (ItemsControl.ContainerFromElement(MessagesGrid, source) is not ListBoxItem row ||
            row.DataContext is not MailRuMessageSummary message)
            return;

        e.Handled = true; // Prevent WPF from clearing all checked rows.
        _ = ActivateMailPreviewAsync(message);
    }

    private async Task ActivateMailPreviewAsync(MailRuMessageSummary message)
    {
        _activePreviewMailId = message.Id;
        DisplaySummary(message);
        UpdatePreviewSelectionFields(message);
        UpdateBulkToolbar();
        await LoadFullMessageAsync(message);
    }

    private void ClearActivePreviewId() => _activePreviewMailId = null;


    private void RememberSelectedMailIds()
    {
        _selectedMailIds.Clear();
        foreach (var message in MessagesGrid.SelectedItems.OfType<MailRuMessageSummary>())
            _selectedMailIds.Add(message.Id);
    }

    private void RestoreSelectedMailIds()
    {
        var selected = _visibleMessages
            .Where(message => _selectedMailIds.Contains(message.Id)).ToArray();

        MessagesGrid.SelectedItems.Clear();
        foreach (var item in selected)
            MessagesGrid.SelectedItems.Add(item);
    }

    private void RemoveConfirmedMailRows(IReadOnlyCollection<string> ids)
    {
        var removed = new HashSet<string>(ids, StringComparer.Ordinal);
        var oldFocused = _activePreviewMailId;
        _suppressMessageSelectionChanged = true;
        try
        {
            _currentMessages.RemoveAll(item => removed.Contains(item.Id));
            for (var i = _visibleMessages.Count - 1; i >= 0; i--)
                if (removed.Contains(_visibleMessages[i].Id))
                    _visibleMessages.RemoveAt(i);

            _selectedMailIds.ExceptWith(removed);
            RestoreSelectedMailIds();
        }
        finally
        {
            _suppressMessageSelectionChanged = false;
        }

        if (oldFocused is not null && removed.Contains(oldFocused))
        {
            ++_messageLoadGeneration;
            ClearSelectedMessage();
        }
        UpdateBulkToolbar();
    }
}
