package com.vk.superapp.api;

import com.vk.superapp.api.BaseLoader;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J#\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/vk/superapp/api/BaseLoader;", "", "Lkotlin/Function0;", "Lokhttp3/OkHttpClient;", "okhttpClientProvider", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "", "url", "token", "Lio/reactivex/rxjava3/core/Observable;", "", "loadWithToken", "(Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "load", "(Ljava/lang/String;)Lio/reactivex/rxjava3/core/Observable;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BaseLoader {

    @NotNull
    private final Function0<OkHttpClient> ipakvmoca;

    /* JADX WARN: Multi-variable type inference failed */
    public BaseLoader(@NotNull Function0<? extends OkHttpClient> okhttpClientProvider) {
        Intrinsics.checkNotNullParameter(okhttpClientProvider, "okhttpClientProvider");
        this.ipakvmoca = okhttpClientProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] ipakvmoca(String str, String str2, BaseLoader baseLoader) {
        ResponseBody responseBodyBody = null;
        try {
            responseBodyBody = baseLoader.ipakvmoca.invoke().newCall(new Request.Builder().url(str2).post(new FormBody.Builder(null, 1, null).add("access_token", str).build()).build()).execute().body();
            Intrinsics.checkNotNull(responseBodyBody);
            byte[] bArrBytes = responseBodyBody.bytes();
            responseBodyBody.close();
            return bArrBytes;
        } catch (Throwable th2) {
            if (responseBodyBody != null) {
                responseBodyBody.close();
            }
            throw th2;
        }
    }

    @NotNull
    public final Observable<byte[]> load(@NotNull final String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        Observable<byte[]> observableObserveOn = Observable.fromCallable(new Callable() { // from class: r5.a
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BaseLoader.ipakvmoca(url, this);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "observeOn(...)");
        return observableObserveOn;
    }

    @NotNull
    public final Observable<byte[]> loadWithToken(@NotNull final String url, @NotNull final String token) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(token, "token");
        Observable<byte[]> observableObserveOn = Observable.fromCallable(new Callable() { // from class: r5.b
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BaseLoader.ipakvmoca(token, url, this);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "observeOn(...)");
        return observableObserveOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] ipakvmoca(String str, BaseLoader baseLoader) {
        ResponseBody responseBodyBody = null;
        try {
            responseBodyBody = baseLoader.ipakvmoca.invoke().newCall(new Request.Builder().url(str).build()).execute().body();
            Intrinsics.checkNotNull(responseBodyBody);
            byte[] bArrBytes = responseBodyBody.bytes();
            responseBodyBody.close();
            return bArrBytes;
        } catch (Throwable th2) {
            if (responseBodyBody != null) {
                responseBodyBody.close();
            }
            throw th2;
        }
    }
}
