package com.vk.pushme.network;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0096@¢\u0006\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/vk/pushme/network/AnalyticsApiImpl;", "Lcom/vk/pushme/network/AnalyticsApi;", "okHttpClient", "Lokhttp3/OkHttpClient;", "<init>", "(Lokhttp3/OkHttpClient;)V", "sendRequest", "Lkotlin/Result;", "", "callbackUrl", "", "sendRequest-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAnalyticsApiImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AnalyticsApiImpl.kt\ncom/vk/pushme/network/AnalyticsApiImpl\n+ 2 CallHandler.kt\ncom/vk/pushme/network/util/CallHandlerKt\n*L\n1#1,19:1\n19#2,14:20\n*S KotlinDebug\n*F\n+ 1 AnalyticsApiImpl.kt\ncom/vk/pushme/network/AnalyticsApiImpl\n*L\n16#1:20,14\n*E\n"})
public final class AnalyticsApiImpl implements AnalyticsApi {

    @NotNull
    private final OkHttpClient okHttpClient;

    public AnalyticsApiImpl(@NotNull OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        this.okHttpClient = okHttpClient;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d0, code lost:
    
        if (r12 == r1) goto L33;
     */
    @Override // com.vk.pushme.network.AnalyticsApi
    @org.jetbrains.annotations.Nullable
    /* JADX INFO: renamed from: sendRequest-gIAlu-s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo12678sendRequestgIAlus(@org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<kotlin.Unit>> r12) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.network.AnalyticsApiImpl.mo12678sendRequestgIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
