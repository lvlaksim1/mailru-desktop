package com.vk.id.captcha.web;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.offline.bundle.utils.HashRoutingUrl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f50841a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NotNull
    private final String f50842b;

    public d(boolean z10, @NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.f50841a = z10;
        this.f50842b = str;
    }

    public final boolean a(@Nullable View view, @Nullable Uri uri) {
        if (uri != null && this.f50841a) {
            String string = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string, "");
            if (StringsKt.startsWith$default(string, StringsKt.substringBefore$default(this.f50842b, HashRoutingUrl.END_HASH_ROUTING, (String) null, 2, (Object) null), false, 2, (Object) null)) {
                return false;
            }
        }
        if (uri != null && view != null) {
            a aVar = new a();
            String string2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "");
            if (aVar.a(string2)) {
                Intent data = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL).setData(uri);
                Intrinsics.checkNotNullExpressionValue(data, "");
                try {
                    view.getContext().startActivity(data);
                    return true;
                } catch (ActivityNotFoundException unused) {
                    return false;
                }
            }
        }
        return true;
    }
}
