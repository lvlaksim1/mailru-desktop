package ru.mail.dependencies;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.logic.cmd.socialbind.LudwigTokensApi;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetLudwigTokenUseCase;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"ru.mail.dependencies.VKIDRatBindPush"})
public final class SocialBindModule_ProvideGetLudwigTokenUseCaseFactory implements Factory<GetLudwigTokenUseCase> {
    private final Provider<ExecutorSelector> executorSelectorProvider;
    private final Provider<LudwigTokensApi> ludwigTokensApiProvider;

    private SocialBindModule_ProvideGetLudwigTokenUseCaseFactory(Provider<ExecutorSelector> provider, Provider<LudwigTokensApi> provider2) {
        this.executorSelectorProvider = provider;
        this.ludwigTokensApiProvider = provider2;
    }

    public static SocialBindModule_ProvideGetLudwigTokenUseCaseFactory create(Provider<ExecutorSelector> provider, Provider<LudwigTokensApi> provider2) {
        return new SocialBindModule_ProvideGetLudwigTokenUseCaseFactory(provider, provider2);
    }

    public static GetLudwigTokenUseCase provideGetLudwigTokenUseCase(ExecutorSelector executorSelector, LudwigTokensApi ludwigTokensApi) {
        return (GetLudwigTokenUseCase) Preconditions.checkNotNullFromProvides(SocialBindModule.INSTANCE.provideGetLudwigTokenUseCase(executorSelector, ludwigTokensApi));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public GetLudwigTokenUseCase get() {
        return provideGetLudwigTokenUseCase(this.executorSelectorProvider.get(), this.ludwigTokensApiProvider.get());
    }
}
