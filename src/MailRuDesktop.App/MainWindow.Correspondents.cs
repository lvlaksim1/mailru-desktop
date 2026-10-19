using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private static bool IsSentFolder(int id, IEnumerable<MailRuFolderSummary> folders)
    {
        var folder = folders.FirstOrDefault(x => x.Id == id);
        return folder is not null &&
            (folder.Type.Equals("sent", StringComparison.OrdinalIgnoreCase) ||
             folder.Type.Equals("sentbox", StringComparison.OrdinalIgnoreCase) ||
             folder.Name.Contains("Отправлен", StringComparison.OrdinalIgnoreCase));
    }

    private bool CurrentFolderIsSent() => IsSentFolder(_currentFolderId,
        (FolderListBox.ItemsSource as IEnumerable<MailRuFolderSummary>) ?? []);

    private MailRuMessageSummary WithCorrespondent(MailRuMessageSummary message,
        bool sentFolder, string? account)
    {
        _conversationIndex.TryGetValue(message.Id, out var group);
        return MailCounterparty.Resolve(message, account, sentFolder, group);
    }

    private MailRuMessageSummary EnrichFromFullMessage(MailRuMessageSummary row,
        MailRuFullMessage full)
    {
        var enriched = row with
        {
            SenderName = string.IsNullOrWhiteSpace(row.SenderName)
                ? full.FromName : row.SenderName,
            SenderEmail = string.IsNullOrWhiteSpace(row.SenderEmail)
                ? full.FromEmail : row.SenderEmail,
            ToAddresses = row.ToAddresses.Count > 0 ? row.ToAddresses : full.To,
            CcAddresses = row.CcAddresses.Count > 0 ? row.CcAddresses : full.Cc
        };
        return WithCorrespondent(enriched, CurrentFolderIsSent(), _activeLogin);
    }
}
