package ru.mail.registration.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;
import ru.mail.Authenticator.R;
import ru.mail.android_utils.ViewUtilsKt;
import ru.mail.auth.AnalyticUtilKt;
import ru.mail.auth.Analytics;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.request.MigrateToPostConfig;
import ru.mail.registration.RegFlowAnalytics;
import ru.mail.registration.ui.childsignup.ChildSignUpDelegate;
import ru.mail.registration.utils.CallInUtils;
import ru.mail.registration.validator.CaptchaValidator;
import ru.mail.registration.validator.PhoneCodeValidator;
import ru.mail.registration.validator.PhoneNumberValidator;
import ru.mail.util.log.Log;
import ru.mail.utils.ClickUtilsKt;
import ru.mail.utils.ContextResourceProvider;
import ru.mail.utils.NetworkUtils;
import ru.mail.utils.PhoneUtils;
import ru.mail.utils.ResourceProvider;
import ru.mail.widget.PhoneEditor;
import ru.mail.widget.RegCheckAutoCompleteTextView;
import ru.mail.widget.RegCheckEditText;
import ru.mail.widget.RegViewInterface;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@AndroidEntryPoint
public class ConfirmationCodeFragment extends Hilt_ConfirmationCodeFragment implements PhoneTextLengthChanged, LoadCaptchaDelegate.LoadCaptchaCallback, ValueChecker.CheckResultListener, SignUpDelegate.SignupResultReceiver, SignupConfirmDelegate.ConfirmResultReceiver, RegQuestionChooser.PhoneRequiredReceiver {
    public static final String ACTION = "confirmation_code_action";
    public static final String ATTR_PHONE = "phones[0].phone";
    public static final String ATTR_PHONES = "phones";
    public static final String ATTR_TOKEN_CAPTCHA = "reg_token.capcha";
    public static final String ATTR_TOKEN_VALUE = "reg_token.value";
    private static final long DELAY = 300;
    private static final Log LOG = Log.getLog("ConfirmationCodeFragment");

    @Inject
    Analytics analytics;
    private TextView mCallInNumber;
    private TextView mCallInTitle;
    private TableRow mCallInView;
    private RegCheckEditText mCaptchaEditText;
    private RegCheckAutoCompleteTextView mCodeEditText;
    private TextView mCodeText;
    private String mEnteredCode;
    private TextView mGoToQuestion;
    private LoadCaptchaDelegate mLoadCaptchaDelegate;
    private String mMrcuCookie;
    private Button mNext;
    private PhoneEditor mPhoneEditor;
    private SignupConfirmDelegate mSignupConfirmDelegate;
    private SignUpDelegate mSignupDelegate;
    private RegViewInterface mViewCaptcha;
    private RegViewInterface mViewCode;
    private RegViewInterface mViewPhone;
    private final TextWatcher mTextWatcher = new TextWatcher() { // from class: ru.mail.registration.ui.ConfirmationCodeFragment.1
        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
            ConfirmationCodeFragment.this.removeErrors();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
        }
    };
    private final TextView.OnEditorActionListener mPhoneActionListener = new TextView.OnEditorActionListener() { // from class: ru.mail.registration.ui.m
        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i10, KeyEvent keyEvent) {
            return this.f96655a.lambda$new$0(textView, i10, keyEvent);
        }
    };
    private final TextView.OnEditorActionListener mCodeActionListener = new TextView.OnEditorActionListener() { // from class: ru.mail.registration.ui.n
        @Override // android.widget.TextView.OnEditorActionListener
        public final boolean onEditorAction(TextView textView, int i10, KeyEvent keyEvent) {
            return this.f96656a.lambda$new$1(textView, i10, keyEvent);
        }
    };
    View.OnClickListener mCheckPhoneCodeButtonListener = new View.OnClickListener() { // from class: ru.mail.registration.ui.o
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f96657a.lambda$new$2(view);
        }
    };
    View.OnClickListener mReloadCaptcha = new View.OnClickListener() { // from class: ru.mail.registration.ui.p
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f96658a.lambda$new$3(view);
        }
    };

    /* JADX INFO: compiled from: ProGuard */
    public interface ConfirmationCodeSetup {
        boolean isInternetRu();

        boolean isInternetRuFromDeeplink();

        boolean isInternetRuSecurityEnabled();
    }

    @NonNull
    private SignUpDelegate createSignUpDelegate() {
        MigrateToPostConfig migrateToPostConfig = AuthenticatorConfig.getInstance().getMigrateToPostConfig();
        return isChildRegFlow() ? new ChildSignUpDelegate(Authenticator.getAccountManagerWrapper(requireContext()), migrateToPostConfig, requireActivity(), this, getAccountData()) : new ConfirmationSignUpDelegate(requireActivity(), this, getAccountData(), migrateToPostConfig);
    }

    private void initFocus() {
        this.mPhoneEditor.requestFocus();
        ((InputMethodManager) requireActivity().getSystemService("input_method")).showSoftInput(this.mPhoneEditor, 1);
    }

    private void initGoToQuestionBtn(final String str) {
        ClickUtilsKt.setDebouncedListener(this.mGoToQuestion, 300L, new Function1() { // from class: ru.mail.registration.ui.i
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f96648a.lambda$initGoToQuestionBtn$12(str, (View) obj);
            }
        });
    }

    private void initUIValues(View view) {
        ResourceProvider resourceProviderFrom = ContextResourceProvider.from(requireActivity());
        this.mPhoneEditor.setPhoneChanged(this);
        this.mPhoneEditor.setValueChecker(new ValueChecker<>(new PhoneNumberValidator(resourceProviderFrom), this));
        this.mCodeEditText.setValueChecker(new ValueChecker<>(new PhoneCodeValidator(resourceProviderFrom), this));
        this.mCaptchaEditText.setValueChecker(new ValueChecker<>(new CaptchaValidator(resourceProviderFrom), this));
        ClickUtilsKt.setDebouncedListener(this.mNext, 300L, new Function1() { // from class: ru.mail.registration.ui.f
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f96645a.lambda$initUIValues$5((View) obj);
            }
        });
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.captcha_refresh_button);
        imageButton.setImageDrawable(requireContext().getDrawable(R.drawable.ic_refresh));
        imageButton.setOnClickListener(this.mReloadCaptcha);
    }

    private boolean isLimitExceedError(ArrayList<ErrorValue> arrayList) {
        Iterator<ErrorValue> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().getKey().equalsIgnoreCase("LIMIT_EXCEED_MIN")) {
                return true;
            }
        }
        return false;
    }

    private boolean isPhoneInputHasFinished() {
        return (this.mPhoneEditor.isEnabled() || this.mPhoneEditor.getPhone().isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$initGoToQuestionBtn$12(String str, View view) {
        if (str.equals("CAPTURE")) {
            onSwitchToQuestionClicked();
        } else {
            prepareUIToShowCode();
            this.analytics.registrationSwitchToSms(AnalyticUtilKt.getRegFormParam(getAccountData()));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$initUIValues$5(View view) {
        onGetPhoneCodeButtonClicked();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$0(TextView textView, int i10, KeyEvent keyEvent) {
        if ((i10 != 0 && i10 != 6 && i10 != 5) || getActivity() == null) {
            return false;
        }
        onGetPhoneCodeButtonClicked();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$new$1(TextView textView, int i10, KeyEvent keyEvent) {
        if (i10 != 0 && i10 != 6 && i10 != 5) {
            return false;
        }
        onCheckCodeButtonClicked();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2(View view) {
        onCheckCodeButtonClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(View view) {
        if (NetworkUtils.hasInternetConnection(requireActivity())) {
            loadCaptcha();
            return;
        }
        removeErrors();
        addError(getResources().getString(R.string.network_error_no_connection));
        showErrors();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$onCreateView$4(View view) {
        onSwitchToQuestionClicked();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$prepareUIToCallIn$10(String str, View view) {
        makeCall(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareUIToCallIn$11(String str) {
        this.analytics.registrationWithCallShowSecondBtn(str, false);
        this.mGoToQuestion.setVisibility(0);
        this.mGoToQuestion.setText(getResources().getString(R.string.reg_confirm_ets));
        initGoToQuestionBtn(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$prepareUIToShowCallUI$9(View view) {
        this.mCheckPhoneCodeButtonListener.onClick(view);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareUIToShowCode$6() {
        ((InputMethodManager) requireActivity().getSystemService("input_method")).showSoftInput(this.mCodeEditText, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$prepareUIToShowCode$7(View view) {
        this.mCheckPhoneCodeButtonListener.onClick(view);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$prepareUIToShowCode$8(View view) {
        onSwitchToQuestionClicked();
        return Unit.INSTANCE;
    }

    private void makeCall(String str) {
        this.analytics.registrationRequestCallIn(AnalyticUtilKt.getRegFormParam(getAccountData()));
        startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:+" + str)));
    }

    private void onCheckCodeButtonClicked() {
        removeErrors();
        if (!NetworkUtils.hasInternetConnection(requireActivity())) {
            Toast.makeText(getActivity(), R.string.registration_no_internet_done, 0).show();
        } else if (this.mCodeEditText.checkValue()) {
            this.mViewCode.setError(false);
            String string = this.mCodeEditText.getText().toString();
            this.mEnteredCode = string;
            taskConfirmCode(string);
        } else {
            onPhoneCodeValidatorFailed();
            showErrors();
        }
        this.analytics.registrationCheckCode(AnalyticUtilKt.getRegFormParam(getAccountData()));
    }

    private void onGetPhoneCodeButtonClicked() {
        removeErrors();
        if (!NetworkUtils.hasInternetConnection(requireActivity())) {
            Toast.makeText(getActivity(), R.string.registration_no_internet_request_code, 0).show();
        } else if (this.mPhoneEditor.checkValue()) {
            String string = this.mPhoneEditor.getText().toString();
            if (!string.startsWith(Marker.ANY_NON_NULL_MARKER)) {
                this.mPhoneEditor.setText(Marker.ANY_NON_NULL_MARKER + string);
            }
            getAccountData().setPhone(this.mPhoneEditor.getPhone());
            taskGetRegIdAndSendCode();
            onSendPhoneCode();
        } else {
            onPhoneNumberValidatorFailed();
            showErrors();
        }
        this.analytics.registrationRequestCode(AnalyticUtilKt.getRegFormParam(getAccountData()));
    }

    public void enforcePhone(boolean z10) {
        ConfirmationCodeSetup confirmationCodeSetup = (ConfirmationCodeSetup) getActivity();
        boolean zIsInternetRu = confirmationCodeSetup != null ? confirmationCodeSetup.isInternetRu() : false;
        boolean zIsInternetRuSecurityEnabled = confirmationCodeSetup != null ? confirmationCodeSetup.isInternetRuSecurityEnabled() : false;
        if (z10 || ((zIsInternetRu && zIsInternetRuSecurityEnabled) || isChildRegFlow())) {
            this.mGoToQuestion.setVisibility(8);
        } else {
            this.mGoToQuestion.setVisibility(0);
        }
    }

    protected Authenticator.Type getAccountType() {
        return Authenticator.Type.DEFAULT;
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment
    public String getAction() {
        return ACTION;
    }

    protected TableRow getCallInView(View view) {
        return (TableRow) view.findViewById(R.id.callin_reg_layout);
    }

    protected RegViewInterface getCaptchaRegView(View view) {
        return (RegViewInterface) view.findViewById(R.id.captcha_code_layout);
    }

    protected RegViewInterface getCodeRegView(View view) {
        return (RegViewInterface) view.findViewById(R.id.reg_code);
    }

    @LayoutRes
    protected int getConfirmationCodeLayout() {
        return R.layout.reg_confirm_phone;
    }

    protected String getEnteredCode() {
        return this.mEnteredCode;
    }

    protected String getPhoneCode() {
        return this.mCodeEditText.getText().toString();
    }

    protected RegViewInterface getPhoneRegView(View view) {
        return (RegViewInterface) view.findViewById(R.id.reg_phone);
    }

    public SignupConfirmDelegate getSignupConfirmDelegate() {
        return this.mSignupConfirmDelegate;
    }

    public SignUpDelegate getSignupDelegate() {
        return this.mSignupDelegate;
    }

    public boolean isCodeError(ArrayList<ErrorValue> arrayList) {
        Iterator<ErrorValue> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().getKey().equalsIgnoreCase(ATTR_TOKEN_VALUE)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPhoneError(ArrayList<ErrorValue> arrayList) {
        Iterator<ErrorValue> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().getKey().equalsIgnoreCase("phones[0].phone")) {
                return true;
            }
        }
        return false;
    }

    public boolean isPhoneNumberNotRequiredForRegistration() {
        ConfirmationCodeSetup confirmationCodeSetup = (ConfirmationCodeSetup) getActivity();
        return (((confirmationCodeSetup != null ? confirmationCodeSetup.isInternetRu() : false) && (confirmationCodeSetup != null ? confirmationCodeSetup.isInternetRuSecurityEnabled() : false)) || isChildRegFlow()) ? false : true;
    }

    public void loadCaptcha() {
        View view = getView();
        if (view != null) {
            LoadCaptchaDelegate loadCaptchaDelegate = new LoadCaptchaDelegate(requireActivity(), this, (ImageView) view.findViewById(R.id.captcha_image), (ProgressBar) view.findViewById(R.id.captcha_progress), (ImageButton) view.findViewById(R.id.captcha_refresh_button));
            this.mLoadCaptchaDelegate = loadCaptchaDelegate;
            loadCaptchaDelegate.loadCaptcha();
            this.analytics.registrationLoadCaptcha(AnalyticUtilKt.getRegFormParam(getAccountData()));
        }
    }

    @Override // ru.mail.registration.ui.LoadCaptchaDelegate.LoadCaptchaCallback
    public void loadCaptchaFail() {
        Toast.makeText(getActivity(), getString(R.string.authenticator_network_error), 1).show();
        this.analytics.registrationLoadCaptchaError(AnalyticUtilKt.getRegFormParam(getAccountData()), getAccountData().getEmail());
    }

    @Override // ru.mail.registration.ui.LoadCaptchaDelegate.LoadCaptchaCallback
    public void loadCaptchaSuccess(LoadCaptchaDelegate.CaptchaResult captchaResult) {
        this.mMrcuCookie = captchaResult.getCookie();
        this.analytics.registrationLoadCaptchaSuccess(AnalyticUtilKt.getRegFormParam(getAccountData()), getAccountData().getEmail());
    }

    protected boolean needToRedrawUICallIn(String str) {
        if (this.mCallInNumber.getVisibility() != 0) {
            return true;
        }
        return !CallInUtils.getCallInFormatPhone(str, 0).equals(this.mCallInNumber.getText().toString());
    }

    @Override // ru.mail.registration.ui.SignupConfirmDelegate.ConfirmResultReceiver
    public void onAccountRegistered(SignupConfirmDelegate.RegistrationResult registrationResult) {
        if (getConfirmationReceiver() != null) {
            this.analytics.registrationSuccess(AnalyticUtilKt.getRegFormParam(getAccountData()), getAccountData().getEmail(), ((ConfirmationActivity) getActivity()).isMigrated(), ((ConfirmationActivity) getActivity()).getMigrationFrom());
            ConfirmationCodeSetup confirmationCodeSetup = (ConfirmationCodeSetup) getActivity();
            if (confirmationCodeSetup != null && confirmationCodeSetup.isInternetRu()) {
                if (confirmationCodeSetup.isInternetRuFromDeeplink()) {
                    this.analytics.registrationInternetRuFromDeeplink();
                } else {
                    this.analytics.registrationInternetRu();
                }
            }
            onAccountRegistered(registrationResult, getAccountData(), TextUtils.isEmpty(getAccountData().getCode()) ? RegFlowAnalytics.PHONE : RegFlowAnalytics.PHONE_RECAPTCHA);
        }
    }

    @Override // ru.mail.registration.ui.Hilt_ConfirmationCodeFragment, ru.mail.registration.ui.BaseAccountDataFragment, androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // ru.mail.registration.ui.BaseAccountDataFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(getConfirmationCodeLayout(), viewGroup, false);
        this.mViewPhone = getPhoneRegView(viewInflate);
        this.mViewCode = getCodeRegView(viewInflate);
        this.mViewCaptcha = getCaptchaRegView(viewInflate);
        this.mCallInView = getCallInView(viewInflate);
        this.mCallInNumber = (TextView) viewInflate.findViewById(R.id.number);
        this.mCallInTitle = (TextView) viewInflate.findViewById(R.id.title_msg);
        PhoneEditor phoneEditor = (PhoneEditor) viewInflate.findViewById(R.id.phone);
        this.mPhoneEditor = phoneEditor;
        phoneEditor.addTextChangedListener(this.mTextWatcher);
        this.mCodeText = (TextView) viewInflate.findViewById(R.id.hint_text);
        RegCheckAutoCompleteTextView regCheckAutoCompleteTextView = (RegCheckAutoCompleteTextView) viewInflate.findViewById(R.id.code);
        this.mCodeEditText = regCheckAutoCompleteTextView;
        regCheckAutoCompleteTextView.addTextChangedListener(this.mTextWatcher);
        this.mCaptchaEditText = (RegCheckEditText) viewInflate.findViewById(R.id.captcha_code);
        this.mNext = (Button) viewInflate.findViewById(R.id.confirm_next);
        TextView textView = (TextView) viewInflate.findViewById(R.id.confirmation_switch_to_question);
        this.mGoToQuestion = textView;
        ClickUtilsKt.setDebouncedListener(textView, 300L, new Function1() { // from class: ru.mail.registration.ui.q
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f96659a.lambda$onCreateView$4((View) obj);
            }
        });
        initUIValues(viewInflate);
        this.analytics.registrationView(AnalyticUtilKt.getRegFormParam(getAccountData()));
        enforcePhone(false);
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

    @Override // ru.mail.registration.ui.RegQuestionChooser.PhoneRequiredReceiver
    public void onPhoneRequired(List<ErrorValue> list) {
        if (isAdded()) {
            stopProgress();
            removeErrors();
            addError(getString(R.string.reg_err_should_add_phone));
            showErrors();
            enforcePhone(true);
        }
    }

    @Override // ru.mail.registration.ui.PhoneTextLengthChanged
    public void onPhoneValidationChanged(boolean z10) {
        this.mNext.setEnabled(z10);
        this.mPhoneEditor.setOnEditorActionListener(z10 ? this.mPhoneActionListener : null);
    }

    @Override // ru.mail.registration.ui.SignupConfirmDelegate.ConfirmResultReceiver
    public void onRegistrationFail(List<ErrorValue> list) {
        if (isAdded()) {
            stopProgress();
            processErrors(list);
        }
        String regFormParam = AnalyticUtilKt.getRegFormParam(getAccountData());
        this.analytics.registrationError(regFormParam, getAccountData().getEmail());
        this.analytics.registrationError(regFormParam);
    }

    @Override // ru.mail.registration.ui.SignUpDelegate.SignupResultReceiver
    public void onSignupCancelled() {
        if (isAdded()) {
            stopProgress();
        }
    }

    @Override // ru.mail.registration.ui.SignUpDelegate.SignupResultReceiver
    public void onSignupError(List<? extends ErrorValue> list) {
        if (isAdded()) {
            stopProgress();
            processErrors(list);
        }
    }

    @Override // ru.mail.registration.ui.SignUpDelegate.SignupResultReceiver
    public void onSignupOk(String str, @Nullable String str2) {
        if (isAdded()) {
            this.mViewPhone.setError(false);
            stopProgress();
            prepareUIToShowCode();
        }
    }

    protected void onSwitchToQuestionClicked() {
        ConfirmationCodeFragment confirmationCodeFragment;
        stopProgress();
        if (NetworkUtils.hasInternetConnection(requireContext())) {
            confirmationCodeFragment = this;
            new RegQuestionChooser(requireContext(), (ConfirmationActivity) getActivity(), getAccountData(), AuthenticatorConfig.getInstance().getMigrateToPostConfig(), confirmationCodeFragment).performSignupCheck(isPhoneInputHasFinished() ? ConfirmationActivity.CaptchaQuestionAnalyticsFlow.NO_PHONE_WHILE_WAITING_SMS : ConfirmationActivity.CaptchaQuestionAnalyticsFlow.NO_PHONE, this);
        } else {
            confirmationCodeFragment = this;
            Toast.makeText(getActivity(), R.string.registration_no_internet_request_code, 0).show();
        }
        confirmationCodeFragment.analytics.registrationSwitchToQuestion(AnalyticUtilKt.getRegFormParam(getAccountData()));
    }

    @Override // ru.mail.registration.ui.ValueChecker.CheckResultListener
    public void onValueValidationError(String str) {
        addError(str);
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getAccountData().getPhone() != null) {
            this.mPhoneEditor.setText(PhoneUtils.withPlusAtStart(getAccountData().getPhone()));
            PhoneEditor phoneEditor = this.mPhoneEditor;
            phoneEditor.setSelection(phoneEditor.getText().length());
        }
        initFocus();
        this.mSignupDelegate = createSignUpDelegate();
        this.mSignupConfirmDelegate = new SignupConfirmDelegateImpl(this, requireActivity(), AuthenticatorConfig.getInstance().getMigrateToPostConfig(), getXmailFromParam());
    }

    protected void prepareUIToCallIn(final String str, boolean z10, long j10, int i10, long j11, final String str2) {
        this.analytics.registrationOpenConfirmationCallIn();
        View view = getView();
        if (view != null) {
            view.findViewById(R.id.reg_errors).setEnabled(false);
            view.findViewById(R.id.reg_phone).setVisibility(8);
        }
        this.mCodeEditText.setVisibility(8);
        this.mCodeText.setVisibility(8);
        setCallInLayoutVisibility(0);
        this.mCallInTitle.setText(requireContext().getString(R.string.reg_confirm_callin_title, CallInUtils.getCallInFormatPhone(this.mPhoneEditor.getPhone(), 4)));
        this.mCallInNumber.setText(CallInUtils.getCallInFormatPhone(str, 0));
        this.mNext.setText(getResources().getString(R.string.reg_confirm_callin));
        ClickUtilsKt.setDebouncedListener(this.mNext, 300L, new Function1() { // from class: ru.mail.registration.ui.k
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f96651a.lambda$prepareUIToCallIn$10(str, (View) obj);
            }
        });
        this.mGoToQuestion.setVisibility(8);
        if (z10 && isPhoneNumberNotRequiredForRegistration()) {
            long showSecondBtnTimeout = CallInUtils.getShowSecondBtnTimeout(j10, i10, j11 * 1000);
            LOG.d("Timeout for show btn (ms) " + showSecondBtnTimeout);
            ViewUtilsKt.postDelayedSafe(this.mGoToQuestion, new Runnable() { // from class: ru.mail.registration.ui.l
                @Override // java.lang.Runnable
                public final void run() {
                    this.f96653a.lambda$prepareUIToCallIn$11(str2);
                }
            }, showSecondBtnTimeout);
        }
    }

    protected void prepareUIToShowCallUI(String str, boolean z10, String str2) {
        String string = getString(R.string.reg_phone_callui_description, str);
        if (string.equals(this.mCodeText.getText().toString())) {
            return;
        }
        setCallInLayoutVisibility(8);
        this.analytics.registrationOpenConfirmationCallUI();
        this.mNext.setText(getResources().getString(R.string.reg_confirm_ok));
        View view = getView();
        if (view != null) {
            view.findViewById(R.id.reg_errors).setEnabled(false);
            view.findViewById(R.id.reg_phone).setVisibility(0);
        }
        this.mPhoneEditor.setOnEditorActionListener(null);
        this.mPhoneEditor.setEnabled(false);
        this.mCodeEditText.setVisibility(0);
        this.mCodeText.setVisibility(0);
        setPhoneCodeLayoutVisibility(0);
        this.mCodeText.setText(string);
        this.mCodeEditText.requestFocus();
        ClickUtilsKt.setDebouncedListener(this.mNext, 300L, new Function1() { // from class: ru.mail.registration.ui.j
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f96650a.lambda$prepareUIToShowCallUI$9((View) obj);
            }
        });
        this.mCodeEditText.setOnEditorActionListener(this.mCodeActionListener);
        if (!z10 || !isPhoneNumberNotRequiredForRegistration()) {
            this.mGoToQuestion.setVisibility(8);
            return;
        }
        this.mGoToQuestion.setText(getResources().getString(R.string.reg_confirm_ets));
        this.mGoToQuestion.setVisibility(0);
        this.analytics.registrationWithCallShowSecondBtn(str2, true);
        initGoToQuestionBtn(str2);
    }

    protected void prepareUIToShowCode() {
        setCallInLayoutVisibility(8);
        this.mNext.setText(getResources().getString(R.string.reg_confirm_ok));
        View view = getView();
        if (view != null) {
            view.findViewById(R.id.reg_errors).setEnabled(false);
            view.findViewById(R.id.reg_phone).setVisibility(0);
        }
        this.mPhoneEditor.setOnEditorActionListener(null);
        this.mPhoneEditor.setEnabled(false);
        this.mCodeEditText.setVisibility(0);
        this.mCodeText.setVisibility(0);
        setPhoneCodeLayoutVisibility(0);
        this.mCodeText.setText(R.string.reg_phone_send_description);
        this.mCodeEditText.requestFocus();
        ViewUtilsKt.postDelayedSafe(this.mCodeEditText, new Runnable() { // from class: ru.mail.registration.ui.r
            @Override // java.lang.Runnable
            public final void run() {
                this.f96660a.lambda$prepareUIToShowCode$6();
            }
        }, 100L);
        ClickUtilsKt.setDebouncedListener(this.mNext, 300L, new Function1() { // from class: ru.mail.registration.ui.g
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f96646a.lambda$prepareUIToShowCode$7((View) obj);
            }
        });
        this.mCodeEditText.setOnEditorActionListener(this.mCodeActionListener);
        if (!isPhoneNumberNotRequiredForRegistration()) {
            this.mGoToQuestion.setVisibility(8);
        } else {
            this.mGoToQuestion.setText(getResources().getString(R.string.reg_confirm_no_code));
            ClickUtilsKt.setDebouncedListener(this.mGoToQuestion, 300L, new Function1() { // from class: ru.mail.registration.ui.h
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f96647a.lambda$prepareUIToShowCode$8((View) obj);
                }
            });
        }
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment
    protected void removeErrors() {
        super.removeErrors();
        this.mViewPhone.setError(false);
        this.mViewCode.setError(false);
        this.mViewCaptcha.setError(false);
    }

    protected void setCallInLayoutVisibility(int i10) {
        this.mCallInView.setVisibility(i10);
    }

    protected void setCaptchaLayoutVisibility(int i10) {
        View view = getView();
        if (view != null) {
            view.findViewById(R.id.captcha_image_layout).setVisibility(i10);
            view.findViewById(R.id.captcha_code_layout).setVisibility(i10);
            view.findViewById(R.id.captcha_divider).setVisibility(i10);
        }
    }

    protected void setPhoneCodeLayoutVisibility(int i10) {
        View view = getView();
        if (view != null) {
            view.findViewById(R.id.reg_code).setVisibility(i10);
            view.findViewById(R.id.reg_code_divider_down).setVisibility(i10);
        }
    }

    @Override // ru.mail.registration.ui.ConfirmationBaseFragment
    protected void showResErrors(List<ErrorValue> list) {
        removeErrors();
        if (list.isEmpty()) {
            return;
        }
        for (int i10 = 0; i10 < list.size(); i10++) {
            StringBuilder sb2 = new StringBuilder();
            ErrorValue errorValue = list.get(i10);
            sb2.append(getString(errorValue.getErr().getErrorMsg()));
            if (errorValue.getKey().equals("phones[0].phone")) {
                if (errorValue.getErr() == ErrorStatus.INVALID || errorValue.getErr() == ErrorStatus.REQUIRED) {
                    sb2.append(StringUtils.SPACE);
                    sb2.append(getString(R.string.reg_confirm_number));
                }
                onPhoneNumberServerError();
                this.mViewPhone.setError(true);
            }
            if (errorValue.getKey().equals(ATTR_TOKEN_VALUE)) {
                sb2.append(StringUtils.SPACE);
                sb2.append(this.mViewCode.getTitleText());
                onPhoneCodeServerError();
                this.mCodeEditText.setText("");
                this.mViewCode.setError(true);
            }
            if (errorValue.getKey().equals(ATTR_TOKEN_CAPTCHA)) {
                sb2.append(StringUtils.SPACE);
                sb2.append(this.mViewCaptcha.getTitleText());
                onCaptchaServerError();
                this.mViewCaptcha.setError(true);
                this.mCodeEditText.setText("");
                loadCaptcha();
            }
            if (errorValue.getErr().getErrorMsg() == ErrorStatus.INVALID.getErrorMsg()) {
                sb2.append(StringUtils.SPACE);
                sb2.append(getString(ErrorStatus.INVALID_END.getErrorMsg()));
            }
            if (errorValue.getErr().ordinal() >= ErrorStatus.SERVERERROR.ordinal() && errorValue.getErr().ordinal() <= ErrorStatus.ACCESS_DENIED.ordinal() && errorValue.getKey() != null) {
                sb2.append(errorValue.getKey());
            }
            addError(sb2.toString());
        }
        showErrors();
    }

    protected void taskConfirmCode(String str) {
        startProgress();
        this.mSignupConfirmDelegate.onStartRegTokenConfirm(getAccountData(), str);
    }

    protected void taskGetRegIdAndSendCode() {
        startProgress();
        this.mSignupDelegate.performCodeSignup(true);
    }

    @Override // ru.mail.registration.ui.SignupConfirmDelegate.ConfirmResultReceiver
    public void onRegistrationFail(int i10) {
        if (isAdded()) {
            addError(getString(i10));
            showErrors();
        }
        String regFormParam = AnalyticUtilKt.getRegFormParam(getAccountData());
        this.analytics.registrationError(regFormParam, getAccountData().getEmail());
        this.analytics.registrationError(regFormParam);
    }

    protected void onCaptchaServerError() {
    }

    protected void onCaptchaValidatorFailed() {
    }

    protected void onPhoneCodeServerError() {
    }

    protected void onPhoneCodeValidatorFailed() {
    }

    protected void onPhoneNumberServerError() {
    }

    protected void onPhoneNumberValidatorFailed() {
    }

    protected void onSendPhoneCode() {
    }
}
