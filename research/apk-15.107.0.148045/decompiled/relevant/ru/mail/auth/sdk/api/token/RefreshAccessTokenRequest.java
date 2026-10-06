package ru.mail.auth.sdk.api.token;

import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.sdk.api.ApiCommand;
import ru.mail.auth.sdk.api.ApiMethod;
import ru.mail.auth.sdk.api.ApiQuery;
import ru.mail.auth.sdk.api.BaseAuthResponseProcessor;
import ru.mail.auth.sdk.api.ResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class RefreshAccessTokenRequest extends ApiCommand<OAuthTokensResult> {
    private String mBaseUrl;
    private String mClientId;
    private OAuthTokensStorage mTokensStorage;

    /* JADX INFO: compiled from: ProGuard */
    private class Processor extends BaseAuthResponseProcessor<OAuthTokensResult> {
        private Processor() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ru.mail.auth.sdk.api.BaseAuthResponseProcessor
        public OAuthTokensResult processOkResult(JSONObject jSONObject) throws JSONException {
            return new OAuthTokensResult(jSONObject.getString("access_token"), RefreshAccessTokenRequest.this.mTokensStorage.getRefreshToken());
        }
    }

    public RefreshAccessTokenRequest(String str, OAuthTokensStorage oAuthTokensStorage, String str2) {
        this.mClientId = str;
        this.mTokensStorage = oAuthTokensStorage;
        this.mBaseUrl = str2;
    }

    @Override // ru.mail.auth.sdk.api.ApiCommand
    protected ApiQuery getQuery() {
        return new ApiQuery.Builder().withHost(this.mBaseUrl).withMethodName(ApiMethod.TOKEN.getValue()).withPostParam("grant_type", GrantType.REFRESH_TOKEN.getValue()).withPostParam("client_id", this.mClientId).withPostParam("refresh_token", this.mTokensStorage.getRefreshToken()).build();
    }

    @Override // ru.mail.auth.sdk.api.ApiCommand
    protected ResponseProcessor<OAuthTokensResult> getResponseProcessor() {
        return new Processor();
    }
}
