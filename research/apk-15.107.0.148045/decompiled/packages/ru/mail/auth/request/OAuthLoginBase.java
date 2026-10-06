package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.OauthParams;
import ru.mail.auth.Analytics;
import ru.mail.auth.AuthenticatorEntryPoint;
import ru.mail.auth.request.OAuthLoginBase.Params;
import ru.mail.authorizationsdk.feature.ok.data.OKAuthRemoteSource;
import ru.mail.authorizesdk.data.request.common.PostRequest;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@UrlPath(pathSegments = {"token"})
public abstract class OAuthLoginBase<P extends Params> extends PostRequest<P, Result> {
    public static final String ACCESS_TOKEN = "access_token";
    public static final String ERROR = "error";
    public static final String ERROR_CODE = "error_code";
    public static final String EXPIRES_IN = "expires_in";
    private static final Log LOG = Log.getLog("OAuthLoginBase");
    public static final String REFRESH_TOKEN = "refresh_token";
    private final Analytics mAnalytics;
    private final OauthParams mMailParams;

    /* JADX INFO: compiled from: ProGuard */
    public enum GrantType {
        PASSWORD("password"),
        AUTH_CODE(OKAuthRemoteSource.AUTH_GRANT_TYPE),
        REFRESH_TOKEN("refresh_token");

        private String mValue;

        GrantType(String str) {
            this.mValue = str;
        }

        public String getValue() {
            return this.mValue;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public class OAuthLoginDelegate extends NetworkCommand<P, Result>.NetworkCommandBaseDelegate {
        public OAuthLoginDelegate() {
            super();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return (jSONObject.has("refresh_token") && jSONObject.has("access_token")) ? String.valueOf(200) : "-1";
            } catch (JSONException e10) {
                OAuthLoginBase.LOG.e("Error parsing response " + e10);
                return "-1";
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            try {
                JSONObject jSONObject = new JSONObject(response.getRespString());
                if (jSONObject.has("error_code")) {
                    int i10 = jSONObject.getInt("error_code");
                    OAuthLoginBase.this.mAnalytics.failedOAuthTokenRefresh(String.valueOf(i10));
                    String string = jSONObject.getString("error");
                    if (i10 == 3 || i10 == 6) {
                        return new AuthCommandStatus.ERROR_INVALID_LOGIN(string);
                    }
                }
            } catch (JSONException e10) {
                OAuthLoginBase.this.mAnalytics.failedOAuthTokenRefresh("json exception " + response.getStatusCode());
                OAuthLoginBase.LOG.e("Error parsing error response " + e10);
            }
            return super.onError(response);
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return new CommandStatus.ERROR();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Keep
    public static class Params {
        private static final String PARAM_KEY_CLIENT_ID = "client_id";
        private static final String PARAM_KEY_GRANT_TYPE = "grant_type";

        @Param(method = HttpMethod.POST, name = "client_id")
        private final String mClientId;

        @Param(getterName = "getGrantType", method = HttpMethod.POST, name = PARAM_KEY_GRANT_TYPE, useGetter = true)
        private final GrantType mGrantType;

        public Params(String str, GrantType grantType) {
            this.mClientId = str;
            this.mGrantType = grantType;
        }

        public String getClientId() {
            return this.mClientId;
        }

        public String getGrantType() {
            return this.mGrantType.getValue();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mAccessToken;
        private final long mExpirationDate;
        private final String mRefreshToken;

        public Result(String str, String str2, long j10) {
            this.mAccessToken = str;
            this.mRefreshToken = str2;
            this.mExpirationDate = j10;
        }

        public String getAccessToken() {
            return this.mAccessToken;
        }

        public long getExpirationDate() {
            return this.mExpirationDate;
        }

        public String getRefreshToken() {
            return this.mRefreshToken;
        }
    }

    public OAuthLoginBase(Context context, HostProvider hostProvider, OauthParams oauthParams, P p10) {
        super(context, p10, hostProvider);
        this.mMailParams = oauthParams;
        this.mAnalytics = AuthenticatorEntryPoint.analytics(context);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<P, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new OAuthLoginDelegate();
    }

    protected OauthParams getMailParams() {
        return this.mMailParams;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<P, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new JsonStatusResponseProcessor(response, networkCommandBaseDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            if (jSONObject.has("refresh_token") && jSONObject.has("access_token")) {
                return new Result(jSONObject.getString("access_token"), jSONObject.getString("refresh_token"), jSONObject.getLong("expires_in"));
            }
        } catch (JSONException e10) {
            e10.printStackTrace();
            this.mAnalytics.failedOAuthTokenRefresh("token json error " + e10.getMessage());
        }
        throw new NetworkCommand.PostExecuteException();
    }
}
