package ru.mail.libverify.platform.firebase;

import android.content.Context;
import android.content.Intent;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.libverify.platform.core.IInternalFactory;
import ru.mail.libverify.platform.core.ILog;
import ru.mail.libverify.platform.core.ISmsRetrieverService;
import ru.mail.libverify.platform.core.JwsService;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public static ILog f87688a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    public static IInternalFactory f87690c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    public static ISmsRetrieverService f87692e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NotNull
    public static final Lazy<ILog> f87689b = LazyKt.lazy(b.f87698a);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NotNull
    public static final C0391a f87691d = new C0391a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @NotNull
    public static final ISmsRetrieverService f87693f = new ISmsRetrieverService() { // from class: ad.a
        @Override // ru.mail.libverify.platform.core.ISmsRetrieverService
        public final void enqueueWork(Context context, Intent intent) {
            ru.mail.libverify.platform.firebase.a.a(context, intent);
        }
    };

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @NotNull
    public static final ru.mail.libverify.platform.firebase.d.a f87694g = new ru.mail.libverify.platform.firebase.d.a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @NotNull
    public static final Lazy<ru.mail.libverify.platform.firebase.b.b> f87695h = LazyKt.lazy(c.f87699a);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @NotNull
    public static final Lazy<ru.mail.libverify.platform.firebase.c.a> f87696i = LazyKt.lazy(e.f87701a);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    @NotNull
    public static final Lazy<JwsService> f87697j = LazyKt.lazy(d.f87700a);

    /* JADX INFO: renamed from: ru.mail.libverify.platform.firebase.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ProGuard */
    public static final class C0391a implements IInternalFactory {
        @Override // ru.mail.libverify.platform.core.IInternalFactory
        public final void deliverGcmMessageIntent(@NotNull Context context, @Nullable String str, @NotNull Map<String, String> data) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(data, "data");
        }

        @Override // ru.mail.libverify.platform.core.IInternalFactory
        public final void refreshGcmToken(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static final class b extends Lambda implements Function0<ILog> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f87698a = new b();

        public b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final ILog invoke() {
            return new ru.mail.libverify.platform.firebase.b();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static final class c extends Lambda implements Function0<ru.mail.libverify.platform.firebase.b.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f87699a = new c();

        public c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final ru.mail.libverify.platform.firebase.b.b invoke() {
            FirebaseCoreService.INSTANCE.getClass();
            return new ru.mail.libverify.platform.firebase.b.b(FirebaseCoreService.Companion.a());
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static final class d extends Lambda implements Function0<JwsService> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f87700a = new d();

        public d() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final JwsService invoke() {
            return new ru.mail.libverify.platform.firebase.a.b();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static final class e extends Lambda implements Function0<ru.mail.libverify.platform.firebase.c.a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f87701a = new e();

        public e() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final ru.mail.libverify.platform.firebase.c.a invoke() {
            return new ru.mail.libverify.platform.firebase.c.a();
        }
    }

    public static final void a(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(intent, "<anonymous parameter 1>");
    }
}
