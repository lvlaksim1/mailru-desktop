package com.vk.pushme.logic.request;

import com.vk.pushme.logic.Subscription;
import com.vk.pushme.logic.usecase.SubscriptionUseCase;
import java.util.Collection;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.vk.pushme.logic.request.EditSubscriptionRequest$execute$subscribeResult$1", f = "EditSubscriptionRequest.kt", i = {}, l = {71}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class EditSubscriptionRequest$execute$subscribeResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SubscriptionUseCase.Result>, Object> {
    final /* synthetic */ Collection<Subscription> $updatedSubscriptions;
    int label;
    final /* synthetic */ EditSubscriptionRequest this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    EditSubscriptionRequest$execute$subscribeResult$1(EditSubscriptionRequest editSubscriptionRequest, Collection<Subscription> collection, Continuation<? super EditSubscriptionRequest$execute$subscribeResult$1> continuation) {
        super(2, continuation);
        this.this$0 = editSubscriptionRequest;
        this.$updatedSubscriptions = collection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EditSubscriptionRequest$execute$subscribeResult$1(this.this$0, this.$updatedSubscriptions, continuation);
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
        SubscriptionUseCase subscriptionUseCase = this.this$0.subscriptionUseCase;
        Collection<Subscription> collection = this.$updatedSubscriptions;
        boolean z10 = this.this$0.appendSdkDeviceId;
        this.label = 1;
        Object objInvoke = subscriptionUseCase.invoke(collection, z10, this);
        return objInvoke == coroutine_suspended ? coroutine_suspended : objInvoke;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SubscriptionUseCase.Result> continuation) {
        return ((EditSubscriptionRequest$execute$subscribeResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
