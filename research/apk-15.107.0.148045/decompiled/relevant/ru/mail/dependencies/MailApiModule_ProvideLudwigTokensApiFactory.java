package ru.mail.dependencies;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import retrofit2.Retrofit;
import ru.mail.logic.cmd.socialbind.LudwigTokensApi;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata({"ru.mail.network.qualifier.MailApiRetrofit"})
public final class MailApiModule_ProvideLudwigTokensApiFactory implements Factory<LudwigTokensApi> {
    private final Provider<Retrofit> retrofitProvider;

    private MailApiModule_ProvideLudwigTokensApiFactory(Provider<Retrofit> provider) {
        this.retrofitProvider = provider;
    }

    public static MailApiModule_ProvideLudwigTokensApiFactory create(Provider<Retrofit> provider) {
        return new MailApiModule_ProvideLudwigTokensApiFactory(provider);
    }

    public static LudwigTokensApi provideLudwigTokensApi(Retrofit retrofit) {
        return (LudwigTokensApi) Preconditions.checkNotNullFromProvides(MailApiModule.INSTANCE.provideLudwigTokensApi(retrofit));
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public LudwigTokensApi get() {
        return provideLudwigTokensApi(this.retrofitProvider.get());
    }
}
