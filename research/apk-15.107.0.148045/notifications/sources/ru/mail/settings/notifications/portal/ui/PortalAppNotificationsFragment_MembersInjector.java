package ru.mail.settings.notifications.portal.ui;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import ru.mail.logic.gotoaction.GoToActionInMailsListHandler;
import ru.mail.logic.profile.AuthOperationExecutor;
import ru.mail.logic.vpn.VpnBlockingDelegate;
import ru.mail.settings.notifications.portal.di.PortalAppNotificationsViewModelAssistedFactory;
import ru.mail.ui.accessibility.AccessibilityViewManager;
import ru.mail.ui.fragments.mailbox.AbstractAccessFragmentCore_MembersInjector;
import ru.mail.ui.fragments.mailbox.BaseMailFragment_MembersInjector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@DaggerGenerated
@QualifierMetadata
public final class PortalAppNotificationsFragment_MembersInjector implements MembersInjector<PortalAppNotificationsFragment> {
    private final Provider<PortalAppNotificationsViewModelAssistedFactory> assistedFactoryProvider;
    private final Provider<AccessibilityViewManager> mAccessibilityViewManagerProvider;
    private final Provider<AuthOperationExecutor> mAuthOperationExecutorProvider;
    private final Provider<GoToActionInMailsListHandler> mGoToActionHandlerProvider;
    private final Provider<VpnBlockingDelegate> vpnBlockingDelegateProvider;

    private PortalAppNotificationsFragment_MembersInjector(Provider<AccessibilityViewManager> provider, Provider<AuthOperationExecutor> provider2, Provider<GoToActionInMailsListHandler> provider3, Provider<VpnBlockingDelegate> provider4, Provider<PortalAppNotificationsViewModelAssistedFactory> provider5) {
        this.mAccessibilityViewManagerProvider = provider;
        this.mAuthOperationExecutorProvider = provider2;
        this.mGoToActionHandlerProvider = provider3;
        this.vpnBlockingDelegateProvider = provider4;
        this.assistedFactoryProvider = provider5;
    }

    public static MembersInjector<PortalAppNotificationsFragment> create(Provider<AccessibilityViewManager> provider, Provider<AuthOperationExecutor> provider2, Provider<GoToActionInMailsListHandler> provider3, Provider<VpnBlockingDelegate> provider4, Provider<PortalAppNotificationsViewModelAssistedFactory> provider5) {
        return new PortalAppNotificationsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    @InjectedFieldSignature("ru.mail.settings.notifications.portal.ui.PortalAppNotificationsFragment.assistedFactory")
    public static void injectAssistedFactory(PortalAppNotificationsFragment portalAppNotificationsFragment, PortalAppNotificationsViewModelAssistedFactory portalAppNotificationsViewModelAssistedFactory) {
        portalAppNotificationsFragment.assistedFactory = portalAppNotificationsViewModelAssistedFactory;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PortalAppNotificationsFragment portalAppNotificationsFragment) {
        BaseMailFragment_MembersInjector.injectMAccessibilityViewManager(portalAppNotificationsFragment, DoubleCheck.lazy((Provider) this.mAccessibilityViewManagerProvider));
        AbstractAccessFragmentCore_MembersInjector.injectMAuthOperationExecutor(portalAppNotificationsFragment, DoubleCheck.lazy((Provider) this.mAuthOperationExecutorProvider));
        AbstractAccessFragmentCore_MembersInjector.injectMGoToActionHandler(portalAppNotificationsFragment, DoubleCheck.lazy((Provider) this.mGoToActionHandlerProvider));
        AbstractAccessFragmentCore_MembersInjector.injectVpnBlockingDelegate(portalAppNotificationsFragment, DoubleCheck.lazy((Provider) this.vpnBlockingDelegateProvider));
        injectAssistedFactory(portalAppNotificationsFragment, this.assistedFactoryProvider.get());
    }
}
