package ru.mail.mailbox.cmd;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class SimpleCommandExecutor implements CommandExecutor {
    private final ExecutorService mExecutorService;

    public SimpleCommandExecutor(ExecutorService executorService) {
        this.mExecutorService = executorService;
    }

    @Override // ru.mail.mailbox.cmd.CommandExecutor
    public <R> ObservableFuture<R> execute(ReusePolicy reusePolicy, Priority priority, Callable<R> callable) {
        ObservableFutureTask observableFutureTask = new ObservableFutureTask(callable);
        this.mExecutorService.execute(observableFutureTask);
        return observableFutureTask;
    }

    @Override // ru.mail.mailbox.cmd.CommandExecutor
    public boolean hasActiveFutures() {
        return false;
    }

    @Override // ru.mail.mailbox.cmd.CommandExecutor
    public void cancelAllFutures() {
    }

    @Override // ru.mail.mailbox.cmd.CommandExecutor
    public void resume() {
    }

    @Override // ru.mail.mailbox.cmd.CommandExecutor
    public void shutdown() {
    }

    @Override // ru.mail.mailbox.cmd.CommandExecutor
    public void awaitTermination(long j10, TimeUnit timeUnit) throws InterruptedException {
    }
}
