package com.vk.superapp.api.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import com.vk.core.preference.Preference;
import com.vk.superapp.core.utils.VKCLogger;
import com.vk.superapp.core.vendor.SuperappDeviceIdProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H%¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004H$¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0017¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u000f\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0004H\u0004¢\u0006\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00068$X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\u00068$X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/vk/superapp/api/analytics/VkBaseDeviceIdProvider;", "Lcom/vk/superapp/core/vendor/SuperappDeviceIdProvider;", "<init>", "()V", "Landroid/content/Context;", "context", "", "loadDeviceId", "(Landroid/content/Context;)Ljava/lang/String;", "", "isProviderAvailable", "(Landroid/content/Context;)Z", "", "init", "(Landroid/content/Context;)V", "getDeviceId", "()Ljava/lang/String;", "Landroid/content/SharedPreferences;", "getPreference", "(Landroid/content/Context;)Landroid/content/SharedPreferences;", "getDeviceIdPreferenceKey", "deviceIdPreferenceKey", "getDeviceIdLogTag", "deviceIdLogTag", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVkBaseDeviceIdProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VkBaseDeviceIdProvider.kt\ncom/vk/superapp/api/analytics/VkBaseDeviceIdProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,84:1\n1#2:85\n*E\n"})
public abstract class VkBaseDeviceIdProvider implements SuperappDeviceIdProvider {
    @Override // com.vk.superapp.core.vendor.SuperappDeviceIdProvider
    @Nullable
    public String getDeviceId() {
        String string$default = Preference.getString$default("device_id_storage", getDeviceIdPreferenceKey(), null, 4, null);
        if (string$default.length() > 0) {
            return string$default;
        }
        return null;
    }

    @NotNull
    protected abstract String getDeviceIdLogTag();

    @NotNull
    protected abstract String getDeviceIdPreferenceKey();

    @NotNull
    protected final SharedPreferences getPreference(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(null, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        return sharedPreferences;
    }

    @Override // com.vk.superapp.core.vendor.SuperappDeviceIdProvider
    @WorkerThread
    public void init(@NotNull Context context) {
        boolean zIsProviderAvailable;
        String strLoadDeviceId;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            zIsProviderAvailable = isProviderAvailable(context);
        } catch (Throwable unused) {
            zIsProviderAvailable = false;
        }
        if (!zIsProviderAvailable) {
            VKCLogger.INSTANCE.i(getDeviceIdLogTag() + " isn't available");
            return;
        }
        try {
            strLoadDeviceId = loadDeviceId(context);
        } catch (Throwable th2) {
            VKCLogger.INSTANCE.e("Loading " + getDeviceIdLogTag() + " is failed", th2);
            strLoadDeviceId = null;
        }
        if (strLoadDeviceId != null) {
            Preference.set("device_id_storage", getDeviceIdPreferenceKey(), strLoadDeviceId);
        }
    }

    protected abstract boolean isProviderAvailable(@NotNull Context context);

    @WorkerThread
    @Nullable
    protected abstract String loadDeviceId(@NotNull Context context) throws Throwable;
}
