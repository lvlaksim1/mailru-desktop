package ru.mail.serverapi.retrofit.session;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.serverapi.AccountManagerSettings;
import ru.mail.serverapi.BrowserCookieSetter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@ScopeMetadata
@DaggerGenerated
@QualifierMetadata({"dagger.hilt.android.qualifiers.ApplicationContext"})
public final class TokenRepository_Factory implements Factory<TokenRepository> {
    private final Provider<AccountManagerSettings> accountManagerSettingsProvider;
    private final Provider<BrowserCookieSetter> browserCookieSetterProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ExecutorSelector> executorProvider;
    private final Provider<NoAuthInfoCreator> noAuthInfoCreatorProvider;

    private TokenRepository_Factory(Provider<Context> provider, Provider<NoAuthInfoCreator> provider2, Provider<AccountManagerSettings> provider3, Provider<ExecutorSelector> provider4, Provider<BrowserCookieSetter> provider5) {
        this.contextProvider = provider;
        this.noAuthInfoCreatorProvider = provider2;
        this.accountManagerSettingsProvider = provider3;
        this.executorProvider = provider4;
        this.browserCookieSetterProvider = provider5;
    }

    public static TokenRepository_Factory create(Provider<Context> provider, Provider<NoAuthInfoCreator> provider2, Provider<AccountManagerSettings> provider3, Provider<ExecutorSelector> provider4, Provider<BrowserCookieSetter> provider5) {
        return new TokenRepository_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static TokenRepository newInstance(Context context, NoAuthInfoCreator noAuthInfoCreator, AccountManagerSettings accountManagerSettings, ExecutorSelector executorSelector, BrowserCookieSetter browserCookieSetter) {
        return new TokenRepository(context, noAuthInfoCreator, accountManagerSettings, executorSelector, browserCookieSetter);
    }

    @Override // javax.inject.Provider, jakarta.inject.Provider
    public TokenRepository get() {
        return newInstance(this.contextProvider.get(), this.noAuthInfoCreatorProvider.get(), this.accountManagerSettingsProvider.get(), this.executorProvider.get(), this.browserCookieSetterProvider.get());
    }
}
