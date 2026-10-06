package ru.mail.serverapi;

import android.content.Context;
import java.util.Objects;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class MailAuthorizationApiFactory implements MailAuthorizationApiType.Factory<ServerApi> {
    private final AccountManagerSettings mAccountManagerSettings;
    private final PlatformInfo mPlatformInfo;

    /* JADX INFO: compiled from: ProGuard */
    public static class LegacyMpopServerApi extends MailServerApi {
        public LegacyMpopServerApi(PlatformInfo platformInfo, AccountManagerSettings accountManagerSettings) {
            super(platformInfo, accountManagerSettings);
        }

        @Override // ru.mail.network.ServerApi
        public HostProvider createApiHostProvider(Context context) {
            return new MailHostProvider(context, "mail_api", R.string.mail_api_default_scheme, R.string.mail_api_default_host, getPlatformInfo());
        }

        @Override // ru.mail.network.ServerApi
        public ResponseProcessor createResponseProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
            return new LegacyResponseProcessor(response, networkCommandBaseDelegate);
        }

        @Override // ru.mail.network.ServerApi
        public ServerCommandBase.ServerCommandBaseDelegate createDefaultDelegate(ServerCommandBase serverCommandBase) {
            Objects.requireNonNull(serverCommandBase);
            return new ServerCommandBase.LegacyDelegate();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class LegacyServerApi extends MailServerApi {
        public LegacyServerApi(PlatformInfo platformInfo, AccountManagerSettings accountManagerSettings) {
            super(platformInfo, accountManagerSettings);
        }

        @Override // ru.mail.network.ServerApi
        public HostProvider createApiHostProvider(Context context) {
            return new MailHostProvider(context, "mail_api", ru.mail.Authenticator.R.string.auth_default_scheme, R.string.authstat_default_host, getPlatformInfo());
        }

        @Override // ru.mail.network.ServerApi
        public ResponseProcessor createResponseProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
            return new LegacyResponseProcessor(response, networkCommandBaseDelegate);
        }

        @Override // ru.mail.network.ServerApi
        public ServerCommandBase.ServerCommandBaseDelegate createDefaultDelegate(ServerCommandBase serverCommandBase) {
            Objects.requireNonNull(serverCommandBase);
            return new ServerCommandBase.LegacyDelegate();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static abstract class MailServerApi implements ServerApi<ServerCommandBase> {
        private final AccountManagerSettings mAccountManagerSettings;
        private final PlatformInfo mPlatformInfo;

        protected MailServerApi(PlatformInfo platformInfo, AccountManagerSettings accountManagerSettings) {
            this.mPlatformInfo = platformInfo;
            this.mAccountManagerSettings = accountManagerSettings;
        }

        protected AccountManagerSettings getAccountManagerSettings() {
            return this.mAccountManagerSettings;
        }

        protected PlatformInfo getPlatformInfo() {
            return this.mPlatformInfo;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    public static class TornadoMpopTokenServerApi extends MailServerApi {
        public TornadoMpopTokenServerApi(PlatformInfo platformInfo, AccountManagerSettings accountManagerSettings) {
            super(platformInfo, accountManagerSettings);
        }

        @Override // ru.mail.network.ServerApi
        public HostProvider createApiHostProvider(Context context) {
            return new MailHostProvider(context, "new_mail_api", R.string.new_mail_api_default_scheme, R.string.new_mail_api_default_host, getPlatformInfo());
        }

        @Override // ru.mail.network.ServerApi
        public ResponseProcessor createResponseProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
            return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
        }

        @Override // ru.mail.network.ServerApi
        public NetworkCommand.NetworkCommandBaseDelegate createDefaultDelegate(ServerCommandBase serverCommandBase) {
            Objects.requireNonNull(serverCommandBase);
            return new ServerCommandBase.TornadoDelegate();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    public static class TornadoServerApi extends MailServerApi {
        public TornadoServerApi(PlatformInfo platformInfo, AccountManagerSettings accountManagerSettings) {
            super(platformInfo, accountManagerSettings);
        }

        @Override // ru.mail.network.ServerApi
        public HostProvider createApiHostProvider(Context context) {
            return new MailHostProvider(context, "new_mail_api", R.string.new_mail_api_default_scheme, R.string.new_mail_api_default_host, getPlatformInfo());
        }

        @Override // ru.mail.network.ServerApi
        public ResponseProcessor createResponseProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
            return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
        }

        @Override // ru.mail.network.ServerApi
        public ServerCommandBase.ServerCommandBaseDelegate createDefaultDelegate(ServerCommandBase serverCommandBase) {
            Objects.requireNonNull(serverCommandBase);
            return new ServerCommandBase.TornadoDelegate();
        }
    }

    public MailAuthorizationApiFactory(PlatformInfo platformInfo, AccountManagerSettings accountManagerSettings) {
        this.mPlatformInfo = platformInfo;
        this.mAccountManagerSettings = accountManagerSettings;
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public ServerApi legacy() {
        return new LegacyServerApi(this.mPlatformInfo, this.mAccountManagerSettings);
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public ServerApi legacyMpop() {
        return new LegacyMpopServerApi(this.mPlatformInfo, this.mAccountManagerSettings);
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public ServerApi tornado() {
        return new TornadoServerApi(this.mPlatformInfo, this.mAccountManagerSettings);
    }

    @Override // ru.mail.serverapi.MailAuthorizationApiType.Factory
    public ServerApi tornadoMpop() {
        return new TornadoMpopTokenServerApi(this.mPlatformInfo, this.mAccountManagerSettings);
    }
}
