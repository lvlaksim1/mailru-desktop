package ru.mail.settings.notifications.portal.data;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.portal.app.adapter.notifications.tags.AppTagsStorage;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@ScopeMetadata("dagger.hilt.android.scopes.ViewModelScoped")
@DaggerGenerated
@QualifierMetadata
public final class PortalAppNotificationsRepositoryImpl_Factory implements Factory<PortalAppNotificationsRepositoryImpl> {
    private final Provider<AppTagsStorage> tagsStorageProvider;

    private PortalAppNotificationsRepositoryImpl_Factory(Provider<AppTagsStorage> provider) {
        this.tagsStorageProvider = provider;
    }

    public static PortalAppNotificationsRepositoryImpl_Factory create(Provider<AppTagsStorage> provider) {
        return new PortalAppNotificationsRepositoryImpl_Factory(provider);
    }

    public static PortalAppNotificationsRepositoryImpl newInstance(AppTagsStorage appTagsStorage) {
        return new PortalAppNotificationsRepositoryImpl(appTagsStorage);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PortalAppNotificationsRepositoryImpl get() {
        return newInstance(this.tagsStorageProvider.get());
    }
}
