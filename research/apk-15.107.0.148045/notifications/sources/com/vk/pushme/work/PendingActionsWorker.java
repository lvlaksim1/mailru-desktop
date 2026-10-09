package com.vk.pushme.work;

import android.content.Context;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkerParameters;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.PendingActionDao;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.database.entity.PendingAction;
import com.vk.pushme.logic.Subscription;
import com.vk.pushme.logic.usecase.SubscriptionUseCase;
import com.vk.pushme.logic.usecase.UnsubscribeUseCase;
import com.vk.pushme.mapper.EntityMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 -2\u00020\u0001:\u0002,-B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0018\u001a\u00020\u0019H\u0096@¢\u0006\u0002\u0010\u001aJ\u001c\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eH\u0082@¢\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001fH\u0082@¢\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020&H\u0082@¢\u0006\u0002\u0010'J\u0016\u0010(\u001a\u00020\"2\u0006\u0010#\u001a\u00020)H\u0082@¢\u0006\u0002\u0010*J\b\u0010+\u001a\u00020\"H\u0002R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\r\u001a\u0004\b\u0015\u0010\u0016¨\u0006."}, d2 = {"Lcom/vk/pushme/work/PendingActionsWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "logger", "Lcom/vk/pushme/common/Logger;", "getLogger", "()Lcom/vk/pushme/common/Logger;", "logger$delegate", "Lkotlin/Lazy;", "subscriptionDao", "Lcom/vk/pushme/database/dao/SubscriptionDao;", "getSubscriptionDao", "()Lcom/vk/pushme/database/dao/SubscriptionDao;", "subscriptionDao$delegate", "pendingActionDao", "Lcom/vk/pushme/database/dao/PendingActionDao;", "getPendingActionDao", "()Lcom/vk/pushme/database/dao/PendingActionDao;", "pendingActionDao$delegate", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processBatch", "Lcom/vk/pushme/work/PendingActionsWorker$ProcessBatchResult;", "batch", "", "Lcom/vk/pushme/database/entity/PendingAction;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processAction", "", "action", "(Lcom/vk/pushme/database/entity/PendingAction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processSubscribeAction", "Lcom/vk/pushme/logic/PendingAction$Subscribe;", "(Lcom/vk/pushme/logic/PendingAction$Subscribe;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processUnsubscribeAction", "Lcom/vk/pushme/logic/PendingAction$Unsubscribe;", "(Lcom/vk/pushme/logic/PendingAction$Unsubscribe;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldRetryOnError", "ProcessBatchResult", "Companion", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPendingActionsWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PendingActionsWorker.kt\ncom/vk/pushme/work/PendingActionsWorker\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,176:1\n1617#2,9:177\n1869#2:186\n1870#2:188\n1626#2:189\n1#3:187\n*S KotlinDebug\n*F\n+ 1 PendingActionsWorker.kt\ncom/vk/pushme/work/PendingActionsWorker\n*L\n117#1:177,9\n117#1:186\n117#1:188\n117#1:189\n117#1:187\n*E\n"})
public final class PendingActionsWorker extends CoroutineWorker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private static final int LIMIT = 100;
    private static final int MAX_ATTEMPTS_COUNT = 10;

    @NotNull
    public static final String UNIQUE_WORK_ID = "PushMeSDK_PendingActionsWorker";

    /* JADX INFO: renamed from: logger$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy logger;

    /* JADX INFO: renamed from: pendingActionDao$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pendingActionDao;

    /* JADX INFO: renamed from: subscriptionDao$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy subscriptionDao;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/vk/pushme/work/PendingActionsWorker$Companion;", "", "<init>", "()V", "UNIQUE_WORK_ID", "", "MAX_ATTEMPTS_COUNT", "", "LIMIT", "buildWorkRequest", "Landroidx/work/OneTimeWorkRequest;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPendingActionsWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PendingActionsWorker.kt\ncom/vk/pushme/work/PendingActionsWorker$Companion\n+ 2 OneTimeWorkRequest.kt\nandroidx/work/OneTimeWorkRequestKt\n*L\n1#1,176:1\n105#2:177\n*S KotlinDebug\n*F\n+ 1 PendingActionsWorker.kt\ncom/vk/pushme/work/PendingActionsWorker$Companion\n*L\n160#1:177\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final OneTimeWorkRequest buildWorkRequest() {
            return new OneTimeWorkRequest.Builder((Class<? extends ListenableWorker>) PendingActionsWorker.class).setConstraints(WorkerExtensionsKt.setRequiredNetworkTypeConnected(new Constraints.Builder(), PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getConfig().getSkipConnectionCheckByGoogle()).build()).setInitialDelay(5L, TimeUnit.SECONDS).setBackoffCriteria(BackoffPolicy.LINEAR, 3L, TimeUnit.MINUTES).build();
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/vk/pushme/work/PendingActionsWorker$ProcessBatchResult;", "", "<init>", "()V", "OK", "Error", "Lcom/vk/pushme/work/PendingActionsWorker$ProcessBatchResult$Error;", "Lcom/vk/pushme/work/PendingActionsWorker$ProcessBatchResult$OK;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static abstract class ProcessBatchResult {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/vk/pushme/work/PendingActionsWorker$ProcessBatchResult$Error;", "Lcom/vk/pushme/work/PendingActionsWorker$ProcessBatchResult;", "lastSuccessActionId", "", "<init>", "(Ljava/lang/Long;)V", "getLastSuccessActionId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error extends ProcessBatchResult {

            @Nullable
            private final Long lastSuccessActionId;

            public Error(@Nullable Long l10) {
                super(null);
                this.lastSuccessActionId = l10;
            }

            @Nullable
            public final Long getLastSuccessActionId() {
                return this.lastSuccessActionId;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/vk/pushme/work/PendingActionsWorker$ProcessBatchResult$OK;", "Lcom/vk/pushme/work/PendingActionsWorker$ProcessBatchResult;", "<init>", "()V", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OK extends ProcessBatchResult {

            @NotNull
            public static final OK INSTANCE = new OK();

            private OK() {
                super(null);
            }
        }

        public /* synthetic */ ProcessBatchResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ProcessBatchResult() {
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.PendingActionsWorker$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.PendingActionsWorker", f = "PendingActionsWorker.kt", i = {}, l = {30}, m = "doWork", n = {}, s = {}, v = 1)
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
            return PendingActionsWorker.this.doWork(this);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.PendingActionsWorker$doWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\f0\u0001¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0003*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lorg/jspecify/annotations/NonNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.PendingActionsWorker$doWork$2", f = "PendingActionsWorker.kt", i = {0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3}, l = {43, 47, 56, 60}, m = "invokeSuspend", n = {"offset", "batch", "lastProcessedActionId", "offset", "hasErrors", "batch", "lastProcessedActionId", "result", "offset", "hasErrors", "batch", "lastProcessedActionId", "offset", "hasErrors", "it", "$i$a$-let-PendingActionsWorker$doWork$2$1"}, s = {"I$0", "L$0", "L$1", "I$0", "I$1", "L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1", "J$0", "I$2"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PendingActionsWorker.this.new AnonymousClass2(continuation);
        }

        /* JADX WARN: Code duplicated, block: B:33:0x0106  */
        /* JADX WARN: Code duplicated, block: B:36:0x011c A[PHI: r2 r10 r11 r12 r13
          0x011c: PHI (r2v18 int) = (r2v23 int), (r2v33 int) binds: [B:34:0x0118, B:12:0x004e] A[DONT_GENERATE, DONT_INLINE]
          0x011c: PHI (r10v8 int) = (r10v10 int), (r10v13 int) binds: [B:34:0x0118, B:12:0x004e] A[DONT_GENERATE, DONT_INLINE]
          0x011c: PHI (r11v4 kotlin.jvm.internal.Ref$ObjectRef) = (r11v5 kotlin.jvm.internal.Ref$ObjectRef), (r11v8 kotlin.jvm.internal.Ref$ObjectRef) binds: [B:34:0x0118, B:12:0x004e] A[DONT_GENERATE, DONT_INLINE]
          0x011c: PHI (r12v3 java.util.List) = (r12v7 java.util.List), (r12v11 java.util.List) binds: [B:34:0x0118, B:12:0x004e] A[DONT_GENERATE, DONT_INLINE]
          0x011c: PHI (r13v1 java.lang.Object) = (r13v8 java.lang.Object), (r13v9 java.lang.Object) binds: [B:34:0x0118, B:12:0x004e] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:38:0x0122  */
        /* JADX WARN: Code duplicated, block: B:39:0x014a  */
        /* JADX WARN: Code duplicated, block: B:43:0x0181 A[PHI: r2 r10 r11 r12
          0x0181: PHI (r2v16 int) = (r2v22 int), (r2v23 int) binds: [B:38:0x0122, B:32:0x0104] A[DONT_GENERATE, DONT_INLINE]
          0x0181: PHI (r10v7 int) = (r10v8 int), (r10v10 int) binds: [B:38:0x0122, B:32:0x0104] A[DONT_GENERATE, DONT_INLINE]
          0x0181: PHI (r11v3 kotlin.jvm.internal.Ref$ObjectRef) = (r11v4 kotlin.jvm.internal.Ref$ObjectRef), (r11v5 kotlin.jvm.internal.Ref$ObjectRef) binds: [B:38:0x0122, B:32:0x0104] A[DONT_GENERATE, DONT_INLINE]
          0x0181: PHI (r12v2 java.util.List) = (r12v3 java.util.List), (r12v7 java.util.List) binds: [B:38:0x0122, B:32:0x0104] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:45:0x0187  */
        /* JADX WARN: Code duplicated, block: B:48:0x01d3  */
        /* JADX WARN: Code duplicated, block: B:52:0x01fe  */
        /* JADX WARN: Code duplicated, block: B:55:0x0217  */
        /* JADX WARN: Code duplicated, block: B:57:0x021c  */
        /* JADX WARN: Code duplicated, block: B:59:0x0240  */
        /* JADX WARN: Code duplicated, block: B:61:0x0245  */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x017a, code lost:
        
            if (r12 == r1) goto L47;
         */
        /* JADX WARN: Instruction removed from duplicated block: B:38:0x0122, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:45:0x0187, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:57:0x021c, please report this as an issue */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r14v4, types: [T, java.lang.Long] */
        /* JADX WARN: Type inference failed for: r2v21, types: [T, java.lang.Long] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x017a -> B:42:0x017d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instruction units count: 586
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.work.PendingActionsWorker.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.PendingActionsWorker$processBatch$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.PendingActionsWorker", f = "PendingActionsWorker.kt", i = {0, 0, 0}, l = {78}, m = "processBatch", n = {"batch", "lastSuccessActionId", "action"}, s = {"L$0", "L$1", "L$3"}, v = 1)
    static final class C10801 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C10801(Continuation<? super C10801> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PendingActionsWorker.this.processBatch(null, this);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.PendingActionsWorker$processSubscribeAction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.PendingActionsWorker", f = "PendingActionsWorker.kt", i = {0, 1, 1, 1}, l = {112, 129}, m = "processSubscribeAction", n = {"action", "action", "subscriptions", "mappedSubscriptions"}, s = {"L$0", "L$0", "L$1", "L$2"}, v = 1)
    static final class C10811 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C10811(Continuation<? super C10811> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PendingActionsWorker.this.processSubscribeAction(null, this);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.PendingActionsWorker$processUnsubscribeAction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.PendingActionsWorker", f = "PendingActionsWorker.kt", i = {0}, l = {138}, m = "processUnsubscribeAction", n = {"action"}, s = {"L$0"}, v = 1)
    static final class C10821 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C10821(Continuation<? super C10821> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PendingActionsWorker.this.processUnsubscribeAction(null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PendingActionsWorker(@NotNull Context appContext, @NotNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.logger = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.work.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PendingActionsWorker.logger_delegate$lambda$0();
            }
        });
        this.subscriptionDao = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.work.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PendingActionsWorker.subscriptionDao_delegate$lambda$0();
            }
        });
        this.pendingActionDao = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.work.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PendingActionsWorker.pendingActionDao_delegate$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Logger getLogger() {
        return (Logger) this.logger.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PendingActionDao getPendingActionDao() {
        return (PendingActionDao) this.pendingActionDao.getValue();
    }

    private final SubscriptionDao getSubscriptionDao() {
        return (SubscriptionDao) this.subscriptionDao.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Logger logger_delegate$lambda$0() {
        return PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getLogger().createLogger("PendingActionsWorker");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PendingActionDao pendingActionDao_delegate$lambda$0() {
        return PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getDatabase().pendingActionDao();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processAction(PendingAction pendingAction, Continuation<? super Boolean> continuation) {
        try {
            com.vk.pushme.logic.PendingAction domainModel = EntityMapper.INSTANCE.toDomainModel(pendingAction);
            if (domainModel instanceof com.vk.pushme.logic.PendingAction.Subscribe) {
                return processSubscribeAction((com.vk.pushme.logic.PendingAction.Subscribe) domainModel, continuation);
            }
            if (domainModel instanceof com.vk.pushme.logic.PendingAction.Unsubscribe) {
                return processUnsubscribeAction((com.vk.pushme.logic.PendingAction.Unsubscribe) domainModel, continuation);
            }
            throw new NoWhenBranchMatchedException();
        } catch (Exception e10) {
            getLogger().error("Failed to parse action with type " + pendingAction.getType() + " and data " + pendingAction.getData(), e10);
            return Boxing.boxBoolean(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0073  */
    /* JADX WARN: Code duplicated, block: B:19:0x008d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x008e  */
    /* JADX WARN: Code duplicated, block: B:23:0x009a  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x008e -> B:21:0x0092). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object processBatch(java.util.List<com.vk.pushme.database.entity.PendingAction> r10, kotlin.coroutines.Continuation<? super com.vk.pushme.work.PendingActionsWorker.ProcessBatchResult> r11) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.work.PendingActionsWorker.processBatch(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object processSubscribeAction(com.vk.pushme.logic.PendingAction.Subscribe subscribe, Continuation<? super Boolean> continuation) {
        C10811 c10811;
        PendingActionsWorker pendingActionsWorker;
        com.vk.pushme.logic.PendingAction.Subscribe subscribe2;
        com.vk.pushme.logic.PendingAction.Subscribe subscribe3;
        List list;
        Subscription domainModel;
        if (continuation instanceof C10811) {
            c10811 = (C10811) continuation;
            int i10 = c10811.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c10811.label = i10 - Integer.MIN_VALUE;
                pendingActionsWorker = this;
            } else {
                pendingActionsWorker = this;
                c10811 = pendingActionsWorker.new C10811(continuation);
            }
        } else {
            pendingActionsWorker = this;
            c10811 = pendingActionsWorker.new C10811(continuation);
        }
        Object allForApplication = c10811.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c10811.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(allForApplication);
            SubscriptionDao subscriptionDao = pendingActionsWorker.getSubscriptionDao();
            String application = subscribe.getApplication();
            c10811.L$0 = subscribe;
            c10811.label = 1;
            allForApplication = subscriptionDao.getAllForApplication(application, c10811);
            if (allForApplication != coroutine_suspended) {
                subscribe2 = subscribe;
            }
            return coroutine_suspended;
        }
        if (i11 == 1) {
            subscribe2 = (com.vk.pushme.logic.PendingAction.Subscribe) c10811.L$0;
            ResultKt.throwOnFailure(allForApplication);
        } else {
            if (i11 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            list = (List) c10811.L$1;
            subscribe3 = (com.vk.pushme.logic.PendingAction.Subscribe) c10811.L$0;
            ResultKt.throwOnFailure(allForApplication);
        }
        SubscriptionUseCase.Result result = (SubscriptionUseCase.Result) allForApplication;
        Logger.info$default(pendingActionsWorker.getLogger(), "Subscription result for app " + subscribe3.getApplication() + " and " + list.size() + " accounts: " + result, null, 2, null);
        return Boxing.boxBoolean(Intrinsics.areEqual(result, SubscriptionUseCase.Result.OK.INSTANCE));
        List<com.vk.pushme.database.entity.Subscription> list2 = (List) allForApplication;
        if (list2.isEmpty()) {
            Logger.warn$default(pendingActionsWorker.getLogger(), "Unable to find subscriptions for app " + subscribe2.getApplication(), null, 2, null);
            return Boxing.boxBoolean(true);
        }
        ArrayList arrayList = new ArrayList();
        for (com.vk.pushme.database.entity.Subscription subscription : list2) {
            try {
                domainModel = EntityMapper.INSTANCE.toDomainModel(subscription);
            } catch (IllegalArgumentException e10) {
                pendingActionsWorker.getLogger().error("Unable to convert subscription with ID " + subscription.getId(), e10);
                domainModel = null;
            }
            if (domainModel != null) {
                arrayList.add(domainModel);
            }
        }
        if (arrayList.isEmpty()) {
            Logger.warn$default(pendingActionsWorker.getLogger(), "Mapped subscriptions are empty", null, 2, null);
            return Boxing.boxBoolean(true);
        }
        PushMeSdk.Companion companion = PushMeSdk.INSTANCE;
        SubscriptionUseCase subscriptionUseCase$push_me_sdk_release = companion.getInstance$push_me_sdk_release().getSubscriptionUseCase$push_me_sdk_release();
        boolean shouldAppendSdkDeviceId = companion.getInstance$push_me_sdk_release().getConfig().getShouldAppendSdkDeviceId();
        c10811.L$0 = subscribe2;
        c10811.L$1 = list2;
        c10811.L$2 = SpillingKt.nullOutSpilledVariable(arrayList);
        c10811.label = 2;
        allForApplication = subscriptionUseCase$push_me_sdk_release.invoke(arrayList, shouldAppendSdkDeviceId, c10811);
        if (allForApplication != coroutine_suspended) {
            subscribe3 = subscribe2;
            list = list2;
            SubscriptionUseCase.Result result2 = (SubscriptionUseCase.Result) allForApplication;
            Logger.info$default(pendingActionsWorker.getLogger(), "Subscription result for app " + subscribe3.getApplication() + " and " + list.size() + " accounts: " + result2, null, 2, null);
            return Boxing.boxBoolean(Intrinsics.areEqual(result2, SubscriptionUseCase.Result.OK.INSTANCE));
        }
        return coroutine_suspended;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object processUnsubscribeAction(com.vk.pushme.logic.PendingAction.Unsubscribe unsubscribe, Continuation<? super Boolean> continuation) {
        C10821 c10821;
        if (continuation instanceof C10821) {
            c10821 = (C10821) continuation;
            int i10 = c10821.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c10821.label = i10 - Integer.MIN_VALUE;
            } else {
                c10821 = new C10821(continuation);
            }
        } else {
            c10821 = new C10821(continuation);
        }
        Object objInvoke = c10821.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c10821.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objInvoke);
            UnsubscribeUseCase unsubscribeUseCase$push_me_sdk_release = PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getUnsubscribeUseCase$push_me_sdk_release();
            String application = unsubscribe.getApplication();
            Set<String> accounts = unsubscribe.getAccounts();
            c10821.L$0 = unsubscribe;
            c10821.label = 1;
            objInvoke = unsubscribeUseCase$push_me_sdk_release.invoke(application, accounts, c10821);
            if (objInvoke == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            unsubscribe = (com.vk.pushme.logic.PendingAction.Unsubscribe) c10821.L$0;
            ResultKt.throwOnFailure(objInvoke);
        }
        UnsubscribeUseCase.Result result = (UnsubscribeUseCase.Result) objInvoke;
        Logger.info$default(getLogger(), "Unsubscribe result for app " + unsubscribe.getApplication() + " and " + unsubscribe.getAccounts().size() + " accounts: " + result, null, 2, null);
        return Boxing.boxBoolean(Intrinsics.areEqual(result, UnsubscribeUseCase.Result.OK.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldRetryOnError() {
        return getRunAttemptCount() < 10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SubscriptionDao subscriptionDao_delegate$lambda$0() {
        return PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getDatabase().subscriptionDao();
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
