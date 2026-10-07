package ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ludvig_captcha.external.LudwigSdkInitializer;
import ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCallbacks;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\t\u001a\u00020\u0005H\u0002J\b\u0010\n\u001a\u00020\u0005H&¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/WebViewInitFailHandler;", "", "getCurrentActivity", "Landroid/app/Activity;", "initWebViewOrStartDialog", "", "rootView", "Landroid/view/View;", "initWebView", "onWebViewInitFail", "justCloseScreen", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface WebViewInitFailHandler {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void initWebViewOrStartDialog(@NotNull WebViewInitFailHandler webViewInitFailHandler, @Nullable View view) {
            WebViewInitFailHandler.super.initWebViewOrStartDialog(view);
        }
    }

    private default void onWebViewInitFail() {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            return;
        }
        LudwigSdkInitializer.INSTANCE.getWebViewUpdateDialogCreator$ludvig_captcha_release().showWebViewUpdateDialog(currentActivity, new WebViewUpdateDialogCallbacks() { // from class: ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebViewInitFailHandler.onWebViewInitFail.1
            @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCallbacks
            public void onCancelled() {
                WebViewInitFailHandler.this.justCloseScreen();
            }

            @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCallbacks
            public void onNegativeButtonClicked() {
                WebViewInitFailHandler.this.justCloseScreen();
            }

            @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCallbacks
            public void onNeutralButtonClicked() {
            }

            @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCallbacks
            public void onPositiveButtonClicked() {
            }
        });
    }

    @Nullable
    Activity getCurrentActivity();

    void initWebView(@Nullable View rootView);

    default void initWebViewOrStartDialog(@Nullable View rootView) {
        try {
            initWebView(rootView);
        } catch (RuntimeException e10) {
            Log.e(LudwigSdkInitializer.LUDWIG_SDK_TAG, "Web view init error", e10);
            onWebViewInitFail();
        }
    }

    void justCloseScreen();
}
