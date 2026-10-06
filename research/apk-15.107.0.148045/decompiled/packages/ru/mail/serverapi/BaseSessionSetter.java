package ru.mail.serverapi;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import org.apache.http.cookie.SM;
import ru.mail.auth.Authenticator;
import ru.mail.auth.MailAccountConstants;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.NoAuthInfo;
import ru.mail.network.SessionSetter;
import ru.mail.network.service.NetworkService;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public abstract class BaseSessionSetter implements SessionSetter {
    private static final Log LOG = Log.getLog("BaseSessionSetter");
    private final AccountManagerSettings mAccountManagerSettings;
    private final Context mContext;
    private final LogFilter mLogFilter = prepareTokenFilter();
    private final SessionKeeper mSessionKeeper;

    /* JADX INFO: compiled from: ProGuard */
    public interface SessionKeeper {
        String getLogin();

        NoAuthInfo getNoAuthInfo();

        NoAuthInfo getNoAuthInfo(String str, String str2);

        String peekAuthToken() throws NetworkCommandWithSession.BadSessionException;

        void pushAuthToken(String str);
    }

    public BaseSessionSetter(Context context, SessionKeeper sessionKeeper, AccountManagerSettings accountManagerSettings) {
        this.mContext = context;
        this.mAccountManagerSettings = accountManagerSettings;
        this.mSessionKeeper = sessionKeeper;
    }

    protected String filterTokenString(String str) {
        return this.mLogFilter.filter(str);
    }

    public AccountManagerSettings getAccountManagerSettings() {
        return this.mAccountManagerSettings;
    }

    protected Context getContext() {
        return this.mContext;
    }

    public SessionKeeper getSessionKeeper() {
        return this.mSessionKeeper;
    }

    protected LogFilter prepareTokenFilter() {
        return new LogFilter();
    }

    protected void setCookieOrThrow(NetworkService networkService, String str, NoAuthInfo noAuthInfo) throws NetworkCommandWithSession.BadSessionException {
        String cookieHeader = MailAccountConstants.getCookieHeader(str, Authenticator.ValidAccountTypes.getEnumByValue(this.mAccountManagerSettings.getAccountType()).getCookieDomain());
        if (TextUtils.isEmpty(str)) {
            throw new NetworkCommandWithSession.BadSessionException("session is empty", noAuthInfo);
        }
        LOG.d(" cookie header = " + filterTokenString(cookieHeader));
        networkService.setRequestProperty(SM.COOKIE, cookieHeader);
    }

    protected void setMpopCookie(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
        String login = this.mSessionKeeper.getLogin();
        String strPeekAuthToken = Authenticator.getAccountManagerWrapper(getContext().getApplicationContext()).peekAuthToken(new Account(login, this.mAccountManagerSettings.getAccountType()), "ru.mail");
        setCookieOrThrow(networkService, strPeekAuthToken, this.mSessionKeeper.getNoAuthInfo(login, strPeekAuthToken));
    }
}
