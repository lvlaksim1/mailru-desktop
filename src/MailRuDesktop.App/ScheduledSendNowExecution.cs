using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

internal enum ScheduledSendNowOutcome
{
    SentAndTrashed,
    PreparationFailed,
    ContextChanged,
    SendUnconfirmed,
    SentTrashRejected,
    SentTrashUnconfirmed
}

// The owner-approved order is prepare -> send one copy -> move its original
// into Trash. Never try another send if the response could already represent
// a delivered message. Moving the original is a separate best-effort stage.
internal static class ScheduledSendNowExecution
{
    internal static async Task<ScheduledSendNowOutcome> RunAsync(
        Func<Task<MailRuOutgoingMessage>> prepare,
        Func<MailRuOutgoingMessage, Task<bool>> sendPrepared,
        Func<Task<bool>> moveOriginalToTrash,
        Func<bool> isCurrent,
        Action? beforeSend = null)
    {
        ArgumentNullException.ThrowIfNull(prepare);
        ArgumentNullException.ThrowIfNull(sendPrepared);
        ArgumentNullException.ThrowIfNull(moveOriginalToTrash);
        ArgumentNullException.ThrowIfNull(isCurrent);

        if (!isCurrent())
            return ScheduledSendNowOutcome.ContextChanged;

        MailRuOutgoingMessage copy;
        try
        {
            copy = await prepare();
        }
        catch
        {
            return ScheduledSendNowOutcome.PreparationFailed;
        }

        if (!isCurrent())
            return ScheduledSendNowOutcome.ContextChanged;

        // Mark as attempted just before dispatch. Even a timeout can mean
        // the server received and sent this exact clone. No automatic retry.
        try
        {
            beforeSend?.Invoke();
            if (!await sendPrepared(copy))
                return ScheduledSendNowOutcome.SendUnconfirmed;
        }
        catch
        {
            return ScheduledSendNowOutcome.SendUnconfirmed;
        }

        // Once the clone was confirmed sent, try moving the *original* using
        // the captured account token even if the visible account switched.
        // A failure here must not cause the clone to be sent a second time.
        try
        {
            return await moveOriginalToTrash()
                ? ScheduledSendNowOutcome.SentAndTrashed
                : ScheduledSendNowOutcome.SentTrashRejected;
        }
        catch
        {
            return ScheduledSendNowOutcome.SentTrashUnconfirmed;
        }
    }
}
