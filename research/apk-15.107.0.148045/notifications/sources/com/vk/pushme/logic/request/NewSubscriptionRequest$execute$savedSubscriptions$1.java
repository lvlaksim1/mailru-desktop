package com.vk.pushme.logic.request;

import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.logic.Subscription;
import com.vk.pushme.mapper.EntityMapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/vk/pushme/logic/Subscription;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.vk.pushme.logic.request.NewSubscriptionRequest$execute$savedSubscriptions$1", f = "NewSubscriptionRequest.kt", i = {}, l = {48}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nNewSubscriptionRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NewSubscriptionRequest.kt\ncom/vk/pushme/logic/request/NewSubscriptionRequest$execute$savedSubscriptions$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,127:1\n1563#2:128\n1634#2,3:129\n*S KotlinDebug\n*F\n+ 1 NewSubscriptionRequest.kt\ncom/vk/pushme/logic/request/NewSubscriptionRequest$execute$savedSubscriptions$1\n*L\n49#1:128\n49#1:129,3\n*E\n"})
final class NewSubscriptionRequest$execute$savedSubscriptions$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Subscription>>, Object> {
    int label;
    final /* synthetic */ NewSubscriptionRequest this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NewSubscriptionRequest$execute$savedSubscriptions$1(NewSubscriptionRequest newSubscriptionRequest, Continuation<? super NewSubscriptionRequest$execute$savedSubscriptions$1> continuation) {
        super(2, continuation);
        this.this$0 = newSubscriptionRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NewSubscriptionRequest$execute$savedSubscriptions$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Subscription>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Subscription>>) continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i10 = this.label;
        if (i10 == 0) {
            ResultKt.throwOnFailure(obj);
            SubscriptionDao subscriptionDao = this.this$0.subscriptionDao;
            String str = this.this$0.application;
            this.label = 1;
            obj = subscriptionDao.getAllForApplication(str, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Iterable iterable = (Iterable) obj;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(EntityMapper.INSTANCE.toDomainModel((com.vk.pushme.database.entity.Subscription) it.next()));
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Subscription>> continuation) {
        return ((NewSubscriptionRequest$execute$savedSubscriptions$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
