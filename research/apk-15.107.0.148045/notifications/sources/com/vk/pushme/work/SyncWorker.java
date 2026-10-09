package com.vk.pushme.work;

import android.content.Context;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.CoroutineWorker;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkerParameters;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.logic.Subscription;
import com.vk.pushme.mapper.EntityMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u000e\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0082@¢\u0006\u0002\u0010\u0010R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/vk/pushme/work/SyncWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "logger", "Lcom/vk/pushme/common/Logger;", "getLogger", "()Lcom/vk/pushme/common/Logger;", "logger$delegate", "Lkotlin/Lazy;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldRetryOnError", "", "getShouldRetryExtra", "getAllSubscriptions", "", "Lcom/vk/pushme/logic/Subscription;", "Companion", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSyncWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SyncWorker.kt\ncom/vk/pushme/work/SyncWorker\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,143:1\n1617#2,9:144\n1869#2:153\n1870#2:155\n1626#2:156\n1#3:154\n*S KotlinDebug\n*F\n+ 1 SyncWorker.kt\ncom/vk/pushme/work/SyncWorker\n*L\n79#1:144,9\n79#1:153\n79#1:155\n79#1:156\n79#1:154\n*E\n"})
public final class SyncWorker extends CoroutineWorker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private static final int MAX_ATTEMPTS_COUNT = 10;

    @NotNull
    public static final String PERIODIC_UNIQUE_WORK_ID = "PushMeSDK_SyncWorker_Periodic";

    @NotNull
    private static final String SHOULD_RETRY_EXTRA = "should_retry_extra";

    @NotNull
    public static final String UNIQUE_WORK_ID = "PushMeSDK_SyncWorker";

    /* JADX INFO: renamed from: logger$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy logger;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/vk/pushme/work/SyncWorker$Companion;", "", "<init>", "()V", "UNIQUE_WORK_ID", "", "PERIODIC_UNIQUE_WORK_ID", "MAX_ATTEMPTS_COUNT", "", "SHOULD_RETRY_EXTRA", "buildOneTimeWorkRequest", "Landroidx/work/OneTimeWorkRequest;", "shouldRetry", "", "skipConnectionCheckByGoogle", "initialDelayInSeconds", "", "buildPeriodicWorkRequest", "Landroidx/work/PeriodicWorkRequest;", "intervalInMinutes", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nSyncWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SyncWorker.kt\ncom/vk/pushme/work/SyncWorker$Companion\n+ 2 OneTimeWorkRequest.kt\nandroidx/work/OneTimeWorkRequestKt\n+ 3 PeriodicWorkRequest.kt\nandroidx/work/PeriodicWorkRequestKt\n*L\n1#1,143:1\n105#2:144\n364#3:145\n*S KotlinDebug\n*F\n+ 1 SyncWorker.kt\ncom/vk/pushme/work/SyncWorker$Companion\n*L\n100#1:144\n124#1:145\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ OneTimeWorkRequest buildOneTimeWorkRequest$default(Companion companion, boolean z10, boolean z11, long j10, int i10, Object obj) {
            if ((i10 & 4) != 0) {
                j10 = 0;
            }
            return companion.buildOneTimeWorkRequest(z10, z11, j10);
        }

        @NotNull
        public final OneTimeWorkRequest buildOneTimeWorkRequest(boolean shouldRetry, boolean skipConnectionCheckByGoogle, long initialDelayInSeconds) {
            return new OneTimeWorkRequest.Builder((Class<? extends ListenableWorker>) SyncWorker.class).setInitialDelay(initialDelayInSeconds, TimeUnit.SECONDS).setConstraints(WorkerExtensionsKt.setRequiredNetworkTypeConnected(new Constraints.Builder(), skipConnectionCheckByGoogle).build()).setInputData(new Data.Builder().putBoolean(SyncWorker.SHOULD_RETRY_EXTRA, shouldRetry).build()).setBackoffCriteria(BackoffPolicy.LINEAR, 3L, TimeUnit.MINUTES).build();
        }

        @NotNull
        public final PeriodicWorkRequest buildPeriodicWorkRequest(long intervalInMinutes, boolean skipConnectionCheckByGoogle) {
            return new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) SyncWorker.class, intervalInMinutes, TimeUnit.MINUTES).setInitialDelay(12L, TimeUnit.HOURS).setConstraints(WorkerExtensionsKt.setRequiredNetworkTypeConnected(new Constraints.Builder(), skipConnectionCheckByGoogle).build()).setInputData(new Data.Builder().putBoolean(SyncWorker.SHOULD_RETRY_EXTRA, false).build()).build();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.SyncWorker$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.SyncWorker", f = "SyncWorker.kt", i = {}, l = {30}, m = "doWork", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return SyncWorker.this.doWork(this);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.SyncWorker$doWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\f0\u0001¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0003*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lorg/jspecify/annotations/NonNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.SyncWorker$doWork$2", f = "SyncWorker.kt", i = {1}, l = {38, 50}, m = "invokeSuspend", n = {"subscriptions"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SyncWorker.this.new AnonymousClass2(continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x00e0, code lost:
        
            if (r8 == r0) goto L31;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                Method dump skipped, instruction units count: 305
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.work.SyncWorker.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.SyncWorker$getAllSubscriptions$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.SyncWorker", f = "SyncWorker.kt", i = {0}, l = {79}, m = "getAllSubscriptions", n = {"dao"}, s = {"L$0"}, v = 1)
    static final class C10841 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10841(Continuation<? super C10841> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SyncWorker.this.getAllSubscriptions(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SyncWorker(@NotNull Context appContext, @NotNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.logger = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.work.g
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncWorker.logger_delegate$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object getAllSubscriptions(Continuation<? super Collection<Subscription>> continuation) {
        C10841 c10841;
        Subscription domainModel;
        if (continuation instanceof C10841) {
            c10841 = (C10841) continuation;
            int i10 = c10841.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c10841.label = i10 - Integer.MIN_VALUE;
            } else {
                c10841 = new C10841(continuation);
            }
        } else {
            c10841 = new C10841(continuation);
        }
        Object all = c10841.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c10841.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(all);
            SubscriptionDao subscriptionDao = PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getDatabase().subscriptionDao();
            c10841.L$0 = SpillingKt.nullOutSpilledVariable(subscriptionDao);
            c10841.label = 1;
            all = subscriptionDao.getAll(c10841);
            if (all == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(all);
        }
        ArrayList arrayList = new ArrayList();
        for (com.vk.pushme.database.entity.Subscription subscription : (Iterable) all) {
            try {
                domainModel = EntityMapper.INSTANCE.toDomainModel(subscription);
            } catch (IllegalArgumentException e10) {
                getLogger().error("Unable to convert subscription with ID " + subscription.getId(), e10);
                domainModel = null;
            }
            if (domainModel != null) {
                arrayList.add(domainModel);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Logger getLogger() {
        return (Logger) this.logger.getValue();
    }

    private final boolean getShouldRetryExtra() {
        return getInputData().getBoolean(SHOULD_RETRY_EXTRA, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Logger logger_delegate$lambda$0() {
        return PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getLogger().createLogger("SyncWorker");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldRetryOnError() {
        return getShouldRetryExtra() && getRunAttemptCount() < 10;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Override // androidx.work.CoroutineWorker
    @Nullable
    public Object doWork(@NotNull Continuation<? super ListenableWorker.Result> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i10 = anonymousClass1.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i10 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objWithContext = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objWithContext);
            CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
            anonymousClass1.label = 1;
            objWithContext = BuildersKt.withContext(completableJobSupervisorJob$default, anonymousClass2, anonymousClass1);
            if (objWithContext == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objWithContext);
        }
        Intrinsics.checkNotNullExpressionValue(objWithContext, "withContext(...)");
        return objWithContext;
    }
}
