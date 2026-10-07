package com.vk.id.captcha.sensors;

import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.vk.id.captcha.sensors.a.a;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: renamed from: com.vk.id.captcha.c.c, reason: from Kotlin metadata */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b`\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001\u0010J#\u0010\u0007\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004H&¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\tH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\u0005H&¢\u0006\u0004\b\u000e\u0010\u000f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lcom/vk/id/captcha/sensors/BaseSensorListener;", "Lcom/vk/id/captcha/sensors/model/SensorData;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", "Lkotlin/Function1;", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnSensorChangedListener", "(Lkotlin/jvm/functions/Function1;)V", "Lcom/vk/id/captcha/sensors/model/PeriodMs;", "periodMs", "startListening-6M9zLzk", "(I)V", "startListening", "stopListening", "()V", "Companion", "captcha_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface BaseSensorListener<T extends com.vk.id.captcha.sensors.a.a> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NotNull
    public static final a f50785b = a.f50786a;

    /* JADX INFO: renamed from: com.vk.id.captcha.c.c$a */
    /* JADX INFO: compiled from: ProGuard */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ a f50786a = new a();

        private a() {
        }
    }

    void a();

    void a(int i10);

    void a(@NotNull Function1<? super T, Unit> function1);
}
