package com.vk.api.sdk.chain;

import com.google.android.gms.ads.RequestConfiguration;
import com.vk.api.sdk.VKApiJSONResponseParser;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.auth.VKAccessTokenProvider;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.okhttp.OkHttpExecutor;
import com.vk.api.sdk.okhttp.OkHttpMethodCall;
import com.vk.api.sdk.utils.HitmanChallengeKt;
import com.vk.lists.PaginationHelper;
import java.io.IOException;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010!\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001b\u001a\u00020\u001cH\u0016¢\u0006\u0002\u0010\"J\u0017\u0010#\u001a\u0004\u0018\u00018\u00002\u0006\u0010$\u001a\u00020%H\u0016¢\u0006\u0002\u0010&J\u0018\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010$\u001a\u00020%H\u0004J\u001a\u0010+\u001a\u00020(2\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020\bH\u0016J\u0016\u0010/\u001a\u00020(*\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u00060"}, d2 = {"Lcom/vk/api/sdk/chain/MethodChainCall;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Lcom/vk/api/sdk/chain/ChainCall;", "manager", "Lcom/vk/api/sdk/VKApiManager;", "okHttpExecutor", "Lcom/vk/api/sdk/okhttp/OkHttpExecutor;", "callBuilder", "Lcom/vk/api/sdk/okhttp/OkHttpMethodCall$Builder;", "defaultDeviceId", "", "defaultLang", "parser", "Lcom/vk/api/sdk/VKApiJSONResponseParser;", "<init>", "(Lcom/vk/api/sdk/VKApiManager;Lcom/vk/api/sdk/okhttp/OkHttpExecutor;Lcom/vk/api/sdk/okhttp/OkHttpMethodCall$Builder;Ljava/lang/String;Ljava/lang/String;Lcom/vk/api/sdk/VKApiJSONResponseParser;)V", "getOkHttpExecutor", "()Lcom/vk/api/sdk/okhttp/OkHttpExecutor;", "getCallBuilder", "()Lcom/vk/api/sdk/okhttp/OkHttpMethodCall$Builder;", "getDefaultDeviceId", "()Ljava/lang/String;", "setDefaultDeviceId", "(Ljava/lang/String;)V", "getDefaultLang", "getParser", "()Lcom/vk/api/sdk/VKApiJSONResponseParser;", "args", "Lcom/vk/api/sdk/chain/ChainArgs;", "getArgs", "()Lcom/vk/api/sdk/chain/ChainArgs;", "setArgs", "(Lcom/vk/api/sdk/chain/ChainArgs;)V", "call", "(Lcom/vk/api/sdk/chain/ChainArgs;)Ljava/lang/Object;", "runRequest", "mc", "Lcom/vk/api/sdk/okhttp/OkHttpMethodCall;", "(Lcom/vk/api/sdk/okhttp/OkHttpMethodCall;)Ljava/lang/Object;", "addHitmanChallenge", "", "headers", "Lokhttp3/Headers;", "trackClientIdClientSecretMethods", "provider", "Lcom/vk/api/sdk/auth/VKAccessTokenProvider;", "methodBuilder", "tryAddHitmanChallengeTokenFrom", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMethodChainCall.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MethodChainCall.kt\ncom/vk/api/sdk/chain/MethodChainCall\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,116:1\n1#2:117\n*E\n"})
public class MethodChainCall<T> extends ChainCall<T> {

    @Nullable
    private ChainArgs args;

    @NotNull
    private final OkHttpMethodCall.Builder callBuilder;

    @NotNull
    private String defaultDeviceId;

    @NotNull
    private final String defaultLang;

    @NotNull
    private final OkHttpExecutor okHttpExecutor;

    @Nullable
    private final VKApiJSONResponseParser<T> parser;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MethodChainCall(@NotNull VKApiManager manager, @NotNull OkHttpExecutor okHttpExecutor, @NotNull OkHttpMethodCall.Builder callBuilder, @NotNull String defaultDeviceId, @NotNull String defaultLang, @Nullable VKApiJSONResponseParser<T> vKApiJSONResponseParser) {
        super(manager);
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(okHttpExecutor, "okHttpExecutor");
        Intrinsics.checkNotNullParameter(callBuilder, "callBuilder");
        Intrinsics.checkNotNullParameter(defaultDeviceId, "defaultDeviceId");
        Intrinsics.checkNotNullParameter(defaultLang, "defaultLang");
        this.okHttpExecutor = okHttpExecutor;
        this.callBuilder = callBuilder;
        this.defaultDeviceId = defaultDeviceId;
        this.defaultLang = defaultLang;
        this.parser = vKApiJSONResponseParser;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpExecutor.ExecutorResponse runRequest$lambda$4(MethodChainCall methodChainCall, OkHttpMethodCall okHttpMethodCall) throws InterruptedException, VKApiException, IOException {
        OkHttpExecutor.ExecutorResponse executorResponseExecute = methodChainCall.okHttpExecutor.execute(okHttpMethodCall);
        methodChainCall.addHitmanChallenge(executorResponseExecute.getHeaders(), okHttpMethodCall);
        return executorResponseExecute;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpExecutor.ExecutorResponseStream runRequest$lambda$6(MethodChainCall methodChainCall, OkHttpMethodCall okHttpMethodCall) throws InterruptedException, VKApiException, IOException {
        OkHttpExecutor.ExecutorResponseStream executorResponseStreamExecuteStream = methodChainCall.okHttpExecutor.executeStream(okHttpMethodCall);
        methodChainCall.addHitmanChallenge(executorResponseStreamExecuteStream.getHeaders(), okHttpMethodCall);
        return executorResponseStreamExecuteStream;
    }

    private final void tryAddHitmanChallengeTokenFrom(OkHttpMethodCall.Builder builder, ChainArgs chainArgs) {
        String hitmanChallengeToken;
        if (chainArgs == null || (hitmanChallengeToken = chainArgs.getHitmanChallengeToken()) == null) {
            return;
        }
        builder.headers("X-Challenge-Solution", hitmanChallengeToken);
    }

    protected final void addHitmanChallenge(@NotNull Headers headers, @NotNull OkHttpMethodCall mc2) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(mc2, "mc");
        HitmanChallengeKt.tryInsertHitmanChallengeParamsFrom(this.args, headers, mc2.getResolvedRequestUrl());
    }

    @Override // com.vk.api.sdk.chain.ChainCall
    @Nullable
    public T call(@NotNull ChainArgs args) throws Exception {
        Intrinsics.checkNotNullParameter(args, "args");
        this.args = args;
        if (args.hasCaptcha()) {
            this.callBuilder.args(VKApiCodes.EXTRA_CAPTCHA_SID, args.getCaptchaSid());
            if (args.getCaptchaSuccessToken().length() > 0) {
                this.callBuilder.args(VKApiCodes.EXTRA_CAPTCHA_SUCCESS_TOKEN, args.getCaptchaSuccessToken());
            } else {
                this.callBuilder.args(VKApiCodes.EXTRA_CAPTCHA_KEY, args.getCaptchaKey());
            }
            Integer captchaAttempt = args.getCaptchaAttempt();
            if (captchaAttempt != null) {
                this.callBuilder.args(VKApiCodes.EXTRA_CAPTCHA_ATTEMPT, String.valueOf(captchaAttempt.intValue()));
            }
            Double captchaTimestamp = args.getCaptchaTimestamp();
            if (captchaTimestamp != null) {
                this.callBuilder.args(VKApiCodes.EXTRA_CAPTCHA_TIMESTAMP, String.valueOf(captchaTimestamp.doubleValue()));
            }
            Boolean isSoundCaptcha = args.getIsSoundCaptcha();
            if (isSoundCaptcha != null) {
                this.callBuilder.args(VKApiCodes.EXTRA_CAPTCHA_IS_SOUND, isSoundCaptcha.booleanValue() ? "1" : PaginationHelper.DEFAULT_NEXT_FROM);
            }
        }
        tryAddHitmanChallengeTokenFrom(this.callBuilder, args);
        if (args.getUserConfirmed()) {
            this.callBuilder.args(VKApiCodes.EXTRA_CONFIRM, "1");
        }
        String strArgs = this.callBuilder.args("device_id");
        if (strArgs == null) {
            strArgs = "";
        }
        if (StringsKt.isBlank(strArgs)) {
            strArgs = this.defaultDeviceId;
        }
        OkHttpMethodCall.Builder builder = this.callBuilder;
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = strArgs.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        builder.args("device_id", lowerCase);
        String strArgs2 = this.callBuilder.args("lang");
        String str = strArgs2 != null ? strArgs2 : "";
        if (StringsKt.isBlank(str)) {
            str = this.defaultLang;
        }
        OkHttpMethodCall.Builder builder2 = this.callBuilder;
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
        String lowerCase2 = str.toLowerCase(locale2);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        builder2.args("lang", lowerCase2);
        return runRequest(this.callBuilder.build());
    }

    @Nullable
    protected final ChainArgs getArgs() {
        return this.args;
    }

    @NotNull
    public final OkHttpMethodCall.Builder getCallBuilder() {
        return this.callBuilder;
    }

    @NotNull
    public final String getDefaultDeviceId() {
        return this.defaultDeviceId;
    }

    @NotNull
    public final String getDefaultLang() {
        return this.defaultLang;
    }

    @NotNull
    public final OkHttpExecutor getOkHttpExecutor() {
        return this.okHttpExecutor;
    }

    @Nullable
    public final VKApiJSONResponseParser<T> getParser() {
        return this.parser;
    }

    @Nullable
    public T runRequest(@NotNull final OkHttpMethodCall mc2) {
        Intrinsics.checkNotNullParameter(mc2, "mc");
        return (T) StrategyCallsKt.callWithExecuteErrorCheck(this.parser, new Function0() { // from class: com.vk.api.sdk.chain.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MethodChainCall.runRequest$lambda$4(this.f40017a, mc2);
            }
        }, new Function0() { // from class: com.vk.api.sdk.chain.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MethodChainCall.runRequest$lambda$6(this.f40019a, mc2);
            }
        }, mc2.getMethod(), null);
    }

    protected final void setArgs(@Nullable ChainArgs chainArgs) {
        this.args = chainArgs;
    }

    public final void setDefaultDeviceId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.defaultDeviceId = str;
    }

    public void trackClientIdClientSecretMethods(@Nullable VKAccessTokenProvider provider, @NotNull OkHttpMethodCall.Builder methodBuilder) {
        Intrinsics.checkNotNullParameter(methodBuilder, "methodBuilder");
    }
}
