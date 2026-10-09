package ru.mail.util.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.calleridentification.CallerIdentNotificationManager;
import ru.mail.calleridentification.CallerIdentNotificationParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lru/mail/util/push/CallerIdentNotificationManagerImpl;", "Lru/mail/calleridentification/CallerIdentNotificationManager;", "<init>", "()V", "showNotification", "", "context", "Landroid/content/Context;", "ntfnParams", "Lru/mail/calleridentification/CallerIdentNotificationParams;", "clearNotification", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CallerIdentNotificationManagerImpl implements CallerIdentNotificationManager {
    public static final int $stable = 0;

    @Override // ru.mail.calleridentification.CallerIdentNotificationManager
    public void clearNotification(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        NotificationHandler.INSTANCE.from(context).clearCallerNotification();
    }

    @Override // ru.mail.calleridentification.CallerIdentNotificationManager
    public void showNotification(@NotNull Context context, @NotNull CallerIdentNotificationParams ntfnParams) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ntfnParams, "ntfnParams");
        NotificationHandler.INSTANCE.from(context).showCallerNotification(ntfnParams);
    }
}
