package ru.mail.serverapi.retrofit.session;

import android.accounts.Account;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.MailLoginFragment;
import ru.mail.serverapi.AccountManagerSettings;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lru/mail/serverapi/retrofit/session/TokenProvider;", "", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "<init>", "(Lru/mail/auth/AccountManagerWrapper;Lru/mail/serverapi/AccountManagerSettings;)V", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "", "getAccountType", "()Ljava/lang/String;", "peekAuthToken", "login", "tokenType", "authName", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TokenProvider {

    @NotNull
    private final AccountManagerWrapper accountManager;

    @NotNull
    private final String accountType;

    public TokenProvider(@NotNull AccountManagerWrapper accountManager, @NotNull AccountManagerSettings accountManagerSettings) {
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        this.accountManager = accountManager;
        String accountType = accountManagerSettings.getAccountType();
        Intrinsics.checkNotNullExpressionValue(accountType, "getAccountType(...)");
        this.accountType = accountType;
    }

    @NotNull
    public final String getAccountType() {
        return this.accountType;
    }

    @NotNull
    public final String peekAuthToken(@NotNull String login, @NotNull String tokenType, @NotNull String authName) throws BadSessionException {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        Intrinsics.checkNotNullParameter(authName, "authName");
        String strPeekAuthToken = this.accountManager.peekAuthToken(new Account(login, this.accountType), tokenType);
        if (strPeekAuthToken == null || strPeekAuthToken.length() == 0) {
            throw new BadSessionException("Unable to get auth token from AccountManager", login, tokenType, authName);
        }
        return strPeekAuthToken;
    }
}
