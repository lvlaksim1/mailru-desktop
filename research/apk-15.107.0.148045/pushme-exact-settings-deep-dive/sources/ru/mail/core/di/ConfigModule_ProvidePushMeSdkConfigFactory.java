package ru.mail.core.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.config.ConfigRetriever;
import ru.mail.config.section.PushMeSdkDto;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@ScopeMetadata("dagger.Reusable")
@DaggerGenerated
@QualifierMetadata
public final class ConfigModule_ProvidePushMeSdkConfigFactory implements Factory<PushMeSdkDto> {
    private final Provider<ConfigRetriever> configRetrieverProvider;

    private ConfigModule_ProvidePushMeSdkConfigFactory(Provider<ConfigRetriever> provider) {
        this.configRetrieverProvider = provider;
    }

    public static ConfigModule_ProvidePushMeSdkConfigFactory create(Provider<ConfigRetriever> provider) {
        return new ConfigModule_ProvidePushMeSdkConfigFactory(provider);
    }

    public static PushMeSdkDto providePushMeSdkConfig(ConfigRetriever configRetriever) {
        return (PushMeSdkDto) Preconditions.checkNotNullFromProvides(ConfigModule.INSTANCE.providePushMeSdkConfig(configRetriever));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PushMeSdkDto get() {
        return providePushMeSdkConfig(this.configRetrieverProvider.get());
    }
}
