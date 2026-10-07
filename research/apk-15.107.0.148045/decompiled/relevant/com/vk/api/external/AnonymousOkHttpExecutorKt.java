package com.vk.api.external;

import android.net.Uri;
import com.vk.api.external.call.CustomHeader;
import com.vk.api.external.call.HttpUrlPostCall;
import com.vk.api.sdk.chain.ChainArgs;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.okhttp.OkHttpExecutor;
import com.vk.api.sdk.utils.HitmanChallengeKt;
import com.vk.lists.PaginationHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"execute", "Lcom/vk/api/external/AnonymousOkHttpExecutor$SuperMethodResponse;", "Lcom/vk/api/sdk/okhttp/OkHttpExecutor;", "call", "Lcom/vk/api/external/call/HttpUrlPostCall;", "chainArgs", "Lcom/vk/api/sdk/chain/ChainArgs;", "external_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AnonymousOkHttpExecutorKt {
    @NotNull
    public static final AnonymousOkHttpExecutor.SuperMethodResponse execute(@NotNull OkHttpExecutor okHttpExecutor, @NotNull HttpUrlPostCall call, @Nullable ChainArgs chainArgs) {
        String url;
        Intrinsics.checkNotNullParameter(okHttpExecutor, "<this>");
        Intrinsics.checkNotNullParameter(call, "call");
        if (chainArgs == null || !chainArgs.hasCaptcha()) {
            url = call.getUrl();
        } else {
            Uri.Builder builderBuildUpon = Uri.parse(call.getUrl()).buildUpon();
            if (chainArgs.getCaptchaSuccessToken().length() > 0) {
                builderBuildUpon.appendQueryParameter(VKApiCodes.EXTRA_CAPTCHA_SUCCESS_TOKEN, chainArgs.getCaptchaSuccessToken());
            } else {
                builderBuildUpon.appendQueryParameter(VKApiCodes.EXTRA_CAPTCHA_KEY, chainArgs.getCaptchaKey());
            }
            builderBuildUpon.appendQueryParameter(VKApiCodes.EXTRA_CAPTCHA_SID, chainArgs.getCaptchaSid());
            Integer captchaAttempt = chainArgs.getCaptchaAttempt();
            if (captchaAttempt != null) {
                builderBuildUpon.appendQueryParameter(VKApiCodes.EXTRA_CAPTCHA_ATTEMPT, String.valueOf(captchaAttempt.intValue()));
            }
            Double captchaTimestamp = chainArgs.getCaptchaTimestamp();
            if (captchaTimestamp != null) {
                builderBuildUpon.appendQueryParameter(VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP, String.valueOf(captchaTimestamp.doubleValue()));
            }
            Boolean isSoundCaptcha = chainArgs.getIsSoundCaptcha();
            if (isSoundCaptcha != null) {
                builderBuildUpon.appendQueryParameter(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND, isSoundCaptcha.booleanValue() ? "1" : PaginationHelper.DEFAULT_NEXT_FROM);
            }
            url = builderBuildUpon.build().toString();
        }
        Intrinsics.checkNotNull(url);
        Request.Builder builderTryInsertHitmanChallengeTokenFrom = HitmanChallengeKt.tryInsertHitmanChallengeTokenFrom(new Request.Builder().post(call.getRequestBody()).cacheControl(CacheControl.FORCE_NETWORK).url(url), chainArgs);
        for (CustomHeader customHeader : call.getCustomHeaders()) {
            builderTryInsertHitmanChallengeTokenFrom.addHeader(customHeader.getKey(), customHeader.getValue());
        }
        Response responseExecuteRequest = okHttpExecutor.executeRequest(builderTryInsertHitmanChallengeTokenFrom.build());
        HitmanChallengeKt.tryInsertHitmanChallengeParamsFrom(chainArgs, responseExecuteRequest.headers(), HitmanChallengeKt.tryGetDomain(call.getUrl()));
        return new AnonymousOkHttpExecutor.SuperMethodResponse(okHttpExecutor.readResponse(responseExecuteRequest), responseExecuteRequest.headers(), responseExecuteRequest.code(), responseExecuteRequest.request().url().getUrl());
    }
}
