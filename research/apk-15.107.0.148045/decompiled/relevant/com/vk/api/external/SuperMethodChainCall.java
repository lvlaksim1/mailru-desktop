package com.vk.api.external;

import com.google.android.gms.ads.RequestConfiguration;
import com.vk.api.external.okhttp.AnonymousOkHttpMethodCall;
import com.vk.api.sdk.VKApiJSONResponseParser;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.auth.VKAccessTokenProvider;
import com.vk.api.sdk.chain.ChainArgs;
import com.vk.api.sdk.chain.MethodChainCall;
import com.vk.api.sdk.okhttp.OkHttpExecutor;
import com.vk.api.sdk.okhttp.OkHttpMethodCall;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/vk/api/external/SuperMethodChainCall;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Lcom/vk/api/sdk/chain/MethodChainCall;", "manager", "Lcom/vk/api/sdk/VKApiManager;", "okHttpExecutor", "Lcom/vk/api/sdk/okhttp/OkHttpExecutor;", "callBuilder", "Lcom/vk/api/sdk/okhttp/OkHttpMethodCall$Builder;", "defaultDeviceId", "", "defaultLang", "parser", "Lcom/vk/api/sdk/VKApiJSONResponseParser;", "<init>", "(Lcom/vk/api/sdk/VKApiManager;Lcom/vk/api/sdk/okhttp/OkHttpExecutor;Lcom/vk/api/sdk/okhttp/OkHttpMethodCall$Builder;Ljava/lang/String;Ljava/lang/String;Lcom/vk/api/sdk/VKApiJSONResponseParser;)V", "call", "args", "Lcom/vk/api/sdk/chain/ChainArgs;", "(Lcom/vk/api/sdk/chain/ChainArgs;)Ljava/lang/Object;", "external_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class SuperMethodChainCall<T> extends MethodChainCall<T> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuperMethodChainCall(@NotNull VKApiManager manager, @NotNull OkHttpExecutor okHttpExecutor, @NotNull OkHttpMethodCall.Builder callBuilder, @NotNull String defaultDeviceId, @NotNull String defaultLang, @Nullable VKApiJSONResponseParser<T> vKApiJSONResponseParser) {
        super(manager, okHttpExecutor, callBuilder, defaultDeviceId, defaultLang, vKApiJSONResponseParser);
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(okHttpExecutor, "okHttpExecutor");
        Intrinsics.checkNotNullParameter(callBuilder, "callBuilder");
        Intrinsics.checkNotNullParameter(defaultDeviceId, "defaultDeviceId");
        Intrinsics.checkNotNullParameter(defaultLang, "defaultLang");
    }

    @Override // com.vk.api.sdk.chain.MethodChainCall, com.vk.api.sdk.chain.ChainCall
    @Nullable
    public T call(@NotNull ChainArgs args) throws Exception {
        Intrinsics.checkNotNullParameter(args, "args");
        OkHttpMethodCall.Builder callBuilder = getCallBuilder();
        boolean z10 = callBuilder instanceof AnonymousOkHttpMethodCall.Builder;
        boolean isAnonymous = z10 ? ((AnonymousOkHttpMethodCall.Builder) callBuilder).getIsAnonymous() : false;
        boolean forceRemoveAuth = z10 ? callBuilder.getForceRemoveAuth() : false;
        if (isAnonymous) {
            VKAccessTokenProvider value = getManager().getConfig().getAnonymousTokenProvider().getValue();
            String token = value != null ? value.getToken() : null;
            if (value == null || !value.isUsed() || token == null || token.length() == 0 || forceRemoveAuth) {
                callBuilder.args("client_id", String.valueOf(getManager().getConfig().getAppId()));
                callBuilder.args(SharedKt.PARAM_CLIENT_SECRET, getManager().getConfig().getClientSecret());
                callBuilder.getArgs().remove("access_token");
                if (!forceRemoveAuth) {
                    trackClientIdClientSecretMethods(value, callBuilder);
                }
            } else {
                callBuilder.args("access_token", token);
                callBuilder.getArgs().remove("client_id");
                callBuilder.getArgs().remove(SharedKt.PARAM_CLIENT_SECRET);
            }
        }
        return (T) super.call(args);
    }
}
