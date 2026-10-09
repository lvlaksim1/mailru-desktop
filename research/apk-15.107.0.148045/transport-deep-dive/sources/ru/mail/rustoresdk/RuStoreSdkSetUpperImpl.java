package ru.mail.rustoresdk;

import android.app.Application;
import com.vk.push.common.AppInfo;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.log.Log;
import ru.rustore.sdk.pushclient.RuStorePushClient;
import ru.rustore.sdk.pushclient.common.logger.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J4\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Lru/mail/rustoresdk/RuStoreSdkSetUpperImpl;", "Lru/mail/rustoresdk/RuStoreSdkSetUpper;", "<init>", "()V", "setUp", "", "application", "Landroid/app/Application;", "pushProjectId", "", "hostPackageName", "hostPubKey", "testModeEnabled", "", "createLogger", "Lru/rustore/sdk/pushclient/common/logger/Logger;", "rustore-sdk-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuStoreSdkSetUpperImpl implements RuStoreSdkSetUpper {
    private final Logger createLogger() {
        return new RuStoreLogger("RuStoreSDK", Log.INSTANCE.getLog("RuStoreSDK"));
    }

    @Override // ru.mail.rustoresdk.RuStoreSdkSetUpper
    public void setUp(@NotNull Application application, @NotNull String pushProjectId, @Nullable String hostPackageName, @Nullable String hostPubKey, boolean testModeEnabled) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pushProjectId, "pushProjectId");
        RuStorePushClient.init$default(RuStorePushClient.f102575a, application, pushProjectId, createLogger(), null, (hostPackageName == null || StringsKt.isBlank(hostPackageName) || hostPubKey == null || StringsKt.isBlank(hostPubKey)) ? null : CollectionsKt.listOf(new AppInfo(hostPackageName, hostPubKey)), testModeEnabled, null, 72, null);
    }
}
