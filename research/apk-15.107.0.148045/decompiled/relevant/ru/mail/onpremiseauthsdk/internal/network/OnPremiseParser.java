package ru.mail.onpremiseauthsdk.internal.network;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.onpremiseauthsdk.internal.OnPremiseAuthUtilsKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lru/mail/onpremiseauthsdk/internal/network/OnPremiseParser;", "", "<init>", "()V", "parseWebView", "Lru/mail/onpremiseauthsdk/internal/network/OnPremiseAuth;", "jsonObject", "Lorg/json/JSONObject;", "parseDefault", "onpremiseauthsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OnPremiseParser {
    @NotNull
    public final OnPremiseAuth parseDefault(@NotNull JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        return new OnPremiseAuth.Success(OnPremiseAuthUtilsKt.parseSsoAuthData(jsonObject));
    }

    @NotNull
    public final OnPremiseAuth parseWebView(@NotNull JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        return jsonObject.has("access_token") ? new OnPremiseAuth.Success(OnPremiseAuthUtilsKt.parseAuthData(jsonObject)) : new OnPremiseAuth.Failed(OnPremiseAuthUtilsKt.parseAuthError(jsonObject));
    }
}
