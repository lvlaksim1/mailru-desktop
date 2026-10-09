package ru.mail.rustoresdk;

import androidx.annotation.WorkerThread;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H'J\b\u0010\u0004\u001a\u00020\u0005H'¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/rustoresdk/RuStorePushSdkWrapper;", "", "getToken", "", "deleteToken", "", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RuStorePushSdkWrapper {
    @WorkerThread
    boolean deleteToken();

    @WorkerThread
    @Nullable
    String getToken();
}
