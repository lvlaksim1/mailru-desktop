package com.vk.pushme.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.utils.utils.NetworkUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006J\f\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u0006¨\u0006\t"}, d2 = {"Lcom/vk/pushme/util/Extensions;", "", "<init>", "()V", "isNetworkAvailable", "", "Landroid/content/Context;", "getVersionName", "", "push-me-util_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Extensions {

    @NotNull
    public static final Extensions INSTANCE = new Extensions();

    private Extensions() {
    }

    @Nullable
    public final String getVersionName(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            PackageInfo packageInfo = Build.VERSION.SDK_INT >= 33 ? context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L)) : context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    public final boolean isNetworkAvailable(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return NetworkUtils.hasInternetConnection$default(false, null, 3, null);
    }
}
