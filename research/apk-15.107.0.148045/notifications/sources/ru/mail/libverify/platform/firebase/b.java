package ru.mail.libverify.platform.firebase;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.libverify.platform.core.ILog;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public final class b implements ILog {
    @Override // ru.mail.libverify.platform.core.ILog
    public final void d(@NotNull String logTag, @NotNull String message) {
        Intrinsics.checkNotNullParameter(logTag, "logTag");
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // ru.mail.libverify.platform.core.ILog
    public final void e(@NotNull String logTag, @NotNull String message) {
        Intrinsics.checkNotNullParameter(logTag, "logTag");
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // ru.mail.libverify.platform.core.ILog
    public final void v(@NotNull String logTag, @NotNull String message) {
        Intrinsics.checkNotNullParameter(logTag, "logTag");
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // ru.mail.libverify.platform.core.ILog
    public final void e(@NotNull String logTag, @NotNull String message, @NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter(logTag, "logTag");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(exception, "exception");
    }
}
