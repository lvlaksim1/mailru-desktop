package ru.mail.data.cmd.server.calls;

import android.accounts.Account;
import android.text.TextUtils;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.calleridentification.CallsAuthProvider;
import ru.mail.calleridentification.CallsAuthStrategy;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.logic.content.DataManager;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u000e\u001a\u00020\rH\u0016J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u001e\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\rH\u0002J\u0018\u0010\u001b\u001a\u0004\u0018\u00010\r2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u000bH\u0002J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\rH\u0002J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010!\u001a\u00020\rH\u0002J\f\u0010 \u001a\u00020\u0019*\u00020#H\u0002J\f\u0010\"\u001a\u00020\u0019*\u00020#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsAuthProviderImpl;", "Lru/mail/calleridentification/CallsAuthProvider;", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "dataManager", "Lru/mail/logic/content/DataManager;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Lru/mail/auth/AccountManagerWrapper;Lru/mail/logic/content/DataManager;Lru/mail/util/log/Logger;)V", "currentAuthStrategy", "Lru/mail/calleridentification/CallsAuthStrategy;", "currentAccount", "", "getCurrentAccount", "initialize", "", "explicitAccount", "discardAuthorization", "getRequestAuthHeaders", "", "authToken", "getAuthStore", "Lru/mail/calleridentification/CallsAuthProvider$AuthStore;", "isAnonymous", "", "selectAccount", "findAuthorizedAccount", "accounts", "", "Lru/mail/data/entities/MailboxProfile;", "selectAuthStrategy", "isOauthEnabled", "login", "isHttpAccount", "Landroid/accounts/Account;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCallsAuthProviderImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CallsAuthProviderImpl.kt\nru/mail/data/cmd/server/calls/CallsAuthProviderImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,109:1\n295#2,2:110\n12970#3,2:112\n12970#3,2:114\n*S KotlinDebug\n*F\n+ 1 CallsAuthProviderImpl.kt\nru/mail/data/cmd/server/calls/CallsAuthProviderImpl\n*L\n73#1:110,2\n89#1:112,2\n95#1:114,2\n*E\n"})
public final class CallsAuthProviderImpl implements CallsAuthProvider {
    public static final int $stable = 8;

    @NotNull
    private final AccountManagerWrapper accountManager;

    @Nullable
    private volatile String currentAccount;

    @Nullable
    private volatile CallsAuthStrategy currentAuthStrategy;

    @NotNull
    private final DataManager dataManager;

    @Nullable
    private final Logger logger;

    public CallsAuthProviderImpl(@NotNull AccountManagerWrapper accountManager, @NotNull DataManager dataManager, @Nullable Logger logger) {
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        this.accountManager = accountManager;
        this.dataManager = dataManager;
        this.logger = logger;
    }

    private final String findAuthorizedAccount(List<? extends MailboxProfile> accounts) {
        Object next;
        Iterator<T> it = accounts.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!this.dataManager.isAccountAuthorized(((MailboxProfile) next).getLogin()));
        MailboxProfile mailboxProfile = (MailboxProfile) next;
        if (mailboxProfile != null) {
            return mailboxProfile.getLogin();
        }
        return null;
    }

    private final boolean isHttpAccount(String login) {
        for (Account account : this.accountManager.getAppAccounts()) {
            if (Intrinsics.areEqual(account.name, login) && isHttpAccount(account)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isOauthEnabled(String login) {
        if (AuthenticatorConfig.getInstance().isOAuthEnabled()) {
            for (Account account : this.accountManager.getAppAccounts()) {
                if (Intrinsics.areEqual(account.name, login) && isOauthEnabled(account)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final String selectAccount() {
        String login = this.dataManager.getMailboxContext().getProfile().getLogin();
        if (!this.dataManager.isAccountAuthorized(login)) {
            List<MailboxProfile> accounts = this.dataManager.getAccounts();
            Intrinsics.checkNotNullExpressionValue(accounts, "getAccounts(...)");
            String strFindAuthorizedAccount = findAuthorizedAccount(accounts);
            if (strFindAuthorizedAccount != null) {
                login = strFindAuthorizedAccount;
            }
        }
        Intrinsics.checkNotNull(login);
        return login;
    }

    private final CallsAuthStrategy selectAuthStrategy() {
        String strSelectAccount = this.currentAccount;
        if (strSelectAccount == null) {
            strSelectAccount = selectAccount();
        }
        return (isOauthEnabled(strSelectAccount) && isHttpAccount(strSelectAccount)) ? CallsBaseAuthStrategy.INSTANCE : new CallsAnonymAuthStrategy();
    }

    @Override // ru.mail.calleridentification.CallsAuthProvider
    public void discardAuthorization() {
        Logger logger = this.logger;
        if (logger != null) {
            Logger.info$default(logger, "Discard authorization", null, 2, null);
        }
        CallsAuthStrategy callsAuthStrategy = this.currentAuthStrategy;
        if (callsAuthStrategy != null) {
            callsAuthStrategy.discardAuthorization();
        }
        this.currentAuthStrategy = null;
        this.currentAccount = null;
    }

    @Override // ru.mail.calleridentification.CallsAuthProvider
    @NotNull
    public CallsAuthProvider.AuthStore getAuthStore() {
        CallsAuthProvider.AuthStore authStore;
        CallsAuthStrategy callsAuthStrategy = this.currentAuthStrategy;
        return (callsAuthStrategy == null || (authStore = callsAuthStrategy.getAuthStore()) == null) ? CallsBaseAuthStrategy.EmptyAuthStore.INSTANCE : authStore;
    }

    @Override // ru.mail.calleridentification.CallsAuthProvider
    @NotNull
    public String getCurrentAccount() {
        if (this.currentAccount == null) {
            this.currentAccount = selectAccount();
        }
        String str = this.currentAccount;
        Intrinsics.checkNotNull(str);
        return str;
    }

    @Override // ru.mail.calleridentification.CallsAuthProvider
    @NotNull
    public Map<String, String> getRequestAuthHeaders(@Nullable String authToken) {
        CallsAuthStrategy callsAuthStrategy = this.currentAuthStrategy;
        if (callsAuthStrategy != null) {
            return callsAuthStrategy.getRequestAuthHeaders(authToken);
        }
        Logger logger = this.logger;
        if (logger != null) {
            Logger.warn$default(logger, "Authorization used before initialization", null, 2, null);
        }
        return MapsKt.emptyMap();
    }

    @Override // ru.mail.calleridentification.CallsAuthProvider
    public void initialize(@Nullable String explicitAccount) {
        if (explicitAccount == null) {
            explicitAccount = selectAccount();
        }
        this.currentAccount = explicitAccount;
        CallsAuthStrategy callsAuthStrategySelectAuthStrategy = selectAuthStrategy();
        callsAuthStrategySelectAuthStrategy.initialize();
        Logger logger = this.logger;
        if (logger != null) {
            Logger.info$default(logger, "Init authentication with strategy " + Reflection.getOrCreateKotlinClass(callsAuthStrategySelectAuthStrategy.getClass()).getSimpleName(), null, 2, null);
        }
        this.currentAuthStrategy = callsAuthStrategySelectAuthStrategy;
    }

    @Override // ru.mail.calleridentification.CallsAuthProvider
    public boolean isAnonymous() {
        return this.currentAuthStrategy instanceof CallsAnonymAuthStrategy;
    }

    private final boolean isHttpAccount(Account account) {
        return !TextUtils.equals("IMAP", this.accountManager.getUserData(account, "transport_type"));
    }

    public /* synthetic */ CallsAuthProviderImpl(AccountManagerWrapper accountManagerWrapper, DataManager dataManager, Logger logger, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(accountManagerWrapper, dataManager, (i10 & 4) != 0 ? null : logger);
    }

    private final boolean isOauthEnabled(Account account) {
        return TextUtils.equals("true", this.accountManager.getUserData(account, Authenticator.KEY_OAUTH_ENABLED));
    }
}
