package com.vk.core.util;

import android.content.Context;
import com.vk.core.deviceid.core.DeviceIdStorage;
import com.vk.core.deviceid.core.RealDeviceIdProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0018\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000bH\u0007J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u0015"}, d2 = {"Lcom/vk/core/util/DeviceIdProvider;", "", "<init>", "()V", "init", "", "deviceIdStorage", "Lcom/vk/core/deviceid/core/DeviceIdStorage;", "deviceIdChangedListener", "Lkotlin/Function0;", "getDeviceId", "", "context", "Landroid/content/Context;", "getNextDeviceId", "getDeviceToken", "memberId", "", "setDeviceToken", "deviceToken", "clearDeviceToken", "lite_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DeviceIdProvider {

    @NotNull
    public static final DeviceIdProvider INSTANCE = new DeviceIdProvider();

    private DeviceIdProvider() {
    }

    @JvmStatic
    public static final synchronized void clearDeviceToken(long memberId) {
        RealDeviceIdProvider.clearDeviceToken(memberId);
    }

    @JvmStatic
    @NotNull
    public static final synchronized String getDeviceId(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return RealDeviceIdProvider.getDeviceId(context);
    }

    @JvmStatic
    @NotNull
    public static final synchronized String getDeviceToken(long memberId) {
        return RealDeviceIdProvider.getDeviceToken(memberId);
    }

    @JvmStatic
    @NotNull
    public static final synchronized String getNextDeviceId(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return RealDeviceIdProvider.getNextDeviceId(context);
    }

    @JvmStatic
    public static final void init(@NotNull DeviceIdStorage deviceIdStorage, @NotNull Function0<Unit> deviceIdChangedListener) {
        Intrinsics.checkNotNullParameter(deviceIdStorage, "deviceIdStorage");
        Intrinsics.checkNotNullParameter(deviceIdChangedListener, "deviceIdChangedListener");
        RealDeviceIdProvider.init(deviceIdStorage, deviceIdChangedListener);
    }

    public static /* synthetic */ void init$default(DeviceIdStorage deviceIdStorage, Function0 function0, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            function0 = new Function0() { // from class: com.vk.core.util.e
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DeviceIdProvider.thgilerockvmoca();
                }
            };
        }
        init(deviceIdStorage, function0);
    }

    @JvmStatic
    public static final synchronized void setDeviceToken(long memberId, @NotNull String deviceToken) {
        Intrinsics.checkNotNullParameter(deviceToken, "deviceToken");
        RealDeviceIdProvider.setDeviceToken(memberId, deviceToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit thgilerockvmoca() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final String getDeviceId() {
        return RealDeviceIdProvider.INSTANCE.getDeviceId();
    }
}
