package ru.mail.authorizationsdk.feature.captcha;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import ru.mail.march.viewmodel.MutableEventFlow;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaRouteKt$LudwigCaptchaRoute$1$1", f = "LudwigCaptchaRoute.kt", i = {}, l = {37}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class LudwigCaptchaRouteKt$LudwigCaptchaRoute$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<String, Bundle, Unit> $onResult;
    final /* synthetic */ WebCaptchaComposeViewModel $viewModel;
    int label;

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaRouteKt$LudwigCaptchaRoute$1$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaRouteKt$LudwigCaptchaRoute$1$1$1", f = "LudwigCaptchaRoute.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<LudwigCaptchaResult, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<String, Bundle, Unit> $onResult;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function2<? super String, ? super Bundle, Unit> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$onResult = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$onResult, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LudwigCaptchaResult ludwigCaptchaResult = (LudwigCaptchaResult) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$onResult.invoke(LudwigCaptchaResult.RESULT_KEY, LudwigCaptchaResult.Companion.getBundle$default(LudwigCaptchaResult.INSTANCE, ludwigCaptchaResult, null, 2, null));
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(LudwigCaptchaResult ludwigCaptchaResult, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(ludwigCaptchaResult, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    LudwigCaptchaRouteKt$LudwigCaptchaRoute$1$1(WebCaptchaComposeViewModel webCaptchaComposeViewModel, Function2<? super String, ? super Bundle, Unit> function2, Continuation<? super LudwigCaptchaRouteKt$LudwigCaptchaRoute$1$1> continuation) {
        super(2, continuation);
        this.$viewModel = webCaptchaComposeViewModel;
        this.$onResult = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LudwigCaptchaRouteKt$LudwigCaptchaRoute$1$1(this.$viewModel, this.$onResult, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i10 = this.label;
        if (i10 == 0) {
            ResultKt.throwOnFailure(obj);
            this.$viewModel.onStartScreen();
            MutableEventFlow<LudwigCaptchaResult> resultFlow = this.$viewModel.getResultFlow();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$onResult, null);
            this.label = 1;
            if (FlowKt.collectLatest(resultFlow, anonymousClass1, this) == coroutine_suspended) {
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
        return ((LudwigCaptchaRouteKt$LudwigCaptchaRoute$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
