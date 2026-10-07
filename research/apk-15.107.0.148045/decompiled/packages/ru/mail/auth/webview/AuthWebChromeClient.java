package ru.mail.auth.webview;

import android.os.Message;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.FilteringStrategy;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\u0004\b\n\u0010\u000bJ,\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lru/mail/auth/webview/AuthWebChromeClient;", "Landroid/webkit/WebChromeClient;", "currentWindow", "Landroid/webkit/WebView;", "webViewContainer", "Landroid/view/ViewGroup;", "layoutParams", "Landroid/view/ViewGroup$LayoutParams;", "createWebView", "Lkotlin/Function0;", "<init>", "(Landroid/webkit/WebView;Landroid/view/ViewGroup;Landroid/view/ViewGroup$LayoutParams;Lkotlin/jvm/functions/Function0;)V", "logFilter", "Lru/mail/util/log/LogFilter;", "onCreateWindow", "", Promotion.ACTION_VIEW, "isDialog", "isUserGesture", "resultMsg", "Landroid/os/Message;", "onCloseWindow", "", "window", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AuthWebChromeClient extends WebChromeClient {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("AuthWebChromeClient");

    @NotNull
    private final Function0<WebView> createWebView;

    @NotNull
    private final WebView currentWindow;

    @NotNull
    private final ViewGroup.LayoutParams layoutParams;

    @NotNull
    private final LogFilter logFilter;

    @NotNull
    private final ViewGroup webViewContainer;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0006\u001a\u00020\u0007*\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lru/mail/auth/webview/AuthWebChromeClient$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "clear", "", "Landroid/webkit/WebView;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void clear(@NotNull WebView webView) {
            Intrinsics.checkNotNullParameter(webView, "<this>");
            webView.clearHistory();
            webView.onPause();
            webView.removeAllViews();
            webView.destroy();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AuthWebChromeClient(@NotNull WebView currentWindow, @NotNull ViewGroup webViewContainer, @NotNull ViewGroup.LayoutParams layoutParams, @NotNull Function0<? extends WebView> createWebView) {
        Intrinsics.checkNotNullParameter(currentWindow, "currentWindow");
        Intrinsics.checkNotNullParameter(webViewContainer, "webViewContainer");
        Intrinsics.checkNotNullParameter(layoutParams, "layoutParams");
        Intrinsics.checkNotNullParameter(createWebView, "createWebView");
        this.currentWindow = currentWindow;
        this.webViewContainer = webViewContainer;
        this.layoutParams = layoutParams;
        this.createWebView = createWebView;
        Formats.ParamFormat paramFormatNewUrlFormat = Formats.newUrlFormat("token");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat, "newUrlFormat(...)");
        FilteringStrategy.Constraint constraintNewParamNamedConstraint = Constraints.newParamNamedConstraint(paramFormatNewUrlFormat);
        Formats.ParamFormat paramFormatNewUrlFormat2 = Formats.newUrlFormat("code");
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat2, "newUrlFormat(...)");
        this.logFilter = new LogFilter(constraintNewParamNamedConstraint, Constraints.newParamNamedConstraint(paramFormatNewUrlFormat2));
    }

    @Override // android.webkit.WebChromeClient
    public void onCloseWindow(@Nullable WebView window) {
        Log log = LOG;
        log.d("onCloseWindow: window=" + window + " url=" + this.logFilter.filter(String.valueOf(window != null ? window.getUrl() : null)));
        this.webViewContainer.removeView(this.currentWindow);
        INSTANCE.clear(this.currentWindow);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onCreateWindow(@Nullable WebView view, boolean isDialog, boolean isUserGesture, @Nullable Message resultMsg) {
        if (resultMsg == null) {
            return false;
        }
        WebView webViewInvoke = this.createWebView.invoke();
        Log log = LOG;
        log.d("onCreateWindow: opener=" + view + " newWebView=" + webViewInvoke + " url=" + this.logFilter.filter(String.valueOf(view != null ? view.getUrl() : null)));
        this.webViewContainer.addView(webViewInvoke, this.layoutParams);
        webViewInvoke.setWebChromeClient(new AuthWebChromeClient(webViewInvoke, this.webViewContainer, this.layoutParams, this.createWebView));
        Object obj = resultMsg.obj;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.webkit.WebView.WebViewTransport");
        ((WebView.WebViewTransport) obj).setWebView(webViewInvoke);
        resultMsg.sendToTarget();
        return true;
    }
}
