package ru.mail.logic.sendmessage.queue.notification;

import android.content.Context;
import android.os.Build;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.sendmessage.queue.SendQueueMode;
import ru.mail.logic.sendmessage.queue.SendQueueModeProvider;
import ru.mail.march.concurrent.IoDispatcher;
import ru.mail.messagesend.queue.SendQueueState;
import ru.mail.messagesend.queue.SendQueueStateProvider;
import ru.mail.messagesend.queue.SendQueueStatus;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Singleton
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002$%BM\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010 \u001a\u00020\u0019H\u0002J\u0016\u0010!\u001a\u0004\u0018\u00010\u0017*\u00020\u001e2\u0006\u0010\"\u001a\u00020#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/DefaultSendQueueNotificationGateway;", "Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationGateway;", "context", "Landroid/content/Context;", "modeProvider", "Lru/mail/logic/sendmessage/queue/SendQueueModeProvider;", "mailNotificationsEnabledProvider", "Lru/mail/logic/sendmessage/queue/notification/MailNotificationsEnabledProvider;", "stateProvider", "Lru/mail/messagesend/queue/SendQueueStateProvider;", "renderer", "Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationRenderer;", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "applicationScope", "Lkotlinx/coroutines/CoroutineScope;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "<init>", "(Landroid/content/Context;Lru/mail/logic/sendmessage/queue/SendQueueModeProvider;Lru/mail/logic/sendmessage/queue/notification/MailNotificationsEnabledProvider;Lru/mail/messagesend/queue/SendQueueStateProvider;Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationRenderer;Landroidx/core/app/NotificationManagerCompat;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;)V", "notificationLock", "", "lastTerminalNotification", "Lru/mail/logic/sendmessage/queue/notification/DefaultSendQueueNotificationGateway$TerminalNotification;", "refreshImmediately", "", "hide", "", "render", "state", "Lru/mail/messagesend/queue/SendQueueState;", "shouldHideCompletion", "canPostNotifications", "terminalNotification", RemoteMessageConst.Notification.CHANNEL_ID, "", "TerminalNotification", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultSendQueueNotificationGateway implements SendQueueNotificationGateway {

    @NotNull
    private final Context context;

    @Nullable
    private TerminalNotification lastTerminalNotification;

    @NotNull
    private final MailNotificationsEnabledProvider mailNotificationsEnabledProvider;

    @NotNull
    private final SendQueueModeProvider modeProvider;

    @NotNull
    private final Object notificationLock;

    @NotNull
    private final NotificationManagerCompat notificationManager;

    @NotNull
    private final SendQueueNotificationRenderer renderer;

    @NotNull
    private final SendQueueStateProvider stateProvider;

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("SendQueueNotification");

    /* JADX INFO: renamed from: ru.mail.logic.sendmessage.queue.notification.DefaultSendQueueNotificationGateway$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.logic.sendmessage.queue.notification.DefaultSendQueueNotificationGateway$1", f = "SendQueueNotificationGateway.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DefaultSendQueueNotificationGateway.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow<SendQueueState> state = DefaultSendQueueNotificationGateway.this.stateProvider.getState();
                final DefaultSendQueueNotificationGateway defaultSendQueueNotificationGateway = DefaultSendQueueNotificationGateway.this;
                FlowCollector<? super SendQueueState> flowCollector = new FlowCollector() { // from class: ru.mail.logic.sendmessage.queue.notification.DefaultSendQueueNotificationGateway.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((SendQueueState) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(SendQueueState sendQueueState, Continuation<? super Unit> continuation) {
                        defaultSendQueueNotificationGateway.refreshImmediately();
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (state.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/DefaultSendQueueNotificationGateway$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "getLOG", "()Lru/mail/util/log/Log;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Log getLOG() {
            return DefaultSendQueueNotificationGateway.LOG;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/DefaultSendQueueNotificationGateway$TerminalNotification;", "", "state", "Lru/mail/messagesend/queue/SendQueueState;", RemoteMessageConst.Notification.CHANNEL_ID, "", "<init>", "(Lru/mail/messagesend/queue/SendQueueState;Ljava/lang/String;)V", "getState", "()Lru/mail/messagesend/queue/SendQueueState;", "getChannelId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class TerminalNotification {

        @NotNull
        private final String channelId;

        @NotNull
        private final SendQueueState state;

        public TerminalNotification(@NotNull SendQueueState state, @NotNull String channelId) {
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(channelId, "channelId");
            this.state = state;
            this.channelId = channelId;
        }

        public static /* synthetic */ TerminalNotification copy$default(TerminalNotification terminalNotification, SendQueueState sendQueueState, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                sendQueueState = terminalNotification.state;
            }
            if ((i10 & 2) != 0) {
                str = terminalNotification.channelId;
            }
            return terminalNotification.copy(sendQueueState, str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SendQueueState getState() {
            return this.state;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getChannelId() {
            return this.channelId;
        }

        @NotNull
        public final TerminalNotification copy(@NotNull SendQueueState state, @NotNull String channelId) {
            Intrinsics.checkNotNullParameter(state, "state");
            Intrinsics.checkNotNullParameter(channelId, "channelId");
            return new TerminalNotification(state, channelId);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TerminalNotification)) {
                return false;
            }
            TerminalNotification terminalNotification = (TerminalNotification) other;
            return Intrinsics.areEqual(this.state, terminalNotification.state) && Intrinsics.areEqual(this.channelId, terminalNotification.channelId);
        }

        @NotNull
        public final String getChannelId() {
            return this.channelId;
        }

        @NotNull
        public final SendQueueState getState() {
            return this.state;
        }

        public int hashCode() {
            return (this.state.hashCode() * 31) + this.channelId.hashCode();
        }

        @NotNull
        public String toString() {
            return "TerminalNotification(state=" + this.state + ", channelId=" + this.channelId + ")";
        }
    }

    @Inject
    public DefaultSendQueueNotificationGateway(@ApplicationContext @NotNull Context context, @NotNull SendQueueModeProvider modeProvider, @NotNull MailNotificationsEnabledProvider mailNotificationsEnabledProvider, @NotNull SendQueueStateProvider stateProvider, @NotNull SendQueueNotificationRenderer renderer, @NotNull NotificationManagerCompat notificationManager, @NotNull CoroutineScope applicationScope, @IoDispatcher @NotNull CoroutineDispatcher ioDispatcher) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(modeProvider, "modeProvider");
        Intrinsics.checkNotNullParameter(mailNotificationsEnabledProvider, "mailNotificationsEnabledProvider");
        Intrinsics.checkNotNullParameter(stateProvider, "stateProvider");
        Intrinsics.checkNotNullParameter(renderer, "renderer");
        Intrinsics.checkNotNullParameter(notificationManager, "notificationManager");
        Intrinsics.checkNotNullParameter(applicationScope, "applicationScope");
        Intrinsics.checkNotNullParameter(ioDispatcher, "ioDispatcher");
        this.context = context;
        this.modeProvider = modeProvider;
        this.mailNotificationsEnabledProvider = mailNotificationsEnabledProvider;
        this.stateProvider = stateProvider;
        this.renderer = renderer;
        this.notificationManager = notificationManager;
        this.notificationLock = new Object();
        BuildersKt__Builders_commonKt.launch$default(applicationScope, ioDispatcher, null, new AnonymousClass1(null), 2, null);
    }

    private final boolean canPostNotifications() {
        return (Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(this.context, "android.permission.POST_NOTIFICATIONS") == 0) && this.notificationManager.areNotificationsEnabled();
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0074  */
    /* JADX WARN: Code duplicated, block: B:33:0x0083  */
    private final boolean render(SendQueueState state) {
        Object objM13123constructorimpl;
        Throwable thM13126exceptionOrNullimpl;
        Boolean bool;
        boolean z10;
        try {
            Result.Companion companion = Result.INSTANCE;
            boolean z11 = false;
            if (this.modeProvider.snapshot() != SendQueueMode.NEW_QUEUE || state.getStatus() == SendQueueStatus.EMPTY || shouldHideCompletion(state)) {
                hide();
            } else {
                if (canPostNotifications()) {
                    String strEnsureChannel = this.renderer.ensureChannel();
                    TerminalNotification terminalNotification = terminalNotification(state, strEnsureChannel);
                    z10 = true;
                    if (terminalNotification == null || !Intrinsics.areEqual(terminalNotification, this.lastTerminalNotification)) {
                        if (terminalNotification == null) {
                            this.lastTerminalNotification = null;
                        }
                        this.notificationManager.notify(1113, this.renderer.render(state, strEnsureChannel));
                        this.lastTerminalNotification = terminalNotification;
                        z11 = true;
                    }
                    objM13123constructorimpl = Result.m13123constructorimpl(Boolean.valueOf(z10));
                    thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
                    if (thM13126exceptionOrNullimpl != null) {
                        LOG.e("Unable to refresh send queue notification", thM13126exceptionOrNullimpl);
                    }
                    bool = Boolean.FALSE;
                    if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                        objM13123constructorimpl = bool;
                    }
                    return ((Boolean) objM13123constructorimpl).booleanValue();
                }
                this.lastTerminalNotification = null;
            }
            z10 = z11;
            objM13123constructorimpl = Result.m13123constructorimpl(Boolean.valueOf(z10));
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
        thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
        if (thM13126exceptionOrNullimpl != null) {
            LOG.e("Unable to refresh send queue notification", thM13126exceptionOrNullimpl);
        }
        bool = Boolean.FALSE;
        if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
            objM13123constructorimpl = bool;
        }
        return ((Boolean) objM13123constructorimpl).booleanValue();
    }

    private final boolean shouldHideCompletion(SendQueueState state) {
        if (state.getStatus() != SendQueueStatus.COMPLETED) {
            return false;
        }
        if (state.getCompletedInForeground()) {
            return true;
        }
        return state.getAllSucceededMessagesSentToSelf() && this.mailNotificationsEnabledProvider.snapshot();
    }

    private final TerminalNotification terminalNotification(SendQueueState sendQueueState, String str) {
        if (sendQueueState.getStatus() == SendQueueStatus.COMPLETED || sendQueueState.getStatus() == SendQueueStatus.PAUSED_FAILED || sendQueueState.getStatus() == SendQueueStatus.PAUSED_TIMEOUT) {
            return new TerminalNotification(sendQueueState, str);
        }
        return null;
    }

    @Override // ru.mail.logic.sendmessage.queue.notification.SendQueueNotificationGateway
    public void hide() {
        synchronized (this.notificationLock) {
            this.lastTerminalNotification = null;
            this.notificationManager.cancel(1113);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // ru.mail.logic.sendmessage.queue.notification.SendQueueNotificationGateway
    public boolean refreshImmediately() {
        boolean zRender;
        synchronized (this.notificationLock) {
            zRender = render(this.stateProvider.getState().getValue());
        }
        return zRender;
    }
}
