package ru.mail.authorizationsdk.di.modules;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.authorizationsdk.external.config.AuthorizationSdkConfig;
import ru.mail.authorizationsdk.feature.captcha.config.LudwigConfig;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class AuthorizeConfigModule_ProvidesLudwigConfigFactory implements Factory<LudwigConfig> {
    private final Provider<AuthorizationSdkConfig> configProvider;
    private final AuthorizeConfigModule module;

    private AuthorizeConfigModule_ProvidesLudwigConfigFactory(AuthorizeConfigModule authorizeConfigModule, Provider<AuthorizationSdkConfig> provider) {
        this.module = authorizeConfigModule;
        this.configProvider = provider;
    }

    public static AuthorizeConfigModule_ProvidesLudwigConfigFactory create(AuthorizeConfigModule authorizeConfigModule, Provider<AuthorizationSdkConfig> provider) {
        return new AuthorizeConfigModule_ProvidesLudwigConfigFactory(authorizeConfigModule, provider);
    }

    public static LudwigConfig providesLudwigConfig(AuthorizeConfigModule authorizeConfigModule, AuthorizationSdkConfig authorizationSdkConfig) {
        return (LudwigConfig) Preconditions.checkNotNullFromProvides(authorizeConfigModule.providesLudwigConfig(authorizationSdkConfig));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public LudwigConfig get() {
        return providesLudwigConfig(this.module, this.configProvider.get());
    }
}
