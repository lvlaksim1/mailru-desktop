package ru.mail.serverapi.retrofit.session;

import android.accounts.Account;
import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.kit.result.tools.Result;
import ru.mail.serverapi.AccountManagerSettings;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J$\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J8\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u00122\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00192\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010\u001d\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0002J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lru/mail/serverapi/retrofit/session/SessionRecoveryInterceptor;", "Lokhttp3/Interceptor;", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "tokenRepository", "Lru/mail/serverapi/retrofit/session/TokenRepository;", "<init>", "(Lru/mail/auth/AccountManagerWrapper;Lru/mail/serverapi/AccountManagerSettings;Lru/mail/serverapi/retrofit/session/TokenRepository;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "proceedWithSessionRecovery", "attempts", "", "handleInvalidSessionException", "Lru/mail/kit/result/tools/Result;", "", "Lru/mail/serverapi/retrofit/session/SessionError;", OkListenerKt.KEY_EXCEPTION, "Lru/mail/serverapi/retrofit/session/RequestSessionException;", "handleAuthError", "login", "", "authName", "tokenType", "token", "invalidateToken", "isAccountExists", "", "manager", "account", "Landroid/accounts/Account;", "isValidForAuthorization", "managerWrapper", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionRecoveryInterceptor implements Interceptor {

    @NotNull
    private final AccountManagerWrapper accountManager;

    @NotNull
    private final AccountManagerSettings accountManagerSettings;

    @NotNull
    private final TokenRepository tokenRepository;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RequestSessionException.Reason.values().length];
            try {
                iArr[RequestSessionException.Reason.User.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RequestSessionException.Reason.Token.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[RequestSessionException.Reason.BadSession.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[RequestSessionException.Reason.TwoStepRequired.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[RequestSessionException.Reason.BindRequired.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SessionRecoveryInterceptor(@NotNull AccountManagerWrapper accountManager, @NotNull AccountManagerSettings accountManagerSettings, @NotNull TokenRepository tokenRepository) {
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        Intrinsics.checkNotNullParameter(tokenRepository, "tokenRepository");
        this.accountManager = accountManager;
        this.accountManagerSettings = accountManagerSettings;
        this.tokenRepository = tokenRepository;
    }

    private final Result<Unit, SessionError> handleAuthError(String login, String authName, String tokenType, String token) {
        Account account = new Account(login, this.accountManagerSettings.getAccountType());
        if (isAccountExists(this.accountManager, account)) {
            return isValidForAuthorization(this.accountManager, account) ? this.tokenRepository.refreshToken(login, authName, tokenType, token) : Result.INSTANCE.failure(new SessionError.NoAuth(login));
        }
        return Result.INSTANCE.failure(SessionError.Unexpected.INSTANCE);
    }

    static /* synthetic */ Result handleAuthError$default(SessionRecoveryInterceptor sessionRecoveryInterceptor, String str, String str2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        return sessionRecoveryInterceptor.handleAuthError(str, str2, str3, str4);
    }

    private final Result<Unit, SessionError> handleInvalidSessionException(RequestSessionException exception, int attempts) {
        if (attempts < 1) {
            return Result.INSTANCE.failure(SessionError.Unexpected.INSTANCE);
        }
        String login = exception.getLogin();
        if (login == null) {
            return Result.INSTANCE.failure(new SessionError.NoAuth(""));
        }
        int i10 = WhenMappings.$EnumSwitchMapping$0[exception.getReason().ordinal()];
        if (i10 == 1 || i10 == 2) {
            invalidateToken(exception.getToken());
            return handleAuthError(login, exception.getAuthName(), exception.getTokenType(), exception.getToken());
        }
        if (i10 == 3) {
            return handleAuthError(login, exception.getAuthName(), exception.getTokenType(), exception.getToken());
        }
        if (i10 == 4 || i10 == 5) {
            return Result.INSTANCE.failure(new SessionError.NoAuth(login));
        }
        throw new NoWhenBranchMatchedException();
    }

    private final void invalidateToken(String token) {
        if (token != null) {
            AccountManagerWrapper accountManagerWrapper = this.accountManager;
            String accountType = this.accountManagerSettings.getAccountType();
            Intrinsics.checkNotNullExpressionValue(accountType, "getAccountType(...)");
            accountManagerWrapper.invalidateAuthToken(accountType, token);
        }
    }

    private final boolean isAccountExists(AccountManagerWrapper manager, Account account) {
        return ArraysKt.contains(manager.getAppAccounts(), account);
    }

    private final boolean isValidForAuthorization(AccountManagerWrapper managerWrapper, Account account) {
        return !TextUtils.equals(managerWrapper.getUserData(account, Authenticator.KEY_UNAUTHORIZED), Authenticator.VALUE_UNAUTHORIZED);
    }

    private final Response proceedWithSessionRecovery(Interceptor.Chain chain, int attempts) throws SessionException {
        try {
            return chain.proceed(chain.request());
        } catch (RequestSessionException e10) {
            Result<Unit, SessionError> resultHandleInvalidSessionException = handleInvalidSessionException(e10, attempts);
            if (resultHandleInvalidSessionException instanceof Result.Success) {
                return proceedWithSessionRecovery(chain, attempts - 1);
            }
            if (resultHandleInvalidSessionException instanceof Result.Failure) {
                throw new SessionException((SessionError) ((Result.Failure) resultHandleInvalidSessionException).getError());
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return proceedWithSessionRecovery(chain, 3);
    }
}
