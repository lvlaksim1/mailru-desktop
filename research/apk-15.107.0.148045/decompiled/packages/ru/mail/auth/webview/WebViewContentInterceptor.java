package ru.mail.auth.webview;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import androidx.annotation.Keep;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.TornadoSendRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Keep
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \r*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\rB\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lru/mail/auth/webview/WebViewContentInterceptor;", "E", "", "vm", "Lru/mail/auth/webview/WebViewViewModel;", "<init>", "(Lru/mail/auth/webview/WebViewViewModel;)V", "getVm", "()Lru/mail/auth/webview/WebViewViewModel;", "showHTML", "", TornadoSendRequest.FIELD_BODY_HTML, "", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebViewContentInterceptor<E> {

    @NotNull
    public static final String HTML = "javascript:window.WebViewContentInterceptor.showHTML(document.getElementsByTagName('body')[0].innerHTML);";

    @NotNull
    public static final String NAME = "WebViewContentInterceptor";

    @NotNull
    private final WebViewViewModel<E> vm;

    public WebViewContentInterceptor(@NotNull WebViewViewModel<E> vm) {
        Intrinsics.checkNotNullParameter(vm, "vm");
        this.vm = vm;
    }

    @NotNull
    public final WebViewViewModel<E> getVm() {
        return this.vm;
    }

    @JavascriptInterface
    public final void showHTML(@Nullable String html) {
        if (TextUtils.isEmpty(html)) {
            this.vm.onPageError();
        } else {
            this.vm.onContentLoaded();
        }
    }
}
