package com.vk.id.captcha.sensors;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    private static final Lazy f50783a = LazyKt.lazy(new Function0<d>() { // from class: com.vk.id.captcha.c.b.1
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final d invoke() {
            return new d("vk-sensor-thread");
        }
    });

    @NotNull
    public static final d a() {
        return (d) f50783a.getValue();
    }
}
