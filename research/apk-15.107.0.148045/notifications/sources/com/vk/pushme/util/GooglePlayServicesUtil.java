package com.vk.pushme.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002¨\u0006\f"}, d2 = {"Lcom/vk/pushme/util/GooglePlayServicesUtil;", "", "<init>", "()V", "getPlayServicesVersion", "", "context", "Landroid/content/Context;", "getPackageInfoCompat", "Landroid/content/pm/PackageInfo;", "packageManager", "Landroid/content/pm/PackageManager;", "push-me-util_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GooglePlayServicesUtil {

    @NotNull
    public static final GooglePlayServicesUtil INSTANCE = new GooglePlayServicesUtil();

    private GooglePlayServicesUtil() {
    }

    private final PackageInfo getPackageInfoCompat(PackageManager packageManager) {
        if (packageManager == null) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 33 ? packageManager.getPackageInfo("com.google.android.gms", PackageManager.PackageInfoFlags.of(0L)) : packageManager.getPackageInfo("com.google.android.gms", 0);
    }

    public final long getPlayServicesVersion(@NotNull Context context) {
        PackageInfo packageInfoCompat;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            packageInfoCompat = getPackageInfoCompat(context.getPackageManager());
        } catch (Exception e10) {
            Log.e("GooglePlayServicesUtil", "Failed to get play services version", e10);
            packageInfoCompat = null;
        }
        if (packageInfoCompat != null) {
            return packageInfoCompat.versionCode;
        }
        return 0L;
    }
}
