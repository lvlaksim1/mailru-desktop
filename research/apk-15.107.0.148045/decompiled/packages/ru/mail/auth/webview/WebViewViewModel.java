package ru.mail.auth.webview;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\t\b&\u0018\u0000 ,*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001,B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u001e\u001a\u00020\u001bH\u0014J\u0012\u0010\u001f\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010!\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\"\u001a\u00020\u001bH\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010 \u001a\u00020\u0013H\u0016J\b\u0010%\u001a\u00020\u001bH\u0016J\u0006\u0010&\u001a\u00020\u001bJ\b\u0010'\u001a\u00020\u001bH\u0016J\b\u0010(\u001a\u00020\u001bH&J\u0015\u0010)\u001a\u00020\u001b2\u0006\u0010*\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010+R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0018\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001d¨\u0006-"}, d2 = {"Lru/mail/auth/webview/WebViewViewModel;", "E", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "_loadingState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lru/mail/auth/webview/LoadingState;", "loadingState", "Lkotlinx/coroutines/flow/StateFlow;", "getLoadingState", "()Lkotlinx/coroutines/flow/StateFlow;", "eventChannel", "Lkotlinx/coroutines/channels/Channel;", "eventFlow", "Lkotlinx/coroutines/flow/Flow;", "getEventFlow", "()Lkotlinx/coroutines/flow/Flow;", "currentUrl", "", "attemptNumber", "", "loadAttemptJob", "Lkotlinx/coroutines/Job;", "reloadTask", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/jvm/functions/Function1;", "newAttempt", "onPageStarted", "url", "onPageFinished", "onPageError", "onPageRedirect", "", "onContentLoaded", "onDetach", "onRetryClicked", "onBackPressed", "sendEvent", "event", "(Ljava/lang/Object;)V", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class WebViewViewModel<E> extends ViewModel {
    private static final int MAX_ATTEMPT_COUNT = 3;
    private static final long REFRESH_TIMEOUT = 10000;

    @NotNull
    private final MutableStateFlow<LoadingState> _loadingState;
    private int attemptNumber;

    @Nullable
    private String currentUrl;

    @NotNull
    private final Channel<E> eventChannel;

    @NotNull
    private final Flow<E> eventFlow;

    @Nullable
    private Job loadAttemptJob;

    @NotNull
    private final StateFlow<LoadingState> loadingState;

    @NotNull
    private final Function1<Continuation<? super Unit>, Object> reloadTask;

    /* JADX INFO: renamed from: ru.mail.auth.webview.WebViewViewModel$onPageStarted$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.auth.webview.WebViewViewModel$onPageStarted$1", f = "WebViewViewModel.kt", i = {}, l = {42}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ WebViewViewModel<E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(WebViewViewModel<E> webViewViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = webViewViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                Function1 function1 = ((WebViewViewModel) this.this$0).reloadTask;
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

    /* JADX INFO: renamed from: ru.mail.auth.webview.WebViewViewModel$sendEvent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.auth.webview.WebViewViewModel$sendEvent$1", f = "WebViewViewModel.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C15861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ E $event;
        int label;
        final /* synthetic */ WebViewViewModel<E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C15861(WebViewViewModel<E> webViewViewModel, E e10, Continuation<? super C15861> continuation) {
            super(2, continuation);
            this.this$0 = webViewViewModel;
            this.$event = e10;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C15861(this.this$0, this.$event, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                Channel channel = ((WebViewViewModel) this.this$0).eventChannel;
                E e10 = this.$event;
                this.label = 1;
                if (channel.send(e10, this) == coroutine_suspended) {
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
            return ((C15861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public WebViewViewModel() {
        MutableStateFlow<LoadingState> MutableStateFlow = StateFlowKt.MutableStateFlow(new LoadingState.Loading(null));
        this._loadingState = MutableStateFlow;
        this.loadingState = MutableStateFlow;
        Channel<E> channelChannel$default = ChannelKt.Channel$default(0, null, null, 7, null);
        this.eventChannel = channelChannel$default;
        this.eventFlow = FlowKt.receiveAsFlow(channelChannel$default);
        this.attemptNumber = 1;
        this.reloadTask = new WebViewViewModel$reloadTask$1(this, null);
    }

    @NotNull
    public final Flow<E> getEventFlow() {
        return this.eventFlow;
    }

    @NotNull
    public final StateFlow<LoadingState> getLoadingState() {
        return this.loadingState;
    }

    protected void newAttempt() {
        this.attemptNumber++;
        this._loadingState.setValue(new LoadingState.Loading(this.currentUrl));
    }

    public abstract void onBackPressed();

    public void onContentLoaded() {
        Job job = this.loadAttemptJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this._loadingState.setValue(LoadingState.ContentLoaded.INSTANCE);
    }

    public final void onDetach() {
        Job job = this.loadAttemptJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.loadAttemptJob = null;
    }

    public void onPageError() {
        if (this.attemptNumber <= 3) {
            newAttempt();
        } else {
            this._loadingState.setValue(LoadingState.LoadingError.INSTANCE);
        }
    }

    public void onPageFinished(@Nullable String url) {
        this._loadingState.setValue(LoadingState.PageLoaded.INSTANCE);
    }

    public boolean onPageRedirect(@NotNull String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return false;
    }

    public void onPageStarted(@Nullable String url) {
        this.currentUrl = url;
        if (this.attemptNumber <= 3) {
            Job job = this.loadAttemptJob;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.loadAttemptJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(this, null), 3, null);
        }
    }

    public void onRetryClicked() {
        Job job = this.loadAttemptJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.attemptNumber = 1;
        this._loadingState.setValue(new LoadingState.Loading(this.currentUrl));
    }

    protected final void sendEvent(E event) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C15861(this, event, null), 3, null);
    }
}
