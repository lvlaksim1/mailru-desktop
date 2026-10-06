package ru.mail.auth.request;

import ru.mail.auth.Authenticator;
import ru.mail.auth.MailLoginFragment;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public enum OAuthProvider {
    MAILRU("default", Authenticator.Type.DEFAULT),
    YAHOO("yahoo", Authenticator.Type.YAHOO_OAUTH),
    YANDEX(MailLoginFragment.YANDEX_DOMAIN, Authenticator.Type.YANDEX_OAUTH),
    GMAIL("gmail", Authenticator.Type.OAUTH),
    MSN("msn", Authenticator.Type.OUTLOOK_OAUTH);

    private final String mRepr;
    private Authenticator.Type mType;

    OAuthProvider(String str, Authenticator.Type type) {
        this.mRepr = str;
        this.mType = type;
    }

    public static OAuthProvider from(String str) {
        for (OAuthProvider oAuthProvider : values()) {
            if (oAuthProvider.mRepr.equals(str)) {
                return oAuthProvider;
            }
        }
        return MAILRU;
    }

    public String getRepr() {
        return this.mRepr;
    }

    public Authenticator.Type getType() {
        return this.mType;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mRepr;
    }
}
