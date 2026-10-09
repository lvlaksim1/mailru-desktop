package com.vk.pushme.network.util;

import com.google.android.gms.ads.RequestConfiguration;
import com.vk.pushme.network.PushMeRequestException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Call;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00020\u0005H\u0080H¢\u0006\u0002\u0010\u0007\u001a\u0012\u0010\b\u001a\u00020\t*\u00020\u0003H\u0080@¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"handleCall", "Lkotlin/Result;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Lokhttp3/Call;", "parseSuccess", "Lkotlin/Function1;", "", "(Lokhttp3/Call;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "await", "Lokhttp3/Response;", "(Lokhttp3/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "push-me-network_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCallHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CallHandler.kt\ncom/vk/pushme/network/util/CallHandlerKt\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,65:1\n426#2,11:66\n*S KotlinDebug\n*F\n+ 1 CallHandler.kt\ncom/vk/pushme/network/util/CallHandlerKt\n*L\n36#1:66,11\n*E\n"})
public final class CallHandlerKt {

    /* JADX INFO: renamed from: com.vk.pushme.network.util.CallHandlerKt$handleCall$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 176)
    @DebugMetadata(c = "com.vk.pushme.network.util.CallHandlerKt", f = "CallHandler.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {20, 22}, m = "handleCall", n = {"$this$handleCall", "parseSuccess", "$i$f$handleCall", "$this$handleCall", "parseSuccess", "response", "$i$f$handleCall"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 1)
    @SourceDebugExtension({"SMAP\nCallHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CallHandler.kt\ncom/vk/pushme/network/util/CallHandlerKt$handleCall$1\n*L\n1#1,65:1\n*E\n"})
    static final class AnonymousClass1<T> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            Object objHandleCall = CallHandlerKt.handleCall(null, null, this);
            return objHandleCall == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objHandleCall : Result.m13122boximpl(objHandleCall);
        }
    }

    @Nullable
    public static final Object await(@NotNull Call call, @NotNull Continuation<? super Response> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        ContinuationCallback continuationCallback = new ContinuationCallback(call, cancellableContinuationImpl);
        call.enqueue(continuationCallback);
        cancellableContinuationImpl.invokeOnCancellation(continuationCallback);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Nullable
    public static final <T> Object handleCall(@NotNull Call call, @NotNull Function1<? super String, ? extends T> function1, @NotNull Continuation<? super Result<? extends T>> continuation) {
        AnonymousClass1 anonymousClass1;
        int i10;
        Object objAwait;
        Function1<? super String, ? extends T> function2;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i11 = anonymousClass1.label;
            if ((i11 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i11 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objWithContext = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i12 = anonymousClass1.label;
        try {
            if (i12 == 0) {
                ResultKt.throwOnFailure(objWithContext);
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(call);
                anonymousClass1.L$1 = function1;
                i10 = 0;
                anonymousClass1.I$0 = 0;
                anonymousClass1.label = 1;
                objAwait = await(call, anonymousClass1);
                if (objAwait == coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i12 == 1) {
                int i13 = anonymousClass1.I$0;
                function1 = (Function1) anonymousClass1.L$1;
                Call call2 = (Call) anonymousClass1.L$0;
                ResultKt.throwOnFailure(objWithContext);
                i10 = i13;
                call = call2;
                objAwait = objWithContext;
            } else {
                if (i12 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                function2 = (Function1) anonymousClass1.L$1;
                ResultKt.throwOnFailure(objWithContext);
            }
            return Result.m13123constructorimpl(function2.invoke((String) objWithContext));
            Response response = (Response) objAwait;
            if (!response.isSuccessful()) {
                PushMeRequestException pushMeRequestException = new PushMeRequestException(response.message(), response.code());
                Result.Companion companion = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(pushMeRequestException));
            }
            CoroutineDispatcher io2 = Dispatchers.getIO();
            CallHandlerKt$handleCall$result$responseData$1 callHandlerKt$handleCall$result$responseData$1 = new CallHandlerKt$handleCall$result$responseData$1(response, null);
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(call);
            anonymousClass1.L$1 = function1;
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(response);
            anonymousClass1.I$0 = i10;
            anonymousClass1.label = 2;
            objWithContext = BuildersKt.withContext(io2, callHandlerKt$handleCall$result$responseData$1, anonymousClass1);
            if (objWithContext != coroutine_suspended) {
                function2 = function1;
                return Result.m13123constructorimpl(function2.invoke((String) objWithContext));
            }
            return coroutine_suspended;
        } catch (Exception e10) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m13123constructorimpl(ResultKt.createFailure(e10));
        }
    }

    private static final <T> Object handleCall$$forInline(Call call, Function1<? super String, ? extends T> function1, Continuation<? super Result<? extends T>> continuation) {
        try {
            InlineMarker.mark(0);
            Object objAwait = await(call, continuation);
            InlineMarker.mark(1);
            Response response = (Response) objAwait;
            if (!response.isSuccessful()) {
                PushMeRequestException pushMeRequestException = new PushMeRequestException(response.message(), response.code());
                Result.Companion companion = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(pushMeRequestException));
            }
            CoroutineDispatcher io2 = Dispatchers.getIO();
            CallHandlerKt$handleCall$result$responseData$1 callHandlerKt$handleCall$result$responseData$1 = new CallHandlerKt$handleCall$result$responseData$1(response, null);
            InlineMarker.mark(0);
            Object objWithContext = BuildersKt.withContext(io2, callHandlerKt$handleCall$result$responseData$1, continuation);
            InlineMarker.mark(1);
            return Result.m13123constructorimpl(function1.invoke((String) objWithContext));
        } catch (Exception e10) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m13123constructorimpl(ResultKt.createFailure(e10));
        }
    }
}
