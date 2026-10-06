package ru.mail.mailbox.cmd;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class AlreadyDoneObservableFuture<R> extends ObservableFutureWithMapping<R> {
    private final R mResult;

    public AlreadyDoneObservableFuture(R r10) {
        this.mResult = r10;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public Object await(@NotNull Continuation<? super ExecutionResult<R>> continuation) {
        if (Thread.currentThread().isInterrupted()) {
            new ExecutionResult.Interrupted(new InterruptedException("Unable to get result because current thread is interrupted"));
        }
        return new ExecutionResult.Success(this.mResult);
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public R getOrThrow() throws ExecutionException, InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Unable to get result because current thread is interrupted");
        }
        return this.mResult;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ObservableFuture<R> observe(Scheduler scheduler, final ObservableFuture.Observer<R> observer) {
        scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.AlreadyDoneObservableFuture.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                observer.onDone(AlreadyDoneObservableFuture.this.mResult);
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public ObservableFuture<R> observeDoneResult(@NotNull Scheduler scheduler, @NotNull final ObservableFuture.ResultDoneObserver<R> resultDoneObserver) {
        scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.AlreadyDoneObservableFuture.3
            @Override // java.lang.Runnable
            public void run() {
                resultDoneObserver.onDone(new ExecutionResult.Success(AlreadyDoneObservableFuture.this.mResult));
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ObservableFuture<R> observeResult(Scheduler scheduler, final ObservableFuture.ResultObserver<R> resultObserver) {
        scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.AlreadyDoneObservableFuture.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                resultObserver.onSuccess(AlreadyDoneObservableFuture.this.mResult);
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ExecutionResult<R> obtainResult() {
        if (Thread.currentThread().isInterrupted()) {
            new ExecutionResult.Interrupted(new InterruptedException("Unable to get result because current thread is interrupted"));
        }
        return new ExecutionResult.Success(this.mResult);
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public R getOrThrow(long j10, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException {
        return getOrThrow();
    }

    @Override // ru.mail.mailbox.cmd.Cancelable
    public void cancel() {
    }
}
