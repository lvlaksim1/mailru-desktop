package com.vk.id.captcha.web.a;

import android.app.Activity;
import android.app.Dialog;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
public final class b extends Dialog {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(@NotNull Activity activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "");
    }
}
