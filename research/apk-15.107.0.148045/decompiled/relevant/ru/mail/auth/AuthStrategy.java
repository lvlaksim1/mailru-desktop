package ru.mail.auth;

import android.accounts.Account;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.huawei.hms.support.api.entity.core.CommonCode;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.ludwig.LudwigParams;
import ru.mail.auth.request.AuthorizeRequest;
import ru.mail.auth.request.AuthorizeRequestCommand;
import ru.mail.auth.request.AuthorizeResult;
import ru.mail.auth.request.BaseOAuthLoginRequest;
import ru.mail.auth.request.CgiBinAuthSendAgentRequest;
import ru.mail.auth.request.HttpsAuthorizeLoginCommand;
import ru.mail.auth.request.VKOauth2SendAgentRequest;
import ru.mail.auth.webview.MailSecondStepFragment;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.deviceinfo.di.DeviceInfoEntryPoint;
import ru.mail.di.AuthDeviceInfoEntryPoint;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.registration.Statistic;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class AuthStrategy {
    private static final Log LOG = Log.getLog("AuthStrategy");
    protected final Authenticator.AuthVisitor mVisitor;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class AuthHostProvider extends PreferenceHostProvider {
        private final String mExtraUserAgent;

        public AuthHostProvider(Context context, Bundle bundle) {
            super(context, "auth", ru.mail.Authenticator.R.string.auth_default_scheme, ru.mail.Authenticator.R.string.auth_default_host, bundle);
            this.mExtraUserAgent = bundle != null ? bundle.getString(MailAccountConstants.EXTRA_AUTH_USER_AGENT) : null;
        }

        @Override // ru.mail.network.PreferenceHostProvider
        public void getPlatformParams(Uri.Builder builder) {
            builder.appendQueryParameter("Lang", DeviceInfoEntryPoint.localeInfoProvider(getApplicationContext()).getLanguage());
            super.getPlatformParams(builder);
        }

        @Override // ru.mail.network.PreferenceHostProvider
        public String getUserAgentImpl() {
            return TextUtils.isEmpty(this.mExtraUserAgent) ? super.getUserAgentImpl() : this.mExtraUserAgent;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class SwaHostProvider extends PreferenceHostProvider {
        private final String mExtraUserAgent;

        public SwaHostProvider(Context context, Bundle bundle) {
            super(context, "auth", ru.mail.Authenticator.R.string.swa_default_scheme, ru.mail.Authenticator.R.string.swa_default_host, bundle);
            this.mExtraUserAgent = bundle != null ? bundle.getString(MailAccountConstants.EXTRA_AUTH_USER_AGENT) : null;
        }

        @Override // ru.mail.network.PreferenceHostProvider
        public void getPlatformParams(Uri.Builder builder) {
            builder.appendQueryParameter("Lang", DeviceInfoEntryPoint.localeInfoProvider(getApplicationContext()).getLanguage());
            super.getPlatformParams(builder);
        }

        @Override // ru.mail.network.PreferenceHostProvider
        public String getUserAgentImpl() {
            return TextUtils.isEmpty(this.mExtraUserAgent) ? super.getUserAgentImpl() : this.mExtraUserAgent;
        }
    }

    public AuthStrategy(Authenticator.AuthVisitor authVisitor) {
        this.mVisitor = authVisitor;
    }

    @NonNull
    private Bundle authenticateOauth(Context context, MailAccount mailAccount, CommandStatus<?> commandStatus, Authenticator.Type type) throws NetworkErrorException {
        Bundle bundle = new Bundle();
        bundle.putString(MailAccountConstants.EXTRA_AUTH_USER_AGENT, ((AuthCommandStatus.OAUTH_REQUIRED) commandStatus).getData());
        return type.getMPopStrategy().authenticate(context, mailAccount, bundle);
    }

    private boolean checkMpopCookieTransitionFailed(CommandStatus<?> commandStatus, Context context, MailAccount mailAccount) {
        return (!AuthorizeRequest.isAuthFailedResult(commandStatus) || AuthenticatorConfig.getInstance().isOAuthEnabled() || TextUtils.isEmpty(getOAuthToken(context, mailAccount))) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getMpopCookie(Context context, MailAccount mailAccount) {
        return getToken(context, mailAccount, "ru.mail");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getOAuthToken(Context context, MailAccount mailAccount) {
        return getToken(context, mailAccount, MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH);
    }

    private static String getToken(Context context, MailAccount mailAccount, String str) {
        return Authenticator.getAccountManagerWrapper(context).peekAuthToken(new Account(mailAccount.name, mailAccount.type), str);
    }

    @NonNull
    private Bundle handleStatusOK(Context context, MailAccount mailAccount, String str, Command<?, ?> command, CommandStatus<?> commandStatus) throws NetworkErrorException, IllegalStateException {
        Log log = LOG;
        log.i("Auth result is OK, status = " + commandStatus);
        AuthorizeResult authorizeResult = (AuthorizeResult) commandStatus.getData();
        Bundle bundle = (Bundle) authorizeResult.accept(new AuthResultVisitor(str, mailAccount));
        authorizeResult.accept(new LogAuthResultVisitor(context, mailAccount));
        if (bundle == null) {
            log.e("Could not obtain auth data from visitor");
            throw new NetworkErrorException(AuthErrors.getErrorMessage(context, mailAccount.name, 500));
        }
        onRegisterRequired(command, bundle);
        if (commandStatus.getData() instanceof HttpsAuthorizeLoginCommand.TsaCookieResult) {
            String tsaCookie = ((HttpsAuthorizeLoginCommand.TsaCookieResult) commandStatus.getData()).getTsaCookie();
            if (!TextUtils.isEmpty(tsaCookie)) {
                bundle.putString(Authenticator.BUNDLE_KEY_2FACTOR_AUTH_TSA_COOKIE, tsaCookie);
                log.e("set tsa after quick 2fa");
            }
        }
        if (command instanceof CgiBinAuthSendAgentRequest) {
            bundle.putString(MailAccountConstants.LOGIN_EXTRA_CGI_BIN_AUTH_FROM, ((CgiBinAuthSendAgentRequest) command).getTypeTag());
        }
        return bundle;
    }

    private Bundle setDeviceInfo(Context context, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!bundle.containsKey("deviceInfo")) {
            bundle.putSerializable("deviceInfo", AuthDeviceInfoEntryPoint.authDeviceInfoFactory(context).getDeviceInfo());
        }
        return bundle;
    }

    @NotNull
    public abstract Bundle authenticate(Context context, MailAccount mailAccount, Bundle bundle) throws NetworkErrorException;

    protected HostProvider createHostProvider(Context context, Bundle bundle) {
        return new AuthHostProvider(context.getApplicationContext(), setDeviceInfo(context, bundle));
    }

    public abstract void onRegisterRequired(Command<?, ?> command, Bundle bundle);

    /* JADX WARN: Multi-variable type inference failed */
    public Bundle processAuthResponse(Context context, MailAccount mailAccount, String str, Command<?, ?> command) throws NetworkErrorException, IllegalStateException {
        MailAccount mailAccount2 = (TextUtils.isEmpty(mailAccount.name) && (command instanceof VKOauth2SendAgentRequest)) ? new MailAccount(((VKOauth2SendAgentRequest) command).getEmail(), mailAccount.type) : mailAccount;
        Log log = LOG;
        log.i("Processing auth response for account " + mailAccount2.name);
        CommandStatus<?> commandStatus = (CommandStatus) command.getResult();
        log.i("Command status is " + commandStatus);
        Bundle bundle = new Bundle();
        if (checkMpopCookieTransitionFailed(commandStatus, context, mailAccount2)) {
            AuthenticatorEntryPoint.analytics(context).failedTransitionToCookie();
            log.i("Cookie transition failed");
        }
        if (commandStatus != null && !(commandStatus instanceof CommandStatus.CANCELLED)) {
            if (commandStatus instanceof AuthCommandStatus.SOCIAL_AUTH_OK) {
                AuthCommandStatus.SOCIAL_AUTH_OK social_auth_ok = (AuthCommandStatus.SOCIAL_AUTH_OK) commandStatus;
                return handleStatusOK(context, new MailAccount(social_auth_ok.getBindedEmail(), mailAccount2.type), str, command, (CommandStatus) ((CommandStatus) social_auth_ok.getData()).getData());
            }
            if (commandStatus instanceof AuthCommandStatus.MIGRANT_REG_REQUIRED) {
                bundle.putSerializable(MailAccountConstants.ACTION_XMAIL_REG, ((BaseOAuthLoginRequest.Result) commandStatus.getData()).getMigrantToken());
                return bundle;
            }
            if (commandStatus instanceof CommandStatus.OK) {
                return handleStatusOK(context, mailAccount2, str, command, commandStatus);
            }
            if (commandStatus instanceof AuthCommandStatus.MAIL_SERVER_SETTINGS_REQUIRED) {
                boolean zBooleanValue = ((Boolean) ((AuthCommandStatus.MAIL_SERVER_SETTINGS_REQUIRED) commandStatus).getData()).booleanValue();
                bundle.putBoolean(MailAccountConstants.LOGIN_EXTRA_MAIL_SERVER_SETTINGS_PARAM, zBooleanValue);
                log.i("Captcha is required: " + zBooleanValue);
                return bundle;
            }
            if (commandStatus instanceof AuthCommandStatus.EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED) {
                DoregistrationParameter data = ((AuthCommandStatus.EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED) commandStatus).getData();
                Bundle bundle2 = new Bundle();
                Intent intentPutExtra = Authenticator.getLoginActivityIntent(context.getPackageName()).addCategory("android.intent.category.DEFAULT").putExtra(MailAccountConstants.LOGIN_EXTRA_DOREGISTRATION_PARAM, data).putExtra("authAccount", mailAccount2.name).putExtra("password", str);
                bundle.putParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, intentPutExtra);
                onRegisterRequired(command, bundle2);
                intentPutExtra.putExtras(bundle2);
                return bundle;
            }
            if (commandStatus instanceof AuthCommandStatus.OAUTH_OUTLOOK_REQUIRED) {
                return authenticateOauth(context, mailAccount2, commandStatus, Authenticator.Type.OUTLOOK_OAUTH);
            }
            if (commandStatus instanceof AuthCommandStatus.OAUTH_YAHOO_REQUIRED) {
                return authenticateOauth(context, mailAccount2, commandStatus, Authenticator.Type.YAHOO_OAUTH);
            }
            if (commandStatus instanceof AuthCommandStatus.OAUTH_YANDEX_REQUIRED) {
                return authenticateOauth(context, mailAccount2, commandStatus, Authenticator.Type.YANDEX_OAUTH);
            }
            if (commandStatus instanceof AuthCommandStatus.OAUTH_REQUIRED) {
                return authenticateOauth(context, mailAccount2, commandStatus, Authenticator.Type.OAUTH);
            }
            if (commandStatus instanceof NetworkCommandStatus.ERROR_INVALID_LOGIN) {
                bundle.putInt("errorCode", 22);
                return bundle;
            }
            if (commandStatus instanceof AuthCommandStatus.MRIM_DISABLED) {
                Intent intent = new Intent(MailAccountConstants.ACTION_MRIM_DISABLED);
                intent.setPackage(context.getPackageName());
                intent.putExtra("authAccount", mailAccount2.name);
                intent.putExtra(MailLoginFragment.EXTRA_ACCOUNT_TYPE, mailAccount2.type);
                bundle.putParcelable(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, intent);
                return bundle;
            }
            if (commandStatus instanceof AuthCommandStatus.MAIL_SECOND_STEP_REQUIRED) {
                AuthCommandStatus.MAIL_SECOND_STEP_REQUIRED.SecondStepParams data2 = ((AuthCommandStatus.MAIL_SECOND_STEP_REQUIRED) commandStatus).getData();
                Bundle bundle3 = new Bundle();
                bundle3.putString("authAccount", mailAccount2.name);
                bundle3.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.DEFAULT.toString());
                bundle3.putString("password", str);
                bundle3.putString(MailSecondStepFragment.EXT_SECSTEP_COOKIE_HEADER, data2.getSecondStepCookie());
                bundle3.putString("url", data2.getSecondStepUrl());
                LudwigParams.INSTANCE.putLudwigToken(data2.getSecondStepLudwigToken(), bundle3);
                bundle.putParcelable(MailAccountConstants.ACTION_MAIL_SECOND_STEP, bundle3);
                return bundle;
            }
            if (commandStatus instanceof AuthCommandStatus.TWO_FACTOR_BIND_FORBIDDEN) {
                AuthCommandStatus.TWO_FACTOR_BIND_FORBIDDEN.Params params = (AuthCommandStatus.TWO_FACTOR_BIND_FORBIDDEN.Params) commandStatus.getData();
                bundle.putInt(Authenticator.KEY_SWA_CODE, params.getSwaCode());
                bundle.putString(Authenticator.KEY_BIND_SOCIAL_TYPE, params.getSocialType());
                bundle.putString("authAccount", mailAccount2.name);
                return bundle;
            }
            if (commandStatus instanceof AuthCommandStatus.ERROR_WITH_IMAP_SETTINGS) {
                String str2 = (String) commandStatus.getData();
                bundle.putInt(Authenticator.KEY_SWA_CODE, 723);
                bundle.putString("imap_settings", str2);
                bundle.putString("authAccount", mailAccount2.name);
                bundle.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, mailAccount2.type);
                return bundle;
            }
            if (commandStatus instanceof AuthCommandStatus.EXTERNAL_AUTH_PROHIBIT) {
                bundle.putString(MailAccountConstants.EXTERNAL_AUTH_PROHIBIT, mailAccount2.name);
                return bundle;
            }
            if (!(commandStatus instanceof CommandStatus.ERROR_WITH_STATUS_CODE)) {
                if (commandStatus instanceof CommandStatus.ERROR) {
                    throw new NetworkErrorException(AuthErrors.getErrorMessage(context, mailAccount2.name, (commandStatus.getData() instanceof Integer ? (Integer) commandStatus.getData() : -1).intValue()));
                }
                if (commandStatus instanceof NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED) {
                    throw new NetworkErrorException("Connection timeout");
                }
                if (commandStatus instanceof NetworkCommandStatus.VPN_BLOCKED) {
                    throw new NetworkErrorException("Vpn blocked, check white urls");
                }
                throw new IllegalArgumentException("unknown response status " + commandStatus.getClass().getSimpleName());
            }
            bundle.putInt(Authenticator.KEY_SWA_CODE, ((Integer) commandStatus.getData()).intValue());
            bundle.putString("authAccount", mailAccount2.name);
            bundle.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, mailAccount2.type);
            if (((CommandStatus.ERROR_WITH_STATUS_CODE) commandStatus).isOk0()) {
                bundle.putInt(Authenticator.EXTRA_ERROR_CODE_ADDITIONAL, 24);
                return bundle;
            }
        }
        return bundle;
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class AuthResultVisitor implements AuthorizeResult.AuthorizeResultVisitor<Bundle> {
        protected final MailAccount mAccount;
        private final String mPassword;

        public AuthResultVisitor(String str, MailAccount mailAccount) {
            this.mPassword = str;
            this.mAccount = mailAccount;
        }

        private void fillCommonData(Bundle bundle) {
            bundle.putString("authAccount", this.mAccount.name);
            bundle.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, this.mAccount.type);
            bundle.putString("password", this.mPassword);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // ru.mail.auth.request.AuthorizeResult.AuthorizeResultVisitor
        @Nullable
        public Bundle visit(AuthorizeRequestCommand.OAuthTokensResult oAuthTokensResult) {
            Bundle bundle = new Bundle();
            String refreshToken = oAuthTokensResult.getRefreshToken();
            if (TextUtils.isEmpty(refreshToken)) {
                return null;
            }
            fillCommonData(bundle);
            bundle.putString("ru.mail.oauth2.access", oAuthTokensResult.getAccessToken());
            bundle.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, refreshToken);
            bundle.putString(Statistic.TOKEN_TYPE, "oauth2");
            return bundle;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // ru.mail.auth.request.AuthorizeResult.AuthorizeResultVisitor
        @Nullable
        public Bundle visit(AuthorizeRequestCommand.MpopCookieResult mpopCookieResult) {
            Bundle bundle = new Bundle();
            String mpopCookie = mpopCookieResult.getMpopCookie();
            if (TextUtils.isEmpty(mpopCookie)) {
                return null;
            }
            fillCommonData(bundle);
            bundle.putString("authtoken", mpopCookie);
            bundle.putString(MailAccountConstants.SECURITY_TOKEN, mpopCookieResult.getSecurityTokens());
            bundle.putString(Statistic.TOKEN_TYPE, "mpop");
            return bundle;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    private static class LogAuthResultVisitor extends AuthResultVisitor {
        private final Context mContext;

        LogAuthResultVisitor(Context context, MailAccount mailAccount) {
            super("", mailAccount);
            this.mContext = context;
        }

        @Keep
        public Context getContext() {
            return this.mContext;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // ru.mail.auth.AuthStrategy.AuthResultVisitor, ru.mail.auth.request.AuthorizeResult.AuthorizeResultVisitor
        @Nullable
        public Bundle visit(AuthorizeRequestCommand.OAuthTokensResult oAuthTokensResult) {
            if (TextUtils.isEmpty(AuthStrategy.getMpopCookie(this.mContext, this.mAccount)) || TextUtils.isEmpty(oAuthTokensResult.getRefreshToken())) {
                return null;
            }
            AuthenticatorEntryPoint.analytics(this.mContext).transitionToOAuthSuccess();
            return null;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // ru.mail.auth.AuthStrategy.AuthResultVisitor, ru.mail.auth.request.AuthorizeResult.AuthorizeResultVisitor
        @Nullable
        public Bundle visit(AuthorizeRequestCommand.MpopCookieResult mpopCookieResult) {
            if (TextUtils.isEmpty(AuthStrategy.getOAuthToken(this.mContext, this.mAccount)) || TextUtils.isEmpty(mpopCookieResult.getMpopCookie())) {
                return null;
            }
            AuthenticatorEntryPoint.analytics(this.mContext).transitionToCookieSuccess();
            return null;
        }
    }
}
