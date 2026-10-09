package ru.mail.dependecies;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.utils.FirebaseInfoProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata({"dagger.hilt.android.qualifiers.ApplicationContext"})
public final class MailUtilsModule_ProvideFirebaseInfoProviderFactory implements Factory<FirebaseInfoProvider> {
    private final Provider<Context> contextProvider;

    private MailUtilsModule_ProvideFirebaseInfoProviderFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static MailUtilsModule_ProvideFirebaseInfoProviderFactory create(Provider<Context> provider) {
        return new MailUtilsModule_ProvideFirebaseInfoProviderFactory(provider);
    }

    public static FirebaseInfoProvider provideFirebaseInfoProvider(Context context) {
        return (FirebaseInfoProvider) Preconditions.checkNotNullFromProvides(MailUtilsModule.INSTANCE.provideFirebaseInfoProvider(context));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public FirebaseInfoProvider get() {
        return provideFirebaseInfoProvider(this.contextProvider.get());
    }
}
