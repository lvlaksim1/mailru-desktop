package com.vk.pushme.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0015\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/vk/pushme/util/ManifestReader;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "readString", "", "key", "readResource", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "getBundle", "Landroid/os/Bundle;", "push-me-util_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ManifestReader {

    @NotNull
    private final Context context;

    public ManifestReader(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    private final Bundle getBundle() throws PackageManager.NameNotFoundException {
        ApplicationInfo applicationInfo = this.context.getPackageManager().getApplicationInfo(this.context.getPackageName(), 128);
        Intrinsics.checkNotNullExpressionValue(applicationInfo, "getApplicationInfo(...)");
        return applicationInfo.metaData;
    }

    @Nullable
    public final Integer readResource(@NotNull String key) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle bundle = getBundle();
        if (bundle != null && bundle.containsKey(key)) {
            return Integer.valueOf(bundle.getInt(key));
        }
        return null;
    }

    @Nullable
    public final String readString(@NotNull String key) throws PackageManager.NameNotFoundException {
        Intrinsics.checkNotNullParameter(key, "key");
        Bundle bundle = getBundle();
        if (bundle != null) {
            return bundle.getString(key);
        }
        return null;
    }
}
