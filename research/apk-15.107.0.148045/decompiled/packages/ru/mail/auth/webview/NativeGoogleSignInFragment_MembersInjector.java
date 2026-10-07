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
public final class NativeGoogleSignInFragment_MembersInjector implements MembersInjector<NativeGoogleSignInFragment> {
    private final Provider<Analytics> analyticsProvider;

    private NativeGoogleSignInFragment_MembersInjector(Provider<Analytics> provider) {
        this.analyticsProvider = provider;
    }

    public static MembersInjector<NativeGoogleSignInFragment> create(Provider<Analytics> provider) {
        return new NativeGoogleSignInFragment_MembersInjector(provider);
    }

    @InjectedFieldSignature("ru.mail.auth.webview.NativeGoogleSignInFragment.analytics")
    public static void injectAnalytics(NativeGoogleSignInFragment nativeGoogleSignInFragment, Analytics analytics) {
        nativeGoogleSignInFragment.analytics = analytics;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NativeGoogleSignInFragment nativeGoogleSignInFragment) {
        injectAnalytics(nativeGoogleSignInFragment, this.analyticsProvider.get());
    }
}
