package ru.mail.util.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.hilt.work.HiltWorker;
import androidx.work.CoroutineWorker;
import androidx.work.WorkerParameters;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import javax.inject.Provider;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.content.DataManager;
import ru.mail.utils.SafetyDependenciesProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@HiltWorker
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B3\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\r\u001a\u00020\u000eH\u0096@¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/util/push/PushTokenCheckWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParameters", "Landroidx/work/WorkerParameters;", "dataManager", "Ljavax/inject/Provider;", "Lru/mail/logic/content/DataManager;", "provider", "Lru/mail/utils/SafetyDependenciesProvider;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Ljavax/inject/Provider;Lru/mail/utils/SafetyDependenciesProvider;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(message = "Can be removed after PushMe SDK integration")
@SourceDebugExtension({"SMAP\nPushTokenCheckWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushTokenCheckWorker.kt\nru/mail/util/push/PushTokenCheckWorker\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,45:1\n1#2:46\n*E\n"})
public final class PushTokenCheckWorker extends CoroutineWorker {

    @NotNull
    public static final String uniqueId = "PushTokenCheckWorkerUniqueId";

    @NotNull
    private final Provider<DataManager> dataManager;

    @NotNull
    private final SafetyDependenciesProvider provider;
    public static final int $stable = 8;

    /* JADX INFO: renamed from: ru.mail.util.push.PushTokenCheckWorker$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.util.push.PushTokenCheckWorker", f = "PushTokenCheckWorker.kt", i = {1, 1}, l = {25, 26, 28}, m = "doWork", n = {"$this$doWork_u24lambda_u240", "$i$a$-apply-PushTokenCheckWorker$doWork$result$1"}, s = {"L$1", "I$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
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
            return PushTokenCheckWorker.this.doWork(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @AssistedInject
    public PushTokenCheckWorker(@Assisted @NotNull Context context, @Assisted @NotNull WorkerParameters workerParameters, @NotNull Provider<DataManager> dataManager, @NotNull SafetyDependenciesProvider provider) {
        super(context, workerParameters);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParameters, "workerParameters");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.dataManager = dataManager;
        this.provider = provider;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0083, code lost:
    
        if (r7 == r1) goto L27;
     */
    @Override // androidx.work.CoroutineWorker
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof ru.mail.util.push.PushTokenCheckWorker.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r7
            ru.mail.util.push.PushTokenCheckWorker$doWork$1 r0 = (ru.mail.util.push.PushTokenCheckWorker.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            ru.mail.util.push.PushTokenCheckWorker$doWork$1 r0 = new ru.mail.util.push.PushTokenCheckWorker$doWork$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L45
            if (r2 == r5) goto L41
            if (r2 == r4) goto L37
            if (r2 != r3) goto L2f
            kotlin.ResultKt.throwOnFailure(r7)
            goto L86
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L37:
            java.lang.Object r2 = r0.L$1
            ru.mail.logic.content.DataManager r2 = (ru.mail.logic.content.DataManager) r2
            java.lang.Object r2 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L72
        L41:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L55
        L45:
            kotlin.ResultKt.throwOnFailure(r7)
            ru.mail.utils.SafetyDependenciesProvider r7 = r6.provider
            javax.inject.Provider<ru.mail.logic.content.DataManager> r2 = r6.dataManager
            r0.label = r5
            java.lang.Object r7 = r7.invoke(r2, r0)
            if (r7 != r1) goto L55
            goto L85
        L55:
            r2 = r7
            ru.mail.logic.content.DataManager r2 = (ru.mail.logic.content.DataManager) r2
            ru.mail.logic.content.impl.DataManagerChecker r5 = new ru.mail.logic.content.impl.DataManagerChecker
            r5.<init>(r2)
            r0.L$0 = r7
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r2)
            r0.L$1 = r2
            r2 = 0
            r0.I$0 = r2
            r0.label = r4
            java.lang.Object r2 = r5.check(r0)
            if (r2 != r1) goto L71
            goto L85
        L71:
            r2 = r7
        L72:
            ru.mail.logic.content.DataManager r2 = (ru.mail.logic.content.DataManager) r2
            ru.mail.mailbox.cmd.ObservableFuture r7 = r2.checkPushToken()
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r7 = r7.await(r0)
            if (r7 != r1) goto L86
        L85:
            return r1
        L86:
            ru.mail.mailbox.cmd.ExecutionResult r7 = (ru.mail.mailbox.cmd.ExecutionResult) r7
            ru.mail.kit.result.tools.Result r7 = r7.asResult()
            boolean r0 = r7 instanceof ru.mail.kit.result.tools.Result.Success
            if (r0 == 0) goto L98
            androidx.work.ListenableWorker$Result r7 = androidx.work.ListenableWorker.Result.success()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            return r7
        L98:
            boolean r7 = r7 instanceof ru.mail.kit.result.tools.Result.Failure
            if (r7 == 0) goto La4
            androidx.work.ListenableWorker$Result r7 = androidx.work.ListenableWorker.Result.failure()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            return r7
        La4:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: ru.mail.util.push.PushTokenCheckWorker.doWork(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
