package com.vk.pushme.logic.request;

import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.PendingActionDao;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.database.entity.Subscription;
import com.vk.pushme.logic.usecase.SubscriptionUseCase;
import com.vk.pushme.mapper.EntityMapper;
import com.vk.pushme.model.Request;
import com.vk.pushme.model.SubscriptionSettingsBuilder;
import com.vk.pushme.model.result.EditSubscriptionResult;
import com.vk.pushme.util.Utils;
import com.vk.pushme.work.util.WorkScheduler;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BW\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0002H\u0016J,\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001bH\u0002J\b\u0010\u001f\u001a\u00020 H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/vk/pushme/logic/request/EditSubscriptionRequest;", "Lcom/vk/pushme/model/Request;", "Lcom/vk/pushme/model/result/EditSubscriptionResult;", "workScheduler", "Lcom/vk/pushme/work/util/WorkScheduler;", "account", "", "application", "builder", "Lcom/vk/pushme/model/SubscriptionSettingsBuilder;", "appendSdkDeviceId", "", "subscriptionUseCase", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;", "subscriptionDao", "Lcom/vk/pushme/database/dao/SubscriptionDao;", "pendingActionDao", "Lcom/vk/pushme/database/dao/PendingActionDao;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "logger", "Lcom/vk/pushme/common/Logger;", "<init>", "(Lcom/vk/pushme/work/util/WorkScheduler;Ljava/lang/String;Ljava/lang/String;Lcom/vk/pushme/model/SubscriptionSettingsBuilder;ZLcom/vk/pushme/logic/usecase/SubscriptionUseCase;Lcom/vk/pushme/database/dao/SubscriptionDao;Lcom/vk/pushme/database/dao/PendingActionDao;Lkotlinx/coroutines/CoroutineScope;Lcom/vk/pushme/common/Logger;)V", "execute", "replaceSubscription", "", "Lcom/vk/pushme/logic/Subscription;", "subscriptions", "old", "new", "enqueue", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEditSubscriptionRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EditSubscriptionRequest.kt\ncom/vk/pushme/logic/request/EditSubscriptionRequest\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,164:1\n1#2:165\n*E\n"})
public final class EditSubscriptionRequest extends Request<EditSubscriptionResult> {

    @NotNull
    private final String account;
    private final boolean appendSdkDeviceId;

    @NotNull
    private final String application;

    @NotNull
    private final SubscriptionSettingsBuilder builder;

    @NotNull
    private final CoroutineScope coroutineScope;

    @NotNull
    private final Logger logger;

    @NotNull
    private final PendingActionDao pendingActionDao;

    @NotNull
    private final SubscriptionDao subscriptionDao;

    @NotNull
    private final SubscriptionUseCase subscriptionUseCase;

    @NotNull
    private final WorkScheduler workScheduler;

    /* JADX INFO: renamed from: com.vk.pushme.logic.request.EditSubscriptionRequest$enqueue$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.request.EditSubscriptionRequest$enqueue$1", f = "EditSubscriptionRequest.kt", i = {1, 1, 2, 2, 2}, l = {132, 148, 151}, m = "invokeSuspend", n = {"subscription", "updatedSubscription", "subscription", "updatedSubscription", "pendingAction"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EditSubscriptionRequest.this.new AnonymousClass1(continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:37:0x0124, code lost:
        
            if (r6.insert(r7, r9) == r0) goto L38;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 340
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.logic.request.EditSubscriptionRequest.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.logic.request.EditSubscriptionRequest$execute$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.request.EditSubscriptionRequest$execute$1", f = "EditSubscriptionRequest.kt", i = {}, l = {81}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C10741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Subscription $updatedEntity;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C10741(Subscription subscription, Continuation<? super C10741> continuation) {
            super(2, continuation);
            this.$updatedEntity = subscription;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EditSubscriptionRequest.this.new C10741(this.$updatedEntity, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                SubscriptionDao subscriptionDao = EditSubscriptionRequest.this.subscriptionDao;
                Subscription subscription = this.$updatedEntity;
                this.label = 1;
                if (subscriptionDao.insert(subscription, this) == coroutine_suspended) {
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
            return ((C10741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.logic.request.EditSubscriptionRequest$execute$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.request.EditSubscriptionRequest$execute$2", f = "EditSubscriptionRequest.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Subscription $updatedEntity;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Subscription subscription, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$updatedEntity = subscription;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return EditSubscriptionRequest.this.new AnonymousClass2(this.$updatedEntity, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                SubscriptionDao subscriptionDao = EditSubscriptionRequest.this.subscriptionDao;
                Subscription subscription = this.$updatedEntity;
                this.label = 1;
                if (subscriptionDao.insert(subscription, this) == coroutine_suspended) {
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

    public EditSubscriptionRequest(@NotNull WorkScheduler workScheduler, @NotNull String account, @NotNull String application, @NotNull SubscriptionSettingsBuilder builder, boolean z10, @NotNull SubscriptionUseCase subscriptionUseCase, @NotNull SubscriptionDao subscriptionDao, @NotNull PendingActionDao pendingActionDao, @NotNull CoroutineScope coroutineScope, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(workScheduler, "workScheduler");
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(subscriptionUseCase, "subscriptionUseCase");
        Intrinsics.checkNotNullParameter(subscriptionDao, "subscriptionDao");
        Intrinsics.checkNotNullParameter(pendingActionDao, "pendingActionDao");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.workScheduler = workScheduler;
        this.account = account;
        this.application = application;
        this.builder = builder;
        this.appendSdkDeviceId = z10;
        this.subscriptionUseCase = subscriptionUseCase;
        this.subscriptionDao = subscriptionDao;
        this.pendingActionDao = pendingActionDao;
        this.coroutineScope = coroutineScope;
        this.logger = logger.createLogger("EditSubscriptionRequest");
    }

    private final Collection<com.vk.pushme.logic.Subscription> replaceSubscription(Collection<com.vk.pushme.logic.Subscription> subscriptions, com.vk.pushme.logic.Subscription old, com.vk.pushme.logic.Subscription subscription) {
        ArrayList arrayList = new ArrayList(subscriptions.size());
        for (com.vk.pushme.logic.Subscription subscription2 : subscriptions) {
            if (Intrinsics.areEqual(subscription2, old)) {
                subscription2 = subscription;
            }
            arrayList.add(subscription2);
        }
        return arrayList;
    }

    @Override // com.vk.pushme.model.Request
    public void enqueue() {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vk.pushme.model.Request
    @NotNull
    public EditSubscriptionResult execute() {
        Object next;
        Utils.INSTANCE.checkNotMainThread();
        Logger.info$default(this.logger, "Calling execute for account " + this.account + " and application " + this.application, null, 2, null);
        try {
            List list = (List) BuildersKt__BuildersKt.runBlocking$default(null, new EditSubscriptionRequest$execute$allSubscriptions$1(this, null), 1, null);
            Iterator it = list.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!StringsKt.equals(((com.vk.pushme.logic.Subscription) next).getAccount(), this.account, true));
            com.vk.pushme.logic.Subscription subscription = (com.vk.pushme.logic.Subscription) next;
            if (subscription == null) {
                Logger.warn$default(this.logger, "Unable to find subscription for given parameters", null, 2, null);
                return new EditSubscriptionResult.SubscriptionNotFoundError(this.account, this.application);
            }
            com.vk.pushme.logic.Subscription subscriptionCopyWith$push_me_sdk_release = SubscriptionSettingsBuilder.INSTANCE.copyWith$push_me_sdk_release(subscription, this.builder);
            if (Intrinsics.areEqual(subscriptionCopyWith$push_me_sdk_release, subscription)) {
                Logger.warn$default(this.logger, "No changes detected. Did you apply any changes?", null, 2, null);
                return EditSubscriptionResult.ChangesNotFound.INSTANCE;
            }
            SubscriptionUseCase.Result result = (SubscriptionUseCase.Result) BuildersKt__BuildersKt.runBlocking$default(null, new EditSubscriptionRequest$execute$subscribeResult$1(this, replaceSubscription(list, subscription, subscriptionCopyWith$push_me_sdk_release), null), 1, null);
            if (Intrinsics.areEqual(result, SubscriptionUseCase.Result.OK.INSTANCE)) {
                Logger.info$default(this.logger, "Network request is successful, trying to update subscription in database", null, 2, null);
                BuildersKt__BuildersKt.runBlocking$default(null, new C10741(EntityMapper.INSTANCE.toDatabaseModel(subscriptionCopyWith$push_me_sdk_release), null), 1, null);
                Logger.info$default(this.logger, "Subscription has been updated in database", null, 2, null);
                return EditSubscriptionResult.OK.INSTANCE;
            }
            if (!(result instanceof SubscriptionUseCase.Result.NoAuthError)) {
                if (Intrinsics.areEqual(result, SubscriptionUseCase.Result.MissingPushTokenError.INSTANCE)) {
                    Logger.warn$default(this.logger, "Missing push tokens", null, 2, null);
                    return EditSubscriptionResult.MissingPushTokenError.INSTANCE;
                }
                if (!(result instanceof SubscriptionUseCase.Result.UnknownError)) {
                    throw new NoWhenBranchMatchedException();
                }
                this.logger.warn("Unable to send push settings", ((SubscriptionUseCase.Result.UnknownError) result).getT());
                return new EditSubscriptionResult.UnknownError(((SubscriptionUseCase.Result.UnknownError) result).getT());
            }
            boolean zContains = ((SubscriptionUseCase.Result.NoAuthError) result).getSuccessAccounts().contains(this.account);
            Logger.info$default(this.logger, "No auth error, edit successful: " + zContains, null, 2, null);
            if (!zContains) {
                Logger.error$default(this.logger, "Unable to edit subscription due to auth token error", null, 2, null);
                return EditSubscriptionResult.NoAuthError.INSTANCE;
            }
            BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass2(EntityMapper.INSTANCE.toDatabaseModel(subscriptionCopyWith$push_me_sdk_release), null), 1, null);
            Logger.info$default(this.logger, "Subscription has been updated in database", null, 2, null);
            return EditSubscriptionResult.OK.INSTANCE;
        } catch (Exception e10) {
            this.logger.error("Unable to edit subscription", e10);
            return new EditSubscriptionResult.UnknownError(e10);
        }
    }
}
