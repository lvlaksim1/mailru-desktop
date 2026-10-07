package ru.mail.ui.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.mailapp.R;
import ru.mail.registration.RegFlowAnalytics;
import ru.mail.registration.ui.BaseAccountDataFragment;
import ru.mail.registration.ui.ConfirmationActivityInterface;
import ru.mail.registration.ui.ConfirmationQuestionFragment;
import ru.mail.registration.ui.ErrorStatus;
import ru.mail.registration.ui.ErrorValue;
import ru.mail.registration.ui.SignupConfirmDelegate;
import ru.mail.registration.ui.SignupConfirmDelegateImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class RecaptchaMailRuFragment extends BaseAccountDataFragment implements RecaptchaPresenter.View, SignupConfirmDelegate.ConfirmResultReceiver {
    public static final String EXTRA_SITE_KEY = "site_key";
    private RecaptchaPresenter mRecaptchaPresenter;
    private SignupConfirmDelegateImpl mSignupConfirmDelegate;

    public static RecaptchaMailRuFragment newInstance(String str) {
        RecaptchaMailRuFragment recaptchaMailRuFragment = new RecaptchaMailRuFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_SITE_KEY, str);
        recaptchaMailRuFragment.setArguments(bundle);
        return recaptchaMailRuFragment;
    }

    @Override // ru.mail.registration.ui.SignupConfirmDelegate.ConfirmResultReceiver
    public void onAccountRegistered(SignupConfirmDelegate.RegistrationResult registrationResult) {
        stopProgress();
        ConfirmationActivityInterface confirmationReceiver = getConfirmationReceiver();
        if (confirmationReceiver != null) {
            confirmationReceiver.onAccountRegistered(registrationResult, getAccountData(), Authenticator.Type.DEFAULT.toString(), RegFlowAnalytics.RECAPTCHA);
        }
    }

    @Override // ru.mail.registration.ui.BaseAccountDataFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.mRecaptchaPresenter = new RecaptchaPresenterImpl(getAccountData(), getThemedContext());
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.mSignupConfirmDelegate = new SignupConfirmDelegateImpl(this, getThemedContext(), AuthenticatorConfig.getInstance().getMigrateToPostConfig(), getXmailFromParam());
        this.mRecaptchaPresenter.onAttach(this);
        this.mRecaptchaPresenter.onStartRecaptchaValidation(getActivity(), getArguments().getString(EXTRA_SITE_KEY), RegFlowAnalytics.NO_PHONE);
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mRecaptchaPresenter.onDetach();
    }

    @Override // ru.mail.registration.ui.SignupConfirmDelegate.ConfirmResultReceiver
    public void onRegistrationFail(@Nullable List<ErrorValue> list) {
        stopProgress();
        getRegChooserResultReceiver().onChooseQuestionError(list, getAccountData(), ConfirmationQuestionFragment.ACTION);
    }

    @Override // ru.mail.ui.registration.RecaptchaPresenter.View
    public void showRecaptchaFail() {
        getRegChooserResultReceiver().onChooseQuestionError(Collections.singletonList(new ErrorValue(ErrorStatus.CAPTCHA, getString(R.string.authenticator_captcha_error))), getAccountData(), ConfirmationQuestionFragment.ACTION);
    }

    @Override // ru.mail.ui.registration.RecaptchaPresenter.View
    public void showRecaptchaSuccess() {
        startProgress();
        this.mSignupConfirmDelegate.onStartRegAnketaConfirm(getAccountData());
    }

    @Override // ru.mail.registration.ui.SignupConfirmDelegate.ConfirmResultReceiver
    public void onRegistrationFail(int i10) {
        stopProgress();
        getRegChooserResultReceiver().onChooseQuestionError(Collections.singletonList(new ErrorValue(ErrorStatus.SERVERERROR, getString(i10))), getAccountData(), ConfirmationQuestionFragment.ACTION);
    }
}
