package ru.mail.auth.request;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.Authenticator.R;
import ru.mail.OauthParams;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.auth.request.BaseOAuthLoginRequest.Params;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public abstract class BaseOAuthLoginRequest<P extends Params> extends SingleRequest<P, Result> {
    private static final String BIND_TOKEN = "bind_token";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String HEADER_X_AUTH_URI = "X-Auth-URI";
    private static final Log LOG = Log.getLog("BaseOAuthLoginRequest");
    private static final String REFRESH_TOKEN = "refresh_token";

    /* JADX INFO: compiled from: ProGuard */
    public class AuthorizeDelegate extends NetworkCommand<P, Result>.NetworkCommandBaseDelegate {
        private static final String HEADER_STATUS = "X-SWA-STATUS";

        public AuthorizeDelegate() {
            super();
        }

        private int getSwaStatus(String str) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e10) {
                BaseOAuthLoginRequest.LOG.e("SWA status parsing exception ", e10);
                return -1;
            }
        }

        private boolean hasOkStatus(String str) {
            try {
                return new JSONObject(str).optString("status", "").equalsIgnoreCase("ok");
            } catch (JSONException unused) {
                return false;
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            return (str.contains("Ok=1") || hasOkStatus(str)) ? String.valueOf(200) : "-1";
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return new CommandStatus.ERROR();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onResponseOk(NetworkCommand.Response response) {
            String headerField = BaseOAuthLoginRequest.this.getNetworkService().getHeaderField(HEADER_STATUS);
            if (TextUtils.isEmpty(headerField)) {
                return super.onResponseOk(response);
            }
            int swaStatus = getSwaStatus(headerField);
            return (swaStatus == 812 || swaStatus == 813) ? new CommandStatus.ERROR_WITH_STATUS_CODE(swaStatus) : super.onResponseOk(response);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mAuthUri;
        private final String mLogin;
        private final String mMigrantToken;

        public Result(String str, String str2, String str3) {
            this.mAuthUri = str;
            this.mLogin = str2;
            this.mMigrantToken = str3;
        }

        public String getAuthUri() {
            return this.mAuthUri;
        }

        public String getLogin() {
            return this.mLogin;
        }

        @Nullable
        public String getMigrantToken() {
            return this.mMigrantToken;
        }
    }

    public BaseOAuthLoginRequest(Context context, HostProvider hostProvider, P p10, boolean z10) {
        super(context, p10, hostProvider, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<P, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new AuthorizeDelegate();
    }

    @Override // ru.mail.network.NetworkCommand
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newUrlFormat("refresh_token"), Formats.newJsonFormat("refresh_token"), Formats.newUrlFormat(BIND_TOKEN), Formats.newJsonFormat(BIND_TOKEN), Formats.newUrlFormat("client_secret"), Formats.newJsonFormat("client_secret"));
        return logFilterPrepareTokenFilter;
    }

    @Override // ru.mail.authorizesdk.data.request.common.SingleRequest, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    public BaseOAuthLoginRequest(Context context, HostProvider hostProvider, P p10) {
        this(context, hostProvider, p10, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        String headerField = getNetworkService().getHeaderField(HEADER_X_AUTH_URI);
        return new Result(headerField, Uri.parse(headerField).getQueryParameter("Login"), Uri.parse(headerField).getQueryParameter("migrant_token"));
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_KEY_MOB_JSON)
        private static final int MOB_JSON = 1;
        private static final String PARAM_KEY_BIND_TOKEN = "bind_token";
        private static final String PARAM_KEY_CLIENT_ID = "client_id";
        private static final String PARAM_KEY_CLIENT_SECRET = "client_secret";
        private static final String PARAM_KEY_MOBILE_HEADER = "X-Mobile-App";
        private static final String PARAM_KEY_MOB_JSON = "mob_json";
        private static final String PARAM_KEY_OAUTH2 = "oauth2";
        private static final String PARAM_KEY_REFRESH_TOKEN = "refresh_token";
        private static final String PARAM_KEY_SCOPE = "scope";
        private static final String PARAM_KEY_SIMPLE = "simple";
        private static final String PARAM_MIGRANT = "migrant";
        private static final String PARAM_MIGRANT_NATIVE = "migrant_native";

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_KEY_SIMPLE)
        private static final int SIMPLE = 1;

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_KEY_BIND_TOKEN)
        private final String BIND_TOKEN;

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_KEY_OAUTH2)
        private final int OAUTH2;

        @Param(method = HttpMethod.GET, name = "client_id")
        private final String mClientId;

        @Param(method = HttpMethod.GET, name = "client_secret")
        private final String mClientSecret;

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_MIGRANT)
        private final int mMigrant;

        @Keep
        @Param(method = HttpMethod.GET, name = PARAM_MIGRANT_NATIVE)
        private final int mMigrantNative;

        @Keep
        @Param(method = HttpMethod.HEADER_ADD, name = PARAM_KEY_MOBILE_HEADER)
        private String mMobileAppHeader;

        @Param(method = HttpMethod.GET, name = "refresh_token")
        private final String mRefreshToken;

        @Param(method = HttpMethod.GET, name = "scope")
        private final String mScope;

        public Params(Context context, OauthParams oauthParams, String str, boolean z10, boolean z11) {
            this.OAUTH2 = AuthenticatorConfig.getInstance().isOAuthEnabled() ? 1 : 0;
            this.BIND_TOKEN = SocialLoginInfoHolder.getBindToken();
            this.mClientId = oauthParams.getClientId();
            this.mClientSecret = TextUtils.isEmpty(oauthParams.getSecretId()) ? null : oauthParams.getSecretId();
            this.mRefreshToken = TextUtils.isEmpty(str) ? null : str;
            this.mScope = TextUtils.isEmpty(oauthParams.getScope()) ? null : oauthParams.getScope();
            this.mMobileAppHeader = context.getString(R.string.auth_csrf_header);
            this.mMigrant = z10 ? 1 : 0;
            this.mMigrantNative = z11 ? 1 : 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Params params = (Params) obj;
            if (this.mClientId.equals(params.mClientId) && Objects.equals(this.mClientSecret, params.mClientSecret) && Objects.equals(this.mRefreshToken, params.mRefreshToken)) {
                return Objects.equals(this.mScope, params.mScope);
            }
            return false;
        }

        public int hashCode() {
            int iHashCode = this.mClientId.hashCode() * 31;
            String str = this.mClientSecret;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mRefreshToken;
            int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.mScope;
            return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
        }

        public Params(Context context, OauthParams oauthParams, String str) {
            this(context, oauthParams, str, false, false);
        }
    }
}
