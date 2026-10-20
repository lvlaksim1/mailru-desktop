using Microsoft.Web.WebView2.Core;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MessageWindow
{
    // The detached reader is modal. Close it and let MainWindow open the
    // existing separate compose workspace for this exact member.
    private async void ConversationAction_WebMessageReceived(
        object? sender, CoreWebView2WebMessageReceivedEventArgs e)
    {
        if (!ConversationMessageActions.TryParse(e.WebMessageAsJson,
                out var action, out var messageId, out var revision) ||
            revision is not null ||
            !ConversationMessageActions.IsVerifiedMember(
                _loadedConversation, messageId))
            return;

        var conversation = _loadedConversation;
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

    private async void ConversationNavigationCompleted(
        object? sender, CoreWebView2NavigationCompletedEventArgs e)
    {
        if (!e.IsSuccess || _loadedConversation is null ||
            MessageWebView.CoreWebView2 is null) return;
        try
        {
            await MessageWebView.CoreWebView2.ExecuteScriptAsync(
                ReaderShellScripts.BindDetachedConversationActions());
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("detached_conversation_buttons", ex.GetType().Name);
        }
    }
}
