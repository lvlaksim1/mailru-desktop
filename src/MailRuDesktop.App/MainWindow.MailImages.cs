namespace MailRuDesktop.App;

public partial class MainWindow
{
    private MailImageProxyHandler? _mailImageHandler;

    private void ConfigureMailImageProxy()
    {
        if (_mailImageHandler is not null || MessageWebView.CoreWebView2 is null)
            return;

        _mailImageHandler = new MailImageProxyHandler(
            MessageWebView.CoreWebView2, _mailRu,
            () => _accessToken,
            () => _activeLogin,
            () => _displayedConversation is not null &&
                  _displayedConversationBodies is { Count: > 0 }
                ? _displayedConversationBodies.Keys.ToArray()
                : _currentFullMessage is { } mail
                    ? new[] { mail.Id }
                    : Array.Empty<string>());
        _mailImageHandler.Attach();
    }
}
