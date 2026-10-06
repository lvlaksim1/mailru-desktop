package ru.mail.serverapi;

import android.content.Context;
import com.google.android.gms.ads.RequestConfiguration;
import com.sun.mail.imap.IMAPStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandInterceptor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.CommandWithAuthorization;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NoAuthInfo;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002:\u0001(B'\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0011H\u0016J/\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u0010\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u00112\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u000f2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0002J\u0016\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J'\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0010\u0010\u0010\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0011H\u0002¢\u0006\u0002\u0010\u001eJ \u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0003H\u0002J0\u0010#\u001a\u00020\u000f2\u0010\u0010$\u001a\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u00112\f\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00192\u0006\u0010\"\u001a\u00020\u0003H\u0002J\u0014\u0010&\u001a\u00020\u000f2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030'H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lru/mail/serverapi/AuthInterceptor;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Lru/mail/mailbox/cmd/CommandInterceptor;", "", "context", "Landroid/content/Context;", "authManager", "Lru/mail/serverapi/CommandAuthManager;", "analytics", "Lru/mail/serverapi/Analytics;", "notifyAuthFailure", "", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/CommandAuthManager;Lru/mail/serverapi/Analytics;Z)V", "beforeExecute", "", IMAPStore.ID_COMMAND, "Lru/mail/mailbox/cmd/Command;", "afterExecute", "executor", "Lru/mail/mailbox/cmd/CommandInterceptor$Executor;", "result", "(Lru/mail/mailbox/cmd/CommandInterceptor$Executor;Lru/mail/mailbox/cmd/Command;Ljava/lang/Object;)Ljava/lang/Object;", "returnError", "originalStatus", "Lru/mail/mailbox/cmd/CommandStatus;", "handleError", "error", "", "retryCommand", "(Lru/mail/mailbox/cmd/CommandInterceptor$Executor;Lru/mail/mailbox/cmd/Command;)Ljava/lang/Object;", "handleNoAuthResult", "noAuthInfo", "Lru/mail/network/NoAuthInfo;", "baseStatus", "handleAuthCommandResult", "authCmd", "status", "logBadSession", "Lru/mail/network/NetworkCommandStatus$BAD_SESSION;", "AuthError", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAuthInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthInterceptor.kt\nru/mail/serverapi/AuthInterceptor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,130:1\n1869#2,2:131\n1#3:133\n*S KotlinDebug\n*F\n+ 1 AuthInterceptor.kt\nru/mail/serverapi/AuthInterceptor\n*L\n37#1:131,2\n*E\n"})
public final class AuthInterceptor<T> implements CommandInterceptor<T, T, Object> {

    @NotNull
    private final Analytics analytics;

    @NotNull
    private final CommandAuthManager authManager;

    @NotNull
    private final Context context;
    private final boolean notifyAuthFailure;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0013\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lru/mail/serverapi/AuthInterceptor$AuthError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "status", "Lru/mail/mailbox/cmd/CommandStatus;", "<init>", "(Lru/mail/mailbox/cmd/CommandStatus;)V", "getStatus", "()Lru/mail/mailbox/cmd/CommandStatus;", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AuthError extends Exception {

        @NotNull
        private final CommandStatus<?> status;

        public AuthError(@NotNull CommandStatus<?> status) {
            Intrinsics.checkNotNullParameter(status, "status");
            this.status = status;
        }

        @NotNull
        public final CommandStatus<?> getStatus() {
            return this.status;
        }
    }

    public AuthInterceptor(@NotNull Context context, @NotNull CommandAuthManager authManager, @NotNull Analytics analytics, boolean z10) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(authManager, "authManager");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        this.context = context;
        this.authManager = authManager;
        this.analytics = analytics;
        this.notifyAuthFailure = z10;
    }

    private final void handleAuthCommandResult(Command<?, ?> authCmd, CommandStatus<?> status, Object baseStatus) throws AuthError {
        if (status instanceof NetworkCommandStatus.ERROR_INVALID_LOGIN) {
            if (authCmd != null) {
                Analytics analytics = this.analytics;
                String simpleName = authCmd.getClass().getSimpleName();
                Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
                analytics.authCommandError(simpleName);
            }
            if (baseStatus instanceof NetworkCommandStatus.BAD_SESSION) {
                status = new NetworkCommandStatus.NO_AUTH<>(((NetworkCommandStatus.BAD_SESSION) baseStatus).getNoAuthInfo());
            } else if (baseStatus instanceof NetworkCommandStatus.NO_AUTH) {
                status = (CommandStatus) baseStatus;
            }
            returnError(status);
            return;
        }
        if (status instanceof MailCommandStatus.SWITCH_TO_IMAP) {
            returnError(status);
            return;
        }
        if (status == null) {
            returnError(new NetworkCommandStatus.AUTH_CANCELLED());
        } else if (NetworkCommand.statusOK(status)) {
            this.authManager.success();
        } else {
            returnError(status);
        }
    }

    private final void handleNoAuthResult(CommandInterceptor.Executor executor, NoAuthInfo noAuthInfo, Object baseStatus) throws AuthError {
        this.authManager.invalidateToken(noAuthInfo.getAuthToken());
        CommandAuthManager commandAuthManager = this.authManager;
        String login = noAuthInfo.getLogin();
        Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
        CommandAuthManager.State stateCheckState = commandAuthManager.checkState(login);
        if (!stateCheckState.canAuthenticate()) {
            returnError(stateCheckState.getIsAccountExists() ? new NetworkCommandStatus.NO_AUTH<>(noAuthInfo) : new CommandStatus.ERROR<>());
            return;
        }
        Command<?, CommandStatus<?>> commandCreateAuthCmd = noAuthInfo.createAuthCmd(this.context);
        if (!(commandCreateAuthCmd instanceof RefreshExternalToken)) {
            throw new IllegalArgumentException("RefreshExternalToken class expected");
        }
        ((RefreshExternalToken) commandCreateAuthCmd).setNotifyAuthFailure(this.notifyAuthFailure);
        handleAuthCommandResult(commandCreateAuthCmd, (CommandStatus) executor.executeCommand(commandCreateAuthCmd), baseStatus);
    }

    private final void logBadSession(NetworkCommandStatus.BAD_SESSION<?> status) {
        NoAuthInfo noAuthInfo = status.getNoAuthInfo();
        if (noAuthInfo != null) {
            String simpleName = status.getClass().getSimpleName();
            String authorizationApi = noAuthInfo.getAuthorizationApi();
            String strClassifyTokenType = this.authManager.classifyTokenType(noAuthInfo.getLogin());
            Analytics analytics = this.analytics;
            Intrinsics.checkNotNull(simpleName);
            analytics.badSession(simpleName, authorizationApi, strClassifyTokenType);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final T retryCommand(CommandInterceptor.Executor executor, Command<?, T> command) {
        beforeExecute(command);
        return (T) afterExecute(executor, command, executor.executeCommand(command));
    }

    private final void returnError(CommandStatus<?> originalStatus) throws AuthError {
        throw new AuthError(originalStatus);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandInterceptor
    public T afterExecute(@NotNull CommandInterceptor.Executor executor, @NotNull Command<?, T> command, T result) throws AuthError {
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(command, "command");
        if (!(command instanceof CommandWithAuthorization) || (result instanceof MailCommandStatus.NO_AUTH_TWO_STEP_REQUIRED) || (result instanceof MailCommandStatus.NO_AUTH_BIND_REQUIRED)) {
            return result;
        }
        if (result instanceof NetworkCommandStatus.NO_AUTH) {
            NoAuthInfo noAuthInfo = ((NetworkCommandStatus.NO_AUTH) result).getNoAuthInfo();
            Intrinsics.checkNotNullExpressionValue(noAuthInfo, "getNoAuthInfo(...)");
            handleNoAuthResult(executor, noAuthInfo, result);
            return retryCommand(executor, command);
        }
        if (result instanceof NetworkCommandStatus.NO_AUTH_MULTIPLE) {
            List<NoAuthInfo> data = ((NetworkCommandStatus.NO_AUTH_MULTIPLE) result).getData();
            Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
            for (NoAuthInfo noAuthInfo2 : data) {
                Intrinsics.checkNotNull(noAuthInfo2);
                handleNoAuthResult(executor, noAuthInfo2, result);
            }
            return retryCommand(executor, command);
        }
        if (result instanceof NetworkCommandStatus.BAD_SESSION) {
            NetworkCommandStatus.BAD_SESSION<?> bad_session = (NetworkCommandStatus.BAD_SESSION) result;
            logBadSession(bad_session);
            NoAuthInfo noAuthInfo3 = bad_session.getNoAuthInfo();
            Intrinsics.checkNotNullExpressionValue(noAuthInfo3, "getNoAuthInfo(...)");
            handleNoAuthResult(executor, noAuthInfo3, result);
            return retryCommand(executor, command);
        }
        return result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandInterceptor
    public void beforeExecute(@NotNull Command<?, T> command) {
        Intrinsics.checkNotNullParameter(command, "command");
        if (command instanceof CommandWithAuthorization) {
            ((CommandWithAuthorization) command).refreshAuthApi();
        }
    }

    @Override // ru.mail.mailbox.cmd.CommandInterceptor
    @Nullable
    public Object handleError(@NotNull Throwable error) {
        Intrinsics.checkNotNullParameter(error, "error");
        if (error instanceof AuthError) {
            return ((AuthError) error).getStatus();
        }
        return null;
    }
}
