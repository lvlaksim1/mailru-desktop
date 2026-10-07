package ru.mail.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.huawei.hms.support.api.entity.core.CommonCode;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import ru.mail.auth.ludwig.LudwigParams;
import ru.mail.auth.request.MailServerParameters;
import ru.mail.auth.request.MailServerParametersRequest;
import ru.mail.auth.webview.OAuthTokenResponse;
import ru.mail.auth.webview.TokensHolder;
import ru.mail.authorizesdk.auth.request.ProgressLoginCmd;
import ru.mail.authorizesdk.auth.request.ProgressStep;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.authorizesdk.domain.models.NewAuthSdkConfig;
import ru.mail.authorizesdk.domain.models.NewAuthorizationSdkConfig;
import ru.mail.credentialsexchanger.core.VkAuthResult;
import ru.mail.credentialsexchanger.data.entity.VkIdAuthSource;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.OnCommandCompleted;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.util.log.Log;
import ru.mail.utils.RandomStringGenerator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@AndroidEntryPoint
public class MailLoginFragment extends Hilt_MailLoginFragment implements OnCommandCompleted {
    public static final String EXTRA_ACCOUNT_REQUESTED_AUTH = "extra_account_requested_auth";
    public static final String EXTRA_ACCOUNT_TYPE = "accountType";
    public static final String EXTRA_ERROR_CODE = "extra_error_code";
    public static final String EXTRA_ERROR_MESSAGE = "extra_error_message";
    public static final String EXTRA_IMAP_ONLY = "extra_imap_only";
    public static final String EXTRA_IMAP_SKIP_MAILRU_OAUTH_STEPS = "extra_imap_skip_mailru_oauth_steps";
    public static final String EXTRA_OPTIONS = "extra_options";
    public static final String EXTRA_REQUEST_CAPTCHA = "extra_request_captcha";
    public static final String EXTRA_REQUEST_CODE = "extra_request_code";
    private static final Log LOG = Log.getLog("MailLoginFragment");
    public static final int REQUEST_CODE_GET_GOOGLE_REFRESH_TOKEN = 48;
    public static final int REQUEST_CODE_GET_OUTLOOK_REFRESH_TOKEN = 49;
    public static final int REQUEST_CODE_INSTALL_PLAY_SERVICES = 385;
    public static final int REQUEST_CODE_MAIL_SECOND_STEP = 118;
    public static final int REQUEST_CODE_PERMISSION = 38;
    public static final String SAVE_PARAMETER_LOGIN = "SAVE_PARAMETER_LOGIN";
    public static final String SAVE_PARAMETER_PASSWORD = "SAVE_PARAMETER_PASSWORD";
    public static final String YANDEX_DOMAIN = "yandex";

    @Inject
    Analytics analytics;
    public ProgressAsyncTask<String, ProgressStep> mActiveAuthorizeTask;
    private final OnAuthorizeComplete mAuthorizeListener = new OnAuthorizeComplete() { // from class: ru.mail.auth.n2
        @Override // ru.mail.auth.OnAuthorizeComplete
        public final void onAuthorizeCompleted(Bundle bundle) {
            this.f80773a.processAuthorizeResponse(bundle);
        }
    };
    private String mLogin;
    private String mPassword;
    public CallbackTask<?, ?> mSendMailServerParametersTask;
    private AuthMessageCallback mUICallback;

    @Inject
    protected NewAuthSdkConfig newAuthSdkConfig;

    @Inject
    protected NewAuthorizationSdkConfig newAuthorizationSdkConfig;

    public static String getResultKey() {
        return "MailLoginFragmentResultKey";
    }

    public static MailLoginFragment newInstance(String str, boolean z10, boolean z11, Bundle bundle) {
        MailLoginFragment mailLoginFragment = new MailLoginFragment();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(EXTRA_IMAP_SKIP_MAILRU_OAUTH_STEPS, z10);
        bundle.putBoolean(EXTRA_IMAP_ONLY, z11);
        mailLoginFragment.setArguments(str, bundle);
        return mailLoginFragment;
    }

    private void onRegisterResult(Bundle bundle) {
        setLogin(bundle.getString("authAccount"));
        setPassword(bundle.getString("password"));
        startDoregistrationStep((DoregistrationParameter) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_DOREGISTRATION_PARAM), Authenticator.Type.valueOf(bundle.getString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE)));
    }

    private void onSendMailServerSettingsFail(Bundle bundle) {
        onSendMailServerSettingsFail(bundle, Collections.EMPTY_LIST);
    }

    private void onSmsCodeSent(Bundle bundle) {
        if (this.mUICallback == null) {
            return;
        }
        if (((Class) bundle.get(Authenticator.EXTRA_SMS_CODE_STATUS)) == CommandStatus.OK.class) {
            this.mUICallback.onMessageHandle(new Message(Message.Id.ON_SMS_CODE_SEND_SUCCESS, bundle));
        } else {
            this.mUICallback.onMessageHandle(new Message(Message.Id.ON_SMS_CODE_SEND_FAIL, bundle));
        }
    }

    private void processDefaultLogin(Bundle bundle) {
        getActivity().getIntent().putExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN, bundle.getString("authAccount"));
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_LOGIN_SCREEN, null, EmailServiceResources.MailServiceResources.OTHER));
    }

    private void processResponseBundle(Bundle bundle) {
        String string = bundle != null ? bundle.getString(EXTRA_ACCOUNT_REQUESTED_AUTH) : null;
        if (bundle == null) {
            notifyAuthError(string, null, -1);
            return;
        }
        if (bundle.containsKey("errorCode")) {
            int i10 = bundle.getInt("errorCode");
            if (i10 == 22) {
                notifyAuthFailed(string, bundle);
                return;
            } else {
                notifyAuthError(string, bundle.getString("errorMessage"), i10);
                return;
            }
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_MAIL_SERVER_SETTINGS_PARAM)) {
            requestMailServerSettings(bundle.getBoolean(MailAccountConstants.LOGIN_EXTRA_MAIL_SERVER_SETTINGS_PARAM));
            return;
        }
        if (bundle.containsKey("authtoken") || bundle.containsKey(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH)) {
            notifyAuthSuccess(string, bundle);
            return;
        }
        if (bundle.containsKey(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK)) {
            processIntentResponse(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.ACTION_MAIL_SECOND_STEP)) {
            startSecondStep(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.ACTION_MAIL_SSO)) {
            startSSOExtraAuthStep(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.ACTION_MAIL_VK_PASSWORD)) {
            startVkPasswordExtraAuthStep(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.EXTERNAL_AUTH_PROHIBIT)) {
            getUICallback().onMessageHandle(new Message(Message.Id.EXTERNAL_AUTH_PROHIBIT, bundle));
            return;
        }
        if (!bundle.containsKey(Authenticator.KEY_SWA_CODE)) {
            if (bundle.containsKey(MailAccountConstants.ACTION_XMAIL_REG)) {
                getParentFragmentManager().setFragmentResult(getResultKey(), bundle);
                return;
            }
            return;
        }
        int i11 = bundle.getInt(Authenticator.EXTRA_ERROR_CODE_ADDITIONAL, 0);
        if (needShowPassAppsError(string) && i11 == 24) {
            notifyAuthFailedPasswordsForApps();
            this.analytics.authFailedPasswordsForApps();
            return;
        }
        String string2 = bundle.getString("authAccount");
        if (string2 == null) {
            notifyAuthError(string, null, -1);
        } else {
            int i12 = bundle.getInt(Authenticator.KEY_SWA_CODE);
            notifyAuthError(string, AuthErrors.getErrorMessage(getResworbkvmocaf(), string2, i12), i12);
        }
    }

    private boolean showYandexHelpIfNeeded(@Nullable String str) {
        return this.newAuthorizationSdkConfig.getYandexHelpConfig().isEnabled() && (str != null && str.toLowerCase().contains(YANDEX_DOMAIN));
    }

    private void startDoregistrationStep(DoregistrationParameter doregistrationParameter, Authenticator.Type type) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(MailAccountConstants.LOGIN_EXTRA_DOREGISTRATION_PARAM, doregistrationParameter);
        bundle.putString("authAccount", this.mLogin);
        bundle.putString("password", this.mPassword);
        bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type.toString());
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_DOREGISTRATION, bundle));
    }

    private void startGoogleExtraAuthStep(Bundle bundle) {
        Bundle bundle2 = (Bundle) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_GOOGLE_REFRESH_TOKEN);
        String string = bundle.getString("login_extra_xmail_migration_from");
        bundle2.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.OAUTH.toString());
        bundle2.putString("login_extra_xmail_migration_from", string);
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_GOOGLE_AUTH, bundle2));
    }

    private void startMrimDisabledDialog(Intent intent) {
        String stringExtra = intent.getStringExtra("authAccount");
        if (AuthenticatorConfig.getInstance().isMrimDialogInNewSdkEnabled()) {
            Bundle bundle = new Bundle();
            bundle.putString("authAccount", stringExtra);
            this.mUICallback.onMessageHandle(new Message(Message.Id.SHOW_MRIM_DIALOG, bundle));
        } else {
            MrimDisabledDialog.newInstance(getActivity(), stringExtra).show();
        }
        notifyAuthCancelled();
        this.analytics.mrimDisabledView();
    }

    private void startOutlookExtraAuthStep(Bundle bundle) {
        Bundle bundle2 = (Bundle) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_OUTLOOK_REFRESH_TOKEN);
        bundle2.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.OUTLOOK_OAUTH.toString());
        bundle2.putString("authAccount", bundle.getString("authAccount"));
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_OUTLOOK_AUTH, bundle2));
    }

    private void startSSOExtraAuthStep(Bundle bundle) {
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_SSO_AUTH, (Bundle) bundle.getParcelable(MailAccountConstants.ACTION_MAIL_SSO)));
        this.analytics.loginSSOView();
    }

    private void startSecondStep(Bundle bundle) {
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_SECOND_STEP, (Bundle) bundle.getParcelable(MailAccountConstants.ACTION_MAIL_SECOND_STEP)));
        this.analytics.loginTwoFactorView();
    }

    private void startVKConnectExtraAuthStep(Bundle bundle) {
        Bundle bundle2 = (Bundle) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_VK_CONNECT_REFRESH_TOKEN);
        bundle2.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.VK_CONNECT.toString());
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_VK_CONNECT_AUTH, bundle2));
    }

    private void startVkPasswordExtraAuthStep(Bundle bundle) {
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_VK_PASSWORD_AUTH, (Bundle) bundle.getParcelable(MailAccountConstants.ACTION_MAIL_VK_PASSWORD)));
        this.analytics.loginVkPasswordView();
    }

    private void startWebAuthNExtraAuthStep(Bundle bundle) {
        Bundle bundle2 = (Bundle) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_WEB_AUTH_N_REFRESH_TOKEN);
        bundle2.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.WEB_AUTH_N.toString());
        this.mUICallback.onMessageHandle(new Message(Message.Id.AUTHENTICATE, bundle2));
    }

    private void startYahooExtraAuthStep(Bundle bundle) {
        Bundle bundle2 = (Bundle) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_YAHOO_REFRESH_TOKEN);
        bundle2.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.YAHOO_OAUTH.toString());
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_YAHOO_AUTH, bundle2));
    }

    private void startYandexExtraAuthStep(Bundle bundle) {
        Bundle bundle2 = (Bundle) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_YANDEX_REFRESH_TOKEN);
        bundle2.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.YANDEX_OAUTH.toString());
        this.mUICallback.onMessageHandle(new Message(Message.Id.START_YANDEX_AUTH, bundle2));
    }

    public void authenticate(String str, String str2) {
        authenticate(str, str2, Authenticator.getAccountType(str, null));
    }

    public void authenticatePermissionGranted(String str, String str2, Authenticator.Type type) {
        setLogin(str);
        setPassword(str2);
        Bundle bundle = new Bundle();
        bundle.putString(Authenticator.BUNDLE_PARAM_PASSWORD, this.mPassword);
        boolean z10 = true;
        bundle.putBoolean(Authenticator.BUNDLE_PARAM_PERMISSION_GRANTED, true);
        String string = getArguments().getString(EXTRA_ACCOUNT_TYPE);
        if (!this.newAuthSdkConfig.getLudvigCaptchaConfig().isLudvigCaptchaEnabled() && !this.newAuthorizationSdkConfig.getLudvigCaptchaConfig().isLudvigCaptchaEnabled()) {
            z10 = false;
        }
        LudwigParams.INSTANCE.putIsLudwigEnabled(Boolean.valueOf(z10), bundle);
        beginAuthorization(new AuthorizeTask(getActivity(), this.mAuthorizeListener, type, this.mLogin, bundle, string));
        this.analytics.oAuthNative();
    }

    protected void beginAuthorization(ProgressAsyncTask<String, ProgressStep> progressAsyncTask) {
        if (this.mActiveAuthorizeTask != null) {
            cancelActiveAuthorizeTask();
        }
        if (!isAdded()) {
            throw new IllegalStateException("This fragment can't start authentication before attached to activity or after detached from activity");
        }
        this.mActiveAuthorizeTask = progressAsyncTask;
        this.mUICallback.onMessageHandle(new Message(Message.Id.ON_AUTH_STARTED, null, progressAsyncTask));
        this.mActiveAuthorizeTask.execute(new String[0]);
    }

    public void cancelActiveAuthorizeTask() {
        ProgressAsyncTask<String, ProgressStep> progressAsyncTask = this.mActiveAuthorizeTask;
        if (progressAsyncTask != null) {
            progressAsyncTask.cancel();
            this.mActiveAuthorizeTask = null;
        }
    }

    public void cancelSendMailServerSettingsTask() {
        CallbackTask<?, ?> callbackTask = this.mSendMailServerParametersTask;
        if (callbackTask != null) {
            callbackTask.clearCallback();
            this.mSendMailServerParametersTask.cancel();
            this.mSendMailServerParametersTask = null;
        }
    }

    public OnAuthorizeComplete getAuthorizeListener() {
        return this.mAuthorizeListener;
    }

    public String getLogin() {
        return this.mLogin;
    }

    public String getPassword() {
        return this.mPassword;
    }

    protected AuthMessageCallback getUICallback() {
        return this.mUICallback;
    }

    protected boolean needShowPassAppsError(String str) {
        return false;
    }

    protected void notifyAuthCancelled() {
        this.mUICallback.onMessageHandle(new Message(Message.Id.ON_AUTH_CANCELLED));
    }

    protected void notifyAuthError(@Nullable String str, String str2, int i10) {
        Bundle bundle = new Bundle();
        LoginActivity loginActivity = (LoginActivity) getActivity();
        bundle.putInt("errorCode", i10);
        if (i10 == 812 && showYandexHelpIfNeeded(str) && loginActivity != null) {
            loginActivity.openYandexHelp();
        } else {
            this.mUICallback.onMessageHandle(new Message(Message.Id.ON_AUTH_ERROR, bundle, str2));
        }
    }

    protected void notifyAuthFailed(@Nullable String str, Bundle bundle) {
        this.mUICallback.onMessageHandle(new Message(Message.Id.ON_AUTH_FAILED, bundle));
    }

    protected void notifyAuthFailedPasswordsForApps() {
        Bundle bundle = new Bundle();
        bundle.putInt(Authenticator.EXTRA_ERROR_CODE_ADDITIONAL, 24);
        this.mUICallback.onMessageHandle(new Message(Message.Id.ON_AUTH_FAILED, bundle));
    }

    protected void notifyAuthSuccess(@Nullable String str, Bundle bundle) {
        this.mUICallback.onMessageHandle(new Message(Message.Id.ON_AUTH_SUCCEEDED, bundle));
        if (TextUtils.isEmpty(bundle.getString(Authenticator.EXTRA_STATISTIC_MESSAGE))) {
            return;
        }
        this.analytics.loginTwoFactorSuccess();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i10, int i11, Intent intent) {
        super.onActivityResult(i10, i11, intent);
        if (i10 == 38 && i11 == -1) {
            authenticatePermissionGranted(this.mLogin, null, Authenticator.Type.OAUTH);
            return;
        }
        if (i10 == 385 && i11 == -1) {
            authenticate(this.mLogin, null, Authenticator.Type.OAUTH);
        } else if (i11 == 0) {
            notifyAuthCancelled();
        } else {
            getActivity().finish();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.auth.Hilt_MailLoginFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mUICallback = (AuthMessageCallback) activity;
    }

    @Override // ru.mail.mailbox.cmd.OnCommandCompleted
    public void onCommandComplete(Command command) {
        if (this.mUICallback == null) {
            return;
        }
        CommandStatus commandStatus = (CommandStatus) command.getResult();
        Bundle bundle = new Bundle();
        if (commandStatus instanceof CommandStatus.OK) {
            this.mUICallback.onMessageHandle(new Message(Message.Id.ON_SEND_SERVER_SETTINGS_SUCCESS));
            return;
        }
        if (commandStatus instanceof AuthCommandStatus.ERROR_WITH_STATUS_CODE) {
            AuthCommandStatus.ERROR_WITH_STATUS_CODE error_with_status_code = (AuthCommandStatus.ERROR_WITH_STATUS_CODE) commandStatus;
            bundle.putInt(EXTRA_ERROR_CODE, error_with_status_code.getData().intValue());
            bundle.putString(EXTRA_ERROR_MESSAGE, error_with_status_code.getMessage());
            onSendMailServerSettingsFail(bundle);
            return;
        }
        if (commandStatus instanceof NetworkCommandStatus.BAD_REQUEST) {
            List<MailServerParametersRequest.InvalidFieldName> list = (List) commandStatus.getData();
            bundle.putInt(EXTRA_ERROR_CODE, 400);
            onSendMailServerSettingsFail(bundle, list);
        } else if (commandStatus instanceof AuthCommandStatus.CODE_ERROR) {
            bundle.putInt(EXTRA_ERROR_CODE, 429);
            onSendMailServerSettingsFail(bundle, (List) commandStatus.getData());
        } else if (!(commandStatus instanceof NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED)) {
            onSendMailServerSettingsFail(bundle);
        } else {
            bundle.putInt(EXTRA_ERROR_CODE, 408);
            onSendMailServerSettingsFail(bundle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        setLogin(bundle.getString(SAVE_PARAMETER_LOGIN));
        setPassword(bundle.getString(SAVE_PARAMETER_PASSWORD));
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        cancelActiveAuthorizeTask();
        cancelSendMailServerSettingsTask();
    }

    public void onOAuthVKConnect(VkAuthResult.LoginResult loginResult) {
        Bundle bundle = new Bundle();
        String login = loginResult.getMailAccount().getLogin();
        String authUrl = loginResult.getMailAccount().getAuthUrl();
        setPassword(loginResult.getSilentToken());
        bundle.putString(MailAccountConstants.LOGIN_EXTRA_VK_CONNECT_EMAIL, login);
        bundle.putString(MailAccountConstants.LOGIN_EXTRA_VK_CONNECT_AUTH_URL, authUrl);
        bundle.putParcelable(VkIdAuthSource.KEY, loginResult.getSource());
        bundle.putBoolean(MailAccountConstants.LOGIN_EXTRA_VKID_RESET_SOFT_BIND, loginResult.isResetSoftVKIDBind());
        String password = getPassword();
        Authenticator.Type type = Authenticator.Type.VK_CONNECT;
        authenticate(null, password, type, bundle);
        this.analytics.oAuthWebView(type.toString());
    }

    public void onOAuthWebView(TokensHolder tokensHolder, Bundle bundle) {
        boolean z10;
        Bundle bundle2;
        Authenticator.Type typeValueOf = Authenticator.Type.valueOf(tokensHolder.getAccountType());
        OAuthTokenResponse tokenResponse = tokensHolder.getTokenResponse();
        boolean z11 = false;
        if (getArguments() == null || (bundle2 = getArguments().getBundle("extra_options")) == null) {
            z10 = false;
        } else {
            z10 = bundle2.getBoolean(EXTRA_IMAP_ONLY, false);
            z11 = bundle2.getBoolean(EXTRA_IMAP_SKIP_MAILRU_OAUTH_STEPS, false);
        }
        setPassword(((z11 || z10) ? Arrays.asList(Authenticator.Type.YANDEX_OAUTH, Authenticator.Type.VK_CONNECT, Authenticator.Type.OAUTH, Authenticator.Type.YAHOO_OAUTH, Authenticator.Type.OUTLOOK_OAUTH) : Arrays.asList(Authenticator.Type.YANDEX_OAUTH, Authenticator.Type.VK_CONNECT)).contains(typeValueOf) ? tokenResponse.getAccessToken() : tokenResponse.getRefreshToken());
        String email = tokensHolder.getEmail();
        if (!TextUtils.isEmpty(email)) {
            setLogin(email);
        }
        Bundle bundle3 = new Bundle();
        if (tokenResponse.getAccessToken() != null) {
            bundle3.putString(MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN, tokenResponse.getAccessToken());
            bundle3.putLong(MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN_EXPIRED_TIME, tokenResponse.getExpiresInSeconds() == null ? 0L : tokenResponse.getExpiresInSeconds().longValue());
            if (z11 || z10) {
                bundle3.putBoolean(EXTRA_IMAP_SKIP_MAILRU_OAUTH_STEPS, true);
            }
        }
        if (bundle != null) {
            bundle3.putAll(bundle);
        }
        authenticate(this.mLogin, this.mPassword, typeValueOf, bundle3);
        this.analytics.oAuthWebView(typeValueOf.toString());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            notifyAuthCancelled();
            return;
        }
        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();
        if (!MailAccountConstants.ACTION_LOGIN.equals(intent.getAction())) {
            LOG.w("Unknown action for login activity " + intent.getAction());
            notifyAuthCancelled();
            return;
        }
        if (extras != null) {
            if (extras.containsKey(MailAccountConstants.LOGIN_EXTRA_DOREGISTRATION_PARAM)) {
                onRegisterResult(extras);
                return;
            }
            if (extras.containsKey(MailAccountConstants.LOGIN_EXTRA_MAIL_SERVER_SETTINGS_PARAM)) {
                requestMailServerSettings(extras.getBoolean(MailAccountConstants.LOGIN_EXTRA_MAIL_SERVER_SETTINGS_PARAM));
                return;
            }
            if (extras.containsKey(MailAccountConstants.LOGIN_EXTRA_PERMISSION)) {
                Intent intent2 = (Intent) extras.getParcelable(MailAccountConstants.LOGIN_EXTRA_PERMISSION);
                setLogin(extras.getString("authAccount"));
                startActivityForResult(intent2, 38);
                return;
            }
            if (extras.containsKey(MailAccountConstants.LOGIN_EXTRA_NO_PLAY_SERVICES)) {
                Intent intent3 = (Intent) extras.getParcelable(MailAccountConstants.LOGIN_EXTRA_NO_PLAY_SERVICES);
                setLogin(extras.getString("authAccount"));
                startActivityForResult(intent3, REQUEST_CODE_INSTALL_PLAY_SERVICES);
            } else {
                if (extras.containsKey(MailAccountConstants.LOGIN_EXTRA_GOOGLE_REFRESH_TOKEN)) {
                    setLogin(extras.getString("authAccount"));
                    if (this.newAuthorizationSdkConfig.isABExperiment()) {
                        this.analytics.startGoogleRefreshTokenOnResume(this.newAuthorizationSdkConfig.isAnyLoginEnabled());
                    }
                    startGoogleExtraAuthStep(extras);
                    return;
                }
                if (extras.containsKey(MailAccountConstants.LOGIN_EXTRA_OUTLOOK_REFRESH_TOKEN)) {
                    this.mLogin = extras.getString("authAccount");
                    startOutlookExtraAuthStep(extras);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(SAVE_PARAMETER_LOGIN, this.mLogin);
        bundle.putString(SAVE_PARAMETER_PASSWORD, this.mPassword);
    }

    public void processAuthorizeResponse(Bundle bundle) {
        if (this.mUICallback != null) {
            if ((bundle != null ? bundle.getInt(Authenticator.KEY_SWA_CODE, -1) : -1) == 714) {
                processDefaultLogin(bundle);
            } else {
                processResponseBundle(bundle);
            }
        }
    }

    void processIntentResponse(Bundle bundle) {
        Intent intent = (Intent) bundle.getParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK);
        String string = bundle.getString("login_extra_xmail_migration_from");
        if (MailAccountConstants.ACTION_MRIM_DISABLED.equals(intent.getAction())) {
            startMrimDisabledDialog(intent);
            return;
        }
        Bundle extras = intent.getExtras();
        extras.putString("login_extra_xmail_migration_from", string);
        startExtraAuthSteps(extras);
    }

    protected void requestMailServerSettings(boolean z10) {
        if (this.mUICallback != null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(EXTRA_REQUEST_CAPTCHA, z10);
            this.mUICallback.onMessageHandle(new Message(Message.Id.ON_NEED_SEND_SERVER_SETTINGS, bundle));
        }
    }

    public void sendMailServerSettings(MailServerParameters mailServerParameters) {
        cancelSendMailServerSettingsTask();
        ProgressLoginCmd progressLoginCmd = new ProgressLoginCmd(new MailServerParametersRequest(getActivity(), new PreferenceHostProvider(getActivity(), "domain_settings", ru.mail.Authenticator.R.string.domain_settings_default_scheme, ru.mail.Authenticator.R.string.domain_settings_default_host, getArguments().getBundle("extra_options")), mailServerParameters, AuthenticatorConfig.getInstance().getMigrateToPostConfig().getIs12143Enabled()));
        CallbackTask<?, ?> callbackTask = new CallbackTask<>(progressLoginCmd, this);
        this.mSendMailServerParametersTask = callbackTask;
        callbackTask.execute();
        this.mUICallback.onMessageHandle(new Message(Message.Id.ON_SEND_SERVER_SETTINGS_STARTED, null, progressLoginCmd));
    }

    protected void setArguments(String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(EXTRA_ACCOUNT_TYPE, str);
        bundle2.putBundle("extra_options", bundle);
        setArguments(bundle2);
    }

    protected void setLogin(String str) {
        this.mLogin = str != null ? str.toLowerCase() : null;
    }

    protected void setPassword(String str) {
        this.mPassword = str;
    }

    protected void startExtraAuthSteps(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_DOREGISTRATION_PARAM)) {
            onRegisterResult(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_GOOGLE_REFRESH_TOKEN)) {
            if (this.newAuthorizationSdkConfig.isABExperiment()) {
                this.analytics.startGoogleRefreshToken(this.newAuthorizationSdkConfig.isAnyLoginEnabled());
            }
            setLogin(bundle.getString("authAccount"));
            setPassword(null);
            startGoogleExtraAuthStep(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_OUTLOOK_REFRESH_TOKEN)) {
            setLogin(bundle.getString("authAccount"));
            setPassword(null);
            startOutlookExtraAuthStep(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_YAHOO_REFRESH_TOKEN)) {
            setLogin(bundle.getString("authAccount"));
            setPassword(null);
            startYahooExtraAuthStep(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_YANDEX_REFRESH_TOKEN)) {
            setLogin(bundle.getString("authAccount"));
            setPassword(null);
            startYandexExtraAuthStep(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_VK_CONNECT_REFRESH_TOKEN)) {
            setLogin(bundle.getString("authAccount"));
            setPassword(null);
            startVKConnectExtraAuthStep(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_PERMISSION)) {
            startActivityForResult((Intent) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_PERMISSION), 38);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_NO_PLAY_SERVICES)) {
            startActivityForResult((Intent) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_NO_PLAY_SERVICES), REQUEST_CODE_INSTALL_PLAY_SERVICES);
            return;
        }
        if (bundle.containsKey(Authenticator.EXTRA_SMS_CODE_STATUS)) {
            onSmsCodeSent(bundle);
            return;
        }
        if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_REGISTRATION)) {
            getActivity().startActivityForResult((Intent) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_REGISTRATION), 57);
        } else if (bundle.containsKey(MailAccountConstants.LOGIN_EXTRA_WEB_AUTH_N_REFRESH_TOKEN)) {
            setLogin(bundle.getString("authAccount"));
            setPassword(null);
            startWebAuthNExtraAuthStep(bundle);
        }
    }

    private void onSendMailServerSettingsFail(Bundle bundle, List<MailServerParametersRequest.InvalidFieldName> list) {
        this.mUICallback.onMessageHandle(new Message(Message.Id.ON_SEND_SERVER_SETTINGS_FAIL, bundle, list));
    }

    public void authenticate(String str, String str2, Authenticator.Type type) {
        authenticate(str, str2, type, null);
    }

    public void authenticate(String str, String str2, Authenticator.Type type, Bundle bundle) {
        setLogin(str);
        setPassword(str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        Bundle bundle3 = getArguments().getBundle("extra_options");
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        LudwigParams.INSTANCE.putIsLudwigEnabled(Boolean.valueOf(this.newAuthSdkConfig.getLudvigCaptchaConfig().isLudvigCaptchaEnabled() || this.newAuthorizationSdkConfig.getLudvigCaptchaConfig().isLudvigCaptchaEnabled()), bundle2);
        bundle2.putString(Authenticator.BUNDLE_PARAM_PASSWORD, this.mPassword);
        beginAuthorization(new AuthorizeTask(getActivity(), this.mAuthorizeListener, type, this.mLogin, bundle2, getArguments().getString(EXTRA_ACCOUNT_TYPE)));
    }

    public void onOAuthVKConnect(String str, String str2, VkIdAuthSource vkIdAuthSource) {
        Bundle bundle = new Bundle();
        setPassword(RandomStringGenerator.generateString(10));
        bundle.putString(MailAccountConstants.LOGIN_EXTRA_VK_CONNECT_EMAIL, str);
        bundle.putString(MailAccountConstants.LOGIN_EXTRA_VK_CONNECT_AUTH_URL, str2);
        bundle.putParcelable(VkIdAuthSource.KEY, vkIdAuthSource);
        String password = getPassword();
        Authenticator.Type type = Authenticator.Type.VK_CONNECT;
        authenticate(null, password, type, bundle);
        this.analytics.oAuthWebView(type.toString());
    }

    public void executeImapRedirect(String str, String str2, String str3) {
    }
}
