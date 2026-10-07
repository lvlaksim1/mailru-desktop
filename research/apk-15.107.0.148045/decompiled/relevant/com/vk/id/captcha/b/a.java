package com.vk.id.captcha.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.vk.id.captcha.sensors.SensorsDataRepository;
import com.vk.id.captcha.sensors.SensorsDataRepositoryImpl;
import com.vk.id.captcha.web.b;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.app.data.openapi.File;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u000f\u0012\u0006\u0010\u001c\u001a\u00020\u0002¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u001b\u0010\u0003\u001a\u00020\u00068AX\u0081\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\"\u0010\u0011\u001a\u00020\u000b8\u0001@\u0001X\u0081\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0005\u0010\u0010R\u0014\u0010\t\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001a\u0010\u000e\u001a\u00020\u00158\u0001X\u0081\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u0016\u001a\u0004\b\u0011\u0010\u0017R\u001b\u0010\u0013\u001a\u00020\u00188AX\u0081\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\b\u001a\u0004\b\u0003\u0010\u0019R\u001b\u0010\u0007\u001a\u00020\u001a8AX\u0081\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\b\u001a\u0004\b\u0005\u0010\u001b"}, d2 = {"Lcom/vk/id/captcha/b/a;", "", "Landroid/content/Context;", "b", "Landroid/content/Context;", "a", "Ljava/util/concurrent/ThreadPoolExecutor;", "g", "Lkotlin/Lazy;", "d", "()Ljava/util/concurrent/ThreadPoolExecutor;", "", "h", "Z", "e", "()Z", "(Z)V", "c", "", File.TYPE_FILE, "I", "Landroid/os/Handler;", "Landroid/os/Handler;", "()Landroid/os/Handler;", "Lcom/vk/id/captcha/web/b;", "()Lcom/vk/id/captcha/web/b;", "Lcom/vk/id/captcha/c/e;", "()Lcom/vk/id/captcha/c/e;", "p0", "<init>", "(Landroid/content/Context;)V"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    private static volatile a f50745i;

    /* JADX INFO: renamed from: b, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private final Context a;

    /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private final Lazy g;

    /* JADX INFO: renamed from: d, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private final Lazy f;

    @NotNull
    private final Handler e;

    /* JADX INFO: renamed from: f, reason: collision with root package name and from kotlin metadata */
    private final int d;

    /* JADX INFO: renamed from: g, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private final Lazy b;

    /* JADX INFO: renamed from: h, reason: collision with root package name and from kotlin metadata */
    private boolean c;

    /* JADX INFO: renamed from: com.vk.id.captcha.b.a$a, reason: collision with other inner class name and from kotlin metadata */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0011\u0010\u000b\u001a\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\n"}, d2 = {"Lcom/vk/id/captcha/b/a$a;", "", "Landroid/content/Context;", "p0", "", "a", "(Landroid/content/Context;)V", "Lcom/vk/id/captcha/b/a;", Logger.METHOD_I, "Lcom/vk/id/captcha/b/a;", "()Lcom/vk/id/captcha/b/a;", "b", "<init>", "()V"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        @JvmName(name = "a")
        @NotNull
        public static a a() {
            a aVar = a.f50745i;
            if (aVar != null) {
                return aVar;
            }
            throw new IllegalStateException("DI is not initialized!");
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static void a(@NotNull Context p10) {
            Intrinsics.checkNotNullParameter(p10, "");
            if (a.f50745i == null) {
                synchronized (a.class) {
                    try {
                        if (a.f50745i == null) {
                            a.f50745i = new a(p10);
                        }
                        Unit unit = Unit.INSTANCE;
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
            }
        }
    }

    public a(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        this.a = context;
        this.g = LazyKt.lazy(new Function0<SensorsDataRepositoryImpl>() { // from class: com.vk.id.captcha.b.a.3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final SensorsDataRepositoryImpl invoke() {
                SensorsDataRepositoryImpl.a aVar = SensorsDataRepositoryImpl.f50790a;
                return SensorsDataRepositoryImpl.a.a(a.this.a);
            }
        });
        this.f = LazyKt.lazy(new Function0<b>() { // from class: com.vk.id.captcha.b.a.2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final b invoke() {
                return new b(a.this.a);
            }
        });
        this.e = new Handler(Looper.getMainLooper());
        this.d = Math.max(3, Runtime.getRuntime().availableProcessors());
        this.b = LazyKt.lazy(new Function0<ThreadPoolExecutor>() { // from class: com.vk.id.captcha.b.a.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final ThreadPoolExecutor invoke() {
                return new ThreadPoolExecutor(a.this.d, a.this.d << 1, 10L, TimeUnit.SECONDS, new SynchronousQueue());
            }
        });
    }

    @JvmName(name = "d")
    @NotNull
    public final ThreadPoolExecutor d() {
        return (ThreadPoolExecutor) this.b.getValue();
    }

    @JvmName(name = "e")
    /* JADX INFO: renamed from: e, reason: from getter */
    public final boolean getC() {
        return this.c;
    }

    @JvmName(name = "a")
    @NotNull
    public final SensorsDataRepository a() {
        return (SensorsDataRepository) this.g.getValue();
    }

    @JvmName(name = "b")
    @NotNull
    public final b b() {
        return (b) this.f.getValue();
    }

    @JvmName(name = "c")
    @NotNull
    /* JADX INFO: renamed from: c, reason: from getter */
    public final Handler getE() {
        return this.e;
    }

    @JvmName(name = "a")
    public final void a(boolean z10) {
        this.c = true;
    }
}
