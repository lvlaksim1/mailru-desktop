package com.vk.api.sdk.exceptions;

import android.os.Bundle;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import okio.Utf8;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.network.NetworkCommand;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b8\n\u0002\u0010\u0006\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0016\u0018\u0000 v2\u00020\u0001:\u0001vB\u0099\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u0006\u0010l\u001a\u00020\u0007J\u0013\u0010o\u001a\u00020\u00072\b\u0010p\u001a\u0004\u0018\u00010qH\u0096\u0002J\b\u0010r\u001a\u00020\u0003H\u0016J\b\u0010s\u001a\u00020\u0005H\u0016J\u000e\u0010t\u001a\u00020\u00072\u0006\u0010u\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u001f\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u0011\u0010*\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b*\u0010\u001dR\u0011\u0010+\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b+\u0010\u001dR\u0011\u0010,\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b,\u0010\u001dR\u0011\u0010-\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b-\u0010\u001dR\u0011\u0010.\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b.\u0010\u001dR\u0011\u0010/\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b/\u0010\u001dR\u0011\u00100\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b0\u0010\u001dR\u0011\u00101\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b1\u0010\u001dR\u0011\u00102\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b2\u0010\u001dR\u0011\u00103\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b4\u0010\u001dR\u0011\u00105\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b5\u0010\u001dR\u0011\u00106\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b6\u0010\u001dR\u0011\u00107\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b7\u0010\u001dR\u0011\u00108\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b8\u0010\u001dR\u0011\u00109\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b9\u0010\u001dR\u0011\u0010:\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b:\u0010\u001dR\u0011\u0010;\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b;\u0010\u001dR\u0011\u0010<\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b<\u0010\u001dR\u0011\u0010=\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b=\u0010\u001dR\u0011\u0010>\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b>\u0010\u001dR\u0011\u0010?\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b?\u0010\u001dR\u0011\u0010@\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b@\u0010\u001dR\u0011\u0010A\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bA\u0010\u001dR\u0011\u0010B\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bB\u0010\u001dR\u0011\u0010C\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bC\u0010\u001dR\u0011\u0010D\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bD\u0010\u001dR\u0011\u0010E\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\bF\u0010\u001bR\u0011\u0010G\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\bH\u0010\u001bR\u0011\u0010I\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bJ\u0010\u0019R\u0011\u0010K\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\bL\u0010\u0019R\u0011\u0010M\u001a\u00020N8F¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0013\u0010Q\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0013\u0010T\u001a\u0004\u0018\u00010N8F¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0011\u0010W\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bX\u0010\u001dR\u0013\u0010Y\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0011\u0010\\\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b]\u0010\u001bR\u0013\u0010^\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b_\u0010\u001bR\u0011\u0010`\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\ba\u0010\u001bR\u0011\u0010b\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\bc\u0010\u001bR\u0013\u0010d\u001a\u0004\u0018\u00010e8F¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0011\u0010h\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\bi\u0010\u001bR\u0013\u0010j\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\bk\u0010\u001bR\u0013\u0010m\u001a\u0004\u0018\u00010\n8F¢\u0006\u0006\u001a\u0004\bn\u0010 ¨\u0006w"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "Lcom/vk/api/sdk/exceptions/VKApiException;", "code", "", "apiMethod", "", "hasLocalizedMessage", "", "detailMessage", "extra", "Landroid/os/Bundle;", "executeErrors", "", "errorMsg", "requestParams", "", "subcode", "viewType", "Lcom/vk/api/sdk/exceptions/ApiErrorViewType;", "responseContentType", "cause", "", "<init>", "(ILjava/lang/String;ZLjava/lang/String;Landroid/os/Bundle;Ljava/util/List;Ljava/lang/String;Ljava/util/Map;ILcom/vk/api/sdk/exceptions/ApiErrorViewType;Ljava/lang/String;Ljava/lang/Throwable;)V", "getCode", "()I", "getApiMethod", "()Ljava/lang/String;", "getHasLocalizedMessage", "()Z", "getDetailMessage", "getExtra", "()Landroid/os/Bundle;", "getExecuteErrors", "()Ljava/util/List;", "getErrorMsg", "getRequestParams", "()Ljava/util/Map;", "getSubcode", "getViewType", "()Lcom/vk/api/sdk/exceptions/ApiErrorViewType;", "getResponseContentType", "isAnonymTokenInvalid", "isAnonymTokenExpired", "isCompositeError", "isMultiRequestError", "isChatAccessDenied", "isChatDoesNotExistError", "isNotImplementedError", "isAccessError", "isInternalServerError", "shouldSkipRequestRetryPolicy", "getShouldSkipRequestRetryPolicy", "isTooManyRequestsError", "isInvalidCredentialsError", "isAppUpdateNeeded", "isCurrentVersionDeprecated", "isUserConfirmRequired", "isTokenConfirmationRequired", "isValidationRequired", "isCaptchaError", "isPhoneBannedSubCode", "isUserInvalid", "isPasswordConfirmRequired", "isRateLimitReachedError", "isSectionTemporaryUnavailableError", "isAccessTokenExpired", "isNotFound", "isGeoblock", "captchaSid", "getCaptchaSid", "captchaImg", "getCaptchaImg", "captchaHeight", "getCaptchaHeight", "captchaWidth", "getCaptchaWidth", "captchaRatio", "", "getCaptchaRatio", "()D", "captchaAttempt", "getCaptchaAttempt", "()Ljava/lang/Integer;", "captchaTimestamp", "getCaptchaTimestamp", "()Ljava/lang/Double;", "captchaIsRefreshEnabled", "getCaptchaIsRefreshEnabled", "captchaIsSoundCaptchaAvailable", "getCaptchaIsSoundCaptchaAvailable", "()Ljava/lang/Boolean;", "captchaTrack", "getCaptchaTrack", "captchaRedirectUri", "getCaptchaRedirectUri", "validationUrl", "getValidationUrl", "userConfirmText", "getUserConfirmText", "userBanInfo", "Lorg/json/JSONObject;", "getUserBanInfo", "()Lorg/json/JSONObject;", "extensionHash", "getExtensionHash", CommonConstant.KEY_ACCESS_TOKEN, "getAccessToken", "hasExtra", "printableExtra", "getPrintableExtra", "equals", "other", "", "hashCode", "toString", "hasError", "errorCode", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVKApiExecutionException.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VKApiExecutionException.kt\ncom/vk/api/sdk/exceptions/VKApiExecutionException\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,313:1\n1#2:314\n*E\n"})
public class VKApiExecutionException extends VKApiException {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String ERROR_VIEW_TYPE_KEY = "view";
    public static final long serialVersionUID = 7524047853274172872L;

    @NotNull
    private final String apiMethod;
    private final int code;

    @NotNull
    private final String detailMessage;

    @Nullable
    private final String errorMsg;

    @Nullable
    private final List<VKApiExecutionException> executeErrors;

    @Nullable
    private final Bundle extra;
    private final boolean hasLocalizedMessage;

    @Nullable
    private final Map<String, String> requestParams;

    @Nullable
    private final String responseContentType;
    private final int subcode;

    @Nullable
    private final ApiErrorViewType viewType;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes3.dex */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J(\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/vk/api/sdk/exceptions/VKApiExecutionException$Companion;", "", "<init>", "()V", "serialVersionUID", "", "ERROR_VIEW_TYPE_KEY", "", "parse", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "json", "Lorg/json/JSONObject;", "methodName", "extra", "Landroid/os/Bundle;", "defineDetailMessage", "code", "", "subcode", "method", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nVKApiExecutionException.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VKApiExecutionException.kt\ncom/vk/api/sdk/exceptions/VKApiExecutionException$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,313:1\n1193#2,2:314\n1267#2,4:316\n*S KotlinDebug\n*F\n+ 1 VKApiExecutionException.kt\ncom/vk/api/sdk/exceptions/VKApiExecutionException$Companion\n*L\n275#1:314,2\n275#1:316,4\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final String defineDetailMessage(JSONObject json, int code, int subcode, String method) {
            if (json.has("error_text")) {
                String strOptString = json.optString("error_text");
                return strOptString == null ? "" : strOptString;
            }
            if (json.has("error_description")) {
                String strOptString2 = json.optString("error_description");
                return strOptString2 == null ? "" : strOptString2;
            }
            if (json.has("error_descr")) {
                String strOptString3 = json.optString("error_descr");
                return strOptString3 == null ? "" : strOptString3;
            }
            return (json.has("error_msg") ? json.optString("error_msg") : json.toString()) + " (" + code + ":" + subcode + ") | by [" + method + "]";
        }

        public static /* synthetic */ VKApiExecutionException parse$default(Companion companion, JSONObject jSONObject, String str, Bundle bundle, int i10, Object obj) {
            if ((i10 & 2) != 0) {
                str = null;
            }
            if ((i10 & 4) != 0) {
                bundle = null;
            }
            return companion.parse(jSONObject, str, bundle);
        }

        @JvmOverloads
        @NotNull
        public final VKApiExecutionException parse(@NotNull JSONObject json) {
            Intrinsics.checkNotNullParameter(json, "json");
            return parse$default(this, json, null, null, 6, null);
        }

        private Companion() {
        }

        @JvmOverloads
        @NotNull
        public final VKApiExecutionException parse(@NotNull JSONObject json, @Nullable String str) {
            Intrinsics.checkNotNullParameter(json, "json");
            return parse$default(this, json, str, null, 4, null);
        }

        @JvmOverloads
        @NotNull
        public final VKApiExecutionException parse(@NotNull JSONObject json, @Nullable String methodName, @Nullable Bundle extra) throws JSONException {
            String str;
            JSONArray jSONArray;
            Intrinsics.checkNotNullParameter(json, "json");
            if (methodName == null) {
                String strOptString = json.optString("method");
                str = strOptString == null ? "" : strOptString;
            } else {
                str = methodName;
            }
            int iOptInt = json.optInt("error_code", 1);
            int iOptInt2 = json.optInt("error_subcode", 1);
            String strOptString2 = json.optString("error_msg");
            String str2 = strOptString2 == null ? "" : strOptString2;
            try {
                jSONArray = json.getJSONArray("request_params");
            } catch (JSONException unused) {
                jSONArray = new JSONArray();
            }
            IntRange intRangeUntil = RangesKt.until(0, jSONArray.length());
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10)), 16));
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                JSONObject jSONObject = jSONArray.getJSONObject(((IntIterator) it).nextInt());
                Pair pair = TuplesKt.to(jSONObject.getString("key"), jSONObject.getString("value"));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            ApiErrorViewType.Companion companion = ApiErrorViewType.INSTANCE;
            String strOptString3 = json.optString("view");
            return new VKApiExecutionException(iOptInt, str, json.has("error_text"), defineDetailMessage(json, iOptInt, iOptInt2, str), extra, null, str2, linkedHashMap, iOptInt2, companion.fromString(strOptString3 != null ? strOptString3 : ""), null, null, 3104, null);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VKApiExecutionException(int i10, @NotNull String apiMethod, boolean z10, @NotNull String detailMessage) {
        this(i10, apiMethod, z10, detailMessage, null, null, null, null, 0, null, null, null, 4080, null);
        Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VKApiExecutionException)) {
            return false;
        }
        VKApiExecutionException vKApiExecutionException = (VKApiExecutionException) other;
        if (this.code != vKApiExecutionException.code) {
            return false;
        }
        Bundle bundle = this.extra;
        Bundle bundle2 = vKApiExecutionException.extra;
        return bundle == null ? bundle2 == null : Intrinsics.areEqual(bundle, bundle2);
    }

    @Nullable
    public final String getAccessToken() {
        Bundle bundle = this.extra;
        if (bundle != null) {
            return bundle.getString("access_token", null);
        }
        return null;
    }

    @NotNull
    public final String getApiMethod() {
        return this.apiMethod;
    }

    @Nullable
    public final Integer getCaptchaAttempt() {
        Bundle bundle = this.extra;
        if (bundle == null || !bundle.containsKey(VKApiCodes.EXTRA_CAPTCHA_ATTEMPT)) {
            return null;
        }
        return Integer.valueOf(this.extra.getInt(VKApiCodes.EXTRA_CAPTCHA_ATTEMPT, -1));
    }

    public final int getCaptchaHeight() {
        Bundle bundle = this.extra;
        if (bundle != null) {
            return bundle.getInt(VKApiCodes.EXTRA_CAPTCHA_IMG_HEIGHT, -1);
        }
        return -1;
    }

    @NotNull
    public final String getCaptchaImg() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_CAPTCHA_IMG, "")) == null) ? "" : string;
    }

    public final boolean getCaptchaIsRefreshEnabled() {
        Bundle bundle = this.extra;
        if (bundle == null || !bundle.containsKey(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED)) {
            return false;
        }
        return this.extra.getBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED, false);
    }

    @Nullable
    public final Boolean getCaptchaIsSoundCaptchaAvailable() {
        Bundle bundle = this.extra;
        boolean z10 = false;
        if (bundle != null && bundle.containsKey(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE)) {
            z10 = this.extra.getBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, false);
        }
        return Boolean.valueOf(z10);
    }

    public final double getCaptchaRatio() {
        Bundle bundle = this.extra;
        if (bundle != null) {
            return bundle.getDouble(VKApiCodes.EXTRA_CAPTCHA_RATIO, -1.0d);
        }
        return -1.0d;
    }

    @Nullable
    public final String getCaptchaRedirectUri() {
        Bundle bundle = this.extra;
        if (bundle != null) {
            return bundle.getString("redirect_uri", null);
        }
        return null;
    }

    @NotNull
    public final String getCaptchaSid() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_CAPTCHA_SID, "")) == null) ? "" : string;
    }

    @Nullable
    public final Double getCaptchaTimestamp() {
        Bundle bundle = this.extra;
        if (bundle == null || !bundle.containsKey(VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP)) {
            return null;
        }
        return Double.valueOf(this.extra.getDouble(VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP, -1.0d));
    }

    @NotNull
    public final String getCaptchaTrack() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_CAPTCHA_TRACK, "")) == null) ? "" : string;
    }

    public final int getCaptchaWidth() {
        Bundle bundle = this.extra;
        if (bundle != null) {
            return bundle.getInt(VKApiCodes.EXTRA_CAPTCHA_IMG_WIDTH, -1);
        }
        return -1;
    }

    public final int getCode() {
        return this.code;
    }

    @NotNull
    public final String getDetailMessage() {
        return this.detailMessage;
    }

    @Nullable
    public final String getErrorMsg() {
        return this.errorMsg;
    }

    @Nullable
    public final List<VKApiExecutionException> getExecuteErrors() {
        return this.executeErrors;
    }

    @NotNull
    public final String getExtensionHash() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_EXTENSION_HASH, null)) == null) ? "" : string;
    }

    @Nullable
    public final Bundle getExtra() {
        return this.extra;
    }

    public final boolean getHasLocalizedMessage() {
        return this.hasLocalizedMessage;
    }

    @Nullable
    public final Bundle getPrintableExtra() {
        Bundle bundle = this.extra;
        if (bundle == null || !bundle.containsKey("access_token")) {
            return this.extra;
        }
        Bundle bundle2 = new Bundle(this.extra);
        bundle2.putString("access_token", "hidden");
        return bundle2;
    }

    @Nullable
    public final Map<String, String> getRequestParams() {
        return this.requestParams;
    }

    @Nullable
    public final String getResponseContentType() {
        return this.responseContentType;
    }

    public final boolean getShouldSkipRequestRetryPolicy() {
        return this.code == 10 && this.subcode == 1152;
    }

    public final int getSubcode() {
        return this.subcode;
    }

    @Nullable
    public final JSONObject getUserBanInfo() {
        String string;
        Bundle bundle = this.extra;
        if (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_USER_BAN_INFO)) == null) {
            return null;
        }
        return new JSONObject(string);
    }

    @NotNull
    public final String getUserConfirmText() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString("confirmation_text", "")) == null) ? "" : string;
    }

    @NotNull
    public final String getValidationUrl() {
        String string;
        Bundle bundle = this.extra;
        return (bundle == null || (string = bundle.getString(VKApiCodes.EXTRA_VALIDATION_URL, "")) == null) ? "" : string;
    }

    @Nullable
    public final ApiErrorViewType getViewType() {
        return this.viewType;
    }

    public final boolean hasError(int errorCode) {
        if (this.code == errorCode) {
            return true;
        }
        List<VKApiExecutionException> list = this.executeErrors;
        Object obj = null;
        if (list != null) {
            for (Object obj2 : list) {
                if (((VKApiExecutionException) obj2).code == errorCode) {
                    obj = obj2;
                    break;
                }
            }
            obj = (VKApiExecutionException) obj;
        }
        return obj != null;
    }

    public final boolean hasExtra() {
        Bundle bundle = this.extra;
        return (bundle == null || Intrinsics.areEqual(bundle, Bundle.EMPTY)) ? false : true;
    }

    public int hashCode() {
        int i10 = this.code * 31;
        Bundle bundle = this.extra;
        return i10 + (bundle != null ? bundle.hashCode() : 0);
    }

    public final boolean isAccessError() {
        int i10 = this.code;
        return i10 == 15 || i10 == 30 || i10 == 203 || i10 == 1016 || i10 == 200 || i10 == 201;
    }

    public final boolean isAccessTokenExpired() {
        return this.code == 1117;
    }

    public final boolean isAnonymTokenExpired() {
        return this.code == 1114;
    }

    public final boolean isAnonymTokenInvalid() {
        return this.code == 1116;
    }

    public final boolean isAppUpdateNeeded() {
        return this.code == 35;
    }

    public final boolean isCaptchaError() {
        return this.code == 14;
    }

    public final boolean isChatAccessDenied() {
        return this.code == 917;
    }

    public final boolean isChatDoesNotExistError() {
        return this.code == 927;
    }

    public final boolean isCompositeError() {
        return this.code == Integer.MIN_VALUE;
    }

    public final boolean isCurrentVersionDeprecated() {
        return this.code == 34;
    }

    public final boolean isGeoblock() {
        return this.code == 13300;
    }

    public final boolean isInternalServerError() {
        int i10 = this.code;
        return i10 == 1 || i10 == 10 || i10 == 13;
    }

    public final boolean isInvalidCredentialsError() {
        int i10 = this.code;
        return i10 == 4 || i10 == 5 || i10 == 3610;
    }

    public final boolean isMultiRequestError() {
        return this.code == -2147483647;
    }

    public final boolean isNotFound() {
        return this.code == 104;
    }

    public final boolean isNotImplementedError() {
        return this.code == 33;
    }

    public final boolean isPasswordConfirmRequired() {
        return this.code == 3609;
    }

    public final boolean isPhoneBannedSubCode() {
        int i10 = this.subcode;
        return i10 == 1112 || i10 == 1113;
    }

    public final boolean isRateLimitReachedError() {
        return this.code == 29;
    }

    public final boolean isSectionTemporaryUnavailableError() {
        return this.code == 43;
    }

    public final boolean isTokenConfirmationRequired() {
        return this.code == 25;
    }

    public final boolean isTooManyRequestsError() {
        return this.code == 6;
    }

    public final boolean isUserConfirmRequired() {
        return this.code == 24;
    }

    public final boolean isUserInvalid() {
        return this.code == 31;
    }

    public final boolean isValidationRequired() {
        return this.code == 17;
    }

    @Override // java.lang.Throwable
    @NotNull
    public String toString() {
        int i10 = this.code;
        Bundle printableExtra = getPrintableExtra();
        String str = this.apiMethod;
        List<VKApiExecutionException> list = this.executeErrors;
        return "VKApiExecutionException{code=" + i10 + ", extra=" + printableExtra + ", method=" + str + ", executeErrors=" + (list != null ? CollectionsKt.joinToString$default(list, null, "[", "]", 0, null, null, 57, null) : null) + ", super=" + super.toString() + NetworkCommand.URL_PATH_PARAM_SUFFIX;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VKApiExecutionException(int i10, @NotNull String apiMethod, boolean z10, @NotNull String detailMessage, @Nullable Bundle bundle) {
        this(i10, apiMethod, z10, detailMessage, bundle, null, null, null, 0, null, null, null, 4064, null);
        Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VKApiExecutionException(int i10, @NotNull String apiMethod, boolean z10, @NotNull String detailMessage, @Nullable Bundle bundle, @Nullable List<? extends VKApiExecutionException> list) {
        this(i10, apiMethod, z10, detailMessage, bundle, list, null, null, 0, null, null, null, 4032, null);
        Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VKApiExecutionException(int i10, @NotNull String apiMethod, boolean z10, @NotNull String detailMessage, @Nullable Bundle bundle, @Nullable List<? extends VKApiExecutionException> list, @Nullable String str) {
        this(i10, apiMethod, z10, detailMessage, bundle, list, str, null, 0, null, null, null, Utf8.MASK_2BYTES, null);
        Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VKApiExecutionException(int i10, @NotNull String apiMethod, boolean z10, @NotNull String detailMessage, @Nullable Bundle bundle, @Nullable List<? extends VKApiExecutionException> list, @Nullable String str, @Nullable Map<String, String> map) {
        this(i10, apiMethod, z10, detailMessage, bundle, list, str, map, 0, null, null, null, 3840, null);
        Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VKApiExecutionException(int i10, @NotNull String apiMethod, boolean z10, @NotNull String detailMessage, @Nullable Bundle bundle, @Nullable List<? extends VKApiExecutionException> list, @Nullable String str, @Nullable Map<String, String> map, int i11) {
        this(i10, apiMethod, z10, detailMessage, bundle, list, str, map, i11, null, null, null, 3584, null);
        Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VKApiExecutionException(int i10, @NotNull String apiMethod, boolean z10, @NotNull String detailMessage, @Nullable Bundle bundle, @Nullable List<? extends VKApiExecutionException> list, @Nullable String str, @Nullable Map<String, String> map, int i11, @Nullable ApiErrorViewType apiErrorViewType) {
        this(i10, apiMethod, z10, detailMessage, bundle, list, str, map, i11, apiErrorViewType, null, null, 3072, null);
        Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VKApiExecutionException(int i10, @NotNull String apiMethod, boolean z10, @NotNull String detailMessage, @Nullable Bundle bundle, @Nullable List<? extends VKApiExecutionException> list, @Nullable String str, @Nullable Map<String, String> map, int i11, @Nullable ApiErrorViewType apiErrorViewType, @Nullable String str2) {
        this(i10, apiMethod, z10, detailMessage, bundle, list, str, map, i11, apiErrorViewType, str2, null, 2048, null);
        Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
    }

    public /* synthetic */ VKApiExecutionException(int i10, String str, boolean z10, String str2, Bundle bundle, List list, String str3, Map map, int i11, ApiErrorViewType apiErrorViewType, String str4, Throwable th2, int i12, DefaultConstructorMarker defaultConstructorMarker) {
        this(i10, str, z10, str2, (i12 & 16) != 0 ? Bundle.EMPTY : bundle, (i12 & 32) != 0 ? null : list, (i12 & 64) != 0 ? null : str3, (i12 & 128) != 0 ? null : map, (i12 & 256) != 0 ? -1 : i11, (i12 & 512) != 0 ? null : apiErrorViewType, (i12 & 1024) != 0 ? null : str4, (i12 & 2048) != 0 ? null : th2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public VKApiExecutionException(int i10, @NotNull String apiMethod, boolean z10, @NotNull String detailMessage, @Nullable Bundle bundle, @Nullable List<? extends VKApiExecutionException> list, @Nullable String str, @Nullable Map<String, String> map, int i11, @Nullable ApiErrorViewType apiErrorViewType, @Nullable String str2, @Nullable Throwable th2) {
        super(detailMessage, th2 == null ? new VKApiException(String.valueOf(list)) : th2);
        Intrinsics.checkNotNullParameter(apiMethod, "apiMethod");
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
        this.code = i10;
        this.apiMethod = apiMethod;
        this.hasLocalizedMessage = z10;
        this.detailMessage = detailMessage;
        this.extra = bundle;
        this.executeErrors = list;
        this.errorMsg = str;
        this.requestParams = map;
        this.subcode = i11;
        this.viewType = apiErrorViewType;
        this.responseContentType = str2;
    }
}
