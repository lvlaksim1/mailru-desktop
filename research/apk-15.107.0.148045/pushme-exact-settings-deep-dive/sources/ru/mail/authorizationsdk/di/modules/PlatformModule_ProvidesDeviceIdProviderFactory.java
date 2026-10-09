package ru.mail.authorizationsdk.di.modules;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.network.utils.device.deviceid.DeviceIdProvider;
import ru.mail.network.utils.device.deviceid.accountprovider.GoogleAccountProvider;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata
public final class PlatformModule_ProvidesDeviceIdProviderFactory implements Factory<DeviceIdProvider> {
    private final Provider<Context> contextProvider;
    private final Provider<GoogleAccountProvider> googleAccountProvider;
    private final Provider<Logger> loggerProvider;
    private final PlatformModule module;

    private PlatformModule_ProvidesDeviceIdProviderFactory(PlatformModule platformModule, Provider<Logger> provider, Provider<Context> provider2, Provider<GoogleAccountProvider> provider3) {
        this.module = platformModule;
        this.loggerProvider = provider;
        this.contextProvider = provider2;
        this.googleAccountProvider = provider3;
    }

    public static PlatformModule_ProvidesDeviceIdProviderFactory create(PlatformModule platformModule, Provider<Logger> provider, Provider<Context> provider2, Provider<GoogleAccountProvider> provider3) {
        return new PlatformModule_ProvidesDeviceIdProviderFactory(platformModule, provider, provider2, provider3);
    }

    public static DeviceIdProvider providesDeviceIdProvider(PlatformModule platformModule, Logger logger, Context context, GoogleAccountProvider googleAccountProvider) {
        return (DeviceIdProvider) Preconditions.checkNotNullFromProvides(platformModule.providesDeviceIdProvider(logger, context, googleAccountProvider));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DeviceIdProvider get() {
        return providesDeviceIdProvider(this.module, this.loggerProvider.get(), this.contextProvider.get(), this.googleAccountProvider.get());
    }
}
