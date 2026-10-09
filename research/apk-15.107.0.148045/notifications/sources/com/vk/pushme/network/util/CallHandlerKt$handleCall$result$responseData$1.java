package com.vk.pushme.network.util;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 176)
@DebugMetadata(c = "com.vk.pushme.network.util.CallHandlerKt$handleCall$result$responseData$1", f = "CallHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nCallHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CallHandler.kt\ncom/vk/pushme/network/util/CallHandlerKt$handleCall$result$responseData$1\n*L\n1#1,65:1\n*E\n"})
public final class CallHandlerKt$handleCall$result$responseData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ Response $response;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallHandlerKt$handleCall$result$responseData$1(Response response, Continuation<? super CallHandlerKt$handleCall$result$responseData$1> continuation) {
        super(2, continuation);
        this.$response = response;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CallHandlerKt$handleCall$result$responseData$1(this.$response, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String strString;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ResponseBody responseBodyBody = this.$response.body();
        return (responseBodyBody == null || (strString = responseBodyBody.string()) == null) ? "" : strString;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((CallHandlerKt$handleCall$result$responseData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
