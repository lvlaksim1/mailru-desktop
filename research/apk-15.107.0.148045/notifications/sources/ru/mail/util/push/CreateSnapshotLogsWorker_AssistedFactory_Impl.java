package ru.mail.util.push;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@DaggerGenerated
public final class CreateSnapshotLogsWorker_AssistedFactory_Impl implements CreateSnapshotLogsWorker_AssistedFactory {
    private final CreateSnapshotLogsWorker_Factory delegateFactory;

    CreateSnapshotLogsWorker_AssistedFactory_Impl(CreateSnapshotLogsWorker_Factory createSnapshotLogsWorker_Factory) {
        this.delegateFactory = createSnapshotLogsWorker_Factory;
    }

    public static Provider<CreateSnapshotLogsWorker_AssistedFactory> createFactoryProvider(CreateSnapshotLogsWorker_Factory createSnapshotLogsWorker_Factory) {
        return InstanceFactory.create(new CreateSnapshotLogsWorker_AssistedFactory_Impl(createSnapshotLogsWorker_Factory));
    }

    @Override // ru.mail.util.push.CreateSnapshotLogsWorker_AssistedFactory, androidx.hilt.work.WorkerAssistedFactory
    public CreateSnapshotLogsWorker create(Context context, WorkerParameters workerParameters) {
        return this.delegateFactory.get(context, workerParameters);
    }

    public static javax.inject.Provider<CreateSnapshotLogsWorker_AssistedFactory> create(CreateSnapshotLogsWorker_Factory createSnapshotLogsWorker_Factory) {
        return InstanceFactory.create(new CreateSnapshotLogsWorker_AssistedFactory_Impl(createSnapshotLogsWorker_Factory));
    }
}
