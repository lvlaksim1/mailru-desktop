package ru.mail.auth.webview;

import androidx.annotation.Nullable;
import ru.mail.authorizesdk.data.request.common.constants.MailAccountConstantsClass;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class OutlookOauth2AccessTokenFragment extends OAuthAccessTokenFragment {
    private static final Log LOG = Log.getLog("OutlookOauth2AccessTokenFragment");

    @Override // ru.mail.auth.webview.OAuthAccessTokenFragment
    protected OAuthAccessTokenFragment.EmailHolder getEmailHolder() {
        return new OAuthAccessTokenFragment.EmailHolder() { // from class: ru.mail.auth.webview.OutlookOauth2AccessTokenFragment.1
            @Override // ru.mail.auth.webview.OAuthAccessTokenFragment.EmailHolder
            @Nullable
            public String requestEmail(String str) {
                return OutlookOauth2AccessTokenFragment.this.getLoginHintFromIntent();
            }
        };
    }

    @Override // ru.mail.auth.BaseAuthFragment
    protected String getLoginHintFromIntent() {
        return getArguments() != null ? getArguments().getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT, "") : "";
    }

    @Override // ru.mail.auth.webview.OAuthAccessTokenFragment
    protected String getSpecificAuthUrlParams() {
        if (getArguments() == null) {
            return "";
        }
        return "&username=" + getLoginHintFromIntent();
    }
}
