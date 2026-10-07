package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\b\u0000\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u0006\u0010\u0019\u001a\u00020\u0016J\u0006\u0010\u001a\u001a\u00020\u0016J\u0006\u0010\u001b\u001a\u00020\u0016J\u0006\u0010\u001c\u001a\u00020\u0016J\u0006\u0010\u001d\u001a\u00020\u0016J\u0006\u0010\u001e\u001a\u00020\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0013\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017¨\u0006 "}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewRetryDelegate;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "refreshTimeout", "", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineScope;J)V", "state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewDelegateState;", "getState", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "attemptNumber", "", "loadAttemptJob", "Lkotlinx/coroutines/Job;", "reloadTask", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function1;", "newAttempt", "onPageStarted", "onPageFinished", "onPageError", "onPageCriticalError", "fullRetry", "detach", "Companion", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebViewRetryDelegate {
    private static final int MAX_ATTEMPT_COUNT = 3;
    private static final long REFRESH_TIMEOUT = 59000;
    private int attemptNumber;

    @NotNull
    private final CoroutineDispatcher dispatcher;

    @Nullable
    private Job loadAttemptJob;
    private final long refreshTimeout;

    @NotNull
    private final Function1<Continuation<? super Unit>, Object> reloadTask;

    @NotNull
    private final MutableStateFlow<WebViewDelegateState> state;

    @NotNull
    private final CoroutineScope viewModelScope;

    /* JADX INFO: renamed from: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebViewRetryDelegate$onPageStarted$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebViewRetryDelegate$onPageStarted$1", f = "WebViewRetryDelegate.kt", i = {}, l = {35}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WebViewRetryDelegate.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                Function1 function1 = WebViewRetryDelegate.this.reloadTask;
                this.label = 1;
                if (function1.invoke(this) == coroutine_suspended) {
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

    public WebViewRetryDelegate(@NotNull CoroutineDispatcher dispatcher, @NotNull CoroutineScope viewModelScope, long j10) {
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(viewModelScope, "viewModelScope");
        this.dispatcher = dispatcher;
        this.viewModelScope = viewModelScope;
        this.refreshTimeout = j10;
        this.state = StateFlowKt.MutableStateFlow(null);
        this.attemptNumber = 1;
        this.reloadTask = new WebViewRetryDelegate$reloadTask$1(this, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void newAttempt() {
        int i10 = this.attemptNumber + 1;
        this.attemptNumber = i10;
        this.state.setValue(new WebViewDelegateState.Loading(i10, true));
    }

    public final void detach() {
        Job job = this.loadAttemptJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.loadAttemptJob = null;
    }

    public final void fullRetry() {
        Job job = this.loadAttemptJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.attemptNumber = 1;
        this.state.setValue(new WebViewDelegateState.Loading(1, true));
    }

    @NotNull
    public final MutableStateFlow<WebViewDelegateState> getState() {
        return this.state;
    }

    public final void onPageCriticalError() {
        Job job = this.loadAttemptJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.state.setValue(WebViewDelegateState.CriticalError.INSTANCE);
    }

    public final void onPageError() {
        Job job = this.loadAttemptJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (this.attemptNumber < 3) {
            newAttempt();
        } else {
            this.state.setValue(WebViewDelegateState.Error.INSTANCE);
        }
    }

    public final void onPageFinished() {
        Job job = this.loadAttemptJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.state.setValue(WebViewDelegateState.Loaded.INSTANCE);
    }

    public final void onPageStarted() {
        this.state.setValue(new WebViewDelegateState.Loading(this.attemptNumber, false));
        Job job = this.loadAttemptJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.loadAttemptJob = BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, this.dispatcher, null, new AnonymousClass1(null), 2, null);
    }

    public /* synthetic */ WebViewRetryDelegate(CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, long j10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineDispatcher, coroutineScope, (i10 & 4) != 0 ? REFRESH_TIMEOUT : j10);
    }
}
