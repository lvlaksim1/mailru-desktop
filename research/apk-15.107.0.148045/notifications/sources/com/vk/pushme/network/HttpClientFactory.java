package com.vk.pushme.network;

import com.vk.pushme.common.Logger;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import ru.mail.hitman.HitmanLib;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/vk/pushme/network/HttpClientFactory;", "", "<init>", "()V", "DEFAULT_TIMEOUT", "", "createClient", "Lokhttp3/OkHttpClient;", "logger", "Lcom/vk/pushme/common/Logger;", "debugLogsEnabled", "", "createHttpLoggingInterceptor", "Lokhttp3/Interceptor;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HttpClientFactory {
    private static final long DEFAULT_TIMEOUT = 60;

    @NotNull
    public static final HttpClientFactory INSTANCE = new HttpClientFactory();

    private HttpClientFactory() {
    }

    private final Interceptor createHttpLoggingInterceptor(final Logger logger, boolean debugLogsEnabled) {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() { // from class: com.vk.pushme.network.a
            @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
            public final void log(String str) {
                HttpClientFactory.createHttpLoggingInterceptor$lambda$0(logger, str);
            }
        }).setLevel(debugLogsEnabled ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void createHttpLoggingInterceptor$lambda$0(Logger logger, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Logger.info$default(logger, it, null, 2, null);
    }

    @NotNull
    public final OkHttpClient createClient(@NotNull Logger logger, boolean debugLogsEnabled) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Logger loggerCreateLogger = logger.createLogger("HttpLogging");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        return builder.connectTimeout(DEFAULT_TIMEOUT, timeUnit).writeTimeout(DEFAULT_TIMEOUT, timeUnit).readTimeout(DEFAULT_TIMEOUT, timeUnit).retryOnConnectionFailure(true).addInterceptor(HitmanLib.INSTANCE.getHitmanInterceptor()).addInterceptor(createHttpLoggingInterceptor(loggerCreateLogger, debugLogsEnabled)).build();
    }
}
