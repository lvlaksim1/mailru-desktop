package com.vk.pushme.network;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.vk.pushme.network.AnalyticsApiImpl", f = "AnalyticsApiImpl.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {21, 23}, m = "sendRequest-gIAlu-s", n = {"callbackUrl", "okHttpRequest", "$this$handleCall$iv", "$i$f$handleCall", "callbackUrl", "okHttpRequest", "$this$handleCall$iv", "response$iv", "$i$f$handleCall"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
final class AnalyticsApiImpl$sendRequest$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AnalyticsApiImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AnalyticsApiImpl$sendRequest$1(AnalyticsApiImpl analyticsApiImpl, Continuation<? super AnalyticsApiImpl$sendRequest$1> continuation) {
        super(continuation);
        this.this$0 = analyticsApiImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo12678sendRequestgIAlus = this.this$0.mo12678sendRequestgIAlus(null, this);
        return objMo12678sendRequestgIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo12678sendRequestgIAlus : Result.m13122boximpl(objMo12678sendRequestgIAlus);
    }
}
