package ru.mail.data.cmd.server.calls;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.calleridentification.CallsAuthProvider;
import ru.mail.calleridentification.CallsRepository;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Formats;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0010B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsCsrfTokenRequest;", "Lru/mail/data/cmd/server/calls/CallsBaseGetRequest;", "Lru/mail/data/cmd/server/calls/CallsCsrfTokenRequest$Params;", "Lru/mail/calleridentification/CallsRepository$RequestCsrfTokenResult;", "context", "Landroid/content/Context;", "params", "authProvider", "Lru/mail/calleridentification/CallsAuthProvider;", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/calls/CallsCsrfTokenRequest$Params;Lru/mail/calleridentification/CallsAuthProvider;)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "extractCsrfToken", "", "Params", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "token"})
public final class CallsCsrfTokenRequest extends CallsBaseGetRequest<Params, CallsRepository.RequestCsrfTokenResult> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0014¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsCsrfTokenRequest$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "login", "", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Ljava/lang/String;Lru/mail/serverapi/FolderState;)V", "needAppendActMode", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
    public CallsCsrfTokenRequest(@NotNull Context context, @NotNull Params params, @NotNull CallsAuthProvider authProvider) {
        super(context, params, authProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(authProvider, "authProvider");
    }

    private final String extractCsrfToken() {
        String strExtractCookie = SingleRequest.extractCookie(getNetworkService(), CallsAnonymAuthStrategy.CSRF_COOKIE_NAME, Formats.newUrlFormat(CallsAnonymAuthStrategy.CSRF_COOKIE_NAME));
        Intrinsics.checkNotNullExpressionValue(strExtractCookie, "extractCookie(...)");
        return strExtractCookie;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public CallsRepository.RequestCsrfTokenResult onPostExecuteRequest(@NotNull NetworkCommand.Response resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        return new CallsRepository.RequestCsrfTokenResult(extractCsrfToken());
    }
}
