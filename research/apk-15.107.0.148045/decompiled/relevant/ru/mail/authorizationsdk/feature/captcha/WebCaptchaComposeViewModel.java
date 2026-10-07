package ru.mail.authorizationsdk.feature.captcha;

import android.content.Context;
import android.os.Bundle;
import androidx.compose.runtime.Stable;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
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
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.di.viewmodel.CommonViewModelFactory;
import ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver;
import ru.mail.authorizationsdk.feature.captcha.analytics.AnalyticCompanion;
import ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents;
import ru.mail.authorizationsdk.feature.captcha.config.LudwigConfig;
import ru.mail.authorizationsdk.feature.captcha.ludochka.LudochkaConfig;
import ru.mail.authorizationsdk.feature.captcha.ludochka.LudochkaEvent;
import ru.mail.authorizationsdk.feature.captcha.webview.WebViewClientExecutor;
import ru.mail.authorizationsdk.feature.captcha.webview.WebViewClientStateDelegate;
import ru.mail.authorizationsdk.feature.core.presentation.ExternalAuthRedirectPicker;
import ru.mail.authorizationsdk.navigation.DestBase;
import ru.mail.march.concurrent.ViewModelDispatcher;
import ru.mail.march.viewmodel.ExtensionsKt;
import ru.mail.march.viewmodel.MutableEventFlow;
import ru.mail.util.log.Logger;
import statusnavbars.StatusNavBarHelper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Stable
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 S2\u00020\u0001:\u0003QRSBE\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0006\u0010G\u001a\u00020HJ\u000e\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020KJ\u0006\u0010L\u001a\u00020HJ\b\u0010M\u001a\u00020HH\u0014J\u000e\u0010N\u001a\u00020H2\u0006\u0010O\u001a\u00020PR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0014\u0010%\u001a\u00020&X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u00100\u001a\u000201¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u00104\u001a\u000205¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\"\"\u0004\b:\u0010;R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u00020>0=¢\u0006\b\n\u0000\u001a\u0004\b<\u0010?R\u0019\u0010@\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010A0=¢\u0006\b\n\u0000\u001a\u0004\bB\u0010?R\u0010\u0010C\u001a\u0004\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020FX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "viewModelDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "baseLogger", "Lru/mail/util/log/Logger;", "config", "Lru/mail/authorizationsdk/feature/captcha/config/LudwigConfig;", "statusNavBarHelper", "Lstatusnavbars/StatusNavBarHelper;", "analytics", "Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;", "urlsResolver", "Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;", "<init>", "(Landroidx/lifecycle/SavedStateHandle;Lkotlinx/coroutines/CoroutineDispatcher;Lru/mail/util/log/Logger;Lru/mail/authorizationsdk/feature/captcha/config/LudwigConfig;Lstatusnavbars/StatusNavBarHelper;Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;)V", "getConfig", "()Lru/mail/authorizationsdk/feature/captcha/config/LudwigConfig;", "getStatusNavBarHelper", "()Lstatusnavbars/StatusNavBarHelper;", "getAnalytics", "()Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;", "userEnterScreenTimeMls", "", "log", "getLog", "()Lru/mail/util/log/Logger;", "args", "Landroid/os/Bundle;", "imitationHost", "", "getImitationHost", "()Ljava/lang/String;", "ludwigToken", "getLudwigToken", "ludochkaConfig", "Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaConfig;", "getLudochkaConfig$authorizationsdk_release", "()Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaConfig;", "redirectPicker", "Lru/mail/authorizationsdk/feature/core/presentation/ExternalAuthRedirectPicker;", "resultFlow", "Lru/mail/march/viewmodel/MutableEventFlow;", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "getResultFlow", "()Lru/mail/march/viewmodel/MutableEventFlow;", "stateDelegate", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate;", "getStateDelegate", "()Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate;", "webViewClient", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientExecutor;", "getWebViewClient", "()Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientExecutor;", "localPage", "getLocalPage", "setLocalPage", "(Ljava/lang/String;)V", "isLoading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "webCaptchaState", "Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState;", "getWebCaptchaState", "fileReadJob", "Lkotlinx/coroutines/Job;", "analyticCompanion", "Lru/mail/authorizationsdk/feature/captcha/analytics/AnalyticCompanion;", "onStartScreen", "", "loadingPageFromFile", "context", "Landroid/content/Context;", "onDetach", "onCleared", "onEvent", "event", "Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaEvent;", "WebCaptchaState", "Factory", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebCaptchaComposeViewModel extends ViewModel {
    public static final int $stable = 0;

    @NotNull
    private static final String LUDVIG_LOCAL_PAGE = "iframe_ludochka.html";

    @NotNull
    public static final String LUDWIG_TOKEN = "ludwig_token";

    @NotNull
    private final AnalyticCompanion analyticCompanion;

    @NotNull
    private final LudwigCaptchaAnalyticEvents analytics;

    @Nullable
    private final Bundle args;

    @NotNull
    private final LudwigConfig config;

    @Nullable
    private Job fileReadJob;

    @NotNull
    private final String imitationHost;

    @NotNull
    private final MutableStateFlow<Boolean> isLoading;

    @NotNull
    private String localPage;

    @NotNull
    private final Logger log;

    @NotNull
    private final LudochkaConfig ludochkaConfig;

    @NotNull
    private final String ludwigToken;

    @NotNull
    private final ExternalAuthRedirectPicker redirectPicker;

    @NotNull
    private final MutableEventFlow<LudwigCaptchaResult> resultFlow;

    @NotNull
    private final WebViewClientStateDelegate stateDelegate;

    @NotNull
    private final StatusNavBarHelper statusNavBarHelper;
    private final long userEnterScreenTimeMls;

    @NotNull
    private final MutableStateFlow<WebCaptchaState> webCaptchaState;

    @NotNull
    private final WebViewClientExecutor webViewClient;

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate$State;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel$1", f = "WebCaptchaComposeViewModel.kt", i = {0, 1, 2, 3, 4, 5, 6, 7}, l = {77, 78, 82, 90, 95, 99, 100, 101}, m = "invokeSuspend", n = {"it", "it", "it", "it", "it", "it", "it", "it"}, s = {"L$0", "L$0", "L$0", "L$0", "L$0", "L$0", "L$0", "L$0"}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<WebViewClientStateDelegate.State, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = WebCaptchaComposeViewModel.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Code duplicated, block: B:35:0x00e0  */
        /* JADX WARN: Code duplicated, block: B:47:0x0145  */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0071, code lost:
        
            if (r6.emit(r2, r5) == r1) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0091, code lost:
        
            if (r6.emit(r2, r5) == r1) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0107, code lost:
        
            if (r6.emit(r2, r5) == r1) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x015b, code lost:
        
            if (r6.emit(r2, r5) == r1) goto L49;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                Method dump skipped, instruction units count: 382
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(WebViewClientStateDelegate.State state, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes15.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$Factory;", "Lru/mail/authorizationsdk/di/viewmodel/CommonViewModelFactory;", "Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel;", "create", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @AssistedFactory
    public interface Factory extends CommonViewModelFactory<WebCaptchaComposeViewModel> {
        @Override // ru.mail.authorizationsdk.di.viewmodel.CommonViewModelFactory
        @NotNull
        WebCaptchaComposeViewModel create(@NotNull SavedStateHandle savedStateHandle);
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState;", "", "<init>", "()V", "LoadedLocalPage", "ErrorLoading", "Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState$ErrorLoading;", "Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState$LoadedLocalPage;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Stable
    public static abstract class WebCaptchaState {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState$ErrorLoading;", "Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class ErrorLoading extends WebCaptchaState {
            public static final int $stable = 0;

            @NotNull
            public static final ErrorLoading INSTANCE = new ErrorLoading();

            private ErrorLoading() {
                super(null);
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof ErrorLoading);
            }

            public int hashCode() {
                return 1866881728;
            }

            @NotNull
            public String toString() {
                return "ErrorLoading";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState$LoadedLocalPage;", "Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState;", "url", "", "<init>", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class LoadedLocalPage extends WebCaptchaState {
            public static final int $stable = 0;

            @NotNull
            private final String url;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LoadedLocalPage(@NotNull String url) {
                super(null);
                Intrinsics.checkNotNullParameter(url, "url");
                this.url = url;
            }

            public static /* synthetic */ LoadedLocalPage copy$default(LoadedLocalPage loadedLocalPage, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = loadedLocalPage.url;
                }
                return loadedLocalPage.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getUrl() {
                return this.url;
            }

            @NotNull
            public final LoadedLocalPage copy(@NotNull String url) {
                Intrinsics.checkNotNullParameter(url, "url");
                return new LoadedLocalPage(url);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof LoadedLocalPage) && Intrinsics.areEqual(this.url, ((LoadedLocalPage) other).url);
            }

            @NotNull
            public final String getUrl() {
                return this.url;
            }

            public int hashCode() {
                return this.url.hashCode();
            }

            @NotNull
            public String toString() {
                return "LoadedLocalPage(url=" + this.url + ")";
            }
        }

        public /* synthetic */ WebCaptchaState(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private WebCaptchaState() {
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel$loadingPageFromFile$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel$loadingPageFromFile$1", f = "WebCaptchaComposeViewModel.kt", i = {2}, l = {116, 119, 122}, m = "invokeSuspend", n = {"e"}, s = {"L$0"}, v = 1)
    static final class C16231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C16231(Context context, Continuation<? super C16231> continuation) {
            super(2, continuation);
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WebCaptchaComposeViewModel.this.new C16231(this.$context, continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0067, code lost:
        
            if (r7.emit(r1, r6) == r0) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x009e, code lost:
        
            if (r1.emit(r3, r6) == r0) goto L23;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L2d
                if (r1 == r4) goto L29
                if (r1 == r3) goto L22
                if (r1 != r2) goto L1a
                java.lang.Object r0 = r6.L$0
                java.io.IOException r0 = (java.io.IOException) r0
                kotlin.ResultKt.throwOnFailure(r7)
                goto La1
            L1a:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L22:
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.io.IOException -> L27
                goto Laf
            L27:
                r7 = move-exception
                goto L6a
            L29:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L43
            L2d:
                kotlin.ResultKt.throwOnFailure(r7)
                ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel r7 = ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.this
                kotlinx.coroutines.flow.MutableStateFlow r7 = r7.isLoading()
                java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
                r6.label = r4
                java.lang.Object r7 = r7.emit(r1, r6)
                if (r7 != r0) goto L43
                goto La0
            L43:
                ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel r7 = ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.this     // Catch: java.io.IOException -> L27
                android.content.Context r1 = r6.$context     // Catch: java.io.IOException -> L27
                java.lang.String r4 = "iframe_ludochka.html"
                java.lang.String r1 = ru.mail.android_utils.IOCompatUtils.readAsset(r1, r4)     // Catch: java.io.IOException -> L27
                r7.setLocalPage(r1)     // Catch: java.io.IOException -> L27
                ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel r7 = ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.this     // Catch: java.io.IOException -> L27
                kotlinx.coroutines.flow.MutableStateFlow r7 = r7.getWebCaptchaState()     // Catch: java.io.IOException -> L27
                ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel$WebCaptchaState$LoadedLocalPage r1 = new ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel$WebCaptchaState$LoadedLocalPage     // Catch: java.io.IOException -> L27
                ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel r4 = ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.this     // Catch: java.io.IOException -> L27
                java.lang.String r4 = r4.getLocalPage()     // Catch: java.io.IOException -> L27
                r1.<init>(r4)     // Catch: java.io.IOException -> L27
                r6.label = r3     // Catch: java.io.IOException -> L27
                java.lang.Object r7 = r7.emit(r1, r6)     // Catch: java.io.IOException -> L27
                if (r7 != r0) goto Laf
                goto La0
            L6a:
                ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel r1 = ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.this
                ru.mail.util.log.Logger r1 = r1.getLog()
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r4.<init>()
                java.lang.String r5 = "Read Local page exception "
                r4.append(r5)
                r4.append(r7)
                java.lang.String r4 = r4.toString()
                r5 = 0
                ru.mail.util.log.Logger.e$default(r1, r4, r5, r3, r5)
                ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel r1 = ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.this
                ru.mail.march.viewmodel.MutableEventFlow r1 = r1.getResultFlow()
                ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaResult$Error r3 = new ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaResult$Error
                java.lang.String r4 = "CriticalErrorNoLocalPage"
                r3.<init>(r4)
                java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
                r6.L$0 = r7
                r6.label = r2
                java.lang.Object r7 = r1.emit(r3, r6)
                if (r7 != r0) goto La1
            La0:
                return r0
            La1:
                ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel r7 = ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.this
                kotlinx.coroutines.flow.MutableStateFlow r7 = r7.isLoading()
                r0 = 0
                java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
                r7.setValue(r0)
            Laf:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.C16231.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C16231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel$onEvent$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel$onEvent$1", f = "WebCaptchaComposeViewModel.kt", i = {0, 2}, l = {152, 159, 168}, m = "invokeSuspend", n = {"userCaptchaDurationMls", "userCaptchaDurationMls"}, s = {"J$0", "J$0"}, v = 1)
    static final class C16241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LudochkaEvent $event;
        long J$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C16241(LudochkaEvent ludochkaEvent, Continuation<? super C16241> continuation) {
            super(2, continuation);
            this.$event = ludochkaEvent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WebCaptchaComposeViewModel.this.new C16241(this.$event, continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0085, code lost:
        
            if (r8.emit(r4, r7) == r0) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00f2, code lost:
        
            if (r8.emit(r1, r7) == r0) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x012b, code lost:
        
            if (r8.emit(r1, r7) == r0) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x012d, code lost:
        
            return r0;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                Method dump skipped, instruction units count: 305
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel.C16241.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C16241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @AssistedInject
    public WebCaptchaComposeViewModel(@Assisted @NotNull SavedStateHandle savedStateHandle, @ViewModelDispatcher @NotNull CoroutineDispatcher viewModelDispatcher, @NotNull Logger baseLogger, @NotNull LudwigConfig config, @NotNull StatusNavBarHelper statusNavBarHelper, @NotNull LudwigCaptchaAnalyticEvents analytics, @NotNull AuthorizationSdkUrlsResolver urlsResolver) {
        Intrinsics.checkNotNullParameter(savedStateHandle, "savedStateHandle");
        Intrinsics.checkNotNullParameter(viewModelDispatcher, "viewModelDispatcher");
        Intrinsics.checkNotNullParameter(baseLogger, "baseLogger");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(statusNavBarHelper, "statusNavBarHelper");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(urlsResolver, "urlsResolver");
        this.config = config;
        this.statusNavBarHelper = statusNavBarHelper;
        this.analytics = analytics;
        this.userEnterScreenTimeMls = System.currentTimeMillis();
        Logger loggerCreateLogger = baseLogger.createLogger("WebCaptchaComposeViewModel");
        this.log = loggerCreateLogger;
        Bundle bundle = (Bundle) savedStateHandle.get(DestBase.PARAMS);
        this.args = bundle;
        this.imitationHost = urlsResolver.getCaptchaImitationHost();
        String string = (bundle == null || (string = bundle.getString(LUDWIG_TOKEN)) == null) ? "" : string;
        this.ludwigToken = string;
        this.ludochkaConfig = new LudochkaConfig(string, urlsResolver.getLudwigUrl());
        this.redirectPicker = new ExternalAuthRedirectPicker();
        this.resultFlow = ExtensionsKt.sideEffect(ViewModelKt.getViewModelScope(this), viewModelDispatcher, loggerCreateLogger, new Flow[0]);
        WebViewClientStateDelegate webViewClientStateDelegate = new WebViewClientStateDelegate();
        this.stateDelegate = webViewClientStateDelegate;
        this.webViewClient = new WebViewClientExecutor(loggerCreateLogger, webViewClientStateDelegate, analytics);
        this.localPage = "";
        this.isLoading = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this.webCaptchaState = StateFlowKt.MutableStateFlow(null);
        this.analyticCompanion = new AnalyticCompanion(false, false, 3, null);
        FlowKt.launchIn(FlowKt.onEach(FlowKt.filterNotNull(webViewClientStateDelegate.getState()), new AnonymousClass1(null)), ViewModelKt.getViewModelScope(this));
    }

    @NotNull
    public final LudwigCaptchaAnalyticEvents getAnalytics() {
        return this.analytics;
    }

    @NotNull
    public final LudwigConfig getConfig() {
        return this.config;
    }

    @NotNull
    public final String getImitationHost() {
        return this.imitationHost;
    }

    @NotNull
    public final String getLocalPage() {
        return this.localPage;
    }

    @NotNull
    public final Logger getLog() {
        return this.log;
    }

    @NotNull
    /* JADX INFO: renamed from: getLudochkaConfig$authorizationsdk_release, reason: from getter */
    public final LudochkaConfig getLudochkaConfig() {
        return this.ludochkaConfig;
    }

    @NotNull
    public final String getLudwigToken() {
        return this.ludwigToken;
    }

    @NotNull
    public final MutableEventFlow<LudwigCaptchaResult> getResultFlow() {
        return this.resultFlow;
    }

    @NotNull
    public final WebViewClientStateDelegate getStateDelegate() {
        return this.stateDelegate;
    }

    @NotNull
    public final StatusNavBarHelper getStatusNavBarHelper() {
        return this.statusNavBarHelper;
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
        if (this.localPage.length() > 0) {
            return;
        }
        Logger.d$default(this.log, "loading Page from file", null, 2, null);
        this.fileReadJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C16231(context, null), 2, null);
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

    public final void onEvent(@NotNull LudochkaEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new C16241(event, null), 3, null);
    }

    public final void onStartScreen() {
        this.redirectPicker.reset();
    }

    public final void setLocalPage(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localPage = str;
    }
}
