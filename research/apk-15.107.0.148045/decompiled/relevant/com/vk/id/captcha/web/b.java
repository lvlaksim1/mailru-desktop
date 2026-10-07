package com.vk.id.captcha.web;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    private final Context f50837a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NotNull
    private final Lazy f50838b;

    public b(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        this.f50837a = context;
        this.f50838b = LazyKt.lazy(new Function0<ConnectivityManager>() { // from class: com.vk.id.captcha.web.b.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @Nullable
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final ConnectivityManager invoke() {
                return (ConnectivityManager) b.this.f50837a.getSystemService("connectivity");
            }
        });
    }

    private final ConnectivityManager b() {
        return (ConnectivityManager) this.f50838b.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0040  */
    public final boolean a() {
        Network activeNetwork;
        Boolean boolValueOf;
        NetworkCapabilities networkCapabilities;
        boolean z10;
        ConnectivityManager connectivityManagerB = b();
        if (connectivityManagerB != null && (activeNetwork = connectivityManagerB.getActiveNetwork()) != null) {
            ConnectivityManager connectivityManagerB2 = b();
            if (connectivityManagerB2 == null || (networkCapabilities = connectivityManagerB2.getNetworkCapabilities(activeNetwork)) == null) {
                boolValueOf = null;
            } else {
                Intrinsics.checkNotNull(networkCapabilities);
                if (networkCapabilities.hasCapability(12)) {
                    z10 = true;
                    if (!networkCapabilities.hasTransport(1) && !networkCapabilities.hasTransport(4) && !networkCapabilities.hasTransport(0) && !networkCapabilities.hasTransport(3)) {
                        z10 = false;
                    }
                } else {
                    z10 = false;
                }
                boolValueOf = Boolean.valueOf(z10);
            }
            if (boolValueOf != null) {
                return boolValueOf.booleanValue();
            }
        }
        return false;
    }
}
