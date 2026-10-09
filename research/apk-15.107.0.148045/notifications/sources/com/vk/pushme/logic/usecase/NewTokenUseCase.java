package com.vk.pushme.logic.usecase;

import androidx.work.ExistingWorkPolicy;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.PushTokenDao;
import com.vk.pushme.database.entity.PushToken;
import com.vk.pushme.model.Transport;
import com.vk.pushme.work.DeleteTokenFromServerWorker;
import com.vk.pushme.work.SyncWorker;
import com.vk.pushme.work.util.WorkScheduler;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/vk/pushme/logic/usecase/NewTokenUseCase;", "", "pushTokenDao", "Lcom/vk/pushme/database/dao/PushTokenDao;", "workScheduler", "Lcom/vk/pushme/work/util/WorkScheduler;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "logger", "Lcom/vk/pushme/common/Logger;", "<init>", "(Lcom/vk/pushme/database/dao/PushTokenDao;Lcom/vk/pushme/work/util/WorkScheduler;Lkotlinx/coroutines/CoroutineScope;Lcom/vk/pushme/common/Logger;)V", "getLogger", "()Lcom/vk/pushme/common/Logger;", "logger$delegate", "Lkotlin/Lazy;", "invoke", "", "newPushToken", "", "transport", "Lcom/vk/pushme/model/Transport;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NewTokenUseCase {

    @NotNull
    private final CoroutineScope coroutineScope;

    /* JADX INFO: renamed from: logger$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy logger;

    @NotNull
    private final PushTokenDao pushTokenDao;

    @NotNull
    private final WorkScheduler workScheduler;

    /* JADX INFO: renamed from: com.vk.pushme.logic.usecase.NewTokenUseCase$invoke$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.usecase.NewTokenUseCase$invoke$1", f = "NewTokenUseCase.kt", i = {}, l = {31}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PushToken $entity;
        final /* synthetic */ String $newPushToken;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PushToken pushToken, String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$entity = pushToken;
            this.$newPushToken = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NewTokenUseCase.this.new AnonymousClass1(this.$entity, this.$newPushToken, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                PushTokenDao pushTokenDao = NewTokenUseCase.this.pushTokenDao;
                PushToken pushToken = this.$entity;
                this.label = 1;
                obj = pushTokenDao.replaceTokenForGivenTransport(pushToken, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            PushToken pushToken2 = (PushToken) obj;
            String token = pushToken2 != null ? pushToken2.getToken() : null;
            if (Intrinsics.areEqual(token, this.$newPushToken)) {
                Logger.warn$default(NewTokenUseCase.this.getLogger(), "Providing push token which is exactly the same as the previous one", null, 2, null);
                return Unit.INSTANCE;
            }
            if (token != null && !StringsKt.isBlank(token)) {
                Logger.info$default(NewTokenUseCase.this.getLogger(), "Enqueue worker for invalidating old push token", null, 2, null);
                NewTokenUseCase.this.workScheduler.enqueue(DeleteTokenFromServerWorker.INSTANCE.buildWorkRequest(token));
            }
            NewTokenUseCase.this.workScheduler.enqueueUniqueWork(SyncWorker.UNIQUE_WORK_ID, ExistingWorkPolicy.REPLACE, SyncWorker.Companion.buildOneTimeWorkRequest$default(SyncWorker.INSTANCE, true, PushMeSdk.INSTANCE.getInstance$push_me_sdk_release().getConfig().getSkipConnectionCheckByGoogle(), 0L, 4, null));
            Logger.info$default(NewTokenUseCase.this.getLogger(), "Sync worker has been enqueued", null, 2, null);
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public NewTokenUseCase(@NotNull PushTokenDao pushTokenDao, @NotNull WorkScheduler workScheduler, @NotNull CoroutineScope coroutineScope, @NotNull final Logger logger) {
        Intrinsics.checkNotNullParameter(pushTokenDao, "pushTokenDao");
        Intrinsics.checkNotNullParameter(workScheduler, "workScheduler");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.pushTokenDao = pushTokenDao;
        this.workScheduler = workScheduler;
        this.coroutineScope = coroutineScope;
        this.logger = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.logic.usecase.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NewTokenUseCase.logger_delegate$lambda$0(logger);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Logger getLogger() {
        return (Logger) this.logger.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Logger logger_delegate$lambda$0(Logger logger) {
        return logger.createLogger("NewTokenUseCase");
    }

    public final void invoke(@NotNull String newPushToken, @NotNull Transport transport) {
        Intrinsics.checkNotNullParameter(newPushToken, "newPushToken");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Logger.info$default(getLogger(), "Handle new token for transport " + transport.getValue(), null, 2, null);
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(new PushToken(0L, newPushToken, transport.getValue(), System.currentTimeMillis(), 1, null), newPushToken, null), 3, null);
    }
}
