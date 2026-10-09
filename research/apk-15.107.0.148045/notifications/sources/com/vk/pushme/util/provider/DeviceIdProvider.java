package com.vk.pushme.util.provider;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/vk/pushme/util/provider/DeviceIdProvider;", "", "getAndroidId", "", "getDeviceId", "getSdkDeviceId", "push-me-util_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface DeviceIdProvider {
    @Nullable
    String getAndroidId();

    @Nullable
    String getDeviceId();

    @Nullable
    String getSdkDeviceId();
}
