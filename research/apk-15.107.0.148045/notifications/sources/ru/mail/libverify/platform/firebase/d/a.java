package ru.mail.libverify.platform.firebase.d;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.libverify.platform.core.ILog;
import ru.mail.libverify.platform.core.IPlatformUtils;
import ru.mail.libverify.platform.firebase.FirebaseCoreService;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public final class a implements IPlatformUtils {
    @Override // ru.mail.libverify.platform.core.IPlatformUtils
    public final boolean checkGooglePlayServicesNewer(@NotNull Context context) {
        String str;
        int i10;
        int i11;
        Intrinsics.checkNotNullParameter(context, "context");
        int[] targetVersion = b.f87707a;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(targetVersion, "targetVersion");
        if (!c.a(context)) {
            return false;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (str = packageManager.getPackageInfo("com.google.android.gms", 0).versionName) == null || str.length() == 0) {
                return false;
            }
            List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null);
            if (listSplit$default.size() < 2) {
                return false;
            }
            for (int i12 = 0; i12 < 2 && (i10 = Integer.parseInt((String) listSplit$default.get(i12))) <= (i11 = targetVersion[i12]); i12++) {
                if (i10 < i11) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // ru.mail.libverify.platform.core.IPlatformUtils
    public final <T extends BroadcastReceiver> void disableReceiver(@NotNull Context context, @NotNull Class<T> clazz) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        FirebaseCoreService.INSTANCE.getClass();
        ILog iLogA = FirebaseCoreService.Companion.a();
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) clazz), 2, 1);
            iLogA.v("Utils", "disabled receiver: ".concat(clazz.getName()));
        } catch (Throwable th2) {
            iLogA.e("Utils", "failed to disable receiver: ".concat(clazz.getName()), th2);
        }
    }

    @Override // ru.mail.libverify.platform.core.IPlatformUtils
    public final <T extends BroadcastReceiver> void enableReceiver(@NotNull Context context, @NotNull Class<T> clazz) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        FirebaseCoreService.INSTANCE.getClass();
        ILog iLogA = FirebaseCoreService.Companion.a();
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, (Class<?>) clazz), 1, 1);
            iLogA.v("Utils", "enabled receiver: ".concat(clazz.getName()));
        } catch (Throwable th2) {
            iLogA.e("Utils", "failed to enable receiver: ".concat(clazz.getName()), th2);
        }
    }

    @Override // ru.mail.libverify.platform.core.IPlatformUtils
    public final boolean hasGooglePlayServices(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return c.a(context);
    }
}
