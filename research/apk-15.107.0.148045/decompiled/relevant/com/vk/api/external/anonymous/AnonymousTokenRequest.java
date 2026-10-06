package com.vk.api.external.anonymous;

import com.vk.api.external.AnonymousOkHttpExecutorKt;
import com.vk.api.external.ExternalApiManagerKt;
import com.vk.api.external.call.HttpUrlPostCall;
import com.vk.api.sdk.VKApiConfig;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.chain.ChainArgs;
import com.vk.api.sdk.chain.ChainCall;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.internal.ApiCommand;
import com.vk.api.sdk.internal.QueryStringGenerator;
import com.vk.auth.restore.RestoreConstants;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated(message = "Don't use in new code.")
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0014¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/vk/api/external/anonymous/AnonymousTokenRequest;", "Lcom/vk/api/sdk/internal/ApiCommand;", "", "", "sendCurrentToken", "Lcom/vk/api/sdk/VKApiManager;", "manager", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "error", "<init>", "(ZLcom/vk/api/sdk/VKApiManager;Lcom/vk/api/sdk/exceptions/VKApiExecutionException;)V", "onExecute", "(Lcom/vk/api/sdk/VKApiManager;)Ljava/lang/String;", "lanretxesreganamipakvmocb", "Lcom/vk/api/sdk/VKApiManager;", "getManager", "()Lcom/vk/api/sdk/VKApiManager;", "lanretxesreganamipakvmocc", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "getError", "()Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "external_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAnonymousTokenRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AnonymousTokenRequest.kt\ncom/vk/api/external/anonymous/AnonymousTokenRequest\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,103:1\n1#2:104\n*E\n"})
public final class AnonymousTokenRequest extends ApiCommand<String> {
    private final boolean lanretxesreganamipakvmoca;

    /* JADX INFO: renamed from: lanretxesreganamipakvmocb, reason: from kotlin metadata */
    @NotNull
    private final VKApiManager manager;

    /* JADX INFO: renamed from: lanretxesreganamipakvmocc, reason: from kotlin metadata */
    @Nullable
    private final VKApiExecutionException error;

    @NotNull
    private final LinkedHashMap lanretxesreganamipakvmocd;

    public /* synthetic */ AnonymousTokenRequest(boolean z10, VKApiManager vKApiManager, VKApiExecutionException vKApiExecutionException, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(z10, vKApiManager, (i10 & 4) != 0 ? null : vKApiExecutionException);
    }

    @Nullable
    public final VKApiExecutionException getError() {
        return this.error;
    }

    @NotNull
    public final VKApiManager getManager() {
        return this.manager;
    }

    public AnonymousTokenRequest(boolean z10, @NotNull VKApiManager manager, @Nullable VKApiExecutionException vKApiExecutionException) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        this.lanretxesreganamipakvmoca = z10;
        this.manager = manager;
        this.error = vKApiExecutionException;
        this.lanretxesreganamipakvmocd = new LinkedHashMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vk.api.sdk.internal.ApiCommand
    @NotNull
    public String onExecute(@NotNull final VKApiManager manager) {
        String value;
        VKApiExecutionException vKApiExecutionException;
        Map<String, String> requestParams;
        String str;
        Intrinsics.checkNotNullParameter(manager, "manager");
        VKApiConfig config = manager.getConfig();
        String strValueOf = String.valueOf(this.manager.getConfig().getAppId());
        if (strValueOf != null) {
            this.lanretxesreganamipakvmocd.put("client_id", strValueOf);
        }
        String clientSecret = this.manager.getConfig().getClientSecret();
        if (clientSecret != null) {
            this.lanretxesreganamipakvmocd.put(SharedKt.PARAM_CLIENT_SECRET, clientSecret);
        }
        if (this.lanretxesreganamipakvmoca && (vKApiExecutionException = this.error) != null && (requestParams = vKApiExecutionException.getRequestParams()) != null && (str = requestParams.get("access_token")) != null) {
            this.lanretxesreganamipakvmocd.put("access_token", str);
        }
        String lang = this.manager.getConfig().getLang();
        if (lang != null) {
            this.lanretxesreganamipakvmocd.put("lang", lang);
        }
        this.lanretxesreganamipakvmocd.put(RestoreConstants.DEFAULT_URL_SCHEME, "1");
        if (config.getDeviceId().getValue().length() > 0 && (value = config.getDeviceId().getValue()) != null) {
            this.lanretxesreganamipakvmocd.put("device_id", value);
        }
        final HttpUrlPostCall httpUrlPostCall = new HttpUrlPostCall("https://" + config.getOauthHostProvider().invoke() + "/get_anonym_token", 0L, 0, 0, RequestBody.INSTANCE.create(QueryStringGenerator.buildNotSignedQueryString$default(QueryStringGenerator.INSTANCE, this.lanretxesreganamipakvmocd, manager.getConfig().getVersion(), null, manager.getConfig().getAppId(), null, false, null, false, 244, null), MediaType.INSTANCE.get("application/x-www-form-urlencoded; charset=utf-8")), (List) null, 46, (DefaultConstructorMarker) null);
        return (String) ExternalApiManagerKt.execute(manager, httpUrlPostCall, new ChainCall<String>(httpUrlPostCall) { // from class: com.vk.api.external.anonymous.AnonymousTokenRequest$onExecute$chainCall$1
            final /* synthetic */ HttpUrlPostCall lanretxesreganamipakvmocb;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.lanretxesreganamipakvmoca);
                this.lanretxesreganamipakvmocb = httpUrlPostCall;
            }

            @Override // com.vk.api.sdk.chain.ChainCall
            public String call(ChainArgs args) throws JSONException, VKApiException {
                Intrinsics.checkNotNullParameter(args, "args");
                JSONObject responseJson = AnonymousOkHttpExecutorKt.execute(this.lanretxesreganamipakvmoca.getExecutor(), this.lanretxesreganamipakvmocb, args).getResponseBodyJson();
                if (responseJson == null) {
                    throw new VKApiException("Response returned null instead of valid string response");
                }
                Intrinsics.checkNotNullParameter(responseJson, "responseJson");
                String string = responseJson.getString("token");
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                return string;
            }
        }, false);
    }
}
