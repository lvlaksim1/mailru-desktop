package com.vk.pushme.logic.request;

import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.vk.pushme.logic.request.UnsubscribeRequest$execute$accounts$1", f = "UnsubscribeRequest.kt", i = {}, l = {36}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class UnsubscribeRequest$execute$accounts$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Set<? extends String>>, Object> {
    int label;
    final /* synthetic */ UnsubscribeRequest this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UnsubscribeRequest$execute$accounts$1(UnsubscribeRequest unsubscribeRequest, Continuation<? super UnsubscribeRequest$execute$accounts$1> continuation) {
        super(2, continuation);
        this.this$0 = unsubscribeRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UnsubscribeRequest$execute$accounts$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Set<? extends String>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Set<String>>) continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        UnsubscribeRequest unsubscribeRequest = this.this$0;
        this.label = 1;
        Object accountsForUnsubscribe = unsubscribeRequest.getAccountsForUnsubscribe(this);
        return accountsForUnsubscribe == coroutine_suspended ? coroutine_suspended : accountsForUnsubscribe;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Set<String>> continuation) {
        return ((UnsubscribeRequest$execute$accounts$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
