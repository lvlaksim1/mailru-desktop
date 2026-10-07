package ru.mail.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import ru.mail.auth.request.MailServerParameters;
import ru.mail.auth.request.MailServerParametersRequest;
import ru.mail.auth.util.DomainUtils;
import ru.mail.mailbox.cmd.ProgressObservable;
import ru.mail.registration.ui.LoadCaptchaDelegate;
import ru.mail.registration.ui.UnknownDomainRequestErrors;
import ru.mail.uikit.utils.TouchAreaUtil;
import ru.mail.util.log.Log;
import ru.mail.widget.DefaultValueEditText;
import ru.mail.widget.RegView;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@AndroidEntryPoint
public abstract class LoginScreenFragment extends Hilt_LoginScreenFragment implements LoadCaptchaDelegate.LoadCaptchaCallback {
    private static final Log LOG = Log.getLog("LoginScreenFragment");
    public static final String MAIL_SERVER_SETTINGS_KEY = "mail_server_settings_key";
    private static final int MAX_VALID_PORT = 65536;
    private static final int MIN_VALID_PORT = 0;

    @Inject
    Analytics analytics;
    private boolean mAuthCodePlateClosed;
    private View mAuthCodeView;
    private EditText mCaptchaEditText;
    private TextView mDomainSettingsErrorView;
    private View mDomainSettingsView;
    private DefaultValueEditText mIncomingHostView;
    private DefaultValueEditText mIncomingPortView;
    private RadioGroup mIncomingSslView;
    private LoadCaptchaDelegate mLoadCaptchaDelegate;
    private DefaultValueEditText mOutgoingHostView;
    private DefaultValueEditText mOutgoingPortView;
    private RadioGroup mOutgoingSslView;
    private RadioGroup mProtocolView;
    private boolean mSendMailServerSettingsSuccess;
    private MailServerParameters mailServerParameters;
    private final View.OnClickListener mRefreshCaptchaListener = new View.OnClickListener() { // from class: ru.mail.auth.h2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f80756a.lambda$new$0(view);
        }
    };
    private final RadioGroup.OnCheckedChangeListener mDomainRadioButtonsListener = new RadioGroup.OnCheckedChangeListener() { // from class: ru.mail.auth.i2
        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public final void onCheckedChanged(RadioGroup radioGroup, int i10) {
            this.f80759a.lambda$new$1(radioGroup, i10);
        }
    };

    /* JADX INFO: renamed from: ru.mail.auth.LoginScreenFragment$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ru$mail$auth$request$MailServerParametersRequest$InvalidFieldName;

        static {
            int[] iArr = new int[MailServerParametersRequest.InvalidFieldName.values().length];
            $SwitchMap$ru$mail$auth$request$MailServerParametersRequest$InvalidFieldName = iArr;
            try {
                iArr[MailServerParametersRequest.InvalidFieldName.COLLECT_SERVER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ru$mail$auth$request$MailServerParametersRequest$InvalidFieldName[MailServerParametersRequest.InvalidFieldName.COLLECT_PORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ru$mail$auth$request$MailServerParametersRequest$InvalidFieldName[MailServerParametersRequest.InvalidFieldName.COLLECT_SSL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$ru$mail$auth$request$MailServerParametersRequest$InvalidFieldName[MailServerParametersRequest.InvalidFieldName.SMTP_SERVER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$ru$mail$auth$request$MailServerParametersRequest$InvalidFieldName[MailServerParametersRequest.InvalidFieldName.SMTP_PORT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$ru$mail$auth$request$MailServerParametersRequest$InvalidFieldName[MailServerParametersRequest.InvalidFieldName.SMTP_SSL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$ru$mail$auth$request$MailServerParametersRequest$InvalidFieldName[MailServerParametersRequest.InvalidFieldName.CODE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private enum Protocol {
        IMAP(993, 143, "imap.", ru.mail.Authenticator.R.id.protocol_imap, MailServerParameters.INCOMING_SERVER_TYPE.IMAP),
        POP3(995, 110, "pop.", ru.mail.Authenticator.R.id.protocol_pop, MailServerParameters.INCOMING_SERVER_TYPE.POP3),
        ACTYVE_SYNC(443, 0, "", ru.mail.Authenticator.R.id.protocol_active_sync, MailServerParameters.INCOMING_SERVER_TYPE.EXCHANGE),
        SMTP(465, 25, "smtp.", 0, null);

        private final MailServerParameters.INCOMING_SERVER_TYPE mEmailServiceType;
        private final String mHostPrefix;
        private final int mNoSslPort;
        private final int mRadionButtonId;
        private final int mSslPort;

        Protocol(int i10, int i11, String str, int i12, MailServerParameters.INCOMING_SERVER_TYPE incoming_server_type) {
            this.mSslPort = i10;
            this.mNoSslPort = i11;
            this.mHostPrefix = str;
            this.mRadionButtonId = i12;
            this.mEmailServiceType = incoming_server_type;
        }

        public static Protocol getByRadioButton(int i10) {
            for (Protocol protocol : values()) {
                if (protocol.mRadionButtonId == i10) {
                    return protocol;
                }
            }
            return null;
        }

        public String getDefaultHost(String str) {
            String domain;
            try {
                domain = DomainUtils.getDomain(str);
            } catch (IllegalArgumentException e10) {
                LoginScreenFragment.LOG.d(e10.getMessage());
                domain = null;
            }
            if (TextUtils.isEmpty(domain)) {
                return "";
            }
            return getDefaultHostPrefix() + domain;
        }

        public String getDefaultHostPrefix() {
            return this.mHostPrefix;
        }

        public int getDefaultPort(boolean z10) {
            return z10 ? this.mSslPort : this.mNoSslPort;
        }

        public MailServerParameters.INCOMING_SERVER_TYPE getMailServerParametersType() {
            return this.mEmailServiceType;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private class UIAuthVisitor extends BaseMessageVisitor {
        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void onNeedSendServerSettings(Message message) {
            LoginScreenFragment.this.onNeedSendMailServerSettings(message.getData().getBoolean(MailLoginFragment.EXTRA_REQUEST_CAPTCHA));
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void onSendServerSettingsFail(Message message) {
            LoginScreenFragment.this.onSendMailServerSettingsFail(message.getData().getInt(MailLoginFragment.EXTRA_ERROR_CODE), message.getData().getString(MailLoginFragment.EXTRA_ERROR_MESSAGE), (List) message.getObj());
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void onSendServerSettingsStarted(Message message) {
            LoginScreenFragment.this.showAuthProgress((ProgressObservable) message.getObj());
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void onSendServerSettingsSuccess(Message message) {
            LoginScreenFragment.this.onSendMailServerSettingsSuccess();
        }

        private UIAuthVisitor() {
        }
    }

    private void clearDomainSettingsErrors() {
        setDomainFieldsError(Arrays.asList(MailServerParametersRequest.InvalidFieldName.values()), false);
    }

    private void expandsRadioButtonsTouchArea() {
        float dimension = (int) getResources().getDimension(ru.mail.Authenticator.R.dimen.domain_settings_radio_button_right_margin);
        TouchAreaUtil.expandTouchArea(this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.protocol_pop), dimension);
        TouchAreaUtil.expandTouchArea(this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.protocol_imap), dimension);
        TouchAreaUtil.expandTouchArea(this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.incoming_ssl_on), dimension);
        TouchAreaUtil.expandTouchArea(this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.incoming_ssl_off), dimension);
        TouchAreaUtil.expandTouchArea(this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.outgoing_ssl_on), dimension);
        TouchAreaUtil.expandTouchArea(this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.outgoing_ssl_off), dimension);
    }

    private int getDomainSettingsTopInScrollView() {
        View view = this.mDomainSettingsView;
        View view2 = (View) view.getParent();
        int top = 0;
        while (true) {
            View view3 = view2;
            View view4 = view;
            view = view3;
            if (view.getId() == ru.mail.Authenticator.R.id.scroll_view) {
                return top;
            }
            top += view4.getTop();
            view2 = (View) view.getParent();
        }
    }

    private RegView getFieldLayout(MailServerParametersRequest.InvalidFieldName invalidFieldName) {
        switch (AnonymousClass1.$SwitchMap$ru$mail$auth$request$MailServerParametersRequest$InvalidFieldName[invalidFieldName.ordinal()]) {
            case 1:
                return (RegView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.incoming_host_layout);
            case 2:
                return (RegView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.incoming_port_layout);
            case 3:
                return (RegView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.incoming_ssl_layout);
            case 4:
                return (RegView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.outgoing_host_layout);
            case 5:
                return (RegView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.outgoing_port_layout);
            case 6:
                return (RegView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.outgoing_ssl_layout);
            case 7:
                return (RegView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_code_layout);
            default:
                return null;
        }
    }

    private String getIncomingHost() {
        return this.mIncomingHostView.getText().toString();
    }

    private int getIncomingPort() {
        try {
            return Integer.parseInt(this.mIncomingPortView.getText().toString());
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private List<MailServerParametersRequest.InvalidFieldName> getInvalidMailServerParameters() {
        ArrayList arrayList = new ArrayList();
        if (!isPortValid(getIncomingPort())) {
            arrayList.add(MailServerParametersRequest.InvalidFieldName.COLLECT_PORT);
        }
        if (!isPortValid(getOutgoingPort())) {
            arrayList.add(MailServerParametersRequest.InvalidFieldName.SMTP_PORT);
        }
        if (TextUtils.isEmpty(getIncomingHost())) {
            arrayList.add(MailServerParametersRequest.InvalidFieldName.COLLECT_SERVER);
        }
        if (TextUtils.isEmpty(getOutgoingHost())) {
            arrayList.add(MailServerParametersRequest.InvalidFieldName.SMTP_SERVER);
        }
        if (needCaptcha() && !isCaptchaValid(getCaptchaCode())) {
            arrayList.add(MailServerParametersRequest.InvalidFieldName.CODE);
        }
        return arrayList;
    }

    private String getOutgoingHost() {
        return this.mOutgoingHostView.getText().toString();
    }

    private int getOutgoingPort() {
        try {
            return Integer.parseInt(this.mOutgoingPortView.getText().toString());
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    private MailServerParameters.INCOMING_SERVER_TYPE getProtocol() {
        return Protocol.getByRadioButton(this.mProtocolView.getCheckedRadioButtonId()).getMailServerParametersType();
    }

    private void hideDomainSettingsError() {
        this.mDomainSettingsErrorView.setVisibility(8);
    }

    private boolean incomingSsl() {
        return this.mIncomingSslView.getCheckedRadioButtonId() == ru.mail.Authenticator.R.id.incoming_ssl_on;
    }

    private void initCaptchaViews() {
        this.mCaptchaEditText = (EditText) this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_code);
        ImageButton imageButton = (ImageButton) this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_refresh_button);
        imageButton.setImageDrawable(getResworbkvmocaf().getDrawable(ru.mail.Authenticator.R.drawable.ic_refresh));
        imageButton.setOnClickListener(this.mRefreshCaptchaListener);
    }

    private void initCodeAuthPlate() {
        this.mAuthCodeView = this.mRootView.findViewById(ru.mail.Authenticator.R.id.code_auth_plate);
        ((ImageView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.close_plate_btn)).setOnClickListener(new View.OnClickListener() { // from class: ru.mail.auth.j2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f80762a.lambda$initCodeAuthPlate$2(view);
            }
        });
        ((TextView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.btn_send_code)).setOnClickListener(new View.OnClickListener() { // from class: ru.mail.auth.k2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f80764a.lambda$initCodeAuthPlate$3(view);
            }
        });
    }

    private void initDomainSettingsViews() {
        View viewFindViewById = this.mRootView.findViewById(ru.mail.Authenticator.R.id.domain_settings);
        this.mDomainSettingsView = viewFindViewById;
        this.mDomainSettingsErrorView = (TextView) viewFindViewById.findViewById(ru.mail.Authenticator.R.id.domain_settings_error);
        RadioGroup radioGroup = (RadioGroup) this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.protocol);
        this.mProtocolView = radioGroup;
        radioGroup.setOnCheckedChangeListener(this.mDomainRadioButtonsListener);
        this.mIncomingHostView = (DefaultValueEditText) this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.incoming_host);
        this.mIncomingPortView = (DefaultValueEditText) this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.incoming_port);
        RadioGroup radioGroup2 = (RadioGroup) this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.incoming_ssl);
        this.mIncomingSslView = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(this.mDomainRadioButtonsListener);
        this.mOutgoingHostView = (DefaultValueEditText) this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.outgoing_host);
        this.mOutgoingPortView = (DefaultValueEditText) this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.outgoing_port);
        RadioGroup radioGroup3 = (RadioGroup) this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.outgoing_ssl);
        this.mOutgoingSslView = radioGroup3;
        radioGroup3.setOnCheckedChangeListener(this.mDomainRadioButtonsListener);
        expandsRadioButtonsTouchArea();
    }

    private boolean isCaptchaValid(String str) {
        return !TextUtils.isEmpty(str);
    }

    private boolean isPortValid(int i10) {
        return i10 > 0 && i10 < 65536;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCodeAuthPlate$2(View view) {
        this.mAuthCodePlateClosed = true;
        hideAuthCodePlate();
        this.analytics.oneTimeCodeClose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initCodeAuthPlate$3(View view) {
        startCodeAuth(getLastFailedLogin());
        this.analytics.oneTimeCodeClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        startLoadCaptcha();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(RadioGroup radioGroup, int i10) {
        updateDefaultDomainSettings();
    }

    private boolean needCaptcha() {
        return this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_code_layout).getVisibility() == 0;
    }

    private boolean outgoingSsl() {
        return this.mOutgoingSslView.getCheckedRadioButtonId() == ru.mail.Authenticator.R.id.outgoing_ssl_on;
    }

    private void scrollToDomainSettingsTop() {
        ((ScrollView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.scroll_view)).smoothScrollTo(0, getDomainSettingsTopInScrollView());
    }

    private void sendMailServerSettings() {
        hideDomainSettingsError();
        clearDomainSettingsErrors();
        List<MailServerParametersRequest.InvalidFieldName> invalidMailServerParameters = getInvalidMailServerParameters();
        if (!invalidMailServerParameters.isEmpty()) {
            setDomainFieldsError(invalidMailServerParameters, true);
            showDomainSettingsError(getString(ru.mail.Authenticator.R.string.error_code_unknow_domain_400));
        } else {
            if (this.mailServerParameters == null) {
                throw new IllegalStateException("mailServerParameters == null");
            }
            setServerParametersFromViews();
            getAuthCallBack().onMessageHandle(new Message(Message.Id.START_SEND_SERVER_SETTINGS, null, this.mailServerParameters));
        }
    }

    private void setDomainFieldsError(List<MailServerParametersRequest.InvalidFieldName> list, boolean z10) {
        Iterator<MailServerParametersRequest.InvalidFieldName> it = list.iterator();
        while (it.hasNext()) {
            RegView fieldLayout = getFieldLayout(it.next());
            if (fieldLayout != null) {
                fieldLayout.setError(z10);
            }
        }
    }

    protected static void setEmailServiceLogoImageView(View view, int i10) {
        ImageView imageView = (ImageView) view.findViewById(ru.mail.Authenticator.R.id.email_service_logo_imageview);
        if (imageView == null) {
            return;
        }
        imageView.setImageResource(i10);
        imageView.setTag(Integer.valueOf(i10));
        View viewFindViewById = view.findViewById(ru.mail.Authenticator.R.id.login_title_text_layout);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
        View viewFindViewById2 = view.findViewById(ru.mail.Authenticator.R.id.login_title_image_layout);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(0);
        }
    }

    private void setProtocolControls(boolean z10) {
        int[] iArr = {ru.mail.Authenticator.R.id.incoming_port_layout, ru.mail.Authenticator.R.id.divider3, ru.mail.Authenticator.R.id.incoming_server, ru.mail.Authenticator.R.id.outgoing_server, ru.mail.Authenticator.R.id.divider6, ru.mail.Authenticator.R.id.outgoing_host_layout, ru.mail.Authenticator.R.id.divider7, ru.mail.Authenticator.R.id.outgoing_port_layout, ru.mail.Authenticator.R.id.divider8, ru.mail.Authenticator.R.id.outgoing_ssl_layout};
        int[] iArr2 = {ru.mail.Authenticator.R.id.active_sync_login, ru.mail.Authenticator.R.id.divider2};
        int i10 = z10 ? 8 : 0;
        int i11 = z10 ? 0 : 8;
        View viewFindViewById = this.mRootView.findViewById(ru.mail.Authenticator.R.id.active_sync_login_divider);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(i11);
        }
        for (int i12 = 0; i12 < 10; i12++) {
            this.mDomainSettingsView.findViewById(iArr[i12]).setVisibility(i10);
        }
        for (int i13 = 0; i13 < 2; i13++) {
            this.mRootView.findViewById(iArr2[i13]).setVisibility(i11);
        }
        ((RegView) this.mDomainSettingsView.findViewById(ru.mail.Authenticator.R.id.incoming_host_layout)).setTitleText(getString(z10 ? ru.mail.Authenticator.R.string.server : ru.mail.Authenticator.R.string.host));
        if (getResources().getBoolean(ru.mail.Authenticator.R.bool.show_right_login_drawable_for_exchange)) {
            ((EditText) this.mRootView.findViewById(ru.mail.Authenticator.R.id.login)).setCompoundDrawablesWithIntrinsicBounds(ru.mail.Authenticator.R.drawable.ic_login_name, 0, z10 ? ru.mail.Authenticator.R.drawable.mail_app_domain_select : 0, 0);
        }
    }

    private void setServerParametersFromViews() {
        this.mailServerParameters.setEmail(this.mLogin);
        this.mailServerParameters.setPassword(this.mPassword);
        this.mailServerParameters.setIncomingServerType(getProtocol());
        if (getProtocol().equals(MailServerParameters.INCOMING_SERVER_TYPE.EXCHANGE)) {
            String strTrim = ((EditText) this.mRootView.findViewById(ru.mail.Authenticator.R.id.active_sync_login)).getText().toString().toLowerCase().trim();
            MailServerParameters mailServerParameters = this.mailServerParameters;
            if (strTrim.length() == 0) {
                strTrim = this.mLogin;
            }
            mailServerParameters.setLogin(strTrim);
        }
        this.mailServerParameters.setIncomingServerHost(getIncomingHost());
        this.mailServerParameters.setIncomingServerPort(getIncomingPort());
        this.mailServerParameters.setIncomingServerUseSsl(incomingSsl());
        this.mailServerParameters.setOutgoingServerHost(getOutgoingHost());
        this.mailServerParameters.setOutgoingServerPort(getOutgoingPort());
        this.mailServerParameters.setOutgoingServerUseSsl(outgoingSsl());
        this.mailServerParameters.setCaptchaCode(needCaptcha() ? getCaptchaCode() : null);
    }

    private void setShowDomainSettingsFromParams() {
        Bundle bundle = getArguments().getBundle(Authenticator.VALUE_NEED_SEND_SERVER_PARAMS);
        if (bundle != null) {
            String string = getArguments().getString(Authenticator.BUNDLE_PARAM_PASSWORD);
            this.mPassword = string;
            this.mPasswordView.setText(string);
            this.mLogin = getArguments().getString(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN);
            onNeedSendMailServerSettings(bundle.getBoolean(MailLoginFragment.EXTRA_REQUEST_CAPTCHA));
        }
    }

    private void showDomainSettingsError(String str) {
        notifyActivityOnError();
        this.mDomainSettingsErrorView.setVisibility(0);
        this.mDomainSettingsErrorView.setText(str);
        scrollToDomainSettingsTop();
    }

    private void showProtocols(List<Integer> list) {
        int childCount = this.mProtocolView.getChildCount();
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = this.mProtocolView.getChildAt(i10);
            if (list.contains(Integer.valueOf(childAt.getId()))) {
                childAt.setVisibility(0);
            } else {
                childAt.setVisibility(8);
            }
        }
    }

    private void startLoadCaptcha() {
        LoadCaptchaDelegate loadCaptchaDelegate = new LoadCaptchaDelegate(getActivity(), this, (ImageView) this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_image), (ProgressBar) this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_progress), (ImageButton) this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_refresh_button));
        this.mLoadCaptchaDelegate = loadCaptchaDelegate;
        loadCaptchaDelegate.loadCaptcha();
    }

    private void updateDefaultDomainSettings() {
        Protocol byRadioButton = Protocol.getByRadioButton(this.mProtocolView.getCheckedRadioButtonId());
        Protocol protocol = Protocol.ACTYVE_SYNC;
        setProtocolControls(byRadioButton.equals(protocol));
        boolean z10 = this.mIncomingSslView.getCheckedRadioButtonId() == ru.mail.Authenticator.R.id.incoming_ssl_on;
        this.mIncomingHostView.setDefaultValue(byRadioButton.getDefaultHost(getLogin()));
        this.mIncomingPortView.setDefaultValue(String.valueOf(byRadioButton.getDefaultPort(z10)));
        if (byRadioButton.equals(protocol)) {
            return;
        }
        boolean z11 = this.mOutgoingSslView.getCheckedRadioButtonId() == ru.mail.Authenticator.R.id.outgoing_ssl_on;
        DefaultValueEditText defaultValueEditText = this.mOutgoingHostView;
        Protocol protocol2 = Protocol.SMTP;
        defaultValueEditText.setDefaultValue(protocol2.getDefaultHost(getLogin()));
        this.mOutgoingPortView.setDefaultValue(String.valueOf(protocol2.getDefaultPort(z11)));
    }

    private void updateOnEditorActionListener() {
        EditText editText = this.mCaptchaEditText;
        if (editText != null) {
            editText.setOnEditorActionListener(null);
        }
        DefaultValueEditText defaultValueEditText = this.mOutgoingPortView;
        if (defaultValueEditText != null) {
            defaultValueEditText.setOnEditorActionListener(null);
        }
        EditText editText2 = this.mPasswordView;
        if (editText2 != null) {
            editText2.setOnEditorActionListener(null);
        }
        EditText editText3 = this.mCaptchaEditText;
        if (editText3 != null && editText3.getVisibility() == 0) {
            this.mCaptchaEditText.setOnEditorActionListener(this.mOnEditorActionListener);
            return;
        }
        DefaultValueEditText defaultValueEditText2 = this.mOutgoingPortView;
        if (defaultValueEditText2 != null && defaultValueEditText2.getVisibility() == 0) {
            this.mOutgoingPortView.setOnEditorActionListener(this.mOnEditorActionListener);
            return;
        }
        EditText editText4 = this.mPasswordView;
        if (editText4 == null || editText4.getVisibility() != 0) {
            return;
        }
        this.mPasswordView.setOnEditorActionListener(this.mOnEditorActionListener);
    }

    @Override // ru.mail.auth.BaseAuthFragment
    protected void authProgressDialogCancelled() {
        this.mPasswordView.setText("");
    }

    protected boolean canShowAuthCodePlate() {
        return !this.mAuthCodePlateClosed;
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment
    protected Message.Visitor createMessageVisitor() {
        return new UIAuthVisitor();
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment
    protected int getAuthorizationLayoutId() {
        return ru.mail.Authenticator.R.layout.authorization;
    }

    public String getCaptchaCode() {
        return this.mCaptchaEditText.getText().toString();
    }

    public ErrorDelegate getErrorAuthView() {
        return this.mPasswordErrorDelegate;
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment
    protected String getFromAnalytics() {
        return "one_step";
    }

    protected String getLoginExtra() {
        return this.mLoginExtra;
    }

    @DrawableRes
    protected int getLogoResId(@NonNull EmailServiceResources.MailServiceResources mailServiceResources) {
        return mailServiceResources.getLogoResourceId();
    }

    public EditText getPasswordView() {
        return this.mPasswordView;
    }

    protected void hideAuthCodePlate() {
        setAuthCodePlateVisibility(false);
    }

    protected boolean isImapOnly() {
        return false;
    }

    @Override // ru.mail.registration.ui.LoadCaptchaDelegate.LoadCaptchaCallback
    public void loadCaptchaFail() {
        showAuthErrorAsToast(getString(ru.mail.Authenticator.R.string.authenticator_network_error), true);
    }

    @Override // ru.mail.registration.ui.LoadCaptchaDelegate.LoadCaptchaCallback
    public void loadCaptchaSuccess(LoadCaptchaDelegate.CaptchaResult captchaResult) {
        MailServerParameters mailServerParameters = this.mailServerParameters;
        if (mailServerParameters != null) {
            mailServerParameters.setMrcuCookie(captchaResult.getCookie());
        }
    }

    @Override // ru.mail.auth.BaseAuthFragment
    protected void onAuthError(String str, int i10) {
        super.onAuthError(str, i10);
        this.analytics.loginScreenAuthError(getLoginAnalyticName(), i10);
    }

    @Override // ru.mail.auth.BaseAuthFragment
    protected void onAuthFailed(@Nullable Bundle bundle) {
        super.onAuthFailed(bundle);
        this.analytics.loginErrorInvalidLoginOrPassword();
    }

    @Override // ru.mail.auth.BaseAuthFragment
    protected void onAuthSucceeded(Bundle bundle) {
        super.onAuthSucceeded(bundle);
        if (this.mSendMailServerSettingsSuccess) {
            this.analytics.manualSettingsLoginSuccess(getDomain());
        }
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment, ru.mail.auth.BaseAuthFragment
    public void onBadAuth(Bundle bundle) {
        super.onBadAuth(bundle);
        checkCodeAuthAvailable();
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        EmailServiceResources.MailServiceResources emailServiceType = getEmailServiceType();
        if (emailServiceType.showLogo()) {
            setEmailServiceLogoImageView(this.mRootView, getLogoResId(emailServiceType));
        }
        initCodeAuthPlate();
        initDomainSettingsViews();
        initCaptchaViews();
        setShowDomainSettingsFromParams();
        this.analytics.showPassAuth(String.valueOf(emailServiceType));
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

    protected void onNeedSendMailServerSettings(boolean z10) {
        LOG.d("onNeedSendMailServerSettings()");
        dismissProgress();
        this.mailServerParameters = new MailServerParameters(this.mLogin, this.mPassword);
        showDomainSettingsView(true);
        if (z10) {
            showCaptchaView(true);
        }
        this.analytics.manualSettingsLoginView();
    }

    protected void onSendMailServerSettingsFail(int i10, String str, List<MailServerParametersRequest.InvalidFieldName> list) {
        LOG.d("onSendMailServerSettingsFail");
        if (i10 == 500 && "exists_domain".equals(str)) {
            onSendMailServerSettingsSuccess();
            return;
        }
        dismissProgress();
        showCaptchaView(i10 == 429);
        showDomainSettingsError(UnknownDomainRequestErrors.getErrorMessage(getActivity(), i10, str, list));
        setDomainFieldsError(list, true);
    }

    protected void onSendMailServerSettingsSuccess() {
        LOG.d("onSendMailServerSettingsSuccess()");
        if (this.mailServerParameters != null) {
            this.mailServerParameters = null;
        }
        this.mSendMailServerSettingsSuccess = true;
        showCaptchaView(false);
        showDomainSettingsView(false);
        if (hasInternetConnection()) {
            startMailAuth();
        } else {
            showAuthErrorAsToast(getString(ru.mail.Authenticator.R.string.mapp_restore_inet), true);
        }
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment
    protected void onStartAuthorization(Authenticator.Type type) {
        if (this.mailServerParameters == null) {
            startAuthenticate(this.mLogin, this.mPassword, type);
            return;
        }
        setServerParametersFromViews();
        if (!isImapOnly()) {
            sendMailServerSettings();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(MAIL_SERVER_SETTINGS_KEY, this.mailServerParameters);
        startAuthenticate(this.mLogin, this.mPassword, type, bundle);
    }

    protected void setAuthCodePlateVisibility(boolean z10) {
        this.mAuthCodeView.setVisibility(z10 ? 0 : 8);
    }

    public void setErrorAuthView(ErrorDelegate errorDelegate) {
        this.mPasswordErrorDelegate = errorDelegate;
    }

    public void setPasswordView(EditText editText) {
        this.mPasswordView = editText;
    }

    protected void showAuthCodePlate() {
        setAuthCodePlateVisibility(true);
        this.analytics.oneTimeCodeView();
    }

    public void showCaptchaView(boolean z10) {
        this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_image_layout).setVisibility(z10 ? 0 : 8);
        this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_code_layout).setVisibility(z10 ? 0 : 8);
        this.mRootView.findViewById(ru.mail.Authenticator.R.id.captcha_divider).setVisibility(z10 ? 0 : 8);
        updateOnEditorActionListener();
        if (z10) {
            this.mCaptchaEditText.setText("");
            this.mCaptchaEditText.requestFocus();
            startLoadCaptcha();
        }
    }

    public void showDomainSettingsView(boolean z10) {
        this.mDomainSettingsView.setVisibility(z10 ? 0 : 8);
        if (z10) {
            if (isImapOnly()) {
                showProtocols(Arrays.asList(Integer.valueOf(ru.mail.Authenticator.R.id.protocol_imap)));
            } else if (getEmailServiceType().equals(EmailServiceResources.MailServiceResources.EXCHANGE)) {
                this.mProtocolView.check(ru.mail.Authenticator.R.id.protocol_active_sync);
            }
        }
        View viewFindViewById = this.mRootView.findViewById(ru.mail.Authenticator.R.id.restore_password);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(z10 ? 8 : 0);
            new LoginAccessibilityDelegate().initRestorePassword(viewFindViewById);
        }
        updateOnEditorActionListener();
        updateDefaultDomainSettings();
        scrollToDomainSettingsTop();
        setRestorePwdViewEnabled(z10);
    }

    protected void checkCodeAuthAvailable() {
    }
}
