package ru.mail.registration.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import ru.mail.Authenticator.R;
import ru.mail.auth.Analytics;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.registration.RegFlowAnalytics;
import ru.mail.utils.NetworkUtils;
import ru.mail.widget.EmptyTextWatcher;
import ru.mail.widget.RegCheckEditText;
import ru.mail.widget.RegErrorsViewInterface;
import ru.mail.widget.RegView;
import ru.mail.widget.RegViewInterface;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@AndroidEntryPoint
public class ConfirmationQuestionFragment extends Hilt_ConfirmationQuestionFragment implements LoadCaptchaDelegate.LoadCaptchaCallback, SignupConfirmDelegate.ConfirmResultReceiver {
    public static final String ACTION = "confirmation_question_action";
    protected static final String ARG_CAPTCHA_FLOW = "captcha_flow";

    @Inject
    Analytics analytics;
    private RegCheckEditText mCaptchaCodeEditText;
    private RegViewInterface mCaptchaCodeRegView;
    private Button mDoneButton;
    private LoadCaptchaDelegate mLoadCaptchaDelegate;
    private String mMrcuCookie;
    private ImageButton mRefreshCaptchaButton;
    private SignupConfirmDelegate mSignupDelegate;
    View.OnClickListener mDoneListener = new View.OnClickListener() { // from class: ru.mail.registration.ui.t
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f96662a.lambda$new$0(view);
        }
    };
    View.OnClickListener mClickSwitchToPhone = new View.OnClickListener() { // from class: ru.mail.registration.ui.u
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f96663a.lambda$new$1(view);
        }
    };
    TextWatcher mAnswerAndCodeWatcher = new EmptyTextWatcher() { // from class: ru.mail.registration.ui.ConfirmationQuestionFragment.1
        @Override // ru.mail.widget.EmptyTextWatcher, android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            ConfirmationQuestionFragment.this.checkFilledField();
        }
    };
    private final TextView.OnEditorActionListener mDoneActionListener = new TextView.OnEditorActionListener() { // from class: ru.mail.registration.ui.v
        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i10, KeyEvent keyEvent) {
            return this.f96664a.lambda$new$2(textView, i10, keyEvent);
        }
    };
    private final View.OnClickListener mReloadCaptcha = new View.OnClickListener() { // from class: ru.mail.registration.ui.w
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f96665a.lambda$new$3(view);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void checkFilledField() {
        boolean zIsEmpty = TextUtils.isEmpty(getCaptchaCode());
        this.mDoneButton.setEnabled(!zIsEmpty);
        this.mCaptchaCodeEditText.setOnEditorActionListener(!zIsEmpty ? this.mDoneActionListener : null);
    }

    private String getCaptchaCode() {
        return this.mCaptchaCodeEditText.getText().toString();
    }

    private void initFocus() {
        this.mCaptchaCodeEditText.requestFocus();
        ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(this.mCaptchaCodeEditText, 1);
    }

    private void initViewsListeners() {
        this.mCaptchaCodeEditText.setOnEditorActionListener(this.mDoneActionListener);
        this.mCaptchaCodeEditText.addTextChangedListener(this.mAnswerAndCodeWatcher);
        this.mDoneButton.setOnClickListener(this.mDoneListener);
        this.mRefreshCaptchaButton.setOnClickListener(this.mReloadCaptcha);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$configureToolbar$4(View view) {
        requireActivity().onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        onDoneButtonClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(View view) {
        onSwitchToSmsCodeClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$2(TextView textView, int i10, KeyEvent keyEvent) {
        if (i10 != 0 && i10 != 6 && i10 != 5) {
            return false;
        }
        onDoneButtonClicked();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(View view) {
        if (NetworkUtils.hasInternetConnection(getActivity())) {
            loadCaptcha(getView());
            return;
        }
        removeErrors();
        addError(getResources().getString(R.string.network_error_no_connection));
        showErrors();
    }

    public static ConfirmationQuestionFragment newInstance(ConfirmationActivity.CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow) {
        ConfirmationQuestionFragment confirmationQuestionFragment = new ConfirmationQuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_CAPTCHA_FLOW, captchaQuestionAnalyticsFlow.toString());
        confirmationQuestionFragment.setArguments(bundle);
        return confirmationQuestionFragment;
    }

    private void onSwitchToSmsCodeClicked() {
        ((RegChooserResultReceiver) getActivity()).onSwitchToConfirmPhoneFragment();
        this.analytics.registrationCaptchaSwitchToSmsCode();
    }

    private void setCodeEditTextTag(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        this.mCaptchaCodeEditText.setTag(arrayList);
    }

    protected void configureToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.registration_title));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: ru.mail.registration.ui.s
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f96661a.lambda$configureToolbar$4(view2);
                }
            });
        }
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment
    public String getAction() {
        return ACTION;
    }

    protected RegCheckEditText getCaptchaCodeEditText() {
        return this.mCaptchaCodeEditText;
    }

    protected int getConfirmCapchaLayout() {
        return R.layout.reg_confirm_question;
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment
    protected RegErrorsViewInterface getErrorsView(View view) {
        return (RegErrorsViewInterface) view.findViewById(R.id.captcha_error);
    }

    protected String getmMrcuCookie() {
        return this.mMrcuCookie;
    }

    protected void loadCaptcha(View view) {
        LoadCaptchaDelegate loadCaptchaDelegate = new LoadCaptchaDelegate(getActivity(), this, (ImageView) view.findViewById(R.id.captcha_image), (ProgressBar) view.findViewById(R.id.captcha_progress), (ImageButton) view.findViewById(R.id.captcha_refresh_button));
        this.mLoadCaptchaDelegate = loadCaptchaDelegate;
        loadCaptchaDelegate.loadCaptcha();
        this.analytics.registrationCaptchaLoadCaptcha();
    }

    @Override // ru.mail.registration.ui.LoadCaptchaDelegate.LoadCaptchaCallback
    public void loadCaptchaFail() {
        Toast.makeText(getActivity(), getString(R.string.authenticator_network_error), 1).show();
        this.analytics.registrationCaptchaLoadCaptchaError();
    }

    @Override // ru.mail.registration.ui.LoadCaptchaDelegate.LoadCaptchaCallback
    public void loadCaptchaSuccess(LoadCaptchaDelegate.CaptchaResult captchaResult) {
        this.mMrcuCookie = captchaResult.getCookie();
        this.analytics.registrationCaptchaLoadCaptchaSuccess();
    }

    @Override // ru.mail.registration.ui.SignupConfirmDelegate.ConfirmResultReceiver
    public void onAccountRegistered(SignupConfirmDelegate.RegistrationResult registrationResult) {
        onAccountRegistered(registrationResult, getAccountData(), RegFlowAnalytics.CAPTCHA);
        this.analytics.registrationCaptchaRegistrationSuccess();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(getConfirmCapchaLayout(), viewGroup, false);
        configureToolbar(viewInflate);
        this.mCaptchaCodeEditText = (RegCheckEditText) viewInflate.findViewById(R.id.captcha_code);
        this.mCaptchaCodeRegView = (RegViewInterface) viewInflate.findViewById(R.id.captcha_code_layout);
        this.mDoneButton = (Button) viewInflate.findViewById(R.id.question_ok);
        ImageButton imageButton = (ImageButton) viewInflate.findViewById(R.id.captcha_refresh_button);
        this.mRefreshCaptchaButton = imageButton;
        imageButton.setImageDrawable(getResworbkvmocaf().getDrawable(R.drawable.ic_refresh));
        viewInflate.findViewById(R.id.tv_set_phone).setOnClickListener(this.mClickSwitchToPhone);
        initViewsListeners();
        showCaptchaViews(viewInflate);
        loadCaptcha(viewInflate);
        this.analytics.registrationCaptchaView(getArguments().getString(ARG_CAPTCHA_FLOW, "unknown"));
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LoadCaptchaDelegate loadCaptchaDelegate = this.mLoadCaptchaDelegate;
        if (loadCaptchaDelegate != null) {
            loadCaptchaDelegate.clearCallBackRef();
        }
    }

    protected void onDoneButtonClicked() {
        removeErrors();
        if (NetworkUtils.hasInternetConnection(getActivity())) {
            startProgress();
            startRegTask();
        } else {
            addError(getResources().getString(R.string.network_error_no_connection));
            showErrors();
        }
        this.analytics.registrationCaptchaDoneClick();
    }

    @Override // ru.mail.registration.ui.SignupConfirmDelegate.ConfirmResultReceiver
    public void onRegistrationFail(List<ErrorValue> list) {
        if (isAdded()) {
            stopProgress();
            processErrors(list);
        }
        this.analytics.registrationCaptchaRegistrationError();
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initFocus();
        this.mSignupDelegate = new SignupConfirmDelegateImpl(this, getResworbkvmocaf(), AuthenticatorConfig.getInstance().getMigrateToPostConfig(), getXmailFromParam());
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment
    protected void removeErrors() {
        super.removeErrors();
        this.mCaptchaCodeEditText.setError(false);
    }

    protected void setCaptchaCodeEditText(RegCheckEditText regCheckEditText) {
        this.mCaptchaCodeEditText = regCheckEditText;
    }

    protected void setmMrcuCookie(String str) {
        this.mMrcuCookie = str;
    }

    protected void showCaptchaViews(View view) {
        view.findViewById(R.id.captcha_image_layout).setVisibility(0);
        view.findViewById(R.id.captcha_code_layout).setVisibility(0);
        view.findViewById(R.id.captcha_divider).setVisibility(0);
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment
    public void showResErrors(List<ErrorValue> list) {
        removeErrors();
        for (int i10 = 0; i10 < list.size(); i10++) {
            StringBuffer stringBuffer = new StringBuffer("");
            ErrorValue errorValue = list.get(i10);
            stringBuffer.append(getString(errorValue.getErr().getErrorMsg()));
            if (errorValue.getKey().equals("reg_anketa.capcha")) {
                this.analytics.registrationCaptchaRegistrationCaptchaError();
                String titleText = ((RegView) getView().findViewById(R.id.captcha_code_layout)).getTitleText();
                stringBuffer.append(StringUtils.SPACE);
                stringBuffer.append(titleText);
                this.mCaptchaCodeRegView.setError(true);
                this.mCaptchaCodeEditText.setText("");
                loadCaptcha(getView());
            }
            if (errorValue.getErr().getErrorMsg() == ErrorStatus.INVALID.getErrorMsg()) {
                stringBuffer.append(StringUtils.SPACE);
                stringBuffer.append(getString(ErrorStatus.INVALID_END.getErrorMsg()));
            }
            if (errorValue.getErr().ordinal() >= ErrorStatus.SERVERERROR.ordinal() && errorValue.getErr().ordinal() <= ErrorStatus.ACCESS_DENIED.ordinal()) {
                stringBuffer.append(errorValue.getKey());
            }
            addError(stringBuffer.toString());
        }
        showErrors();
    }

    protected void startRegTask() {
        getAccountData().setCode(getCaptchaCode());
        getAccountData().setCookie(this.mMrcuCookie);
        this.mSignupDelegate.onStartRegAnketaConfirm(getAccountData());
    }

    @Override // ru.mail.registration.ui.SignupConfirmDelegate.ConfirmResultReceiver
    public void onRegistrationFail(int i10) {
        addError(getString(i10));
        showErrors();
        this.analytics.registrationCaptchaRegistrationError();
    }
}
