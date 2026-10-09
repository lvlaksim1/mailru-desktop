package ru.mail.libverify.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.service.notification.StatusBarNotification;
import androidx.annotation.RequiresApi;
import androidx.core.content.pm.ShortcutManagerCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.libverify.i.m;
import ru.mail.verify.core.utils.FileLog;
import ru.mail.verify.core.utils.components.MessageBus;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@SourceDebugExtension({"SMAP\nNotificationBarManagerImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationBarManagerImpl.kt\nru/mail/libverify/notifications/NotificationBarManagerImpl\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,189:1\n372#2,7:190\n215#3,2:197\n*S KotlinDebug\n*F\n+ 1 NotificationBarManagerImpl.kt\nru/mail/libverify/notifications/NotificationBarManagerImpl\n*L\n37#1:190,7\n101#1:197,2\n*E\n"})
public final class a implements ru.mail.libverify.i.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    private final Context f87578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NotNull
    private final MessageBus f87579b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NotNull
    private final ru.mail.libverify.g0.d f87580c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NotNull
    private final m f87581d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @NotNull
    private final ru.mail.libverify.i.f f87582e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @NotNull
    private final ru.mail.libverify.w.f f87583f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @NotNull
    private final Lazy f87584g = LazyKt.lazy(new b());

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @NotNull
    private final HashMap<String, HashSet<String>> f87585h = new HashMap<>();

    /* JADX INFO: renamed from: ru.mail.libverify.notifications.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ProGuard */
    public static final class C0388a {
        @JvmStatic
        @RequiresApi(23)
        @Nullable
        public static Notification a(@NotNull Context context, @Nullable String str) {
            for (StatusBarNotification statusBarNotification : ((NotificationManager) context.getSystemService("notification")).getActiveNotifications()) {
                if (Intrinsics.areEqual(statusBarNotification.getTag(), str)) {
                    return statusBarNotification.getNotification();
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    static final class b extends Lambda implements Function0<NotificationManager> {
        b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final NotificationManager invoke() {
            return (NotificationManager) a.this.f87578a.getSystemService("notification");
        }
    }

    @Inject
    public a(@NotNull Context context, @NotNull MessageBus messageBus, @NotNull ru.mail.libverify.g0.d dVar, @NotNull m mVar, @NotNull ru.mail.libverify.i.f fVar, @NotNull ru.mail.libverify.w.f fVar2) {
        this.f87578a = context;
        this.f87579b = messageBus;
        this.f87580c = dVar;
        this.f87581d = mVar;
        this.f87582e = fVar;
        this.f87583f = fVar2;
    }

    @Override // ru.mail.libverify.i.c
    public final void a(@NotNull String str, @NotNull ru.mail.libverify.i.k kVar) {
        HashMap<String, HashSet<String>> map = this.f87585h;
        HashSet<String> hashSet = map.get(str);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            map.put(str, hashSet);
        }
        hashSet.add(kVar.d());
        a(kVar);
    }

    @Override // ru.mail.libverify.i.c
    public final void b(@NotNull String str) {
        HashSet<String> hashSet = this.f87585h.get(str);
        if (hashSet == null) {
            return;
        }
        for (String str2 : hashSet) {
            a(str2);
            HashSet<String> hashSet2 = this.f87585h.get(str);
            if (hashSet2 != null) {
                hashSet2.remove(str2);
            }
        }
    }

    @Override // ru.mail.libverify.i.c
    public final void b() {
        Iterator<Map.Entry<String, ru.mail.libverify.i.k>> it = this.f87582e.a().entrySet().iterator();
        while (it.hasNext()) {
            ru.mail.libverify.i.k value = it.next().getValue();
            if (C0388a.a(this.f87578a, value.d()) != null) {
                a(value);
            } else {
                a(value.d());
            }
        }
    }

    @Override // ru.mail.libverify.i.c
    public final void a(@NotNull final ru.mail.libverify.i.k kVar) {
        Long lC;
        FileLog.v("NotificationBarManager", "show notification %s", kVar.d());
        this.f87582e.a(kVar.d(), kVar);
        b(kVar);
        if (!kVar.e() || (lC = kVar.c()) == null) {
            return;
        }
        long jLongValue = lC.longValue();
        FileLog.v("NotificationBarManager", "notification %s ongoing timeout %d", kVar.d(), lC);
        this.f87579b.a(ru.mail.libverify.p0.e.a(ru.mail.libverify.p0.a.NOTIFICATION_BAR_MANAGER_ONGOING_NOTIFICATION_SHOWN, kVar.d(), lC));
        this.f87580c.b().postDelayed(new Runnable() { // from class: ru.mail.libverify.notifications.l
            @Override // java.lang.Runnable
            public final void run() {
                a.a(this.f87591a, kVar);
            }
        }, jLongValue);
    }

    private final void b(ru.mail.libverify.i.k kVar) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(this.f87581d.b());
        arrayList.add(this.f87581d.a());
        ((NotificationManager) this.f87584g.getValue()).createNotificationChannels(arrayList);
        Notification notificationBuild = kVar.a(this.f87583f).build();
        if (kVar.f()) {
            notificationBuild.defaults &= -4;
        } else {
            notificationBuild.vibrate = new long[]{500, 500};
        }
        if (this.f87582e.a(kVar.d()) == null) {
            return;
        }
        String strD = kVar.d();
        boolean z10 = false;
        try {
            FileLog.d("NotificationBarManager", "safeNotify tag %s", strD);
            ((NotificationManager) this.f87584g.getValue()).notify(strD, 0, notificationBuild);
            z10 = true;
        } catch (SecurityException e10) {
            FileLog.e("NotificationBarManager", "safeNotify error", e10);
        }
        if (!ru.mail.libverify.i.i.a(this.f87578a, kVar.a()) || !z10) {
            FileLog.e("NotificationBarManager", "Failed to show notification %s", kVar.d());
            this.f87582e.b(kVar.d());
        } else {
            kVar.g();
        }
        ShortcutManagerCompat.removeAllDynamicShortcuts(this.f87578a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(a aVar, ru.mail.libverify.i.k kVar) {
        aVar.b(kVar);
        FileLog.v("NotificationBarManager", "ongoing timeout for %s expired, silent = %s, ongoing = %s", kVar.d(), Boolean.valueOf(kVar.f()), Boolean.valueOf(kVar.e()));
    }

    @Override // ru.mail.libverify.i.c
    public final void a() {
        this.f87582e.clear();
        try {
            FileLog.d("NotificationBarManager", "cancel all");
            ((NotificationManager) this.f87584g.getValue()).cancelAll();
            ShortcutManagerCompat.removeAllDynamicShortcuts(this.f87578a);
        } catch (NullPointerException e10) {
            FileLog.e("NotificationBarManager", "cancel all", e10);
        } catch (SecurityException e11) {
            FileLog.e("NotificationBarManager", "cancel all", e11);
        }
    }

    @Override // ru.mail.libverify.i.c
    public final void a(@NotNull String str) {
        try {
            this.f87582e.b(str);
            FileLog.d("NotificationBarManager", "cancel tag %s", str);
            ((NotificationManager) this.f87584g.getValue()).cancel(str, 0);
            ShortcutManagerCompat.removeAllDynamicShortcuts(this.f87578a);
        } catch (NullPointerException e10) {
            FileLog.e("NotificationBarManager", "cancel", e10);
        } catch (SecurityException e11) {
            FileLog.e("NotificationBarManager", "cancel", e11);
        }
    }
}
