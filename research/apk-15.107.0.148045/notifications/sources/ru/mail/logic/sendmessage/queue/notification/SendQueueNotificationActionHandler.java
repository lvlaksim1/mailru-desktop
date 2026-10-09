package ru.mail.logic.sendmessage.queue.notification;

import androidx.compose.runtime.internal.StabilityInferred;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.logic.sendmessage.queue.SendQueueMode;
import ru.mail.logic.sendmessage.queue.SendQueueModeProvider;
import ru.mail.messagesend.queue.KickReason;
import ru.mail.messagesend.queue.SendQueue;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B!\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationActionHandler;", "", "modeProvider", "Lru/mail/logic/sendmessage/queue/SendQueueModeProvider;", "sendQueue", "Lru/mail/messagesend/queue/SendQueue;", "notificationGateway", "Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationGateway;", "<init>", "(Lru/mail/logic/sendmessage/queue/SendQueueModeProvider;Lru/mail/messagesend/queue/SendQueue;Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationGateway;)V", "handle", "", "action", "Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationAction;", "onComplete", "Lkotlin/Function0;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Singleton
@SourceDebugExtension({"SMAP\nSendQueueNotificationActionHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SendQueueNotificationActionHandler.kt\nru/mail/logic/sendmessage/queue/notification/SendQueueNotificationActionHandler\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,69:1\n1#2:70\n*E\n"})
public final class SendQueueNotificationActionHandler {

    @NotNull
    private final SendQueueModeProvider modeProvider;

    @NotNull
    private final SendQueueNotificationGateway notificationGateway;

    @NotNull
    private final SendQueue sendQueue;

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("SendQueueNotificationAction");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationActionHandler$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "getLOG", "()Lru/mail/util/log/Log;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Log getLOG() {
            return SendQueueNotificationActionHandler.LOG;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SendQueueNotificationAction.values().length];
            try {
                iArr[SendQueueNotificationAction.RETRY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SendQueueNotificationAction.CANCEL_ALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SendQueueNotificationAction.DISCARD_FAILURES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public SendQueueNotificationActionHandler(@NotNull SendQueueModeProvider modeProvider, @NotNull SendQueue sendQueue, @NotNull SendQueueNotificationGateway notificationGateway) {
        Intrinsics.checkNotNullParameter(modeProvider, "modeProvider");
        Intrinsics.checkNotNullParameter(sendQueue, "sendQueue");
        Intrinsics.checkNotNullParameter(notificationGateway, "notificationGateway");
        this.modeProvider = modeProvider;
        this.sendQueue = sendQueue;
        this.notificationGateway = notificationGateway;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handle$lambda$0(Function0 function0, Result result) {
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(result.getValue());
        if (thM13126exceptionOrNullimpl != null) {
            LOG.e("Unable to enqueue send queue retry", thM13126exceptionOrNullimpl);
        }
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handle$lambda$1(Function0 function0, Result result) {
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(result.getValue());
        if (thM13126exceptionOrNullimpl != null) {
            LOG.e("Unable to enqueue send queue cancellation", thM13126exceptionOrNullimpl);
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handle$lambda$2(Function0 function0, Result result) {
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(result.getValue());
        if (thM13126exceptionOrNullimpl != null) {
            LOG.e("Unable to cancel send queue", thM13126exceptionOrNullimpl);
        }
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handle$lambda$3(Function0 function0, Result result) {
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(result.getValue());
        if (thM13126exceptionOrNullimpl != null) {
            LOG.e("Unable to enqueue failed queue discard", thM13126exceptionOrNullimpl);
            function0.invoke();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit handle$lambda$4(Function0 function0, Result result) {
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(result.getValue());
        if (thM13126exceptionOrNullimpl != null) {
            LOG.e("Unable to discard failed send queue items", thM13126exceptionOrNullimpl);
        }
        function0.invoke();
        return Unit.INSTANCE;
    }

    public final void handle(@NotNull SendQueueNotificationAction action, @NotNull final Function0<Unit> onComplete) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        if (this.modeProvider.snapshot() != SendQueueMode.NEW_QUEUE) {
            this.notificationGateway.hide();
            onComplete.invoke();
            return;
        }
        int i10 = WhenMappings.$EnumSwitchMapping$0[action.ordinal()];
        if (i10 == 1) {
            this.sendQueue.kick(KickReason.MANUAL_RETRY, new Function1() { // from class: ru.mail.logic.sendmessage.queue.notification.a
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SendQueueNotificationActionHandler.handle$lambda$0(onComplete, (Result) obj);
                }
            });
        } else if (i10 == 2) {
            this.sendQueue.cancelAll(new Function1() { // from class: ru.mail.logic.sendmessage.queue.notification.b
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SendQueueNotificationActionHandler.handle$lambda$1(onComplete, (Result) obj);
                }
            }, new Function1() { // from class: ru.mail.logic.sendmessage.queue.notification.c
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SendQueueNotificationActionHandler.handle$lambda$2(onComplete, (Result) obj);
                }
            });
        } else {
            if (i10 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            this.sendQueue.discardFailures(new Function1() { // from class: ru.mail.logic.sendmessage.queue.notification.d
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SendQueueNotificationActionHandler.handle$lambda$3(onComplete, (Result) obj);
                }
            }, new Function1() { // from class: ru.mail.logic.sendmessage.queue.notification.e
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SendQueueNotificationActionHandler.handle$lambda$4(onComplete, (Result) obj);
                }
            });
        }
    }
}
