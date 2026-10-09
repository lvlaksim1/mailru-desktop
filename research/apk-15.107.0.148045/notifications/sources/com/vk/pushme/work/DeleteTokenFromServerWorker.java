package com.vk.pushme.work;

import android.content.Context;
import android.util.Log;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.CoroutineWorker;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkerParameters;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.common.Logger;
import com.vk.pushme.logic.usecase.DeleteTokenUseCase;
import com.vk.pushme.util.Extensions;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
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
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u000e\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0010J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/vk/pushme/work/DeleteTokenFromServerWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "logger", "Lcom/vk/pushme/common/Logger;", "getLogger", "()Lcom/vk/pushme/common/Logger;", "logger$delegate", "Lkotlin/Lazy;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPushTokenExtra", "", "Companion", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DeleteTokenFromServerWorker extends CoroutineWorker {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private static final int MAX_ATTEMPTS_COUNT = 20;

    @NotNull
    private static final String PUSH_TOKEN_EXTRA = "push_token";

    /* JADX INFO: renamed from: logger$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy logger;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/vk/pushme/work/DeleteTokenFromServerWorker$Companion;", "", "<init>", "()V", "MAX_ATTEMPTS_COUNT", "", "PUSH_TOKEN_EXTRA", "", "buildWorkRequest", "Landroidx/work/OneTimeWorkRequest;", "pushToken", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDeleteTokenFromServerWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeleteTokenFromServerWorker.kt\ncom/vk/pushme/work/DeleteTokenFromServerWorker$Companion\n+ 2 OneTimeWorkRequest.kt\nandroidx/work/OneTimeWorkRequestKt\n*L\n1#1,95:1\n105#2:96\n*S KotlinDebug\n*F\n+ 1 DeleteTokenFromServerWorker.kt\ncom/vk/pushme/work/DeleteTokenFromServerWorker$Companion\n*L\n74#1:96\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final OneTimeWorkRequest buildWorkRequest(@NotNull String pushToken) {
            Intrinsics.checkNotNullParameter(pushToken, "pushToken");
            if (StringsKt.isBlank(pushToken)) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            return new OneTimeWorkRequest.Builder((Class<? extends ListenableWorker>) DeleteTokenFromServerWorker.class).setInitialDelay(30L, TimeUnit.SECONDS).setConstraints(WorkerExtensionsKt.setRequiredNetworkTypeConnected(new Constraints.Builder(), PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getConfig().getSkipConnectionCheckByGoogle()).build()).setInputData(new Data.Builder().putString(DeleteTokenFromServerWorker.PUSH_TOKEN_EXTRA, pushToken).build()).setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 30000L, TimeUnit.MILLISECONDS).build();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.DeleteTokenFromServerWorker$doWork$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.DeleteTokenFromServerWorker", f = "DeleteTokenFromServerWorker.kt", i = {}, l = {27}, m = "doWork", n = {}, s = {}, v = 1)
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
            return DeleteTokenFromServerWorker.this.doWork(this);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.work.DeleteTokenFromServerWorker$doWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\f0\u0001¢\u0006\u0002\b\u0002¢\u0006\u0002\b\u0003*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Landroidx/work/ListenableWorker$Result;", "Lorg/jspecify/annotations/NonNull;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.work.DeleteTokenFromServerWorker$doWork$2", f = "DeleteTokenFromServerWorker.kt", i = {0}, l = {50}, m = "invokeSuspend", n = {"token"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ListenableWorker.Result>, Object> {
        Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DeleteTokenFromServerWorker.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                PushMeSdk.Companion companion = PushMeSdk.INSTANCE;
                if (!companion.isInitialized$push_me_sdk_release()) {
                    Log.w("PushMeSDK", "SDK is not initialized, DeleteTokenFromServerWorker will not run");
                    return ListenableWorker.Result.failure();
                }
                Logger.info$default(DeleteTokenFromServerWorker.this.getLogger(), "Worker has started, attempts count: " + DeleteTokenFromServerWorker.this.getRunAttemptCount(), null, 2, null);
                if (DeleteTokenFromServerWorker.this.getRunAttemptCount() >= 20) {
                    Logger.warn$default(DeleteTokenFromServerWorker.this.getLogger(), "Too many attempts for this worker, no more retries", null, 2, null);
                    return ListenableWorker.Result.failure();
                }
                Extensions extensions = Extensions.INSTANCE;
                Context applicationContext = DeleteTokenFromServerWorker.this.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                if (!extensions.isNetworkAvailable(applicationContext)) {
                    Logger.info$default(DeleteTokenFromServerWorker.this.getLogger(), "Network is unavailable, will retry later", null, 2, null);
                    return ListenableWorker.Result.retry();
                }
                String pushTokenExtra = DeleteTokenFromServerWorker.this.getPushTokenExtra();
                if (pushTokenExtra == null || StringsKt.isBlank(pushTokenExtra)) {
                    Logger.error$default(DeleteTokenFromServerWorker.this.getLogger(), "Push token input data is missing", null, 2, null);
                    return ListenableWorker.Result.failure();
                }
                DeleteTokenUseCase deleteTokenUseCase$push_me_sdk_release = companion.getInstance$push_me_sdk_release().getDeleteTokenUseCase$push_me_sdk_release();
                this.L$0 = SpillingKt.nullOutSpilledVariable(pushTokenExtra);
                this.label = 1;
                obj = deleteTokenUseCase$push_me_sdk_release.invoke(pushTokenExtra, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            DeleteTokenUseCase.Result result = (DeleteTokenUseCase.Result) obj;
            if (Intrinsics.areEqual(result, DeleteTokenUseCase.Result.OK.INSTANCE)) {
                Logger.info$default(DeleteTokenFromServerWorker.this.getLogger(), "Work has finished successfully", null, 2, null);
                return ListenableWorker.Result.success();
            }
            if (!(result instanceof DeleteTokenUseCase.Result.UnknownError)) {
                throw new NoWhenBranchMatchedException();
            }
            DeleteTokenFromServerWorker.this.getLogger().error("Work has finished with failure", ((DeleteTokenUseCase.Result.UnknownError) result).getT());
            return ListenableWorker.Result.retry();
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ListenableWorker.Result> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeleteTokenFromServerWorker(@NotNull Context appContext, @NotNull WorkerParameters workerParams) {
        super(appContext, workerParams);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.logger = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.work.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DeleteTokenFromServerWorker.logger_delegate$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Logger getLogger() {
        return (Logger) this.logger.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPushTokenExtra() {
        return getInputData().getString(PUSH_TOKEN_EXTRA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Logger logger_delegate$lambda$0() {
        return PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getLogger().createLogger("DeleteTokenFromServerWorker");
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
