package com.vk.superapp.browser.internal.bridges.js;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.vk.api.external.exceptions.VKWebAuthException;
import com.vk.api.sdk.VKApiConfig;
import com.vk.api.sdk.auth.VKAuthParams;
import com.vk.auth.api.models.AuthResult;
import com.vk.pushme.analytcis.AnalyticsErrorType;
import com.vk.superapp.api.contract.SuperappApi;
import com.vk.superapp.api.dto.auth.VkAuthCredentials;
import com.vk.superapp.api.states.VkGetOauthTokenArgs;
import com.vk.superapp.bridges.SuperappAuthBridge;
import com.vk.superapp.bridges.SuperappBridgesKt;
import com.vk.superapp.bridges.dto.AuthData;
import com.vk.superapp.bridges.js.JsVkConnectBridge;
import com.vk.superapp.browser.internal.bridges.BaseWebBridge;
import com.vk.superapp.browser.internal.bridges.JsApiMethodType;
import com.vk.superapp.browser.internal.bridges.MethodScope;
import com.vk.superapp.browser.internal.bridges.WebAppBridge;
import com.vk.superapp.browser.internal.utils.VKWebViewClient;
import com.vk.superapp.browser.internal.utils.WebClients;
import com.vk.superapp.core.api.models.WebAuthAnswer;
import com.vk.superapp.core.errors.VkAppsErrors;
import com.vk.superapp.core.utils.WebLogger;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 /2\u00020\u00012\u00020\u0002:\u0001/B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0014¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fH\u0014¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0017¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0017¢\u0006\u0004\b\u0018\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0017¢\u0006\u0004\b\u0019\u0010\u0017J\u0017\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0017¢\u0006\u0004\b\u001a\u0010\u0017R$\u0010\"\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010#\u001a\u00020\u001b8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b#\u0010\u001d\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!R.\u0010.\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010&8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u00060"}, d2 = {"Lcom/vk/superapp/browser/internal/bridges/js/JsAndroidBridge;", "Lcom/vk/superapp/browser/internal/bridges/BaseWebBridge;", "Lcom/vk/superapp/bridges/js/JsVkConnectBridge;", "Lcom/vk/superapp/browser/internal/bridges/MethodScope;", "allowedMethodsScope", "<init>", "(Lcom/vk/superapp/browser/internal/bridges/MethodScope;)V", "Lcom/vk/superapp/bridges/dto/AuthData;", "getAuth", "()Lcom/vk/superapp/bridges/dto/AuthData;", "Lcom/vk/superapp/api/dto/auth/VkAuthCredentials;", "getAuthCredentials", "()Lcom/vk/superapp/api/dto/auth/VkAuthCredentials;", "Lcom/vk/auth/api/models/AuthResult;", "authResult", "", "keepAlive", "", "onAuth", "(Lcom/vk/auth/api/models/AuthResult;Z)V", "", "data", "VKWebAppGetAuthToken", "(Ljava/lang/String;)V", "VKWebAppGetSilentToken", "VKWebAppOAuthActivate", "VKWebAppOAuthDeactivate", "Landroid/content/Context;", "resworbkvmoci", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "context", "appContext", "getAppContext", "setAppContext", "Lcom/vk/superapp/browser/internal/utils/WebClients$Holder;", "webView", "resworbkvmocj", "Lcom/vk/superapp/browser/internal/utils/WebClients$Holder;", "getWebViewHolder", "()Lcom/vk/superapp/browser/internal/utils/WebClients$Holder;", "setWebViewHolder", "(Lcom/vk/superapp/browser/internal/utils/WebClients$Holder;)V", "webViewHolder", "Companion", "browser_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJsAndroidBridge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsAndroidBridge.kt\ncom/vk/superapp/browser/internal/bridges/js/JsAndroidBridge\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,167:1\n1#2:168\n*E\n"})
public class JsAndroidBridge extends BaseWebBridge implements JsVkConnectBridge {

    @NotNull
    public static final String CLOSE_RESULT_ERROR = "error";

    @NotNull
    public static final String CLOSE_RESULT_SUCCESS = "success";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    protected Context appContext;

    /* JADX INFO: renamed from: resworbkvmoci, reason: from kotlin metadata */
    @Nullable
    private Context context;

    /* JADX INFO: renamed from: resworbkvmocj, reason: from kotlin metadata */
    @Nullable
    private WebClients.Holder webViewHolder;

    @NotNull
    private final Consumer<Throwable> resworbkvmock;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/vk/superapp/browser/internal/bridges/js/JsAndroidBridge$Companion;", "", "<init>", "()V", AnalyticsErrorType.UNKNOWN_ERROR, "", "DEFAULT_REDIRECT_URL", "getDEFAULT_REDIRECT_URL", "()Ljava/lang/String;", "CLOSE_RESULT_SUCCESS", "CLOSE_RESULT_ERROR", "browser_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getDEFAULT_REDIRECT_URL() {
            return "https://" + VKApiConfig.INSTANCE.getDEFAULT_OAUTH_WEB_DOMAIN() + "/blank.html";
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsAndroidBridge(@NotNull MethodScope allowedMethodsScope) {
        super(allowedMethodsScope);
        Intrinsics.checkNotNullParameter(allowedMethodsScope, "allowedMethodsScope");
        this.resworbkvmock = new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.c
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws JSONException {
                JsAndroidBridge.resworbkvmoca(this.f52080a, (Throwable) obj);
            }
        };
    }

    private final Consumer<WebAuthAnswer> resworbkvmoca(final String str) {
        return new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.a
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws JSONException {
                JsAndroidBridge.resworbkvmoca(str, this, (WebAuthAnswer) obj);
            }
        };
    }

    @Override // com.vk.superapp.bridges.js.JsVkConnectBridge
    @android.webkit.JavascriptInterface
    @SuppressLint({"CheckResult"})
    public void VKWebAppGetAuthToken(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (onJsApiCalled(JsApiMethodType.GET_AUTH_TOKEN, data)) {
            JSONObject jSONObject = new JSONObject(data);
            final long jOptLong = jSONObject.optLong("app_id", 0L);
            final String strOptString = jSONObject.optString(CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "");
            final String strOptString2 = jSONObject.optString("redirect_url", INSTANCE.getDEFAULT_REDIRECT_URL());
            runUiThread$browser_release(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.b
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return JsAndroidBridge.resworbkvmoca(this.f52070a, strOptString, strOptString2, jOptLong);
                }
            });
        }
    }

    @Override // com.vk.superapp.bridges.js.JsVkConnectBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppGetSilentToken(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        WebLogger.INSTANCE.e("Not available for internal apps");
        WebAppBridge.DefaultImpls.sendEventFailed$default(this, JsApiMethodType.GET_SILENT_TOKEN, VkAppsErrors.Client.UNSUPPORTED_PLATFORM, null, null, null, null, 60, null);
    }

    @Override // com.vk.superapp.bridges.js.JsVkConnectBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppOAuthActivate(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        WebLogger.INSTANCE.e("Not available for internal apps");
        WebAppBridge.DefaultImpls.sendEventFailed$default(this, JsApiMethodType.GET_SILENT_TOKEN, VkAppsErrors.Client.UNSUPPORTED_PLATFORM, null, null, null, null, 60, null);
    }

    @Override // com.vk.superapp.bridges.js.JsVkConnectBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppOAuthDeactivate(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        WebLogger.INSTANCE.e("Not available for internal apps");
        WebAppBridge.DefaultImpls.sendEventFailed$default(this, JsApiMethodType.GET_SILENT_TOKEN, VkAppsErrors.Client.UNSUPPORTED_PLATFORM, null, null, null, null, 60, null);
    }

    @NotNull
    protected final Context getAppContext() {
        Context context = this.appContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appContext");
        return null;
    }

    @NotNull
    public AuthData getAuth() {
        return SuperappAuthBridge.DefaultImpls.getAuth$default(SuperappBridgesKt.getSuperappAuth(), null, 1, null);
    }

    @Nullable
    protected VkAuthCredentials getAuthCredentials() {
        return null;
    }

    @Nullable
    public final Context getContext() {
        return this.context;
    }

    @Override // com.vk.superapp.browser.internal.bridges.BaseWebBridge
    @Nullable
    public WebClients.Holder getWebViewHolder() {
        return this.webViewHolder;
    }

    protected void onAuth(@NotNull AuthResult authResult, boolean keepAlive) {
        Intrinsics.checkNotNullParameter(authResult, "authResult");
    }

    protected final void setAppContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.appContext = context;
    }

    public final void setContext(@Nullable Context context) {
        this.context = context;
    }

    @Override // com.vk.superapp.browser.internal.bridges.BaseWebBridge
    public void setWebViewHolder(@Nullable WebClients.Holder holder) {
        WebView webView;
        this.webViewHolder = holder;
        Context context = (holder == null || (webView = holder.getWebView()) == null) ? null : webView.getContext();
        this.context = context;
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            setAppContext(applicationContext);
        }
        WebViewClient client = holder != null ? holder.getClient() : null;
        if (client instanceof VKWebViewClient) {
            setBodyHolder(((VKWebViewClient) client).proxyDelegate().getDataHolder());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(String str, JsAndroidBridge jsAndroidBridge, WebAuthAnswer webAuthAnswer) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("access_token", webAuthAnswer.getAccessToken());
        jSONObject.put(CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, str);
        WebAppBridge.DefaultImpls.sendEventSuccess$default(jsAndroidBridge, JsApiMethodType.GET_AUTH_TOKEN, jSONObject, null, null, 12, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(JsAndroidBridge jsAndroidBridge, Throwable th2) throws JSONException {
        Throwable cause = th2.getCause();
        if (cause instanceof VKWebAuthException) {
            WebLogger webLogger = WebLogger.INSTANCE;
            StringBuilder sb2 = new StringBuilder("auth error: ");
            VKWebAuthException vKWebAuthException = (VKWebAuthException) cause;
            sb2.append(vKWebAuthException.getError());
            sb2.append(TokenParser.SP);
            sb2.append(vKWebAuthException.getErrorDescription());
            sb2.append(TokenParser.SP);
            sb2.append(vKWebAuthException.getCom.huawei.hms.support.hianalytics.HiAnalyticsConstant.HaKey.BI_KEY_ERRORREASON java.lang.String());
            sb2.append(TokenParser.SP);
            sb2.append(vKWebAuthException.getLastResponseCode());
            webLogger.e(sb2.toString());
            vKWebAuthException.getErrorDescription();
            jsAndroidBridge.sendEventFailed(JsApiMethodType.GET_AUTH_TOKEN, VkAppsErrors.createForAuth$default(VkAppsErrors.INSTANCE, vKWebAuthException.getErrorDescription(), vKWebAuthException.getError(), vKWebAuthException.getCom.huawei.hms.support.hianalytics.HiAnalyticsConstant.HaKey.BI_KEY_ERRORREASON java.lang.String(), null, 8, null));
            return;
        }
        WebLogger.INSTANCE.e("auth error: " + th2);
        jsAndroidBridge.sendEventFailed(JsApiMethodType.GET_AUTH_TOKEN, VkAppsErrors.createForAuth$default(VkAppsErrors.INSTANCE, "unknown_error", "", "", null, 8, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(JsAndroidBridge jsAndroidBridge, String str, String str2, long j10) {
        AuthData auth = jsAndroidBridge.getAuth();
        VkGetOauthTokenArgs.Companion companion = VkGetOauthTokenArgs.INSTANCE;
        String accessToken = auth.getAccessToken();
        if (accessToken == null) {
            accessToken = "";
        }
        String secret = auth.getSecret();
        WebView webView = jsAndroidBridge.webView();
        String url = webView != null ? webView.getUrl() : null;
        Intrinsics.checkNotNull(str);
        Intrinsics.checkNotNull(str2);
        SuperappApi.Account.DefaultImpls.sendGetAccessToken$default(SuperappBridgesKt.getSuperappApi().getAccount(), j10, companion.createForVkUi(accessToken, secret, j10, str, (1984 & 16) != 0 ? VKAuthParams.INSTANCE.getDEFAULT_REDIRECT_URL() : str2, url, (1984 & 64) != 0 ? "android" : null, (1984 & 128) != 0 ? "token" : null, (1984 & 256) != 0 ? null : null, (1984 & 512) != 0 ? false : false, (1984 & 1024) != 0 ? false : false), null, 4, null).subscribe(jsAndroidBridge.resworbkvmoca(str), jsAndroidBridge.resworbkvmock);
        return Unit.INSTANCE;
    }
}
