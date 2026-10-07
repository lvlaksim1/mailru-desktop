package ru.mail.registration.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import ru.mail.Authenticator.R;
import ru.mail.auth.AuthMessageCallback;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.DoregistrationParameter;
import ru.mail.auth.MailAccountConstants;
import ru.mail.auth.Message;
import ru.mail.auth.request.ExternalAccountRegistrationRequest;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.registration.request.RegisterExternalAccount;
import ru.mail.registration.validator.CaptchaValidator;
import ru.mail.registration.validator.NameValidator;
import ru.mail.registration.validator.SurnameValidator;
import ru.mail.util.log.Log;
import ru.mail.utils.ColorUtil;
import ru.mail.utils.ContextResourceProvider;
import ru.mail.utils.ResourceProvider;
import ru.mail.widget.DisableBrowserMovementMethod;
import ru.mail.widget.RegCheckAutoCompleteTextView;
import ru.mail.widget.RegCheckEditText;
import ru.mail.widget.RegErrorsViewInterface;
import ru.mail.widget.RegView;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class DoregistrationFragment extends Fragment implements LoadCaptchaDelegate.LoadCaptchaCallback, ValueChecker.CheckResultListener {
    private static final Log LOG = Log.getLog("DoregistrationFragment");
    private AuthMessageCallback mAuthCallBack;
    private RegCheckEditText mCaptchaEditText;
    private RegView mCaptchaRegView;
    private boolean mCaptchaRequired;
    private Button mDoneButton;
    private RegErrorsViewInterface mErrors;
    private RegCheckAutoCompleteTextView mFirstNameEditText;
    private RegView mFirstNameRegView;
    private RegCheckAutoCompleteTextView mLastNameEditText;
    private RegView mLastNameRegView;
    private LoadCaptchaDelegate mLoadCaptchaDelegate;
    private String mMrcuCookie;
    private boolean mOAuth;
    private String mRegistrationId;
    private View mRootView;
    private ScrollView scrollView;
    private TextView.OnEditorActionListener actionListener = new TextView.OnEditorActionListener() { // from class: ru.mail.registration.ui.DoregistrationFragment.1
        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i10, KeyEvent keyEvent) {
            if (i10 != 0 && i10 != 6 && i10 != 5) {
                return false;
            }
            DoregistrationFragment.this.onOkButtonClick();
            return true;
        }
    };
    private CompoundButton.OnCheckedChangeListener mCheckListener = new CompoundButton.OnCheckedChangeListener() { // from class: ru.mail.registration.ui.DoregistrationFragment.2
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z10) {
            DoregistrationFragment.this.mDoneButton.setEnabled(z10);
        }
    };
    private View.OnClickListener updateCaptchaListener = new View.OnClickListener() { // from class: ru.mail.registration.ui.DoregistrationFragment.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DoregistrationFragment.this.loadCaptcha();
        }
    };
    private View.OnClickListener onDoneButtonListener = new View.OnClickListener() { // from class: ru.mail.registration.ui.DoregistrationFragment.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DoregistrationFragment.this.onOkButtonClick();
        }
    };
    private TextWatcher mEditTextWatcher = new TextWatcher() { // from class: ru.mail.registration.ui.DoregistrationFragment.5
        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
            DoregistrationFragment.this.hideErrors();
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i10, int i11, int i12) {
        }
    };
    private View.OnClickListener onRefreshClickListener = new View.OnClickListener() { // from class: ru.mail.registration.ui.DoregistrationFragment.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DoregistrationFragment.this.loadCaptcha();
        }
    };

    private boolean isUserInputValid() {
        boolean z10;
        hideErrors();
        if (this.mFirstNameEditText.checkValue()) {
            this.mFirstNameRegView.setError(false);
            z10 = true;
        } else {
            this.mFirstNameRegView.setError(true);
            z10 = false;
        }
        if (this.mLastNameEditText.checkValue()) {
            this.mLastNameRegView.setError(false);
        } else {
            this.mLastNameRegView.setError(true);
            z10 = false;
        }
        if (this.mCaptchaEditText.checkValue() || !this.mCaptchaRequired) {
            this.mCaptchaRegView.setError(false);
            return z10;
        }
        this.mCaptchaRegView.setError(true);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadCaptcha() {
        LoadCaptchaDelegate loadCaptchaDelegate = new LoadCaptchaDelegate(getActivity(), this, (ImageView) this.mRootView.findViewById(R.id.captcha_image), (ProgressBar) this.mRootView.findViewById(R.id.captcha_progress), (ImageButton) this.mRootView.findViewById(R.id.captcha_refresh_button));
        this.mLoadCaptchaDelegate = loadCaptchaDelegate;
        loadCaptchaDelegate.loadCaptcha();
    }

    private void processExtras(Bundle bundle) {
        DoregistrationParameter doregistrationParameter = (DoregistrationParameter) bundle.getParcelable(MailAccountConstants.LOGIN_EXTRA_DOREGISTRATION_PARAM);
        this.mRegistrationId = doregistrationParameter.getRegId();
        boolean zIsCaptchaRequired = doregistrationParameter.isCaptchaRequired();
        this.mCaptchaRequired = zIsCaptchaRequired;
        showCaptcha(zIsCaptchaRequired ? 0 : 8);
        if (this.mCaptchaRequired) {
            ((ImageButton) this.mRootView.findViewById(R.id.captcha_refresh_button)).setOnClickListener(this.onRefreshClickListener);
            this.mCaptchaEditText.setOnEditorActionListener(this.actionListener);
            this.mLastNameEditText.setNextFocusForwardId(this.mCaptchaEditText.getId());
            Bitmap captcha = doregistrationParameter.getCaptcha();
            String cookie = doregistrationParameter.getCookie();
            this.mMrcuCookie = cookie;
            if (captcha == null || cookie == null) {
                loadCaptcha();
            } else {
                ((ImageView) this.mRootView.findViewById(R.id.captcha_image)).setImageBitmap(captcha);
            }
        } else {
            this.mLastNameEditText.setOnEditorActionListener(this.actionListener);
        }
        setFirstName(doregistrationParameter);
        setLastName(doregistrationParameter);
    }

    private void sendRequest(String str, String str2, String str3) {
        new RegisterExternalAccount(str, str2, str3, this.mRegistrationId, this.mMrcuCookie, AuthenticatorConfig.getInstance().getMigrateToPostConfig(), this).execute(new Void[0]);
    }

    private void setFirstName(DoregistrationParameter doregistrationParameter) {
        String firstName = doregistrationParameter.getFirstName();
        if (firstName != null) {
            this.mFirstNameEditText.setText(firstName);
        }
    }

    private void setLastName(DoregistrationParameter doregistrationParameter) {
        String lastName = doregistrationParameter.getLastName();
        if (lastName != null) {
            this.mLastNameEditText.setText(lastName);
        }
    }

    private void showCaptcha(int i10) {
        this.mRootView.findViewById(R.id.captcha_image_layout).setVisibility(i10);
        this.mRootView.findViewById(R.id.captcha_code_layout).setVisibility(i10);
        this.mRootView.findViewById(R.id.captcha_divider).setVisibility(i10);
    }

    protected void addError(String str) {
        this.mErrors.addError(str);
    }

    public RegCheckAutoCompleteTextView getFirstNameEditText() {
        return this.mFirstNameEditText;
    }

    public RegCheckAutoCompleteTextView getLastNameEditText() {
        return this.mLastNameEditText;
    }

    public String getMrcuCookie() {
        return this.mMrcuCookie;
    }

    protected String getUserAgreementUrl() {
        return getString(R.string.user_agreement_link);
    }

    public String getmRegistrationId() {
        return this.mRegistrationId;
    }

    protected void hideErrors() {
        this.mErrors.removeAndHide();
        this.mFirstNameRegView.setError(false);
        this.mLastNameRegView.setError(false);
        this.mCaptchaRegView.setError(false);
    }

    public boolean isCaptchaRequired() {
        return this.mCaptchaRequired;
    }

    @Override // ru.mail.registration.ui.LoadCaptchaDelegate.LoadCaptchaCallback
    public void loadCaptchaFail() {
        Toast.makeText(getActivity(), getString(R.string.authenticator_network_error), 1).show();
    }

    @Override // ru.mail.registration.ui.LoadCaptchaDelegate.LoadCaptchaCallback
    public void loadCaptchaSuccess(LoadCaptchaDelegate.CaptchaResult captchaResult) {
        this.mMrcuCookie = captchaResult.getCookie();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mAuthCallBack = (AuthMessageCallback) context;
    }

    public void onCaptchaVerifyFail(ExternalAccountRegistrationRequest externalAccountRegistrationRequest) {
        String message;
        if (this.mCaptchaRequired) {
            this.mCaptchaEditText.setText("");
            loadCaptcha();
        }
        CommandStatus<?> result = externalAccountRegistrationRequest.getResult();
        if (result instanceof NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED) {
            message = getString(R.string.authenticator_network_error);
        } else if (result instanceof AuthCommandStatus.CAPTCHA) {
            message = getString(R.string.authenticator_captcha_error);
            addError(message);
            showErrors();
        } else {
            message = result instanceof AuthCommandStatus.ERROR_WITH_STATUS_CODE ? ((AuthCommandStatus.ERROR_WITH_STATUS_CODE) result).getMessage() : getString(R.string.authenticator_error);
        }
        Toast.makeText(getActivity(), message, 0).show();
    }

    public void onCaptchaVerifyOK() {
        Bundle bundle = new Bundle(getArguments());
        bundle.putBoolean(MailAccountConstants.LOGIN_EXTRA_OAUTH, this.mOAuth);
        this.mAuthCallBack.onMessageHandle(new Message(Message.Id.AUTHENTICATE, bundle));
        getFragmentManager().popBackStack();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.doregistration, viewGroup, false);
        ResourceProvider resourceProviderFrom = ContextResourceProvider.from(getActivity());
        this.mFirstNameRegView = (RegView) this.mRootView.findViewById(R.id.first_name_regview);
        RegCheckAutoCompleteTextView regCheckAutoCompleteTextView = (RegCheckAutoCompleteTextView) this.mRootView.findViewById(R.id.first_name);
        this.mFirstNameEditText = regCheckAutoCompleteTextView;
        regCheckAutoCompleteTextView.addTextChangedListener(this.mEditTextWatcher);
        this.mFirstNameEditText.setValueChecker(new ValueChecker<>(new NameValidator(resourceProviderFrom), this));
        this.mLastNameRegView = (RegView) this.mRootView.findViewById(R.id.last_name_regview);
        RegCheckAutoCompleteTextView regCheckAutoCompleteTextView2 = (RegCheckAutoCompleteTextView) this.mRootView.findViewById(R.id.last_name);
        this.mLastNameEditText = regCheckAutoCompleteTextView2;
        regCheckAutoCompleteTextView2.addTextChangedListener(this.mEditTextWatcher);
        this.mLastNameEditText.setValueChecker(new ValueChecker<>(new SurnameValidator(resourceProviderFrom), this));
        this.mCaptchaRegView = (RegView) this.mRootView.findViewById(R.id.captcha_code_layout);
        RegCheckEditText regCheckEditText = (RegCheckEditText) this.mRootView.findViewById(R.id.captcha_code);
        this.mCaptchaEditText = regCheckEditText;
        regCheckEditText.addTextChangedListener(this.mEditTextWatcher);
        this.mCaptchaEditText.setValueChecker(new ValueChecker<>(new CaptchaValidator(resourceProviderFrom), this));
        this.mErrors = (RegErrorsViewInterface) this.mRootView.findViewById(R.id.reg_erros);
        this.mDoneButton = (Button) this.mRootView.findViewById(R.id.done);
        ((CheckBox) this.mRootView.findViewById(R.id.agreement_checkbox)).setOnCheckedChangeListener(this.mCheckListener);
        TextView textView = (TextView) this.mRootView.findViewById(R.id.agreement_text);
        textView.setText(Html.fromHtml(getResources().getString(R.string.authenticator_signup_finish_lisence_agreement_link, getUserAgreementUrl(), ColorUtil.colorStr(getThemedContext(), ru.mail.uikit.R.color.link))));
        textView.setMovementMethod(DisableBrowserMovementMethod.getInstance());
        ImageButton imageButton = (ImageButton) this.mRootView.findViewById(R.id.captcha_refresh_button);
        imageButton.setImageDrawable(getThemedContext().getDrawable(R.drawable.ic_refresh));
        imageButton.setOnClickListener(this.updateCaptchaListener);
        this.mDoneButton.setOnClickListener(this.onDoneButtonListener);
        if (getArguments() == null) {
            throw new IllegalStateException("You must put extras for this Fragment");
        }
        processExtras(getArguments());
        this.scrollView = (ScrollView) this.mRootView.findViewById(R.id.scrollView);
        return this.mRootView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LoadCaptchaDelegate loadCaptchaDelegate = this.mLoadCaptchaDelegate;
        if (loadCaptchaDelegate != null) {
            loadCaptchaDelegate.clearCallBackRef();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mAuthCallBack = null;
    }

    protected void onOkButtonClick() {
        if (!isUserInputValid()) {
            showErrors();
        } else {
            sendRequest(this.mFirstNameEditText.getText().toString(), this.mLastNameEditText.getText().toString(), this.mCaptchaEditText.getText().toString());
        }
    }

    @Override // ru.mail.registration.ui.ValueChecker.CheckResultListener
    public void onValueValidationError(String str) {
        addError(str);
    }

    protected void showErrors() {
        this.mErrors.show(false);
        this.scrollView.requestChildRectangleOnScreen((View) this.mErrors, new Rect(), false);
    }
}
