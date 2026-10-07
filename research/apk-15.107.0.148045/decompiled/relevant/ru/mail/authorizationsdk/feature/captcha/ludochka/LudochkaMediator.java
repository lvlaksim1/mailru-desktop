package ru.mail.authorizationsdk.feature.captcha.ludochka;

import android.util.Log;
import android.webkit.JavascriptInterface;
import androidx.annotation.Keep;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0007J\b\u0010\n\u001a\u00020\tH\u0007J\b\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\fH\u0007J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\tH\u0007J\b\u0010\u0010\u001a\u00020\fH\u0007J\b\u0010\u0011\u001a\u00020\fH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaMediator;", "", "config", "Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaConfig;", "eventListener", "Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaEventListener;", "<init>", "(Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaConfig;Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaEventListener;)V", "getLudwigUrl", "", "getLudwigToken", "onLudwigClosed", "", "onLudwigCanceled", "onLudwigError", "error", "onResize", "onLudwigSuccess", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LudochkaMediator {

    @NotNull
    public static final String JS_CLASS_NAME = "LudochkaMediator";

    @NotNull
    private final LudochkaConfig config;

    @NotNull
    private final LudochkaEventListener eventListener;
    public static final int $stable = 8;

    public LudochkaMediator(@NotNull LudochkaConfig config, @NotNull LudochkaEventListener eventListener) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.config = config;
        this.eventListener = eventListener;
    }

    @JavascriptInterface
    @Keep
    @NotNull
    public final String getLudwigToken() {
        return this.config.getLudwigToken();
    }

    @JavascriptInterface
    @Keep
    @NotNull
    public final String getLudwigUrl() {
        return this.config.getLudwigUrl();
    }

    @JavascriptInterface
    @Keep
    public final void onLudwigCanceled() {
        this.eventListener.onEvent(LudochkaEvent.Cancel.INSTANCE);
    }

    @JavascriptInterface
    @Keep
    public final void onLudwigClosed() {
        this.eventListener.onEvent(LudochkaEvent.Close.INSTANCE);
    }

    @JavascriptInterface
    @Keep
    public final void onLudwigError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        this.eventListener.onEvent(new LudochkaEvent.Error(error));
    }

    @JavascriptInterface
    @Keep
    public final void onLudwigSuccess() {
        try {
            this.eventListener.onEvent(new LudochkaEvent.Success(this.config.getLudwigToken()));
        } catch (Throwable th2) {
            Log.e("LUDVIG_SDK_TAG", "onLudwigSuccess", th2);
        }
    }

    @JavascriptInterface
    @Keep
    public final void onResize() {
        this.eventListener.onEvent(LudochkaEvent.Resize.INSTANCE);
    }
}
