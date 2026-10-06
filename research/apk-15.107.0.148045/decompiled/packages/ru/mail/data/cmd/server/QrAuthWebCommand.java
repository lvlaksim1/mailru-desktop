package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailapp.R;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0018B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0014JV\u0010\u0010\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0018\u0010\u0012\u001a\u0014\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0014\u0018\u00010\u00132(\u0010\u0015\u001a$\u0018\u00010\u0016R\u001e\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00030\u00030\u0014H\u0014¨\u0006\u0019"}, d2 = {"Lru/mail/data/cmd/server/QrAuthWebCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/QrAuthWebCommand$Params;", "", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/QrAuthWebCommand$Params;Z)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "serverApi", "Lru/mail/network/ServerApi;", "Lru/mail/network/NetworkCommand;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "kotlin.jvm.PlatformType", "Params", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHost = R.string.account_default_host, defScheme = R.string.account_default_scheme, prefKey = "account")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "auth", "qr", "allow"})
public final class QrAuthWebCommand extends PostServerRequest<Params, String> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0014R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lru/mail/data/cmd/server/QrAuthWebCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "autogenToken", "", "login", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getAutogenToken", "()Ljava/lang/String;", "needAppendActMode", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @Param(method = HttpMethod.POST, name = "token")
        @NotNull
        private final String autogenToken;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String autogenToken, @NotNull String login) {
            super(new AccountInfo(login, false, 2, null), null);
            Intrinsics.checkNotNullParameter(autogenToken, "autogenToken");
            Intrinsics.checkNotNullParameter(login, "login");
            this.autogenToken = autogenToken;
        }

        @NotNull
        public final String getAutogenToken() {
            return this.autogenToken;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QrAuthWebCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@Nullable NetworkCommand.Response resp, @Nullable ServerApi<? extends NetworkCommand<?, ?>> serverApi, @Nullable NetworkCommand<Params, String>.NetworkCommandBaseDelegate customDelegate) {
        return new TornadoResponseProcessor(resp, customDelegate) { // from class: ru.mail.data.cmd.server.QrAuthWebCommand.getResponseProcessor.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor
            protected CommandStatus<?> processResponse(int responseStatus) {
                if (responseStatus == 404) {
                    return new MailCommandStatus.QR_TOKEN_NOT_FOUND();
                }
                CommandStatus<?> commandStatusProcessResponse = super.processResponse(responseStatus);
                Intrinsics.checkNotNullExpressionValue(commandStatusProcessResponse, "processResponse(...)");
                return commandStatusProcessResponse;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public String onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            String string = new JSONObject(resp.getRespString()).getJSONObject("body").getString("login");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        } catch (Exception unused) {
            throw new NetworkCommand.PostExecuteException();
        }
    }
}
