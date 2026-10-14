using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

internal enum ScheduledSendNowOutcome
{
    Sent,
    PreparationFailed,
    OriginalNotDeleted,
    RemovalUncertain,
    ContextChanged,
    SendUnconfirmed
}

// One-way operation: prepare -> delete -> verify -> send. Never retry a send
// after an unknown response, and never send while the schedule may still exist.
internal static class ScheduledSendNowExecution
{
    internal static async Task<ScheduledSendNowOutcome> RunAsync(
        Func<Task<MailRuOutgoingMessage>> prepare,
        Func<Task<bool>> deleteOriginal,
        Func<Task<bool>> verifyDeletion,
        Func<MailRuOutgoingMessage, Task<bool>> sendPrepared,
        Func<bool> isCurrent,
        Action<ScheduledSendNowOutcome>? stage = null)
    {
        ArgumentNullException.ThrowIfNull(prepare);
        ArgumentNullException.ThrowIfNull(deleteOriginal);
        ArgumentNullException.ThrowIfNull(verifyDeletion);
        ArgumentNullException.ThrowIfNull(sendPrepared);
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

        bool deleted;
        try
        {
            deleted = await deleteOriginal();
        }
        catch
        {
            // Server may have deleted the schedule despite a lost response.
            return ScheduledSendNowOutcome.RemovalUncertain;
        }

        if (!deleted)
            return ScheduledSendNowOutcome.OriginalNotDeleted;

        bool absent;
        try
        {
            absent = await verifyDeletion();
        }
        catch
        {
            return ScheduledSendNowOutcome.RemovalUncertain;
        }
        if (!absent)
            return ScheduledSendNowOutcome.RemovalUncertain;
        if (!isCurrent())
            return ScheduledSendNowOutcome.ContextChanged;

        // No automatic retry after reaching this point. An HTTP failure may
        // conceal an already delivered message.
        stage?.Invoke(ScheduledSendNowOutcome.SendUnconfirmed);
        try
        {
            return await sendPrepared(copy)
                ? ScheduledSendNowOutcome.Sent
                : ScheduledSendNowOutcome.SendUnconfirmed;
        }
        catch
        {
            return ScheduledSendNowOutcome.SendUnconfirmed;
        }
    }
}
