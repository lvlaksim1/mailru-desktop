package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.OauthParams;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class OAuthAccessRefresh extends OAuthLoginBase<Params> {
    private static final Log LOG = Log.getLog("OAuthAccessRefresh");
    private final String mRefreshToken;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends OAuthLoginBase.Params {
        private static final String PARAM_KEY_CLIENT_SECRET = "client_secret";
        private static final String PARAM_KEY_REFRESH_TOKEN = "refresh_token";

        @Param(method = HttpMethod.POST, name = "client_secret")
        private final String mClientSecret;

        @Param(method = HttpMethod.POST, name = "refresh_token")
        private final String mRefreshToken;

        public Params(String str, String str2, @Nullable String str3) {
            super(str, OAuthLoginBase.GrantType.REFRESH_TOKEN);
            this.mRefreshToken = str2;
            this.mClientSecret = str3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getRefreshToken() {
            return this.mRefreshToken;
        }
    }

    public OAuthAccessRefresh(Context context, HostProvider hostProvider, OauthParams oauthParams, Params params) {
        super(context, hostProvider, oauthParams, params);
        this.mRefreshToken = params.getRefreshToken();
    }

    @Override // ru.mail.auth.request.OAuthLoginBase, ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, OAuthLoginBase.Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new OAuthLoginBase<Params>.OAuthLoginDelegate() { // from class: ru.mail.auth.request.OAuthAccessRefresh.1
            @Override // ru.mail.auth.request.OAuthLoginBase.OAuthLoginDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            protected String getResponseStatusImpl(String str) {
                try {
                    return new JSONObject(str).has("access_token") ? String.valueOf(200) : "-1";
                } catch (JSONException e10) {
                    OAuthAccessRefresh.LOG.e("Error parsing response " + e10);
                    return "-1";
                }
            }
        };
    }

    public String getRequestId() {
        String headerField;
        try {
            headerField = getNetworkService().getHeaderField("x-mru-request-id");
        } catch (Exception unused) {
            headerField = null;
        }
        return headerField == null ? "" : headerField;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.auth.request.OAuthLoginBase, ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public OAuthLoginBase.Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            return new OAuthLoginBase.Result(jSONObject.getString("access_token"), this.mRefreshToken, jSONObject.getLong("expires_in"));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    public OAuthAccessRefresh(Context context, HostProvider hostProvider, OauthParams oauthParams, String str) {
        this(context, hostProvider, oauthParams, new Params(oauthParams.getClientId(), str, null));
    }
}
