package ru.mail.util.push;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.config.Configuration;
import ru.mail.logic.content.impl.CreateLogsArchiveUseCase;
import ru.mail.march.internal.work.WorkScheduler;
import ru.mail.util.log.Logger;
import ru.mail.utils.SafetyDependenciesProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class CreateSnapshotLogsWorker_Factory {
    private final Provider<MailAppAnalytics> analyticsProvider;
    private final Provider<Configuration> configurationProvider;
    private final Provider<CreateLogsArchiveUseCase> createLogsArchiveUseCaseProvider;
    private final Provider<InternalStorageProvider> internalStorageProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<SafetyDependenciesProvider> providerProvider;
    private final Provider<WorkScheduler> workSchedulerProvider;

    private CreateSnapshotLogsWorker_Factory(Provider<CreateLogsArchiveUseCase> provider, Provider<Configuration> provider2, Provider<WorkScheduler> provider3, Provider<MailAppAnalytics> provider4, Provider<InternalStorageProvider> provider5, Provider<SafetyDependenciesProvider> provider6, Provider<Logger> provider7) {
        this.createLogsArchiveUseCaseProvider = provider;
        this.configurationProvider = provider2;
        this.workSchedulerProvider = provider3;
        this.analyticsProvider = provider4;
        this.internalStorageProvider = provider5;
        this.providerProvider = provider6;
        this.loggerProvider = provider7;
    }

    public static CreateSnapshotLogsWorker_Factory create(Provider<CreateLogsArchiveUseCase> provider, Provider<Configuration> provider2, Provider<WorkScheduler> provider3, Provider<MailAppAnalytics> provider4, Provider<InternalStorageProvider> provider5, Provider<SafetyDependenciesProvider> provider6, Provider<Logger> provider7) {
        return new CreateSnapshotLogsWorker_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static CreateSnapshotLogsWorker newInstance(Context context, WorkerParameters workerParameters, javax.inject.Provider<CreateLogsArchiveUseCase> provider, javax.inject.Provider<Configuration> provider2, javax.inject.Provider<WorkScheduler> provider3, javax.inject.Provider<MailAppAnalytics> provider4, InternalStorageProvider internalStorageProvider, SafetyDependenciesProvider safetyDependenciesProvider, Logger logger) {
        return new CreateSnapshotLogsWorker(context, workerParameters, provider, provider2, provider3, provider4, internalStorageProvider, safetyDependenciesProvider, logger);
    }

    public CreateSnapshotLogsWorker get(Context context, WorkerParameters workerParameters) {
        return newInstance(context, workerParameters, this.createLogsArchiveUseCaseProvider, this.configurationProvider, this.workSchedulerProvider, this.analyticsProvider, this.internalStorageProvider.get(), this.providerProvider.get(), this.loggerProvider.get());
    }
}
