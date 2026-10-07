package ru.mail.auth.webview;

import android.accounts.Account;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.util.Joiner;
import dagger.hilt.android.AndroidEntryPoint;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.message.TokenParser;
import ru.mail.Authenticator.R;
import ru.mail.auth.Analytics;
import ru.mail.auth.Authenticator;
import ru.mail.auth.MailAccountConstants;
import ru.mail.auth.Message;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.authorizesdk.data.request.common.constants.MailAccountConstantsClass;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@AndroidEntryPoint
public class NativeGoogleSignInFragment extends Hilt_NativeGoogleSignInFragment implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {
    private static final Log LOG = Log.getLog("NativeGoogleSignInFragment");
    private static final int RC_RESOLUTION = 2332;
    private static final int RC_SIGN_IN = 2321;
    public static final String REQUEST_KEY = "NativeGoogleSignInFragment_request_key";
    public static final String SHOULD_EXIT_LOGIN_ON_CANCEL = "should_exit_login_on_cancel";

    @Inject
    Analytics analytics;
    private GoogleApiClient mGoogleApiClient;
    private OAuth2Helper mOAuth2Helper;
    private boolean mNeedClearDefaultAccount = true;
    private boolean mSignInRequested = false;
    private boolean shouldExitLoginOnCancel = false;

    /* JADX INFO: compiled from: ProGuard */
    private static class ProcessTokenTask extends AsyncTask<GoogleSignInAccount, Void, Pair<TokensHolder, String>> {
        private final WeakReference<Analytics> mAnalytics;
        private final String mErrorMsg;
        private final WeakReference<NativeGoogleSignInFragment> mFragment;
        private final OAuth2Helper mHelper;
        private final TokensHolder mTokensHolder;

        private ProcessTokenTask(NativeGoogleSignInFragment nativeGoogleSignInFragment, OAuth2Helper oAuth2Helper, Analytics analytics) {
            this.mFragment = new WeakReference<>(nativeGoogleSignInFragment);
            this.mAnalytics = new WeakReference<>(analytics);
            this.mHelper = oAuth2Helper;
            this.mErrorMsg = nativeGoogleSignInFragment.getString(R.string.error_network);
            TokensHolder tokensHolder = new TokensHolder();
            this.mTokensHolder = tokensHolder;
            tokensHolder.setAccountType(nativeGoogleSignInFragment.getArguments().getString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Pair<TokensHolder, String> doInBackground(GoogleSignInAccount... googleSignInAccountArr) {
            GoogleSignInAccount googleSignInAccount = googleSignInAccountArr[0];
            String serverAuthCode = googleSignInAccount.getServerAuthCode();
            String email = googleSignInAccount.getEmail();
            String strJoin = Joiner.on(TokenParser.SP).join(googleSignInAccount.getGrantedScopes());
            if (TextUtils.isEmpty(email)) {
                this.mAnalytics.get().googleSignInError("email is null or empty");
                this.mTokensHolder.setErrorMessage(this.mErrorMsg);
            } else {
                this.mTokensHolder.setEmail(email);
            }
            if (TextUtils.isEmpty(serverAuthCode)) {
                NativeGoogleSignInFragment.LOG.e("Empty server code");
            } else {
                try {
                    this.mTokensHolder.setTokenResponse((OAuthTokenResponse) new GoogleClientTokenResponse(this.mHelper.retrieveAndStoreAccessToken(serverAuthCode)));
                } catch (IOException e10) {
                    NativeGoogleSignInFragment.LOG.d("Error retrieve token ", e10);
                    this.mAnalytics.get().googleSignInError("Can't retrieveAndStoreAccessToken " + e10.getMessage());
                    this.mTokensHolder.setErrorMessage(this.mErrorMsg);
                }
            }
            return new Pair<>(this.mTokensHolder, strJoin);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Pair<TokensHolder, String> pair) {
            TokensHolder tokensHolder = (TokensHolder) pair.first;
            String str = (String) pair.second;
            NativeGoogleSignInFragment nativeGoogleSignInFragment = this.mFragment.get();
            if (nativeGoogleSignInFragment == null || nativeGoogleSignInFragment.getActivity() == null || nativeGoogleSignInFragment.isStopped()) {
                return;
            }
            nativeGoogleSignInFragment.getFragmentManager().popBackStackImmediate();
            if (!TextUtils.isEmpty(tokensHolder.getErrorMessage()) || tokensHolder.getTokenResponse() == null) {
                NativeGoogleSignInFragment.LOG.e("failed to auth, token resp:" + tokensHolder.getTokenResponse());
                nativeGoogleSignInFragment.showAuthError(tokensHolder.getErrorMessage());
                return;
            }
            NativeGoogleSignInFragment.LOG.i("Auth success");
            this.mAnalytics.get().googleSignInSuccess();
            Bundle arguments = nativeGoogleSignInFragment.getArguments();
            if (arguments == null) {
                arguments = new Bundle();
            }
            arguments.putString(MailAccountConstants.LOGIN_GRANTED_SCOPES, str);
            nativeGoogleSignInFragment.getAuthCallBack().onMessageHandle(new Message(Message.Id.AUTHENTICATE_OAUTH, arguments, tokensHolder));
        }
    }

    private void addScopes(GoogleSignInOptions.Builder builder, Oauth2Params oauth2Params) {
        Collection<String> collectionConvertScopesToString = this.mOAuth2Helper.convertScopesToString(oauth2Params.getScope(), StringUtils.SPACE);
        collectionConvertScopesToString.remove("email");
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = collectionConvertScopesToString.iterator();
        while (it.hasNext()) {
            arrayList.add(new Scope(it.next()));
        }
        builder.requestScopes(new Scope("email"), (Scope[]) arrayList.toArray(new Scope[arrayList.size()]));
    }

    private void fallbackToBrowserAuthFlow() {
        if (getActivity() == null || isStopped()) {
            return;
        }
        LOG.i("fallback to browser flow");
        getFragmentManager().popBackStackImmediate();
        getArguments().putBoolean(MailAccountConstantsClass.EXTRA_USE_NATIVE_SIGNIN, false);
        getAuthCallBack().onMessageHandle(new Message(Message.Id.START_GOOGLE_AUTH, getArguments()));
    }

    private boolean isAccountValid(String str) {
        for (Account account : Authenticator.getAccountManagerWrapper(getActivity().getApplication()).getExternalAccountsByType("com.google")) {
            if (TextUtils.equals(str, account.name)) {
                return true;
            }
        }
        return false;
    }

    private void signIn() {
        if (this.mSignInRequested) {
            return;
        }
        this.analytics.startGoogleSignInActivity();
        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient), RC_SIGN_IN);
        this.mSignInRequested = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i10, int i11, Intent intent) {
        if (i10 != RC_SIGN_IN) {
            if (i10 == RC_RESOLUTION) {
                LOG.i("resolution is " + i11);
                if (i11 == -1) {
                    this.analytics.googleResolutionResultSuccess();
                    this.mGoogleApiClient.connect();
                    return;
                } else {
                    this.analytics.googleSwitchToWebView("on_resolution_result_not_ok");
                    fallbackToBrowserAuthFlow();
                    return;
                }
            }
            return;
        }
        GoogleSignInResult signInResultFromIntent = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
        if (signInResultFromIntent == null) {
            LOG.d("onActivityResult: GoogleSignInResult is null!");
            getAuthCallBack().onMessageHandle(new Message(Message.Id.ON_AUTH_ERROR));
            return;
        }
        Log log = LOG;
        log.d("onActivityResult:GET_AUTH_CODE:success:" + signInResultFromIntent.getStatus().isSuccess());
        log.d("Status code: " + signInResultFromIntent.getStatus().getStatusCode());
        log.d("Status message: " + signInResultFromIntent.getStatus().getStatusMessage());
        log.d("CommonStatusCodes: " + CommonStatusCodes.getStatusCodeString(signInResultFromIntent.getStatus().getStatusCode()));
        if (signInResultFromIntent.isSuccess() && signInResultFromIntent.getSignInAccount() != null) {
            log.d("Processing task...");
            new ProcessTokenTask(this.mOAuth2Helper, this.analytics).execute(signInResultFromIntent.getSignInAccount());
            return;
        }
        log.w("User aborted request or error occurred");
        int statusCode = signInResultFromIntent.getStatus().getStatusCode();
        if (statusCode == 5) {
            this.analytics.googleSignInError("ApiException INVALID_ACCOUNT");
            getAuthCallBack().onMessageHandle(new Message(Message.Id.ON_AUTH_FAILED));
        } else if (statusCode != 12501) {
            this.analytics.googleResultCancelled();
            getAuthCallBack().onMessageHandle(new Message(Message.Id.ON_AUTH_ERROR));
        }
        if (this.shouldExitLoginOnCancel && isAdded()) {
            getParentFragmentManager().setFragmentResult(REQUEST_KEY, requireArguments());
        } else {
            if (isStopped()) {
                return;
            }
            getFragmentManager().popBackStackImmediate();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnected(@Nullable Bundle bundle) {
        if (!this.mNeedClearDefaultAccount) {
            LOG.d("onConnected signIn");
            signIn();
        } else {
            LOG.d("onConnected clearDefault");
            this.mGoogleApiClient.clearDefaultAccountAndReconnect();
            this.mNeedClearDefaultAccount = false;
        }
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log log = LOG;
        log.e("onConnectionFailed");
        this.analytics.googleSignInRequiredResolution();
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (!connectionResult.hasResolution()) {
            log.i("no resolution, showing error dialog");
            this.analytics.googleNoResolutionShowErrorDialog();
            GoogleApiAvailability.getInstance().getErrorDialog(activity, connectionResult.getErrorCode(), RC_RESOLUTION).show();
            return;
        }
        try {
            log.i("starting problem resolution");
            this.analytics.startGoogleResolution();
            connectionResult.startResolutionForResult(activity, RC_RESOLUTION);
        } catch (IntentSender.SendIntentException e10) {
            LOG.e("failed resolution ", e10);
            this.analytics.googleSwitchToWebView("SendIntentException");
            fallbackToBrowserAuthFlow();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnectionSuspended(int i10) {
        LOG.i("onConnectionSuspended " + i10);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        Oauth2Params oauth2Params = new Oauth2Params(getArguments(), BearerToken.authorizationHeaderAccessMethod());
        this.mOAuth2Helper = new OAuth2Helper(defaultSharedPreferences, oauth2Params);
        this.shouldExitLoginOnCancel = getArguments().getBoolean(SHOULD_EXIT_LOGIN_ON_CANCEL);
        GoogleSignInOptions.Builder builderRequestServerAuthCode = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestServerAuthCode(oauth2Params.getClientId(), true);
        addScopes(builderRequestServerAuthCode, oauth2Params);
        String loginHintFromIntent = getLoginHintFromIntent();
        if (!TextUtils.isEmpty(loginHintFromIntent) && isAccountValid(loginHintFromIntent)) {
            builderRequestServerAuthCode.setAccountName(loginHintFromIntent);
            this.mNeedClearDefaultAccount = false;
            LOG.d("loginHint " + loginHintFromIntent);
        }
        this.mGoogleApiClient = new GoogleApiClient.Builder(getResworbkvmocaf()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Auth.GOOGLE_SIGN_IN_API, builderRequestServerAuthCode.build()).build();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.analytics.openGoogleAuth();
        return new FrameLayout(getResworbkvmocaf());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.analytics.googleAuthClosed();
    }

    @Override // ru.mail.auth.BaseAuthFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override // ru.mail.auth.BaseAuthFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        GoogleApiClient googleApiClient = this.mGoogleApiClient;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }
}
