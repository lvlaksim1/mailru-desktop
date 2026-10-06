package ru.mail.mailbox.cmd;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class MappedObservableFuture<R, T> extends ObservableFutureWithMapping<T> {
    private final ObservableFuture<R> mBaseFuture;
    private boolean mIsResultAlreadyMapped;
    private T mMappedResult;
    private final ObservableFuture.Mapper<R, T> mMapper;

    public MappedObservableFuture(ObservableFuture<R> observableFuture, ObservableFuture.Mapper<R, T> mapper) {
        this.mBaseFuture = observableFuture;
        this.mMapper = mapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized T mapInternal(R r10) {
        try {
            if (!this.mIsResultAlreadyMapped) {
                this.mMappedResult = this.mMapper.map(r10);
                this.mIsResultAlreadyMapped = true;
            }
        } catch (Throwable th2) {
            throw th2;
        }
        return this.mMappedResult;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public Object await(@NotNull Continuation<? super ExecutionResult<T>> continuation) {
        return this.mBaseFuture.obtainResult().handle(new ExecutionResult.Handler<R, ExecutionResult<T>>() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.4
            @Override // ru.mail.mailbox.cmd.ExecutionResult.Handler
            public ExecutionResult<T> cancelled(@NotNull Throwable th2) {
                return new ExecutionResult.Cancelled(th2);
            }

            @Override // ru.mail.mailbox.cmd.ExecutionResult.Handler
            public ExecutionResult<T> exception(@NotNull Throwable th2) {
                return new ExecutionResult.Exception(th2);
            }

            @Override // ru.mail.mailbox.cmd.ExecutionResult.Handler
            public ExecutionResult<T> interrupted(@NotNull Throwable th2) {
                return new ExecutionResult.Interrupted(th2);
            }

            @Override // ru.mail.mailbox.cmd.ExecutionResult.Handler
            public ExecutionResult<T> success(R r10) {
                return new ExecutionResult.Success(MappedObservableFuture.this.mapInternal(r10));
            }
        });
    }

    @Override // ru.mail.mailbox.cmd.Cancelable
    public void cancel() {
        this.mBaseFuture.cancel();
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public T getOrThrow() throws ExecutionException, InterruptedException {
        return mapInternal(this.mBaseFuture.getOrThrow());
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ObservableFuture<T> observe(final Scheduler scheduler, final ObservableFuture.Observer<T> observer) {
        this.mBaseFuture.observe(Schedulers.immediate(), new ObservableFuture.Observer<R>() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.1
            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onCancelled() {
                scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        observer.onCancelled();
                    }
                });
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onDone(R r10) {
                try {
                    final Object objMapInternal = MappedObservableFuture.this.mapInternal(r10);
                    scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.1.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public void run() {
                            observer.onDone(objMapInternal);
                        }
                    });
                } catch (Exception e10) {
                    scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.1.2
                        @Override // java.lang.Runnable
                        public void run() {
                            observer.onError(e10);
                        }
                    });
                }
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onError(final Exception exc) {
                scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.1.4
                    @Override // java.lang.Runnable
                    public void run() {
                        observer.onError(exc);
                    }
                });
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ObservableFuture<T> observeResult(final Scheduler scheduler, final ObservableFuture.ResultObserver<T> resultObserver) {
        this.mBaseFuture.observeResult(Schedulers.immediate(), new ObservableFuture.ResultObserver<R>() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.3
            @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
            public void onCancelled(final Throwable th2) {
                scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.3.4
                    @Override // java.lang.Runnable
                    public void run() {
                        resultObserver.onCancelled(th2);
                    }
                });
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
            public void onException(final Throwable th2) {
                scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.3.3
                    @Override // java.lang.Runnable
                    public void run() {
                        resultObserver.onException(th2);
                    }
                });
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
            public void onInterrupted(final Throwable th2) {
                scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.3.5
                    @Override // java.lang.Runnable
                    public void run() {
                        resultObserver.onInterrupted(th2);
                    }
                });
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
            public void onSuccess(R r10) {
                try {
                    final Object objMapInternal = MappedObservableFuture.this.mapInternal(r10);
                    scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.3.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.lang.Runnable
                        public void run() {
                            resultObserver.onSuccess(objMapInternal);
                        }
                    });
                } catch (Exception e10) {
                    scheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            resultObserver.onException(e10);
                        }
                    });
                }
            }
        });
        return this;
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public ExecutionResult<T> obtainResult() {
        return (ExecutionResult) this.mBaseFuture.obtainResult().handle(new ExecutionResult.Handler<R, ExecutionResult<T>>() { // from class: ru.mail.mailbox.cmd.MappedObservableFuture.2
            @Override // ru.mail.mailbox.cmd.ExecutionResult.Handler
            public ExecutionResult<T> cancelled(@NotNull Throwable th2) {
                return new ExecutionResult.Cancelled(th2);
            }

            @Override // ru.mail.mailbox.cmd.ExecutionResult.Handler
            public ExecutionResult<T> exception(@NotNull Throwable th2) {
                return new ExecutionResult.Exception(th2);
            }

            @Override // ru.mail.mailbox.cmd.ExecutionResult.Handler
            public ExecutionResult<T> interrupted(@NotNull Throwable th2) {
                return new ExecutionResult.Interrupted(th2);
            }

            @Override // ru.mail.mailbox.cmd.ExecutionResult.Handler
            public ExecutionResult<T> success(R r10) {
                return new ExecutionResult.Success(MappedObservableFuture.this.mapInternal(r10));
            }
        });
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public T getOrThrow(long j10, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return mapInternal(this.mBaseFuture.getOrThrow(j10, timeUnit));
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public ObservableFuture<T> observeDoneResult(@NotNull Scheduler scheduler, @NotNull ObservableFuture.ResultDoneObserver<T> resultDoneObserver) {
        return this;
    }
}
