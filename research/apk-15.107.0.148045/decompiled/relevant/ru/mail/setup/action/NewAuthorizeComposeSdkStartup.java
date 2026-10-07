package ru.mail.setup.action;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.appsflyer.AppsFlyerLib;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.vk.auth.main.VkClientAuthLib;
import dagger.Lazy;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ads.info.provider.api.AdvertisingInfoProvider;
import ru.mail.ads.info.provider.api.di.GoogleAdvertisingInfo;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.android_utils.PendingIntentCreator;
import ru.mail.android_utils.PendingIntentUtils;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.auth.request.util.AccountInfoUtilsKt;
import ru.mail.auth.webview.MailSecondStepFragment;
import ru.mail.authorizationsdk.di.modules.model.ExternalPlatformData;
import ru.mail.authorizationsdk.domain.model.SocialAuthInitContainer;
import ru.mail.authorizationsdk.external.analytics.AuthAnalyticsSdk;
import ru.mail.authorizationsdk.external.analytics.common.AppReporter;
import ru.mail.authorizationsdk.external.api.AuthorizationSdk;
import ru.mail.authorizationsdk.external.config.AuthorizationSdkConfig;
import ru.mail.authorizationsdk.external.config.BrowserConfig;
import ru.mail.authorizationsdk.external.config.BuildConfigurationVariables;
import ru.mail.authorizationsdk.external.config.ForceVkIdSecret;
import ru.mail.authorizationsdk.external.config.ImapConfig;
import ru.mail.authorizationsdk.external.config.MailAuthConfig;
import ru.mail.authorizationsdk.external.config.MailUrls;
import ru.mail.authorizationsdk.external.config.MrimConfig;
import ru.mail.authorizationsdk.external.config.PushAuthInfoConfig;
import ru.mail.authorizationsdk.external.config.RegConfig;
import ru.mail.authorizationsdk.external.config.VkBindInLoginConfig;
import ru.mail.authorizationsdk.external.config.VkPasswordConfig;
import ru.mail.authorizationsdk.external.config.YandexHelpConfiguration;
import ru.mail.authorizationsdk.external.config.common.CommonConfig;
import ru.mail.authorizationsdk.external.config.common.DomainSuggestionsConfig;
import ru.mail.authorizationsdk.external.config.common.OidcIssuerConfig;
import ru.mail.authorizationsdk.external.config.flavor.FlavorConfig;
import ru.mail.authorizationsdk.external.config.login.LoginConfig;
import ru.mail.authorizationsdk.external.config.login.LoginServiceGmail;
import ru.mail.authorizationsdk.external.device.DeviceInfo;
import ru.mail.authorizationsdk.external.secret.Secrets;
import ru.mail.authorizationsdk.external.service.ActiveAccountModeProvider;
import ru.mail.authorizationsdk.external.service.SocialLoginInfoHolderProvider;
import ru.mail.authorizationsdk.feature.captcha.config.LudwigConfig;
import ru.mail.authorizationsdk.feature.core.presentation.theme.DarkThemeResolver;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.vkmail.model.VkLoginScreenConfig;
import ru.mail.authorizationsdk.feature.registration.data.ChildRegHelper;
import ru.mail.authorizationsdk.feature.secondfactor.config.SecondStepConfig;
import ru.mail.authorizationsdk.feature.secondfactor.external.TsaCookieStore;
import ru.mail.authorizationsdk.feature.socialauth.config.EsiaConfig;
import ru.mail.authorizationsdk.feature.socialauth.config.SocialAuthConfig;
import ru.mail.authorizationsdk.feature.socialauth.config.VKIDRecoveryConfig;
import ru.mail.authorizationsdk.feature.socialauth.config.VkAgreementsConfig;
import ru.mail.authorizationsdk.feature.socialauth.config.VkConfig;
import ru.mail.authorizationsdk.feature.socialauth.config.VkMailAgreementsConfig;
import ru.mail.authorizationsdk.feature.socialauth.data.SocialAuthRepository;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.ReturnParams;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.SessionRestoreConfig;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.notification.RestoreSessionAuthModeIntentCreator;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.notification.RestoreSessionNotificationProvider;
import ru.mail.authorizesdk.domain.models.NewSdkRestoreAuthFlowConfig;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.credentialsexchanger.Constants;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.dependencies.AppStartupScope;
import ru.mail.deviceinfo.AppVersionProvider;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.deviceinfo.DeviceTypeProvider;
import ru.mail.deviceinfo.GooglePlayInfoProvider;
import ru.mail.deviceinfo.LocaleInfoProvider;
import ru.mail.deviceinfo.SimOperatorProvider;
import ru.mail.deviceinfo.TimeZoneProvider;
import ru.mail.logic.auth.GoogleWebClientOauthParamsProvider;
import ru.mail.logic.auth.OutlookOauthParamsProvider;
import ru.mail.logic.auth.SuperAppKitIds;
import ru.mail.logic.auth.YahooOauthParamsProvider;
import ru.mail.logic.auth.YandexOauthParamsProvider;
import ru.mail.mailapp.BuildConfig;
import ru.mail.mailapp.R;
import ru.mail.march.internal.work.NetworkRequirementManager;
import ru.mail.network.utils.device.deviceid.AdvertisingIdProvider;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.sdk.MailSdkEntryPoint;
import ru.mail.test.recognition.TestRecognition;
import ru.mail.theme.utils.DarkThemeUtils;
import ru.mail.ui.RequestCode;
import ru.mail.ui.auth.MailLoginChecker;
import ru.mail.util.analytics.Distributors;
import ru.mail.util.log.AppLogger;
import ru.mail.util.log.Logger;
import ru.mail.util.push.NotificationHandler;
import ru.mail.util.reporter.AbstractErrorReporter;
import ru.mail.utils.FirebaseInfoProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u00ad\u0001\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\u0006\u0010\u001f\u001a\u00020 \u0012\u0006\u0010!\u001a\u00020\"\u0012\u0006\u0010#\u001a\u00020$\u0012\u0006\u0010%\u001a\u00020&\u0012\b\b\u0001\u0010'\u001a\u00020(¢\u0006\u0004\b)\u0010*J\u000e\u0010+\u001a\u00020,H\u0096@¢\u0006\u0002\u0010-J\u0010\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u000200H\u0002J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0005H\u0002J\f\u00104\u001a\u000205*\u000200H\u0002J\u0010\u00106\u001a\u0002072\u0006\u00103\u001a\u000208H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lru/mail/setup/action/NewAuthorizeComposeSdkStartup;", "Lru/mail/setup/action/CoroutineAppStartUp;", "logger", "Lru/mail/util/log/Logger;", "application", "Landroid/app/Application;", "advertisingInfoProvider", "Lru/mail/ads/info/provider/api/AdvertisingInfoProvider;", "appVersionProvider", "Lru/mail/deviceinfo/AppVersionProvider;", "deviceTypeProvider", "Lru/mail/deviceinfo/DeviceTypeProvider;", "simOperatorProvider", "Lru/mail/deviceinfo/SimOperatorProvider;", "localeInfoProvider", "Lru/mail/deviceinfo/LocaleInfoProvider;", "timeZoneProvider", "Lru/mail/deviceinfo/TimeZoneProvider;", "googlePlayInfoProvider", "Lru/mail/deviceinfo/GooglePlayInfoProvider;", "deviceIdProvider", "Lru/mail/deviceinfo/DeviceIdProvider;", "okHttpClient", "Lokhttp3/OkHttpClient;", "configRepository", "Lru/mail/config/ConfigurationRepository;", "socialAuthInitContainer", "Ldagger/Lazy;", "Lru/mail/authorizationsdk/domain/model/SocialAuthInitContainer;", "socialAuthRepository", "Lru/mail/authorizationsdk/feature/socialauth/data/SocialAuthRepository;", "childRegHelper", "Lru/mail/authorizationsdk/feature/registration/data/ChildRegHelper;", "firebaseInfoProvider", "Lru/mail/utils/FirebaseInfoProvider;", "browserConfig", "Lru/mail/authorizationsdk/external/config/BrowserConfig;", "restoreSessionAuthModeIntentCreator", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/notification/RestoreSessionAuthModeIntentCreator;", CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Lru/mail/util/log/Logger;Landroid/app/Application;Lru/mail/ads/info/provider/api/AdvertisingInfoProvider;Lru/mail/deviceinfo/AppVersionProvider;Lru/mail/deviceinfo/DeviceTypeProvider;Lru/mail/deviceinfo/SimOperatorProvider;Lru/mail/deviceinfo/LocaleInfoProvider;Lru/mail/deviceinfo/TimeZoneProvider;Lru/mail/deviceinfo/GooglePlayInfoProvider;Lru/mail/deviceinfo/DeviceIdProvider;Lokhttp3/OkHttpClient;Lru/mail/config/ConfigurationRepository;Ldagger/Lazy;Lru/mail/authorizationsdk/feature/socialauth/data/SocialAuthRepository;Lru/mail/authorizationsdk/feature/registration/data/ChildRegHelper;Lru/mail/utils/FirebaseInfoProvider;Lru/mail/authorizationsdk/external/config/BrowserConfig;Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/notification/RestoreSessionAuthModeIntentCreator;Lkotlinx/coroutines/CoroutineScope;)V", "suspendPerform", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setConfigToSdk", "config", "Lru/mail/config/Configuration;", "getNotificationProvider", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/notification/RestoreSessionNotificationProvider;", "context", "getConfig", "Lru/mail/authorizationsdk/external/config/AuthorizationSdkConfig;", "createAnalyticsCallback", "Lru/mail/authorizationsdk/external/analytics/AuthAnalyticsSdk;", "Landroid/content/Context;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NewAuthorizeComposeSdkStartup extends CoroutineAppStartUp {
    public static final int $stable = 8;

    @NotNull
    private final AdvertisingInfoProvider advertisingInfoProvider;

    @NotNull
    private final AppVersionProvider appVersionProvider;

    @NotNull
    private final Application application;

    @NotNull
    private final BrowserConfig browserConfig;

    @NotNull
    private final ChildRegHelper childRegHelper;

    @NotNull
    private final ConfigurationRepository configRepository;

    @NotNull
    private final DeviceIdProvider deviceIdProvider;

    @NotNull
    private final DeviceTypeProvider deviceTypeProvider;

    @NotNull
    private final FirebaseInfoProvider firebaseInfoProvider;

    @NotNull
    private final GooglePlayInfoProvider googlePlayInfoProvider;

    @NotNull
    private final LocaleInfoProvider localeInfoProvider;

    @NotNull
    private final Logger logger;

    @NotNull
    private final OkHttpClient okHttpClient;

    @NotNull
    private final RestoreSessionAuthModeIntentCreator restoreSessionAuthModeIntentCreator;

    @NotNull
    private final SimOperatorProvider simOperatorProvider;

    @NotNull
    private final Lazy<SocialAuthInitContainer> socialAuthInitContainer;

    @NotNull
    private final SocialAuthRepository socialAuthRepository;

    @NotNull
    private final TimeZoneProvider timeZoneProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @Inject
    public NewAuthorizeComposeSdkStartup(@AppLogger @NotNull Logger logger, @NotNull Application application, @NotNull @GoogleAdvertisingInfo AdvertisingInfoProvider advertisingInfoProvider, @NotNull AppVersionProvider appVersionProvider, @NotNull DeviceTypeProvider deviceTypeProvider, @NotNull SimOperatorProvider simOperatorProvider, @NotNull LocaleInfoProvider localeInfoProvider, @NotNull TimeZoneProvider timeZoneProvider, @NotNull GooglePlayInfoProvider googlePlayInfoProvider, @NotNull DeviceIdProvider deviceIdProvider, @NotNull OkHttpClient okHttpClient, @NotNull ConfigurationRepository configRepository, @NotNull Lazy<SocialAuthInitContainer> socialAuthInitContainer, @NotNull SocialAuthRepository socialAuthRepository, @NotNull ChildRegHelper childRegHelper, @NotNull FirebaseInfoProvider firebaseInfoProvider, @NotNull BrowserConfig browserConfig, @NotNull RestoreSessionAuthModeIntentCreator restoreSessionAuthModeIntentCreator, @NotNull @AppStartupScope CoroutineScope scope) {
        super(scope);
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(advertisingInfoProvider, "advertisingInfoProvider");
        Intrinsics.checkNotNullParameter(appVersionProvider, "appVersionProvider");
        Intrinsics.checkNotNullParameter(deviceTypeProvider, "deviceTypeProvider");
        Intrinsics.checkNotNullParameter(simOperatorProvider, "simOperatorProvider");
        Intrinsics.checkNotNullParameter(localeInfoProvider, "localeInfoProvider");
        Intrinsics.checkNotNullParameter(timeZoneProvider, "timeZoneProvider");
        Intrinsics.checkNotNullParameter(googlePlayInfoProvider, "googlePlayInfoProvider");
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Intrinsics.checkNotNullParameter(socialAuthInitContainer, "socialAuthInitContainer");
        Intrinsics.checkNotNullParameter(socialAuthRepository, "socialAuthRepository");
        Intrinsics.checkNotNullParameter(childRegHelper, "childRegHelper");
        Intrinsics.checkNotNullParameter(firebaseInfoProvider, "firebaseInfoProvider");
        Intrinsics.checkNotNullParameter(browserConfig, "browserConfig");
        Intrinsics.checkNotNullParameter(restoreSessionAuthModeIntentCreator, "restoreSessionAuthModeIntentCreator");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.logger = logger;
        this.application = application;
        this.advertisingInfoProvider = advertisingInfoProvider;
        this.appVersionProvider = appVersionProvider;
        this.deviceTypeProvider = deviceTypeProvider;
        this.simOperatorProvider = simOperatorProvider;
        this.localeInfoProvider = localeInfoProvider;
        this.timeZoneProvider = timeZoneProvider;
        this.googlePlayInfoProvider = googlePlayInfoProvider;
        this.deviceIdProvider = deviceIdProvider;
        this.okHttpClient = okHttpClient;
        this.configRepository = configRepository;
        this.socialAuthInitContainer = socialAuthInitContainer;
        this.socialAuthRepository = socialAuthRepository;
        this.childRegHelper = childRegHelper;
        this.firebaseInfoProvider = firebaseInfoProvider;
        this.browserConfig = browserConfig;
        this.restoreSessionAuthModeIntentCreator = restoreSessionAuthModeIntentCreator;
    }

    private final AuthAnalyticsSdk createAnalyticsCallback(Context context) {
        final MailAppAnalytics mailAppAnalyticsAnalytics = MailAppDependencies.analytics(context);
        return new AuthAnalyticsSdk() { // from class: ru.mail.setup.action.NewAuthorizeComposeSdkStartup.createAnalyticsCallback.1
            @Override // ru.mail.authorizationsdk.external.analytics.AuthAnalyticsSdk
            public void onAnalyticEvent(String eventName, Map<String, String> params) {
                Intrinsics.checkNotNullParameter(eventName, "eventName");
                Intrinsics.checkNotNullParameter(params, "params");
                mailAppAnalyticsAnalytics.onNewAuthorizeComposeSdkEvent(eventName, params);
            }
        };
    }

    private final AuthorizationSdkConfig getConfig(final Configuration configuration) {
        FlavorConfig.Mail mail = FlavorConfig.Mail.INSTANCE;
        String serviceUserAgreement = this.socialAuthInitContainer.get().getVkInitConfig().getServiceUserAgreement();
        VkClientAuthLib vkClientAuthLib = VkClientAuthLib.INSTANCE;
        String string = VkClientAuthLib.getVkConnectTermsUrl$default(vkClientAuthLib, false, 1, null).toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        String string2 = VkClientAuthLib.getVkConnectPrivacyUrl$default(vkClientAuthLib, false, 1, null).toString();
        Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
        VkLoginScreenConfig vkLoginScreenConfig = new VkLoginScreenConfig(false, configuration.isHelpInAuthScreenEnabled(), configuration.getNewAuthorizationSdkConfig().isVkLoginYandexBtnEnabled(), configuration.getNewAuthorizationSdkConfig().isNewVKIDButtonEnabled(), new VkMailAgreementsConfig(serviceUserAgreement, string, string2), 1, null);
        boolean zIsLoginEnabled = configuration.getNewAuthorizationSdkConfig().isLoginEnabled();
        boolean zIsLoginVkEnabled = configuration.getNewAuthorizationSdkConfig().isLoginVkEnabled();
        boolean zIsForceVKIDEnabled = configuration.getNewAuthorizationSdkConfig().isForceVKIDEnabled();
        boolean zIsNewAutologinLogicEnabled = configuration.getNewAuthorizationSdkConfig().isNewAutologinLogicEnabled();
        boolean zIsNewRestoreLogicEnabled = configuration.getNewAuthorizationSdkConfig().isNewRestoreLogicEnabled();
        boolean zIsEsiaComposeLogicEnabled = configuration.getNewAuthorizationSdkConfig().isEsiaComposeLogicEnabled();
        boolean zIsVkIdFullComposeEnabled = configuration.getNewAuthorizationSdkConfig().isVkIdFullComposeEnabled();
        boolean zIsNewStackModeEnabled = configuration.getNewAuthorizationSdkConfig().isNewStackModeEnabled();
        boolean zIsPasswordEnabled = configuration.getNewAuthorizationSdkConfig().isPasswordEnabled();
        boolean zIsNewExternalAuthEnabled = configuration.isNewExternalAuthEnabled();
        CommonConfig commonConfig = new CommonConfig(zIsLoginEnabled, zIsLoginVkEnabled, false, new Function0() { // from class: ru.mail.setup.action.c1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(NewAuthorizeComposeSdkStartup.getConfig$lambda$0(configuration));
            }
        }, zIsForceVKIDEnabled, zIsNewAutologinLogicEnabled, zIsNewRestoreLogicEnabled, zIsEsiaComposeLogicEnabled, zIsVkIdFullComposeEnabled, zIsNewStackModeEnabled, zIsPasswordEnabled, zIsNewExternalAuthEnabled, configuration.getNewAuthorizationSdkConfig().isEsiaForceAuthEnabled(), new VkPasswordConfig(configuration.getNewAuthorizationSdkConfig().getVkPassword().isEnabled(), configuration.getNewAuthorizationSdkConfig().getVkPassword().isInternalSdkFlowEnabled() || configuration.getNewAuthorizationSdkConfig().isAnyLoginEnabled()), new DomainSuggestionsConfig(configuration.getNewAuthorizationSdkConfig().isGmailSuggestAvailable(), configuration.getSocialLoginConfig().getVkBindSuggestDomains(), configuration.getLoginSuggestedDomains()), new OidcIssuerConfig(configuration.getNewAuthorizationSdkConfig().getOidcIssuerConfig().getOidcEnabled(), configuration.getNewAuthorizationSdkConfig().getOidcIssuerConfig().getOidcIssuerUrl()), 4, null);
        LoginConfig loginConfig = new LoginConfig(new LoginServiceGmail(configuration.getLoginServicesGmailConfig().getEnabled(), configuration.getLoginServicesGmailConfig().getUseNewImageLogo()), new Function0() { // from class: ru.mail.setup.action.d1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(NewAuthorizeComposeSdkStartup.getConfig$lambda$1(this.f97217a));
            }
        }, configuration.isHelpInAuthScreenEnabled(), configuration.getLoginButtonGmailConfig().getEnabled(), configuration.getNewAuthorizationSdkConfig().isQrAuthEnabled(), true, true, true);
        NewSdkRestoreAuthFlowConfig restoreAuthFlowConfig = configuration.getNewAuthorizationSdkConfig().getRestoreAuthFlowConfig();
        SessionRestoreConfig sessionRestoreConfig = new SessionRestoreConfig(restoreAuthFlowConfig.getMinimumDelay(), restoreAuthFlowConfig.getTimePoints(), restoreAuthFlowConfig.isEnabled(), restoreAuthFlowConfig.isForceServiceChooser(), restoreAuthFlowConfig.getShowLimit());
        LudwigConfig ludwigConfig = new LudwigConfig(configuration.getNewAuthorizationSdkConfig().getLudvigCaptchaConfig().isLudvigCaptchaEnabled(), configuration.getNewAuthorizationSdkConfig().getLudvigCaptchaConfig().isDomStorageEnabled(), configuration.getNewAuthorizationSdkConfig().getLudvigCaptchaConfig().isTextZoomDisabled());
        YandexHelpConfiguration yandexHelpConfiguration = new YandexHelpConfiguration(configuration.getNewAuthorizationSdkConfig().getYandexHelpConfig().isEnabled(), configuration.getNewAuthorizationSdkConfig().getYandexHelpConfig().getUrl());
        SecondStepConfig secondStepConfig = new SecondStepConfig(configuration.getNewAuthorizationSdkConfig().getSecondStepConfig().isScaleDisabled());
        MrimConfig mrimConfig = new MrimConfig(configuration.getNewAuthorizationSdkConfig().getMrimConfig().getUrl());
        PushAuthInfoConfig pushAuthInfoConfig = new PushAuthInfoConfig(configuration.getSocialLoginConfig().isForceVkAuthEnabled(), configuration.getSocialLoginConfig().isForceVkAuthRemoveAltAuthEnabled(), configuration.getSocialLoginConfig().getVkidBindInLogin().isEnabled(), configuration.getNewAuthorizationSdkConfig().getSso().isEnabled(), configuration.getNewAuthorizationSdkConfig().getVkPassword().isEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().isVkEmailForwardingEnabled(), MapsKt.mapOf(TuplesKt.to(PushAuthInfoConfig.ExternalAuth.YAHOO, SetsKt.setOf("yahoo.com")), TuplesKt.to(PushAuthInfoConfig.ExternalAuth.YANDEX, SetsKt.setOf((Object[]) new String[]{"yandex.ru", "yandex.com", "yandex.ua", "yandex.kz", "yandex.by", "yandex.com.tr", "ya.ru"})), TuplesKt.to(PushAuthInfoConfig.ExternalAuth.MSN, SetsKt.setOf((Object[]) new String[]{"outlook.com", "hotmail.com"})), TuplesKt.to(PushAuthInfoConfig.ExternalAuth.GOOGLE, SetsKt.setOf("gmail.com"))));
        MailUrls mailUrls = new MailUrls(configuration.getHelpLink());
        BrowserConfig browserConfig = this.browserConfig;
        VkConfig vkConfig = new VkConfig(configuration.getSocialLoginConfig().isVKConnectLoginEnabled(), configuration.getSocialLoginConfig().isOneTapEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().isVkEmailForwardingEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().isVkEmailForwardingPasswordEnabled(), new VkConfig.RestoreVkidFlags(configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().isRestoreVkidEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().isRestoreVkidInOldAuthEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().isRestoreVkidInChoiceAndForceAuthFlowEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().getRestoreVkidFlags().getAllEmailWithoutBlockedEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().getRestoreVkidFlags().getMailVkidRebindEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().getRestoreVkidFlags().getNpcUnblockEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().getRestoreVkidFlags().getMrimUnblockEnabled(), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().getRestoreVkidFlags().getRestoreAuthParamEnabled()), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().isAutologinEnabled());
        String string3 = VkClientAuthLib.getVkConnectPrivacyUrl$default(vkClientAuthLib, false, 1, null).toString();
        Intrinsics.checkNotNullExpressionValue(string3, "toString(...)");
        String string4 = VkClientAuthLib.getVkConnectTermsUrl$default(vkClientAuthLib, false, 1, null).toString();
        Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
        SocialAuthConfig socialAuthConfig = new SocialAuthConfig(vkConfig, new VkAgreementsConfig(string3, string4), new EsiaConfig(configuration.getEsiaConfig().getEnabledLogin(), configuration.getEsiaConfig().getEnabledRegistration(), configuration.getEsiaConfig().getWebViewFlow(), configuration.getEsiaConfig().getHelp2faErrorUrl()), new VKIDRecoveryConfig(configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().isRecoveryEnabled()), configuration.getNewAuthorizationSdkConfig().getSocialAuthConfig().isInitEnabled(), this.socialAuthInitContainer.get().getVkInitConfig().isSocialAccountEnabled());
        BuildConfigurationVariables buildConfigurationVariables = new BuildConfigurationVariables(BuildConfigVariablesHolder.accountType);
        ImapConfig imapConfig = new ImapConfig(configuration.getNewAuthorizationSdkConfig().getImapLocal().isEnabled(), configuration.getProvidersInfo(), MailBoxFolder.FOLDER_ID_DRAFTS, 950L, MailBoxFolder.FOLDER_ID_SENT, MailBoxFolder.FOLDER_ID_TRASH, MailBoxFolder.FOLDER_ID_ALL_MAILS);
        RegConfig regConfig = new RegConfig(configuration.getNewAuthorizationSdkConfig().getRegConfig().isEnabledSocial(), configuration.getNewAuthorizationSdkConfig().getRegConfig().isEnabledMain(), configuration.getNewAuthorizationSdkConfig().getRegConfig().getUserTermsUrl(), configuration.getNewAuthorizationSdkConfig().getRegConfig().isUseTabs(), configuration.getNewAuthorizationSdkConfig().getRegConfig().getVkTokenThrowingInConfirmRequest());
        boolean zIsGoogleAvailable = configuration.getNewAuthorizationSdkConfig().getMailAuthConfig().isGoogleAvailable();
        boolean zIsVkIdAvailable = configuration.getNewAuthorizationSdkConfig().getMailAuthConfig().isVkIdAvailable();
        boolean zIsYandexAvailable = configuration.getNewAuthorizationSdkConfig().getMailAuthConfig().isYandexAvailable();
        boolean zIsEmailAuthAvailable = configuration.getNewAuthorizationSdkConfig().getMailAuthConfig().isEmailAuthAvailable();
        MailAuthConfig mailAuthConfig = new MailAuthConfig(zIsGoogleAvailable, zIsVkIdAvailable, zIsYandexAvailable, configuration.getNewAuthorizationSdkConfig().getMailAuthConfig().isOutlookAuthAvailable(), configuration.getNewAuthorizationSdkConfig().getMailAuthConfig().isYahooAuthAvailable(), configuration.getNewAuthorizationSdkConfig().getMailAuthConfig().isSSOAuthAvailable(), configuration.getNewAuthorizationSdkConfig().getMailAuthConfig().isExternalDomainsAvailable(), zIsEmailAuthAvailable, configuration.getNewAuthorizationSdkConfig().getMailAuthConfig().getShowBothPromo());
        Configuration.SocialLoginConfig.VkidBindInLogin vkidBindInLogin = configuration.getSocialLoginConfig().getVkidBindInLogin();
        return new AuthorizationSdkConfig(commonConfig, loginConfig, vkLoginScreenConfig, imapConfig, sessionRestoreConfig, ludwigConfig, yandexHelpConfiguration, secondStepConfig, pushAuthInfoConfig, mailUrls, mrimConfig, socialAuthConfig, browserConfig, buildConfigurationVariables, regConfig, mailAuthConfig, new VkBindInLoginConfig(configuration.getNewAuthorizationSdkConfig().isVkBindInLoginEnable(), vkidBindInLogin.isBackBtnVisible(), vkidBindInLogin.isSecondaryBtnVisible(), vkidBindInLogin.isResetPasswordWarningVisible()), mail);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getConfig$lambda$0(Configuration configuration) {
        return configuration.getNewAuthorizationSdkConfig().isABExperiment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getConfig$lambda$1(NewAuthorizeComposeSdkStartup newAuthorizeComposeSdkStartup) {
        return newAuthorizeComposeSdkStartup.configRepository.getConfiguration().getNewAuthorizationSdkConfig().isAnyLoginEnabled();
    }

    private final RestoreSessionNotificationProvider getNotificationProvider(final Application context) {
        return new RestoreSessionNotificationProvider() { // from class: ru.mail.setup.action.NewAuthorizeComposeSdkStartup.getNotificationProvider.1
            @Override // ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.notification.RestoreSessionNotificationProvider
            public void showRestoreFlowNotification(ReturnParams returnUserParams) {
                Intrinsics.checkNotNullParameter(returnUserParams, "returnUserParams");
                try {
                    NotificationHandler.INSTANCE.from(context).showRestoreAuthSDKFlowNotification(returnUserParams.getNotificationTitleId(), returnUserParams.getNotificationTextId(), PendingIntentCreator.getActivity$default(context, RequestCode.RESTORE_AUTH_NOTIFICATION.id(), returnUserParams.createIntentFromNotification(context, this.restoreSessionAuthModeIntentCreator), PendingIntentUtils.Companion.getPendingIntentFlags$default(PendingIntentUtils.INSTANCE, false, 1, null), false, 16, null));
                } catch (Exception e10) {
                    this.logger.e("Cannot locate NotificationHandler!", e10);
                }
            }
        };
    }

    private final void setConfigToSdk(Configuration config) {
        AuthorizationSdk.INSTANCE.setConfig(getConfig(config));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String suspendPerform$lambda$0(NewAuthorizeComposeSdkStartup newAuthorizeComposeSdkStartup, Context context) {
        Intrinsics.checkNotNullParameter(context, "<unused var>");
        return newAuthorizeComposeSdkStartup.advertisingInfoProvider.getAdvertisingId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String suspendPerform$lambda$1(NewAuthorizeComposeSdkStartup newAuthorizeComposeSdkStartup, String str) {
        return AccountInfoUtilsKt.getActiveMode(newAuthorizeComposeSdkStartup.application, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String suspendPerform$lambda$2(NewAuthorizeComposeSdkStartup newAuthorizeComposeSdkStartup) {
        return newAuthorizeComposeSdkStartup.firebaseInfoProvider.getInstanceId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String suspendPerform$lambda$3(NewAuthorizeComposeSdkStartup newAuthorizeComposeSdkStartup) {
        return AppsFlyerLib.getInstance().getAppsFlyerUID(newAuthorizeComposeSdkStartup.application);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean suspendPerform$lambda$4(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DarkThemeUtils.INSTANCE.isNightModeEnabled(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SocialAuthInitContainer suspendPerform$lambda$5(NewAuthorizeComposeSdkStartup newAuthorizeComposeSdkStartup) {
        SocialAuthInitContainer socialAuthInitContainer = newAuthorizeComposeSdkStartup.socialAuthInitContainer.get();
        Intrinsics.checkNotNullExpressionValue(socialAuthInitContainer, "get(...)");
        return socialAuthInitContainer;
    }

    @Override // ru.mail.setup.action.CoroutineAppStartUp
    @Nullable
    public Object suspendPerform(@NotNull Continuation<? super Unit> continuation) {
        final AbstractErrorReporter abstractErrorReporterFrom;
        ConfigurationWithRawData configuration = this.configRepository.getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        DeviceInfo deviceInfo = new DeviceInfo(this.appVersionProvider.getAppVersion(), this.appVersionProvider.getVersionCode(), this.googlePlayInfoProvider.getPlayServicesVersion(), this.googlePlayInfoProvider.isPlayServicesAvailable(), this.deviceIdProvider.getDeviceId(), this.deviceIdProvider.getUdid(), this.localeInfoProvider.getLanguage(), this.localeInfoProvider.getContry(), this.timeZoneProvider.getTimeZone(), this.simOperatorProvider.getSimOperator(), this.deviceTypeProvider.getType());
        try {
            abstractErrorReporterFrom = AbstractErrorReporter.from(this.application);
        } catch (Exception e10) {
            this.logger.e("Cannot get AbstractErrorReporter", e10);
            abstractErrorReporterFrom = null;
        }
        AppReporter appReporter = new AppReporter() { // from class: ru.mail.setup.action.NewAuthorizeComposeSdkStartup$suspendPerform$appReporter$1
            @Override // ru.mail.authorizationsdk.external.analytics.common.AppReporter
            public void onAuthError() {
                AbstractErrorReporter abstractErrorReporter = abstractErrorReporterFrom;
                if (abstractErrorReporter != null) {
                    abstractErrorReporter.report();
                }
            }

            @Override // ru.mail.authorizationsdk.external.analytics.common.AppReporter
            public void onAuthCanceled() {
            }
        };
        NetworkRequirementManager networkRequirementManager = MailSdkEntryPoint.INSTANCE.networkRequirementManager(this.application);
        TsaCookieStore tsaCookieStore = new TsaCookieStore() { // from class: ru.mail.setup.action.NewAuthorizeComposeSdkStartup$suspendPerform$tsaCookieStore$1
            @Override // ru.mail.authorizationsdk.feature.secondfactor.external.TsaCookieStore
            public String getTsaCookie(Context context, String login) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(login, "login");
                return MailSecondStepFragment.getTsaCookie(context, login, null);
            }

            @Override // ru.mail.authorizationsdk.feature.secondfactor.external.TsaCookieStore
            public void saveTsaCookie(Context context, String login, String tsaCookie) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(tsaCookie, "tsaCookie");
                MailSecondStepFragment.setTsaCookie(context, login, tsaCookie);
            }
        };
        AdvertisingIdProvider advertisingIdProvider = new AdvertisingIdProvider() { // from class: ru.mail.setup.action.e1
            @Override // ru.mail.network.utils.device.deviceid.AdvertisingIdProvider
            public final String getAdvertisingId(Context context) {
                return NewAuthorizeComposeSdkStartup.suspendPerform$lambda$0(this.f97220a, context);
            }
        };
        RestoreSessionNotificationProvider notificationProvider = getNotificationProvider(this.application);
        Secrets secrets = new Secrets(YahooOauthParamsProvider.MAILRU_CLIENT_ID, YahooOauthParamsProvider.MAILRU_SECRET_ID);
        Secrets secrets2 = new Secrets(YandexOauthParamsProvider.MAILRU_CLIENT_ID, YandexOauthParamsProvider.MAILRU_SECRET_ID);
        Secrets secrets3 = new Secrets(OutlookOauthParamsProvider.MAILRU_CLIENT_ID, OutlookOauthParamsProvider.MAILRU_SECRET_ID);
        Secrets secrets4 = new Secrets(GoogleWebClientOauthParamsProvider.MAILRU_CLIENT_ID, GoogleWebClientOauthParamsProvider.MAILRU_SECRET_ID);
        ForceVkIdSecret forceVkIdSecret = new ForceVkIdSecret(Constants.SCOPES, SuperAppKitIds.INSTANCE.getClientId(this.application));
        AuthorizationSdk authorizationSdk = AuthorizationSdk.INSTANCE;
        CoroutineScope scope = getScope();
        Application application = this.application;
        boolean isTest = TestRecognition.getIsTest();
        boolean zIsMiniMailEnabled = configuration.isMiniMailEnabled();
        Logger logger = this.logger;
        List<String> vkBindSuggestDomains = configuration.getSocialLoginConfig().getVkBindSuggestDomains();
        ActiveAccountModeProvider activeAccountModeProvider = new ActiveAccountModeProvider() { // from class: ru.mail.setup.action.f1
            @Override // ru.mail.authorizationsdk.external.service.ActiveAccountModeProvider
            public final String isActiveMode(String str) {
                return NewAuthorizeComposeSdkStartup.suspendPerform$lambda$1(this.f97222a, str);
            }
        };
        AuthAnalyticsSdk authAnalyticsSdkCreateAnalyticsCallback = createAnalyticsCallback(this.application);
        String string = this.application.getString(R.string.auth_csrf_header);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        AccountManager accountManager = AccountManager.get(this.application);
        Intrinsics.checkNotNullExpressionValue(accountManager, "get(...)");
        OkHttpClient okHttpClient = this.okHttpClient;
        SocialLoginInfoHolderProvider socialLoginInfoHolderProvider = new SocialLoginInfoHolderProvider() { // from class: ru.mail.setup.action.NewAuthorizeComposeSdkStartup.suspendPerform.3
            @Override // ru.mail.authorizationsdk.external.service.SocialLoginInfoHolderProvider
            public void clear() {
                SocialLoginInfoHolder.clear(false);
            }

            @Override // ru.mail.authorizationsdk.external.service.SocialLoginInfoHolderProvider
            public CredentialsExchanger.SocialBindType getBindType() {
                return SocialLoginInfoHolder.getBindType();
            }

            @Override // ru.mail.authorizationsdk.external.service.SocialLoginInfoHolderProvider
            public boolean isCurrentlyBindingEmail() {
                return SocialLoginInfoHolder.isCurrentlyBindingEmail();
            }
        };
        ExternalPlatformData externalPlatformData = new ExternalPlatformData(LazyKt.lazy(new Function0() { // from class: ru.mail.setup.action.g1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NewAuthorizeComposeSdkStartup.suspendPerform$lambda$2(this.f97223a);
            }
        }), null, LazyKt.lazy(new Function0() { // from class: ru.mail.setup.action.h1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NewAuthorizeComposeSdkStartup.suspendPerform$lambda$3(this.f97224a);
            }
        }), Distributors.getFirstDistributor(this.application).toString(), BuildConfig.DISTRIBUTOR, 2, null);
        SocialAuthRepository socialAuthRepository = this.socialAuthRepository;
        ChildRegHelper childRegHelper = this.childRegHelper;
        AuthorizationSdk.initialize$default(authorizationSdk, scope, application, isTest, zIsMiniMailEnabled, accountManager, activeAccountModeProvider, logger, tsaCookieStore, authAnalyticsSdkCreateAnalyticsCallback, okHttpClient, string, null, externalPlatformData, deviceInfo, socialLoginInfoHolderProvider, advertisingIdProvider, secrets, secrets2, secrets3, secrets4, forceVkIdSecret, new Function0() { // from class: ru.mail.setup.action.j1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NewAuthorizeComposeSdkStartup.suspendPerform$lambda$5(this.f97225a);
            }
        }, appReporter, networkRequirementManager.getNetworkType(), notificationProvider, new DarkThemeResolver() { // from class: ru.mail.setup.action.i1
            @Override // ru.mail.authorizationsdk.feature.core.presentation.theme.DarkThemeResolver
            public final boolean isSystemInDarkTheme(Context context) {
                return NewAuthorizeComposeSdkStartup.suspendPerform$lambda$4(context);
            }
        }, socialAuthRepository, childRegHelper, null, null, null, null, null, null, new MailLoginChecker(), vkBindSuggestDomains, null, -268433408, 19, null);
        setConfigToSdk(configuration);
        return Unit.INSTANCE;
    }
}
