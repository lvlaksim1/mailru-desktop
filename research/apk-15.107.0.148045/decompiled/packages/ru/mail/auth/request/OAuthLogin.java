package ru.mail.auth.request;

import android.content.Context;
import ru.mail.OauthParams;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class OAuthLogin extends OAuthLoginBase<Params> {
    private static final Log LOG = Log.getLog("OAuthLogin");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends OAuthLoginBase.Params {
        private static final String PARAM_KEY_PASSWORD = "password";
        private static final String PARAM_KEY_USERNAME = "username";

        @Param(method = HttpMethod.POST, name = "password")
        private final String mPassword;

        @Param(method = HttpMethod.POST, name = PARAM_KEY_USERNAME)
        private final String mUsername;

        public Params(String str, String str2, String str3) {
            super(str, OAuthLoginBase.GrantType.PASSWORD);
            this.mUsername = str2;
            this.mPassword = str3;
        }
    }

    public OAuthLogin(Context context, HostProvider hostProvider, OauthParams oauthParams, String str, String str2) {
        super(context, hostProvider, oauthParams, new Params(oauthParams.getClientId(), str, str2));
    }
}
