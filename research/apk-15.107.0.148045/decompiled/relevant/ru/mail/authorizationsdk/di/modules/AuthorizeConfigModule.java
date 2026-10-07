package ru.mail.authorizationsdk.di.modules;

import androidx.compose.runtime.internal.StabilityInferred;
import dagger.Module;
import dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.external.config.AuthorizationSdkConfig;
import ru.mail.authorizationsdk.external.config.BrowserConfig;
import ru.mail.authorizationsdk.external.config.BuildConfigurationVariables;
import ru.mail.authorizationsdk.external.config.ImapConfig;
import ru.mail.authorizationsdk.external.config.MrimConfig;
import ru.mail.authorizationsdk.external.config.PushAuthInfoConfig;
import ru.mail.authorizationsdk.external.config.RegConfig;
import ru.mail.authorizationsdk.external.config.VkBindInLoginConfig;
import ru.mail.authorizationsdk.external.config.YandexHelpConfiguration;
import ru.mail.authorizationsdk.external.config.common.CommonConfig;
import ru.mail.authorizationsdk.external.config.common.DomainSuggestionsConfig;
import ru.mail.authorizationsdk.external.config.common.OidcIssuerConfig;
import ru.mail.authorizationsdk.external.config.flavor.FlavorConfig;
import ru.mail.authorizationsdk.external.config.login.LoginConfig;
import ru.mail.authorizationsdk.feature.captcha.config.LudwigConfig;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.vkmail.model.VkLoginScreenConfig;
import ru.mail.authorizationsdk.feature.secondfactor.config.SecondStepConfig;
import ru.mail.authorizationsdk.feature.socialauth.config.SocialAuthConfig;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.SessionRestoreConfig;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010&\u001a\u00020'2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006*"}, d2 = {"Lru/mail/authorizationsdk/di/modules/AuthorizeConfigModule;", "", "<init>", "()V", "providesLoginConfig", "Lru/mail/authorizationsdk/external/config/login/LoginConfig;", "config", "Lru/mail/authorizationsdk/external/config/AuthorizationSdkConfig;", "providesVkLoginScreenConfig", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/vkmail/model/VkLoginScreenConfig;", "providesCommonConfig", "Lru/mail/authorizationsdk/external/config/common/CommonConfig;", "providesDomainSuggestionsConfig", "Lru/mail/authorizationsdk/external/config/common/DomainSuggestionsConfig;", "providesSessionRestoreConfig", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/SessionRestoreConfig;", "providesLudwigConfig", "Lru/mail/authorizationsdk/feature/captcha/config/LudwigConfig;", "providesYandexHelpConfig", "Lru/mail/authorizationsdk/external/config/YandexHelpConfiguration;", "providesSecondStepConfig", "Lru/mail/authorizationsdk/feature/secondfactor/config/SecondStepConfig;", "providesSocialAuthConfig", "Lru/mail/authorizationsdk/feature/socialauth/config/SocialAuthConfig;", "providesMrimConfig", "Lru/mail/authorizationsdk/external/config/MrimConfig;", "providesBuildConfigurationVariables", "Lru/mail/authorizationsdk/external/config/BuildConfigurationVariables;", "providesImapConfig", "Lru/mail/authorizationsdk/external/config/ImapConfig;", "providePushAuthInfoConfig", "Lru/mail/authorizationsdk/external/config/PushAuthInfoConfig;", "provideBrowserConfig", "Lru/mail/authorizationsdk/external/config/BrowserConfig;", "providesRegConfig", "Lru/mail/authorizationsdk/external/config/RegConfig;", "provideVkBindInLoginConfig", "Lru/mail/authorizationsdk/external/config/VkBindInLoginConfig;", "provideFlavorConfig", "Lru/mail/authorizationsdk/external/config/flavor/FlavorConfig;", "provideOidcIssuerConfig", "Lru/mail/authorizationsdk/external/config/common/OidcIssuerConfig;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
public final class AuthorizeConfigModule {
    public static final int $stable = 0;

    @Provides
    @NotNull
    public final BrowserConfig provideBrowserConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getBrowserConfig();
    }

    @Provides
    @NotNull
    public final FlavorConfig provideFlavorConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getFlavorConfig();
    }

    @Provides
    @NotNull
    public final OidcIssuerConfig provideOidcIssuerConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getCommonConfig().getOidcIssuerConfig();
    }

    @Provides
    @NotNull
    public final PushAuthInfoConfig providePushAuthInfoConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getPushAuthInfoConfig();
    }

    @Provides
    @NotNull
    public final VkBindInLoginConfig provideVkBindInLoginConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getVkBindInLogin();
    }

    @Provides
    @NotNull
    public final BuildConfigurationVariables providesBuildConfigurationVariables(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getBuildConfigurationVariables();
    }

    @Provides
    @NotNull
    public final CommonConfig providesCommonConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getCommonConfig();
    }

    @Provides
    @NotNull
    public final DomainSuggestionsConfig providesDomainSuggestionsConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getCommonConfig().getDomainSuggestionsConfig();
    }

    @Provides
    @NotNull
    public final ImapConfig providesImapConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getImapConfig();
    }

    @Provides
    @NotNull
    public final LoginConfig providesLoginConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getLoginConfig();
    }

    @Provides
    @NotNull
    public final LudwigConfig providesLudwigConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getLudwigCaptchaConfig();
    }

    @Provides
    @NotNull
    public final MrimConfig providesMrimConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getMrimConfig();
    }

    @Provides
    @NotNull
    public final RegConfig providesRegConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getRegConfig();
    }

    @Provides
    @NotNull
    public final SecondStepConfig providesSecondStepConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getSecondStepConfig();
    }

    @Provides
    @NotNull
    public final SessionRestoreConfig providesSessionRestoreConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getSessionRestoreConfig();
    }

    @Provides
    @NotNull
    public final SocialAuthConfig providesSocialAuthConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getSocialAuthConfig();
    }

    @Provides
    @NotNull
    public final VkLoginScreenConfig providesVkLoginScreenConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getVkLoginConfig();
    }

    @Provides
    @NotNull
    public final YandexHelpConfiguration providesYandexHelpConfig(@NotNull AuthorizationSdkConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return config.getYandexHelpConfig();
    }
}
