package ru.mail.auth.webview;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.AuthUtil;
import ru.mail.auth.Authenticator;
import ru.mail.auth.EncryptedPreferencesEntryPoint;
import ru.mail.authorizesdk.external.GoodInitializeSdk;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@SuppressLint({"SetJavaScriptEnabled"})
public class MailSecondStepFragment extends BaseSecondStepAuthFragment implements MailSecondStepView {
    public static final String EXT_SECSTEP_COOKIE_HEADER = "secstep_cookie";
    public static final String EXT_SECSTEP_URL = "url";
    public static final String IS_RELOGIN_AFTER_BAD_TOKEN_KEY = "is_relogin_after_bad_token";
    private static final Log LOG = Log.getLog("MailSecondStepFragment");
    private static final String PREF = "pref";
    private static final String TSA = "tsa";
    private CriticalAuthRequests[] mCriticalAuthRequests = {CriticalAuthRequests.API, CriticalAuthRequests.CAPTCHA, CriticalAuthRequests.STATIC};

    private String appendExtraParams(Uri uri) {
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.appendQueryParameter(PreferenceHostProvider.URL_PARAM_MP, "android");
        String paramXmailFrom = getParamXmailFrom();
        if (!TextUtils.isEmpty(paramXmailFrom)) {
            builderBuildUpon.appendQueryParameter("from", paramXmailFrom);
        }
        return builderBuildUpon.build().toString();
    }

    public static String getAnyTsaCookie(Context context, Bundle bundle) {
        for (Account account : Authenticator.getAccountManagerWrapper(context.getApplicationContext()).getAppAccounts()) {
            String tsaCookie = getTsaCookie(context, account.name, bundle);
            if (tsaCookie != null && !tsaCookie.isEmpty()) {
                return tsaCookie;
            }
        }
        return "";
    }

    private String getParamXmailFrom() {
        return getArguments().getString("login_extra_xmail_migration_from");
    }

    public static String getPrefKey(String str) {
        return str + "-" + TSA;
    }

    public static String getTsaCookie(Context context, String str, Bundle bundle) {
        String tsaFromAccountManager = getTsaFromAccountManager(context, str);
        if (tsaFromAccountManager == null) {
            tsaFromAccountManager = getTsaFromPrefs(context, str, bundle);
        }
        LOG.d("getTsaCookie for " + str);
        return tsaFromAccountManager;
    }

    private static String getTsaFromAccountManager(Context context, String str) {
        return Authenticator.getAccountManagerWrapper(context.getApplicationContext()).getUserData(new Account(str, GoodInitializeSdk.INSTANCE.getBuildConfigAccountType()), Authenticator.KEY_USER_2FACTOR_TSA_COOKIE);
    }

    private static String getTsaFromPrefs(Context context, String str, Bundle bundle) {
        if (bundle == null || !bundle.getBoolean(IS_RELOGIN_AFTER_BAD_TOKEN_KEY, false)) {
            String string = EncryptedPreferencesEntryPoint.encryptedPreferences(context).getString(getPrefKey(str), null);
            LOG.d("get tsa from new pref");
            return string;
        }
        String string2 = context.getSharedPreferences(PREF, 0).getString(TSA, null);
        if (string2 != null) {
            Authenticator.getAccountManagerWrapper(context.getApplicationContext()).setUserData(new Account(str, GoodInitializeSdk.INSTANCE.getBuildConfigAccountType()), Authenticator.KEY_USER_2FACTOR_TSA_COOKIE, string2);
        }
        LOG.d("get tsa from old pref");
        return string2;
    }

    private boolean isXmailMigration() {
        return getArguments().getString("login_extra_xmail_migration_from") != null;
    }

    public static void setTsaCookie(Context context, String str, String str2) {
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
        if (AuthUtil.isAccountExistInAccountManager(context.getApplicationContext(), str2)) {
            accountManagerWrapper.setUserData(new Account(str2, GoodInitializeSdk.INSTANCE.getBuildConfigAccountType()), Authenticator.KEY_USER_2FACTOR_TSA_COOKIE, str);
            EncryptedPreferencesEntryPoint.encryptedPreferences(context).edit().putString(getPrefKey(str2), str).apply();
            LOG.d("setTsaCookie for " + str2);
        }
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected String extractTsaCookieValue() {
        String strSubstring = null;
        for (String str : CookieManager.getInstance().getCookie(getUrl()).split(MailThreadRepresentation.PAYLOAD_DELIM_CHAR)) {
            String strTrim = str.trim();
            if (strTrim.startsWith("tsa=")) {
                LOG.d(strTrim);
                strSubstring = strTrim.substring(4);
            }
        }
        return strSubstring;
    }

    protected String getSecStepCookieHeader() {
        return getArguments().getString(EXT_SECSTEP_COOKIE_HEADER);
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected String getUrl() {
        String string = getArguments().getString("url");
        return (!isXmailMigration() || string == null) ? string : appendExtraParams(Uri.parse(string));
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected boolean isCriticalUrl(Uri uri) {
        for (CriticalAuthRequests criticalAuthRequests : this.mCriticalAuthRequests) {
            if (criticalAuthRequests.matches(uri, getLpmitnenopmochtuaokvmocb())) {
                return true;
            }
        }
        return false;
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected void onRedirectSuccess(Uri uri) {
        setTsaCookie(requireActivity(), extractTsaCookieValue(), getLogin());
        super.onRedirectSuccess(uri);
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected void setupCookies() {
        CookieSyncManager cookieSyncManagerCreateInstance = CookieSyncManager.createInstance(getLpmitnenopmochtuaokvmocb());
        String url = getUrl();
        CookieManager.getInstance().setCookie(url, getSecStepCookieHeader());
        String tsaCookie = getTsaCookie(getActivity(), getLogin(), null);
        if (!TextUtils.isEmpty(tsaCookie)) {
            CookieManager.getInstance().setCookie(url, "tsa=" + tsaCookie);
        }
        cookieSyncManagerCreateInstance.sync();
    }
}
