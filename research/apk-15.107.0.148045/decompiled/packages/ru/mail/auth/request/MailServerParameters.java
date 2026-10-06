package ru.mail.auth.request;

import java.io.Serializable;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public class MailServerParameters implements Serializable {
    private static final Log LOG = Log.getLog("MailServerParameters");
    private String mCaptchaCode;
    private boolean mCaptchaResponse;
    private String mEmail;
    private String mIncomingServerHost;
    private int mIncomingServerPort;
    private INCOMING_SERVER_TYPE mIncomingServerType;
    private boolean mIncomingServerUseSsl;
    private String mLogin;
    private String mMrcuCookie;
    private String mOutgoingServerHost;
    private int mOutgoingServerPort;
    private boolean mOutgoingServerUseSsl;
    private String mPassword;

    /* JADX INFO: compiled from: ProGuard */
    public enum INCOMING_SERVER_TYPE {
        IMAP("imap"),
        POP3("pop3"),
        EXCHANGE("exchange");

        private final String value;

        INCOMING_SERVER_TYPE(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    public MailServerParameters(String str, String str2) {
        this.mEmail = str;
        this.mPassword = str2;
    }

    public String getCaptchaCode() {
        return this.mCaptchaCode;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getIncomingServerHost() {
        return this.mIncomingServerHost;
    }

    public int getIncomingServerPort() {
        return this.mIncomingServerPort;
    }

    public INCOMING_SERVER_TYPE getIncomingServerType() {
        return this.mIncomingServerType;
    }

    public String getLogin() {
        return this.mLogin;
    }

    public String getMrcuCookie() {
        return this.mMrcuCookie;
    }

    public String getOutgoingServerHost() {
        return this.mOutgoingServerHost;
    }

    public int getOutgoingServerPort() {
        return this.mOutgoingServerPort;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public boolean isCaptchaResponse() {
        return this.mCaptchaResponse;
    }

    public boolean isIncomingServerUseSsl() {
        return this.mIncomingServerUseSsl;
    }

    public boolean ismOutgoingServerUseSsl() {
        return this.mOutgoingServerUseSsl;
    }

    public void setCaptchaCode(String str) {
        this.mCaptchaCode = str;
    }

    public void setCaptchaResponse(boolean z10) {
        this.mCaptchaResponse = z10;
    }

    public void setEmail(String str) {
        this.mEmail = str;
    }

    public void setIncomingServerHost(String str) {
        this.mIncomingServerHost = str;
    }

    public void setIncomingServerPort(int i10) {
        this.mIncomingServerPort = i10;
    }

    public void setIncomingServerType(INCOMING_SERVER_TYPE incoming_server_type) {
        this.mIncomingServerType = incoming_server_type;
    }

    public void setIncomingServerUseSsl(boolean z10) {
        this.mIncomingServerUseSsl = z10;
    }

    public void setLogin(String str) {
        this.mLogin = str;
    }

    public void setMrcuCookie(String str) {
        this.mMrcuCookie = str;
    }

    public void setOutgoingServerHost(String str) {
        this.mOutgoingServerHost = str;
    }

    public void setOutgoingServerPort(int i10) {
        this.mOutgoingServerPort = i10;
    }

    public void setOutgoingServerUseSsl(boolean z10) {
        this.mOutgoingServerUseSsl = z10;
    }

    public void setPassword(String str) {
        this.mPassword = str;
    }

    public MailServerParameters(INCOMING_SERVER_TYPE incoming_server_type, String str, int i10, boolean z10, String str2, int i11, boolean z11, String str3, String str4) {
        this.mIncomingServerType = incoming_server_type;
        this.mIncomingServerHost = str;
        this.mIncomingServerPort = i10;
        this.mIncomingServerUseSsl = z10;
        this.mOutgoingServerHost = str2;
        this.mOutgoingServerPort = i11;
        this.mOutgoingServerUseSsl = z11;
        this.mEmail = str3;
        this.mPassword = str4;
    }
}
