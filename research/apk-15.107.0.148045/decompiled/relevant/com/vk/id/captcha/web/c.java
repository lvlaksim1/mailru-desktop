package com.vk.id.captcha.web;

import android.content.res.Configuration;
import android.net.Uri;
import com.my.target.common.webform.WebFormSetViewSettings;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    private final Configuration f50840a;

    public c(@NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "");
        this.f50840a = configuration;
    }

    @NotNull
    public final String a(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "");
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        int i10 = this.f50840a.uiMode & 48;
        String str2 = WebFormSetViewSettings.StatusBarStyle.LIGHT;
        if (i10 != 16 && i10 == 32) {
            str2 = "dark";
        }
        String string = builderBuildUpon.appendQueryParameter("scheme", str2).build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        return string;
    }
}
