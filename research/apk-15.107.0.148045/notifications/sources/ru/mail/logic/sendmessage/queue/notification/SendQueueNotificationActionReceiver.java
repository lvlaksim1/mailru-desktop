package ru.mail.logic.sendmessage.queue.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.dependencies.sendmessage.SendMailEntryPoint;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationActionReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SendQueueNotificationActionReceiver extends BroadcastReceiver {

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("SendQueueNotificationReceiver");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationActionReceiver$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "getLOG", "()Lru/mail/util/log/Log;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Log getLOG() {
            return SendQueueNotificationActionReceiver.LOG;
        }

        private Companion() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onReceive$lambda$0(AtomicBoolean atomicBoolean, BroadcastReceiver.PendingResult pendingResult) {
        if (atomicBoolean.compareAndSet(false, true)) {
            pendingResult.finish();
        }
        return Unit.INSTANCE;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SendQueueNotificationAction sendQueueNotificationActionFromIntentAction = SendQueueNotificationAction.INSTANCE.fromIntentAction(intent.getAction());
        if (sendQueueNotificationActionFromIntentAction == null) {
            return;
        }
        final BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Function0<Unit> function0 = new Function0() { // from class: ru.mail.logic.sendmessage.queue.notification.f
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SendQueueNotificationActionReceiver.onReceive$lambda$0(atomicBoolean, pendingResultGoAsync);
            }
        };
        try {
            SendMailEntryPoint.INSTANCE.sendQueueNotificationActionHandler(context).handle(sendQueueNotificationActionFromIntentAction, function0);
        } catch (Exception e10) {
            LOG.e("Unable to handle send queue notification action", e10);
            function0.invoke();
        }
    }
}
