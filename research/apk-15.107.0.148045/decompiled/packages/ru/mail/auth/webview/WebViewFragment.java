package ru.mail.auth.webview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherKt;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.FlowExtKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.Authenticator.R;
import ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks;
import ru.mail.android_utils.webview.WebViewUpdateDialogCreator;
import ru.mail.auth.util.KeyboardVisibilityHelperDelegate;
import ru.mail.locator.Locator;
import ru.mail.remotelayout.data.dto.ItemDto;
import ru.mail.utils.KeyboardVisibilityHelper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H&J\b\u0010\u0017\u001a\u00020\u0014H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u000eH\u0016J\u0018\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0014H\u0016J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u000eH&J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020\u0014H\u0016J\b\u0010%\u001a\u00020\u0014H\u0016J\b\u0010&\u001a\u00020\u0014H\u0002J\b\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+H&J\u0015\u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00028\u0000H&¢\u0006\u0002\u0010.J\b\u0010/\u001a\u00020\u0014H\u0016J\b\u00100\u001a\u000201H&J\b\u00102\u001a\u000203H&J\b\u00104\u001a\u000205H&R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u00020\bX¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u00020\u000eX¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u00066À\u0006\u0003"}, d2 = {"Lru/mail/auth/webview/WebViewFragment;", "E", "", "vm", "Lru/mail/auth/webview/WebViewViewModel;", "getVm", "()Lru/mail/auth/webview/WebViewViewModel;", "webViewContainer", "Landroid/view/ViewGroup;", "getWebViewContainer", "()Landroid/view/ViewGroup;", "setWebViewContainer", "(Landroid/view/ViewGroup;)V", "retryView", "Landroid/view/View;", "getRetryView", "()Landroid/view/View;", "setRetryView", "(Landroid/view/View;)V", "setErrorDialog", "", ItemDto.KEY_TYPE_DIALOG, "Landroidx/appcompat/app/AlertDialog;", "initViewModel", "getCurrentWebView", "Landroid/webkit/WebView;", "initWebViewOrStartDialog", "rootView", "initToolbar", "title", "", "onWebViewInitFail", "initWebView", "setWebViewSettings", "settings", "Landroid/webkit/WebSettings;", "initKeyboardListener", "initBackPressedListener", "backPressedCallback", "isWebViewGoBackEnabled", "", "renderLoadingState", "loadingState", "Lru/mail/auth/webview/LoadingState;", "handleEvent", "event", "(Ljava/lang/Object;)V", "setupCookies", "getLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "requireActivity", "Landroidx/fragment/app/FragmentActivity;", "requireContext", "Landroid/content/Context;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface WebViewFragment<E> {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        @NotNull
        public static <E> WebView getCurrentWebView(@NotNull WebViewFragment<E> webViewFragment) {
            return WebViewFragment.super.getCurrentWebView();
        }

        @Deprecated
        public static <E> void initBackPressedListener(@NotNull WebViewFragment<E> webViewFragment) {
            WebViewFragment.super.initBackPressedListener();
        }

        @Deprecated
        public static <E> void initKeyboardListener(@NotNull WebViewFragment<E> webViewFragment) {
            WebViewFragment.super.initKeyboardListener();
        }

        @Deprecated
        public static <E> void initToolbar(@NotNull WebViewFragment<E> webViewFragment, @NotNull View rootView, @NotNull String title) {
            Intrinsics.checkNotNullParameter(rootView, "rootView");
            Intrinsics.checkNotNullParameter(title, "title");
            WebViewFragment.super.initToolbar(rootView, title);
        }

        @Deprecated
        public static <E> void initViewModel(@NotNull WebViewFragment<E> webViewFragment) {
            WebViewFragment.super.initViewModel();
        }

        @Deprecated
        public static <E> void initWebViewOrStartDialog(@NotNull WebViewFragment<E> webViewFragment, @NotNull View rootView) {
            Intrinsics.checkNotNullParameter(rootView, "rootView");
            WebViewFragment.super.initWebViewOrStartDialog(rootView);
        }

        @Deprecated
        public static <E> boolean isWebViewGoBackEnabled(@NotNull WebViewFragment<E> webViewFragment) {
            return WebViewFragment.super.isWebViewGoBackEnabled();
        }

        @Deprecated
        public static <E> void onWebViewInitFail(@NotNull WebViewFragment<E> webViewFragment) {
            WebViewFragment.super.onWebViewInitFail();
        }

        @Deprecated
        public static <E> void setWebViewSettings(@NotNull WebViewFragment<E> webViewFragment, @NotNull WebSettings settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            WebViewFragment.super.setWebViewSettings(settings);
        }

        @Deprecated
        public static <E> void setupCookies(@NotNull WebViewFragment<E> webViewFragment) {
            WebViewFragment.super.setupCookies();
        }
    }

    /* JADX INFO: renamed from: ru.mail.auth.webview.WebViewFragment$initViewModel$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "loadingState", "Lru/mail/auth/webview/LoadingState;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.auth.webview.WebViewFragment$initViewModel$1", f = "WebViewFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<LoadingState, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ WebViewFragment<E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(WebViewFragment<E> webViewFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = webViewFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LoadingState loadingState = (LoadingState) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.renderLoadingState(loadingState);
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(LoadingState loadingState, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(loadingState, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.auth.webview.WebViewFragment$initViewModel$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\n"}, d2 = {"<anonymous>", "", "E", "event"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.auth.webview.WebViewFragment$initViewModel$2", f = "WebViewFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<E, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ WebViewFragment<E> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(WebViewFragment<E> webViewFragment, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = webViewFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object obj2 = this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.handleEvent((E) obj2);
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(E e10, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(e10, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    default void backPressedCallback() {
        WebView currentWebView = getCurrentWebView();
        if (isWebViewGoBackEnabled() && currentWebView.canGoBack()) {
            currentWebView.goBack();
        } else if (getWebViewContainer().getChildCount() <= 1) {
            getVm2().onBackPressed();
        } else {
            getWebViewContainer().removeView(currentWebView);
            AuthWebChromeClient.INSTANCE.clear(currentWebView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static Unit initBackPressedListener$lambda$0(WebViewFragment webViewFragment, OnBackPressedCallback addCallback) {
        Intrinsics.checkNotNullParameter(addCallback, "$this$addCallback");
        webViewFragment.backPressedCallback();
        return Unit.INSTANCE;
    }

    @NotNull
    default WebView getCurrentWebView() {
        Object objLast = SequencesKt.last(ViewGroupKt.getChildren(getWebViewContainer()));
        Intrinsics.checkNotNull(objLast, "null cannot be cast to non-null type android.webkit.WebView");
        return (WebView) objLast;
    }

    @NotNull
    LifecycleOwner getLifecycleOwner();

    @NotNull
    View getRetryView();

    @NotNull
    /* JADX INFO: renamed from: getVm */
    WebViewViewModel<E> getVm2();

    @NotNull
    ViewGroup getWebViewContainer();

    void handleEvent(E event);

    default void initBackPressedListener() {
        OnBackPressedDispatcherKt.addCallback$default(requireActivity().getOnBackPressedDispatcher(), getLifecycleOwner(), false, new Function1() { // from class: ru.mail.auth.webview.v
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WebViewFragment.initBackPressedListener$lambda$0(this.f80852a, (OnBackPressedCallback) obj);
            }
        }, 2, null);
    }

    default void initKeyboardListener() {
        new KeyboardVisibilityHelperDelegate(getLifecycleOwner(), new KeyboardVisibilityHelper.KeyboardVisibilityListener(this) { // from class: ru.mail.auth.webview.WebViewFragment$initKeyboardListener$keyboardVisibilityListener$1
            final /* synthetic */ WebViewFragment<E> this$0;

            {
                this.this$0 = this;
            }

            @Override // ru.mail.utils.KeyboardVisibilityHelper.KeyboardVisibilityListener
            public void onKeyboardHidden() {
                ViewParent parent = this.this$0.getRetryView().getParent();
                if (parent != null) {
                    parent.requestLayout();
                }
            }

            @Override // ru.mail.utils.KeyboardVisibilityHelper.KeyboardVisibilityListener
            public void onKeyboardShown() {
                this.this$0.getRetryView().setVisibility(8);
            }
        }, new Function0() { // from class: ru.mail.auth.webview.u
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f80851a.requireActivity();
            }
        });
    }

    default void initToolbar(@NotNull View rootView, @NotNull String title) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(title, "title");
        Toolbar toolbar = new Toolbar(requireContext());
        toolbar.setId(R.id.toolbar);
        toolbar.setTitle(title);
        Context contextRequireContext = requireContext();
        int i10 = ru.mail.auth.R.color.colorIconPrimary;
        toolbar.setTitleTextColor(ContextCompat.getColor(contextRequireContext, i10));
        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon != null) {
            navigationIcon.setTint(ContextCompat.getColor(requireContext(), i10));
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: ru.mail.auth.webview.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f80853a.backPressedCallback();
            }
        });
        ((ViewGroup) rootView).addView(toolbar, 0);
    }

    default void initViewModel() {
        Lifecycle lifecycle = getLifecycleOwner().getLifecycle();
        LifecycleCoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(getLifecycleOwner());
        FlowKt.launchIn(FlowKt.onEach(FlowExtKt.flowWithLifecycle$default(getVm2().getLoadingState(), lifecycle, null, 2, null), new AnonymousClass1(this, null)), lifecycleScope);
        FlowKt.launchIn(FlowKt.onEach(FlowExtKt.flowWithLifecycle$default(getVm2().getEventFlow(), lifecycle, null, 2, null), new AnonymousClass2(this, null)), lifecycleScope);
    }

    void initWebView(@NotNull View rootView);

    default void initWebViewOrStartDialog(@NotNull View rootView) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        try {
            initWebView(rootView);
        } catch (RuntimeException e10) {
            Log.e("WebViewFragment", "Web view init error", e10);
            onWebViewInitFail();
        }
    }

    default boolean isWebViewGoBackEnabled() {
        return false;
    }

    default void onWebViewInitFail() {
        AlertDialog alertDialogCreateWebViewUpdateDialog = ((WebViewUpdateDialogCreator) Locator.INSTANCE.from(requireContext()).locate(WebViewUpdateDialogCreator.class)).createWebViewUpdateDialog(requireActivity(), new WebViewUpdateDialogCallbacks(this) { // from class: ru.mail.auth.webview.WebViewFragment$onWebViewInitFail$errorDialog$1
            final /* synthetic */ WebViewFragment<E> this$0;

            {
                this.this$0 = this;
            }

            @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks
            public void onCancelled() {
                this.this$0.getVm2().onBackPressed();
            }

            @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks
            public void onNegativeButtonClicked() {
                this.this$0.getVm2().onBackPressed();
            }

            @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks
            public void onNeutralButtonClicked() {
            }

            @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks
            public void onPositiveButtonClicked() {
            }
        }, new WebViewUpdateDialogCreator.DialogCallback(this) { // from class: ru.mail.auth.webview.WebViewFragment$onWebViewInitFail$errorDialog$2
            final /* synthetic */ WebViewFragment<E> this$0;

            {
                this.this$0 = this;
            }

            @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCreator.DialogCallback
            public void dismissDialog() {
                this.this$0.setErrorDialog(null);
            }
        });
        if (alertDialogCreateWebViewUpdateDialog != null) {
            alertDialogCreateWebViewUpdateDialog.show();
        }
        setErrorDialog(alertDialogCreateWebViewUpdateDialog);
    }

    void renderLoadingState(@NotNull LoadingState loadingState);

    @NotNull
    FragmentActivity requireActivity();

    @NotNull
    Context requireContext();

    void setErrorDialog(@Nullable AlertDialog dialog);

    void setRetryView(@NotNull View view);

    void setWebViewContainer(@NotNull ViewGroup viewGroup);

    default void setWebViewSettings(@NotNull WebSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(true);
        settings.setMixedContentMode(0);
    }

    default void setupCookies() {
    }
}
