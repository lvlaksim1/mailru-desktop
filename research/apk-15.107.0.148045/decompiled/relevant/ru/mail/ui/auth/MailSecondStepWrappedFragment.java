package ru.mail.ui.auth;

import android.view.View;
import ru.mail.auth.webview.MailSecondStepFragment;
import ru.mail.ui.webview.WebViewCreatorWraper;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class MailSecondStepWrappedFragment extends MailSecondStepFragment {
    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initWebView$0(View view) {
        super.initWebView(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.auth.webview.BaseSecondStepAuthFragment, ru.mail.auth.webview.BaseWebViewFragment
    public void initWebView(final View view) {
        new WebViewCreatorWraper(getActivity()).wrap(new Runnable() { // from class: ru.mail.ui.auth.r
            @Override // java.lang.Runnable
            public final void run() {
                this.f98222a.lambda$initWebView$0(view);
            }
        });
    }
}
