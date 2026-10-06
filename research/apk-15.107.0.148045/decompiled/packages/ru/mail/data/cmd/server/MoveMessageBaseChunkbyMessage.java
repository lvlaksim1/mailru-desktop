package ru.mail.data.cmd.server;

import android.content.Context;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.DatabaseCommandBase;
import ru.mail.data.cmd.database.SelectChangedMailsCommand;
import ru.mail.data.entities.MailMessage;
import ru.mail.logic.cmd.MassOperationCmd;
import ru.mail.logic.content.MailboxContext;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class MoveMessageBaseChunkbyMessage<T extends Command<?, ? extends CommandStatus<?>>> extends MassOperationCmd<MailMessage, T> {
    private static final Log LOG = Log.getLog("MoveMessageBaseChunkbyMessage");

    public MoveMessageBaseChunkbyMessage(Context context, MailboxContext mailboxContext, boolean z10) {
        super(context, mailboxContext, z10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.BaseDependentStatusCmd, ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @CheckForNull
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        SelectChangedMailsCommand.Result result;
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if ((command instanceof SelectChangedMailsCommand) && DatabaseCommandBase.statusOK(t10) && (result = (SelectChangedMailsCommand.Result) ((AsyncDbHandler.CommonResponse) t10).getObj()) != null && result.getMessages().size() > 0) {
            initCommands((MailMessage[]) result.getMessages().toArray(new MailMessage[0]));
        }
        return t10;
    }
}
