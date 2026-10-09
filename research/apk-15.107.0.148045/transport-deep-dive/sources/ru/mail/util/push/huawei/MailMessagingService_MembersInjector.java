package ru.mail.util.push.huawei;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import ru.mail.arbiter.RequestArbiter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@DaggerGenerated
@QualifierMetadata
public final class MailMessagingService_MembersInjector implements MembersInjector<MailMessagingService> {
    private final Provider<RequestArbiter> requestArbiterProvider;

    private MailMessagingService_MembersInjector(Provider<RequestArbiter> provider) {
        this.requestArbiterProvider = provider;
    }

    public static MembersInjector<MailMessagingService> create(Provider<RequestArbiter> provider) {
        return new MailMessagingService_MembersInjector(provider);
    }

    @InjectedFieldSignature("ru.mail.util.push.huawei.MailMessagingService.requestArbiter")
    public static void injectRequestArbiter(MailMessagingService mailMessagingService, RequestArbiter requestArbiter) {
        mailMessagingService.requestArbiter = requestArbiter;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MailMessagingService mailMessagingService) {
        injectRequestArbiter(mailMessagingService, this.requestArbiterProvider.get());
    }
}
