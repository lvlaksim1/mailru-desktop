package ru.mail.filter.data;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.data.cache.FiltersCache;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class FiltersMemoryCacheRepositoryImpl_Factory implements Factory<FiltersMemoryCacheRepositoryImpl> {
    private final Provider<FiltersCache> cacheProvider;

    private FiltersMemoryCacheRepositoryImpl_Factory(Provider<FiltersCache> provider) {
        this.cacheProvider = provider;
    }

    public static FiltersMemoryCacheRepositoryImpl_Factory create(Provider<FiltersCache> provider) {
        return new FiltersMemoryCacheRepositoryImpl_Factory(provider);
    }

    public static FiltersMemoryCacheRepositoryImpl newInstance(FiltersCache filtersCache) {
        return new FiltersMemoryCacheRepositoryImpl(filtersCache);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public FiltersMemoryCacheRepositoryImpl get() {
        return newInstance(this.cacheProvider.get());
    }
}
