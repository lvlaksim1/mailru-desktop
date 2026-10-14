namespace MailRuDesktop.App;

// UI-thread-only state for explicit folder choices made during loading.
// Never apply a choice originating from another account.
internal sealed class PendingFolderSelection
{
    private int? _folderId;
    private long _accountGeneration;
    private string? _accountLogin;

    internal void Request(int folderId, long accountGeneration, string? accountLogin)
    {
        _folderId = folderId;
        _accountGeneration = accountGeneration;
        _accountLogin = accountLogin;
    }

    internal bool Supersedes(int loadingFolderId, long accountGeneration, string? accountLogin) =>
        _folderId is int requested &&
        requested != loadingFolderId &&
        _accountGeneration == accountGeneration &&
        string.Equals(_accountLogin, accountLogin, StringComparison.OrdinalIgnoreCase);

    internal int? Take(long accountGeneration, string? accountLogin)
    {
        if (_folderId is not int requested)
            return null;
        if (_accountGeneration > accountGeneration)
            return null;
        var matches = _accountGeneration == accountGeneration &&
            string.Equals(_accountLogin, accountLogin, StringComparison.OrdinalIgnoreCase);
        _folderId = null;
        _accountLogin = null;
        return matches ? requested : null;
    }
}
