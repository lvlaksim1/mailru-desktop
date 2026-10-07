package ru.mail.authorizationsdk.feature.customserver.presentation.delegates;

import android.graphics.Bitmap;
import androidx.compose.runtime.Stable;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.ImageBitmap;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.domain.usecase.pikachu.PikachuUseCase;
import ru.mail.authorizationsdk.domain.usecase.pikachu.model.PikachuResult;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Stable
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\u001c\u001a\u00020\r2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bJ\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\fH\u0016J\b\u0010 \u001a\u00020\rH\u0016J\u000e\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\u0010J\u0010\u0010#\u001a\u00020\r2\b\u0010$\u001a\u0004\u0018\u00010\fJ\u0006\u0010%\u001a\u00020\rJ\u000e\u0010&\u001a\u00020\rH\u0082@¢\u0006\u0002\u0010'J\u0010\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0011R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u001c\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\"\u0010\u0019\u001a\u0004\u0018\u00010\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006+"}, d2 = {"Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/PikachuCaptchaVmDelegate;", "Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/PikachuCaptchaProvider;", "ioDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getPikachuCaptchaUseCase", "Lru/mail/authorizationsdk/domain/usecase/pikachu/PikachuUseCase;", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lru/mail/authorizationsdk/domain/usecase/pikachu/PikachuUseCase;)V", CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "Lkotlinx/coroutines/CoroutineScope;", "errorListener", "Lkotlin/Function1;", "", "", "isNeedShowCaptcha", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "isCaptchaLoading", "captchaBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "getCaptchaBitmap", "captchaCode", "getCaptchaCode", "value", "captchaCookie", "getCaptchaCookie", "()Ljava/lang/String;", "setErrorListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "onUpdateCaptchaCode", "newCaptchaCode", "needNewCaptchaCode", "setShowCaptcha", "isNeedShow", "setCaptchaCookie", "cookie", "onDestroy", "getNewCaptcha", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setNewCaptchaBitmap", "newCaptcha", "Landroid/graphics/Bitmap;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PikachuCaptchaVmDelegate implements PikachuCaptchaProvider {
    public static final int $stable = 0;

    @NotNull
    private final MutableStateFlow<ImageBitmap> captchaBitmap;

    @NotNull
    private final MutableStateFlow<String> captchaCode;

    @Nullable
    private String captchaCookie;

    @Nullable
    private Function1<? super String, Unit> errorListener;

    @NotNull
    private final PikachuUseCase getPikachuCaptchaUseCase;

    @NotNull
    private final MutableStateFlow<Boolean> isCaptchaLoading;

    @NotNull
    private final MutableStateFlow<Boolean> isNeedShowCaptcha;

    @NotNull
    private final CoroutineScope scope;

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate$getNewCaptcha$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate", f = "PikachuCaptchaVmDelegate.kt", i = {}, l = {59}, m = "getNewCaptcha", n = {}, s = {}, v = 1)
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
            return PikachuCaptchaVmDelegate.this.getNewCaptcha(this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate$needNewCaptchaCode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate$needNewCaptchaCode$1", f = "PikachuCaptchaVmDelegate.kt", i = {}, l = {40, 41}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C16331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C16331(Continuation<? super C16331> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PikachuCaptchaVmDelegate.this.new C16331(continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
        
            if (r5.getNewCaptcha(r4) == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L1e
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r5)
                goto L3f
            L12:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L1a:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L34
            L1e:
                kotlin.ResultKt.throwOnFailure(r5)
                ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate r5 = ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate.this
                kotlinx.coroutines.flow.MutableStateFlow r5 = r5.isCaptchaLoading()
                java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                r4.label = r3
                java.lang.Object r5 = r5.emit(r1, r4)
                if (r5 != r0) goto L34
                goto L3e
            L34:
                ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate r5 = ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate.this
                r4.label = r2
                java.lang.Object r5 = ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate.access$getNewCaptcha(r5, r4)
                if (r5 != r0) goto L3f
            L3e:
                return r0
            L3f:
                ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate r5 = ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate.this
                kotlinx.coroutines.flow.MutableStateFlow r5 = r5.isCaptchaLoading()
                r0 = 0
                java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
                r5.setValue(r0)
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaVmDelegate.C16331.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C16331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public PikachuCaptchaVmDelegate(@NotNull CoroutineDispatcher ioDispatcher, @NotNull PikachuUseCase getPikachuCaptchaUseCase) {
        Intrinsics.checkNotNullParameter(ioDispatcher, "ioDispatcher");
        Intrinsics.checkNotNullParameter(getPikachuCaptchaUseCase, "getPikachuCaptchaUseCase");
        this.getPikachuCaptchaUseCase = getPikachuCaptchaUseCase;
        this.scope = CoroutineScopeKt.CoroutineScope(ioDispatcher);
        Boolean bool = Boolean.FALSE;
        this.isNeedShowCaptcha = StateFlowKt.MutableStateFlow(bool);
        this.isCaptchaLoading = StateFlowKt.MutableStateFlow(bool);
        this.captchaBitmap = StateFlowKt.MutableStateFlow(null);
        this.captchaCode = StateFlowKt.MutableStateFlow("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object getNewCaptcha(Continuation<? super Unit> continuation) {
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
        Object objInvoke = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(objInvoke);
            PikachuUseCase pikachuUseCase = this.getPikachuCaptchaUseCase;
            anonymousClass1.label = 1;
            objInvoke = pikachuUseCase.invoke(anonymousClass1);
            if (objInvoke == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objInvoke);
        }
        PikachuResult pikachuResult = (PikachuResult) objInvoke;
        if (pikachuResult instanceof PikachuResult.Success) {
            PikachuResult.Success success = (PikachuResult.Success) pikachuResult;
            setNewCaptchaBitmap(success.getCaptcha());
            setCaptchaCookie(success.getCookie());
        } else {
            if (!(pikachuResult instanceof PikachuResult.Error)) {
                throw new NoWhenBranchMatchedException();
            }
            Function1<? super String, Unit> function1 = this.errorListener;
            if (function1 != null) {
                function1.invoke(((PikachuResult.Error) pikachuResult).getMessage());
            }
        }
        return Unit.INSTANCE;
    }

    private final void setNewCaptchaBitmap(Bitmap newCaptcha) {
        getCaptchaBitmap().setValue(AndroidImageBitmap_androidKt.asImageBitmap(newCaptcha));
    }

    @Nullable
    public final String getCaptchaCookie() {
        return this.captchaCookie;
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    public void needNewCaptchaCode() {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C16331(null), 3, null);
    }

    public final void onDestroy() {
        CoroutineScopeKt.cancel$default(this.scope, null, 1, null);
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    public void onUpdateCaptchaCode(@NotNull String newCaptchaCode) {
        Intrinsics.checkNotNullParameter(newCaptchaCode, "newCaptchaCode");
        getCaptchaCode().setValue(newCaptchaCode);
    }

    public final void setCaptchaCookie(@Nullable String cookie) {
        this.captchaCookie = cookie;
    }

    public final void setErrorListener(@NotNull Function1<? super String, Unit> listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.errorListener = listener;
    }

    public final void setShowCaptcha(boolean isNeedShow) {
        isNeedShowCaptcha().setValue(Boolean.valueOf(isNeedShow));
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    @NotNull
    public MutableStateFlow<ImageBitmap> getCaptchaBitmap() {
        return this.captchaBitmap;
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    @NotNull
    public MutableStateFlow<String> getCaptchaCode() {
        return this.captchaCode;
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    @NotNull
    public MutableStateFlow<Boolean> isCaptchaLoading() {
        return this.isCaptchaLoading;
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider
    @NotNull
    public MutableStateFlow<Boolean> isNeedShowCaptcha() {
        return this.isNeedShowCaptcha;
    }
}
