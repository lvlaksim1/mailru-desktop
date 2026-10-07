package com.vk.superapp.api.chain.auth;

import android.os.Bundle;
import android.os.SystemClock;
import com.vk.api.external.AnonymousOkHttpExecutor;
import com.vk.api.external.AnonymousOkHttpExecutorKt;
import com.vk.api.external.call.HttpUrlPostCall;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.chain.ChainArgs;
import com.vk.api.sdk.chain.ChainCall;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.okhttp.OkHttpExecutor;
import com.vk.core.extensions.JsonObjectExtKt;
import com.vk.superapp.core.api.models.AuthAnswer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\b\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/vk/superapp/api/chain/auth/OAuthHttpUrlChainCall;", "Lcom/vk/api/sdk/chain/ChainCall;", "Lcom/vk/superapp/core/api/models/AuthAnswer;", "Lcom/vk/api/sdk/VKApiManager;", "manager", "Lcom/vk/api/sdk/okhttp/OkHttpExecutor;", "okHttpExecutor", "Lcom/vk/api/external/call/HttpUrlPostCall;", "call", "<init>", "(Lcom/vk/api/sdk/VKApiManager;Lcom/vk/api/sdk/okhttp/OkHttpExecutor;Lcom/vk/api/external/call/HttpUrlPostCall;)V", "Lcom/vk/api/sdk/chain/ChainArgs;", "args", "(Lcom/vk/api/sdk/chain/ChainArgs;)Lcom/vk/superapp/core/api/models/AuthAnswer;", "Companion", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class OAuthHttpUrlChainCall extends ChainCall<AuthAnswer> {
    private static final long ipakvmocc = TimeUnit.SECONDS.toMillis(10);

    @NotNull
    private final OkHttpExecutor ipakvmoca;

    @NotNull
    private final HttpUrlPostCall ipakvmocb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OAuthHttpUrlChainCall(@NotNull VKApiManager manager, @NotNull OkHttpExecutor okHttpExecutor, @NotNull HttpUrlPostCall call) {
        super(manager);
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(okHttpExecutor, "okHttpExecutor");
        Intrinsics.checkNotNullParameter(call, "call");
        this.ipakvmoca = okHttpExecutor;
        this.ipakvmocb = call;
    }

    private final AuthAnswer ipakvmoca(ChainArgs chainArgs, long j10) throws Exception {
        if (j10 + (this.ipakvmocb.getTimeoutMs() > 0 ? this.ipakvmocb.getTimeoutMs() : ipakvmocc) < System.currentTimeMillis()) {
            throw new IOException();
        }
        AnonymousOkHttpExecutor.SuperMethodResponse superMethodResponseExecute = AnonymousOkHttpExecutorKt.execute(this.ipakvmoca, this.ipakvmocb, chainArgs);
        JSONObject responseBodyJson = superMethodResponseExecute.getResponseBodyJson();
        if (responseBodyJson == null) {
            throw new VKApiException("Response returned null instead of valid string response");
        }
        String strOptString = responseBodyJson.optString("error", null);
        boolean zHas = responseBodyJson.has("processing");
        if (!Intrinsics.areEqual(strOptString, "need_captcha")) {
            if (zHas) {
                SystemClock.sleep(Math.max(200L, Math.min(responseBodyJson.optLong("timeout", 200L), this.ipakvmocb.getTimeoutMs() > 0 ? this.ipakvmocb.getTimeoutMs() : ipakvmocc)));
                return ipakvmoca(chainArgs, j10);
            }
            AuthAnswer authAnswer = new AuthAnswer(responseBodyJson);
            if (superMethodResponseExecute.getHeaders().get("x-vkc-client-cookie") != null) {
                authAnswer.setCookies(new ArrayList<>(superMethodResponseExecute.getHeaders().values("x-vkc-client-cookie")));
            }
            return authAnswer;
        }
        Bundle bundle = new Bundle();
        bundle.putString(VKApiCodes.EXTRA_CAPTCHA_SID, responseBodyJson.getString(VKApiCodes.EXTRA_CAPTCHA_SID));
        bundle.putString(VKApiCodes.EXTRA_CAPTCHA_IMG, responseBodyJson.getString(VKApiCodes.EXTRA_CAPTCHA_IMG));
        Integer intOrNull = JsonObjectExtKt.getIntOrNull(responseBodyJson, VKApiCodes.EXTRA_CAPTCHA_ATTEMPT);
        if (intOrNull != null) {
            bundle.putInt(VKApiCodes.EXTRA_CAPTCHA_ATTEMPT, intOrNull.intValue());
        }
        Double doubleOrNull = JsonObjectExtKt.getDoubleOrNull(responseBodyJson, VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP);
        if (doubleOrNull != null) {
            bundle.putDouble(VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP, doubleOrNull.doubleValue());
        }
        Double doubleOrNull2 = JsonObjectExtKt.getDoubleOrNull(responseBodyJson, VKApiCodes.EXTRA_CAPTCHA_RATIO);
        if (doubleOrNull2 != null) {
            bundle.putDouble(VKApiCodes.EXTRA_CAPTCHA_RATIO, doubleOrNull2.doubleValue());
        }
        Boolean booleanOrNull = JsonObjectExtKt.getBooleanOrNull(responseBodyJson, VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED);
        if (booleanOrNull != null) {
            bundle.putBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_REFRESH_ENABLED, booleanOrNull.booleanValue());
        }
        Integer intOrNull2 = JsonObjectExtKt.getIntOrNull(responseBodyJson, VKApiCodes.EXTRA_CAPTCHA_IMG_HEIGHT);
        if (intOrNull2 != null) {
            bundle.putInt(VKApiCodes.EXTRA_CAPTCHA_IMG_HEIGHT, intOrNull2.intValue());
        }
        Integer intOrNull3 = JsonObjectExtKt.getIntOrNull(responseBodyJson, VKApiCodes.EXTRA_CAPTCHA_IMG_WIDTH);
        if (intOrNull3 != null) {
            bundle.putInt(VKApiCodes.EXTRA_CAPTCHA_IMG_WIDTH, intOrNull3.intValue());
        }
        Boolean booleanOrNull2 = JsonObjectExtKt.getBooleanOrNull(responseBodyJson, VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE);
        if (booleanOrNull2 != null) {
            bundle.putBoolean(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND_AVAILABLE, booleanOrNull2.booleanValue());
        }
        String stringOrNull = JsonObjectExtKt.getStringOrNull(responseBodyJson, VKApiCodes.EXTRA_CAPTCHA_TRACK);
        if (stringOrNull != null) {
            bundle.putString(VKApiCodes.EXTRA_CAPTCHA_TRACK, stringOrNull);
        }
        String stringOrNull2 = JsonObjectExtKt.getStringOrNull(responseBodyJson, "redirect_uri");
        if (stringOrNull2 != null) {
            bundle.putString("redirect_uri", stringOrNull2);
        }
        throw new VKApiExecutionException(14, this.ipakvmocb.getUrl(), false, "need_captcha", bundle, null, null, null, 0, null, null, null, 4064, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vk.api.sdk.chain.ChainCall
    @Nullable
    public AuthAnswer call(@NotNull ChainArgs args) throws Exception {
        Intrinsics.checkNotNullParameter(args, "args");
        return ipakvmoca(args, System.currentTimeMillis());
    }
}
