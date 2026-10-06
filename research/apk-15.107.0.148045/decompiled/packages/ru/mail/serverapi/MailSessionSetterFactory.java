package ru.mail.serverapi;

import android.content.Context;
import ru.mail.network.SessionSetter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class MailSessionSetterFactory implements MailAuthorizationApiType.Factory<SessionSetter> {
    private final AccountManagerSettings mAccountManagerSettings;
    private final Context mContext;
    private final BaseSessionSetter.SessionKeeper mSessionKeeper;

    public MailSessionSetterFactory(Context context, AccountManagerSettings accountManagerSettings, BaseSessionSetter.SessionKeeper sessionKeeper) {
        this.mContext = context;
        this.mAccountManagerSettings = accountManagerSettings;
        this.mSessionKeeper = sessionKeeper;
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public SessionSetter legacy() {
        return new LegacySession(this.mContext, this.mSessionKeeper, this.mAccountManagerSettings);
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public SessionSetter legacyMpop() {
        return new LegacyMpopSession(this.mContext, this.mSessionKeeper, this.mAccountManagerSettings);
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public SessionSetter tornado() {
        return new TornadoSession(this.mContext, this.mSessionKeeper, this.mAccountManagerSettings);
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public SessionSetter tornadoMpop() {
        return new TornadoMpopSession(this.mContext, this.mSessionKeeper, this.mAccountManagerSettings);
    }
}
