package ru.mail.serverapi.retrofit;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.appmetricstracker.monitors.traffic.tagged.TaggedTrafficExtras;
import ru.mail.util.log.Logger;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/serverapi/retrofit/TornadoStatusMetricTagInterceptor;", "Lokhttp3/Interceptor;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Lru/mail/util/log/Logger;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "setBodyStatusCode", "", "response", "body", "Lokhttp3/ResponseBody;", PushProcessor.DATAKEY_EXTRAS, "Lru/mail/appmetricstracker/monitors/traffic/tagged/TaggedTrafficExtras;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TornadoStatusMetricTagInterceptor implements Interceptor {

    @NotNull
    private final Logger logger;

    public TornadoStatusMetricTagInterceptor(@NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
    }

    private final void setBodyStatusCode(Response response, ResponseBody body, TaggedTrafficExtras extras) throws IOException {
        BufferedSource source = body.getSource();
        source.request(LongCompanionObject.MAX_VALUE);
        Buffer bufferClone = source.getBuffer().clone();
        if (StringsKt.equals("gzip", response.headers().get("Content-Encoding"), true)) {
            GzipSource gzipSource = new GzipSource(bufferClone);
            try {
                bufferClone = new Buffer();
                bufferClone.writeAll(gzipSource);
                CloseableKt.closeFinally(gzipSource, null);
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(gzipSource, th2);
                    throw th3;
                }
            }
        }
        try {
            extras.getParams().put("body_code", String.valueOf(new JSONObject(bufferClone.readUtf8()).getInt("status")));
        } catch (Exception e10) {
            this.logger.e("Unable to read json status from response", e10);
        }
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Response responseProceed = chain.proceed(request);
        TaggedTrafficExtras taggedTrafficExtras = (TaggedTrafficExtras) request.tag(TaggedTrafficExtras.class);
        ResponseBody responseBodyBody = responseProceed.body();
        if (taggedTrafficExtras != null && responseBodyBody != null && responseProceed.code() == 200) {
            setBodyStatusCode(responseProceed, responseBodyBody, taggedTrafficExtras);
        }
        return responseProceed;
    }
}
