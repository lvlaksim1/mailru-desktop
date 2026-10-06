package ru.mail.hosts;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.ads.config.api.data.model.AdConfiguration;
import ru.mail.mails.R;
import ru.mail.network.HostProviderWrapper;
import ru.mail.sdk.MailSdk;
import ru.mail.vpn.detect.api.WhitelistProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u000f"}, d2 = {"Lru/mail/hosts/VpnWhitelistProviderImpl;", "Lru/mail/vpn/detect/api/WhitelistProvider;", "hostProviderWrapper", "Lru/mail/network/HostProviderWrapper;", "adConfig", "Lru/mail/ads/config/api/data/model/AdConfiguration;", "<init>", "(Lru/mail/network/HostProviderWrapper;Lru/mail/ads/config/api/data/model/AdConfiguration;)V", "hostsWhitelist", "", "", "getHostsWhitelist", "()Ljava/util/List;", "urlsWhitelist", "getUrlsWhitelist", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VpnWhitelistProviderImpl implements WhitelistProvider {
    public static final int $stable = 8;

    @NotNull
    private final List<String> hostsWhitelist;

    @NotNull
    private final List<String> urlsWhitelist;

    @Inject
    public VpnWhitelistProviderImpl(@NotNull HostProviderWrapper hostProviderWrapper, @NotNull AdConfiguration adConfig) {
        Intrinsics.checkNotNullParameter(hostProviderWrapper, "hostProviderWrapper");
        Intrinsics.checkNotNullParameter(adConfig, "adConfig");
        List<String> listMutableListOf = CollectionsKt.mutableListOf(hostProviderWrapper.getSchemeOrHost(R.string.push_default_host), hostProviderWrapper.getSchemeOrHost(ru.mail.Authenticator.R.string.swa_default_host), hostProviderWrapper.getSchemeOrHost(ru.mail.authorizationsdk.R.string.sdk_auth_mail_host), hostProviderWrapper.getSchemeOrHost(ru.mail.authorizationsdk.R.string.sdk_yandex_api_default_host), hostProviderWrapper.getSchemeOrHost(ru.mail.authorizationsdk.R.string.sdk_for_yahoo_token_server_host), hostProviderWrapper.getSchemeOrHost(ru.mail.authorizationsdk.R.string.sdk_outlook_default_host), hostProviderWrapper.getSchemeOrHost(ru.mail.Authenticator.R.string.google_api_default_host), MailSdk.INSTANCE.getMailSdkPushConfig().getPusherHost());
        if (!adConfig.getAdNetworkConfig().getRbBlockRequestIfVpnEnabled()) {
            listMutableListOf.add(hostProviderWrapper.getSchemeOrHost(R.string.rb_default_host));
        }
        this.hostsWhitelist = listMutableListOf;
        int i10 = R.string.auth_default_host;
        String str = hostProviderWrapper.getSchemeOrHost(i10) + "/cgi-bin/oauth2_ok_token";
        String str2 = hostProviderWrapper.getSchemeOrHost(i10) + "/cgi-bin/auth";
        String str3 = hostProviderWrapper.getSchemeOrHost(i10) + "/oauth2_google_token";
        String str4 = hostProviderWrapper.getSchemeOrHost(i10) + "/oauth2_yandex_token";
        String str5 = hostProviderWrapper.getSchemeOrHost(i10) + "/oauth2_outlook_token";
        String str6 = hostProviderWrapper.getSchemeOrHost(i10) + "/oauth2_yahoo_token";
        int i11 = R.string.new_mail_api_default_host;
        this.urlsWhitelist = CollectionsKt.listOf((Object[]) new String[]{str, str2, str3, str4, str5, str6, hostProviderWrapper.getSchemeOrHost(i11) + "/api/v1/omicron/get", hostProviderWrapper.getSchemeOrHost(i11) + "/api/v1/checkab/newmetrics"});
    }

    @Override // ru.mail.vpn.detect.api.WhitelistProvider
    @NotNull
    public List<String> getHostsWhitelist() {
        return this.hostsWhitelist;
    }

    @Override // ru.mail.vpn.detect.api.WhitelistProvider
    @NotNull
    public List<String> getUrlsWhitelist() {
        return this.urlsWhitelist;
    }
}
