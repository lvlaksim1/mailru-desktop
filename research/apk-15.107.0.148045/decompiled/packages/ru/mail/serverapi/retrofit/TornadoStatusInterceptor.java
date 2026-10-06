package ru.mail.serverapi.retrofit;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/serverapi/retrofit/TornadoStatusInterceptor;", "Lokhttp3/Interceptor;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Lru/mail/util/log/Logger;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TornadoStatusInterceptor implements Interceptor {

    @NotNull
    private final Logger logger;

    public TornadoStatusInterceptor(@NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response responseProceed = chain.proceed(chain.request());
        ResponseBody responseBodyBody = responseProceed.body();
        if (responseProceed.code() == 200 && responseBodyBody != null) {
            try {
                JSONObject jSONObject = new JSONObject(responseBodyBody.string());
                int i10 = jSONObject.getInt("status");
                String string = jSONObject.getString("body");
                MediaType mediaType = responseBodyBody.get$contentType();
                Response.Builder builderCode = responseProceed.newBuilder().code(i10);
                ResponseBody.Companion companion = ResponseBody.INSTANCE;
                Intrinsics.checkNotNull(string);
                return builderCode.body(companion.create(string, mediaType)).build();
            } catch (Exception e10) {
                this.logger.e("Unable to read json status from response", e10);
            }
        }
        return responseProceed;
    }
}
