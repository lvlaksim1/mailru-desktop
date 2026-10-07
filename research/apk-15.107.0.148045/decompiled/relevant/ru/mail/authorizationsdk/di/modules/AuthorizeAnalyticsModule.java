package ru.mail.authorizationsdk.di.modules;

import dagger.Binds;
import dagger.Module;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.external.analytics.AuthorizationSdkAnalyticsImpl;
import ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics;
import ru.mail.authorizationsdk.external.analytics.common.DebugAutologinAnalytics;
import ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics;
import ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics;
import ru.mail.authorizationsdk.feature.authactivity.domain.authdelegate.AuthRequestAnalytics;
import ru.mail.authorizationsdk.feature.authactivity.vk.VkIdAuthAnalytics;
import ru.mail.authorizationsdk.feature.beforerecovery.analytics.BeforeRecoveryVKIDAnalytics;
import ru.mail.authorizationsdk.feature.bindemail.analytics.EsiaBindEmailAnalytics;
import ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents;
import ru.mail.authorizationsdk.feature.changepassword.analytics.ChangePasswordAnalytics;
import ru.mail.authorizationsdk.feature.customserver.analytics.CustomServerAnalytics;
import ru.mail.authorizationsdk.feature.enterphone.presentation.EnterPhoneAnalytics;
import ru.mail.authorizationsdk.feature.externalmigration.analytics.ExternalAccMigrationAnalytics;
import ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics;
import ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents;
import ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents;
import ru.mail.authorizationsdk.feature.imaplocal.analytics.ImapLocalAnalytics;
import ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics;
import ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics;
import ru.mail.authorizationsdk.feature.mrim.MrimAnalytics;
import ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents;
import ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics;
import ru.mail.authorizationsdk.feature.password.presentation.PasswordAnalytics;
import ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeAnalytics;
import ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeAnalytics;
import ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccAnalytics;
import ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics;
import ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics;
import ru.mail.authorizationsdk.feature.restorevkpassword.analytics.RestoreVkAnalytics;
import ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics;
import ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics;
import ru.mail.authorizationsdk.feature.socialauth.esiascreen.analytics.EsiaAnalytics;
import ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics;
import ru.mail.authorizationsdk.feature.unblockuser.analytics.UnblockUserAnalytics;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.SessionRestoreAnalytics;
import ru.mail.authorizationsdk.feature.vkbindavailable.analytics.VkBindInLoginAnalytics;
import ru.mail.authorizationsdk.feature.vkid.screens.vkfragmentsupport.analytics.VkFragmentSupportAnalytics;
import ru.mail.authorizationsdk.feature.vkid.screens.wrongvkidaccount.analytics.WrongVkidAccountAnalytics;
import ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics;
import ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics;
import ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics;
import ru.mail.authorizationsdk.feature.yandexhelp.analytics.YandexHelpAnalytics;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0098\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010&\u001a\u00020'2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010,\u001a\u00020-2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010.\u001a\u00020/2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u00100\u001a\u0002012\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u00102\u001a\u0002032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u00104\u001a\u0002052\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u00106\u001a\u0002072\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u00108\u001a\u0002092\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010:\u001a\u00020;2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010<\u001a\u00020=2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010>\u001a\u00020?2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010@\u001a\u00020A2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010B\u001a\u00020C2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010D\u001a\u00020E2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010F\u001a\u00020G2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010H\u001a\u00020I2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010J\u001a\u00020K2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010L\u001a\u00020M2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010N\u001a\u00020O2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010P\u001a\u00020Q2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010R\u001a\u00020S2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010T\u001a\u00020U2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010V\u001a\u00020W2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010X\u001a\u00020Y2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010Z\u001a\u00020[2\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\\À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/di/modules/AuthorizeAnalyticsModule;", "", "bindAuthActivityAnalytics", "Lru/mail/authorizationsdk/feature/authactivity/AuthActivityAnalytics;", "analytics", "Lru/mail/authorizationsdk/external/analytics/AuthorizationSdkAnalyticsImpl;", "bindLoginAnalytics", "Lru/mail/authorizationsdk/feature/login/analytics/LoginAnalytics;", "bindLoginBindFlowAnalytics", "Lru/mail/authorizationsdk/feature/loginbindflow/analytics/LoginBindFlowAnalytics;", "bindEsiaBindEmailAnalytics", "Lru/mail/authorizationsdk/feature/bindemail/analytics/EsiaBindEmailAnalytics;", "bindForceVKIDAnalytics", "Lru/mail/authorizationsdk/feature/forcevkid/analytics/ForceVKIDAnalytics;", "bindRestorePasswordAnalyticsEvents", "Lru/mail/authorizationsdk/feature/restorepassword/analytics/RestorePasswordAnalytics;", "bindLudwigCaptchaAnalyticEvents", "Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;", "bindExternalAccMigrationAnalyticsEvents", "Lru/mail/authorizationsdk/feature/externalmigration/analytics/ExternalAccMigrationAnalytics;", "bindOneTimeCodeAnalyticEvents", "Lru/mail/authorizationsdk/feature/onetimecode/analytics/OneTimeCodeAnalyticEvents;", "bindChoiceAccountEvents", "Lru/mail/authorizationsdk/feature/socialauth/choicescreen/analytics/ChoiceAccAnalytics;", "bindEsiaAnalytics", "Lru/mail/authorizationsdk/feature/socialauth/esiascreen/analytics/EsiaAnalytics;", "bindRestoreVkAnalytics", "Lru/mail/authorizationsdk/feature/restorevkpassword/analytics/RestoreVkAnalytics;", "bindGoogleNativeAnalyticEvents", "Lru/mail/authorizationsdk/feature/google/nativelib/analytics/GoogleNativeAnalyticEvents;", "bindGoogleWebAnalyticEvents", "Lru/mail/authorizationsdk/feature/google/web/analytics/GoogleWebAnalyticEvents;", "bindGoogleAnalyticEvents", "Lru/mail/authorizationsdk/feature/google/common/analytics/GoogleAnalytics;", "bindWrongVkidAccountAnalytics", "Lru/mail/authorizationsdk/feature/vkid/screens/wrongvkidaccount/analytics/WrongVkidAccountAnalytics;", "bindVkFastLoginAnalytics", "Lru/mail/authorizationsdk/feature/vkid/screens/vkfragmentsupport/analytics/VkFragmentSupportAnalytics;", "bindVkIdAuthAnalytics", "Lru/mail/authorizationsdk/feature/authactivity/vk/VkIdAuthAnalytics;", "bindEnterPhoneCodeAnalytics", "Lru/mail/authorizationsdk/feature/phone/entercode/presentation/EnterPhoneCodeAnalytics;", "bindEnterEmailCodeAnalytics", "Lru/mail/authorizationsdk/feature/phone/enteremailcode/presentation/EnterEmailCodeAnalytics;", "bindEnterEmailCodeAfterListAccAnalytics", "Lru/mail/authorizationsdk/feature/phone/enteremailcodeafterlistacc/presentation/EnterEmailCodeAfterListAccAnalytics;", "bindDebugGoogleAnalytics", "Lru/mail/authorizationsdk/external/analytics/common/DebugGoogleAnalytics;", "bindDebugAutologinAnalytics", "Lru/mail/authorizationsdk/external/analytics/common/DebugAutologinAnalytics;", "bindSecondFactorAnalyticsEvents", "Lru/mail/authorizationsdk/feature/secondfactor/analytics/SecondFactorAnalytics;", "bindSSOAnalyticsEvents", "Lru/mail/authorizationsdk/feature/sso/analytics/SSOAnalytics;", "bindVkPasswordAnalyticsEvents", "Lru/mail/authorizationsdk/feature/vkpassword/analytics/VkPasswordAnalytics;", "bindAuthRequestAnalyticsEvents", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthRequestAnalytics;", "bindYahooAnalyticsEvents", "Lru/mail/authorizationsdk/feature/yahoo/analytics/YahooAnalytics;", "bindYandexAnalyticsEvents", "Lru/mail/authorizationsdk/feature/yandex/analytics/YandexAnalytics;", "bindMrimAnalyticsEvents", "Lru/mail/authorizationsdk/feature/mrim/MrimAnalytics;", "bindYandexHelpAnalyticsEvents", "Lru/mail/authorizationsdk/feature/yandexhelp/analytics/YandexHelpAnalytics;", "bindOutlookAnalyticsEvents", "Lru/mail/authorizationsdk/feature/outlook/analytics/OutlookAnalytics;", "bindCustomServerAnalytics", "Lru/mail/authorizationsdk/feature/customserver/analytics/CustomServerAnalytics;", "bindImapLocalAnalytics", "Lru/mail/authorizationsdk/feature/imaplocal/analytics/ImapLocalAnalytics;", "bindSessionRestoreAnalytics", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/SessionRestoreAnalytics;", "bindCommonAnalytics", "Lru/mail/authorizationsdk/external/analytics/common/CommonAnalytics;", "bindPasswordAnalytics", "Lru/mail/authorizationsdk/feature/password/presentation/PasswordAnalytics;", "bindSocialAuthAnalytics", "Lru/mail/authorizationsdk/feature/socialauth/analytics/SocialAuthAnalytics;", "bindRegistrationAnalytics", "Lru/mail/authorizationsdk/feature/registration/RegistrationAnalytics;", "bindBeforeRecoveryVKIDAnalytics", "Lru/mail/authorizationsdk/feature/beforerecovery/analytics/BeforeRecoveryVKIDAnalytics;", "bindEnterPhoneAnalytics", "Lru/mail/authorizationsdk/feature/enterphone/presentation/EnterPhoneAnalytics;", "bindUnblockUserAnalytics", "Lru/mail/authorizationsdk/feature/unblockuser/analytics/UnblockUserAnalytics;", "bindChangePasswordAnalytics", "Lru/mail/authorizationsdk/feature/changepassword/analytics/ChangePasswordAnalytics;", "provideVkBindInLoginAnalytics", "Lru/mail/authorizationsdk/feature/vkbindavailable/analytics/VkBindInLoginAnalytics;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
public interface AuthorizeAnalyticsModule {
    @Binds
    @NotNull
    AuthActivityAnalytics bindAuthActivityAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    AuthRequestAnalytics bindAuthRequestAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    BeforeRecoveryVKIDAnalytics bindBeforeRecoveryVKIDAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    ChangePasswordAnalytics bindChangePasswordAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    ChoiceAccAnalytics bindChoiceAccountEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    CommonAnalytics bindCommonAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    CustomServerAnalytics bindCustomServerAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    DebugAutologinAnalytics bindDebugAutologinAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    DebugGoogleAnalytics bindDebugGoogleAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    EnterEmailCodeAfterListAccAnalytics bindEnterEmailCodeAfterListAccAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    EnterEmailCodeAnalytics bindEnterEmailCodeAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    EnterPhoneAnalytics bindEnterPhoneAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    EnterPhoneCodeAnalytics bindEnterPhoneCodeAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    EsiaAnalytics bindEsiaAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    EsiaBindEmailAnalytics bindEsiaBindEmailAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    ExternalAccMigrationAnalytics bindExternalAccMigrationAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    ForceVKIDAnalytics bindForceVKIDAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    GoogleAnalytics bindGoogleAnalyticEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    GoogleNativeAnalyticEvents bindGoogleNativeAnalyticEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    GoogleWebAnalyticEvents bindGoogleWebAnalyticEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    ImapLocalAnalytics bindImapLocalAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    LoginAnalytics bindLoginAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    LoginBindFlowAnalytics bindLoginBindFlowAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    LudwigCaptchaAnalyticEvents bindLudwigCaptchaAnalyticEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    MrimAnalytics bindMrimAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    OneTimeCodeAnalyticEvents bindOneTimeCodeAnalyticEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    OutlookAnalytics bindOutlookAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    PasswordAnalytics bindPasswordAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    RegistrationAnalytics bindRegistrationAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    RestorePasswordAnalytics bindRestorePasswordAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    RestoreVkAnalytics bindRestoreVkAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    SSOAnalytics bindSSOAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    SecondFactorAnalytics bindSecondFactorAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    SessionRestoreAnalytics bindSessionRestoreAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    SocialAuthAnalytics bindSocialAuthAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    UnblockUserAnalytics bindUnblockUserAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    VkFragmentSupportAnalytics bindVkFastLoginAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    VkIdAuthAnalytics bindVkIdAuthAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    VkPasswordAnalytics bindVkPasswordAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    WrongVkidAccountAnalytics bindWrongVkidAccountAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    YahooAnalytics bindYahooAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    YandexAnalytics bindYandexAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    YandexHelpAnalytics bindYandexHelpAnalyticsEvents(@NotNull AuthorizationSdkAnalyticsImpl analytics);

    @Binds
    @NotNull
    VkBindInLoginAnalytics provideVkBindInLoginAnalytics(@NotNull AuthorizationSdkAnalyticsImpl analytics);
}
