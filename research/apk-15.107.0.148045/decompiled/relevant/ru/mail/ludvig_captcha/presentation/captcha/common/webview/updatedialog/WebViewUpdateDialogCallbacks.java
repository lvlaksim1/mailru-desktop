package ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog;

import kotlin.Metadata;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/updatedialog/WebViewUpdateDialogCallbacks;", "", "onPositiveButtonClicked", "", "onNeutralButtonClicked", "onNegativeButtonClicked", "onCancelled", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface WebViewUpdateDialogCallbacks {
    void onCancelled();

    void onNegativeButtonClicked();

    void onNeutralButtonClicked();

    void onPositiveButtonClicked();
}
