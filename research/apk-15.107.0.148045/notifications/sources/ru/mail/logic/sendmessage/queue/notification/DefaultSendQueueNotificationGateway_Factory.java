package ru.mail.logic.sendmessage.queue.notification;

import android.content.Context;
import androidx.core.app.NotificationManagerCompat;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import ru.mail.logic.sendmessage.queue.SendQueueModeProvider;
import ru.mail.messagesend.queue.SendQueueStateProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata({"dagger.hilt.android.qualifiers.ApplicationContext", "ru.mail.march.concurrent.IoDispatcher"})
public final class DefaultSendQueueNotificationGateway_Factory implements Factory<DefaultSendQueueNotificationGateway> {
    private final Provider<CoroutineScope> applicationScopeProvider;
    private final Provider<Context> contextProvider;
    private final Provider<CoroutineDispatcher> ioDispatcherProvider;
    private final Provider<MailNotificationsEnabledProvider> mailNotificationsEnabledProvider;
    private final Provider<SendQueueModeProvider> modeProvider;
    private final Provider<NotificationManagerCompat> notificationManagerProvider;
    private final Provider<SendQueueNotificationRenderer> rendererProvider;
    private final Provider<SendQueueStateProvider> stateProvider;

    private DefaultSendQueueNotificationGateway_Factory(Provider<Context> provider, Provider<SendQueueModeProvider> provider2, Provider<MailNotificationsEnabledProvider> provider3, Provider<SendQueueStateProvider> provider4, Provider<SendQueueNotificationRenderer> provider5, Provider<NotificationManagerCompat> provider6, Provider<CoroutineScope> provider7, Provider<CoroutineDispatcher> provider8) {
        this.contextProvider = provider;
        this.modeProvider = provider2;
        this.mailNotificationsEnabledProvider = provider3;
        this.stateProvider = provider4;
        this.rendererProvider = provider5;
        this.notificationManagerProvider = provider6;
        this.applicationScopeProvider = provider7;
        this.ioDispatcherProvider = provider8;
    }

    public static DefaultSendQueueNotificationGateway_Factory create(Provider<Context> provider, Provider<SendQueueModeProvider> provider2, Provider<MailNotificationsEnabledProvider> provider3, Provider<SendQueueStateProvider> provider4, Provider<SendQueueNotificationRenderer> provider5, Provider<NotificationManagerCompat> provider6, Provider<CoroutineScope> provider7, Provider<CoroutineDispatcher> provider8) {
        return new DefaultSendQueueNotificationGateway_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static DefaultSendQueueNotificationGateway newInstance(Context context, SendQueueModeProvider sendQueueModeProvider, MailNotificationsEnabledProvider mailNotificationsEnabledProvider, SendQueueStateProvider sendQueueStateProvider, SendQueueNotificationRenderer sendQueueNotificationRenderer, NotificationManagerCompat notificationManagerCompat, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        return new DefaultSendQueueNotificationGateway(context, sendQueueModeProvider, mailNotificationsEnabledProvider, sendQueueStateProvider, sendQueueNotificationRenderer, notificationManagerCompat, coroutineScope, coroutineDispatcher);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DefaultSendQueueNotificationGateway get() {
        return newInstance(this.contextProvider.get(), this.modeProvider.get(), this.mailNotificationsEnabledProvider.get(), this.stateProvider.get(), this.rendererProvider.get(), this.notificationManagerProvider.get(), this.applicationScopeProvider.get(), this.ioDispatcherProvider.get());
    }
}
