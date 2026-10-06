package ru.mail.mailbox.cmd;

import com.google.android.gms.ads.RequestConfiguration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.mail.util.log.Log;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\b\b\u0016\u0018\u0000 7*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u00044567B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eJ\u001e\u0010\r\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0096\u0002¢\u0006\u0002\u0010\u0013J\r\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0096@¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0014J$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH\u0016J$\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0016J$\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\"H\u0016J(\u0010#\u001a\b\u0012\u0004\u0012\u0002H$0\u0003\"\u0004\b\u0001\u0010$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H$0&H\u0016J\u0015\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020\u001aH\u0002J\u0010\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020-H\u0002J\u0015\u0010.\u001a\u00020\u001a2\u0006\u0010(\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010)J\u0010\u0010/\u001a\u00020\u001a2\u0006\u0010,\u001a\u000200H\u0002J\u0010\u00101\u001a\u00020\u001a2\u0006\u0010,\u001a\u000200H\u0002J\u0010\u00102\u001a\u00020\u001a2\u0006\u0010,\u001a\u000200H\u0002J\b\u00103\u001a\u00020\u001aH\u0016R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFutureTask;", "R", "Ljava/util/concurrent/FutureTask;", "Lru/mail/mailbox/cmd/ObservableFuture;", "callable", "Ljava/util/concurrent/Callable;", "<init>", "(Ljava/util/concurrent/Callable;)V", "mObservers", "", "Lru/mail/mailbox/cmd/ObservableFutureTask$ScheduledObserver;", "mResultObservers", "Lru/mail/mailbox/cmd/ObservableFuture$ResultObserver;", "get", "()Ljava/lang/Object;", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "(JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", "getOrThrow", "await", "Lru/mail/mailbox/cmd/ExecutionResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "obtainResult", "done", "", "observe", "scheduler", "Lru/mail/mailbox/cmd/Scheduler;", "observer", "Lru/mail/mailbox/cmd/ObservableFuture$Observer;", "observeResult", "observeDoneResult", "Lru/mail/mailbox/cmd/ObservableFuture$ResultDoneObserver;", BlockParser.MAP_TYPE, RequestConfiguration.MAX_AD_CONTENT_RATING_T, "mapper", "Lru/mail/mailbox/cmd/ObservableFuture$Mapper;", "notifyDone", "result", "(Ljava/lang/Object;)V", "notifyCanceled", "notifyError", OkListenerKt.KEY_EXCEPTION, "Ljava/util/concurrent/ExecutionException;", "notifySuccessResult", "notifyExceptionResult", "", "notifyCanceledResult", "notifyInterruptedResult", "cancel", "ScheduledObserver", "ScheduledResultObserver", "ScheduledDoneResultObserver", "Companion", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nObservableFutureTask.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ObservableFutureTask.kt\nru/mail/mailbox/cmd/ObservableFutureTask\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,345:1\n426#2,11:346\n*S KotlinDebug\n*F\n+ 1 ObservableFutureTask.kt\nru/mail/mailbox/cmd/ObservableFutureTask\n*L\n64#1:346,11\n*E\n"})
public class ObservableFutureTask<R> extends FutureTask<R> implements ObservableFuture<R> {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("ObservableFutureTask");

    @NotNull
    private final List<ScheduledObserver<R>> mObservers;

    @NotNull
    private final List<ObservableFuture.ResultObserver<R>> mResultObservers;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFutureTask$ScheduledDoneResultObserver;", "R", "Lru/mail/mailbox/cmd/ObservableFuture$ResultObserver;", "mScheduler", "Lru/mail/mailbox/cmd/Scheduler;", "mObserver", "Lru/mail/mailbox/cmd/ObservableFuture$ResultDoneObserver;", "<init>", "(Lru/mail/mailbox/cmd/Scheduler;Lru/mail/mailbox/cmd/ObservableFuture$ResultDoneObserver;)V", "onSuccess", "", "result", "(Ljava/lang/Object;)V", "onCancelled", "cause", "", "onInterrupted", "onException", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final class ScheduledDoneResultObserver<R> implements ObservableFuture.ResultObserver<R> {

        @NotNull
        private final ObservableFuture.ResultDoneObserver<R> mObserver;

        @NotNull
        private final Scheduler mScheduler;

        public ScheduledDoneResultObserver(@NotNull Scheduler mScheduler, @NotNull ObservableFuture.ResultDoneObserver<R> mObserver) {
            Intrinsics.checkNotNullParameter(mScheduler, "mScheduler");
            Intrinsics.checkNotNullParameter(mObserver, "mObserver");
            this.mScheduler = mScheduler;
            this.mObserver = mObserver;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onCancelled$lambda$0(ScheduledDoneResultObserver scheduledDoneResultObserver, Throwable th2) {
            scheduledDoneResultObserver.mObserver.onDone(new ExecutionResult.Cancelled(th2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onException$lambda$0(ScheduledDoneResultObserver scheduledDoneResultObserver, Throwable th2) {
            scheduledDoneResultObserver.mObserver.onDone(new ExecutionResult.Exception(th2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onInterrupted$lambda$0(ScheduledDoneResultObserver scheduledDoneResultObserver, Throwable th2) {
            scheduledDoneResultObserver.mObserver.onDone(new ExecutionResult.Interrupted(th2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(ScheduledDoneResultObserver scheduledDoneResultObserver, Object obj) {
            scheduledDoneResultObserver.mObserver.onDone(new ExecutionResult.Success(obj));
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
        public void onCancelled(@NotNull final Throwable cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.d
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledDoneResultObserver.onCancelled$lambda$0(this.f95491a, cause);
                }
            });
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
        public void onException(@NotNull final Throwable cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.f
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledDoneResultObserver.onException$lambda$0(this.f95495a, cause);
                }
            });
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
        public void onInterrupted(@NotNull final Throwable cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.c
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledDoneResultObserver.onInterrupted$lambda$0(this.f95489a, cause);
                }
            });
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
        public void onSuccess(final R result) {
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.e
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledDoneResultObserver.onSuccess$lambda$0(this.f95493a, result);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00018\u0001H\u0016¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\tH\u0016J\u0018\u0010\r\u001a\u00020\t2\u000e\u0010\u000e\u001a\n\u0018\u00010\u000fj\u0004\u0018\u0001`\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFutureTask$ScheduledObserver;", "R", "Lru/mail/mailbox/cmd/ObservableFuture$Observer;", "mScheduler", "Lru/mail/mailbox/cmd/Scheduler;", "mObserver", "<init>", "(Lru/mail/mailbox/cmd/Scheduler;Lru/mail/mailbox/cmd/ObservableFuture$Observer;)V", "onDone", "", "result", "(Ljava/lang/Object;)V", "onCancelled", BatchApiRequest.FIELD_NAME_ON_ERROR, OkListenerKt.KEY_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final class ScheduledObserver<R> implements ObservableFuture.Observer<R> {

        @NotNull
        private final ObservableFuture.Observer<R> mObserver;

        @NotNull
        private final Scheduler mScheduler;

        public ScheduledObserver(@NotNull Scheduler mScheduler, @NotNull ObservableFuture.Observer<R> mObserver) {
            Intrinsics.checkNotNullParameter(mScheduler, "mScheduler");
            Intrinsics.checkNotNullParameter(mObserver, "mObserver");
            this.mScheduler = mScheduler;
            this.mObserver = mObserver;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onCancelled$lambda$0(ScheduledObserver scheduledObserver) {
            scheduledObserver.mObserver.onCancelled();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onDone$lambda$0(ScheduledObserver scheduledObserver, Object obj) {
            scheduledObserver.mObserver.onDone(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$0(ScheduledObserver scheduledObserver, Exception exc) {
            scheduledObserver.mObserver.onError(exc);
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
        public void onCancelled() {
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.i
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledObserver.onCancelled$lambda$0(this.f95501a);
                }
            });
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
        public void onDone(@Nullable final R result) {
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.g
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledObserver.onDone$lambda$0(this.f95497a, result);
                }
            });
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
        public void onError(@Nullable final Exception exception) {
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.h
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledObserver.onError$lambda$0(this.f95499a, exception);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/mailbox/cmd/ObservableFutureTask$ScheduledResultObserver;", "R", "Lru/mail/mailbox/cmd/ObservableFuture$ResultObserver;", "mScheduler", "Lru/mail/mailbox/cmd/Scheduler;", "mObserver", "<init>", "(Lru/mail/mailbox/cmd/Scheduler;Lru/mail/mailbox/cmd/ObservableFuture$ResultObserver;)V", "onSuccess", "", "result", "(Ljava/lang/Object;)V", "onCancelled", "cause", "", "onInterrupted", "onException", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final class ScheduledResultObserver<R> implements ObservableFuture.ResultObserver<R> {

        @NotNull
        private final ObservableFuture.ResultObserver<R> mObserver;

        @NotNull
        private final Scheduler mScheduler;

        public ScheduledResultObserver(@NotNull Scheduler mScheduler, @NotNull ObservableFuture.ResultObserver<R> mObserver) {
            Intrinsics.checkNotNullParameter(mScheduler, "mScheduler");
            Intrinsics.checkNotNullParameter(mObserver, "mObserver");
            this.mScheduler = mScheduler;
            this.mObserver = mObserver;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onCancelled$lambda$0(ScheduledResultObserver scheduledResultObserver, Throwable th2) {
            scheduledResultObserver.mObserver.onCancelled(th2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onException$lambda$0(ScheduledResultObserver scheduledResultObserver, Throwable th2) {
            scheduledResultObserver.mObserver.onException(th2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onInterrupted$lambda$0(ScheduledResultObserver scheduledResultObserver, Throwable th2) {
            scheduledResultObserver.mObserver.onInterrupted(th2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(ScheduledResultObserver scheduledResultObserver, Object obj) {
            scheduledResultObserver.mObserver.onSuccess(obj);
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
        public void onCancelled(@NotNull final Throwable cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.k
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledResultObserver.onCancelled$lambda$0(this.f95504a, cause);
                }
            });
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
        public void onException(@NotNull final Throwable cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.m
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledResultObserver.onException$lambda$0(this.f95508a, cause);
                }
            });
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
        public void onInterrupted(@NotNull final Throwable cause) {
            Intrinsics.checkNotNullParameter(cause, "cause");
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.j
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledResultObserver.onInterrupted$lambda$0(this.f95502a, cause);
                }
            });
        }

        @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultObserver
        public void onSuccess(final R result) {
            this.mScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.l
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableFutureTask.ScheduledResultObserver.onSuccess$lambda$0(this.f95506a, result);
                }
            });
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ObservableFutureTask(@NotNull Callable<R> callable) {
        super(callable);
        Intrinsics.checkNotNullParameter(callable, "callable");
        this.mObservers = new CopyOnWriteArrayList();
        this.mResultObservers = new CopyOnWriteArrayList();
    }

    static /* synthetic */ <R> Object await$suspendImpl(final ObservableFutureTask<R> observableFutureTask, Continuation<? super ExecutionResult<R>> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        observableFutureTask.observeDoneResult(Schedulers.immediate(), new ObservableFuture.ResultDoneObserver<R>() { // from class: ru.mail.mailbox.cmd.ObservableFutureTask$await$2$1
            @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultDoneObserver
            public void onDone(ExecutionResult<R> result) {
                Intrinsics.checkNotNullParameter(result, "result");
                cancellableContinuationImpl.resumeWith(Result.m13123constructorimpl(result));
            }
        });
        cancellableContinuationImpl.invokeOnCancellation(new Function1<Throwable, Unit>(observableFutureTask) { // from class: ru.mail.mailbox.cmd.ObservableFutureTask$await$2$2
            final /* synthetic */ ObservableFutureTask<R> this$0;

            {
                this.this$0 = observableFutureTask;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th2) {
                invoke2(th2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th2) {
                this.this$0.cancel();
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    private final void notifyCanceled() {
        Iterator<ScheduledObserver<R>> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onCancelled();
        }
        this.mObservers.clear();
    }

    private final void notifyCanceledResult(Throwable exception) {
        Iterator<ObservableFuture.ResultObserver<R>> it = this.mResultObservers.iterator();
        while (it.hasNext()) {
            it.next().onCancelled(exception);
        }
        this.mResultObservers.clear();
    }

    private final void notifyDone(R result) {
        Iterator<ScheduledObserver<R>> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onDone(result);
        }
        this.mObservers.clear();
    }

    private final void notifyError(ExecutionException exception) {
        Iterator<ScheduledObserver<R>> it = this.mObservers.iterator();
        while (it.hasNext()) {
            it.next().onError(new Exception(exception.getCause()));
        }
        this.mObservers.clear();
        Throwable cause = exception.getCause();
        if (cause instanceof CommandCancellationException) {
            notifyCanceledResult(cause);
        } else if (cause instanceof CommandExecutionException) {
            notifyExceptionResult(cause);
        } else {
            notifyExceptionResult(exception);
        }
    }

    private final void notifyExceptionResult(Throwable exception) {
        Iterator<ObservableFuture.ResultObserver<R>> it = this.mResultObservers.iterator();
        while (it.hasNext()) {
            it.next().onException(exception);
        }
        this.mResultObservers.clear();
    }

    private final void notifyInterruptedResult(Throwable exception) {
        Iterator<ObservableFuture.ResultObserver<R>> it = this.mResultObservers.iterator();
        while (it.hasNext()) {
            it.next().onInterrupted(exception);
        }
        this.mResultObservers.clear();
    }

    private final void notifySuccessResult(R result) {
        Iterator<ObservableFuture.ResultObserver<R>> it = this.mResultObservers.iterator();
        while (it.hasNext()) {
            it.next().onSuccess(result);
        }
        this.mResultObservers.clear();
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @Nullable
    public Object await(@NotNull Continuation<? super ExecutionResult<R>> continuation) {
        return await$suspendImpl(this, continuation);
    }

    @Override // ru.mail.mailbox.cmd.Cancelable
    public void cancel() {
        cancel(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.FutureTask
    protected void done() {
        super.done();
        try {
            Object obj = super.get();
            notifyDone(obj);
            notifySuccessResult(obj);
        } catch (InterruptedException e10) {
            LOG.i("Task was cancelled", e10);
            notifyCanceled();
            notifyInterruptedResult(e10);
        } catch (CancellationException e11) {
            LOG.i("Task was interrupted", e11);
            notifyCanceled();
            notifyCanceledResult(e11);
        } catch (ExecutionException e12) {
            LOG.e("Unable to execute task", e12);
            notifyError(e12);
            Throwable cause = e12.getCause();
            if (cause instanceof CommandCancellationException) {
                notifyCanceledResult(cause);
            } else if (cause instanceof CommandExecutionException) {
                notifyExceptionResult(cause);
            } else {
                notifyExceptionResult(e12);
            }
        }
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    public R get() throws ExecutionException, InterruptedException {
        try {
            return (R) super.get();
        } catch (CancellationException e10) {
            throw new CancelledException(e10);
        }
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public R getOrThrow() throws ExecutionException, InterruptedException {
        return get();
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public <T> ObservableFuture<T> map(@NotNull ObservableFuture.Mapper<R, T> mapper) {
        Intrinsics.checkNotNullParameter(mapper, "mapper");
        return new MappedObservableFuture(this, mapper);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public ObservableFuture<R> observe(@NotNull Scheduler scheduler, @NotNull ObservableFuture.Observer<R> observer) {
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        Intrinsics.checkNotNullParameter(observer, "observer");
        ScheduledObserver scheduledObserver = new ScheduledObserver(scheduler, observer);
        if (!isDone()) {
            this.mObservers.add((ScheduledObserver<R>) scheduledObserver);
            return this;
        }
        try {
            scheduledObserver.onDone(super.get());
            return this;
        } catch (InterruptedException unused) {
            scheduledObserver.onCancelled();
            return this;
        } catch (CancellationException unused2) {
            scheduledObserver.onCancelled();
            return this;
        } catch (ExecutionException e10) {
            scheduledObserver.onError(new Exception(e10.getCause()));
            return this;
        }
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public ObservableFuture<R> observeDoneResult(@NotNull Scheduler scheduler, @NotNull ObservableFuture.ResultDoneObserver<R> observer) {
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        Intrinsics.checkNotNullParameter(observer, "observer");
        ScheduledDoneResultObserver scheduledDoneResultObserver = new ScheduledDoneResultObserver(scheduler, observer);
        if (!isDone()) {
            this.mResultObservers.add(scheduledDoneResultObserver);
            return this;
        }
        try {
            observer.onDone(new ExecutionResult.Success(super.get()));
            return this;
        } catch (InterruptedException e10) {
            observer.onDone(new ExecutionResult.Interrupted(e10));
            return this;
        } catch (CancellationException e11) {
            observer.onDone(new ExecutionResult.Cancelled(e11));
            return this;
        } catch (ExecutionException e12) {
            Throwable cause = e12.getCause();
            if (cause instanceof CommandCancellationException) {
                observer.onDone(new ExecutionResult.Cancelled(e12));
            } else if (cause instanceof CommandExecutionException) {
                observer.onDone(new ExecutionResult.Exception(e12));
            } else {
                scheduledDoneResultObserver.onException(e12);
            }
            return this;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public ObservableFuture<R> observeResult(@NotNull Scheduler scheduler, @NotNull ObservableFuture.ResultObserver<R> observer) {
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        Intrinsics.checkNotNullParameter(observer, "observer");
        ScheduledResultObserver scheduledResultObserver = new ScheduledResultObserver(scheduler, observer);
        if (!isDone()) {
            this.mResultObservers.add(scheduledResultObserver);
            return this;
        }
        try {
            scheduledResultObserver.onSuccess(super.get());
            return this;
        } catch (InterruptedException e10) {
            scheduledResultObserver.onInterrupted(e10);
            return this;
        } catch (CancellationException e11) {
            scheduledResultObserver.onCancelled(e11);
            return this;
        } catch (ExecutionException e12) {
            Throwable cause = e12.getCause();
            if (cause instanceof CommandCancellationException) {
                scheduledResultObserver.onCancelled(cause);
            } else if (cause instanceof CommandExecutionException) {
                scheduledResultObserver.onException(cause);
            } else {
                scheduledResultObserver.onException(e12);
            }
            return this;
        }
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    @NotNull
    public ExecutionResult<R> obtainResult() {
        ExecutionResult<R> interrupted;
        try {
            return new ExecutionResult.Success(get());
        } catch (ExecutionException e10) {
            Throwable cause = e10.getCause();
            if (cause instanceof CommandCancellationException) {
                return new ExecutionResult.Cancelled(cause);
            }
            if (cause instanceof CommandExecutionException) {
                return new ExecutionResult.Exception(cause);
            }
            interrupted = new ExecutionResult.Exception<>(e10);
            return interrupted;
        } catch (CancelledException e11) {
            interrupted = new ExecutionResult.Cancelled<>(e11);
            return interrupted;
        } catch (InterruptedException e12) {
            Thread.currentThread().interrupt();
            interrupted = new ExecutionResult.Interrupted<>(e12);
            return interrupted;
        }
    }

    @Override // ru.mail.mailbox.cmd.ObservableFuture
    public R getOrThrow(long timeout, @NotNull TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return get(timeout, unit);
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    public R get(long timeout, @NotNull TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
        Intrinsics.checkNotNullParameter(unit, "unit");
        try {
            return (R) super.get(timeout, unit);
        } catch (CancellationException e10) {
            throw new CancelledException(e10);
        }
    }
}
