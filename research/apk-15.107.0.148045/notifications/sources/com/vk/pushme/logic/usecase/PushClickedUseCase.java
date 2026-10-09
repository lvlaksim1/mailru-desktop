package com.vk.pushme.logic.usecase;

import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.PushDao;
import com.vk.pushme.work.util.WorkScheduler;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0086\u0002J\u0016\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/vk/pushme/logic/usecase/PushClickedUseCase;", "", "pushDao", "Lcom/vk/pushme/database/dao/PushDao;", "workScheduler", "Lcom/vk/pushme/work/util/WorkScheduler;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "logger", "Lcom/vk/pushme/common/Logger;", "<init>", "(Lcom/vk/pushme/database/dao/PushDao;Lcom/vk/pushme/work/util/WorkScheduler;Lkotlinx/coroutines/CoroutineScope;Lcom/vk/pushme/common/Logger;)V", "getLogger", "()Lcom/vk/pushme/common/Logger;", "logger$delegate", "Lkotlin/Lazy;", "invoke", "", "pushId", "", "processNotificationClick", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushClickedUseCase {

    @NotNull
    private final CoroutineScope coroutineScope;

    /* JADX INFO: renamed from: logger$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy logger;

    @NotNull
    private final PushDao pushDao;

    @NotNull
    private final WorkScheduler workScheduler;

    /* JADX INFO: renamed from: com.vk.pushme.logic.usecase.PushClickedUseCase$invoke$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.usecase.PushClickedUseCase$invoke$1", f = "PushClickedUseCase.kt", i = {}, l = {20}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $pushId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j10, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$pushId = j10;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PushClickedUseCase.this.new AnonymousClass1(this.$pushId, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                PushClickedUseCase pushClickedUseCase = PushClickedUseCase.this;
                long j10 = this.$pushId;
                this.label = 1;
                if (pushClickedUseCase.processNotificationClick(j10, this) == coroutine_suspended) {
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
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.logic.usecase.PushClickedUseCase$processNotificationClick$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.usecase.PushClickedUseCase", f = "PushClickedUseCase.kt", i = {0, 1, 1, 1, 1}, l = {28, 43}, m = "processNotificationClick", n = {"pushId", "push", "url", "workRequest", "pushId"}, s = {"J$0", "L$0", "L$1", "L$2", "J$0"}, v = 1)
    static final class C10771 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C10771(Continuation<? super C10771> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PushClickedUseCase.this.processNotificationClick(0L, this);
        }
    }

    public PushClickedUseCase(@NotNull PushDao pushDao, @NotNull WorkScheduler workScheduler, @NotNull CoroutineScope coroutineScope, @NotNull final Logger logger) {
        Intrinsics.checkNotNullParameter(pushDao, "pushDao");
        Intrinsics.checkNotNullParameter(workScheduler, "workScheduler");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.pushDao = pushDao;
        this.workScheduler = workScheduler;
        this.coroutineScope = coroutineScope;
        this.logger = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.logic.usecase.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushClickedUseCase.logger_delegate$lambda$0(logger);
            }
        });
    }

    private final Logger getLogger() {
        return (Logger) this.logger.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Logger logger_delegate$lambda$0(Logger logger) {
        return logger.createLogger("PushClickedUseCase");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00e3, code lost:
    
        if (r6.delete(r11, r0) == r1) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object processNotificationClick(long r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instruction units count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.logic.usecase.PushClickedUseCase.processNotificationClick(long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void invoke(long pushId) {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(pushId, null), 3, null);
    }
}
