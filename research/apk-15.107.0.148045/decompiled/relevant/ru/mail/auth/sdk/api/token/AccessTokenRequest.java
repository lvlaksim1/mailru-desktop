package ru.mail.auth.sdk.api.token;

import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.sdk.OAuthParams;
import ru.mail.auth.sdk.api.ApiCommand;
import ru.mail.auth.sdk.api.ApiQuery;
import ru.mail.auth.sdk.api.BaseAuthResponseProcessor;
import ru.mail.auth.sdk.api.ResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class AccessTokenRequest extends ApiCommand<OAuthTokensResult> {
    private final ApiQuery mApiQuery;

    /* JADX INFO: compiled from: ProGuard */
    private class Processor extends BaseAuthResponseProcessor<OAuthTokensResult> {
        private Processor() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ru.mail.auth.sdk.api.BaseAuthResponseProcessor
        public OAuthTokensResult processOkResult(JSONObject jSONObject) throws JSONException {
            return new OAuthTokensResult(jSONObject.getString("access_token"), jSONObject.getString("refresh_token"));
        }
    }

    public AccessTokenRequest(OAuthParams oAuthParams, String str, @Nullable String str2) {
        this.mApiQuery = new ApiQuery.Builder().withHost(oAuthParams.getBaseUrl()).withMethodName("token").withPostParam("grant_type", GrantType.AUTH_CODE.getValue()).withPostParam("client_id", oAuthParams.getClientId()).withPostParam("redirect_uri", oAuthParams.getRedirectUrl()).withPostParam("code", str).withPostParam("code_verifier", str2).build();
    }

    @Override // ru.mail.auth.sdk.api.ApiCommand
    protected ApiQuery getQuery() {
        return this.mApiQuery;
    }

    @Override // ru.mail.auth.sdk.api.ApiCommand
    protected ResponseProcessor<OAuthTokensResult> getResponseProcessor() {
        return new Processor();
    }
}
