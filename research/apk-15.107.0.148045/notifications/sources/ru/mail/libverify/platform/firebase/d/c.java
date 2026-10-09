package ru.mail.libverify.platform.firebase.d;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.CommonStatusCodes;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.libverify.platform.core.ILog;
import ru.mail.libverify.platform.firebase.FirebaseCoreService;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public final class c {
    public static boolean a(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            FirebaseCoreService.INSTANCE.getClass();
            ILog iLogA = FirebaseCoreService.Companion.a();
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            Intrinsics.checkNotNullExpressionValue(googleApiAvailability, "getInstance(...)");
            int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
            iLogA.d("Utils", "play services api availability: " + CommonStatusCodes.getStatusCodeString(iIsGooglePlayServicesAvailable));
            return iIsGooglePlayServicesAvailable == 0;
        } catch (Throwable unused) {
        }
    }
}
