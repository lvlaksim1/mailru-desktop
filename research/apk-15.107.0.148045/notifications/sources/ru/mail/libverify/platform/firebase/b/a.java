package ru.mail.libverify.platform.firebase.b;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.libverify.platform.core.ILog;
import ru.mail.libverify.platform.gcm.IDv2ProviderService;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public final class a implements IDv2ProviderService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    public final Context f87703a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    public final ILog f87704b;

    public a(@NotNull Context context, @Nullable ILog iLog) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f87703a = context;
        this.f87704b = iLog;
    }

    @Override // ru.mail.libverify.platform.gcm.IDv2ProviderService
    @Nullable
    public final String get() {
        String string;
        try {
            ILog iLog = this.f87704b;
            if (iLog != null) {
                iLog.d("IDV2Provider", "Try to read android_id");
            }
            Cursor cursorQuery = this.f87703a.getContentResolver().query(Uri.parse("content://com.google.android.gsf.gservices"), null, null, new String[]{RbParams.Default.URL_PARAM_KEY_ANDROID_ID}, null);
            if (cursorQuery != null && (!cursorQuery.moveToFirst() || cursorQuery.getColumnCount() < 2)) {
                if (!cursorQuery.isClosed()) {
                    cursorQuery.close();
                }
                ILog iLog2 = this.f87704b;
                if (iLog2 != null) {
                    iLog2.e("IDV2Provider", "Failed to read android_id; Cursor is closed");
                }
                return null;
            }
            if (cursorQuery == null) {
                return null;
            }
            try {
                try {
                    string = cursorQuery.getString(1);
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(cursorQuery, th2);
                        throw th3;
                    }
                }
            } catch (Exception e10) {
                ILog iLog3 = this.f87704b;
                if (iLog3 != null) {
                    iLog3.e("IDV2Provider", "Failed to read android_id", e10);
                }
                string = null;
            }
            CloseableKt.closeFinally(cursorQuery, null);
            return string;
        } catch (Exception e11) {
            ILog iLog4 = this.f87704b;
            if (iLog4 != null) {
                iLog4.e("IDV2Provider", "Failed to read android_id", e11);
            }
            return null;
        }
    }
}
