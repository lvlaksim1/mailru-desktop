package ru.mail.util.push;

import android.content.Context;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public interface AvailabilityCheckResult {
    boolean isAvailable();

    boolean isUserRecoverable();

    void showUserRecoveryNotification(@NonNull Context context);
}
