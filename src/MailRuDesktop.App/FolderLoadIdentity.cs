namespace MailRuDesktop.App;

// A response belongs to the session that started the folder load.
// Comparing only the optional account-switch argument is insufficient for
// ordinary folder loads, which pass no explicit generation.
internal readonly record struct FolderLoadIdentity(
    long AccountGeneration,
    string? Login,
    string? AccessToken)
{
    internal bool IsCurrent(long generation, string? login, string? accessToken) =>
        generation == AccountGeneration &&
        !string.IsNullOrWhiteSpace(Login) &&
        !string.IsNullOrWhiteSpace(AccessToken) &&
        string.Equals(login, Login, StringComparison.OrdinalIgnoreCase) &&
        string.Equals(accessToken, AccessToken, StringComparison.Ordinal);
}
