package ru.mail.auth.webview;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import java.net.URLDecoder;
import java.util.HashMap;
import ru.mail.Authenticator.R;
import ru.mail.android_utils.extension.StringKt;
import ru.mail.auth.AuthUtil;
import ru.mail.auth.Authenticator;
import ru.mail.auth.BaseAuthActivity;
import ru.mail.auth.Message;
import ru.mail.uikit.view.ObservableWebView;
import ru.mail.uikit.view.pulltorefresh.IndeterminateProgressBar;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.mail.utils.KeyboardVisibilityHelper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public abstract class BaseSecondStepAuthFragment extends BaseWebViewFragment implements MailSecondStepView, KeyboardVisibilityHelper.KeyboardVisibilityListener {
    private static final String CLOSE_URI = "internal-api://close";
    public static final String EMAIL_PARAM_KEY = "email";
    private static final String JS_FILL_LOGIN = "javascript:window.postMessage({   type: 'account-login-app:autofill',   detail: {     login: '%s'  }}, '*');";
    private static final String PARAM_LOGIN = "Login";
    private static final String PARAM_STATE = "state";
    private static final String RECAPTCHA_PARAM = "captcha_type";
    private static final String RECAPTCHA_VALUE = "recaptcha";
    private static final String REDIRECT_FAIL_PATH = "/fail";
    private static final String REDIRECT_HOST = "mobile-auth";
    private static final String REDIRECT_INTERNAL_ERROR_PATH = "/error";
    private static final String REDIRECT_SUCCESS_PATH = "/success";
    public static final String REQUEST_KEY = "BaseSecondStepAuthFragment_request_key";
    private static final String STATE_PASSWORD = "password";
    private static final String SWITCH_STATE_URI = "internal-api://switch-state";
    private static final String TOKEN_LOG_PREFIX = "token";
    public static final String VK_CLIENT_ID_PARAM_KEY = "VK_CLIENT_ID_PARAM_KEY";
    private KeyboardVisibilityHelper mKeyboardVisibilityHelper;
    private IndeterminateProgressBar mProgressBar;
    private View.OnClickListener mRetryClickListener = new View.OnClickListener() { // from class: ru.mail.auth.webview.BaseSecondStepAuthFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BaseSecondStepAuthFragment.this.mRetryDelegate.retry();
        }
    };
    private WebViewRetryDelegate mRetryDelegate;
    private View mRetryView;
    private WebView mWebView;
    private static final Log LOG = Log.getLog("BaseSecondStepAuthFragment");
    private static final LogFilter sLogFilter = new LogFilter(Constraints.newParamNamedConstraint(Formats.newUrlFormat("token")));

    /* JADX INFO: compiled from: ProGuard */
    @Keep
    private class WebViewContentInterceptor {
        public static final String HTML = "javascript:window.WebViewContentInterceptor.showHTML(document.getElementsByTagName('body')[0].innerHTML);";
        public static final String NAME = "WebViewContentInterceptor";

        @JavascriptInterface
        public void showHTML(String str) {
            if (TextUtils.isEmpty(str)) {
                BaseSecondStepAuthFragment.this.mRetryDelegate.onPageContentError();
            } else {
                BaseSecondStepAuthFragment.this.mRetryDelegate.onPageContentLoaded();
            }
        }

        private WebViewContentInterceptor() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class WebViewRetryDelegate {
        private static final int MAX_RETRY_COUNT = 3;
        private static final int REFRESH_TIMEOUT = 59000;
        private boolean mAfterReload;
        private boolean mFailed;
        private final Handler mHandler = new Handler(Looper.getMainLooper());
        private Runnable mReloadTask = new Runnable() { // from class: ru.mail.auth.webview.BaseSecondStepAuthFragment.WebViewRetryDelegate.1
            @Override // java.lang.Runnable
            public void run() {
                if (WebViewRetryDelegate.this.mRetryCount < 3) {
                    WebViewRetryDelegate.this.mAfterReload = true;
                    if (WebViewRetryDelegate.this.mView != null) {
                        WebViewRetryDelegate.this.mView.loadPage();
                    }
                } else {
                    WebViewRetryDelegate.this.onPageError();
                }
                WebViewRetryDelegate.this.mRetryCount++;
            }
        };
        private int mRetryCount;
        private final String mUrl;

        @Nullable
        private MailSecondStepView mView;

        public WebViewRetryDelegate(@Nullable MailSecondStepView mailSecondStepView, String str) {
            this.mUrl = str;
            this.mView = mailSecondStepView;
        }

        public void detach() {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mView = null;
        }

        public void onPageContentError() {
            this.mHandler.post(new Runnable() { // from class: ru.mail.auth.webview.BaseSecondStepAuthFragment.WebViewRetryDelegate.3
                @Override // java.lang.Runnable
                public void run() {
                    WebViewRetryDelegate.this.onPageError();
                }
            });
        }

        public void onPageContentLoaded() {
            this.mHandler.post(new Runnable() { // from class: ru.mail.auth.webview.BaseSecondStepAuthFragment.WebViewRetryDelegate.2
                @Override // java.lang.Runnable
                public void run() {
                    WebViewRetryDelegate.this.mHandler.removeCallbacksAndMessages(null);
                    if (WebViewRetryDelegate.this.mView != null) {
                        WebViewRetryDelegate.this.mView.setLoadedState();
                    }
                }
            });
        }

        public void onPageError() {
            this.mHandler.removeCallbacksAndMessages(null);
            this.mFailed = true;
            MailSecondStepView mailSecondStepView = this.mView;
            if (mailSecondStepView != null) {
                mailSecondStepView.setErrorState();
            }
        }

        public void onPageFinished(String str) {
            if (!TextUtils.equals(this.mUrl, str) || this.mFailed) {
                return;
            }
            if (this.mAfterReload) {
                this.mAfterReload = false;
                return;
            }
            MailSecondStepView mailSecondStepView = this.mView;
            if (mailSecondStepView != null) {
                mailSecondStepView.checkWebViewContent();
            }
        }

        public void onPageStarted(String str) {
            if (!TextUtils.equals(this.mUrl, str) || this.mFailed) {
                return;
            }
            this.mAfterReload = false;
            if (this.mRetryCount <= 3) {
                this.mHandler.postDelayed(this.mReloadTask, 59000L);
            }
            MailSecondStepView mailSecondStepView = this.mView;
            if (mailSecondStepView != null) {
                mailSecondStepView.setLoadingState();
            }
        }

        public void retry() {
            this.mFailed = false;
            this.mAfterReload = false;
            MailSecondStepView mailSecondStepView = this.mView;
            if (mailSecondStepView != null) {
                mailSecondStepView.loadPage();
            }
        }
    }

    private void appendLoginToInputField() {
        this.mWebView.loadUrl(String.format(JS_FILL_LOGIN, getLogin()));
    }

    private String getArgumentSaveByKey(String str) {
        try {
            return getArguments().getString(str, "");
        } catch (Throwable unused) {
            LOG.e("Can't get argument by key " + str);
            return "";
        }
    }

    private boolean isRecaptcha(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter(RECAPTCHA_PARAM);
        if (queryParameter == null) {
            return false;
        }
        return queryParameter.equals(RECAPTCHA_VALUE);
    }

    private void safeClose() {
        try {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                fragmentManager.popBackStackImmediate();
            }
        } catch (Exception e10) {
            LOG.e("error closing", e10);
        }
    }

    private void showError(int i10) {
        if (isAdded()) {
            showAuthError(getString(i10));
            getFragmentManager().popBackStack();
        }
    }

    @Override // ru.mail.auth.webview.MailSecondStepView
    public void checkWebViewContent() {
        this.mWebView.loadUrl("javascript:window.WebViewContentInterceptor.showHTML(document.getElementsByTagName('body')[0].innerHTML);");
    }

    protected HashMap<String, String> composeCookies() {
        return new HashMap<>();
    }

    protected HashMap<String, String> extractQueryParams(Uri uri) {
        HashMap<String, String> map = new HashMap<>();
        for (String str : uri.getQueryParameterNames()) {
            map.put(str, uri.getQueryParameter(str));
        }
        return map;
    }

    protected String extractTsaCookieValue() {
        return "";
    }

    protected final String getLogin() {
        return getArgumentSaveByKey("authAccount");
    }

    protected abstract String getUrl();

    @Override // ru.mail.auth.webview.BaseWebViewFragment
    public WebView getWebView() {
        return this.mWebView;
    }

    @Override // ru.mail.auth.webview.BaseWebViewFragment
    protected void initWebView(View view) {
        int i10;
        LOG.i("Starting task to retrieve request token.");
        ObservableWebView observableWebView = new ObservableWebView(requireActivity());
        this.mWebView = observableWebView;
        observableWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setVisibility(0);
        this.mWebView.getSettings().setSavePassword(false);
        this.mWebView.addJavascriptInterface(new WebViewContentInterceptor(), "WebViewContentInterceptor");
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.webview_container);
        if (!isRecaptcha(getUrl()) || getActivity() == null) {
            i10 = -1;
        } else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            i10 = displayMetrics.heightPixels;
        }
        frameLayout.addView(this.mWebView, new FrameLayout.LayoutParams(-1, i10, 0));
        this.mProgressBar = (IndeterminateProgressBar) view.findViewById(R.id.progress);
        this.mRetryDelegate = new WebViewRetryDelegate(this, getUrl());
        this.mRetryView = view.findViewById(R.id.retry_block);
        ((Button) view.findViewById(R.id.retry)).setOnClickListener(this.mRetryClickListener);
        setupCookies();
        this.mWebView.setWebViewClient(new SecondStepWebViewClient(getArgumentSaveByKey(VK_CLIENT_ID_PARAM_KEY)));
        this.mWebView.loadUrl(getUrl());
    }

    protected boolean isCriticalUrl(Uri uri) {
        return true;
    }

    @Override // ru.mail.auth.webview.MailSecondStepView
    public void loadPage() {
        this.mWebView.loadUrl(getUrl());
    }

    protected void onClose() {
        getFragmentManager().popBackStack();
    }

    @Override // ru.mail.auth.webview.BaseWebViewFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        initWebViewOrStartDialog(viewOnCreateView);
        initToolbar(viewOnCreateView, getString(R.string.code_auth_webview_title));
        KeyboardVisibilityHelper keyboardVisibilityHelperFrom = KeyboardVisibilityHelper.from(getActivity());
        this.mKeyboardVisibilityHelper = keyboardVisibilityHelperFrom;
        keyboardVisibilityHelperFrom.setKeyboardVisibilityListener(this);
        return viewOnCreateView;
    }

    @Override // ru.mail.auth.webview.BaseWebViewFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mKeyboardVisibilityHelper.release();
        super.onDestroyView();
    }

    @Override // ru.mail.auth.BaseAuthFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        WebViewRetryDelegate webViewRetryDelegate = this.mRetryDelegate;
        if (webViewRetryDelegate != null) {
            webViewRetryDelegate.detach();
        }
        super.onDetach();
    }

    protected void onError(int i10) {
        showError(i10);
    }

    @Override // ru.mail.auth.webview.BaseWebViewFragment, ru.mail.ui.view.OnBackPressedCallback
    public boolean onHardwareBack() {
        getParentFragmentManager().setFragmentResult(REQUEST_KEY, requireArguments());
        return false;
    }

    protected void onInternalError(int i10) {
        showError(i10);
    }

    @Override // ru.mail.utils.KeyboardVisibilityHelper.KeyboardVisibilityListener
    public void onKeyboardShown() {
        this.mRetryView.setVisibility(8);
    }

    protected void onRedirectSuccess(Uri uri) {
        safeClose();
        Bundle bundle = new Bundle(getArguments());
        Bundle bundle2 = new Bundle();
        onRedirectSuccessOptions(bundle2, uri);
        String string = bundle.getString("login_extra_xmail_migration_from");
        if (string != null) {
            String queryParameter = uri.getQueryParameter(PARAM_LOGIN);
            if (queryParameter != null) {
                bundle.putString("authAccount", queryParameter);
            }
            LOG.d("onRedirectSuccess xmail migration login = " + queryParameter);
            bundle2.putString("login_extra_xmail_migration_from", string);
        }
        bundle.putBundle(BaseAuthActivity.EXTRA_BUNDLE, bundle2);
        getAuthCallBack().onMessageHandle(new Message(Message.Id.AUTHENTICATE, bundle));
    }

    protected void onRedirectSuccessOptions(Bundle bundle, Uri uri) {
        bundle.putSerializable(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_PARAMS, extractQueryParams(uri));
        bundle.putSerializable(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_COOKIES, composeCookies());
        bundle.putString(Authenticator.BUNDLE_KEY_2FACTOR_AUTH_TSA_COOKIE, extractTsaCookieValue());
    }

    protected void onSwitchToPassword() {
        Bundle bundle = new Bundle();
        AuthUtil.proxyStringParam(bundle, getArguments(), "authAccount");
        getFragmentManager().popBackStackImmediate();
        getAuthCallBack().onMessageHandle(new Message(Message.Id.ON_NEED_SWITCH_TO_PASSWORD, bundle));
    }

    protected void onSwitchToRestoreVkId(String str) {
        Bundle bundle = new Bundle();
        AuthUtil.proxyStringParam(bundle, getArguments(), "authAccount");
        bundle.putString("email", str);
        getFragmentManager().popBackStackImmediate();
        getAuthCallBack().onMessageHandle(new Message(Message.Id.ON_NEED_SWITCH_TO_RESTORE_VK_ID, bundle));
    }

    @Override // ru.mail.auth.webview.MailSecondStepView
    public void setErrorState() {
        this.mRetryView.setVisibility(0);
        this.mProgressBar.setLoadState(false);
    }

    @Override // ru.mail.auth.webview.MailSecondStepView
    public void setLoadedState() {
        this.mProgressBar.setLoadState(false);
        appendLoginToInputField();
    }

    @Override // ru.mail.auth.webview.MailSecondStepView
    public void setLoadingState() {
        this.mRetryView.setVisibility(8);
        this.mProgressBar.setLoadState(true);
    }

    protected abstract void setupCookies();

    /* JADX INFO: compiled from: ProGuard */
    private class SecondStepWebViewClient extends WebViewClient {
        private String restoreVkIdDeeplink;

        SecondStepWebViewClient(String str) {
            this.restoreVkIdDeeplink = "vk" + str + "://mailrestoretovkid";
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            BaseSecondStepAuthFragment.LOG.d("onPageFinished : " + BaseSecondStepAuthFragment.sLogFilter.filter(str));
            BaseSecondStepAuthFragment.this.mRetryDelegate.onPageFinished(str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            BaseSecondStepAuthFragment.LOG.d("onPageStarted : " + BaseSecondStepAuthFragment.sLogFilter.filter(str));
            BaseSecondStepAuthFragment.this.mRetryDelegate.onPageStarted(str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            if (BaseSecondStepAuthFragment.this.isCriticalUrl(webResourceRequest.getUrl())) {
                BaseSecondStepAuthFragment.this.mRetryDelegate.onPageError();
            }
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            BaseSecondStepAuthFragment.LOG.d("shouldInterceptRequest : " + BaseSecondStepAuthFragment.sLogFilter.filter(str));
            return super.shouldInterceptRequest(BaseSecondStepAuthFragment.this.mWebView, str);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            BaseSecondStepAuthFragment.LOG.d("shouldOverrideUrl : " + BaseSecondStepAuthFragment.sLogFilter.filter(str));
            Uri uri = Uri.parse(str);
            String path = uri.getPath();
            if (TextUtils.equals(BaseSecondStepAuthFragment.CLOSE_URI, str)) {
                BaseSecondStepAuthFragment.this.onClose();
                return true;
            }
            if (uri.toString().startsWith("internal-api://switch-state")) {
                if (TextUtils.equals(uri.getQueryParameter("state"), "password")) {
                    BaseSecondStepAuthFragment.this.onSwitchToPassword();
                }
                return true;
            }
            if (uri.toString().startsWith(this.restoreVkIdDeeplink)) {
                String queryParameter = uri.getQueryParameter("email");
                if (!StringKt.isValidEmail(queryParameter)) {
                    try {
                        queryParameter = URLDecoder.decode(queryParameter, "UTF-8");
                    } catch (Throwable th2) {
                        BaseSecondStepAuthFragment.LOG.e("Cannot encode email " + th2);
                    }
                }
                BaseSecondStepAuthFragment.this.onSwitchToRestoreVkId(queryParameter);
                return true;
            }
            if (!TextUtils.equals(uri.getHost(), "mobile-auth")) {
                return false;
            }
            if (BaseSecondStepAuthFragment.this.mRetryDelegate != null) {
                BaseSecondStepAuthFragment.this.mRetryDelegate.detach();
            }
            if (TextUtils.equals(path, "/success")) {
                BaseSecondStepAuthFragment.this.onRedirectSuccess(uri);
            } else if (TextUtils.equals(path, "/fail")) {
                BaseSecondStepAuthFragment.this.onError(R.string.authenticator_error);
            } else if (TextUtils.equals(path, "/error")) {
                BaseSecondStepAuthFragment.this.onInternalError(R.string.reg_err_network_server_error);
            }
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i10, String str, String str2) {
            if (BaseSecondStepAuthFragment.this.isCriticalUrl(Uri.parse(str2))) {
                BaseSecondStepAuthFragment.this.mRetryDelegate.onPageError();
            }
        }
    }

    @Override // ru.mail.utils.KeyboardVisibilityHelper.KeyboardVisibilityListener
    public void onKeyboardHidden() {
    }
}
