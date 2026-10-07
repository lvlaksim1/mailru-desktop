package com.vk.id.captcha.sensors;

import com.vk.id.captcha.sensors.a.a;
import com.vk.id.captcha.sensors.a.b;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: renamed from: com.vk.id.captcha.c.e, reason: from Kotlin metadata */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001JI\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u001c\u0010\t\u001a\u0018\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u000b0\u0005j\u0002`\f\u0012\u0004\u0012\u00020\u00030\nH&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0003H&\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcom/vk/id/captcha/sensors/SensorsDataRepository;", "", "startListening", "", "sensors", "", "Lcom/vk/id/captcha/sensors/model/Sensors;", "periodMs", "Lcom/vk/id/captcha/sensors/model/PeriodMs;", "onDataUpdate", "Lkotlin/Function1;", "Lcom/vk/id/captcha/sensors/model/SensorData;", "Lcom/vk/id/captcha/sensors/model/SensorsData;", "startListening-vmuVbT4", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;)V", "stopListening", "captcha_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface SensorsDataRepository {
    void a();

    void a(@NotNull List<? extends b> list, int i10, @NotNull Function1<? super List<? extends a>, Unit> function1);
}
