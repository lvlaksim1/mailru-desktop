package ru.mail.auth.sdk.api.token;

import ru.mail.auth.sdk.OAuthParams;
import ru.mail.auth.sdk.api.ApiManager;
import ru.mail.auth.sdk.call.CallException;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class ExpiredTokenUpdater implements TokenExpiredListener {
    private final OAuthParams mOAuthParams;
    private final OAuthTokensStorage mOAuthTokensStorage;

    public ExpiredTokenUpdater(OAuthTokensStorage oAuthTokensStorage, OAuthParams oAuthParams) {
        this.mOAuthTokensStorage = oAuthTokensStorage;
        this.mOAuthParams = oAuthParams;
    }

    @Override // ru.mail.auth.sdk.api.token.TokenExpiredListener
    public void onTokenExpired() throws CallException {
        this.mOAuthTokensStorage.saveAccessToken(ApiManager.getRefreshAccessTokenCall(this.mOAuthParams.getClientId(), this.mOAuthTokensStorage.getRefreshToken(), this.mOAuthParams.getBaseUrl()).execute().getAccessToken());
    }
}
