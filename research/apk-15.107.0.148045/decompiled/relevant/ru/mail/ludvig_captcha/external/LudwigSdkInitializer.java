package ru.mail.ludvig_captcha.external;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCreator;
import ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCreatorImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\"\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\t@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/ludvig_captcha/external/LudwigSdkInitializer;", "", "<init>", "()V", "webViewUpdateDialogCreator", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/updatedialog/WebViewUpdateDialogCreator;", "getWebViewUpdateDialogCreator$ludvig_captcha_release", "()Lru/mail/ludvig_captcha/presentation/captcha/common/webview/updatedialog/WebViewUpdateDialogCreator;", "value", "Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;", "webViewCaptchaAnalytics", "getWebViewCaptchaAnalytics$ludvig_captcha_release", "()Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;", LudwigSdkInitializer.LUDWIG_SDK_TAG, "", "initialize", "", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LudwigSdkInitializer {

    @NotNull
    public static final String LUDWIG_SDK_TAG = "LUDWIG_SDK_TAG";

    @Nullable
    private static LudwigAnalyticsCallback webViewCaptchaAnalytics;

    @NotNull
    public static final LudwigSdkInitializer INSTANCE = new LudwigSdkInitializer();

    @NotNull
    private static final WebViewUpdateDialogCreator webViewUpdateDialogCreator = new WebViewUpdateDialogCreatorImpl();

    private LudwigSdkInitializer() {
    }

    public static /* synthetic */ void initialize$default(LudwigSdkInitializer ludwigSdkInitializer, LudwigAnalyticsCallback ludwigAnalyticsCallback, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            ludwigAnalyticsCallback = null;
        }
        ludwigSdkInitializer.initialize(ludwigAnalyticsCallback);
    }

    @Nullable
    public final LudwigAnalyticsCallback getWebViewCaptchaAnalytics$ludvig_captcha_release() {
        return webViewCaptchaAnalytics;
    }

    @NotNull
    public final WebViewUpdateDialogCreator getWebViewUpdateDialogCreator$ludvig_captcha_release() {
        return webViewUpdateDialogCreator;
    }

    public final void initialize(@Nullable LudwigAnalyticsCallback webViewCaptchaAnalytics2) {
        if (webViewCaptchaAnalytics != null) {
            return;
        }
        webViewCaptchaAnalytics = webViewCaptchaAnalytics2;
    }
}
