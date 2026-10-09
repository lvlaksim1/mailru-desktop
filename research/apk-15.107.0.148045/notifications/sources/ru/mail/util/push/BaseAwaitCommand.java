package ru.mail.util.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationManagerCompat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.locator.Locator;
import ru.mail.logic.navigation.Navigator;
import ru.mail.logic.navigation.NavigatorPendingAction;
import ru.mail.logic.navigation.executor.AppContextExecutor;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u0000 \u001e*\u0004\b\u0000\u0010\u00012\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002:\u0001\u001eB1\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00028\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0004J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH$J\b\u0010\u001d\u001a\u00020\u001aH\u0004R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lru/mail/util/push/BaseAwaitCommand;", "Params", "Lru/mail/mailbox/cmd/Command;", "Ljava/lang/Void;", "context", "Landroid/content/Context;", "notificationId", "", "intentExtras", "Landroid/os/Bundle;", "pendingResult", "Landroid/content/BroadcastReceiver$PendingResult;", "params", "<init>", "(Landroid/content/Context;ILandroid/os/Bundle;Landroid/content/BroadcastReceiver$PendingResult;Ljava/lang/Object;)V", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "executorSelector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "onExecute", "selector", "findPathFor", "Lru/mail/logic/navigation/NavigatorPendingAction;", "url", "", "execute", "", "contextExecutor", "Lru/mail/logic/navigation/executor/AppContextExecutor;", "closeNotificationWithCheck", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBaseAwaitCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseAwaitCommand.kt\nru/mail/util/push/BaseAwaitCommand\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,88:1\n1#2:89\n*E\n"})
public abstract class BaseAwaitCommand<Params> extends Command<Params, Void> {

    @NotNull
    public static final String EXTRA_AUTO_CLOSE_NOTIFICATION_ID = "auto_close_notificationId";

    @NotNull
    public static final String EXTRA_CALENDAR_PUSH_ML_REMINDER = "calendar_push_ml_reminder";

    @NotNull
    public static final String EXTRA_CALENDAR_PUSH_ML_SUBTYPE = "calendar_push_ml_subtype";

    @NotNull
    public static final String EXTRA_CALENDAR_PUSH_ML_TYPE = "calendar_push_ml_type";

    @NotNull
    public static final String EXTRA_CALENDAR_PUSH_SUBTYPE = "calendar_push_subtype";

    @NotNull
    public static final String EXTRA_CALENDAR_PUSH_TYPE = "calendar_push_type";

    @NotNull
    public static final String EXTRA_CALENDAR_PUSH_UID = "calendar_push_uid";

    @NotNull
    public static final String EXTRA_INTENT_EXTRAS = "intent_extras";

    @NotNull
    public static final String EXTRA_PORTAL_PUSH_APP_ID = "portal_push_app_id";

    @NotNull
    public static final String EXTRA_PORTAL_PUSH_BUTTONS = "portal_push_buttons";

    @NotNull
    public static final String EXTRA_PORTAL_PUSH_CAMPAIGN = "portal_push_campaign";

    @NotNull
    public static final String EXTRA_PORTAL_PUSH_EMAIL = "portal_push_email";

    @NotNull
    public static final String EXTRA_PORTAL_PUSH_PATH = "portal_push_path";

    @NotNull
    public static final String EXTRA_PROMOTE_PUSH_TYPE = "push_type";

    @NotNull
    public static final String EXTRA_PUSH_ANALYTIC_OPEN_URL = "EXTRA_PUSH_ANALYTIC_OPEN_URL";

    @NotNull
    public static final String EXTRA_PUSH_MESSAGE_TYPE = "push_message_type";

    @NotNull
    public static final String EXTRA_PUSH_ME_SDK_PUSH_ID = "EXTRA_PUSH_ME_SDK_PUSH_ID";

    @NotNull
    public static final String EXTRA_PUSH_URI = "push_uri";
    public static final int NOT_CLOSABLE_NOTIFICATION_ID = -1;

    @NotNull
    private final Context context;

    @Nullable
    private final Bundle intentExtras;
    private final int notificationId;

    @NotNull
    private final BroadcastReceiver.PendingResult pendingResult;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("this");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lru/mail/util/push/BaseAwaitCommand$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "getLOG", "()Lru/mail/util/log/Log;", "EXTRA_PUSH_URI", "", "EXTRA_INTENT_EXTRAS", "EXTRA_AUTO_CLOSE_NOTIFICATION_ID", "EXTRA_PUSH_MESSAGE_TYPE", "EXTRA_PROMOTE_PUSH_TYPE", "EXTRA_PORTAL_PUSH_APP_ID", "EXTRA_PORTAL_PUSH_PATH", "EXTRA_PORTAL_PUSH_CAMPAIGN", "EXTRA_PORTAL_PUSH_EMAIL", "EXTRA_PORTAL_PUSH_BUTTONS", "EXTRA_PUSH_ANALYTIC_OPEN_URL", BaseAwaitCommand.EXTRA_PUSH_ME_SDK_PUSH_ID, "NOT_CLOSABLE_NOTIFICATION_ID", "", "EXTRA_CALENDAR_PUSH_TYPE", "EXTRA_CALENDAR_PUSH_SUBTYPE", "EXTRA_CALENDAR_PUSH_ML_TYPE", "EXTRA_CALENDAR_PUSH_ML_SUBTYPE", "EXTRA_CALENDAR_PUSH_UID", "EXTRA_CALENDAR_PUSH_ML_REMINDER", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Log getLOG() {
            return BaseAwaitCommand.LOG;
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseAwaitCommand(@NotNull Context context, int i10, @Nullable Bundle bundle, @NotNull BroadcastReceiver.PendingResult pendingResult, Params params) {
        super(params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pendingResult, "pendingResult");
        this.context = context;
        this.notificationId = i10;
        this.intentExtras = bundle;
        this.pendingResult = pendingResult;
    }

    protected final void closeNotificationWithCheck() {
        if (this.notificationId != -1) {
            NotificationManagerCompat.from(this.context).cancel(this.notificationId);
            if (Build.VERSION.SDK_INT < 31) {
                this.context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            }
        }
    }

    protected abstract void execute(@NotNull AppContextExecutor contextExecutor);

    @NotNull
    protected final NavigatorPendingAction findPathFor(@NotNull String url) throws ExecutionException, InterruptedException, TimeoutException {
        Intrinsics.checkNotNullParameter(url, "url");
        NavigatorPendingAction orThrow = ((Navigator) Locator.INSTANCE.from(this.context).locate(Navigator.class)).findPathFor(url).getOrThrow(8L, TimeUnit.SECONDS);
        Intrinsics.checkNotNullExpressionValue(orThrow, "getOrThrow(...)");
        return orThrow;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector executorSelector) {
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        CommandExecutor commandGroupExecutor = executorSelector.getCommandGroupExecutor();
        Intrinsics.checkNotNullExpressionValue(commandGroupExecutor, "getCommandGroupExecutor(...)");
        return commandGroupExecutor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    @Nullable
    public Void onExecute(@Nullable ExecutorSelector selector) {
        try {
            AppContextExecutor appContextExecutor = new AppContextExecutor(this.context);
            Bundle bundle = this.intentExtras;
            if (bundle != null) {
                appContextExecutor.getIntentExtra().putAll(bundle);
            }
            appContextExecutor.getIntentExtra().putBoolean(PushLaunchKt.EXTRA_FROM_NOTIFICATION, true);
            execute(appContextExecutor);
            Unit unit = Unit.INSTANCE;
            closeNotificationWithCheck();
        } catch (Exception e10) {
            LOG.e("Something get wrong in BaseAwaitCommand", e10);
        }
        this.pendingResult.finish();
        return null;
    }
}
