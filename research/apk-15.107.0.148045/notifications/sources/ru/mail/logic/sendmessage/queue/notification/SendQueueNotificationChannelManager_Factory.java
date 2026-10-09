package ru.mail.logic.sendmessage.queue.notification;

import android.content.Context;
import androidx.core.app.NotificationManagerCompat;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata({"dagger.hilt.android.qualifiers.ApplicationContext"})
public final class SendQueueNotificationChannelManager_Factory implements Factory<SendQueueNotificationChannelManager> {
    private final Provider<Context> contextProvider;
    private final Provider<SendQueueNotificationChannelGenerationStore> generationStoreProvider;
    private final Provider<NotificationManagerCompat> notificationManagerProvider;

    private SendQueueNotificationChannelManager_Factory(Provider<Context> provider, Provider<NotificationManagerCompat> provider2, Provider<SendQueueNotificationChannelGenerationStore> provider3) {
        this.contextProvider = provider;
        this.notificationManagerProvider = provider2;
        this.generationStoreProvider = provider3;
    }

    public static SendQueueNotificationChannelManager_Factory create(Provider<Context> provider, Provider<NotificationManagerCompat> provider2, Provider<SendQueueNotificationChannelGenerationStore> provider3) {
        return new SendQueueNotificationChannelManager_Factory(provider, provider2, provider3);
    }

    public static SendQueueNotificationChannelManager newInstance(Context context, NotificationManagerCompat notificationManagerCompat, SendQueueNotificationChannelGenerationStore sendQueueNotificationChannelGenerationStore) {
        return new SendQueueNotificationChannelManager(context, notificationManagerCompat, sendQueueNotificationChannelGenerationStore);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SendQueueNotificationChannelManager get() {
        return newInstance(this.contextProvider.get(), this.notificationManagerProvider.get(), this.generationStoreProvider.get());
    }
}
