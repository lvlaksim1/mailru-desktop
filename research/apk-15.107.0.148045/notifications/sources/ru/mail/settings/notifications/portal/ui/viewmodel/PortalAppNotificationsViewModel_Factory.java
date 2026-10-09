package ru.mail.settings.notifications.portal.ui.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import kotlinx.coroutines.CoroutineDispatcher;
import ru.mail.settings.notifications.portal.domain.interactor.PortalAppNotificationsInteractor;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"ru.mail.march.concurrent.ViewModelDispatcher", "ru.mail.util.log.AppLogger"})
public final class PortalAppNotificationsViewModel_Factory {
    private final Provider<Logger> appLoggerProvider;
    private final Provider<CoroutineDispatcher> dispatcherProvider;
    private final Provider<PortalAppNotificationsInteractor> interactorProvider;

    private PortalAppNotificationsViewModel_Factory(Provider<CoroutineDispatcher> provider, Provider<Logger> provider2, Provider<PortalAppNotificationsInteractor> provider3) {
        this.dispatcherProvider = provider;
        this.appLoggerProvider = provider2;
        this.interactorProvider = provider3;
    }

    public static PortalAppNotificationsViewModel_Factory create(Provider<CoroutineDispatcher> provider, Provider<Logger> provider2, Provider<PortalAppNotificationsInteractor> provider3) {
        return new PortalAppNotificationsViewModel_Factory(provider, provider2, provider3);
    }

    public static PortalAppNotificationsViewModel newInstance(CoroutineDispatcher coroutineDispatcher, Logger logger, String str, PortalAppNotificationsInteractor portalAppNotificationsInteractor) {
        return new PortalAppNotificationsViewModel(coroutineDispatcher, logger, str, portalAppNotificationsInteractor);
    }

    public PortalAppNotificationsViewModel get(String str) {
        return newInstance(this.dispatcherProvider.get(), this.appLoggerProvider.get(), str, this.interactorProvider.get());
    }
}
