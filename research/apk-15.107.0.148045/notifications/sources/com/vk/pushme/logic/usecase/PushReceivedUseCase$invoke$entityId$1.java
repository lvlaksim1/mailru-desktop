package com.vk.pushme.logic.usecase;

import com.vk.pushme.database.dao.PushDao;
import com.vk.pushme.database.entity.Push;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.vk.pushme.logic.usecase.PushReceivedUseCase$invoke$entityId$1", f = "PushReceivedUseCase.kt", i = {}, l = {36}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class PushReceivedUseCase$invoke$entityId$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Long>, Object> {
    final /* synthetic */ Push $push;
    int label;
    final /* synthetic */ PushReceivedUseCase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PushReceivedUseCase$invoke$entityId$1(PushReceivedUseCase pushReceivedUseCase, Push push, Continuation<? super PushReceivedUseCase$invoke$entityId$1> continuation) {
        super(2, continuation);
        this.this$0 = pushReceivedUseCase;
        this.$push = push;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PushReceivedUseCase$invoke$entityId$1(this.this$0, this.$push, continuation);
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
        PushDao pushDao = this.this$0.pushDao;
        Push push = this.$push;
        this.label = 1;
        Object objInsert = pushDao.insert(push, this);
        return objInsert == coroutine_suspended ? coroutine_suspended : objInsert;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Long> continuation) {
        return ((PushReceivedUseCase$invoke$entityId$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
