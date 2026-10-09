package ru.mail.kotlett.runtime.divkit.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.sun.mail.imap.IMAPStore;
import com.yandex.div.core.state.DivStatePath;
import com.yandex.div.core.view2.Div2View;
import com.yandex.div.data.DivParsingEnvironment;
import com.yandex.div.json.expressions.Expression;
import com.yandex.div.json.expressions.ExpressionResolver;
import com.yandex.div2.DivCustom;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.mail.kotlett.runtime.divkit.custom.props.WebViewProps;
import ru.mail.kotlett.runtime.ui.SessionActionHandler;
import ru.mail.kotlett.runtime.ui.WebViewFactory;
import ru.mail.kotlett.spec.CustomViewSpec;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001(B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ0\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J(\u0010 \u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0017J\u0018\u0010!\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J&\u0010\"\u001a\u00020\u0016*\u00020\u00022\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001d2\b\u0010&\u001a\u0004\u0018\u00010\u0010H\u0002J\u001c\u0010'\u001a\u00020\u0016*\u00020\u00022\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001dH\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00100\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lru/mail/kotlett/runtime/divkit/custom/WebViewAdapter;", "Lru/mail/kotlett/runtime/divkit/custom/CustomViewAdapter;", "Landroid/webkit/WebView;", IMAPStore.ID_ENVIRONMENT, "Lcom/yandex/div/data/DivParsingEnvironment;", "sessionActionHandler", "Lru/mail/kotlett/runtime/ui/SessionActionHandler;", "<init>", "(Lcom/yandex/div/data/DivParsingEnvironment;Lru/mail/kotlett/runtime/ui/SessionActionHandler;)V", "factory", "Lru/mail/kotlett/runtime/ui/WebViewFactory;", "getFactory$kotlett_release", "()Lru/mail/kotlett/runtime/ui/WebViewFactory;", "setFactory$kotlett_release", "(Lru/mail/kotlett/runtime/ui/WebViewFactory;)V", "type", "", "getType", "()Ljava/lang/String;", "boundUrls", "", "bindView", "", Promotion.ACTION_VIEW, "div", "Lcom/yandex/div2/DivCustom;", "divView", "Lcom/yandex/div/core/view2/Div2View;", "expressionResolver", "Lcom/yandex/div/json/expressions/ExpressionResolver;", "path", "Lcom/yandex/div/core/state/DivStatePath;", "createView", "release", "bindUrl", "props", "Lru/mail/kotlett/runtime/divkit/custom/props/WebViewProps;", "resolver", "previousUrl", "bindMessageEventCallback", "JSIObject", "kotlett_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWebViewAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebViewAdapter.kt\nru/mail/kotlett/runtime/divkit/custom/WebViewAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,109:1\n1#2:110\n*E\n"})
public final class WebViewAdapter extends CustomViewAdapter<WebView> {

    @NotNull
    private final Map<WebView, String> boundUrls;

    @NotNull
    private final DivParsingEnvironment environment;

    @Nullable
    private WebViewFactory factory;

    @NotNull
    private final SessionActionHandler sessionActionHandler;

    @NotNull
    private final String type;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0004H\u0007R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/kotlett/runtime/divkit/custom/WebViewAdapter$JSIObject;", "", "callback", "Lkotlin/Function1;", "", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "onMessageReceived", "message", "kotlett_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class JSIObject {

        @NotNull
        private final Function1<String, Unit> callback;

        /* JADX WARN: Multi-variable type inference failed */
        public JSIObject(@NotNull Function1<? super String, Unit> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.callback = callback;
        }

        @JavascriptInterface
        public final void onMessageReceived(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.callback.invoke(message);
        }
    }

    public WebViewAdapter(@NotNull DivParsingEnvironment environment, @NotNull SessionActionHandler sessionActionHandler) {
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(sessionActionHandler, "sessionActionHandler");
        this.environment = environment;
        this.sessionActionHandler = sessionActionHandler;
        this.type = CustomViewSpec.WebView.TYPE;
        this.boundUrls = new LinkedHashMap();
    }

    @SuppressLint({"JavascriptInterface"})
    private final void bindMessageEventCallback(WebView webView, WebViewProps webViewProps, ExpressionResolver expressionResolver) {
        final Uri uriEvaluate;
        Expression<Uri> messageEventCallback = webViewProps.getMessageEventCallback();
        if (messageEventCallback == null || (uriEvaluate = messageEventCallback.evaluate(expressionResolver)) == null) {
            return;
        }
        webView.addJavascriptInterface(new JSIObject(new Function1() { // from class: ru.mail.kotlett.runtime.divkit.custom.o
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WebViewAdapter.bindMessageEventCallback$lambda$0(uriEvaluate, this, (String) obj);
            }
        }), "Kotlett");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindMessageEventCallback$lambda$0(Uri uri, WebViewAdapter webViewAdapter, String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        webViewAdapter.sessionActionHandler.handleAction(uri.buildUpon().appendQueryParameter("data", data).build());
        return Unit.INSTANCE;
    }

    private final void bindUrl(final WebView webView, WebViewProps webViewProps, ExpressionResolver expressionResolver, String str) {
        if (webViewProps.getUrl() == null) {
            return;
        }
        String strEvaluate = webViewProps.getUrl().evaluate(expressionResolver);
        this.boundUrls.put(webView, strEvaluate);
        if (Intrinsics.areEqual(strEvaluate, str)) {
            strEvaluate = null;
        }
        if (strEvaluate != null) {
            webView.loadUrl(strEvaluate);
        }
        observe(webViewProps.getUrl(), webView, expressionResolver, new Function1() { // from class: ru.mail.kotlett.runtime.divkit.custom.n
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WebViewAdapter.bindUrl$lambda$1(this.f86000a, webView, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit bindUrl$lambda$1(WebViewAdapter webViewAdapter, WebView webView, String newUrl) {
        Intrinsics.checkNotNullParameter(newUrl, "newUrl");
        if (!Intrinsics.areEqual(webViewAdapter.boundUrls.get(webView), newUrl)) {
            webViewAdapter.boundUrls.put(webView, newUrl);
            webView.loadUrl(newUrl);
        }
        return Unit.INSTANCE;
    }

    @Nullable
    /* JADX INFO: renamed from: getFactory$kotlett_release, reason: from getter */
    public final WebViewFactory getFactory() {
        return this.factory;
    }

    @Override // ru.mail.kotlett.runtime.divkit.custom.CustomViewAdapter
    @NotNull
    public String getType() {
        return this.type;
    }

    public final void setFactory$kotlett_release(@Nullable WebViewFactory webViewFactory) {
        this.factory = webViewFactory;
    }

    @Override // ru.mail.kotlett.runtime.divkit.custom.CustomViewAdapter
    public void bindView(@NotNull WebView view, @NotNull DivCustom div, @NotNull Div2View divView, @NotNull ExpressionResolver expressionResolver, @NotNull DivStatePath path) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(div, "div");
        Intrinsics.checkNotNullParameter(divView, "divView");
        Intrinsics.checkNotNullParameter(expressionResolver, "expressionResolver");
        Intrinsics.checkNotNullParameter(path, "path");
        String str = this.boundUrls.get(view);
        release(view, div);
        WebViewProps.Companion companion = WebViewProps.INSTANCE;
        DivParsingEnvironment divParsingEnvironment = this.environment;
        JSONObject jSONObject = div.customProps;
        if (jSONObject == null) {
            return;
        }
        WebViewProps webViewPropsFromJson = companion.fromJson(divParsingEnvironment, jSONObject);
        bindUrl(view, webViewPropsFromJson, expressionResolver, str);
        bindMessageEventCallback(view, webViewPropsFromJson, expressionResolver);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x003e  */
    /* JADX WARN: Code duplicated, block: B:22:0x0060  */
    @Override // ru.mail.kotlett.runtime.divkit.custom.CustomViewAdapter
    @SuppressLint({"SetJavaScriptEnabled"})
    @NotNull
    public WebView createView(@NotNull DivCustom div, @NotNull Div2View divView, @NotNull ExpressionResolver expressionResolver, @NotNull DivStatePath path) {
        WebView webView;
        WebViewFactory.WebViewClient webViewClient;
        Intrinsics.checkNotNullParameter(div, "div");
        Intrinsics.checkNotNullParameter(divView, "divView");
        Intrinsics.checkNotNullParameter(expressionResolver, "expressionResolver");
        Intrinsics.checkNotNullParameter(path, "path");
        JSONObject jSONObject = div.customProps;
        WebViewProps webViewPropsFromJson = jSONObject != null ? WebViewProps.INSTANCE.fromJson(this.environment, jSONObject) : null;
        WebViewFactory webViewFactory = this.factory;
        if (webViewFactory != null) {
            Context context = divView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            webView = webViewFactory.createWebView(context, webViewPropsFromJson != null ? webViewPropsFromJson.getFactoryParams() : null);
            if (webView == null) {
                webView = new WebView(divView.getContext());
                webView.getSettings().setJavaScriptEnabled(true);
            }
        } else {
            webView = new WebView(divView.getContext());
            webView.getSettings().setJavaScriptEnabled(true);
        }
        WebViewFactory webViewFactory2 = this.factory;
        if (webViewFactory2 == null) {
            webViewClient = new WebViewFactory.WebViewClient();
        } else {
            webViewClient = webViewFactory2.createWebViewClient(webViewPropsFromJson != null ? webViewPropsFromJson.getFactoryParams() : null);
            if (webViewClient == null) {
                webViewClient = new WebViewFactory.WebViewClient();
            }
        }
        webView.setWebViewClient(webViewClient);
        return webView;
    }

    @Override // ru.mail.kotlett.runtime.divkit.custom.CustomViewAdapter
    public void release(@NotNull WebView view, @NotNull DivCustom div) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(div, "div");
        super.release(view, div);
        view.removeJavascriptInterface("Kotlett");
        this.boundUrls.remove(view);
    }
}
