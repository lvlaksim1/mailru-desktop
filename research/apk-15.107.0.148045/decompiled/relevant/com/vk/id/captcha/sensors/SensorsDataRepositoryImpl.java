package com.vk.id.captcha.sensors;

import android.content.Context;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;

/* JADX INFO: renamed from: com.vk.id.captcha.c.f, reason: from Kotlin metadata */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0016\b\u0000\u0018\u00002\u00020\u0001:\u00016B;\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0004¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J-\u0010\u0015\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u000e0\rj\u0002`\u0012\u0012\u0004\u0012\u00020\u00130\u0011H\u0002¢\u0006\u0004\b\u0015\u0010\u0016JI\u0010\u001d\u001a\u00020\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\r2\u0006\u0010\u001a\u001a\u00020\u00192\u001c\u0010\u0014\u001a\u0018\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u000e0\rj\u0002`\u0012\u0012\u0004\u0012\u00020\u00130\u0011H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001e\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010 R\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R(\u0010%\u001a\u0004\u0018\u00010\u00052\b\u0010$\u001a\u0004\u0018\u00010\u00058\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b%\u0010&\"\u0004\b'\u0010(R(\u0010)\u001a\u0004\u0018\u00010\u00072\b\u0010$\u001a\u0004\u0018\u00010\u00078\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b)\u0010*\"\u0004\b+\u0010,R(\u0010-\u001a\u0004\u0018\u00010\t2\b\u0010$\u001a\u0004\u0018\u00010\t8\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\b\u0012\u0004\u0012\u00020\u00170\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010 R\u0016\u00103\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u0010#R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00104R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010 R\u0016\u00105\u001a\u00020!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u0010#\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00067"}, d2 = {"Lcom/vk/id/captcha/sensors/SensorsDataRepositoryImpl;", "Lcom/vk/id/captcha/sensors/SensorsDataRepository;", "Landroid/os/Handler;", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Lcom/vk/id/captcha/sensors/BaseSensorListener;", "Lcom/vk/id/captcha/sensors/model/SensorData$AccelerometerSensorData;", "accelerometer", "Lcom/vk/id/captcha/sensors/model/SensorData$GyroscopeSensorData;", "gyroscope", "Lcom/vk/id/captcha/sensors/model/SensorData$MotionSensorData;", "motion", "<init>", "(Landroid/os/Handler;Lcom/vk/id/captcha/sensors/BaseSensorListener;Lcom/vk/id/captcha/sensors/BaseSensorListener;Lcom/vk/id/captcha/sensors/BaseSensorListener;)V", "", "Lcom/vk/id/captcha/sensors/model/SensorData;", "getCurrentData", "()Ljava/util/List;", "Lkotlin/Function1;", "Lcom/vk/id/captcha/sensors/model/SensorsData;", "", "onDataUpdate", "onNewData", "(Lkotlin/jvm/functions/Function1;)V", "Lcom/vk/id/captcha/sensors/model/Sensors;", "sensors", "Lcom/vk/id/captcha/sensors/model/PeriodMs;", "periodMs", "startListening-vmuVbT4", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;)V", "startListening", "stopListening", "()V", "Lcom/vk/id/captcha/sensors/BaseSensorListener;", "", "accelerometerChanged", "Z", "value", "currentAccelerometerSensorData", "Lcom/vk/id/captcha/sensors/model/SensorData$AccelerometerSensorData;", "setCurrentAccelerometerSensorData", "(Lcom/vk/id/captcha/sensors/model/SensorData$AccelerometerSensorData;)V", "currentGyroscopeSensorData", "Lcom/vk/id/captcha/sensors/model/SensorData$GyroscopeSensorData;", "setCurrentGyroscopeSensorData", "(Lcom/vk/id/captcha/sensors/model/SensorData$GyroscopeSensorData;)V", "currentMotionSensorData", "Lcom/vk/id/captcha/sensors/model/SensorData$MotionSensorData;", "setCurrentMotionSensorData", "(Lcom/vk/id/captcha/sensors/model/SensorData$MotionSensorData;)V", "currentSensors", "Ljava/util/List;", "gyroscopeChanged", "Landroid/os/Handler;", "motionChanged", "Companion", "captcha_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SensorsDataRepositoryImpl implements SensorsDataRepository {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    public static final a f50790a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NotNull
    private final Handler f50791b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NotNull
    private final BaseSensorListener<com.vk.id.captcha.sensors.a.a.C0198a> f50792c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NotNull
    private final BaseSensorListener<com.vk.id.captcha.c.a.a.b> f50793d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @NotNull
    private final BaseSensorListener<com.vk.id.captcha.c.a.a.c> f50794e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    private com.vk.id.captcha.sensors.a.a.C0198a f50795f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @Nullable
    private com.vk.id.captcha.c.a.a.b f50796g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @Nullable
    private com.vk.id.captcha.c.a.a.c f50797h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @NotNull
    private List<? extends com.vk.id.captcha.sensors.a.b> f50798i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f50799j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f50800k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f50801l;

    /* JADX INFO: renamed from: com.vk.id.captcha.c.f$a */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0001¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lcom/vk/id/captcha/c/f$a;", "", "Landroid/content/Context;", "p0", "Lcom/vk/id/captcha/c/f;", "a", "(Landroid/content/Context;)Lcom/vk/id/captcha/c/f;", "<init>", "()V"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class a {

        /* JADX INFO: renamed from: com.vk.id.captcha.c.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* synthetic */ class C0202a extends FunctionReferenceImpl implements Function1<float[], com.vk.id.captcha.sensors.a.a.C0198a> {
            C0202a(com.vk.id.captcha.sensors.a.a.C0198a.Companion companion) {
                super(1, companion, com.vk.id.captcha.sensors.a.a.C0198a.Companion.class, "a", "a([F)Lcom/vk/id/captcha/c/a/a$a;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.vk.id.captcha.sensors.a.a.C0198a invoke(@NotNull float[] fArr) {
                Intrinsics.checkNotNullParameter(fArr, "");
                return ((com.vk.id.captcha.sensors.a.a.C0198a.Companion) this.receiver).a(fArr);
            }
        }

        /* JADX INFO: renamed from: com.vk.id.captcha.c.f$a$b */
        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* synthetic */ class b extends FunctionReferenceImpl implements Function1<float[], com.vk.id.captcha.c.a.a.b> {
            b(com.vk.id.captcha.c.a.a.b.Companion companion) {
                super(1, companion, com.vk.id.captcha.c.a.a.b.Companion.class, "a", "a([F)Lcom/vk/id/captcha/c/a/a$b;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.vk.id.captcha.c.a.a.b invoke(@NotNull float[] fArr) {
                Intrinsics.checkNotNullParameter(fArr, "");
                return ((com.vk.id.captcha.c.a.a.b.Companion) this.receiver).a(fArr);
            }
        }

        /* JADX INFO: renamed from: com.vk.id.captcha.c.f$a$c */
        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* synthetic */ class c extends FunctionReferenceImpl implements Function1<float[], com.vk.id.captcha.c.a.a.c> {
            c(com.vk.id.captcha.c.a.a.c.Companion companion) {
                super(1, companion, com.vk.id.captcha.c.a.a.c.Companion.class, "a", "a([F)Lcom/vk/id/captcha/c/a/a$c;", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.vk.id.captcha.c.a.a.c invoke(@NotNull float[] fArr) {
                Intrinsics.checkNotNullParameter(fArr, "");
                return ((com.vk.id.captcha.c.a.a.c.Companion) this.receiver).a(fArr);
            }
        }

        private a() {
        }

        @NotNull
        public static SensorsDataRepositoryImpl a(@NotNull Context p10) {
            Intrinsics.checkNotNullParameter(p10, "");
            AndroidSensorListener androidSensorListener = new AndroidSensorListener(p10, null, 1, new C0202a(com.vk.id.captcha.sensors.a.a.C0198a.INSTANCE), 2, null);
            AndroidSensorListener androidSensorListener2 = new AndroidSensorListener(p10, null, 4, new b(com.vk.id.captcha.c.a.a.b.INSTANCE), 2, null);
            AndroidSensorListener androidSensorListener3 = new AndroidSensorListener(p10, null, 11, new c(com.vk.id.captcha.c.a.a.c.INSTANCE), 2, null);
            com.vk.id.captcha.b.a.Companion companion = com.vk.id.captcha.b.a.INSTANCE;
            return new SensorsDataRepositoryImpl(com.vk.id.captcha.b.a.Companion.a().getE(), androidSensorListener, androidSensorListener2, androidSensorListener3, null);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: renamed from: com.vk.id.captcha.c.f$b */
    /* JADX INFO: compiled from: ProGuard */
    public /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f50805a;

        static {
            int[] iArr = new int[com.vk.id.captcha.sensors.a.b.a().length];
            try {
                iArr[com.vk.id.captcha.sensors.a.b.ACCELEROMETER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[com.vk.id.captcha.sensors.a.b.GYROSCOPE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[com.vk.id.captcha.sensors.a.b.MOTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            f50805a = iArr;
        }
    }

    public /* synthetic */ SensorsDataRepositoryImpl(Handler handler, BaseSensorListener baseSensorListener, BaseSensorListener baseSensorListener2, BaseSensorListener baseSensorListener3, DefaultConstructorMarker defaultConstructorMarker) {
        this(handler, baseSensorListener, baseSensorListener2, baseSensorListener3);
    }

    @Override // com.vk.id.captcha.sensors.SensorsDataRepository
    public final void a(@NotNull List<? extends com.vk.id.captcha.sensors.a.b> list, int i10, @NotNull final Function1<? super List<? extends com.vk.id.captcha.sensors.a.a>, Unit> function1) {
        Intrinsics.checkNotNullParameter(list, "");
        Intrinsics.checkNotNullParameter(function1, "");
        this.f50798i = list;
        Iterator<? extends com.vk.id.captcha.sensors.a.b> it = list.iterator();
        while (it.hasNext()) {
            int i11 = b.f50805a[it.next().ordinal()];
            if (i11 == 1) {
                this.f50792c.a(i10);
                this.f50792c.a(new Function1<com.vk.id.captcha.sensors.a.a.C0198a, Unit>() { // from class: com.vk.id.captcha.c.f.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    public final void a(@NotNull com.vk.id.captcha.sensors.a.a.C0198a c0198a) {
                        Intrinsics.checkNotNullParameter(c0198a, "");
                        SensorsDataRepositoryImpl.a(SensorsDataRepositoryImpl.this, c0198a);
                        SensorsDataRepositoryImpl.a(SensorsDataRepositoryImpl.this, function1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final /* synthetic */ Unit invoke(com.vk.id.captcha.sensors.a.a.C0198a c0198a) {
                        a(c0198a);
                        return Unit.INSTANCE;
                    }
                });
            } else if (i11 == 2) {
                this.f50793d.a(i10);
                this.f50793d.a(new Function1<com.vk.id.captcha.c.a.a.b, Unit>() { // from class: com.vk.id.captcha.c.f.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    public final void a(@NotNull com.vk.id.captcha.c.a.a.b bVar) {
                        Intrinsics.checkNotNullParameter(bVar, "");
                        SensorsDataRepositoryImpl.a(SensorsDataRepositoryImpl.this, bVar);
                        SensorsDataRepositoryImpl.a(SensorsDataRepositoryImpl.this, function1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final /* synthetic */ Unit invoke(com.vk.id.captcha.c.a.a.b bVar) {
                        a(bVar);
                        return Unit.INSTANCE;
                    }
                });
            } else if (i11 == 3) {
                this.f50794e.a(i10);
                this.f50794e.a(new Function1<com.vk.id.captcha.c.a.a.c, Unit>() { // from class: com.vk.id.captcha.c.f.3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    public final void a(@NotNull com.vk.id.captcha.c.a.a.c cVar) {
                        Intrinsics.checkNotNullParameter(cVar, "");
                        SensorsDataRepositoryImpl.a(SensorsDataRepositoryImpl.this, cVar);
                        SensorsDataRepositoryImpl.a(SensorsDataRepositoryImpl.this, function1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final /* synthetic */ Unit invoke(com.vk.id.captcha.c.a.a.c cVar) {
                        a(cVar);
                        return Unit.INSTANCE;
                    }
                });
            }
        }
    }

    private SensorsDataRepositoryImpl(Handler handler, BaseSensorListener<com.vk.id.captcha.sensors.a.a.C0198a> baseSensorListener, BaseSensorListener<com.vk.id.captcha.c.a.a.b> baseSensorListener2, BaseSensorListener<com.vk.id.captcha.c.a.a.c> baseSensorListener3) {
        this.f50791b = handler;
        this.f50792c = baseSensorListener;
        this.f50793d = baseSensorListener2;
        this.f50794e = baseSensorListener3;
        this.f50798i = CollectionsKt.emptyList();
    }

    @Override // com.vk.id.captcha.sensors.SensorsDataRepository
    public final void a() {
        this.f50792c.a();
        this.f50793d.a();
        this.f50794e.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(Function1 function1, List list) {
        Intrinsics.checkNotNullParameter(function1, "");
        Intrinsics.checkNotNullParameter(list, "");
        function1.invoke(list);
    }

    public static final /* synthetic */ void a(SensorsDataRepositoryImpl sensorsDataRepositoryImpl, com.vk.id.captcha.sensors.a.a.C0198a c0198a) {
        sensorsDataRepositoryImpl.f50795f = c0198a;
        sensorsDataRepositoryImpl.f50799j = true;
    }

    public static final /* synthetic */ void a(SensorsDataRepositoryImpl sensorsDataRepositoryImpl, final Function1 function1) {
        com.vk.id.captcha.sensors.a.a.C0198a c0198a = sensorsDataRepositoryImpl.f50795f;
        com.vk.id.captcha.c.a.a.b bVar = sensorsDataRepositoryImpl.f50796g;
        com.vk.id.captcha.c.a.a.c cVar = sensorsDataRepositoryImpl.f50797h;
        final ArrayList arrayList = new ArrayList();
        Iterator<? extends com.vk.id.captcha.sensors.a.b> it = sensorsDataRepositoryImpl.f50798i.iterator();
        while (it.hasNext()) {
            int i10 = b.f50805a[it.next().ordinal()];
            if (i10 != 1) {
                if (i10 != 2) {
                    if (i10 == 3 && cVar != null && sensorsDataRepositoryImpl.f50801l) {
                        arrayList.add(cVar);
                    }
                } else if (bVar != null && sensorsDataRepositoryImpl.f50800k) {
                    arrayList.add(bVar);
                }
            } else if (c0198a != null && sensorsDataRepositoryImpl.f50799j) {
                arrayList.add(c0198a);
            }
        }
        if (arrayList.size() == sensorsDataRepositoryImpl.f50798i.size()) {
            sensorsDataRepositoryImpl.f50791b.post(new Runnable() { // from class: com.vk.id.captcha.c.g
                @Override // java.lang.Runnable
                public final void run() {
                    SensorsDataRepositoryImpl.a(function1, arrayList);
                }
            });
            Iterator<? extends com.vk.id.captcha.sensors.a.b> it2 = sensorsDataRepositoryImpl.f50798i.iterator();
            while (it2.hasNext()) {
                int i11 = b.f50805a[it2.next().ordinal()];
                if (i11 == 1) {
                    sensorsDataRepositoryImpl.f50799j = false;
                } else if (i11 == 2) {
                    sensorsDataRepositoryImpl.f50800k = false;
                } else if (i11 == 3) {
                    sensorsDataRepositoryImpl.f50801l = false;
                }
            }
        }
    }

    public static final /* synthetic */ void a(SensorsDataRepositoryImpl sensorsDataRepositoryImpl, com.vk.id.captcha.c.a.a.b bVar) {
        sensorsDataRepositoryImpl.f50796g = bVar;
        sensorsDataRepositoryImpl.f50800k = true;
    }

    public static final /* synthetic */ void a(SensorsDataRepositoryImpl sensorsDataRepositoryImpl, com.vk.id.captcha.c.a.a.c cVar) {
        sensorsDataRepositoryImpl.f50797h = cVar;
        sensorsDataRepositoryImpl.f50801l = true;
    }
}
