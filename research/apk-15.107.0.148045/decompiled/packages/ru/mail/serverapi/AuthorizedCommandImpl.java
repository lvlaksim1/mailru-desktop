package ru.mail.serverapi;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import ru.mail.asserter.core.AsserterConfigFactory;
import ru.mail.asserter.core.AsserterFactory;
import ru.mail.asserter.description.Descriptions;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.network.CommandWithAuthorization;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NoAuthInfo;
import ru.mail.serverapi.retrofit.MailApiCommand;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogCollector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class AuthorizedCommandImpl extends CommandGroup implements AuthorizationAwareCommand {
    private static final Log LOG = Log.getLog("AuthorizedCommandImpl");
    private AsserterConfigFactory asserterConfigFactory;
    protected final AccountManagerSettings mAccountManagerSettings;
    protected final Analytics mAnalytics;
    protected final List<Command<?, ? extends CommandStatus<?>>> mAuthCmdImplList;
    private List<Command<?, ? extends CommandStatus<?>>> mAuthCmdList;
    protected final Context mContext;
    protected final FolderState mFolderState;
    protected final String mLogin;
    protected final boolean mNotifyAuthFailure;

    public AuthorizedCommandImpl(Context context, String str, FolderState folderState) {
        this(context, false, str, folderState);
    }

    private boolean canAuthenticate(NoAuthInfo noAuthInfo) {
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(this.mContext.getApplicationContext());
        Account account = new Account(noAuthInfo.getLogin(), this.mAccountManagerSettings.getAccountType());
        boolean zIsAccountExists = isAccountExists(accountManagerWrapper, account);
        boolean zIsValidForAuthorization = isValidForAuthorization(accountManagerWrapper, account);
        if (zIsAccountExists && zIsValidForAuthorization) {
            return true;
        }
        removeAllCommands();
        this.mAuthCmdImplList.clear();
        setResult(zIsAccountExists ? new NetworkCommandStatus.NO_AUTH(noAuthInfo) : new CommandStatus.ERROR());
        return false;
    }

    private void checkAuthCmdListEmpty() {
        if (!this.mAuthCmdImplList.isEmpty()) {
            throw new IllegalStateException("AuthCmdList is not empty in onNoAuth()");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AuthorizedCommandImpl createRequest(Context context, String str, FolderState folderState, Priority priority, Command... commandArr) {
        AuthorizedCommandImpl authorizedCommandImpl = new AuthorizedCommandImpl(context, str, folderState);
        for (Command command : commandArr) {
            authorizedCommandImpl.addCommand(command, priority);
        }
        return authorizedCommandImpl;
    }

    @Nullable
    private static NoAuthInfo getNoAuthInfo(CommandStatus<?> commandStatus) {
        if (commandStatus instanceof NetworkCommandStatus.BAD_SESSION) {
            return ((NetworkCommandStatus.BAD_SESSION) commandStatus).getNoAuthInfo();
        }
        if (commandStatus instanceof NetworkCommandStatus.NO_AUTH) {
            return ((NetworkCommandStatus.NO_AUTH) commandStatus).getNoAuthInfo();
        }
        return null;
    }

    private void invalidateToken(NoAuthInfo noAuthInfo) {
        String authToken = noAuthInfo.getAuthToken();
        LOG.v("chain " + this);
        if (authToken != null) {
            Authenticator.getAccountManagerWrapper(this.mContext.getApplicationContext()).invalidateAuthToken(this.mAccountManagerSettings.getAccountType(), authToken);
        }
    }

    private boolean isAccountExists(AccountManagerWrapper accountManagerWrapper, Account account) {
        for (Account account2 : accountManagerWrapper.getAppAccounts()) {
            if (account.equals(account2)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidForAuthorization(AccountManagerWrapper accountManagerWrapper, Account account) {
        return !TextUtils.equals(accountManagerWrapper.getUserData(account, Authenticator.KEY_UNAUTHORIZED), Authenticator.VALUE_UNAUTHORIZED);
    }

    private void logBadSession(NetworkCommandStatus.BAD_SESSION<?> bad_session) {
        NoAuthInfo noAuthInfo = getNoAuthInfo(bad_session);
        if (noAuthInfo != null) {
            this.mAnalytics.badSession(bad_session.getClass().getSimpleName(), noAuthInfo.getAuthorizationApi(), new AuthTokenAnalyticsClassifier.AccountManagerClassifier(getContext(), this.mAccountManagerSettings).classify(noAuthInfo.getLogin()));
        }
    }

    protected boolean addAuthCommand(Command<?, ? extends CommandStatus<?>> command) {
        return this.mAuthCmdImplList.add(command);
    }

    protected void addAuthCommandsAtFront() {
        List<Command<?, ? extends CommandStatus<?>>> authCommands = getAuthCommands();
        this.mAuthCmdList = authCommands;
        Iterator<Command<?, ? extends CommandStatus<?>>> it = authCommands.iterator();
        while (it.hasNext()) {
            addCommandAtFront(it.next());
        }
        LOG.d("added chain: " + toString());
    }

    protected AsserterConfigFactory getAsserterConfigFactory() {
        if (this.asserterConfigFactory == null) {
            this.asserterConfigFactory = (AsserterConfigFactory) Locator.from(getContext()).locate(AsserterConfigFactory.class);
        }
        return this.asserterConfigFactory;
    }

    protected List<Command<?, ? extends CommandStatus<?>>> getAuthCommands() {
        if (this.mAuthCmdImplList.isEmpty()) {
            throw new IllegalStateException("AuthCmdList is empty in getAuthCmd()");
        }
        ArrayList arrayList = new ArrayList(this.mAuthCmdImplList);
        this.mAuthCmdImplList.clear();
        return arrayList;
    }

    public CommandStatus<?> getAuthorizationStatus() {
        Object result = getResult();
        return result instanceof CommandStatus ? (CommandStatus) result : new CommandStatus.OK();
    }

    public Context getContext() {
        return this.mContext;
    }

    protected FolderState getFolderState() {
        return this.mFolderState;
    }

    protected String getLogin() {
        return this.mLogin;
    }

    protected boolean notifyAuthFailure() {
        return this.mNotifyAuthFailure;
    }

    protected void onAuthCmdCompleted(CommandStatus<?> commandStatus) {
        LOG.d("onAuthCmdCompleted status=" + commandStatus + " result " + getResult());
        if (commandStatus instanceof NetworkCommandStatus.ERROR_INVALID_LOGIN) {
            Command<?, ?> commandPeekActualCommand = peekActualCommand();
            if (commandPeekActualCommand != null) {
                this.mAnalytics.authCommandError(commandPeekActualCommand.getClass().getSimpleName());
            }
            if (getResult() instanceof NetworkCommandStatus.BAD_SESSION) {
                setResult(new NetworkCommandStatus.NO_AUTH(((NetworkCommandStatus.BAD_SESSION) getResult()).getNoAuthInfo()));
            } else if (!(getResult() instanceof NetworkCommandStatus.NO_AUTH)) {
                setResult(commandStatus);
            }
            removeAllCommands();
            return;
        }
        if (commandStatus instanceof MailCommandStatus.SWITCH_TO_IMAP) {
            setResult(commandStatus);
            removeAllCommands();
            return;
        }
        if (commandStatus == null) {
            setResult(new NetworkCommandStatus.AUTH_CANCELLED());
            removeAllCommands();
        } else {
            if (!NetworkCommand.statusOK(commandStatus)) {
                setResult(commandStatus);
                removeAllCommands();
                return;
            }
            try {
                CookieSetterEntryPoint.browserCookieSetter(this.mContext).setUpSessionInBrowser(this.mContext, getLogin(), this.mAccountManagerSettings.getAccountType());
            } catch (Exception e10) {
                AsserterFactory.createAsserter(getAsserterConfigFactory().createAsserterConfiguration("AuthorizedCommandImpl")).fail("Exception when tried to get webview", e10, Descriptions.compositionOf(Collections.singletonList(Descriptions.logs((LogCollector) Locator.from(this.mContext).locate(LogCollector.class)))));
            }
            onAuthSucceeded();
            setResult(new CommandStatus.ERROR());
        }
    }

    protected void onBadSession(NetworkCommandStatus.BAD_SESSION<?> bad_session) {
        if (canAuthenticate(bad_session.getNoAuthInfo())) {
            setupAuthCmd(bad_session.getNoAuthInfo());
            LOG.d("onBadSession(): " + bad_session);
            addAuthCommandsAtFront();
        }
        logBadSession(bad_session);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        if (command instanceof CommandWithAuthorization) {
            ((CommandWithAuthorization) command).refreshAuthApi();
            T t10 = (T) executeCommand(command, priority, executorSelector);
            CommandStatus<?> commandStatus = (CommandStatus) t10;
            onSetStatusFromExecutedCommand(commandStatus);
            processServerCommandResult(command, commandStatus);
            return t10;
        }
        if (command instanceof AuthorizationAwareCommand) {
            T t11 = (T) super.onExecuteCommand(command, priority, executorSelector);
            onSetStatusFromExecutedCommand(((AuthorizationAwareCommand) command).getAuthorizationStatus());
            return t11;
        }
        List<Command<?, ? extends CommandStatus<?>>> list = this.mAuthCmdList;
        if (list != null && list.contains(command)) {
            T t12 = (T) super.onExecuteCommand(command, priority, executorSelector);
            onAuthCmdCompleted((CommandStatus) t12);
            return t12;
        }
        if (command instanceof MailApiCommand) {
            T t13 = (T) super.onExecuteCommand(command, priority, executorSelector);
            onSetStatusFromExecutedCommand((CommandStatus) t13);
            return t13;
        }
        LOG.d("super chain: " + toString());
        return (T) super.onExecuteCommand(command, priority, executorSelector);
    }

    protected void onNoAuth(NetworkCommandStatus.NO_AUTH<?> no_auth) {
        checkAuthCmdListEmpty();
        invalidateToken(no_auth.getNoAuthInfo());
        if (canAuthenticate(no_auth.getNoAuthInfo())) {
            setupAuthCmd(no_auth.getNoAuthInfo());
            LOG.d("onNoAuth(): " + no_auth);
            addAuthCommandsAtFront();
        }
    }

    protected void onSetStatusFromExecutedCommand(CommandStatus<?> commandStatus) {
        setResult(commandStatus);
    }

    protected <T extends CommandStatus<?>> void processServerCommandResult(Command<?, ?> command, T t10) {
        if ((t10 instanceof MailCommandStatus.NO_AUTH_TWO_STEP_REQUIRED) || (t10 instanceof MailCommandStatus.NO_AUTH_BIND_REQUIRED)) {
            LOG.d("2step or bind required");
            removeCommand(command);
            return;
        }
        if (t10 instanceof NetworkCommandStatus.NO_AUTH) {
            onNoAuth((NetworkCommandStatus.NO_AUTH<?>) t10);
            return;
        }
        if (t10 instanceof NetworkCommandStatus.NO_AUTH_MULTIPLE) {
            onNoAuth((NetworkCommandStatus.NO_AUTH_MULTIPLE) t10);
            return;
        }
        if (t10 instanceof NetworkCommandStatus.BAD_SESSION) {
            onBadSession((NetworkCommandStatus.BAD_SESSION) t10);
            return;
        }
        removeCommand(command);
        LOG.d("removed chain: " + toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    public synchronized void setResult(Object obj) {
        try {
            if (obj == null) {
                super.setResult(new MailCommandStatus.EMPTY_RESULT_ERROR());
            } else {
                super.setResult(obj);
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    protected void setupAuthCmd(NoAuthInfo noAuthInfo) {
        Command<?, CommandStatus<?>> commandCreateAuthCmd = noAuthInfo.createAuthCmd(this.mContext);
        if (!(commandCreateAuthCmd instanceof RefreshExternalToken)) {
            throw new IllegalArgumentException("RefreshExternalToken class expected");
        }
        ((RefreshExternalToken) commandCreateAuthCmd).setNotifyAuthFailure(this.mNotifyAuthFailure);
        addAuthCommand(commandCreateAuthCmd);
        LOG.v("setNotifyAuthFailure to " + this.mNotifyAuthFailure);
    }

    public boolean statusOK() {
        return super.getResult() instanceof CommandStatus.OK;
    }

    public AuthorizedCommandImpl(Context context, Command command, String str, FolderState folderState) {
        this(context, str, folderState);
        addCommand(command);
    }

    public AuthorizedCommandImpl(Context context, boolean z10, String str, FolderState folderState) {
        this.mContext = context;
        this.mLogin = str;
        this.mFolderState = folderState;
        setResult(new CommandStatus.NOT_EXECUTED());
        this.mNotifyAuthFailure = z10;
        this.mAuthCmdImplList = new ArrayList();
        Locator locatorFrom = Locator.from(context);
        this.mAccountManagerSettings = (AccountManagerSettings) locatorFrom.locate(AccountManagerSettings.class);
        this.mAnalytics = (Analytics) locatorFrom.locate(Analytics.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AuthorizedCommandImpl createRequest(Context context, String str, FolderState folderState, Command... commandArr) {
        AuthorizedCommandImpl authorizedCommandImpl = new AuthorizedCommandImpl(context, str, folderState);
        for (Command command : commandArr) {
            authorizedCommandImpl.addCommand(command);
        }
        return authorizedCommandImpl;
    }

    protected void onNoAuth(NetworkCommandStatus.NO_AUTH_MULTIPLE no_auth_multiple) {
        checkAuthCmdListEmpty();
        boolean z10 = true;
        for (NoAuthInfo noAuthInfo : no_auth_multiple.getData()) {
            invalidateToken(noAuthInfo);
            boolean zCanAuthenticate = canAuthenticate(noAuthInfo);
            if (zCanAuthenticate) {
                setupAuthCmd(noAuthInfo);
            }
            z10 = zCanAuthenticate;
        }
        if (z10) {
            LOG.d("onNoAuth(): " + no_auth_multiple);
            addAuthCommandsAtFront();
        }
    }

    protected void onAuthSucceeded() {
    }
}
