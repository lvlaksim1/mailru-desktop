package ru.mail.onpremiseauthsdk.internal.network;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import ru.mail.onpremiseauthsdk.api.OnPremiseAuthConfigProvider;
import ru.mail.onpremiseauthsdk.api.OnPremiseAuthDependencies;
import ru.mail.util.log.EmptyLogger;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tJ\u0015\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lru/mail/onpremiseauthsdk/internal/network/OnPremiseAuthApi;", "", "dependencies", "Lru/mail/onpremiseauthsdk/api/OnPremiseAuthDependencies;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Lru/mail/onpremiseauthsdk/api/OnPremiseAuthDependencies;Lru/mail/util/log/Logger;)V", "getTokenForTest", "", "email", "password", "getAccessAndRefresh", "code", "getBodyOrThrow", "response", "Lokhttp3/Response;", "getBodyOrThrow$onpremiseauthsdk_release", "Companion", "onpremiseauthsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OnPremiseAuthApi {

    @NotNull
    private static final String AUTHORIZATION_CODE = "authorization_code";

    @NotNull
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @NotNull
    private static final String CODE = "code";

    @NotNull
    private static final String GRANT_TYPE = "grant_type";

    @NotNull
    private static final String REDIRECT_URI = "redirect_uri";

    @NotNull
    private static final String TOKEN_API_PATH = "token";

    @NotNull
    private final OnPremiseAuthDependencies dependencies;

    @NotNull
    private final Logger logger;

    public OnPremiseAuthApi(@NotNull OnPremiseAuthDependencies dependencies, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(dependencies, "dependencies");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.dependencies = dependencies;
        this.logger = logger.createLogger("OnPremiseAuthApi");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final String getAccessAndRefresh(@NotNull String code) {
        String str;
        Intrinsics.checkNotNullParameter(code, "code");
        OnPremiseAuthConfigProvider authConfigProvider = this.dependencies.getAuthConfigProvider();
        FormBody formBodyBuild = new FormBody.Builder(null, 1, 0 == true ? 1 : 0).add(GRANT_TYPE, "authorization_code").add("code", code).add("redirect_uri", authConfigProvider.getConfig().getRedirectUrl()).build();
        String strInvoke = this.dependencies.getHostProvider().invoke();
        if (StringsKt.endsWith$default(strInvoke, "/", false, 2, (Object) null)) {
            str = strInvoke + "token";
        } else {
            str = strInvoke + "/token";
        }
        Request requestBuild = new Request.Builder().url(str).addHeader("Authorization", Credentials.basic$default(authConfigProvider.getConfig().getClientId(), authConfigProvider.getConfig().getSecret(), null, 4, null)).addHeader("Content-Type", "application/x-www-form-urlencoded").post(formBodyBuild).build();
        Logger.info$default(this.logger, "Get access and refresh with auth request = " + requestBuild, null, 2, null);
        Response responseExecute = this.dependencies.getOkClient().newCall(requestBuild).execute();
        try {
            String bodyOrThrow$onpremiseauthsdk_release = getBodyOrThrow$onpremiseauthsdk_release(responseExecute);
            CloseableKt.closeFinally(responseExecute, null);
            return bodyOrThrow$onpremiseauthsdk_release;
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(responseExecute, th2);
                throw th3;
            }
        }
    }

    @NotNull
    public final String getBodyOrThrow$onpremiseauthsdk_release(@NotNull Response response) throws IOException {
        String strString;
        Intrinsics.checkNotNullParameter(response, "response");
        Logger.info$default(this.logger, "Get body with auth response = " + response, null, 2, null);
        if (response.isSuccessful()) {
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                throw new IOException("The request was successful, but server returned an empty body.");
            }
            return strString;
        }
        throw new IOException("Server returned error: responseCode = " + response.code());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final String getTokenForTest(@NotNull String email, @NotNull String password) throws IOException {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(password, "password");
        Request requestBuild = new Request.Builder().url("https://alt-aj-https.mail.ru/cgi-bin/auth?Lang=en_US&mp=android&mmp=mail").header("Content-Type", "application/x-www-form-urlencoded").post(new FormBody.Builder(null, 1, 0 == true ? 1 : 0).add("Password", password).add("oauth2", "1").add("Login", email).add("mobile", "1").add("mob_json", "1").add("simple", "1").build()).build();
        Logger.info$default(this.logger, "Get token for test with auth request = " + requestBuild, null, 2, null);
        Response responseExecute = this.dependencies.getOkClient().newCall(requestBuild).execute();
        try {
            String bodyOrThrow$onpremiseauthsdk_release = getBodyOrThrow$onpremiseauthsdk_release(responseExecute);
            CloseableKt.closeFinally(responseExecute, null);
            return bodyOrThrow$onpremiseauthsdk_release;
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                CloseableKt.closeFinally(responseExecute, th2);
                throw th3;
            }
        }
    }

    public /* synthetic */ OnPremiseAuthApi(OnPremiseAuthDependencies onPremiseAuthDependencies, Logger logger, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(onPremiseAuthDependencies, (i10 & 2) != 0 ? new EmptyLogger() : logger);
    }
}
