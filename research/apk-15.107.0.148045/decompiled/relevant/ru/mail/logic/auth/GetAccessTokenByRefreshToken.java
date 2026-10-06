package ru.mail.logic.auth;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Calendar;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0003\u001b\u001c\u001dB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J:\u0010\u0010\u001a\u00020\u00112\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2&\u0010\u0012\u001a\"0\u0013R\u001e\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00070\u0007\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\r0\r0\u0014H\u0014J\u001a\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u000fH\u0002¨\u0006\u001e"}, d2 = {"Lru/mail/logic/auth/GetAccessTokenByRefreshToken;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand;", "app", "", "context", "Landroid/content/Context;", "params", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;", "usePostParams", "", "<init>", "(Ljava/lang/String;Landroid/content/Context;Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;Z)V", "onPostExecuteRequest", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result;", "resp", "Lru/mail/network/NetworkCommand$Response;", "customResponseProcessor", "Lru/mail/serverapi/TornadoResponseProcessor;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "isBadRefreshToken", "responseStatus", "", "errorCode", "response", "BadRefreshTokenError", "Params", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetAccessTokenByRefreshToken extends GetAuthCodeByAccessTokenCommand {

    @NotNull
    private static final String ACCESS_TOKEN_FIELD = "access_token";

    @NotNull
    private static final String ERROR_CODE_FIELD = "error_code";

    @NotNull
    private static final String EXPIRES_FIELD = "expires_in";
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("GetAccessTokenByRefreshToken");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lru/mail/logic/auth/GetAccessTokenByRefreshToken$BadRefreshTokenError;", "Lru/mail/mailbox/cmd/CommandStatus$ERROR;", "", "<init>", "()V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class BadRefreshTokenError extends CommandStatus.ERROR<Unit> {

        @NotNull
        public static final BadRefreshTokenError INSTANCE = new BadRefreshTokenError();
        public static final int $stable = 8;

        private BadRefreshTokenError() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0010\u0010\u0005\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lru/mail/logic/auth/GetAccessTokenByRefreshToken$Params;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;", "login", "", "clientId", "refreshToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends GetAuthCodeByAccessTokenCommand.Params {
        public static final int $stable = 8;

        @Param(method = HttpMethod.POST, name = "refresh_token")
        @NotNull
        private final String refreshToken;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String login, @NotNull String clientId, @NotNull String refreshToken) {
            super(null, login, false, "refresh_token", clientId, null, null, null, null, 480, null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            this.refreshToken = refreshToken;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetAccessTokenByRefreshToken(@NotNull String app, @NotNull Context context, @NotNull GetAuthCodeByAccessTokenCommand.Params params, boolean z10) {
        super(app, context, params, z10);
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    private final int errorCode(NetworkCommand.Response response) {
        String respString;
        try {
            if (response == null || (respString = response.getRespString()) == null) {
                respString = "";
            }
            return new JSONObject(respString).optInt("error_code", -1);
        } catch (JSONException e10) {
            LOG.e("Failed to exchange error code", e10);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isBadRefreshToken(int responseStatus, NetworkCommand.Response resp) {
        return responseStatus == 403 && errorCode(resp) == 6;
    }

    @Override // ru.mail.logic.auth.GetAuthCodeByAccessTokenCommand
    @NotNull
    protected TornadoResponseProcessor customResponseProcessor(@Nullable NetworkCommand.Response resp, @NotNull NetworkCommand<GetAuthCodeByAccessTokenCommand.Params, GetAuthCodeByAccessTokenCommand.Result>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        return new TornadoResponseProcessor(customDelegate, this) { // from class: ru.mail.logic.auth.GetAccessTokenByRefreshToken.customResponseProcessor.1
            final /* synthetic */ GetAccessTokenByRefreshToken this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.$resp, customDelegate);
                this.this$0 = this;
            }

            @Override // ru.mail.serverapi.TornadoResponseProcessor
            protected CommandStatus<?> processResponse(int responseStatus) {
                if (this.this$0.isBadRefreshToken(responseStatus, this.$resp)) {
                    return BadRefreshTokenError.INSTANCE;
                }
                CommandStatus<?> commandStatusProcessResponse = super.processResponse(responseStatus);
                Intrinsics.checkNotNull(commandStatusProcessResponse);
                return commandStatusProcessResponse;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public GetAuthCodeByAccessTokenCommand.Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(13, new JSONObject(resp.getRespString()).getInt("expires_in"));
            String string = new JSONObject(resp.getRespString()).getString("access_token");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            Date time = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "getTime(...)");
            return new GetAuthCodeByAccessTokenCommand.Result.RefreshResult(string, time);
        } catch (JSONException e10) {
            LOG.e("Failed to exchange result", e10);
            getAnalytics().logCodeExchangeResult("get_access_token_by_refresh_json_exception");
            getAnalytics().logCodeExchangeResult(((GetAuthCodeByAccessTokenCommand.Params) getParams()).getGrantType(), getApp(), "get_access_token_by_refresh_json_exception");
            throw new NetworkCommand.PostExecuteException("Json error", e10);
        }
    }
}
