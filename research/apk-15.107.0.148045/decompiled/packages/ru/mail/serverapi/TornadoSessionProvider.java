package ru.mail.serverapi;

import android.accounts.Account;
import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.network.AuthCommandCreator;
import ru.mail.network.NoAuthInfo;
import ru.mail.network.ParamNameValuePair;
import ru.mail.network.api.SessionInfo;
import ru.mail.network.api.SessionProvider;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0019B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0012\u001a\u00020\u000fH\u0016J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0002H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lru/mail/serverapi/TornadoSessionProvider;", "Lru/mail/network/api/SessionProvider;", "Lru/mail/network/NoAuthInfo;", "platformInfo", "Lru/mail/serverapi/PlatformInfo;", "login", "", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "<init>", "(Lru/mail/serverapi/PlatformInfo;Ljava/lang/String;Lru/mail/auth/AccountManagerWrapper;Lru/mail/serverapi/AccountManagerSettings;)V", "tokenType", "sessionInfo", "Lru/mail/network/api/SessionInfo;", "authCommandCreator", "Lru/mail/network/AuthCommandCreator;", "getSessionInfo", "validateSession", "Lru/mail/network/api/SessionProvider$ValidationResult;", "refreshSession", "", "getAuthCommandCreator", "getNoAuthInfo", "Companion", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TornadoSessionProvider implements SessionProvider<NoAuthInfo> {

    @NotNull
    private static final Companion Companion = new Companion(null);

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("TornadoSessionProvider");

    @NotNull
    private final AccountManagerWrapper accountManager;

    @NotNull
    private final AccountManagerSettings accountManagerSettings;

    @NotNull
    private AuthCommandCreator authCommandCreator;

    @Nullable
    private final String login;
    private SessionInfo sessionInfo;

    @NotNull
    private final String tokenType;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/serverapi/TornadoSessionProvider$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public TornadoSessionProvider(@NotNull PlatformInfo platformInfo, @Nullable String str, @NotNull AccountManagerWrapper accountManager, @NotNull AccountManagerSettings accountManagerSettings) {
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        this.login = str;
        this.accountManager = accountManager;
        this.accountManagerSettings = accountManagerSettings;
        Object objCreate = MailAuthorizationApiType.TORNADO.create(new MailApiTokenTypeFactory());
        Intrinsics.checkNotNullExpressionValue(objCreate, "create(...)");
        this.tokenType = (String) objCreate;
        AuthCommandCreator authCommandCreator = new MailAuthCommandCreatorFactory(platformInfo, accountManagerSettings).tornado();
        Intrinsics.checkNotNullExpressionValue(authCommandCreator, "tornado(...)");
        this.authCommandCreator = authCommandCreator;
    }

    @Override // ru.mail.network.api.SessionProvider
    @NotNull
    public AuthCommandCreator getAuthCommandCreator() {
        return this.authCommandCreator;
    }

    @Override // ru.mail.network.api.SessionProvider
    @NotNull
    public SessionInfo getSessionInfo() {
        if (this.sessionInfo == null) {
            String str = this.login;
            Intrinsics.checkNotNull(str);
            Account account = new Account(str, this.accountManagerSettings.getAccountType());
            LOG.d("peekAuthToken " + this.tokenType);
            String strPeekAuthToken = this.accountManager.peekAuthToken(account, this.tokenType);
            this.sessionInfo = new SessionInfo(this.login, strPeekAuthToken, CollectionsKt.listOf(new ParamNameValuePair("access_token", strPeekAuthToken)), CollectionsKt.emptyList());
        }
        SessionInfo sessionInfo = this.sessionInfo;
        if (sessionInfo != null) {
            return sessionInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sessionInfo");
        return null;
    }

    @Override // ru.mail.network.api.SessionProvider
    public void refreshSession(@NotNull SessionInfo sessionInfo) {
        Intrinsics.checkNotNullParameter(sessionInfo, "sessionInfo");
        this.sessionInfo = sessionInfo;
    }

    @Override // ru.mail.network.api.SessionProvider
    @NotNull
    public SessionProvider.ValidationResult<NoAuthInfo> validateSession(@NotNull SessionInfo sessionInfo) {
        Intrinsics.checkNotNullParameter(sessionInfo, "sessionInfo");
        return TextUtils.isEmpty(sessionInfo.getAuthToken()) ? new SessionProvider.ValidationResult.Fail(getNoAuthInfo()) : new SessionProvider.ValidationResult.OK(sessionInfo);
    }

    @Override // ru.mail.network.api.SessionProvider
    @NotNull
    public NoAuthInfo getNoAuthInfo() {
        return new NoAuthInfo(this.login, getAuthCommandCreator(), getSessionInfo().getAuthToken());
    }
}
