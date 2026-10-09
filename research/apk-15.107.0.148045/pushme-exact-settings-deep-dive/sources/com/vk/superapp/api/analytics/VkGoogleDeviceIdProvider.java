package com.vk.superapp.api.analytics;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailability;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0014¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0014¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u0011\u001a\u00020\u00048\u0014X\u0094D¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0006R\u001a\u0010\u0014\u001a\u00020\u00048\u0014X\u0094D¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/vk/superapp/api/analytics/VkGoogleDeviceIdProvider;", "Lcom/vk/superapp/api/analytics/VkBaseDeviceIdProvider;", "<init>", "()V", "", "getStatsKey", "()Ljava/lang/String;", "Landroid/content/Context;", "context", "", "isProviderAvailable", "(Landroid/content/Context;)Z", "loadDeviceId", "(Landroid/content/Context;)Ljava/lang/String;", "ipakvmoca", "Ljava/lang/String;", "getDeviceIdPreferenceKey", "deviceIdPreferenceKey", "ipakvmocb", "getDeviceIdLogTag", "deviceIdLogTag", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVkGoogleDeviceIdProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VkGoogleDeviceIdProvider.kt\ncom/vk/superapp/api/analytics/VkGoogleDeviceIdProvider\n+ 2 CommonExt.kt\ncom/vk/core/extensions/CommonExtKt\n*L\n1#1,55:1\n47#2,6:56\n*S KotlinDebug\n*F\n+ 1 VkGoogleDeviceIdProvider.kt\ncom/vk/superapp/api/analytics/VkGoogleDeviceIdProvider\n*L\n50#1:56,6\n*E\n"})
public final class VkGoogleDeviceIdProvider extends VkBaseDeviceIdProvider {

    @NotNull
    public static final VkGoogleDeviceIdProvider INSTANCE = new VkGoogleDeviceIdProvider();

    /* JADX INFO: renamed from: ipakvmoca, reason: from kotlin metadata */
    @NotNull
    private static final String deviceIdPreferenceKey = "googleDeviceId";

    /* JADX INFO: renamed from: ipakvmocb, reason: from kotlin metadata */
    @NotNull
    private static final String deviceIdLogTag = "googleDeviceId";

    private VkGoogleDeviceIdProvider() {
    }

    @Override // com.vk.superapp.api.analytics.VkBaseDeviceIdProvider
    @NotNull
    protected String getDeviceIdLogTag() {
        return deviceIdLogTag;
    }

    @Override // com.vk.superapp.api.analytics.VkBaseDeviceIdProvider
    @NotNull
    protected String getDeviceIdPreferenceKey() {
        return deviceIdPreferenceKey;
    }

    @Override // com.vk.superapp.core.vendor.SuperappDeviceIdProvider
    @NotNull
    public String getStatsKey() {
        return "gaid";
    }

    @Override // com.vk.superapp.api.analytics.VkBaseDeviceIdProvider
    protected boolean isProviderAvailable(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0;
    }

    @Override // com.vk.superapp.api.analytics.VkBaseDeviceIdProvider
    @Nullable
    protected String loadDeviceId(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
        } catch (Throwable unused) {
            return null;
        }
    }
}
