package ru.mail.auth.webview;

import android.net.Uri;
import android.text.TextUtils;
import androidx.fragment.app.FragmentManager;
import org.apache.commons.codec.language.Soundex;
import ru.mail.auth.Authenticator;
import ru.mail.auth.EmailServiceResources;
import ru.mail.auth.Message;
import ru.mail.auth.request.GetEmailRequest;
import ru.mail.auth.request.YahooEmailRequest;
import ru.mail.authorizesdk.data.request.common.constants.MailAccountConstantsClass;
import ru.mail.deviceinfo.DeviceInfo;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class YahooOauth2AccessTokenFragment extends OAuthAccessTokenFragment {
    private static final Log LOG = Log.getLog("YahooOauth2AccessTokenFragment");
    public static final String YAHOO_HELP_JP_URL = "yahoo-help.jp";

    private void launchJapanYahooLogin(Uri uri) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null || getActivity() == null) {
            return;
        }
        fragmentManager.popBackStackImmediate();
        String queryParameter = uri.getQueryParameter("email");
        if (!TextUtils.isEmpty(queryParameter)) {
            getActivity().getIntent().putExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN, queryParameter);
        }
        getAuthCallBack().onMessageHandle(new Message(Message.Id.START_LOGIN_SCREEN, null, EmailServiceResources.MailServiceResources.YAHOO_JP));
    }

    private boolean needOpenJapanYahooLogin(Uri uri) {
        return (TextUtils.equals(uri.getQueryParameter("error"), "access_denied") && TextUtils.equals(uri.getQueryParameter("account"), "yahoo_japan")) || uri.getAuthority().contains("yahoo-help.jp");
    }

    @Override // ru.mail.auth.webview.OAuthAccessTokenFragment
    protected OAuthAccessTokenFragment.EmailHolder getEmailHolder() {
        return new OAuthAccessTokenFragment.EmailHolderWithRequest() { // from class: ru.mail.auth.webview.YahooOauth2AccessTokenFragment.1
            @Override // ru.mail.auth.webview.OAuthAccessTokenFragment.EmailHolderWithRequest
            public GetEmailRequest<?> getEmailRequestCmd(String str) {
                return new YahooEmailRequest(YahooOauth2AccessTokenFragment.this.getThemedContext(), str);
            }
        };
    }

    @Override // ru.mail.auth.BaseAuthFragment
    protected String getLoginHintFromIntent() {
        return getArguments() != null ? getArguments().getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT, "") : "";
    }

    @Override // ru.mail.auth.webview.OAuthAccessTokenFragment
    protected String getSpecificAuthUrlParams() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("&language=");
        sb2.append(DeviceInfo.getLanguage(getThemedContext()).replace('_', Soundex.SILENT_MARKER));
        if (!TextUtils.isEmpty(getLoginHintFromIntent())) {
            sb2.append("&login=");
            sb2.append(getLoginHintFromIntent());
        }
        return sb2.toString();
    }

    @Override // ru.mail.auth.webview.OAuthAccessTokenFragment
    protected boolean needHandleRedirectUrl(Uri uri) {
        if (!needOpenJapanYahooLogin(uri)) {
            return super.needHandleRedirectUrl(uri);
        }
        launchJapanYahooLogin(uri);
        return false;
    }
}
