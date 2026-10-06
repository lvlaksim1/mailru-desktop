package ru.mail.data.cmd.server;

import android.content.Context;
import javax.annotation.Nullable;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.serverapi.BaseDependentStatusCmd;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class SimpleDependentStatusCmd<T extends Command<?, ? extends CommandStatus<?>>> extends BaseDependentStatusCmd {
    private static final Log LOG = Log.getLog("SimpleDependentStatusCmd");
    private final Command<?, ? extends CommandStatus<?>> mCommand;

    public SimpleDependentStatusCmd(Context context, MailboxContext mailboxContext, T t10) {
        super(context, false, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
        addCommand(t10);
        this.mCommand = t10;
    }

    private Object getInternalCmdResult(Object obj) {
        Command<?, ? extends CommandStatus<?>> command = this.mCommand;
        if (command != null && command.isCancelled()) {
            return new CommandStatus.CANCELLED();
        }
        Command<?, ? extends CommandStatus<?>> command2 = this.mCommand;
        return (command2 != null && (command2.getResult() instanceof CommandStatus.NOT_EXECUTED) && (obj instanceof MailCommandStatus.EMPTY_RESULT_ERROR)) ? new CommandStatus.NOT_EXECUTED() : obj;
    }

    public Command<?, ? extends CommandStatus<?>> getCommand() {
        return this.mCommand;
    }

    @Override // ru.mail.serverapi.BaseDependentStatusCmd
    protected boolean isDependentCommand(Command<?, ?> command) {
        return this.mCommand.getClass().isAssignableFrom(command.getClass());
    }

    @Override // ru.mail.serverapi.BaseDependentStatusCmd, ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        Log log = LOG;
        log.d("cmd : " + command);
        log.d("result : " + t10);
        return t10;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.Command
    public synchronized void setResult(Object obj) {
        super.setResult(getInternalCmdResult(obj));
    }
}
