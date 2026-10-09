package com.vk.pushme.work;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"setRequiredNetworkTypeConnected", "Landroidx/work/Constraints$Builder;", "skipConnectionCheckByGoogle", "", "push-me-sdk_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class WorkerExtensionsKt {
    @NotNull
    public static final Constraints.Builder setRequiredNetworkTypeConnected(@NotNull Constraints.Builder builder, boolean z10) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        if (!z10) {
            builder.setRequiredNetworkType(NetworkType.CONNECTED);
        }
        return builder;
    }
}
