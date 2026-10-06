package ru.mail.serverapi;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class BrowserCookieSetterOld implements BrowserCookieSetter {
    private static final Log LOG = Log.getLog("BrowserCookieSetterOld");

    @Override // ru.mail.serverapi.BrowserCookieSetter
    public synchronized void setUpSessionInBrowser(Context context, String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                Log log = LOG;
                log.d("Setting up session in browser started");
                AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(context);
                Account account = new Account(str, str2);
                String cookieDomainByAccountType = Authenticator.getCookieDomainByAccountType(str2);
                CookieSyncManager.createInstance(context);
                CookieManager.getInstance().removeAllCookie();
                CookieManager.getInstance().setAcceptCookie(true);
                String strPeekAuthToken = accountManagerWrapper.peekAuthToken(account, "ru.mail");
                if (AuthenticatorConfig.getInstance().isOAuthEnabled() || TextUtils.isEmpty(strPeekAuthToken)) {
                    log.d("Cookie is empty: " + TextUtils.isEmpty(strPeekAuthToken) + " || or Oauth2 enabled. No need to set cookie while setting up session in browser");
                } else {
                    log.d("Setting cookie while setting up session in browser");
                    CookieManager.getInstance().setCookie("https://" + cookieDomainByAccountType, "Mpop=" + strPeekAuthToken + "; path=/; domain=" + cookieDomainByAccountType + "; Secure");
                    CookieManager cookieManager = CookieManager.getInstance();
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("https://.");
                    sb2.append(cookieDomainByAccountType);
                    cookieManager.setCookie(sb2.toString(), "Mpop=" + strPeekAuthToken + "; path=/; domain=." + cookieDomainByAccountType + "; Secure");
                }
                CookieSyncManager.getInstance().sync();
            } catch (Exception e10) {
                LOG.e("Error while setting up session in browser", e10);
                throw e10;
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }
}
