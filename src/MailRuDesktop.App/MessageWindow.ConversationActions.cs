using Microsoft.Web.WebView2.Core;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MessageWindow
{
    // The detached reader is modal. Close it and let MainWindow open the
    // existing separate compose workspace for this exact member.
    private async void ConversationAction_NavigationStarting(
        object? sender, CoreWebView2NavigationStartingEventArgs e)
    {
        if (!ConversationActionNavigation.IsCommandUrl(e.Uri)) return;
        e.Cancel = true;
        if (!ConversationActionNavigation.TryParse(e.Uri,
                _conversationActionNonce, out var action, out var messageId) ||
            !ConversationMessageActions.IsVerifiedMember(
                _loadedConversation, messageId))
            return;

        var conversation = _loadedConversation;
        var nonce = _conversationActionNonce;
        try
        {
            MailRuFullMessage? full = null;
            _loadedConversationBodies?.TryGetValue(messageId, out full);
            if (full is null && !string.IsNullOrWhiteSpace(_accessToken))
                full = await _mailRu.GetFullMessageAsync(
                    _accessToken, messageId, markRead: false);

            if (full is null ||
                !string.Equals(full.Id, messageId, StringComparison.Ordinal) ||
                !ReferenceEquals(conversation, _loadedConversation) ||
                nonce != _conversationActionNonce ||
                !ConversationMessageActions.IsVerifiedMember(
                    _loadedConversation, messageId))
                return;

            _requestedActionSource = full;
            if (action == "reply") ReplyRequested = true;
            else ForwardRequested = true;
            Close();
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("detached_conversation_action", ex.GetType().Name);
            StatusText.Text = "Не удалось загрузить выбранное письмо переписки.";
        }
    }
}
