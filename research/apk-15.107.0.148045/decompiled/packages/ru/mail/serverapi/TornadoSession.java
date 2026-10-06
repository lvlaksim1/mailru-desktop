package ru.mail.serverapi;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.service.NetworkService;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class TornadoSession extends BaseSessionSetter {
    private static final String TOKEN_PARAM_NAME = "access_token";

    public TornadoSession(Context context, BaseSessionSetter.SessionKeeper sessionKeeper, AccountManagerSettings accountManagerSettings) {
        super(context, sessionKeeper, accountManagerSettings);
    }

    @NonNull
    private String peekAuthToken() throws NetworkCommandWithSession.BadSessionException {
        String strPeekAuthToken = getSessionKeeper().peekAuthToken();
        if (TextUtils.isEmpty(strPeekAuthToken)) {
            throw new NetworkCommandWithSession.BadSessionException("auth token empty", getSessionKeeper().getNoAuthInfo());
        }
        return strPeekAuthToken;
    }

    @Override // ru.mail.network.SessionSetter
    public void urlSetup(Uri.Builder builder) throws NetworkCommandWithSession.BadSessionException {
        String strPeekAuthToken = peekAuthToken();
        getSessionKeeper().pushAuthToken(strPeekAuthToken);
        builder.appendQueryParameter("access_token", strPeekAuthToken);
    }

    @Override // ru.mail.network.SessionSetter
    public void cookieSetup(NetworkService networkService) {
    }
}
