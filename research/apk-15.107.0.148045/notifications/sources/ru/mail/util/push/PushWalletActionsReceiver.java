package ru.mail.util.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.entity.core.CommonCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.IntentActionsProvider;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.locator.Locator;
import ru.mail.logic.navigation.executor.AppContextExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002\n\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lru/mail/util/push/PushWalletActionsReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "WalletAwaitCommand", "Params", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushWalletActionsReceiver extends BroadcastReceiver {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003JQ\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0006HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006#"}, d2 = {"Lru/mail/util/push/PushWalletActionsReceiver$Params;", "", "account", "", "eventUrl", "notificationId", "", "eventUid", "mlType", "mlSubtype", "mlReminder", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getEventUrl", "getNotificationId", "()I", "getEventUid", "getMlType", "getMlSubtype", "getMlReminder", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params {
        public static final int $stable = 0;

        @NotNull
        private final String account;

        @NotNull
        private final String eventUid;

        @Nullable
        private final String eventUrl;

        @NotNull
        private final String mlReminder;

        @NotNull
        private final String mlSubtype;

        @NotNull
        private final String mlType;
        private final int notificationId;

        public Params(@NotNull String account, @Nullable String str, int i10, @NotNull String eventUid, @NotNull String mlType, @NotNull String mlSubtype, @NotNull String mlReminder) {
            Intrinsics.checkNotNullParameter(account, "account");
            Intrinsics.checkNotNullParameter(eventUid, "eventUid");
            Intrinsics.checkNotNullParameter(mlType, "mlType");
            Intrinsics.checkNotNullParameter(mlSubtype, "mlSubtype");
            Intrinsics.checkNotNullParameter(mlReminder, "mlReminder");
            this.account = account;
            this.eventUrl = str;
            this.notificationId = i10;
            this.eventUid = eventUid;
            this.mlType = mlType;
            this.mlSubtype = mlSubtype;
            this.mlReminder = mlReminder;
        }

        public static /* synthetic */ Params copy$default(Params params, String str, String str2, int i10, String str3, String str4, String str5, String str6, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = params.account;
            }
            if ((i11 & 2) != 0) {
                str2 = params.eventUrl;
            }
            if ((i11 & 4) != 0) {
                i10 = params.notificationId;
            }
            if ((i11 & 8) != 0) {
                str3 = params.eventUid;
            }
            if ((i11 & 16) != 0) {
                str4 = params.mlType;
            }
            if ((i11 & 32) != 0) {
                str5 = params.mlSubtype;
            }
            if ((i11 & 64) != 0) {
                str6 = params.mlReminder;
            }
            String str7 = str5;
            String str8 = str6;
            String str9 = str4;
            int i12 = i10;
            return params.copy(str, str2, i12, str3, str9, str7, str8);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccount() {
            return this.account;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEventUrl() {
            return this.eventUrl;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getNotificationId() {
            return this.notificationId;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getEventUid() {
            return this.eventUid;
        }

        @NotNull
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMlType() {
            return this.mlType;
        }

        @NotNull
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getMlSubtype() {
            return this.mlSubtype;
        }

        @NotNull
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getMlReminder() {
            return this.mlReminder;
        }

        @NotNull
        public final Params copy(@NotNull String account, @Nullable String eventUrl, int notificationId, @NotNull String eventUid, @NotNull String mlType, @NotNull String mlSubtype, @NotNull String mlReminder) {
            Intrinsics.checkNotNullParameter(account, "account");
            Intrinsics.checkNotNullParameter(eventUid, "eventUid");
            Intrinsics.checkNotNullParameter(mlType, "mlType");
            Intrinsics.checkNotNullParameter(mlSubtype, "mlSubtype");
            Intrinsics.checkNotNullParameter(mlReminder, "mlReminder");
            return new Params(account, eventUrl, notificationId, eventUid, mlType, mlSubtype, mlReminder);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Params)) {
                return false;
            }
            Params params = (Params) other;
            return Intrinsics.areEqual(this.account, params.account) && Intrinsics.areEqual(this.eventUrl, params.eventUrl) && this.notificationId == params.notificationId && Intrinsics.areEqual(this.eventUid, params.eventUid) && Intrinsics.areEqual(this.mlType, params.mlType) && Intrinsics.areEqual(this.mlSubtype, params.mlSubtype) && Intrinsics.areEqual(this.mlReminder, params.mlReminder);
        }

        @NotNull
        public final String getAccount() {
            return this.account;
        }

        @NotNull
        public final String getEventUid() {
            return this.eventUid;
        }

        @Nullable
        public final String getEventUrl() {
            return this.eventUrl;
        }

        @NotNull
        public final String getMlReminder() {
            return this.mlReminder;
        }

        @NotNull
        public final String getMlSubtype() {
            return this.mlSubtype;
        }

        @NotNull
        public final String getMlType() {
            return this.mlType;
        }

        public final int getNotificationId() {
            return this.notificationId;
        }

        public int hashCode() {
            int iHashCode = this.account.hashCode() * 31;
            String str = this.eventUrl;
            return ((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.notificationId)) * 31) + this.eventUid.hashCode()) * 31) + this.mlType.hashCode()) * 31) + this.mlSubtype.hashCode()) * 31) + this.mlReminder.hashCode();
        }

        @NotNull
        public String toString() {
            return "Params(account=" + this.account + ", eventUrl=" + this.eventUrl + ", notificationId=" + this.notificationId + ", eventUid=" + this.eventUid + ", mlType=" + this.mlType + ", mlSubtype=" + this.mlSubtype + ", mlReminder=" + this.mlReminder + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lru/mail/util/push/PushWalletActionsReceiver$WalletAwaitCommand;", "Lru/mail/util/push/BaseAwaitCommand;", "Lru/mail/util/push/PushWalletActionsReceiver$Params;", "context", "Landroid/content/Context;", "intentExtras", "Landroid/os/Bundle;", "pendingResult", "Landroid/content/BroadcastReceiver$PendingResult;", "params", "<init>", "(Landroid/content/Context;Landroid/os/Bundle;Landroid/content/BroadcastReceiver$PendingResult;Lru/mail/util/push/PushWalletActionsReceiver$Params;)V", "execute", "", "contextExecutor", "Lru/mail/logic/navigation/executor/AppContextExecutor;", "sendErrorLog", "errorMsg", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class WalletAwaitCommand extends BaseAwaitCommand<Params> {

        @NotNull
        private final Context context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WalletAwaitCommand(@NotNull Context context, @Nullable Bundle bundle, @NotNull BroadcastReceiver.PendingResult pendingResult, @NotNull Params params) {
            super(context, params.getNotificationId(), bundle, pendingResult, params);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(pendingResult, "pendingResult");
            Intrinsics.checkNotNullParameter(params, "params");
            this.context = context;
        }

        private final void sendErrorLog(String errorMsg) {
            MailAppDependencies.analytics(this.context).sendWalletPushClickedError(errorMsg);
            BaseAwaitCommand.INSTANCE.getLOG().e(errorMsg);
        }

        @Override // ru.mail.util.push.BaseAwaitCommand
        protected void execute(@NotNull AppContextExecutor contextExecutor) {
            Intrinsics.checkNotNullParameter(contextExecutor, "contextExecutor");
            String eventUrl = getParams().getEventUrl();
            if (eventUrl == null) {
                sendErrorLog("Push has not eventUrl");
            } else {
                MailAppDependencies.analytics(this.context).sendWalletPushClicked(getParams().getEventUid(), getParams().getMlType(), getParams().getMlSubtype(), getParams().getMlReminder());
                findPathFor(eventUrl).perform(contextExecutor);
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (Intrinsics.areEqual(IntentActionsProvider.actionOpenWalletPushReceiver, intent.getAction())) {
            Bundle bundleExtra = intent.getBundleExtra("intent_extras");
            String string = bundleExtra != null ? bundleExtra.getString("account_login") : null;
            String stringExtra = intent.getStringExtra("push_uri");
            String stringExtra2 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_UID);
            String str = stringExtra2 == null ? "" : stringExtra2;
            String stringExtra3 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_TYPE);
            String str2 = stringExtra3 == null ? "" : stringExtra3;
            String stringExtra4 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_SUBTYPE);
            String str3 = stringExtra4 == null ? "" : stringExtra4;
            String stringExtra5 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_REMINDER);
            String str4 = stringExtra5 == null ? "" : stringExtra5;
            if (bundleExtra == null || string == null) {
                return;
            }
            BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
            Intrinsics.checkNotNullExpressionValue(pendingResultGoAsync, "goAsync(...)");
            WalletAwaitCommand walletAwaitCommand = new WalletAwaitCommand(context, bundleExtra, pendingResultGoAsync, new Params(string, stringExtra, intent.getIntExtra("auto_close_notificationId", -1), str, str2, str3, str4));
            String stringExtra6 = intent.getStringExtra("EXTRA_PUSH_ANALYTIC_OPEN_URL");
            if (stringExtra6 != null) {
                AnalyticUrlRequest.execute(context, stringExtra6);
            }
            walletAwaitCommand.execute((ExecutorSelector) Locator.INSTANCE.locate(context, RequestArbiter.class));
        }
    }
}
