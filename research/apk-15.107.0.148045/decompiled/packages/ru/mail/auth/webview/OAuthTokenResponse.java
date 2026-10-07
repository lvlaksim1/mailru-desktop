package ru.mail.auth.webview;

import androidx.annotation.Nullable;
import ru.mail.authorizesdk.auth.providers.oauth.OAuthTokenResponseProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public interface OAuthTokenResponse extends OAuthTokenResponseProvider {
    @Nullable
    String getAccessToken();

    @Nullable
    Long getExpiresInSeconds();

    @Nullable
    String getRefreshToken();
}
