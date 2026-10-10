using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    // RAM-only cache: no mail content or credentials are written to disk.
    private MailRuConversation? _displayedConversation;
    private Dictionary<string, MailRuFullMessage>? _displayedConversationBodies;
    private string? _displayedConversationSelectedId;
    private readonly Dictionary<string, MailRuFullMessage> _conversationBodyCache =
        new(StringComparer.Ordinal);

    private async Task<bool> ShowConversationIfAvailableAsync(
        MailRuMessageSummary selected, MailRuFullMessage current,
        long loadGeneration, CancellationToken cancellationToken)
    {
        if (_serverSearchMode ||
            !_conversationIndex.TryGetValue(selected.Id, out var conversation) ||
            (conversation.VerifiedCount ?? 0) <= 1)
            return false;

        if (conversation.Members.Count < (conversation.VerifiedCount ?? 0) &&
            !string.IsNullOrWhiteSpace(conversation.ThreadId) &&
            !string.IsNullOrWhiteSpace(_accessToken))
        {
            try
            {
                var raw = await _mailRu.GetThreadMessagesAsync(
                    _accessToken, conversation.ThreadId,
                    limit: Math.Clamp(conversation.VerifiedCount ?? 1, 1, 200),
                    cancellationToken: cancellationToken);
                if (loadGeneration != _messageLoadGeneration ||
                    !string.Equals(_activePreviewMailId, selected.Id,
                        StringComparison.Ordinal))
                    return true;
                var expanded = MailRuConversationParser.ExpandFromThreadDetail(
                    conversation, raw);
                if (expanded.Members.Count > conversation.Members.Count)
                {
                    conversation = expanded;
                    foreach (var member in expanded.Members)
                        _conversationIndex = new Dictionary<string, MailRuConversation>(
                            _conversationIndex, StringComparer.Ordinal)
                        {
                            [member.Id] = expanded
                        };
                }
                PushDiagnostics.Record("MAIL", "THREAD_DETAIL_MEMBERS",
                    conversation.Members.Count);
            }
            catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
            {
                throw;
            }
            catch (Exception ex)
            {
                DiagnosticLog.Write("thread_detail", ex.GetType().Name);
            }
        }

        var keyPrefix = (_activeLogin ?? "") + "|";
        var bodies = new Dictionary<string, MailRuFullMessage>(StringComparer.Ordinal)
        {
            [selected.Id] = current
        };
        _conversationBodyCache[keyPrefix + selected.Id] = current;

        // Read separately represented messages using their exact IDs, and do
        // not set mark_read for historical correspondence. Avoid unbounded
        // request bursts on threads with hundreds of messages; other headings
        // stay available with an honest preview rather than fake body content.
        var recent = conversation.Members
            .Where(x => x.Id != selected.Id)
            .OrderByDescending(x => x.DateUnix ?? long.MinValue)
            .Take(18).ToArray();
        foreach (var member in recent)
        {
            cancellationToken.ThrowIfCancellationRequested();
            if (loadGeneration != _messageLoadGeneration ||
                !string.Equals(_activePreviewMailId, selected.Id,
                    StringComparison.Ordinal))
                return true;

            if (_conversationBodyCache.TryGetValue(keyPrefix + member.Id, out var cached))
            {
                bodies[member.Id] = cached;
                continue;
            }

            try
            {
                var received = await _mailRu.GetFullMessageAsync(
                    _accessToken!, member.Id, markRead: false,
                    cancellationToken: cancellationToken);
                if (!string.Equals(received.Id, member.Id, StringComparison.Ordinal))
                {
                    DiagnosticLog.Write("conversation_body", "ID_MISMATCH");
                    continue;
                }
                bodies[member.Id] = received;
                if (_conversationBodyCache.Count >= 90)
                    _conversationBodyCache.Clear();
                _conversationBodyCache[keyPrefix + member.Id] = received;
            }
            catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
            {
                throw;
            }
            catch (Exception error)
            {
                // A moved/deleted letter does not prevent opening the rest.
                DiagnosticLog.Write("conversation_body",
                    error.GetType().Name);
            }
        }

        if (loadGeneration != _messageLoadGeneration ||
            !string.Equals(_activePreviewMailId, selected.Id,
                StringComparison.Ordinal))
            return true;

        // The permanent reader shell gets one staged document: no extra
        // WebView2 instance, no XAML overlay and no half-painted history.
        _readerWaitingForFullMessage = false;
        _displayedConversation = conversation;
        _displayedConversationBodies = bodies;
        _displayedConversationSelectedId = selected.Id;
        ShowTrustedConversationHtml();
        return true;
    }
    private void ShowTrustedConversationHtml()
    {
        if (_displayedConversation is null ||
            _displayedConversationBodies is null ||
            _displayedConversationSelectedId is null)
            return;
        _currentPreparedHtml = MailRuConversationHtml.Render(
            _displayedConversation, _displayedConversationSelectedId,
            _displayedConversationBodies,
            ThemeManager.ReaderBackgroundHtml,
            ThemeManager.ReaderForegroundHtml,
            ThemeManager.ReaderMutedHtml);
        if (_readerReady && MessageWebView.CoreWebView2 is not null)
            _ = StageReaderDocumentAsync(_readerGeneration, _currentPreparedHtml);
    }
}
