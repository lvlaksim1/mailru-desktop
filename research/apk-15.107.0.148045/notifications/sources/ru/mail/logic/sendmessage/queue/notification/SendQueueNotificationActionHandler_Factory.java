package ru.mail.logic.sendmessage.queue.notification;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.logic.sendmessage.queue.SendQueueModeProvider;
import ru.mail.messagesend.queue.SendQueue;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata
public final class SendQueueNotificationActionHandler_Factory implements Factory<SendQueueNotificationActionHandler> {
    private final Provider<SendQueueModeProvider> modeProvider;
    private final Provider<SendQueueNotificationGateway> notificationGatewayProvider;
    private final Provider<SendQueue> sendQueueProvider;

    private SendQueueNotificationActionHandler_Factory(Provider<SendQueueModeProvider> provider, Provider<SendQueue> provider2, Provider<SendQueueNotificationGateway> provider3) {
        this.modeProvider = provider;
        this.sendQueueProvider = provider2;
        this.notificationGatewayProvider = provider3;
    }

    public static SendQueueNotificationActionHandler_Factory create(Provider<SendQueueModeProvider> provider, Provider<SendQueue> provider2, Provider<SendQueueNotificationGateway> provider3) {
        return new SendQueueNotificationActionHandler_Factory(provider, provider2, provider3);
    }

    public static SendQueueNotificationActionHandler newInstance(SendQueueModeProvider sendQueueModeProvider, SendQueue sendQueue, SendQueueNotificationGateway sendQueueNotificationGateway) {
        return new SendQueueNotificationActionHandler(sendQueueModeProvider, sendQueue, sendQueueNotificationGateway);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SendQueueNotificationActionHandler get() {
        return newInstance(this.modeProvider.get(), this.sendQueueProvider.get(), this.notificationGatewayProvider.get());
    }
}
