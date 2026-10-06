package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.InsertUserProfileDataCommand;
import ru.mail.data.cmd.database.LoadAccountsInMailCacheCmd;
import ru.mail.data.cmd.database.UpdateMailboxEnteredBetaProgramOnWebCmd;
import ru.mail.data.cmd.database.UpdateMailboxThemeCmd;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.data.entities.MailboxProfileUtils;
import ru.mail.locator.Locator;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.RefreshUserDataSettings;
import ru.mail.logic.content.feature.features.UserDataFeature;
import ru.mail.logic.content.impl.BaseMailboxContext;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mailapp.service.profilesharing.UserProfileData;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.serverapi.AuthorizedCommandImpl;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class RefreshUserDataCmd extends AuthorizedCommandImpl {
    private static final Log LOG = Log.getLog("RefreshUserDataCmd");
    public static final long WEEK = 604800000;
    private int accountRefreshed;
    private int accountsRequestedRefresh;
    private boolean launchedShortVersion;
    private final Context mApplicationContext;

    public RefreshUserDataCmd(Context context, MailboxContext mailboxContext) {
        super(context, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
        this.launchedShortVersion = false;
        this.accountsRequestedRefresh = 0;
        this.accountRefreshed = 0;
        Context applicationContext = context.getApplicationContext();
        this.mApplicationContext = applicationContext;
        addCommand(new LoadAccountsInMailCacheCmd(applicationContext));
    }

    private void addFullCommand(List<MailboxProfile> list) {
        MailboxProfileUtils.filterUnauthorized(getContext(), list);
        Iterator<MailboxProfile> it = list.iterator();
        while (it.hasNext()) {
            BaseMailboxContext baseMailboxContext = new BaseMailboxContext(it.next());
            if (baseMailboxContext.isFeatureSupported(UserDataFeature.INSTANCE, new Void[0])) {
                this.accountsRequestedRefresh++;
                addCommand(shouldUseGolangApi() ? new GolangGetUserDataCommand(this.mApplicationContext, new GolangGetUserDataCommand.Params(baseMailboxContext, CommonDataManager.from(getContext()))) : new GetUserDataCommand(this.mApplicationContext, new GetUserDataCommand.Params(baseMailboxContext, CommonDataManager.from(getContext()))));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T addRefreshCommand(T t10) {
        List<T> list;
        AsyncDbHandler.CommonResponse commonResponse = (AsyncDbHandler.CommonResponse) t10;
        if (commonResponse.isFailed() || (list = commonResponse.getList()) == null) {
            return t10;
        }
        LinkedList linkedList = new LinkedList(list);
        if (RefreshUserDataSettings.from(this.mApplicationContext).getUserRefreshDate().before(new Date(System.currentTimeMillis() - 604800000))) {
            LOG.d("Refresh user data with full cmd");
            addFullCommand(linkedList);
            return t10;
        }
        DTOConfiguration.Config.UserShort userShortTimeoutConfig = getUserShortTimeoutConfig();
        if (!userShortTimeoutConfig.getEnabled()) {
            LOG.d("Skip refresh with user short command");
            return t10;
        }
        Date userRefreshDateShort = RefreshUserDataSettings.from(this.mApplicationContext).getUserRefreshDateShort();
        Date date = new Date(System.currentTimeMillis() - userShortTimeoutConfig.getTimeout());
        if (userRefreshDateShort.before(date)) {
            LOG.d("Refresh user data with short cmd");
            addShortCommand(linkedList);
            return t10;
        }
        LOG.d("Skip refresh with short cmd, last refresh: " + userRefreshDateShort + "; refresh available if last was before " + date);
        return t10;
    }

    private void addShortCommand(List<MailboxProfile> list) {
        MailboxProfileUtils.filterUnauthorized(getContext(), list);
        Iterator<MailboxProfile> it = list.iterator();
        while (it.hasNext()) {
            BaseMailboxContext baseMailboxContext = new BaseMailboxContext(it.next());
            if (baseMailboxContext.isFeatureSupported(UserDataFeature.INSTANCE, new Void[0])) {
                GolangUserShortCommand golangUserShortCommand = new GolangUserShortCommand(getContext(), new ServerCommandEmailParams(MailboxContextUtil.getAccountInfo(baseMailboxContext, CommonDataManager.from(getContext())), MailboxContextUtil.getFolderState(baseMailboxContext)));
                this.launchedShortVersion = true;
                addCommand(golangUserShortCommand);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MailboxContext getMailboxContextFromGetUserDataParams(Command command) {
        if (command instanceof GetUserDataCommand) {
            return ((GetUserDataCommand.Params) ((GetUserDataCommand) command).getParams()).getMailboxContext();
        }
        if (command instanceof GolangGetUserDataCommand) {
            return ((GolangGetUserDataCommand.Params) ((GolangGetUserDataCommand) command).getParams()).getMailboxContext();
        }
        throw new IllegalArgumentException("Unsupported command class: " + command.getClass().getSimpleName());
    }

    private DTOConfiguration.Config.UserShort getUserShortTimeoutConfig() {
        return ((ConfigurationRepository) Locator.from(getContext()).locate(ConfigurationRepository.class)).getConfiguration().getUserShortTimeout();
    }

    private void insertUserProfileDataToDb(@NotNull GetUserDataResult getUserDataResult) {
        addCommand(new InsertUserProfileDataCommand(this.mApplicationContext, new UserProfileData(getUserDataResult.getEmail(), getUserDataResult.getFirstName(), getUserDataResult.getLastName(), getUserDataResult.getBirthday(), getUserDataResult.getPhone(), getUserDataResult.isPhoneVerified())));
    }

    private boolean isGetUserDataCommand(Command command) {
        return (command instanceof GetUserDataCommand) || (command instanceof GolangGetUserDataCommand);
    }

    private boolean isInsertUserProfileDataCommand(Command command) {
        return command instanceof InsertUserProfileDataCommand;
    }

    private boolean isUpdateMailboxEnteredBetaProgramOnWebCommand(Command command) {
        return command instanceof UpdateMailboxEnteredBetaProgramOnWebCmd;
    }

    private boolean isUserShortDataCommand(Command command) {
        return command instanceof GolangUserShortCommand;
    }

    private boolean shouldUseGolangApi() {
        return ConfigurationRepository.from(this.mApplicationContext).getConfiguration().isNewMetaThreadsSettingsEnabled();
    }

    private void updateEnteredBetaProgram(String str, boolean z10) {
        this.accountsRequestedRefresh++;
        LOG.d("Update entered beta program on web for cmd: " + z10);
        addCommand(new UpdateMailboxEnteredBetaProgramOnWebCmd(this.mApplicationContext, str, z10));
    }

    private void updateTheme(String str, String str2) {
        addCommand(new UpdateMailboxThemeCmd(this.mApplicationContext, str, str2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T updateUserData(Command<?, T> command, T t10) {
        if (!(t10 instanceof CommandStatus.OK)) {
            return t10;
        }
        GetUserDataResult getUserDataResult = (GetUserDataResult) ((CommandStatus.OK) t10).getData();
        insertUserProfileDataToDb(getUserDataResult);
        updateTheme(getUserDataResult.getEmail(), getUserDataResult.getTheme());
        updateEnteredBetaProgram(getUserDataResult.getEmail(), getUserDataResult.getEnteredBetaOnWeb());
        updateVerifiedPhoneOperator(getMailboxContextFromGetUserDataParams(command));
        return t10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> T updateUserShortData(Command<?, T> command, T t10) {
        if (!(t10 instanceof CommandStatus.OK)) {
            return t10;
        }
        GolangUserShortCommand.UserData userData = (GolangUserShortCommand.UserData) ((CommandStatus.OK) t10).getData();
        String str = userData.getLogin() + "@" + userData.getDomain();
        LOG.d("Update entered beta program on web for short cmd: " + userData.getIsEnteredBetaProgram());
        updateEnteredBetaProgram(str, userData.getIsEnteredBetaProgram());
        return t10;
    }

    private void updateVerifiedPhoneOperator(MailboxContext mailboxContext) {
        addCommand(new GetUserVerifiedPhone(this.mApplicationContext, new ServerCommandEmailParams(MailboxContextUtil.getAccountInfo(mailboxContext, CommonDataManager.from(getContext())), MailboxContextUtil.getFolderState(mailboxContext)), MigrateToPostUtils.is12170Enabled(this.mApplicationContext)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if ((command instanceof LoadAccountsInMailCacheCmd) && t10 != 0) {
            return (T) addRefreshCommand(t10);
        }
        if (isUserShortDataCommand(command) && t10 != 0) {
            return (T) updateUserShortData(command, t10);
        }
        if (isGetUserDataCommand(command) && t10 != 0) {
            return (T) updateUserData(command, t10);
        }
        if (t10 != 0 && ((isUpdateMailboxEnteredBetaProgramOnWebCommand(command) || isInsertUserProfileDataCommand(command)) && ((AsyncDbHandler.CommonResponse) t10).getCount() > 0)) {
            this.accountRefreshed++;
        }
        return t10;
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onExecutionComplete() {
        int i10 = this.accountsRequestedRefresh;
        if (i10 > 0 && i10 == this.accountRefreshed) {
            if (this.launchedShortVersion) {
                LOG.d("Update short cmd refresh time");
                RefreshUserDataSettings.from(this.mApplicationContext).setRefreshDateShort(new Date());
                return;
            } else {
                LOG.d("Update cmd refresh time");
                RefreshUserDataSettings.from(this.mApplicationContext).setRefreshDate(new Date());
                return;
            }
        }
        LOG.d("Not refreshed update time: accountsRequestedRefresh: " + this.accountsRequestedRefresh + " accountRefreshed: " + this.accountRefreshed);
    }
}
