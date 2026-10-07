package ru.mail.auth.webview;

import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.CredentialStore;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import ru.mail.authorizesdk.auth.providers.oauth.OAuth2HelperProvider;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.FilteringStrategy;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class OAuth2Helper implements OAuth2HelperProvider {
    public static final Formats.ParamFormat ACCESS_TOKEN_FORMAT;
    public static final Formats.ParamFormat AUTH_BEARER_HEADER_FORMAT;
    public static final Formats.ParamFormat AUTH_CODE_HEADER_FORMAT;
    private static final HttpTransport HTTP_TRANSPORT;
    private static final JsonFactory JSON_FACTORY;
    private static final Log LOG = Log.getLog("OAuth2Helper");
    public static final Formats.ParamFormat REFRESH_TOKEN_FORMAT;
    private static final LogFilter sLogFilter;
    private final CredentialStore credentialStore;
    private AuthorizationCodeFlow flow;
    private Oauth2Params oauth2Params;

    static {
        Formats.ParamFormat paramFormatNewHeaderFormat = Formats.newHeaderFormat("Authorization: OAuth");
        AUTH_CODE_HEADER_FORMAT = paramFormatNewHeaderFormat;
        Formats.ParamFormat paramFormatNewHeaderFormat2 = Formats.newHeaderFormat("Authorization: Bearer");
        AUTH_BEARER_HEADER_FORMAT = paramFormatNewHeaderFormat2;
        Formats.ParamFormat paramFormatNewUrlFormat = Formats.newUrlFormat("access_token");
        ACCESS_TOKEN_FORMAT = paramFormatNewUrlFormat;
        Formats.ParamFormat paramFormatNewUrlFormat2 = Formats.newUrlFormat("refresh_token");
        REFRESH_TOKEN_FORMAT = paramFormatNewUrlFormat2;
        HTTP_TRANSPORT = new NetHttpTransport();
        JSON_FACTORY = new JacksonFactory();
        sLogFilter = new LogFilter(paramFormatNewUrlFormat, paramFormatNewUrlFormat2, paramFormatNewHeaderFormat, paramFormatNewHeaderFormat2);
    }

    public OAuth2Helper(SharedPreferences sharedPreferences, Oauth2Params oauth2Params) {
        SharedPreferencesCredentialStore sharedPreferencesCredentialStore = new SharedPreferencesCredentialStore(sharedPreferences);
        this.credentialStore = sharedPreferencesCredentialStore;
        this.oauth2Params = oauth2Params;
        this.flow = new AuthorizationCodeFlow.Builder(oauth2Params.getAccessMethod(), HTTP_TRANSPORT, JSON_FACTORY, new GenericUrl(oauth2Params.getTokenServerUrl()), new ClientParametersAuthentication(oauth2Params.getClientId(), oauth2Params.getSecretId()), oauth2Params.getClientId(), oauth2Params.getAuthServerUrl()).setCredentialStore(sharedPreferencesCredentialStore).build();
    }

    public static List<FilteringStrategy.Constraint> getConstraints() {
        return Arrays.asList(Constraints.newParamNamedConstraint(AUTH_CODE_HEADER_FORMAT), Constraints.newParamNamedConstraint(AUTH_BEARER_HEADER_FORMAT), Constraints.newParamNamedConstraint(ACCESS_TOKEN_FORMAT), Constraints.newParamNamedConstraint(REFRESH_TOKEN_FORMAT));
    }

    @Override // ru.mail.authorizesdk.auth.providers.oauth.OAuth2HelperProvider
    public Collection<String> convertScopesToString(String str, String str2) {
        String[] strArrSplit = str.split(str2);
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, strArrSplit);
        return arrayList;
    }

    public String getAuthorizationUrl() {
        AuthorizationCodeRequestUrl redirectUri = this.flow.newAuthorizationUrl().setRedirectUri(this.oauth2Params.getRedirectUri());
        if (!TextUtils.isEmpty(this.oauth2Params.getScope())) {
            redirectUri.setScopes(convertScopesToString(this.oauth2Params.getScope()));
        }
        return redirectUri.build();
    }

    Uri getRedirectURI() {
        return Uri.parse(this.oauth2Params.getRedirectUri());
    }

    @Override // ru.mail.authorizesdk.auth.providers.oauth.OAuth2HelperProvider
    public TokenResponse retrieveAndStoreAccessToken(String str) throws IOException {
        Log log = LOG;
        log.i("retrieveAndStoreAccessToken for " + str);
        TokenResponse tokenResponseExecute = this.flow.newTokenRequest(str).setScopes(convertScopesToString(this.oauth2Params.getScope())).setRedirectUri(this.oauth2Params.getRedirectUri()).execute();
        log.i("Found tokenResponse :");
        LogFilter logFilter = sLogFilter;
        log.i(logFilter.filter(ACCESS_TOKEN_FORMAT.getFormattedMsg(tokenResponseExecute.getAccessToken())));
        log.i(logFilter.filter(REFRESH_TOKEN_FORMAT.getFormattedMsg(tokenResponseExecute.getRefreshToken())));
        log.i("Expires_in : " + tokenResponseExecute.getExpiresInSeconds());
        log.i("ClientId() :" + this.flow.getClientId());
        return tokenResponseExecute;
    }

    public Collection<String> convertScopesToString(String str) {
        return convertScopesToString(str, ",");
    }
}
