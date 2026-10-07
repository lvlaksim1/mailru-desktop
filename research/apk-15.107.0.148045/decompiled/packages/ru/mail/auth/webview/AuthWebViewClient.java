package ru.mail.auth.webview;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.analytics.ecommerce.Promotion;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.FilteringStrategy;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001d*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u001dB\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\"\u0010\u0012\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J.\u0010\u0012\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lru/mail/auth/webview/AuthWebViewClient;", "E", "Landroid/webkit/WebViewClient;", "vm", "Lru/mail/auth/webview/WebViewViewModel;", "<init>", "(Lru/mail/auth/webview/WebViewViewModel;)V", "logFilter", "Lru/mail/util/log/LogFilter;", "onPageStarted", "", Promotion.ACTION_VIEW, "Landroid/webkit/WebView;", "url", "", "bitmap", "Landroid/graphics/Bitmap;", "onPageFinished", "onReceivedError", Event.Companion.Network.Fail.REQUEST_TAG, "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "errorCode", "", "description", "failingUrl", "shouldOverrideUrlLoading", "", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AuthWebViewClient<E> extends WebViewClient {

    @NotNull
    public static final String CODE_LOG_PREFIX = "code";

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("AuthWebViewClient");

    @NotNull
    public static final String TOKEN_LOG_PREFIX = "token";

    @NotNull
    private final LogFilter logFilter;

    @NotNull
    private final WebViewViewModel<E> vm;

    public AuthWebViewClient(@NotNull WebViewViewModel<E> vm) {
        Intrinsics.checkNotNullParameter(vm, "vm");
        this.vm = vm;
        Formats.ParamFormat paramFormatNewUrlFormat = Formats.newUrlFormat("token");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat, "newUrlFormat(...)");
        FilteringStrategy.Constraint constraintNewParamNamedConstraint = Constraints.newParamNamedConstraint(paramFormatNewUrlFormat);
        Formats.ParamFormat paramFormatNewUrlFormat2 = Formats.newUrlFormat("code");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat2, "newUrlFormat(...)");
        this.logFilter = new LogFilter(constraintNewParamNamedConstraint, Constraints.newParamNamedConstraint(paramFormatNewUrlFormat2));
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(@NotNull WebView view, @NotNull String url) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        LOG.d("onPageFinished: url=" + this.logFilter.filter(url) + " webView=" + view);
        this.vm.onPageFinished(url);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(@NotNull WebView view, @NotNull String url, @Nullable Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        LOG.d("onPageStarted: url=" + this.logFilter.filter(url) + " webView=" + view);
        this.vm.onPageStarted(url);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(@Nullable WebView view, @NotNull WebResourceRequest request, @NotNull WebResourceError error) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(error, "error");
        Log log = LOG;
        LogFilter logFilter = this.logFilter;
        String string = request.getUrl().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        log.d("onReceivedError: url=" + logFilter.filter(string) + " error=" + ((Object) error.getDescription()) + " webView=" + view);
        this.vm.onPageError();
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(url, "url");
        LOG.d("shouldOverrideUrl: url=" + this.logFilter.filter(url) + " webView=" + view);
        return this.vm.onPageRedirect(url);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(@Nullable WebView view, int errorCode, @Nullable String description, @Nullable String failingUrl) {
        LOG.d("onReceivedError: url=" + this.logFilter.filter(String.valueOf(failingUrl)) + " errorCode=" + errorCode + " description=" + description + "webView=" + view);
        this.vm.onPageError();
    }
}
