package ru.mail.settings.notifications.portal.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.portal.app.adapter.notifications.tags.AppTagsStorage;
import ru.mail.settings.notifications.portal.domain.PortalAppNotificationsRepository;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class PortalAppNotificationsModule_ProvidePortalAppNotificationsRepositoryFactory implements Factory<PortalAppNotificationsRepository> {
    private final PortalAppNotificationsModule module;
    private final Provider<AppTagsStorage> tagsStorageProvider;

    private PortalAppNotificationsModule_ProvidePortalAppNotificationsRepositoryFactory(PortalAppNotificationsModule portalAppNotificationsModule, Provider<AppTagsStorage> provider) {
        this.module = portalAppNotificationsModule;
        this.tagsStorageProvider = provider;
    }

    public static PortalAppNotificationsModule_ProvidePortalAppNotificationsRepositoryFactory create(PortalAppNotificationsModule portalAppNotificationsModule, Provider<AppTagsStorage> provider) {
        return new PortalAppNotificationsModule_ProvidePortalAppNotificationsRepositoryFactory(portalAppNotificationsModule, provider);
    }

    public static PortalAppNotificationsRepository providePortalAppNotificationsRepository(PortalAppNotificationsModule portalAppNotificationsModule, AppTagsStorage appTagsStorage) {
        return (PortalAppNotificationsRepository) Preconditions.checkNotNullFromProvides(portalAppNotificationsModule.providePortalAppNotificationsRepository(appTagsStorage));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PortalAppNotificationsRepository get() {
        return providePortalAppNotificationsRepository(this.module, this.tagsStorageProvider.get());
    }
}
