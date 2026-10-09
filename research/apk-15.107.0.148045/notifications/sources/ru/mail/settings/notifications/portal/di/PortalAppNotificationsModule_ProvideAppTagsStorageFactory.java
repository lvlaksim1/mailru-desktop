package ru.mail.settings.notifications.portal.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.portal.app.adapter.notifications.tags.AppTagsStorage;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"dagger.hilt.android.qualifiers.ApplicationContext"})
public final class PortalAppNotificationsModule_ProvideAppTagsStorageFactory implements Factory<AppTagsStorage> {
    private final Provider<Context> contextProvider;
    private final PortalAppNotificationsModule module;

    private PortalAppNotificationsModule_ProvideAppTagsStorageFactory(PortalAppNotificationsModule portalAppNotificationsModule, Provider<Context> provider) {
        this.module = portalAppNotificationsModule;
        this.contextProvider = provider;
    }

    public static PortalAppNotificationsModule_ProvideAppTagsStorageFactory create(PortalAppNotificationsModule portalAppNotificationsModule, Provider<Context> provider) {
        return new PortalAppNotificationsModule_ProvideAppTagsStorageFactory(portalAppNotificationsModule, provider);
    }

    public static AppTagsStorage provideAppTagsStorage(PortalAppNotificationsModule portalAppNotificationsModule, Context context) {
        return (AppTagsStorage) Preconditions.checkNotNullFromProvides(portalAppNotificationsModule.provideAppTagsStorage(context));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public AppTagsStorage get() {
        return provideAppTagsStorage(this.module, this.contextProvider.get());
    }
}
