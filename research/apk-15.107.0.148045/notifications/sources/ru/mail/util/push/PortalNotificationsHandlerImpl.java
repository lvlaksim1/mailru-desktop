package ru.mail.util.push;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spanned;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.text.HtmlCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.IntentActionsProvider;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.config.section.PortalConfigDto;
import ru.mail.core.di.ConfigModuleEntryPoint;
import ru.mail.portal.app.adapter.TabAppAdapter;
import ru.mail.portal.app.adapter.di.Portal;
import ru.mail.portal.app.adapter.notifications.PortalPushButton;
import ru.mail.portal.app.adapter.notifications.handler.PortalNotificationsHandler;
import ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings;
import ru.mail.portal.kit.PortalKit;
import ru.mail.router.NotificationNavigationType;
import ru.mail.router.RedirectLogger;
import ru.mail.util.log.Log;
import ru.mail.util.push.notification.utill.NotificationSummaryTextResolver;
import ru.mail.util.push.notification.utill.PortalPendingIntentResolver;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0091\u0001\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000eH\u0016¢\u0006\u0002\u0010\u001eJz\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\b\u0010$\u001a\u0004\u0018\u00010\u000e2\u0006\u0010%\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u000e2\u0006\u0010&\u001a\u00020\u0016H\u0002J\u0012\u0010'\u001a\u00020(2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002Jn\u0010)\u001a\u00020\u00162\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u00070\u0007¢\u0006\u0002\b\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lru/mail/util/push/PortalNotificationsHandlerImpl;", "Lru/mail/portal/app/adapter/notifications/handler/PortalNotificationsHandler;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "notificationManagerCompat", "Landroidx/core/app/NotificationManagerCompat;", "Lorg/jspecify/annotations/NonNull;", "config", "Lru/mail/config/section/PortalConfigDto$NotificationsDto;", "handlePush", "", "app", "", "title", "body", "deepLink", "buttons", "", "Lru/mail/portal/app/adapter/notifications/PortalPushButton;", "notificationId", "", "pushCampaign", "emailFromPush", "imgUrl", "imgType", "langFilter", "openUrl", "summaryTextFromPayload", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "createPortalPushPendingIntent", "Landroid/app/PendingIntent;", "actionResourceId", "navigationType", "Lru/mail/router/NotificationNavigationType;", "path", "appId", "requestCode", "isPushApplicable", "", "createNotificationId", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPortalNotificationsHandlerImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalNotificationsHandlerImpl.kt\nru/mail/util/push/PortalNotificationsHandlerImpl\n+ 2 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,273:1\n38#2,4:274\n1869#3,2:278\n*S KotlinDebug\n*F\n+ 1 PortalNotificationsHandlerImpl.kt\nru/mail/util/push/PortalNotificationsHandlerImpl\n*L\n136#1:274,4\n151#1:278,2\n*E\n"})
public final class PortalNotificationsHandlerImpl implements PortalNotificationsHandler {

    @NotNull
    private final PortalConfigDto.NotificationsDto config;

    @NotNull
    private final Context context;

    @NotNull
    private final NotificationManagerCompat notificationManagerCompat;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PortalNotificationsHandlerImpl");

    public PortalNotificationsHandlerImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(context);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(...)");
        this.notificationManagerCompat = notificationManagerCompatFrom;
        this.config = ConfigModuleEntryPoint.INSTANCE.providePortalConfig(context).getNotifications();
    }

    private final int createNotificationId(String app, String title, String body, String deepLink, List<PortalPushButton> buttons, String pushCampaign, String emailFromPush, String imgUrl, String imgType, String langFilter) {
        return (app + title + body + deepLink + buttons + pushCampaign + emailFromPush + imgUrl + imgType + langFilter).hashCode();
    }

    private final PendingIntent createPortalPushPendingIntent(Context context, String actionResourceId, NotificationNavigationType navigationType, List<PortalPushButton> buttons, String pushCampaign, String emailFromPush, String path, String appId, String deepLink, int notificationId, String openUrl, int requestCode) {
        Bundle bundle = new Bundle();
        if (emailFromPush != null && emailFromPush.length() != 0) {
            bundle.putString("account_login", emailFromPush);
            bundle.putBoolean("extra_change_account", true);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("push_uri", deepLink);
        bundle2.putBundle("intent_extras", bundle);
        bundle2.putString("portal_push_app_id", appId);
        bundle2.putString("portal_push_path", path);
        bundle2.putString("portal_push_campaign", pushCampaign);
        bundle2.putString("portal_push_email", emailFromPush);
        bundle2.putString("EXTRA_PUSH_ANALYTIC_OPEN_URL", openUrl);
        List<PortalPushButton> list = buttons;
        if (list != null && !list.isEmpty()) {
            Intrinsics.checkNotNull(buttons, "null cannot be cast to non-null type java.util.ArrayList<ru.mail.portal.app.adapter.notifications.PortalPushButton>");
            bundle2.putParcelableArrayList("portal_push_buttons", (ArrayList) buttons);
        }
        bundle2.putInt("auto_close_notificationId", notificationId);
        return new PortalPendingIntentResolver(context, new RedirectLogger()).resolve(deepLink, buttons, actionResourceId, navigationType, notificationId, requestCode, bundle, bundle2);
    }

    private final boolean isPushApplicable(String langFilter) {
        if (langFilter == null || langFilter.length() == 0) {
            return true;
        }
        return Intrinsics.areEqual(this.context.getResources().getConfiguration().locale.getLanguage(), new Locale(langFilter).getLanguage());
    }

    @Override // ru.mail.portal.app.adapter.notifications.handler.PortalNotificationsHandler
    public void handlePush(@Nullable String app, @NotNull String title, @Nullable String body, @NotNull String deepLink, @Nullable List<PortalPushButton> buttons, @Nullable Integer notificationId, @Nullable String pushCampaign, @Nullable String emailFromPush, @Nullable String imgUrl, @Nullable String imgType, @Nullable String langFilter, @Nullable String openUrl, @Nullable String summaryTextFromPayload) {
        int iCreateNotificationId;
        String str;
        TabAppAdapter tabAppAdapter;
        Integer iconForNotificationResId;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(deepLink, "deepLink");
        PortalNotificationsSettings portalNotificationsSettings = Portal.Notifications.settings();
        if (!isPushApplicable(langFilter)) {
            LOG.w("This portal notification are not supported for '" + this.context.getResources().getConfiguration().locale.getLanguage() + "' language");
            return;
        }
        try {
            Uri uri = Uri.parse(deepLink);
            String authority = app == null ? uri.getAuthority() : app;
            if (authority == null || StringsKt.isBlank(authority)) {
                LOG.w("App ID could not be parsed");
                throw new IllegalStateException("Incorrect deep link: " + deepLink + " or payload field \"app\": " + app);
            }
            PortalPushAnalyticParamsResolver portalPushAnalyticParamsResolver = new PortalPushAnalyticParamsResolver(this.config);
            Intrinsics.checkNotNull(uri);
            Map<String, String> mapResolve = portalPushAnalyticParamsResolver.resolve(uri, buttons);
            if (notificationId != null) {
                iCreateNotificationId = notificationId.intValue();
                str = emailFromPush;
            } else {
                iCreateNotificationId = createNotificationId(app, title, body, deepLink, buttons, pushCampaign, emailFromPush, imgUrl, imgType, langFilter);
                str = emailFromPush;
            }
            int i10 = iCreateNotificationId;
            Bundle bundle = new Bundle();
            if (str != null && str.length() != 0) {
                bundle.putString("account_login", str);
                bundle.putBoolean("extra_change_account", true);
            }
            if (!portalNotificationsSettings.areNotificationsEnabled(authority)) {
                MailAppDependencies.analytics(this.context).onPortalPushShowingDenied(authority, mapResolve, pushCampaign, str);
                return;
            }
            String str2 = authority;
            PendingIntent pendingIntentCreatePortalPushPendingIntent = createPortalPushPendingIntent(this.context, IntentActionsProvider.actionOpenPushUrl, NotificationNavigationType.Url.INSTANCE, buttons, pushCampaign, str, uri.getPath(), str2, deepLink, i10, openUrl, 1);
            PortalNotificationsHandlerImpl portalNotificationsHandlerImpl = this;
            try {
                int iIntValue = (!portalNotificationsHandlerImpl.config.isUniqueAppIconsEnabled() || (tabAppAdapter = PortalKit.getRepository().getAllTabApps().get(str2)) == null || (iconForNotificationResId = tabAppAdapter.getIconForNotificationResId()) == null) ? 2131232767 : iconForNotificationResId.intValue();
                String strResolve = NotificationSummaryTextResolver.INSTANCE.resolve(summaryTextFromPayload, str2, portalNotificationsHandlerImpl.context);
                String channelId = portalNotificationsSettings.getChannelId(str2);
                Spanned spannedFromHtml = HtmlCompat.fromHtml(title, 63);
                Intrinsics.checkNotNullExpressionValue(spannedFromHtml, "fromHtml(...)");
                NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(portalNotificationsHandlerImpl.context, channelId).setContentTitle(spannedFromHtml).setSmallIcon(iIntValue).setColor(new NotificationConfiguration(portalNotificationsHandlerImpl.context).getLightColor()).setContentIntent(pendingIntentCreatePortalPushPendingIntent).setAutoCancel(true);
                if (iIntValue != 2131232767) {
                    autoCancel.setGroup(str2);
                }
                NotificationCompat.Builder deleteIntent = autoCancel.setDeleteIntent(NotificationIntentFactory.forPortalPushRemoval(portalNotificationsHandlerImpl.context, deepLink, uri.getPath(), str2, pushCampaign, emailFromPush, buttons));
                Intrinsics.checkNotNullExpressionValue(deleteIntent, "setDeleteIntent(...)");
                if (buttons != null) {
                    for (PortalPushButton portalPushButton : buttons) {
                        String title2 = portalPushButton.getTitle();
                        Context context = portalNotificationsHandlerImpl.context;
                        String str3 = IntentActionsProvider.actionPortalPushButton;
                        NotificationNavigationType.PortalAction portalAction = NotificationNavigationType.PortalAction.INSTANCE;
                        String path = Uri.parse(portalPushButton.getDeepLink()).getPath();
                        String deepLink2 = portalPushButton.getDeepLink();
                        PortalNotificationsHandlerImpl portalNotificationsHandlerImpl2 = portalNotificationsHandlerImpl;
                        Map<String, String> map = mapResolve;
                        deleteIntent.addAction(0, title2, portalNotificationsHandlerImpl2.createPortalPushPendingIntent(context, str3, portalAction, buttons, pushCampaign, emailFromPush, path, str2, deepLink2, i10, openUrl, 2));
                        portalNotificationsHandlerImpl = portalNotificationsHandlerImpl2;
                        mapResolve = map;
                    }
                }
                PortalNotificationsHandlerImpl portalNotificationsHandlerImpl3 = portalNotificationsHandlerImpl;
                new PortalNotificationStyler(portalNotificationsHandlerImpl3.context, body, imgUrl, imgType, strResolve).setStyle(deleteIntent);
                portalNotificationsHandlerImpl3.notificationManagerCompat.notify(i10, deleteIntent.build());
                MailAppDependencies.analytics(portalNotificationsHandlerImpl3.context).onPortalPushShown(str2, uri.getPath(), mapResolve, pushCampaign, emailFromPush);
            } catch (Exception e10) {
                e = e10;
                LOG.e("Failed to show portal notification", e);
            }
        } catch (Exception e11) {
            e = e11;
        }
    }
}
