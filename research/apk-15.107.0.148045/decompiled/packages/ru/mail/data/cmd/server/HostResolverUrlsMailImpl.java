package ru.mail.data.cmd.server;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lru/mail/data/cmd/server/HostResolverUrlsMailImpl;", "Lru/mail/data/cmd/server/HostResolverUrls;", "domain", "", "<init>", "(Ljava/lang/String;)V", "getDomain", "()Ljava/lang/String;", "discoveryHostUrl", "discoveryHostSubDomainUrl", "subDomainForDnsResolve", "subdomainForMyTeamConfig", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HostResolverUrlsMailImpl implements HostResolverUrls {
    public static final int $stable = 0;

    @NotNull
    private final String domain;

    public HostResolverUrlsMailImpl(@NotNull String domain) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        this.domain = domain;
    }

    @Override // ru.mail.data.cmd.server.HostResolverUrls
    @NotNull
    public String discoveryHostSubDomainUrl() {
        return "https://e." + this.domain + "/discovery_host";
    }

    @Override // ru.mail.data.cmd.server.HostResolverUrls
    @NotNull
    public String discoveryHostUrl() {
        return "https://" + this.domain + "/discovery_host";
    }

    @NotNull
    public final String getDomain() {
        return this.domain;
    }

    @Override // ru.mail.data.cmd.server.HostResolverUrls
    @NotNull
    public String subDomainForDnsResolve() {
        return "e." + this.domain;
    }

    @Override // ru.mail.data.cmd.server.HostResolverUrls
    @NotNull
    public String subdomainForMyTeamConfig() {
        return "u." + this.domain;
    }
}
