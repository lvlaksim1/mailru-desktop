package ru.mail.auth.request;

import android.content.Context;
import ru.mail.OauthParams;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@UrlPath(pathSegments = {"oauth2_yahoo_token"})
public class YahooOAuthLoginRequest extends BaseOAuthLoginRequest<Params> {
    private static final Log LOG = Log.getLog("YahooOAuthLoginRequest");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends BaseOAuthLoginRequest.Params {
        private static final String PARAM_KEY_REDIRECT_URI = "redirect_uri";

        @Param(method = HttpMethod.GET, name = "redirect_uri")
        private final String mRedirectUri;

        public Params(Context context, OauthParams oauthParams, String str) {
            super(context, oauthParams, str);
            this.mRedirectUri = oauthParams.getRedirectUri();
        }
    }

    public YahooOAuthLoginRequest(Context context, HostProvider hostProvider, String str, OauthParams oauthParams, boolean z10) {
        super(context, hostProvider, new Params(context, oauthParams, str), z10);
    }
}
