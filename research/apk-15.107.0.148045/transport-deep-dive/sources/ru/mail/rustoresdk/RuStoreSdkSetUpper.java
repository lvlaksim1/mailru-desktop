package ru.mail.rustoresdk;

import android.app.Application;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bH&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lru/mail/rustoresdk/RuStoreSdkSetUpper;", "", "setUp", "", "application", "Landroid/app/Application;", "pushProjectId", "", "hostPackageName", "hostPubKey", "testModeEnabled", "", "rustore-sdk-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RuStoreSdkSetUpper {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void setUp$default(RuStoreSdkSetUpper ruStoreSdkSetUpper, Application application, String str, String str2, String str3, boolean z10, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setUp");
        }
        if ((i10 & 16) != 0) {
            z10 = false;
        }
        ruStoreSdkSetUpper.setUp(application, str, str2, str3, z10);
    }

    void setUp(@NotNull Application application, @NotNull String pushProjectId, @Nullable String hostPackageName, @Nullable String hostPubKey, boolean testModeEnabled);
}
