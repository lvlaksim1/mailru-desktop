package ru.mail.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import ru.mail.android_utils.extension.BundleKt;
import ru.mail.auth.MailAccountConstants;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.auth.loginactivity.LoginActivityEvents;
import ru.mail.config.ConfigurationRepository;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.logic.auth.BaseAuthDelegate;
import ru.mail.logic.navigation.restoreauth.ReturnParams;
import ru.mail.registration.RegFlowAnalytics;
import ru.mail.registration.ui.AbstractRegistrationFragment;
import ru.mail.registration.ui.AccountData;
import ru.mail.registration.ui.AuthDelegate;
import ru.mail.registration.ui.ConfirmationActivity;
import ru.mail.registration.ui.ConfirmationCodeFragment;
import ru.mail.registration.ui.RegistrationMailRuFragment;
import ru.mail.registration.ui.SignupConfirmDelegate;
import ru.mail.ui.auth.universal.authDesign.AuthDesignFactory;
import ru.mail.ui.auth.universal.authDesign.RegistrationDesign;
import ru.mail.ui.fragments.ConfirmationCodeLibverifyMailRuFragment;
import ru.mail.ui.fragments.ConfirmationCodeMailRuFragment;
import ru.mail.ui.fragments.ConfirmationQuestionMailRuFragment;
import ru.mail.ui.fragments.RegPermissionsResolver;
import ru.mail.ui.fragments.RegistrationLibverifyFragment;
import ru.mail.ui.fragments.mailbox.PerformanceMonitor;
import ru.mail.ui.fragments.tutorial.permissions.PermissionsFragment;
import ru.mail.ui.fragments.tutorial.permissions.ReadPhoneStatePermissionFragment;
import ru.mail.ui.registration.social.SocialRegisterConfirmFragment;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@AndroidEntryPoint
public class ConfirmationMailRuActivity extends Hilt_ConfirmationMailRuActivity implements PermissionsFragment.PermissionGrantListener, RegPermissionsResolver, ConfirmationCodeFragment.ConfirmationCodeSetup {
    private Bundle authBundle;
    private boolean isImmediateAuth = false;
    private boolean isSuccessAccountReg = false;
    private RegistrationDesign mRegistrationDesign;
    private LoginActivityEvents.AfterRegAuth regAuth;

    private Fragment createReadPhoneStatePermissionFragment() {
        return ReadPhoneStatePermissionFragment.newInstance();
    }

    private Bundle getPhoneStatePermissionArgs() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ConfirmationCodeLibverifyMailRuFragment.REQUEST_PHONE_STATE_PERMISSION, true);
        return bundle;
    }

    private boolean hasSocialDataInIntent(Intent intent) {
        AccountData accountData = (AccountData) getIntent().getSerializableExtra(AbstractRegistrationFragment.ACCOUNT_DATA);
        if (accountData == null) {
            return false;
        }
        return hasSocialExtras(intent) || accountData.hasKnownPhone();
    }

    private boolean hasSocialExtras(Intent intent) {
        return TextUtils.equals(intent.getStringExtra(AbstractRegistrationFragment.CONFIRMATION_ACTION), SocialRegisterConfirmFragment.ACTION) || intent.getBooleanExtra(RegistrationMailRuFragment.USER_STARTED_VKID_REGISTRATION_WITH_FULL_DATA, false);
    }

    private boolean shouldOpenSocialConfirmation() {
        Intent intent = getIntent();
        if (!this.isImmediateAuth) {
            if (intent != null) {
                return hasSocialDataInIntent(intent);
            }
            return false;
        }
        onAuthSucceeded(this.authBundle);
        finish();
        goToAccount(this.authBundle.getString("authAccount"));
        return true;
    }

    private void switchToSocialConfirmation(String str) {
        switchTo(SocialRegisterConfirmFragment.INSTANCE.newInstance(str), false);
    }

    @Override // ru.mail.registration.ui.ConfirmationActivity
    protected Fragment createCaptchaQuestionFragment(ConfirmationActivity.CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow) {
        return ConfirmationQuestionMailRuFragment.newInstance(captchaQuestionAnalyticsFlow);
    }

    @Override // ru.mail.auth.BaseAuthActivity
    protected AuthDelegate createDelegate() {
        return new BaseAuthDelegate();
    }

    @Override // ru.mail.registration.ui.ConfirmationActivity
    protected Fragment createRecaptchaQuestionFragment(String str, ConfirmationActivity.CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow) {
        return RecaptchaMailRuFragment.newInstance(str);
    }

    @Override // ru.mail.registration.ui.ConfirmationActivity
    protected int getConfirmationLayoutId() {
        return this.mRegistrationDesign.getConfirmActivityLayout();
    }

    @Override // ru.mail.registration.ui.Hilt_BaseRegistrationConfirmActivity, androidx.activity.ComponentActivity, androidx.lifecycle.HasDefaultViewModelProviderFactory
    public /* bridge */ /* synthetic */ ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return super.getDefaultViewModelProviderFactory();
    }

    @Override // ru.mail.registration.ui.BaseRegistrationConfirmActivity
    protected String getIsRestore() {
        return ReturnParams.resolveIsRestore(ReturnParams.fromIntent(getIntent()));
    }

    @Override // ru.mail.registration.ui.MigrationDataProvider
    @NonNull
    public String getMigrationFrom() {
        return getIntent().getExtras() != null ? getIntent().getExtras().getString("login_extra_xmail_migration_from", "") : "";
    }

    @Override // ru.mail.registration.ui.BaseRegistrationConfirmActivity
    protected String getRestoreType() {
        return ReturnParams.resolveName(ReturnParams.fromIntent(getIntent()));
    }

    @Override // ru.mail.registration.ui.BaseRegistrationConfirmActivity
    protected String hasActiveAccounts() {
        return ReturnParams.resolveHasAccounts(ReturnParams.fromIntent(getIntent()));
    }

    @Override // ru.mail.registration.ui.ConfirmationActivity
    protected void initFragments() {
        if (this.isSuccessAccountReg) {
            SocialLoginInfoHolder.setBindState(new SocialLoginInfoHolder.BindState(null, CredentialsExchanger.SocialBindType.fromString(this.regAuth.getBindType())));
            onAccountRegistered(RegDataHelper.buildRegResult(this.regAuth), RegDataHelper.buildAccountData(this.regAuth), this.regAuth.getAccountType(), RegDataHelper.buildRegFLow(this.regAuth));
        } else {
            if (!shouldOpenSocialConfirmation()) {
                super.initFragments();
                return;
            }
            String stringExtra = getIntent().getStringExtra(ConfirmationActivity.CONFIRM_ACT_VK_TOKEN_KEY);
            if (stringExtra == null) {
                stringExtra = "";
            }
            switchToSocialConfirmation(stringExtra);
        }
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment.ConfirmationCodeSetup
    public boolean isInternetRu() {
        return getIntent().getBooleanExtra(RegistrationMailRuFragment.IS_INTERNET_RU_DOMAIN, false);
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment.ConfirmationCodeSetup
    public boolean isInternetRuFromDeeplink() {
        return getIntent().getBooleanExtra(RegistrationMailRuFragment.IS_INTERNET_RU_FROM_DEEPLINK, false);
    }

    @Override // ru.mail.registration.ui.ConfirmationCodeFragment.ConfirmationCodeSetup
    public boolean isInternetRuSecurityEnabled() {
        return ConfigurationRepository.from(this).getConfiguration().isInternetRuSecurityEnabled();
    }

    @Override // ru.mail.registration.ui.MigrationDataProvider
    public boolean isMigrated() {
        return getIntent().hasExtra("login_extra_xmail_migration_from");
    }

    @Override // ru.mail.registration.ui.BaseRegistrationConfirmActivity, ru.mail.auth.BaseAuthActivity
    public void onAccountAdded() {
        if (this.isSuccessAccountReg) {
            goToAccount(this.regAuth.getEmail());
        }
        super.onAccountAdded();
    }

    @Override // ru.mail.registration.ui.BaseRegistrationConfirmActivity, ru.mail.registration.ui.ConfirmationActivityInterface
    public void onAccountRegistered(SignupConfirmDelegate.RegistrationResult registrationResult, AccountData accountData, String str, RegFlowAnalytics regFlowAnalytics) {
        PerformanceMonitor.from(getApplicationContext()).registerAccount().start();
        super.onAccountRegistered(registrationResult, accountData, str, regFlowAnalytics);
    }

    @Override // ru.mail.auth.BaseAuthActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i10, int i11, Intent intent) {
        super.onActivityResult(i10, i11, intent);
        if (i10 == 192 && i11 == 2) {
            setResult(2);
            finish();
        }
    }

    @Override // ru.mail.registration.ui.ConfirmationActivity, ru.mail.registration.ui.BaseRegistrationConfirmActivity, ru.mail.registration.ui.Hilt_BaseRegistrationConfirmActivity, ru.mail.auth.BaseAuthActivity, ru.mail.auth.BaseToolbarActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (getIntent() != null && getIntent().getExtras() != null) {
            if (getIntent().getExtras().getBoolean("isImmediateAuth", false)) {
                this.isImmediateAuth = true;
                this.authBundle = getIntent().getExtras();
            }
            LoginActivityEvents.AfterRegAuth afterRegAuth = (LoginActivityEvents.AfterRegAuth) BundleKt.getParcelableObj(getIntent().getExtras(), MailAccountConstants.SUCCESS_REGISTER_ACCOUNT, LoginActivityEvents.AfterRegAuth.class);
            this.regAuth = afterRegAuth;
            if (afterRegAuth != null) {
                this.isSuccessAccountReg = true;
            }
        }
        RegistrationDesign registrationActivityDesign = new AuthDesignFactory(this).getRegistrationActivityDesign();
        this.mRegistrationDesign = registrationActivityDesign;
        registrationActivityDesign.setTheme();
        super.onCreate(bundle);
        this.mRegistrationDesign.setBackground();
    }

    @Override // ru.mail.ui.fragments.tutorial.permissions.PermissionsFragment.PermissionGrantListener
    public void onPermissionDenied() {
        onSwitchToPhoneConfirmationFragment(getPhoneStatePermissionArgs());
    }

    @Override // ru.mail.ui.fragments.tutorial.permissions.PermissionsFragment.PermissionGrantListener
    public void onPermissionGranted() {
        onSwitchToPhoneConfirmationFragment(getPhoneStatePermissionArgs());
    }

    @Override // ru.mail.ui.fragments.RegPermissionsResolver
    public void onSwitchToPhoneStatePermissionFragment() {
        switchTo(createReadPhoneStatePermissionFragment(), false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.registration.ui.ConfirmationActivity
    public ConfirmationCodeFragment createConfirmationCodeFragment() {
        Bundle extras = getIntent().getExtras();
        return (extras == null || !extras.getBoolean(RegistrationLibverifyFragment.EXTRA_NEED_USE_LIB_VERIFY)) ? new ConfirmationCodeMailRuFragment() : new ConfirmationCodeLibverifyMailRuFragment();
    }
}
