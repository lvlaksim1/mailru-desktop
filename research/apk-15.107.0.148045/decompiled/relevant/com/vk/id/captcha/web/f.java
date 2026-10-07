package com.vk.id.captcha.web;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    private static final Lazy f50850a = LazyKt.lazy(new Function0<com.vk.id.captcha.sensors.d>() { // from class: com.vk.id.captcha.web.f.1
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final com.vk.id.captcha.sensors.d invoke() {
            return new com.vk.id.captcha.sensors.d("vk-webview-thread");
        }
    });

    @NotNull
    public static final com.vk.id.captcha.sensors.d a() {
        return (com.vk.id.captcha.sensors.d) f50850a.getValue();
    }
}
