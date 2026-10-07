package ru.mail.auth.webview;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.api.client.auth.oauth2.BearerToken;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import net.openid.appauth.AppAuthConfiguration;
import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.TokenResponse;
import net.openid.appauth.browser.AnyBrowserMatcher;
import net.openid.appauth.browser.BrowserDescriptor;
import net.openid.appauth.browser.BrowserSelector;
import net.openid.appauth.browser.Browsers;
import net.openid.appauth.browser.ExactBrowserMatcher;
import net.openid.appauth.browser.VersionRange;
import net.openid.appauth.browser.VersionedBrowserMatcher;
import org.json.JSONException;
import ru.mail.android_utils.PendingIntentCreator;
import ru.mail.android_utils.PendingIntentUtils;
import ru.mail.auth.AuthMessageCallback;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.Message;
import ru.mail.auth.request.GetEmailRequest;
import ru.mail.auth.request.GoogleGetEmailRequest;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.authorizesdk.data.request.common.constants.MailAccountConstantsClass;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.uikit.R;
import ru.mail.util.log.Log;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class AppAuthGoogleSignInDelegate implements LifecycleDelegate {
    private static final String APPAUTH_ACTION = "google_sign_in";
    private static final String EXTRA_AUTH_STATE = "authState";
    private String mAccountType;
    private Context mAppContext;

    @NonNull
    private Bundle mAuthParams;

    @Nullable
    private AuthorizationService mAuthService;

    @Nullable
    private String mLoginHint;

    @Nullable
    private AuthMessageCallback mMessageCallback;
    private Oauth2Params mOauth2Params;
    private static final Log LOG = Log.getLog("AppAuthGoogleSignInDelegate");
    public static final Parcelable.Creator<AppAuthGoogleSignInDelegate> CREATOR = new Parcelable.Creator<AppAuthGoogleSignInDelegate>() { // from class: ru.mail.auth.webview.AppAuthGoogleSignInDelegate.1
        @Override // android.os.Parcelable.Creator
        public AppAuthGoogleSignInDelegate createFromParcel(Parcel parcel) {
            return new AppAuthGoogleSignInDelegate(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AppAuthGoogleSignInDelegate[] newArray(int i10) {
            return new AppAuthGoogleSignInDelegate[i10];
        }
    };

    /* JADX INFO: compiled from: ProGuard */
    private static class TokenExchangeCallback implements AuthorizationService.TokenResponseCallback {
        private final WeakReference<AppAuthGoogleSignInDelegate> mDelegate;

        TokenExchangeCallback(AppAuthGoogleSignInDelegate appAuthGoogleSignInDelegate) {
            this.mDelegate = new WeakReference<>(appAuthGoogleSignInDelegate);
        }

        @Override // net.openid.appauth.AuthorizationService.TokenResponseCallback
        public void onTokenRequestCompleted(@Nullable TokenResponse tokenResponse, @Nullable AuthorizationException authorizationException) {
            AppAuthGoogleSignInDelegate appAuthGoogleSignInDelegate = this.mDelegate.get();
            if (tokenResponse != null) {
                new TokenExchangeTask(appAuthGoogleSignInDelegate).execute(tokenResponse);
            } else if (appAuthGoogleSignInDelegate != null) {
                appAuthGoogleSignInDelegate.onAuthError();
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class TokenExchangeTask extends AsyncTask<TokenResponse, Void, TokensHolder> {
        private final String mAccType;
        private final Context mAppContext;
        private final WeakReference<AppAuthGoogleSignInDelegate> mDelegate;

        TokenExchangeTask(AppAuthGoogleSignInDelegate appAuthGoogleSignInDelegate) {
            this.mDelegate = new WeakReference<>(appAuthGoogleSignInDelegate);
            this.mAppContext = appAuthGoogleSignInDelegate.mAppContext;
            this.mAccType = appAuthGoogleSignInDelegate.mAccountType;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public TokensHolder doInBackground(TokenResponse... tokenResponseArr) {
            TokensHolder tokensHolder = new TokensHolder();
            TokenResponse tokenResponse = tokenResponseArr[0];
            tokensHolder.setTokenResponse((OAuthTokenResponse) new AppAuthTokenResponse(tokenResponse));
            tokensHolder.setAccountType(this.mAccType);
            GoogleGetEmailRequest googleGetEmailRequest = new GoogleGetEmailRequest(this.mAppContext, tokenResponse.f77522c);
            String email = null;
            try {
                CommandStatus<?> orThrow = googleGetEmailRequest.execute(ExecutorSelectors.defaultSelector()).getOrThrow();
                if (orThrow instanceof CommandStatus.OK) {
                    email = ((GetEmailRequest.Result) orThrow.getData()).getEmail();
                }
            } catch (InterruptedException unused) {
            } catch (ExecutionException e10) {
                throw new RuntimeException("Unable to execute GoogleGetEmailRequest", e10);
            }
            tokensHolder.setEmail(email);
            return tokensHolder;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(TokensHolder tokensHolder) {
            AppAuthGoogleSignInDelegate appAuthGoogleSignInDelegate = this.mDelegate.get();
            if (appAuthGoogleSignInDelegate != null) {
                if (TextUtils.isEmpty(tokensHolder.getEmail())) {
                    appAuthGoogleSignInDelegate.onAuthError();
                } else {
                    appAuthGoogleSignInDelegate.onTokenExchangeComplete(tokensHolder);
                }
            }
        }
    }

    public AppAuthGoogleSignInDelegate(@NonNull Bundle bundle) {
        this.mAuthParams = bundle;
        initAuthParams();
    }

    @NonNull
    private AppAuthConfiguration createConfiguration(Context context) {
        List<BrowserDescriptor> allBrowsers;
        try {
            allBrowsers = BrowserSelector.getAllBrowsers(context);
        } catch (Exception unused) {
            allBrowsers = Collections.EMPTY_LIST;
        }
        BrowserDescriptor browserDescriptorSelectSupportedBrowser = selectSupportedBrowser(allBrowsers);
        AppAuthConfiguration.Builder builder = new AppAuthConfiguration.Builder();
        if (browserDescriptorSelectSupportedBrowser != null) {
            builder.setBrowserMatcher(new ExactBrowserMatcher(browserDescriptorSelectSupportedBrowser));
        } else {
            builder.setBrowserMatcher(AnyBrowserMatcher.f77536a);
        }
        return builder.build();
    }

    private AuthState getAuthStateFromIntent(Intent intent) {
        if (!intent.hasExtra(EXTRA_AUTH_STATE)) {
            throw new IllegalArgumentException("The AuthState instance is missing in the intent.");
        }
        try {
            return AuthState.jsonDeserialize(intent.getStringExtra(EXTRA_AUTH_STATE));
        } catch (JSONException unused) {
            throw new IllegalArgumentException("The AuthState instance is missing in the intent.");
        }
    }

    @NonNull
    private Oauth2Params getOauth2Params(Bundle bundle) {
        return new Oauth2Params(bundle, BearerToken.authorizationHeaderAccessMethod());
    }

    private void initAuthParams() {
        this.mOauth2Params = getOauth2Params(this.mAuthParams);
        this.mAccountType = this.mAuthParams.getString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE);
        this.mLoginHint = this.mAuthParams.getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT);
    }

    public static boolean isSuitableAction(String str) {
        return TextUtils.equals(str, APPAUTH_ACTION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAuthError() {
        AuthMessageCallback authMessageCallback = this.mMessageCallback;
        if (authMessageCallback != null) {
            authMessageCallback.onMessageHandle(new Message(Message.Id.ON_AUTH_ERROR));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTokenExchangeComplete(TokensHolder tokensHolder) {
        AuthMessageCallback authMessageCallback = this.mMessageCallback;
        if (authMessageCallback != null) {
            authMessageCallback.onMessageHandle(new Message(Message.Id.AUTHENTICATE_OAUTH, null, tokensHolder));
        }
    }

    @Nullable
    private BrowserDescriptor selectSupportedBrowser(List<BrowserDescriptor> list) {
        ArrayList<VersionedBrowserMatcher> arrayList = new ArrayList();
        arrayList.add(VersionedBrowserMatcher.f77560e);
        arrayList.add(new VersionedBrowserMatcher("com.sec.android.app.sbrowser", (Set<String>) Browsers.SBrowser.f77548a, true, VersionRange.atLeast(AuthenticatorConfig.getInstance().getMinSupportedSBrowserVersion())));
        arrayList.add(VersionedBrowserMatcher.f77561f);
        arrayList.add(VersionedBrowserMatcher.f77563h);
        arrayList.add(VersionedBrowserMatcher.f77564i);
        for (VersionedBrowserMatcher versionedBrowserMatcher : arrayList) {
            for (BrowserDescriptor browserDescriptor : list) {
                if (versionedBrowserMatcher.matches(browserDescriptor) && !supportCustomTabs(browserDescriptor)) {
                    return browserDescriptor;
                }
            }
        }
        return null;
    }

    private boolean supportCustomTabs(BrowserDescriptor browserDescriptor) {
        return browserDescriptor.f77542d.booleanValue();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onCreate(FragmentActivity fragmentActivity, AuthMessageCallback authMessageCallback) {
        this.mMessageCallback = authMessageCallback;
        this.mAppContext = fragmentActivity.getApplicationContext();
    }

    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onDestroy() {
        AuthorizationService authorizationService = this.mAuthService;
        if (authorizationService != null) {
            authorizationService.dispose();
            this.mAuthService = null;
        }
        this.mMessageCallback = null;
    }

    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onNewIntent(Intent intent) {
        if (isSuitableAction(intent.getAction())) {
            AuthState authStateFromIntent = getAuthStateFromIntent(intent);
            AuthorizationResponse authorizationResponseFromIntent = AuthorizationResponse.fromIntent(intent);
            authStateFromIntent.update(authorizationResponseFromIntent, AuthorizationException.fromIntent(intent));
            if (authorizationResponseFromIntent == null || this.mAuthService == null) {
                return;
            }
            HashMap map = new HashMap();
            if (!TextUtils.isEmpty(this.mOauth2Params.getSecretId())) {
                map.put(SharedKt.PARAM_CLIENT_SECRET, this.mOauth2Params.getSecretId());
            }
            this.mAuthService.performTokenRequest(authorizationResponseFromIntent.createTokenExchangeRequest(map), new TokenExchangeCallback(this));
        }
    }

    public void onRequestAuthCode(Activity activity) {
        AuthorizationServiceConfiguration authorizationServiceConfiguration = new AuthorizationServiceConfiguration(Uri.parse(this.mOauth2Params.getAuthServerUrl()), Uri.parse(this.mOauth2Params.getTokenServerUrl()), null);
        this.mAuthService = new AuthorizationService(activity, createConfiguration(this.mAppContext));
        HashMap map = new HashMap();
        map.put(MailBoxFolder.COL_NAME_ACCESS_TYPE, "offline");
        AuthorizationRequest authorizationRequestBuild = new AuthorizationRequest.Builder(authorizationServiceConfiguration, this.mOauth2Params.getClientId(), "code", Uri.parse(this.mOauth2Params.getRedirectUri())).setAdditionalParameters(map).setPromptValues("select_account", "consent").setScope(this.mOauth2Params.getScope()).setLoginHint(this.mLoginHint).build();
        Intent intent = new Intent(activity, activity.getClass());
        intent.setAction(APPAUTH_ACTION);
        intent.addFlags(603979776);
        intent.putExtra(EXTRA_AUTH_STATE, new AuthState().jsonSerializeString());
        PendingIntent activity2 = PendingIntentCreator.getActivity(activity, authorizationRequestBuild.hashCode(), intent, PendingIntentUtils.getPendingIntentFlags(true) | 1073741824);
        try {
            AuthorizationService authorizationService = this.mAuthService;
            authorizationService.performAuthorizationRequest(authorizationRequestBuild, activity2, authorizationService.createCustomTabsIntentBuilder(new Uri[0]).setToolbarColor(ContextCompat.getColor(activity, R.color.action_bar_bg)).build());
        } catch (ActivityNotFoundException unused) {
            AuthMessageCallback authMessageCallback = this.mMessageCallback;
            if (authMessageCallback != null) {
                authMessageCallback.onMessageHandle(new Message(Message.Id.ON_AUTH_ERROR, null, this.mAppContext.getString(ru.mail.Authenticator.R.string.error_no_browser_found)));
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        parcel.writeBundle(this.mAuthParams);
    }

    protected AppAuthGoogleSignInDelegate(Parcel parcel) {
        this.mAuthParams = parcel.readBundle(getClass().getClassLoader());
        initAuthParams();
    }

    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onStop() {
    }

    @Override // ru.mail.auth.webview.LifecycleDelegate
    public void onResume(FragmentActivity fragmentActivity, AuthMessageCallback authMessageCallback) {
    }
}
