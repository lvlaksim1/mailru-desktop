using System.Collections.ObjectModel;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

/// <summary>Reconcile rows without issuing a collection Reset or blanking the list.</summary>
internal static class MailListReconciliation
{
    internal static void Apply(ObservableCollection<MailRuMessageSummary> current,
        IReadOnlyList<MailRuMessageSummary> next)
    {
        var ids = new HashSet<string>(next.Select(x => x.Id), StringComparer.Ordinal);
        for (var i = current.Count - 1; i >= 0; i--)
            if (!ids.Contains(current[i].Id)) current.RemoveAt(i);

        for (var i = 0; i < next.Count; i++)
        {
            var desired = next[i];
            if (i < current.Count && current[i].Id == desired.Id)
            {
                if (!Equivalent(current[i], desired)) current[i] = desired;
                continue;
            }

            var existing = -1;
            for (var j = i + 1; j < current.Count; j++)
                if (current[j].Id == desired.Id)
                {
                    existing = j;
                    break;
                }
            if (existing >= 0)
            {
                current.Move(existing, i);
                if (!Equivalent(current[i], desired)) current[i] = desired;
            }
            else current.Insert(i, desired);
        }
        while (current.Count > next.Count) current.RemoveAt(current.Count - 1);
    }

    private static bool Equivalent(MailRuMessageSummary a, MailRuMessageSummary b) =>
        a.Id == b.Id && a.Subject == b.Subject && a.Snippet == b.Snippet &&
        a.SenderName == b.SenderName && a.SenderEmail == b.SenderEmail &&
        a.DateUnix == b.DateUnix && a.SizeBytes == b.SizeBytes &&
        a.FolderId == b.FolderId && a.Unread == b.Unread &&
        a.Flagged == b.Flagged && a.Pinned == b.Pinned &&
        a.HasAttachment == b.HasAttachment &&
        a.CorrespondentText == b.CorrespondentText &&
        a.CorrespondentEmail == b.CorrespondentEmail &&
        a.ToAddresses.SequenceEqual(b.ToAddresses, StringComparer.Ordinal) &&
        a.CcAddresses.SequenceEqual(b.CcAddresses, StringComparer.Ordinal);
}
