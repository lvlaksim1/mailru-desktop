package ru.mail.serverapi;

import ru.mail.auth.MailAccountConstants;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class MailApiTokenTypeFactory implements MailAuthorizationApiType.Factory<String> {
    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public String legacy() {
        return "ru.mail";
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public String legacyMpop() {
        return MailAccountConstants.AUTHTOKEN_TYPE_MPOP_TOKEN;
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public String tornado() {
        return "ru.mail.oauth2.access";
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public String tornadoMpop() {
        return MailAccountConstants.AUTHTOKEN_TYPE_MPOP_TOKEN;
    }
}
