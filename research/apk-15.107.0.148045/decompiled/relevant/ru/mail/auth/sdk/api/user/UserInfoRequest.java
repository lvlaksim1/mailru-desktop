package ru.mail.auth.sdk.api.user;

import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.sdk.api.ApiCommand;
import ru.mail.auth.sdk.api.ApiMethod;
import ru.mail.auth.sdk.api.ApiQuery;
import ru.mail.auth.sdk.api.BaseAuthResponseProcessor;
import ru.mail.auth.sdk.api.ResponseProcessor;
import ru.mail.auth.sdk.api.token.OAuthTokensStorage;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class UserInfoRequest extends ApiCommand<UserInfoResult> {
    private String mBaseUrl;
    private OAuthTokensStorage mProvider;

    /* JADX INFO: compiled from: ProGuard */
    private static class Processor extends BaseAuthResponseProcessor<UserInfoResult> {
        private Processor() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ru.mail.auth.sdk.api.BaseAuthResponseProcessor
        public UserInfoResult processOkResult(JSONObject jSONObject) throws JSONException {
            return new UserInfoResult(jSONObject.optString("name"), jSONObject.optString("image"), jSONObject.optString("email"), jSONObject.optString("id"));
        }
    }

    public UserInfoRequest(OAuthTokensStorage oAuthTokensStorage, String str) {
        this.mProvider = oAuthTokensStorage;
        this.mBaseUrl = str;
    }

    @Override // ru.mail.auth.sdk.api.ApiCommand
    protected ApiQuery getQuery() {
        return new ApiQuery.Builder().withHost(this.mBaseUrl).withMethodName(ApiMethod.USERINFO.getValue()).withPostParam("access_token", this.mProvider.getAccessToken()).build();
    }

    @Override // ru.mail.auth.sdk.api.ApiCommand
    protected ResponseProcessor<UserInfoResult> getResponseProcessor() {
        return new Processor();
    }
}
