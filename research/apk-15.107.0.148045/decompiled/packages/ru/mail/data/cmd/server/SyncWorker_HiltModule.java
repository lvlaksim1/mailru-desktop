package ru.mail.data.cmd.server;

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
/* JADX INFO: loaded from: classes9.dex */
@OriginatingElement(topLevelClass = SyncWorker.class)
@Module
@InstallIn({SingletonComponent.class})
public interface SyncWorker_HiltModule {
    @Binds
    @IntoMap
    @StringKey("ru.mail.data.cmd.server.SyncWorker")
    WorkerAssistedFactory<? extends ListenableWorker> bind(SyncWorker_AssistedFactory syncWorker_AssistedFactory);
}
