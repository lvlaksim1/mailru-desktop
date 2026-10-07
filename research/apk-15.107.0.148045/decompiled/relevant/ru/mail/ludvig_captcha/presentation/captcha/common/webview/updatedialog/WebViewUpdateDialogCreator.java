package ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog;

import android.content.Context;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/updatedialog/WebViewUpdateDialogCreator;", "", "showWebViewUpdateDialog", "", "context", "Landroid/content/Context;", "dialogCallbacks", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/updatedialog/WebViewUpdateDialogCallbacks;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface WebViewUpdateDialogCreator {
    void showWebViewUpdateDialog(@Nullable Context context);

    void showWebViewUpdateDialog(@Nullable Context context, @Nullable WebViewUpdateDialogCallbacks dialogCallbacks);
}
