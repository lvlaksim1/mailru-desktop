package com.vk.api.sdk.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.annotation.RequiresApi;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.api.sdk.R;
import com.vk.api.sdk.VK;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.api.sdk.auth.VKAuthManager;
import com.vk.api.sdk.auth.VKAuthParams;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.extensions.ContextExtKt;
import com.vk.api.sdk.utils.VKUtils;
import com.vk.api.sdk.utils.VKValidationLocker;
import com.vk.dto.common.id.UserId;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.offline.bundle.OfflineBundleContract;
import ru.mail.offline.bundle.utils.HashRoutingUrl;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001e\u001fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\u000bH\u0003J\u0014\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010H\u0014J\b\u0010\u0012\u001a\u00020\u000bH\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0018\u001a\u00020\u000bH\u0002J\b\u0010\u0019\u001a\u00020\u000bH\u0014J\b\u0010\u001a\u001a\u00020\u000bH\u0002J\u0010\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006 "}, d2 = {"Lcom/vk/api/sdk/ui/VKWebViewAuthActivity;", "Landroid/app/Activity;", "<init>", "()V", "webView", "Landroid/webkit/WebView;", "progress", "Landroid/widget/ProgressBar;", "params", "Lcom/vk/api/sdk/auth/VKAuthParams;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "configureWebView", "getUrlParams", "", "", "loadUrl", "needValidationResult", "", "redirectUrl", "getRedirectUrl", "()Ljava/lang/String;", "showWebView", "onDestroy", "notifyLockerAndFinish", "handleSuccess", "uri", "Landroid/net/Uri;", "OAuthWebViewClient", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVKWebViewAuthActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VKWebViewAuthActivity.kt\ncom/vk/api/sdk/ui/VKWebViewAuthActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,293:1\n1#2:294\n*E\n"})
public class VKWebViewAuthActivity extends Activity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String LOG_TAG = "VKWebViewAuthActivity";

    @NotNull
    public static final String VK_EXTRA_AUTH_PARAMS = "vk_auth_params";

    @NotNull
    private static final String VK_EXTRA_VALIDATION_URL = "vk_validation_url";

    @NotNull
    public static final String VK_RESULT_INTENT_NAME = "com.vk.auth-token";

    @Nullable
    private static VKApiValidationHandler.Credentials validationResult;
    private VKAuthParams params;
    private ProgressBar progress;
    private WebView webView;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\b\u0015J\u001e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcom/vk/api/sdk/ui/VKWebViewAuthActivity$Companion;", "", "<init>", "()V", "VK_EXTRA_AUTH_PARAMS", "", "VK_RESULT_INTENT_NAME", "LOG_TAG", "VK_EXTRA_VALIDATION_URL", "validationResult", "Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "getValidationResult", "()Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "setValidationResult", "(Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;)V", "createAuthIntent", "Landroid/content/Intent;", "ctx", "Landroid/content/Context;", "params", "Lcom/vk/api/sdk/auth/VKAuthParams;", "createAuthIntent$core_release", "startForAuth", "", "activity", "Landroid/app/Activity;", "code", "", "startForValidation", "context", "validationUrl", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Intent createAuthIntent$core_release(@NotNull Context ctx, @NotNull VKAuthParams params) {
            Intrinsics.checkNotNullParameter(ctx, "ctx");
            Intrinsics.checkNotNullParameter(params, "params");
            Intent intentPutExtra = new Intent(ctx, (Class<?>) VKWebViewAuthActivity.class).putExtra(VKWebViewAuthActivity.VK_EXTRA_AUTH_PARAMS, params.toBundle());
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            return intentPutExtra;
        }

        @Nullable
        public final VKApiValidationHandler.Credentials getValidationResult() {
            return VKWebViewAuthActivity.validationResult;
        }

        public final void setValidationResult(@Nullable VKApiValidationHandler.Credentials credentials) {
            VKWebViewAuthActivity.validationResult = credentials;
        }

        public final void startForAuth(@NotNull Activity activity, @NotNull VKAuthParams params, int code) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(params, "params");
            activity.startActivityForResult(createAuthIntent$core_release(activity, params), code);
        }

        public final void startForValidation(@NotNull Context context, @NotNull String validationUrl) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(validationUrl, "validationUrl");
            Intent intentPutExtra = new Intent(context, (Class<?>) VKWebViewAuthActivity.class).putExtra(VKWebViewAuthActivity.VK_EXTRA_VALIDATION_URL, validationUrl);
            Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
            if (ContextExtKt.toActivitySafe(context) == null) {
                intentPutExtra.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
            }
            context.startActivity(intentPutExtra);
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0017J\u001c\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J&\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001c\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\"\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0017J.\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\fH\u0016J&\u0010\u001a\u001a\u00020\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0014\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/vk/api/sdk/ui/VKWebViewAuthActivity$OAuthWebViewClient;", "Landroid/webkit/WebViewClient;", "<init>", "(Lcom/vk/api/sdk/ui/VKWebViewAuthActivity;)V", "hasError", "", "shouldOverrideUrlLoading", Promotion.ACTION_VIEW, "Landroid/webkit/WebView;", Event.Companion.Network.Fail.REQUEST_TAG, "Landroid/webkit/WebResourceRequest;", "url", "", "handleUrl", "onPageStarted", "", "favicon", "Landroid/graphics/Bitmap;", "onPageFinished", "onReceivedError", "error", "Landroid/webkit/WebResourceError;", "errorCode", "", "description", "failingUrl", "onReceivedSslError", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/webkit/SslErrorHandler;", "Landroid/net/http/SslError;", BatchApiRequest.FIELD_NAME_ON_ERROR, "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class OAuthWebViewClient extends WebViewClient {
        private boolean hasError;

        public OAuthWebViewClient() {
        }

        private final boolean handleUrl(String url) {
            int i10 = 0;
            if (url == null) {
                return false;
            }
            if (VKWebViewAuthActivity.this.needValidationResult()) {
                Uri uri = Uri.parse(StringsKt.replace$default(url, OfflineBundleContract.HASH_SYMBOL, HashRoutingUrl.END_HASH_ROUTING, false, 4, (Object) null));
                if (uri.getQueryParameter("success") != null) {
                    VKWebViewAuthActivity vKWebViewAuthActivity = VKWebViewAuthActivity.this;
                    Intrinsics.checkNotNull(uri);
                    vKWebViewAuthActivity.handleSuccess(uri);
                } else if (uri.getQueryParameter("cancel") != null) {
                    VKWebViewAuthActivity.this.notifyLockerAndFinish();
                }
                return false;
            }
            String redirectUrl = VKWebViewAuthActivity.this.getRedirectUrl();
            if (redirectUrl != null && !StringsKt.startsWith$default(url, redirectUrl, false, 2, (Object) null)) {
                return false;
            }
            Intent intent = new Intent(VKWebViewAuthActivity.VK_RESULT_INTENT_NAME);
            String strSubstring = url.substring(StringsKt.indexOf$default((CharSequence) url, OfflineBundleContract.HASH_SYMBOL, 0, false, 6, (Object) null) + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            intent.putExtra(VKAuthManager.VK_EXTRA_TOKEN_DATA, strSubstring);
            Map<String, String> mapExplodeQueryString = VKUtils.explodeQueryString(strSubstring);
            if (mapExplodeQueryString == null || (!mapExplodeQueryString.containsKey("error") && !mapExplodeQueryString.containsKey("cancel"))) {
                i10 = -1;
            }
            VKWebViewAuthActivity.this.setResult(i10, intent);
            VKWebViewAuthActivity.this.notifyLockerAndFinish();
            return true;
        }

        private final void onError(int errorCode) {
            this.hasError = true;
            Intent intent = new Intent();
            intent.putExtra(VKApiCodes.EXTRA_VW_LOGIN_ERROR, errorCode);
            VKWebViewAuthActivity.this.setResult(0, intent);
            VKWebViewAuthActivity.this.finish();
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(@Nullable WebView view, @Nullable String url) {
            super.onPageFinished(view, url);
            if (this.hasError) {
                return;
            }
            VKWebViewAuthActivity.this.showWebView();
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(@Nullable WebView view, @Nullable String url, @Nullable Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            handleUrl(url);
        }

        @Override // android.webkit.WebViewClient
        @RequiresApi(21)
        public void onReceivedError(@NotNull WebView view, @NotNull WebResourceRequest request, @Nullable WebResourceError error) {
            String string;
            int errorCode;
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(request, "request");
            super.onReceivedError(view, request, error);
            String string2 = request.getUrl().toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            if (error != null) {
                string = error.getDescription().toString();
                errorCode = error.getErrorCode();
            } else {
                string = "no_description";
                errorCode = -1;
            }
            Log.w(VKWebViewAuthActivity.LOG_TAG, errorCode + ":" + string + ":" + string2);
            WebView webView = VKWebViewAuthActivity.this.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            if (Intrinsics.areEqual(webView.getUrl(), string2)) {
                onError(errorCode);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(@Nullable WebView view, @Nullable SslErrorHandler handler, @Nullable SslError error) {
            super.onReceivedSslError(view, handler, error);
            WebView webView = null;
            String url = error != null ? error.getUrl() : null;
            if (url == null) {
                url = "";
            }
            Log.w(VKWebViewAuthActivity.LOG_TAG, "-11:ssl_exception:" + url);
            WebView webView2 = VKWebViewAuthActivity.this.webView;
            if (webView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
            } else {
                webView = webView2;
            }
            if (Intrinsics.areEqual(webView.getUrl(), url)) {
                onError(-11);
            }
        }

        @Override // android.webkit.WebViewClient
        @RequiresApi(21)
        public boolean shouldOverrideUrlLoading(@Nullable WebView view, @Nullable WebResourceRequest request) {
            return handleUrl(String.valueOf(request != null ? request.getUrl() : null));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(@Nullable WebView view, @Nullable String url) {
            return handleUrl(url);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(@Nullable WebView view, int errorCode, @Nullable String description, @Nullable String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            Log.w(VKWebViewAuthActivity.LOG_TAG, errorCode + ":" + description + ":" + failingUrl);
            WebView webView = VKWebViewAuthActivity.this.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            if (Intrinsics.areEqual(webView.getUrl(), failingUrl)) {
                onError(errorCode);
            }
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private final void configureWebView() {
        WebView webView = this.webView;
        WebView webView2 = null;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        webView.setWebViewClient(new OAuthWebViewClient());
        webView.setVerticalScrollBarEnabled(false);
        webView.setVisibility(4);
        webView.setOverScrollMode(2);
        WebView webView3 = this.webView;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView2 = webView3;
        }
        webView2.getSettings().setJavaScriptEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getRedirectUrl() {
        if (needValidationResult()) {
            return getIntent().getStringExtra(VK_EXTRA_VALIDATION_URL);
        }
        VKAuthParams vKAuthParams = this.params;
        if (vKAuthParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("params");
            vKAuthParams = null;
        }
        return vKAuthParams.getRedirectUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleSuccess(Uri uri) {
        VKApiValidationHandler.Credentials empty;
        Integer intOrNull;
        if (uri.getQueryParameter("access_token") != null) {
            String queryParameter = uri.getQueryParameter("access_token");
            String queryParameter2 = uri.getQueryParameter(AccountManagerRepositoryImpl.SECRET_ARG);
            String queryParameter3 = uri.getQueryParameter("user_id");
            UserId userId = queryParameter3 != null ? new UserId(Long.parseLong(queryParameter3)) : null;
            String queryParameter4 = uri.getQueryParameter("expires_in");
            empty = new VKApiValidationHandler.Credentials(queryParameter2, queryParameter, userId, (queryParameter4 == null || (intOrNull = StringsKt.toIntOrNull(queryParameter4)) == null) ? 0 : intOrNull.intValue(), System.currentTimeMillis());
        } else {
            empty = VKApiValidationHandler.Credentials.INSTANCE.getEMPTY();
        }
        validationResult = empty;
        notifyLockerAndFinish();
    }

    private final void loadUrl() {
        String string;
        try {
            if (needValidationResult()) {
                string = getIntent().getStringExtra(VK_EXTRA_VALIDATION_URL);
                if (string == null) {
                    throw new IllegalStateException("There is no vk_validation_url key inside");
                }
            } else {
                Uri.Builder builderBuildUpon = Uri.parse("https://api.vk.com/oauth/authorize").buildUpon();
                for (Map.Entry<String, String> entry : getUrlParams().entrySet()) {
                    builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
                }
                string = builderBuildUpon.build().toString();
                Intrinsics.checkNotNull(string);
            }
            WebView webView = this.webView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("webView");
                webView = null;
            }
            webView.loadUrl(string);
        } catch (Exception e10) {
            e10.printStackTrace();
            setResult(0);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean needValidationResult() {
        return getIntent().getStringExtra(VK_EXTRA_VALIDATION_URL) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyLockerAndFinish() {
        VKValidationLocker.INSTANCE.signal();
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showWebView() {
        ProgressBar progressBar = this.progress;
        WebView webView = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progress");
            progressBar = null;
        }
        progressBar.setVisibility(8);
        WebView webView2 = this.webView;
        if (webView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
        } else {
            webView = webView2;
        }
        webView.setVisibility(0);
    }

    @NotNull
    protected Map<String, String> getUrlParams() {
        VKAuthParams vKAuthParams = this.params;
        VKAuthParams vKAuthParams2 = null;
        if (vKAuthParams == null) {
            Intrinsics.throwUninitializedPropertyAccessException("params");
            vKAuthParams = null;
        }
        Pair pair = TuplesKt.to("client_id", String.valueOf(vKAuthParams.getAppId()));
        VKAuthParams vKAuthParams3 = this.params;
        if (vKAuthParams3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("params");
            vKAuthParams3 = null;
        }
        Pair pair2 = TuplesKt.to(CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, vKAuthParams3.getScopeString());
        VKAuthParams vKAuthParams4 = this.params;
        if (vKAuthParams4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("params");
        } else {
            vKAuthParams2 = vKAuthParams4;
        }
        return MapsKt.mapOf(pair, pair2, TuplesKt.to("redirect_uri", vKAuthParams2.getRedirectUrl()), TuplesKt.to(CommonConstant.ReqAccessTokenParam.RESPONSE_TYPE, "token"), TuplesKt.to("display", "mobile"), TuplesKt.to(Logger.METHOD_V, VK.getApiVersion()), TuplesKt.to("revoke", "1"));
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vk_webview_auth_dialog);
        View viewFindViewById = findViewById(R.id.webView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.webView = (WebView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.progress);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.progress = (ProgressBar) viewFindViewById2;
        VKAuthParams vKAuthParamsFromBundle = VKAuthParams.INSTANCE.fromBundle(getIntent().getBundleExtra(VK_EXTRA_AUTH_PARAMS));
        if (vKAuthParamsFromBundle != null) {
            this.params = vKAuthParamsFromBundle;
        } else if (!needValidationResult()) {
            finish();
        }
        configureWebView();
        loadUrl();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        WebView webView = this.webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("webView");
            webView = null;
        }
        webView.destroy();
        VKValidationLocker.INSTANCE.signal();
        super.onDestroy();
    }
}
