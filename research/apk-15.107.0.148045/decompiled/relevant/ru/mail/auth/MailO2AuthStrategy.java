package ru.mail.auth;

import android.accounts.Account;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.request.OAuthAccessRefresh;
import ru.mail.auth.request.OAuthLoginBase;
import ru.mail.auth.webview.MailSecondStepFragment;
import ru.mail.credentialsexchanger.data.network.MailOAuthRequest;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class MailO2AuthStrategy extends AuthStrategy {
    public static final String EXTRA_TOKEN_TYPE = "token_type";
    private static final Log LOG = Log.getLog("MailO2AuthStrategy");
    private static final LogFilter sLogFilter = new LogFilter(Constraints.newParamNamedConstraint(Formats.newJsonFormat("ru.mail.oauth2.access")), Constraints.newParamNamedConstraint(Formats.newUrlFormat("ru.mail.oauth2.access")), Constraints.newParamNamedConstraint(Formats.newJsonFormat(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH2_DIRECT_ACCESS)), Constraints.newParamNamedConstraint(Formats.newUrlFormat(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH2_DIRECT_ACCESS)));

    public MailO2AuthStrategy() {
        super(null);
    }

    private Bundle getAccessAndRefresh(Context context, MailAccount mailAccount, Bundle bundle) throws NetworkErrorException {
        bundle.putString(EXTRA_TOKEN_TYPE, MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH);
        bundle.putBoolean(Authenticator.NEED_ACCESS_TOKEN, true);
        return authenticate(context, mailAccount, bundle);
    }

    private String getErrorStatusMessage(CommandStatus commandStatus) {
        if (commandStatus instanceof CommandStatus.OK) {
            return "no error";
        }
        if (commandStatus instanceof AuthCommandStatus.ERROR_INVALID_LOGIN) {
            return ((AuthCommandStatus.ERROR_INVALID_LOGIN) commandStatus).getData();
        }
        if (commandStatus instanceof AuthCommandStatus.ERROR_WITH_STATUS_CODE) {
            return ((AuthCommandStatus.ERROR_WITH_STATUS_CODE) commandStatus).getMessage();
        }
        if (!commandStatus.hasData()) {
            return "other errors";
        }
        return "error data " + commandStatus.getData();
    }

    private String getStatus(CommandStatus commandStatus) {
        return commandStatus != null ? commandStatus.getClass().getSimpleName() : "null";
    }

    private Bundle processResponse(Context context, MailAccount mailAccount, Bundle bundle) {
        String string = bundle.getString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH);
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
        if (!TextUtils.isEmpty(string)) {
            Account account = new Account(mailAccount.name, mailAccount.type);
            accountManagerWrapper.setAuthToken(account, "ru.mail.oauth2.access", bundle.getString("ru.mail.oauth2.access"));
            accountManagerWrapper.setAuthToken(account, MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, string);
            return bundle;
        }
        if (!bundle.containsKey(MailAccountConstants.ACTION_MAIL_SECOND_STEP) || AuthenticatorConfig.getInstance().needRemoveSwitchToMpop()) {
            return bundle;
        }
        AuthenticatorEntryPoint.analytics(context).failedTransitionForTwoFactorAccount();
        AuthenticatorConfig.getInstance().setOAuthEnabledForSession(false);
        new OAuthTransitionManager(context).disableUntilUpdate();
        Bundle bundle2 = new Bundle();
        bundle2.putString("authtoken", MailAccountConstants.INVALID_TOKEN);
        bundle2.putString("authAccount", mailAccount.name);
        bundle2.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, mailAccount.type);
        return bundle2;
    }

    @Override // ru.mail.auth.AuthStrategy
    @NotNull
    public Bundle authenticate(Context context, MailAccount mailAccount, Bundle bundle) throws NetworkErrorException {
        Log log = LOG;
        log.i("Calling authenticate for " + mailAccount.name + ", strategy = " + getClass().getSimpleName());
        String tokenType = bundle != null ? MailAccountConstants.getTokenType(bundle.getString(EXTRA_TOKEN_TYPE)) : null;
        if (TextUtils.isEmpty(tokenType)) {
            log.w("Token type is null or blank");
            throw new IllegalArgumentException("You should specify extra token type");
        }
        log.i("Auth token type = " + tokenType);
        Account account = new Account(mailAccount.name, mailAccount.type);
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
        if (tokenType.equals("ru.mail.oauth2.access") || tokenType.equals(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH2_DIRECT_ACCESS)) {
            String strPeekAuthToken = tokenType.equals("ru.mail.oauth2.access") ? accountManagerWrapper.peekAuthToken(account, MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH) : accountManagerWrapper.getPassword(account);
            if (TextUtils.isEmpty(strPeekAuthToken)) {
                log.i("Refresh token is blank, getting both tokens");
                return getAccessAndRefresh(context, mailAccount, bundle);
            }
            log.i("Refresh token is not blank, getting access token");
            return getAccessToken(context, mailAccount, bundle, tokenType, strPeekAuthToken, O2AuthApp.MAIL);
        }
        if (!tokenType.equals(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH) || TextUtils.isEmpty(accountManagerWrapper.getPassword(account))) {
            return getLoginOptions();
        }
        log.i("Obtaining refresh token by password");
        bundle.putBoolean(MailSecondStepFragment.IS_RELOGIN_AFTER_BAD_TOKEN_KEY, true);
        return getRefreshToken(context, mailAccount, bundle);
    }

    @NonNull
    protected OAuthAccessRefresh createAuthRefreshCommand(Context context, MailAccount mailAccount, Bundle bundle, String str, O2AuthApp o2AuthApp) {
        return new OAuthAccessRefresh(context, createHostProvider(context, bundle), o2AuthApp.getOauthParamsProvider().getParams(mailAccount.type, context), str);
    }

    @Override // ru.mail.auth.AuthStrategy
    protected HostProvider createHostProvider(Context context, Bundle bundle) {
        return new PreferenceHostProvider(context.getApplicationContext(), MailOAuthRequest.BODY_KEY, ru.mail.Authenticator.R.string.oauth_default_scheme, ru.mail.Authenticator.R.string.oauth_default_host);
    }

    protected Bundle getAccessToken(Context context, MailAccount mailAccount, Bundle bundle, String str, String str2, O2AuthApp o2AuthApp) throws NetworkErrorException {
        Bundle bundle2 = new Bundle();
        OAuthAccessRefresh oAuthAccessRefreshCreateAuthRefreshCommand = createAuthRefreshCommand(context, mailAccount, bundle, str2, o2AuthApp);
        CommandStatus commandStatusWrapWithProgressAndExecute = AuthorizeTask.wrapWithProgressAndExecute(oAuthAccessRefreshCreateAuthRefreshCommand, bundle);
        boolean zIsRefreshTokenUpdateAllowed = AuthenticatorConfig.getInstance().isRefreshTokenUpdateAllowed();
        AuthenticatorEntryPoint.analytics(context).refreshAccessTokenFailed(zIsRefreshTokenUpdateAllowed, getStatus(commandStatusWrapWithProgressAndExecute), oAuthAccessRefreshCreateAuthRefreshCommand.getRequestId(), getErrorStatusMessage(commandStatusWrapWithProgressAndExecute));
        Log log = LOG;
        log.i("Getting access token execution result = " + commandStatusWrapWithProgressAndExecute + ", refresh token update allowed = " + zIsRefreshTokenUpdateAllowed);
        if (!(commandStatusWrapWithProgressAndExecute instanceof CommandStatus.OK)) {
            if (!(commandStatusWrapWithProgressAndExecute instanceof AuthCommandStatus.ERROR_INVALID_LOGIN)) {
                return processFailedLoginStatus(oAuthAccessRefreshCreateAuthRefreshCommand);
            }
            if (!zIsRefreshTokenUpdateAllowed) {
                return bundle2;
            }
            Authenticator.getAccountManagerWrapper(context.getApplicationContext()).invalidateAuthToken(mailAccount.type, str2);
            return getAccessAndRefresh(context, mailAccount, bundle);
        }
        OAuthLoginBase.Result result = (OAuthLoginBase.Result) commandStatusWrapWithProgressAndExecute.getData();
        Account account = new Account(mailAccount.name, mailAccount.type);
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
        accountManagerWrapper.setAuthToken(account, str, result.getAccessToken());
        accountManagerWrapper.setUserData(account, AccountManagerWrapper.Key.MASTER_ACCESS_TOKEN_EXPIRE, String.valueOf(System.currentTimeMillis() + (result.getExpirationDate() * 1000)));
        bundle2.putString(str, result.getAccessToken());
        bundle2.putString("authAccount", mailAccount.name);
        bundle2.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, mailAccount.type);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("getTokenResult ");
        sb2.append(sLogFilter.filter("" + bundle2));
        log.d(sb2.toString());
        return bundle2;
    }

    protected Bundle getLoginOptions() {
        return null;
    }

    protected OauthParamsProvider getOauthParamsProvider(O2AuthApp o2AuthApp) {
        return o2AuthApp.getOauthParamsProvider();
    }

    protected Bundle getRefreshToken(Context context, MailAccount mailAccount, Bundle bundle) throws NetworkErrorException {
        Authenticator.Type accountType = Authenticator.getAccountType(mailAccount.name, null);
        appendExtras(context, mailAccount, bundle);
        return processResponse(context, mailAccount, accountType.getMPopStrategy().authenticate(context, mailAccount, bundle));
    }

    @Override // ru.mail.auth.AuthStrategy
    public void onRegisterRequired(Command<?, ?> command, Bundle bundle) {
        throw new UnsupportedOperationException("Don't do that");
    }

    @Override // ru.mail.auth.AuthStrategy
    public Bundle processAuthResponse(Context context, MailAccount mailAccount, String str, Command<?, ?> command) throws NetworkErrorException {
        throw new UnsupportedOperationException("Don't do that");
    }

    Bundle processFailedLoginStatus(OAuthLoginBase oAuthLoginBase) throws NetworkErrorException {
        Bundle bundle = new Bundle();
        CommandStatus<?> result = oAuthLoginBase.getResult();
        if (!(result instanceof AuthCommandStatus.ERROR_WITH_STATUS_CODE)) {
            throw new NetworkErrorException("Network error while refreshing access token");
        }
        AuthCommandStatus.ERROR_WITH_STATUS_CODE error_with_status_code = (AuthCommandStatus.ERROR_WITH_STATUS_CODE) result;
        bundle.putInt("errorCode", error_with_status_code.getData().intValue());
        bundle.putString("errorMessage", error_with_status_code.getMessage());
        Log log = LOG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("getTokenResult ");
        sb2.append(sLogFilter.filter("" + bundle));
        log.d(sb2.toString());
        return bundle;
    }

    protected void appendExtras(Context context, MailAccount mailAccount, Bundle bundle) {
    }
}
