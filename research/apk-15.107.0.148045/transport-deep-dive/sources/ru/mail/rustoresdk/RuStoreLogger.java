package ru.mail.rustoresdk;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;
import ru.mail.util.log.Log;
import ru.rustore.sdk.pushclient.common.logger.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/rustoresdk/RuStoreLogger;", "Lru/rustore/sdk/pushclient/common/logger/Logger;", "tag", "", "log", "Lru/mail/util/log/Log;", "<init>", "(Ljava/lang/String;Lru/mail/util/log/Log;)V", "createLogger", "verbose", "", "message", "throwable", "", "debug", XmailMigrationPromoSheet.BUTTON_INFO, "warn", "error", "rustore-sdk-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuStoreLogger implements Logger {

    @NotNull
    private final Log log;

    @NotNull
    private final String tag;

    public RuStoreLogger(@NotNull String tag, @NotNull Log log) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(log, "log");
        this.tag = tag;
        this.log = log;
    }

    @Override // com.vk.push.common.Logger
    @NotNull
    public /* bridge */ com.vk.push.common.Logger createLogger(@NotNull Object obj) {
        return Logger.DefaultImpls.createLogger(this, obj);
    }

    @Override // com.vk.push.common.Logger
    public void debug(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.d(message);
        } else {
            this.log.d(message, throwable);
        }
    }

    @Override // com.vk.push.common.Logger
    public void error(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.e(message);
        } else {
            this.log.e(message, throwable);
        }
    }

    @Override // com.vk.push.common.Logger
    public void info(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.i(message);
        } else {
            this.log.i(message, throwable);
        }
    }

    @Override // com.vk.push.common.Logger
    public void verbose(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.v(message);
        } else {
            this.log.v(message, throwable);
        }
    }

    @Override // com.vk.push.common.Logger
    public void warn(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.w(message);
        } else {
            this.log.w(message, throwable);
        }
    }

    @Override // com.vk.push.common.Logger
    @NotNull
    public Logger createLogger(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        String str = this.tag + ":" + tag;
        return new RuStoreLogger(str, Log.INSTANCE.getLog(str));
    }
}
