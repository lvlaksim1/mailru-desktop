using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

/// <summary>
/// Collects server search pages without re-filtering by subject/snippet.
/// Offsets advance by the number of records delivered by the server, not
/// the number of distinct message IDs displayed in the UI.
/// </summary>
internal sealed class MailServerSearchPages
{
    public const int PageSize = 200;
    public const int MaxPages = 100;

    private readonly HashSet<string> _seenIds = new(StringComparer.Ordinal);
    private readonly List<MailRuMessageSummary> _messages = [];

    public IReadOnlyList<MailRuMessageSummary> Messages => _messages;
    public int NextOffset { get; private set; }
    public int PagesFetched { get; private set; }
    public int? ReportedTotal { get; private set; }
    public string Source { get; private set; } = string.Empty;
    public bool HasMore { get; private set; }

    public void AddPage(MailRuSearchResult page)
    {
        if (PagesFetched >= MaxPages)
            throw new InvalidOperationException("Search page limit exceeded.");

        Source = page.Source;
        PagesFetched++;
        var received = page.Messages.Count;
        NextOffset = checked(NextOffset + received);
        if (page.HasReportedTotal)
            ReportedTotal = Math.Max(ReportedTotal ?? 0, page.Found);

        var newlyAdded = 0;
        foreach (var message in page.Messages)
            if (_seenIds.Add(message.Id))
            {
                _messages.Add(message);
                newlyAdded++;
            }

        // Stop if the server has nothing new, even when its reported count
        // is larger. Avoid endless repeated or empty pages.
        HasMore = PagesFetched < MaxPages && received > 0 && newlyAdded > 0 &&
                  (ReportedTotal is int total
                      ? NextOffset < total
                      : received == PageSize);
    }

    public string GetProgressText()
    {
        var progress = ReportedTotal is int total
            ? $"найдено {total}, загружено {_messages.Count}"
            : $"загружено {_messages.Count} (общее число сервером не указано)";
        if (!HasMore && ReportedTotal is int expected && _messages.Count < expected)
            progress += " · результаты получены не полностью";
        if (HasMore)
            progress += " · загрузка следующих результатов...";
        return $"Серверный поиск: {progress} · {Source}";
    }
}
