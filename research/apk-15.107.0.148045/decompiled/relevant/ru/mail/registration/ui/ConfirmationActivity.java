package ru.mail.registration.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import ru.mail.Authenticator.R;
import ru.mail.auth.AnalyticUtilKt;
import ru.mail.auth.Analytics;
import ru.mail.auth.AuthenticatorConfig;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@AndroidEntryPoint
public abstract class ConfirmationActivity extends Hilt_ConfirmationActivity implements RegChooserResultReceiver {
    public static final String CONFIRM_ACT_VK_TOKEN_KEY = "VK_TOKEN_KEY";

    @Inject
    Analytics analytics;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ProGuard */
    public static class CaptchaQuestionAnalyticsFlow {
        private static final /* synthetic */ CaptchaQuestionAnalyticsFlow[] $VALUES = $values();
        public static final CaptchaQuestionAnalyticsFlow MISSING_PHONE_SUPPORT;
        public static final CaptchaQuestionAnalyticsFlow NO_PHONE;
        public static final CaptchaQuestionAnalyticsFlow NO_PHONE_WHILE_WAITING_SMS;

        /* JADX INFO: renamed from: ru.mail.registration.ui.ConfirmationActivity$CaptchaQuestionAnalyticsFlow$1, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass1 extends CaptchaQuestionAnalyticsFlow {
            @Override // java.lang.Enum
            public String toString() {
                return "nophone";
            }

            private AnonymousClass1(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.registration.ui.ConfirmationActivity$CaptchaQuestionAnalyticsFlow$2, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass2 extends CaptchaQuestionAnalyticsFlow {
            @Override // java.lang.Enum
            public String toString() {
                return "nophoneWaitingSMS";
            }

            private AnonymousClass2(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.registration.ui.ConfirmationActivity$CaptchaQuestionAnalyticsFlow$3, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass3 extends CaptchaQuestionAnalyticsFlow {
            @Override // java.lang.Enum
            public String toString() {
                return "missingPhoneSupport";
            }

            private AnonymousClass3(String str, int i10) {
                super(str, i10);
            }
        }

        private static /* synthetic */ CaptchaQuestionAnalyticsFlow[] $values() {
            return new CaptchaQuestionAnalyticsFlow[]{NO_PHONE, NO_PHONE_WHILE_WAITING_SMS, MISSING_PHONE_SUPPORT};
        }

        static {
            NO_PHONE = new AnonymousClass1("NO_PHONE", 0);
            NO_PHONE_WHILE_WAITING_SMS = new AnonymousClass2("NO_PHONE_WHILE_WAITING_SMS", 1);
            MISSING_PHONE_SUPPORT = new AnonymousClass3("MISSING_PHONE_SUPPORT", 2);
        }

        public static CaptchaQuestionAnalyticsFlow valueOf(String str) {
            return (CaptchaQuestionAnalyticsFlow) Enum.valueOf(CaptchaQuestionAnalyticsFlow.class, str);
        }

        public static CaptchaQuestionAnalyticsFlow[] values() {
            return (CaptchaQuestionAnalyticsFlow[]) $VALUES.clone();
        }

        private CaptchaQuestionAnalyticsFlow(String str, int i10) {
            super(str, i10);
        }
    }

    private String getRegFromParam() {
        return AnalyticUtilKt.getRegFormParam((AccountData) getIntent().getSerializableExtra(AbstractRegistrationFragment.ACCOUNT_DATA));
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    protected Fragment createCaptchaQuestionFragment(CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow) {
        return ConfirmationQuestionFragment.newInstance(captchaQuestionAnalyticsFlow);
    }

    protected Fragment createConfirmationCodeFragment() {
        return new ConfirmationCodeFragment();
    }

    protected Fragment createRecaptchaQuestionFragment(String str, CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow) {
        return createCaptchaQuestionFragment(captchaQuestionAnalyticsFlow);
    }

    protected int getConfirmationLayoutId() {
        return R.layout.reg_switcher;
    }

    protected void initFragments() {
        if (getIntent() == null) {
            onSwitchToConfirmPhoneFragment();
        } else if (TextUtils.equals(getIntent().getStringExtra(AbstractRegistrationFragment.CONFIRMATION_ACTION), ConfirmationCodeFragment.ACTION)) {
            onSwitchToConfirmPhoneFragment();
        } else {
            new RegQuestionChooser(getApplicationContext(), this, (AccountData) getIntent().getSerializableExtra(AbstractRegistrationFragment.ACCOUNT_DATA), AuthenticatorConfig.getInstance().getMigrateToPostConfig(), null).performSignupCheck(CaptchaQuestionAnalyticsFlow.MISSING_PHONE_SUPPORT, this);
        }
    }

    @Override // ru.mail.registration.ui.RegChooserResultReceiver
    public void onChooseQuestionError(@Nullable List<ErrorValue> list, AccountData accountData, String str) {
        Intent intent = new Intent();
        if (list == null) {
            ErrorStatus errorStatus = ErrorStatus.SERVERERROR;
            intent.putExtra(AbstractRegistrationFragment.ERRORS_LIST, new ArrayList(Collections.singletonList(new ErrorValue(errorStatus, getString(errorStatus.getErrorMsg())))));
        } else {
            intent.putExtra(AbstractRegistrationFragment.CONFIRMATION_ACTION, str);
            intent.putExtra(AbstractRegistrationFragment.ERRORS_LIST, new ArrayList(list));
        }
        intent.putExtra(AbstractRegistrationFragment.ACCOUNT_DATA, accountData);
        setResult(0, intent);
        finish();
    }

    @Override // ru.mail.registration.ui.BaseRegistrationConfirmActivity, ru.mail.registration.ui.Hilt_BaseRegistrationConfirmActivity, ru.mail.auth.BaseAuthActivity, ru.mail.auth.BaseToolbarActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getConfirmationLayoutId());
        initActionBar();
        initFragments();
        this.analytics.registrationOpenConfirmation(getRegFromParam());
    }

    @Override // ru.mail.registration.ui.RegChooserResultReceiver
    public void onSwitchToCaptchaQuestionFragment(CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow) {
        switchTo(createCaptchaQuestionFragment(captchaQuestionAnalyticsFlow), true);
    }

    @Override // ru.mail.registration.ui.RegChooserResultReceiver
    public void onSwitchToConfirmPhoneFragment() {
        switchTo(createConfirmationCodeFragment(), false);
    }

    protected void onSwitchToPhoneConfirmationFragment(@Nullable Bundle bundle) {
        Fragment fragmentCreateConfirmationCodeFragment = createConfirmationCodeFragment();
        fragmentCreateConfirmationCodeFragment.setArguments(bundle);
        switchTo(fragmentCreateConfirmationCodeFragment, false);
    }

    @Override // ru.mail.registration.ui.RegChooserResultReceiver
    public void onSwitchToRecaptchaQuestionFragment(String str, CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow) {
        switchTo(createRecaptchaQuestionFragment(str, captchaQuestionAnalyticsFlow), false);
    }

    protected void switchTo(Fragment fragment, boolean z10) {
        hideKeyboard();
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.fragment_container, fragment);
        if (z10) {
            fragmentTransactionBeginTransaction.addToBackStack(null);
        }
        fragmentTransactionBeginTransaction.commit();
    }
}
