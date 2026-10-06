package ru.mail.serverapi.retrofit;

import android.net.Uri;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.network.FormBodyExtensionsKt;
import ru.mail.network.ParamNameValuePair;
import ru.mail.network.PostSignCreator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002¨\u0006\u000b"}, d2 = {"Lru/mail/serverapi/retrofit/PostParamsSignatureInterceptor;", "Lokhttp3/Interceptor;", "<init>", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "appendSignatureToRequest", "Lokhttp3/Request;", Event.Companion.Network.Fail.REQUEST_TAG, "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPostParamsSignatureInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PostParamsSignatureInterceptor.kt\nru/mail/serverapi/retrofit/PostParamsSignatureInterceptor\n+ 2 FormBodyExtensions.kt\nru/mail/network/FormBodyExtensionsKt\n*L\n1#1,43:1\n6#2,4:44\n*S KotlinDebug\n*F\n+ 1 PostParamsSignatureInterceptor.kt\nru/mail/serverapi/retrofit/PostParamsSignatureInterceptor\n*L\n30#1:44,4\n*E\n"})
public final class PostParamsSignatureInterceptor implements Interceptor {
    private final Request appendSignatureToRequest(Request request) {
        if (Intrinsics.areEqual(request.method(), "POST")) {
            RequestBody requestBodyBody = request.body();
            FormBody formBody = requestBodyBody instanceof FormBody ? (FormBody) requestBodyBody : null;
            if (formBody != null) {
                ArrayList arrayList = new ArrayList();
                int size = formBody.size();
                for (int i10 = 0; i10 < size; i10++) {
                    arrayList.add(new ParamNameValuePair(formBody.name(i10), formBody.value(i10)));
                }
                String strCreate = new PostSignCreator(Uri.EMPTY, arrayList).create();
                FormBody.Builder builderNewBuilder = FormBodyExtensionsKt.newBuilder(formBody);
                Intrinsics.checkNotNull(strCreate);
                return request.newBuilder().post(builderNewBuilder.add("md5_post_signature", strCreate).build()).build();
            }
        }
        return request;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(appendSignatureToRequest(chain.request()));
    }
}
