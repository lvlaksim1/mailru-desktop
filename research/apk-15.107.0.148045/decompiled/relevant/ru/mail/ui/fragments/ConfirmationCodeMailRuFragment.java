package ru.mail.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.Collections;
import javax.inject.Inject;
import ru.mail.config.ConfigurationRepository;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mailapp.R;
import ru.mail.registration.RegFlowAnalytics;
import ru.mail.registration.ui.ErrorStatus;
import ru.mail.registration.ui.ErrorValue;
import ru.mail.ui.auth.universal.authDesign.AuthDesignFactory;
import ru.mail.ui.registration.RecaptchaPresenter;
import ru.mail.ui.registration.RecaptchaPresenterImpl;
import ru.mail.util.SafetyVerifyManager;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@AndroidEntryPoint
public class ConfirmationCodeMailRuFragment extends Hilt_ConfirmationCodeMailRuFragment implements RecaptchaPresenter.View {
    protected RecaptchaPresenterImpl mRecaptchaPresenter;

    @Nullable
    private String mRecaptchaSiteKey;

    @Inject
    DTOConfiguration.Config.RegRebrandingConfig mRegRebrandingConfig;

    private void addUseExtendedSignup() {
        getAccountData().setUseExtendedSignup(ConfigurationRepository.from(getResworbkvmocaf()).getConfiguration().isRecaptchaEnabled());
    }

    protected void addJwsVerificationResponse() {
        SafetyVerifyManager safetyVerifyManagerFrom = SafetyVerifyManager.from(getResworbkvmocaf());
        getAccountData().setJwsVerification(safetyVerifyManagerFrom.getJwsVerificationResponse());
        safetyVerifyManagerFrom.cancelSafetyNetRequest();
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment
    protected int getConfirmationCodeLayout() {
        return new AuthDesignFactory(requireActivity()).getRegistrationActivityDesign().getConfirmSmsCodeLayout();
    }

    @Override // ru.mail.ui.fragments.Hilt_ConfirmationCodeMailRuFragment, ru.mail.registration.ui.Hilt_ConfirmationCodeFragment, androidx.fragment.app.Fragment
    /* JADX INFO: renamed from: getContext */
    public /* bridge */ /* synthetic */ Context getResworbkvmocaf() {
        return super.getResworbkvmocaf();
    }

    @Override // ru.mail.registration.ui.Hilt_ConfirmationCodeFragment, androidx.fragment.app.Fragment, androidx.lifecycle.HasDefaultViewModelProviderFactory
    public /* bridge */ /* synthetic */ ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return super.getDefaultViewModelProviderFactory();
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment
    protected boolean isRedesignEnabled() {
        return this.mRegRebrandingConfig.getRedesignEnabled();
    }

    @Override // ru.mail.ui.fragments.Hilt_ConfirmationCodeMailRuFragment, ru.mail.registration.ui.Hilt_ConfirmationCodeFragment, androidx.fragment.app.Fragment
    @CallSuper
    public /* bridge */ /* synthetic */ void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment, ru.mail.registration.ui.BaseAccountDataFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addUseExtendedSignup();
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        RecaptchaPresenterImpl recaptchaPresenterImpl = new RecaptchaPresenterImpl(getAccountData(), requireContext());
        this.mRecaptchaPresenter = recaptchaPresenterImpl;
        recaptchaPresenterImpl.onAttach((RecaptchaPresenter.View) this);
        return viewOnCreateView;
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mRecaptchaPresenter.onDetach();
    }

    @Override // ru.mail.ui.fragments.Hilt_ConfirmationCodeMailRuFragment, ru.mail.registration.ui.Hilt_ConfirmationCodeFragment, androidx.fragment.app.Fragment
    public /* bridge */ /* synthetic */ LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return super.onGetLayoutInflater(bundle);
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment, ru.mail.registration.ui.SignUpDelegate.SignupResultReceiver
    public void onSignupOk(String str, @Nullable String str2) {
        super.onSignupOk(str, str2);
        this.mRecaptchaSiteKey = str2;
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment
    protected void onSwitchToQuestionClicked() {
        addJwsVerificationResponse();
        super.onSwitchToQuestionClicked();
    }

    @Override // ru.mail.ui.registration.RecaptchaPresenter.View
    public void showRecaptchaFail() {
        showResErrors(Collections.singletonList(new ErrorValue(ErrorStatus.CAPTCHA, getString(R.string.authenticator_captcha_error))));
    }

    public void showRecaptchaSuccess() {
        super.taskConfirmCode(getEnteredCode());
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment
    protected void taskConfirmCode(String str) {
        if (TextUtils.isEmpty(this.mRecaptchaSiteKey)) {
            super.taskConfirmCode(str);
        } else {
            this.mRecaptchaPresenter.onStartRecaptchaValidation(getActivity(), this.mRecaptchaSiteKey, RegFlowAnalytics.PHONE);
        }
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment
    protected void taskGetRegIdAndSendCode() {
        startProgress();
        addJwsVerificationResponse();
        super.taskGetRegIdAndSendCode();
    }
}
