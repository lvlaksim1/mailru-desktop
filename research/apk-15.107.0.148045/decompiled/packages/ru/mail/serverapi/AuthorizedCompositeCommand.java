package ru.mail.serverapi;

import android.content.Context;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.CompositeCommand;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public abstract class AuthorizedCompositeCommand extends CompositeCommand<Object> implements AuthorizationAwareCommand {
    private static final Log LOG = Log.getLog("AuthorizedCompositeCommand");
    protected final AccountManagerSettings mAccountManagerSettings;
    protected final Analytics mAnalytics;
    private final CommandAuthManager mAuthManager;
    protected final Context mContext;
    protected final FolderState mFolderState;
    private boolean mIsInternalCall;
    protected final String mLogin;
    protected final boolean mNotifyAuthFailure;

    public AuthorizedCompositeCommand(Context context, String str, FolderState folderState) {
        this(context, false, str, folderState);
    }

    @Override // ru.mail.mailbox.cmd.CompositeCommand
    protected <T> T executeCommand(Command<?, T> command) {
        if (this.mIsInternalCall) {
            this.mIsInternalCall = false;
            return (T) super.executeCommand(command);
        }
        this.mIsInternalCall = true;
        return (T) executeCommand(command, new AuthInterceptor(this.mContext, this.mAuthManager, this.mAnalytics, this.mNotifyAuthFailure));
    }

    @Override // ru.mail.serverapi.AuthorizationAwareCommand
    public CommandStatus<?> getAuthorizationStatus() {
        Object result = getResult();
        return result instanceof CommandStatus ? (CommandStatus) result : new CommandStatus.OK();
    }

    protected String getLogin() {
        return this.mLogin;
    }

    public AuthorizedCompositeCommand(Context context, boolean z10, String str, FolderState folderState) {
        this.mContext = context;
        this.mLogin = str;
        this.mFolderState = folderState;
        this.mNotifyAuthFailure = z10;
        Locator locatorFrom = Locator.from(context);
        AccountManagerSettings accountManagerSettings = (AccountManagerSettings) locatorFrom.locate(AccountManagerSettings.class);
        this.mAccountManagerSettings = accountManagerSettings;
        this.mAnalytics = (Analytics) locatorFrom.locate(Analytics.class);
        this.mAuthManager = new CommandAuthManager(context, accountManagerSettings, str);
    }
}
