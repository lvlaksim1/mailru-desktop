package ru.mail.onpremiseauthsdk.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.onpremiseauthsdk.api.models.AuthData;
import ru.mail.onpremiseauthsdk.api.models.AuthError;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0006"}, d2 = {"parseAuthData", "Lru/mail/onpremiseauthsdk/api/models/AuthData;", "Lorg/json/JSONObject;", "parseAuthError", "Lru/mail/onpremiseauthsdk/api/models/AuthError;", "parseSsoAuthData", "onpremiseauthsdk_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class OnPremiseAuthUtilsKt {
    @NotNull
    public static final AuthData parseAuthData(@NotNull JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        String strOptString = jSONObject.optString("access_token");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        String strOptString2 = jSONObject.optString("refresh_token");
        Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
        return new AuthData(strOptString, strOptString2, jSONObject.optInt("expires_in"));
    }

    @NotNull
    public static final AuthError parseAuthError(@NotNull JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        String strOptString = jSONObject.optString("error");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        int iOptInt = jSONObject.optInt("error_code");
        String strOptString2 = jSONObject.optString("error_description");
        Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
        return new AuthError(strOptString, iOptInt, strOptString2);
    }

    @NotNull
    public static final AuthData parseSsoAuthData(@NotNull JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("body");
        if (jSONObjectOptJSONObject == null) {
            jSONObjectOptJSONObject = new JSONObject();
        }
        String strOptString = jSONObjectOptJSONObject.optString("access_token");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        String strOptString2 = jSONObjectOptJSONObject.optString("refresh_token");
        Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
        return new AuthData(strOptString, strOptString2, jSONObjectOptJSONObject.optInt("expires_in"));
    }
}
