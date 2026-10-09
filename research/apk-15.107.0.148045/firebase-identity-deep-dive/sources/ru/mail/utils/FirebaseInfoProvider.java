package ru.mail.utils;

import androidx.annotation.WorkerThread;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u0003H'J\b\u0010\u0006\u001a\u00020\u0007H'¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/utils/FirebaseInfoProvider;", "", "getInstanceId", "", "getToken", "senderId", "deleteToken", "", "mail-utils_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface FirebaseInfoProvider {
    @WorkerThread
    void deleteToken();

    @WorkerThread
    @NotNull
    String getInstanceId();

    @WorkerThread
    @Nullable
    String getToken(@NotNull String senderId);
}
