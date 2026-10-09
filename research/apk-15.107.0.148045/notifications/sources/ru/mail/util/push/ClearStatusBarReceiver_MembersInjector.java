package ru.mail.util.push;

import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.config.section.PortalConfigDto;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@DaggerGenerated
@QualifierMetadata
public final class ClearStatusBarReceiver_MembersInjector implements MembersInjector<ClearStatusBarReceiver> {
    private final Provider<MailAppAnalytics> analyticsProvider;
    private final Provider<NotificationHandler> notificationHandlerProvider;
    private final Provider<PortalConfigDto> portalConfigProvider;

    private ClearStatusBarReceiver_MembersInjector(Provider<MailAppAnalytics> provider, Provider<NotificationHandler> provider2, Provider<PortalConfigDto> provider3) {
        this.analyticsProvider = provider;
        this.notificationHandlerProvider = provider2;
        this.portalConfigProvider = provider3;
    }

    public static MembersInjector<ClearStatusBarReceiver> create(Provider<MailAppAnalytics> provider, Provider<NotificationHandler> provider2, Provider<PortalConfigDto> provider3) {
        return new ClearStatusBarReceiver_MembersInjector(provider, provider2, provider3);
    }

    @InjectedFieldSignature("ru.mail.util.push.ClearStatusBarReceiver.analytics")
    public static void injectAnalytics(ClearStatusBarReceiver clearStatusBarReceiver, Lazy<MailAppAnalytics> lazy) {
        clearStatusBarReceiver.analytics = lazy;
    }

    @InjectedFieldSignature("ru.mail.util.push.ClearStatusBarReceiver.notificationHandler")
    public static void injectNotificationHandler(ClearStatusBarReceiver clearStatusBarReceiver, Lazy<NotificationHandler> lazy) {
        clearStatusBarReceiver.notificationHandler = lazy;
    }

    @InjectedFieldSignature("ru.mail.util.push.ClearStatusBarReceiver.portalConfig")
    public static void injectPortalConfig(ClearStatusBarReceiver clearStatusBarReceiver, Lazy<PortalConfigDto> lazy) {
        clearStatusBarReceiver.portalConfig = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ClearStatusBarReceiver clearStatusBarReceiver) {
        injectAnalytics(clearStatusBarReceiver, DoubleCheck.lazy((Provider) this.analyticsProvider));
        injectNotificationHandler(clearStatusBarReceiver, DoubleCheck.lazy((Provider) this.notificationHandlerProvider));
        injectPortalConfig(clearStatusBarReceiver, DoubleCheck.lazy((Provider) this.portalConfigProvider));
    }
}
