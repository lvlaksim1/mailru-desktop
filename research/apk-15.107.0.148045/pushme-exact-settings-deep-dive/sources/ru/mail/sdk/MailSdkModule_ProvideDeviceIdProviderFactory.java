package ru.mail.sdk;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.deviceinfo.DeviceIdProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata({"dagger.hilt.android.qualifiers.ApplicationContext"})
public final class MailSdkModule_ProvideDeviceIdProviderFactory implements Factory<DeviceIdProvider> {
    private final Provider<AccountManagerWrapper> accountManagerWrapperProvider;
    private final Provider<Context> contextProvider;

    private MailSdkModule_ProvideDeviceIdProviderFactory(Provider<Context> provider, Provider<AccountManagerWrapper> provider2) {
        this.contextProvider = provider;
        this.accountManagerWrapperProvider = provider2;
    }

    public static MailSdkModule_ProvideDeviceIdProviderFactory create(Provider<Context> provider, Provider<AccountManagerWrapper> provider2) {
        return new MailSdkModule_ProvideDeviceIdProviderFactory(provider, provider2);
    }

    public static DeviceIdProvider provideDeviceIdProvider(Context context, AccountManagerWrapper accountManagerWrapper) {
        return (DeviceIdProvider) Preconditions.checkNotNullFromProvides(MailSdkModule.INSTANCE.provideDeviceIdProvider(context, accountManagerWrapper));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public DeviceIdProvider get() {
        return provideDeviceIdProvider(this.contextProvider.get(), this.accountManagerWrapperProvider.get());
    }
}
