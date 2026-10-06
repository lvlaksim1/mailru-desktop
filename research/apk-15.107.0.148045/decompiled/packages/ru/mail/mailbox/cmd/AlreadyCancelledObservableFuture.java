package ru.mail.mailbox.cmd;

import java.util.concurrent.TimeUnit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public class AlreadyCancelledObservableFuture<R> extends ObservableFutureWithMapping<R> {
    private final String mMessage;

    public AlreadyCancelledObservableFuture(String str) {
        this.mMessage = str;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public R getOrThrow() throws InterruptedException {
        throw new CancelledException(new Throwable(this.mMessage));
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ObservableFuture<R> observe(Scheduler scheduler, final ObservableFuture.Observer<R> observer) {
        scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.AlreadyCancelledObservableFuture.1
            @Override // java.lang.Runnable
            public void run() {
                observer.onCancelled();
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public ObservableFuture<R> observeDoneResult(@NotNull Scheduler scheduler, @NotNull final ObservableFuture.ResultDoneObserver<R> resultDoneObserver) {
        scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.AlreadyCancelledObservableFuture.3
            @Override // java.lang.Runnable
            public void run() {
                resultDoneObserver.onDone(new ExecutionResult.Cancelled(new Throwable(AlreadyCancelledObservableFuture.this.mMessage)));
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ObservableFuture<R> observeResult(Scheduler scheduler, final ObservableFuture.ResultObserver<R> resultObserver) {
        scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.AlreadyCancelledObservableFuture.2
            @Override // java.lang.Runnable
            public void run() {
                resultObserver.onCancelled(new Throwable(AlreadyCancelledObservableFuture.this.mMessage));
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ExecutionResult<R> obtainResult() {
        return new ExecutionResult.Cancelled(new Throwable(this.mMessage));
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public ExecutionResult<R> await(@NotNull Continuation<? super ExecutionResult<R>> continuation) {
        return new ExecutionResult.Cancelled(new Throwable(this.mMessage));
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public R getOrThrow(long j10, TimeUnit timeUnit) throws InterruptedException {
        return getOrThrow();
    }

    @Override // ru.mail.mailbox.cmd.Cancelable
    public void cancel() {
    }
}
