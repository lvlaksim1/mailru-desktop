package ru.mail.serverapi;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001aB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007J\u0010\u0010\u0010\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0007J\u0006\u0010\u0011\u001a\u00020\u000eJ\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lru/mail/serverapi/CommandAuthManager;", "", "context", "Landroid/content/Context;", "mAccountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "mLogin", "", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/AccountManagerSettings;Ljava/lang/String;)V", "checkState", "Lru/mail/serverapi/CommandAuthManager$State;", "login", "invalidateToken", "", "oldTokenValue", "classifyTokenType", "success", "isAccountExists", "", "manager", "Lru/mail/auth/AccountManagerWrapper;", "account", "Landroid/accounts/Account;", "isValidForAuthorization", "managerWrapper", "State", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CommandAuthManager {

    @NotNull
    private final Context context;

    @NotNull
    private final AccountManagerSettings mAccountManagerSettings;

    @NotNull
    private final String mLogin;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\b\u001a\u00020\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lru/mail/serverapi/CommandAuthManager$State;", "", "isAccountExists", "", "isValidAccount", "<init>", "(ZZ)V", "()Z", "canAuthenticate", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class State {
        private final boolean isAccountExists;
        private final boolean isValidAccount;

        public State(boolean z10, boolean z11) {
            this.isAccountExists = z10;
            this.isValidAccount = z11;
        }

        public final boolean canAuthenticate() {
            return this.isAccountExists && this.isValidAccount;
        }

        /* JADX INFO: renamed from: isAccountExists, reason: from getter */
        public final boolean getIsAccountExists() {
            return this.isAccountExists;
        }
    }

    public CommandAuthManager(@NotNull Context context, @NotNull AccountManagerSettings mAccountManagerSettings, @NotNull String mLogin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mAccountManagerSettings, "mAccountManagerSettings");
        Intrinsics.checkNotNullParameter(mLogin, "mLogin");
        this.context = context;
        this.mAccountManagerSettings = mAccountManagerSettings;
        this.mLogin = mLogin;
    }

    private final boolean isAccountExists(AccountManagerWrapper manager, Account account) {
        for (Account account2 : manager.getAppAccounts()) {
            if (Intrinsics.areEqual(account, account2)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isValidForAuthorization(AccountManagerWrapper managerWrapper, Account account) {
        return !TextUtils.equals(managerWrapper.getUserData(account, Authenticator.KEY_UNAUTHORIZED), Authenticator.VALUE_UNAUTHORIZED);
    }

    @NotNull
    public final State checkState(@NotNull String login) {
        Intrinsics.checkNotNullParameter(login, "login");
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(this.context.getApplicationContext());
        Account account = new Account(login, this.mAccountManagerSettings.getAccountType());
        Intrinsics.checkNotNull(accountManagerWrapper);
        return new State(isAccountExists(accountManagerWrapper, account), isValidForAuthorization(accountManagerWrapper, account));
    }

    @NotNull
    public final String classifyTokenType(@Nullable String login) {
        String strClassify = new AuthTokenAnalyticsClassifier.AccountManagerClassifier(this.context, this.mAccountManagerSettings).classify(login);
        Intrinsics.checkNotNullExpressionValue(strClassify, "classify(...)");
        return strClassify;
    }

    public final void invalidateToken(@Nullable String oldTokenValue) {
        if (oldTokenValue != null) {
            AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(this.context.getApplicationContext());
            String accountType = this.mAccountManagerSettings.getAccountType();
            Intrinsics.checkNotNullExpressionValue(accountType, "getAccountType(...)");
            accountManagerWrapper.invalidateAuthToken(accountType, oldTokenValue);
        }
    }

    public final void success() {
        CookieSetterEntryPoint.INSTANCE.browserCookieSetter(this.context).setUpSessionInBrowser(this.context, this.mLogin, this.mAccountManagerSettings.getAccountType());
    }
}
