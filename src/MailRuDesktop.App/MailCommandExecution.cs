using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

/// <summary>
/// Shared confirmation boundary for commands which change mailbox state.
/// The result of a request issued for an older account/folder cannot mutate
/// the currently displayed mailbox. Failures never apply local changes.
/// </summary>
internal enum MailCommandOutcome
{
    Confirmed,
    Rejected,
    Superseded
}

internal static class MailCommandExecution
{
    public static async Task<MailCommandOutcome> RunAsync(
        Func<Task<MailRuCommandResult>> send,
        Func<bool> isCurrent,
        Action<string> showResponse,
        Action applyConfirmed)
    {
        ArgumentNullException.ThrowIfNull(send);
        ArgumentNullException.ThrowIfNull(isCurrent);
        ArgumentNullException.ThrowIfNull(showResponse);
        ArgumentNullException.ThrowIfNull(applyConfirmed);

        var response = await send();
        if (!isCurrent())
            return MailCommandOutcome.Superseded;

        showResponse(response.RawResponse);
        if (!response.Success)
            return MailCommandOutcome.Rejected;

        applyConfirmed();
        return MailCommandOutcome.Confirmed;
    }
}
