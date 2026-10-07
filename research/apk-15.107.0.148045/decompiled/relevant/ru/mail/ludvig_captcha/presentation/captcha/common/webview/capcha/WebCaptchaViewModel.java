package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ludvig_captcha.external.LudwigAnalyticsCallback;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 -2\u00020\u0001:\u0002,-B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(J\u0006\u0010)\u001a\u00020&J\u0006\u0010*\u001a\u00020&J\b\u0010+\u001a\u00020&H\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0003X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\t\"\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001dR\u0019\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel;", "Landroidx/lifecycle/ViewModel;", "imitationHost", "", "analytics", "Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;", "<init>", "(Ljava/lang/String;Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;)V", "getImitationHost", "()Ljava/lang/String;", "getAnalytics", "()Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;", "userEnterScreenTimeMls", "", "getUserEnterScreenTimeMls", "()J", "stateDelegate", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate;", "webViewClient", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientExecutor;", "getWebViewClient", "()Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientExecutor;", "localPage", "getLocalPage", "setLocalPage", "(Ljava/lang/String;)V", "isLoading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "webCaptchaState", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState;", "getWebCaptchaState", "fileReadJob", "Lkotlinx/coroutines/Job;", "analyticCompanion", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/AnalyticCompanion;", "loadingPageFromFile", "", "context", "Landroid/content/Context;", "forceReloadAfterRestartActivity", "onDetach", "onCleared", "WebCaptchaState", "Companion", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebCaptchaViewModel extends ViewModel {

    @NotNull
    private static final String LUDVIG_LOCAL_PAGE = "iframe_ludochka.html";

    @NotNull
    private final AnalyticCompanion analyticCompanion;

    @Nullable
    private final LudwigAnalyticsCallback analytics;

    @Nullable
    private Job fileReadJob;

    @NotNull
    private final String imitationHost;

    @NotNull
    private final MutableStateFlow<Boolean> isLoading;
    public String localPage;

    @NotNull
    private final WebViewClientStateDelegate stateDelegate;
    private final long userEnterScreenTimeMls;

    @NotNull
    private final MutableStateFlow<WebCaptchaState> webCaptchaState;

    @NotNull
    private final WebViewClientExecutor webViewClient;

    /* JADX INFO: renamed from: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate$State;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel$1", f = "WebCaptchaViewModel.kt", i = {0, 1, 2, 3, 4, 4}, l = {35, 40, 49, 58, 62}, m = "invokeSuspend", n = {"it", "it", "it", "it", "it", "state"}, s = {"L$0", "L$0", "L$0", "L$0", "L$0", "L$1"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<WebViewClientStateDelegate.State, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = WebCaptchaViewModel.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0058, code lost:
        
            if (r10.emit(r2, r9) == r1) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0085, code lost:
        
            if (r10.emit(r2, r9) == r1) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00cc, code lost:
        
            if (r10.emit(r2, r9) == r1) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0112, code lost:
        
            if (r10.emit(r2, r9) == r1) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x012f, code lost:
        
            if (r2.emit(r10, r9) == r1) goto L52;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 315
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(WebViewClientStateDelegate.State state, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState;", "", "<init>", "()V", "LoadedLocalPage", "Captcha", "ErrorLoading", "CriticalErrorNoLocalPage", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState$Captcha;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState$CriticalErrorNoLocalPage;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState$ErrorLoading;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState$LoadedLocalPage;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class WebCaptchaState {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState$Captcha;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Captcha extends WebCaptchaState {

            @NotNull
            public static final Captcha INSTANCE = new Captcha();

            private Captcha() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState$CriticalErrorNoLocalPage;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class CriticalErrorNoLocalPage extends WebCaptchaState {

            @NotNull
            public static final CriticalErrorNoLocalPage INSTANCE = new CriticalErrorNoLocalPage();

            private CriticalErrorNoLocalPage() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState$ErrorLoading;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState;", "<init>", "()V", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ErrorLoading extends WebCaptchaState {

            @NotNull
            public static final ErrorLoading INSTANCE = new ErrorLoading();

            private ErrorLoading() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState$LoadedLocalPage;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState;", "needLoadPageAgain", "", "<init>", "(Z)V", "getNeedLoadPageAgain", "()Z", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class LoadedLocalPage extends WebCaptchaState {
            private final boolean needLoadPageAgain;

            public LoadedLocalPage(boolean z10) {
                super(null);
                this.needLoadPageAgain = z10;
            }

            public final boolean getNeedLoadPageAgain() {
                return this.needLoadPageAgain;
            }
        }

        public /* synthetic */ WebCaptchaState(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private WebCaptchaState() {
        }
    }

    /* JADX INFO: renamed from: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel$loadingPageFromFile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel$loadingPageFromFile$1", f = "WebCaptchaViewModel.kt", i = {1}, l = {70, 75}, m = "invokeSuspend", n = {"e"}, s = {"L$0"}, v = 1)
    static final class C22941 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C22941(Context context, Continuation<? super C22941> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WebCaptchaViewModel.this.new C22941(this.$context, continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0064, code lost:
        
            if (r1.emit(r3, r4) == r0) goto L18;
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
                if (r1 == 0) goto L22
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                java.lang.Object r0 = r4.L$0
                java.io.IOException r0 = (java.io.IOException) r0
                kotlin.ResultKt.throwOnFailure(r5)
                goto L67
            L16:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L1e:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L38
            L22:
                kotlin.ResultKt.throwOnFailure(r5)
                ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel r5 = ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel.this
                kotlinx.coroutines.flow.MutableStateFlow r5 = r5.isLoading()
                java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                r4.label = r3
                java.lang.Object r5 = r5.emit(r1, r4)
                if (r5 != r0) goto L38
                goto L66
            L38:
                ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel r5 = ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel.this     // Catch: java.io.IOException -> L4f
                android.content.Context r1 = r4.$context     // Catch: java.io.IOException -> L4f
                java.lang.String r3 = "iframe_ludochka.html"
                java.lang.String r1 = ru.mail.android_utils.IOCompatUtils.readAsset(r1, r3)     // Catch: java.io.IOException -> L4f
                r5.setLocalPage(r1)     // Catch: java.io.IOException -> L4f
                ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel r5 = ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel.this     // Catch: java.io.IOException -> L4f
                ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebViewClientStateDelegate r5 = ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel.access$getStateDelegate$p(r5)     // Catch: java.io.IOException -> L4f
                r5.fullRetry()     // Catch: java.io.IOException -> L4f
                goto L75
            L4f:
                r5 = move-exception
                ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel r1 = ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel.this
                kotlinx.coroutines.flow.MutableStateFlow r1 = r1.getWebCaptchaState()
                ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel$WebCaptchaState$CriticalErrorNoLocalPage r3 = ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel.WebCaptchaState.CriticalErrorNoLocalPage.INSTANCE
                java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)
                r4.L$0 = r5
                r4.label = r2
                java.lang.Object r5 = r1.emit(r3, r4)
                if (r5 != r0) goto L67
            L66:
                return r0
            L67:
                ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel r5 = ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel.this
                kotlinx.coroutines.flow.MutableStateFlow r5 = r5.isLoading()
                r0 = 0
                java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
                r5.setValue(r0)
            L75:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaViewModel.C22941.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C22941) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public WebCaptchaViewModel(@NotNull String imitationHost, @Nullable LudwigAnalyticsCallback ludwigAnalyticsCallback) {
        Intrinsics.checkNotNullParameter(imitationHost, "imitationHost");
        this.imitationHost = imitationHost;
        this.analytics = ludwigAnalyticsCallback;
        this.userEnterScreenTimeMls = System.currentTimeMillis();
        WebViewClientStateDelegate webViewClientStateDelegate = new WebViewClientStateDelegate();
        this.stateDelegate = webViewClientStateDelegate;
        this.webViewClient = new WebViewClientExecutor(webViewClientStateDelegate, ludwigAnalyticsCallback);
        this.isLoading = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.webCaptchaState = StateFlowKt.MutableStateFlow(null);
        this.analyticCompanion = new AnalyticCompanion(false, false, 3, null);
        FlowKt.launchIn(FlowKt.onEach(FlowKt.filterNotNull(webViewClientStateDelegate.getState()), new AnonymousClass1(null)), ViewModelKt.getViewModelScope(this));
    }

    public final void forceReloadAfterRestartActivity() {
        this.stateDelegate.fullRetry();
    }

    @Nullable
    public final LudwigAnalyticsCallback getAnalytics() {
        return this.analytics;
    }

    @NotNull
    public final String getImitationHost() {
        return this.imitationHost;
    }

    @NotNull
    public final String getLocalPage() {
        String str = this.localPage;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("localPage");
        return null;
    }

    public final long getUserEnterScreenTimeMls() {
        return this.userEnterScreenTimeMls;
    }

    @NotNull
    public final MutableStateFlow<WebCaptchaState> getWebCaptchaState() {
        return this.webCaptchaState;
    }

    @NotNull
    public final WebViewClientExecutor getWebViewClient() {
        return this.webViewClient;
    }

    @NotNull
    public final MutableStateFlow<Boolean> isLoading() {
        return this.isLoading;
    }

    public final void loadingPageFromFile(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.webViewClient.attach(context);
        if (this.localPage != null) {
            return;
        }
        this.fileReadJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C22941(context, null), 2, null);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        Job job = this.fileReadJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.fileReadJob = null;
    }

    public final void onDetach() {
        this.webViewClient.detach();
    }

    public final void setLocalPage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localPage = str;
    }
}
