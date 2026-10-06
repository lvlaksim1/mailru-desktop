package ru.mail.data.cmd.server.calls;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.toggle.anonymous.AnonymousFeatureManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ads.core.impl.analytics.SessionParamsProviderImpl;
import ru.mail.auth.request.AccountInfo;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.calleridentification.CallsRepository;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Formats;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\fH\u0014JP\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0016\u0010\u0011\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00130\u00122&\u0010\u0014\u001a\"0\u0015R\u001e\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00030\u00030\u0013H\u0014J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0014¨\u0006\u0019"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsAnonymTokenRequest;", "Lru/mail/authorizesdk/data/request/common/SingleRequest;", "Lru/mail/data/cmd/server/calls/CallsAnonymTokenRequest$Params;", "Lru/mail/calleridentification/CallsRepository$RequestAnonTokenResult;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/calls/CallsAnonymTokenRequest$Params;)V", "needPlatformParams", "", "getHostProvider", "Lru/mail/network/HostProvider;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "serverApi", "Lru/mail/network/ServerApi;", "Lru/mail/network/NetworkCommand;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "kotlin.jvm.PlatformType", "onPostExecuteRequest", "Params", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", SessionParamsProviderImpl.PARAM_SESSION_ID, AnonymousFeatureManager.DEFAULT_ANONYMOUS_STORAGE_NAME})
public final class CallsAnonymTokenRequest extends SingleRequest<Params, CallsRepository.RequestAnonTokenResult> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0014¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsAnonymTokenRequest$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "login", "", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Ljava/lang/String;Lru/mail/serverapi/FolderState;)V", "needAppendActMode", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandBaseParams {
        public static final int $stable = 8;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String login, @Nullable FolderState folderState) {
            super(new AccountInfo(login, false, 2, null), folderState);
            Intrinsics.checkNotNullParameter(login, "login");
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallsAnonymTokenRequest(@NotNull Context context, @NotNull Params params) {
        super(context, params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        HostProvider hostProvider = super.getHostProvider();
        Intrinsics.checkNotNullExpressionValue(hostProvider, "getHostProvider(...)");
        Context applicationContext = getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return new CallsHostProvider(hostProvider, applicationContext);
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@NotNull NetworkCommand.Response resp, @NotNull ServerApi<? extends NetworkCommand<?, ?>> serverApi, @NotNull NetworkCommand<Params, CallsRepository.RequestAnonTokenResult>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        Intrinsics.checkNotNullParameter(serverApi, "serverApi");
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        return new CallsResponseProcessor(resp, customDelegate);
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean needPlatformParams() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public CallsRepository.RequestAnonTokenResult onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        String strExtractCookie = SingleRequest.extractCookie(getNetworkService(), CallsAnonymAuthStrategy.ANON_COOKIE_NAME, Formats.newUrlFormat(CallsAnonymAuthStrategy.ANON_COOKIE_NAME));
        if (strExtractCookie == null || strExtractCookie.length() == 0) {
            throw new NetworkCommand.PostExecuteException("No anonymous token received");
        }
        return new CallsRepository.RequestAnonTokenResult(strExtractCookie);
    }
}
