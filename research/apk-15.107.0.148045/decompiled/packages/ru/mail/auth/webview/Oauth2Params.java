package ru.mail.auth.webview;

import android.os.Bundle;
import com.google.api.client.auth.oauth2.Credential;
import ru.mail.authorizesdk.data.request.common.constants.MailAccountConstantsClass;
import ru.mail.authorizesdk.domain.models.oauth2.Oauth2Arguments;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class Oauth2Params {
    private static final Log LOG = Log.getLog("Oauth2Params");
    private final Credential.AccessMethod accessMethod;
    private final String authServerUrl;
    private final String clientId;
    private final String redirectUri;
    private final String scope;
    private final String secretId;
    private final String tokenServerUrl;

    public Oauth2Params(Bundle bundle, Credential.AccessMethod accessMethod) {
        if (bundle == null) {
            throw new RuntimeException("null passed as oauth2 parameters");
        }
        this.clientId = bundle.getString(MailAccountConstantsClass.EXTRA_OAUTH2_CLIENT_ID);
        this.secretId = bundle.getString(MailAccountConstantsClass.EXTRA_OAUTH2_SECRET_ID);
        this.redirectUri = bundle.getString(MailAccountConstantsClass.EXTRA_OAUTH2_REDIRECT_URI);
        this.authServerUrl = bundle.getString(MailAccountConstantsClass.EXTRA_OAUTH2_AUTH_URL);
        this.tokenServerUrl = bundle.getString(MailAccountConstantsClass.EXTRA_OAUTH2_TOKEN_URL);
        this.scope = bundle.getString(MailAccountConstantsClass.EXTRA_OAUTH2_SCOPE);
        this.accessMethod = accessMethod;
    }

    public Credential.AccessMethod getAccessMethod() {
        return this.accessMethod;
    }

    public String getAuthServerUrl() {
        return this.authServerUrl;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getRedirectUri() {
        return this.redirectUri;
    }

    public String getScope() {
        return this.scope;
    }

    public String getSecretId() {
        return this.secretId;
    }

    public String getTokenServerUrl() {
        return this.tokenServerUrl;
    }

    public Oauth2Params(Oauth2Arguments oauth2Arguments, Credential.AccessMethod accessMethod) {
        this.clientId = oauth2Arguments.getClientId();
        this.secretId = oauth2Arguments.getSecretId();
        this.redirectUri = oauth2Arguments.getRedirectUri();
        this.authServerUrl = oauth2Arguments.getAuthServerUrl();
        this.tokenServerUrl = oauth2Arguments.getTokenServerUrl();
        this.scope = oauth2Arguments.getScope();
        this.accessMethod = accessMethod;
    }
}
