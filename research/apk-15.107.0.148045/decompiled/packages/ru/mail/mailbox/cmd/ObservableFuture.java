package ru.mail.mailbox.cmd;

import com.google.android.gms.ads.RequestConfiguration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0004\u001b\u001c\u001d\u001eJ\r\u0010\u0003\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¢\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH¦@¢\u0006\u0002\u0010\fJ$\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H&J(\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00130\u0000\"\u0004\b\u0001\u0010\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\u00130\u0015H&J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bH&J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H&J$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH&¨\u0006\u001fÀ\u0006\u0003"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFuture;", "R", "Lru/mail/mailbox/cmd/Cancelable;", "getOrThrow", "()Ljava/lang/Object;", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", "await", "Lru/mail/mailbox/cmd/ExecutionResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observe", "scheduler", "Lru/mail/mailbox/cmd/Scheduler;", "observer", "Lru/mail/mailbox/cmd/ObservableFuture$Observer;", BlockParser.MAP_TYPE, RequestConfiguration.MAX_AD_CONTENT_RATING_T, "mapper", "Lru/mail/mailbox/cmd/ObservableFuture$Mapper;", "obtainResult", "observeResult", "Lru/mail/mailbox/cmd/ObservableFuture$ResultObserver;", "observeDoneResult", "Lru/mail/mailbox/cmd/ObservableFuture$ResultDoneObserver;", "Observer", "ResultObserver", "ResultDoneObserver", "Mapper", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ObservableFuture<R> extends Cancelable {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u0003J\u0017\u0010\u0004\u001a\u0004\u0018\u00018\u00022\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFuture$Mapper;", "R", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", BlockParser.MAP_TYPE, "result", "(Ljava/lang/Object;)Ljava/lang/Object;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Mapper<R, T> {
        @Nullable
        T map(R result);
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u0017\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00018\u0001H&¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0004H&J\u0018\u0010\b\u001a\u00020\u00042\u000e\u0010\t\u001a\n\u0018\u00010\nj\u0004\u0018\u0001`\u000bH&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFuture$Observer;", "R", "", "onDone", "", "result", "(Ljava/lang/Object;)V", "onCancelled", BatchApiRequest.FIELD_NAME_ON_ERROR, OkListenerKt.KEY_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Observer<R> {
        void onCancelled();

        void onDone(@Nullable R result);

        void onError(@Nullable Exception exception);
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFuture$ResultDoneObserver;", "R", "", "onDone", "", "result", "Lru/mail/mailbox/cmd/ExecutionResult;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ResultDoneObserver<R> {
        void onDone(@NotNull ExecutionResult<R> result);
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0001H&¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFuture$ResultObserver;", "R", "", "onSuccess", "", "result", "(Ljava/lang/Object;)V", "onException", "cause", "", "onCancelled", "onInterrupted", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ResultObserver<R> {
        void onCancelled(@NotNull Throwable cause);

        void onException(@NotNull Throwable cause);

        void onInterrupted(@NotNull Throwable cause);

        void onSuccess(R result);
    }

    @Nullable
    Object await(@NotNull Continuation<? super ExecutionResult<R>> continuation);

    R getOrThrow() throws ExecutionException, InterruptedException;

    R getOrThrow(long timeout, @NotNull TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException;

    @NotNull
    <T> ObservableFuture<T> map(@NotNull Mapper<R, T> mapper);

    @NotNull
    ObservableFuture<R> observe(@NotNull Scheduler scheduler, @NotNull Observer<R> observer);

    @NotNull
    ObservableFuture<R> observeDoneResult(@NotNull Scheduler scheduler, @NotNull ResultDoneObserver<R> observer);

    @NotNull
    ObservableFuture<R> observeResult(@NotNull Scheduler scheduler, @NotNull ResultObserver<R> observer);

    @NotNull
    ExecutionResult<R> obtainResult();
}
