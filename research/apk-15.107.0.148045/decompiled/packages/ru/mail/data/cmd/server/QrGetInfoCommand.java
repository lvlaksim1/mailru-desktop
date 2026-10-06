package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailapp.R;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u001f B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0012\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014JV\u0010\u0017\u001a\u00020\u00182\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0018\u0010\u0019\u001a\u0014\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u001b\u0018\u00010\u001a2(\u0010\u001c\u001a$\u0018\u00010\u001dR\u001e\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00030\u00030\u001bH\u0014¨\u0006!"}, d2 = {"Lru/mail/data/cmd/server/QrGetInfoCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/QrGetInfoCommand$Params;", "Lru/mail/data/cmd/server/QrGetInfoCommand$Result;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/QrGetInfoCommand$Params;Z)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onSetupSessionInUrl", "", "url", "Landroid/net/Uri$Builder;", "setUpSession", "networkService", "Lru/mail/network/service/NetworkService;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "serverApi", "Lru/mail/network/ServerApi;", "Lru/mail/network/NetworkCommand;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "kotlin.jvm.PlatformType", "Result", "Params", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHost = R.string.account_default_host, defScheme = R.string.account_default_scheme, prefKey = "account")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "auth", "qr", "get"})
public final class QrGetInfoCommand extends PostServerRequest<Params, Result> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0014R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/server/QrGetInfoCommand$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "autogenToken", "", "<init>", "(Ljava/lang/String;)V", "getAutogenToken", "()Ljava/lang/String;", "needAppendActMode", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandBaseParams {
        public static final int $stable = 8;

        @Param(method = HttpMethod.POST, name = "token")
        @NotNull
        private final String autogenToken;

        public Params(@NotNull String autogenToken) {
            Intrinsics.checkNotNullParameter(autogenToken, "autogenToken");
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

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/data/cmd/server/QrGetInfoCommand$Result;", "", "login", "", "page", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "getPage", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Result {
        public static final int $stable = 0;

        @NotNull
        private final String login;

        @NotNull
        private final String page;

        public Result(@NotNull String login, @NotNull String page) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(page, "page");
            this.login = login;
            this.page = page;
        }

        public static /* synthetic */ Result copy$default(Result result, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = result.login;
            }
            if ((i10 & 2) != 0) {
                str2 = result.page;
            }
            return result.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPage() {
            return this.page;
        }

        @NotNull
        public final Result copy(@NotNull String login, @NotNull String page) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(page, "page");
            return new Result(login, page);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.login, result.login) && Intrinsics.areEqual(this.page, result.page);
        }

        @NotNull
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        public final String getPage() {
            return this.page;
        }

        public int hashCode() {
            return (this.login.hashCode() * 31) + this.page.hashCode();
        }

        @NotNull
        public String toString() {
            return "Result(login=" + this.login + ", page=" + this.page + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QrGetInfoCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
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
    protected ResponseProcessor getResponseProcessor(@Nullable NetworkCommand.Response resp, @Nullable ServerApi<? extends NetworkCommand<?, ?>> serverApi, @Nullable NetworkCommand<Params, Result>.NetworkCommandBaseDelegate customDelegate) {
        return new TornadoResponseProcessor(resp, customDelegate) { // from class: ru.mail.data.cmd.server.QrGetInfoCommand.getResponseProcessor.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor
            protected CommandStatus<?> processResponse(int responseStatus) {
                if (responseStatus != 200) {
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
    public Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        String string = getContext().getResources().getString(R.string.qr_fallback_scheme);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getContext().getResources().getString(R.string.qr_fallback_host);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        try {
            JSONObject jSONObject = new JSONObject(resp.getRespString()).getJSONObject("body");
            String strOptString = jSONObject.optString("login");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            String strOptString2 = jSONObject.optString("page", string + "://" + string2);
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
            return new Result(strOptString, strOptString2);
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(@Nullable Uri.Builder url) {
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(@Nullable NetworkService networkService) {
    }
}
