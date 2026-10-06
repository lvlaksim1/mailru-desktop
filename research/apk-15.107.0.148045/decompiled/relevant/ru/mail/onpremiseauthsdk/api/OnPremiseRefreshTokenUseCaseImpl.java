package ru.mail.onpremiseauthsdk.api;

import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.onpremiseauthsdk.api.models.AuthData;
import ru.mail.onpremiseauthsdk.api.models.AuthError;
import ru.mail.onpremiseauthsdk.internal.OnPremiseRefreshTokenRepository;
import ru.mail.onpremiseauthsdk.internal.network.OnPremiseAuth;
import ru.mail.onpremiseauthsdk.internal.network.OnPremiseParser;
import ru.mail.ui.quickactions.QuickActionOptionProvider;
import ru.mail.util.log.Log;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ;2\u00020\u0001:\u0001;B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J&\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001dH\u0002J \u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J3\u0010\"\u001a\b\u0012\u0004\u0012\u00020 0#2\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001dH\u0002¢\u0006\u0004\b$\u0010%J;\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00120#2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001dH\u0002¢\u0006\u0004\b'\u0010(J.\u0010)\u001a\u00020*2\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d2\u0006\u0010+\u001a\u00020,H\u0002J6\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001dH\u0002J6\u0010/\u001a\u00020\u00122\u0006\u0010.\u001a\u0002002\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001dH\u0002J&\u00101\u001a\u00020\u00122\u0006\u0010.\u001a\u0002022\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001dH\u0002J\u0010\u00103\u001a\u00020\u00122\u0006\u0010.\u001a\u000204H\u0002J\u0010\u00105\u001a\u00020*2\u0006\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020:H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f¨\u0006<"}, d2 = {"Lru/mail/onpremiseauthsdk/api/OnPremiseRefreshTokenUseCaseImpl;", "Lru/mail/onpremiseauthsdk/api/OnPremiseRefreshTokenUseCase;", "dependencies", "Lru/mail/onpremiseauthsdk/api/OnPremiseAuthDependencies;", "<init>", "(Lru/mail/onpremiseauthsdk/api/OnPremiseAuthDependencies;)V", "parser", "Lru/mail/onpremiseauthsdk/internal/network/OnPremiseParser;", "getParser", "()Lru/mail/onpremiseauthsdk/internal/network/OnPremiseParser;", "parser$delegate", "Lkotlin/Lazy;", "repository", "Lru/mail/onpremiseauthsdk/internal/OnPremiseRefreshTokenRepository;", "getRepository", "()Lru/mail/onpremiseauthsdk/internal/OnPremiseRefreshTokenRepository;", "repository$delegate", "refreshToken", "Lru/mail/onpremiseauthsdk/internal/network/OnPremiseAuth;", "handleHttpError", "response", "Lokhttp3/Response;", "handleSuccessfulResponse", "handleEmptyBody", "parseResponse", "", "httpCode", "", "responseMeta", "Lkotlin/Function0;", "handleOAuthErrorResponse", "jsonObject", "Lorg/json/JSONObject;", "oauthError", "parseJsonObject", "Lkotlin/Result;", "parseJsonObject-0E7RQCE", "(Ljava/lang/String;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "parseWebViewResponse", "parseWebViewResponse-BWLJW6A", "(Lorg/json/JSONObject;Ljava/lang/String;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "logJsonParseError", "", OkListenerKt.KEY_EXCEPTION, "Lorg/json/JSONException;", "handleParsedResponse", "parsedResponse", "handleFailedParsedResponse", "Lru/mail/onpremiseauthsdk/internal/network/OnPremiseAuth$Failed;", "handleIllegalStateParsedResponse", "Lru/mail/onpremiseauthsdk/internal/network/OnPremiseAuth$IllegalState;", "handleSuccessParsedResponse", "Lru/mail/onpremiseauthsdk/internal/network/OnPremiseAuth$Success;", "notifyRefreshTokenFailure", "error", "Lru/mail/onpremiseauthsdk/api/OnPremiseRefreshTokenError;", "notifyRefreshTokenResult", "result", "Lru/mail/onpremiseauthsdk/api/OnPremiseRefreshTokenResult;", "Companion", "onpremiseauthsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OnPremiseRefreshTokenUseCaseImpl implements OnPremiseRefreshTokenUseCase {
    private static final int NO_HTTP_CODE = -1;

    @NotNull
    private final OnPremiseAuthDependencies dependencies;

    /* JADX INFO: renamed from: parser$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy parser;

    /* JADX INFO: renamed from: repository$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy repository;

    @NotNull
    private static final Companion Companion = new Companion(null);

    @NotNull
    private static final String TAG = "OnPremiseRefreshTokenUseCase";

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog(TAG);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/onpremiseauthsdk/api/OnPremiseRefreshTokenUseCaseImpl$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", QuickActionOptionProvider.OPTION_TAG, "", "NO_HTTP_CODE", "", "onpremiseauthsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public OnPremiseRefreshTokenUseCaseImpl(@NotNull OnPremiseAuthDependencies dependencies) {
        Intrinsics.checkNotNullParameter(dependencies, "dependencies");
        this.dependencies = dependencies;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        this.parser = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: ru.mail.onpremiseauthsdk.api.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OnPremiseRefreshTokenUseCaseImpl.parser_delegate$lambda$0();
            }
        });
        this.repository = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: ru.mail.onpremiseauthsdk.api.e
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OnPremiseRefreshTokenUseCaseImpl.repository_delegate$lambda$0(this.f96137a);
            }
        });
    }

    private final OnPremiseParser getParser() {
        return (OnPremiseParser) this.parser.getValue();
    }

    private final OnPremiseRefreshTokenRepository getRepository() {
        return (OnPremiseRefreshTokenRepository) this.repository.getValue();
    }

    private final OnPremiseAuth handleEmptyBody(Response response) {
        notifyRefreshTokenFailure(new OnPremiseRefreshTokenError.EmptyBody(response.code()));
        LOG.w("refreshToken response body is empty: " + OnPremiseRefreshTokenLogExtensionsKt.toLogMetadata(response));
        return new OnPremiseAuth.IllegalState(new IOException("The request was successful, but server returned an empty body."));
    }

    private final OnPremiseAuth handleFailedParsedResponse(OnPremiseAuth.Failed parsedResponse, JSONObject jsonObject, String response, int httpCode, Function0<String> responseMeta) {
        notifyRefreshTokenFailure(OnPremiseRefreshTokenJsonExtensionsKt.toRefreshTokenError(jsonObject, httpCode));
        Log log = LOG;
        String strInvoke = responseMeta.invoke();
        log.w("parseResponse error: no access_token, " + ((Object) strInvoke) + ", responseSize=" + response.length());
        return parsedResponse;
    }

    private final OnPremiseAuth handleHttpError(Response response) {
        ResponseBody responseBodyBody = response.body();
        String strString = responseBodyBody != null ? responseBodyBody.string() : null;
        if (strString == null) {
            strString = "";
        }
        notifyRefreshTokenFailure(new OnPremiseRefreshTokenError.HttpError(response.code()));
        LOG.i("refreshToken request error: " + OnPremiseRefreshTokenLogExtensionsKt.toLogMetadata(response) + ", bodySize=" + strString.length());
        return new OnPremiseAuth.Failed(new AuthError(strString, response.code(), "Server returned error: responseCode = " + response.code()));
    }

    private final OnPremiseAuth handleIllegalStateParsedResponse(OnPremiseAuth.IllegalState parsedResponse, int httpCode, Function0<String> responseMeta) {
        String simpleName = parsedResponse.getError().getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        notifyRefreshTokenFailure(new OnPremiseRefreshTokenError.IllegalState(httpCode, simpleName));
        LOG.w("parseResponse error: IllegalState, " + ((Object) responseMeta.invoke()), parsedResponse.getError());
        return parsedResponse;
    }

    private final OnPremiseAuth handleOAuthErrorResponse(JSONObject jsonObject, String oauthError, int httpCode) {
        Integer numOptIntOrNull = OnPremiseRefreshTokenJsonExtensionsKt.optIntOrNull(jsonObject, "error_code");
        notifyRefreshTokenFailure(OnPremiseRefreshTokenJsonExtensionsKt.toRefreshTokenError(jsonObject, httpCode));
        LOG.w("parseResponse OAuth error: error=" + oauthError + ", httpCode=" + httpCode);
        int iIntValue = numOptIntOrNull != null ? numOptIntOrNull.intValue() : 0;
        String strOptString = jsonObject.optString("error_description");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        return new OnPremiseAuth.Failed(new AuthError(oauthError, iIntValue, strOptString));
    }

    private final OnPremiseAuth handleParsedResponse(OnPremiseAuth parsedResponse, JSONObject jsonObject, String response, int httpCode, Function0<String> responseMeta) {
        if (parsedResponse instanceof OnPremiseAuth.Failed) {
            return handleFailedParsedResponse((OnPremiseAuth.Failed) parsedResponse, jsonObject, response, httpCode, responseMeta);
        }
        if (parsedResponse instanceof OnPremiseAuth.IllegalState) {
            return handleIllegalStateParsedResponse((OnPremiseAuth.IllegalState) parsedResponse, httpCode, responseMeta);
        }
        if (parsedResponse instanceof OnPremiseAuth.Success) {
            return handleSuccessParsedResponse((OnPremiseAuth.Success) parsedResponse);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final OnPremiseAuth handleSuccessParsedResponse(OnPremiseAuth.Success parsedResponse) {
        AuthData authData = parsedResponse.getAuthData();
        LOG.i("parseResponse success");
        OnPremiseTokenStorage tokenStorage = this.dependencies.getTokenStorage();
        tokenStorage.saveAccessToken(authData.getAccessToken());
        tokenStorage.saveExpiresIn(authData.getExpiresIn());
        return new OnPremiseAuth.Success(new AuthData(authData.getAccessToken(), this.dependencies.getTokenStorage().getRefreshToken(), authData.getExpiresIn()));
    }

    private final OnPremiseAuth handleSuccessfulResponse(final Response response) {
        ResponseBody responseBodyBody = response.body();
        String strString = responseBodyBody != null ? responseBodyBody.string() : null;
        return (strString == null || strString.length() == 0) ? handleEmptyBody(response) : parseResponse(strString, response.code(), new Function0() { // from class: ru.mail.onpremiseauthsdk.api.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OnPremiseRefreshTokenLogExtensionsKt.toLogMetadata(response);
            }
        });
    }

    private final void logJsonParseError(String response, int httpCode, Function0<String> responseMeta, JSONException exception) {
        String simpleName = exception.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        notifyRefreshTokenFailure(new OnPremiseRefreshTokenError.JsonParseError(httpCode, simpleName));
        Log log = LOG;
        String strInvoke = responseMeta.invoke();
        log.e("parseResponse error: unable to parse response, " + ((Object) strInvoke) + ", responseSize=" + response.length(), exception);
    }

    private final void notifyRefreshTokenFailure(OnPremiseRefreshTokenError error) {
        notifyRefreshTokenResult(new OnPremiseRefreshTokenResult.Failure(error));
    }

    private final void notifyRefreshTokenResult(OnPremiseRefreshTokenResult result) {
        Object objM13123constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            this.dependencies.getRefreshTokenResultListener().onRefreshTokenResult(result);
            objM13123constructorimpl = Result.m13123constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
        if (thM13126exceptionOrNullimpl != null) {
            LOG.w("refreshToken result listener failed", thM13126exceptionOrNullimpl);
        }
    }

    /* JADX INFO: renamed from: parseJsonObject-0E7RQCE, reason: not valid java name */
    private final Object m15754parseJsonObject0E7RQCE(String response, int httpCode, Function0<String> responseMeta) {
        try {
            Result.Companion companion = Result.INSTANCE;
            return Result.m13123constructorimpl(new JSONObject(response));
        } catch (JSONException e10) {
            logJsonParseError(response, httpCode, responseMeta, e10);
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m13123constructorimpl(ResultKt.createFailure(e10));
        }
    }

    private final OnPremiseAuth parseResponse(String response, int httpCode, Function0<String> responseMeta) {
        Object objM15754parseJsonObject0E7RQCE = m15754parseJsonObject0E7RQCE(response, httpCode, responseMeta);
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM15754parseJsonObject0E7RQCE);
        if (thM13126exceptionOrNullimpl != null) {
            return new OnPremiseAuth.IllegalState(thM13126exceptionOrNullimpl);
        }
        JSONObject jSONObject = (JSONObject) objM15754parseJsonObject0E7RQCE;
        String strOptStringOrNull = OnPremiseRefreshTokenJsonExtensionsKt.optStringOrNull(jSONObject, "error");
        if (strOptStringOrNull != null) {
            return handleOAuthErrorResponse(jSONObject, strOptStringOrNull, httpCode);
        }
        Object objM15755parseWebViewResponseBWLJW6A = m15755parseWebViewResponseBWLJW6A(jSONObject, response, httpCode, responseMeta);
        Throwable thM13126exceptionOrNullimpl2 = Result.m13126exceptionOrNullimpl(objM15755parseWebViewResponseBWLJW6A);
        return thM13126exceptionOrNullimpl2 == null ? handleParsedResponse((OnPremiseAuth) objM15755parseWebViewResponseBWLJW6A, jSONObject, response, httpCode, responseMeta) : new OnPremiseAuth.IllegalState(thM13126exceptionOrNullimpl2);
    }

    /* JADX INFO: renamed from: parseWebViewResponse-BWLJW6A, reason: not valid java name */
    private final Object m15755parseWebViewResponseBWLJW6A(JSONObject jsonObject, String response, int httpCode, Function0<String> responseMeta) {
        try {
            Result.Companion companion = Result.INSTANCE;
            return Result.m13123constructorimpl(getParser().parseWebView(jsonObject));
        } catch (JSONException e10) {
            logJsonParseError(response, httpCode, responseMeta, e10);
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m13123constructorimpl(ResultKt.createFailure(e10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OnPremiseParser parser_delegate$lambda$0() {
        return new OnPremiseParser();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OnPremiseRefreshTokenRepository repository_delegate$lambda$0(OnPremiseRefreshTokenUseCaseImpl onPremiseRefreshTokenUseCaseImpl) {
        return new OnPremiseRefreshTokenRepository(onPremiseRefreshTokenUseCaseImpl.dependencies.getTokenStorage(), onPremiseRefreshTokenUseCaseImpl.dependencies.getAuthConfigProvider(), onPremiseRefreshTokenUseCaseImpl.dependencies.getOkClient(), onPremiseRefreshTokenUseCaseImpl.dependencies.getHostProvider());
    }

    @Override // ru.mail.onpremiseauthsdk.api.OnPremiseRefreshTokenUseCase
    @NotNull
    public OnPremiseAuth refreshToken() {
        Object objM13123constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            Response responseRefreshToken = getRepository().refreshToken();
            try {
                OnPremiseAuth onPremiseAuthHandleHttpError = !responseRefreshToken.isSuccessful() ? handleHttpError(responseRefreshToken) : handleSuccessfulResponse(responseRefreshToken);
                CloseableKt.closeFinally(responseRefreshToken, null);
                objM13123constructorimpl = Result.m13123constructorimpl(onPremiseAuthHandleHttpError);
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(responseRefreshToken, th2);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th4));
        }
        Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
        if (thM13126exceptionOrNullimpl == null) {
            OnPremiseAuth onPremiseAuth = (OnPremiseAuth) objM13123constructorimpl;
            if (!(onPremiseAuth instanceof OnPremiseAuth.Success)) {
                return onPremiseAuth;
            }
            LOG.i("refreshToken success");
            notifyRefreshTokenResult(OnPremiseRefreshTokenResult.Success.INSTANCE);
            return onPremiseAuth;
        }
        if (thM13126exceptionOrNullimpl instanceof OnPremiseRefreshTokenRepository.RefreshTokenNotFoundException) {
            LOG.i("refreshToken skipped: refresh token not found");
        } else if (thM13126exceptionOrNullimpl instanceof IOException) {
            String simpleName = thM13126exceptionOrNullimpl.getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
            notifyRefreshTokenFailure(new OnPremiseRefreshTokenError.RequestException(simpleName));
            LOG.i("refreshToken request error", thM13126exceptionOrNullimpl);
        } else {
            String simpleName2 = thM13126exceptionOrNullimpl.getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName2, "getSimpleName(...)");
            notifyRefreshTokenFailure(new OnPremiseRefreshTokenError.IllegalState(-1, simpleName2));
            LOG.i("refreshToken error", thM13126exceptionOrNullimpl);
        }
        return new OnPremiseAuth.IllegalState(thM13126exceptionOrNullimpl);
    }
}
