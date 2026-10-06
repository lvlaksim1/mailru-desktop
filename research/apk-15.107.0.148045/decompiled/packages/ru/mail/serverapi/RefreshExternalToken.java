package ru.mail.serverapi;

import android.accounts.Account;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.io.IOException;
import java.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.MailAccountConstants;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class RefreshExternalToken extends Command<Params, CommandStatus<?>> {
    private static final Log LOG = Log.getLog("RefreshExternalToken");
    private final AccountManagerWrapper mAccountManager;
    private final AccountManagerSettings mAccountManagerSettings;
    private final Analytics mAnalytics;
    private final Context mContext;
    private boolean mNotifyAuthFailure;
    private final PlatformInfo mPlatformInfo;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {
        private final String mAuthTokenType;
        private final String mLogin;

        public Params(String str, String str2) {
            this.mLogin = str;
            this.mAuthTokenType = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Params params = (Params) obj;
                if (Objects.equals(this.mAuthTokenType, params.mAuthTokenType) && Objects.equals(this.mLogin, params.mLogin)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.mAuthTokenType, this.mLogin);
        }

        public String toString() {
            return "Params{mLogin='" + this.mLogin + "', mAuthTokenType='" + this.mAuthTokenType + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public RefreshExternalToken(Context context, Params params, PlatformInfo platformInfo, AccountManagerSettings accountManagerSettings) {
        super(params);
        this.mContext = context;
        this.mPlatformInfo = platformInfo;
        this.mAccountManager = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
        this.mNotifyAuthFailure = false;
        this.mAccountManagerSettings = accountManagerSettings;
        this.mAnalytics = (Analytics) Locator.from(context).locate(Analytics.class);
    }

    private Account getAccount() {
        return new Account(getParams().mLogin, this.mAccountManagerSettings.getAccountType());
    }

    private String getAccountType() {
        return this.mAccountManager.getUserData(getAccount(), "type");
    }

    protected Context getContext() {
        return this.mContext;
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onExecutionComplete() {
        super.onExecutionComplete();
        String str = getParams().mAuthTokenType;
        String accountType = getAccountType();
        CommandStatus<?> result = getResult();
        this.mAnalytics.refreshToken(str, result == null ? "null" : result.getClass().getSimpleName(), accountType);
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("AUTH");
    }

    public void setNotifyAuthFailure(boolean z10) {
        this.mNotifyAuthFailure = z10;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, getAccountType());
            bundle.putString("extenid", this.mPlatformInfo.getAppsFlyerId());
            bundle.putString("authCurrent", this.mPlatformInfo.getCurrentDistributor());
            bundle.putString("authFirst", this.mPlatformInfo.getFirstDistributor());
            new Authenticator.OAuthSuppressSetterGetter(bundle).setDomainsSuppressed(this.mPlatformInfo.getExistingLoginSuppressedOauth());
            Bundle result = this.mAccountManager.getAuthToken(getAccount(), getParams().mAuthTokenType, bundle, this.mNotifyAuthFailure, null, null).getResult();
            if (result == null) {
                LOG.e("Failed to refresh token, bunlde is null for " + getParams().toString());
                this.mAnalytics.refreshTokenError("GetAuthTokenIsNull");
                return new CommandStatus.ERROR();
            }
            if (!result.containsKey("authtoken") && !result.containsKey("ru.mail.oauth2.access") && !result.containsKey(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH2_DIRECT_ACCESS) && !result.containsKey(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH)) {
                if (result.containsKey(Authenticator.KEY_SWA_CODE) && result.getInt(Authenticator.KEY_SWA_CODE) == 723) {
                    return new MailCommandStatus.SWITCH_TO_IMAP(result.getString("imap_settings"));
                }
                if ((result.containsKey("errorCode") && result.getInt("errorCode") == 22) || result.containsKey(CommonCode.Resolution.HAS_RESOLUTION_FROM_APK)) {
                    LOG.e("Failed to refresh token, invalid login for " + getParams().toString());
                    this.mAnalytics.refreshTokenError("InvalidLogin");
                    return new NetworkCommandStatus.ERROR_INVALID_LOGIN(getParams().mLogin);
                }
                LOG.e("Failed to refresh token for " + getParams().toString() + ", bunlde = " + result.toString());
                this.mAnalytics.refreshTokenError("Error");
                return new CommandStatus.ERROR();
            }
            return new CommandStatus.OK();
        } catch (AuthenticatorException e10) {
            e = e10;
            Throwable th2 = e;
            LOG.e("Authenticator exception for " + getParams().toString(), th2);
            this.mAnalytics.refreshTokenError(th2.getClass().getSimpleName());
            return new NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED();
        } catch (OperationCanceledException e11) {
            LOG.e("Authenticator cancled", e11);
            this.mAnalytics.refreshTokenError(e11.getClass().getSimpleName());
            return new NetworkCommandStatus.AUTH_CANCELLED();
        } catch (IOException e12) {
            e = e12;
            Throwable th3 = e;
            LOG.e("Authenticator exception for " + getParams().toString(), th3);
            this.mAnalytics.refreshTokenError(th3.getClass().getSimpleName());
            return new NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED();
        }
    }
}
