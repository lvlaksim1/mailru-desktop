package ru.mail.logic.auth;

import android.content.Context;
import android.text.TextUtils;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.auth.request.AuthType;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0014B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014J(\u0010\u000e\u001a\"0\u000fR\u001e\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00030\u00030\u0010H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014¨\u0006\u0015"}, d2 = {"Lru/mail/logic/auth/ChangeAuthTypeCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/logic/auth/ChangeAuthTypeCommand$Params;", "Lru/mail/auth/request/AuthType;", "context", "Landroid/content/Context;", "params", "usePostParamsOnly", "", "<init>", "(Landroid/content/Context;Lru/mail/logic/auth/ChangeAuthTypeCommand$Params;Z)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getCustomDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "Params", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHostStrRes = "string/account_default_host", defSchemeStrRes = "string/account_default_scheme", prefKey = "account")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "pushauth", "method", "set"})
public final class ChangeAuthTypeCommand extends PostServerRequest<Params, AuthType> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014R\u0010\u0010\u0004\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/logic/auth/ChangeAuthTypeCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "login", "", "authType", "Lru/mail/auth/request/AuthType;", "<init>", "(Ljava/lang/String;Lru/mail/auth/request/AuthType;)V", "userLogin", "needAppendEmail", "", "needAppendActMode", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @Param(method = HttpMethod.POST, name = "method")
        @NotNull
        private final AuthType authType;

        @Param(method = HttpMethod.POST, name = "login")
        @NotNull
        private final String userLogin;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String login, @NotNull AuthType authType) {
            super(new AccountInfo(login, false, 2, null), null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(authType, "authType");
            this.authType = authType;
            this.userLogin = login;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }

        @Override // ru.mail.serverapi.ServerCommandEmailParams, ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendEmail() {
            return false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChangeAuthTypeCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected NetworkCommand<Params, AuthType>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, AuthType>.TornadoDelegate(this) { // from class: ru.mail.logic.auth.ChangeAuthTypeCommand.getCustomDelegate.1
            {
                super();
            }

            @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onUnauthorized(String reason) {
                Intrinsics.checkNotNullParameter(reason, "reason");
                if (TextUtils.equals(reason, "no auth")) {
                    CommandStatus<?> commandStatusOnUnauthorized = super.onUnauthorized("user");
                    Intrinsics.checkNotNullExpressionValue(commandStatusOnUnauthorized, "onUnauthorized(...)");
                    return commandStatusOnUnauthorized;
                }
                CommandStatus<?> commandStatusOnUnauthorized2 = super.onUnauthorized(reason);
                Intrinsics.checkNotNullExpressionValue(commandStatusOnUnauthorized2, "onUnauthorized(...)");
                return commandStatusOnUnauthorized2;
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public AuthType onPostExecuteRequest(@NotNull NetworkCommand.Response resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        AuthType authTypeFrom = AuthType.from(new JSONObject(resp.getRespString()).getJSONObject("body").getString("method"));
        Intrinsics.checkNotNullExpressionValue(authTypeFrom, "from(...)");
        return authTypeFrom;
    }
}
