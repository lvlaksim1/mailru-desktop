package com.vk.pushme.common;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/vk/pushme/common/DefaultLogger;", "Lcom/vk/pushme/common/Logger;", "tag", "", "<init>", "(Ljava/lang/String;)V", "verbose", "", "message", "throwable", "", "debug", XmailMigrationPromoSheet.BUTTON_INFO, "warn", "error", "createLogger", "push-me-common_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultLogger implements Logger {

    @Nullable
    private final String tag;

    /* JADX WARN: Multi-variable type inference failed */
    public DefaultLogger() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.vk.pushme.common.Logger
    @NotNull
    public Logger createLogger(@NotNull String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        String str = this.tag;
        if (str != null) {
            tag = str + ":" + tag;
        }
        return new DefaultLogger(tag);
    }

    @Override // com.vk.pushme.common.Logger
    public void debug(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.d(this.tag, message, throwable);
    }

    @Override // com.vk.pushme.common.Logger
    public void error(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.e(this.tag, message, throwable);
    }

    @Override // com.vk.pushme.common.Logger
    public void info(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.i(this.tag, message, throwable);
    }

    @Override // com.vk.pushme.common.Logger
    public void verbose(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.v(this.tag, message, throwable);
    }

    @Override // com.vk.pushme.common.Logger
    public void warn(@NotNull String message, @Nullable Throwable throwable) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.w(this.tag, message, throwable);
    }

    public DefaultLogger(@Nullable String str) {
        this.tag = str;
    }

    public /* synthetic */ DefaultLogger(String str, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : str);
    }
}
