package ru.mail.authorizationsdk.feature.captcha.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.webkit.WebViewAssetLoader;
import com.google.android.gms.analytics.ecommerce.Promotion;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.util.kotlin.extension.StringKt;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0013J\"\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J \u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J(\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u001bH\u0017J\u001e\u0010(\u001a\u0004\u0018\u00010)2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010 \u001a\u0004\u0018\u00010!H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientExecutor;", "Landroid/webkit/WebViewClient;", "logger", "Lru/mail/util/log/Logger;", "stateDelegate", "Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate;", "analytics", "Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;", "<init>", "(Lru/mail/util/log/Logger;Lru/mail/authorizationsdk/feature/captcha/webview/WebViewClientStateDelegate;Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;)V", "webViewAssetLoader", "Landroidx/webkit/WebViewAssetLoader;", "getWebViewAssetLoader", "()Landroidx/webkit/WebViewAssetLoader;", "setWebViewAssetLoader", "(Landroidx/webkit/WebViewAssetLoader;)V", "hasError", "", "attach", "", "context", "Landroid/content/Context;", "detach", "onPageStarted", Promotion.ACTION_VIEW, "Landroid/webkit/WebView;", "url", "", "bitmap", "Landroid/graphics/Bitmap;", "onPageFinished", "onReceivedError", Event.Companion.Network.Fail.REQUEST_TAG, "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "errorCode", "", "description", "failingUrl", "shouldInterceptRequest", "Landroid/webkit/WebResourceResponse;", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebViewClientExecutor extends WebViewClient {

    @NotNull
    private static final String LOCAL_HOST = "https://appassets.androidplatform.net/assets";
    private static final int MAX_LENGTH = 29;

    @NotNull
    private final LudwigCaptchaAnalyticEvents analytics;
    private boolean hasError;

    @NotNull
    private final Logger logger;

    @NotNull
    private final WebViewClientStateDelegate stateDelegate;

    @Nullable
    private WebViewAssetLoader webViewAssetLoader;
    public static final int $stable = 8;

    public WebViewClientExecutor(@NotNull Logger logger, @NotNull WebViewClientStateDelegate stateDelegate, @NotNull LudwigCaptchaAnalyticEvents analytics) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(stateDelegate, "stateDelegate");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        this.logger = logger;
        this.stateDelegate = stateDelegate;
        this.analytics = analytics;
    }

    public final void attach(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.webViewAssetLoader = new WebViewAssetLoader.Builder().addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(context)).build();
    }

    public final void detach() {
        this.webViewAssetLoader = null;
    }

    @Nullable
    public final WebViewAssetLoader getWebViewAssetLoader() {
        return this.webViewAssetLoader;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(@NotNull WebView view, @NotNull String url) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        if (this.hasError) {
            return;
        }
        Logger.d$default(this.logger, "WebViewClientExecutor onPageFinished : " + url, null, 2, null);
        this.stateDelegate.onPageFinished();
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(@NotNull WebView view, @NotNull String url, @Nullable Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        this.hasError = false;
        Logger.d$default(this.logger, "WebViewClientExecutor onPageStarted : " + url, null, 2, null);
        this.stateDelegate.onPageStarted();
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(@NotNull WebView view, @NotNull WebResourceRequest request, @NotNull WebResourceError error) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(error, "error");
        this.hasError = true;
        Logger.d$default(this.logger, "onReceivedError : " + error + ", description = " + ((Object) error.getDescription()), null, 2, null);
        this.stateDelegate.onPageError();
        LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents = this.analytics;
        CharSequence description = error.getDescription();
        Intrinsics.checkNotNullExpressionValue(description, "getDescription(...)");
        ludwigCaptchaAnalyticEvents.onWebViewError(StringKt.substringSafe(description, 0, 29));
    }

    public final void setWebViewAssetLoader(@Nullable WebViewAssetLoader webViewAssetLoader) {
        this.webViewAssetLoader = webViewAssetLoader;
    }

    @Override // android.webkit.WebViewClient
    @Nullable
    public WebResourceResponse shouldInterceptRequest(@Nullable WebView view, @Nullable WebResourceRequest request) {
        Uri url;
        String string;
        WebResourceResponse webResourceResponseShouldInterceptRequest;
        if (request != null && (url = request.getUrl()) != null && (string = url.toString()) != null) {
            if (StringsKt.contains$default((CharSequence) string, (CharSequence) "ludochka.min.js", false, 2, (Object) null) && this.webViewAssetLoader != null) {
                Logger.d$default(this.logger, "shouldInterceptRequest : " + request.getUrl(), null, 2, null);
                WebViewAssetLoader webViewAssetLoader = this.webViewAssetLoader;
                if (webViewAssetLoader != null) {
                    webResourceResponseShouldInterceptRequest = webViewAssetLoader.shouldInterceptRequest(Uri.parse(LOCAL_HOST + request.getUrl().getPath()));
                } else {
                    webResourceResponseShouldInterceptRequest = null;
                }
                if ((webResourceResponseShouldInterceptRequest != null ? webResourceResponseShouldInterceptRequest.getData() : null) == null) {
                    this.stateDelegate.onLocalPageLoadError();
                }
                return webResourceResponseShouldInterceptRequest;
            }
        }
        return super.shouldInterceptRequest(view, request);
    }

    @Override // android.webkit.WebViewClient
    @Deprecated(message = "Deprecated in Java")
    public void onReceivedError(@NotNull WebView view, int errorCode, @NotNull String description, @NotNull String failingUrl) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
        this.hasError = true;
        Logger.d$default(this.logger, "onReceivedError errorCode: " + errorCode, null, 2, null);
        this.stateDelegate.onPageError();
        this.analytics.onWebViewError(StringKt.substringSafe(description, 0, 29));
    }
}
