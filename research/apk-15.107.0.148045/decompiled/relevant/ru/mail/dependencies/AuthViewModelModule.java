package ru.mail.dependencies;

import android.content.Context;
import android.content.res.Resources;
import androidx.compose.runtime.internal.StabilityInferred;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ActivityRetainedScoped;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider;
import ru.mail.credentialsexchanger.data.network.urlprovider.UrlProviderImpl;
import ru.mail.logic.auth.SuperAppKitIds;
import ru.mail.logic.cmd.socialbind.LudwigTokensApi;
import ru.mail.logic.cmd.socialbind.SocialBindAddApi;
import ru.mail.logic.cmd.socialbind.VerificationPasswordCheckApi;
import ru.mail.logic.cmd.socialbind.VkPreflightApi;
import ru.mail.logic.content.DataManager;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProviderWrapper;
import ru.mail.ui.auth.universal.UserBoundByVKIDDelegate;
import ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthInteractor;
import ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthInteractorImpl;
import ru.mail.ui.auth.universal.forceauthorizationbyvkid.VkSdkProvider;
import ru.mail.ui.auth.universal.forceauthorizationbyvkid.VkSdkProviderImpl;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.AddSocialBindUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.BindVKIDNewFlowUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.BindVKIDUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetLocalDataForVKIDBindUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetLudwigTokenUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetMailDataForVKIDBindUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetVKIDDataForVKIDBindUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetVKPreflightUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.VKIDBindEmailPromoInteractorImpl;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.VKIDBindPromoInteractorImpl;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.VerifyPasswordCheckUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.interfaces.VKIDBindEmailPromoInteractor;
import ru.mail.ui.auth.universal.vkidbindpromo.interfaces.VKIDBindPromoInteractor;
import ru.mail.ui.fragments.regtabs.childtab.ChildTabInteractor;
import ru.mail.ui.fragments.regtabs.childtab.ChildTabInteractorImpl;
import ru.mail.ui.fragments.regtabs.childtab.IsAgeBelow14UseCase;
import ru.mail.ui.fragments.regtabs.defaulttab.TabDefaultRegInteractor;
import ru.mail.ui.fragments.regtabs.defaulttab.TabDefaultRegInteractorImpl;
import ru.mail.ui.fragments.settings.security.vkbind.VkBindInteractor;
import ru.mail.ui.fragments.settings.security.vkbind.VkBindInteractorImpl;
import ru.mail.ui.fragments.settings.security.webauthn.WebAuthNInteractor;
import ru.mail.ui.fragments.settings.security.webauthn.WebAuthNInteractorImpl;
import ru.mail.util.analytics.interactor_analytics.InteractorAnalyticsProvider;
import ru.mail.util.feature.MailFeatureProvider;
import ru.mail.util.vk_account.VkAccountProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000ð\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u0007J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J@\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J(\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0007J \u0010'\u001a\u00020(2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010)\u001a\u00020*H\u0007J\u0018\u0010+\u001a\u00020,2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010)\u001a\u00020*H\u0007J \u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u00020,2\u0006\u0010)\u001a\u00020*H\u0007J\u0010\u00100\u001a\u00020$2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J:\u00101\u001a\u00020&2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010)\u001a\u00020*2\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0007J\u0018\u0010:\u001a\u0002032\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0007J\u0018\u0010?\u001a\u0002052\u0006\u0010;\u001a\u00020<2\u0006\u0010@\u001a\u00020AH\u0007J\u0018\u0010B\u001a\u0002072\u0006\u0010;\u001a\u00020<2\u0006\u0010C\u001a\u00020DH\u0007J\u0018\u0010E\u001a\u0002092\u0006\u0010;\u001a\u00020<2\u0006\u0010F\u001a\u00020GH\u0007J@\u0010H\u001a\u00020I2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010J\u001a\u00020K2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u0018\u0010L\u001a\u00020M2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u0018\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020Q2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\"\u0010R\u001a\u00020S2\b\b\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010T\u001a\u00020UH\u0007¨\u0006V"}, d2 = {"Lru/mail/dependencies/AuthViewModelModule;", "", "<init>", "()V", "provideVkSdkProvider", "Lru/mail/ui/auth/universal/forceauthorizationbyvkid/VkSdkProvider;", "context", "Landroid/content/Context;", "provideUrlProvider", "Lru/mail/credentialsexchanger/data/network/urlprovider/UrlProvider;", "provideForceVKIDAuthInteractor", "Lru/mail/ui/auth/universal/forceauthorizationbyvkid/ForceVKIDAuthInteractor;", "configRepo", "Lru/mail/config/ConfigurationRepository;", "vkSdkProvider", "credentialsExchanger", "Lru/mail/credentialsexchanger/core/CredentialsExchanger;", "provideVKIDBindEmailPromoInteractor", "Lru/mail/ui/auth/universal/vkidbindpromo/interfaces/VKIDBindEmailPromoInteractor;", "vkAccountProvider", "Lru/mail/util/vk_account/VkAccountProvider;", "dataManager", "Lru/mail/logic/content/DataManager;", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "analyticsProvider", "Lru/mail/util/analytics/interactor_analytics/InteractorAnalyticsProvider;", "userBoundByVKIDDelegate", "Lru/mail/ui/auth/universal/UserBoundByVKIDDelegate;", "provideVKIDNewBindPromoInteractor", "Lru/mail/ui/auth/universal/vkidbindpromo/interfaces/VKIDBindPromoInteractor;", "mailAppAnalytics", "Lru/mail/analytics/MailAppAnalytics;", "getLocalDataForVKIDBindUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLocalDataForVKIDBindUseCase;", "bindVKIDUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDUseCase;", "bindVKIDNewFlowUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase;", "provideGetMailDataForVKIDBindUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetMailDataForVKIDBindUseCase;", "configuration", "Lru/mail/config/Configuration;", "provideGetVKIDDataForVKIDBindUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetVKIDDataForVKIDBindUseCase;", "provideGetLocalDataForVKIDBindUseCase", "getMailDataForVKIDBindUseCase", "getVKIDDataForVKIDBindUseCase", "provideBindVKIDUseCase", "provideBindVKIDNewFlowUseCase", "getLudwigTokenUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase;", "verifyPasswordCheckUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/VerifyPasswordCheckUseCase;", "getVKPreflightUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetVKPreflightUseCase;", "addSocialBindUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/AddSocialBindUseCase;", "provideGetLudwigTokenUseCase", "executorSelector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "ludwigTokensApi", "Lru/mail/logic/cmd/socialbind/LudwigTokensApi;", "provideVerifyPasswordCheckUseCase", "verificationPasswordCheckApi", "Lru/mail/logic/cmd/socialbind/VerificationPasswordCheckApi;", "provideGetVKPreflightUseCase", "preflightApi", "Lru/mail/logic/cmd/socialbind/VkPreflightApi;", "provideAddSocialBindUseCase", "socialBindAddApi", "Lru/mail/logic/cmd/socialbind/SocialBindAddApi;", "provideVkBindInteractor", "Lru/mail/ui/fragments/settings/security/vkbind/VkBindInteractor;", "mailFeatureProvider", "Lru/mail/util/feature/MailFeatureProvider;", "provideChildRegTabInteractor", "Lru/mail/ui/fragments/regtabs/childtab/ChildTabInteractor;", "provideDefaultRegTabInteractor", "Lru/mail/ui/fragments/regtabs/defaulttab/TabDefaultRegInteractor;", "isAgeBelow14", "Lru/mail/ui/fragments/regtabs/childtab/IsAgeBelow14UseCase;", "provideWebAuthNInteractor", "Lru/mail/ui/fragments/settings/security/webauthn/WebAuthNInteractor;", "hostProviderWrapper", "Lru/mail/network/HostProviderWrapper;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
@InstallIn({ActivityRetainedComponent.class})
public final class AuthViewModelModule {
    public static final int $stable = 0;

    @NotNull
    public static final AuthViewModelModule INSTANCE = new AuthViewModelModule();

    private AuthViewModelModule() {
    }

    @Provides
    @NotNull
    public final AddSocialBindUseCase provideAddSocialBindUseCase(@NotNull ExecutorSelector executorSelector, @NotNull SocialBindAddApi socialBindAddApi) {
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        Intrinsics.checkNotNullParameter(socialBindAddApi, "socialBindAddApi");
        return new AddSocialBindUseCase(executorSelector, socialBindAddApi);
    }

    @Provides
    @NotNull
    public final BindVKIDNewFlowUseCase provideBindVKIDNewFlowUseCase(@ApplicationContext @NotNull Context context, @NotNull Configuration configuration, @NotNull GetLudwigTokenUseCase getLudwigTokenUseCase, @NotNull VerifyPasswordCheckUseCase verifyPasswordCheckUseCase, @NotNull GetVKPreflightUseCase getVKPreflightUseCase, @NotNull AddSocialBindUseCase addSocialBindUseCase) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(getLudwigTokenUseCase, "getLudwigTokenUseCase");
        Intrinsics.checkNotNullParameter(verifyPasswordCheckUseCase, "verifyPasswordCheckUseCase");
        Intrinsics.checkNotNullParameter(getVKPreflightUseCase, "getVKPreflightUseCase");
        Intrinsics.checkNotNullParameter(addSocialBindUseCase, "addSocialBindUseCase");
        return new BindVKIDNewFlowUseCase(configuration, getLudwigTokenUseCase, verifyPasswordCheckUseCase, getVKPreflightUseCase, addSocialBindUseCase, SuperAppKitIds.INSTANCE.getClientId(context));
    }

    @Provides
    @NotNull
    public final BindVKIDUseCase provideBindVKIDUseCase(@NotNull CredentialsExchanger credentialsExchanger) {
        Intrinsics.checkNotNullParameter(credentialsExchanger, "credentialsExchanger");
        return new BindVKIDUseCase(credentialsExchanger, false);
    }

    @Provides
    @NotNull
    public final ChildTabInteractor provideChildRegTabInteractor(@NotNull DataManager dataManager, @NotNull InteractorAnalyticsProvider analyticsProvider) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(analyticsProvider, "analyticsProvider");
        return new ChildTabInteractorImpl(dataManager, analyticsProvider, null, 4, null);
    }

    @Provides
    @NotNull
    public final TabDefaultRegInteractor provideDefaultRegTabInteractor(@NotNull IsAgeBelow14UseCase isAgeBelow14, @NotNull VkAccountProvider vkAccountProvider) {
        Intrinsics.checkNotNullParameter(isAgeBelow14, "isAgeBelow14");
        Intrinsics.checkNotNullParameter(vkAccountProvider, "vkAccountProvider");
        return new TabDefaultRegInteractorImpl(isAgeBelow14, vkAccountProvider);
    }

    @Provides
    @ActivityRetainedScoped
    @NotNull
    public final ForceVKIDAuthInteractor provideForceVKIDAuthInteractor(@NotNull ConfigurationRepository configRepo, @NotNull VkSdkProvider vkSdkProvider, @NotNull CredentialsExchanger credentialsExchanger) {
        Intrinsics.checkNotNullParameter(configRepo, "configRepo");
        Intrinsics.checkNotNullParameter(vkSdkProvider, "vkSdkProvider");
        Intrinsics.checkNotNullParameter(credentialsExchanger, "credentialsExchanger");
        ConfigurationWithRawData configuration = configRepo.getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        return new ForceVKIDAuthInteractorImpl(configuration, vkSdkProvider, credentialsExchanger, null, 8, null);
    }

    @Provides
    @NotNull
    public final GetLocalDataForVKIDBindUseCase provideGetLocalDataForVKIDBindUseCase(@NotNull GetMailDataForVKIDBindUseCase getMailDataForVKIDBindUseCase, @NotNull GetVKIDDataForVKIDBindUseCase getVKIDDataForVKIDBindUseCase, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(getMailDataForVKIDBindUseCase, "getMailDataForVKIDBindUseCase");
        Intrinsics.checkNotNullParameter(getVKIDDataForVKIDBindUseCase, "getVKIDDataForVKIDBindUseCase");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new GetLocalDataForVKIDBindUseCase(getMailDataForVKIDBindUseCase, getVKIDDataForVKIDBindUseCase, configuration.getVkIdBindEmailPromoConfig().getRatPromo().getGetDataTimeout());
    }

    @Provides
    @NotNull
    public final GetLudwigTokenUseCase provideGetLudwigTokenUseCase(@NotNull ExecutorSelector executorSelector, @NotNull LudwigTokensApi ludwigTokensApi) {
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        Intrinsics.checkNotNullParameter(ludwigTokensApi, "ludwigTokensApi");
        return new GetLudwigTokenUseCase(executorSelector, ludwigTokensApi);
    }

    @Provides
    @NotNull
    public final GetMailDataForVKIDBindUseCase provideGetMailDataForVKIDBindUseCase(@NotNull DataManager dataManager, @NotNull AccountManagerWrapper accountManager, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new GetMailDataForVKIDBindUseCase(dataManager, accountManager, configuration.getVkIdBindEmailPromoConfig().getRatPromo().isPasswordCheckingEnabled());
    }

    @Provides
    @NotNull
    public final GetVKIDDataForVKIDBindUseCase provideGetVKIDDataForVKIDBindUseCase(@NotNull VkAccountProvider vkAccountProvider, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(vkAccountProvider, "vkAccountProvider");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new GetVKIDDataForVKIDBindUseCase(vkAccountProvider, configuration.getVkIdBindEmailPromoConfig().getRatPromo().isEverywhereSilentInfoEnabled());
    }

    @Provides
    @NotNull
    public final GetVKPreflightUseCase provideGetVKPreflightUseCase(@NotNull ExecutorSelector executorSelector, @NotNull VkPreflightApi preflightApi) {
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        Intrinsics.checkNotNullParameter(preflightApi, "preflightApi");
        return new GetVKPreflightUseCase(executorSelector, preflightApi);
    }

    @Provides
    @NotNull
    public final UrlProvider provideUrlProvider(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Resources resources = context.getResources();
        String string = resources.getString(ru.mail.mailapp.R.string.credentials_exchanger_def_scheme);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = resources.getString(ru.mail.mailapp.R.string.credentials_exchanger_account_mail_host);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        String string3 = resources.getString(ru.mail.mailapp.R.string.credentials_exchanger_alt_aj_auth_host);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
        String string4 = resources.getString(ru.mail.mailapp.R.string.credentials_exchanger_auth_host);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        return new UrlProviderImpl(string, string2, string3, string4);
    }

    @Provides
    @NotNull
    public final VKIDBindEmailPromoInteractor provideVKIDBindEmailPromoInteractor(@NotNull ConfigurationRepository configRepo, @NotNull VkAccountProvider vkAccountProvider, @NotNull DataManager dataManager, @NotNull AccountManagerWrapper accountManager, @NotNull CredentialsExchanger credentialsExchanger, @NotNull InteractorAnalyticsProvider analyticsProvider, @NotNull UserBoundByVKIDDelegate userBoundByVKIDDelegate) {
        Intrinsics.checkNotNullParameter(configRepo, "configRepo");
        Intrinsics.checkNotNullParameter(vkAccountProvider, "vkAccountProvider");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(credentialsExchanger, "credentialsExchanger");
        Intrinsics.checkNotNullParameter(analyticsProvider, "analyticsProvider");
        Intrinsics.checkNotNullParameter(userBoundByVKIDDelegate, "userBoundByVKIDDelegate");
        return new VKIDBindEmailPromoInteractorImpl(configRepo.getConfiguration().getVkIdBindEmailPromoConfig(), vkAccountProvider, dataManager, accountManager, credentialsExchanger, analyticsProvider, userBoundByVKIDDelegate, null, 128, null);
    }

    @Provides
    @NotNull
    public final VKIDBindPromoInteractor provideVKIDNewBindPromoInteractor(@NotNull MailAppAnalytics mailAppAnalytics, @NotNull GetLocalDataForVKIDBindUseCase getLocalDataForVKIDBindUseCase, @NotNull BindVKIDUseCase bindVKIDUseCase, @NotNull BindVKIDNewFlowUseCase bindVKIDNewFlowUseCase) {
        Intrinsics.checkNotNullParameter(mailAppAnalytics, "mailAppAnalytics");
        Intrinsics.checkNotNullParameter(getLocalDataForVKIDBindUseCase, "getLocalDataForVKIDBindUseCase");
        Intrinsics.checkNotNullParameter(bindVKIDUseCase, "bindVKIDUseCase");
        Intrinsics.checkNotNullParameter(bindVKIDNewFlowUseCase, "bindVKIDNewFlowUseCase");
        return new VKIDBindPromoInteractorImpl(mailAppAnalytics, getLocalDataForVKIDBindUseCase, bindVKIDUseCase, bindVKIDNewFlowUseCase);
    }

    @Provides
    @NotNull
    public final VerifyPasswordCheckUseCase provideVerifyPasswordCheckUseCase(@NotNull ExecutorSelector executorSelector, @NotNull VerificationPasswordCheckApi verificationPasswordCheckApi) {
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        Intrinsics.checkNotNullParameter(verificationPasswordCheckApi, "verificationPasswordCheckApi");
        return new VerifyPasswordCheckUseCase(executorSelector, verificationPasswordCheckApi);
    }

    @Provides
    @NotNull
    public final VkBindInteractor provideVkBindInteractor(@NotNull ConfigurationRepository configRepo, @NotNull MailFeatureProvider mailFeatureProvider, @NotNull VkAccountProvider vkAccountProvider, @NotNull DataManager dataManager, @NotNull AccountManagerWrapper accountManager, @NotNull CredentialsExchanger credentialsExchanger, @NotNull InteractorAnalyticsProvider analyticsProvider) {
        Intrinsics.checkNotNullParameter(configRepo, "configRepo");
        Intrinsics.checkNotNullParameter(mailFeatureProvider, "mailFeatureProvider");
        Intrinsics.checkNotNullParameter(vkAccountProvider, "vkAccountProvider");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(credentialsExchanger, "credentialsExchanger");
        Intrinsics.checkNotNullParameter(analyticsProvider, "analyticsProvider");
        return new VkBindInteractorImpl(configRepo.getConfiguration().getVkBindInSettingsConfig(), mailFeatureProvider, vkAccountProvider, dataManager, accountManager, credentialsExchanger, analyticsProvider, null, 128, null);
    }

    @Provides
    @NotNull
    public final VkSdkProvider provideVkSdkProvider(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new VkSdkProviderImpl(context);
    }

    @Provides
    @NotNull
    public final WebAuthNInteractor provideWebAuthNInteractor(@ApplicationContext @NotNull Context context, @NotNull DataManager dataManager, @NotNull HostProviderWrapper hostProviderWrapper) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(hostProviderWrapper, "hostProviderWrapper");
        return new WebAuthNInteractorImpl(context, dataManager, hostProviderWrapper);
    }
}
