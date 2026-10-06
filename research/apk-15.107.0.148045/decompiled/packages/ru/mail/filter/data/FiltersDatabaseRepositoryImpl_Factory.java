package ru.mail.filter.data;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"javax.inject.Named", "dagger.hilt.android.qualifiers.ApplicationContext"})
public final class FiltersDatabaseRepositoryImpl_Factory implements Factory<FiltersDatabaseRepositoryImpl> {
    private final Provider<Context> appContextProvider;
    private final Provider<Logger> loggerProvider;
    private final Provider<RequestArbiter> requestArbiterProvider;

    private FiltersDatabaseRepositoryImpl_Factory(Provider<Logger> provider, Provider<Context> provider2, Provider<RequestArbiter> provider3) {
        this.loggerProvider = provider;
        this.appContextProvider = provider2;
        this.requestArbiterProvider = provider3;
    }

    public static FiltersDatabaseRepositoryImpl_Factory create(Provider<Logger> provider, Provider<Context> provider2, Provider<RequestArbiter> provider3) {
        return new FiltersDatabaseRepositoryImpl_Factory(provider, provider2, provider3);
    }

    public static FiltersDatabaseRepositoryImpl newInstance(Logger logger, Context context, RequestArbiter requestArbiter) {
        return new FiltersDatabaseRepositoryImpl(logger, context, requestArbiter);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public FiltersDatabaseRepositoryImpl get() {
        return newInstance(this.loggerProvider.get(), this.appContextProvider.get(), this.requestArbiterProvider.get());
    }
}
