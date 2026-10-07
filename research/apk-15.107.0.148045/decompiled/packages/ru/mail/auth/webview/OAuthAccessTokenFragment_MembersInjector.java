package ru.mail.auth.webview;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import ru.mail.auth.Analytics;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@DaggerGenerated
@QualifierMetadata
public final class OAuthAccessTokenFragment_MembersInjector implements MembersInjector<OAuthAccessTokenFragment> {
    private final Provider<Analytics> analyticsProvider;

    private OAuthAccessTokenFragment_MembersInjector(Provider<Analytics> provider) {
        this.analyticsProvider = provider;
    }

    public static MembersInjector<OAuthAccessTokenFragment> create(Provider<Analytics> provider) {
        return new OAuthAccessTokenFragment_MembersInjector(provider);
    }

    @InjectedFieldSignature("ru.mail.auth.webview.OAuthAccessTokenFragment.analytics")
    public static void injectAnalytics(OAuthAccessTokenFragment oAuthAccessTokenFragment, Analytics analytics) {
        oAuthAccessTokenFragment.analytics = analytics;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OAuthAccessTokenFragment oAuthAccessTokenFragment) {
        injectAnalytics(oAuthAccessTokenFragment, this.analyticsProvider.get());
    }
}
