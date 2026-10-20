using System.Windows;
using MailRuDesktop.Protocol;
using Microsoft.Web.WebView2.Core;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private static MailRuMessageSummary ConversationSourceSummary(
        MailRuMessageSummary selected, MailRuFullMessage full) =>
        selected with
        {
            Id = full.Id,
            Subject = full.Subject,
            Snippet = full.Text,
            SenderName = full.FromName,
            SenderEmail = full.FromEmail,
            DateUnix = full.DateUnix,
            HasAttachment = full.Attachments.Count > 0,
            ToAddresses = full.To,
            CcAddresses = full.Cc
        };

    private async void ConversationAction_FrameNavigationStarting(
        object? sender, CoreWebView2NavigationStartingEventArgs e)
    {
        // Cancel on the UI thread before any await. The internal action URL
        // must never be requested from the network, including when invalid.
        if (!ConversationActionNavigation.IsCommandUrl(e.Uri)) return;
        e.Cancel = true;
        if (!ConversationActionNavigation.TryParse(e.Uri,
                _conversationActionNonce, out var action, out var messageId) ||
            !ConversationMessageActions.IsVerifiedMember(
                _displayedConversation, messageId) ||
            ActivePreviewMessage is not { } selected ||
            string.IsNullOrWhiteSpace(_accessToken))
            return;
        var readerGeneration = _readerGeneration;

        var conversation = _displayedConversation!;
        var login = _activeLogin;
        var token = _accessToken;
        try
        {
            MailRuFullMessage? full = null;
            _displayedConversationBodies?.TryGetValue(messageId, out full);
            if (full is null)
                full = await _mailRu.GetFullMessageAsync(token, messageId, markRead: false);

            if (!string.Equals(full.Id, messageId, StringComparison.Ordinal) ||
                readerGeneration != _readerGeneration ||
                !string.Equals(_conversationActionNonce,
                    ConversationActionNavigation.ExtractNonce(e.Uri),
                    StringComparison.Ordinal) ||
                !ReferenceEquals(conversation, _displayedConversation) ||
                !string.Equals(login, _activeLogin, StringComparison.OrdinalIgnoreCase) ||
                !ConversationMessageActions.IsVerifiedMember(
                    _displayedConversation, messageId))
                return;

            var summary = ConversationSourceSummary(selected, full);
            if (action == "reply")
                OpenReplyComposeWindow(summary, full);
            else
                OpenForwardComposeWindow(summary, full);
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("conversation_action", ex.GetType().Name);
            FolderStatusText.Text = "Не удалось загрузить выбранное письмо переписки.";
        }
    }
}
