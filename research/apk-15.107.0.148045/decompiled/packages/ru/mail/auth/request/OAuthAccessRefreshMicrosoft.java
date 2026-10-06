package ru.mail.auth.request;

import android.content.Context;
import ru.mail.OauthParams;
import ru.mail.network.HostProvider;
import ru.mail.network.UrlPath;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@UrlPath(pathSegments = {})
public class OAuthAccessRefreshMicrosoft extends OAuthAccessRefresh {
    public OAuthAccessRefreshMicrosoft(Context context, HostProvider hostProvider, OauthParams oauthParams, String str) {
        super(context, hostProvider, oauthParams, new OAuthAccessRefresh.Params(oauthParams.getClientId(), str, oauthParams.getSecretId()));
    }
}
