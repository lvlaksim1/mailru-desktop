namespace MailRuDesktop.App;

// A folder result may be published only by the account/generation that
// started its request. Do not compare access tokens: a successful folder
// read may refresh its own token before returning the result.
internal readonly record struct FolderLoadIdentity(
    long AccountGeneration,
    string? Login)
{
    internal bool IsCurrent(long generation, string? login) =>
        generation == AccountGeneration &&
        !string.IsNullOrWhiteSpace(Login) &&
        string.Equals(login, Login, StringComparison.OrdinalIgnoreCase);
}
