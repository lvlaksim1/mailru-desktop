package com.vk.pushme.logic.request;

import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.PendingActionDao;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.database.entity.Subscription;
import com.vk.pushme.logic.usecase.UnsubscribeUseCase;
import com.vk.pushme.model.Request;
import com.vk.pushme.model.result.UnsubscribeResult;
import com.vk.pushme.util.Utils;
import com.vk.pushme.work.util.WorkScheduler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
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
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BI\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0002H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aH\u0082@¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u001aH\u0082@¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/vk/pushme/logic/request/UnsubscribeRequest;", "Lcom/vk/pushme/model/Request;", "Lcom/vk/pushme/model/result/UnsubscribeResult;", "workScheduler", "Lcom/vk/pushme/work/util/WorkScheduler;", "application", "", "accountForUnsubscribe", "unsubscribeUseCase", "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;", "subscriptionDao", "Lcom/vk/pushme/database/dao/SubscriptionDao;", "pendingActionDao", "Lcom/vk/pushme/database/dao/PendingActionDao;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "logger", "Lcom/vk/pushme/common/Logger;", "<init>", "(Lcom/vk/pushme/work/util/WorkScheduler;Ljava/lang/String;Ljava/lang/String;Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;Lcom/vk/pushme/database/dao/SubscriptionDao;Lcom/vk/pushme/database/dao/PendingActionDao;Lkotlinx/coroutines/CoroutineScope;Lcom/vk/pushme/common/Logger;)V", "execute", "enqueue", "", "deleteSubscriptionsFromDatabase", "", "accounts", "", "(Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAccountsForUnsubscribe", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUnsubscribeRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnsubscribeRequest.kt\ncom/vk/pushme/logic/request/UnsubscribeRequest\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,103:1\n1563#2:104\n1634#2,3:105\n*S KotlinDebug\n*F\n+ 1 UnsubscribeRequest.kt\ncom/vk/pushme/logic/request/UnsubscribeRequest\n*L\n97#1:104\n97#1:105,3\n*E\n"})
public final class UnsubscribeRequest extends Request<UnsubscribeResult> {

    @Nullable
    private final String accountForUnsubscribe;

    @NotNull
    private final String application;

    @NotNull
    private final CoroutineScope coroutineScope;

    @NotNull
    private final Logger logger;

    @NotNull
    private final PendingActionDao pendingActionDao;

    @NotNull
    private final SubscriptionDao subscriptionDao;

    @NotNull
    private final UnsubscribeUseCase unsubscribeUseCase;

    @NotNull
    private final WorkScheduler workScheduler;

    /* JADX INFO: renamed from: com.vk.pushme.logic.request.UnsubscribeRequest$enqueue$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.request.UnsubscribeRequest$enqueue$1", f = "UnsubscribeRequest.kt", i = {1, 2, 2, 2}, l = {70, 72, 78}, m = "invokeSuspend", n = {"accounts", "accounts", "pendingAction", "deleted"}, s = {"L$0", "L$0", "L$1", "I$0"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return UnsubscribeRequest.this.new AnonymousClass1(continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x00c3, code lost:
        
            if (r6.insert(r7, r8) == r0) goto L27;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                Method dump skipped, instruction units count: 243
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.logic.request.UnsubscribeRequest.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.logic.request.UnsubscribeRequest$getAccountsForUnsubscribe$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.request.UnsubscribeRequest", f = "UnsubscribeRequest.kt", i = {}, l = {97}, m = "getAccountsForUnsubscribe", n = {}, s = {}, v = 1)
    static final class C10761 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C10761(Continuation<? super C10761> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return UnsubscribeRequest.this.getAccountsForUnsubscribe(this);
        }
    }

    public UnsubscribeRequest(@NotNull WorkScheduler workScheduler, @NotNull String application, @Nullable String str, @NotNull UnsubscribeUseCase unsubscribeUseCase, @NotNull SubscriptionDao subscriptionDao, @NotNull PendingActionDao pendingActionDao, @NotNull CoroutineScope coroutineScope, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(workScheduler, "workScheduler");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(unsubscribeUseCase, "unsubscribeUseCase");
        Intrinsics.checkNotNullParameter(subscriptionDao, "subscriptionDao");
        Intrinsics.checkNotNullParameter(pendingActionDao, "pendingActionDao");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.workScheduler = workScheduler;
        this.application = application;
        this.accountForUnsubscribe = str;
        this.unsubscribeUseCase = unsubscribeUseCase;
        this.subscriptionDao = subscriptionDao;
        this.pendingActionDao = pendingActionDao;
        this.coroutineScope = coroutineScope;
        this.logger = logger.createLogger("UnsubscribeRequest");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object deleteSubscriptionsFromDatabase(Set<String> set, Continuation<? super Integer> continuation) {
        return this.subscriptionDao.deleteForApplicationAndAccounts(this.application, set, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object getAccountsForUnsubscribe(Continuation<? super Set<String>> continuation) {
        C10761 c10761;
        if (continuation instanceof C10761) {
            c10761 = (C10761) continuation;
            int i10 = c10761.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c10761.label = i10 - Integer.MIN_VALUE;
            } else {
                c10761 = new C10761(continuation);
            }
        } else {
            c10761 = new C10761(continuation);
        }
        Object allForApplication = c10761.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c10761.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(allForApplication);
            String str = this.accountForUnsubscribe;
            if (str != null && !StringsKt.isBlank(str)) {
                return SetsKt.setOf(this.accountForUnsubscribe);
            }
            SubscriptionDao subscriptionDao = this.subscriptionDao;
            String str2 = this.application;
            c10761.label = 1;
            allForApplication = subscriptionDao.getAllForApplication(str2, c10761);
            if (allForApplication == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(allForApplication);
        }
        Iterable iterable = (Iterable) allForApplication;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((Subscription) it.next()).getAccount());
        }
        return CollectionsKt.toSet(arrayList);
    }

    @Override // com.vk.pushme.model.Request
    public void enqueue() {
        Logger.info$default(this.logger, "Calling enqueue for application " + this.application + ", account = " + this.accountForUnsubscribe, null, 2, null);
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vk.pushme.model.Request
    @NotNull
    public UnsubscribeResult execute() {
        Utils.INSTANCE.checkNotMainThread();
        Logger.info$default(this.logger, "Calling execute for application " + this.application + ", account = " + this.accountForUnsubscribe, null, 2, null);
        try {
            Set set = (Set) BuildersKt__BuildersKt.runBlocking$default(null, new UnsubscribeRequest$execute$accounts$1(this, null), 1, null);
            Logger.info$default(this.logger, "Found " + set.size() + " for unsubscribe", null, 2, null);
            UnsubscribeUseCase.Result result = (UnsubscribeUseCase.Result) BuildersKt__BuildersKt.runBlocking$default(null, new UnsubscribeRequest$execute$networkResult$1(this, set, null), 1, null);
            if (!Intrinsics.areEqual(result, UnsubscribeUseCase.Result.OK.INSTANCE)) {
                if (Intrinsics.areEqual(result, UnsubscribeUseCase.Result.MissingDeviceIdError.INSTANCE)) {
                    Logger.error$default(this.logger, "Unable to get device ID", null, 2, null);
                    return new UnsubscribeResult.UnknownError(new IllegalStateException("Unable to get device ID"));
                }
                if (!(result instanceof UnsubscribeUseCase.Result.UnknownError)) {
                    throw new NoWhenBranchMatchedException();
                }
                this.logger.error("Failed to unsubscribe", ((UnsubscribeUseCase.Result.UnknownError) result).getT());
                return new UnsubscribeResult.UnknownError(((UnsubscribeUseCase.Result.UnknownError) result).getT());
            }
            Logger.info$default(this.logger, "Unsubscribe from server successfully completed", null, 2, null);
            int iIntValue = ((Number) BuildersKt__BuildersKt.runBlocking$default(null, new UnsubscribeRequest$execute$deleted$1(this, set, null), 1, null)).intValue();
            Logger.info$default(this.logger, iIntValue + " local entities have been deleted", null, 2, null);
            return UnsubscribeResult.OK.INSTANCE;
        } catch (Exception e10) {
            this.logger.error("Unable to unsubscribe due to exception", e10);
            return new UnsubscribeResult.UnknownError(e10);
        }
    }
}
