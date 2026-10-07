package com.vk.auth.captcha.impl.utils;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0001H\u0000¨\u0006\u0007"}, d2 = {"queryWidth", "Landroid/net/Uri$Builder;", "width", "", "queryRefresh", "querySwapType", "queryFirst", "impl_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class UriUtilsKt {
    @NotNull
    public static final Uri.Builder queryFirst(@NotNull Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.appendQueryParameter(PreferenceHostProvider.URL_PARAM_FIRST, "1");
        return builder;
    }

    @NotNull
    public static final Uri.Builder queryRefresh(@NotNull Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.appendQueryParameter("refresh", "1");
        return builder;
    }

    @NotNull
    public static final Uri.Builder querySwapType(@NotNull Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.appendQueryParameter("swap_type", "1");
        return builder;
    }

    @NotNull
    public static final Uri.Builder queryWidth(@NotNull Uri.Builder builder, int i10) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.appendQueryParameter("width", String.valueOf(i10));
        return builder;
    }
}
