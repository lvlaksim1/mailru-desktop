package ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.example.ludvig_captcha.R;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import ru.mail.ludvig_captcha.external.AnalyticEvents;
import ru.mail.ludvig_captcha.external.LudwigAnalyticsCallback;
import ru.mail.ludvig_captcha.external.LudwigSdkInitializer;
import ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCreatorImpl;
import ru.mail.ludvig_captcha.utils.IntentUtils;
import ru.mail.remotelayout.data.dto.ItemDto;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/ludvig_captcha/presentation/captcha/common/webview/updatedialog/WebViewUpdateDialogCreatorImpl;", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/updatedialog/WebViewUpdateDialogCreator;", "<init>", "()V", ItemDto.KEY_TYPE_DIALOG, "Landroidx/appcompat/app/AlertDialog;", "showWebViewUpdateDialog", "", "context", "Landroid/content/Context;", "dialogCallbacks", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/updatedialog/WebViewUpdateDialogCallbacks;", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebViewUpdateDialogCreatorImpl implements WebViewUpdateDialogCreator {

    @Nullable
    private AlertDialog dialog;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWebViewUpdateDialog$lambda$0$0(LudwigAnalyticsCallback ludwigAnalyticsCallback, WebViewUpdateDialogCallbacks webViewUpdateDialogCallbacks, Context context, DialogInterface dialogInterface, int i10) {
        if (ludwigAnalyticsCallback != null) {
            LudwigAnalyticsCallback.onAnalyticEvent$default(ludwigAnalyticsCallback, AnalyticEvents.UpdateWebViewDialogPositiveButtonClicked.INSTANCE.getEventName(), null, 2, null);
        }
        if (webViewUpdateDialogCallbacks != null) {
            webViewUpdateDialogCallbacks.onPositiveButtonClicked();
        }
        IntentUtils.openGooglePlay$default(context, "com.google.android.webview", true, false, null, 24, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWebViewUpdateDialog$lambda$0$1(LudwigAnalyticsCallback ludwigAnalyticsCallback, WebViewUpdateDialogCallbacks webViewUpdateDialogCallbacks, DialogInterface dialogInterface, int i10) {
        if (ludwigAnalyticsCallback != null) {
            LudwigAnalyticsCallback.onAnalyticEvent$default(ludwigAnalyticsCallback, AnalyticEvents.UpdateWebViewDialogNegativeButtonClicked.INSTANCE.getEventName(), null, 2, null);
        }
        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "onUpdateWebViewDialogNegativeButtonClicked");
        dialogInterface.dismiss();
        if (webViewUpdateDialogCallbacks != null) {
            webViewUpdateDialogCallbacks.onNegativeButtonClicked();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWebViewUpdateDialog$lambda$0$2(LudwigAnalyticsCallback ludwigAnalyticsCallback, WebViewUpdateDialogCallbacks webViewUpdateDialogCallbacks, WebViewUpdateDialogCreatorImpl webViewUpdateDialogCreatorImpl, DialogInterface dialogInterface) {
        if (ludwigAnalyticsCallback != null) {
            LudwigAnalyticsCallback.onAnalyticEvent$default(ludwigAnalyticsCallback, AnalyticEvents.UpdateWebViewDialogCancelled.INSTANCE.getEventName(), null, 2, null);
        }
        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "onUpdateWebViewDialogCancelled");
        if (webViewUpdateDialogCallbacks != null) {
            webViewUpdateDialogCallbacks.onCancelled();
        }
        webViewUpdateDialogCreatorImpl.dialog = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showWebViewUpdateDialog$lambda$0$4(LudwigAnalyticsCallback ludwigAnalyticsCallback, DialogInterface dialogInterface) {
        if (ludwigAnalyticsCallback != null) {
            LudwigAnalyticsCallback.onAnalyticEvent$default(ludwigAnalyticsCallback, AnalyticEvents.UpdateWebViewDialogShowed.INSTANCE.getEventName(), null, 2, null);
        }
        Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "onUpdateWebViewDialogShowed");
    }

    @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCreator
    public void showWebViewUpdateDialog(@Nullable Context context) {
        showWebViewUpdateDialog(context, null);
    }

    @Override // ru.mail.ludvig_captcha.presentation.captcha.common.webview.updatedialog.WebViewUpdateDialogCreator
    public void showWebViewUpdateDialog(@Nullable final Context context, @Nullable final WebViewUpdateDialogCallbacks dialogCallbacks) {
        final LudwigAnalyticsCallback webViewCaptchaAnalytics$ludvig_captcha_release = LudwigSdkInitializer.INSTANCE.getWebViewCaptchaAnalytics$ludvig_captcha_release();
        if (webViewCaptchaAnalytics$ludvig_captcha_release != null) {
            LudwigAnalyticsCallback.onAnalyticEvent$default(webViewCaptchaAnalytics$ludvig_captcha_release, AnalyticEvents.WebViewCreatingError.INSTANCE.getEventName(), null, 2, null);
        }
        if (!(context instanceof Activity)) {
            if (context != null) {
                Toast.makeText(context, R.string.f17533e, 0).show();
                if (webViewCaptchaAnalytics$ludvig_captcha_release != null) {
                    LudwigAnalyticsCallback.onAnalyticEvent$default(webViewCaptchaAnalytics$ludvig_captcha_release, AnalyticEvents.WebViewInflateFailedToastShowed.INSTANCE.getEventName(), null, 2, null);
                }
                Log.d(LudwigSdkInitializer.LUDWIG_SDK_TAG, "onWebViewInflateFailedToastShowed");
                return;
            }
            return;
        }
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context).create();
        Activity activity = (Activity) context;
        alertDialogCreate.setTitle(activity.getString(R.string.f17532d));
        alertDialogCreate.setMessage(activity.getString(R.string.f17530b));
        alertDialogCreate.setCancelable(true);
        alertDialogCreate.setButton(-1, activity.getString(R.string.f17531c), new DialogInterface.OnClickListener() { // from class: pd.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                WebViewUpdateDialogCreatorImpl.showWebViewUpdateDialog$lambda$0$0(webViewCaptchaAnalytics$ludvig_captcha_release, dialogCallbacks, context, dialogInterface, i10);
            }
        });
        alertDialogCreate.setButton(-2, activity.getString(R.string.f17529a), new DialogInterface.OnClickListener() { // from class: pd.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                WebViewUpdateDialogCreatorImpl.showWebViewUpdateDialog$lambda$0$1(webViewCaptchaAnalytics$ludvig_captcha_release, dialogCallbacks, dialogInterface, i10);
            }
        });
        alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: pd.c
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                WebViewUpdateDialogCreatorImpl.showWebViewUpdateDialog$lambda$0$2(webViewCaptchaAnalytics$ludvig_captcha_release, dialogCallbacks, this, dialogInterface);
            }
        });
        alertDialogCreate.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: pd.d
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f79192a.dialog = null;
            }
        });
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: pd.e
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                WebViewUpdateDialogCreatorImpl.showWebViewUpdateDialog$lambda$0$4(webViewCaptchaAnalytics$ludvig_captcha_release, dialogInterface);
            }
        });
        this.dialog = alertDialogCreate;
        alertDialogCreate.show();
    }
}
