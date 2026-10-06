package ru.mail.auth.request;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public enum AuthType {
    PASSWORD("password"),
    SMS("sms"),
    PUSH("push");

    private final String mRepr;

    AuthType(String str) {
        this.mRepr = str;
    }

    public static AuthType from(String str) {
        for (AuthType authType : values()) {
            if (authType.mRepr.equals(str)) {
                return authType;
            }
        }
        return PASSWORD;
    }

    public String getRepr() {
        return this.mRepr;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mRepr;
    }
}
