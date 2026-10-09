package com.vk.pushme.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0007H\u0016¨\u0006\u0010"}, d2 = {"Lcom/vk/pushme/common/EmptyLogger;", "Lcom/vk/pushme/common/Logger;", "<init>", "()V", "verbose", "", "message", "", "throwable", "", "debug", XmailMigrationPromoSheet.BUTTON_INFO, "warn", "error", "createLogger", "tag", "push-me-common_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class EmptyLogger implements Logger {
    @Override // com.vk.pushme.common.Logger
    @NotNull
    public Logger createLogger(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return this;
    }

    @Override // com.vk.pushme.common.Logger
    public void debug(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // com.vk.pushme.common.Logger
    public void error(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // com.vk.pushme.common.Logger
    public void info(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // com.vk.pushme.common.Logger
    public void verbose(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
    }

    @Override // com.vk.pushme.common.Logger
    public void warn(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
