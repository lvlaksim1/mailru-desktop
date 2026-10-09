package ru.mail.util.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.vk.pushme.PushMeSdk;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.IntentActionsProvider;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.config.section.PortalConfigDto;
import ru.mail.core.di.ConfigModuleEntryPoint;
import ru.mail.locator.Locator;
import ru.mail.logic.navigation.executor.AppContextExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lru/mail/util/push/PushUrlReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "AwaitCommand", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushUrlReceiver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushUrlReceiver.kt\nru/mail/util/push/PushUrlReceiver\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,108:1\n1#2:109\n*E\n"})
public final class PushUrlReceiver extends BroadcastReceiver {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014¨\u0006\u0012"}, d2 = {"Lru/mail/util/push/PushUrlReceiver$AwaitCommand;", "Lru/mail/util/push/BaseAwaitCommand;", "", "context", "Landroid/content/Context;", "notificationId", "", "intentExtras", "Landroid/os/Bundle;", "pendingResult", "Landroid/content/BroadcastReceiver$PendingResult;", "url", "<init>", "(Landroid/content/Context;ILandroid/os/Bundle;Landroid/content/BroadcastReceiver$PendingResult;Ljava/lang/String;)V", "execute", "", "contextExecutor", "Lru/mail/logic/navigation/executor/AppContextExecutor;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        String stringExtra;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (!Intrinsics.areEqual(IntentActionsProvider.actionOpenPushUrl, intent.getAction()) || (stringExtra = intent.getStringExtra("push_uri")) == null) {
            return;
        }
        if (intent.hasExtra("push_message_type")) {
            String stringExtra2 = intent.getStringExtra("push_message_type");
            if (stringExtra2 != null) {
                MailAppDependencies.analytics(context).onClickPushMessageReceivedAnalytics(stringExtra2);
            }
        } else if (intent.hasExtra("push_type")) {
            String stringExtra3 = intent.getStringExtra("push_type");
            if (stringExtra3 != null) {
                MailAppDependencies.analytics(context).sendPromotePushOpened(stringExtra3);
            }
        } else if (intent.hasExtra("portal_push_app_id")) {
            String stringExtra4 = intent.getStringExtra("portal_push_app_id");
            Intrinsics.checkNotNull(stringExtra4);
            String stringExtra5 = intent.getStringExtra("portal_push_path");
            Uri uri = Uri.parse(intent.getStringExtra("push_uri"));
            String stringExtra6 = intent.getStringExtra("portal_push_campaign");
            String stringExtra7 = intent.getStringExtra("portal_push_email");
            PortalConfigDto.NotificationsDto notifications = ConfigModuleEntryPoint.INSTANCE.providePortalConfig(context).getNotifications();
            ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("portal_push_buttons");
            PortalPushAnalyticParamsResolver portalPushAnalyticParamsResolver = new PortalPushAnalyticParamsResolver(notifications);
            Intrinsics.checkNotNull(uri);
            MailAppDependencies.analytics(context).onPortalPushClicked(stringExtra4, stringExtra5, portalPushAnalyticParamsResolver.resolve(uri, parcelableArrayListExtra), stringExtra6, stringExtra7);
        }
        int intExtra = intent.getIntExtra("auto_close_notificationId", -1);
        Bundle bundleExtra = intent.getBundleExtra("intent_extras");
        BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        Intrinsics.checkNotNullExpressionValue(pendingResultGoAsync, "goAsync(...)");
        new AwaitCommand(context, intExtra, bundleExtra, pendingResultGoAsync, stringExtra).execute((ExecutorSelector) Locator.INSTANCE.locate(context, RequestArbiter.class));
        String stringExtra8 = intent.getStringExtra("EXTRA_PUSH_ANALYTIC_OPEN_URL");
        if (stringExtra8 != null) {
            AnalyticUrlRequest.execute(context, stringExtra8);
        }
        Long lValueOf = Long.valueOf(intent.getLongExtra(BaseAwaitCommand.EXTRA_PUSH_ME_SDK_PUSH_ID, -1L));
        if (lValueOf.longValue() <= 0) {
            lValueOf = null;
        }
        if (lValueOf != null) {
            PushMeSdk.INSTANCE.onNotificationClicked(Long.valueOf(lValueOf.longValue()));
        }
    }
}
