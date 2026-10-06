package ru.ok.android.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.ok.android.sdk.util.OkAuthType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u00012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0006H\u0002J\"\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0006H\u0002J\u0012\u0010\u001b\u001a\u00020\u00112\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010\u001e\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u001dH\u0014J\"\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u00062\b\u0010'\u001a\u0004\u0018\u00010\u00062\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020\u0011H\u0003J\u001a\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020\u0006H\u0002J\u0010\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u0006H\u0002J\b\u00101\u001a\u00020\u000fH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082.¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lru/ok/android/sdk/OkAuthActivity;", "Landroid/app/Activity;", "()V", "authType", "Lru/ok/android/sdk/util/OkAuthType;", "mAppId", "", "mAppKey", "mRedirectUri", "mScopes", "", "[Ljava/lang/String;", "mWebView", "Landroid/webkit/WebView;", "ssoAuthorizationStarted", "", "auth", "", "buildOAuthUrl", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCancel", "error", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFail", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onSaveInstanceState", "outState", "onSuccess", CommonConstant.KEY_ACCESS_TOKEN, "sessionSecretKey", "expiresIn", "", "prepareWebView", "resolveOkAppLogin", "Landroid/content/pm/ResolveInfo;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "aPackage", "showAlert", "message", "startSsoAuthorization", "OAuthWebViewClient", "odnoklassniki-android-sdk_release"}, k = 1, mv = {1, 1, 15})
public final class OkAuthActivity extends Activity {
    private HashMap _$_findViewCache;
    private OkAuthType authType;
    private String mAppId;
    private String mAppKey;
    private String mRedirectUri;
    private String[] mScopes;
    private WebView mWebView;
    private boolean ssoAuthorizationStarted;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\fH\u0016¨\u0006\u0016"}, d2 = {"Lru/ok/android/sdk/OkAuthActivity$OAuthWebViewClient;", "Lru/ok/android/sdk/OkWebViewClient;", "context", "Landroid/content/Context;", "(Lru/ok/android/sdk/OkAuthActivity;Landroid/content/Context;)V", "onReceivedError", "", Promotion.ACTION_VIEW, "Landroid/webkit/WebView;", "errorCode", "", "description", "", "failingUrl", "onReceivedSslError", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/webkit/SslErrorHandler;", "error", "Landroid/net/http/SslError;", "shouldOverrideUrlLoading", "", "url", "odnoklassniki-android-sdk_release"}, k = 1, mv = {1, 1, 15})
    private final class OAuthWebViewClient extends OkWebViewClient {
        final /* synthetic */ OkAuthActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OAuthWebViewClient(@NotNull OkAuthActivity okAuthActivity, Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.this$0 = okAuthActivity;
        }

        @Override // ru.ok.android.sdk.OkWebViewClient, android.webkit.WebViewClient
        public void onReceivedError(@NotNull WebView view, int errorCode, @NotNull String description, @NotNull String failingUrl) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(description, "description");
            Intrinsics.checkParameterIsNotNull(failingUrl, "failingUrl");
            super.onReceivedError(view, errorCode, description, failingUrl);
            this.this$0.showAlert(getErrorMessage(errorCode));
        }

        @Override // ru.ok.android.sdk.OkWebViewClient, android.webkit.WebViewClient
        public void onReceivedSslError(@NotNull WebView view, @NotNull SslErrorHandler handler, @NotNull SslError error) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(handler, "handler");
            Intrinsics.checkParameterIsNotNull(error, "error");
            super.onReceivedSslError(view, handler, error);
            this.this$0.showAlert(getErrorMessage(error));
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // ru.ok.android.sdk.OkWebViewClient, android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
            String str;
            String str2;
            long j10;
            List listEmptyList;
            List listEmptyList2;
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(url, "url");
            String str3 = this.this$0.mRedirectUri;
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            String str4 = null;
            char c10 = 1;
            if (!StringsKt.startsWith$default(url, str3, false, 2, (Object) null)) {
                if (!StringsKt.contains$default((CharSequence) url, (CharSequence) "st.cmd=userMain", false, 2, (Object) null)) {
                    return super.shouldOverrideUrlLoading(view, url);
                }
                OkAuthActivity.access$getMWebView$p(this.this$0).loadUrl(this.this$0.buildOAuthUrl());
                return true;
            }
            Uri uri = Uri.parse(url);
            Intrinsics.checkExpressionValueIsNotNull(uri, "uri");
            String fragment = uri.getFragment();
            if (fragment != null) {
                List<String> listSplit = new Regex(ContainerUtils.FIELD_DELIMITER).split(fragment, 0);
                if (listSplit.isEmpty()) {
                    listEmptyList = CollectionsKt.emptyList();
                    break;
                }
                ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        listEmptyList = CollectionsKt.emptyList();
                        break;
                    }
                    if (listIterator.previous().length() != 0) {
                        listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
                List list = listEmptyList;
                if (list == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                }
                Object[] array = list.toArray(new String[0]);
                if (array == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                String[] strArr = (String[]) array;
                int length = strArr.length;
                int i10 = 0;
                str = null;
                str2 = null;
                long j11 = 0;
                while (i10 < length) {
                    char c11 = c10;
                    List<String> listSplit2 = new Regex("=").split(strArr[i10], 0);
                    if (listSplit2.isEmpty()) {
                        listEmptyList2 = CollectionsKt.emptyList();
                    } else {
                        ListIterator<String> listIterator2 = listSplit2.listIterator(listSplit2.size());
                        while (true) {
                            if (!listIterator2.hasPrevious()) {
                                listEmptyList2 = CollectionsKt.emptyList();
                            } else if (listIterator2.previous().length() != 0) {
                                listEmptyList2 = CollectionsKt.take(listSplit2, listIterator2.nextIndex() + 1);
                            }
                        }
                    }
                    List list2 = listEmptyList2;
                    if (list2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
                    }
                    Object[] array2 = list2.toArray(new String[0]);
                    if (array2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                    }
                    String[] strArr2 = (String[]) array2;
                    if (strArr2.length == 2) {
                        String str5 = strArr2[0];
                        String str6 = strArr2[c11];
                        switch (str5.hashCode()) {
                            case -1938933922:
                                if (str5.equals("access_token")) {
                                    str4 = str6;
                                    continue;
                                }
                                break;
                            case -1432035435:
                                if (!str5.equals("refresh_token")) {
                                    break;
                                }
                                break;
                            case -833810928:
                                if (!str5.equals("expires_in")) {
                                    continue;
                                } else if (str6.length() != 0) {
                                    j11 = Long.parseLong(str6);
                                } else {
                                    j11 = 0;
                                }
                                break;
                            case 96784904:
                                if (!str5.equals("error")) {
                                    continue;
                                } else {
                                    str2 = str6;
                                }
                                break;
                            case 438353305:
                                if (!str5.equals("session_secret_key")) {
                                    break;
                                }
                                break;
                            default:
                                continue;
                        }
                        str = str6;
                    }
                    i10++;
                    c10 = c11;
                }
                j10 = j11;
            } else {
                str = null;
                str2 = null;
                j10 = 0;
            }
            boolean z10 = c10;
            if (str4 != null) {
                this.this$0.onSuccess(str4, str, j10);
            } else {
                this.this$0.onFail(str2);
            }
            return z10;
        }
    }

    public static final /* synthetic */ WebView access$getMWebView$p(OkAuthActivity okAuthActivity) {
        WebView webView = okAuthActivity.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        return webView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void auth() {
        String str;
        String str2 = this.mAppId;
        if (str2 == null || StringsKt.isBlank(str2) || (str = this.mAppKey) == null || StringsKt.isBlank(str)) {
            onFail(getString(R.string.no_application_data));
            return;
        }
        OkAuthType okAuthType = this.authType;
        OkAuthType okAuthType2 = OkAuthType.NATIVE_SSO;
        if (okAuthType == okAuthType2 || okAuthType == OkAuthType.ANY) {
            if (startSsoAuthorization()) {
                this.ssoAuthorizationStarted = true;
                return;
            } else if (this.authType == okAuthType2) {
                onFail(getString(R.string.no_ok_application_installed));
                return;
            }
        }
        OkAuthType okAuthType3 = this.authType;
        if (okAuthType3 == OkAuthType.WEBVIEW_OAUTH || okAuthType3 == OkAuthType.ANY) {
            WebView webView = this.mWebView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            }
            webView.loadUrl(buildOAuthUrl());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildOAuthUrl() {
        String str = "https://connect.ok.ru/oauth/authorize?client_id=" + this.mAppId + "&response_type=token&redirect_uri=" + this.mRedirectUri + "&layout=m&platform=ANDROID";
        String[] strArr = this.mScopes;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mScopes");
        }
        if (strArr == null || strArr.length == 0) {
            return str;
        }
        String[] strArr2 = this.mScopes;
        if (strArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mScopes");
        }
        return str + "&scope=" + URLEncoder.encode(ArraysKt.joinToString$default(strArr2, MailThreadRepresentation.PAYLOAD_DELIM_CHAR, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onCancel(String error) {
        Intent intent = new Intent();
        intent.putExtra("error", error);
        setResult(3, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFail(String error) {
        Intent intent = new Intent();
        intent.putExtra("error", error);
        setResult(2, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onSuccess(String accessToken, String sessionSecretKey, long expiresIn) {
        if (sessionSecretKey == null) {
            Intrinsics.throwNpe();
        }
        TokenStore.store(this, accessToken, sessionSecretKey);
        Intent intent = new Intent();
        intent.putExtra("access_token", accessToken);
        intent.putExtra("session_secret_key", sessionSecretKey);
        if (expiresIn > 0) {
            intent.putExtra("expires_in", expiresIn);
        }
        setResult(-1, intent);
        finish();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private final void prepareWebView() {
        View viewFindViewById = findViewById(R.id.web_view);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.web_view)");
        WebView webView = (WebView) viewFindViewById;
        this.mWebView = webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView.setWebViewClient(new OAuthWebViewClient(this, this));
        WebView webView2 = this.mWebView;
        if (webView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        WebSettings settings = webView2.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings, "mWebView.settings");
        settings.setJavaScriptEnabled(true);
    }

    private final ResolveInfo resolveOkAppLogin(Intent intent, String aPackage) {
        intent.setClassName(aPackage, "ru.ok.android.external.LoginExternal");
        return getPackageManager().resolveActivity(intent, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAlert(final String message) {
        if (isFinishing()) {
            return;
        }
        try {
            new AlertDialog.Builder(this).setMessage(message).setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() { // from class: ru.ok.android.sdk.OkAuthActivity.showAlert.1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    OkAuthActivity.this.auth();
                }
            }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: ru.ok.android.sdk.OkAuthActivity.showAlert.2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    OkAuthActivity.this.onCancel(message);
                }
            }).show();
        } catch (RuntimeException unused) {
            onCancel(message);
        }
    }

    private final boolean startSsoAuthorization() {
        Intent intent = new Intent();
        ResolveInfo resolveInfoResolveOkAppLogin = resolveOkAppLogin(intent, "ru.ok.android");
        if (resolveInfoResolveOkAppLogin == null && Odnoklassniki.INSTANCE.of(this).getAllowDebugOkSso()) {
            resolveInfoResolveOkAppLogin = resolveOkAppLogin(intent, "ru.ok.android.debug");
        }
        if (resolveInfoResolveOkAppLogin == null) {
            return false;
        }
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(resolveInfoResolveOkAppLogin.activityInfo.packageName, 64);
            if (packageInfo != null && packageInfo.versionCode >= 120) {
                Signature[] signatureArr = packageInfo.signatures;
                Intrinsics.checkExpressionValueIsNotNull(signatureArr, "packageInfo.signatures");
                for (Signature signature : signatureArr) {
                    if (Intrinsics.areEqual(signature.toCharsString(), "3082025b308201c4a00302010202044f6760f9300d06092a864886f70d01010505003071310c300a06035504061303727573310c300a06035504081303737062310c300a0603550407130373706231163014060355040a130d4f646e6f6b6c6173736e696b6931143012060355040b130b6d6f62696c65207465616d311730150603550403130e416e647265792041736c616d6f763020170d3132303331393136333831375a180f32303636313232313136333831375a3071310c300a06035504061303727573310c300a06035504081303737062310c300a0603550407130373706231163014060355040a130d4f646e6f6b6c6173736e696b6931143012060355040b130b6d6f62696c65207465616d311730150603550403130e416e647265792041736c616d6f7630819f300d06092a864886f70d010101050003818d003081890281810080bea15bf578b898805dfd26346b2fbb662889cd6aba3f8e53b5b27c43a984eeec9a5d21f6f11667d987b77653f4a9651e20b94ff10594f76a93a6a36e6a42f4d851847cf1da8d61825ce020b7020cd1bc2eb435b0d416908be9393516ca1976ff736733c1d48ff17cd57f21ad49e05fc99384273efc5546e4e53c5e9f391c430203010001300d06092a864886f70d0101050500038181007d884df69a9748eabbdcfe55f07360433b23606d3b9d4bca03109c3ffb80fccb7809dfcbfd5a466347f1daf036fbbf1521754c2d1d999f9cbc66b884561e8201459aa414677e411e66360c3840ca4727da77f6f042f2c011464e99f34ba7df8b4bceb4fa8231f1d346f4063f7ba0e887918775879e619786728a8078c76647ed")) {
                        intent.putExtra("client_id", this.mAppId);
                        intent.putExtra(SharedKt.PARAM_CLIENT_SECRET, "6C6B6397C2BCE5EDB7290039");
                        intent.putExtra("redirect_uri", this.mRedirectUri);
                        String[] strArr = this.mScopes;
                        if (strArr == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mScopes");
                        }
                        if (!(strArr.length == 0)) {
                            String[] strArr2 = this.mScopes;
                            if (strArr2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mScopes");
                            }
                            intent.putExtra(SharedKt.PARAM_SCOPES, strArr2);
                        }
                        startActivityForResult(intent, 31337);
                        return true;
                    }
                }
            }
        } catch (ActivityNotFoundException | PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i10) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i10));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i10);
        this._$_findViewCache.put(Integer.valueOf(i10), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0049  */
    @Override // android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String stringExtra;
        if (requestCode != 31337) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        this.ssoAuthorizationStarted = false;
        if (data == null || (stringExtra = data.getStringExtra("error")) == null) {
            stringExtra = "";
        }
        if (resultCode == -1) {
            String stringExtra2 = data != null ? data.getStringExtra("access_token") : null;
            String stringExtra3 = data != null ? data.getStringExtra("session_secret_key") : null;
            String stringExtra4 = data != null ? data.getStringExtra("refresh_token") : null;
            long longExtra = data != null ? data.getLongExtra("expires_in", 0L) : 0L;
            if (stringExtra2 != null) {
                if (stringExtra3 == null) {
                    stringExtra3 = stringExtra4;
                }
                onSuccess(stringExtra2, stringExtra3, longExtra);
            } else {
                onFail(stringExtra);
            }
        } else {
            onFail(stringExtra);
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        OkAuthType okAuthType;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oksdk_webview_activity);
        View viewFindViewById = findViewById(R.id.web_view);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById<View>(R.id.web_view)");
        viewFindViewById.setVisibility(4);
        prepareWebView();
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
            savedInstanceState = intent.getExtras();
        }
        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }
        this.mAppId = savedInstanceState.getString("client_id");
        this.mAppKey = savedInstanceState.getString("application_key");
        String string = savedInstanceState.getString("redirect_uri");
        if (string == null) {
            string = "okauth://auth";
        }
        this.mRedirectUri = string;
        String[] stringArray = savedInstanceState.getStringArray(SharedKt.PARAM_SCOPES);
        if (stringArray == null) {
            stringArray = new String[0];
        }
        this.mScopes = stringArray;
        if (savedInstanceState.getSerializable(SharedKt.PARAM_AUTH_TYPE) instanceof OkAuthType) {
            Serializable serializable = savedInstanceState.getSerializable(SharedKt.PARAM_AUTH_TYPE);
            if (serializable == null) {
                throw new TypeCastException("null cannot be cast to non-null type ru.ok.android.sdk.util.OkAuthType");
            }
            okAuthType = (OkAuthType) serializable;
        } else {
            okAuthType = OkAuthType.ANY;
        }
        this.authType = okAuthType;
        boolean z10 = savedInstanceState.getBoolean("SSO_STARTED", false);
        this.ssoAuthorizationStarted = z10;
        if (z10) {
            return;
        }
        auth();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, @NotNull KeyEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (4 != keyCode) {
            return false;
        }
        String string = getString(R.string.authorization_canceled);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.authorization_canceled)");
        showAlert(string);
        return true;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        Intrinsics.checkParameterIsNotNull(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putString("client_id", this.mAppId);
        outState.putString("application_key", this.mAppKey);
        outState.putString("redirect_uri", this.mRedirectUri);
        String[] strArr = this.mScopes;
        if (strArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mScopes");
        }
        outState.putStringArray(SharedKt.PARAM_SCOPES, strArr);
        outState.putSerializable(SharedKt.PARAM_AUTH_TYPE, this.authType);
        outState.putBoolean("SSO_STARTED", this.ssoAuthorizationStarted);
    }
}
