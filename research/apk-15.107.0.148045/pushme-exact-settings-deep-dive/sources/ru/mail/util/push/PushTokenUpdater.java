package ru.mail.util.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.MailApplication;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.locator.Locator;
import ru.mail.logic.push.CopyPushTokensToPushMeSDK;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.util.log.Log;
import ru.mail.util.push.component.PushComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001c\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/util/push/PushTokenUpdater;", "", "<init>", "()V", "logger", "Lru/mail/util/log/Log;", "copyPushTokenToSdk", "", "context", "Landroid/content/Context;", "onSettingsSyncRequested", "Lkotlin/Function0;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushTokenUpdater {
    public static final int $stable = 8;

    @NotNull
    private final Log logger = Log.INSTANCE.getLog("DevPushUpdater");

    public final void copyPushTokenToSdk(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        copyPushTokenToSdk(context, new Function0() { // from class: ru.mail.util.push.g1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        });
    }

    public final void copyPushTokenToSdk(@NotNull final Context context, @NotNull final Function0<Unit> onSettingsSyncRequested) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onSettingsSyncRequested, "onSettingsSyncRequested");
        PushComponent pushComponent = ((MailApplication) context).getPushComponent();
        Intrinsics.checkNotNull(pushComponent);
        new CopyPushTokensToPushMeSDK(pushComponent).execute((RequestArbiter) Locator.INSTANCE.locate(context, RequestArbiter.class)).observe(Schedulers.immediate(), new ObservableFuture.Observer<CommandStatus<Unit>>() { // from class: ru.mail.util.push.PushTokenUpdater.copyPushTokenToSdk.2
            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onCancelled() {
                PushTokenUpdater.this.logger.w("Command cancelled");
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onError(Exception exception) {
                PushTokenUpdater.this.logger.e("Command finished with error", exception);
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onDone(CommandStatus<Unit> result) {
                PushTokenUpdater.this.logger.i("Tokens have been copied, requesting sync...");
                SettingsUtil.sendSettingsAllAccounts(context);
                onSettingsSyncRequested.invoke();
            }
        });
    }
}
