package ru.mail.serverapi;

import android.content.Context;
import androidx.annotation.Nullable;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public abstract class BaseDependentStatusCmd extends AuthorizedCommandImpl {
    protected final DependenceRule mDependenceRule;

    /* JADX INFO: compiled from: ProGuard */
    public interface DependenceRule {
        void mainCommandPostExecuteAction(CommandStatus<?> commandStatus, AuthorizedCommandImpl authorizedCommandImpl);
    }

    /* JADX INFO: compiled from: ProGuard */
    protected static class MyDependenceRule implements DependenceRule {
        protected MyDependenceRule() {
        }

        private boolean noAuth(CommandStatus<?> commandStatus) {
            return (commandStatus instanceof NetworkCommandStatus.NO_AUTH) || (commandStatus instanceof NetworkCommandStatus.NO_AUTH_MULTIPLE) || (commandStatus instanceof NetworkCommandStatus.BAD_SESSION);
        }

        @Override // ru.mail.serverapi.BaseDependentStatusCmd.DependenceRule
        public void mainCommandPostExecuteAction(CommandStatus<?> commandStatus, AuthorizedCommandImpl authorizedCommandImpl) {
            if (NetworkCommand.statusOK(commandStatus) || noAuth(commandStatus)) {
                return;
            }
            authorizedCommandImpl.removeAllCommands();
        }
    }

    public BaseDependentStatusCmd(Context context, boolean z10, String str, FolderState folderState) {
        super(context, z10, str, folderState);
        DependenceRule customDependenceRule = getCustomDependenceRule();
        this.mDependenceRule = customDependenceRule == null ? getDefaultDependenceRule() : customDependenceRule;
    }

    private DependenceRule getDefaultDependenceRule() {
        return new MyDependenceRule();
    }

    public DependenceRule getCustomDependenceRule() {
        return null;
    }

    protected abstract boolean isDependentCommand(Command<?, ?> command);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if (isDependentCommand(command)) {
            this.mDependenceRule.mainCommandPostExecuteAction((CommandStatus) t10, this);
        }
        return t10;
    }

    @Override // ru.mail.serverapi.AuthorizedCommandImpl
    protected void onSetStatusFromExecutedCommand(CommandStatus<?> commandStatus) {
        if (isDependentCommand(getCurrentCommand())) {
            super.onSetStatusFromExecutedCommand(commandStatus);
        }
    }
}
