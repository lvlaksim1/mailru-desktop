package ru.mail.serverapi;

import android.content.Context;
import android.net.Uri;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.service.NetworkService;
import ru.mail.util.log.Formats;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class LegacySession extends BaseSessionSetter {
    private static final String MPOP_HEADER = "Mpop";

    public LegacySession(Context context, BaseSessionSetter.SessionKeeper sessionKeeper, AccountManagerSettings accountManagerSettings) {
        super(context, sessionKeeper, accountManagerSettings);
    }

    @Override // ru.mail.network.SessionSetter
    public void cookieSetup(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
        String strPeekAuthToken = getSessionKeeper().peekAuthToken();
        setCookieOrThrow(networkService, strPeekAuthToken, getSessionKeeper().getNoAuthInfo());
        getSessionKeeper().pushAuthToken(strPeekAuthToken);
    }

    @Override // ru.mail.serverapi.BaseSessionSetter
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newUrlFormat(MPOP_HEADER));
        return logFilterPrepareTokenFilter;
    }

    @Override // ru.mail.network.SessionSetter
    public void urlSetup(Uri.Builder builder) throws NetworkCommandWithSession.BadSessionException {
    }
}
