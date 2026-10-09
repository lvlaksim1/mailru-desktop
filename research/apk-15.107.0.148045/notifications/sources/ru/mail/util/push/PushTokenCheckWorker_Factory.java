package ru.mail.util.push;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.logic.content.DataManager;
import ru.mail.utils.SafetyDependenciesProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class PushTokenCheckWorker_Factory {
    private final Provider<DataManager> dataManagerProvider;
    private final Provider<SafetyDependenciesProvider> providerProvider;

    private PushTokenCheckWorker_Factory(Provider<DataManager> provider, Provider<SafetyDependenciesProvider> provider2) {
        this.dataManagerProvider = provider;
        this.providerProvider = provider2;
    }

    public static PushTokenCheckWorker_Factory create(Provider<DataManager> provider, Provider<SafetyDependenciesProvider> provider2) {
        return new PushTokenCheckWorker_Factory(provider, provider2);
    }

    public static PushTokenCheckWorker newInstance(Context context, WorkerParameters workerParameters, javax.inject.Provider<DataManager> provider, SafetyDependenciesProvider safetyDependenciesProvider) {
        return new PushTokenCheckWorker(context, workerParameters, provider, safetyDependenciesProvider);
    }

    public PushTokenCheckWorker get(Context context, WorkerParameters workerParameters) {
        return newInstance(context, workerParameters, this.dataManagerProvider, this.providerProvider.get());
    }
}
