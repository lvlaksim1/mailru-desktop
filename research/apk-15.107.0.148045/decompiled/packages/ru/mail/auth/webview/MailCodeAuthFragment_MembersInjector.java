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
public final class MailCodeAuthFragment_MembersInjector implements MembersInjector<MailCodeAuthFragment> {
    private final Provider<Analytics> analyticsProvider;

    private MailCodeAuthFragment_MembersInjector(Provider<Analytics> provider) {
        this.analyticsProvider = provider;
    }

    public static MembersInjector<MailCodeAuthFragment> create(Provider<Analytics> provider) {
        return new MailCodeAuthFragment_MembersInjector(provider);
    }

    @InjectedFieldSignature("ru.mail.auth.webview.MailCodeAuthFragment.analytics")
    public static void injectAnalytics(MailCodeAuthFragment mailCodeAuthFragment, Analytics analytics) {
        mailCodeAuthFragment.analytics = analytics;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MailCodeAuthFragment mailCodeAuthFragment) {
        injectAnalytics(mailCodeAuthFragment, this.analyticsProvider.get());
    }
}
