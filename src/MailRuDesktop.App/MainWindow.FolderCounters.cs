using System.Collections.ObjectModel;
using System.Windows.Threading;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly ObservableCollection<MailRuFolderSummary> _folderSummaries = [];
    private readonly DispatcherTimer _folderCountersTimer = new()
    {
        Interval = TimeSpan.FromSeconds(90)
    };
    private bool _folderCounterRefreshBusy;
    private long _folderCounterRevision;

    private void InitializeLiveFolderCounters()
    {
        FolderListBox.ItemsSource = _folderSummaries;
        MoveFolderComboBox.ItemsSource = _folderSummaries;
        _folderCountersTimer.Tick += async (_, _) => await RefreshFolderCountersAsync();
        _folderCountersTimer.Start();
        Closed += (_, _) => _folderCountersTimer.Stop();
    }

    private void ReplaceFolderSummaries(IReadOnlyList<MailRuFolderSummary> folders)
    {
        _updatingFolderSelection = true;
        try
        {
            _folderSummaries.Clear();
            foreach (var folder in folders)
                _folderSummaries.Add(folder);
            FolderListBox.SelectedItem =
                _folderSummaries.FirstOrDefault(folder => folder.Id == _currentFolderId);
            MoveFolderComboBox.SelectedItem =
                _folderSummaries.FirstOrDefault(folder => folder.Id == 500002) ??
                _folderSummaries.FirstOrDefault(folder => folder.Id != _currentFolderId);
        }
        finally { _updatingFolderSelection = false; }
    }

    private void ChangeFolderUnreadCount(int folderId, long delta)
    {
        if (delta == 0)
            return;
        var index = -1;
        for (var i = 0; i < _folderSummaries.Count; i++)
            if (_folderSummaries[i].Id == folderId) { index = i; break; }
        if (index == -1) return;

        var current = _folderSummaries[index];
        var next = Math.Max(0, current.MessagesUnread + delta);
        if (next == current.MessagesUnread) return;
        _folderCounterRevision++;
        ReplaceFolderCountAt(index, current with { MessagesUnread = next });
    }

    private void ReplaceFolderCountAt(int index, MailRuFolderSummary changed)
    {
        var selectedId = (FolderListBox.SelectedItem as MailRuFolderSummary)?.Id;
        var destinationId = (MoveFolderComboBox.SelectedItem as MailRuFolderSummary)?.Id;
        _updatingFolderSelection = true;
        try
        {
            // ObservableCollection sends a Replace notification only for the
            // affected row; the entire folder list is never reloaded.
            _folderSummaries[index] = changed;
            if (selectedId == changed.Id)
                FolderListBox.SelectedItem = changed;
            if (destinationId == changed.Id)
                MoveFolderComboBox.SelectedItem = changed;
        }
        finally { _updatingFolderSelection = false; }
    }

    private async Task RefreshFolderCountersAsync()
    {
        if (_folderCounterRefreshBusy || _loadingFolder ||
            !HasMailboxTransport() || string.IsNullOrWhiteSpace(_activeLogin) ||
            string.IsNullOrWhiteSpace(_accessToken) || _folderSummaries.Count == 0)
            return;

        _folderCounterRefreshBusy = true;
        var account = _activeLogin;
        var token = _accessToken;
        var folder = _currentFolderId;
        var revision = _folderCounterRevision;
        try
        {
            var raw = await _mailRu.GetFolderThreadsAsync(token, folder, 0, 1);
            if (!string.Equals(_activeLogin, account, StringComparison.OrdinalIgnoreCase) ||
                _currentFolderId != folder || _folderCounterRevision != revision)
                return;

            var snapshot = MailRuThreadStatusParser.Parse(raw, folder);
            if (snapshot.Folders.Count == 0)
                return;

            foreach (var update in snapshot.Folders)
            {
                var i = -1;
                for (var j = 0; j < _folderSummaries.Count; j++)
                    if (_folderSummaries[j].Id == update.Id) { i = j; break; }
                if (i < 0) continue;
                var previous = _folderSummaries[i];
                if (previous.MessagesUnread != update.MessagesUnread)
                    ReplaceFolderCountAt(i, previous with
                    {
                        MessagesUnread = update.MessagesUnread,
                        MessagesTotal = update.MessagesTotal
                    });
            }
        }
        catch (Exception ex)
        {
            // Failed background polling must not blank or reset known counters.
            DiagnosticLog.Write("folder_counters", ex.GetType().Name);
        }
        finally { _folderCounterRefreshBusy = false; }
    }
}
