package ru.mail.serverapi.retrofit;

import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.http.NameValuePair;
import org.jetbrains.annotations.NotNull;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.network.FormBodyExtensionsKt;
import ru.mail.network.HostProvider;
import ru.mail.network.request.FilteredParamsProvider;
import ru.mail.network.request.NecessaryParamsProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/serverapi/retrofit/PlatformParamsInterceptor;", "Lokhttp3/Interceptor;", "hostProvider", "Lru/mail/network/HostProvider;", "<init>", "(Lru/mail/network/HostProvider;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "interceptWithPlatformParams", "getFormBody", "Lokhttp3/FormBody;", Event.Companion.Network.Fail.REQUEST_TAG, "Lokhttp3/Request;", "getFilteredParamsProvider", "Lru/mail/network/request/FilteredParamsProvider;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPlatformParamsInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlatformParamsInterceptor.kt\nru/mail/serverapi/retrofit/PlatformParamsInterceptor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,59:1\n1#2:60\n1869#3,2:61\n1869#3,2:63\n*S KotlinDebug\n*F\n+ 1 PlatformParamsInterceptor.kt\nru/mail/serverapi/retrofit/PlatformParamsInterceptor\n*L\n30#1:61,2\n35#1:63,2\n*E\n"})
public final class PlatformParamsInterceptor implements Interceptor {

    @NotNull
    private final HostProvider hostProvider;

    public PlatformParamsInterceptor(@NotNull HostProvider hostProvider) {
        Intrinsics.checkNotNullParameter(hostProvider, "hostProvider");
        this.hostProvider = hostProvider;
    }

    private final FilteredParamsProvider getFilteredParamsProvider() {
        Uri.Builder urlBuilder = this.hostProvider.getUrlBuilder();
        this.hostProvider.getPlatformSpecificParams(urlBuilder);
        Uri uriBuild = urlBuilder.build();
        List listEmptyList = CollectionsKt.emptyList();
        Intrinsics.checkNotNull(uriBuild);
        return new NecessaryParamsProvider(listEmptyList, uriBuild, CollectionsKt.emptyList());
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

    private final Response interceptWithPlatformParams(Interceptor.Chain chain) {
        Request request = chain.request();
        FormBody formBody = getFormBody(request);
        String str = null;
        if (formBody == null) {
            return null;
        }
        String str2 = FormBodyExtensionsKt.get(formBody, "email");
        if (str2 != null && !StringsKt.isBlank(str2)) {
            str = str2;
        }
        FilteredParamsProvider filteredParamsProvider = getFilteredParamsProvider();
        HttpUrl.Builder builderNewBuilder = request.url().newBuilder();
        for (NameValuePair nameValuePair : filteredParamsProvider.provideGetParams(str)) {
            String name = nameValuePair.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            builderNewBuilder.addQueryParameter(name, nameValuePair.getValue());
        }
        FormBody.Builder builderNewBuilder2 = FormBodyExtensionsKt.newBuilder(formBody);
        for (NameValuePair nameValuePair2 : filteredParamsProvider.providePostParams()) {
            String name2 = nameValuePair2.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
            String value = nameValuePair2.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
            builderNewBuilder2.add(name2, value);
        }
        return chain.proceed(request.newBuilder().post(builderNewBuilder2.build()).url(builderNewBuilder.build()).build());
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response responseInterceptWithPlatformParams = interceptWithPlatformParams(chain);
        return responseInterceptWithPlatformParams == null ? chain.proceed(chain.request()) : responseInterceptWithPlatformParams;
    }
}
