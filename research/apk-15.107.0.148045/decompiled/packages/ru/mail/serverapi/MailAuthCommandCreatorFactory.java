package ru.mail.serverapi;

import android.content.Context;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.AuthCommandCreator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class MailAuthCommandCreatorFactory implements MailAuthorizationApiType.Factory<AuthCommandCreator> {
    private final AccountManagerSettings mAccountManagerSettings;
    private final PlatformInfo mPlatformInfo;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    public static class MailAuthCommandCreator implements AuthCommandCreator {
        private final AccountManagerSettings mAccountManagerSettings;
        private final String mAuthName;
        private final PlatformInfo mPlatformInfo;
        private final String mTokenType;

        @Override // ru.mail.network.AuthCommandCreator
        public Command<?, CommandStatus<?>> createAuthCmd(Context context, String str) {
            return new RefreshExternalToken(context, new RefreshExternalToken.Params(str, this.mTokenType), this.mPlatformInfo, this.mAccountManagerSettings);
        }

        @Override // ru.mail.network.AuthCommandCreator
        public String getAuthName() {
            return this.mAuthName;
        }

        private MailAuthCommandCreator(PlatformInfo platformInfo, AccountManagerSettings accountManagerSettings, MailAuthorizationApiType mailAuthorizationApiType) {
            this.mPlatformInfo = platformInfo;
            this.mAccountManagerSettings = accountManagerSettings;
            this.mTokenType = (String) mailAuthorizationApiType.create(new MailApiTokenTypeFactory());
            this.mAuthName = mailAuthorizationApiType.name();
        }
    }

    public MailAuthCommandCreatorFactory(PlatformInfo platformInfo, AccountManagerSettings accountManagerSettings) {
        this.mPlatformInfo = platformInfo;
        this.mAccountManagerSettings = accountManagerSettings;
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public AuthCommandCreator legacy() {
        return new MailAuthCommandCreator(this.mPlatformInfo, this.mAccountManagerSettings, MailAuthorizationApiType.LEGACY);
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public AuthCommandCreator legacyMpop() {
        return new MailAuthCommandCreator(this.mPlatformInfo, this.mAccountManagerSettings, MailAuthorizationApiType.LEGACY_MPOP);
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public AuthCommandCreator tornado() {
        return new MailAuthCommandCreator(this.mPlatformInfo, this.mAccountManagerSettings, MailAuthorizationApiType.TORNADO);
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public AuthCommandCreator tornadoMpop() {
        return new MailAuthCommandCreator(this.mPlatformInfo, this.mAccountManagerSettings, MailAuthorizationApiType.TORNADO_MPOP);
    }
}
