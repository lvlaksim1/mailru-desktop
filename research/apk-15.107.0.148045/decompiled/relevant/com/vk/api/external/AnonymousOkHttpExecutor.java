package com.vk.api.external;

import com.vk.api.external.exceptions.NonSecretMethodCallException;
import com.vk.api.external.okhttp.AnonymousOkHttpMethodCall;
import com.vk.api.sdk.VKApiCredentials;
import com.vk.api.sdk.okhttp.LoggingInterceptor;
import com.vk.api.sdk.okhttp.LoggingPrefixer;
import com.vk.api.sdk.okhttp.OkHttpExecutor;
import com.vk.api.sdk.okhttp.OkHttpExecutorConfig;
import com.vk.api.sdk.okhttp.OkHttpMethodCall;
import com.vk.api.sdk.utils.VKApiCredentialsExtKt;
import com.vk.api.sdk.utils.log.Logger;
import com.vk.superapp.api.dto.auth.PasskeyBeginResult;
import com.vk.superapp.api.dto.geo.GeoServicesConstants;
import com.vk.superapp.sessionmanagment.impl.data.source.SessionSQLiteHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.Headers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.mail.ads.core.impl.analytics.SessionParamsProviderImpl;
import ru.mail.cloud.upload.internal.analytics.EventParams;
import ru.mail.registration.request.RegServerRequest;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u0011H\u0014¨\u0006\u0017"}, d2 = {"Lcom/vk/api/external/AnonymousOkHttpExecutor;", "Lcom/vk/api/sdk/okhttp/OkHttpExecutor;", "config", "Lcom/vk/api/sdk/okhttp/OkHttpExecutorConfig;", "<init>", "(Lcom/vk/api/sdk/okhttp/OkHttpExecutorConfig;)V", "createLoggingInterceptor", "Lcom/vk/api/sdk/okhttp/LoggingInterceptor;", "filterCredentials", "", "logger", "Lcom/vk/api/sdk/utils/log/Logger;", "loggingPrefixer", "Lcom/vk/api/sdk/okhttp/LoggingPrefixer;", "getActualAccessToken", "", "call", "Lcom/vk/api/sdk/okhttp/OkHttpMethodCall;", "getActualSecret", "checkNonSecretMethodCall", "", "SuperMethodResponse", "Companion", "external_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAnonymousOkHttpExecutor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AnonymousOkHttpExecutor.kt\ncom/vk/api/external/AnonymousOkHttpExecutor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,178:1\n1#2:179\n*E\n"})
public class AnonymousOkHttpExecutor extends OkHttpExecutor {

    @NotNull
    protected static final Companion Companion = new Companion(null);

    @NotNull
    private static final List<String> lanretxesreganamipakvmoca = CollectionsKt.listOf((Object[]) new String[]{"access_token", "key", SharedKt.PARAM_CLIENT_SECRET, "anonymous_token", PasskeyBeginResult.SID_KEY, RegServerRequest.ATTR_AUTH_TOKEN, "exchange_token", "exchange_tokens", "common_token", EventParams.HASH, GeoServicesConstants.API_KEY, "api_hash", "access_key", "access_hash", SessionSQLiteHelper.COLUMN_WEBVIEW_RT, SessionSQLiteHelper.COLUMN_WEBVIEW_AT, "wat", "tracker_token", SessionParamsProviderImpl.PARAM_SESSION_ID, "password", "password2", "old_password", "new_password"});

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0084\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/vk/api/external/AnonymousOkHttpExecutor$Companion;", "", "<init>", "()V", "SENSITIVE_KEYS", "", "", "getSENSITIVE_KEYS", "()Ljava/util/List;", "external_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    protected static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<String> getSENSITIVE_KEYS() {
            return AnonymousOkHttpExecutor.lanretxesreganamipakvmoca;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes2.dex */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J:\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0016\u0010\u0013J\u0010\u0010\u0017\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0011J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\rR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010\u0011R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010\u0013¨\u0006("}, d2 = {"Lcom/vk/api/external/AnonymousOkHttpExecutor$SuperMethodResponse;", "", "Lorg/json/JSONObject;", "responseBodyJson", "Lokhttp3/Headers;", "headers", "", "code", "", "lastRequestUrl", "<init>", "(Lorg/json/JSONObject;Lokhttp3/Headers;ILjava/lang/String;)V", "component1", "()Lorg/json/JSONObject;", "component2", "()Lokhttp3/Headers;", "component3", "()I", "component4", "()Ljava/lang/String;", "copy", "(Lorg/json/JSONObject;Lokhttp3/Headers;ILjava/lang/String;)Lcom/vk/api/external/AnonymousOkHttpExecutor$SuperMethodResponse;", "toString", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "lanretxesreganamipakvmoca", "Lorg/json/JSONObject;", "getResponseBodyJson", "lanretxesreganamipakvmocb", "Lokhttp3/Headers;", "getHeaders", "lanretxesreganamipakvmocc", "I", "getCode", "lanretxesreganamipakvmocd", "Ljava/lang/String;", "getLastRequestUrl", "external_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class SuperMethodResponse {

        /* JADX INFO: renamed from: lanretxesreganamipakvmoca, reason: from kotlin metadata */
        @Nullable
        private final JSONObject responseBodyJson;

        /* JADX INFO: renamed from: lanretxesreganamipakvmocb, reason: from kotlin metadata */
        @NotNull
        private final Headers headers;

        /* JADX INFO: renamed from: lanretxesreganamipakvmocc, reason: from kotlin metadata */
        private final int code;

        /* JADX INFO: renamed from: lanretxesreganamipakvmocd, reason: from kotlin metadata */
        @NotNull
        private final String lastRequestUrl;

        public SuperMethodResponse(@Nullable JSONObject jSONObject, @NotNull Headers headers, int i10, @NotNull String lastRequestUrl) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(lastRequestUrl, "lastRequestUrl");
            this.responseBodyJson = jSONObject;
            this.headers = headers;
            this.code = i10;
            this.lastRequestUrl = lastRequestUrl;
        }

        public static /* synthetic */ SuperMethodResponse copy$default(SuperMethodResponse superMethodResponse, JSONObject jSONObject, Headers headers, int i10, String str, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                jSONObject = superMethodResponse.responseBodyJson;
            }
            if ((i11 & 2) != 0) {
                headers = superMethodResponse.headers;
            }
            if ((i11 & 4) != 0) {
                i10 = superMethodResponse.code;
            }
            if ((i11 & 8) != 0) {
                str = superMethodResponse.lastRequestUrl;
            }
            return superMethodResponse.copy(jSONObject, headers, i10, str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final JSONObject getResponseBodyJson() {
            return this.responseBodyJson;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Headers getHeaders() {
            return this.headers;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getLastRequestUrl() {
            return this.lastRequestUrl;
        }

        @NotNull
        public final SuperMethodResponse copy(@Nullable JSONObject responseBodyJson, @NotNull Headers headers, int code, @NotNull String lastRequestUrl) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(lastRequestUrl, "lastRequestUrl");
            return new SuperMethodResponse(responseBodyJson, headers, code, lastRequestUrl);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SuperMethodResponse)) {
                return false;
            }
            SuperMethodResponse superMethodResponse = (SuperMethodResponse) other;
            return Intrinsics.areEqual(this.responseBodyJson, superMethodResponse.responseBodyJson) && Intrinsics.areEqual(this.headers, superMethodResponse.headers) && this.code == superMethodResponse.code && Intrinsics.areEqual(this.lastRequestUrl, superMethodResponse.lastRequestUrl);
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final Headers getHeaders() {
            return this.headers;
        }

        @NotNull
        public final String getLastRequestUrl() {
            return this.lastRequestUrl;
        }

        @Nullable
        public final JSONObject getResponseBodyJson() {
            return this.responseBodyJson;
        }

        public int hashCode() {
            JSONObject jSONObject = this.responseBodyJson;
            return this.lastRequestUrl.hashCode() + ((Integer.hashCode(this.code) + ((this.headers.hashCode() + ((jSONObject == null ? 0 : jSONObject.hashCode()) * 31)) * 31)) * 31);
        }

        @NotNull
        public String toString() {
            return "SuperMethodResponse(responseBodyJson=" + this.responseBodyJson + ", headers=" + this.headers + ", code=" + this.code + ", lastRequestUrl=" + this.lastRequestUrl + ')';
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnonymousOkHttpExecutor(@NotNull OkHttpExecutorConfig config) {
        super(config);
        Intrinsics.checkNotNullParameter(config, "config");
    }

    @Override // com.vk.api.sdk.okhttp.OkHttpExecutor
    protected void checkNonSecretMethodCall(@NotNull OkHttpMethodCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        String actualSecret = getActualSecret(call);
        String actualAccessToken = getActualAccessToken(call);
        if (actualSecret == null || actualSecret.length() == 0) {
            if ((actualAccessToken == null || actualAccessToken.length() == 0) && !call.getAllowNoAuth()) {
                AnonymousOkHttpMethodCall anonymousOkHttpMethodCall = call instanceof AnonymousOkHttpMethodCall ? (AnonymousOkHttpMethodCall) call : null;
                if (anonymousOkHttpMethodCall == null || anonymousOkHttpMethodCall.getIsAnonymous()) {
                    return;
                }
                throw new NonSecretMethodCallException("Trying to call " + call.getMethod() + " without auth: " + CollectionsKt.listOfNotNull((Object[]) new String[]{(actualSecret == null || actualSecret.length() == 0) ? "st" : null, (actualAccessToken == null || actualAccessToken.length() == 0) ? "at" : null}) + ". Mark it with allowNoAuth if needed");
            }
        }
    }

    @Override // com.vk.api.sdk.okhttp.OkHttpExecutor
    @NotNull
    protected LoggingInterceptor createLoggingInterceptor(boolean filterCredentials, @NotNull Logger logger, @NotNull LoggingPrefixer loggingPrefixer) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(loggingPrefixer, "loggingPrefixer");
        return new LoggingInterceptor(filterCredentials, lanretxesreganamipakvmoca, logger, loggingPrefixer);
    }

    @Override // com.vk.api.sdk.okhttp.OkHttpExecutor
    @Nullable
    protected String getActualAccessToken(@NotNull OkHttpMethodCall call) {
        String accessToken;
        Intrinsics.checkNotNullParameter(call, "call");
        if (!(call instanceof AnonymousOkHttpMethodCall)) {
            return super.getActualAccessToken(call);
        }
        AnonymousOkHttpMethodCall anonymousOkHttpMethodCall = (AnonymousOkHttpMethodCall) call;
        Object obj = null;
        if (anonymousOkHttpMethodCall.getForceRemoveAccessToken() || call.getForceRemoveAuth()) {
            return null;
        }
        for (Object obj2 : getCredentials().getValue()) {
            if (Intrinsics.areEqual(((VKApiCredentials) obj2).getUserId(), anonymousOkHttpMethodCall.getUserIdForUseAccessToken())) {
                obj = obj2;
                break;
            }
        }
        VKApiCredentials vKApiCredentials = (VKApiCredentials) obj;
        if (vKApiCredentials != null && (accessToken = vKApiCredentials.getAccessToken()) != null) {
            return accessToken;
        }
        String requestAccessToken = anonymousOkHttpMethodCall.getRequestAccessToken();
        return requestAccessToken == null ? VKApiCredentialsExtKt.activeAccessToken(getCredentials().getValue()) : requestAccessToken;
    }

    @Override // com.vk.api.sdk.okhttp.OkHttpExecutor
    @Nullable
    protected String getActualSecret(@NotNull OkHttpMethodCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        if (!(call instanceof AnonymousOkHttpMethodCall)) {
            return super.getActualSecret(call);
        }
        AnonymousOkHttpMethodCall anonymousOkHttpMethodCall = (AnonymousOkHttpMethodCall) call;
        if (anonymousOkHttpMethodCall.getForceRemoveAccessToken() || call.getForceRemoveAuth()) {
            return null;
        }
        String requestSecret = anonymousOkHttpMethodCall.getRequestSecret();
        return requestSecret == null ? VKApiCredentialsExtKt.activeSecret(getCredentials().getValue()) : requestSecret;
    }
}
