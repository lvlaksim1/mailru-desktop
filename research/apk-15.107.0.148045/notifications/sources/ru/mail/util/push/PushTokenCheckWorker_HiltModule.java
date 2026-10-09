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
@OriginatingElement(topLevelClass = PushTokenCheckWorker.class)
@Module
@InstallIn({SingletonComponent.class})
public interface PushTokenCheckWorker_HiltModule {
    @Binds
    @IntoMap
    @StringKey("ru.mail.util.push.PushTokenCheckWorker")
    WorkerAssistedFactory<? extends ListenableWorker> bind(PushTokenCheckWorker_AssistedFactory pushTokenCheckWorker_AssistedFactory);
}
