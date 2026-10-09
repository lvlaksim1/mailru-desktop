package ru.mail.util.push;

import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.pushme.common.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001a\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001a\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001a\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001a\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/util/push/PushMeSdkLogger;", "Lcom/vk/pushme/common/Logger;", "tag", "", "log", "Lru/mail/util/log/Log;", "<init>", "(Ljava/lang/String;Lru/mail/util/log/Log;)V", "verbose", "", "message", "throwable", "", "debug", XmailMigrationPromoSheet.BUTTON_INFO, "warn", "error", "createLogger", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushMeSdkLogger implements Logger {
    public static final int $stable = 8;

    @NotNull
    private final Log log;

    @NotNull
    private final String tag;

    public PushMeSdkLogger(@NotNull String tag, @NotNull Log log) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(log, "log");
        this.tag = tag;
        this.log = log;
    }

    @Override // com.vk.pushme.common.Logger
    @NotNull
    public Logger createLogger(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        String str = this.tag + ":" + tag;
        return new PushMeSdkLogger(str, Log.INSTANCE.getLog(str));
    }

    @Override // com.vk.pushme.common.Logger
    public void debug(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.d(message);
        } else {
            this.log.d(message, throwable);
        }
    }

    @Override // com.vk.pushme.common.Logger
    public void error(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.e(message);
        } else {
            this.log.e(message, throwable);
        }
    }

    @Override // com.vk.pushme.common.Logger
    public void info(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.i(message);
        } else {
            this.log.i(message, throwable);
        }
    }

    @Override // com.vk.pushme.common.Logger
    public void verbose(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.v(message);
        } else {
            this.log.v(message, throwable);
        }
    }

    @Override // com.vk.pushme.common.Logger
    public void warn(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (throwable == null) {
            this.log.w(message);
        } else {
            this.log.w(message, throwable);
        }
    }
}
