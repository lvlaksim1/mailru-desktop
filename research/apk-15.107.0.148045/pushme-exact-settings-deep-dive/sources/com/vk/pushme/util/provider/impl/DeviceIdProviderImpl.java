package com.vk.pushme.util.provider.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.Settings;
import com.vk.commonid.CommonIdProvider;
import com.vk.pushme.util.provider.DeviceIdProvider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.ad.RbParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\f\u001a\u0004\u0018\u00010\u0007H\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lcom/vk/pushme/util/provider/impl/DeviceIdProviderImpl;", "Lcom/vk/pushme/util/provider/DeviceIdProvider;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "androidIdValue", "", "getAndroidIdValue", "()Ljava/lang/String;", "androidIdValue$delegate", "Lkotlin/Lazy;", "getAndroidId", "getDeviceId", "getSdkDeviceId", "push-me-util_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SuppressLint({"HardwareIds"})
public final class DeviceIdProviderImpl implements DeviceIdProvider {

    /* JADX INFO: renamed from: androidIdValue$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy androidIdValue;

    @NotNull
    private final Context context;

    public DeviceIdProviderImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.androidIdValue = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.util.provider.impl.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DeviceIdProviderImpl.androidIdValue_delegate$lambda$0(this.f51521a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String androidIdValue_delegate$lambda$0(DeviceIdProviderImpl deviceIdProviderImpl) {
        try {
            String string = Settings.Secure.getString(deviceIdProviderImpl.context.getContentResolver(), RbParams.Default.URL_PARAM_KEY_ANDROID_ID);
            return string == null ? "" : string;
        } catch (Throwable unused) {
            return "";
        }
    }

    private final String getAndroidIdValue() {
        return (String) this.androidIdValue.getValue();
    }

    @Override // com.vk.pushme.util.provider.DeviceIdProvider
    @Nullable
    public String getAndroidId() {
        return getAndroidIdValue();
    }

    @Override // com.vk.pushme.util.provider.DeviceIdProvider
    @Nullable
    public String getDeviceId() {
        return getSdkDeviceId();
    }

    @Override // com.vk.pushme.util.provider.DeviceIdProvider
    @Nullable
    public String getSdkDeviceId() {
        try {
            return CommonIdProvider.Companion.getCommonIdGenerated$default(CommonIdProvider.INSTANCE, this.context, null, 2, null);
        } catch (Exception unused) {
            return null;
        }
    }
}
