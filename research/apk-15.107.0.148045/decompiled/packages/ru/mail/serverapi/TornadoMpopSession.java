package ru.mail.serverapi;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.service.NetworkService;
import ru.mail.util.log.Formats;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class TornadoMpopSession extends BaseSessionSetter {
    private static final String MPOP_HEADER = "Mpop";
    private static final String TOKEN_PARAM_NAME = "token";

    public TornadoMpopSession(Context context, BaseSessionSetter.SessionKeeper sessionKeeper, AccountManagerSettings accountManagerSettings) {
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
    public void cookieSetup(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
        setMpopCookie(networkService);
    }

    @Override // ru.mail.serverapi.BaseSessionSetter
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newUrlFormat(MPOP_HEADER));
        return logFilterPrepareTokenFilter;
    }

    @Override // ru.mail.network.SessionSetter
    public void urlSetup(Uri.Builder builder) throws NetworkCommandWithSession.BadSessionException {
        String strPeekAuthToken = peekAuthToken();
        getSessionKeeper().pushAuthToken(strPeekAuthToken);
        builder.appendQueryParameter("token", strPeekAuthToken);
    }
}
