package ru.mail.auth.webview;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks;
import ru.mail.android_utils.webview.WebViewUpdateDialogCreator;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.BaseAuthFragment;
import ru.mail.locator.Locator;
import ru.mail.theme.utils.DarkThemeUtils;
import ru.mail.ui.R;
import ru.mail.ui.view.OnBackPressedCallback;
import ru.mail.util.log.Log;
import statusnavbars.StatusNavBarHelper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public abstract class BaseWebViewFragment extends BaseAuthFragment implements OnBackPressedCallback {
    private static final Log LOG = Log.getLog("BaseWebViewFragment");
    private static AuthenticatorConfig mAuthenticatorConfig = AuthenticatorConfig.getInstance();
    private final StatusNavBarHelper statusNavBarHelper = new StatusNavBarHelper();
    private AlertDialog webViewErrorDialog;

    private void applyFontForToolbarTitle(Toolbar toolbar, String str) {
        for (int i10 = 0; i10 < toolbar.getChildCount(); i10++) {
            View childAt = toolbar.getChildAt(i10);
            if (childAt instanceof TextView) {
                TextView textView = (TextView) childAt;
                if (textView.getText().equals(str)) {
                    try {
                        textView.setTypeface(ResourcesCompat.getFont(textView.getContext(), R.font.vk_sans_display_medium));
                        return;
                    } catch (Resources.NotFoundException unused) {
                        return;
                    }
                }
            }
        }
    }

    private void changeStatusAndNavBarColor(@ColorRes int i10) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        int color = activity.getColor(i10);
        this.statusNavBarHelper.setStatusBarColorWithSave(activity, color);
        this.statusNavBarHelper.setNavBarColorWithSave(activity, color);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initToolbar$0(View view) {
        if (onToolbarBack()) {
            return;
        }
        getActivity().onBackPressed();
    }

    private void restoreStatusAndNavBarColor() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        boolean zIsNightModeEnabled = DarkThemeUtils.isNightModeEnabled(activity);
        this.statusNavBarHelper.restoreStatusBarColor(activity);
        this.statusNavBarHelper.restoreNavBarColor(activity);
        this.statusNavBarHelper.changeStatusBarIconColor(activity.getWindow(), zIsNightModeEnabled);
        this.statusNavBarHelper.changeNavBarIconColor(activity.getWindow(), zIsNightModeEnabled);
    }

    @Nullable
    public abstract WebView getWebView();

    protected void initToolbar(View view, String str) {
        if (mAuthenticatorConfig.isUniversalAuthorization()) {
            Toolbar toolbar = new Toolbar(getResworbkvmocaf());
            toolbar.setId(ru.mail.Authenticator.R.id.toolbar);
            toolbar.setTitle(str);
            int i10 = ru.mail.auth.R.color.text_inverse;
            int i11 = ru.mail.uikit.R.color.icon_theme_tint;
            if (mAuthenticatorConfig.isMailRuFlavor()) {
                i10 = ru.mail.auth.R.color.colorTextPrimary;
                toolbar.setBackgroundColor(ContextCompat.getColor(getResworbkvmocaf(), ru.mail.auth.R.color.f80684bg));
                toolbar.setNavigationIcon(ru.mail.authorizationsdk.R.drawable.ic_left);
                applyFontForToolbarTitle(toolbar, str);
                i11 = i10;
            }
            toolbar.setTitleTextColor(ContextCompat.getColor(getResworbkvmocaf(), i10));
            toolbar.getNavigationIcon().setTint(ContextCompat.getColor(getResworbkvmocaf(), i11));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: ru.mail.auth.webview.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f80844a.lambda$initToolbar$0(view2);
                }
            });
            if (view != null) {
                ((ViewGroup) view).addView(toolbar, 0);
            }
        }
        if (mAuthenticatorConfig.isMailRuFlavor()) {
            changeStatusAndNavBarColor(ru.mail.auth.R.color.f80684bg);
        }
    }

    protected abstract void initWebView(View view);

    protected void initWebViewOrStartDialog(View view) {
        try {
            initWebView(view);
        } catch (RuntimeException e10) {
            LOG.e("Web view init error", e10);
            onWebViewInitFail();
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(ru.mail.Authenticator.R.layout.oauth_screen, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (mAuthenticatorConfig.isMailRuFlavor()) {
            restoreStatusAndNavBarColor();
        }
    }

    public boolean onHardwareBack() {
        return webViewGoBack();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        AlertDialog alertDialog = this.webViewErrorDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.webViewErrorDialog.dismiss();
    }

    @Override // ru.mail.ui.view.OnBackPressedCallback
    public boolean onToolbarBack() {
        return onHardwareBack();
    }

    protected void onWebViewInitFail() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AlertDialog alertDialogCreateWebViewUpdateDialog = ((WebViewUpdateDialogCreator) Locator.from(getResworbkvmocaf()).locate(WebViewUpdateDialogCreator.class)).createWebViewUpdateDialog(activity, new WebViewUpdateDialogCallbacks() { // from class: ru.mail.auth.webview.BaseWebViewFragment.1
                @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks
                public void onCancelled() {
                    FragmentActivity activity2 = BaseWebViewFragment.this.getActivity();
                    if (activity2 != null) {
                        activity2.onBackPressed();
                    }
                }

                @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks
                public void onNegativeButtonClicked() {
                    FragmentActivity activity2 = BaseWebViewFragment.this.getActivity();
                    if (activity2 != null) {
                        activity2.onBackPressed();
                    }
                }

                @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks
                public void onNeutralButtonClicked() {
                }

                @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCallbacks
                public void onPositiveButtonClicked() {
                }
            }, new WebViewUpdateDialogCreator.DialogCallback() { // from class: ru.mail.auth.webview.BaseWebViewFragment.2
                @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCreator.DialogCallback
                public void dismissDialog() {
                    BaseWebViewFragment.this.webViewErrorDialog = null;
                }
            });
            this.webViewErrorDialog = alertDialogCreateWebViewUpdateDialog;
            alertDialogCreateWebViewUpdateDialog.show();
        }
    }

    protected boolean webViewGoBack() {
        WebView webView = getWebView();
        if (webView == null || !webView.canGoBack()) {
            return false;
        }
        webView.goBack();
        return true;
    }
}
