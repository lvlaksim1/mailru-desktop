package ru.mail.util.push;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import androidx.annotation.StringRes;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.intent.ExternalIntent;
import ru.mail.arbiter.NamedThreadFactory;
import ru.mail.calleridentification.CallerIdentNotificationParams;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.logic.navigation.restoreauth.ReturnParams;
import ru.mail.mailbox.cmd.CompleteObserver;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.mailbox.cmd.ObservableFutureTask;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.util.push.calendar.CalendarNotificationPush;
import ru.mail.util.push.wallet.WalletNotificationPush;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u001dH\u0016J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001f\u001a\u00020 H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010#\u001a\u00020$H\u0016J.\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0017\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020*H\u0016J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0017\u001a\u00020'H\u0016J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010(\u001a\u00020'H\u0016J\u0016\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0017\u001a\u00020-H\u0016J\u001e\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010(\u001a\u00020'2\u0006\u0010/\u001a\u00020'H\u0016J\u001e\u00100\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u00101\u001a\u00020*2\u0006\u00102\u001a\u000203H\u0016J,\u00104\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0001\u00105\u001a\u0002062\b\b\u0001\u00107\u001a\u0002062\b\u00108\u001a\u0004\u0018\u000109H\u0016J\u0016\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010;\u001a\u00020<H\u0016J\u000e\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J \u0010>\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020?2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J \u0010@\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020A2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020CH\u0016J\u001e\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010&\u001a\u00020'2\u0006\u0010E\u001a\u00020'H\u0016J\u000e\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\u000e\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J&\u0010H\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010&\u001a\u00020'2\u0006\u0010E\u001a\u00020'2\u0006\u0010I\u001a\u00020'H\u0016J$\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010K\u001a\u00020'2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0MH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006O"}, d2 = {"Lru/mail/util/push/NotificationHandlerImpl;", "Lru/mail/util/push/NotificationHandler;", "context", "Landroid/content/Context;", "notificationHandler", "Lru/mail/util/push/BaseNotificationHandler;", "<init>", "(Landroid/content/Context;Lru/mail/util/push/BaseNotificationHandler;)V", "executor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "getExecutor", "()Ljava/util/concurrent/ExecutorService;", "executor$delegate", "Lkotlin/Lazy;", "showNotification", "Lru/mail/mailbox/cmd/ObservableFuture;", "Ljava/lang/Void;", "pushMessage", "Lru/mail/util/push/NewMailPush;", "externalIntent", "Lru/mail/android_utils/intent/ExternalIntent;", "showPromoteNotification", "message", "Lru/mail/util/push/PromoteUrlPushMessage;", "clearNotification", "params", "Lru/mail/util/push/ClearNotificationParams;", "deleteNotification", "Lru/mail/util/push/DeleteNotificationPush;", "updateNotificationCount", "countPush", "Lru/mail/util/push/CountPush;", "refreshNotification", "updateNotificationsAfterMoveMsg", "movePush", "Lru/mail/util/push/MovePush;", "showErrorNotification", "title", "", "profileId", "folderId", "", "clearErrorNotification", "restorePassNotification", "Lru/mail/util/push/PasswordRestorePush;", "closeNotificationWithSmartReply", "messageId", "showRestoreAuthFlowNotification", "secondsPassed", "returnUserParams", "Lru/mail/logic/navigation/restoreauth/ReturnParams;", "showRestoreAuthSDKFlowNotification", "notificationTitleId", "", "notificationTextId", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "showCallerNotification", "notificationParams", "Lru/mail/calleridentification/CallerIdentNotificationParams;", "clearCallerNotification", "showCalendarNotification", "Lru/mail/util/push/calendar/CalendarNotificationPush;", "showWalletNotification", "Lru/mail/util/push/wallet/WalletNotificationPush;", "showPortalNotification", "Lru/mail/util/push/PortalPush;", "showChallengeNotification", "text", "clearChallengerNotification", "clearStoriesNotification", "showStoriesNotification", "storyId", "safeExecute", "actionName", "action", "Lkotlin/Function0;", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class NotificationHandlerImpl extends NotificationHandler {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    /* JADX INFO: renamed from: executor$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy executor;

    @NotNull
    private final BaseNotificationHandler notificationHandler;

    /* JADX INFO: renamed from: ru.mail.util.push.NotificationHandlerImpl$closeNotificationWithSmartReply$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"ru/mail/util/push/NotificationHandlerImpl$closeNotificationWithSmartReply$2", "Lru/mail/mailbox/cmd/CompleteObserver;", "Ljava/lang/Void;", "onComplete", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AnonymousClass2 extends CompleteObserver<Void> {
        final /* synthetic */ String $messageId;
        final /* synthetic */ String $profileId;
        final /* synthetic */ NotificationHandlerImpl this$0;

        AnonymousClass2(String str, String str2, NotificationHandlerImpl notificationHandlerImpl) {
            this.$profileId = str;
            this.$messageId = str2;
            this.this$0 = notificationHandlerImpl;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onComplete$lambda$0(String str, String str2, NotificationHandlerImpl notificationHandlerImpl) {
            ClearNotificationParams clearNotificationParamsBuild = new ClearNotificationParams.Builder(str).setMessageIds(CollectionsKt.listOf(str2)).build();
            BaseNotificationHandler baseNotificationHandler = notificationHandlerImpl.notificationHandler;
            Intrinsics.checkNotNull(clearNotificationParamsBuild);
            baseNotificationHandler.clearNotification(clearNotificationParamsBuild);
        }

        @Override // ru.mail.mailbox.cmd.CompleteObserver
        public void onComplete() {
            Handler handler = new Handler();
            final String str = this.$profileId;
            final String str2 = this.$messageId;
            final NotificationHandlerImpl notificationHandlerImpl = this.this$0;
            handler.postDelayed(new Runnable() { // from class: ru.mail.util.push.t0
                @Override // java.lang.Runnable
                public final void run() {
                    NotificationHandlerImpl.AnonymousClass2.onComplete$lambda$0(str, str2, notificationHandlerImpl);
                }
            }, 250L);
        }
    }

    public NotificationHandlerImpl(@NotNull Context context, @NotNull BaseNotificationHandler notificationHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationHandler, "notificationHandler");
        this.context = context;
        this.notificationHandler = notificationHandler;
        this.executor = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.x
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.executor_delegate$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearCallerNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl) {
        notificationHandlerImpl.notificationHandler.clearCallerNotification();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearChallengerNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl) {
        notificationHandlerImpl.notificationHandler.clearPromoteNotification();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearErrorNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, String str) {
        notificationHandlerImpl.notificationHandler.clearErrorNotification(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, ClearNotificationParams clearNotificationParams) {
        notificationHandlerImpl.notificationHandler.clearNotification(clearNotificationParams);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit clearStoriesNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl) {
        notificationHandlerImpl.notificationHandler.clearPromoteNotification();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit closeNotificationWithSmartReply$lambda$0(NotificationHandlerImpl notificationHandlerImpl, String str, String str2) {
        notificationHandlerImpl.notificationHandler.closeNotificationWithSmartReply(str, str2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, DeleteNotificationPush deleteNotificationPush) {
        notificationHandlerImpl.notificationHandler.deleteNotification(deleteNotificationPush);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExecutorService executor_delegate$lambda$0() {
        return Executors.newSingleThreadExecutor(new NamedThreadFactory("NotificationHandler"));
    }

    private final ExecutorService getExecutor() {
        return (ExecutorService) this.executor.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit refreshNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl) {
        notificationHandlerImpl.notificationHandler.refreshNotification();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit restorePassNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, PasswordRestorePush passwordRestorePush) {
        notificationHandlerImpl.notificationHandler.restorePassNotification(passwordRestorePush);
        return Unit.INSTANCE;
    }

    private final ObservableFuture<Void> safeExecute(String actionName, final Function0<Unit> action) {
        NotificationHandlerImplKt.LOG.i("Execute action " + actionName);
        ObservableFutureTask observableFutureTask = new ObservableFutureTask(new Callable() { // from class: ru.mail.util.push.c0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return NotificationHandlerImpl.safeExecute$lambda$0(this.f101049a, action);
            }
        });
        getExecutor().execute(observableFutureTask);
        return observableFutureTask;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void safeExecute$lambda$0(NotificationHandlerImpl notificationHandlerImpl, Function0 function0) {
        CommonDataManager.from(notificationHandlerImpl.context).awaitInitialized();
        function0.invoke();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCalendarNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, CalendarNotificationPush calendarNotificationPush) {
        notificationHandlerImpl.notificationHandler.showCalendarNotification(calendarNotificationPush);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showCallerNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, CallerIdentNotificationParams callerIdentNotificationParams) {
        notificationHandlerImpl.notificationHandler.showCallerNotification(callerIdentNotificationParams);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showChallengeNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, String str, String str2) {
        notificationHandlerImpl.notificationHandler.showChallengeNotification(str, str2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showErrorNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, String str, String str2, String str3, long j10) {
        notificationHandlerImpl.notificationHandler.showErrorNotification(str, str2, str3, j10);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showErrorNotification$lambda$1(NotificationHandlerImpl notificationHandlerImpl, String str, String str2) {
        notificationHandlerImpl.notificationHandler.showErrorNotification(str, str2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, NewMailPush newMailPush) {
        notificationHandlerImpl.notificationHandler.showNotification(newMailPush);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPortalNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, PortalPush portalPush) {
        notificationHandlerImpl.notificationHandler.showPortalNotification(portalPush);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showPromoteNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, PromoteUrlPushMessage promoteUrlPushMessage) {
        notificationHandlerImpl.notificationHandler.showPromoteNotification(promoteUrlPushMessage);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showRestoreAuthFlowNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, long j10, ReturnParams returnParams) {
        notificationHandlerImpl.notificationHandler.showRestoreAuthFlowNotification(j10, returnParams);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showRestoreAuthSDKFlowNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, int i10, int i11, PendingIntent pendingIntent) {
        notificationHandlerImpl.notificationHandler.showRestoreAuthSDKFlowNotification(i10, i11, pendingIntent);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showStoriesNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, String str, String str2, String str3) {
        notificationHandlerImpl.notificationHandler.showStoriesNotification(str, str2, str3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showWalletNotification$lambda$0(NotificationHandlerImpl notificationHandlerImpl, WalletNotificationPush walletNotificationPush) {
        notificationHandlerImpl.notificationHandler.showWalletNotification(walletNotificationPush);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit updateNotificationCount$lambda$0(NotificationHandlerImpl notificationHandlerImpl, CountPush countPush) {
        notificationHandlerImpl.notificationHandler.updateNotificationCount(countPush);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit updateNotificationsAfterMoveMsg$lambda$0(NotificationHandlerImpl notificationHandlerImpl, MovePush movePush) {
        notificationHandlerImpl.notificationHandler.updateNotificationsAfterMoveMsg(movePush);
        return Unit.INSTANCE;
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> clearCallerNotification() {
        return safeExecute("clearCallerNotification", new Function0() { // from class: ru.mail.util.push.r0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.clearCallerNotification$lambda$0(this.f101198a);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> clearChallengerNotification() {
        return safeExecute("clearChallengerNotification", new Function0() { // from class: ru.mail.util.push.u
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.clearChallengerNotification$lambda$0(this.f101204a);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> clearErrorNotification(@NotNull final String profileId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        return safeExecute("clearErrorNotification", new Function0() { // from class: ru.mail.util.push.q0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.clearErrorNotification$lambda$0(this.f101196a, profileId);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> clearNotification(@NotNull final ClearNotificationParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        return safeExecute("clearNotification", new Function0() { // from class: ru.mail.util.push.n0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.clearNotification$lambda$0(this.f101098a, params);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> clearStoriesNotification() {
        return safeExecute("clearStoriesNotification", new Function0() { // from class: ru.mail.util.push.a0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.clearStoriesNotification$lambda$0(this.f101043a);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> closeNotificationWithSmartReply(@NotNull final String profileId, @NotNull final String messageId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(messageId, "messageId");
        return safeExecute("closeNotificationWithSmartReply", new Function0() { // from class: ru.mail.util.push.e0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.closeNotificationWithSmartReply$lambda$0(this.f101057a, profileId, messageId);
            }
        }).observe(Schedulers.mainThread(), new AnonymousClass2(profileId, messageId, this));
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> deleteNotification(@NotNull final DeleteNotificationPush pushMessage) {
        Intrinsics.checkNotNullParameter(pushMessage, "pushMessage");
        return safeExecute("deleteNotification", new Function0() { // from class: ru.mail.util.push.d0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.deleteNotification$lambda$0(this.f101054a, pushMessage);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> refreshNotification() {
        return safeExecute("refreshNotification", new Function0() { // from class: ru.mail.util.push.b0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.refreshNotification$lambda$0(this.f101047a);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> restorePassNotification(@NotNull final PasswordRestorePush message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return safeExecute("restorePassNotification", new Function0() { // from class: ru.mail.util.push.w
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.restorePassNotification$lambda$0(this.f101213a, message);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showCalendarNotification(@NotNull final CalendarNotificationPush pushMessage, @Nullable ExternalIntent externalIntent) {
        Intrinsics.checkNotNullParameter(pushMessage, "pushMessage");
        return safeExecute("showCalendarNotification", new Function0() { // from class: ru.mail.util.push.z
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showCalendarNotification$lambda$0(this.f101220a, pushMessage);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showCallerNotification(@NotNull final CallerIdentNotificationParams notificationParams) {
        Intrinsics.checkNotNullParameter(notificationParams, "notificationParams");
        return safeExecute("showCallerNotification", new Function0() { // from class: ru.mail.util.push.s0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showCallerNotification$lambda$0(this.f101199a, notificationParams);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showChallengeNotification(@NotNull final String title, @NotNull final String text) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(text, "text");
        return safeExecute("showChallengeNotification", new Function0() { // from class: ru.mail.util.push.o0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showChallengeNotification$lambda$0(this.f101184a, title, text);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showErrorNotification(@NotNull final String title, @NotNull final String message, @NotNull final String profileId, final long folderId) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        return safeExecute("showErrorNotification", new Function0() { // from class: ru.mail.util.push.i0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showErrorNotification$lambda$0(this.f101077a, title, message, profileId, folderId);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showNotification(@NotNull final NewMailPush pushMessage, @Nullable ExternalIntent externalIntent) {
        Intrinsics.checkNotNullParameter(pushMessage, "pushMessage");
        return safeExecute("showNotification", new Function0() { // from class: ru.mail.util.push.v
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showNotification$lambda$0(this.f101205a, pushMessage);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showPortalNotification(@NotNull final PortalPush pushMessage) {
        Intrinsics.checkNotNullParameter(pushMessage, "pushMessage");
        return safeExecute("showPortalNotification", new Function0() { // from class: ru.mail.util.push.k0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showPortalNotification$lambda$0(this.f101085a, pushMessage);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showPromoteNotification(@NotNull final PromoteUrlPushMessage message, @Nullable ExternalIntent externalIntent) {
        Intrinsics.checkNotNullParameter(message, "message");
        return safeExecute("showPromoteNotification", new Function0() { // from class: ru.mail.util.push.h0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showPromoteNotification$lambda$0(this.f101072a, message);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showRestoreAuthFlowNotification(final long secondsPassed, @NotNull final ReturnParams returnUserParams) {
        Intrinsics.checkNotNullParameter(returnUserParams, "returnUserParams");
        return safeExecute("showRestoreAuthFlowNotification", new Function0() { // from class: ru.mail.util.push.g0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showRestoreAuthFlowNotification$lambda$0(this.f101065a, secondsPassed, returnUserParams);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showRestoreAuthSDKFlowNotification(@StringRes final int notificationTitleId, @StringRes final int notificationTextId, @Nullable final PendingIntent pendingIntent) {
        return safeExecute("showRestoreAuthSDKFlowNotification", new Function0() { // from class: ru.mail.util.push.m0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showRestoreAuthSDKFlowNotification$lambda$0(this.f101092a, notificationTitleId, notificationTextId, pendingIntent);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showStoriesNotification(@NotNull final String title, @NotNull final String text, @NotNull final String storyId) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(storyId, "storyId");
        return safeExecute("showStoriesNotification", new Function0() { // from class: ru.mail.util.push.l0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showStoriesNotification$lambda$0(this.f101087a, title, text, storyId);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showWalletNotification(@NotNull final WalletNotificationPush pushMessage, @Nullable ExternalIntent externalIntent) {
        Intrinsics.checkNotNullParameter(pushMessage, "pushMessage");
        return safeExecute("showWalletNotification", new Function0() { // from class: ru.mail.util.push.p0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showWalletNotification$lambda$0(this.f101188a, pushMessage);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> updateNotificationCount(@NotNull final CountPush countPush) {
        Intrinsics.checkNotNullParameter(countPush, "countPush");
        return safeExecute("updateNotificationCount", new Function0() { // from class: ru.mail.util.push.y
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.updateNotificationCount$lambda$0(this.f101218a, countPush);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> updateNotificationsAfterMoveMsg(@NotNull final MovePush movePush) {
        Intrinsics.checkNotNullParameter(movePush, "movePush");
        return safeExecute("updateNotificationsAfterMoveMsg", new Function0() { // from class: ru.mail.util.push.j0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.updateNotificationsAfterMoveMsg$lambda$0(this.f101082a, movePush);
            }
        });
    }

    @Override // ru.mail.util.push.NotificationHandler
    @NotNull
    public ObservableFuture<Void> showErrorNotification(@NotNull final String title, @NotNull final String message) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        return safeExecute("showErrorNotification", new Function0() { // from class: ru.mail.util.push.f0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationHandlerImpl.showErrorNotification$lambda$1(this.f101062a, title, message);
            }
        });
    }
}
