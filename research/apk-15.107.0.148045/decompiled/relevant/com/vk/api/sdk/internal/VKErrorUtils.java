package com.vk.api.sdk.internal;

import android.os.Bundle;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.exceptions.VKApiIllegalResponseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nJ!\u0010\b\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0002\b\rJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u0007H\u0002J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0012\u001a\u00020\fH\u0002J&\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0016J&\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00072\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0016J \u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\nJ'\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\nH\u0000¢\u0006\u0002\b\u001dJ\u0014\u0010\u001e\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u0007H\u0002¨\u0006 "}, d2 = {"Lcom/vk/api/sdk/internal/VKErrorUtils;", "", "<init>", "()V", "hasSimpleError", "", "response", "Lorg/json/JSONObject;", "hasExecuteError", "ignoreErrors", "", VKApiCodes.PARAM_ERROR_MULTI, "Lorg/json/JSONArray;", "hasExecuteError$core_release", "executeErrorsSet", "", "", "json", "jsonArray", "parseSimpleError", "Lcom/vk/api/sdk/exceptions/VKApiException;", "errorStr", "", "method", CommonConstant.KEY_ACCESS_TOKEN, "errorJson", "parseExecuteError", "ignoredErrors", "errorsJson", "parseExecuteError$core_release", "putCaptchaData", "Landroid/os/Bundle;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVKErrorUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VKErrorUtils.kt\ncom/vk/api/sdk/internal/VKErrorUtils\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,206:1\n13493#2,2:207\n13493#2,2:209\n*S KotlinDebug\n*F\n+ 1 VKErrorUtils.kt\ncom/vk/api/sdk/internal/VKErrorUtils\n*L\n45#1:207,2\n55#1:209,2\n*E\n"})
public final class VKErrorUtils {

    @NotNull
    public static final VKErrorUtils INSTANCE = new VKErrorUtils();

    private VKErrorUtils() {
    }

    private final Set<Integer> executeErrorsSet(JSONObject json) throws JSONException {
        JSONArray jSONArray = json.getJSONArray(VKApiCodes.PARAM_EXECUTE_ERRORS);
        Intrinsics.checkNotNullExpressionValue(jSONArray, "getJSONArray(...)");
        return executeErrorsSet(jSONArray);
    }

    public static /* synthetic */ VKApiException parseSimpleError$default(VKErrorUtils vKErrorUtils, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        if ((i10 & 4) != 0) {
            str3 = null;
        }
        return vKErrorUtils.parseSimpleError(str, str2, str3);
    }

    private final Bundle putCaptchaData(Bundle bundle, JSONObject jSONObject) {
        bundle.putString(VKApiCodes.EXTRA_CAPTCHA_SID, jSONObject.getString(VKApiCodes.EXTRA_CAPTCHA_SID));
        bundle.putString(VKApiCodes.EXTRA_CAPTCHA_IMG, jSONObject.getString(VKApiCodes.EXTRA_CAPTCHA_IMG));
        bundle.putInt(VKApiCodes.EXTRA_CAPTCHA_IMG_HEIGHT, jSONObject.optInt(VKApiCodes.EXTRA_CAPTCHA_IMG_HEIGHT, -1));
        bundle.putInt(VKApiCodes.EXTRA_CAPTCHA_IMG_WIDTH, jSONObject.optInt(VKApiCodes.EXTRA_CAPTCHA_IMG_WIDTH, -1));
        bundle.putDouble(VKApiCodes.EXTRA_CAPTCHA_RATIO, jSONObject.optDouble(VKApiCodes.EXTRA_CAPTCHA_RATIO));
        if (jSONObject.has(VKApiCodes.EXTRA_CAPTCHA_ATTEMPT)) {
            bundle.putInt(VKApiCodes.EXTRA_CAPTCHA_ATTEMPT, jSONObject.optInt(VKApiCodes.EXTRA_CAPTCHA_ATTEMPT, -1));
        }
        if (jSONObject.has(VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP)) {
            bundle.putDouble(VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP, jSONObject.optDouble(VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP, -1.0d));
        }
        if (jSONObject.has(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED)) {
            bundle.putBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED, jSONObject.optBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED, false));
        }
        if (jSONObject.has(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE)) {
            bundle.putBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, jSONObject.optBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, false));
        }
        if (jSONObject.has(VKApiCodes.EXTRA_CAPTCHA_TRACK)) {
            bundle.putString(VKApiCodes.EXTRA_CAPTCHA_TRACK, jSONObject.optString(VKApiCodes.EXTRA_CAPTCHA_TRACK, ""));
        }
        if (jSONObject.has("redirect_uri")) {
            bundle.putString("redirect_uri", jSONObject.optString("redirect_uri", ""));
        }
        return bundle;
    }

    public final boolean hasExecuteError(@NotNull JSONObject response, @Nullable int[] ignoreErrors) throws JSONException {
        Intrinsics.checkNotNullParameter(response, "response");
        if (!response.has(VKApiCodes.PARAM_EXECUTE_ERRORS)) {
            return false;
        }
        if (ignoreErrors == null) {
            return true;
        }
        Set<Integer> setExecuteErrorsSet = executeErrorsSet(response);
        for (int i10 : ignoreErrors) {
            setExecuteErrorsSet.remove(Integer.valueOf(i10));
        }
        return !setExecuteErrorsSet.isEmpty();
    }

    public final boolean hasExecuteError$core_release(@Nullable JSONArray errors, @Nullable int[] ignoreErrors) {
        if (errors == null) {
            return false;
        }
        if (ignoreErrors == null) {
            return true;
        }
        Set<Integer> setExecuteErrorsSet = executeErrorsSet(errors);
        for (int i10 : ignoreErrors) {
            setExecuteErrorsSet.remove(Integer.valueOf(i10));
        }
        return !setExecuteErrorsSet.isEmpty();
    }

    public final boolean hasSimpleError(@NotNull JSONObject response) {
        Intrinsics.checkNotNullParameter(response, "response");
        return response.has("error");
    }

    @NotNull
    public final VKApiException parseExecuteError(@NotNull JSONObject response, @NotNull String method, @Nullable int[] ignoredErrors) throws JSONException {
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(method, "method");
        JSONArray jSONArray = response.getJSONArray(VKApiCodes.PARAM_EXECUTE_ERRORS);
        Intrinsics.checkNotNullExpressionValue(jSONArray, "getJSONArray(...)");
        return parseExecuteError$core_release(jSONArray, method, ignoredErrors);
    }

    @NotNull
    public final VKApiException parseExecuteError$core_release(@NotNull JSONArray errorsJson, @NotNull String method, @Nullable int[] ignoredErrors) {
        Intrinsics.checkNotNullParameter(errorsJson, "errorsJson");
        Intrinsics.checkNotNullParameter(method, "method");
        try {
            ArrayList arrayList = new ArrayList();
            int length = errorsJson.length();
            for (int i10 = 0; i10 < length; i10++) {
                JSONObject jSONObject = errorsJson.getJSONObject(i10);
                Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                VKApiException simpleError$default = parseSimpleError$default(this, jSONObject, (String) null, (String) null, 6, (Object) null);
                if (!(simpleError$default instanceof VKApiExecutionException)) {
                    return simpleError$default;
                }
                int code = ((VKApiExecutionException) simpleError$default).getCode();
                if (code == 1 || code == 14 || code == 17 || code == 3610 || code == 4 || code == 5 || code == 6 || code == 9 || code == 10 || code == 24 || code == 25) {
                    return simpleError$default;
                }
                if (ignoredErrors == null || !ArraysKt.contains(ignoredErrors, ((VKApiExecutionException) simpleError$default).getCode())) {
                    arrayList.add(simpleError$default);
                }
            }
            return new VKApiExecutionException(Integer.MIN_VALUE, method, false, "", null, arrayList, null, null, 0, null, null, null, 4032, null);
        } catch (JSONException e10) {
            return new VKApiIllegalResponseException(e10);
        }
    }

    @NotNull
    public final VKApiException parseSimpleError(@NotNull String errorStr, @Nullable String method, @Nullable String accessToken) {
        Intrinsics.checkNotNullParameter(errorStr, "errorStr");
        JSONObject jSONObjectOptJSONObject = new JSONObject(errorStr).optJSONObject("error");
        if (jSONObjectOptJSONObject == null) {
            jSONObjectOptJSONObject = new JSONObject(errorStr);
        }
        return parseSimpleError(jSONObjectOptJSONObject, method, accessToken);
    }

    private final Set<Integer> executeErrorsSet(JSONArray jsonArray) {
        HashSet hashSet = new HashSet();
        int length = jsonArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            hashSet.add(Integer.valueOf(jsonArray.getJSONObject(i10).getInt("error_code")));
        }
        return hashSet;
    }

    public static /* synthetic */ VKApiException parseSimpleError$default(VKErrorUtils vKErrorUtils, JSONObject jSONObject, String str, String str2, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        if ((i10 & 4) != 0) {
            str2 = null;
        }
        return vKErrorUtils.parseSimpleError(jSONObject, str, str2);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @NotNull
    public final VKApiException parseSimpleError(@NotNull JSONObject errorJson, @Nullable String method, @Nullable String accessToken) {
        Bundle bundle;
        Intrinsics.checkNotNullParameter(errorJson, "errorJson");
        try {
            int iOptInt = errorJson.optInt("error_code");
            Bundle bundle2 = null;
            bundle2 = null;
            if (iOptInt == 5) {
                JSONObject jSONObjectOptJSONObject = errorJson.optJSONObject(VKApiCodes.PARAM_BAN_INFO);
                if (jSONObjectOptJSONObject != null) {
                    bundle2 = new Bundle();
                    bundle2.putString(VKApiCodes.EXTRA_USER_BAN_INFO, jSONObjectOptJSONObject.toString());
                }
            } else if (iOptInt == 14) {
                bundle2 = putCaptchaData(new Bundle(), errorJson);
            } else if (iOptInt == 17) {
                bundle2 = new Bundle();
                bundle2.putString(VKApiCodes.EXTRA_VALIDATION_URL, errorJson.getString("redirect_uri"));
            } else if (iOptInt != 24) {
                if (iOptInt == 100) {
                    JSONObject jSONObjectOptJSONObject2 = errorJson.optJSONObject(VKApiCodes.EXTRA_ADDITIONAL_INFO);
                    String string = jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.toString() : null;
                    bundle = new Bundle();
                    bundle.putString(VKApiCodes.EXTRA_ADDITIONAL_INFO, string);
                } else if (iOptInt == 3609) {
                    bundle = new Bundle();
                    bundle.putString(VKApiCodes.EXTRA_EXTENSION_HASH, errorJson.optString(VKApiCodes.EXTRA_EXTENSION_HASH, null));
                } else if (iOptInt == 13300) {
                    JSONObject jSONObjectOptJSONObject3 = errorJson.optJSONObject(VKApiCodes.PARAM_BAN_INFO);
                    long jOptLong = jSONObjectOptJSONObject3 != null ? jSONObjectOptJSONObject3.optLong(VKApiCodes.PARAM_OWNER_ID) : 0L;
                    bundle2 = new Bundle();
                    bundle2.putLong(VKApiCodes.PARAM_OWNER_ID, jOptLong);
                }
                bundle2 = bundle;
            } else {
                bundle2 = new Bundle();
                bundle2.putString("confirmation_text", errorJson.getString("confirmation_text"));
            }
            if (accessToken != null) {
                if (bundle2 == null) {
                    bundle2 = new Bundle(1);
                }
                bundle2.putString("access_token", accessToken);
            }
            return VKApiExecutionException.INSTANCE.parse(errorJson, method, bundle2);
        } catch (Exception e10) {
            String string2 = errorJson.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            return new VKApiIllegalResponseException(string2, e10);
        }
    }
}
