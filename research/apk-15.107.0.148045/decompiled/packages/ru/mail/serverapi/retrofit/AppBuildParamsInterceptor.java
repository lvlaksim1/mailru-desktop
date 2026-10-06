package ru.mail.serverapi.retrofit;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import ru.mail.deviceinfo.AppVersionProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/serverapi/retrofit/AppBuildParamsInterceptor;", "Lokhttp3/Interceptor;", "appVersionProvider", "Lru/mail/deviceinfo/AppVersionProvider;", "<init>", "(Lru/mail/deviceinfo/AppVersionProvider;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AppBuildParamsInterceptor implements Interceptor {

    @NotNull
    private final AppVersionProvider appVersionProvider;

    public AppBuildParamsInterceptor(@NotNull AppVersionProvider appVersionProvider) {
        Intrinsics.checkNotNullParameter(appVersionProvider, "appVersionProvider");
        this.appVersionProvider = appVersionProvider;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        HttpUrl httpUrlUrl = request.url();
        if (httpUrlUrl.queryParameterNames().contains("appbuild")) {
            return chain.proceed(request);
        }
        return chain.proceed(request.newBuilder().url(httpUrlUrl.newBuilder().addQueryParameter("appbuild", this.appVersionProvider.getVersionCode()).build()).build());
    }
}
