package com.vk.pushme.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/vk/pushme/util/SystemUtils;", "", "<init>", "()V", "isTablet", "", "context", "Landroid/content/Context;", "hasTelephonyFeature", "push-me-util_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SystemUtils {

    @NotNull
    public static final SystemUtils INSTANCE = new SystemUtils();

    private SystemUtils() {
    }

    public final boolean hasTelephonyFeature(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    public final boolean isTablet(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getResources().getBoolean(R.bool.push_me_utils_is_tablet);
    }
}
