package ru.mail.ui.auth;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.android_utils.webview.WebViewUpdateDialogCreator;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.AuthUtil;
import ru.mail.auth.Authenticator;
import ru.mail.auth.BaseMessageVisitor;
import ru.mail.auth.BaseToolbarActivity;
import ru.mail.auth.EmailServiceResources;
import ru.mail.auth.LoginActivity;
import ru.mail.auth.LoginFlowNavigator;
import ru.mail.auth.LoginSuggestFragment;
import ru.mail.auth.MailAccountConstants;
import ru.mail.auth.MailLoginFragment;
import ru.mail.auth.Message;
import ru.mail.auth.composescreens.RegistrationComposeScreen;
import ru.mail.auth.loginstub.LoginStubFragment;
import ru.mail.auth.logscollector.LongClickCounterListener;
import ru.mail.auth.restore.NewSdkRestoreAuthKt;
import ru.mail.auth.util.DomainUtils;
import ru.mail.auth.webview.BaseSecondStepAuthFragment;
import ru.mail.authorizationsdk.external.api.AuthEvent;
import ru.mail.authorizationsdk.external.api.AuthorizationSdk;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.ReturnParamsHelper;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.authorizesdk.presentation.servicechooser.ServiceChooserFragmentSDK;
import ru.mail.authorizesdk.util.extensions.Callback;
import ru.mail.authorizesdk.util.extensions.FragmentKt;
import ru.mail.compose.component.webview.WebViewActivityThemeFix;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.config.translations.DynamicStringsFactoryInstaller;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.credentialsexchanger.presentation.fragment.esia.EsiaWebviewFragment;
import ru.mail.credentialsexchanger.unblockvkusers.RestoreVkEmailHelper;
import ru.mail.credentialsexchanger.unblockvkusers.RestoreVkEmailHelperHolder;
import ru.mail.data.cmd.imap.CheckProviderInfoCredentialsTask;
import ru.mail.data.cmd.imap.ProviderInfo;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.data.entities.MailboxProfileUtils;
import ru.mail.imageloader.ContextWrapper;
import ru.mail.imageloader.ImageLoader;
import ru.mail.imageloader.ImageLoaderRepository;
import ru.mail.locator.Locator;
import ru.mail.logic.auth.BaseAuthDelegate;
import ru.mail.logic.auth.SuperAppKitIds;
import ru.mail.logic.content.Permission;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.logic.experiment.DefaultAction;
import ru.mail.logic.experiment.ExperimentAction;
import ru.mail.logic.experiment.LoginExperiment;
import ru.mail.logic.navigation.restoreauth.RegisterReturnParams;
import ru.mail.logic.navigation.restoreauth.ReturnParams;
import ru.mail.logic.navigation.restoreauth.ServiceChooserParams;
import ru.mail.logic.navigation.restoreauth.SessionRestoreHelper;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mailapp.R;
import ru.mail.march.navigation.ExtensionsKt;
import ru.mail.march.viewmodel.ViewModelObtainerKt;
import ru.mail.qr_auth.QrPromoListener;
import ru.mail.qr_auth.ui.LifecycleCallback;
import ru.mail.qr_auth.ui.QrLoginActivityContract;
import ru.mail.qr_auth.ui.error.SomethingWrongFragment;
import ru.mail.registration.request.SocialAuthKnownFields;
import ru.mail.registration.ui.AuthDelegate;
import ru.mail.registration.ui.DoregistrationFragment;
import ru.mail.social.auth.RestoreVkidStartSource;
import ru.mail.social_auth.domain.AuthResult;
import ru.mail.social_auth.domain.RestoreVkEmailNavigator;
import ru.mail.social_auth.domain.SocialAuthEvent;
import ru.mail.social_auth.domain.VkBindTokens;
import ru.mail.theme.utils.DarkThemeUtils;
import ru.mail.ui.ActivityCallback;
import ru.mail.ui.SystemGoogleAccountPermission;
import ru.mail.ui.TutorialMyComFragment;
import ru.mail.ui.auth.unblockvkusers.RestoreVkEmailHelperDelegate;
import ru.mail.ui.auth.universal.UserBoundByVKIDDelegate;
import ru.mail.ui.auth.universal.authDesign.AuthActivityDesign;
import ru.mail.ui.auth.universal.authDesign.AuthDesignFactory;
import ru.mail.ui.auth.universal.authDesign.ChangeThemeResolver;
import ru.mail.ui.auth.universal.esia.EsiaAuthViewModel;
import ru.mail.ui.auth.universal.esia.EsiaFlowProvider;
import ru.mail.ui.auth.universal.logscollector.LoginLogoViewModel;
import ru.mail.ui.auth.universal.logscollector.LoginLogsViewModel;
import ru.mail.ui.auth.universal.logscollector.LoginPermissionsActivity;
import ru.mail.ui.auth.vkidbindinlogin.presentation.VkBindInLoginFragment;
import ru.mail.ui.auth.welcome.MailRuWelcomeLoginFragment;
import ru.mail.ui.dialogs.GoogleAccountPermissionDialogResult;
import ru.mail.ui.dialogs.GooglePrimaryAccountDialog;
import ru.mail.ui.dialogs.InitialPrivacyAgreementDialog;
import ru.mail.ui.dialogs.InitialPrivacyAgreementDialogResult;
import ru.mail.ui.fragments.mailbox.AccountsAndSmartLockSuggestFragment;
import ru.mail.ui.promosheet.webauthn.disabler.WebAuthNPromoDisabler;
import ru.mail.ui.registration.MailRuRegistrationActivity;
import ru.mail.uikit.drawable.BackgroundTheme;
import ru.mail.util.BuildVariantHelper;
import ru.mail.util.LicenseAgreementManager;
import ru.mail.util.log.AppLogger;
import ru.mail.util.log.Logger;
import ru.mail.util.reporter.AbstractErrorReporter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@AndroidEntryPoint
public class MailRuLoginActivity extends Hilt_MailRuLoginActivity implements MailTwoStepLoginScreenFragment.MyComInitiator, ChangeThemeResolver, RestoreVkEmailHelperHolder, LoginLogsViewModel.View, LongClickCounterListener, ServiceChooserFragmentSDK.OnLongClickEventsSdk, QrPromoListener, SomethingWrongFragment.OnClickListener, LifecycleCallback {
    private static final String AGREEMENT_DIALOG_TAG = "license_agreement";
    private static final String FRAGMENTS_BEFORE_RECREATE = "fragments_before_recreate";
    public static final String GOOGLE_ACCOUNT_DIALOG_TAG = "system_google_account_permission_dialog";
    private static final String HAS_RESTARTED = "hasRestarted";
    private static final String LOGIN_LAUNCHER_STATE = "login_launcher_state";
    private AuthDeeplinkViewModel authDeeplinkViewModel;
    private EsiaAuthViewModel esiaAuthViewModel;
    private LoginLogoViewModel loginLogoViewModel;
    private LoginScreenLauncher loginScreenLauncher;
    ActivityResultLauncher<QrLoginActivityContract.QrLoginActivityParams> mActivityResultLauncher;
    private AuthActivityDesign mAuthActivityDesign;
    private Logger mLogger;
    private LoginLogsViewModel mLoginLogsViewModel;

    @Inject
    SystemGoogleAccountPermission mSystemGoogleAccountPermission;
    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: ru.mail.ui.auth.k
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            this.f98208a.lambda$new$0((Boolean) obj);
        }
    });
    private RestoreVkEmailHelper restoreVkEmailHelper;

    @Inject
    UserBoundByVKIDDelegate userBoundByVKIDDelegate;
    private AlertDialog webViewErrorDialog;

    /* JADX INFO: renamed from: ru.mail.ui.auth.MailRuLoginActivity$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ru$mail$credentialsexchanger$core$CredentialsExchanger$SocialBindType;

        static {
            int[] iArr = new int[CredentialsExchanger.SocialBindType.values().length];
            $SwitchMap$ru$mail$credentialsexchanger$core$CredentialsExchanger$SocialBindType = iArr;
            try {
                iArr[CredentialsExchanger.SocialBindType.VK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ru$mail$credentialsexchanger$core$CredentialsExchanger$SocialBindType[CredentialsExchanger.SocialBindType.VK_BIND_IN_LOGIN_PROMO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private class UIAuthVisitor extends BaseMessageVisitor {
        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void onAuthSucceeded(Message message) {
            MailRuLoginActivity.this.authDeeplinkViewModel.confirmDeeplink();
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void onSwitchToRestoreVkId(Message message) {
            MailRuLoginActivity.this.startRestoreVkidOld(message.getData().getString("email"), RestoreVkidStartSource.FORGET_PASSWORD);
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startPickAccount(Message message) {
            MailRuLoginActivity.this.startMycomPickAccountsScreen(message.getData());
        }

        private UIAuthVisitor() {
        }
    }

    private LoginScreenLauncher createLoginScreenLauncher(LoginScreenLauncher.LoginScreenLaunchState loginScreenLaunchState) {
        final String extraLoginForLoginScreen = getExtraLoginForLoginScreen();
        return new LoginScreenLauncher(loginScreenLaunchState, new ShowLoginScreenCallback() { // from class: ru.mail.ui.auth.j
            @Override // ru.mail.ui.auth.ShowLoginScreenCallback
            public final void showLoginScreen(String str) {
                this.f98206a.lambda$createLoginScreenLauncher$4(extraLoginForLoginScreen, str);
            }
        });
    }

    @NonNull
    private ActivityResultLauncher<QrLoginActivityContract.QrLoginActivityParams> createQrLoginLauncher() {
        return registerForActivityResult(new QrLoginActivityContract(), new ActivityResultCallback() { // from class: ru.mail.ui.auth.f
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f98203a.lambda$createQrLoginLauncher$3((QrLoginActivityContract.Output) obj);
            }
        });
    }

    public static /* synthetic */ WindowInsets g0(View view, WindowInsets windowInsets) {
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i10 = 0; i10 < viewGroup.getChildCount(); i10++) {
            viewGroup.getChildAt(i10).dispatchApplyWindowInsets(windowInsets);
        }
        return windowInsets;
    }

    private AuthActivityDesign getAuthActivityDesign() {
        if (BuildVariantHelper.isVK()) {
            prepareVkExtras();
        }
        return new AuthDesignFactory(this).getAuthActivityDesign();
    }

    private ConfigurationRepository getConfigurationRepository() {
        return (ConfigurationRepository) Locator.from(getApplicationContext()).locate(ConfigurationRepository.class);
    }

    private CommonDataManager getDataManager() {
        return CommonDataManager.from(getApplication());
    }

    private String getExtraLoginForLoginScreen() {
        String loginForRestoreAuth = isNewRestoreFlow() ? NewSdkRestoreAuthKt.getLoginForRestoreAuth(getIntent().getExtras()) : getIntent().getStringExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN);
        return loginForRestoreAuth == null ? "" : loginForRestoreAuth;
    }

    private ArrayList<String> getFragmentsDetails() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != null) {
                String simpleName = fragment.getClass().getSimpleName();
                String tag = fragment.getTag();
                if (tag != null) {
                    simpleName = simpleName + " with tag: " + tag;
                }
                arrayList.add(simpleName);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAuthorizationSdkEvent(AuthEvent authEvent) {
        if (authEvent instanceof AuthEvent.OnLongClickLogo) {
            saveLogs();
        }
    }

    private void initAgreementDialogs() {
        if (isFinishing()) {
            return;
        }
        ExtensionsKt.setFragmentResultListener(this, GoogleAccountPermissionDialogResult.INSTANCE, (Function0<Unit>) new Function0() { // from class: ru.mail.ui.auth.i
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f98205a.lambda$initAgreementDialogs$6();
            }
        });
        SystemGoogleAccountPermission systemGoogleAccountPermission = this.mSystemGoogleAccountPermission;
        if (systemGoogleAccountPermission == null || !systemGoogleAccountPermission.shouldBeRequested()) {
            showLicenseAgreementDialogIfNecessary();
        } else {
            showGooglePrimaryAccountDialogIfNecessary();
        }
    }

    private void initSimpleLoginScreen() {
        String stringExtra = getIntent().getStringExtra(Authenticator.PARAM_EMAIL_SERVICE_TYPE);
        String stringExtra2 = getIntent().getStringExtra(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE);
        String stringExtra3 = getIntent().getStringExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN);
        boolean booleanExtra = getIntent().getBooleanExtra("REGISTER_NEW_MYCOM_ACCOUNT", false);
        initServiceChooser();
        if (stringExtra == null && stringExtra2 == null && stringExtra3 == null && !booleanExtra) {
            loginDefault("LOGIN_TO_OTHER_DOMAIN", false);
        }
    }

    private boolean isDomainOauthBanned(String str) {
        return ConfigurationRepository.from(getApplicationContext()).getConfiguration().getNewLoginSuppressedOauth().matcher(str).find();
    }

    private boolean isEsiaFragments(Fragment fragment) {
        if (fragment instanceof EsiaWebviewFragment) {
            return true;
        }
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            return false;
        }
        return CredentialsExchanger.STRING_ESIA_BIND.equalsIgnoreCase(arguments.getString(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY));
    }

    private boolean isMyComTwoWay() {
        return BuildVariantHelper.isMyCom() && ConfigurationRepository.from(getApplication()).getConfiguration().getTwoStepAuth().isEnabled();
    }

    private boolean isNewRestoreFlow() {
        if (getIntent().getExtras() == null) {
            return false;
        }
        return ReturnParamsHelper.INSTANCE.isIntentForRestoreAuth(getIntent());
    }

    private boolean isTwoStepSupported(EmailServiceResources.MailServiceResources mailServiceResources) {
        if (ConfigurationRepository.from(getApplication()).getConfiguration().getTwoStepAuth().isEnabled()) {
            return mailServiceResources == EmailServiceResources.MailServiceResources.MAILRU || mailServiceResources == EmailServiceResources.MailServiceResources.MAILRU_DEFAULT;
        }
        return false;
    }

    private boolean isUniversalTwoStepSupported() {
        DTOConfiguration.Config.AuthFlow.TwoStepAuth twoStepAuth = ConfigurationRepository.from(getApplication()).getConfiguration().getTwoStepAuth();
        return twoStepAuth.isEnabled() && twoStepAuth.isSkipDomainChooser();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createLoginScreenLauncher$4(String str, String str2) {
        showLoginScreen(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createQrLoginLauncher$3(QrLoginActivityContract.Output output) {
        if (output == null) {
            return;
        }
        String authUrl = output.getAuthUrl();
        String email = output.getEmail();
        if (TextUtils.isEmpty(authUrl) || TextUtils.isEmpty(email)) {
            return;
        }
        startQrAuthenticator(email, authUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$initAgreementDialogs$6() {
        showLicenseAgreementDialogIfNecessary();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:13:0x003f  */
    public /* synthetic */ void lambda$listenEsiaAuthViewModelEvents$8(SocialAuthEvent socialAuthEvent) {
        boolean zEqualsIgnoreCase;
        if (socialAuthEvent instanceof SocialAuthEvent.OpenFragment) {
            zEqualsIgnoreCase = isEsiaFragments(((SocialAuthEvent.OpenFragment) socialAuthEvent).getFragment());
        } else if (socialAuthEvent instanceof SocialAuthEvent.AuthResult) {
            SocialAuthEvent.AuthResult authResult = (SocialAuthEvent.AuthResult) socialAuthEvent;
            AuthResult result = authResult.getResult();
            if (result instanceof AuthResult.LoginResult) {
                zEqualsIgnoreCase = CredentialsExchanger.STRING_ESIA_BIND.equalsIgnoreCase(((AuthResult.LoginResult) result).getSocialBindType());
            } else {
                AuthResult result2 = authResult.getResult();
                if (result2 instanceof VkBindTokens) {
                    zEqualsIgnoreCase = CredentialsExchanger.STRING_ESIA_BIND.equalsIgnoreCase(((VkBindTokens) result2).getBindType());
                } else {
                    zEqualsIgnoreCase = false;
                }
            }
        } else {
            zEqualsIgnoreCase = false;
        }
        if (zEqualsIgnoreCase) {
            socialAuthEventExecutor(socialAuthEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Boolean bool) {
        if (!bool.booleanValue()) {
            Toast.makeText(this, R.string.login_logs_cancelled, 1).show();
        } else if (!this.newAuthorizationSdkConfig.isAnyLoginEnabled()) {
            this.mLoginLogsViewModel.saveLogs();
        } else {
            showSaveLogsStarted();
            this.loginLogoViewModel.launchSaveLogs();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1() {
        this.webViewErrorDialog = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$onCreate$2(AlertDialog alertDialog) {
        this.webViewErrorDialog = alertDialog;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$showLicenseAgreementDialogIfNecessary$7() {
        this.loginScreenLauncher.onLoginScreenLaunchAllowed();
        startXmailMigrationIfNeed(getIntent());
        startGmailAuthByPromo();
        return null;
    }

    private void listenEsiaAuthViewModelEvents() {
        FragmentKt.collectByLifecycle(this, this.esiaAuthViewModel.getEventsFlow(), new Callback() { // from class: ru.mail.ui.auth.g
            @Override // ru.mail.authorizesdk.util.extensions.Callback
            public final void action(Object obj) {
                this.f98204a.lambda$listenEsiaAuthViewModelEvents$8((SocialAuthEvent) obj);
            }
        });
    }

    private void listenToWindowsInsets() {
        findViewById(R.id.root_view).setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: ru.mail.ui.auth.h
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                return MailRuLoginActivity.g0(view, windowInsets);
            }
        });
    }

    private boolean needShowMyComServiceTutorial() {
        return PreferenceManager.getDefaultSharedPreferences(this).getBoolean(LoginActivity.PREF_KEY_SHOW_MYCOM_SERVICE_TUTORIAL, true);
    }

    private void onMyComServiceTutorialShown() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(LoginActivity.PREF_KEY_SHOW_MYCOM_SERVICE_TUTORIAL, false).apply();
    }

    private void prepareVkExtras() {
        String stringExtra;
        if (getIntent().getBooleanExtra(Authenticator.IS_LOGIN_EXISTING_ACCOUNT, false) && (stringExtra = getIntent().getStringExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN)) != null && !DomainUtils.isVkComDomain(stringExtra)) {
            getIntent().putExtra(LoginActivity.EXTRA_EXTERNAL_RELOGIN, true);
        }
        Iterator<MailboxProfile> it = getDataManager().getAccounts().iterator();
        while (it.hasNext()) {
            String login = it.next().getLogin();
            boolean zIsVkComDomain = DomainUtils.isVkComDomain(login);
            boolean zIsAccountAuthorized = getDataManager().isAccountAuthorized(login);
            if (zIsVkComDomain && zIsAccountAuthorized) {
                getIntent().putExtra(LoginActivity.EXTRA_SHOW_EXTERNAL_VK_LOGIN, LoginActivity.EXTRA_VALUE_EXTERNAL_VK_LOGIN_FROM_ADD);
                return;
            }
        }
    }

    private boolean safeRecreate() {
        Intent intent = getIntent();
        if (intent.getBooleanExtra(HAS_RESTARTED, false)) {
            return false;
        }
        intent.putExtra(HAS_RESTARTED, true);
        recreate();
        return true;
    }

    private void saveLogs() {
        if (!Permission.WRITE_EXTERNAL_STORAGE.isGranted(this)) {
            startActivity(new Intent(this, (Class<?>) LoginPermissionsActivity.class));
        } else {
            showSaveLogsStarted();
            this.loginLogoViewModel.launchSaveLogs();
        }
    }

    private void showGooglePrimaryAccountDialogIfNecessary() {
        if (getSupportFragmentManager().findFragmentByTag(GOOGLE_ACCOUNT_DIALOG_TAG) == null) {
            getSupportFragmentManager().beginTransaction().add(new GooglePrimaryAccountDialog(), GOOGLE_ACCOUNT_DIALOG_TAG).commitAllowingStateLoss();
        }
    }

    private void showLicenseAgreementDialogIfNecessary() {
        LicenseAgreementManager licenseAgreementManagerFrom = LicenseAgreementManager.from(getApplicationContext());
        Date privacyPolicyAcceptanceDate = CommonDataManager.from(getApplicationContext()).getPrivacyPolicyAcceptanceDate();
        if (licenseAgreementManagerFrom.getLicenseAgreementDate() == null || privacyPolicyAcceptanceDate != null || getSupportFragmentManager().findFragmentByTag(AGREEMENT_DIALOG_TAG) != null) {
            this.loginScreenLauncher.onLoginScreenLaunchAllowed();
            startXmailMigrationIfNeed(getIntent());
            startGmailAuthByPromo();
        } else {
            InitialPrivacyAgreementDialog initialPrivacyAgreementDialogNewInstance = InitialPrivacyAgreementDialog.newInstance(licenseAgreementManagerFrom.shouldShowReceiveNewslettersOnInitialDialog(), licenseAgreementManagerFrom.shouldShowAcceptCheckboxOnInitialDialog());
            ExtensionsKt.setFragmentResultListener(this, InitialPrivacyAgreementDialogResult.INSTANCE, (Function0<Unit>) new Function0() { // from class: ru.mail.ui.auth.o
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f98212a.lambda$showLicenseAgreementDialogIfNecessary$7();
                }
            });
            getSupportFragmentManager().beginTransaction().add(initialPrivacyAgreementDialogNewInstance, AGREEMENT_DIALOG_TAG).commitAllowingStateLoss();
            MailAppDependencies.analytics(getApplicationContext()).onAgreementDialogShown();
        }
    }

    private void showMyComServiceTutorial() {
        changeToFragment(new TutorialMyComFragment());
    }

    private void showSaveLogsStarted() {
        Toast.makeText(this, R.string.logs_save_started, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMycomPickAccountsScreen(Bundle bundle) {
        MycomAccountPicker mycomAccountPicker = new MycomAccountPicker();
        mycomAccountPicker.setArguments(bundle);
        changeToFragment(mycomAccountPicker);
    }

    @Override // ru.mail.auth.LoginActivity
    protected void addNecessaryProperties(Bundle bundle, String str) {
        bundle.putString(ProviderInfo.KEY_PROVIDER_INFO, str);
        bundle.putString(CheckProviderInfoCredentialsTask.EXTRA_CHECK_RESULT, CheckProviderInfoCredentialsTask.CHECK_RESULT_OK);
        bundle.putString(BaseAuthDelegate.EXTRA_TRANSPORT, MailboxProfile.TransportType.IMAP.toString());
    }

    @Override // ru.mail.auth.logscollector.LongClickCounterListener
    public void cancelLongClickCounter() {
        this.mLoginLogsViewModel.cancelLongClickCounter();
    }

    @Override // ru.mail.ui.auth.universal.authDesign.ChangeThemeResolver
    public void changeTheme(@NotNull BackgroundTheme backgroundTheme) {
        this.mAuthActivityDesign.changeTheme(backgroundTheme);
    }

    @Override // ru.mail.auth.LoginActivity
    public void changeToFragment(Fragment fragment, Bundle bundle) {
        String clientId = SuperAppKitIds.INSTANCE.getClientId(this);
        if (fragment instanceof MailSecondStepWrappedFragment) {
            bundle.putString(BaseSecondStepAuthFragment.VK_CLIENT_ID_PARAM_KEY, clientId);
        }
        fragment.setArguments(bundle);
        changeToFragment(fragment);
    }

    @Override // ru.mail.auth.BaseAuthActivity
    @NotNull
    protected AuthDelegate createDelegate() {
        return new BaseAuthDelegate();
    }

    @Override // ru.mail.auth.LoginActivity
    protected DoregistrationFragment createDoregistrationFragment() {
        return new MailRuDoregistrationFragment();
    }

    @Override // ru.mail.auth.LoginActivity
    protected LoginFlowNavigator createFlowNavigator() {
        return new MailRuLoginFlowNavigator(getApplicationContext(), this, this);
    }

    @Override // ru.mail.auth.LoginActivity
    protected Fragment createGooglePickerFragment() {
        return new GoogleAccountPickerFragment();
    }

    @Override // ru.mail.auth.BaseAuthActivity
    protected MailLoginFragment createLoginFragment(String str, Bundle bundle) {
        return MailRuLoginFragment.newInstance(str, this.mIsImapSkipMailruOauth, this.mIsImapOnly, getIntent().getStringExtra("login_extra_xmail_migration_from"), bundle);
    }

    @Override // ru.mail.auth.LoginActivity
    protected Fragment createLoginScreenFragment(String str) {
        if (openNewLoginIfPossible(str)) {
            return new LoginStubFragment();
        }
        EmailServiceResources.MailServiceResources mailServiceResourcesFromString = EmailServiceResources.MailServiceResources.fromString(str, "ru.mail");
        if (isUniversalTwoStepSupported()) {
            return this.mAuthActivityDesign.getUniversalLoginFragment();
        }
        return isTwoStepSupported(mailServiceResourcesFromString) ? new MailTwoStepLoginScreenFragment() : new MailLoginScreenFragment();
    }

    @Override // ru.mail.auth.LoginActivity
    protected LoginSuggestFragment createLoginSuggestFragment() {
        return new AccountsAndSmartLockSuggestFragment();
    }

    @Override // ru.mail.auth.LoginActivity
    protected Fragment createMicrosoftLoginFragment() {
        return new MicrosoftLoginScreenFragment();
    }

    @Override // ru.mail.auth.LoginActivity
    protected Fragment createMyComLoginFragment() {
        return new SmsLoginFragment();
    }

    @Override // ru.mail.auth.LoginActivity
    protected Fragment createSecondStepFragment() {
        return new MailSecondStepWrappedFragment();
    }

    @Override // ru.mail.auth.LoginActivity
    protected Fragment createServiceChooserFragment() {
        if (!isServiceChooserFragmentSdkEnabled()) {
            return new MailRuServiceChooserFragment();
        }
        ConfigurationWithRawData configuration = ConfigurationRepository.from(getApplication()).getConfiguration();
        List<String> list = Collections.EMPTY_LIST;
        list.addAll(configuration.getHideLoginServicesConfig().getServices());
        MailServicesListHelper mailServicesListHelper = MailServicesListHelper.INSTANCE;
        return ServiceChooserFragmentSDK.INSTANCE.newInstance(mailServicesListHelper.getServicesList(this, list), mailServicesListHelper.isRegistrationAvailable(this));
    }

    @Override // ru.mail.auth.LoginActivity
    protected Fragment createVkBindInLoginFragment(String str) {
        return VkBindInLoginFragment.INSTANCE.createInstance(str);
    }

    @Override // ru.mail.auth.Hilt_LoginActivity, androidx.activity.ComponentActivity, androidx.lifecycle.HasDefaultViewModelProviderFactory
    public /* bridge */ /* synthetic */ ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return super.getDefaultViewModelProviderFactory();
    }

    @Override // ru.mail.auth.LoginActivity
    protected int getLoginActivityLayout() {
        return this.mAuthActivityDesign.getLayout();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return getApplicationContext().getResources();
    }

    @Override // ru.mail.credentialsexchanger.unblockvkusers.RestoreVkEmailHelperHolder
    @NonNull
    public RestoreVkEmailHelper getRestoreVkEmailHelper() {
        return this.restoreVkEmailHelper;
    }

    @Override // ru.mail.auth.LoginSuggestFragment.LoginSuggestSettingsSelector
    public LoginSuggestFragment.LoginSuggestSettings getSettings() {
        return new LoginSuggestSettingsImpl(getConfigurationRepository().getConfiguration());
    }

    @Override // ru.mail.auth.LoginActivity, ru.mail.auth.LoginFragmentInitializer
    public boolean initServiceChooser() {
        boolean zInitServiceChooser = super.initServiceChooser();
        if (zInitServiceChooser) {
            new SessionRestoreHelper(this).schedule(new ServiceChooserParams(AuthUtil.hasActiveAccounts(this)));
        }
        return zInitServiceChooser;
    }

    @Override // ru.mail.auth.LoginActivity, ru.mail.auth.LoginFragmentInitializer
    public void initStartingFragments() {
        this.mFlowNavigator.launchWelcomeFlow();
    }

    public void initStartingFragmentsDefault() {
        initServiceChooser();
        changeToFragmentFromExtra(getIntent());
    }

    public void initStartingFragmentsExperiment() {
        initSimpleLoginScreen();
        changeToFragmentFromExtra(getIntent());
    }

    @Override // ru.mail.auth.LoginFragmentInitializer
    public void initStartingLoginFragments() {
        this.mFlowNavigator.launchLoginFlow();
    }

    @Override // ru.mail.auth.LoginFragmentInitializer
    public void initStartingUniversalLoginExperiment() {
        boolean zHasExtra = getIntent().hasExtra(Authenticator.AUTH_RESTORE_PARAMS);
        boolean zIsVK = BuildVariantHelper.isVK();
        boolean booleanExtra = getIntent().getBooleanExtra(Authenticator.IS_PREFILL_LOGIN_DATA, false);
        boolean booleanExtra2 = getIntent().getBooleanExtra(Authenticator.IS_LOGIN_EXISTING_ACCOUNT, false);
        if (!zHasExtra && !booleanExtra2 && !booleanExtra) {
            getIntent().putExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN, "");
        }
        loginDefault("LOGIN_TO_MAILRU_DOMAIN", true);
        if (!zHasExtra && !zIsVK) {
            initLoginSuggestFragment(R.id.login_fragment);
        }
        changeToFragmentFromExtra(getIntent());
    }

    @Override // ru.mail.auth.LoginFragmentInitializer
    public void initWelcomeLoginFragment() {
        MailRuWelcomeLoginFragment mailRuWelcomeLoginFragment = new MailRuWelcomeLoginFragment();
        if (!getIntent().hasExtra(Authenticator.AUTH_RESTORE_PARAMS)) {
            initLoginSuggestFragment(R.id.login_fragment);
        }
        changeToFragment(mailRuWelcomeLoginFragment, true, true, LoginActivity.WELCOME_FRAGMENT_TAG);
    }

    @Inject
    void injectLogger(@NonNull @AppLogger Logger logger) {
        this.mLogger = logger.createLogger("MailRuLoginActivity");
    }

    @Override // ru.mail.auth.LoginActivity
    public boolean isDeeplinkOpened() {
        return this.authDeeplinkViewModel.isDeeplinkReceived().getValue().booleanValue();
    }

    @Override // ru.mail.auth.BaseToolbarActivity
    protected boolean isServiceChooserFragmentSdkEnabled() {
        return this.newAuthSdkConfig.isEnabled() && this.newAuthSdkConfig.isServiceChooserFragmentSdkEnabled();
    }

    @Override // ru.mail.auth.LoginActivity
    protected boolean isWebAuthNDisablerPromoClicked() {
        return getIntent().getBooleanExtra(WebAuthNPromoDisabler.IS_WEB_AUTH_PROMO_DISABLER, false);
    }

    @Override // ru.mail.auth.LoginFragmentInitializer
    public void launchLoginExperiment() {
        startExperiment(this);
    }

    @Override // ru.mail.auth.LoginSuggestFragment.LoginSuggestInterface
    public void loadMultipleAccountAvatar(ImageView imageView, int i10) {
        imageView.setImageResource(2131231272);
    }

    @Override // ru.mail.auth.LoginSuggestFragment.LoginSuggestInterface
    public void loadSuggestAvatar(ImageView imageView, String str, int i10) {
        ((ImageLoaderRepository) Locator.from(this).locate(ImageLoaderRepository.class)).getSharedImageLoader().loadAvatarImage(imageView, str, (String) null, ContextWrapper.toContextWrapper(this), (ImageLoader.SuccessRequestListener) null);
    }

    @Override // ru.mail.auth.LoginActivity
    protected void loginMyCom() {
        if (!needShowMyComServiceTutorial() || getDataManager().hasMyComAccount(this)) {
            super.loginMyCom();
            return;
        }
        BaseToolbarActivity.hideKeyboard(this);
        showMyComServiceTutorial();
        onMyComServiceTutorialShown();
    }

    @Override // ru.mail.auth.LoginActivity
    protected void onAccountBindSuccessAfterSuccessAuth(String str, String str2) {
        super.onAccountBindSuccessAfterSuccessAuth(str, str2);
        int i10 = AnonymousClass1.$SwitchMap$ru$mail$credentialsexchanger$core$CredentialsExchanger$SocialBindType[CredentialsExchanger.SocialBindType.fromString(str2).ordinal()];
        if (i10 == 1 || i10 == 2) {
            this.userBoundByVKIDDelegate.setBindState(UserBoundByVKIDDelegate.BindState.Success.INSTANCE);
        }
    }

    @Override // ru.mail.auth.LoginActivity, ru.mail.auth.BaseAuthFragment.ErrorDisplay
    public void onAuthError() {
        super.onAuthError();
        AbstractErrorReporter.from(getApplicationContext()).report();
    }

    @Override // ru.mail.auth.LoginActivity, ru.mail.authorizesdk.presentation.common.ErrorDisplaySdk
    public void onAuthErrorCall() {
        onAuthError();
    }

    @Override // ru.mail.authorizesdk.presentation.servicechooser.ServiceChooserFragmentSDK.OnLongClickEventsSdk
    public void onCancelLongClickCounter() {
        cancelLongClickCounter();
    }

    @Override // ru.mail.qr_auth.QrPromoListener
    public void onContinueClick() {
        if (this.newAuthorizationSdkConfig.isQrAuthEnabled()) {
            openQrScannerFromNewSdk();
        } else {
            this.mActivityResultLauncher.launch(new QrLoginActivityContract.QrLoginActivityParams(DarkThemeUtils.isActivityInNightMode(this)));
        }
    }

    @Override // ru.mail.auth.LoginActivity, ru.mail.auth.Hilt_LoginActivity, ru.mail.auth.BaseAuthActivity, ru.mail.auth.BaseToolbarActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        LoginScreenLauncher.LoginScreenLaunchState loginScreenLaunchState = LoginScreenLauncher.LoginScreenLaunchState.None.INSTANCE;
        if (bundle != null) {
            LoginScreenLauncher.LoginScreenLaunchState loginScreenLaunchState2 = Build.VERSION.SDK_INT >= 33 ? (LoginScreenLauncher.LoginScreenLaunchState) bundle.getParcelable(LOGIN_LAUNCHER_STATE, LoginScreenLauncher.LoginScreenLaunchState.class) : (LoginScreenLauncher.LoginScreenLaunchState) bundle.getParcelable(LOGIN_LAUNCHER_STATE);
            if (loginScreenLaunchState2 != null) {
                loginScreenLaunchState = loginScreenLaunchState2;
            }
        }
        this.loginScreenLauncher = createLoginScreenLauncher(loginScreenLaunchState);
        WebViewActivityThemeFix.INSTANCE.initWebView(this);
        DynamicStringsFactoryInstaller.install(this);
        DarkThemeUtils.darkThemeWebViewHotFix(this, new WebViewUpdateDialogCreator.DialogCallback() { // from class: ru.mail.ui.auth.l
            @Override // ru.mail.android_utils.webview.WebViewUpdateDialogCreator.DialogCallback
            public final void dismissDialog() {
                this.f98209a.lambda$onCreate$1();
            }
        }, new Function1() { // from class: ru.mail.ui.auth.m
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f98210a.lambda$onCreate$2((AlertDialog) obj);
            }
        });
        new DarkThemeUtils(this).setResourceConfigurationUiFlag(this);
        this.mAuthActivityDesign = getAuthActivityDesign();
        ConfigurationWithRawData configuration = ConfigurationRepository.from(getApplication()).getConfiguration();
        this.mIsImapSkipMailruOauth = configuration.isImapSkipMailruOauthSteps();
        this.mIsImapOnly = configuration.isImapOnly();
        try {
            super.onCreate(bundle);
            this.authDeeplinkViewModel = (AuthDeeplinkViewModel) new ViewModelProvider(this).get(AuthDeeplinkViewModel.class);
        } catch (IllegalStateException e10) {
            MailAppAnalytics mailAppAnalyticsAnalytics = MailAppDependencies.analytics(getApplicationContext());
            if (bundle == null) {
                mailAppAnalyticsAnalytics.onRestoreFragmentsError("savedInstanceState is null");
                this.mLogger.e("savedInstanceState is null", e10);
                throw e10;
            }
            ArrayList<String> stringArrayList = bundle.getStringArrayList(FRAGMENTS_BEFORE_RECREATE);
            if (stringArrayList == null || stringArrayList.isEmpty()) {
                mailAppAnalyticsAnalytics.onRestoreFragmentsError("no fragments");
                this.mLogger.e("no fragments", e10);
                throw e10;
            }
            stringArrayList.removeAll(getFragmentsDetails());
            if (stringArrayList.isEmpty()) {
                mailAppAnalyticsAnalytics.onRestoreFragmentsError("All fragments restored successfully");
                this.mLogger.e("All fragments restored successfully", e10);
                throw e10;
            }
            String str = stringArrayList.get(0);
            Iterator it = Arrays.asList("vkc", "FastLogin").iterator();
            do {
                if (!it.hasNext()) {
                    String str2 = "Missing fragment: " + str;
                    mailAppAnalyticsAnalytics.onRestoreFragmentsError(str2);
                    this.mLogger.e(str2, e10);
                    if (safeRecreate()) {
                        return;
                    }
                    this.mLogger.e("safeRecreate false", e10);
                    throw e10;
                }
            } while (!str.contains((String) it.next()));
            mailAppAnalyticsAnalytics.onRestoreFragmentsError("Ignoring crash for whitelisted missing fragment: " + str);
        }
        setLoginChecker(new MailLoginChecker());
        this.mAuthActivityDesign.initialize();
        checkLoginSuggestFragment();
        initAgreementDialogs();
        ActivityCallback.INSTANCE.run(this);
        if (this.socialAuthConfig.isInitEnabled()) {
            this.restoreVkEmailHelper = new RestoreVkEmailNavigator(this, R.id.login_fragment).getHelper();
        } else {
            this.restoreVkEmailHelper = new RestoreVkEmailHelperDelegate(this, R.id.login_fragment);
        }
        this.mLoginLogsViewModel = (LoginLogsViewModel) ViewModelObtainerKt.obtainViewModel(this, LoginLogsViewModel.class, this);
        this.loginLogoViewModel = (LoginLogoViewModel) new ViewModelProvider(this).get(LoginLogoViewModel.class);
        FragmentKt.collectByLifecycle(this, AuthorizationSdk.INSTANCE.getEvent(), new Callback() { // from class: ru.mail.ui.auth.n
            @Override // ru.mail.authorizesdk.util.extensions.Callback
            public final void action(Object obj) {
                this.f98211a.handleAuthorizationSdkEvent((AuthEvent) obj);
            }
        });
        this.esiaAuthViewModel = (EsiaAuthViewModel) new ViewModelProvider(this).get(EsiaAuthViewModel.class);
        if (this.socialAuthConfig.isInitEnabled() && !this.socialAuthConfig.isEsiaEnabled()) {
            listenEsiaAuthViewModelEvents();
        }
        this.mActivityResultLauncher = createQrLoginLauncher();
        listenToWindowsInsets();
    }

    @Override // ru.mail.auth.BaseAuthActivity
    protected void onFailRegistration() {
        if (isMyComTwoWay()) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override // ru.mail.qr_auth.ui.LifecycleCallback
    public void onFragmentStart() {
        listenToWindowsInsets();
    }

    @Override // ru.mail.ui.auth.universal.logscollector.LoginLogsViewModel.View
    public void onLogsSaveError() {
        Toast.makeText(this, R.string.login_logs_save_failed, 1).show();
    }

    @Override // ru.mail.ui.auth.universal.logscollector.LoginLogsViewModel.View
    public void onLogsSaveSuccess() {
        Toast.makeText(this, R.string.login_logs_save_success, 1).show();
    }

    @Override // ru.mail.auth.LoginActivity, ru.mail.auth.BaseAuthActivity, ru.mail.auth.AuthMessageCallback
    public void onMessageHandle(Message message) {
        super.onMessageHandle(message);
        message.accept(new UIAuthVisitor());
    }

    @Override // ru.mail.auth.LoginActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        this.authDeeplinkViewModel.removeDeeplinkListeners();
        super.onPause();
        AlertDialog alertDialog = this.webViewErrorDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.webViewErrorDialog.dismiss();
    }

    @Override // ru.mail.qr_auth.ui.error.SomethingWrongFragment.OnClickListener
    public void onQrScanClick() {
        onContinueClick();
        getSupportFragmentManager().popBackStack();
    }

    @Override // ru.mail.ui.auth.universal.logscollector.LoginLogsViewModel.View
    public void onReadyForSaveLogs() {
        if (!Permission.WRITE_EXTERNAL_STORAGE.isGranted(this)) {
            this.requestPermissionLauncher.launch("android.permission.WRITE_EXTERNAL_STORAGE");
        } else {
            Toast.makeText(this, R.string.logs_save_started, 0).show();
            this.mLoginLogsViewModel.saveLogs();
        }
    }

    @Override // ru.mail.auth.LoginActivity
    protected void onRequestNewAddAccount() {
        if (isUniversalTwoStepSupported()) {
            initStartingUniversalLoginExperiment();
        } else {
            super.onRequestNewAddAccount();
        }
    }

    @Override // ru.mail.auth.LoginActivity, ru.mail.auth.BaseAuthActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.authDeeplinkViewModel.listenDeepLinks();
    }

    @Override // ru.mail.auth.BaseAuthActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putStringArrayList(FRAGMENTS_BEFORE_RECREATE, getFragmentsDetails());
        bundle.putParcelable(LOGIN_LAUNCHER_STATE, this.loginScreenLauncher.getLaunchState());
        super.onSaveInstanceState(bundle);
    }

    @Override // ru.mail.authorizesdk.presentation.servicechooser.ServiceChooserFragmentSDK.OnLongClickEventsSdk
    public void onStartCounter() {
        startLongClickCounter();
    }

    @Override // ru.mail.auth.LoginActivity
    protected boolean openNewLoginIfPossible(String str) {
        if (!this.newAuthorizationSdkConfig.isAnyLoginEnabled()) {
            return false;
        }
        this.loginScreenLauncher.requestShowLoginScreen(str);
        return true;
    }

    @Override // ru.mail.auth.LoginActivity
    protected RegistrationComposeScreen prepareRegistrationScreen(String str, Bundle bundle) {
        return new RegistrationComposeScreen(bundle.getString(MailRuRegistrationActivity.EXTRA_SIGNUP_TOKEN), bundle.containsKey(MailRuRegistrationActivity.EXTRA_KNOWN_FIELDS) ? (SocialAuthKnownFields) bundle.getSerializable(MailRuRegistrationActivity.EXTRA_KNOWN_FIELDS) : null, bundle.getBoolean(LoginActivity.EXTRA_AFTER_SOCIAL_REG, false), bundle.getString(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY), bundle.getString(MailAccountConstants.EXTRA_EMAIL_FOR_SIGNUP), str, getVkAccessTokenFromBundle(bundle));
    }

    @Override // ru.mail.auth.LoginActivity, ru.mail.auth.LoginFragmentInitializer
    public void proxyToRegisterActivity() {
        RegisterReturnParams registerReturnParams = (RegisterReturnParams) ReturnParams.fromIntent(getIntent());
        if (registerReturnParams != null) {
            Intent registrationActivityIntent = Authenticator.getRegistrationActivityIntent(getApplicationContext().getPackageName());
            registerReturnParams.appendRegisterParams(registrationActivityIntent);
            startActivityForResult(registrationActivityIntent, LoginActivity.REQUEST_ADD_NEW_MAILRU_ACCOUNT);
        }
    }

    @Override // ru.mail.auth.LoginActivity
    protected void showServiceChooserFragment() {
        if (getLoginWelcomeFragment() == null) {
            super.showServiceChooserFragment();
        } else {
            changeToServiceChooserFragment();
        }
    }

    @Override // ru.mail.auth.LoginActivity
    protected void startEsiaFlow(String str) {
        ActivityResultCaller actualFragment = getActualFragment();
        if (actualFragment instanceof EsiaFlowProvider) {
            ((EsiaFlowProvider) actualFragment).startEsiaFlow(str);
        }
    }

    public void startExperiment(MailRuLoginActivity mailRuLoginActivity) {
        LoginExperiment.performExperiment(mailRuLoginActivity, new ExperimentAction(mailRuLoginActivity), new DefaultAction(mailRuLoginActivity));
    }

    @Override // ru.mail.auth.LoginActivity
    public void startLoginScreen(EmailServiceResources.MailServiceResources mailServiceResources) {
        if (isDomainOauthBanned(mailServiceResources.getDefaultDomain())) {
            loginDefault(mailServiceResources.getService(), true);
        } else {
            super.startLoginScreen(mailServiceResources);
        }
    }

    @Override // ru.mail.auth.logscollector.LongClickCounterListener
    public void startLongClickCounter() {
        this.mLoginLogsViewModel.startLongClickCounter();
    }

    @Override // ru.mail.ui.auth.MailTwoStepLoginScreenFragment.MyComInitiator
    public void startMyComLogin() {
        startLoginScreen(EmailServiceResources.MailServiceResources.MYCOM);
    }

    @Override // ru.mail.auth.LoginActivity
    protected void startUnblockUserScreen(String str) {
        this.restoreVkEmailHelper.showUnblockWebViewFragment(str, true);
    }

    @Override // ru.mail.auth.LoginActivity
    protected void switchToPassword(String str) {
        Fragment actualFragment = getActualFragment();
        if (actualFragment instanceof MailTwoStepLoginScreenFragment) {
            ((MailTwoStepLoginScreenFragment) actualFragment).showStep(str, TwoStepAuthPresenter.View.Step.PASSWORD);
        }
    }

    @Override // ru.mail.auth.LoginActivity
    protected void switchToValidAccountIfNecessary() {
        try {
            CommonDataManager commonDataManagerFrom = CommonDataManager.from(this);
            MailboxProfile profile = commonDataManagerFrom.getMailboxContext().getProfile();
            if (profile == null) {
                return;
            }
            AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(getApplicationContext());
            if (MailboxProfileUtils.isValid(profile, accountManagerWrapper)) {
                return;
            }
            for (MailboxProfile mailboxProfile : commonDataManagerFrom.getAccounts()) {
                if (MailboxProfileUtils.isValid(mailboxProfile, accountManagerWrapper)) {
                    commonDataManagerFrom.setAccount(mailboxProfile, false);
                    return;
                }
            }
        } catch (Throwable th2) {
            Log.e("MailRuLoginActivity", th2.toString());
        }
    }
}
