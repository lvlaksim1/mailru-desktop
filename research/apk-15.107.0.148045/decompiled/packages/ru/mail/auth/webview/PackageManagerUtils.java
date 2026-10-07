package ru.mail.auth.webview;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/auth/webview/PackageManagerUtils;", "", "<init>", "()V", "queryIntentActivities", "", "Landroid/content/pm/ResolveInfo;", "context", "Landroid/content/Context;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", Collector.FLAGS, "", "resolveActivity", "Landroid/content/ComponentName;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PackageManagerUtils {

    @NotNull
    public static final PackageManagerUtils INSTANCE = new PackageManagerUtils();

    private PackageManagerUtils() {
    }

    @JvmStatic
    @NotNull
    public static final List<ResolveInfo> queryIntentActivities(@NotNull Context context, @NotNull Intent intent, int flags) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        try {
            List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, flags);
            Intrinsics.checkNotNull(listQueryIntentActivities);
            return listQueryIntentActivities;
        } catch (Exception unused) {
            return CollectionsKt.emptyList();
        }
    }

    @JvmStatic
    @Nullable
    public static final ComponentName resolveActivity(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        try {
            return intent.resolveActivity(context.getPackageManager());
        } catch (Exception unused) {
            return null;
        }
    }
}
