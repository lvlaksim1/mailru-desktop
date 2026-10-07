package ru.mail.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.color.MaterialColors;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.HashMap;
import javax.inject.Inject;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.android_utils.SystemUtils;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.libverify.api.VerificationApi;
import ru.mail.libverify.api.VerificationFactory;
import ru.mail.libverify.api.VerificationParameters;
import ru.mail.logic.content.Permission;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mailapp.R;
import ru.mail.registration.RegFlowAnalytics;
import ru.mail.registration.ui.ConfirmationActivity;
import ru.mail.registration.ui.ConfirmationCodeFragment;
import ru.mail.registration.ui.ErrorStatus;
import ru.mail.registration.ui.ErrorValue;
import ru.mail.util.log.Log;
import ru.mail.utils.CastUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@AndroidEntryPoint
public class ConfirmationCodeLibverifyMailRuFragment extends Hilt_ConfirmationCodeLibverifyMailRuFragment {
    private static final String EXTRA_VERIFICATION_ID_KEY = "extra_verification_id_key";
    private static final Log LOG = Log.getLog("ConfirmationCodeLibverifyMailRuFragment");
    public static final String REQUEST_PHONE_STATE_PERMISSION = "request_phone_state_permission";
    private String mLibverifyToken;

    @Inject
    DTOConfiguration.Config.RegRebrandingConfig mRebrandingConfig;
    private VerificationApi mVerificationApi;
    private String mVerificationId;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final VerificationApi.VerificationStateChangedListener mVerificationStateChangedListener = new AnonymousClass1();

    /* JADX INFO: renamed from: ru.mail.ui.fragments.ConfirmationCodeLibverifyMailRuFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    class AnonymousClass1 implements VerificationApi.VerificationStateChangedListener {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onStateChanged$0(String str, VerificationApi.VerificationStateDescriptor verificationStateDescriptor) {
            if (TextUtils.equals(str, ConfirmationCodeLibverifyMailRuFragment.this.mVerificationId)) {
                if (verificationStateDescriptor != null) {
                    ConfirmationCodeLibverifyMailRuFragment.this.handleVerificationState(verificationStateDescriptor);
                    return;
                }
                ConfirmationCodeLibverifyMailRuFragment.this.mVerificationId = null;
                if (ConfirmationCodeLibverifyMailRuFragment.this.isAdded()) {
                    ConfirmationCodeLibverifyMailRuFragment confirmationCodeLibverifyMailRuFragment = ConfirmationCodeLibverifyMailRuFragment.this;
                    confirmationCodeLibverifyMailRuFragment.addError(confirmationCodeLibverifyMailRuFragment.getString(ErrorStatus.SERVERERROR.getErrorMsg()));
                    ConfirmationCodeLibverifyMailRuFragment.this.showErrors();
                }
            }
        }

        @Override // ru.mail.libverify.api.VerificationApi.VerificationStateChangedListener
        public void onStateChanged(@NonNull final String str, @Nullable final VerificationApi.VerificationStateDescriptor verificationStateDescriptor) {
            ConfirmationCodeLibverifyMailRuFragment.LOG.d("state = " + verificationStateDescriptor);
            ConfirmationCodeLibverifyMailRuFragment.this.mHandler.post(new Runnable() { // from class: ru.mail.ui.fragments.b
                @Override // java.lang.Runnable
                public final void run() {
                    this.f98594a.lambda$onStateChanged$0(str, verificationStateDescriptor);
                }
            });
        }
    }

    /* JADX INFO: renamed from: ru.mail.ui.fragments.ConfirmationCodeLibverifyMailRuFragment$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState;

        static {
            int[] iArr = new int[VerificationApi.VerificationState.values().length];
            $SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState = iArr;
            try {
                iArr[VerificationApi.VerificationState.INITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState[VerificationApi.VerificationState.VERIFYING_PHONE_NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState[VerificationApi.VerificationState.VERIFYING_SMS_CODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState[VerificationApi.VerificationState.SUCCEEDED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState[VerificationApi.VerificationState.FAILED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState[VerificationApi.VerificationState.SUSPENDED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState[VerificationApi.VerificationState.WAITING_FOR_SMS_CODE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState[VerificationApi.VerificationState.FINAL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void configureToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        if (this.mRebrandingConfig.getRedesignEnabled()) {
            toolbar.setTitle(getString(R.string.confirmation_code_title));
            toolbar.setTitleTextColor(MaterialColors.getColor(toolbar, R.attr.vkuiColorTextPrimary));
            toolbar.setNavigationIcon(R.drawable.ic_left);
        } else {
            toolbar.setTitle(getString(R.string.registration_title));
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: ru.mail.ui.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f98507a.lambda$configureToolbar$0(view2);
            }
        });
        ((ConfirmationActivity) requireActivity()).setSupportActionBar(toolbar);
    }

    private VerificationApi getVerificationApiInstance() {
        if (this.mVerificationApi == null) {
            VerificationApi verificationFactory = VerificationFactory.getInstance(requireContext());
            this.mVerificationApi = verificationFactory;
            verificationFactory.addVerificationStateChangedListener(this.mVerificationStateChangedListener);
        }
        return this.mVerificationApi;
    }

    private void handleArgumentsData() {
        if (getArguments() == null || !getArguments().getBoolean(REQUEST_PHONE_STATE_PERMISSION)) {
            return;
        }
        startGetRegIdAndSendCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleVerificationState(VerificationApi.VerificationStateDescriptor verificationStateDescriptor) {
        if (isAdded()) {
            Log log = LOG;
            log.d("Verification State : " + verificationStateDescriptor.getState() + ". Source : " + verificationStateDescriptor.getSource() + ". Reason : " + verificationStateDescriptor.getReason());
            int i10 = AnonymousClass2.$SwitchMap$ru$mail$libverify$api$VerificationApi$VerificationState[verificationStateDescriptor.getState().ordinal()];
            if (i10 == 7) {
                VerificationApi.CallInDescriptor callInDescriptor = verificationStateDescriptor.getCallInDescriptor();
                VerificationApi.CallUIDescriptor callUIDescriptor = verificationStateDescriptor.getCallUIDescriptor();
                stopProgress();
                if (callUIDescriptor != null) {
                    log.d("CallUIDescriptor not null");
                    Configuration.CallUIRegistrationConfig callUIRegistrationConfig = ConfigurationRepository.from(getResworbkvmocaf()).getConfiguration().getCallUIRegistrationConfig();
                    prepareUIToShowCallUI(String.valueOf(callUIDescriptor.getCodeLength()), callUIRegistrationConfig.isSecondBtnOnScreenEnabled(), callUIRegistrationConfig.getSecondBtnFallback().name());
                    showErrorIfIncorrectCode(verificationStateDescriptor);
                    return;
                }
                if (callInDescriptor == null) {
                    prepareUIToShowCode();
                    log.d("CallIn/CallUI Descriptor is null. Show sms input");
                    showErrorIfIncorrectCode(verificationStateDescriptor);
                    return;
                }
                log.d("CallInDescriptor not null, timeout " + callInDescriptor.getNumberTimeout());
                if (needToRedrawUICallIn(callInDescriptor.getPhoneNumber())) {
                    Configuration.CallInRegistrationConfig callInRegistrationConfig = ConfigurationRepository.from(getResworbkvmocaf()).getConfiguration().getCallInRegistrationConfig();
                    prepareUIToCallIn(callInDescriptor.getPhoneNumber(), callInRegistrationConfig.isSecondBtnOnScreenEnabled(), callInDescriptor.getNumberTimeout(), callInRegistrationConfig.getNumberOfRetry(), callInRegistrationConfig.getTimeoutDelta(), callInRegistrationConfig.getSecondBtnFallback().name());
                    return;
                }
                return;
            }
            if (i10 != 8) {
                return;
            }
            VerificationApi.FailReason reason = verificationStateDescriptor.getReason();
            if (reason == VerificationApi.FailReason.OK) {
                addJwsVerificationResponse();
                this.mLibverifyToken = verificationStateDescriptor.getToken();
                requestMpopToken();
                return;
            }
            if (reason == VerificationApi.FailReason.INCORRECT_PHONE_NUMBER || reason == VerificationApi.FailReason.UNSUPPORTED_NUMBER || reason == VerificationApi.FailReason.GENERAL_ERROR) {
                String string = getString(ErrorStatus.WRONG_PHONE_NUMBER.getErrorMsg());
                if (!getErrors().contains(string)) {
                    addError(string);
                }
                stopProgress();
                showErrors();
                this.mVerificationApi.resetVerificationCodeError(this.mVerificationId);
                return;
            }
            if (reason == VerificationApi.FailReason.NO_MORE_ROUTES) {
                MailAppDependencies.analytics(getResworbkvmocaf()).noMoreLibverifyRoutes();
                if (isPhoneNumberNotRequiredForRegistration()) {
                    onSwitchToQuestionClicked();
                } else {
                    requireActivity().onBackPressed();
                    Toast.makeText(getActivity(), getString(R.string.should_add_phone), 1).show();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$configureToolbar$0(View view) {
        requireActivity().onBackPressed();
    }

    private void requestMpopToken() {
        getSignupDelegate().performCodeSignup(false);
    }

    private boolean shouldPhonePermissionBeShown() {
        return ConfigurationRepository.from(getResworbkvmocaf()).getConfiguration().getShouldRequestPhonePermissions() && !Permission.READ_PHONE_STATE.isGranted(getResworbkvmocaf()) && SystemUtils.hasTelephonyFeature(requireContext());
    }

    private void showErrorIfIncorrectCode(VerificationApi.VerificationStateDescriptor verificationStateDescriptor) {
        if (verificationStateDescriptor.getSource() == VerificationApi.VerificationSource.USER_INPUT && verificationStateDescriptor.getReason() == VerificationApi.FailReason.INCORRECT_SMS_CODE) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new ErrorValue(ErrorStatus.INVALID, ConfirmationCodeFragment.ATTR_TOKEN_VALUE));
            showResErrors(arrayList);
            this.mVerificationApi.resetVerificationCodeError(this.mVerificationId);
        }
    }

    private void showPhonePermissionFragment() {
        ((RegPermissionsResolver) CastUtils.checkedCastTo(requireActivity(), RegPermissionsResolver.class)).onSwitchToPhoneStatePermissionFragment();
    }

    private void startGetRegIdAndSendCode() {
        startProgress();
        removeErrors();
        VerificationParameters verificationParameters = new VerificationParameters();
        Boolean bool = Boolean.TRUE;
        verificationParameters.setCallInEnabled(bool);
        verificationParameters.setCallUIEnabled(bool);
        getVerificationApiInstance().addVerificationStateChangedListener(this.mVerificationStateChangedListener);
        this.mVerificationId = getVerificationApiInstance().startVerification(getString(R.string.libverify_application_service_name), getAccountData().getPhone(), getAccountData().getLogin(), new HashMap(), verificationParameters);
        MailAppDependencies.analytics(getResworbkvmocaf()).smsLibverifyAction();
    }

    @Override // ru.mail.ui.fragments.ConfirmationCodeMailRuFragment, ru.mail.registration.ui.ConfirmationCodeFragment, ru.mail.registration.ui.BaseAccountDataFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mVerificationId = bundle.getString(EXTRA_VERIFICATION_ID_KEY, null);
        }
    }

    @Override // ru.mail.ui.fragments.ConfirmationCodeMailRuFragment, ru.mail.registration.ui.ConfirmationCodeFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        enforcePhone((ConfigurationRepository.from(getResworbkvmocaf()).getConfiguration().isAllowedRegistrationWithoutPhone() && TextUtils.isEmpty(getAccountData().getSignupPrepareToken())) ? false : true);
        return viewOnCreateView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mVerificationId == null || this.mLibverifyToken != null) {
            return;
        }
        getVerificationApiInstance().requestVerificationState(this.mVerificationId, this.mVerificationStateChangedListener);
    }

    @Override // ru.mail.ui.fragments.ConfirmationCodeMailRuFragment, ru.mail.registration.ui.ConfirmationCodeFragment, ru.mail.registration.ui.SignUpDelegate.SignupResultReceiver
    public void onSignupOk(String str, @Nullable String str2) {
        stopProgress();
        if (TextUtils.isEmpty(str2)) {
            getSignupConfirmDelegate().onStartRegVerifyConfirm(getAccountData(), this.mVerificationId, this.mLibverifyToken);
        } else {
            this.mRecaptchaPresenter.onStartRecaptchaValidation(getActivity(), str2, RegFlowAnalytics.NO_PHONE);
        }
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment, ru.mail.registration.ui.ConfirmationBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        handleArgumentsData();
        configureToolbar(view);
    }

    @Override // ru.mail.ui.fragments.ConfirmationCodeMailRuFragment, ru.mail.ui.registration.RecaptchaPresenter.View
    public void showRecaptchaSuccess() {
        getSignupConfirmDelegate().onStartRegVerifyConfirm(getAccountData(), this.mVerificationId, this.mLibverifyToken);
    }

    @Override // ru.mail.ui.fragments.ConfirmationCodeMailRuFragment, ru.mail.registration.ui.ConfirmationCodeFragment
    protected void taskConfirmCode(String str) {
        startProgress();
        removeErrors();
        if (this.mLibverifyToken == null) {
            getVerificationApiInstance().verifySmsCode(this.mVerificationId, str);
        } else {
            requestMpopToken();
        }
    }

    @Override // ru.mail.ui.fragments.ConfirmationCodeMailRuFragment, ru.mail.registration.ui.ConfirmationCodeFragment
    protected void taskGetRegIdAndSendCode() {
        if (shouldPhonePermissionBeShown()) {
            showPhonePermissionFragment();
        } else {
            startGetRegIdAndSendCode();
        }
    }
}
