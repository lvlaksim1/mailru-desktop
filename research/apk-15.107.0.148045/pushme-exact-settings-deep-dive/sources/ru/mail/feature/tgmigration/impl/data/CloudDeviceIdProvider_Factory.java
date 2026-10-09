package ru.mail.feature.tgmigration.impl.data;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"dagger.hilt.android.qualifiers.ApplicationContext"})
public final class CloudDeviceIdProvider_Factory implements Factory<CloudDeviceIdProvider> {
    private final Provider<Context> contextProvider;

    private CloudDeviceIdProvider_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static CloudDeviceIdProvider_Factory create(Provider<Context> provider) {
        return new CloudDeviceIdProvider_Factory(provider);
    }

    public static CloudDeviceIdProvider newInstance(Context context) {
        return new CloudDeviceIdProvider(context);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public CloudDeviceIdProvider get() {
        return newInstance(this.contextProvider.get());
    }
}
