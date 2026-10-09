package ru.mail.utils;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.google.firebase.FirebaseApp;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001aB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u001c\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lru/mail/utils/FirebaseAppInitializer;", "", "<init>", "()V", "readyLatch", "Ljava/util/concurrent/CountDownLatch;", "initialized", "", "initStarted", "Ljava/util/concurrent/atomic/AtomicBoolean;", "awaitTimeoutMs", "", "awaitTimeoutListener", "Lru/mail/utils/FirebaseAppInitializer$AwaitTimeoutListener;", "configure", "", "initialize", "context", "Landroid/content/Context;", "additionalInit", "Lkotlin/Function0;", "awaitInitialized", "ensureInitialized", "DEFAULT_AWAIT_TIMEOUT_MS", "LOG", "Lru/mail/util/log/Log;", "AwaitTimeoutListener", "mail-utils_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FirebaseAppInitializer {
    public static final long DEFAULT_AWAIT_TIMEOUT_MS = 5000;

    @Nullable
    private static volatile AwaitTimeoutListener awaitTimeoutListener;
    private static volatile boolean initialized;

    @NotNull
    public static final FirebaseAppInitializer INSTANCE = new FirebaseAppInitializer();

    @NotNull
    private static final CountDownLatch readyLatch = new CountDownLatch(1);

    @NotNull
    private static final AtomicBoolean initStarted = new AtomicBoolean(false);
    private static volatile long awaitTimeoutMs = 5000;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("FirebaseAppInitializer");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/utils/FirebaseAppInitializer$AwaitTimeoutListener;", "", "onAwaitTimeout", "", "timeoutMs", "", "mail-utils_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface AwaitTimeoutListener {
        void onAwaitTimeout(long timeoutMs);
    }

    private FirebaseAppInitializer() {
    }

    @JvmStatic
    public static final void ensureInitialized(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (initialized) {
            return;
        }
        FirebaseApp.initializeApp(context.getApplicationContext());
    }

    @WorkerThread
    public final void awaitInitialized(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (initialized || !initStarted.get()) {
            return;
        }
        long j10 = awaitTimeoutMs;
        if (readyLatch.await(j10, TimeUnit.MILLISECONDS)) {
            return;
        }
        LOG.w("Waiting for background Firebase init timed out, initializing on caller thread");
        AwaitTimeoutListener awaitTimeoutListener2 = awaitTimeoutListener;
        if (awaitTimeoutListener2 != null) {
            awaitTimeoutListener2.onAwaitTimeout(j10);
        }
        ensureInitialized(context);
    }

    public final void configure(long awaitTimeoutMs2, @NotNull AwaitTimeoutListener awaitTimeoutListener2) {
        Intrinsics.checkNotNullParameter(awaitTimeoutListener2, "awaitTimeoutListener");
        initStarted.set(true);
        awaitTimeoutMs = awaitTimeoutMs2;
        awaitTimeoutListener = awaitTimeoutListener2;
    }

    public final void initialize(@NotNull Context context, @NotNull Function0<Unit> additionalInit) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(additionalInit, "additionalInit");
        try {
            FirebaseApp.initializeApp(context.getApplicationContext());
            additionalInit.invoke();
        } finally {
            initialized = true;
            readyLatch.countDown();
        }
    }
}
