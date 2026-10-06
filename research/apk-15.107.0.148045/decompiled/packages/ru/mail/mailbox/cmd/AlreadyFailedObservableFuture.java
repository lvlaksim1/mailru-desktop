package ru.mail.mailbox.cmd;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public class AlreadyFailedObservableFuture<R> extends ObservableFutureWithMapping<R> {
    private final Exception mException;

    public AlreadyFailedObservableFuture(Exception exc) {
        this.mException = exc;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public Object await(@NotNull Continuation<? super ExecutionResult<R>> continuation) {
        return new ExecutionResult.Exception(this.mException);
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public R getOrThrow() throws ExecutionException {
        throw new ExecutionException(this.mException);
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ObservableFuture<R> observe(Scheduler scheduler, final ObservableFuture.Observer<R> observer) {
        scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.AlreadyFailedObservableFuture.1
            @Override // java.lang.Runnable
            public void run() {
                observer.onError(AlreadyFailedObservableFuture.this.mException);
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public ObservableFuture<R> observeDoneResult(@NotNull Scheduler scheduler, @NotNull final ObservableFuture.ResultDoneObserver<R> resultDoneObserver) {
        scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.AlreadyFailedObservableFuture.3
            @Override // java.lang.Runnable
            public void run() {
                resultDoneObserver.onDone(new ExecutionResult.Exception(AlreadyFailedObservableFuture.this.mException));
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ObservableFuture<R> observeResult(Scheduler scheduler, final ObservableFuture.ResultObserver<R> resultObserver) {
        scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.AlreadyFailedObservableFuture.2
            @Override // java.lang.Runnable
            public void run() {
                resultObserver.onException(AlreadyFailedObservableFuture.this.mException);
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ExecutionResult<R> obtainResult() {
        return new ExecutionResult.Exception(this.mException);
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public R getOrThrow(long j10, TimeUnit timeUnit) throws ExecutionException {
        throw new ExecutionException(this.mException);
    }

    @Override // ru.mail.mailbox.cmd.Cancelable
    public void cancel() {
    }
}
