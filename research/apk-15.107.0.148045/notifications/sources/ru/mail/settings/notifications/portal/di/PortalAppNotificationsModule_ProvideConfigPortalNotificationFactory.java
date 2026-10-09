package ru.mail.settings.notifications.portal.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class PortalAppNotificationsModule_ProvideConfigPortalNotificationFactory implements Factory<Configuration.Portal.Notifications> {
    private final Provider<ConfigurationRepository> configRepoProvider;
    private final PortalAppNotificationsModule module;

    private PortalAppNotificationsModule_ProvideConfigPortalNotificationFactory(PortalAppNotificationsModule portalAppNotificationsModule, Provider<ConfigurationRepository> provider) {
        this.module = portalAppNotificationsModule;
        this.configRepoProvider = provider;
    }

    public static PortalAppNotificationsModule_ProvideConfigPortalNotificationFactory create(PortalAppNotificationsModule portalAppNotificationsModule, Provider<ConfigurationRepository> provider) {
        return new PortalAppNotificationsModule_ProvideConfigPortalNotificationFactory(portalAppNotificationsModule, provider);
    }

    public static Configuration.Portal.Notifications provideConfigPortalNotification(PortalAppNotificationsModule portalAppNotificationsModule, ConfigurationRepository configurationRepository) {
        return (Configuration.Portal.Notifications) Preconditions.checkNotNullFromProvides(portalAppNotificationsModule.provideConfigPortalNotification(configurationRepository));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public Configuration.Portal.Notifications get() {
        return provideConfigPortalNotification(this.module, this.configRepoProvider.get());
    }
}
