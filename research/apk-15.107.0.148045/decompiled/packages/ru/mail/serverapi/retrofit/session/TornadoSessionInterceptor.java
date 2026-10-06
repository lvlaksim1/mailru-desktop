package ru.mail.serverapi.retrofit.session;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import ru.mail.ads.core.impl.analytics.SessionParamsProviderImpl;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.network.FormBodyExtensionsKt;
import ru.mail.serverapi.retrofit.RetrofitConfig;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0010H\u0002J\u0018\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lru/mail/serverapi/retrofit/session/TornadoSessionInterceptor;", "Lokhttp3/Interceptor;", "logger", "Lru/mail/util/log/Logger;", "sessionCreator", "Lru/mail/serverapi/retrofit/session/SessionCreator;", "config", "Lru/mail/serverapi/retrofit/RetrofitConfig;", "<init>", "(Lru/mail/util/log/Logger;Lru/mail/serverapi/retrofit/session/SessionCreator;Lru/mail/serverapi/retrofit/RetrofitConfig;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "interceptWithSession", "getFormBody", "Lokhttp3/FormBody;", Event.Companion.Network.Fail.REQUEST_TAG, "Lokhttp3/Request;", "applySession", SessionParamsProviderImpl.PARAM_SESSION_ID, "Lru/mail/serverapi/retrofit/session/TornadoSession;", "body", "handleResponse", "response", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTornadoSessionInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TornadoSessionInterceptor.kt\nru/mail/serverapi/retrofit/session/TornadoSessionInterceptor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,111:1\n1#2:112\n*E\n"})
public final class TornadoSessionInterceptor implements Interceptor {

    @NotNull
    private final RetrofitConfig config;

    @NotNull
    private final Logger logger;

    @NotNull
    private final SessionCreator sessionCreator;

    public TornadoSessionInterceptor(@NotNull Logger logger, @NotNull SessionCreator sessionCreator, @NotNull RetrofitConfig config) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(sessionCreator, "sessionCreator");
        Intrinsics.checkNotNullParameter(config, "config");
        this.logger = logger;
        this.sessionCreator = sessionCreator;
        this.config = config;
    }

    private final Request applySession(TornadoSession session, Request request, FormBody body) {
        Request.Builder builderNewBuilder = request.newBuilder();
        FormBody.Builder builderNewBuilder2 = FormBodyExtensionsKt.newBuilder(body);
        session.applyToRequest(builderNewBuilder, builderNewBuilder2);
        return builderNewBuilder.post(builderNewBuilder2.build()).build();
    }

    private final FormBody getFormBody(Request request) {
        if (!Intrinsics.areEqual(request.method(), "POST")) {
            return null;
        }
        RequestBody requestBodyBody = request.body();
        if (requestBodyBody instanceof FormBody) {
            return (FormBody) requestBodyBody;
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x004a  */
    private final Response handleResponse(Response response, TornadoSession session) throws IOException {
        RequestSessionException.Reason reason;
        ResponseBody responseBodyBody = response.body();
        if (responseBodyBody == null) {
            return response;
        }
        try {
            String strString = responseBodyBody.string();
            if (response.code() == 403) {
                switch (strString) {
                    case "bind_required":
                        reason = RequestSessionException.Reason.BindRequired;
                        break;
                    case "user":
                        reason = RequestSessionException.Reason.User;
                        break;
                    case "token":
                        reason = RequestSessionException.Reason.Token;
                        break;
                    case "twostep_required":
                        reason = RequestSessionException.Reason.TwoStepRequired;
                        break;
                    default:
                        reason = null;
                        break;
                }
                if (reason != null) {
                    throw session.prepareSessionException(reason);
                }
            }
            return response.newBuilder().body(ResponseBody.INSTANCE.create(strString, responseBodyBody.get$contentType())).build();
        } catch (Exception e10) {
            this.logger.e("Unable to read body from response", e10);
            return response;
        }
    }

    private final Response interceptWithSession(Interceptor.Chain chain) throws RequestSessionException {
        try {
            Request request = chain.request();
            FormBody formBody = getFormBody(request);
            if (formBody == null) {
                return null;
            }
            String str = FormBodyExtensionsKt.get(formBody, "email");
            if (str == null || StringsKt.isBlank(str)) {
                str = null;
            }
            TornadoSession tornadoSessionCreate = this.sessionCreator.create(str);
            return handleResponse(chain.proceed(applySession(tornadoSessionCreate, request, formBody)), tornadoSessionCreate);
        } catch (IOException e10) {
            this.logger.e("IOException occurred", e10);
            return null;
        } catch (BadSessionException e11) {
            this.logger.e("Unable to add session to request", e11);
            throw new RequestSessionException(RequestSessionException.Reason.BadSession, e11.getAuthName(), e11.getTokenType(), e11.getLogin(), null, 16, null);
        } catch (RequestSessionException e12) {
            this.logger.e("RequestSessionException occurred", e12);
            if (this.config.getRethrowRequestSessionException()) {
                throw e12;
            }
            return null;
        }
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws RequestSessionException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response responseInterceptWithSession = interceptWithSession(chain);
        return responseInterceptWithSession == null ? chain.proceed(chain.request()) : responseInterceptWithSession;
    }
}
