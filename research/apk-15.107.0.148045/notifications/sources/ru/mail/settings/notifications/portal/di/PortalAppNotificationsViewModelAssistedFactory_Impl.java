package ru.mail.settings.notifications.portal.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;
import ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel;
import ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel_Factory;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@DaggerGenerated
public final class PortalAppNotificationsViewModelAssistedFactory_Impl implements PortalAppNotificationsViewModelAssistedFactory {
    private final PortalAppNotificationsViewModel_Factory delegateFactory;

    PortalAppNotificationsViewModelAssistedFactory_Impl(PortalAppNotificationsViewModel_Factory portalAppNotificationsViewModel_Factory) {
        this.delegateFactory = portalAppNotificationsViewModel_Factory;
    }

    public static Provider<PortalAppNotificationsViewModelAssistedFactory> createFactoryProvider(PortalAppNotificationsViewModel_Factory portalAppNotificationsViewModel_Factory) {
        return InstanceFactory.create(new PortalAppNotificationsViewModelAssistedFactory_Impl(portalAppNotificationsViewModel_Factory));
    }

    @Override // ru.mail.settings.notifications.portal.di.PortalAppNotificationsViewModelAssistedFactory
    public PortalAppNotificationsViewModel create(String str) {
        return this.delegateFactory.get(str);
    }

    public static javax.inject.Provider<PortalAppNotificationsViewModelAssistedFactory> create(PortalAppNotificationsViewModel_Factory portalAppNotificationsViewModel_Factory) {
        return InstanceFactory.create(new PortalAppNotificationsViewModelAssistedFactory_Impl(portalAppNotificationsViewModel_Factory));
    }
}
