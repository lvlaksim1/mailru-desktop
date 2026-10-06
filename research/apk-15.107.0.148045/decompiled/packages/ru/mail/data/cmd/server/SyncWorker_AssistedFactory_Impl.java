package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@DaggerGenerated
public final class SyncWorker_AssistedFactory_Impl implements SyncWorker_AssistedFactory {
    private final SyncWorker_Factory delegateFactory;

    SyncWorker_AssistedFactory_Impl(SyncWorker_Factory syncWorker_Factory) {
        this.delegateFactory = syncWorker_Factory;
    }

    public static Provider<SyncWorker_AssistedFactory> createFactoryProvider(SyncWorker_Factory syncWorker_Factory) {
        return InstanceFactory.create(new SyncWorker_AssistedFactory_Impl(syncWorker_Factory));
    }

    @Override // ru.mail.data.cmd.server.SyncWorker_AssistedFactory, androidx.hilt.work.WorkerAssistedFactory
    public SyncWorker create(Context context, WorkerParameters workerParameters) {
        return this.delegateFactory.get(context, workerParameters);
    }

    public static javax.inject.Provider<SyncWorker_AssistedFactory> create(SyncWorker_Factory syncWorker_Factory) {
        return InstanceFactory.create(new SyncWorker_AssistedFactory_Impl(syncWorker_Factory));
    }
}
