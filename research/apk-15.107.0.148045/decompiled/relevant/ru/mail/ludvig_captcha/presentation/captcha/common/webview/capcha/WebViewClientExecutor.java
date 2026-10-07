package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.WebViewAssetLoader;
import com.google.android.gms.analytics.ecommerce.Promotion;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.ludvig_captcha.external.AnalyticEvents;
import ru.mail.ludvig_captcha.external.LudwigAnalyticsCallback;
import ru.mail.ludvig_captcha.external.LudwigSdkInitializer;
import ru.mail.util.kotlin.extension.StringKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 (2\u00020\u0001:\u0001(B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0011J\"\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J \u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J(\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u0019H\u0017J\u001e\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientExecutor;", "Landroid/webkit/WebViewClient;", "stateDelegate", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate;", "analyticsCallback", "Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;", "<init>", "(Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewClientStateDelegate;Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;)V", "webViewAssetLoader", "Landroidx/webkit/WebViewAssetLoader;", "getWebViewAssetLoader", "()Landroidx/webkit/WebViewAssetLoader;", "setWebViewAssetLoader", "(Landroidx/webkit/WebViewAssetLoader;)V", "hasError", "", "attach", "", "context", "Landroid/content/Context;", "detach", "onPageStarted", Promotion.ACTION_VIEW, "Landroid/webkit/WebView;", "url", "", "bitmap", "Landroid/graphics/Bitmap;", "onPageFinished", "onReceivedError", Event.Companion.Network.Fail.REQUEST_TAG, "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "errorCode", "", "description", "failingUrl", "shouldInterceptRequest", "Landroid/webkit/WebResourceResponse;", "Companion", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebViewClientExecutor extends WebViewClient {

    @NotNull
    private static final String LOCAL_HOST = "https://appassets.androidplatform.net/assets";
    private static final int MAX_LENGTH = 29;

    @Nullable
    private final LudwigAnalyticsCallback analyticsCallback;
    private boolean hasError;

    @NotNull
    private final WebViewClientStateDelegate stateDelegate;

    @Nullable
    private WebViewAssetLoader webViewAssetLoader;

    public WebViewClientExecutor(@NotNull WebViewClientStateDelegate stateDelegate, @Nullable LudwigAnalyticsCallback ludwigAnalyticsCallback) {
        Intrinsics.checkNotNullParameter(stateDelegate, "stateDelegate");
        this.stateDelegate = stateDelegate;
        this.analyticsCallback = ludwigAnalyticsCallback;
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
        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "WebViewClientExecutor onPageFinished : " + url);
        if (this.hasError) {
            this.stateDelegate.onPageError();
        } else {
            this.stateDelegate.onPageFinished();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(@NotNull WebView view, @NotNull String url, @Nullable Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "WebViewClientExecutor onPageStarted : " + url);
        this.hasError = false;
        this.stateDelegate.onPageStarted();
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(@NotNull WebView view, @NotNull WebResourceRequest request, @NotNull WebResourceError error) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(error, "error");
        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "onReceivedError : " + error + ", description = " + ((Object) error.getDescription()));
        this.hasError = true;
        CharSequence description = error.getDescription();
        Intrinsics.checkNotNullExpressionValue(description, "getDescription(...)");
        AnalyticEvents.WebViewError webViewError = new AnalyticEvents.WebViewError(StringKt.substringSafe(description, 0, 29));
        LudwigAnalyticsCallback ludwigAnalyticsCallback = this.analyticsCallback;
        if (ludwigAnalyticsCallback != null) {
            ludwigAnalyticsCallback.onAnalyticEvent(webViewError.getEventName(), webViewError.getParams());
        }
    }

    public final void setWebViewAssetLoader(@Nullable WebViewAssetLoader webViewAssetLoader) {
        this.webViewAssetLoader = webViewAssetLoader;
    }

    @Override // android.webkit.WebViewClient
    @Nullable
    public WebResourceResponse shouldInterceptRequest(@Nullable WebView view, @Nullable WebResourceRequest request) {
        Uri url;
        String string;
        if (request == null || (url = request.getUrl()) == null || (string = url.toString()) == null || !StringsKt.contains$default((CharSequence) string, (CharSequence) "ludochka.min.js", false, 2, (Object) null) || this.webViewAssetLoader == null) {
            return super.shouldInterceptRequest(view, request);
        }
        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "shouldInterceptRequest : " + request.getUrl());
        WebViewAssetLoader webViewAssetLoader = this.webViewAssetLoader;
        Intrinsics.checkNotNull(webViewAssetLoader);
        WebResourceResponse webResourceResponseShouldInterceptRequest = webViewAssetLoader.shouldInterceptRequest(Uri.parse(LOCAL_HOST + request.getUrl().getPath()));
        if (webResourceResponseShouldInterceptRequest != null && webResourceResponseShouldInterceptRequest.getData() != null) {
            return webResourceResponseShouldInterceptRequest;
        }
        this.stateDelegate.onLocalPageLoadError();
        return webResourceResponseShouldInterceptRequest;
    }

    @Override // android.webkit.WebViewClient
    @Deprecated(message = "Deprecated in Java")
    public void onReceivedError(@NotNull WebView view, int errorCode, @NotNull String description, @NotNull String failingUrl) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(failingUrl, "failingUrl");
        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "onReceivedError errorCode: " + errorCode);
        this.hasError = true;
        AnalyticEvents.WebViewError webViewError = new AnalyticEvents.WebViewError(StringKt.substringSafe(description, 0, 29));
        LudwigAnalyticsCallback ludwigAnalyticsCallback = this.analyticsCallback;
        if (ludwigAnalyticsCallback != null) {
            ludwigAnalyticsCallback.onAnalyticEvent(webViewError.getEventName(), webViewError.getParams());
        }
    }
}
