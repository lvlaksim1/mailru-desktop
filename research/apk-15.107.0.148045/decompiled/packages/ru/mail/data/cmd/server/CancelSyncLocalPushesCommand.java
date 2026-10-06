package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import ru.mail.locator.Locator;
import ru.mail.logic.sync.PollLocalPushesCommand;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.march.internal.work.WorkScheduler;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class CancelSyncLocalPushesCommand extends SyncControlCommand<Params, CommandStatus<?>> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {
        public boolean equals(Object obj) {
            if (this != obj) {
                return obj != null && getClass() == obj.getClass();
            }
            return true;
        }

        public int hashCode() {
            return PollLocalPushesCommand.AUTHORITY.hashCode();
        }
    }

    public CancelSyncLocalPushesCommand(Context context) {
        super(new Params(), context);
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("IPC");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        try {
            ((WorkScheduler) Locator.locate(this.mContext, WorkScheduler.class)).cancelById(ContentProvider.obtainProvider(PollLocalPushesCommand.AUTHORITY).getWorkUniqueId());
            return new CommandStatus.OK();
        } catch (IllegalArgumentException e10) {
            return new CommandStatus.ERROR(e10);
        }
    }
}
