package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import android.text.Html;
import androidx.annotation.Keep;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.Authenticator.R;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.request.AccountInfo;
import ru.mail.credentialsexchanger.data.network.MailOAuthRequest;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001d2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u001b\u001c\u001dB'\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\bH\u0014J(\u0010\u0017\u001a\"0\u0018R\u001e\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00030\u00030\u0019H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lru/mail/data/cmd/server/OAuthChildCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/OAuthChildCommand$Params;", "Lru/mail/data/cmd/server/OAuthChildCommand$Result;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "authUrl", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/OAuthChildCommand$Params;ZLjava/lang/String;)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPrepareUrl", "Landroid/net/Uri;", "builder", "Landroid/net/Uri$Builder;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "prepareUrlMigrateToPost", "getCustomDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "Params", "Result", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OAuthChildCommand extends ServerCommandBase<Params, Result> {

    @NotNull
    private static final String ACCESS_TOKEN = "access_token";

    @NotNull
    private static final String EXPIRES_IN = "expires_in";

    @NotNull
    private static final String REFRESH_TOKEN = "refresh_token";

    @NotNull
    private static final String STATUS = "status";

    @NotNull
    private final String authUrl;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("OAuthChildCommand");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u0004\u0018\u00010\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u00020\u00118\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00118\u0002X\u0083D¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00118\u0002X\u0083D¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00118\u0002X\u0083D¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/data/cmd/server/OAuthChildCommand$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "context", "Landroid/content/Context;", "authUrl", "", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "token", "childLogin", "getChildLogin", "()Ljava/lang/String;", "OAUTH2", "", "mMobileAppHeader", "MOBILE", "SIMPLE", "MOB_JSON", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandBaseParams {

        @NotNull
        private static final String PARAM_KEY_LOGIN = "Login";

        @NotNull
        private static final String PARAM_KEY_MOBILE = "mobile";

        @NotNull
        private static final String PARAM_KEY_MOBILE_HEADER = "X-Mobile-App";

        @NotNull
        private static final String PARAM_KEY_MOB_JSON = "mob_json";

        @NotNull
        private static final String PARAM_KEY_OAUTH2 = "oauth2";

        @NotNull
        private static final String PARAM_KEY_SIMPLE = "simple";

        @NotNull
        private static final String PARAM_KEY_TOKEN = "token";

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_KEY_MOBILE)
        private final int MOBILE;

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_KEY_MOB_JSON)
        private final int MOB_JSON;

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_KEY_OAUTH2)
        private final int OAUTH2;

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_KEY_SIMPLE)
        private final int SIMPLE;

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_KEY_LOGIN)
        @Nullable
        private final String childLogin;

        @Keep
        @Param(method = HttpMethod.HEADER_ADD, name = PARAM_KEY_MOBILE_HEADER)
        @NotNull
        private final String mMobileAppHeader;

        @Keep
        @Param(method = HttpMethod.GET, name = "token")
        @Nullable
        private final String token;
        public static final int $stable = 8;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull Context context, @NotNull String authUrl, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(authUrl, "authUrl");
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            this.OAUTH2 = AuthenticatorConfig.getInstance().isOAuthEnabled() ? 1 : 0;
            this.MOBILE = 1;
            this.SIMPLE = 1;
            this.MOB_JSON = 1;
            String string = context.getString(R.string.auth_csrf_header);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            this.mMobileAppHeader = string;
            String string2 = Html.fromHtml(authUrl, 63).toString();
            this.token = Uri.parse(string2).getQueryParameter("token");
            this.childLogin = Uri.parse(string2).getQueryParameter(PARAM_KEY_LOGIN);
        }

        @Nullable
        public final String getChildLogin() {
            return this.childLogin;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001b"}, d2 = {"Lru/mail/data/cmd/server/OAuthChildCommand$Result;", "", "login", "", "expires", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "<init>", "(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "getExpires", "()J", "getAccessToken", "getRefreshToken", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Result {
        public static final int $stable = 0;

        @NotNull
        private final String accessToken;
        private final long expires;

        @Nullable
        private final String login;

        @NotNull
        private final String refreshToken;

        public Result(@Nullable String str, long j10, @NotNull String accessToken, @NotNull String refreshToken) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            this.login = str;
            this.expires = j10;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        public static /* synthetic */ Result copy$default(Result result, String str, long j10, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = result.login;
            }
            if ((i10 & 2) != 0) {
                j10 = result.expires;
            }
            if ((i10 & 4) != 0) {
                str2 = result.accessToken;
            }
            if ((i10 & 8) != 0) {
                str3 = result.refreshToken;
            }
            return result.copy(str, j10, str2, str3);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getExpires() {
            return this.expires;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        @NotNull
        public final Result copy(@Nullable String login, long expires, @NotNull String accessToken, @NotNull String refreshToken) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            return new Result(login, expires, accessToken, refreshToken);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.login, result.login) && this.expires == result.expires && Intrinsics.areEqual(this.accessToken, result.accessToken) && Intrinsics.areEqual(this.refreshToken, result.refreshToken);
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        public final long getExpires() {
            return this.expires;
        }

        @Nullable
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        public int hashCode() {
            String str = this.login;
            return ((((((str == null ? 0 : str.hashCode()) * 31) + Long.hashCode(this.expires)) * 31) + this.accessToken.hashCode()) * 31) + this.refreshToken.hashCode();
        }

        @NotNull
        public String toString() {
            return "Result(login=" + this.login + ", expires=" + this.expires + ", accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OAuthChildCommand(@NotNull Context context, @NotNull Params params, boolean z10, @NotNull String authUrl) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(authUrl, "authUrl");
        this.authUrl = authUrl;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new NetworkCommand<Params, Result>.NetworkCommandBaseDelegate(this) { // from class: ru.mail.data.cmd.server.OAuthChildCommand.getCustomDelegate.1
            {
                super();
            }

            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            protected String getResponseStatusImpl(String resp) {
                Intrinsics.checkNotNullParameter(resp, "resp");
                try {
                    return StringsKt.equals(new JSONObject(resp).getString("status"), "ok", true) ? "200" : "-1";
                } catch (JSONException e10) {
                    OAuthChildCommand.LOG.e("Error parsing response " + e10);
                    return "-1";
                }
            }

            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onError(NetworkCommand.Response resp) {
                Intrinsics.checkNotNullParameter(resp, "resp");
                String respString = resp.getRespString();
                Intrinsics.checkNotNull(respString);
                if (StringsKt.contains$default((CharSequence) respString, (CharSequence) "fail", false, 2, (Object) null)) {
                    return new NetworkCommandStatus.ERROR_INVALID_LOGIN();
                }
                CommandStatus<?> commandStatusOnError = super.onError(resp);
                Intrinsics.checkNotNull(commandStatusOnError);
                return commandStatusOnError;
            }

            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onFolderAccessDenied() {
                return new CommandStatus.ERROR();
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected Uri onPrepareUrl(@NotNull Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Uri uri = Uri.parse(this.authUrl);
        Uri uriBuild = builder.authority(uri.getAuthority()).path(uri.getPath()).build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        return uriBuild;
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean prepareUrlMigrateToPost() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONObject jSONObject = new JSONObject(resp.getRespString()).getJSONObject(MailOAuthRequest.BODY_KEY);
            Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
            String childLogin = ((Params) getParams()).getChildLogin();
            long j10 = jSONObject.getLong("expires_in");
            String string = jSONObject.getString("access_token");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = jSONObject.getString("refresh_token");
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            return new Result(childLogin, j10, string, string2);
        } catch (JSONException e10) {
            LOG.e("Unable to parse oauth tokens " + e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
