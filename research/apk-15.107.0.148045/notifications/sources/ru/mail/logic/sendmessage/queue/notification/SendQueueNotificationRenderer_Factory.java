package ru.mail.logic.sendmessage.queue.notification;

import android.content.Context;
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
public final class SendQueueNotificationRenderer_Factory implements Factory<SendQueueNotificationRenderer> {
    private final Provider<SendQueueNotificationChannelManager> channelManagerProvider;
    private final Provider<Context> contextProvider;

    private SendQueueNotificationRenderer_Factory(Provider<Context> provider, Provider<SendQueueNotificationChannelManager> provider2) {
        this.contextProvider = provider;
        this.channelManagerProvider = provider2;
    }

    public static SendQueueNotificationRenderer_Factory create(Provider<Context> provider, Provider<SendQueueNotificationChannelManager> provider2) {
        return new SendQueueNotificationRenderer_Factory(provider, provider2);
    }

    public static SendQueueNotificationRenderer newInstance(Context context, SendQueueNotificationChannelManager sendQueueNotificationChannelManager) {
        return new SendQueueNotificationRenderer(context, sendQueueNotificationChannelManager);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SendQueueNotificationRenderer get() {
        return newInstance(this.contextProvider.get(), this.channelManagerProvider.get());
    }
}
