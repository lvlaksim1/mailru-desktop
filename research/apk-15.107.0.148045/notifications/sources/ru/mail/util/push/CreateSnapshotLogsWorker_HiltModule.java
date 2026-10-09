package ru.mail.util.push;

import androidx.hilt.work.WorkerAssistedFactory;
import androidx.work.ListenableWorker;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@OriginatingElement(topLevelClass = CreateSnapshotLogsWorker.class)
@Module
@InstallIn({SingletonComponent.class})
public interface CreateSnapshotLogsWorker_HiltModule {
    @Binds
    @IntoMap
    @StringKey("ru.mail.util.push.CreateSnapshotLogsWorker")
    WorkerAssistedFactory<? extends ListenableWorker> bind(CreateSnapshotLogsWorker_AssistedFactory createSnapshotLogsWorker_AssistedFactory);
}
