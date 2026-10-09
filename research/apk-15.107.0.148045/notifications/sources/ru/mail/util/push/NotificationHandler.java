package ru.mail.util.push;

import android.app.PendingIntent;
import android.content.Context;
import androidx.annotation.StringRes;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.common.internal.BaseGmsClient;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.intent.ExternalIntent;
import ru.mail.calleridentification.CallerIdentNotificationParams;
import ru.mail.locator.Locator;
import ru.mail.logic.navigation.restoreauth.ReturnParams;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.util.push.calendar.CalendarNotificationPush;
import ru.mail.util.push.wallet.WalletNotificationPush;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH&J \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\f\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0012H&J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0014\u001a\u00020\u0015H&J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0018\u001a\u00020\u0019H&J.\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fH&J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\u001cH&J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001d\u001a\u00020\u001cH&J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\f\u001a\u00020\"H&J\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u001cH&J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020(H&J,\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0001\u0010*\u001a\u00020+2\b\b\u0001\u0010,\u001a\u00020+2\b\u0010-\u001a\u0004\u0018\u00010.H&J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u00100\u001a\u000201H&J\u000e\u00102\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J \u00103\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u0002042\b\u0010\t\u001a\u0004\u0018\u00010\nH&J \u00105\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u0002062\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0016\u00107\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u000208H&J\u001e\u00109\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010:\u001a\u00020\u001cH&J\u000e\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J&\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010:\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020\u001cH&J\u000e\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006@"}, d2 = {"Lru/mail/util/push/NotificationHandler;", "", "<init>", "()V", "showNotification", "Lru/mail/mailbox/cmd/ObservableFuture;", "Ljava/lang/Void;", "pushMessage", "Lru/mail/util/push/NewMailPush;", "externalIntent", "Lru/mail/android_utils/intent/ExternalIntent;", "showPromoteNotification", "message", "Lru/mail/util/push/PromoteUrlPushMessage;", "clearNotification", "params", "Lru/mail/util/push/ClearNotificationParams;", "deleteNotification", "Lru/mail/util/push/DeleteNotificationPush;", "updateNotificationCount", "countPush", "Lru/mail/util/push/CountPush;", "refreshNotification", "updateNotificationsAfterMoveMsg", "movePush", "Lru/mail/util/push/MovePush;", "showErrorNotification", "title", "", "profileId", "folderId", "", "clearErrorNotification", "restorePassNotification", "Lru/mail/util/push/PasswordRestorePush;", "closeNotificationWithSmartReply", "messageId", "showRestoreAuthFlowNotification", "secondsPassed", "returnUserParams", "Lru/mail/logic/navigation/restoreauth/ReturnParams;", "showRestoreAuthSDKFlowNotification", "notificationTitleId", "", "notificationTextId", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "showCallerNotification", "notificationParams", "Lru/mail/calleridentification/CallerIdentNotificationParams;", "clearCallerNotification", "showCalendarNotification", "Lru/mail/util/push/calendar/CalendarNotificationPush;", "showWalletNotification", "Lru/mail/util/push/wallet/WalletNotificationPush;", "showPortalNotification", "Lru/mail/util/push/PortalPush;", "showChallengeNotification", "text", "clearChallengerNotification", "showStoriesNotification", "storyId", "clearStoriesNotification", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class NotificationHandler {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lru/mail/util/push/NotificationHandler$Companion;", "", "<init>", "()V", "from", "Lru/mail/util/push/NotificationHandler;", "context", "Landroid/content/Context;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final NotificationHandler from(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return (NotificationHandler) Locator.INSTANCE.from(context).locate(NotificationHandler.class);
        }

        private Companion() {
        }
    }

    @JvmStatic
    @NotNull
    public static final NotificationHandler from(@NotNull Context context) {
        return INSTANCE.from(context);
    }

    @NotNull
    public abstract ObservableFuture<Void> clearCallerNotification();

    @NotNull
    public abstract ObservableFuture<Void> clearChallengerNotification();

    @NotNull
    public abstract ObservableFuture<Void> clearErrorNotification(@NotNull String profileId);

    @NotNull
    public abstract ObservableFuture<Void> clearNotification(@NotNull ClearNotificationParams params);

    @NotNull
    public abstract ObservableFuture<Void> clearStoriesNotification();

    @NotNull
    public abstract ObservableFuture<Void> closeNotificationWithSmartReply(@NotNull String profileId, @NotNull String messageId);

    @NotNull
    public abstract ObservableFuture<Void> deleteNotification(@NotNull DeleteNotificationPush pushMessage);

    @NotNull
    public abstract ObservableFuture<Void> refreshNotification();

    @NotNull
    public abstract ObservableFuture<Void> restorePassNotification(@NotNull PasswordRestorePush message);

    @NotNull
    public abstract ObservableFuture<Void> showCalendarNotification(@NotNull CalendarNotificationPush pushMessage, @Nullable ExternalIntent externalIntent);

    @NotNull
    public abstract ObservableFuture<Void> showCallerNotification(@NotNull CallerIdentNotificationParams notificationParams);

    @NotNull
    public abstract ObservableFuture<Void> showChallengeNotification(@NotNull String title, @NotNull String text);

    @NotNull
    public abstract ObservableFuture<Void> showErrorNotification(@NotNull String title, @NotNull String message);

    @NotNull
    public abstract ObservableFuture<Void> showErrorNotification(@NotNull String title, @NotNull String message, @NotNull String profileId, long folderId);

    @NotNull
    public abstract ObservableFuture<Void> showNotification(@NotNull NewMailPush pushMessage, @Nullable ExternalIntent externalIntent);

    @NotNull
    public abstract ObservableFuture<Void> showPortalNotification(@NotNull PortalPush pushMessage);

    @NotNull
    public abstract ObservableFuture<Void> showPromoteNotification(@NotNull PromoteUrlPushMessage message, @Nullable ExternalIntent externalIntent);

    @NotNull
    public abstract ObservableFuture<Void> showRestoreAuthFlowNotification(long secondsPassed, @NotNull ReturnParams returnUserParams);

    @NotNull
    public abstract ObservableFuture<Void> showRestoreAuthSDKFlowNotification(@StringRes int notificationTitleId, @StringRes int notificationTextId, @Nullable PendingIntent pendingIntent);

    @NotNull
    public abstract ObservableFuture<Void> showStoriesNotification(@NotNull String title, @NotNull String text, @NotNull String storyId);

    @NotNull
    public abstract ObservableFuture<Void> showWalletNotification(@NotNull WalletNotificationPush pushMessage, @Nullable ExternalIntent externalIntent);

    @NotNull
    public abstract ObservableFuture<Void> updateNotificationCount(@NotNull CountPush countPush);

    @NotNull
    public abstract ObservableFuture<Void> updateNotificationsAfterMoveMsg(@NotNull MovePush movePush);
}
