package ru.mail.dependencies;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.config.Configuration;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.AddSocialBindUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.BindVKIDNewFlowUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetLudwigTokenUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetVKPreflightUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.VerifyPasswordCheckUseCase;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"dagger.hilt.android.qualifiers.ApplicationContext"})
public final class AuthViewModelModule_ProvideBindVKIDNewFlowUseCaseFactory implements Factory<BindVKIDNewFlowUseCase> {
    private final Provider<AddSocialBindUseCase> addSocialBindUseCaseProvider;
    private final Provider<Configuration> configurationProvider;
    private final Provider<Context> contextProvider;
    private final Provider<GetLudwigTokenUseCase> getLudwigTokenUseCaseProvider;
    private final Provider<GetVKPreflightUseCase> getVKPreflightUseCaseProvider;
    private final Provider<VerifyPasswordCheckUseCase> verifyPasswordCheckUseCaseProvider;

    private AuthViewModelModule_ProvideBindVKIDNewFlowUseCaseFactory(Provider<Context> provider, Provider<Configuration> provider2, Provider<GetLudwigTokenUseCase> provider3, Provider<VerifyPasswordCheckUseCase> provider4, Provider<GetVKPreflightUseCase> provider5, Provider<AddSocialBindUseCase> provider6) {
        this.contextProvider = provider;
        this.configurationProvider = provider2;
        this.getLudwigTokenUseCaseProvider = provider3;
        this.verifyPasswordCheckUseCaseProvider = provider4;
        this.getVKPreflightUseCaseProvider = provider5;
        this.addSocialBindUseCaseProvider = provider6;
    }

    public static AuthViewModelModule_ProvideBindVKIDNewFlowUseCaseFactory create(Provider<Context> provider, Provider<Configuration> provider2, Provider<GetLudwigTokenUseCase> provider3, Provider<VerifyPasswordCheckUseCase> provider4, Provider<GetVKPreflightUseCase> provider5, Provider<AddSocialBindUseCase> provider6) {
        return new AuthViewModelModule_ProvideBindVKIDNewFlowUseCaseFactory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static BindVKIDNewFlowUseCase provideBindVKIDNewFlowUseCase(Context context, Configuration configuration, GetLudwigTokenUseCase getLudwigTokenUseCase, VerifyPasswordCheckUseCase verifyPasswordCheckUseCase, GetVKPreflightUseCase getVKPreflightUseCase, AddSocialBindUseCase addSocialBindUseCase) {
        return (BindVKIDNewFlowUseCase) Preconditions.checkNotNullFromProvides(AuthViewModelModule.INSTANCE.provideBindVKIDNewFlowUseCase(context, configuration, getLudwigTokenUseCase, verifyPasswordCheckUseCase, getVKPreflightUseCase, addSocialBindUseCase));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public BindVKIDNewFlowUseCase get() {
        return provideBindVKIDNewFlowUseCase(this.contextProvider.get(), this.configurationProvider.get(), this.getLudwigTokenUseCaseProvider.get(), this.verifyPasswordCheckUseCaseProvider.get(), this.getVKPreflightUseCaseProvider.get(), this.addSocialBindUseCaseProvider.get());
    }
}
