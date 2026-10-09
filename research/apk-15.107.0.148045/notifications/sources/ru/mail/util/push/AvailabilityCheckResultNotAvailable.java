package ru.mail.util.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lru/mail/util/push/AvailabilityCheckResultNotAvailable;", "Lru/mail/util/push/AvailabilityCheckResult;", "<init>", "()V", "isAvailable", "", "isUserRecoverable", "showUserRecoveryNotification", "", "context", "Landroid/content/Context;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AvailabilityCheckResultNotAvailable implements AvailabilityCheckResult {
    public static final int $stable = 0;

    @Override // ru.mail.util.push.AvailabilityCheckResult
    public boolean isAvailable() {
        return false;
    }

    @Override // ru.mail.util.push.AvailabilityCheckResult
    public boolean isUserRecoverable() {
        return false;
    }

    @Override // ru.mail.util.push.AvailabilityCheckResult
    public void showUserRecoveryNotification(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
