package ru.mail.auth;

import android.accounts.Account;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.vk.superapp.SuperappKit;
import dagger.Lazy;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BooleanSupplier;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel;
import ru.mail.auth.authorizesdkinit.AccountManagerProviderImpl;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.auth.composescreens.BeforeRecoveryComposeScreen;
import ru.mail.auth.composescreens.CustomServerComposeScreen;
import ru.mail.auth.composescreens.GoogleNativeComposeScreen;
import ru.mail.auth.composescreens.GoogleWebComposeScreen;
import ru.mail.auth.composescreens.LoginComposeScreen;
import ru.mail.auth.composescreens.OutlookComposeScreen;
import ru.mail.auth.composescreens.PasswordComposeScreen;
import ru.mail.auth.composescreens.RegistrationComposeScreen;
import ru.mail.auth.composescreens.YahooComposeScreen;
import ru.mail.auth.composescreens.YandexComposeScreen;
import ru.mail.auth.loginactivity.LoginActivityDataState;
import ru.mail.auth.loginactivity.LoginActivityEvents;
import ru.mail.auth.loginactivity.LoginActivityViewModel;
import ru.mail.auth.loginactivity.loginscreen.EmailPasswordUpdater;
import ru.mail.auth.ludwig.LudwigCaptchaComposeScreen;
import ru.mail.auth.ludwig.LudwigCaptchaScreen;
import ru.mail.auth.ludwig.LudwigParams;
import ru.mail.auth.mappers.AuthSdkMappersKt;
import ru.mail.auth.mrim.MrimComposeScreen;
import ru.mail.auth.onetimecode.OneTimeCodeComposeScreen;
import ru.mail.auth.request.MailServerParameters;
import ru.mail.auth.restore.RestorePasswordComposeScreen;
import ru.mail.auth.restore.RestorePasswordFragment;
import ru.mail.auth.restore.RestorePasswordParams;
import ru.mail.auth.restore.RestorePasswordScreen;
import ru.mail.auth.restore.UtilsKt;
import ru.mail.auth.secondstep.SecondStepComposeScreen;
import ru.mail.auth.socialauth.SocialAuthComposeScreen;
import ru.mail.auth.sso.SSOComposeScreen;
import ru.mail.auth.vkidbindinlogin.VkBindInLoginComposeScreen;
import ru.mail.auth.vkpassword.VkPasswordComposeScreen;
import ru.mail.auth.webview.BaseSecondStepAuthFragment;
import ru.mail.auth.webview.MailCodeAuthFragment;
import ru.mail.auth.webview.MailSecondStepFragment;
import ru.mail.auth.webview.NativeGoogleSignInFragment;
import ru.mail.auth.webview.OutlookOauth2AccessTokenFragment;
import ru.mail.auth.webview.TokensHolder;
import ru.mail.auth.webview.VKConnectSignInDelegate;
import ru.mail.auth.webview.YahooOauth2AccessTokenFragment;
import ru.mail.auth.webview.YandexOauth2AccessTokenFragment;
import ru.mail.auth.xmail.NewExternalAuthViewModel;
import ru.mail.authorizationsdk.domain.model.Oauth2Params;
import ru.mail.authorizationsdk.external.analytics.AuthAnalyticsSdk;
import ru.mail.authorizationsdk.feature.authactivity.AuthActivityContract;
import ru.mail.authorizationsdk.feature.authactivity.AuthMode;
import ru.mail.authorizationsdk.feature.authactivity.GatedAuthActivityLauncher;
import ru.mail.authorizationsdk.feature.authactivity.result.AuthResult;
import ru.mail.authorizationsdk.feature.externalmigration.presentation.mode.ExternalAccScreenMode;
import ru.mail.authorizationsdk.feature.google.common.presentation.GoogleResult;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookResult;
import ru.mail.authorizationsdk.feature.socialauth.domain.SocialAuthInitMode;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.ReturnParamsHelper;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooResult;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexResult;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.authorizesdk.data.request.common.constants.MailAccountConstantsClass;
import ru.mail.authorizesdk.domain.models.NewAuthSdkConfig;
import ru.mail.authorizesdk.domain.models.NewAuthorizationSdkConfig;
import ru.mail.authorizesdk.domain.models.RestoreVkidFlags;
import ru.mail.authorizesdk.domain.models.SocialAuthConfig;
import ru.mail.authorizesdk.domain.models.oauth2.AuthScreen;
import ru.mail.authorizesdk.domain.models.oauth2.Oauth2Arguments;
import ru.mail.authorizesdk.presentation.accountmigration.ExternalAccMigrationComposeScreen;
import ru.mail.authorizesdk.presentation.accountmigration.ExternalAccMigrationFragment;
import ru.mail.authorizesdk.presentation.accountmigration.ExternalAccMigrationScreen;
import ru.mail.authorizesdk.presentation.common.ErrorDisplaySdk;
import ru.mail.authorizesdk.presentation.common.ProgressBarSdk;
import ru.mail.authorizesdk.presentation.loginactivity.StartRegistrationCompanion;
import ru.mail.authorizesdk.presentation.vkauth.VkAuthSdk;
import ru.mail.authorizesdk.util.extensions.Callback;
import ru.mail.authorizesdk.util.extensions.FragmentKt;
import ru.mail.authorizesdk.util.mvi.navigation.Screen;
import ru.mail.authorizesdk.util.mvi.navigation.ViewEvent;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.credentialsexchanger.core.VkAuthResult;
import ru.mail.credentialsexchanger.data.VkidAuthWithoutPasswordHolder;
import ru.mail.credentialsexchanger.data.VkidBindInLoginHolder;
import ru.mail.credentialsexchanger.data.entity.VkIdAuthSource;
import ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment;
import ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.params.LudwigHost;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutionResult;
import ru.mail.mailbox.cmd.ExecutorSelectors;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.march.viewmodel.ViewModelObtainerKt;
import ru.mail.network.HostProviderWrapperImpl;
import ru.mail.offline.attaches.storage.api.MailOfflineAttachmentPersistedCacheStatus;
import ru.mail.registration.RegistrationActivity;
import ru.mail.registration.Statistic;
import ru.mail.registration.request.SignupPrepareRequest;
import ru.mail.registration.request.XmailRegPrepareCommand;
import ru.mail.registration.ui.ConfirmationActivity;
import ru.mail.registration.ui.CustomProgress;
import ru.mail.registration.ui.DoregistrationFragment;
import ru.mail.social.auth.RestoreVkidStartSource;
import ru.mail.social.auth.presentation.AutoLoginProvider;
import ru.mail.social_auth.domain.SocialAuthDelegate;
import ru.mail.social_auth.domain.SocialAuthEvent;
import ru.mail.social_auth.domain.SocialAuthType;
import ru.mail.social_auth.presentation.SocialAuthSdk;
import ru.mail.ui.accessibility.AccessibilityViewManager;
import ru.mail.ui.accessibility.ChangeAccessibilityActivity;
import ru.mail.ui.utils.AccessibilityUtils;
import ru.mail.ui.view.OnBackPressedCallback;
import ru.mail.util.kotlin.extension.StringKt;
import ru.mail.util.log.Log;
import ru.mail.utils.feature.reversed.matching.MatchingExtKt;
import ru.mail.utils.feature.reversed.matching.ReversedMatchingVkConstants;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@AndroidEntryPoint
public abstract class LoginActivity extends Hilt_LoginActivity implements LoginSuggestFragment.LoginSuggestInterface, LoginSuggestFragment.LoginSuggestSettingsSelector, AnimationStateProvider, BaseAuthFragment.ErrorDisplay, ErrorDisplaySdk, ProgressBarSdk, VkAuthSdk, FragmentNavigatorInterface, LoginFragmentSearcher, LoginFragmentInitializer, LoginStateInfo, StartRegistrationCompanion, ChangeAccessibilityActivity {
    public static final String ACTION_LOGIN_SMS = "com.my.auth.LOGIN_SMS";
    public static final String EXTRA_AFTER_SOCIAL_REG = "after_social_reg";
    public static final String EXTRA_EXTERNAL_RELOGIN = "extra_external_relogin";
    public static final String EXTRA_FRAGMENT_TAG = "fragment_tag";
    private static final String EXTRA_KNOWN_FIELDS = "known_fields";
    public static final String EXTRA_LOGIN_FROM = "extra_login_from";
    public static final String EXTRA_SHOW_EXTERNAL_VK_LOGIN = "extra_from_show_external_vk_login";
    private static final String EXTRA_SIGNUP_TOKEN = "signup_token";
    public static final String EXTRA_VALUE_EXTERNAL_VK_LOGIN_FROM_ADD = "value_need_show_external_vk_login_from_add";
    public static final String EXTRA_VALUE_EXTERNAL_VK_LOGIN_FROM_LOGIN = "value_need_show_external_vk_login_from_login";
    public static final String EXTRA_VALUE_LOGIN_FROM_XMAIL_DEEPLINK = "extra_value_login_from_xmail_deeplink";
    public static final String EXTRA_VKID_AUTH_WITHOUT_PASSWORD = "extra_vkid_auth_without_password";
    public static final String EXTRA_VKID_AUTH_WITHOUT_PASSWORD_ALT_AUTH = "extra_vkid_auth_without_password_alt_auth";
    public static final String EXTRA_VKID_BIND_IN_LOGIN = "extra_vkid_bind_in_login";
    private static final String EXTRA_XMAIL_REG_NOT_GMAIL_ACC = "xmail_reg_not_gmail_acc";
    private static final String EXTRA_XMAIL_REG_PREPARE_RESULT = "xmail_reg_prepare_result";
    public static final String FRAGMENT_TAG = "login_fragment_tag";
    public static final String PREF_KEY_SHOW_MYCOM_SERVICE_TUTORIAL = "tutorial_my_com_service";
    public static final int REQUEST_ADD_ACCOUNT_FROM_CHOOSER = 3463;
    public static final int REQUEST_ADD_NEW_MAILRU_ACCOUNT = 3466;
    public static final int REQUEST_MY_COM_TUTORIAL = 135;
    public static final int REQUEST_SELECT_GOOGLE_ACCOUNT_FROM_CHOOSER = 3465;
    public static final String SERVICE_CHOOSER_FRAGMENT_TAG = "service_chooser_fragment_tag";
    public static final String WELCOME_FRAGMENT_TAG = "login_welcome_fragment_tag";
    private GatedAuthActivityLauncher activityLauncher;

    @Inject
    Analytics analytics;
    private LoginActivityAuthorizationViewModel loginActivityAuthorizationViewModel;
    private LoginActivityViewModel loginActivityViewModel;

    @Inject
    protected Lazy<AccessibilityViewManager> mAccessibilityViewManager;
    protected LoginFlowNavigator mFlowNavigator;
    private boolean mIsActivityRefreshing;
    private CustomProgress mProgressDialog;
    private String mServiceType;

    @Inject
    @Deprecated
    protected NewAuthSdkConfig newAuthSdkConfig;

    @Inject
    protected NewAuthorizationSdkConfig newAuthorizationSdkConfig;
    private NewExternalAuthViewModel newExternalAuthViewModel;

    @Inject
    protected ReturnParamsFactory returnParamsFactory;

    @Inject
    protected SocialAuthConfig socialAuthConfig;
    private static final Log LOG = Log.getLog("LoginActivity");
    private static String IS_VK_BINDIN_LOGIN = "is_vk_binding_login";
    private boolean mIsAnimationEnabled = true;
    private boolean mIsWebAuthNDisablerNotClicked = true;
    private boolean isAutologinLaunched = false;
    private boolean isFromSocialAuth = false;

    /* JADX INFO: renamed from: ru.mail.auth.LoginActivity$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$ru$mail$auth$EmailServiceResources$MailServiceResources;
        static final /* synthetic */ int[] $SwitchMap$ru$mail$credentialsexchanger$core$CredentialsExchanger$SocialBindType;
        static final /* synthetic */ int[] $SwitchMap$ru$mail$social_auth$domain$SocialAuthType;

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
            try {
                $SwitchMap$ru$mail$credentialsexchanger$core$CredentialsExchanger$SocialBindType[CredentialsExchanger.SocialBindType.ESIA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SocialAuthType.values().length];
            $SwitchMap$ru$mail$social_auth$domain$SocialAuthType = iArr2;
            try {
                iArr2[SocialAuthType.VkAuth.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$ru$mail$social_auth$domain$SocialAuthType[SocialAuthType.VkForceAuth.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$ru$mail$social_auth$domain$SocialAuthType[SocialAuthType.VkEmailForwarding.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$ru$mail$social_auth$domain$SocialAuthType[SocialAuthType.VkAutoLogin.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$ru$mail$social_auth$domain$SocialAuthType[SocialAuthType.VkRestore.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[EmailServiceResources.MailServiceResources.values().length];
            $SwitchMap$ru$mail$auth$EmailServiceResources$MailServiceResources = iArr3;
            try {
                iArr3[EmailServiceResources.MailServiceResources.MYCOM.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$ru$mail$auth$EmailServiceResources$MailServiceResources[EmailServiceResources.MailServiceResources.GOOGLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    class LoginUIVisitor extends BaseMessageVisitor {
        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Void lambda$startGoogleAuth$0(Message message) {
            LoginActivity.this.startGoogleAuthScreen(message.getData());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Void lambda$startOutlookAuth$3(Message message) {
            LoginActivity.this.startOutlookAuthScreen(message.getData());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Void lambda$startYahooAuth$1(Message message) {
            LoginActivity.this.startYahooAuthScreen(message.getData());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Void lambda$startYandexAuth$2(Message message) {
            LoginActivity.this.startYandexAuthScreen(message.getData());
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void processSignupPrepareResponse(CommandStatus.OK<?> ok, String str) {
            Object data = ok.getData();
            if (data instanceof SignupPrepareRequest.Response) {
                SignupPrepareRequest.Response response = (SignupPrepareRequest.Response) data;
                SocialLoginInfoHolder.setSignupPrepareResult(response);
                Bundle bundle = new Bundle();
                bundle.putString("signup_token", response.getSignupToken());
                bundle.putSerializable("known_fields", response.getKnownFields());
                bundle.putBoolean(LoginActivity.EXTRA_AFTER_SOCIAL_REG, response.getAfterSocialLogin());
                bundle.putString(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY, response.getType());
                LoginActivity.this.startSocialRegistration(bundle, str);
            }
        }

        private void sendSignupPrepareRequest(String str, final String str2) {
            new SignupPrepareRequest(LoginActivity.this.getApplicationContext(), str, AuthenticatorConfig.getInstance().getMigrateToPostConfig().getIs12146Enabled()).execute(ExecutorSelectors.defaultSelector()).observe(Schedulers.immediate(), new ObservableFuture.Observer<CommandStatus<?>>() { // from class: ru.mail.auth.LoginActivity.LoginUIVisitor.1
                @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
                public void onDone(CommandStatus<?> commandStatus) {
                    if ((commandStatus instanceof CommandStatus.OK) && commandStatus.hasData()) {
                        LoginUIVisitor.this.processSignupPrepareResponse((CommandStatus.OK) commandStatus, str2);
                    }
                }

                @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
                public void onCancelled() {
                }

                @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
                public void onError(Exception exc) {
                }
            });
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void authenticateOauth(Message message) {
            LoginActivity.this.onAuthWebView((TokensHolder) message.getObj(), message.getData());
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void goToLogin(Message message) {
            Object obj = message.getObj();
            if (obj instanceof VkAuthResult.GoToLogin) {
                VkAuthResult.GoToLogin goToLogin = (VkAuthResult.GoToLogin) obj;
                LoginActivity.this.startLoginScreenForBind(goToLogin.getBindToken(), goToLogin.getSocialBindType().getStringToken(), goToLogin.getVkToken());
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void goToSignup(Message message) {
            Object obj = message.getObj();
            if (obj instanceof VkAuthResult.GoToSignup) {
                VkAuthResult.GoToSignup goToSignup = (VkAuthResult.GoToSignup) obj;
                CredentialsExchanger.SocialBindType socialBindType = goToSignup.getSocialBindType();
                String bindToken = goToSignup.getBindToken();
                SocialLoginInfoHolder.setBindState(new SocialLoginInfoHolder.BindState(bindToken, socialBindType));
                boolean zIsEmpty = bindToken.isEmpty();
                String vkToken = goToSignup.getVkToken();
                if (zIsEmpty) {
                    LoginActivity.this.startSocialRegistration(Bundle.EMPTY, vkToken);
                } else {
                    sendSignupPrepareRequest(goToSignup.getSocialBindType().getStringToken(), vkToken);
                }
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void loginResult(Message message) {
            Object obj = message.getObj();
            if (obj instanceof VkAuthResult.LoginResult) {
                LoginActivity.this.analytics.onSuccessDefaultOrForceVKIDAuth();
                LoginActivity.this.getLoginFragment().onOAuthVKConnect((VkAuthResult.LoginResult) obj);
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void onExternalAuthProhibited(Message message) {
            String string = message.getData().getString("authAccount");
            if (LoginActivity.this.newAuthorizationSdkConfig.isExternalAccMigrationEnabled()) {
                LoginActivity.this.loginActivityAuthorizationViewModel.navigateTo(new ExternalAccMigrationComposeScreen(string, ExternalAccScreenMode.OTHER.getAnalyticName()), null);
            } else {
                LoginActivity.this.navigateToScreen(new ExternalAccMigrationScreen(string, ExternalAccMigrationFragment.Mode.OTHER), null);
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void openDialog(Message message) {
            super.openDialog(message);
            Object obj = message.getObj();
            if (obj instanceof DialogFragment) {
                DialogFragment dialogFragment = (DialogFragment) obj;
                LoginActivity.this.getSupportFragmentManager().beginTransaction().add(dialogFragment, dialogFragment.getTag()).commitAllowingStateLoss();
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void openFragment(Message message) {
            Object obj = message.getObj();
            if (obj instanceof Fragment) {
                Fragment fragment = (Fragment) obj;
                LoginActivity.this.changeToFragment(fragment, true, true, fragment.getTag());
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void openFragmentSingleTop(Message message) {
            Object obj = message.getObj();
            if (obj instanceof Fragment) {
                String string = message.getData().getString(LoginActivity.EXTRA_FRAGMENT_TAG);
                LoginActivity.this.changeToFragmentSingleTop((Fragment) obj, true, string);
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void popBackStack(Message message) {
            super.popBackStack(message);
            LoginActivity.this.popBackStackIfAllowed();
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void showEsiaAuth(Message message) {
            LoginActivity.this.startEsiaFlow(message.getData().getString("authAccount"));
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void showMrimDialog(Message message) {
            String string = message.getData().getString("authAccount");
            if (string == null) {
                string = "";
            }
            LoginActivity.this.loginActivityAuthorizationViewModel.navigateTo(new MrimComposeScreen(string), null);
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void showVkidBindInLogin(Message message) {
            String string = message.getData().getString("authAccount");
            if (LoginActivity.this.newAuthorizationSdkConfig.isVkBindInLoginEnable()) {
                LoginActivity.this.loginActivityAuthorizationViewModel.navigateTo(new VkBindInLoginComposeScreen(string), null);
            } else {
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.changeToFragment(loginActivity.createVkBindInLoginFragment(string), true, true, LoginActivity.FRAGMENT_TAG);
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startCodeAuth(Message message) {
            if (!LoginActivity.this.newAuthorizationSdkConfig.isOneTimeCodeEnabled()) {
                LoginActivity.this.changeToCodeAuthFragment(message.getData());
                return;
            }
            String string = message.getData().getString("authAccount");
            String string2 = message.getData().getString(MailCodeAuthFragment.EXTRA_FROM);
            if (TextUtils.isEmpty(string)) {
                LoginActivity.LOG.e("error", new IllegalStateException("Can't find login or url for oneTimeCode"));
                LoginActivity.this.showAuthErrorToast("Can't find login or url for oneTimeCode");
            } else {
                LoginActivity.this.loginActivityAuthorizationViewModel.navigateTo(new OneTimeCodeComposeScreen(string, string2), null);
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startDoregistration(Message message) {
            LoginActivity.this.toDoRegistrationScreen(message.getData());
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startGoogleAuth(final Message message) {
            LoginActivity.this.startNewExternalAuth(message.getData().getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT), message.getData(), new Function0() { // from class: ru.mail.auth.f2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f80750a.lambda$startGoogleAuth$0(message);
                }
            });
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startLoading(Message message) {
            super.startLoading(message);
            LoginActivity.this.showProgress();
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startLoginScreen(Message message) {
            LoginActivity.this.startLoginScreen((EmailServiceResources.MailServiceResources) message.getObj());
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startLoginScreenForBind(Message message) {
            LoginActivity.this.startLoginScreenForBind(message.getData());
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startOutlookAuth(final Message message) {
            LoginActivity.this.startNewExternalAuth(message.getData().getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT), message.getData(), new Function0() { // from class: ru.mail.auth.c2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f80739a.lambda$startOutlookAuth$3(message);
                }
            });
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startRestorePassword(Message message) {
            RestorePasswordParams restorePasswordParams = RestorePasswordParams.INSTANCE;
            Uri restoreUri = restorePasswordParams.getRestoreUri(message.getData());
            String restoreLogin = restorePasswordParams.getRestoreLogin(message.getData());
            if (restoreUri == null || restoreLogin == null) {
                LoginActivity.LOG.e("Restore password: Unexpected null restoreUri=" + restoreUri + "; loginToRestore=" + restoreLogin);
                return;
            }
            String tsaCookie = !TextUtils.isEmpty(restoreLogin) ? MailSecondStepFragment.getTsaCookie(LoginActivity.this.getBaseContext(), restoreLogin, null) : "";
            if (TextUtils.isEmpty(tsaCookie)) {
                tsaCookie = MailSecondStepFragment.getAnyTsaCookie(LoginActivity.this.getBaseContext(), null);
            }
            if (!AuthenticatorConfig.getInstance().isRestorePasswordWebViewEnabled()) {
                try {
                    LoginActivity.this.startActivity(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, restoreUri).addFlags(SQLiteDatabase.CREATE_IF_NECESSARY));
                    return;
                } catch (ActivityNotFoundException e10) {
                    LoginActivity.LOG.e("error", e10);
                    LoginActivity loginActivity = LoginActivity.this;
                    loginActivity.showAuthErrorToast(loginActivity.getString(ru.mail.Authenticator.R.string.no_browser_to_open_link));
                    return;
                }
            }
            Uri.Builder builderBuildUpon = restoreUri.buildUpon();
            boolean z10 = LoginActivity.this.newAuthorizationSdkConfig.getSocialAuthConfig().isRestoreVkidEnabled() || LoginActivity.this.newAuthorizationSdkConfig.getSocialAuthConfig().isRestoreVkidInOldAuthEnabled();
            boolean isRebind = restorePasswordParams.getIsRebind(message.getData());
            UtilsKt.appendRestoreParams(builderBuildUpon, restoreLogin, z10, isRebind);
            String string = builderBuildUpon.toString();
            LoginActivity.this.analytics.onRestorePasswordOpened(!TextUtils.isEmpty(restoreLogin));
            if (LoginActivity.this.newAuthorizationSdkConfig.isRestorePasswordEnabled()) {
                LoginActivity.this.loginActivityAuthorizationViewModel.navigateTo(new RestorePasswordComposeScreen(restoreLogin, isRebind), null);
            } else {
                LoginActivity.this.navigateToScreen(new RestorePasswordScreen(string, restoreLogin, tsaCookie, isRebind), null);
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startSSOAuth(Message message) {
            Bundle data = message.getData();
            LoginActivity.this.loginActivityAuthorizationViewModel.navigateTo(new SSOComposeScreen(data.getString("authAccount"), data.getString(MailAccountConstantsClass.EXTRA_OAUTH2_AUTH_URL)), new LoginActivityDataState.Common(data));
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startSecondStep(Message message) {
            Bundle data = message.getData();
            String ludwigToken = LudwigParams.INSTANCE.getLudwigToken(data);
            if (LoginActivity.this.newAuthSdkConfig.getLudvigCaptchaConfig().isLudvigCaptchaEnabled() && !TextUtils.isEmpty(ludwigToken)) {
                LoginActivityDataState.LudwigCaptcha ludwigCaptcha = new LoginActivityDataState.LudwigCaptcha(ludwigToken, data.getString("authAccount"), data.getString("password"), Authenticator.Type.valueOf(message.getData().getString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE)), message.getData().getBundle(BaseAuthActivity.EXTRA_BUNDLE));
                LoginActivity.this.navigateToScreen(new LudwigCaptchaScreen(ludwigToken), ludwigCaptcha);
                return;
            }
            if (LoginActivity.this.newAuthorizationSdkConfig.getLudvigCaptchaConfig().isLudvigCaptchaEnabled() && !TextUtils.isEmpty(ludwigToken)) {
                LoginActivityDataState.LudwigCaptcha ludwigCaptcha2 = new LoginActivityDataState.LudwigCaptcha(ludwigToken, data.getString("authAccount"), data.getString("password"), Authenticator.Type.valueOf(message.getData().getString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE)), message.getData().getBundle(BaseAuthActivity.EXTRA_BUNDLE));
                LoginActivity.this.loginActivityAuthorizationViewModel.navigateTo(new LudwigCaptchaComposeScreen(ludwigToken), ludwigCaptcha2);
                return;
            }
            String string = data.getString("authAccount");
            String string2 = data.getString("url");
            boolean zIsAnyXmailMigration = LoginActivity.this.isAnyXmailMigration(data);
            if (zIsAnyXmailMigration) {
                data.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.DEFAULT.toString());
            }
            if (!LoginActivity.this.newAuthorizationSdkConfig.getSecondStepConfig().isSecondStepEnabled() || string == null || string2 == null) {
                LoginActivity loginActivity = LoginActivity.this;
                loginActivity.changeToFragment(loginActivity.createSecondStepFragment(), data);
            } else {
                LoginActivity.this.loginActivityAuthorizationViewModel.navigateTo(new SecondStepComposeScreen(string, string2, data.getString(MailSecondStepFragment.EXT_SECSTEP_COOKIE_HEADER), zIsAnyXmailMigration, data.getString("login_extra_xmail_migration_from")), new LoginActivityDataState.Common(data));
            }
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startSecondStepNew(Message message) {
            LoginActivity.this.startVkToSecondStep();
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startSendServerSettings(Message message) {
            LoginActivity.this.getLoginFragment().sendMailServerSettings((MailServerParameters) message.getObj());
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startVKConnectAuth(Message message) {
            Bundle data = message.getData();
            boolean z10 = data.getBoolean(LoginActivity.EXTRA_VKID_AUTH_WITHOUT_PASSWORD, false);
            boolean z11 = data.getBoolean(LoginActivity.EXTRA_VKID_AUTH_WITHOUT_PASSWORD_ALT_AUTH, true);
            boolean z12 = data.getBoolean(LoginActivity.EXTRA_VKID_BIND_IN_LOGIN, false);
            VkidAuthWithoutPasswordHolder.setVkidAuthWithoutPassword(z10);
            VkidBindInLoginHolder.setVkidBindInLogin(z12);
            LoginActivity.this.startVKConnectAuth(z10, z11, z12);
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startVkPasswordAuth(Message message) {
            Bundle data = message.getData();
            LoginActivity.this.loginActivityAuthorizationViewModel.navigateTo(new VkPasswordComposeScreen(data.getString("authAccount"), data.getString(MailAccountConstantsClass.EXTRA_OAUTH2_AUTH_URL)), new LoginActivityDataState.Common(data));
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startYahooAuth(final Message message) {
            LoginActivity.this.startNewExternalAuth(message.getData().getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT), message.getData(), new Function0() { // from class: ru.mail.auth.d2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f80745a.lambda$startYahooAuth$1(message);
                }
            });
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void startYandexAuth(final Message message) {
            LoginActivity.this.startNewExternalAuth(message.getData().getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT), message.getData(), new Function0() { // from class: ru.mail.auth.e2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f80748a.lambda$startYandexAuth$2(message);
                }
            });
        }

        @Override // ru.mail.auth.BaseMessageVisitor, ru.mail.auth.Message.Visitor
        public void stopLoading(Message message) {
            super.stopLoading(message);
            LoginActivity.this.hideProgress();
        }

        private LoginUIVisitor() {
        }
    }

    public static /* synthetic */ Unit G(Function0 function0) {
        function0.invoke();
        return null;
    }

    private void checkFinishOnNonSupportedFlow() {
        if (this.newAuthorizationSdkConfig.isAnyLoginEnabled()) {
            finish();
        }
    }

    private void clearBackStack() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        this.mIsAnimationEnabled = false;
        while (supportFragmentManager.getBackStackEntryCount() > 0) {
            supportFragmentManager.popBackStackImmediate();
        }
        this.mIsAnimationEnabled = true;
    }

    private void clearIntentData() {
        getIntent().putExtra(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, "");
        getIntent().putExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN, "");
        getIntent().putExtra(Authenticator.IS_LOGIN_EXISTING_ACCOUNT, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishIfNeed() {
        if (isXmailMigrationFromNotLogin(getIntent().getExtras())) {
            finish();
        }
    }

    @Nullable
    private ForceVkidAuthListener getForceVkidAuthListener() {
        ActivityResultCaller activityResultCallerFindFragmentByTag = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (activityResultCallerFindFragmentByTag instanceof ForceVkidAuthListener) {
            return (ForceVkidAuthListener) activityResultCallerFindFragmentByTag;
        }
        return null;
    }

    static Intent getLoginActivityIntent(String str, String str2) {
        Intent intent = new Intent();
        intent.setPackage(str2);
        if (str == null || !str.equals("LOGIN_TO_MYCOM_DOMAIN")) {
            intent.setAction(MailAccountConstants.ACTION_LOGIN);
            return intent;
        }
        intent.setAction(ACTION_LOGIN_SMS);
        return intent;
    }

    private String getLoginSuggestFragmentTag() {
        return getString(ru.mail.Authenticator.R.string.login_suggestions_fragment_tag);
    }

    private Fragment getOrCreateServiceChooserFragment() {
        Fragment serviceChooserFragment = getServiceChooserFragment();
        return serviceChooserFragment == null ? createServiceChooserFragment() : serviceChooserFragment;
    }

    private boolean isNeedOauthScreen(String str) {
        return Authenticator.Type.OUTLOOK_OAUTH.toString().equals(str) || Authenticator.Type.YAHOO_OAUTH.toString().equals(str) || Authenticator.Type.YANDEX_OAUTH.toString().equals(str);
    }

    private boolean isUserLoggedInResult(int i10, int i11) {
        if (i11 == -1 || i11 == 14) {
            return i10 == 3465 || i10 == 3466;
        }
        return false;
    }

    private boolean isXmailMigrationFromNotLogin(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        String string = bundle.getString("login_extra_xmail_migration_from");
        return !TextUtils.isEmpty(string) && MailAccountConstants.isXmailMigrationExceptLogin(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(AuthResult authResult) {
        if (authResult == null) {
            LOG.d("LoginActivity result is null");
        } else {
            this.loginActivityAuthorizationViewModel.onAuthorizeSdkResult(authResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$1() {
        LOG.w("AuthActivity launch aborted: auth SDK init timed out");
        showAuthErrorToast(getString(ru.mail.authorizationsdk.R.string.ludwig_sdk_something_went_wrong_message));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$10(String str, Bundle bundle) {
        processResultXmailMigrationNativeReg(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onCreate$2() {
        return this.newAuthorizationSdkConfig.isLoginVkEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3(Screen screen) {
        if (!(screen instanceof ExternalAccMigrationComposeScreen)) {
            navigateToScreen(screen, null);
        } else {
            this.loginActivityAuthorizationViewModel.navigateTo((ExternalAccMigrationComposeScreen) screen, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$4(Boolean bool) {
        if (bool.booleanValue()) {
            showProgress();
        } else {
            hideProgress();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$5(Boolean bool) {
        if (bool.booleanValue()) {
            showProgress();
        } else {
            hideProgress();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$6(ViewEvent.Navigation navigation) {
        Screen<?> screen = navigation.getScreen();
        if (screen instanceof SecondStepComposeScreen) {
            SecondStepComposeScreen secondStepComposeScreen = (SecondStepComposeScreen) screen;
            this.activityLauncher.launch(new AuthMode.SecondStep(secondStepComposeScreen.getLogin(), secondStepComposeScreen.getUrl(), secondStepComposeScreen.getSecondStepCookieHeader(), secondStepComposeScreen.getIsXmailMigration(), secondStepComposeScreen.getParamXmailFrom()));
            return;
        }
        if (screen instanceof LudwigCaptchaComposeScreen) {
            this.activityLauncher.launch(new AuthMode.Ludwig(((LudwigCaptchaComposeScreen) screen).getLudwigToken()));
            return;
        }
        if (screen instanceof OneTimeCodeComposeScreen) {
            OneTimeCodeComposeScreen oneTimeCodeComposeScreen = (OneTimeCodeComposeScreen) screen;
            this.activityLauncher.launch(new AuthMode.OneTimeCode(oneTimeCodeComposeScreen.getEmail(), oneTimeCodeComposeScreen.getFrom()));
            return;
        }
        if (screen instanceof ExternalAccMigrationComposeScreen) {
            ExternalAccMigrationComposeScreen externalAccMigrationComposeScreen = (ExternalAccMigrationComposeScreen) screen;
            String email = externalAccMigrationComposeScreen.getEmail();
            String mode = externalAccMigrationComposeScreen.getMode();
            hideActionBar();
            this.activityLauncher.launch(new AuthMode.ExternalAccMigrationPopup(mode, email, getSupportActionBar() != null));
            return;
        }
        if (screen instanceof YahooComposeScreen) {
            this.activityLauncher.launch(new AuthMode.Yahoo(((YahooComposeScreen) screen).getHint()));
            return;
        }
        if (screen instanceof YandexComposeScreen) {
            this.activityLauncher.launch(new AuthMode.Yandex(((YandexComposeScreen) screen).getHint()));
            return;
        }
        if (screen instanceof OutlookComposeScreen) {
            this.activityLauncher.launch(new AuthMode.Outlook(((OutlookComposeScreen) screen).getHint()));
            return;
        }
        if (screen instanceof GoogleNativeComposeScreen) {
            GoogleNativeComposeScreen googleNativeComposeScreen = (GoogleNativeComposeScreen) screen;
            this.activityLauncher.launch(new AuthMode.GoogleNative(googleNativeComposeScreen.getHint(), googleNativeComposeScreen.getXmailMigrationFrom()));
            return;
        }
        if (screen instanceof GoogleWebComposeScreen) {
            GoogleWebComposeScreen googleWebComposeScreen = (GoogleWebComposeScreen) screen;
            this.activityLauncher.launch(new AuthMode.GoogleWeb(googleWebComposeScreen.getHint(), googleWebComposeScreen.getXmailMigrationFrom()));
            return;
        }
        if (screen instanceof CustomServerComposeScreen) {
            CustomServerComposeScreen customServerComposeScreen = (CustomServerComposeScreen) screen;
            LOG.e("CustomServerComposeScreen");
            this.activityLauncher.launch(new AuthMode.CustomServer(customServerComposeScreen.getEmail(), customServerComposeScreen.getPassword(), customServerComposeScreen.getServiceType(), customServerComposeScreen.getIsLocalImapFlow()));
            return;
        }
        if (screen instanceof LoginComposeScreen) {
            LoginComposeScreen loginComposeScreen = (LoginComposeScreen) screen;
            String email2 = loginComposeScreen.getEmail();
            String serviceType = loginComposeScreen.getServiceType();
            boolean zIsManualLogout = loginComposeScreen.getIsManualLogout();
            boolean zIsDeeplinkOpened = loginComposeScreen.getIsDeeplinkOpened();
            if (loginComposeScreen.getIsLoginVkMode()) {
                this.activityLauncher.launch(new AuthMode.VkLogin(email2, "", serviceType, zIsManualLogout, zIsDeeplinkOpened, false));
                return;
            } else {
                this.activityLauncher.launch(new AuthMode.Login(email2, "", serviceType, zIsManualLogout, zIsDeeplinkOpened, false));
                return;
            }
        }
        if (screen instanceof PasswordComposeScreen) {
            PasswordComposeScreen passwordComposeScreen = (PasswordComposeScreen) screen;
            SocialLoginInfoHolder.BindState bindState = passwordComposeScreen.getBindState();
            if (bindState != null) {
                this.activityLauncher.launch(new AuthMode.PasswordWithBind(passwordComposeScreen.getEmail(), passwordComposeScreen.getServiceType(), passwordComposeScreen.getIsSupportRestore(), bindState.getBindToken(), bindState.getType().getStringToken()));
                return;
            } else {
                this.activityLauncher.launch(new AuthMode.Password(passwordComposeScreen.getEmail(), passwordComposeScreen.getServiceType(), "", passwordComposeScreen.getIsSupportRestore()));
                return;
            }
        }
        if (screen instanceof BeforeRecoveryComposeScreen) {
            BeforeRecoveryComposeScreen beforeRecoveryComposeScreen = (BeforeRecoveryComposeScreen) screen;
            this.activityLauncher.launch(new AuthMode.BeforeRecovery(beforeRecoveryComposeScreen.getEmail(), beforeRecoveryComposeScreen.getFailUrl()));
            return;
        }
        if (screen instanceof RestorePasswordComposeScreen) {
            RestorePasswordComposeScreen restorePasswordComposeScreen = (RestorePasswordComposeScreen) screen;
            this.activityLauncher.launch(new AuthMode.RestorePassword(restorePasswordComposeScreen.getLoginToRestore(), restorePasswordComposeScreen.getIsRebind()));
            return;
        }
        if (screen instanceof SSOComposeScreen) {
            SSOComposeScreen sSOComposeScreen = (SSOComposeScreen) screen;
            this.activityLauncher.launch(new AuthMode.SSO(sSOComposeScreen.getLogin(), sSOComposeScreen.getUrl()));
            return;
        }
        if (screen instanceof VkPasswordComposeScreen) {
            VkPasswordComposeScreen vkPasswordComposeScreen = (VkPasswordComposeScreen) screen;
            this.activityLauncher.launch(new AuthMode.VkPassword(vkPasswordComposeScreen.getLogin(), vkPasswordComposeScreen.getUrl()));
            return;
        }
        if (screen instanceof MrimComposeScreen) {
            this.activityLauncher.launch(new AuthMode.MrimDialog(((MrimComposeScreen) screen).getEmail()));
        }
        if (screen instanceof SocialAuthComposeScreen) {
            this.activityLauncher.launch(new AuthMode.SocialAuth(((SocialAuthComposeScreen) screen).getMode()));
            return;
        }
        if (screen instanceof VkBindInLoginComposeScreen) {
            this.activityLauncher.launch(new AuthMode.VkBindInLogin(((VkBindInLoginComposeScreen) screen).getLogin()));
        } else if (screen instanceof RegistrationComposeScreen) {
            RegistrationComposeScreen registrationComposeScreen = (RegistrationComposeScreen) screen;
            this.activityLauncher.launch(new AuthMode.Registration(registrationComposeScreen.getSignupToken(), registrationComposeScreen.getKnownFields() != null ? AuthSdkMappersKt.parseAuthSdkModel(registrationComposeScreen.getKnownFields().getFieldValues()) : null, registrationComposeScreen.getAfterSocialReg(), registrationComposeScreen.getSocailBindType(), registrationComposeScreen.getEmailForSignup(), registrationComposeScreen.getFrom(), registrationComposeScreen.getVkAccessToken()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$7(ViewEvent viewEvent) {
        this.loginActivityViewModel.getNavEvent().setResultListener(this);
        if (viewEvent instanceof ViewEvent.Navigation) {
            Screen<?> screen = ((ViewEvent.Navigation) viewEvent).getScreen();
            if (screen instanceof LudwigCaptchaScreen) {
                LudwigCaptchaScreen ludwigCaptchaScreen = (LudwigCaptchaScreen) screen;
                changeToFragment(WebCaptchaFragment.newInstance(ludwigCaptchaScreen.getLudwigToken(), null, this.newAuthSdkConfig.getLudvigCaptchaConfig().isSetLudwigTestDomain() ? new LudwigHost("https://access.mini-mail.ru?mp=android", "https://access.mini-mail.ru/") : null, this.newAuthSdkConfig.getLudvigCaptchaConfig().isDomStorageEnabled(), this.newAuthSdkConfig.getLudvigCaptchaConfig().isTextZoomDisabled()), true, true, FRAGMENT_TAG);
            } else if (screen instanceof RestorePasswordScreen) {
                RestorePasswordScreen restorePasswordScreen = (RestorePasswordScreen) screen;
                changeToFragment(RestorePasswordFragment.INSTANCE.newInstance(restorePasswordScreen.getRestoreUrl(), restorePasswordScreen.getLoginToRestore(), restorePasswordScreen.getTsaCookie(), restorePasswordScreen.getIsRebind()), true, true, FRAGMENT_TAG);
            } else if (screen instanceof ExternalAccMigrationScreen) {
                ExternalAccMigrationScreen externalAccMigrationScreen = (ExternalAccMigrationScreen) screen;
                String email = externalAccMigrationScreen.getEmail();
                ExternalAccMigrationFragment externalAccMigrationFragmentNewInstance = ExternalAccMigrationFragment.INSTANCE.newInstance(externalAccMigrationScreen.getMode(), email, true, getSupportActionBar() != null);
                hideActionBar();
                changeToFragment(externalAccMigrationFragmentNewInstance, true, true, FRAGMENT_TAG);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareVkAuth$11(Boolean bool) {
        if (bool.booleanValue()) {
            showProgress();
        } else {
            hideProgress();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$startVKAutoLogin$14() {
        this.isAutologinLaunched = true;
        this.loginActivityAuthorizationViewModel.navigateTo(new SocialAuthComposeScreen(SocialAuthInitMode.AutoLogin.INSTANCE), null);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$startVKConnectAuth$13(boolean z10, boolean z11, boolean z12) {
        this.loginActivityAuthorizationViewModel.navigateTo(new SocialAuthComposeScreen(new SocialAuthInitMode.StartForceVkAuth(z10, z11, z12)), null);
        return null;
    }

    private void loginInternal(Fragment fragment, String str, boolean z10) {
        if (fragment instanceof AuthScreen) {
            clearIntentData();
            changeToFragment(fragment, z10);
            return;
        }
        this.mServiceType = str;
        Bundle bundle = new Bundle();
        bundle.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, str);
        AuthUtil.proxyStringParam(bundle, getIntent().getExtras(), AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE);
        AuthUtil.proxyStringParam(bundle, getIntent().getExtras(), Authenticator.EXTRA_ADD_ACCOUNT_LOGIN);
        AuthUtil.proxyBooleanParam(bundle, getIntent().getExtras(), Authenticator.IS_LOGIN_EXISTING_ACCOUNT);
        AuthUtil.proxyByteArray(bundle, getIntent().getExtras(), Authenticator.AUTH_RESTORE_PARAMS);
        AuthUtil.proxyBooleanParam(bundle, getIntent().getExtras(), Authenticator.IS_HIDE_UI_ON_START);
        AuthUtil.proxyBooleanParam(bundle, getIntent().getExtras(), "is_maual_logout");
        AuthUtil.proxyStringParam(bundle, getIntent().getExtras(), "login_extra_xmail_migration_from");
        AuthUtil.proxyBooleanParam(bundle, getIntent().getExtras(), ReversedMatchingVkConstants.IS_FROM_VK_APP);
        AuthUtil.proxyBooleanParam(bundle, getIntent().getExtras(), Authenticator.SHOULD_CHECK_LOGIN_STATUS, true);
        bundle.putBoolean(Authenticator.IS_VK_SIGN_IN_DELEGATE_DISABLED, this.socialAuthConfig.isInitEnabled());
        if (fragment.getArguments() != null) {
            bundle.putAll(fragment.getArguments());
        }
        fragment.setArguments(bundle);
        clearIntentData();
        changeToFragment(fragment, z10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void navigateToScreen(Screen screen, LoginActivityDataState loginActivityDataState) {
        this.loginActivityViewModel.navigateTo(screen, loginActivityDataState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAuthWebView(TokensHolder tokensHolder, Bundle bundle) {
        getLoginFragment().onOAuthWebView(tokensHolder, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void popBackStackIfAllowed() {
        boolean booleanExtra = getIntent().getBooleanExtra(IS_VK_BINDIN_LOGIN, false);
        if (isWebAuthNDisablerPromoClicked() || booleanExtra) {
            return;
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = supportFragmentManager.getFragments();
        if (fragments.isEmpty() || (fragments.get(fragments.size() - 1) instanceof BaseLoginScreenFragment) || supportFragmentManager.isStateSaved()) {
            return;
        }
        supportFragmentManager.popBackStackImmediate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: processResultXmailMigration, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void lambda$onCreate$9(Bundle bundle, String str) {
        if (bundle == null || TextUtils.isEmpty(bundle.getString("login_extra_xmail_migration_from"))) {
            return;
        }
        if (MailAccountConstants.isXmailMigrationExceptLogin(bundle.getString("login_extra_xmail_migration_from"))) {
            finish();
        } else if (Objects.equals(str, NativeGoogleSignInFragment.REQUEST_KEY)) {
            getSupportFragmentManager().popBackStackImmediate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: processSignupPrepareResponse, reason: merged with bridge method [inline-methods] */
    public void lambda$sendSignupPrepareRequest$15(ExecutionResult<CommandStatus<?>> executionResult, @Nullable String str) {
        if (executionResult instanceof ExecutionResult.Success) {
            CommandStatus commandStatus = (CommandStatus) ((ExecutionResult.Success) executionResult).getResult();
            if (commandStatus.hasData()) {
                Object data = commandStatus.getData();
                if (data instanceof SignupPrepareRequest.Response) {
                    SignupPrepareRequest.Response response = (SignupPrepareRequest.Response) data;
                    SocialLoginInfoHolder.setSignupPrepareResult(response);
                    Bundle bundle = new Bundle();
                    bundle.putString("signup_token", response.getSignupToken());
                    bundle.putSerializable("known_fields", response.getKnownFields());
                    bundle.putBoolean(EXTRA_AFTER_SOCIAL_REG, response.getAfterSocialLogin());
                    bundle.putString(MailAccountConstants.MAIL_RU_REG_ACT_VK_ACCESS_TOKEN_KEY, str);
                    bundle.putString(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY, response.getType());
                    bundle.putBoolean(Authenticator.IS_VK_SIGN_IN_DELEGATE_DISABLED, this.socialAuthConfig.isInitEnabled());
                    startRegistrationCompat(AuthSource.BIND_EMAIL, bundle);
                }
            }
        }
    }

    private void refreshActivityState() {
        clearBackStack();
        onRequestNewAddAccount();
        this.analytics.loginView(getExtraFrom());
    }

    private void sendSignupPrepareRequest(String str, @Nullable final String str2) {
        new SignupPrepareRequest(getApplicationContext(), str, AuthenticatorConfig.getInstance().getMigrateToPostConfig().getIs12146Enabled()).execute(ExecutorSelectors.defaultSelector()).observeDoneResult(Schedulers.immediate(), new ObservableFuture.ResultDoneObserver() { // from class: ru.mail.auth.u1
            @Override // ru.mail.mailbox.cmd.ObservableFuture.ResultDoneObserver
            public final void onDone(ExecutionResult executionResult) {
                this.f80836a.lambda$sendSignupPrepareRequest$15(str2, executionResult);
            }
        });
    }

    private void setResultForActualFragment(int i10, int i11, Intent intent) {
        Fragment loginSuggestFragment = LoginSuggestFragment.isLoginSuggestRequest(i10) ? getLoginSuggestFragment() : getActualFragment();
        if (loginSuggestFragment != null) {
            loginSuggestFragment.onActivityResult(i10, i11, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAuthErrorToast(String str) {
        hideProgress();
        onAuthErrorCall();
        Toast.makeText(this, str, 0).show();
    }

    @Deprecated
    private void socialAuthResult(ru.mail.social_auth.domain.AuthResult authResult) {
        this.isFromSocialAuth = true;
        if (authResult instanceof ru.mail.social_auth.domain.AuthResult.LoginResult) {
            ru.mail.social_auth.domain.AuthResult.LoginResult loginResult = (ru.mail.social_auth.domain.AuthResult.LoginResult) authResult;
            hideProgress();
            SocialLoginInfoHolder.setBindState(new SocialLoginInfoHolder.BindState(null, CredentialsExchanger.SocialBindType.fromString(loginResult.getSocialBindType())));
            startOauthVkConnect(loginResult.getEmail(), loginResult.getAuthUrl(), loginResult.getSource());
            return;
        }
        if (authResult instanceof ru.mail.social_auth.domain.AuthResult.GoToUnblockUser) {
            startUnblockUserScreen(((ru.mail.social_auth.domain.AuthResult.GoToUnblockUser) authResult).getFailUrl());
            return;
        }
        if (authResult instanceof ru.mail.social_auth.domain.AuthResult.GoToLogin) {
            ru.mail.social_auth.domain.AuthResult.GoToLogin goToLogin = (ru.mail.social_auth.domain.AuthResult.GoToLogin) authResult;
            startLoginScreenForBind(goToLogin.getBindToken(), goToLogin.getBindType(), goToLogin.getVkAccessToken());
            return;
        }
        if (authResult instanceof ru.mail.social_auth.domain.AuthResult.GoToSignup) {
            ru.mail.social_auth.domain.AuthResult.GoToSignup goToSignup = (ru.mail.social_auth.domain.AuthResult.GoToSignup) authResult;
            startSignup(goToSignup.getBindToken(), goToSignup.getBindType(), goToSignup.getVkAccessToken());
        }
        if (authResult instanceof ru.mail.social_auth.domain.AuthResult.GoToSecondStep) {
            ru.mail.social_auth.domain.AuthResult.GoToSecondStep goToSecondStep = (ru.mail.social_auth.domain.AuthResult.GoToSecondStep) authResult;
            if (goToSecondStep.getBindToken() != null && goToSecondStep.getBindType() != null) {
                SocialLoginInfoHolder.setBindState(new SocialLoginInfoHolder.BindState(goToSecondStep.getBindToken(), CredentialsExchanger.SocialBindType.fromString(goToSecondStep.getBindType())));
            }
            startVkToSecondStep();
        }
    }

    private void startBrowserAuth(Bundle bundle) {
        createAppAuthDelegate(bundle).onRequestAuthCode(this);
    }

    private void startGoogleCompose(Bundle bundle) {
        String string = bundle.getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT);
        String string2 = bundle.getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT, "");
        String string3 = bundle.getString("login_extra_xmail_migration_from", "");
        updateAccountTypeValidity(string, bundle);
        this.loginActivityAuthorizationViewModel.navigateTo(new GoogleNativeComposeScreen(string2, string3), null);
    }

    private void startGoogleWebCompose(Bundle bundle) {
        String string = bundle.getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT);
        String string2 = bundle.getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT, "");
        String string3 = bundle.getString("login_extra_xmail_migration_from", "");
        updateAccountTypeValidity(string, bundle);
        this.loginActivityAuthorizationViewModel.navigateTo(new GoogleWebComposeScreen(string2, string3), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNewExternalAuth(String str, Bundle bundle, final Function0<Void> function0) {
        if (isAnyXmailMigration(bundle)) {
            function0.invoke();
        } else {
            this.newExternalAuthViewModel.startNewExternalAuth(this, str, new Function0() { // from class: ru.mail.auth.h1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return LoginActivity.G(function0);
                }
            });
        }
    }

    private void startOauthVkConnect(String str, String str2, VkIdAuthSource vkIdAuthSource) {
        this.analytics.onSuccessDefaultOrForceVKIDAuth();
        getLoginFragment().onOAuthVKConnect(str, str2, vkIdAuthSource);
    }

    private void startRegistrationCompat(String str, Bundle bundle) {
        if (this.newAuthSdkConfig.isEnabled() && this.newAuthSdkConfig.isServiceChooserFragmentSdkEnabled()) {
            startRegistration(str, bundle);
        } else {
            ServiceChooserFragment.startRegistration(this, str, bundle);
        }
    }

    private void startRegistrationNewExternalOauth() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Authenticator.NEED_FORCE_CREATE_COLLECTOR, true);
        bundle.putString("login_extra_xmail_migration_from", "xmail-manual-login");
        startRegistration(AuthSource.NEW_EXTERNAL_AUTH, bundle);
    }

    private void startSignup(String str, String str2, String str3) {
        SocialLoginInfoHolder.setBindState(new SocialLoginInfoHolder.BindState(str, CredentialsExchanger.SocialBindType.fromString(str2)));
        sendSignupPrepareRequest(str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSocialRegistration(Bundle bundle, String str) {
        bundle.putString(MailAccountConstants.MAIL_RU_REG_ACT_VK_ACCESS_TOKEN_KEY, str);
        if (this.newAuthSdkConfig.isEnabled() && this.newAuthSdkConfig.isServiceChooserFragmentSdkEnabled()) {
            startRegistration(AuthSource.BIND_EMAIL, bundle);
        } else {
            ServiceChooserFragment.startRegistration(this, AuthSource.BIND_EMAIL, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVKConnectAuth(final boolean z10, final boolean z11, final boolean z12) {
        if (this.socialAuthConfig.isInitEnabled()) {
            SocialAuthSdk.runWhenSdkInitialized("startVKConnectAuth", new Function0() { // from class: ru.mail.auth.t1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f80823a.lambda$startVKConnectAuth$13(z10, z11, z12);
                }
            });
            return;
        }
        CredentialsExchanger.INSTANCE.setUnauthorized(VKAuthenticator.INSTANCE.getMailRuClientId(getApplicationContext()));
        createVKConnectDelegate().startVKConnectAuth(z10, z11, z12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVkToSecondStep() {
        hideProgress();
        ForceVkidAuthListener forceVkidAuthListener = getForceVkidAuthListener();
        if (forceVkidAuthListener != null) {
            forceVkidAuthListener.proceedToSecondStep();
        }
    }

    private void startXmailLogin(String str) {
        getIntent().putExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN, str);
        startLoginScreen(EmailServiceResources.MailServiceResources.MAILRU_DEFAULT);
    }

    private void startXmailMigrationFromLogin() {
        Bundle bundle = new Bundle();
        bundle.putString("login_extra_xmail_migration_from", MailAccountConstants.XMAIL_MIGRATION_LOGIN);
        getLoginFragment().authenticate(null, null, Authenticator.Type.OAUTH, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toDoRegistrationScreen(Bundle bundle) {
        changeToFragment(createDoregistrationFragment(), bundle);
    }

    private void updateAccountTypeValidity(@Nullable String str, Bundle bundle) {
        bundle.putBoolean(Oauth2Params.IS_ACCOUNT_VALID, str != null && new AccountManagerProviderImpl(Authenticator.getAccountManagerWrapper(getApplicationContext())).isExternalAccountValid("com.google", str));
    }

    private void updateEmailPassword(@NonNull String str, @NonNull String str2) {
        ActivityResultCaller actualFragment = getActualFragment();
        if (actualFragment instanceof EmailPasswordUpdater) {
            ((EmailPasswordUpdater) actualFragment).updateEmailPassword(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewAuthorizationSdkEventExecutor(ViewEvent viewEvent) {
        if (viewEvent instanceof LoginActivityEvents.Error) {
            LoginActivityEvents.Error error = (LoginActivityEvents.Error) viewEvent;
            if (viewEvent instanceof LoginActivityEvents.Error.NeedYandexOauth) {
                LoginActivityEvents.Error.NeedYandexOauth needYandexOauth = (LoginActivityEvents.Error.NeedYandexOauth) viewEvent;
                if (this.newAuthorizationSdkConfig.isYandexAuthEnabled()) {
                    this.activityLauncher.launch(new AuthMode.Yandex(needYandexOauth.getEmail()));
                    return;
                } else {
                    startAuthenticate(needYandexOauth.getEmail(), null, Authenticator.Type.YANDEX_OAUTH);
                    return;
                }
            }
            if (viewEvent instanceof LoginActivityEvents.Error.NeedYahooOauth) {
                LoginActivityEvents.Error.NeedYahooOauth needYahooOauth = (LoginActivityEvents.Error.NeedYahooOauth) viewEvent;
                if (this.newAuthorizationSdkConfig.isYahooAuthEnabled()) {
                    this.activityLauncher.launch(new AuthMode.Yahoo(needYahooOauth.getEmail()));
                    return;
                } else {
                    startAuthenticate(needYahooOauth.getEmail(), null, Authenticator.Type.YAHOO_OAUTH);
                    return;
                }
            }
            if (viewEvent instanceof LoginActivityEvents.Error.NeedOutlookOauth) {
                LoginActivityEvents.Error.NeedOutlookOauth needOutlookOauth = (LoginActivityEvents.Error.NeedOutlookOauth) viewEvent;
                if (this.newAuthorizationSdkConfig.isOutlookAuthEnabled()) {
                    this.activityLauncher.launch(new AuthMode.Outlook(needOutlookOauth.getEmail()));
                    return;
                } else {
                    startAuthenticate(needOutlookOauth.getEmail(), null, Authenticator.Type.OUTLOOK_OAUTH);
                    return;
                }
            }
            if (viewEvent instanceof LoginActivityEvents.Error.NeedGoogleOauth) {
                LoginActivityEvents.Error.NeedGoogleOauth needGoogleOauth = (LoginActivityEvents.Error.NeedGoogleOauth) viewEvent;
                if (this.newAuthorizationSdkConfig.isNativeGoogleAuthEnabled()) {
                    this.activityLauncher.launch(new AuthMode.GoogleNative(needGoogleOauth.getEmail(), ""));
                    return;
                } else {
                    startSimpleGoogleLogin(needGoogleOauth.getEmail());
                    return;
                }
            }
            if (viewEvent instanceof LoginActivityEvents.Error.NeedDoRegistration) {
                LoginActivityEvents.Error.NeedDoRegistration needDoRegistration = (LoginActivityEvents.Error.NeedDoRegistration) viewEvent;
                Parcelable parcelableBuild = DoregistrationParameter.builder().setRegId(needDoRegistration.getRegId()).setCaptchaRequired(needDoRegistration.isNeedCaptcha()).build();
                Bundle bundle = new Bundle();
                String email = needDoRegistration.getEmail();
                String emailDomain = StringKt.getEmailDomain(email);
                bundle.putParcelable(MailAccountConstants.LOGIN_EXTRA_DOREGISTRATION_PARAM, parcelableBuild);
                bundle.putString("authAccount", email);
                bundle.putString("password", needDoRegistration.getPassword());
                bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.getTypeByDomain(emailDomain).toString());
                toDoRegistrationScreen(bundle);
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.Error.ImapRedirect) {
                LoginActivityEvents.Error.ImapRedirect imapRedirect = (LoginActivityEvents.Error.ImapRedirect) viewEvent;
                getLoginFragment().executeImapRedirect(imapRedirect.getEmail(), imapRedirect.getPassword(), imapRedirect.getSettings());
                return;
            } else if (viewEvent instanceof LoginActivityEvents.Error.OauthImapFailed) {
                notifyAuthError(AuthErrors.getErrorMessage(this, ((LoginActivityEvents.Error.OauthImapFailed) viewEvent).getEmail(), 812), 812);
                return;
            } else {
                if (viewEvent instanceof LoginActivityEvents.Error.CommonError) {
                    showAuthErrorToast(((LoginActivityEvents.Error.CommonError) error).getMessage());
                    return;
                }
                return;
            }
        }
        if (viewEvent instanceof LoginActivityEvents.LudwigEvents) {
            if (!(viewEvent instanceof LoginActivityEvents.LudwigEvents.LudwigCaptchaSuccess)) {
                if (viewEvent instanceof LoginActivityEvents.LudwigEvents.LudwigCaptchaError) {
                    showAuthErrorToast(getString(ru.mail.authorizationsdk.R.string.ludwig_sdk_something_went_wrong_message));
                    return;
                }
                return;
            }
            LoginActivityEvents.LudwigEvents.LudwigCaptchaSuccess ludwigCaptchaSuccess = (LoginActivityEvents.LudwigEvents.LudwigCaptchaSuccess) viewEvent;
            String ludwigToken = ludwigCaptchaSuccess.getLudwigToken();
            String login = ludwigCaptchaSuccess.getLogin();
            String password = ludwigCaptchaSuccess.getPassword();
            Authenticator.Type type = ludwigCaptchaSuccess.getType();
            Bundle extraData = ludwigCaptchaSuccess.getExtraData();
            if (extraData == null) {
                extraData = new Bundle();
            }
            LudwigParams.INSTANCE.putLudwigToken(ludwigToken, extraData);
            startAuthenticate(login, password, type, extraData);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.CustomServerEvents.Success) {
            LoginActivityEvents.CustomServerEvents.Success success = (LoginActivityEvents.CustomServerEvents.Success) viewEvent;
            String email2 = success.getEmail();
            String password2 = success.getPassword();
            updateEmailPassword(email2, password2);
            Bundle bundle2 = new Bundle();
            bundle2.putString("authAccount", email2);
            bundle2.putString("password", password2);
            bundle2.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.getAccountType(email2, null).toString());
            bundle2.putBundle(BaseAuthActivity.EXTRA_BUNDLE, null);
            onMessageHandle(new Message(Message.Id.AUTHENTICATE, bundle2));
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartXmailMigrationFromLogin) {
            startXmailMigrationFromLogin();
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartRegistrationNewExternalAuth) {
            startRegistrationNewExternalOauth();
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartLoginScreenWithXmail) {
            startXmailLogin(((LoginActivityEvents.StartLoginScreenWithXmail) viewEvent).getEmail());
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartDefaultLoginScreenRequired) {
            getIntent().putExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN, ((LoginActivityEvents.StartDefaultLoginScreenRequired) viewEvent).getEmail());
            startLoginScreen(EmailServiceResources.MailServiceResources.OTHER);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.OneTimeCodeEvents) {
            if (viewEvent instanceof LoginActivityEvents.OneTimeCodeEvents.Success) {
                LoginActivityEvents.OneTimeCodeEvents.Success success2 = (LoginActivityEvents.OneTimeCodeEvents.Success) viewEvent;
                String email3 = success2.getEmail();
                String string = UUID.randomUUID().toString();
                Authenticator.Type type2 = Authenticator.Type.DEFAULT;
                Bundle bundle3 = new Bundle();
                bundle3.putSerializable(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_PARAMS, success2.getQueryParams());
                bundle3.putSerializable(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_COOKIES, success2.getActCookie());
                bundle3.putBoolean(Authenticator.IS_ENTERED_BY_ONE_TIME_CODE, true);
                startAuthenticate(email3, string, type2, bundle3);
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.OneTimeCodeEvents.SwitchToPassword) {
                switchToPassword(((LoginActivityEvents.OneTimeCodeEvents.SwitchToPassword) viewEvent).getEmail());
                return;
            } else if (viewEvent instanceof LoginActivityEvents.OneTimeCodeEvents.Error) {
                showAuthErrorToast(((LoginActivityEvents.OneTimeCodeEvents.Error) viewEvent).getError());
                return;
            }
        }
        if (viewEvent instanceof LoginActivityEvents.Login) {
            if (viewEvent instanceof LoginActivityEvents.Login.BackClick) {
                if (ReturnParamsHelper.INSTANCE.isIntentForRestoreAuth(getIntent()) && AuthUtil.hasActiveAccounts(this) && getAuthDelegate() != null) {
                    switchToValidAccountIfNecessary();
                    getAuthDelegate().openMailForCurrentAccount(this);
                    return;
                } else {
                    switchToValidAccountIfNecessary();
                    finish();
                    return;
                }
            }
            if (viewEvent instanceof LoginActivityEvents.Login.NeedRegistration) {
                startRegistration(AuthSource.LOGIN_VIEW, Bundle.EMPTY);
                SocialLoginInfoHolder.clear();
                return;
            } else if (viewEvent instanceof LoginActivityEvents.Login.AlreadyLoggedIn) {
                LoginActivityEvents.Login.AlreadyLoggedIn alreadyLoggedIn = (LoginActivityEvents.Login.AlreadyLoggedIn) viewEvent;
                if (getAuthDelegate() != null) {
                    getAuthDelegate().switchToAccount(alreadyLoggedIn.getEmail(), this);
                }
            }
        }
        if (viewEvent instanceof LoginActivityEvents.ImapLocalSuccess) {
            LoginActivityEvents.ImapLocalSuccess imapLocalSuccess = (LoginActivityEvents.ImapLocalSuccess) viewEvent;
            showProgress();
            Bundle bundle4 = new Bundle();
            bundle4.putString("authAccount", imapLocalSuccess.getEmail());
            bundle4.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, imapLocalSuccess.getEmail());
            bundle4.putString("password", imapLocalSuccess.getPassword());
            addNecessaryProperties(bundle4, imapLocalSuccess.getProviderInfo());
            bundle4.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
            this.returnParamsFactory.putReturnParams(imapLocalSuccess.getEmail(), getIntent(), bundle4, this);
            onAuthSucceeded(bundle4);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.OAuthImapLocalSuccess) {
            LoginActivityEvents.OAuthImapLocalSuccess oAuthImapLocalSuccess = (LoginActivityEvents.OAuthImapLocalSuccess) viewEvent;
            showProgress();
            Bundle bundle5 = new Bundle();
            bundle5.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, oAuthImapLocalSuccess.getEmail());
            bundle5.putString("authAccount", oAuthImapLocalSuccess.getEmail());
            addNecessaryProperties(bundle5, oAuthImapLocalSuccess.getProviderInfo());
            bundle5.putString(MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN, oAuthImapLocalSuccess.getVendorAccessToken());
            bundle5.putString("password", oAuthImapLocalSuccess.getVendorAccessToken());
            bundle5.putString("type", "OAUTH");
            bundle5.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH2_DIRECT_ACCESS, oAuthImapLocalSuccess.getVendorAccessToken());
            bundle5.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, oAuthImapLocalSuccess.getVendorRefreshToken());
            bundle5.putBoolean(MailLoginFragment.EXTRA_IMAP_SKIP_MAILRU_OAUTH_STEPS, true);
            bundle5.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
            this.returnParamsFactory.putReturnParams(oAuthImapLocalSuccess.getEmail(), getIntent(), bundle5, this);
            onAuthSucceeded(bundle5);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.PasswordAuth) {
            LoginActivityEvents.PasswordAuth passwordAuth = (LoginActivityEvents.PasswordAuth) viewEvent;
            showProgress();
            Bundle bundle6 = new Bundle();
            String password3 = passwordAuth.getPassword().isEmpty() ? "password" : passwordAuth.getPassword();
            bundle6.putString("authAccount", passwordAuth.getEmail());
            bundle6.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, passwordAuth.getEmail());
            bundle6.putString("password", password3);
            bundle6.putString(Authenticator.BUNDLE_KEY_2FACTOR_AUTH_TSA_COOKIE, passwordAuth.getTsaCookie());
            bundle6.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
            bundle6.putString("ru.mail.oauth2.access", passwordAuth.getAccessToken());
            bundle6.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, passwordAuth.getRefreshToken());
            bundle6.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, passwordAuth.getAccountType());
            bundle6.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, passwordAuth.getAccountType());
            bundle6.putString(Statistic.TOKEN_TYPE, "oauth2");
            if (passwordAuth.getIsAutoLogin()) {
                bundle6.putParcelable(VkIdAuthSource.KEY, VkIdAuthSource.AutoLogin);
            }
            this.returnParamsFactory.putReturnParams(passwordAuth.getEmail(), getIntent(), bundle6, this);
            if (!TextUtils.isEmpty(passwordAuth.getBindType())) {
                bundle6.putString(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY, passwordAuth.getBindType());
                onAccountBindSuccessAfterSuccessAuth(passwordAuth.getEmail(), passwordAuth.getBindType());
            }
            onAuthSucceeded(bundle6);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.RestoreWithoutPasswordSuccess) {
            LoginActivityEvents.RestoreWithoutPasswordSuccess restoreWithoutPasswordSuccess = (LoginActivityEvents.RestoreWithoutPasswordSuccess) viewEvent;
            showProgress();
            Bundle bundle7 = new Bundle();
            bundle7.putString("authAccount", restoreWithoutPasswordSuccess.getEmail());
            bundle7.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, restoreWithoutPasswordSuccess.getEmail());
            bundle7.putString("password", "password");
            bundle7.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
            bundle7.putString("ru.mail.oauth2.access", restoreWithoutPasswordSuccess.getAccessToken());
            bundle7.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, restoreWithoutPasswordSuccess.getRefreshToken());
            bundle7.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, restoreWithoutPasswordSuccess.getAccountType());
            bundle7.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, restoreWithoutPasswordSuccess.getAccountType());
            bundle7.putString(Statistic.TOKEN_TYPE, "oauth2");
            this.returnParamsFactory.putReturnParams(restoreWithoutPasswordSuccess.getEmail(), getIntent(), bundle7, this);
            onAuthSucceeded(bundle7);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.OneTimeCodeSuccessAuth) {
            LoginActivityEvents.OneTimeCodeSuccessAuth oneTimeCodeSuccessAuth = (LoginActivityEvents.OneTimeCodeSuccessAuth) viewEvent;
            showProgress();
            Bundle bundle8 = new Bundle();
            bundle8.putString("ru.mail.oauth2.access", oneTimeCodeSuccessAuth.getAccessToken());
            bundle8.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, oneTimeCodeSuccessAuth.getRefreshToken());
            bundle8.putString(Statistic.TOKEN_TYPE, "oauth2");
            bundle8.putString("authAccount", oneTimeCodeSuccessAuth.getEmail());
            bundle8.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, oneTimeCodeSuccessAuth.getEmail());
            bundle8.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, oneTimeCodeSuccessAuth.getAccountType());
            bundle8.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, oneTimeCodeSuccessAuth.getAccountType());
            bundle8.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
            bundle8.putBoolean(Authenticator.IS_ENTERED_BY_ONE_TIME_CODE, true);
            bundle8.putString("password", UUID.randomUUID().toString());
            this.returnParamsFactory.putReturnParams(oneTimeCodeSuccessAuth.getEmail(), getIntent(), bundle8, this);
            if (!TextUtils.isEmpty(oneTimeCodeSuccessAuth.getBindType())) {
                bundle8.putString(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY, oneTimeCodeSuccessAuth.getBindType());
                onAccountBindSuccessAfterSuccessAuth(oneTimeCodeSuccessAuth.getEmail(), oneTimeCodeSuccessAuth.getBindType());
            }
            onAuthSucceeded(bundle8);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.AfterRegAuth) {
            LoginActivityEvents.AfterRegAuth afterRegAuth = (LoginActivityEvents.AfterRegAuth) viewEvent;
            showProgress();
            Intent confirmationActivityIntent = Authenticator.getConfirmationActivityIntent(getApplicationContext().getPackageName());
            confirmationActivityIntent.putExtra(MailAccountConstants.SUCCESS_REGISTER_ACCOUNT, afterRegAuth);
            confirmationActivityIntent.putExtra(Authenticator.NEED_FORCE_CREATE_COLLECTOR, afterRegAuth.getForceCreateCollector());
            confirmationActivityIntent.putExtra(ConfirmationActivity.CONFIRM_ACT_VK_TOKEN_KEY, afterRegAuth.getVkAccessToken());
            if (afterRegAuth.getMigrationFrom() != null) {
                confirmationActivityIntent.putExtra("login_extra_xmail_migration_from", afterRegAuth.getMigrationFrom());
            }
            startActivity(confirmationActivityIntent);
            finish();
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.YahooEvents) {
            if (viewEvent instanceof LoginActivityEvents.YahooEvents.Success) {
                LoginActivityEvents.YahooEvents.Success success3 = (LoginActivityEvents.YahooEvents.Success) viewEvent;
                showProgress();
                Bundle bundle9 = new Bundle();
                bundle9.putString("authAccount", success3.getEmail());
                bundle9.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, success3.getEmail());
                bundle9.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, success3.getAccountType());
                bundle9.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, success3.getAccountType());
                bundle9.putString("ru.mail.oauth2.access", success3.getAccessToken());
                bundle9.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, success3.getRefreshToken());
                bundle9.putString(MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN, success3.getYahooAccessToken());
                bundle9.putString("password", success3.getYahooRefreshToken());
                bundle9.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
                bundle9.putString(Statistic.TOKEN_TYPE, "oauth2");
                this.returnParamsFactory.putReturnParams(success3.getEmail(), getIntent(), bundle9, this);
                onAuthSucceeded(bundle9);
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.YahooEvents.JapanAccount) {
                showAuthErrorToast(((LoginActivityEvents.YahooEvents.JapanAccount) viewEvent).getEmail());
                startLoginScreen(EmailServiceResources.MailServiceResources.YAHOO_JP);
                return;
            } else if (viewEvent instanceof LoginActivityEvents.YahooEvents.Error) {
                LoginActivityEvents.YahooEvents.Error error2 = (LoginActivityEvents.YahooEvents.Error) viewEvent;
                if (error2.getCode() == YahooResult.YahooErrorCodes.OAUTH_IMAP_FAILED) {
                    notifyAuthError(AuthErrors.getErrorMessage(this, error2.getEmail() != null ? error2.getEmail() : "", 812), 812);
                    return;
                } else {
                    showAuthErrorToast(error2.getMessage());
                    return;
                }
            }
        }
        if (viewEvent instanceof LoginActivityEvents.YandexEvents) {
            if (viewEvent instanceof LoginActivityEvents.YandexEvents.Success) {
                LoginActivityEvents.YandexEvents.Success success4 = (LoginActivityEvents.YandexEvents.Success) viewEvent;
                showProgress();
                Bundle bundle10 = new Bundle();
                bundle10.putString("authAccount", success4.getEmail());
                bundle10.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, success4.getEmail());
                bundle10.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, success4.getAccountType());
                bundle10.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, success4.getAccountType());
                bundle10.putString("ru.mail.oauth2.access", success4.getAccessToken());
                bundle10.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, success4.getRefreshToken());
                bundle10.putString(MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN, success4.getYandexAccessToken());
                bundle10.putString("password", success4.getYandexRefreshToken());
                bundle10.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
                bundle10.putString(Statistic.TOKEN_TYPE, "oauth2");
                this.returnParamsFactory.putReturnParams(success4.getEmail(), getIntent(), bundle10, this);
                onAuthSucceeded(bundle10);
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.YandexEvents.Error) {
                LoginActivityEvents.YandexEvents.Error error3 = (LoginActivityEvents.YandexEvents.Error) viewEvent;
                if (error3.getCode() == YandexResult.YandexErrorCodes.OAUTH_IMAP_FAILED) {
                    notifyAuthError(AuthErrors.getErrorMessage(this, error3.getEmail() != null ? error3.getEmail() : "", 812), 812);
                    return;
                } else {
                    showAuthErrorToast(error3.getMessage());
                    return;
                }
            }
        }
        if (viewEvent instanceof LoginActivityEvents.OutlookEvents) {
            if (viewEvent instanceof LoginActivityEvents.OutlookEvents.Success) {
                LoginActivityEvents.OutlookEvents.Success success5 = (LoginActivityEvents.OutlookEvents.Success) viewEvent;
                showProgress();
                Bundle bundle11 = new Bundle();
                bundle11.putString("authAccount", success5.getEmail());
                bundle11.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, success5.getEmail());
                bundle11.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, success5.getAccountType());
                bundle11.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, success5.getAccountType());
                bundle11.putString("ru.mail.oauth2.access", success5.getAccessToken());
                bundle11.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, success5.getRefreshToken());
                bundle11.putString(MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN, success5.getOutlookAccessToken());
                bundle11.putString("password", success5.getOutlookRefreshToken());
                bundle11.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
                bundle11.putString(Statistic.TOKEN_TYPE, "oauth2");
                this.returnParamsFactory.putReturnParams(success5.getEmail(), getIntent(), bundle11, this);
                onAuthSucceeded(bundle11);
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.OutlookEvents.Error) {
                LoginActivityEvents.OutlookEvents.Error error4 = (LoginActivityEvents.OutlookEvents.Error) viewEvent;
                if (error4.getCode() == OutlookResult.OutlookErrorCodes.OAUTH_IMAP_FAILED) {
                    notifyAuthError(AuthErrors.getErrorMessage(this, error4.getEmail() != null ? error4.getEmail() : "", 812), 812);
                    return;
                } else {
                    showAuthErrorToast(error4.getMessage());
                    return;
                }
            }
        }
        if (viewEvent instanceof LoginActivityEvents.VkSilentSuccess) {
            LoginActivityEvents.VkSilentSuccess vkSilentSuccess = (LoginActivityEvents.VkSilentSuccess) viewEvent;
            showProgress();
            Bundle bundle12 = new Bundle();
            bundle12.putString("authAccount", vkSilentSuccess.getEmail());
            bundle12.putString("password", vkSilentSuccess.getSilentToken());
            Authenticator.Type type3 = Authenticator.Type.VK_CONNECT;
            bundle12.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, type3.name());
            bundle12.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type3.name());
            bundle12.putString("ru.mail.oauth2.access", vkSilentSuccess.getAccessToken());
            bundle12.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, vkSilentSuccess.getRefreshToken());
            bundle12.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, vkSilentSuccess.getEmail());
            bundle12.putString(Statistic.TOKEN_TYPE, "oauth2");
            bundle12.putString(VkIdAuthSource.KEY, VkIdAuthSource.Auth.name());
            this.returnParamsFactory.putReturnParams(vkSilentSuccess.getEmail(), getIntent(), bundle12, this);
            onAuthSucceeded(bundle12);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.RestorePasswordComposeEvents) {
            if (viewEvent instanceof LoginActivityEvents.RestorePasswordComposeEvents.Success) {
                LoginActivityEvents.RestorePasswordComposeEvents.Success success6 = (LoginActivityEvents.RestorePasswordComposeEvents.Success) viewEvent;
                String login2 = success6.getLogin();
                Bundle bundle13 = new Bundle();
                bundle13.putSerializable(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_PARAMS, success6.getQueryParams());
                startAuthenticate(login2, null, Authenticator.Type.DEFAULT, bundle13);
                this.analytics.onRestorePasswordClosed("SUCCESS");
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.RestorePasswordComposeEvents.Closed) {
                this.analytics.onRestorePasswordClosed(((LoginActivityEvents.RestorePasswordComposeEvents.Closed) viewEvent).getAnalyticsTag());
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.RestorePasswordComposeEvents.Error) {
                showAuthErrorToast(((LoginActivityEvents.RestorePasswordComposeEvents.Error) viewEvent).getError());
                this.analytics.onRestorePasswordClosed(MailOfflineAttachmentPersistedCacheStatus.ERROR);
                return;
            } else {
                if (viewEvent instanceof LoginActivityEvents.RestorePasswordComposeEvents.GoToRestoreVkid) {
                    startRestoreVkid(((LoginActivityEvents.RestorePasswordComposeEvents.GoToRestoreVkid) viewEvent).getEmail(), RestoreVkidStartSource.FORGET_PASSWORD);
                    this.analytics.onRestorePasswordGoToRestoreVkid(true);
                    return;
                }
                return;
            }
        }
        if (viewEvent instanceof LoginActivityEvents.GoogleEvents) {
            if (viewEvent instanceof LoginActivityEvents.GoogleEvents.Success) {
                LoginActivityEvents.GoogleEvents.Success success7 = (LoginActivityEvents.GoogleEvents.Success) viewEvent;
                showProgress();
                Bundle bundle14 = new Bundle();
                bundle14.putString("authAccount", success7.getEmail());
                bundle14.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, success7.getEmail());
                bundle14.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, success7.getAccountType());
                bundle14.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, success7.getAccountType());
                bundle14.putString("ru.mail.oauth2.access", success7.getAccessToken());
                bundle14.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, success7.getRefreshToken());
                bundle14.putString(MailAccountConstants.LOGIN_EXTRA_ACCESS_TOKEN, success7.getGoogleAccessToken());
                bundle14.putString("password", success7.getGoogleRefreshToken());
                bundle14.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
                bundle14.putString(Statistic.TOKEN_TYPE, "oauth2");
                bundle14.putString(MailAccountConstants.LOGIN_EXTRA_OUATH2_ACCOUNT_TYPE, getAccountType());
                bundle14.putString("login_extra_xmail_migration_from", success7.getXmailMigrationFrom());
                this.returnParamsFactory.putReturnParams(success7.getEmail(), getIntent(), bundle14, this);
                onAuthSucceeded(bundle14);
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.GoogleEvents.MigrantRegistrationRequired) {
                LoginActivityEvents.GoogleEvents.MigrantRegistrationRequired migrantRegistrationRequired = (LoginActivityEvents.GoogleEvents.MigrantRegistrationRequired) viewEvent;
                String migrantToken = migrantRegistrationRequired.getMigrantToken();
                String xmailMigrationFrom = migrantRegistrationRequired.getXmailMigrationFrom();
                String email4 = migrantRegistrationRequired.getEmail();
                Bundle bundle15 = new Bundle();
                bundle15.putString(MailAccountConstants.ACTION_XMAIL_REG, migrantToken);
                bundle15.putString("login_extra_xmail_migration_from", xmailMigrationFrom);
                bundle15.putString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH, email4);
                processResultXmailMigrationNativeReg(bundle15);
                return;
            }
            checkFinishOnNonSupportedFlow();
            if (viewEvent instanceof LoginActivityEvents.GoogleEvents.Error) {
                LoginActivityEvents.GoogleEvents.Error error5 = (LoginActivityEvents.GoogleEvents.Error) viewEvent;
                if (error5.getCode() instanceof GoogleResult.GoogleErrorCodes.XmailMigrationSignInError) {
                    finish();
                    return;
                }
                GoogleResult.GoogleErrorCodes code = error5.getCode();
                if (code instanceof GoogleResult.GoogleErrorCodes.WebViewAuthRequired) {
                    Oauth2Params oauth2Params = ((GoogleResult.GoogleErrorCodes.WebViewAuthRequired) code).getOauth2Params();
                    Oauth2Arguments oauth2Arguments = new Oauth2Arguments(oauth2Params.getClientId(), oauth2Params.getSecretId(), oauth2Params.getRedirectUri(), oauth2Params.getAuthServerUrl(), oauth2Params.getTokenServerUrl(), oauth2Params.getScope(), Authenticator.Type.OAUTH.toString(), oauth2Params.getLoginHint());
                    Bundle bundle16 = new Bundle();
                    bundle16.putAll(oauth2Arguments.toBundle());
                    bundle16.putString(MailAccountConstants.LOGIN_EXTRA_OUATH2_ACCOUNT_TYPE, getAccountType());
                    bundle16.putString("login_extra_xmail_migration_from", oauth2Params.getXmailMigrationFrom());
                    startBrowserAuth(bundle16);
                    return;
                }
                if (error5.getCode() instanceof GoogleResult.GoogleErrorCodes.OauthImapFailed) {
                    notifyAuthError(AuthErrors.getErrorMessage(this, error5.getEmail() != null ? error5.getEmail() : "", 812), 812);
                    return;
                } else if (error5.getCode() instanceof GoogleResult.GoogleErrorCodes.NecessaryScopeNotProvided) {
                    notifyAuthError(AuthErrors.getErrorMessage(this, "", 813), 813);
                    return;
                } else {
                    showAuthErrorToast(error5.getMessage());
                    return;
                }
            }
        }
        if (viewEvent instanceof LoginActivityEvents.SecondStepEvents) {
            if (viewEvent instanceof LoginActivityEvents.SecondStepEvents.Success) {
                LoginActivityEvents.SecondStepEvents.Success success8 = (LoginActivityEvents.SecondStepEvents.Success) viewEvent;
                Bundle bundle17 = new Bundle();
                Bundle oldBundle = success8.getOldBundle();
                Bundle bundle18 = oldBundle != null ? new Bundle(oldBundle) : new Bundle();
                bundle17.putSerializable(Authenticator.BUNDLE_PARAM_SECSTEP_REDIRECT_PARAMS, success8.getQueryParams());
                bundle17.putString(Authenticator.BUNDLE_KEY_2FACTOR_AUTH_TSA_COOKIE, success8.getTsaCookie());
                if (success8.getXmailMigrationFrom() != null) {
                    if (success8.getXmailLogin() != null) {
                        bundle18.putString("authAccount", success8.getXmailLogin());
                    }
                    LOG.d("onRedirectSuccess xmail migration login = " + success8.getXmailLogin());
                    bundle17.putString("login_extra_xmail_migration_from", success8.getXmailMigrationFrom());
                }
                bundle18.putBundle(BaseAuthActivity.EXTRA_BUNDLE, bundle17);
                onMessageHandle(new Message(Message.Id.AUTHENTICATE, bundle18));
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.SecondStepEvents.SwitchToRecovery) {
                startRestoreVkid(((LoginActivityEvents.SecondStepEvents.SwitchToRecovery) viewEvent).getEmail(), RestoreVkidStartSource.FORGET_PASSWORD);
                this.analytics.onRestorePasswordGoToRestoreVkid(true);
                return;
            } else if (viewEvent instanceof LoginActivityEvents.SecondStepEvents.SwitchToPassword) {
                switchToPassword(((LoginActivityEvents.SecondStepEvents.SwitchToPassword) viewEvent).getEmail());
                return;
            } else if (viewEvent instanceof LoginActivityEvents.SecondStepEvents.Error) {
                showAuthErrorToast(((LoginActivityEvents.SecondStepEvents.Error) viewEvent).getError());
                return;
            } else if (viewEvent instanceof LoginActivityEvents.SecondStepEvents.NeedEndActivity) {
                finish();
                return;
            }
        }
        if (viewEvent instanceof LoginActivityEvents.SSOEvents) {
            if (viewEvent instanceof LoginActivityEvents.SSOEvents.Success) {
                LoginActivityEvents.SSOEvents.Success success9 = (LoginActivityEvents.SSOEvents.Success) viewEvent;
                Bundle bundle19 = new Bundle();
                Bundle oldBundle2 = success9.getOldBundle();
                Bundle bundle20 = oldBundle2 != null ? new Bundle(oldBundle2) : new Bundle();
                bundle19.putString(Authenticator.SSO_AUTH_AG_TOKEN, success9.getAgToken());
                bundle20.putBundle(BaseAuthActivity.EXTRA_BUNDLE, bundle19);
                onMessageHandle(new Message(Message.Id.AUTHENTICATE, bundle20));
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.SSOEvents.Error) {
                showAuthErrorToast(((LoginActivityEvents.SSOEvents.Error) viewEvent).getError());
                return;
            } else if (viewEvent instanceof LoginActivityEvents.SSOEvents.SwitchToRestoreVkId) {
                startRestoreVkid(((LoginActivityEvents.SSOEvents.SwitchToRestoreVkId) viewEvent).getEmail(), RestoreVkidStartSource.FORGET_PASSWORD);
                this.analytics.onRestorePasswordGoToRestoreVkid(true);
                return;
            }
        }
        if (viewEvent instanceof LoginActivityEvents.VkPasswordEvents) {
            if (viewEvent instanceof LoginActivityEvents.VkPasswordEvents.Success) {
                LoginActivityEvents.VkPasswordEvents.Success success10 = (LoginActivityEvents.VkPasswordEvents.Success) viewEvent;
                Bundle bundle21 = new Bundle();
                Bundle oldBundle3 = success10.getOldBundle();
                Bundle bundle22 = oldBundle3 != null ? new Bundle(oldBundle3) : new Bundle();
                bundle21.putString(Authenticator.VK_PASSWORD_AUTH_AG_TOKEN, success10.getAgToken());
                bundle22.putBundle(r18, bundle21);
                onMessageHandle(new Message(Message.Id.AUTHENTICATE, bundle22));
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.VkPasswordEvents.Error) {
                showAuthErrorToast(((LoginActivityEvents.VkPasswordEvents.Error) viewEvent).getError());
                return;
            }
        }
        if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent) {
            this.isFromSocialAuth = true;
            if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent.Success) {
                LoginActivityEvents.SocialAuthEvent.Success success11 = (LoginActivityEvents.SocialAuthEvent.Success) viewEvent;
                Analytics analytics = this.analytics;
                Authenticator.Type type4 = Authenticator.Type.VK_CONNECT;
                analytics.oAuthWebView(type4.name());
                showProgress();
                Bundle bundle23 = new Bundle();
                bundle23.putString("authAccount", success11.getEmail());
                bundle23.putString("password", type4.name());
                bundle23.putString(MailLoginFragment.EXTRA_ACCOUNT_TYPE, type4.name());
                bundle23.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, type4.name());
                bundle23.putString("ru.mail.oauth2.access", success11.getAccessToken());
                bundle23.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, success11.getRefreshToken());
                bundle23.putString(Statistic.TOKEN_TYPE, "oauth2");
                bundle23.putParcelable(VkIdAuthSource.KEY, VkIdAuthSource.Auth);
                if (!TextUtils.isEmpty(success11.getBindType())) {
                    bundle23.putString(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY, success11.getBindType());
                }
                this.returnParamsFactory.putReturnParams(success11.getEmail(), getIntent(), bundle23, this);
                int i10 = AnonymousClass2.$SwitchMap$ru$mail$social_auth$domain$SocialAuthType[success11.getAuthType().ordinal()];
                if (i10 == 1 || i10 == 2 || i10 == 3 || i10 == 4) {
                    this.analytics.onSuccessDefaultOrForceVKIDAuth();
                    if (success11.getAuthType() == SocialAuthType.VkAutoLogin) {
                        bundle23.putParcelable(VkIdAuthSource.KEY, VkIdAuthSource.AutoLogin);
                    }
                    bundle23.putString(Statistic.AUTH_TYPE, type4.name());
                } else if (i10 == 5) {
                    bundle23.putParcelable(VkIdAuthSource.KEY, VkIdAuthSource.Restore);
                }
                onAuthSucceeded(bundle23);
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent.Result) {
                socialAuthResult(((LoginActivityEvents.SocialAuthEvent.Result) viewEvent).getValue());
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent.Error) {
                LoginActivityEvents.SocialAuthEvent.Error error6 = (LoginActivityEvents.SocialAuthEvent.Error) viewEvent;
                this.analytics.onSocialAuthError(error6.getErrorType(), this.socialAuthConfig.isEsiaEnabled(), this.socialAuthConfig.isInitEnabled(), this.newAuthSdkConfig.getSocialAuthModuleConfig().isSocialAuthModuleEnable(), this.newAuthSdkConfig.getSocialAuthModuleConfig().isViewModelEnabled(), SuperappKit.isInitialized());
                showAuthErrorToast(Integer.valueOf(error6.getErrorCode()), error6.getErrorMsg());
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent.Close) {
                if (((LoginActivityEvents.SocialAuthEvent.Close) viewEvent).isAutoLogin()) {
                    hideProgress();
                    return;
                }
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent.StartRegistration) {
                startRegistrationCompat(AuthSource.LOGIN_VIEW, new Bundle());
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent.OpenMailRestore) {
                LoginActivityEvents.SocialAuthEvent.OpenMailRestore openMailRestore = (LoginActivityEvents.SocialAuthEvent.OpenMailRestore) viewEvent;
                BaseToolbarActivity.hideKeyboard(this);
                Uri uri = Uri.parse(new HostProviderWrapperImpl(this).getSchemeOrHost(ru.mail.Authenticator.R.string.restore_password_url));
                String emailForRestore = openMailRestore.getEmailForRestore();
                Bundle bundle24 = new Bundle();
                RestorePasswordParams restorePasswordParams = RestorePasswordParams.INSTANCE;
                restorePasswordParams.putIsRebind(openMailRestore.isRebind(), bundle24);
                restorePasswordParams.putRestoreUri(uri, bundle24);
                if (emailForRestore == null) {
                    emailForRestore = "";
                }
                restorePasswordParams.putRestoreLogin(emailForRestore, bundle24);
                onMessageHandle(new Message(Message.Id.START_RESTORE_PASSWORD, bundle24));
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent.OpenRestoreVkidOldAuth) {
                startRestoreVkidOld(((LoginActivityEvents.SocialAuthEvent.OpenRestoreVkidOldAuth) viewEvent).getEmailForRestore(), RestoreVkidStartSource.FORGET_PASSWORD);
                return;
            } else if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent.PasswordSuccessfullyChanged) {
                recreate();
                return;
            } else if (viewEvent instanceof LoginActivityEvents.SocialAuthEvent.NotAuthorizedErrorDuringPasswordChange) {
                recreate();
                return;
            }
        }
        if (viewEvent instanceof LoginActivityEvents.VkBindInLogin) {
            if (viewEvent instanceof LoginActivityEvents.VkBindInLogin.StartBinding) {
                getIntent().putExtra(IS_VK_BINDIN_LOGIN, true);
                VkidAuthWithoutPasswordHolder.setVkidAuthWithoutPassword(false);
                VkidBindInLoginHolder.setVkidBindInLogin(true);
                startVKConnectAuth(false, true, true);
                return;
            }
            if (!(viewEvent instanceof LoginActivityEvents.VkBindInLogin.LoginWithAnotherWay)) {
                boolean z10 = viewEvent instanceof LoginActivityEvents.VkBindInLogin.Back;
                return;
            }
            SocialLoginInfoHolder.clear();
            VkidBindInLoginHolder.setVkidBindInLogin(false);
            startVkToSecondStep();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void viewEventExecutor(ViewEvent viewEvent) {
        if (viewEvent instanceof LoginActivityEvents.StartGoogleAuthScreen) {
            startGoogleAuthScreen(((LoginActivityEvents.StartGoogleAuthScreen) viewEvent).getBundle());
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartLoginOAuthWebView) {
            getLoginFragment().onOAuthWebView(((LoginActivityEvents.StartLoginOAuthWebView) viewEvent).getTokensHolder(), null);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartRegistration) {
            startRegistrationByActivityItself();
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartRegistrationNewExternalAuth) {
            startRegistrationNewExternalOauth();
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartXmailMigrationFromLogin) {
            startXmailMigrationFromLogin();
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartLoginScreen) {
            startLoginScreen(((LoginActivityEvents.StartLoginScreen) viewEvent).getService());
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartVKAnotherLogin) {
            getIntent().putExtra(EXTRA_SHOW_EXTERNAL_VK_LOGIN, EXTRA_VALUE_EXTERNAL_VK_LOGIN_FROM_LOGIN);
            startLoginScreen(EmailServiceResources.MailServiceResources.OTHER);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.StartLoginScreenWithXmail) {
            startXmailLogin(((LoginActivityEvents.StartLoginScreenWithXmail) viewEvent).getEmail());
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.ShowActionBar) {
            showActionBar();
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.LudwigEvents) {
            if (!(viewEvent instanceof LoginActivityEvents.LudwigEvents.LudwigCaptchaSuccess)) {
                if (viewEvent instanceof LoginActivityEvents.LudwigEvents.LudwigCaptchaError) {
                    showAuthErrorToast(((LoginActivityEvents.LudwigEvents.LudwigCaptchaError) viewEvent).getError());
                    return;
                }
                return;
            }
            LoginActivityEvents.LudwigEvents.LudwigCaptchaSuccess ludwigCaptchaSuccess = (LoginActivityEvents.LudwigEvents.LudwigCaptchaSuccess) viewEvent;
            String ludwigToken = ludwigCaptchaSuccess.getLudwigToken();
            String login = ludwigCaptchaSuccess.getLogin();
            String password = ludwigCaptchaSuccess.getPassword();
            Authenticator.Type type = ludwigCaptchaSuccess.getType();
            Bundle extraData = ludwigCaptchaSuccess.getExtraData();
            if (extraData == null) {
                extraData = new Bundle();
            }
            LudwigParams.INSTANCE.putLudwigToken(ludwigToken, extraData);
            startAuthenticate(login, password, type, extraData);
            return;
        }
        if (viewEvent instanceof LoginActivityEvents.RestorePasswordEvents) {
            if (viewEvent instanceof LoginActivityEvents.RestorePasswordEvents.RestorePasswordSuccess) {
                LoginActivityEvents.RestorePasswordEvents.RestorePasswordSuccess restorePasswordSuccess = (LoginActivityEvents.RestorePasswordEvents.RestorePasswordSuccess) viewEvent;
                String login2 = restorePasswordSuccess.getLogin();
                Bundle extraData2 = restorePasswordSuccess.getExtraData();
                boolean zIsRebind = restorePasswordSuccess.getIsRebind();
                startAuthenticate(login2, null, Authenticator.Type.DEFAULT, extraData2);
                this.analytics.onRestorePasswordClosed(zIsRebind ? "SUCCESS_REBIND" : "SUCCESS");
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.RestorePasswordEvents.RestorePasswordClosed) {
                this.analytics.onRestorePasswordClosed(((LoginActivityEvents.RestorePasswordEvents.RestorePasswordClosed) viewEvent).getAnalyticsTag());
                return;
            }
            if (viewEvent instanceof LoginActivityEvents.RestorePasswordEvents.RestorePasswordError) {
                showAuthErrorToast(getString(((LoginActivityEvents.RestorePasswordEvents.RestorePasswordError) viewEvent).getErrorRes()));
                this.analytics.onRestorePasswordClosed(MailOfflineAttachmentPersistedCacheStatus.ERROR);
            } else if (viewEvent instanceof LoginActivityEvents.RestorePasswordEvents.GoToRestoreVkid) {
                startRestoreVkidOld(((LoginActivityEvents.RestorePasswordEvents.GoToRestoreVkid) viewEvent).getEmail(), RestoreVkidStartSource.FORGET_PASSWORD);
                this.analytics.onRestorePasswordGoToRestoreVkid(false);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addServiceChooserFragment() {
        Fragment orCreateServiceChooserFragment = getOrCreateServiceChooserFragment();
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        if (orCreateServiceChooserFragment instanceof AuthScreen) {
            this.loginActivityViewModel.navigateTo(((AuthScreen) orCreateServiceChooserFragment).getScreen(), null);
        }
        fragmentTransactionBeginTransaction.replace(ru.mail.Authenticator.R.id.login_fragment, orCreateServiceChooserFragment, SERVICE_CHOOSER_FRAGMENT_TAG);
        fragmentTransactionBeginTransaction.commitNowAllowingStateLoss();
    }

    public void changeToCodeAuthFragment(Bundle bundle) {
        changeToFragment(new MailCodeAuthFragment(), bundle);
    }

    public void changeToFragment(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        changeToFragment(fragment);
    }

    @Override // ru.mail.auth.FragmentNavigatorInterface
    public void changeToFragmentFromExtra(Intent intent) {
        setIntent(intent);
        String stringExtra = intent.getStringExtra(Authenticator.PARAM_EMAIL_SERVICE_TYPE);
        String stringExtra2 = intent.getStringExtra(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE);
        String stringExtra3 = intent.getStringExtra(Authenticator.EXTRA_ADD_ACCOUNT_LOGIN);
        boolean booleanExtra = intent.getBooleanExtra("REGISTER_NEW_MYCOM_ACCOUNT", false);
        boolean booleanExtra2 = intent.getBooleanExtra(Authenticator.EXTRA_SKIP_SERVICE_CHOOSER, false);
        if ("LOGIN_TO_MYCOM_DOMAIN".equals(stringExtra) || booleanExtra) {
            loginMyCom();
            return;
        }
        if (!isNeedOauthScreen(stringExtra2) && !TextUtils.isEmpty(stringExtra) && (!TextUtils.isEmpty(stringExtra3) || booleanExtra2)) {
            loginDefault(stringExtra, false);
            return;
        }
        if (isNeedOauthScreen(stringExtra2) && (!TextUtils.isEmpty(stringExtra3) || booleanExtra2)) {
            startWebView(stringExtra2, stringExtra3);
        } else if (TextUtils.isEmpty(stringExtra2)) {
            clearIntentData();
        } else {
            loginDefault("LOGIN_TO_OTHER_DOMAIN", false);
        }
    }

    public void changeToFragmentNoAnimations(Fragment fragment, boolean z10, String str) {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(ru.mail.Authenticator.R.id.login_fragment, fragment, str);
        if (z10) {
            fragmentTransactionBeginTransaction.addToBackStack(str);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    public void changeToFragmentSingleTop(Fragment fragment, boolean z10, String str) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        List<Fragment> fragments = supportFragmentManager.getFragments();
        if (!fragments.isEmpty()) {
            String tag = fragments.get(fragments.size() - 1).getTag();
            if (tag == null || !tag.equals(str) || supportFragmentManager.isStateSaved()) {
                fragmentTransactionBeginTransaction.setCustomAnimations(ru.mail.Authenticator.R.anim.fragment_open_in, ru.mail.Authenticator.R.anim.fragment_exit_in, ru.mail.Authenticator.R.anim.fragment_open_out, ru.mail.Authenticator.R.anim.fragment_exit_out);
            } else {
                fragmentTransactionBeginTransaction.setCustomAnimations(0, 0, ru.mail.Authenticator.R.anim.fragment_open_out, ru.mail.Authenticator.R.anim.fragment_exit_out);
                supportFragmentManager.popBackStack();
            }
        }
        fragmentTransactionBeginTransaction.replace(ru.mail.Authenticator.R.id.login_fragment, fragment, str);
        if (z10) {
            fragmentTransactionBeginTransaction.addToBackStack(str);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    public void changeToServiceChooserFragment() {
        changeToServiceChooserFragment(true, true);
    }

    @Override // ru.mail.auth.BaseAuthActivity
    protected boolean checkAccountAlreadyLogin(String str) {
        if (SocialLoginInfoHolder.isCurrentlyBindingEmail()) {
            return false;
        }
        return super.checkAccountAlreadyLogin(str);
    }

    protected void checkLoginSuggestFragment() {
        new LoginSuggestFragmentAccessor(getLoginSuggestFragment(), getSettings()).access();
    }

    protected DoregistrationFragment createDoregistrationFragment() {
        return new DoregistrationFragment();
    }

    protected LoginFlowNavigator createFlowNavigator() {
        return new LoginFlowNavigatorImpl(this, this);
    }

    protected abstract Fragment createGooglePickerFragment();

    protected abstract Fragment createLoginScreenFragment(String str);

    protected abstract LoginSuggestFragment createLoginSuggestFragment();

    protected abstract Fragment createMicrosoftLoginFragment();

    protected abstract Fragment createMyComLoginFragment();

    protected Fragment createSecondStepFragment() {
        return new MailSecondStepFragment();
    }

    protected abstract Fragment createServiceChooserFragment();

    protected abstract Fragment createVkBindInLoginFragment(String str);

    @Override // ru.mail.auth.BaseToolbarActivity
    protected void doRegistrationFragmentOnBackPressCheck() {
        if (this.newAuthorizationSdkConfig.isAnyLoginEnabled() && (getActualFragment() instanceof DoregistrationFragment)) {
            createLoginScreenFragment(EmailServiceResources.MailServiceResources.MAILRU.getService());
        }
    }

    @Override // ru.mail.auth.BaseToolbarActivity
    @Nullable
    protected OnBackPressedCallback findBackPressedCallback() {
        ActivityResultCaller actualFragment = getActualFragment();
        if (actualFragment instanceof OnBackPressedCallback) {
            return (OnBackPressedCallback) actualFragment;
        }
        return null;
    }

    @Override // ru.mail.ui.accessibility.ChangeAccessibilityActivity
    @NonNull
    public AccessibilityViewManager getAccessibilityViewManager() {
        return this.mAccessibilityViewManager.get();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [ru.mail.registration.ui.AuthDelegate] */
    @Override // ru.mail.auth.LoginSuggestFragment.LoginSuggestInterface
    public String getAccountType() {
        return getAuthDelegate().getAccountType();
    }

    @Override // ru.mail.auth.LoginFragmentSearcher
    @Nullable
    public Fragment getActualFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        int backStackEntryCount = supportFragmentManager.getBackStackEntryCount() - 1;
        if (backStackEntryCount < 0) {
            return getServiceChooserFragment();
        }
        Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag(supportFragmentManager.getBackStackEntryAt(backStackEntryCount).getName());
        return fragmentFindFragmentByTag == null ? getServiceChooserFragment() : fragmentFindFragmentByTag;
    }

    @Keep
    public String getExtraFrom() {
        Bundle extras = getIntent().getExtras();
        boolean zIsFromVkApp = MatchingExtKt.isFromVkApp(getIntent().getExtras());
        if (extras != null && extras.containsKey(EXTRA_LOGIN_FROM)) {
            return extras.getString(EXTRA_LOGIN_FROM);
        }
        if (zIsFromVkApp) {
            return ReversedMatchingVkConstants.FROM_VK;
        }
        return null;
    }

    @Override // ru.mail.auth.LoginSuggestFragment.LoginSuggestInterface
    public String getFlurryFrom() {
        return "SelectService";
    }

    protected int getLoginActivityLayout() {
        return ru.mail.Authenticator.R.layout.login_activity;
    }

    @Override // ru.mail.auth.LoginFragmentSearcher
    @Nullable
    public Fragment getLoginWelcomeFragment() {
        return getSupportFragmentManager().findFragmentByTag(WELCOME_FRAGMENT_TAG);
    }

    protected LoginFlowNavigator getOrCreateFlowNavigator() {
        if (this.mFlowNavigator == null) {
            this.mFlowNavigator = createFlowNavigator();
        }
        return this.mFlowNavigator;
    }

    @Override // ru.mail.auth.BaseAuthActivity
    protected RestoreVkidFlags getRestoreVkidFlags() {
        return this.newAuthorizationSdkConfig.getSocialAuthConfig().getRestoreVkidFlags();
    }

    @Override // ru.mail.auth.LoginFragmentSearcher
    @Nullable
    public Fragment getServiceChooserFragment() {
        return getSupportFragmentManager().findFragmentByTag(SERVICE_CHOOSER_FRAGMENT_TAG);
    }

    protected String getVkAccessTokenFromBundle(Bundle bundle) {
        String string = bundle.getString(MailAccountConstants.MAIL_RU_REG_ACT_VK_ACCESS_TOKEN_KEY);
        return !TextUtils.isEmpty(string) ? string : "";
    }

    boolean hasGoogleAccount() {
        Account[] externalAccountsByType = Authenticator.getAccountManagerWrapper(getApplicationContext()).getExternalAccountsByType("com.google");
        return externalAccountsByType != null && externalAccountsByType.length > 0;
    }

    @Override // ru.mail.authorizesdk.presentation.common.ProgressBarSdk
    public void hideProgress() {
        CustomProgress customProgress = this.mProgressDialog;
        if (customProgress == null || !customProgress.isShowing()) {
            return;
        }
        try {
            this.mProgressDialog.dismiss();
        } catch (RuntimeException e10) {
            LOG.e("mProgressDialog dismiss exception " + e10.getMessage());
        }
    }

    @Override // ru.mail.auth.LoginFragmentInitializer
    public void initLoginSuggestFragment(@IdRes int i10) {
        if (getLoginSuggestFragment() == null) {
            LoginSuggestFragment loginSuggestFragmentCreateLoginSuggestFragment = createLoginSuggestFragment();
            if (new LoginSuggestFragmentAccessor(loginSuggestFragmentCreateLoginSuggestFragment, getSettings()).shouldBeIncluded()) {
                getSupportFragmentManager().beginTransaction().add(i10, loginSuggestFragmentCreateLoginSuggestFragment, getLoginSuggestFragmentTag()).commit();
                getSupportFragmentManager().executePendingTransactions();
            }
        }
    }

    @Override // ru.mail.auth.LoginFragmentInitializer
    public boolean initServiceChooser() {
        boolean booleanExtra = getIntent().getBooleanExtra(Authenticator.EXTRA_SKIP_SERVICE_CHOOSER, false);
        boolean z10 = !booleanExtra;
        if (!booleanExtra) {
            showServiceChooserFragment();
            initLoginSuggestFragment(ru.mail.Authenticator.R.id.login_suggest_fragment);
        }
        return z10;
    }

    @Override // ru.mail.auth.LoginFragmentInitializer
    public void initStartingFragments() {
        initServiceChooser();
        changeToFragmentFromExtra(getIntent());
    }

    @Override // ru.mail.auth.AnimationStateProvider
    public boolean isAnimationEnabled() {
        return this.mIsAnimationEnabled;
    }

    public boolean isDeeplinkOpened() {
        return false;
    }

    @Override // ru.mail.auth.BaseAuthActivity
    protected boolean isResetSoftVkIdEnabled() {
        return AuthenticatorConfig.getInstance().isResetSoftVkIdEnabled();
    }

    @Override // ru.mail.auth.BaseAuthActivity
    protected boolean isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled() {
        return this.newAuthorizationSdkConfig.getSocialAuthConfig().isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled();
    }

    @Override // ru.mail.auth.BaseAuthActivity
    protected boolean isSoftVkidAutologinDisabled() {
        return AuthenticatorConfig.getInstance().isSoftVkidAutologinDisabled();
    }

    protected boolean isWebAuthNDisablerPromoClicked() {
        return false;
    }

    protected void loginDefault(String str, boolean z10) {
        loginInternal(createLoginScreenFragment(str), str, z10);
    }

    public void loginGoogle() {
        LOG.d("loginGoogle()");
        if (!needShowGoogleAccountPicker()) {
            startAuthenticate(null, null, Authenticator.Type.OAUTH);
        } else {
            this.analytics.showGooglePickerScreen();
            showGooglePickerScreen();
        }
    }

    protected void loginMicrosoft(EmailServiceResources.MailServiceResources mailServiceResources) {
        LOG.d("loginMicrosoft()");
        loginInternal(createMicrosoftLoginFragment(), mailServiceResources.getService(), true);
    }

    protected void loginMyCom() {
        loginInternal(createMyComLoginFragment(), "LOGIN_TO_MYCOM_DOMAIN", true);
    }

    protected void loginYahoo() {
        LOG.d("loginYahoo()");
        startAuthenticate(null, null, Authenticator.Type.YAHOO_OAUTH);
    }

    protected void loginYandex() {
        LOG.d("loginYandex()");
        startAuthenticate(null, null, Authenticator.Type.YANDEX_OAUTH);
    }

    protected boolean needShowGoogleAccountPicker() {
        return hasGoogleAccount();
    }

    protected void notifyAuthError(String str, int i10) {
        Bundle bundle = new Bundle();
        bundle.putInt("errorCode", i10);
        onMessageHandle(new Message(Message.Id.ON_AUTH_ERROR, bundle, str));
    }

    @Override // ru.mail.ui.accessibility.ChangeAccessibilityActivity
    public void onAccessibilityImportanceChange(int i10) {
        View viewFindViewById = findViewById(ru.mail.Authenticator.R.id.login_fragment);
        if (viewFindViewById != null) {
            viewFindViewById.setImportantForAccessibility(i10);
        }
    }

    protected void onAccountBindSuccessAfterSuccessAuth(String str, String str2) {
        int i10 = AnonymousClass2.$SwitchMap$ru$mail$credentialsexchanger$core$CredentialsExchanger$SocialBindType[CredentialsExchanger.SocialBindType.fromString(str2).ordinal()];
        if (i10 == 1) {
            this.analytics.onSuccessVkBind(str, "auth");
        } else if (i10 == 2) {
            this.analytics.onSuccessVkBind(str, "VKID_BIND_IN_LOGIN");
        } else {
            if (i10 != 3) {
                return;
            }
            this.analytics.onSuccessEsiaBind();
        }
    }

    @Override // ru.mail.auth.BaseAuthActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i10, int i11, Intent intent) {
        super.onActivityResult(i10, i11, intent);
        if (isUserLoggedInResult(i10, i11)) {
            setResult(-1);
            finish();
            return;
        }
        if ((i10 == 192 || i10 == 3466) && i11 == 2) {
            this.mIsActivityRefreshing = true;
            getIntent().putExtra(EXTRA_LOGIN_FROM, "Welcome");
            openNewLoginIfPossible(EmailServiceResources.MailServiceResources.MAILRU.getService());
        } else {
            if (i10 != 3466) {
                setResultForActualFragment(i10, i11, intent);
                return;
            }
            EmailServiceResources.MailServiceResources mailServiceResources = EmailServiceResources.MailServiceResources.MAILRU;
            openNewLoginIfPossible(mailServiceResources.getService());
            if (this.newAuthorizationSdkConfig.isAnyLoginEnabled()) {
                createLoginScreenFragment(mailServiceResources.getService());
            }
        }
    }

    @Override // ru.mail.auth.BaseAuthFragment.ErrorDisplay
    public void onAuthCanceled() {
        finishIfNeed();
    }

    @Override // ru.mail.auth.BaseAuthFragment.ErrorDisplay
    public void onAuthError() {
        finishIfNeed();
    }

    @Override // ru.mail.authorizesdk.presentation.common.ErrorDisplaySdk
    public void onAuthErrorCall() {
        onAuthError();
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [ru.mail.registration.ui.AuthDelegate] */
    @Override // ru.mail.auth.BaseAuthActivity
    protected void onAuthSucceeded(Bundle bundle) {
        if (this.newAuthorizationSdkConfig.isABExperiment()) {
            bundle.putBoolean(MailAccountConstants.AUTH_ANALYTICS_BY_GROUP_ENABLED, true);
            bundle.putBoolean(MailAccountConstants.AUTH_ANALYTICS_IS_A_GROUP, this.newAuthorizationSdkConfig.isAnyLoginEnabled());
        }
        String string = bundle.getString("authAccount");
        if (getLoginChecker().accountAlreadyLogin(this, string)) {
            getAuthDelegate().switchToAccount(string, this);
        } else {
            bundle.putString(Authenticator.PARAM_EMAIL_SERVICE_TYPE, this.mServiceType);
            super.onAuthSucceeded(bundle);
        }
    }

    @Override // ru.mail.auth.Hilt_LoginActivity, ru.mail.auth.BaseAuthActivity, ru.mail.auth.BaseToolbarActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityResultLauncher activityResultLauncherRegisterForActivityResult = registerForActivityResult(new AuthActivityContract(), new ActivityResultCallback() { // from class: ru.mail.auth.v1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                this.f80841a.lambda$onCreate$0((AuthResult) obj);
            }
        });
        final Analytics analytics = this.analytics;
        Objects.requireNonNull(analytics);
        this.activityLauncher = new GatedAuthActivityLauncher(activityResultLauncherRegisterForActivityResult, this, new AuthAnalyticsSdk() { // from class: ru.mail.auth.i1
            @Override // ru.mail.authorizationsdk.external.analytics.AuthAnalyticsSdk
            public final void onAnalyticEvent(String str, Map map) {
                analytics.onNewAuthorizeSdkGateEvent(str, map);
            }
        }, new Runnable() { // from class: ru.mail.auth.j1
            @Override // java.lang.Runnable
            public final void run() {
                this.f80761a.showProgress();
            }
        }, new Runnable() { // from class: ru.mail.auth.k1
            @Override // java.lang.Runnable
            public final void run() {
                this.f80763a.hideProgress();
            }
        }, new Runnable() { // from class: ru.mail.auth.l1
            @Override // java.lang.Runnable
            public final void run() {
                this.f80765a.lambda$onCreate$1();
            }
        }, new BooleanSupplier() { // from class: ru.mail.auth.m1
            @Override // java.util.function.BooleanSupplier
            public final boolean getAsBoolean() {
                return this.f80769a.lambda$onCreate$2();
            }
        });
        this.newExternalAuthViewModel = (NewExternalAuthViewModel) new ViewModelProvider(this).get(NewExternalAuthViewModel.class);
        ViewModelProvider viewModelProvider = ViewModelObtainerKt.getViewModelObtainer(this).getViewModelProvider();
        this.loginActivityViewModel = (LoginActivityViewModel) viewModelProvider.get(LoginActivityViewModel.class);
        this.loginActivityAuthorizationViewModel = (LoginActivityAuthorizationViewModel) viewModelProvider.get(LoginActivityAuthorizationViewModel.class);
        this.analytics.loginView(getExtraFrom());
        setContentView(getLoginActivityLayout());
        initActionBar();
        LoginFlowNavigator orCreateFlowNavigator = getOrCreateFlowNavigator();
        this.mFlowNavigator = orCreateFlowNavigator;
        orCreateFlowNavigator.launchFlow(bundle);
        FragmentKt.collectByLifecycle(this, this.newExternalAuthViewModel.getAuthEvent(), new Callback() { // from class: ru.mail.auth.n1
            @Override // ru.mail.authorizesdk.util.extensions.Callback
            public final void action(Object obj) {
                this.f80772a.lambda$onCreate$3((Screen) obj);
            }
        });
        FragmentKt.collectByLifecycle(this, this.loginActivityViewModel.getViewEvent(), new Callback() { // from class: ru.mail.auth.o1
            @Override // ru.mail.authorizesdk.util.extensions.Callback
            public final void action(Object obj) {
                this.f80776a.viewEventExecutor((ViewEvent) obj);
            }
        });
        FragmentKt.collectByLifecycle(this, this.loginActivityViewModel.getLoadingEvent(), new Callback() { // from class: ru.mail.auth.p1
            @Override // ru.mail.authorizesdk.util.extensions.Callback
            public final void action(Object obj) {
                this.f80783a.lambda$onCreate$4((Boolean) obj);
            }
        });
        FragmentKt.collectByLifecycle(this, this.loginActivityAuthorizationViewModel.isLoading(), new Callback() { // from class: ru.mail.auth.q1
            @Override // ru.mail.authorizesdk.util.extensions.Callback
            public final void action(Object obj) {
                this.f80786a.lambda$onCreate$5((Boolean) obj);
            }
        });
        FragmentKt.collectByLifecycle(this, this.loginActivityAuthorizationViewModel.getViewEvent(), new Callback() { // from class: ru.mail.auth.w1
            @Override // ru.mail.authorizesdk.util.extensions.Callback
            public final void action(Object obj) {
                this.f80843a.viewAuthorizationSdkEventExecutor((ViewEvent) obj);
            }
        });
        FragmentKt.collectByLifecycle(this, this.loginActivityAuthorizationViewModel.getNavEvent(), new Callback() { // from class: ru.mail.auth.x1
            @Override // ru.mail.authorizesdk.util.extensions.Callback
            public final void action(Object obj) {
                this.f80857a.lambda$onCreate$6((ViewEvent.Navigation) obj);
            }
        });
        FragmentKt.collectByLifecycle(this, this.loginActivityViewModel.getNavEvent(), new Callback() { // from class: ru.mail.auth.y1
            @Override // ru.mail.authorizesdk.util.extensions.Callback
            public final void action(Object obj) {
                this.f80860a.lambda$onCreate$7((ViewEvent) obj);
            }
        });
        this.loginActivityViewModel.getNavEvent().setResultListener(this);
        getSupportFragmentManager().setFragmentResultListener(NativeGoogleSignInFragment.REQUEST_KEY, this, new FragmentResultListener() { // from class: ru.mail.auth.z1
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle2) {
                this.f80862a.lambda$onCreate$8(str, bundle2);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(BaseSecondStepAuthFragment.REQUEST_KEY, this, new FragmentResultListener() { // from class: ru.mail.auth.a2
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle2) {
                this.f80689a.lambda$onCreate$9(str, bundle2);
            }
        });
        getSupportFragmentManager().setFragmentResultListener(MailLoginFragment.getResultKey(), this, new FragmentResultListener() { // from class: ru.mail.auth.b2
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str, Bundle bundle2) {
                this.f80736a.lambda$onCreate$10(str, bundle2);
            }
        });
    }

    @Override // ru.mail.auth.Hilt_LoginActivity, ru.mail.auth.BaseAuthActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        SocialAuthDelegate.setEnteredEmail(null);
        super.onDestroy();
    }

    @Override // ru.mail.auth.BaseAuthActivity, ru.mail.auth.AuthMessageCallback
    public void onMessageHandle(Message message) {
        super.onMessageHandle(message);
        message.accept(new LoginUIVisitor());
        ActivityResultCaller actualFragment = getActualFragment();
        if (actualFragment instanceof AuthMessageCallback) {
            ((AuthMessageCallback) actualFragment).onMessageHandle(message);
        }
    }

    @Override // ru.mail.auth.BaseAuthActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!this.newAuthorizationSdkConfig.isAnyLoginEnabled() || this.newAuthorizationSdkConfig.isXmailExtraFlowEnabled()) {
            startXmailMigrationIfNeed(intent);
        }
        this.analytics.loginView(getExtraFrom());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mAccessibilityViewManager.get().removeListener(this);
    }

    protected void onRequestNewAddAccount() {
        addServiceChooserFragment();
    }

    @Override // ru.mail.auth.BaseAuthActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mIsActivityRefreshing) {
            this.mIsActivityRefreshing = false;
            refreshActivityState();
        }
        if (AccessibilityUtils.isScreenReaderOn(this)) {
            this.mAccessibilityViewManager.get().addListener(this);
        }
        if (isWebAuthNDisablerPromoClicked() && this.mIsWebAuthNDisablerNotClicked) {
            this.mIsWebAuthNDisablerNotClicked = false;
            startVKConnectAuth(false, true, true);
        }
    }

    @Override // ru.mail.auth.BaseToolbarActivity
    protected void onToolbarBackClicked() {
        BaseToolbarActivity.hideKeyboard(this);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (supportFragmentManager.getBackStackEntryCount() > 0) {
            supportFragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    protected boolean openNewLoginIfPossible(String str) {
        return false;
    }

    protected void openQrScannerFromNewSdk() {
        this.activityLauncher.launch(AuthMode.QrAuth.INSTANCE);
    }

    protected void openYandexHelp() {
        this.activityLauncher.launch(AuthMode.YandexHelp.INSTANCE);
    }

    protected RegistrationComposeScreen prepareRegistrationScreen(String str, Bundle bundle) {
        return new RegistrationComposeScreen(null, null, false, null, null, null, getVkAccessTokenFromBundle(bundle));
    }

    @Override // ru.mail.authorizesdk.presentation.vkauth.VkAuthSdk
    public void prepareVkAuth() {
        if (this.socialAuthConfig.isInitEnabled()) {
            return;
        }
        CredentialsExchanger.INSTANCE.setUnauthorized(AuthenticatorConfig.getInstance().getOAuthParamsCreator().createVKConnectProvider().getParams(Authenticator.ValidAccountTypes.VK.getValue(), this).getClientId());
        VKConnectSignInDelegate vKConnectSignInDelegateCreateVKConnectDelegate = createVKConnectDelegate();
        if (getActualFragment() instanceof AuthScreen) {
            FragmentKt.collectByLifecycle(this, vKConnectSignInDelegateCreateVKConnectDelegate.getProgressBar(), new Callback() { // from class: ru.mail.auth.s1
                @Override // ru.mail.authorizesdk.util.extensions.Callback
                public final void action(Object obj) {
                    this.f80811a.lambda$prepareVkAuth$11((Boolean) obj);
                }
            });
        }
    }

    protected void processResultXmailMigrationNativeReg(final Bundle bundle) {
        String string = bundle.getString(MailAccountConstants.ACTION_XMAIL_REG);
        new XmailRegPrepareCommand(this, string).execute(ExecutorSelectors.defaultSelector()).observe(Schedulers.mainThread(), new ObservableFuture.Observer<Object>() { // from class: ru.mail.auth.LoginActivity.1
            final String networkErrorMsg;

            {
                this.networkErrorMsg = LoginActivity.this.getResources().getString(ru.mail.Authenticator.R.string.network_error);
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onCancelled() {
                LoginActivity.this.showAuthErrorToast(this.networkErrorMsg);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onDone(Object obj) {
                Bundle bundle2 = new Bundle();
                AuthUtil.proxyStringParam(bundle2, bundle, "login_extra_xmail_migration_from");
                if (obj instanceof XmailRegPrepareCommand.Result) {
                    XmailRegPrepareCommand.Result result = (XmailRegPrepareCommand.Result) obj;
                    if (result.getSignupToken() == null) {
                        LoginActivity.this.showAuthErrorToast(this.networkErrorMsg);
                        return;
                    }
                    LoginActivity.this.analytics.onMigrationDataLoaded(bundle.getString(MailLoginFragment.EXTRA_ACCOUNT_REQUESTED_AUTH), bundle.getString("login_extra_xmail_migration_from"), result.getMigrantLogin());
                    bundle2.putParcelable("xmail_reg_prepare_result", result);
                    LoginActivity.this.onMessageHandle(new Message(Message.Id.ON_REGISTRATION_STARTED));
                    LoginActivity.this.startRegistration(AuthSource.XMAIL_MIGRATION, bundle2);
                    LoginActivity.this.finishIfNeed();
                }
                if (obj instanceof CommandStatus.ERROR) {
                    V data = ((CommandStatus.ERROR) obj).getData();
                    if (data instanceof Integer) {
                        Integer num = (Integer) data;
                        if (num.intValue() != 409) {
                            LoginActivity.this.showAuthErrorToast(num, null);
                            return;
                        }
                        bundle2.putBoolean("xmail_reg_not_gmail_acc", true);
                        LoginActivity.this.onMessageHandle(new Message(Message.Id.ON_REGISTRATION_STARTED));
                        LoginActivity.this.startRegistration(AuthSource.XMAIL_MIGRATION, bundle2);
                        LoginActivity.this.finishIfNeed();
                    }
                }
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onError(Exception exc) {
                LoginActivity.this.showAuthErrorToast(this.networkErrorMsg);
            }
        });
    }

    @Override // ru.mail.auth.LoginStateInfo
    public boolean shouldMoveToRegActivity() {
        return getIntent().getBooleanExtra(Authenticator.MOVE_TO_REG_PARAMS, false);
    }

    protected void showGooglePickerScreen() {
        loginInternal(createGooglePickerFragment(), "LOGIN_TO_GOOGLE_DOMAIN", true);
    }

    public void showLoginScreen(String str, String str2) {
        Bundle extras = getIntent().getExtras();
        if (isXmailMigrationFromNotLogin(extras)) {
            if (this.newAuthorizationSdkConfig.isABExperiment()) {
                this.analytics.startGoogleNativeReplaceLoginXmailFlow();
            }
            this.loginActivityAuthorizationViewModel.navigateTo(new GoogleNativeComposeScreen("", extras.getString("login_extra_xmail_migration_from", "")), null);
            return;
        }
        boolean zIsLoginVkEnabled = this.newAuthorizationSdkConfig.isLoginVkEnabled();
        boolean z10 = false;
        if (extras != null && extras.getBoolean("is_maual_logout", false)) {
            z10 = true;
        }
        this.loginActivityAuthorizationViewModel.navigateTo(new LoginComposeScreen(str, zIsLoginVkEnabled, str2, z10, isDeeplinkOpened()), null);
    }

    public void showPasswordScreen(String str, String str2, Boolean bool) {
        PasswordComposeScreen passwordComposeScreen = new PasswordComposeScreen(str, str2, bool.booleanValue(), SocialLoginInfoHolder.getBindState());
        SocialLoginInfoHolder.clear();
        this.loginActivityAuthorizationViewModel.navigateTo(passwordComposeScreen, null);
    }

    @Override // ru.mail.authorizesdk.presentation.common.ProgressBarSdk
    public void showProgress() {
        if (this.mProgressDialog == null) {
            CustomProgress customProgress = new CustomProgress(this);
            this.mProgressDialog = customProgress;
            customProgress.getTextView().setText(ru.mail.Authenticator.R.string.progress_auth);
        }
        this.mProgressDialog.show();
    }

    protected void showServiceChooserFragment() {
        addServiceChooserFragment();
    }

    public void socialAuthEventExecutor(SocialAuthEvent socialAuthEvent) {
        this.isFromSocialAuth = true;
        if (socialAuthEvent instanceof SocialAuthEvent.AuthResult) {
            socialAuthResult(((SocialAuthEvent.AuthResult) socialAuthEvent).getResult());
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.AuthResultWithOnStartEmailAuthBySocial) {
            SocialAuthEvent.AuthResultWithOnStartEmailAuthBySocial authResultWithOnStartEmailAuthBySocial = (SocialAuthEvent.AuthResultWithOnStartEmailAuthBySocial) socialAuthEvent;
            this.analytics.startEmailAuthBySocial(authResultWithOnStartEmailAuthBySocial.getStringToken());
            socialAuthResult(authResultWithOnStartEmailAuthBySocial.getResult());
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.HideKeyboard) {
            BaseToolbarActivity.hideKeyboard(this);
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.StartRegistration) {
            startRegistrationCompat(AuthSource.LOGIN_VIEW, new Bundle());
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.Error) {
            SocialAuthEvent.Error error = (SocialAuthEvent.Error) socialAuthEvent;
            int errorCode = error.getErrorCode();
            showAuthErrorToast(Integer.valueOf(errorCode), error.getErrorMsg());
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.ShowFragment) {
            SocialAuthEvent.ShowFragment showFragment = (SocialAuthEvent.ShowFragment) socialAuthEvent;
            showFragment.getFragment().show(getSupportFragmentManager(), showFragment.getTag());
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.PopBackStackIfAllowed) {
            popBackStackIfAllowed();
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.OpenFragment) {
            SocialAuthEvent.OpenFragment openFragment = (SocialAuthEvent.OpenFragment) socialAuthEvent;
            changeToFragment(openFragment.getFragment(), true, true, openFragment.getFragment().getTag());
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.GetAuthorizedEmailsAndStartVkAuth) {
            if (this.socialAuthConfig.isInitEnabled()) {
                this.loginActivityAuthorizationViewModel.navigateTo(new SocialAuthComposeScreen(new SocialAuthInitMode.CustomEvent(true)), null);
                return;
            }
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.CleanSocialHolder) {
            SocialLoginInfoHolder.clear();
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.OnSkipRegistrationWithVkc) {
            this.analytics.onSkipRegistrationWithVkc();
        }
        if (socialAuthEvent instanceof SocialAuthEvent.OpenVkidBottomSheet) {
            if (this.socialAuthConfig.isInitEnabled()) {
                this.loginActivityAuthorizationViewModel.navigateTo(new SocialAuthComposeScreen(new SocialAuthInitMode.CustomEvent(false)), null);
                return;
            }
            return;
        }
        if (socialAuthEvent instanceof SocialAuthEvent.ShowFragmentSingleTop) {
            SocialAuthEvent.ShowFragmentSingleTop showFragmentSingleTop = (SocialAuthEvent.ShowFragmentSingleTop) socialAuthEvent;
            changeToFragmentSingleTop(showFragmentSingleTop.getFragment(), true, showFragmentSingleTop.getTag());
        }
    }

    public void startCustomServerAuthScreen(Fragment fragment, Bundle bundle, String str, String str2, EmailServiceResources.MailServiceResources mailServiceResources) {
        if (!this.newAuthorizationSdkConfig.isCustomServerAuthEnabled() || this.mIsImapOnly) {
            changeToFragment(fragment, false);
        } else {
            this.loginActivityAuthorizationViewModel.showCustomServerScreen(mailServiceResources, str, str2, false, bundle);
        }
    }

    protected void startGmailAuthByPromo() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        String string = extras.getString(MailAccountConstants.LOGIN_EXTRA_GOOGLE_MAIL_PARAM);
        String string2 = extras.getString(MailAccountConstants.EMAIL_EXTRA_GOOGLE_MAIL_PARAM);
        if (TextUtils.isEmpty(string) || !string.equals(MailAccountConstants.LOGIN_GOOGLE_MAIL_FROM_PROMO) || TextUtils.isEmpty(string2)) {
            return;
        }
        getLoginFragment().authenticate(string2, null, Authenticator.Type.OAUTH, extras);
    }

    protected void startGoogleAuthScreen(Bundle bundle) {
        boolean zIsXmailMigrationFromNotLogin = isXmailMigrationFromNotLogin(bundle);
        if (!bundle.getBoolean(MailAccountConstantsClass.EXTRA_USE_NATIVE_SIGNIN, true)) {
            if (this.newAuthorizationSdkConfig.isABExperiment()) {
                this.analytics.startGoogleWebFromLoginActivity(this.newAuthorizationSdkConfig.isAnyLoginEnabled(), true ^ this.newAuthorizationSdkConfig.isWebGoogleAuthEnabled());
            }
            if (this.newAuthorizationSdkConfig.isWebGoogleAuthEnabled()) {
                startGoogleWebCompose(bundle);
                return;
            } else {
                startBrowserAuth(bundle);
                return;
            }
        }
        if (this.newAuthorizationSdkConfig.isABExperiment()) {
            this.analytics.startGoogleNativeFromLoginActivity(this.newAuthorizationSdkConfig.isAnyLoginEnabled(), this.newAuthorizationSdkConfig.isNativeGoogleAuthEnabled());
        }
        if (this.newAuthorizationSdkConfig.isNativeGoogleAuthEnabled()) {
            startGoogleCompose(bundle);
        } else {
            bundle.putBoolean(NativeGoogleSignInFragment.SHOULD_EXIT_LOGIN_ON_CANCEL, zIsXmailMigrationFromNotLogin);
            changeToFragment(new NativeGoogleSignInFragment(), bundle);
        }
    }

    public void startLoginScreen(EmailServiceResources.MailServiceResources mailServiceResources) {
        if (mailServiceResources.isMicrosoftDomain(this)) {
            loginMicrosoft(mailServiceResources);
            return;
        }
        if (mailServiceResources.isYahooDomain()) {
            loginYahoo();
            return;
        }
        if (mailServiceResources.isYandexDomain()) {
            loginYandex();
            return;
        }
        int i10 = AnonymousClass2.$SwitchMap$ru$mail$auth$EmailServiceResources$MailServiceResources[mailServiceResources.ordinal()];
        if (i10 == 1) {
            loginMyCom();
        } else if (i10 != 2) {
            loginDefault(mailServiceResources.getService(), true);
        } else {
            loginGoogle();
        }
    }

    protected void startLoginScreenForBind(Bundle bundle) {
        Fragment fragmentCreateLoginScreenFragment = createLoginScreenFragment(EmailServiceResources.MailServiceResources.MAILRU.getService());
        fragmentCreateLoginScreenFragment.setArguments(bundle);
        changeToFragment(fragmentCreateLoginScreenFragment);
    }

    protected void startOutlookAuthScreen(Bundle bundle) {
        if (!this.newAuthorizationSdkConfig.isOutlookAuthEnabled()) {
            changeToFragment(new OutlookOauth2AccessTokenFragment(), bundle);
        } else {
            this.loginActivityAuthorizationViewModel.navigateTo(new OutlookComposeScreen(bundle.getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT, "")), new LoginActivityDataState.Common(bundle));
        }
    }

    protected void startQrAuthenticator(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(MailAccountConstants.LOGIN_EXTRA_CGI_BIN_AUTH_URL, str2);
        bundle.putString(MailAccountConstants.LOGIN_EXTRA_CGI_BIN_AUTH_EMAIL, str);
        startAuthenticate(str, str2, Authenticator.Type.QR_LOGIN, bundle);
    }

    @Override // ru.mail.authorizesdk.presentation.loginactivity.StartRegistrationCompanion
    public void startRegistration(String str) {
        startRegistration(str, Bundle.EMPTY);
    }

    public void startRegistrationByActivityItself() {
        startRegistration(AuthSource.SERVICE_CHOOSER);
    }

    public boolean startRegistrationScreenSdk(String str, Bundle bundle) {
        if (!this.newAuthorizationSdkConfig.getRegConfig().isEnabledMain()) {
            return false;
        }
        this.loginActivityAuthorizationViewModel.navigateTo(prepareRegistrationScreen(str, bundle), null);
        return true;
    }

    public void startRestoreVkid(String str, RestoreVkidStartSource restoreVkidStartSource) {
        this.loginActivityAuthorizationViewModel.navigateTo(new SocialAuthComposeScreen(new SocialAuthInitMode.RestoreVkid(str, restoreVkidStartSource)), null);
    }

    public void startRestoreVkidOld(String str, RestoreVkidStartSource restoreVkidStartSource) {
        createVKConnectDelegate().startRestoreVkid(str, restoreVkidStartSource);
    }

    public void startSimpleGoogleLogin(@Nullable String str) {
        getLoginFragment().authenticate(str, null, Authenticator.Type.OAUTH, null);
    }

    public void startSocialAuth(SocialAuthInitMode socialAuthInitMode) {
        this.loginActivityAuthorizationViewModel.navigateTo(new SocialAuthComposeScreen(socialAuthInitMode), null);
    }

    public void startVKAutoLogin(Function1 function1) {
        if (!this.socialAuthConfig.isInitEnabled()) {
            createVKConnectDelegate().startVKAutoLogin(function1);
            return;
        }
        function1.invoke(new AutoLoginProvider.Error("fake call to dismiss progress bar"));
        if (!this.isAutologinLaunched && !this.isFromSocialAuth) {
            SocialAuthSdk.runWhenSdkInitialized("startVKAutoLogin", new Function0() { // from class: ru.mail.auth.r1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.f80795a.lambda$startVKAutoLogin$14();
                }
            });
        } else {
            this.isAutologinLaunched = false;
            this.isFromSocialAuth = false;
        }
    }

    public void startVkEmailForwarding(String str, String str2, boolean z10) {
        boolean z11 = this.socialAuthConfig.isVkEmailForwardingPasswordEnabled() && z10;
        if (this.socialAuthConfig.isInitEnabled()) {
            this.loginActivityAuthorizationViewModel.navigateTo(new SocialAuthComposeScreen(new SocialAuthInitMode.StartVkEmailForwarding(str, str2, z10)), null);
        } else {
            CredentialsExchanger.INSTANCE.setUnauthorized(VKAuthenticator.INSTANCE.getMailRuClientId(getApplicationContext()));
            createVKConnectDelegate().startVkEmailForwarding(str2, z11);
        }
    }

    public void startVkEmailRecoveryFlow(String str, String str2) {
        this.loginActivityAuthorizationViewModel.navigateTo(new BeforeRecoveryComposeScreen(str, str2), null);
    }

    protected void startXmailMigrationIfNeed(Intent intent) {
        Bundle extras = intent.getExtras();
        if (isXmailMigrationFromNotLogin(extras)) {
            this.analytics.startXmailMigration();
            getLoginFragment().authenticate(null, null, Authenticator.Type.OAUTH, extras);
        }
    }

    protected void startYahooAuthScreen(Bundle bundle) {
        if (!this.newAuthorizationSdkConfig.isYahooAuthEnabled()) {
            changeToFragment(new YahooOauth2AccessTokenFragment(), bundle);
        } else {
            this.loginActivityAuthorizationViewModel.navigateTo(new YahooComposeScreen(bundle.getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT, "")), new LoginActivityDataState.Common(bundle));
        }
    }

    protected void startYandexAuthScreen(Bundle bundle) {
        if (!this.newAuthorizationSdkConfig.isYandexAuthEnabled()) {
            changeToFragment(new YandexOauth2AccessTokenFragment(), bundle);
        } else {
            this.loginActivityAuthorizationViewModel.navigateTo(new YandexComposeScreen(bundle.getString(MailAccountConstantsClass.LOGIN_EXTRA_OUATH2_LOGIN_HINT, "")), new LoginActivityDataState.Common(bundle));
        }
    }

    @Override // ru.mail.auth.BaseAuthActivity
    public void switchToAccount(String str) {
        LOG.d("switchToAccount " + str);
        super.switchToAccount(str);
    }

    private String getAccountType(AuthMode authMode) {
        return (authMode != null && (authMode instanceof AuthMode.Yahoo)) ? Authenticator.Type.YAHOO_OAUTH.toString() : "";
    }

    public void changeToServiceChooserFragment(boolean z10, boolean z11) {
        changeToFragment(getOrCreateServiceChooserFragment(), z10, z11, SERVICE_CHOOSER_FRAGMENT_TAG);
    }

    @Override // ru.mail.auth.LoginFragmentSearcher
    @Nullable
    public LoginSuggestFragment getLoginSuggestFragment() {
        return (LoginSuggestFragment) getSupportFragmentManager().findFragmentByTag(getLoginSuggestFragmentTag());
    }

    @Override // ru.mail.authorizesdk.presentation.loginactivity.StartRegistrationCompanion
    public void startRegistration(String str, Bundle bundle) {
        Intent registrationActivityIntent = Authenticator.getRegistrationActivityIntent(getApplicationContext().getPackageName());
        AuthUtil.proxyByteArray(registrationActivityIntent, getIntent().getExtras(), Authenticator.AUTH_RESTORE_PARAMS);
        registrationActivityIntent.putExtra(RegistrationActivity.EXTRA_REG_FROM, str);
        registrationActivityIntent.putExtras(bundle);
        startActivityForResult(registrationActivityIntent, REQUEST_ADD_NEW_MAILRU_ACCOUNT);
    }

    public void changeToFragment(Fragment fragment) {
        changeToFragment(fragment, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAuthErrorToast(Integer num, String str) {
        hideProgress();
        onAuthErrorCall();
        if (str == null) {
            str = AuthErrors.getErrorMessage(this, "", num.intValue());
        }
        if (str == null) {
            str = getResources().getString(ru.mail.Authenticator.R.string.network_error);
        }
        Toast.makeText(this, str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoginScreenForBind(String str, String str2, String str3) {
        CredentialsExchanger.SocialBindType socialBindTypeFromString = CredentialsExchanger.SocialBindType.fromString(str2);
        SocialLoginInfoHolder.setBindState(new SocialLoginInfoHolder.BindState(str, socialBindTypeFromString));
        Bundle bundle = new Bundle();
        bundle.putString(MailAccountConstants.TWO_STEP_VK_ACCESS_TOKEN_KEY, str3);
        bundle.putBoolean(MailAccountConstants.EXTRA_SCREEN_FOR_SOCIAL_BIND, true);
        bundle.putString(CredentialsExchanger.SOCIAL_BIND_TYPE_KEY, socialBindTypeFromString.getStringToken());
        bundle.putBoolean(Authenticator.IS_VK_SIGN_IN_DELEGATE_DISABLED, this.socialAuthConfig.isInitEnabled());
        startLoginScreenForBind(bundle);
    }

    public void changeToFragment(Fragment fragment, boolean z10) {
        changeToFragment(fragment, z10, true);
    }

    public void changeToFragment(Fragment fragment, boolean z10, boolean z11) {
        changeToFragment(fragment, z10, z11, FRAGMENT_TAG);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void changeToFragment(Fragment fragment, boolean z10, boolean z11, String str) {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        if (z10) {
            fragmentTransactionBeginTransaction.setCustomAnimations(ru.mail.Authenticator.R.anim.fragment_open_in, ru.mail.Authenticator.R.anim.fragment_exit_in, ru.mail.Authenticator.R.anim.fragment_open_out, ru.mail.Authenticator.R.anim.fragment_exit_out);
        } else {
            fragmentTransactionBeginTransaction.setCustomAnimations(0, 0, ru.mail.Authenticator.R.anim.fragment_open_out, ru.mail.Authenticator.R.anim.fragment_exit_out);
        }
        fragmentTransactionBeginTransaction.replace(ru.mail.Authenticator.R.id.login_fragment, fragment, str);
        if (fragment instanceof AuthScreen) {
            this.loginActivityViewModel.navigateTo(((AuthScreen) fragment).getScreen(), null);
        }
        if (z11) {
            fragmentTransactionBeginTransaction.addToBackStack(str);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    @Override // ru.mail.auth.LoginFragmentInitializer
    public void proxyToRegisterActivity() {
    }

    protected void switchToValidAccountIfNecessary() {
    }

    protected void addNecessaryProperties(Bundle bundle, String str) {
    }

    protected void startEsiaFlow(String str) {
    }

    protected void startUnblockUserScreen(String str) {
    }

    protected void switchToPassword(String str) {
    }
}
