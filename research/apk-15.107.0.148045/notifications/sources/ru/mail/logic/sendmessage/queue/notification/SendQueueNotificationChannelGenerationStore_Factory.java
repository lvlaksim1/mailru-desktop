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
public final class SendQueueNotificationChannelGenerationStore_Factory implements Factory<SendQueueNotificationChannelGenerationStore> {
    private final Provider<Context> contextProvider;

    private SendQueueNotificationChannelGenerationStore_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static SendQueueNotificationChannelGenerationStore_Factory create(Provider<Context> provider) {
        return new SendQueueNotificationChannelGenerationStore_Factory(provider);
    }

    public static SendQueueNotificationChannelGenerationStore newInstance(Context context) {
        return new SendQueueNotificationChannelGenerationStore(context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public SendQueueNotificationChannelGenerationStore get() {
        return newInstance(this.contextProvider.get());
    }
}
