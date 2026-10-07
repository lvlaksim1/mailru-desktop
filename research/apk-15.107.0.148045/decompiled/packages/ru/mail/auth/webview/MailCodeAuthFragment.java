package ru.mail.auth.webview;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.HashMap;
import javax.inject.Inject;
import ru.mail.Authenticator.R;
import ru.mail.auth.Analytics;
import ru.mail.auth.Authenticator;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@AndroidEntryPoint
public class MailCodeAuthFragment extends Hilt_MailCodeAuthFragment {
    private static final String ACT_COOKIE = "act";
    public static final String EXTRA_FROM = "extra_from";
    public static final String EXTRA_URL = "extra_url";
    private static final Log LOG = Log.getLog("MailCodeAuthFragment");

    @Inject
    Analytics analytics;

    @Nullable
    private String getCookie(String str, String str2) {
        String cookie = CookieManager.getInstance().getCookie(str);
        if (cookie != null && !cookie.isEmpty()) {
            for (String str3 : cookie.split(MailThreadRepresentation.PAYLOAD_DELIM_CHAR)) {
                if (str3.trim().startsWith(str2 + "=")) {
                    String[] strArrSplit = str3.split("=");
                    if (strArrSplit.length == 2) {
                        return strArrSplit[1];
                    }
                }
            }
        }
        return null;
    }

    private String getSource() {
        return getArguments().getString(EXTRA_FROM);
    }

    private void setTitle(@StringRes int i10) {
        ActionBar supportActionBar;
        FragmentActivity activity = getActivity();
        if (!(activity instanceof AppCompatActivity) || (supportActionBar = ((AppCompatActivity) activity).getSupportActionBar()) == null) {
            return;
        }
        supportActionBar.setTitle(getString(i10));
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected HashMap<String, String> composeCookies() {
        HashMap<String, String> mapComposeCookies = super.composeCookies();
        String cookie = getCookie(getUrl(), ACT_COOKIE);
        if (cookie != null) {
            mapComposeCookies.put(ACT_COOKIE, cookie);
            return mapComposeCookies;
        }
        LOG.w("No act cookie found");
        return mapComposeCookies;
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected String getUrl() {
        return Uri.parse(getArguments().getString("extra_url")).buildUpon().appendQueryParameter("email", getLogin()).build().toString();
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected void onClose() {
        super.onClose();
        this.analytics.oneTimeCodeWebViewClose(getSource());
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment, ru.mail.auth.webview.BaseWebViewFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setTitle(R.string.code_auth_webview_title);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment, ru.mail.auth.webview.BaseWebViewFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        setTitle(R.string.add_your_email);
        super.onDestroyView();
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected void onError(int i10) {
        super.onError(i10);
        this.analytics.oneTimeCodeError(getSource());
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment, ru.mail.auth.webview.BaseWebViewFragment, ru.mail.ui.view.OnBackPressedCallback
    public boolean onHardwareBack() {
        return webViewGoBack();
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected void onInternalError(int i10) {
        super.onInternalError(i10);
        this.analytics.oneTimeCodeFail(getSource());
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected void onRedirectSuccess(Uri uri) {
        super.onRedirectSuccess(uri);
        this.analytics.oneTimeCodeSuccess(getSource());
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected void onRedirectSuccessOptions(Bundle bundle, Uri uri) {
        super.onRedirectSuccessOptions(bundle, uri);
        bundle.putBoolean(Authenticator.IS_ENTERED_BY_ONE_TIME_CODE, true);
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected void onSwitchToPassword() {
        super.onSwitchToPassword();
        this.analytics.oneTimeCodeSwitchToPass(getSource());
    }

    @Override // ru.mail.auth.webview.BaseWebViewFragment, ru.mail.ui.view.OnBackPressedCallback
    public boolean onToolbarBack() {
        return false;
    }

    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment
    protected void setupCookies() {
    }
}
