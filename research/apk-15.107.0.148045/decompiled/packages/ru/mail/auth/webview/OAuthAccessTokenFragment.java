package ru.mail.auth.webview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewDatabase;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.TokenResponse;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import ru.mail.Authenticator.R;
import ru.mail.auth.Analytics;
import ru.mail.auth.Message;
import ru.mail.auth.request.GetEmailRequest;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.logic.navigation.segue.ClickerLinkConstructor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.uikit.view.ObservableWebView;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@SuppressLint({"SetJavaScriptEnabled"})
@AndroidEntryPoint
public abstract class OAuthAccessTokenFragment extends Hilt_OAuthAccessTokenFragment {
    private static final Log LOG = Log.getLog("OAuthAccessTokenFragment");
    private static final LogFilter sLogFilter = new LogFilter(Formats.newUrlFormat("token"), Formats.newUrlFormat(ClickerLinkConstructor.AUTOGEN_TOKEN), Formats.newUrlFormat("refresh_token"), Formats.newUrlFormat("access_token"));

    @Inject
    Analytics analytics;
    private ProcessToken mProcessToken;
    private View mRootView;
    private OAuth2Helper oAuth2Helper;
    private WebView webview;
    volatile boolean handled = false;
    private WebViewClient mWebViewClient = new WebViewClient() { // from class: ru.mail.auth.webview.OAuthAccessTokenFragment.1
        private String mLoadingError;

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            OAuthAccessTokenFragment.LOG.d("onPageFinished : " + OAuthAccessTokenFragment.sLogFilter.filter(str) + " handled = " + OAuthAccessTokenFragment.this.handled);
            Analytics analytics = OAuthAccessTokenFragment.this.analytics;
            String str2 = this.mLoadingError;
            analytics.loadingOAuthWebViewScreen(str2 == null ? "success" : "error", str2);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            OAuthAccessTokenFragment.LOG.d("onPageStarted : " + OAuthAccessTokenFragment.sLogFilter.filter(str) + " handled = " + OAuthAccessTokenFragment.this.handled);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i10, String str, String str2) {
            super.onReceivedError(webView, i10, str, str2);
            this.mLoadingError = str;
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            OAuthAccessTokenFragment.LOG.d("shouldOverrideUrl : " + OAuthAccessTokenFragment.sLogFilter.filter(str));
            this.mLoadingError = null;
            Uri uri = Uri.parse(str);
            if (!OAuthAccessTokenFragment.this.needHandleRedirectUrl(uri)) {
                OAuthAccessTokenFragment.this.webview.setVisibility(OAuthAccessTokenFragment.this.handled ? 4 : 0);
                return false;
            }
            OAuthAccessTokenFragment.this.webview.setVisibility(4);
            if (OAuthAccessTokenFragment.this.handled) {
                return true;
            }
            OAuthAccessTokenFragment.this.handleRedirectUrl(uri);
            return true;
        }
    };

    /* JADX INFO: compiled from: ProGuard */
    public interface EmailHolder {
        @Nullable
        String requestEmail(String str);
    }

    /* JADX INFO: compiled from: ProGuard */
    public abstract class EmailHolderWithRequest implements EmailHolder {
        public EmailHolderWithRequest() {
        }

        public abstract GetEmailRequest<?> getEmailRequestCmd(String str);

        @Override // ru.mail.auth.webview.OAuthAccessTokenFragment.EmailHolder
        @Nullable
        public String requestEmail(String str) {
            try {
                CommandStatus<?> orThrow = getEmailRequestCmd(str).execute(ExecutorSelectors.defaultSelector()).getOrThrow();
                if (orThrow instanceof CommandStatus.OK) {
                    return ((GetEmailRequest.Result) orThrow.getData()).getEmail();
                }
                return null;
            } catch (InterruptedException unused) {
                return null;
            } catch (ExecutionException e10) {
                throw new RuntimeException(e10);
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class ProcessToken extends AsyncTask<Uri, Void, TokensHolder> {
        public static final String CODE = "code";
        private final Log LOG;
        private final Analytics mAnalytics;
        private OAuthAccessTokenFragment mFragment;
        private final Uri mUrl;

        public ProcessToken(Uri uri, OAuthAccessTokenFragment oAuthAccessTokenFragment, Analytics analytics) {
            Log log = Log.getLog("ProcessToken");
            this.LOG = log;
            this.mUrl = uri;
            this.mFragment = oAuthAccessTokenFragment;
            this.mAnalytics = analytics;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("ProcessToken constructor : ");
            sb2.append(OAuthAccessTokenFragment.sLogFilter.filter("" + uri));
            log.d(sb2.toString());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public TokensHolder doInBackground(Uri... uriArr) {
            TokensHolder tokensHolder = new TokensHolder();
            FragmentActivity activity = this.mFragment.getActivity();
            if (activity == null) {
                Log log = this.LOG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Not doing anything for url ");
                sb2.append(OAuthAccessTokenFragment.sLogFilter.filter("" + this.mUrl));
                log.i(sb2.toString());
                return tokensHolder;
            }
            Log log2 = this.LOG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Redirect URL found");
            sb3.append(OAuthAccessTokenFragment.sLogFilter.filter("" + this.mUrl));
            log2.i(sb3.toString());
            this.mFragment.handled = true;
            try {
                if (this.mUrl.getQueryParameter("code") != null) {
                    String queryParameter = this.mUrl.getQueryParameter("code");
                    this.LOG.i("Found " + queryParameter);
                    TokenResponse tokenResponseRetrieveAndStoreAccessToken = this.mFragment.oAuth2Helper.retrieveAndStoreAccessToken(queryParameter);
                    String strRequestEmail = this.mFragment.getEmailHolder().requestEmail(tokenResponseRetrieveAndStoreAccessToken.getAccessToken());
                    if (TextUtils.isEmpty(strRequestEmail)) {
                        this.mAnalytics.onOauthRequestEmailEmptyError();
                        tokensHolder.setErrorMessage(activity.getString(R.string.error_network));
                        return tokensHolder;
                    }
                    tokensHolder.setTokenResponse((OAuthTokenResponse) new GoogleClientTokenResponse(tokenResponseRetrieveAndStoreAccessToken));
                    tokensHolder.setEmail(strRequestEmail);
                    return tokensHolder;
                }
            } catch (Exception e10) {
                e10.printStackTrace();
                tokensHolder.setErrorMessage(activity.getString(R.string.error_network));
            }
            return tokensHolder;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(TokensHolder tokensHolder) {
            FragmentActivity activity = this.mFragment.getActivity();
            FragmentManager fragmentManager = this.mFragment.getFragmentManager();
            if (fragmentManager == null || activity == null || this.mFragment.isStopped()) {
                return;
            }
            fragmentManager.popBackStackImmediate();
            if (tokensHolder.getTokenResponse() != null) {
                tokensHolder.setAccountType(this.mFragment.getArguments().getString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE));
                this.mFragment.getAuthCallBack().onMessageHandle(new Message(Message.Id.AUTHENTICATE_OAUTH, null, tokensHolder));
            } else {
                if (TextUtils.isEmpty(tokensHolder.getErrorMessage())) {
                    return;
                }
                this.mFragment.showAuthError(tokensHolder.getErrorMessage());
            }
        }
    }

    protected abstract EmailHolder getEmailHolder();

    protected abstract String getSpecificAuthUrlParams();

    @Override // ru.mail.auth.webview.BaseWebViewFragment
    public WebView getWebView() {
        return this.webview;
    }

    protected void handleRedirectUrl(Uri uri) {
        ProcessToken processToken = new ProcessToken(uri, this, this.analytics);
        this.mProcessToken = processToken;
        processToken.execute(new Uri[0]);
    }

    @Override // ru.mail.auth.webview.BaseWebViewFragment
    protected void initWebView(View view) {
        ObservableWebView observableWebView = new ObservableWebView(requireActivity());
        this.webview = observableWebView;
        observableWebView.getSettings().setJavaScriptEnabled(true);
        this.webview.setVisibility(0);
        this.webview.getSettings().setSavePassword(false);
        ((FrameLayout) view.findViewById(R.id.webview_container)).addView(this.webview, new FrameLayout.LayoutParams(-1, -1, 0));
        String str = this.oAuth2Helper.getAuthorizationUrl() + getSpecificAuthUrlParams();
        LOG.i("Using authorizationUrl = " + sLogFilter.filter(str));
        this.handled = false;
        WebViewDatabase.getInstance(getActivity()).clearUsernamePassword();
        WebViewDatabase.getInstance(getActivity()).clearHttpAuthUsernamePassword();
        WebViewDatabase.getInstance(getActivity()).clearFormData();
        CookieManager.getInstance().removeAllCookie();
        this.webview.setWebViewClient(this.mWebViewClient);
        this.webview.loadUrl(str);
    }

    protected boolean needHandleRedirectUrl(Uri uri) {
        Uri redirectURI = this.oAuth2Helper.getRedirectURI();
        return TextUtils.equals(redirectURI.getScheme(), uri.getScheme()) && TextUtils.equals(redirectURI.getAuthority(), uri.getAuthority()) && redirectURI.getPathSegments().containsAll(uri.getPathSegments());
    }

    @Override // ru.mail.auth.webview.BaseWebViewFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log log = LOG;
        log.i("Starting task to retrieve request token.");
        this.mRootView = super.onCreateView(layoutInflater, viewGroup, bundle);
        log.i("Starting task to retrieve request token.");
        this.oAuth2Helper = new OAuth2Helper(PreferenceManager.getDefaultSharedPreferences(getActivity()), new Oauth2Params(getArguments(), BearerToken.authorizationHeaderAccessMethod()));
        initWebViewOrStartDialog(this.mRootView);
        initToolbar(this.mRootView, getResources().getString(R.string.add_your_email));
        return this.mRootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        try {
            CookieManager.getInstance().removeAllCookie();
        } catch (RuntimeException e10) {
            LOG.e("Web view init error on destroy", e10);
            onWebViewInitFail();
        }
        ProcessToken processToken = this.mProcessToken;
        if (processToken != null) {
            processToken.cancel(true);
            this.mProcessToken = null;
        }
    }
}
