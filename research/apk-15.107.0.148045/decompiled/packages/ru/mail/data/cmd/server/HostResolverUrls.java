package ru.mail.data.cmd.server;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/data/cmd/server/HostResolverUrls;", "", "discoveryHostUrl", "", "discoveryHostSubDomainUrl", "subDomainForDnsResolve", "subdomainForMyTeamConfig", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface HostResolverUrls {
    @NotNull
    String discoveryHostSubDomainUrl();

    @NotNull
    String discoveryHostUrl();

    @NotNull
    String subDomainForDnsResolve();

    @NotNull
    String subdomainForMyTeamConfig();
}
