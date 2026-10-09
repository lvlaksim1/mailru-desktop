package ru.mail.util.push.gcm;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lru/mail/util/push/gcm/PushExceptionHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "innerHandler", "asserterThrowable", "Lru/mail/util/push/gcm/AsserterThrowable;", "<init>", "(Ljava/lang/Thread$UncaughtExceptionHandler;Lru/mail/util/push/gcm/AsserterThrowable;)V", "uncaughtException", "", "thread", "Ljava/lang/Thread;", OkListenerKt.KEY_EXCEPTION, "", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushExceptionHandler implements Thread.UncaughtExceptionHandler {

    @NotNull
    private final AsserterThrowable asserterThrowable;

    @NotNull
    private final Thread.UncaughtExceptionHandler innerHandler;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lru/mail/util/push/gcm/PushExceptionHandler$Companion;", "", "<init>", "()V", "createPushExceptionHandler", "Ljava/lang/Thread$UncaughtExceptionHandler;", "defaultHandler", "asserterThrowable", "Lru/mail/util/push/gcm/AsserterThrowable;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Thread.UncaughtExceptionHandler createPushExceptionHandler(@NotNull Thread.UncaughtExceptionHandler defaultHandler, @NotNull AsserterThrowable asserterThrowable) {
            Intrinsics.checkNotNullParameter(defaultHandler, "defaultHandler");
            Intrinsics.checkNotNullParameter(asserterThrowable, "asserterThrowable");
            return new PushExceptionHandler(defaultHandler, asserterThrowable);
        }

        private Companion() {
        }
    }

    public PushExceptionHandler(@NotNull Thread.UncaughtExceptionHandler innerHandler, @NotNull AsserterThrowable asserterThrowable) {
        Intrinsics.checkNotNullParameter(innerHandler, "innerHandler");
        Intrinsics.checkNotNullParameter(asserterThrowable, "asserterThrowable");
        this.innerHandler = innerHandler;
        this.asserterThrowable = asserterThrowable;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(@NotNull Thread thread, @NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter(thread, "thread");
        Intrinsics.checkNotNullParameter(exception, "exception");
        this.asserterThrowable.handleThrowable(thread, exception);
        this.innerHandler.uncaughtException(thread, exception);
    }
}
