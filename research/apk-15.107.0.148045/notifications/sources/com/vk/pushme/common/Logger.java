package com.vk.pushme.common;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0005H&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/vk/pushme/common/Logger;", "", "verbose", "", "message", "", "throwable", "", "debug", XmailMigrationPromoSheet.BUTTON_INFO, "warn", "error", "createLogger", "tag", "push-me-common_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Logger {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void debug$default(Logger logger, String str, Throwable th2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: debug");
        }
        if ((i10 & 2) != 0) {
            th2 = null;
        }
        logger.debug(str, th2);
    }

    static /* synthetic */ void error$default(Logger logger, String str, Throwable th2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: error");
        }
        if ((i10 & 2) != 0) {
            th2 = null;
        }
        logger.error(str, th2);
    }

    static /* synthetic */ void info$default(Logger logger, String str, Throwable th2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: info");
        }
        if ((i10 & 2) != 0) {
            th2 = null;
        }
        logger.info(str, th2);
    }

    static /* synthetic */ void verbose$default(Logger logger, String str, Throwable th2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: verbose");
        }
        if ((i10 & 2) != 0) {
            th2 = null;
        }
        logger.verbose(str, th2);
    }

    static /* synthetic */ void warn$default(Logger logger, String str, Throwable th2, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: warn");
        }
        if ((i10 & 2) != 0) {
            th2 = null;
        }
        logger.warn(str, th2);
    }

    @NotNull
    Logger createLogger(@NotNull String tag);

    void debug(@NotNull String message, @Nullable Throwable throwable);

    void error(@NotNull String message, @Nullable Throwable throwable);

    void info(@NotNull String message, @Nullable Throwable throwable);

    void verbose(@NotNull String message, @Nullable Throwable throwable);

    void warn(@NotNull String message, @Nullable Throwable throwable);
}
