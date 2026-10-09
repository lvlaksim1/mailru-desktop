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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.vk.pushme.logic.request.UnsubscribeRequest$execute$deleted$1", f = "UnsubscribeRequest.kt", i = {}, l = {44}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class UnsubscribeRequest$execute$deleted$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final /* synthetic */ Set<String> $accounts;
    int label;
    final /* synthetic */ UnsubscribeRequest this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UnsubscribeRequest$execute$deleted$1(UnsubscribeRequest unsubscribeRequest, Set<String> set, Continuation<? super UnsubscribeRequest$execute$deleted$1> continuation) {
        super(2, continuation);
        this.this$0 = unsubscribeRequest;
        this.$accounts = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UnsubscribeRequest$execute$deleted$1(this.this$0, this.$accounts, continuation);
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
        Set<String> set = this.$accounts;
        this.label = 1;
        Object objDeleteSubscriptionsFromDatabase = unsubscribeRequest.deleteSubscriptionsFromDatabase(set, this);
        return objDeleteSubscriptionsFromDatabase == coroutine_suspended ? coroutine_suspended : objDeleteSubscriptionsFromDatabase;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((UnsubscribeRequest$execute$deleted$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
