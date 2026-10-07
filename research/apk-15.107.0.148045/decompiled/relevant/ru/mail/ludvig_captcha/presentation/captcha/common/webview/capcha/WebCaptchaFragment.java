package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.annotation.MainThread;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.FlowExtKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import com.example.ludvig_captcha.R;
import com.vk.superapp.api.internal.requests.utils.WebRequestHelper;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ludvig_captcha.external.AnalyticEvents;
import ru.mail.ludvig_captcha.external.LudwigAnalyticsCallback;
import ru.mail.ludvig_captcha.external.LudwigSdkInitializer;
import ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.params.LudwigHost;
import ru.mail.ludvig_captcha.utils.KeyboardVisibilityHelper;
import ru.mail.ludvig_captcha.utils.ludochka.LudochkaConfig;
import ru.mail.ludvig_captcha.utils.ludochka.LudochkaEvent;
import ru.mail.ludvig_captcha.utils.ludochka.LudochkaEventListener;
import ru.mail.ludvig_captcha.utils.ludochka.LudochkaMediator;
import ru.mail.r7editor.impl.presentation.webview.R7WebViewConfigInjector;
import ru.mail.test.recognition.TestRecognition;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 02\u00020\u00012\u00020\u0002:\u00010B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000eH\u0003J$\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\u0010\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u000eH\u0002J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020\u0019H\u0002J\b\u0010)\u001a\u00020\u0019H\u0002J\b\u0010*\u001a\u00020\u0019H\u0002J\n\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010-\u001a\u00020\u0019H\u0016J\u0012\u0010.\u001a\u00020\u00192\b\u0010'\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010/\u001a\u00020\u0019H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0013\u001a\u0004\u0018\u00010\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016¨\u00061"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment;", "Landroidx/fragment/app/Fragment;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewInitFailHandler;", "<init>", "()V", "webViewProp", "Landroid/webkit/WebView;", "viewModel", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel;", "retryView", "Landroid/view/View;", "progressBar", "Landroid/widget/FrameLayout;", "ludwigToken", "", "getLudwigToken", "()Ljava/lang/String;", "ludwigToken$delegate", "Lkotlin/Lazy;", "ludwigHost", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/params/LudwigHost;", "getLudwigHost", "()Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/params/LudwigHost;", "ludwigHost$delegate", "onResult", "", "result", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/Result;", "errorMessage", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "loadLocalLudvigPage", "localPage", "initToolbar", "rootView", "backPressExecute", "initKeyboardListener", "initViewModel", "getCurrentActivity", "Landroidx/fragment/app/FragmentActivity;", "justCloseScreen", "initWebView", "onDetach", "Companion", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWebCapchaFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebCapchaFragment.kt\nru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment\n+ 2 View.kt\nandroidx/core/view/ViewKt\n*L\n1#1,331:1\n257#2,2:332\n*S KotlinDebug\n*F\n+ 1 WebCapchaFragment.kt\nru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment\n*L\n163#1:332,2\n*E\n"})
public final class WebCaptchaFragment extends Fragment implements WebViewInitFailHandler {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String EMPTY_TOKEN_ERROR_MESSAGE = "There is no ludwig token in arguments!";

    @NotNull
    private static final String ERROR = "ERROR";

    @NotNull
    private static final String IS_DOM_STORAGE_ENABLED = "IS_DOM_STORAGE_ENABLED";

    @NotNull
    private static final String IS_TEXT_ZOOM_DISABLED = "IS_TEXT_ZOOM_DISABLED";

    @NotNull
    private static final String LUDWIG_HOST = "LUDWIG_HOST";

    @NotNull
    private static final String LUDWIG_TOKEN = "LUDVIG_TOKEN";

    @NotNull
    private static final String RESULT = "RESULT";

    @NotNull
    private static final String TOOLBAR_CAPTION = "TOOLBAR_CAPTION";

    /* JADX INFO: renamed from: ludwigHost$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy ludwigHost;

    /* JADX INFO: renamed from: ludwigToken$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy ludwigToken;

    @Nullable
    private FrameLayout progressBar;

    @Nullable
    private View retryView;
    private WebCaptchaViewModel viewModel;

    @Nullable
    private WebView webViewProp;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0007J0\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0017J\u0017\u0010\u001c\u001a\u0004\u0018\u00010\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u001dJ\u0010\u0010\u0015\u001a\u00020\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u0017J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0017J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0017J\u0006\u0010 \u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment$Companion;", "", "<init>", "()V", "EMPTY_TOKEN_ERROR_MESSAGE", "", WebCaptchaFragment.TOOLBAR_CAPTION, "LUDWIG_TOKEN", WebCaptchaFragment.LUDWIG_HOST, WebCaptchaFragment.IS_DOM_STORAGE_ENABLED, WebCaptchaFragment.IS_TEXT_ZOOM_DISABLED, "ERROR", WebCaptchaFragment.RESULT, "newInstance", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment;", "ludwigToken", "toolbarCaption", "ludwigHost", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/params/LudwigHost;", "isDomStorageEnabled", "", "isTextZoomDisabled", "getArgumentsBundle", "Landroid/os/Bundle;", "ludvigToken", "getResult", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/Result;", "bundle", "getIsDomStorageEnabled", "(Landroid/os/Bundle;)Ljava/lang/Boolean;", "getLudwigToken", "getErrorMessage", "getResultKey", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nWebCapchaFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebCapchaFragment.kt\nru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,331:1\n1400#2,2:332\n*S KotlinDebug\n*F\n+ 1 WebCapchaFragment.kt\nru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment$Companion\n*L\n312#1:332,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Bundle getArgumentsBundle$default(Companion companion, String str, String str2, LudwigHost ludwigHost, boolean z10, int i10, Object obj) {
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            if ((i10 & 4) != 0) {
                ludwigHost = null;
            }
            if ((i10 & 8) != 0) {
                z10 = false;
            }
            return companion.getArgumentsBundle(str, str2, ludwigHost, z10);
        }

        public static /* synthetic */ WebCaptchaFragment newInstance$default(Companion companion, String str, String str2, LudwigHost ludwigHost, boolean z10, boolean z11, int i10, Object obj) {
            if ((i10 & 4) != 0) {
                ludwigHost = null;
            }
            return companion.newInstance(str, str2, ludwigHost, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? false : z11);
        }

        @NotNull
        public final Bundle getArgumentsBundle(@NotNull String ludvigToken, @Nullable String toolbarCaption, @Nullable LudwigHost ludwigHost, boolean isDomStorageEnabled) {
            Intrinsics.checkNotNullParameter(ludvigToken, "ludvigToken");
            return BundleKt.bundleOf(TuplesKt.to(WebCaptchaFragment.LUDWIG_TOKEN, ludvigToken), TuplesKt.to(WebCaptchaFragment.TOOLBAR_CAPTION, toolbarCaption), TuplesKt.to(WebCaptchaFragment.LUDWIG_HOST, ludwigHost), TuplesKt.to(WebCaptchaFragment.IS_DOM_STORAGE_ENABLED, Boolean.valueOf(isDomStorageEnabled)));
        }

        @Nullable
        public final String getErrorMessage(@Nullable Bundle bundle) {
            if (bundle != null) {
                return bundle.getString("ERROR");
            }
            return null;
        }

        @Nullable
        public final Boolean getIsDomStorageEnabled(@Nullable Bundle bundle) {
            if (bundle != null) {
                return Boolean.valueOf(bundle.getBoolean(WebCaptchaFragment.IS_DOM_STORAGE_ENABLED));
            }
            return null;
        }

        @Nullable
        public final String getLudwigToken(@Nullable Bundle bundle) {
            if (bundle != null) {
                return bundle.getString(WebCaptchaFragment.LUDWIG_TOKEN);
            }
            return null;
        }

        @Nullable
        public final Result getResult(@Nullable Bundle bundle) {
            String string;
            if (bundle != null && (string = bundle.getString(WebCaptchaFragment.RESULT, null)) != null) {
                for (Result result : Result.values()) {
                    if (Intrinsics.areEqual(result.name(), string)) {
                        return result;
                    }
                }
            }
            return null;
        }

        @NotNull
        public final String getResultKey() {
            return "WEB_CAPTCHA_FRAGMENT_LUDWIG_SUPPORT_RESULT_KEY";
        }

        public final boolean isTextZoomDisabled(@Nullable Bundle bundle) {
            if (bundle != null) {
                return bundle.getBoolean(WebCaptchaFragment.IS_TEXT_ZOOM_DISABLED);
            }
            return false;
        }

        @JvmStatic
        @NotNull
        public final WebCaptchaFragment newInstance(@NotNull String ludwigToken, @Nullable String toolbarCaption, @Nullable LudwigHost ludwigHost, boolean isDomStorageEnabled, boolean isTextZoomDisabled) {
            Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
            WebCaptchaFragment webCaptchaFragment = new WebCaptchaFragment();
            webCaptchaFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(WebCaptchaFragment.LUDWIG_TOKEN, ludwigToken), TuplesKt.to(WebCaptchaFragment.TOOLBAR_CAPTION, toolbarCaption), TuplesKt.to(WebCaptchaFragment.LUDWIG_HOST, ludwigHost), TuplesKt.to(WebCaptchaFragment.IS_DOM_STORAGE_ENABLED, Boolean.valueOf(isDomStorageEnabled)), TuplesKt.to(WebCaptchaFragment.IS_TEXT_ZOOM_DISABLED, Boolean.valueOf(isTextZoomDisabled))));
            return webCaptchaFragment;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment$onCreateView$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaViewModel$WebCaptchaState;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment$onCreateView$1", f = "WebCapchaFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nWebCapchaFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebCapchaFragment.kt\nru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment$onCreateView$1\n+ 2 View.kt\nandroidx/core/view/ViewKt\n*L\n1#1,331:1\n257#2,2:332\n257#2,2:334\n257#2,2:336\n*S KotlinDebug\n*F\n+ 1 WebCapchaFragment.kt\nru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment$onCreateView$1\n*L\n124#1:332,2\n130#1:334,2\n137#1:336,2\n*E\n"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<WebCaptchaViewModel.WebCaptchaState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = WebCaptchaFragment.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            WebCaptchaViewModel.WebCaptchaState webCaptchaState = (WebCaptchaViewModel.WebCaptchaState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            WebCaptchaViewModel webCaptchaViewModel = null;
            if (webCaptchaState instanceof WebCaptchaViewModel.WebCaptchaState.LoadedLocalPage) {
                View view = WebCaptchaFragment.this.retryView;
                if (view != null) {
                    view.setVisibility(8);
                }
                if (((WebCaptchaViewModel.WebCaptchaState.LoadedLocalPage) webCaptchaState).getNeedLoadPageAgain()) {
                    WebCaptchaFragment webCaptchaFragment = WebCaptchaFragment.this;
                    WebCaptchaViewModel webCaptchaViewModel2 = webCaptchaFragment.viewModel;
                    if (webCaptchaViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        webCaptchaViewModel = webCaptchaViewModel2;
                    }
                    webCaptchaFragment.loadLocalLudvigPage(webCaptchaViewModel.getLocalPage());
                }
                Boxing.boxInt(Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "init loading Ludwig"));
            } else if (webCaptchaState instanceof WebCaptchaViewModel.WebCaptchaState.Captcha) {
                View view2 = WebCaptchaFragment.this.retryView;
                if (view2 != null) {
                    view2.setVisibility(8);
                }
                WebView webView = WebCaptchaFragment.this.webViewProp;
                String url = webView != null ? webView.getUrl() : null;
                if (url == null || url.length() == 0) {
                    WebCaptchaViewModel webCaptchaViewModel3 = WebCaptchaFragment.this.viewModel;
                    if (webCaptchaViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        webCaptchaViewModel = webCaptchaViewModel3;
                    }
                    webCaptchaViewModel.forceReloadAfterRestartActivity();
                }
            } else if (webCaptchaState instanceof WebCaptchaViewModel.WebCaptchaState.ErrorLoading) {
                View view3 = WebCaptchaFragment.this.retryView;
                if (view3 != null) {
                    view3.setVisibility(0);
                }
            } else {
                if (!Intrinsics.areEqual(webCaptchaState, WebCaptchaViewModel.WebCaptchaState.CriticalErrorNoLocalPage.INSTANCE)) {
                    throw new NoWhenBranchMatchedException();
                }
                WebCaptchaFragment.this.onResult(Result.ERROR, "Can't read asset with Ludwig page from file!");
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(WebCaptchaViewModel.WebCaptchaState webCaptchaState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(webCaptchaState, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment$onCreateView$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment$onCreateView$2", f = "WebCapchaFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nWebCapchaFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebCapchaFragment.kt\nru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment$onCreateView$2\n+ 2 View.kt\nandroidx/core/view/ViewKt\n*L\n1#1,331:1\n257#2,2:332\n*S KotlinDebug\n*F\n+ 1 WebCapchaFragment.kt\nru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebCaptchaFragment$onCreateView$2\n*L\n146#1:332,2\n*E\n"})
    static final class C22922 extends SuspendLambda implements Function2<Boolean, Continuation<? super Unit>, Object> {
        /* synthetic */ boolean Z$0;
        int label;

        C22922(Continuation<? super C22922> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C22922 c22922 = WebCaptchaFragment.this.new C22922(continuation);
            c22922.Z$0 = ((Boolean) obj).booleanValue();
            return c22922;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Boolean bool, Continuation<? super Unit> continuation) {
            return invoke(bool.booleanValue(), continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            boolean z10 = this.Z$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FrameLayout frameLayout = WebCaptchaFragment.this.progressBar;
            if (frameLayout != null) {
                frameLayout.setVisibility(z10 ? 0 : 8);
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(boolean z10, Continuation<? super Unit> continuation) {
            return ((C22922) create(Boolean.valueOf(z10), continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment$onResult$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment$onResult$1", f = "WebCapchaFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C22931 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $errorMessage;
        final /* synthetic */ Result $result;
        int label;

        /* JADX INFO: renamed from: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment$onResult$1$WhenMappings */
        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Result.values().length];
                try {
                    iArr[Result.SUCCESS.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Result.ERROR.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Result.CANCEL.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C22931(Result result, String str, Continuation<? super C22931> continuation) {
            super(2, continuation);
            this.$result = result;
            this.$errorMessage = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return WebCaptchaFragment.this.new C22931(this.$result, this.$errorMessage, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Bundle bundleBundleOf;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            long jCurrentTimeMillis = System.currentTimeMillis();
            WebCaptchaViewModel webCaptchaViewModel = WebCaptchaFragment.this.viewModel;
            WebCaptchaViewModel webCaptchaViewModel2 = null;
            if (webCaptchaViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                webCaptchaViewModel = null;
            }
            long userEnterScreenTimeMls = jCurrentTimeMillis - webCaptchaViewModel.getUserEnterScreenTimeMls();
            int i10 = WhenMappings.$EnumSwitchMapping$0[this.$result.ordinal()];
            if (i10 == 1) {
                Boolean isDomStorageEnabled = WebCaptchaFragment.INSTANCE.getIsDomStorageEnabled(WebCaptchaFragment.this.getArguments());
                AnalyticEvents.CaptchaSuccessDone captchaSuccessDone = new AnalyticEvents.CaptchaSuccessDone(userEnterScreenTimeMls, isDomStorageEnabled != null ? isDomStorageEnabled.booleanValue() : false);
                WebCaptchaViewModel webCaptchaViewModel3 = WebCaptchaFragment.this.viewModel;
                if (webCaptchaViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    webCaptchaViewModel2 = webCaptchaViewModel3;
                }
                LudwigAnalyticsCallback analytics = webCaptchaViewModel2.getAnalytics();
                if (analytics != null) {
                    analytics.onAnalyticEvent(captchaSuccessDone.getEventName(), captchaSuccessDone.getParams());
                }
                bundleBundleOf = BundleKt.bundleOf(TuplesKt.to(WebCaptchaFragment.LUDWIG_TOKEN, WebCaptchaFragment.this.getLudwigToken()), TuplesKt.to(WebCaptchaFragment.RESULT, "SUCCESS"));
            } else if (i10 == 2) {
                String str = this.$errorMessage;
                if (str == null) {
                    str = "";
                }
                Boolean isDomStorageEnabled2 = WebCaptchaFragment.INSTANCE.getIsDomStorageEnabled(WebCaptchaFragment.this.getArguments());
                AnalyticEvents.CaptchaShowCriticalError captchaShowCriticalError = new AnalyticEvents.CaptchaShowCriticalError(str, userEnterScreenTimeMls, isDomStorageEnabled2 != null ? isDomStorageEnabled2.booleanValue() : false);
                WebCaptchaViewModel webCaptchaViewModel4 = WebCaptchaFragment.this.viewModel;
                if (webCaptchaViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    webCaptchaViewModel2 = webCaptchaViewModel4;
                }
                LudwigAnalyticsCallback analytics2 = webCaptchaViewModel2.getAnalytics();
                if (analytics2 != null) {
                    analytics2.onAnalyticEvent(captchaShowCriticalError.getEventName(), captchaShowCriticalError.getParams());
                }
                bundleBundleOf = BundleKt.bundleOf(TuplesKt.to("ERROR", this.$errorMessage), TuplesKt.to(WebCaptchaFragment.RESULT, "ERROR"));
            } else {
                if (i10 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                Boolean isDomStorageEnabled3 = WebCaptchaFragment.INSTANCE.getIsDomStorageEnabled(WebCaptchaFragment.this.getArguments());
                AnalyticEvents.CaptchaCanceledByUser captchaCanceledByUser = new AnalyticEvents.CaptchaCanceledByUser(userEnterScreenTimeMls, isDomStorageEnabled3 != null ? isDomStorageEnabled3.booleanValue() : false);
                WebCaptchaViewModel webCaptchaViewModel5 = WebCaptchaFragment.this.viewModel;
                if (webCaptchaViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    webCaptchaViewModel2 = webCaptchaViewModel5;
                }
                LudwigAnalyticsCallback analytics3 = webCaptchaViewModel2.getAnalytics();
                if (analytics3 != null) {
                    analytics3.onAnalyticEvent(captchaCanceledByUser.getEventName(), captchaCanceledByUser.getParams());
                }
                Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "onCaptchaCanceledByUser");
                bundleBundleOf = BundleKt.bundleOf(TuplesKt.to(WebCaptchaFragment.RESULT, "CANCEL"));
            }
            Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "Result = " + this.$result.name());
            WebCaptchaFragment.this.getParentFragmentManager().setFragmentResult(WebCaptchaFragment.INSTANCE.getResultKey(), bundleBundleOf);
            WebCaptchaFragment.this.getParentFragmentManager().popBackStackImmediate();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C22931) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    public WebCaptchaFragment() {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        this.ludwigToken = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WebCaptchaFragment.ludwigToken_delegate$lambda$0(this.f88447a);
            }
        });
        this.ludwigHost = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WebCaptchaFragment.ludwigHost_delegate$lambda$0(this.f88448a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void backPressExecute() {
        onResult$default(this, Result.CANCEL, null, 2, null);
    }

    private final LudwigHost getLudwigHost() {
        return (LudwigHost) this.ludwigHost.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getLudwigToken() {
        return (String) this.ludwigToken.getValue();
    }

    private final void initKeyboardListener() {
        new KeyboardVisibilityHelperDelegate(this, new KeyboardVisibilityHelper.KeyboardVisibilityListener() { // from class: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment$initKeyboardListener$keyboardVisibilityListener$1
            @Override // ru.mail.ludvig_captcha.utils.KeyboardVisibilityHelper.KeyboardVisibilityListener
            public void onKeyboardHidden() {
                ViewParent parent;
                View view = this.this$0.retryView;
                if (view == null || (parent = view.getParent()) == null) {
                    return;
                }
                parent.requestLayout();
            }

            @Override // ru.mail.ludvig_captcha.utils.KeyboardVisibilityHelper.KeyboardVisibilityListener
            public void onKeyboardShown() {
                View view = this.this$0.retryView;
                if (view != null) {
                    view.setVisibility(8);
                }
            }
        }, new Function0() { // from class: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f88449a.requireActivity();
            }
        });
    }

    private final void initToolbar(ViewGroup rootView) {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.f17526b);
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString(TOOLBAR_CAPTION) : null;
        if (string != null) {
            Intrinsics.checkNotNull(toolbar);
            toolbar.setVisibility(0);
            toolbar.setTitle(string);
            toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), R.color.f17524b));
            Drawable navigationIcon = toolbar.getNavigationIcon();
            if (navigationIcon != null) {
                navigationIcon.setTint(ContextCompat.getColor(requireContext(), R.color.f17523a));
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f88450a.backPressExecute();
                }
            });
        }
    }

    private final void initViewModel() {
        String imitationHost;
        LudwigHost ludwigHost = getLudwigHost();
        if (ludwigHost == null || (imitationHost = ludwigHost.getImitationHost()) == null) {
            imitationHost = TestRecognition.getIsTest() ? "https://access.mini-mail.ru/" : "https://com.mail.ru/";
        }
        LudwigAnalyticsCallback webViewCaptchaAnalytics$ludvig_captcha_release = LudwigSdkInitializer.INSTANCE.getWebViewCaptchaAnalytics$ludvig_captcha_release();
        if (webViewCaptchaAnalytics$ludvig_captcha_release == null) {
            Log.e(LudwigSdkInitializer.LUDWIG_SDK_TAG, "Ludwig analytics not initialized!");
        }
        WebCaptchaViewModel webCaptchaViewModel = (WebCaptchaViewModel) new ViewModelProvider(this, new WebCapchaViewModelFactory(imitationHost, webViewCaptchaAnalytics$ludvig_captcha_release)).get(WebCaptchaViewModel.class);
        this.viewModel = webCaptchaViewModel;
        if (webCaptchaViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            webCaptchaViewModel = null;
        }
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        webCaptchaViewModel.loadingPageFromFile(contextRequireContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadLocalLudvigPage(String localPage) {
        WebView webView = this.webViewProp;
        if (webView != null) {
            WebCaptchaViewModel webCaptchaViewModel = this.viewModel;
            if (webCaptchaViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                webCaptchaViewModel = null;
            }
            webView.loadDataWithBaseURL(webCaptchaViewModel.getImitationHost(), localPage, WebRequestHelper.MIME_HTML, R7WebViewConfigInjector.UTF_8, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LudwigHost ludwigHost_delegate$lambda$0(WebCaptchaFragment webCaptchaFragment) {
        if (Build.VERSION.SDK_INT >= 33) {
            Bundle arguments = webCaptchaFragment.getArguments();
            if (arguments != null) {
                return (LudwigHost) arguments.getParcelable(LUDWIG_HOST, LudwigHost.class);
            }
            return null;
        }
        Bundle arguments2 = webCaptchaFragment.getArguments();
        if (arguments2 != null) {
            return (LudwigHost) arguments2.getParcelable(LUDWIG_HOST);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ludwigToken_delegate$lambda$0(WebCaptchaFragment webCaptchaFragment) {
        String ludwigToken = INSTANCE.getLudwigToken(webCaptchaFragment.getArguments());
        if (ludwigToken == null || ludwigToken.length() == 0) {
            webCaptchaFragment.onResult(Result.ERROR, EMPTY_TOKEN_ERROR_MESSAGE);
        }
        return ludwigToken == null ? "" : ludwigToken;
    }

    @JvmStatic
    @NotNull
    public static final WebCaptchaFragment newInstance(@NotNull String str, @Nullable String str2, @Nullable LudwigHost ludwigHost, boolean z10, boolean z11) {
        return INSTANCE.newInstance(str, str2, ludwigHost, z10, z11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @MainThread
    public final void onResult(Result result, String errorMessage) {
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), Dispatchers.getMain(), null, new C22931(result, errorMessage, null), 2, null);
    }

    static /* synthetic */ void onResult$default(WebCaptchaFragment webCaptchaFragment, Result result, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        webCaptchaFragment.onResult(result, str);
    }

    @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebViewInitFailHandler
    public void initWebView(@Nullable View rootView) {
        LudochkaConfig ludochkaConfig;
        WebSettings settings;
        Log.i(LudwigSdkInitializer.LUDWIG_SDK_TAG, "Starting initialization captcha.");
        WebView webView = new WebView(requireActivity());
        this.webViewProp = webView;
        webView.setVisibility(0);
        WebView webView2 = this.webViewProp;
        if (webView2 != null && (settings = webView2.getSettings()) != null) {
            settings.setJavaScriptEnabled(true);
            settings.setSavePassword(false);
            Companion companion = INSTANCE;
            Boolean isDomStorageEnabled = companion.getIsDomStorageEnabled(getArguments());
            settings.setDomStorageEnabled(isDomStorageEnabled != null ? isDomStorageEnabled.booleanValue() : false);
            if (companion.isTextZoomDisabled(getArguments())) {
                settings.setTextZoom(100);
                settings.setSupportZoom(false);
            }
        }
        FrameLayout frameLayout = rootView != null ? (FrameLayout) rootView.findViewById(R.id.f17527c) : null;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 0);
        if (frameLayout != null) {
            frameLayout.addView(this.webViewProp, layoutParams);
        }
        WebView webView3 = this.webViewProp;
        if (webView3 != null) {
            WebCaptchaViewModel webCaptchaViewModel = this.viewModel;
            if (webCaptchaViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                webCaptchaViewModel = null;
            }
            webView3.setWebViewClient(webCaptchaViewModel.getWebViewClient());
        }
        if (getLudwigHost() == null) {
            ludochkaConfig = new LudochkaConfig(getLudwigToken(), null, 2, null);
        } else {
            String ludwigToken = getLudwigToken();
            LudwigHost ludwigHost = getLudwigHost();
            Intrinsics.checkNotNull(ludwigHost);
            ludochkaConfig = new LudochkaConfig(ludwigToken, ludwigHost.getUrl());
        }
        WebView webView4 = this.webViewProp;
        if (webView4 != null) {
            webView4.addJavascriptInterface(new LudochkaMediator(ludochkaConfig, new LudochkaEventListener() { // from class: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment.initWebView.2
                @Override // ru.mail.ludvig_captcha.utils.ludochka.LudochkaEventListener
                public void onEvent(LudochkaEvent event) {
                    Intrinsics.checkNotNullParameter(event, "event");
                    if (event instanceof LudochkaEvent.Success) {
                        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "Ludochka message = " + event);
                        WebCaptchaFragment.onResult$default(WebCaptchaFragment.this, Result.SUCCESS, null, 2, null);
                        return;
                    }
                    if (event instanceof LudochkaEvent.Error) {
                        LudochkaEvent.Error error = (LudochkaEvent.Error) event;
                        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "Ludochka message = " + event + error.getError());
                        WebCaptchaFragment.this.onResult(Result.ERROR, error.getError());
                        return;
                    }
                    if (!(event instanceof LudochkaEvent.Cancel) && !(event instanceof LudochkaEvent.Close)) {
                        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "Ludochka message = " + event);
                        return;
                    }
                    Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "Ludochka message = " + event);
                    WebCaptchaFragment.onResult$default(WebCaptchaFragment.this, Result.CANCEL, null, 2, null);
                }
            }), "LudochkaMediator");
        }
    }

    @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebViewInitFailHandler
    public /* bridge */ void initWebViewOrStartDialog(@Nullable View view) {
        super.initWebViewOrStartDialog(view);
    }

    @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebViewInitFailHandler
    public void justCloseScreen() {
        onResult$default(this, Result.CANCEL, null, 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.f17528a, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflate(...)");
        this.progressBar = (FrameLayout) viewInflate.findViewById(R.id.f17525a);
        initViewModel();
        initWebViewOrStartDialog(viewInflate);
        initKeyboardListener();
        initToolbar((ViewGroup) viewInflate);
        WebCaptchaViewModel webCaptchaViewModel = this.viewModel;
        if (webCaptchaViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            webCaptchaViewModel = null;
        }
        MutableStateFlow<WebCaptchaViewModel.WebCaptchaState> webCaptchaState = webCaptchaViewModel.getWebCaptchaState();
        Lifecycle lifecycle = getLifecycleRegistry();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "<get-lifecycle>(...)");
        FlowKt.launchIn(FlowKt.onEach(FlowKt.filterNotNull(FlowExtKt.flowWithLifecycle$default(webCaptchaState, lifecycle, null, 2, null)), new AnonymousClass1(null)), LifecycleOwnerKt.getLifecycleScope(this));
        WebCaptchaViewModel webCaptchaViewModel2 = this.viewModel;
        if (webCaptchaViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            webCaptchaViewModel2 = null;
        }
        MutableStateFlow<Boolean> mutableStateFlowIsLoading = webCaptchaViewModel2.isLoading();
        Lifecycle lifecycle2 = getLifecycleRegistry();
        Intrinsics.checkNotNullExpressionValue(lifecycle2, "<get-lifecycle>(...)");
        FlowKt.launchIn(FlowKt.onEach(FlowExtKt.flowWithLifecycle$default(mutableStateFlowIsLoading, lifecycle2, null, 2, null), new C22922(null)), LifecycleOwnerKt.getLifecycleScope(this));
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        WebCaptchaViewModel webCaptchaViewModel = this.viewModel;
        if (webCaptchaViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            webCaptchaViewModel = null;
        }
        webCaptchaViewModel.onDetach();
    }

    @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebViewInitFailHandler
    @Nullable
    public FragmentActivity getCurrentActivity() {
        return getActivity();
    }
}
