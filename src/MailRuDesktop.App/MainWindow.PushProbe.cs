using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private PushProbeWindow? _pushProbeWindow;

    private void OpenPushProbeButton_Click(object sender, RoutedEventArgs e)
    {
        if (_pushProbeWindow is { IsVisible: true })
        {
            _pushProbeWindow.Activate();
            return;
        }
        var window = new PushProbeWindow(_authStore, _activeLogin, OnPushNewMail);
        window.Owner = this;
        window.Closed += (_, _) => _pushProbeWindow = null;
        _pushProbeWindow = window;
        window.Show();
    }

    private void OnPushNewMail(string login)
    {
        if (!string.Equals(login, _activeLogin, StringComparison.OrdinalIgnoreCase))
            return;
        // An event from the server is the trigger. The existing folder load
        // is executed ONCE per confirmed event, never by a polling timer.
        _ = RefreshAfterPushAsync(login);
    }

    private async Task RefreshAfterPushAsync(string login)
    {
        try
        {
            if (string.Equals(login, _activeLogin, StringComparison.OrdinalIgnoreCase))
            {
                await LoadFolderAsync(_currentFolderId);
                await RefreshAccountUnreadCountsAsync();
            }
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("push_event_folder_refresh", ex.GetType().Name);
        }
    }
}
