package ru.mail.auth.restore;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.Authenticator.R;
import ru.mail.auth.Authenticator;
import ru.mail.auth.BaseAuthActivity;
import ru.mail.auth.webview.AuthWebChromeClient;
import ru.mail.auth.webview.AuthWebViewClient;
import ru.mail.auth.webview.LoadingState;
import ru.mail.auth.webview.WebViewContentInterceptor;
import ru.mail.auth.webview.WebViewFragment;
import ru.mail.auth.webview.WebViewViewModel;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.deviceinfo.di.DeviceInfoEntryPoint;
import ru.mail.remotelayout.data.dto.ItemDto;
import ru.mail.uikit.view.pulltorefresh.IndeterminateProgressBar;
import ru.mail.util.kotlin.cookie.MailCookie;
import ru.mail.util.log.Log;
import ru.mail.utils.UtilExtensionsKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 C2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001CB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010(H\u0016J&\u0010.\u001a\u0004\u0018\u00010\"2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u001c2\b\u00102\u001a\u0004\u0018\u000103H\u0016J\b\u00104\u001a\u00020,H\u0016J\u0010\u00105\u001a\u00020,2\u0006\u00106\u001a\u000207H\u0016J\u0010\u00108\u001a\u00020,2\u0006\u00109\u001a\u00020\u0003H\u0016J\b\u0010:\u001a\u00020,H\u0002J\b\u0010;\u001a\u00020,H\u0016J\u0010\u0010<\u001a\u00020,2\u0006\u0010=\u001a\u00020\"H\u0016J\b\u0010>\u001a\u00020?H\u0002J\b\u0010@\u001a\u00020,H\u0016J\b\u0010A\u001a\u00020BH\u0016R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\r\u0010\tR\u001b\u0010\u000f\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u000b\u001a\u0004\b\u0010\u0010\tR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0012\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001b\u001a\u00020\u001cX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lru/mail/auth/restore/RestorePasswordFragment;", "Landroidx/fragment/app/Fragment;", "Lru/mail/auth/webview/WebViewFragment;", "Lru/mail/auth/restore/RestorePasswordEvent;", "<init>", "()V", "restoreUrl", "", "getRestoreUrl", "()Ljava/lang/String;", "restoreUrl$delegate", "Lkotlin/Lazy;", "login", "getLogin", "login$delegate", "tsaCookieValue", "getTsaCookieValue", "tsaCookieValue$delegate", "isRebind", "", "()Z", "isRebind$delegate", "vm", "Lru/mail/auth/restore/RestorePasswordViewModel;", "getVm", "()Lru/mail/auth/restore/RestorePasswordViewModel;", "vm$delegate", "webViewContainer", "Landroid/view/ViewGroup;", "getWebViewContainer", "()Landroid/view/ViewGroup;", "setWebViewContainer", "(Landroid/view/ViewGroup;)V", "retryView", "Landroid/view/View;", "getRetryView", "()Landroid/view/View;", "setRetryView", "(Landroid/view/View;)V", "webViewErrorDialog", "Landroidx/appcompat/app/AlertDialog;", "progressBar", "Lru/mail/uikit/view/pulltorefresh/IndeterminateProgressBar;", "setErrorDialog", "", ItemDto.KEY_TYPE_DIALOG, "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "renderLoadingState", "loadingState", "Lru/mail/auth/webview/LoadingState;", "handleEvent", "event", "safePopBackStack", "onPause", "initWebView", "rootView", "createWebView", "Landroid/webkit/WebView;", "setupCookies", "getLifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRestorePasswordFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RestorePasswordFragment.kt\nru/mail/auth/restore/RestorePasswordFragment\n+ 2 FragmentViewModelLazy.kt\nandroidx/fragment/app/FragmentViewModelLazyKt\n+ 3 View.kt\nandroidx/core/view/ViewKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,285:1\n106#2,15:286\n257#3,2:301\n257#3,2:303\n257#3,2:305\n257#3,2:307\n257#3,2:309\n257#3,2:311\n1869#4,2:313\n*S KotlinDebug\n*F\n+ 1 RestorePasswordFragment.kt\nru/mail/auth/restore/RestorePasswordFragment\n*L\n47#1:286,15\n84#1:301,2\n86#1:303,2\n99#1:305,2\n101#1:307,2\n106#1:309,2\n108#1:311,2\n210#1:313,2\n*E\n"})
public final class RestorePasswordFragment extends Fragment implements WebViewFragment<RestorePasswordEvent> {

    @NotNull
    private static final String COOKIE_DOMAIN = "auth.mail.ru";

    @NotNull
    private static final String COOKIE_URL = "https://auth.mail.ru";

    @NotNull
    private static final String DEVICE_COOKIE_NAME = "DeviceID";

    @NotNull
    private static final String GARAGE_COOKIE_NAME = "GarageID";

    @NotNull
    private static final String JS_FILL_LOGIN = "javascript:window.postMessage({   type: 'account-login-app:autofill',   detail: {     login: '%s'  }}, '*');";

    @NotNull
    private static final String RESTORE_PASSWORD_CANCEL_TAG = "RESTORE_PASSWORD_CANCEL_TAG";

    @NotNull
    private static final String RESTORE_PASSWORD_EMAIL = "email";

    @NotNull
    private static final String RESTORE_PASSWORD_ERROR_RES = "RESTORE_PASSWORD_ERROR_RES";

    @NotNull
    private static final String RESTORE_PASSWORD_IS_REBIND = "is_rebind";

    @NotNull
    private static final String RESTORE_PASSWORD_RESULT = "RESTORE_PASSWORD_RESULT";

    @NotNull
    private static final String RESTORE_URL_ARG = "RESTORE_URL_ARG";

    @NotNull
    private static final String TSA_COOKIE_NAME = "tsa";

    @NotNull
    private static final String TSA_COOKIE_VALUE = "TSA_COOKIE_ARG";
    private IndeterminateProgressBar progressBar;
    public View retryView;

    /* JADX INFO: renamed from: vm$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy vm;
    public ViewGroup webViewContainer;

    @Nullable
    private AlertDialog webViewErrorDialog;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("RestorePasswordFragment");

    /* JADX INFO: renamed from: restoreUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy restoreUrl = UtilExtensionsKt.lazyUnsafe(new Function0() { // from class: ru.mail.auth.restore.b
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return RestorePasswordFragment.restoreUrl_delegate$lambda$0(this.f80801a);
        }
    });

    /* JADX INFO: renamed from: login$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy login = UtilExtensionsKt.lazyUnsafe(new Function0() { // from class: ru.mail.auth.restore.c
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return RestorePasswordFragment.login_delegate$lambda$0(this.f80802a);
        }
    });

    /* JADX INFO: renamed from: tsaCookieValue$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy tsaCookieValue = UtilExtensionsKt.lazyUnsafe(new Function0() { // from class: ru.mail.auth.restore.d
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return RestorePasswordFragment.tsaCookieValue_delegate$lambda$0(this.f80803a);
        }
    });

    /* JADX INFO: renamed from: isRebind$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy isRebind = UtilExtensionsKt.lazyUnsafe(new Function0() { // from class: ru.mail.auth.restore.e
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(RestorePasswordFragment.isRebind_delegate$lambda$0(this.f80804a));
        }
    });

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001aJ\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010 \u001a\u00020!2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010\"\u001a\u00020\u00072\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cJ\u0006\u0010#\u001a\u00020\u0007J\u0010\u0010$\u001a\u00020\u00072\b\u0010\u001f\u001a\u0004\u0018\u00010\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lru/mail/auth/restore/RestorePasswordFragment$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", RestorePasswordFragment.RESTORE_URL_ARG, "", "TSA_COOKIE_VALUE", RestorePasswordFragment.RESTORE_PASSWORD_RESULT, "RESTORE_PASSWORD_EMAIL", "RESTORE_PASSWORD_IS_REBIND", RestorePasswordFragment.RESTORE_PASSWORD_ERROR_RES, RestorePasswordFragment.RESTORE_PASSWORD_CANCEL_TAG, "COOKIE_URL", "COOKIE_DOMAIN", "TSA_COOKIE_NAME", "DEVICE_COOKIE_NAME", "GARAGE_COOKIE_NAME", "JS_FILL_LOGIN", "newInstance", "Lru/mail/auth/restore/RestorePasswordFragment;", "restoreUrl", "login", "tsaCookie", "isRebind", "", "getArgumentsBundle", "Landroid/os/Bundle;", "getResult", "Lru/mail/auth/restore/RestorePasswordResult;", "bundle", "getError", "", "getCancelTag", "getResultKey", "getEmail", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nRestorePasswordFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RestorePasswordFragment.kt\nru/mail/auth/restore/RestorePasswordFragment$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,285:1\n1400#2,2:286\n*S KotlinDebug\n*F\n+ 1 RestorePasswordFragment.kt\nru/mail/auth/restore/RestorePasswordFragment$Companion\n*L\n269#1:286,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Bundle getArgumentsBundle(@NotNull String restoreUrl, @NotNull String login, @NotNull String tsaCookie) {
            Intrinsics.checkNotNullParameter(restoreUrl, "restoreUrl");
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(tsaCookie, "tsaCookie");
            return BundleKt.bundleOf(TuplesKt.to(RestorePasswordFragment.RESTORE_URL_ARG, restoreUrl), TuplesKt.to("authAccount", login), TuplesKt.to(RestorePasswordFragment.TSA_COOKIE_VALUE, tsaCookie));
        }

        @NotNull
        public final String getCancelTag(@Nullable Bundle bundle) {
            String string;
            return (bundle == null || (string = bundle.getString(RestorePasswordFragment.RESTORE_PASSWORD_CANCEL_TAG)) == null) ? "CANCEL" : string;
        }

        @NotNull
        public final String getEmail(@Nullable Bundle bundle) {
            String string;
            return (bundle == null || (string = bundle.getString("email")) == null) ? "" : string;
        }

        public final int getError(@Nullable Bundle bundle) {
            return bundle != null ? bundle.getInt(RestorePasswordFragment.RESTORE_PASSWORD_ERROR_RES) : R.string.authenticator_error;
        }

        @Nullable
        public final RestorePasswordResult getResult(@Nullable Bundle bundle) {
            String string;
            if (bundle != null && (string = bundle.getString(RestorePasswordFragment.RESTORE_PASSWORD_RESULT, null)) != null) {
                for (RestorePasswordResult restorePasswordResult : RestorePasswordResult.values()) {
                    if (Intrinsics.areEqual(restorePasswordResult.name(), string)) {
                        return restorePasswordResult;
                    }
                }
            }
            return null;
        }

        @NotNull
        public final String getResultKey() {
            return "RESTORE_PASSWORD_FRAGMENT_RESULT_KEY";
        }

        @NotNull
        public final RestorePasswordFragment newInstance(@NotNull String restoreUrl, @NotNull String login, @NotNull String tsaCookie, boolean isRebind) {
            Intrinsics.checkNotNullParameter(restoreUrl, "restoreUrl");
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(tsaCookie, "tsaCookie");
            RestorePasswordFragment restorePasswordFragment = new RestorePasswordFragment();
            restorePasswordFragment.setArguments(BundleKt.bundleOf(TuplesKt.to(RestorePasswordFragment.RESTORE_URL_ARG, restoreUrl), TuplesKt.to("authAccount", login), TuplesKt.to(RestorePasswordFragment.TSA_COOKIE_VALUE, tsaCookie), TuplesKt.to("is_rebind", Boolean.valueOf(isRebind))));
            return restorePasswordFragment;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: ru.mail.auth.restore.RestorePasswordFragment$initWebView$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function0<WebView> {
        AnonymousClass2(Object obj) {
            super(0, obj, RestorePasswordFragment.class, "createWebView", "createWebView()Landroid/webkit/WebView;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final WebView invoke() {
            return ((RestorePasswordFragment) this.receiver).createWebView();
        }
    }

    public RestorePasswordFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: ru.mail.auth.restore.RestorePasswordFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: ru.mail.auth.restore.RestorePasswordFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function0.invoke();
            }
        });
        final Function0 function1 = null;
        this.vm = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(RestorePasswordViewModel.class), new Function0<ViewModelStore>() { // from class: ru.mail.auth.restore.RestorePasswordFragment$special$$inlined$viewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStore invoke() {
                return FragmentViewModelLazyKt.m8759viewModels$lambda1(lazy).getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: ru.mail.auth.restore.RestorePasswordFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function2 = function1;
                if (function2 != null && (creationExtras = (CreationExtras) function2.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM8759viewModels$lambda1 = FragmentViewModelLazyKt.m8759viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM8759viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM8759viewModels$lambda1 : null;
                return hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: ru.mail.auth.restore.RestorePasswordFragment$special$$inlined$viewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                ViewModelStoreOwner viewModelStoreOwnerM8759viewModels$lambda1 = FragmentViewModelLazyKt.m8759viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM8759viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM8759viewModels$lambda1 : null;
                return (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) ? this.getDefaultViewModelProviderFactory() : defaultViewModelProviderFactory;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WebView createWebView() {
        WebView webView = new WebView(requireActivity());
        WebSettings settings = webView.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "getSettings(...)");
        setWebViewSettings(settings);
        webView.addJavascriptInterface(new WebViewContentInterceptor(getVm2()), "WebViewContentInterceptor");
        webView.setWebViewClient(new AuthWebViewClient(getVm2()));
        return webView;
    }

    private final String getLogin() {
        return (String) this.login.getValue();
    }

    private final String getRestoreUrl() {
        return (String) this.restoreUrl.getValue();
    }

    private final String getTsaCookieValue() {
        return (String) this.tsaCookieValue.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initWebView$lambda$0(RestorePasswordFragment restorePasswordFragment, View view) {
        restorePasswordFragment.getVm2().onRetryClicked();
    }

    private final boolean isRebind() {
        return ((Boolean) this.isRebind.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isRebind_delegate$lambda$0(RestorePasswordFragment restorePasswordFragment) {
        Bundle arguments = restorePasswordFragment.getArguments();
        if (arguments != null) {
            return arguments.getBoolean("is_rebind");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String login_delegate$lambda$0(RestorePasswordFragment restorePasswordFragment) {
        Bundle arguments = restorePasswordFragment.getArguments();
        String string = arguments != null ? arguments.getString("authAccount") : null;
        return string == null ? "" : string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String restoreUrl_delegate$lambda$0(RestorePasswordFragment restorePasswordFragment) {
        Bundle arguments = restorePasswordFragment.getArguments();
        String string = arguments != null ? arguments.getString(RESTORE_URL_ARG) : null;
        return string == null ? "" : string;
    }

    private final void safePopBackStack() {
        if (isStateSaved()) {
            getParentFragmentManager().popBackStack();
        } else {
            getParentFragmentManager().popBackStackImmediate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String tsaCookieValue_delegate$lambda$0(RestorePasswordFragment restorePasswordFragment) {
        Bundle arguments = restorePasswordFragment.getArguments();
        String string = arguments != null ? arguments.getString(TSA_COOKIE_VALUE) : null;
        return string == null ? "" : string;
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    @NotNull
    public /* bridge */ WebView getCurrentWebView() {
        return super.getCurrentWebView();
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    @NotNull
    public View getRetryView() {
        View view = this.retryView;
        if (view != null) {
            return view;
        }
        Intrinsics.throwUninitializedPropertyAccessException("retryView");
        return null;
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    @NotNull
    public ViewGroup getWebViewContainer() {
        ViewGroup viewGroup = this.webViewContainer;
        if (viewGroup != null) {
            return viewGroup;
        }
        Intrinsics.throwUninitializedPropertyAccessException("webViewContainer");
        return null;
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public /* bridge */ void initBackPressedListener() {
        super.initBackPressedListener();
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public /* bridge */ void initKeyboardListener() {
        super.initKeyboardListener();
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public /* bridge */ void initToolbar(@NotNull View view, @NotNull String str) {
        super.initToolbar(view, str);
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public /* bridge */ void initViewModel() {
        super.initViewModel();
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public void initWebView(@NotNull View rootView) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        WebView webViewCreateWebView = createWebView();
        View viewFindViewById = rootView.findViewById(R.id.webview_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        setWebViewContainer((ViewGroup) viewFindViewById);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 0);
        getWebViewContainer().addView(webViewCreateWebView, layoutParams);
        View viewFindViewById2 = rootView.findViewById(R.id.progress);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.progressBar = (IndeterminateProgressBar) viewFindViewById2;
        View viewFindViewById3 = rootView.findViewById(R.id.retry_block);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        setRetryView(viewFindViewById3);
        View viewFindViewById4 = rootView.findViewById(R.id.retry);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        ((Button) viewFindViewById4).setOnClickListener(new View.OnClickListener() { // from class: ru.mail.auth.restore.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RestorePasswordFragment.initWebView$lambda$0(this.f80800a, view);
            }
        });
        setupCookies();
        webViewCreateWebView.setWebChromeClient(new AuthWebChromeClient(webViewCreateWebView, getWebViewContainer(), layoutParams, new AnonymousClass2(this)));
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public /* bridge */ void initWebViewOrStartDialog(@NotNull View view) {
        super.initWebViewOrStartDialog(view);
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public /* bridge */ boolean isWebViewGoBackEnabled() {
        return super.isWebViewGoBackEnabled();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.oauth_screen, container, false);
        initViewModel();
        Intrinsics.checkNotNull(viewInflate);
        initWebViewOrStartDialog(viewInflate);
        String string = getString(R.string.restore_password_webview_title);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        initToolbar(viewInflate, string);
        initKeyboardListener();
        initBackPressedListener();
        LOG.d("onRestorePassword shown");
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        getVm2().onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        AlertDialog alertDialog = this.webViewErrorDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        alertDialog.dismiss();
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public /* bridge */ void onWebViewInitFail() {
        super.onWebViewInitFail();
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public void renderLoadingState(@NotNull LoadingState loadingState) {
        Intrinsics.checkNotNullParameter(loadingState, "loadingState");
        IndeterminateProgressBar indeterminateProgressBar = null;
        if (loadingState instanceof LoadingState.Loading) {
            IndeterminateProgressBar indeterminateProgressBar2 = this.progressBar;
            if (indeterminateProgressBar2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                indeterminateProgressBar2 = null;
            }
            indeterminateProgressBar2.setVisibility(0);
            IndeterminateProgressBar indeterminateProgressBar3 = this.progressBar;
            if (indeterminateProgressBar3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                indeterminateProgressBar = indeterminateProgressBar3;
            }
            indeterminateProgressBar.setLoadState(true);
            getRetryView().setVisibility(8);
            String url = ((LoadingState.Loading) loadingState).getUrl();
            if (url != null) {
                getCurrentWebView().loadUrl(url);
                return;
            } else {
                getCurrentWebView().loadUrl(getRestoreUrl());
                return;
            }
        }
        if (Intrinsics.areEqual(loadingState, LoadingState.PageLoaded.INSTANCE)) {
            getCurrentWebView().loadUrl("javascript:window.WebViewContentInterceptor.showHTML(document.getElementsByTagName('body')[0].innerHTML);");
            return;
        }
        if (!Intrinsics.areEqual(loadingState, LoadingState.ContentLoaded.INSTANCE)) {
            if (!Intrinsics.areEqual(loadingState, LoadingState.LoadingError.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            IndeterminateProgressBar indeterminateProgressBar4 = this.progressBar;
            if (indeterminateProgressBar4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                indeterminateProgressBar4 = null;
            }
            indeterminateProgressBar4.setVisibility(8);
            IndeterminateProgressBar indeterminateProgressBar5 = this.progressBar;
            if (indeterminateProgressBar5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            } else {
                indeterminateProgressBar = indeterminateProgressBar5;
            }
            indeterminateProgressBar.setLoadState(false);
            getRetryView().setVisibility(0);
            return;
        }
        IndeterminateProgressBar indeterminateProgressBar6 = this.progressBar;
        if (indeterminateProgressBar6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
            indeterminateProgressBar6 = null;
        }
        indeterminateProgressBar6.setVisibility(8);
        IndeterminateProgressBar indeterminateProgressBar7 = this.progressBar;
        if (indeterminateProgressBar7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBar");
        } else {
            indeterminateProgressBar = indeterminateProgressBar7;
        }
        indeterminateProgressBar.setLoadState(false);
        getRetryView().setVisibility(8);
        WebView currentWebView = getCurrentWebView();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(JS_FILL_LOGIN, Arrays.copyOf(new Object[]{getLogin()}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        currentWebView.loadUrl(str);
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public void setErrorDialog(@Nullable AlertDialog dialog) {
        this.webViewErrorDialog = dialog;
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public void setRetryView(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.retryView = view;
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public void setWebViewContainer(@NotNull ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "<set-?>");
        this.webViewContainer = viewGroup;
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public /* bridge */ void setWebViewSettings(@NotNull WebSettings webSettings) {
        super.setWebViewSettings(webSettings);
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public void setupCookies() {
        ArrayList arrayList = new ArrayList();
        DeviceInfoEntryPoint.Companion companion = DeviceInfoEntryPoint.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        DeviceIdProvider deviceIdProvider = companion.deviceIdProvider(contextRequireContext);
        if (getTsaCookieValue().length() > 0) {
            arrayList.add(MailCookie.INSTANCE.newSecureInstance(TSA_COOKIE_NAME, getTsaCookieValue(), COOKIE_DOMAIN));
        }
        MailCookie.Companion companion2 = MailCookie.INSTANCE;
        arrayList.add(companion2.newSecureInstance("DeviceID", deviceIdProvider.getDeviceId(), COOKIE_DOMAIN));
        arrayList.add(companion2.newSecureInstance(GARAGE_COOKIE_NAME, deviceIdProvider.getUdid(), COOKIE_DOMAIN));
        CookieManager cookieManager = CookieManager.getInstance();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            cookieManager.setCookie(COOKIE_URL, ((MailCookie) it.next()).toRFC6265Format());
        }
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    @NotNull
    /* JADX INFO: renamed from: getVm, reason: merged with bridge method [inline-methods] */
    public WebViewViewModel<RestorePasswordEvent> getVm2() {
        return (RestorePasswordViewModel) this.vm.getValue();
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    public void handleEvent(@NotNull RestorePasswordEvent event) {
        RestorePasswordResult restorePasswordResult;
        Intrinsics.checkNotNullParameter(event, "event");
        Bundle bundle = new Bundle();
        if (event instanceof RestorePasswordEvent.Success) {
            RestorePasswordEvent.Success success = (RestorePasswordEvent.Success) event;
            bundle.putBundle(BaseAuthActivity.EXTRA_BUNDLE, BundleKt.bundleOf(TuplesKt.to(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_PARAMS, success.getQueryParams())));
            bundle.putString("authAccount", success.getRestoredLogin());
            bundle.putBoolean("is_rebind", isRebind());
            restorePasswordResult = RestorePasswordResult.SUCCESS;
        } else if (event instanceof RestorePasswordEvent.Cancel) {
            bundle.putString(RESTORE_PASSWORD_CANCEL_TAG, ((RestorePasswordEvent.Cancel) event).getAnalyticsTag());
            restorePasswordResult = RestorePasswordResult.CANCEL;
        } else if (event instanceof RestorePasswordEvent.Error) {
            bundle.putInt(RESTORE_PASSWORD_ERROR_RES, ((RestorePasswordEvent.Error) event).getErrorRes());
            restorePasswordResult = RestorePasswordResult.ERROR;
        } else {
            if (!(event instanceof RestorePasswordEvent.GoToRestoreVkid)) {
                throw new NoWhenBranchMatchedException();
            }
            bundle.putString("email", ((RestorePasswordEvent.GoToRestoreVkid) event).getEmail());
            restorePasswordResult = RestorePasswordResult.GO_TO_RESTORE_VKID;
        }
        bundle.putString(RESTORE_PASSWORD_RESULT, restorePasswordResult.name());
        LOG.d("RestorePasswordResult=" + restorePasswordResult.name());
        safePopBackStack();
        getParentFragmentManager().setFragmentResult(INSTANCE.getResultKey(), bundle);
    }

    @Override // ru.mail.auth.webview.WebViewFragment
    @NotNull
    public LifecycleOwner getLifecycleOwner() {
        return this;
    }
}
