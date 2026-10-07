package ru.mail.authorizationsdk.di.modules.feature;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.android_utils.wrapper.Resources;
import ru.mail.authorizationsdk.data.pikachucaptcha.PikachuCaptchaApi;
import ru.mail.authorizationsdk.domain.usecase.pikachu.PikachuUseCase;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata
public final class PikachuCaptchaModule_ProvidePikachuUseCaseFactory implements Factory<PikachuUseCase> {
    private final PikachuCaptchaModule module;
    private final Provider<PikachuCaptchaApi> pikachuCaptchaApiProvider;
    private final Provider<Resources> resourcesProvider;

    private PikachuCaptchaModule_ProvidePikachuUseCaseFactory(PikachuCaptchaModule pikachuCaptchaModule, Provider<Resources> provider, Provider<PikachuCaptchaApi> provider2) {
        this.module = pikachuCaptchaModule;
        this.resourcesProvider = provider;
        this.pikachuCaptchaApiProvider = provider2;
    }

    public static PikachuCaptchaModule_ProvidePikachuUseCaseFactory create(PikachuCaptchaModule pikachuCaptchaModule, Provider<Resources> provider, Provider<PikachuCaptchaApi> provider2) {
        return new PikachuCaptchaModule_ProvidePikachuUseCaseFactory(pikachuCaptchaModule, provider, provider2);
    }

    public static PikachuUseCase providePikachuUseCase(PikachuCaptchaModule pikachuCaptchaModule, Resources resources, PikachuCaptchaApi pikachuCaptchaApi) {
        return (PikachuUseCase) Preconditions.checkNotNullFromProvides(pikachuCaptchaModule.providePikachuUseCase(resources, pikachuCaptchaApi));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public PikachuUseCase get() {
        return providePikachuUseCase(this.module, this.resourcesProvider.get(), this.pikachuCaptchaApiProvider.get());
    }
}
