package ru.mail.util.push;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spanned;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.PendingIntentUtils;
import ru.mail.portal.app.adapter.notifications.handler.LocalPortalNotificationsHandler;
import ru.mail.router.NotificationNavigationType;
import ru.mail.router.PendingIntentNavigator;
import ru.mail.router.PortalPendingIntentNavigator;
import ru.mail.router.RedirectLogger;
import ru.mail.router.data.PortalIntentParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\b\u001a\u00070\t¢\u0006\u0002\b\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lru/mail/util/push/LocalPortalNotificationHandlerImpl;", "Lru/mail/portal/app/adapter/notifications/handler/LocalPortalNotificationsHandler;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "notificationManagerCompat", "Landroidx/core/app/NotificationManagerCompat;", "Lorg/jspecify/annotations/NonNull;", "portalNavigator", "Lru/mail/router/PortalPendingIntentNavigator;", "getPortalNavigator", "()Lru/mail/router/PortalPendingIntentNavigator;", "portalNavigator$delegate", "Lkotlin/Lazy;", "handlePush", "", "app", "", "title", "text", "deepLink", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LocalPortalNotificationHandlerImpl implements LocalPortalNotificationsHandler {

    @NotNull
    private final Context context;

    @NotNull
    private final NotificationManagerCompat notificationManagerCompat;

    /* JADX INFO: renamed from: portalNavigator$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy portalNavigator;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("LocalPortalNotificationHandlerImpl");

    public LocalPortalNotificationHandlerImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(...)");
        this.notificationManagerCompat = notificationManagerCompatFrom;
        this.portalNavigator = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: ru.mail.util.push.i
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LocalPortalNotificationHandlerImpl.portalNavigator_delegate$lambda$0(this.f101076a);
            }
        });
    }

    private final PortalPendingIntentNavigator getPortalNavigator() {
        return (PortalPendingIntentNavigator) this.portalNavigator.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PortalPendingIntentNavigator portalNavigator_delegate$lambda$0(LocalPortalNotificationHandlerImpl localPortalNotificationHandlerImpl) {
        return new PortalPendingIntentNavigator(localPortalNotificationHandlerImpl.context, new RedirectLogger());
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @Override // ru.mail.portal.app.adapter.notifications.handler.LocalPortalNotificationsHandler
    public void handlePush(@Nullable String app, @NotNull String title, @Nullable String text, @NotNull String deepLink) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        try {
            Uri uri = Uri.parse(deepLink);
            String authority = app == null ? uri.getAuthority() : app;
            if (authority == null || StringsKt.isBlank(authority)) {
                LOG.w("App ID is wrong");
                throw new IllegalStateException("Incorrect deep link: " + deepLink + " or payload field \"app\": " + app);
            }
            Bundle bundle = new Bundle();
            Bundle bundle2 = new Bundle();
            bundle2.putString("push_uri", deepLink);
            bundle2.putBundle("intent_extras", bundle);
            bundle2.putString("portal_push_app_id", authority);
            bundle2.putString("portal_push_path", uri.getPath());
            int iHashCode = (app + deepLink).hashCode();
            String string = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            PendingIntent pendingIntent$default = PendingIntentNavigator.getPendingIntent$default(getPortalNavigator(), new PortalIntentParams(string, null, iHashCode, 1, PendingIntentUtils.Companion.getPendingIntentFlags$default(PendingIntentUtils.INSTANCE, false, 1, null), new Bundle(), NotificationNavigationType.Url.INSTANCE), bundle2, null, 4, null);
            String infoChannelId = NotificationChannelsCompat.from(this.context).getInfoChannelId();
            Spanned spannedFromHtml = HtmlCompat.fromHtml(title, 63);
            Intrinsics.checkNotNullExpressionValue(spannedFromHtml, "fromHtml(...)");
            NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this.context, infoChannelId).setContentTitle(spannedFromHtml).setSmallIcon(2131232767).setColor(new NotificationConfiguration(this.context).getLightColor()).setContentIntent(pendingIntent$default).setAutoCancel(true);
            Intrinsics.checkNotNullExpressionValue(autoCancel, "setAutoCancel(...)");
            if (ContextCompat.checkSelfPermission(this.context, "android.permission.POST_NOTIFICATIONS") != 0) {
                return;
            }
            this.notificationManagerCompat.notify(iHashCode, autoCancel.build());
        } catch (Exception e10) {
            LOG.e("Failed to show local notification", e10);
        }
    }
}
