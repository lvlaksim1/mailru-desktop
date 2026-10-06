package ru.mail.serverapi.retrofit.session;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.AuthCommandCreator;
import ru.mail.network.NoAuthInfo;
import ru.mail.serverapi.AccountManagerSettings;
import ru.mail.serverapi.PlatformInfo;
import ru.mail.serverapi.RefreshExternalToken;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u000fB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/serverapi/retrofit/session/NoAuthInfoCreator;", "", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "platformInfo", "Lru/mail/serverapi/PlatformInfo;", "<init>", "(Lru/mail/serverapi/AccountManagerSettings;Lru/mail/serverapi/PlatformInfo;)V", "create", "Lru/mail/network/NoAuthInfo;", "login", "", "authName", "tokenType", "token", "RetrofitAuthCommandCreator", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NoAuthInfoCreator {

    @NotNull
    private final AccountManagerSettings accountManagerSettings;

    @NotNull
    private final PlatformInfo platformInfo;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000b\u001a\u00020\u0003H\u0016J*\u0010\f\u001a\u0010\u0012\u0002\b\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/serverapi/retrofit/session/NoAuthInfoCreator$RetrofitAuthCommandCreator;", "Lru/mail/network/AuthCommandCreator;", "authName", "", "tokenType", "platformInfo", "Lru/mail/serverapi/PlatformInfo;", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lru/mail/serverapi/PlatformInfo;Lru/mail/serverapi/AccountManagerSettings;)V", "getAuthName", "createAuthCmd", "Lru/mail/mailbox/cmd/Command;", "Lru/mail/mailbox/cmd/CommandStatus;", "context", "Landroid/content/Context;", "login", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RetrofitAuthCommandCreator implements AuthCommandCreator {

        @NotNull
        private final AccountManagerSettings accountManagerSettings;

        @NotNull
        private final String authName;

        @NotNull
        private final PlatformInfo platformInfo;

        @NotNull
        private final String tokenType;

        public RetrofitAuthCommandCreator(@NotNull String authName, @NotNull String tokenType, @NotNull PlatformInfo platformInfo, @NotNull AccountManagerSettings accountManagerSettings) {
            Intrinsics.checkNotNullParameter(authName, "authName");
            Intrinsics.checkNotNullParameter(tokenType, "tokenType");
            Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
            Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
            this.authName = authName;
            this.tokenType = tokenType;
            this.platformInfo = platformInfo;
            this.accountManagerSettings = accountManagerSettings;
        }

        @Override // ru.mail.network.AuthCommandCreator
        @NotNull
        public Command<?, CommandStatus<?>> createAuthCmd(@Nullable Context context, @Nullable String login) {
            return new RefreshExternalToken(context, new RefreshExternalToken.Params(login, this.tokenType), this.platformInfo, this.accountManagerSettings);
        }

        @Override // ru.mail.network.AuthCommandCreator
        @NotNull
        public String getAuthName() {
            return this.authName;
        }
    }

    public NoAuthInfoCreator(@NotNull AccountManagerSettings accountManagerSettings, @NotNull PlatformInfo platformInfo) {
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        this.accountManagerSettings = accountManagerSettings;
        this.platformInfo = platformInfo;
    }

    @NotNull
    public final NoAuthInfo create(@Nullable String login, @NotNull String authName, @NotNull String tokenType, @Nullable String token) {
        Intrinsics.checkNotNullParameter(authName, "authName");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        return new NoAuthInfo(login, new RetrofitAuthCommandCreator(authName, tokenType, this.platformInfo, this.accountManagerSettings), token);
    }
}
