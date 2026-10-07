package com.vk.id.captcha.web;

import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.vk.id.captcha.api.VKCaptcha;
import com.vk.id.captcha.api.common.MainThread;
import com.vk.id.captcha.api.data.VKCaptchaError;
import java.util.concurrent.ThreadPoolExecutor;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.app.data.openapi.File;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005Be\u0012\u0006\u0010\u0003\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020 \u0012\u0006\u0010\n\u001a\u00020\u0017\u0012\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040\"\u0012\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040\"\u0012\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00040\"\u0012\b\b\u0002\u0010/\u001a\u00020\u0014\u0012\u0006\u00100\u001a\u00020\u0002\u0012\b\u00101\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b2\u00103J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J-\u0010\u000b\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00022\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0017¢\u0006\u0004\b\u000b\u0010\fJ-\u0010\u000f\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000eH\u0017¢\u0006\u0004\b\u000f\u0010\u0010J-\u0010\u0012\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\r2\b\u0010\n\u001a\u0004\u0018\u00010\u0011H\u0017¢\u0006\u0004\b\u0012\u0010\u0013J#\u0010\u0015\u001a\u00020\u00142\b\u0010\u0003\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0017¢\u0006\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0005\u001a\u00020\u00178\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001f\u001a\u00020\u00148\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0018\u001a\u00020 8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010!R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\"8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\"8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b%\u0010$R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00040\"8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b&\u0010$R\u0014\u0010\u001d\u001a\u00020\u00028\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b'\u0010\u001bR\u0013\u0010'\u001a\u00020(X\u0083\u0080\u0002¢\u0006\u0006\n\u0004\b)\u0010*R\u0014\u0010\u001a\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010+"}, d2 = {"Lcom/vk/id/captcha/web/g;", "Landroid/webkit/WebViewClient;", "", "p0", "", "a", "(Ljava/lang/String;)V", "Landroid/webkit/WebView;", "p1", "Landroid/graphics/Bitmap;", "p2", "onPageStarted", "(Landroid/webkit/WebView;Ljava/lang/String;Landroid/graphics/Bitmap;)V", "Landroid/webkit/WebResourceRequest;", "Landroid/webkit/WebResourceError;", "onReceivedError", "(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;Landroid/webkit/WebResourceError;)V", "Landroid/webkit/WebResourceResponse;", "onReceivedHttpError", "(Landroid/webkit/WebView;Landroid/webkit/WebResourceRequest;Landroid/webkit/WebResourceResponse;)V", "", "shouldOverrideUrlLoading", "(Landroid/webkit/WebView;Ljava/lang/String;)Z", "Ljava/util/concurrent/ThreadPoolExecutor;", "d", "Ljava/util/concurrent/ThreadPoolExecutor;", "j", "Ljava/lang/String;", "b", "h", "Z", "c", "Lcom/vk/id/captcha/web/b;", "Lcom/vk/id/captcha/web/b;", "Lkotlin/Function0;", "e", "Lkotlin/jvm/functions/Function0;", File.TYPE_FILE, "g", Logger.METHOD_I, "Lcom/vk/id/captcha/web/d;", "k", "Lkotlin/Lazy;", "Landroid/webkit/WebView;", "p3", "p4", "p5", "p6", "p7", "p8", "<init>", "(Landroid/webkit/WebView;Lcom/vk/id/captcha/web/b;Ljava/util/concurrent/ThreadPoolExecutor;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;ZLjava/lang/String;Ljava/lang/String;)V"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class g extends WebViewClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    private static final a f50852a = new a(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private final WebView j;

    /* JADX INFO: renamed from: c, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private final b d;

    /* JADX INFO: renamed from: d, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private final ThreadPoolExecutor a;

    @NotNull
    private final Function0<Unit> e;

    @NotNull
    private final Function0<Unit> f;

    @NotNull
    private final Function0<Unit> g;

    /* JADX INFO: renamed from: h, reason: collision with root package name and from kotlin metadata */
    private final boolean c;

    /* JADX INFO: renamed from: i, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private final String h;

    /* JADX INFO: renamed from: j, reason: collision with root package name and from kotlin metadata */
    @Nullable
    private final String b;

    /* JADX INFO: renamed from: k, reason: collision with root package name and from kotlin metadata */
    @NotNull
    private final Lazy i;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lcom/vk/id/captcha/web/g$a;", "", "<init>", "()V"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public g(@NotNull WebView webView, @NotNull b bVar, @NotNull ThreadPoolExecutor threadPoolExecutor, @NotNull Function0<Unit> function0, @NotNull Function0<Unit> function1, @NotNull Function0<Unit> function2, boolean z10, @NotNull String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(webView, "");
        Intrinsics.checkNotNullParameter(bVar, "");
        Intrinsics.checkNotNullParameter(threadPoolExecutor, "");
        Intrinsics.checkNotNullParameter(function0, "");
        Intrinsics.checkNotNullParameter(function1, "");
        Intrinsics.checkNotNullParameter(function2, "");
        Intrinsics.checkNotNullParameter(str, "");
        this.j = webView;
        this.d = bVar;
        this.a = threadPoolExecutor;
        this.e = function0;
        this.f = function1;
        this.g = function2;
        this.c = z10;
        this.h = str;
        this.b = str2;
        this.i = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<d>() { // from class: com.vk.id.captcha.web.g.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final d invoke() {
                return new d(g.this.c, g.this.h);
            }
        });
    }

    @Override // android.webkit.WebViewClient
    @MainThread
    public final void onPageStarted(@Nullable WebView p10, @Nullable String p11, @Nullable Bitmap p12) {
        super.onPageStarted(p10, p11, p12);
        this.g.invoke();
    }

    @Override // android.webkit.WebViewClient
    @MainThread
    public final void onReceivedError(@Nullable WebView p10, @Nullable WebResourceRequest p11, @Nullable final WebResourceError p12) {
        super.onReceivedError(p10, p11, p12);
        if (p10 != null) {
            p10.loadUrl("file:///android_asset/index.html");
        }
        this.a.execute(new Runnable() { // from class: com.vk.id.captcha.web.n
            @Override // java.lang.Runnable
            public final void run() {
                g.a(this.f50870a, p12);
            }
        });
    }

    @Override // android.webkit.WebViewClient
    @MainThread
    public final void onReceivedHttpError(@Nullable WebView p10, @Nullable final WebResourceRequest p11, @Nullable final WebResourceResponse p12) {
        super.onReceivedHttpError(p10, p11, p12);
        this.a.execute(new Runnable() { // from class: com.vk.id.captcha.web.o
            @Override // java.lang.Runnable
            public final void run() {
                g.a(p11, this, p12);
            }
        });
    }

    @Override // android.webkit.WebViewClient
    @MainThread
    @Deprecated(message = "Deprecated in Java")
    public final boolean shouldOverrideUrlLoading(@Nullable WebView p10, @Nullable String p11) {
        if (((d) this.i.getValue()).a(p10, Uri.parse(p11))) {
            return true;
        }
        WebView webView = this.j;
        if (p11 == null) {
            p11 = "";
        }
        webView.loadUrl(p11);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(WebResourceRequest webResourceRequest, g gVar, WebResourceResponse webResourceResponse) {
        Intrinsics.checkNotNullParameter(gVar, "");
        if (webResourceRequest == null || webResourceRequest.isForMainFrame()) {
            StringBuilder sb2 = new StringBuilder("HttpError loading WebView. ErrorCode: ");
            sb2.append(webResourceResponse != null ? Integer.valueOf(webResourceResponse.getStatusCode()) : null);
            gVar.a(sb2.toString());
        }
    }

    private final void a(String p10) {
        VKCaptcha.INSTANCE.closeCaptcha$captcha_release(new com.vk.id.captcha.a.b(new VKCaptchaError.NetworkError(p10, null, 2, null), this.b));
        this.e.invoke();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(g gVar, WebResourceError webResourceError) {
        Intrinsics.checkNotNullParameter(gVar, "");
        Integer numValueOf = webResourceError != null ? Integer.valueOf(webResourceError.getErrorCode()) : null;
        if ((numValueOf == null || numValueOf.intValue() == -2 || numValueOf.intValue() == -6 || numValueOf.intValue() == -8) && !gVar.d.a()) {
            gVar.f.invoke();
        } else {
            gVar.a("Error loading WebView.");
        }
    }
}
