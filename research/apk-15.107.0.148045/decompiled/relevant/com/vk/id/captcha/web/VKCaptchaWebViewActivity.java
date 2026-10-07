package com.vk.id.captcha.web;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.vk.id.captcha.R;
import com.vk.id.captcha.api.VKCaptcha;
import com.vk.id.captcha.api.VKCaptchaKt;
import com.vk.id.captcha.api.data.VKCaptchaError;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadPoolExecutor;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.kotlett.spec.CustomViewSpec;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001:\u0001BB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0014¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0017¢\u0006\u0004\b\t\u0010\u0003J\u000f\u0010\n\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\n\u0010\u0003J\u000f\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\u0003J\u0017\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ!\u0010\u0014\u001a\u00020\u00062\u0010\u0010\u0013\u001a\f\u0012\u0004\u0012\u00020\u00110\u0010j\u0002`\u0012H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\fH\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0018\u0010\u0003J\u000f\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u001b\u0010\"\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\u001bR\u001d\u0010&\u001a\u0004\u0018\u00010\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b$\u0010%R\u001b\u0010(\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010 \u001a\u0004\b(\u0010\u001bR\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001b\u00100\u001a\u00020,8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010 \u001a\u0004\b.\u0010/R\u001b\u00105\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010 \u001a\u0004\b3\u00104R\u0016\u00107\u001a\u0002068\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b7\u00108R\u001b\u0010=\u001a\u0002098BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b:\u0010 \u001a\u0004\b;\u0010<R\u0016\u0010?\u001a\u00020>8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010A\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010+¨\u0006C"}, d2 = {"Lcom/vk/id/captcha/web/VKCaptchaWebViewActivity;", "Landroid/app/Activity;", "<init>", "()V", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "(Landroid/os/Bundle;)V", "onBackPressed", "onDestroy", "finishActivity", "", "url", "prepareUrl", "(Ljava/lang/String;)Ljava/lang/String;", "", "Lcom/vk/id/captcha/sensors/model/SensorData;", "Lcom/vk/id/captcha/sensors/model/SensorsData;", "data", "sendVKCaptchaListenSensorsChangedEvent", "(Ljava/util/List;)V", "setupWebView", "(Ljava/lang/String;)V", "showNoInternet", "", "wasProcessRecreated", "()Z", "Ljava/util/concurrent/ThreadPoolExecutor;", "backgroundTasksThreadPool", "Ljava/util/concurrent/ThreadPoolExecutor;", "captchaCreatedFromUserRequest$delegate", "Lkotlin/Lazy;", "getCaptchaCreatedFromUserRequest", "captchaCreatedFromUserRequest", "domain$delegate", "getDomain", "()Ljava/lang/String;", "domain", "isHitmanChallenge$delegate", "isHitmanChallenge", "Landroid/os/Handler;", "mainHandler", "Landroid/os/Handler;", "Lcom/vk/id/captcha/web/NetworkConnectionObserver;", "networkConnectionObserver$delegate", "getNetworkConnectionObserver", "()Lcom/vk/id/captcha/web/NetworkConnectionObserver;", "networkConnectionObserver", "Lcom/vk/id/captcha/web/nointernet/VKCaptchaNoInternetFragment;", "noInternetFragment$delegate", "getNoInternetFragment", "()Lcom/vk/id/captcha/web/nointernet/VKCaptchaNoInternetFragment;", "noInternetFragment", "Landroid/widget/ProgressBar;", "progressBar", "Landroid/widget/ProgressBar;", "Lcom/vk/id/captcha/web/UrlDecorator;", "urlDecorator$delegate", "getUrlDecorator", "()Lcom/vk/id/captcha/web/UrlDecorator;", "urlDecorator", "Landroid/webkit/WebView;", "webView", "Landroid/webkit/WebView;", "webViewHandler", "Companion", "captcha_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VKCaptchaWebViewActivity extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    private static final a f50808a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private WebView f50809b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ProgressBar f50810c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NotNull
    private final Lazy f50811d = LazyKt.lazy(new Function0<c>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.10
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final c invoke() {
            Configuration configuration = VKCaptchaWebViewActivity.this.getResources().getConfiguration();
            Intrinsics.checkNotNullExpressionValue(configuration, "");
            return new c(configuration);
        }
    });

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @NotNull
    private final Handler f50812e = new Handler(f.a().a().getLooper());

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @NotNull
    private final Handler f50813f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @NotNull
    private final ThreadPoolExecutor f50814g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @NotNull
    private final Lazy f50815h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @NotNull
    private final Lazy f50816i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    @NotNull
    private final Lazy f50817j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    @NotNull
    private final Lazy f50818k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    @NotNull
    private final Lazy f50819l;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lcom/vk/id/captcha/web/VKCaptchaWebViewActivity$a;", "", "<init>", "()V"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* synthetic */ class b extends FunctionReferenceImpl implements Function1<List<? extends com.vk.id.captcha.sensors.a.a>, Unit> {
        b(VKCaptchaWebViewActivity vKCaptchaWebViewActivity) {
            super(1, vKCaptchaWebViewActivity, VKCaptchaWebViewActivity.class, "sendVKCaptchaListenSensorsChangedEvent", "sendVKCaptchaListenSensorsChangedEvent(Ljava/util/List;)V", 0);
        }

        public final void a(@NotNull List<? extends com.vk.id.captcha.sensors.a.a> list) throws JSONException {
            Intrinsics.checkNotNullParameter(list, "");
            VKCaptchaWebViewActivity.a((VKCaptchaWebViewActivity) this.receiver, list);
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* synthetic */ Unit invoke(List<? extends com.vk.id.captcha.sensors.a.a> list) throws JSONException {
            a(list);
            return Unit.INSTANCE;
        }
    }

    public VKCaptchaWebViewActivity() {
        com.vk.id.captcha.b.a.Companion companion = com.vk.id.captcha.b.a.INSTANCE;
        this.f50813f = com.vk.id.captcha.b.a.Companion.a().getE();
        this.f50814g = com.vk.id.captcha.b.a.Companion.a().d();
        this.f50815h = LazyKt.lazy(new Function0<com.vk.id.captcha.web.b>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.4
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.vk.id.captcha.web.b invoke() {
                com.vk.id.captcha.b.a.Companion companion2 = com.vk.id.captcha.b.a.INSTANCE;
                return com.vk.id.captcha.b.a.Companion.a().b();
            }
        });
        this.f50816i = LazyKt.lazy(new Function0<String>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @Nullable
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final String invoke() {
                return VKCaptchaWebViewActivity.this.getIntent().getStringExtra(VKCaptchaKt.VK_CAPTCHA_CHALLENGE_DOMAIN_URL_KEY);
            }
        });
        this.f50817j = LazyKt.lazy(new Function0<Boolean>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.3
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Boolean invoke() {
                return Boolean.valueOf(VKCaptchaWebViewActivity.this.b() != null);
            }
        });
        this.f50818k = LazyKt.lazy(new Function0<Boolean>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.1
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final Boolean invoke() {
                com.vk.id.captcha.b.a.Companion companion2 = com.vk.id.captcha.b.a.INSTANCE;
                return Boolean.valueOf(com.vk.id.captcha.b.a.Companion.a().getC());
            }
        });
        this.f50819l = LazyKt.lazy(new Function0<com.vk.id.captcha.web.a.c>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.5
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final com.vk.id.captcha.web.a.c invoke() {
                return new com.vk.id.captcha.web.a.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(String str) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void f(final VKCaptchaWebViewActivity vKCaptchaWebViewActivity) {
        Intrinsics.checkNotNullParameter(vKCaptchaWebViewActivity, "");
        if (!vKCaptchaWebViewActivity.a().a()) {
            vKCaptchaWebViewActivity.c();
            return;
        }
        final String stringExtra = vKCaptchaWebViewActivity.getIntent().getStringExtra(VKCaptchaKt.VK_CAPTCHA_URL_KEY);
        Intrinsics.checkNotNull(stringExtra);
        vKCaptchaWebViewActivity.f50813f.post(new Runnable() { // from class: com.vk.id.captcha.web.l
            @Override // java.lang.Runnable
            public final void run() {
                VKCaptchaWebViewActivity.a(this.f50866a, stringExtra);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void g(VKCaptchaWebViewActivity vKCaptchaWebViewActivity) {
        Intrinsics.checkNotNullParameter(vKCaptchaWebViewActivity, "");
        ((com.vk.id.captcha.web.a.c) vKCaptchaWebViewActivity.f50819l.getValue()).show(vKCaptchaWebViewActivity.getFragmentManager(), "NoInternetFragment");
    }

    @Override // android.app.Activity
    @Deprecated(message = "Deprecated in Java")
    public final void onBackPressed() {
        WebView webView = this.f50809b;
        WebView webView2 = null;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            webView = null;
        }
        if (webView.canGoBack()) {
            WebView webView3 = this.f50809b;
            if (webView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
            } else {
                webView2 = webView3;
            }
            webView2.goBack();
            return;
        }
        WebView webView4 = this.f50809b;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
        } else {
            webView2 = webView4;
        }
        webView2.evaluateJavascript("javascript:window.dispatchEvent(new CustomEvent('VKCaptchaUserClose', null))", new ValueCallback() { // from class: com.vk.id.captcha.web.k
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                VKCaptchaWebViewActivity.a((String) obj);
            }
        });
        VKCaptcha.INSTANCE.closeCaptcha();
        d();
    }

    @Override // android.app.Activity
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && !((Boolean) this.f50818k.getValue()).booleanValue()) {
            finish();
        }
        try {
            setContentView(R.layout.vkcaptcha_activity);
        } catch (RuntimeException e10) {
            String message = e10.getMessage();
            if (message != null) {
                String lowerCase = message.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "");
                if (lowerCase != null && StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) CustomViewSpec.WebView.TYPE, false, 2, (Object) null)) {
                    VKCaptcha.INSTANCE.closeCaptcha$captcha_release(new com.vk.id.captcha.a.b(new VKCaptchaError.WebviewIsUpdatingError("Webview is being updated", e10), b()));
                    d();
                    return;
                }
            }
        }
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        View viewFindViewById = findViewById(R.id.webview);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "");
        this.f50809b = (WebView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.progress_bar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "");
        this.f50810c = (ProgressBar) viewFindViewById2;
        this.f50814g.execute(new Runnable() { // from class: com.vk.id.captcha.web.i
            @Override // java.lang.Runnable
            public final void run() {
                VKCaptchaWebViewActivity.f(this.f50864a);
            }
        });
    }

    @Override // android.app.Activity
    protected final void onDestroy() {
        WebView webView = this.f50809b;
        WebView webView2 = null;
        if (webView != null) {
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
                webView = null;
            }
            webView.removeJavascriptInterface("AndroidBridge");
        }
        com.vk.id.captcha.b.a.Companion companion = com.vk.id.captcha.b.a.INSTANCE;
        com.vk.id.captcha.b.a.Companion.a().a().a();
        WebView webView3 = this.f50809b;
        if (webView3 != null) {
            if (webView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("");
            } else {
                webView2 = webView3;
            }
            webView2.destroy();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String b() {
        return (String) this.f50816i.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void c() {
        this.f50813f.post(new Runnable() { // from class: com.vk.id.captcha.web.j
            @Override // java.lang.Runnable
            public final void run() {
                VKCaptchaWebViewActivity.g(this.f50865a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void d() {
        finish();
        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(1, R.anim.fade_in, R.anim.fade_out);
        } else {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    private final com.vk.id.captcha.web.b a() {
        return (com.vk.id.captcha.web.b) this.f50815h.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(VKCaptchaWebViewActivity vKCaptchaWebViewActivity, String str) {
        WebView webView;
        Intrinsics.checkNotNullParameter(vKCaptchaWebViewActivity, "");
        Intrinsics.checkNotNullParameter(str, "");
        WebView webView2 = vKCaptchaWebViewActivity.f50809b;
        WebView webView3 = null;
        if (webView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            webView2 = null;
        }
        webView2.getSettings().setJavaScriptEnabled(true);
        Handler handler = vKCaptchaWebViewActivity.f50812e;
        Function0<Unit> function0 = new Function0<Unit>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.6
            {
                super(0);
            }

            public final void a() {
                VKCaptchaWebViewActivity.this.d();
            }

            @Override // kotlin.jvm.functions.Function0
            public final /* synthetic */ Unit invoke() {
                a();
                return Unit.INSTANCE;
            }
        };
        b bVar = new b(vKCaptchaWebViewActivity);
        com.vk.id.captcha.b.a.Companion companion = com.vk.id.captcha.b.a.INSTANCE;
        webView2.addJavascriptInterface(new VKCaptchaJSInterface(handler, function0, bVar, com.vk.id.captcha.b.a.Companion.a().a(), vKCaptchaWebViewActivity.b()), "AndroidBridge");
        webView2.setBackgroundColor(0);
        WebView webView4 = vKCaptchaWebViewActivity.f50809b;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            webView = null;
        } else {
            webView = webView4;
        }
        webView2.setWebViewClient(new g(webView, vKCaptchaWebViewActivity.a(), vKCaptchaWebViewActivity.f50814g, new Function0<Unit>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.7
            {
                super(0);
            }

            public final void a() {
                VKCaptchaWebViewActivity.this.d();
            }

            @Override // kotlin.jvm.functions.Function0
            public final /* synthetic */ Unit invoke() {
                a();
                return Unit.INSTANCE;
            }
        }, new Function0<Unit>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.8
            {
                super(0);
            }

            public final void a() {
                VKCaptchaWebViewActivity.this.c();
            }

            @Override // kotlin.jvm.functions.Function0
            public final /* synthetic */ Unit invoke() {
                a();
                return Unit.INSTANCE;
            }
        }, new Function0<Unit>() { // from class: com.vk.id.captcha.web.VKCaptchaWebViewActivity.9
            {
                super(0);
            }

            public final void a() {
                ProgressBar progressBar = VKCaptchaWebViewActivity.this.f50810c;
                WebView webView5 = null;
                if (progressBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("");
                    progressBar = null;
                }
                progressBar.setVisibility(8);
                WebView webView6 = VKCaptchaWebViewActivity.this.f50809b;
                if (webView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("");
                } else {
                    webView5 = webView6;
                }
                webView5.setVisibility(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final /* synthetic */ Unit invoke() {
                a();
                return Unit.INSTANCE;
            }
        }, ((Boolean) vKCaptchaWebViewActivity.f50817j.getValue()).booleanValue(), str, vKCaptchaWebViewActivity.b()));
        WebView webView5 = vKCaptchaWebViewActivity.f50809b;
        if (webView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            webView5 = null;
        }
        webView5.getSettings().setCacheMode(2);
        String strA = ((c) vKCaptchaWebViewActivity.f50811d.getValue()).a(str);
        WebView webView6 = vKCaptchaWebViewActivity.f50809b;
        if (webView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
        } else {
            webView3 = webView6;
        }
        webView3.loadUrl(strA);
    }

    public static final /* synthetic */ void a(VKCaptchaWebViewActivity vKCaptchaWebViewActivity, List list) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        Intrinsics.checkNotNullParameter(list, "");
        JSONObject jSONObject2 = new JSONObject();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            com.vk.id.captcha.sensors.a.a aVar = (com.vk.id.captcha.sensors.a.a) it.next();
            jSONObject2.put(aVar.getD(), aVar.b());
        }
        jSONObject.put(ProductAction.ACTION_DETAIL, jSONObject2);
        WebView webView = vKCaptchaWebViewActivity.f50809b;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("");
            webView = null;
        }
        webView.loadUrl("javascript:window.dispatchEvent(new CustomEvent('VKCaptchaListenSensorsChanged', " + jSONObject + "))");
    }
}
