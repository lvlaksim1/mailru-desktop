package ru.mail.util.push;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@DaggerGenerated
public final class PushTokenCheckWorker_AssistedFactory_Impl implements PushTokenCheckWorker_AssistedFactory {
    private final PushTokenCheckWorker_Factory delegateFactory;

    PushTokenCheckWorker_AssistedFactory_Impl(PushTokenCheckWorker_Factory pushTokenCheckWorker_Factory) {
        this.delegateFactory = pushTokenCheckWorker_Factory;
    }

    public static Provider<PushTokenCheckWorker_AssistedFactory> createFactoryProvider(PushTokenCheckWorker_Factory pushTokenCheckWorker_Factory) {
        return InstanceFactory.create(new PushTokenCheckWorker_AssistedFactory_Impl(pushTokenCheckWorker_Factory));
    }

    @Override // ru.mail.util.push.PushTokenCheckWorker_AssistedFactory, androidx.hilt.work.WorkerAssistedFactory
    public PushTokenCheckWorker create(Context context, WorkerParameters workerParameters) {
        return this.delegateFactory.get(context, workerParameters);
    }

    public static javax.inject.Provider<PushTokenCheckWorker_AssistedFactory> create(PushTokenCheckWorker_Factory pushTokenCheckWorker_Factory) {
        return InstanceFactory.create(new PushTokenCheckWorker_AssistedFactory_Impl(pushTokenCheckWorker_Factory));
    }
}
