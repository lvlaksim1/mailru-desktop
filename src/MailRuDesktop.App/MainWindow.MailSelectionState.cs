using System.Windows.Controls;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly HashSet<string> _selectedMailIds = new(StringComparer.Ordinal);

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
        var oldFocused = (MessagesGrid.SelectedItem as MailRuMessageSummary)?.Id;
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
