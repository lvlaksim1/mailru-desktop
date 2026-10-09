package ru.mail.util.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.IntentActionsProvider;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.config.section.PortalConfigDto;
import ru.mail.core.di.ConfigModuleEntryPoint;
import ru.mail.locator.Locator;
import ru.mail.logic.navigation.executor.AppContextExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.portal.app.adapter.notifications.PortalPushButton;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0002\n\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lru/mail/util/push/PortalPushButtonReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "AwaitCommand", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPortalPushButtonReceiver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalPushButtonReceiver.kt\nru/mail/util/push/PortalPushButtonReceiver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,118:1\n1869#2,2:119\n1#3:121\n*S KotlinDebug\n*F\n+ 1 PortalPushButtonReceiver.kt\nru/mail/util/push/PortalPushButtonReceiver\n*L\n58#1:119,2\n*E\n"})
public final class PortalPushButtonReceiver extends BroadcastReceiver {

    @NotNull
    public static final String EMAIL_QUERY_PARAMETER_NAME = "email";
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014¨\u0006\u0012"}, d2 = {"Lru/mail/util/push/PortalPushButtonReceiver$AwaitCommand;", "Lru/mail/util/push/BaseAwaitCommand;", "", "context", "Landroid/content/Context;", "notificationId", "", "intentExtras", "Landroid/os/Bundle;", "pendingResult", "Landroid/content/BroadcastReceiver$PendingResult;", "url", "<init>", "(Landroid/content/Context;ILandroid/os/Bundle;Landroid/content/BroadcastReceiver$PendingResult;Ljava/lang/String;)V", "execute", "", "contextExecutor", "Lru/mail/logic/navigation/executor/AppContextExecutor;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class AwaitCommand extends BaseAwaitCommand<String> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AwaitCommand(@NotNull Context context, int i10, @Nullable Bundle bundle, @NotNull BroadcastReceiver.PendingResult pendingResult, @NotNull String url) {
            super(context, i10, bundle, pendingResult, url);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(pendingResult, "pendingResult");
            Intrinsics.checkNotNullParameter(url, "url");
        }

        @Override // ru.mail.util.push.BaseAwaitCommand
        protected void execute(@NotNull AppContextExecutor contextExecutor) {
            Intrinsics.checkNotNullParameter(contextExecutor, "contextExecutor");
            String params = getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            findPathFor(params).perform(contextExecutor);
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0038 A[PHI: r3
      0x0038: PHI (r3v9 'element' android.net.Uri) = (r3v1 'element' android.net.Uri), (r3v0 'element' android.net.Uri) binds: [B:23:0x009b, B:9:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        int i10;
        String stringExtra;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (Intrinsics.areEqual(IntentActionsProvider.actionPortalPushButton, intent.getAction())) {
            int i11 = -1;
            int intExtra = intent.getIntExtra("auto_close_notificationId", -1);
            String stringExtra2 = intent.getStringExtra("push_uri");
            if (stringExtra2 != null) {
                Uri element = Uri.parse(stringExtra2);
                if (intent.hasExtra("portal_push_app_id") || element.getPath() != null) {
                    String stringExtra3 = intent.getStringExtra("portal_push_app_id");
                    Intrinsics.checkNotNull(stringExtra3);
                    String stringExtra4 = intent.getStringExtra("portal_push_campaign");
                    String stringExtra5 = intent.getStringExtra("portal_push_email");
                    if (stringExtra5 != null) {
                        element = element.buildUpon().appendQueryParameter("email", stringExtra5).build();
                    }
                    PortalConfigDto.NotificationsDto notifications = ConfigModuleEntryPoint.INSTANCE.providePortalConfig(context).getNotifications();
                    ArrayList<PortalPushButton> parcelableArrayListExtra = intent.getParcelableArrayListExtra("portal_push_buttons");
                    PortalPushAnalyticParamsResolver portalPushAnalyticParamsResolver = new PortalPushAnalyticParamsResolver(notifications);
                    Intrinsics.checkNotNullExpressionValue(element, "element");
                    Map<String, String> mapResolve = portalPushAnalyticParamsResolver.resolve(element, parcelableArrayListExtra);
                    PortalPushButton portalPushButton = null;
                    if (parcelableArrayListExtra != null) {
                        for (PortalPushButton portalPushButton2 : parcelableArrayListExtra) {
                            if (Intrinsics.areEqual(portalPushButton2.getDeepLink(), stringExtra2)) {
                                portalPushButton = portalPushButton2;
                            }
                        }
                    }
                    if (portalPushButton != null) {
                        MailAppAnalytics mailAppAnalyticsAnalytics = MailAppDependencies.analytics(context);
                        String title = portalPushButton.getTitle();
                        String path = element.getPath();
                        if (path == null) {
                            path = "unknown";
                        }
                        mailAppAnalyticsAnalytics.onPortalPushButtonClicked(stringExtra3, title, path, mapResolve, stringExtra4, stringExtra5);
                        if (portalPushButton.getCloseNotificationByClick()) {
                            i11 = intExtra;
                        } else {
                            element = element.buildUpon().appendQueryParameter("notification_id", String.valueOf(intExtra)).build();
                        }
                        if (portalPushButton.getNeedSendOpenAction() && (stringExtra = intent.getStringExtra("EXTRA_PUSH_ANALYTIC_OPEN_URL")) != null) {
                            AnalyticUrlRequest.execute(context, stringExtra);
                        }
                        i10 = i11;
                    } else {
                        i10 = intExtra;
                    }
                } else {
                    i10 = intExtra;
                }
                Bundle bundleExtra = intent.getBundleExtra("intent_extras");
                BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
                Intrinsics.checkNotNullExpressionValue(pendingResultGoAsync, "goAsync(...)");
                String string = element.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                new AwaitCommand(context, i10, bundleExtra, pendingResultGoAsync, string).execute((ExecutorSelector) Locator.INSTANCE.locate(context, RequestArbiter.class));
            }
        }
    }
}
