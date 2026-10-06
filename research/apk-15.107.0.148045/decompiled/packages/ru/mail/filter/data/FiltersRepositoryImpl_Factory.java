package ru.mail.filter.data;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.data.dao.ResourceObservable;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContextProvider;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"dagger.hilt.android.qualifiers.ApplicationContext", "javax.inject.Named"})
public final class FiltersRepositoryImpl_Factory implements Factory<FiltersRepositoryImpl> {
    private final Provider<MailAppAnalytics> analyticsProvider;
    private final Provider<Context> appContextProvider;
    private final Provider<DataManager> dataManagerProvider;
    private final Provider<FiltersDatabaseRepository> databaseRepositoryProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<MailboxContextProvider> mailboxContextProvider;
    private final Provider<FiltersMemoryCacheRepository> memoryCacheRepositoryProvider;
    private final Provider<RequestArbiter> requestArbiterProvider;
    private final Provider<ResourceObservable> resourceObservableProvider;

    private FiltersRepositoryImpl_Factory(Provider<Context> provider, Provider<Logger> provider2, Provider<DataManager> provider3, Provider<RequestArbiter> provider4, Provider<MailAppAnalytics> provider5, Provider<FiltersDatabaseRepository> provider6, Provider<FiltersMemoryCacheRepository> provider7, Provider<MailboxContextProvider> provider8, Provider<ResourceObservable> provider9) {
        this.appContextProvider = provider;
        this.loggerProvider = provider2;
        this.dataManagerProvider = provider3;
        this.requestArbiterProvider = provider4;
        this.analyticsProvider = provider5;
        this.databaseRepositoryProvider = provider6;
        this.memoryCacheRepositoryProvider = provider7;
        this.mailboxContextProvider = provider8;
        this.resourceObservableProvider = provider9;
    }

    public static FiltersRepositoryImpl_Factory create(Provider<Context> provider, Provider<Logger> provider2, Provider<DataManager> provider3, Provider<RequestArbiter> provider4, Provider<MailAppAnalytics> provider5, Provider<FiltersDatabaseRepository> provider6, Provider<FiltersMemoryCacheRepository> provider7, Provider<MailboxContextProvider> provider8, Provider<ResourceObservable> provider9) {
        return new FiltersRepositoryImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static FiltersRepositoryImpl newInstance(Context context, Logger logger, DataManager dataManager, RequestArbiter requestArbiter, MailAppAnalytics mailAppAnalytics, FiltersDatabaseRepository filtersDatabaseRepository, FiltersMemoryCacheRepository filtersMemoryCacheRepository, MailboxContextProvider mailboxContextProvider, ResourceObservable resourceObservable) {
        return new FiltersRepositoryImpl(context, logger, dataManager, requestArbiter, mailAppAnalytics, filtersDatabaseRepository, filtersMemoryCacheRepository, mailboxContextProvider, resourceObservable);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public FiltersRepositoryImpl get() {
        return newInstance(this.appContextProvider.get(), this.loggerProvider.get(), this.dataManagerProvider.get(), this.requestArbiterProvider.get(), this.analyticsProvider.get(), this.databaseRepositoryProvider.get(), this.memoryCacheRepositoryProvider.get(), this.mailboxContextProvider.get(), this.resourceObservableProvider.get());
    }
}
