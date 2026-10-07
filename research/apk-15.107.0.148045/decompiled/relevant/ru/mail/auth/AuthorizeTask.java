package ru.mail.auth;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import ru.mail.auth.request.AuthorizeRequest;
import ru.mail.auth.request.AuthorizeTokenRequest;
import ru.mail.auth.request.HttpsAuthorizeLoginRequest;
import ru.mail.auth.webview.MailSecondStepFragment;
import ru.mail.authorizesdk.auth.request.ProgressLoginCmd;
import ru.mail.authorizesdk.auth.request.ProgressStep;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.credentialsexchanger.data.entity.VkIdAuthSource;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.network.HostProvider;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
class AuthorizeTask extends ProgressAsyncTask<String, ProgressStep> {
    private static final String EXTRA_PROGRESS_LISTENERS = "extra_progress_listeners";
    private static final Log LOG = Log.getLog("AuthorizeTask");
    private final String accountType;
    private final Authenticator.Type mAuthType;
    private final Context mContext;
    private final Bundle mExtras;
    private final String mLogin;

    AuthorizeTask(Context context, OnAuthorizeComplete onAuthorizeComplete, Authenticator.Type type, String str, Bundle bundle, String str2) {
        super(onAuthorizeComplete);
        this.mContext = context;
        this.mAuthType = type;
        this.mLogin = str;
        this.mExtras = bundle;
        this.accountType = str2;
    }

    static void executeProgressCmdWithListeners(ProgressLoginCmd progressLoginCmd, Bundle bundle) {
        progressLoginCmd.addObservers(getProgressListeners(bundle));
        try {
            progressLoginCmd.execute(ExecutorSelectors.defaultSelector()).getOrThrow();
        } catch (InterruptedException | ExecutionException e10) {
            LOG.i("Unable to execute command", e10);
        }
    }

    static List<ProgressListener<ProgressStep>> getProgressListeners(Bundle bundle) {
        Serializable serializable = bundle.getSerializable(EXTRA_PROGRESS_LISTENERS);
        return serializable == null ? new ArrayList() : (List) serializable;
    }

    static AuthorizeRequest httpAuthResponse(Context context, HostProvider hostProvider, String str, String str2, Bundle bundle, boolean z10, boolean z11, Map<String, String> map) {
        HttpsAuthorizeLoginRequest httpsAuthorizeLoginRequest = new HttpsAuthorizeLoginRequest(context, hostProvider, str, str2, MailSecondStepFragment.getTsaCookie(context, str, bundle), z10, z11, map, bundle.getBoolean(Authenticator.SHOULD_RESET_PASSWORD));
        executeProgressCmdWithListeners(httpsAuthorizeLoginRequest, bundle);
        return httpsAuthorizeLoginRequest;
    }

    @NonNull
    private Bundle packExceptionToBundle(NetworkErrorException networkErrorException) {
        Bundle bundle = new Bundle();
        bundle.putInt("errorCode", 23);
        bundle.putString("errorMessage", networkErrorException.getMessage());
        return bundle;
    }

    static AuthorizeRequest secondStepAuthResponse(Context context, HostProvider hostProvider, String str, Map<String, String> map, Map<String, String> map2, boolean z10, boolean z11, Bundle bundle) {
        AuthorizeTokenRequest authorizeTokenRequest = new AuthorizeTokenRequest(context, str, map, map2, hostProvider, z10, z11);
        executeProgressCmdWithListeners(authorizeTokenRequest, bundle);
        return authorizeTokenRequest;
    }

    static AuthorizeRequest ssoAuthResponse(Context context, HostProvider hostProvider, String str, Map<String, String> map, boolean z10, boolean z11, Bundle bundle) {
        AuthorizeTokenRequest authorizeTokenRequest = new AuthorizeTokenRequest(context, str, map, new HashMap(), hostProvider, z10, z11);
        executeProgressCmdWithListeners(authorizeTokenRequest, bundle);
        return authorizeTokenRequest;
    }

    static AuthorizeRequest vkPasswordAuthResponse(Context context, HostProvider hostProvider, String str, Map<String, String> map, boolean z10, boolean z11, Bundle bundle) {
        AuthorizeTokenRequest authorizeTokenRequest = new AuthorizeTokenRequest(context, str, map, new HashMap(), hostProvider, z10, z11);
        executeProgressCmdWithListeners(authorizeTokenRequest, bundle);
        return authorizeTokenRequest;
    }

    public static CommandStatus<?> wrapWithProgressAndExecute(SingleRequest<?, ?> singleRequest, Bundle bundle) {
        ProgressLoginCmd progressLoginCmd = new ProgressLoginCmd(singleRequest);
        progressLoginCmd.addObservers(getProgressListeners(bundle));
        try {
            progressLoginCmd.execute(ExecutorSelectors.defaultSelector()).getOrThrow();
        } catch (InterruptedException | ExecutionException e10) {
            LOG.i("Unable to execute command", e10);
        }
        return singleRequest.getResult();
    }

    public String getLogin() {
        return this.mLogin;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Bundle doInBackground(String... strArr) {
        try {
            this.mExtras.putSerializable(EXTRA_PROGRESS_LISTENERS, getObservers());
            Bundle bundleAuthenticate = this.mAuthType.getMPopStrategy().authenticate(this.mContext, new MailAccount(this.mLogin, this.accountType), this.mExtras);
            bundleAuthenticate.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, this.mLogin);
            bundleAuthenticate.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, this.mAuthType.name());
            String string = this.mExtras.getString(Authenticator.BUNDLE_KEY_2FACTOR_AUTH_TSA_COOKIE, null);
            if (string != null) {
                bundleAuthenticate.putString(Authenticator.BUNDLE_KEY_2FACTOR_AUTH_TSA_COOKIE, string);
            }
            String string2 = this.mExtras.getString(Authenticator.SSO_AUTH_AG_TOKEN, null);
            if (string2 != null) {
                bundleAuthenticate.putString(Authenticator.SSO_AUTH_AG_TOKEN, string2);
            }
            String string3 = this.mExtras.getString(Authenticator.VK_PASSWORD_AUTH_AG_TOKEN, null);
            if (string3 != null) {
                bundleAuthenticate.putString(Authenticator.VK_PASSWORD_AUTH_AG_TOKEN, string3);
            }
            String string4 = this.mExtras.getString("login_extra_xmail_migration_from");
            Bundle bundle = (Bundle) bundleAuthenticate.getParcelable(MailAccountConstants.ACTION_MAIL_SECOND_STEP);
            if (string4 != null) {
                if (bundle != null) {
                    bundle.putString("login_extra_xmail_migration_from", string4);
                } else {
                    bundleAuthenticate.putString("login_extra_xmail_migration_from", string4);
                }
            }
            bundleAuthenticate.putParcelable(VkIdAuthSource.KEY, this.mExtras.getParcelable(VkIdAuthSource.KEY));
            return bundleAuthenticate;
        } catch (NetworkErrorException e10) {
            e10.printStackTrace();
            return packExceptionToBundle(e10);
        } catch (IllegalStateException e11) {
            LOG.e("Failed to get authenticate result", e11);
            return new Bundle();
        }
    }
}
