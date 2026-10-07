package ru.mail.ui.registration;

import android.app.Activity;
import ru.mail.logic.content.Detachable;
import ru.mail.registration.RegFlowAnalytics;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public interface RecaptchaPresenter extends Detachable<View> {

    /* JADX INFO: compiled from: ProGuard */
    public interface View {
        void showRecaptchaFail();

        void showRecaptchaSuccess();
    }

    void onStartRecaptchaValidation(Activity activity, String str, RegFlowAnalytics regFlowAnalytics);
}
