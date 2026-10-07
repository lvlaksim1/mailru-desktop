package ru.mail.authorizationsdk.di.modules.feature;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import retrofit2.Retrofit;
import ru.mail.authorizationsdk.data.pikachucaptcha.PikachuCaptchaApi;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"ru.mail.authorizationsdk.di.modules.feature.PikachuCaptchaRetrofit"})
public final class PikachuCaptchaModule_ProvidePikachuCaptchaApiFactory implements Factory<PikachuCaptchaApi> {
    private final PikachuCaptchaModule module;
    private final Provider<Retrofit> retrofitProvider;

    private PikachuCaptchaModule_ProvidePikachuCaptchaApiFactory(PikachuCaptchaModule pikachuCaptchaModule, Provider<Retrofit> provider) {
        this.module = pikachuCaptchaModule;
        this.retrofitProvider = provider;
    }

    public static PikachuCaptchaModule_ProvidePikachuCaptchaApiFactory create(PikachuCaptchaModule pikachuCaptchaModule, Provider<Retrofit> provider) {
        return new PikachuCaptchaModule_ProvidePikachuCaptchaApiFactory(pikachuCaptchaModule, provider);
    }

    public static PikachuCaptchaApi providePikachuCaptchaApi(PikachuCaptchaModule pikachuCaptchaModule, Retrofit retrofit) {
        return (PikachuCaptchaApi) Preconditions.checkNotNullFromProvides(pikachuCaptchaModule.providePikachuCaptchaApi(retrofit));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PikachuCaptchaApi get() {
        return providePikachuCaptchaApi(this.module, this.retrofitProvider.get());
    }
}
