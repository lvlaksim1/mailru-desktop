package ru.mail.serverapi.retrofit.session;

import android.content.Context;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.asserter.core.AsserterConfigFactory;
import ru.mail.asserter.core.AsserterFactory;
import ru.mail.asserter.description.Descriptions;
import ru.mail.kit.result.tools.Result;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NoAuthInfo;
import ru.mail.serverapi.AccountManagerSettings;
import ru.mail.serverapi.BrowserCookieSetter;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.util.log.LogCollector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B3\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ:\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0013J\u001c\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lru/mail/serverapi/retrofit/session/TokenRepository;", "", "context", "Landroid/content/Context;", "noAuthInfoCreator", "Lru/mail/serverapi/retrofit/session/NoAuthInfoCreator;", "accountManagerSettings", "Lru/mail/serverapi/AccountManagerSettings;", "executor", "Lru/mail/mailbox/cmd/ExecutorSelector;", "browserCookieSetter", "Lru/mail/serverapi/BrowserCookieSetter;", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/retrofit/session/NoAuthInfoCreator;Lru/mail/serverapi/AccountManagerSettings;Lru/mail/mailbox/cmd/ExecutorSelector;Lru/mail/serverapi/BrowserCookieSetter;)V", "refreshToken", "Lru/mail/kit/result/tools/Result;", "", "Lru/mail/serverapi/retrofit/session/SessionError;", "login", "", "authName", "tokenType", "token", "noAuthFailure", "noAuthInfo", "Lru/mail/network/NoAuthInfo;", "setUpSessionInBrowser", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TokenRepository {

    @NotNull
    private final AccountManagerSettings accountManagerSettings;

    @NotNull
    private final BrowserCookieSetter browserCookieSetter;

    @NotNull
    private final Context context;

    @NotNull
    private final ExecutorSelector executor;

    @NotNull
    private final NoAuthInfoCreator noAuthInfoCreator;

    @Inject
    public TokenRepository(@ApplicationContext @NotNull Context context, @NotNull NoAuthInfoCreator noAuthInfoCreator, @NotNull AccountManagerSettings accountManagerSettings, @NotNull ExecutorSelector executor, @NotNull BrowserCookieSetter browserCookieSetter) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(noAuthInfoCreator, "noAuthInfoCreator");
        Intrinsics.checkNotNullParameter(accountManagerSettings, "accountManagerSettings");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(browserCookieSetter, "browserCookieSetter");
        this.context = context;
        this.noAuthInfoCreator = noAuthInfoCreator;
        this.accountManagerSettings = accountManagerSettings;
        this.executor = executor;
        this.browserCookieSetter = browserCookieSetter;
    }

    private final Result<Unit, SessionError> noAuthFailure(NoAuthInfo noAuthInfo) {
        Result.Companion companion = Result.INSTANCE;
        String login = noAuthInfo.getLogin();
        Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
        return companion.failure(new SessionError.NoAuth(login));
    }

    public static /* synthetic */ Result refreshToken$default(TokenRepository tokenRepository, String str, String str2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        return tokenRepository.refreshToken(str, str2, str3, str4);
    }

    private final void setUpSessionInBrowser(String login) {
        try {
            this.browserCookieSetter.setUpSessionInBrowser(this.context, login, this.accountManagerSettings.getAccountType());
        } catch (Exception e10) {
            Locator locatorFrom = Locator.INSTANCE.from(this.context);
            AsserterFactory.createAsserter(((AsserterConfigFactory) locatorFrom.locate(AsserterConfigFactory.class)).createAsserterConfiguration("AuthorizedCommandImpl")).fail("Exception when tried to get webview", e10, Descriptions.compositionOf(CollectionsKt.listOf(Descriptions.logs((LogCollector) locatorFrom.locate(LogCollector.class)))));
        }
    }

    @NotNull
    public final Result<Unit, SessionError> refreshToken(@Nullable String login, @NotNull String authName, @NotNull String tokenType, @Nullable String token) {
        Intrinsics.checkNotNullParameter(authName, "authName");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        NoAuthInfo noAuthInfoCreate = this.noAuthInfoCreator.create(login, authName, tokenType, token);
        try {
            CommandStatus<?> orThrow = noAuthInfoCreate.createAuthCmd(this.context).execute(this.executor).getOrThrow();
            if (orThrow instanceof CommandStatus.OK) {
                String login2 = noAuthInfoCreate.getLogin();
                Intrinsics.checkNotNullExpressionValue(login2, "getLogin(...)");
                setUpSessionInBrowser(login2);
                return Result.INSTANCE.success();
            }
            if (orThrow instanceof NetworkCommandStatus.ERROR_INVALID_LOGIN) {
                return noAuthFailure(noAuthInfoCreate);
            }
            if (orThrow instanceof NetworkCommandStatus.AUTH_CANCELLED) {
                return noAuthFailure(noAuthInfoCreate);
            }
            if (!(orThrow instanceof MailCommandStatus.SWITCH_TO_IMAP)) {
                if (orThrow instanceof NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED) {
                    return Result.INSTANCE.failure(SessionError.ConnectionError.INSTANCE);
                }
                return orThrow instanceof CommandStatus.ERROR ? Result.INSTANCE.failure(SessionError.Unexpected.INSTANCE) : Result.INSTANCE.failure(SessionError.Unexpected.INSTANCE);
            }
            Result.Companion companion = Result.INSTANCE;
            String data = ((MailCommandStatus.SWITCH_TO_IMAP) orThrow).getData();
            Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
            return companion.failure(new SessionError.SwitchToImap(data));
        } catch (InterruptedException unused) {
            return noAuthFailure(noAuthInfoCreate);
        } catch (ExecutionException unused2) {
            return Result.INSTANCE.failure(SessionError.Unexpected.INSTANCE);
        }
    }
}
