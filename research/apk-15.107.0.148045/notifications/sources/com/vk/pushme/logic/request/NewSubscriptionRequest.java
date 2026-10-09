package com.vk.pushme.logic.request;

import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.PendingActionDao;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.logic.Subscription;
import com.vk.pushme.logic.usecase.SubscriptionUseCase;
import com.vk.pushme.mapper.EntityMapper;
import com.vk.pushme.model.Request;
import com.vk.pushme.model.result.SubscriptionResult;
import com.vk.pushme.util.Utils;
import com.vk.pushme.work.util.WorkScheduler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BM\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0018\u001a\u00020\u0002H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J&\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u001c*\b\u0012\u0004\u0012\u00020\u00070\u001c2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/vk/pushme/logic/request/NewSubscriptionRequest;", "Lcom/vk/pushme/model/Request;", "Lcom/vk/pushme/model/result/SubscriptionResult;", "workScheduler", "Lcom/vk/pushme/work/util/WorkScheduler;", "newSubscriptions", "", "Lcom/vk/pushme/logic/Subscription;", "appendSdkDeviceId", "", "subscriptionUseCase", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;", "subscriptionDao", "Lcom/vk/pushme/database/dao/SubscriptionDao;", "pendingActionDao", "Lcom/vk/pushme/database/dao/PendingActionDao;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "logger", "Lcom/vk/pushme/common/Logger;", "<init>", "(Lcom/vk/pushme/work/util/WorkScheduler;Ljava/util/Collection;ZLcom/vk/pushme/logic/usecase/SubscriptionUseCase;Lcom/vk/pushme/database/dao/SubscriptionDao;Lcom/vk/pushme/database/dao/PendingActionDao;Lkotlinx/coroutines/CoroutineScope;Lcom/vk/pushme/common/Logger;)V", "application", "", "execute", "enqueue", "", "updateOrInsertByAccount", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNewSubscriptionRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NewSubscriptionRequest.kt\ncom/vk/pushme/logic/request/NewSubscriptionRequest\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,127:1\n1#2:128\n1563#3:129\n1634#3,3:130\n1563#3:133\n1634#3,3:134\n774#3:137\n865#3,2:138\n1563#3:140\n1634#3,3:141\n1563#3:144\n1634#3,3:145\n827#3:148\n855#3,2:149\n*S KotlinDebug\n*F\n+ 1 NewSubscriptionRequest.kt\ncom/vk/pushme/logic/request/NewSubscriptionRequest\n*L\n38#1:129\n38#1:130,3\n65#1:133\n65#1:134,3\n74#1:137\n74#1:138,2\n75#1:140\n75#1:141,3\n123#1:144\n123#1:145,3\n124#1:148\n124#1:149,2\n*E\n"})
public final class NewSubscriptionRequest extends Request<SubscriptionResult> {
    private final boolean appendSdkDeviceId;

    @NotNull
    private final String application;

    @NotNull
    private final CoroutineScope coroutineScope;

    @NotNull
    private final Logger logger;

    @NotNull
    private final Collection<Subscription> newSubscriptions;

    @NotNull
    private final PendingActionDao pendingActionDao;

    @NotNull
    private final SubscriptionDao subscriptionDao;

    @NotNull
    private final SubscriptionUseCase subscriptionUseCase;

    @NotNull
    private final WorkScheduler workScheduler;

    /* JADX INFO: renamed from: com.vk.pushme.logic.request.NewSubscriptionRequest$enqueue$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.request.NewSubscriptionRequest$enqueue$1", f = "NewSubscriptionRequest.kt", i = {0, 1, 1}, l = {105, 109}, m = "invokeSuspend", n = {"dbModels", "dbModels", "pendingAction"}, s = {"L$0", "L$0", "L$1"}, v = 1)
    @SourceDebugExtension({"SMAP\nNewSubscriptionRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NewSubscriptionRequest.kt\ncom/vk/pushme/logic/request/NewSubscriptionRequest$enqueue$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,127:1\n1563#2:128\n1634#2,3:129\n*S KotlinDebug\n*F\n+ 1 NewSubscriptionRequest.kt\ncom/vk/pushme/logic/request/NewSubscriptionRequest$enqueue$1\n*L\n104#1:128\n104#1:129,3\n*E\n"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NewSubscriptionRequest.this.new AnonymousClass1(continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x00db, code lost:
        
            if (r2.insert(r8, r7) == r0) goto L26;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                Method dump skipped, instruction units count: 267
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.logic.request.NewSubscriptionRequest.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.logic.request.NewSubscriptionRequest$execute$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.request.NewSubscriptionRequest$execute$1", f = "NewSubscriptionRequest.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C10751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<com.vk.pushme.database.entity.Subscription> $dbModels;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10751(List<com.vk.pushme.database.entity.Subscription> list, Continuation<? super C10751> continuation) {
            super(2, continuation);
            this.$dbModels = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NewSubscriptionRequest.this.new C10751(this.$dbModels, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                SubscriptionDao subscriptionDao = NewSubscriptionRequest.this.subscriptionDao;
                List<com.vk.pushme.database.entity.Subscription> list = this.$dbModels;
                this.label = 1;
                if (subscriptionDao.insert(list, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C10751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.logic.request.NewSubscriptionRequest$execute$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.request.NewSubscriptionRequest$execute$2", f = "NewSubscriptionRequest.kt", i = {}, l = {76}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<com.vk.pushme.database.entity.Subscription> $dbModels;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(List<com.vk.pushme.database.entity.Subscription> list, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$dbModels = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NewSubscriptionRequest.this.new AnonymousClass2(this.$dbModels, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                SubscriptionDao subscriptionDao = NewSubscriptionRequest.this.subscriptionDao;
                List<com.vk.pushme.database.entity.Subscription> list = this.$dbModels;
                this.label = 1;
                if (subscriptionDao.insert(list, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public NewSubscriptionRequest(@NotNull WorkScheduler workScheduler, @NotNull Collection<Subscription> newSubscriptions, boolean z10, @NotNull SubscriptionUseCase subscriptionUseCase, @NotNull SubscriptionDao subscriptionDao, @NotNull PendingActionDao pendingActionDao, @NotNull CoroutineScope coroutineScope, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(workScheduler, "workScheduler");
        Intrinsics.checkNotNullParameter(newSubscriptions, "newSubscriptions");
        Intrinsics.checkNotNullParameter(subscriptionUseCase, "subscriptionUseCase");
        Intrinsics.checkNotNullParameter(subscriptionDao, "subscriptionDao");
        Intrinsics.checkNotNullParameter(pendingActionDao, "pendingActionDao");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.workScheduler = workScheduler;
        this.newSubscriptions = newSubscriptions;
        this.appendSdkDeviceId = z10;
        this.subscriptionUseCase = subscriptionUseCase;
        this.subscriptionDao = subscriptionDao;
        this.pendingActionDao = pendingActionDao;
        this.coroutineScope = coroutineScope;
        this.logger = logger.createLogger("NewSubscriptionRequest");
        if (newSubscriptions.isEmpty()) {
            throw new IllegalArgumentException("New subscriptions are empty");
        }
        Collection<Subscription> collection = newSubscriptions;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(((Subscription) it.next()).getApplication());
        }
        if (CollectionsKt.toSet(arrayList).size() != 1) {
            throw new IllegalArgumentException("You must create new class instance for another applications");
        }
        this.application = ((Subscription) CollectionsKt.first(this.newSubscriptions)).getApplication();
    }

    private final List<Subscription> updateOrInsertByAccount(List<Subscription> list, Collection<Subscription> collection) {
        Collection<Subscription> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            String lowerCase = ((Subscription) it.next()).getAccount().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            arrayList.add(lowerCase);
        }
        Set set = CollectionsKt.toSet(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list) {
            if (!set.contains(((Subscription) obj).getAccount())) {
                arrayList2.add(obj);
            }
        }
        return CollectionsKt.plus((Collection) arrayList2, (Iterable) collection2);
    }

    @Override // com.vk.pushme.model.Request
    public void enqueue() {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vk.pushme.model.Request
    @NotNull
    public SubscriptionResult execute() {
        Utils.INSTANCE.checkNotMainThread();
        Logger.info$default(this.logger, "Calling execute, new subscriptions count: " + this.newSubscriptions.size(), null, 2, null);
        List<Subscription> listUpdateOrInsertByAccount = updateOrInsertByAccount((List) BuildersKt__BuildersKt.runBlocking$default(null, new NewSubscriptionRequest$execute$savedSubscriptions$1(this, null), 1, null), this.newSubscriptions);
        try {
            SubscriptionUseCase.Result result = (SubscriptionUseCase.Result) BuildersKt__BuildersKt.runBlocking$default(null, new NewSubscriptionRequest$execute$subscribeResult$1(this, listUpdateOrInsertByAccount, null), 1, null);
            if (Intrinsics.areEqual(result, SubscriptionUseCase.Result.OK.INSTANCE)) {
                Logger.info$default(this.logger, "Network request is successful", null, 2, null);
                List<Subscription> list = listUpdateOrInsertByAccount;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(EntityMapper.INSTANCE.toDatabaseModel((Subscription) it.next()));
                }
                BuildersKt__BuildersKt.runBlocking$default(null, new C10751(arrayList, null), 1, null);
                Logger.info$default(this.logger, "Subscriptions inserted (updated) in database: " + arrayList.size(), null, 2, null);
                return SubscriptionResult.OK.INSTANCE;
            }
            if (!(result instanceof SubscriptionUseCase.Result.NoAuthError)) {
                if (Intrinsics.areEqual(result, SubscriptionUseCase.Result.MissingPushTokenError.INSTANCE)) {
                    Logger.warn$default(this.logger, "Missing push tokens", null, 2, null);
                    return SubscriptionResult.MissingPushTokenError.INSTANCE;
                }
                if (!(result instanceof SubscriptionUseCase.Result.UnknownError)) {
                    throw new NoWhenBranchMatchedException();
                }
                this.logger.warn("Unable to send push settings", ((SubscriptionUseCase.Result.UnknownError) result).getT());
                return new SubscriptionResult.UnknownError(((SubscriptionUseCase.Result.UnknownError) result).getT());
            }
            Logger.info$default(this.logger, "Some accounts (" + ((SubscriptionUseCase.Result.NoAuthError) result).getFailedAccounts().size() + ") are invalid", null, 2, null);
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : listUpdateOrInsertByAccount) {
                if (((SubscriptionUseCase.Result.NoAuthError) result).getSuccessAccounts().contains(((Subscription) obj).getAccount())) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayList3.add(EntityMapper.INSTANCE.toDatabaseModel((Subscription) it2.next()));
            }
            BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass2(arrayList3, null), 1, null);
            Logger.info$default(this.logger, "Subscriptions inserted (updated) in database: " + arrayList3.size(), null, 2, null);
            return new SubscriptionResult.NoAuthError(((SubscriptionUseCase.Result.NoAuthError) result).getFailedAccounts(), ((SubscriptionUseCase.Result.NoAuthError) result).getSuccessAccounts());
        } catch (Exception e10) {
            this.logger.error("Unable to process request", e10);
            return new SubscriptionResult.UnknownError(e10);
        }
    }
}
