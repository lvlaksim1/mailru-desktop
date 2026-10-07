package ru.mail.registration.ui;

import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public interface RegChooserResultReceiver {
    void onChooseQuestionError(@Nullable List<ErrorValue> list, AccountData accountData, String str);

    void onSwitchToCaptchaQuestionFragment(ConfirmationActivity.CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow);

    void onSwitchToConfirmPhoneFragment();

    void onSwitchToRecaptchaQuestionFragment(String str, ConfirmationActivity.CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow);
}
