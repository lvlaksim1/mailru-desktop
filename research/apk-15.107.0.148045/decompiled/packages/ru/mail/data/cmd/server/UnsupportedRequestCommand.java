package ru.mail.data.cmd.server;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class UnsupportedRequestCommand extends Command<Void, CommandStatus> {
    public UnsupportedRequestCommand() {
        super(null);
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    @Nullable
    public CommandStatus onExecute(ExecutorSelector executorSelector) {
        return new CommandStatus.UNSUPPORTED_OPERATION();
    }
}
