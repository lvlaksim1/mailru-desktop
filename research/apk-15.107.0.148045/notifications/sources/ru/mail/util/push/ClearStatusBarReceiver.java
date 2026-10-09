package ru.mail.util.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.entity.core.CommonCode;
import dagger.Lazy;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.IntentActionsProvider;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.android_utils.SdkUtils;
import ru.mail.config.section.PortalConfigDto;
import ru.mail.logic.navigation.restoreauth.ReturnParams;
import ru.mail.mailapp.service.MailServiceImpl;
import ru.mail.mailbox.cmd.CompleteObserver;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.portal.app.adapter.notifications.PortalPushButton;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\b\"\u0004\b\u0012\u0010\n¨\u0006\u001f"}, d2 = {"Lru/mail/util/push/ClearStatusBarReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "analytics", "Ldagger/Lazy;", "Lru/mail/analytics/MailAppAnalytics;", "getAnalytics", "()Ldagger/Lazy;", "setAnalytics", "(Ldagger/Lazy;)V", "notificationHandler", "Lru/mail/util/push/NotificationHandler;", "getNotificationHandler", "setNotificationHandler", "portalConfig", "Lru/mail/config/section/PortalConfigDto;", "getPortalConfig", "setPortalConfig", "onReceive", "", "context", "Landroid/content/Context;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "onPortalNotificationSwiped", "onMailMsgNotificationSwiped", "onRestoreAuthFlowDenied", "onCalendarNotificationSwiped", "onWalletNotificationSwiped", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
public final class ClearStatusBarReceiver extends Hilt_ClearStatusBarReceiver {

    @Inject
    public Lazy<MailAppAnalytics> analytics;

    @Inject
    public Lazy<NotificationHandler> notificationHandler;

    @Inject
    public Lazy<PortalConfigDto> portalConfig;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("ClearStatusBarReceiver");

    private final void onCalendarNotificationSwiped(Intent intent) {
        String stringExtra = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_UID);
        if (stringExtra == null) {
            stringExtra = "";
        }
        String stringExtra2 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_TYPE);
        if (stringExtra2 == null) {
            stringExtra2 = "";
        }
        String stringExtra3 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_SUBTYPE);
        getAnalytics().get().onCalendarPushDismissed(stringExtra, stringExtra2, stringExtra3 != null ? stringExtra3 : "");
    }

    private final void onMailMsgNotificationSwiped(Intent intent) {
        Log log = LOG;
        log.d("Notification clear status messages count");
        String stringExtra = intent.getStringExtra(NotificationUpdater.EXTRA_ACCOUNT_ID);
        String[] stringArrayExtra = intent.getStringArrayExtra("message_id");
        ClearNotificationParams clearNotificationParamsBuild = new ClearNotificationParams.Builder(stringExtra).setMessageIds(stringArrayExtra != null ? ArraysKt.toList(stringArrayExtra) : null).dontUpdateNotifications().build();
        final BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        MailAppAnalytics mailAppAnalytics = getAnalytics().get();
        String strHasSmartChoices = MailServiceImpl.hasSmartChoices(intent);
        Intrinsics.checkNotNullExpressionValue(strHasSmartChoices, "hasSmartChoices(...)");
        boolean zHasStageSmartReply = MailServiceImpl.hasStageSmartReply(intent);
        Boolean boolExtractIsDefaultSmartReply = MailServiceImpl.extractIsDefaultSmartReply(intent);
        Intrinsics.checkNotNullExpressionValue(boolExtractIsDefaultSmartReply, "extractIsDefaultSmartReply(...)");
        boolean zBooleanValue = boolExtractIsDefaultSmartReply.booleanValue();
        String strExtractPushType = MailServiceImpl.extractPushType(intent);
        Intrinsics.checkNotNullExpressionValue(strExtractPushType, "extractPushType(...)");
        String strExtractCategory = MailServiceImpl.extractCategory(intent);
        Intrinsics.checkNotNullExpressionValue(strExtractCategory, "extractCategory(...)");
        mailAppAnalytics.sendNotificationSwipedInfo(strHasSmartChoices, zHasStageSmartReply, zBooleanValue, strExtractPushType, strExtractCategory, MailServiceImpl.extractIsReminder(intent));
        log.i("Message swiped params: " + clearNotificationParamsBuild);
        NotificationHandler notificationHandler = getNotificationHandler().get();
        Intrinsics.checkNotNull(clearNotificationParamsBuild);
        notificationHandler.clearNotification(clearNotificationParamsBuild).observe(Schedulers.immediate(), new CompleteObserver<Void>() { // from class: ru.mail.util.push.ClearStatusBarReceiver.onMailMsgNotificationSwiped.1
            @Override // ru.mail.mailbox.cmd.CompleteObserver
            public void onComplete() {
                pendingResultGoAsync.finish();
            }

            @Override // ru.mail.mailbox.cmd.CompleteObserver, ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onError(Exception exception) {
                pendingResultGoAsync.finish();
            }
        });
    }

    private final void onPortalNotificationSwiped(Intent intent) {
        LOG.d("Portal notification swiped");
        String stringExtra = intent.getStringExtra("portal_push_app_id");
        String stringExtra2 = intent.getStringExtra("portal_push_path");
        Uri uri = Uri.parse(intent.getStringExtra("push_uri"));
        String stringExtra3 = intent.getStringExtra("portal_push_campaign");
        String stringExtra4 = intent.getStringExtra("portal_push_email");
        List<PortalPushButton> parcelableArrayListExtra = SdkUtils.hasTiramisu() ? intent.getParcelableArrayListExtra("portal_push_buttons", PortalPushButton.class) : intent.getParcelableArrayListExtra("portal_push_buttons");
        if (parcelableArrayListExtra == null) {
            parcelableArrayListExtra = CollectionsKt.emptyList();
        }
        PortalPushAnalyticParamsResolver portalPushAnalyticParamsResolver = new PortalPushAnalyticParamsResolver(getPortalConfig().get().getNotifications());
        Intrinsics.checkNotNull(uri);
        getAnalytics().get().onPortalPushDismissed(stringExtra, stringExtra2, portalPushAnalyticParamsResolver.resolve(uri, parcelableArrayListExtra), stringExtra3, stringExtra4);
    }

    private final void onRestoreAuthFlowDenied(Intent intent) {
        ReturnParams.Companion companion = ReturnParams.INSTANCE;
        ReturnParams returnParamsFromIntent = companion.fromIntent(intent);
        getAnalytics().get().sendAnalyticRestoreDeclined(companion.resolveName(returnParamsFromIntent), companion.resolveIsRestore(returnParamsFromIntent), companion.resolveHasAccounts(returnParamsFromIntent));
    }

    private final void onWalletNotificationSwiped(Intent intent) {
        String stringExtra = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_UID);
        if (stringExtra == null) {
            stringExtra = "";
        }
        String stringExtra2 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_TYPE);
        if (stringExtra2 == null) {
            stringExtra2 = "";
        }
        String stringExtra3 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_SUBTYPE);
        if (stringExtra3 == null) {
            stringExtra3 = "";
        }
        String stringExtra4 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_REMINDER);
        getAnalytics().get().onWalletPushDismissed(stringExtra, stringExtra2, stringExtra3, stringExtra4 != null ? stringExtra4 : "");
    }

    @NotNull
    public final Lazy<MailAppAnalytics> getAnalytics() {
        Lazy<MailAppAnalytics> lazy = this.analytics;
        if (lazy != null) {
            return lazy;
        }
        Intrinsics.throwUninitializedPropertyAccessException("analytics");
        return null;
    }

    @NotNull
    public final Lazy<NotificationHandler> getNotificationHandler() {
        Lazy<NotificationHandler> lazy = this.notificationHandler;
        if (lazy != null) {
            return lazy;
        }
        Intrinsics.throwUninitializedPropertyAccessException("notificationHandler");
        return null;
    }

    @NotNull
    public final Lazy<PortalConfigDto> getPortalConfig() {
        Lazy<PortalConfigDto> lazy = this.portalConfig;
        if (lazy != null) {
            return lazy;
        }
        Intrinsics.throwUninitializedPropertyAccessException("portalConfig");
        return null;
    }

    @Override // ru.mail.util.push.Hilt_ClearStatusBarReceiver, android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        super.onReceive(context, intent);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String action = intent.getAction();
        if (Intrinsics.areEqual(action, IntentActionsProvider.actionClearNotification)) {
            onMailMsgNotificationSwiped(intent);
            return;
        }
        if (Intrinsics.areEqual(action, IntentActionsProvider.actionRemoveRestoreNotification)) {
            onRestoreAuthFlowDenied(intent);
            return;
        }
        if (Intrinsics.areEqual(action, IntentActionsProvider.actionClearPortalNotification)) {
            onPortalNotificationSwiped(intent);
        } else if (Intrinsics.areEqual(action, IntentActionsProvider.actionClearCalendarNotification)) {
            onCalendarNotificationSwiped(intent);
        } else if (Intrinsics.areEqual(action, IntentActionsProvider.actionClearWalletNotification)) {
            onWalletNotificationSwiped(intent);
        }
    }

    public final void setAnalytics(@NotNull Lazy<MailAppAnalytics> lazy) {
        Intrinsics.checkNotNullParameter(lazy, "<set-?>");
        this.analytics = lazy;
    }

    public final void setNotificationHandler(@NotNull Lazy<NotificationHandler> lazy) {
        Intrinsics.checkNotNullParameter(lazy, "<set-?>");
        this.notificationHandler = lazy;
    }

    public final void setPortalConfig(@NotNull Lazy<PortalConfigDto> lazy) {
        Intrinsics.checkNotNullParameter(lazy, "<set-?>");
        this.portalConfig = lazy;
    }
}
