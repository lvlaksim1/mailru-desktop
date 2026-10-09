package ru.mail.settings.notifications.portal.domain.interactor;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.config.Configuration;
import ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings;
import ru.mail.settings.notifications.portal.domain.PortalAppNotificationsRepository;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"ru.mail.portal.app.adapter.di.PortalApp"})
public final class PortalAppNotificationsInteractor_Factory implements Factory<PortalAppNotificationsInteractor> {
    private final Provider<Configuration.Portal.Notifications> configProvider;
    private final Provider<PortalNotificationsSettings> notificationsSettingsProvider;
    private final Provider<PortalAppNotificationsRepository> repositoryProvider;

    private PortalAppNotificationsInteractor_Factory(Provider<PortalAppNotificationsRepository> provider, Provider<Configuration.Portal.Notifications> provider2, Provider<PortalNotificationsSettings> provider3) {
        this.repositoryProvider = provider;
        this.configProvider = provider2;
        this.notificationsSettingsProvider = provider3;
    }

    public static PortalAppNotificationsInteractor_Factory create(Provider<PortalAppNotificationsRepository> provider, Provider<Configuration.Portal.Notifications> provider2, Provider<PortalNotificationsSettings> provider3) {
        return new PortalAppNotificationsInteractor_Factory(provider, provider2, provider3);
    }

    public static PortalAppNotificationsInteractor newInstance(PortalAppNotificationsRepository portalAppNotificationsRepository, Configuration.Portal.Notifications notifications, PortalNotificationsSettings portalNotificationsSettings) {
        return new PortalAppNotificationsInteractor(portalAppNotificationsRepository, notifications, portalNotificationsSettings);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PortalAppNotificationsInteractor get() {
        return newInstance(this.repositoryProvider.get(), this.configProvider.get(), this.notificationsSettingsProvider.get());
    }
}
