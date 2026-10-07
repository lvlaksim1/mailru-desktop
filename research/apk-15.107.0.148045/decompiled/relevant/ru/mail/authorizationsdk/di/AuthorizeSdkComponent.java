package ru.mail.authorizationsdk.di;

import androidx.compose.runtime.Stable;
import dagger.BindsInstance;
import dagger.Lazy;
import dagger.Subcomponent;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.di.modules.AuthorizeConfigModule;
import ru.mail.authorizationsdk.di.modules.ExternalMigrationModule;
import ru.mail.authorizationsdk.di.modules.ImageLoadConfigDependModule;
import ru.mail.authorizationsdk.di.modules.NetworkConfigDependModule;
import ru.mail.authorizationsdk.di.modules.OidcRemoteModule;
import ru.mail.authorizationsdk.di.modules.VKIDModule;
import ru.mail.authorizationsdk.di.modules.feature.EnterPhoneModule;
import ru.mail.authorizationsdk.di.modules.feature.ForceVKIDModule;
import ru.mail.authorizationsdk.di.modules.feature.GoogleModule;
import ru.mail.authorizationsdk.di.modules.feature.ImapLocalConfigDependModule;
import ru.mail.authorizationsdk.di.modules.feature.LoginModule;
import ru.mail.authorizationsdk.di.modules.feature.OKModule;
import ru.mail.authorizationsdk.di.modules.feature.SessionRestoreModule;
import ru.mail.authorizationsdk.external.analytics.AuthorizationSdkAnalyticsImpl;
import ru.mail.authorizationsdk.external.config.AuthorizationSdkConfig;
import ru.mail.authorizationsdk.external.config.flavor.FlavorConfig;
import ru.mail.authorizationsdk.feature.authactivity.AuthActivity;
import ru.mail.authorizationsdk.feature.authactivity.AuthViewModel;
import ru.mail.authorizationsdk.feature.beforerecovery.presentation.BeforeRecoveryVKIDViewModel;
import ru.mail.authorizationsdk.feature.bindemail.presentation.BindEmailViewModel;
import ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel;
import ru.mail.authorizationsdk.feature.changepassword.presentation.ChangePasswordViewModel;
import ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel;
import ru.mail.authorizationsdk.feature.enterphone.presentation.EnterPhoneViewModel;
import ru.mail.authorizationsdk.feature.externalmigration.presentation.ExternalAccMigrationViewModel;
import ru.mail.authorizationsdk.feature.google.nativelib.presentation.GoogleAssistedFactory;
import ru.mail.authorizationsdk.feature.google.web.presentation.GoogleWebAuthAssistedFactory;
import ru.mail.authorizationsdk.feature.imaplocal.domain.ImapSettingsProcessorAuthSdk;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.AuthPhoneFlowDataHolder;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.CloudLoginViewModel;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.createcloud.CreateCloudViewModel;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloudvk.CloudLoginVKViewModel;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.mail.LoginViewModel;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.vkmail.LoginVKViewModel;
import ru.mail.authorizationsdk.feature.loginbindflow.presentation.LoginBindFlowViewModel;
import ru.mail.authorizationsdk.feature.mrim.MrimDialogViewModel;
import ru.mail.authorizationsdk.feature.oidcdiscovery.domain.OidcDiscoverRemoteUseCase;
import ru.mail.authorizationsdk.feature.ok.presentation.OKLoginViewModel;
import ru.mail.authorizationsdk.feature.onetimecode.OneTimeCodeViewModel;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookAssistedFactory;
import ru.mail.authorizationsdk.feature.password.presentation.PasswordViewModel;
import ru.mail.authorizationsdk.feature.phone.accountlist.presentation.AccountListViewModel;
import ru.mail.authorizationsdk.feature.phone.codereceivetype.presentation.CodeReceivedTypeBottomSheetViewModel;
import ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeViewModel;
import ru.mail.authorizationsdk.feature.phone.entercode.presentation.di.EnterPhoneCodeModule;
import ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeViewModel;
import ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.di.EnterEmailCodeModule;
import ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccViewModel;
import ru.mail.authorizationsdk.feature.phone.notreceivedcode.presentation.NotReceivedCodeBottomSheetViewModel;
import ru.mail.authorizationsdk.feature.registration.presentation.RegistrationMainViewModel;
import ru.mail.authorizationsdk.feature.registration.presentation.screen.RegistrationViewModel;
import ru.mail.authorizationsdk.feature.registration.presentation.screen.parentselection.ParentSelectionViewModel;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordViewModel;
import ru.mail.authorizationsdk.feature.restorevkpassword.presentation.RestoreVkViewModel;
import ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.presentation.ChoiceAccountViewModel;
import ru.mail.authorizationsdk.feature.socialauth.esiascreen.presentation.EsiaViewModel;
import ru.mail.authorizationsdk.feature.socialauth.presentation.SocialAuthViewModel;
import ru.mail.authorizationsdk.feature.sso.presentation.SSOViewModel;
import ru.mail.authorizationsdk.feature.unblockuser.presentation.UnblockUserViewModel;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.ReturnWorker;
import ru.mail.authorizationsdk.feature.vkbindavailable.presentation.VkBindInLoginViewModel;
import ru.mail.authorizationsdk.feature.vkid.screens.wrongvkidaccount.WrongVkidAccountViewModel;
import ru.mail.authorizationsdk.feature.vkpassword.presentation.VkPasswordViewModel;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooViewModel;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexAssistedFactory;
import ru.mail.authorizationsdk.feature.yandexhelp.YandexHelpViewModel;
import ru.mail.util.log.InternalLogger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000Î\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001mJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0019H&J\b\u0010\u001a\u001a\u00020\u001bH&J\b\u0010\u001c\u001a\u00020\u001dH&J\b\u0010\u001e\u001a\u00020\u001fH&J\b\u0010 \u001a\u00020!H&J\b\u0010\"\u001a\u00020#H&J\b\u0010$\u001a\u00020%H&J\b\u0010&\u001a\u00020'H&J\b\u0010(\u001a\u00020)H&J\b\u0010*\u001a\u00020+H&J\b\u0010,\u001a\u00020-H&J\b\u0010.\u001a\u00020/H&J\b\u00100\u001a\u000201H&J\b\u00102\u001a\u000203H&J\b\u00104\u001a\u000205H&J\b\u00106\u001a\u000207H&J\b\u00108\u001a\u000209H&J\b\u0010:\u001a\u00020;H&J\b\u0010<\u001a\u00020=H&J\b\u0010>\u001a\u00020?H&J\b\u0010@\u001a\u00020AH&J\b\u0010B\u001a\u00020CH&J\b\u0010D\u001a\u00020EH&J\b\u0010F\u001a\u00020GH&J\b\u0010H\u001a\u00020IH&J\b\u0010J\u001a\u00020KH&J\b\u0010L\u001a\u00020MH&J\b\u0010N\u001a\u00020OH&J\b\u0010P\u001a\u00020QH&J\b\u0010R\u001a\u00020SH&J\b\u0010T\u001a\u00020UH&J\b\u0010V\u001a\u00020WH&J\b\u0010X\u001a\u00020YH&J\u000e\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[H&J\b\u0010]\u001a\u00020^H&J\b\u0010_\u001a\u00020`H&J\b\u0010a\u001a\u00020bH&J\b\u0010c\u001a\u00020dH&J\b\u0010e\u001a\u00020fH&J\b\u0010g\u001a\u00020hH&J\b\u0010i\u001a\u00020jH&J\b\u0010k\u001a\u00020lH&¨\u0006nÀ\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/di/AuthorizeSdkComponent;", "", "inject", "", "activity", "Lru/mail/authorizationsdk/feature/authactivity/AuthActivity;", "worker", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/ReturnWorker;", "getAnalytics", "Lru/mail/authorizationsdk/external/analytics/AuthorizationSdkAnalyticsImpl;", "getBaseLogger", "Lru/mail/util/log/InternalLogger;", "getFlavorConfig", "Lru/mail/authorizationsdk/external/config/flavor/FlavorConfig;", "getAuthViewModelFactory", "Lru/mail/authorizationsdk/feature/authactivity/AuthViewModel$Factory;", "getSocialAuthViewModelFactory", "Lru/mail/authorizationsdk/feature/socialauth/presentation/SocialAuthViewModel$Factory;", "getCloudLoginVKViewModelFactory", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloudvk/CloudLoginVKViewModel$Factory;", "getLoginVKViewModelFactory", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/vkmail/LoginVKViewModel$Factory;", "getLoginViewModelFactory", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/mail/LoginViewModel$Factory;", "getChoiceAccViewModelFactory", "Lru/mail/authorizationsdk/feature/socialauth/choicescreen/presentation/ChoiceAccountViewModel$Factory;", "getCloudLoginViewModelFactory", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/CloudLoginViewModel$Factory;", "getCreateCloudViewModelFactory", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/createcloud/CreateCloudViewModel$Factory;", "getAuthPhoneFlowDataHolder", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/AuthPhoneFlowDataHolder;", "getPasswordViewModelFactory", "Lru/mail/authorizationsdk/feature/password/presentation/PasswordViewModel$Factory;", "getEnterPhoneViewModelFactory", "Lru/mail/authorizationsdk/feature/enterphone/presentation/EnterPhoneViewModel$Factory;", "getEnterPhoneCodeViewModelFactory", "Lru/mail/authorizationsdk/feature/phone/entercode/presentation/EnterPhoneCodeViewModel$Factory;", "getEnterEmailCodeViewModelFactory", "Lru/mail/authorizationsdk/feature/phone/enteremailcode/presentation/EnterEmailCodeViewModel$Factory;", "getAccountListViewModelFactory", "Lru/mail/authorizationsdk/feature/phone/accountlist/presentation/AccountListViewModel$Factory;", "getEnterEmailCodeAfterListAccViewModelFactory", "Lru/mail/authorizationsdk/feature/phone/enteremailcodeafterlistacc/presentation/EnterEmailCodeAfterListAccViewModel$Factory;", "getNotReceivedCodeBtmSheetViewModel", "Lru/mail/authorizationsdk/feature/phone/notreceivedcode/presentation/NotReceivedCodeBottomSheetViewModel$Factory;", "getCodeReceivedTypeBottomSheetViewModel", "Lru/mail/authorizationsdk/feature/phone/codereceivetype/presentation/CodeReceivedTypeBottomSheetViewModel$Factory;", "getLoginBindFlowViewModelFactory", "Lru/mail/authorizationsdk/feature/loginbindflow/presentation/LoginBindFlowViewModel$Factory;", "getRestorePasswordViewModel", "Lru/mail/authorizationsdk/feature/restorepassword/presentation/RestorePasswordViewModel$Factory;", "getRestoreVkiDViewModel", "Lru/mail/authorizationsdk/feature/restorevkpassword/presentation/RestoreVkViewModel$Factory;", "getWebCaptchaViewModelFactory", "Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$Factory;", "getOneTimeCodeViewModelFactory", "Lru/mail/authorizationsdk/feature/onetimecode/OneTimeCodeViewModel$Factory;", "getSSOViewModelFactory", "Lru/mail/authorizationsdk/feature/sso/presentation/SSOViewModel$Factory;", "getVkPasswordViewModelFactory", "Lru/mail/authorizationsdk/feature/vkpassword/presentation/VkPasswordViewModel$Factory;", "getGoogleNativeViewModelFactory", "Lru/mail/authorizationsdk/feature/google/nativelib/presentation/GoogleAssistedFactory;", "getGoogleWebAuthViewModelFactory", "Lru/mail/authorizationsdk/feature/google/web/presentation/GoogleWebAuthAssistedFactory;", "getSecondFactorViewModelFactory", "Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel$Factory;", "getEsiaViewModelFactory", "Lru/mail/authorizationsdk/feature/socialauth/esiascreen/presentation/EsiaViewModel$Factory;", "getBindEmailViewModelFactory", "Lru/mail/authorizationsdk/feature/bindemail/presentation/BindEmailViewModel$Factory;", "getExternalAccMigrationFactory", "Lru/mail/authorizationsdk/feature/externalmigration/presentation/ExternalAccMigrationViewModel$Factory;", "getYahooViewModelFactory", "Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooViewModel$Factory;", "getYandexViewModelFactory", "Lru/mail/authorizationsdk/feature/yandex/presentation/YandexAssistedFactory;", "getUserBlockedDialogViewModelFactory", "Lru/mail/authorizationsdk/feature/mrim/MrimDialogViewModel$Factory;", "getWrongVkidAccountViewModelFactory", "Lru/mail/authorizationsdk/feature/vkid/screens/wrongvkidaccount/WrongVkidAccountViewModel$Factory;", "getYandexHelpViewModelFactory", "Lru/mail/authorizationsdk/feature/yandexhelp/YandexHelpViewModel$Factory;", "getOKLoginViewModelFactory", "Lru/mail/authorizationsdk/feature/ok/presentation/OKLoginViewModel$Factory;", "getOutlookViewModelFactory", "Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookAssistedFactory;", "getCustomServerViewModelFactory", "Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerViewModel$Factory;", "getImapSettingsProcessorAuthSdk", "Ldagger/Lazy;", "Lru/mail/authorizationsdk/feature/imaplocal/domain/ImapSettingsProcessorAuthSdk;", "getBeforeRecoveryVKIDViewModelFactory", "Lru/mail/authorizationsdk/feature/beforerecovery/presentation/BeforeRecoveryVKIDViewModel$Factory;", "getUnblockUserViewModelFactory", "Lru/mail/authorizationsdk/feature/unblockuser/presentation/UnblockUserViewModel$Factory;", "getChangePasswordViewModelFactory", "Lru/mail/authorizationsdk/feature/changepassword/presentation/ChangePasswordViewModel$Factory;", "getRegistrationMainViewModelFactory", "Lru/mail/authorizationsdk/feature/registration/presentation/RegistrationMainViewModel$Factory;", "getRegistrationViewModelFactory", "Lru/mail/authorizationsdk/feature/registration/presentation/screen/RegistrationViewModel$Factory;", "getParentSelectionViewModelFactory", "Lru/mail/authorizationsdk/feature/registration/presentation/screen/parentselection/ParentSelectionViewModel$Factory;", "getVkBindInLoginViewModelFactory", "Lru/mail/authorizationsdk/feature/vkbindavailable/presentation/VkBindInLoginViewModel$Factory;", "getOidcDiscoveryUseCase", "Lru/mail/authorizationsdk/feature/oidcdiscovery/domain/OidcDiscoverRemoteUseCase;", "Factory", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Stable
@Subcomponent(modules = {AuthorizeConfigModule.class, SessionRestoreModule.class, LoginModule.class, ExternalMigrationModule.class, ForceVKIDModule.class, VKIDModule.class, ImapLocalConfigDependModule.class, NetworkConfigDependModule.class, OidcRemoteModule.class, ImageLoadConfigDependModule.class, EnterPhoneModule.class, EnterPhoneCodeModule.class, EnterEmailCodeModule.class, OKModule.class, GoogleModule.class})
@ConfigScope
public interface AuthorizeSdkComponent {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/di/AuthorizeSdkComponent$Factory;", "", "create", "Lru/mail/authorizationsdk/di/AuthorizeSdkComponent;", "config", "Lru/mail/authorizationsdk/external/config/AuthorizationSdkConfig;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @Subcomponent.Factory
    public interface Factory {
        @NotNull
        AuthorizeSdkComponent create(@BindsInstance @NotNull AuthorizationSdkConfig config);
    }

    @NotNull
    AccountListViewModel.Factory getAccountListViewModelFactory();

    @NotNull
    AuthorizationSdkAnalyticsImpl getAnalytics();

    @NotNull
    AuthPhoneFlowDataHolder getAuthPhoneFlowDataHolder();

    @NotNull
    AuthViewModel.Factory getAuthViewModelFactory();

    @NotNull
    InternalLogger getBaseLogger();

    @NotNull
    BeforeRecoveryVKIDViewModel.Factory getBeforeRecoveryVKIDViewModelFactory();

    @NotNull
    BindEmailViewModel.Factory getBindEmailViewModelFactory();

    @NotNull
    ChangePasswordViewModel.Factory getChangePasswordViewModelFactory();

    @NotNull
    ChoiceAccountViewModel.Factory getChoiceAccViewModelFactory();

    @NotNull
    CloudLoginVKViewModel.Factory getCloudLoginVKViewModelFactory();

    @NotNull
    CloudLoginViewModel.Factory getCloudLoginViewModelFactory();

    @NotNull
    CodeReceivedTypeBottomSheetViewModel.Factory getCodeReceivedTypeBottomSheetViewModel();

    @NotNull
    CreateCloudViewModel.Factory getCreateCloudViewModelFactory();

    @NotNull
    CustomServerViewModel.Factory getCustomServerViewModelFactory();

    @NotNull
    EnterEmailCodeAfterListAccViewModel.Factory getEnterEmailCodeAfterListAccViewModelFactory();

    @NotNull
    EnterEmailCodeViewModel.Factory getEnterEmailCodeViewModelFactory();

    @NotNull
    EnterPhoneCodeViewModel.Factory getEnterPhoneCodeViewModelFactory();

    @NotNull
    EnterPhoneViewModel.Factory getEnterPhoneViewModelFactory();

    @NotNull
    EsiaViewModel.Factory getEsiaViewModelFactory();

    @NotNull
    ExternalAccMigrationViewModel.Factory getExternalAccMigrationFactory();

    @NotNull
    FlavorConfig getFlavorConfig();

    @NotNull
    GoogleAssistedFactory getGoogleNativeViewModelFactory();

    @NotNull
    GoogleWebAuthAssistedFactory getGoogleWebAuthViewModelFactory();

    @NotNull
    Lazy<ImapSettingsProcessorAuthSdk> getImapSettingsProcessorAuthSdk();

    @NotNull
    LoginBindFlowViewModel.Factory getLoginBindFlowViewModelFactory();

    @NotNull
    LoginVKViewModel.Factory getLoginVKViewModelFactory();

    @NotNull
    LoginViewModel.Factory getLoginViewModelFactory();

    @NotNull
    NotReceivedCodeBottomSheetViewModel.Factory getNotReceivedCodeBtmSheetViewModel();

    @NotNull
    OKLoginViewModel.Factory getOKLoginViewModelFactory();

    @NotNull
    OidcDiscoverRemoteUseCase getOidcDiscoveryUseCase();

    @NotNull
    OneTimeCodeViewModel.Factory getOneTimeCodeViewModelFactory();

    @NotNull
    OutlookAssistedFactory getOutlookViewModelFactory();

    @NotNull
    ParentSelectionViewModel.Factory getParentSelectionViewModelFactory();

    @NotNull
    PasswordViewModel.Factory getPasswordViewModelFactory();

    @NotNull
    RegistrationMainViewModel.Factory getRegistrationMainViewModelFactory();

    @NotNull
    RegistrationViewModel.Factory getRegistrationViewModelFactory();

    @NotNull
    RestorePasswordViewModel.Factory getRestorePasswordViewModel();

    @NotNull
    RestoreVkViewModel.Factory getRestoreVkiDViewModel();

    @NotNull
    SSOViewModel.Factory getSSOViewModelFactory();

    @NotNull
    SecondStepViewModel.Factory getSecondFactorViewModelFactory();

    @NotNull
    SocialAuthViewModel.Factory getSocialAuthViewModelFactory();

    @NotNull
    UnblockUserViewModel.Factory getUnblockUserViewModelFactory();

    @NotNull
    MrimDialogViewModel.Factory getUserBlockedDialogViewModelFactory();

    @NotNull
    VkBindInLoginViewModel.Factory getVkBindInLoginViewModelFactory();

    @NotNull
    VkPasswordViewModel.Factory getVkPasswordViewModelFactory();

    @NotNull
    WebCaptchaComposeViewModel.Factory getWebCaptchaViewModelFactory();

    @NotNull
    WrongVkidAccountViewModel.Factory getWrongVkidAccountViewModelFactory();

    @NotNull
    YahooViewModel.Factory getYahooViewModelFactory();

    @NotNull
    YandexHelpViewModel.Factory getYandexHelpViewModelFactory();

    @NotNull
    YandexAssistedFactory getYandexViewModelFactory();

    void inject(@NotNull AuthActivity activity);

    void inject(@NotNull ReturnWorker worker);
}
