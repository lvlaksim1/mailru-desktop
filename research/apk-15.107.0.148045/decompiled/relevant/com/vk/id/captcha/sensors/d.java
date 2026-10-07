package com.vk.id.captcha.sensors;

import android.os.HandlerThread;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@SourceDebugExtension({"SMAP\nHandlerThreadProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HandlerThreadProvider.kt\ncom/vk/id/captcha/sensors/HandlerThreadProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,44:1\n1#2:45\n*E\n"})
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    private final String f50787a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private volatile HandlerThread f50788b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NotNull
    private AtomicInteger f50789c;

    public d(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.f50787a = str;
        this.f50789c = new AtomicInteger();
    }

    @NotNull
    public final HandlerThread a() {
        HandlerThread handlerThread = this.f50788b;
        if (handlerThread != null) {
            this.f50789c.incrementAndGet();
            return handlerThread;
        }
        synchronized (this) {
            HandlerThread handlerThread2 = this.f50788b;
            if (handlerThread2 != null) {
                this.f50789c.incrementAndGet();
                return handlerThread2;
            }
            HandlerThread handlerThread3 = new HandlerThread(this.f50787a);
            handlerThread3.start();
            this.f50788b = handlerThread3;
            this.f50789c.incrementAndGet();
            return handlerThread3;
        }
    }

    public final void b() {
        if (this.f50788b == null) {
            this.f50789c.decrementAndGet();
            return;
        }
        synchronized (this) {
            try {
                if (this.f50789c.decrementAndGet() == 0) {
                    HandlerThread handlerThread = this.f50788b;
                    if (handlerThread != null) {
                        handlerThread.quit();
                    }
                    this.f50788b = null;
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}
