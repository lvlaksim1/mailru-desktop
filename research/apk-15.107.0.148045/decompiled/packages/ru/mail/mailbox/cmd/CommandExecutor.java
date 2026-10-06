package ru.mail.mailbox.cmd;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public interface CommandExecutor {
    void awaitTermination(long j10, TimeUnit timeUnit) throws InterruptedException;

    void cancelAllFutures();

    <R> ObservableFuture<R> execute(ReusePolicy reusePolicy, Priority priority, Callable<R> callable);

    boolean hasActiveFutures();

    void resume();

    void shutdown();
}
