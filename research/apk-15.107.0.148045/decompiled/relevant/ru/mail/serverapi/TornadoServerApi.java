package ru.mail.serverapi;

import android.content.Context;
import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.NameValuePair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.network.NoAuthInfo;
import ru.mail.network.api.RequestBodyCreator;
import ru.mail.network.api.ServerApi;
import ru.mail.network.api.SessionProvider;
import ru.mail.network.api.SignCreator;
import ru.mail.network.api.TornadoRequestBodyCreator;
import ru.mail.network.api.TornadoSignCreator;
import ru.mail.network.api.TornadoUriCreator;
import ru.mail.network.hostprovider.HostInfoProvider;
import ru.mail.network.processor.AuthResult;
import ru.mail.network.processor.UriCreator;
import ru.mail.network.response.ResponseProcessor;
import ru.mail.network.response.TornadoResponseProcessorDelegate;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002BM\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010 \u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\"0!H\u0016J\b\u0010#\u001a\u00020$H\u0016J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030&H\u0016J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020,H\u0016J$\u0010-\u001a\u00020.2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u00112\f\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u0011H\u0016J\n\u00101\u001a\u0004\u0018\u00010\u0007H\u0016R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lru/mail/serverapi/TornadoServerApi;", "TResult", "Lru/mail/network/api/ServerApi;", "Lru/mail/network/NoAuthInfo;", "context", "Landroid/content/Context;", "login", "", "responseProcessorDelegate", "Lru/mail/network/response/TornadoResponseProcessorDelegate;", "platformInfo", "Lru/mail/serverapi/PlatformInfo;", "accountManagerWrapper", "Lru/mail/auth/AccountManagerWrapper;", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "pathSegments", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lru/mail/network/response/TornadoResponseProcessorDelegate;Lru/mail/serverapi/PlatformInfo;Lru/mail/auth/AccountManagerWrapper;Lru/mail/serverapi/AccountManagerSettings;Ljava/util/List;)V", "responseProcessor", "Lru/mail/network/response/TornadoResponseProcessor;", "signCreator", "Lru/mail/network/api/TornadoSignCreator;", "uriCreator", "Lru/mail/network/api/TornadoUriCreator;", "requestBodyCreator", "Lru/mail/network/api/TornadoRequestBodyCreator;", "sessionProvider", "Lru/mail/serverapi/TornadoSessionProvider;", "hostInfoProvider", "Lru/mail/serverapi/MailHostInfoProvider;", "getResponseProcessor", "Lru/mail/network/response/ResponseProcessor;", "Lru/mail/network/processor/AuthResult;", "getApiHostInfoProvider", "Lru/mail/network/hostprovider/HostInfoProvider;", "getSessionProvider", "Lru/mail/network/api/SessionProvider;", "getSignCreator", "Lru/mail/network/api/SignCreator;", "getUriCreator", "Lru/mail/network/processor/UriCreator;", "getRequestBodyCreator", "Lru/mail/network/api/RequestBodyCreator;", "getConfiguredUri", "Landroid/net/Uri;", "params", "Lorg/apache/http/NameValuePair;", "getUserAgentHeader", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TornadoServerApi<TResult> implements ServerApi<TResult, NoAuthInfo> {

    @NotNull
    private final MailHostInfoProvider hostInfoProvider;

    @NotNull
    private final TornadoRequestBodyCreator requestBodyCreator;

    @NotNull
    private final ru.mail.network.response.TornadoResponseProcessor<TResult> responseProcessor;

    @NotNull
    private final TornadoSessionProvider sessionProvider;

    @NotNull
    private final TornadoSignCreator signCreator;

    @NotNull
    private final TornadoUriCreator uriCreator;

    public TornadoServerApi(@NotNull Context context, @Nullable String str, @NotNull TornadoResponseProcessorDelegate<TResult> responseProcessorDelegate, @NotNull PlatformInfo platformInfo, @NotNull AccountManagerWrapper accountManagerWrapper, @NotNull AccountManagerSettings accountManagerSettings, @NotNull List<String> pathSegments) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(responseProcessorDelegate, "responseProcessorDelegate");
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        Intrinsics.checkNotNullParameter(accountManagerWrapper, "accountManagerWrapper");
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        this.responseProcessor = new ru.mail.network.response.TornadoResponseProcessor<>(getSessionProvider(), responseProcessorDelegate);
        this.signCreator = new TornadoSignCreator();
        this.uriCreator = new TornadoUriCreator(false, 1, null);
        this.requestBodyCreator = new TornadoRequestBodyCreator(false, 1, null);
        this.sessionProvider = new TornadoSessionProvider(platformInfo, str, accountManagerWrapper, accountManagerSettings);
        this.hostInfoProvider = new MailHostInfoProvider(platformInfo, "new_mail_api", context, R.string.new_mail_api_default_scheme, R.string.new_mail_api_default_host, null, true, false, 128, null);
    }

    @Override // ru.mail.network.api.ServerApi
    @NotNull
    /* JADX INFO: renamed from: getApiHostInfoProvider */
    public HostInfoProvider getHostInfoProvider() {
        return this.hostInfoProvider;
    }

    @Override // ru.mail.network.api.ServerApi
    @NotNull
    public Uri getConfiguredUri(@NotNull List<String> pathSegments, @NotNull List<? extends NameValuePair> params) {
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        Intrinsics.checkNotNullParameter(params, "params");
        return this.uriCreator.createUri(pathSegments, params, this.hostInfoProvider, this.sessionProvider.getSessionInfo(), this.signCreator);
    }

    @Override // ru.mail.network.api.ServerApi
    @NotNull
    public RequestBodyCreator getRequestBodyCreator() {
        return this.requestBodyCreator;
    }

    @Override // ru.mail.network.api.ServerApi
    @NotNull
    public ResponseProcessor<AuthResult<TResult, NoAuthInfo>> getResponseProcessor() {
        return this.responseProcessor;
    }

    @Override // ru.mail.network.api.ServerApi
    @NotNull
    public SessionProvider<NoAuthInfo> getSessionProvider() {
        return this.sessionProvider;
    }

    @Override // ru.mail.network.api.ServerApi
    @NotNull
    public SignCreator getSignCreator() {
        return this.signCreator;
    }

    @Override // ru.mail.network.api.ServerApi
    @NotNull
    public UriCreator getUriCreator() {
        return this.uriCreator;
    }

    @Override // ru.mail.network.api.ServerApi
    @Nullable
    public String getUserAgentHeader() {
        return this.hostInfoProvider.getUserAgent();
    }
}
