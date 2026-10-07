package com.vk.id.captcha.sensors.a;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.kotlett.runtime.divkit.InterpolatorFields;
import ru.mail.search.config.ConfigFactory;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b0\u0018\u00002\u00020\u0001:\u0003\u0006\u0003\nB\t\b\u0004¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0006\u001a\u00020\u00058'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0003\u000b\f\r"}, d2 = {"Lcom/vk/id/captcha/c/a/a;", "", "Lorg/json/JSONObject;", "b", "()Lorg/json/JSONObject;", "", "a", "()Ljava/lang/String;", "<init>", "()V", "c", "Lcom/vk/id/captcha/c/a/a$a;", "Lcom/vk/id/captcha/c/a/a$b;", "Lcom/vk/id/captcha/c/a/a$c;"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class a {

    /* JADX INFO: renamed from: com.vk.id.captcha.c.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0013\u0012\u0006\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0017¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH×\u0001¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0012\u001a\u00020\r8\u0017X\u0097D¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0013X\u0007¢\u0006\u0006\n\u0004\b\u000b\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0013X\u0007¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0016\u001a\u00020\u0013X\u0007¢\u0006\u0006\n\u0004\b\u0016\u0010\u0014"}, d2 = {"Lcom/vk/id/captcha/c/a/a$a;", "Lcom/vk/id/captcha/c/a/a;", "", "p0", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lorg/json/JSONObject;", "b", "()Lorg/json/JSONObject;", "", "toString", "()Ljava/lang/String;", "e", "Ljava/lang/String;", "a", "", "F", "c", "d", "p1", "p2", "<init>", "(FFF)V"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class C0198a extends a {

        /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);
        public float b;
        public float c;
        public float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name and from kotlin metadata */
        @NotNull
        private final String a;

        /* JADX INFO: renamed from: com.vk.id.captcha.c.a.a$a$a, reason: collision with other inner class name and from kotlin metadata */
        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lcom/vk/id/captcha/c/a/a$a$a;", "", "", "p0", "Lcom/vk/id/captcha/c/a/a$a;", "a", "([F)Lcom/vk/id/captcha/c/a/a$a;", "<init>", "()V"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final C0198a a(@NotNull float[] p10) {
                Intrinsics.checkNotNullParameter(p10, "");
                return new C0198a(p10[0], p10[1], p10[2]);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public C0198a(float f10, float f11, float f12) {
            super(null);
            this.b = f10;
            this.c = f11;
            this.d = f12;
            this.a = "accelerometer";
        }

        @Override // com.vk.id.captcha.sensors.a.a
        @JvmName(name = "a")
        @NotNull
        /* JADX INFO: renamed from: a, reason: from getter */
        public final String getD() {
            return this.a;
        }

        @Override // com.vk.id.captcha.sensors.a.a
        @NotNull
        public final JSONObject b() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(InterpolatorFields.Path.X, Float.valueOf(this.b));
            jSONObject.put(InterpolatorFields.Path.Y, Float.valueOf(this.c));
            jSONObject.put("z", Float.valueOf(this.d));
            return jSONObject;
        }

        public final boolean equals(@Nullable Object p10) {
            if (this == p10) {
                return true;
            }
            if (!(p10 instanceof C0198a)) {
                return false;
            }
            C0198a c0198a = (C0198a) p10;
            return Float.compare(this.b, c0198a.b) == 0 && Float.compare(this.c, c0198a.c) == 0 && Float.compare(this.d, c0198a.d) == 0;
        }

        public final int hashCode() {
            return (((Float.hashCode(this.b) * 31) + Float.hashCode(this.c)) * 31) + Float.hashCode(this.d);
        }

        @NotNull
        public final String toString() {
            return "a(b=" + this.b + ", c=" + this.c + ", d=" + this.d + ')';
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0013\u0012\u0006\u0010\u0017\u001a\u00020\u0013\u0012\u0006\u0010\u0018\u001a\u00020\u0013¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0017¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH×\u0001¢\u0006\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0012\u001a\u00020\r8\u0017X\u0097D¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0013X\u0007¢\u0006\u0006\n\u0004\b\u000b\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0013X\u0007¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0016\u001a\u00020\u0013X\u0007¢\u0006\u0006\n\u0004\b\u0016\u0010\u0014"}, d2 = {"Lcom/vk/id/captcha/c/a/a$b;", "Lcom/vk/id/captcha/c/a/a;", "", "p0", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lorg/json/JSONObject;", "b", "()Lorg/json/JSONObject;", "", "toString", "()Ljava/lang/String;", "e", "Ljava/lang/String;", "a", "", "F", "c", "d", "p1", "p2", "<init>", "(FFF)V"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class b extends a {

        /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);
        public float b;
        public float c;
        public float d;

        /* JADX INFO: renamed from: e, reason: collision with root package name and from kotlin metadata */
        @NotNull
        private final String a;

        /* JADX INFO: renamed from: com.vk.id.captcha.c.a.a$b$a, reason: collision with other inner class name and from kotlin metadata */
        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lcom/vk/id/captcha/c/a/a$b$a;", "", "", "p0", "Lcom/vk/id/captcha/c/a/a$b;", "a", "([F)Lcom/vk/id/captcha/c/a/a$b;", "<init>", "()V"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final b a(@NotNull float[] p10) {
                Intrinsics.checkNotNullParameter(p10, "");
                return new b(p10[0], p10[1], p10[2]);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public b(float f10, float f11, float f12) {
            super(null);
            this.b = f10;
            this.c = f11;
            this.d = f12;
            this.a = "gyroscope";
        }

        @Override // com.vk.id.captcha.sensors.a.a
        @JvmName(name = "a")
        @NotNull
        /* JADX INFO: renamed from: a, reason: from getter */
        public final String getD() {
            return this.a;
        }

        @Override // com.vk.id.captcha.sensors.a.a
        @NotNull
        public final JSONObject b() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(InterpolatorFields.Path.X, Float.valueOf(this.b));
            jSONObject.put(InterpolatorFields.Path.Y, Float.valueOf(this.c));
            jSONObject.put("z", Float.valueOf(this.d));
            return jSONObject;
        }

        public final boolean equals(@Nullable Object p10) {
            if (this == p10) {
                return true;
            }
            if (!(p10 instanceof b)) {
                return false;
            }
            b bVar = (b) p10;
            return Float.compare(this.b, bVar.b) == 0 && Float.compare(this.c, bVar.c) == 0 && Float.compare(this.d, bVar.d) == 0;
        }

        public final int hashCode() {
            return (((Float.hashCode(this.b) * 31) + Float.hashCode(this.c)) * 31) + Float.hashCode(this.d);
        }

        @NotNull
        public final String toString() {
            return "b(b=" + this.b + ", c=" + this.c + ", d=" + this.d + ')';
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\b\u0086\b\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0010\u0012\u0006\u0010\u0017\u001a\u00020\u0010\u0012\u0006\u0010\u0018\u001a\u00020\u0010¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÖ\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0017¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH×\u0001¢\u0006\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\u0010X\u0006¢\u0006\u0006\n\u0004\b\u000b\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0010X\u0006¢\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u0010X\u0006¢\u0006\u0006\n\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0014\u001a\u00020\r8\u0017X\u0097D¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0012\u0010\u000f"}, d2 = {"Lcom/vk/id/captcha/c/a/a$c;", "Lcom/vk/id/captcha/c/a/a;", "", "p0", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Lorg/json/JSONObject;", "b", "()Lorg/json/JSONObject;", "", "toString", "()Ljava/lang/String;", "", "F", "a", "c", "d", "e", "Ljava/lang/String;", "p1", "p2", "<init>", "(FFF)V"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class c extends a {

        /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        /* JADX INFO: renamed from: b, reason: collision with root package name and from kotlin metadata */
        public float a;

        /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
        public float b;

        /* JADX INFO: renamed from: d, reason: collision with root package name and from kotlin metadata */
        public float c;

        /* JADX INFO: renamed from: e, reason: collision with root package name and from kotlin metadata */
        @NotNull
        private final String d;

        /* JADX INFO: renamed from: com.vk.id.captcha.c.a.a$c$a, reason: collision with other inner class name and from kotlin metadata */
        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lcom/vk/id/captcha/c/a/a$c$a;", "", "", "p0", "Lcom/vk/id/captcha/c/a/a$c;", "a", "([F)Lcom/vk/id/captcha/c/a/a$c;", "<init>", "()V"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final c a(@NotNull float[] p10) {
                Intrinsics.checkNotNullParameter(p10, "");
                float f10 = p10[0];
                return new c(f10, f10, f10);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public c(float f10, float f11, float f12) {
            super(null);
            this.a = f10;
            this.b = f11;
            this.c = f12;
            this.d = "motion";
        }

        @Override // com.vk.id.captcha.sensors.a.a
        @JvmName(name = "a")
        @NotNull
        /* JADX INFO: renamed from: a, reason: from getter */
        public final String getD() {
            return this.d;
        }

        @Override // com.vk.id.captcha.sensors.a.a
        @NotNull
        public final JSONObject b() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ConfigFactory.ENVIRONMENT_ALPHA, Float.valueOf(this.a));
            jSONObject.put("beta", Float.valueOf(this.b));
            jSONObject.put("gamma", Float.valueOf(this.c));
            return jSONObject;
        }

        public final boolean equals(@Nullable Object p10) {
            if (this == p10) {
                return true;
            }
            if (!(p10 instanceof c)) {
                return false;
            }
            c cVar = (c) p10;
            return Float.compare(this.a, cVar.a) == 0 && Float.compare(this.b, cVar.b) == 0 && Float.compare(this.c, cVar.c) == 0;
        }

        public final int hashCode() {
            return (((Float.hashCode(this.a) * 31) + Float.hashCode(this.b)) * 31) + Float.hashCode(this.c);
        }

        @NotNull
        public final String toString() {
            return "c(a=" + this.a + ", b=" + this.b + ", c=" + this.c + ')';
        }
    }

    private a() {
    }

    @JvmName(name = "a")
    @NotNull
    /* JADX INFO: renamed from: a */
    public abstract String getD();

    @NotNull
    public abstract JSONObject b();

    public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
