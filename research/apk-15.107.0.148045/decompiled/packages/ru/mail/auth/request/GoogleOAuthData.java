package ru.mail.auth.request;

import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class GoogleOAuthData {
    public static final String GOOGLE_OAUTH_REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
    private static final Log LOG = Log.getLog("GoogleOAuthData");

    public static String createOAuthScope(String str) {
        return "oauth2:server:client_id:" + str + ":api_scope:https://www.googleapis.com/auth/userinfo.profile openid https://mail.google.com/ https://www.googleapis.com/auth/plus.login https://www.google.com/m8/feeds";
    }
}
