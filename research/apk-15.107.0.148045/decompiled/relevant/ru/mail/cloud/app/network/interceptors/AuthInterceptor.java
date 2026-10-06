package ru.mail.cloud.app.network.interceptors;

import androidx.compose.runtime.internal.StabilityInferred;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import ru.mail.asserter.core.AsserterFactory;
import ru.mail.asserter.description.Descriptions;
import ru.mail.cloud.app.network.auth.TokenManager;
import ru.mail.cloud.app.utils.exceptions.NoAuthException;
import ru.mail.portal.app.adapter.di.Portal;
import ru.mail.util.log.Formats;
import ru.mail.util.log.LogBuilder;
import ru.mail.util.log.LogFilter;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J \u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001c\u0010\u0017\u001a\u00020\u0012*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u0005H\u0002R\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lru/mail/cloud/app/network/interceptors/AuthInterceptor;", "Lokhttp3/Interceptor;", "logger", "Lru/mail/util/log/Logger;", "clientId", "", "Lru/mail/cloud/app/network/auth/ClientID;", "tokenManager", "Lru/mail/cloud/app/network/auth/TokenManager;", "<init>", "(Lru/mail/util/log/Logger;Ljava/lang/String;Lru/mail/cloud/app/network/auth/TokenManager;)V", "logFilter", "Lru/mail/util/log/LogFilter;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "log", "", "token", "Lru/mail/cloud/app/network/auth/Token;", "httpUrl", "Lokhttp3/HttpUrl;", "addTokenFieldToBody", "Lokhttp3/Request$Builder;", "originalRequest", "Lokhttp3/Request;", "authToken", "assertEmptyAuthToken", "url", "AuthTokenType", "Companion", "cloud-app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AuthInterceptor implements Interceptor {

    @NotNull
    private static final String ACCESS_TOKEN = "access_token";

    @NotNull
    public static final String API_AUTH_REQUIRED = "auth_required";

    @NotNull
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @NotNull
    private final String clientId;

    @NotNull
    private final LogFilter logFilter;

    @NotNull
    private final Logger logger;

    @NotNull
    private final TokenManager tokenManager;
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes9.dex */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0086\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lru/mail/cloud/app/network/interceptors/AuthInterceptor$AuthTokenType;", "", "Companion", "cloud-app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AuthTokenType {

        @NotNull
        public static final String BASE = "BASE";

        @NotNull
        public static final String BODY = "BODY";

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = Companion.$$INSTANCE;

        @NotNull
        public static final String HEADER = "HEADER";

        @NotNull
        public static final String NONE = "NONE";

        @NotNull
        public static final String WEB = "WEB";

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/cloud/app/network/interceptors/AuthInterceptor$AuthTokenType$Companion;", "", "<init>", "()V", "NONE", "", "BASE", "WEB", "BODY", "HEADER", "cloud-app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();

            @NotNull
            public static final String BASE = "BASE";

            @NotNull
            public static final String BODY = "BODY";

            @NotNull
            public static final String HEADER = "HEADER";

            @NotNull
            public static final String NONE = "NONE";

            @NotNull
            public static final String WEB = "WEB";

            private Companion() {
            }
        }
    }

    public AuthInterceptor(@NotNull Logger logger, @NotNull String clientId, @NotNull TokenManager tokenManager) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(tokenManager, "tokenManager");
        this.clientId = clientId;
        this.tokenManager = tokenManager;
        this.logger = logger.createLogger("AuthInterceptor");
        Formats.ParamFormat paramFormatNewJsonFormat = Formats.newJsonFormat("authtoken");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewJsonFormat, "newJsonFormat(...)");
        Formats.ParamFormat paramFormatNewUrlEncodedJsonFormat = Formats.newUrlEncodedJsonFormat("authtoken");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlEncodedJsonFormat, "newUrlEncodedJsonFormat(...)");
        Formats.ParamFormat paramFormatNewUrlFormat = Formats.newUrlFormat("authtoken");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat, "newUrlFormat(...)");
        this.logFilter = new LogFilter(paramFormatNewJsonFormat, paramFormatNewUrlEncodedJsonFormat, paramFormatNewUrlFormat);
    }

    private final void addTokenFieldToBody(Request.Builder builder, Request request, String str) {
        if (!Intrinsics.areEqual(request.method(), "POST") || !(request.body() instanceof FormBody)) {
            Logger.w$default(this.logger, "Can't add token to body for " + request.url(), null, 2, null);
            return;
        }
        RequestBody requestBodyBody = request.body();
        Intrinsics.checkNotNull(requestBodyBody, "null cannot be cast to non-null type okhttp3.FormBody");
        FormBody formBody = (FormBody) requestBodyBody;
        FormBody.Builder builder2 = new FormBody.Builder(null, 1, null);
        int size = formBody.size();
        for (int i10 = 0; i10 < size; i10++) {
            builder2.addEncoded(formBody.encodedName(i10), formBody.encodedValue(i10));
        }
        builder2.add("access_token", str);
        builder.method(request.method(), builder2.build());
    }

    private final void assertEmptyAuthToken(String url) {
        AsserterFactory.createAsserter(Portal.asserterConfigFactory().createAsserterConfiguration("CloudAuthInterceptor")).fail("Can't perform authorized request, auth token is empty", new NoAuthException("User is not authorized"), Descriptions.compositionOf(CollectionsKt.listOf(Descriptions.constant("Url: " + url))));
    }

    private final void log(String token, HttpUrl httpUrl) {
        Logger.d$default(this.logger, this.logFilter.filter(LogBuilder.addString$default(LogBuilder.addString$default(new LogBuilder("Auth required.").addObject("used"), "authtoken", token, false, 4, null), "url", httpUrl.getUrl(), false, 4, null).endObject().build()), null, 2, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:35:0x00a9  */
    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        HttpUrl httpUrlUrl = request.url();
        String strQueryParameter = httpUrlUrl.queryParameter(API_AUTH_REQUIRED);
        List<String> listHeaders = request.headers("Authorization");
        if ((strQueryParameter == null || StringsKt.equals(strQueryParameter, "NONE", true)) && listHeaders.isEmpty()) {
            return chain.proceed(request);
        }
        String str = (String) BuildersKt__BuildersKt.runBlocking$default(null, new AuthInterceptor$intercept$authToken$1(this, null), 1, null);
        log(str, httpUrlUrl);
        if (str == null || StringsKt.isBlank(str)) {
            assertEmptyAuthToken(httpUrlUrl.getUrl());
            return chain.proceed(request);
        }
        HttpUrl.Builder builderRemoveAllQueryParameters = httpUrlUrl.newBuilder().removeAllQueryParameters(API_AUTH_REQUIRED);
        Request.Builder builderNewBuilder = request.newBuilder();
        if (strQueryParameter != null) {
            switch (strQueryParameter) {
                case "WEB":
                    builderRemoveAllQueryParameters.addQueryParameter("access_token", str);
                    break;
                case "BASE":
                    builderRemoveAllQueryParameters.addQueryParameter("token", str);
                    break;
                case "BODY":
                    addTokenFieldToBody(builderNewBuilder, request, str);
                    Unit unit = Unit.INSTANCE;
                    break;
                case "HEADER":
                    builderNewBuilder.addHeader("Authorization", "Bearer " + str);
                    break;
                default:
                    Unit unit2 = Unit.INSTANCE;
                    break;
            }
        } else {
            Unit unit3 = Unit.INSTANCE;
        }
        builderNewBuilder.url(builderRemoveAllQueryParameters.build());
        if (!listHeaders.isEmpty()) {
            builderNewBuilder.removeHeader("Authorization");
            for (String str2 : listHeaders) {
                if (Intrinsics.areEqual(str2, "Bearer WEB")) {
                    str2 = "Bearer " + str;
                }
                builderNewBuilder.addHeader("Authorization", str2);
            }
        }
        return chain.proceed(builderNewBuilder.build());
    }
}
