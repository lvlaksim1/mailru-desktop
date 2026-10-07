package com.vk.auth.captcha.impl.utils;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"setRefreshButtonEnabled", "", "Landroid/view/View;", "enabled", "", "impl_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ViewUtilsKt {
    public static final void setRefreshButtonEnabled(@NotNull View view, boolean z10) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (z10) {
            view.setEnabled(true);
            view.setAlpha(1.0f);
        } else {
            view.setEnabled(false);
            view.setAlpha(0.64f);
        }
    }
}
