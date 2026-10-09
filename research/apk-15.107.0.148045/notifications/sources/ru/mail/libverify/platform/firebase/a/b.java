package ru.mail.libverify.platform.firebase.a;

import android.content.Context;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.libverify.platform.core.JwsService;
import ru.mail.libverify.platform.core.JwsServiceCallback;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public final class b implements JwsService {
    @Override // ru.mail.libverify.platform.core.JwsService
    public final void getJws(@NotNull Context context, @Nullable byte[] bArr, @NotNull String apiKey, @NotNull JwsServiceCallback jwsServiceCallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(jwsServiceCallback, "jwsServiceCallback");
        jwsServiceCallback.onFailure(new UnsupportedOperationException("Not implemented."));
    }
}
