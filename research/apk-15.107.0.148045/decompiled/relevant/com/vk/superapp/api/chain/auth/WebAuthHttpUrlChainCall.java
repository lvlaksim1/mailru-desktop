package com.vk.superapp.api.chain.auth;

import android.net.Uri;
import android.os.Bundle;
import com.vk.api.external.AnonymousOkHttpExecutor;
import com.vk.api.external.AnonymousOkHttpExecutorKt;
import com.vk.api.external.call.CustomHeader;
import com.vk.api.external.call.HttpUrlPostCall;
import com.vk.api.external.exceptions.VKWebAuthException;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.chain.ChainArgs;
import com.vk.api.sdk.chain.ChainCall;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.internal.VKErrorUtils;
import com.vk.api.sdk.response.ResponseBodyJsonConverter;
import com.vk.api.sdk.utils.HitmanChallengeKt;
import com.vk.core.extensions.JsonObjectExtKt;
import com.vk.superapp.core.api.models.WebAuthAnswer;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u0006\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0006\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/vk/superapp/api/chain/auth/WebAuthHttpUrlChainCall;", "Lcom/vk/api/sdk/chain/ChainCall;", "Lcom/vk/superapp/core/api/models/WebAuthAnswer;", "Lcom/vk/api/sdk/VKApiManager;", "manager", "Lcom/vk/api/external/call/HttpUrlPostCall;", "call", "", "accessTokenParameterName", "<init>", "(Lcom/vk/api/sdk/VKApiManager;Lcom/vk/api/external/call/HttpUrlPostCall;Ljava/lang/String;)V", "Lcom/vk/api/sdk/chain/ChainArgs;", "args", "(Lcom/vk/api/sdk/chain/ChainArgs;)Lcom/vk/superapp/core/api/models/WebAuthAnswer;", "Companion", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWebAuthHttpUrlChainCall.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebAuthHttpUrlChainCall.kt\ncom/vk/superapp/api/chain/auth/WebAuthHttpUrlChainCall\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,256:1\n29#2:257\n29#2:260\n1869#3,2:258\n*S KotlinDebug\n*F\n+ 1 WebAuthHttpUrlChainCall.kt\ncom/vk/superapp/api/chain/auth/WebAuthHttpUrlChainCall\n*L\n70#1:257\n128#1:260\n98#1:258,2\n*E\n"})
public final class WebAuthHttpUrlChainCall extends ChainCall<WebAuthAnswer> {

    @NotNull
    public static final String ERROR_NEED_CAPTCHA = "need_captcha";

    @NotNull
    private final HttpUrlPostCall ipakvmoca;

    @NotNull
    private final String ipakvmocb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebAuthHttpUrlChainCall(@NotNull VKApiManager manager, @NotNull HttpUrlPostCall call, @NotNull String accessTokenParameterName) {
        super(manager);
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(accessTokenParameterName, "accessTokenParameterName");
        this.ipakvmoca = call;
        this.ipakvmocb = accessTokenParameterName;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Code duplicated, block: B:8:0x0034  */
    @Override // com.vk.api.sdk.chain.ChainCall
    @NotNull
    public WebAuthAnswer call(@NotNull ChainArgs args) throws VKWebAuthException, JSONException, InterruptedException, VKApiException, IOException {
        HttpUrlPostCall httpUrlPostCallCopy$default;
        WebAuthAnswer webAuthAnswer;
        Intrinsics.checkNotNullParameter(args, "args");
        String hitmanChallengeToken = args.getHitmanChallengeToken();
        if (hitmanChallengeToken != null) {
            HttpUrlPostCall httpUrlPostCall = this.ipakvmoca;
            httpUrlPostCallCopy$default = HttpUrlPostCall.copy$default(httpUrlPostCall, null, 0L, 0, 0, null, CollectionsKt.plus((Collection<? extends CustomHeader>) httpUrlPostCall.getCustomHeaders(), new CustomHeader("X-Challenge-Solution", hitmanChallengeToken)), 31, null);
            if (httpUrlPostCallCopy$default == null) {
                httpUrlPostCallCopy$default = this.ipakvmoca;
            }
        } else {
            httpUrlPostCallCopy$default = this.ipakvmoca;
        }
        HttpUrlPostCall httpUrlPostCallCopy$default2 = httpUrlPostCallCopy$default;
        if (args.getCaptchaSuccessToken().length() > 0) {
            String string = Uri.parse(httpUrlPostCallCopy$default2.getUrl()).buildUpon().appendQueryParameter(VKApiCodes.EXTRA_CAPTCHA_SUCCESS_TOKEN, args.getCaptchaSuccessToken()).build().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            httpUrlPostCallCopy$default2 = HttpUrlPostCall.copy$default(httpUrlPostCallCopy$default2, string, 0L, 0, 0, null, null, 62, null);
        }
        AnonymousOkHttpExecutor.SuperMethodResponse superMethodResponseExecute = AnonymousOkHttpExecutorKt.execute(getManager().getExecutor(), httpUrlPostCallCopy$default2, args);
        HitmanChallengeKt.tryInsertHitmanChallengeParamsFrom(args, superMethodResponseExecute.getHeaders(), HitmanChallengeKt.tryGetDomain(httpUrlPostCallCopy$default2.getUrl()));
        String lastRequestUrl = superMethodResponseExecute.getLastRequestUrl();
        int code = superMethodResponseExecute.getCode();
        if (Intrinsics.areEqual("/blank.html", Uri.parse(lastRequestUrl).getPath())) {
            Uri uri = Uri.parse(StringsKt.replace$default(lastRequestUrl, '#', '?', false, 4, (Object) null));
            String queryParameter = uri.getQueryParameter(this.ipakvmocb);
            Set<String> queryParameterNames = uri.getQueryParameterNames();
            HashMap map = new HashMap(queryParameterNames.size());
            Intrinsics.checkNotNull(queryParameterNames);
            for (String str : queryParameterNames) {
                String queryParameter2 = uri.getQueryParameter(str);
                if (queryParameter2 != null) {
                    map.put(str, queryParameter2);
                }
            }
            if (queryParameter != null) {
                return new WebAuthAnswer(queryParameter, map);
            }
            throw new VKWebAuthException(code, uri.getQueryParameter("error"), uri.getQueryParameter("error_description"), uri.getQueryParameter("error_reason"), null, null, 48, null);
        }
        JSONObject responseBodyJson = superMethodResponseExecute.getResponseBodyJson();
        String str2 = "";
        if (responseBodyJson != null) {
            VKErrorUtils vKErrorUtils = VKErrorUtils.INSTANCE;
            if (vKErrorUtils.hasSimpleError(responseBodyJson) && (responseBodyJson.get("error") instanceof JSONObject) && responseBodyJson.getJSONObject("error").has("error_code")) {
                JSONObject jSONObject = responseBodyJson.getJSONObject("error");
                Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                String path = Uri.parse(this.ipakvmoca.getUrl()).getPath();
                throw VKErrorUtils.parseSimpleError$default(vKErrorUtils, jSONObject, path == null ? "" : path, (String) null, 4, (Object) null);
            }
        }
        int code2 = superMethodResponseExecute.getCode();
        boolean z10 = 200 <= code2 && code2 < 300;
        JSONObject responseBodyJson2 = (code2 == 401 || z10) ? superMethodResponseExecute.getResponseBodyJson() : null;
        if (z10) {
            if (responseBodyJson2 == null) {
                webAuthAnswer = null;
            } else {
                try {
                    JSONObject jSONObject2 = responseBodyJson2.has(ResponseBodyJsonConverter.FALLBACK_RESPONSE_KEY) ? new JSONObject(responseBodyJson2.getString(ResponseBodyJsonConverter.FALLBACK_RESPONSE_KEY)) : responseBodyJson2;
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    Iterator<String> itKeys = jSONObject2.keys();
                    Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        linkedHashMap.put(next, jSONObject2.getString(next));
                    }
                    String str3 = (String) linkedHashMap.get(this.ipakvmocb);
                    if (str3 != null) {
                        str2 = str3;
                    }
                    webAuthAnswer = new WebAuthAnswer(str2, linkedHashMap);
                } catch (Exception unused) {
                    webAuthAnswer = null;
                }
            }
            if (webAuthAnswer != null) {
                return webAuthAnswer;
            }
        }
        if (responseBodyJson2 == null) {
            throw new VKWebAuthException(code2, null, null, null, null, null, 62, null);
        }
        String strOptString = responseBodyJson2.optString("error", null);
        String strOptString2 = responseBodyJson2.optString("error_description", null);
        String strOptString3 = responseBodyJson2.optString("error_reason", null);
        if (strOptString3 == null) {
            strOptString3 = responseBodyJson2.optString("error_type");
        }
        JSONObject jSONObject3 = responseBodyJson2;
        VKWebAuthException vKWebAuthException = new VKWebAuthException(code2, strOptString, strOptString2, strOptString3, responseBodyJson2.optJSONObject(XmailMigrationPromoSheet.BUTTON_INFO), jSONObject3);
        if (!vKWebAuthException.isNeedCaptchaError()) {
            if (!vKWebAuthException.isNeedConfirmPasswordError()) {
                throw vKWebAuthException;
            }
            String strOptString4 = jSONObject3.optString(VKApiCodes.EXTRA_EXTENSION_HASH);
            Intrinsics.checkNotNull(strOptString4);
            if (strOptString4.length() == 0) {
                throw vKWebAuthException;
            }
            String url = this.ipakvmoca.getUrl();
            Bundle bundle = new Bundle();
            bundle.putString(VKApiCodes.EXTRA_EXTENSION_HASH, strOptString4);
            Unit unit = Unit.INSTANCE;
            throw new VKApiExecutionException(VKApiCodes.CODE_ERROR_NEED_TOKEN_EXTENSION, url, false, "Token extension required", bundle, null, null, null, 0, null, null, null, 4064, null);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(VKApiCodes.EXTRA_CAPTCHA_SID, jSONObject3.getString(VKApiCodes.EXTRA_CAPTCHA_SID));
        bundle2.putString(VKApiCodes.EXTRA_CAPTCHA_IMG, jSONObject3.getString(VKApiCodes.EXTRA_CAPTCHA_IMG));
        Integer intOrNull = JsonObjectExtKt.getIntOrNull(jSONObject3, VKApiCodes.EXTRA_CAPTCHA_ATTEMPT);
        if (intOrNull != null) {
            bundle2.putInt(VKApiCodes.EXTRA_CAPTCHA_ATTEMPT, intOrNull.intValue());
        }
        Double doubleOrNull = JsonObjectExtKt.getDoubleOrNull(jSONObject3, VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP);
        if (doubleOrNull != null) {
            bundle2.putDouble(VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP, doubleOrNull.doubleValue());
        }
        Double doubleOrNull2 = JsonObjectExtKt.getDoubleOrNull(jSONObject3, VKApiCodes.EXTRA_CAPTCHA_RATIO);
        if (doubleOrNull2 != null) {
            bundle2.putDouble(VKApiCodes.EXTRA_CAPTCHA_RATIO, doubleOrNull2.doubleValue());
        }
        Boolean booleanOrNull = JsonObjectExtKt.getBooleanOrNull(jSONObject3, VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED);
        if (booleanOrNull != null) {
            bundle2.putBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED, booleanOrNull.booleanValue());
        }
        Integer intOrNull2 = JsonObjectExtKt.getIntOrNull(jSONObject3, VKApiCodes.EXTRA_CAPTCHA_IMG_HEIGHT);
        if (intOrNull2 != null) {
            bundle2.putInt(VKApiCodes.EXTRA_CAPTCHA_IMG_HEIGHT, intOrNull2.intValue());
        }
        Integer intOrNull3 = JsonObjectExtKt.getIntOrNull(jSONObject3, VKApiCodes.EXTRA_CAPTCHA_IMG_WIDTH);
        if (intOrNull3 != null) {
            bundle2.putInt(VKApiCodes.EXTRA_CAPTCHA_IMG_WIDTH, intOrNull3.intValue());
        }
        Boolean booleanOrNull2 = JsonObjectExtKt.getBooleanOrNull(jSONObject3, VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE);
        if (booleanOrNull2 != null) {
            bundle2.putBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, booleanOrNull2.booleanValue());
        }
        String stringOrNull = JsonObjectExtKt.getStringOrNull(jSONObject3, VKApiCodes.EXTRA_CAPTCHA_TRACK);
        if (stringOrNull != null) {
            bundle2.putString(VKApiCodes.EXTRA_CAPTCHA_TRACK, stringOrNull);
        }
        String stringOrNull2 = JsonObjectExtKt.getStringOrNull(jSONObject3, "redirect_uri");
        if (stringOrNull2 != null) {
            bundle2.putString("redirect_uri", stringOrNull2);
        }
        throw new VKApiExecutionException(14, this.ipakvmoca.getUrl(), false, "need_captcha", bundle2, null, null, null, 0, null, null, null, 4064, null);
    }
}
