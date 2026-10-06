package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.DatabaseCommandBase;
import ru.mail.data.cmd.database.SelectChangedMailsCommand;
import ru.mail.logic.cmd.MassOperationCmd;
import ru.mail.logic.cmd.MoveOperation;
import ru.mail.logic.cmd.SyncMailItemsCommand;
import ru.mail.logic.content.MailboxContext;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class MoveMessageBaseChunkbyMessageId<T extends Command<?, ? extends CommandStatus<?>>> extends MassOperationCmd<String, T> {
    private static final Log LOG = Log.getLog("MoveMessageBaseChunkbyMessage");
    private final MailboxContext mMailboxContext;
    private final List<T> mMoveCommands;

    public MoveMessageBaseChunkbyMessageId(Context context, MailboxContext mailboxContext, boolean z10) {
        super(context, mailboxContext, z10);
        this.mMoveCommands = new ArrayList();
        this.mMailboxContext = mailboxContext;
        LOG.d("MoveMessageBaseChunkbyMessageId");
    }

    public MailboxContext getMailboxContext() {
        return this.mMailboxContext;
    }

    @Override // ru.mail.serverapi.BaseDependentStatusCmd
    protected boolean isDependentCommand(Command<?, ?> command) {
        return this.mMoveCommands.contains(command);
    }

    protected abstract void onClearLocalChangesForMessages(String[] strArr);

    protected abstract T onCreateMainOperation(String... strArr);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.BaseDependentStatusCmd, ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @CheckForNull
    @Nullable
    protected <R> R onExecuteCommand(Command<?, R> command, Priority priority, ExecutorSelector executorSelector) {
        R r10 = (R) super.onExecuteCommand(command, priority, executorSelector);
        if ((command instanceof SelectChangedMailsCommand) && DatabaseCommandBase.statusOK(r10)) {
            SelectChangedMailsCommand.Result result = (SelectChangedMailsCommand.Result) ((AsyncDbHandler.CommonResponse) r10).getObj();
            Log log = LOG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("ChangedMails size=");
            sb2.append(result != null ? result.getMailsIds().size() : 0);
            log.d(sb2.toString());
            if (result != null && !result.getMailsIds().isEmpty()) {
                initCommands((String[]) result.getMailsIds().toArray(new String[0]));
                return r10;
            }
        } else if (this.mMoveCommands.contains(command) && ((r10 instanceof CommandStatus.OK) || !SyncMailItemsCommand.resultedWithRecoverableError(command))) {
            onClearLocalChangesForMessages(((MoveOperation) command).getMovedMessagesIds());
        }
        return r10;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.logic.cmd.MassOperationCmd
    public final T createMainOperation(String... strArr) {
        T t10 = (T) onCreateMainOperation(strArr);
        if (t10 instanceof MoveOperation) {
            this.mMoveCommands.add(t10);
            return t10;
        }
        throw new IllegalStateException("Main operation should implement " + MoveOperation.class);
    }
}
