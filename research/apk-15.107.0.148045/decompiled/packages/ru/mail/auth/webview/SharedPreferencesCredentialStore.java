package ru.mail.auth.webview;

import android.content.SharedPreferences;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.CredentialStore;
import java.io.IOException;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
class SharedPreferencesCredentialStore implements CredentialStore {
    private static final String ACCESS_TOKEN = "_access_token";
    private static final String EXPIRES_IN = "_expires_in";
    private static final String REFRESH_TOKEN = "_refresh_token";
    private static final String SCOPE = "_scope";
    private static final String TOKEN_LOG_PREFIX = "token";
    private SharedPreferences prefs;
    private static final Log LOG = Log.getLog("SharedPreferencesCredentialStore");
    private static final LogFilter sLogFilter = new LogFilter(Formats.newUrlFormat("token"));

    public SharedPreferencesCredentialStore(SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
    }

    @Override // com.google.api.client.auth.oauth2.CredentialStore
    public void delete(String str, Credential credential) throws IOException {
        LOG.i("Deleting credential for userId " + str);
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.remove(str + ACCESS_TOKEN);
        editorEdit.remove(str + EXPIRES_IN);
        editorEdit.remove(str + REFRESH_TOKEN);
        editorEdit.remove(str + SCOPE);
        editorEdit.apply();
    }

    @Override // com.google.api.client.auth.oauth2.CredentialStore
    public boolean load(String str, Credential credential) throws IOException {
        Log log = LOG;
        log.i("Loading credential for userId " + str);
        LogFilter logFilter = sLogFilter;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Loaded access token=");
        sb2.append(this.prefs.getString(str + ACCESS_TOKEN, ""));
        log.i(logFilter.filter(sb2.toString()));
        credential.setAccessToken(this.prefs.getString(str + ACCESS_TOKEN, null));
        if (this.prefs.contains(str + EXPIRES_IN)) {
            credential.setExpirationTimeMilliseconds(Long.valueOf(this.prefs.getLong(str + EXPIRES_IN, 0L)));
        }
        credential.setRefreshToken(this.prefs.getString(str + REFRESH_TOKEN, null));
        return true;
    }

    @Override // com.google.api.client.auth.oauth2.CredentialStore
    public void store(String str, Credential credential) throws IOException {
        Log log = LOG;
        log.i("Storing credential for userId " + str);
        log.i(sLogFilter.filter("Access token=" + credential.getAccessToken()));
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putString(str + ACCESS_TOKEN, credential.getAccessToken());
        if (credential.getExpirationTimeMilliseconds() != null) {
            editorEdit.putLong(str + EXPIRES_IN, credential.getExpirationTimeMilliseconds().longValue());
        }
        if (credential.getRefreshToken() != null) {
            editorEdit.putString(str + REFRESH_TOKEN, credential.getRefreshToken());
        }
        editorEdit.apply();
    }
}
