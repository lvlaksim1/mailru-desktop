package ru.mail.mailbox.cmd;

import java.util.concurrent.Executors;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public class DefaultExecutorSelector implements ExecutorSelector {
    private static final CommandExecutor EXECUTOR = new SimpleCommandExecutor(Executors.newCachedThreadPool());

    @Override // ru.mail.mailbox.cmd.ExecutorSelector
    public CommandExecutor getCommandGroupExecutor() {
        return EXECUTOR;
    }

    @Override // ru.mail.mailbox.cmd.ExecutorSelector
    public CommandExecutor getSingleCommandExecutor(String str) {
        return EXECUTOR;
    }
}
