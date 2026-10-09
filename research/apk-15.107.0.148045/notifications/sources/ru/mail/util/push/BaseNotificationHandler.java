package ru.mail.util.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.StringRes;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.android_utils.PendingIntentCreator;
import ru.mail.android_utils.PendingIntentUtils;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.calleridentification.CallerIdentNotificationParams;
import ru.mail.data.cmd.database.MarkPushHasSelectedSmartReply;
import ru.mail.locator.Locator;
import ru.mail.logic.navigation.restoreauth.ReturnParams;
import ru.mail.logic.navigation.restoreauth.SessionRestoreHelper;
import ru.mail.logic.navigation.restoreauth.TimeDelayEvaluator;
import ru.mail.mailapp.SplashScreenActivity;
import ru.mail.mailbox.cmd.Command;
import ru.mail.portal.app.adapter.di.Portal;
import ru.mail.router.RedirectLogger;
import ru.mail.util.log.Log;
import ru.mail.util.push.calendar.CalendarNotificationPush;
import ru.mail.util.push.notification.NotificationPublisher;
import ru.mail.util.push.notification.utill.UrlPendingIntentFactory;
import ru.mail.util.push.wallet.WalletNotificationPush;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u0000 I2\u00020\u0001:\u0001IB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\u000eJ&\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0016\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)J\u0016\u0010$\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0016\u001a\u00020&J\u000e\u0010*\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020&J\u000e\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020-J\u0016\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u00020)2\u0006\u00100\u001a\u000201J$\u00102\u001a\u00020\u000e2\b\b\u0001\u00103\u001a\u0002042\b\b\u0001\u00105\u001a\u0002042\b\u00106\u001a\u0004\u0018\u000107J\u000e\u00108\u001a\u00020\u000e2\u0006\u00109\u001a\u00020:J\u0006\u0010;\u001a\u00020\u000eJ\u000e\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020>J\u001c\u0010?\u001a\u00020\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030@2\u0006\u0010A\u001a\u00020&H\u0002J\u0016\u0010B\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020&2\u0006\u0010C\u001a\u00020&J\u0016\u0010D\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&2\u0006\u0010E\u001a\u00020&J\u0006\u0010F\u001a\u00020\u000eJ\u001e\u0010G\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020&2\u0006\u0010E\u001a\u00020&2\u0006\u0010H\u001a\u00020&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u00070\t¢\u0006\u0002\b\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lru/mail/util/push/BaseNotificationHandler;", "", "context", "Landroid/content/Context;", "notificationPublisher", "Lru/mail/util/push/notification/NotificationPublisher;", "<init>", "(Landroid/content/Context;Lru/mail/util/push/notification/NotificationPublisher;)V", "notificationManagerCompat", "Landroidx/core/app/NotificationManagerCompat;", "Lorg/jspecify/annotations/NonNull;", "showNotificationTaskListener", "Lru/mail/util/push/ShowNotificationTaskListener;", "showNotification", "", "pushMessage", "Lru/mail/util/push/NewMailPush;", "showCalendarNotification", "Lru/mail/util/push/calendar/CalendarNotificationPush;", "showWalletNotification", "Lru/mail/util/push/wallet/WalletNotificationPush;", "showPortalNotification", "message", "Lru/mail/util/push/PortalPush;", "showPromoteNotification", "Lru/mail/util/push/PromoteUrlPushMessage;", "clearNotification", "params", "Lru/mail/util/push/ClearNotificationParams;", "deleteNotification", "push", "Lru/mail/util/push/DeleteNotificationPush;", "updateNotificationCount", "countPush", "Lru/mail/util/push/CountPush;", "refreshNotification", "showErrorNotification", "title", "", "profileId", "folderId", "", "clearErrorNotification", "restorePassNotification", "passwordRestorePush", "Lru/mail/util/push/PasswordRestorePush;", "showRestoreAuthFlowNotification", "secondsPassed", "returnUserParams", "Lru/mail/logic/navigation/restoreauth/ReturnParams;", "showRestoreAuthSDKFlowNotification", "notificationTitleId", "", "notificationTextId", BaseGmsClient.KEY_PENDING_INTENT, "Landroid/app/PendingIntent;", "showCallerNotification", "notificationParams", "Lru/mail/calleridentification/CallerIdentNotificationParams;", "clearCallerNotification", "updateNotificationsAfterMoveMsg", "movePush", "Lru/mail/util/push/MovePush;", "executeWithErrorMsg", "Lru/mail/mailbox/cmd/Command;", "commandName", "closeNotificationWithSmartReply", "messageId", "showChallengeNotification", "text", "clearPromoteNotification", "showStoriesNotification", "storyId", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BaseNotificationHandler {
    private static final int AUTH_INTERRUPTED_ID = 2457;
    private static final int CALLER_INFO_NOTIFICATION_ID = 1365;

    @NotNull
    public static final String CHALLENGE_NOTIFICATION_EXTRA = "ChallengeNotification";
    private static final int PASS_RESTORE_NOTIFICATION_ID = 2184;
    private static final int PROMOTE_NOTIFICATION_ID = 1911;

    @NotNull
    public static final String STORIES_NOTIFICATION_EXTRA = "StoriesNotification";

    @NotNull
    public static final String STORY_ID = "StoryId";

    @NotNull
    private final Context context;

    @NotNull
    private final NotificationManagerCompat notificationManagerCompat;

    @NotNull
    private final NotificationPublisher notificationPublisher;

    @NotNull
    private final ShowNotificationTaskListener showNotificationTaskListener;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("BaseNotificationHandler");

    public BaseNotificationHandler(@NotNull Context context, @NotNull NotificationPublisher notificationPublisher) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationPublisher, "notificationPublisher");
        this.context = context;
        this.notificationPublisher = notificationPublisher;
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(...)");
        this.notificationManagerCompat = notificationManagerCompatFrom;
        this.showNotificationTaskListener = (ShowNotificationTaskListener) Locator.INSTANCE.from(context).locate(ShowNotificationTaskListener.class);
    }

    private final Object executeWithErrorMsg(Command<?, ?> command, String str) {
        Object orThrow;
        try {
            orThrow = command.execute((RequestArbiter) Locator.INSTANCE.locate(this.context, RequestArbiter.class)).getOrThrow();
        } catch (InterruptedException e10) {
            LOG.e("Unable to execute " + str, e10);
            orThrow = Unit.INSTANCE;
        } catch (ExecutionException e11) {
            LOG.e("Unable to execute " + str, e11);
            orThrow = Unit.INSTANCE;
        }
        Intrinsics.checkNotNull(orThrow);
        return orThrow;
    }

    public final void clearCallerNotification() {
        this.notificationManagerCompat.cancel(CALLER_INFO_NOTIFICATION_ID);
    }

    public final void clearErrorNotification(@NotNull String profileId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        this.notificationPublisher.clearErrorNotification(profileId);
    }

    public final void clearNotification(@NotNull ClearNotificationParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        this.notificationPublisher.clearNotification(params);
    }

    public final void clearPromoteNotification() {
        this.notificationManagerCompat.cancel(PROMOTE_NOTIFICATION_ID);
    }

    public final void closeNotificationWithSmartReply(@NotNull String profileId, @NotNull String messageId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(messageId, "messageId");
        executeWithErrorMsg(new MarkPushHasSelectedSmartReply(this.context, new MarkPushHasSelectedSmartReply.Params(profileId, messageId)), "MarkPushHasSelectedSmartReply");
        refreshNotification();
    }

    public final void deleteNotification(@NotNull DeleteNotificationPush push) {
        Intrinsics.checkNotNullParameter(push, "push");
        this.notificationPublisher.deleteNotification(push);
    }

    public final void refreshNotification() {
        this.notificationPublisher.refreshNotification();
    }

    public final void restorePassNotification(@NotNull PasswordRestorePush passwordRestorePush) {
        Intrinsics.checkNotNullParameter(passwordRestorePush, "passwordRestorePush");
        Bundle bundle = new Bundle();
        bundle.putString("account_login", passwordRestorePush.getEmail());
        Bundle bundle2 = new Bundle();
        bundle2.putString("push_uri", passwordRestorePush.getUri());
        bundle2.putBundle("intent_extras", bundle);
        bundle2.putString("push_message_type", "PushRestore");
        UrlPendingIntentFactory urlPendingIntentFactory = new UrlPendingIntentFactory(this.context, new RedirectLogger());
        String uri = passwordRestorePush.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
        Notification notificationBuild = new NotificationCompat.Builder(this.context, NotificationChannelsCompat.from(this.context).getInfoChannelId()).setContentTitle(passwordRestorePush.getTitle()).setContentText(this.context.getString(ru.mail.mailapp.R.string.restore_push_action)).setSmallIcon(2131232767).setContentIntent(urlPendingIntentFactory.resolve(uri, passwordRestorePush.hashCode(), 1, bundle, bundle2, null)).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(this.context.getString(ru.mail.mailapp.R.string.restore_push_action))).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        this.notificationManagerCompat.notify(PASS_RESTORE_NOTIFICATION_ID, notificationBuild);
    }

    public final void showCalendarNotification(@NotNull CalendarNotificationPush pushMessage) {
        Intrinsics.checkNotNullParameter(pushMessage, "pushMessage");
        NotificationPublisher.showCalendarNotification$default(this.notificationPublisher, pushMessage, null, 2, null);
    }

    public final void showCallerNotification(@NotNull CallerIdentNotificationParams notificationParams) {
        Intrinsics.checkNotNullParameter(notificationParams, "notificationParams");
        Notification notificationBuild = new NotificationCompat.Builder(this.context, NotificationChannelsCompat.from(this.context).getCallerInfoChannelId()).setContentTitle(notificationParams.getTitle()).setContentText(notificationParams.getText()).setPriority(2).setSmallIcon(2131232767).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(notificationParams.getText())).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        this.notificationManagerCompat.notify(CALLER_INFO_NOTIFICATION_ID, notificationBuild);
    }

    public final void showChallengeNotification(@NotNull String title, @NotNull String text) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(text, "text");
        String infoChannelId = NotificationChannelsCompat.from(this.context).getInfoChannelId();
        Intent intent = new Intent(this.context, (Class<?>) SplashScreenActivity.class);
        intent.putExtra(CHALLENGE_NOTIFICATION_EXTRA, 1);
        PendingIntent activity$default = PendingIntentCreator.getActivity$default(this.context, 0, intent, PendingIntentUtils.INSTANCE.getPendingIntentFlags(false), false, 16, null);
        if (activity$default != null) {
            Notification notificationBuild = new NotificationCompat.Builder(this.context, infoChannelId).setContentTitle(title).setContentText(text).setPriority(1).setSmallIcon(2131232767).setAutoCancel(true).setContentIntent(activity$default).setStyle(new NotificationCompat.BigTextStyle().bigText(text)).build();
            Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
            this.notificationManagerCompat.notify(PROMOTE_NOTIFICATION_ID, notificationBuild);
        }
    }

    public final void showErrorNotification(@NotNull String title, @NotNull String message, @NotNull String profileId, long folderId) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        this.notificationPublisher.showErrorNotification(title, message, profileId, folderId);
    }

    public final void showNotification(@NotNull NewMailPush pushMessage) {
        Intrinsics.checkNotNullParameter(pushMessage, "pushMessage");
        NotificationPublisher.showNotification$default(this.notificationPublisher, pushMessage, null, 2, null);
        this.showNotificationTaskListener.onNewMailPush(pushMessage, this.context);
    }

    public final void showPortalNotification(@NotNull PortalPush message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Portal.Notifications.handler().handlePush(message.getApp(), message.getTitle(), message.getBody(), message.getDeepLink(), message.getButtons(), Integer.valueOf(message.hashCode()), message.getPushCampaign(), message.getEmailFromPush(), message.getImgUrl(), message.getImgType(), message.getLangFilter(), message.getOnOpenAnalyticUrl(), message.getSummaryText());
    }

    public final void showPromoteNotification(@NotNull PromoteUrlPushMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        NotificationPublisher.showPromoteNotification$default(this.notificationPublisher, message, null, 2, null);
    }

    public final void showRestoreAuthFlowNotification(long secondsPassed, @NotNull ReturnParams returnUserParams) {
        Intrinsics.checkNotNullParameter(returnUserParams, "returnUserParams");
        new SessionRestoreHelper(this.context).incrementUsages();
        MailAppAnalytics mailAppAnalyticsAnalytics = MailAppDependencies.analytics(this.context);
        String strEvaluate = new TimeDelayEvaluator().evaluate((int) secondsPassed);
        ReturnParams.Companion companion = ReturnParams.INSTANCE;
        mailAppAnalyticsAnalytics.sendAnalyticRestoreShown(strEvaluate, companion.resolveName(returnUserParams), companion.resolveIsRestore(returnUserParams), companion.resolveHasAccounts(returnUserParams));
        Notification notificationBuild = new NotificationCompat.Builder(this.context, NotificationChannelsCompat.from(this.context).getInfoChannelId()).setContentTitle(this.context.getString(returnUserParams.getNotificationTitleId())).setContentText(this.context.getString(returnUserParams.getNotificationTextId())).setSmallIcon(2131232767).setContentIntent(NotificationIntentFactory.forRestoreAuthFlow(this.context, returnUserParams)).setDeleteIntent(NotificationIntentFactory.forRestoreIgnored(this.context)).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(this.context.getString(returnUserParams.getNotificationTextId()))).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        this.notificationManagerCompat.notify(AUTH_INTERRUPTED_ID, notificationBuild);
    }

    public final void showRestoreAuthSDKFlowNotification(@StringRes int notificationTitleId, @StringRes int notificationTextId, @Nullable PendingIntent pendingIntent) {
        Notification notificationBuild = new NotificationCompat.Builder(this.context, NotificationChannelsCompat.from(this.context).getInfoChannelId()).setContentTitle(this.context.getString(notificationTitleId)).setContentText(this.context.getString(notificationTextId)).setSmallIcon(2131232767).setContentIntent(pendingIntent).setDeleteIntent(NotificationIntentFactory.forRestoreIgnored(this.context)).setAutoCancel(true).setStyle(new NotificationCompat.BigTextStyle().bigText(this.context.getString(notificationTextId))).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        this.notificationManagerCompat.notify(AUTH_INTERRUPTED_ID, notificationBuild);
    }

    public final void showStoriesNotification(@NotNull String title, @NotNull String text, @NotNull String storyId) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(storyId, "storyId");
        String infoChannelId = NotificationChannelsCompat.from(this.context).getInfoChannelId();
        Intent intent = new Intent(this.context, (Class<?>) SplashScreenActivity.class);
        intent.putExtra(STORIES_NOTIFICATION_EXTRA, 1);
        intent.putExtra("StoryId", storyId);
        PendingIntent activity$default = PendingIntentCreator.getActivity$default(this.context, 0, intent, PendingIntentUtils.INSTANCE.getPendingIntentFlags(false), false, 16, null);
        if (activity$default != null) {
            Notification notificationBuild = new NotificationCompat.Builder(this.context, infoChannelId).setContentTitle(title).setContentText(text).setPriority(1).setSmallIcon(2131232767).setAutoCancel(true).setContentIntent(activity$default).setStyle(new NotificationCompat.BigTextStyle().bigText(text)).build();
            Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
            this.notificationManagerCompat.notify(PROMOTE_NOTIFICATION_ID, notificationBuild);
        }
    }

    public final void showWalletNotification(@NotNull WalletNotificationPush pushMessage) {
        Intrinsics.checkNotNullParameter(pushMessage, "pushMessage");
        NotificationPublisher.showWalletNotification$default(this.notificationPublisher, pushMessage, null, 2, null);
    }

    public final void updateNotificationCount(@NotNull CountPush countPush) {
        Intrinsics.checkNotNullParameter(countPush, "countPush");
        this.notificationPublisher.updateNotificationCount(countPush);
    }

    public final void updateNotificationsAfterMoveMsg(@NotNull MovePush movePush) {
        Intrinsics.checkNotNullParameter(movePush, "movePush");
        this.notificationPublisher.updateNotificationsAfterMoveMsg(movePush);
    }

    public final void showErrorNotification(@NotNull String title, @NotNull String message) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        this.notificationPublisher.showErrorNotification(title, message);
    }
}
