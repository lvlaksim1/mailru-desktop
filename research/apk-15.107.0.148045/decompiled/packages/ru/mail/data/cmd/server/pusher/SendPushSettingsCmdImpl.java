package ru.mail.data.cmd.server.pusher;

import android.content.Context;
import android.preference.PreferenceManager;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.auth.request.AccountInfo;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.GetMailboxProfilesCommand;
import ru.mail.data.cmd.database.UpdateAccountSyncStatus;
import ru.mail.data.cmd.database.pushfilters.CommitPushFiltersDbCommand;
import ru.mail.data.cmd.database.pushfilters.CommitPushFiltersForProfilesDbCommand;
import ru.mail.data.cmd.database.pushfilters.LoadFiltersDbCommand;
import ru.mail.data.cmd.database.pushfilters.RollbackPushFiltersDbCommand;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.data.entities.MailboxProfileUtils;
import ru.mail.locator.Locator;
import ru.mail.logic.cmd.SyncMailItemsCommand;
import ru.mail.logic.content.CollectionToIdsTransformer;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.feature.features.NotificationsFeature;
import ru.mail.logic.content.impl.BaseMailboxContext;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.logic.pushfilters.FilterAccessor;
import ru.mail.logic.sync.AppSettingsSync;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NoAuthInfo;
import ru.mail.portal.app.adapter.notifications.tags.AppTagsStorageFactory;
import ru.mail.portal.app.adapter.notifications.tags.Tag;
import ru.mail.util.log.Log;
import ru.mail.util.push.model.MultiAccountSettings;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class SendPushSettingsCmdImpl extends SendPushSettingsCmd {
    public static final String KEY_PREF_MAIL_APP_PUSH = "mail_app_push";
    private static final Log LOG = Log.getLog("SendPushSettingsCmd");
    private List<MailboxProfile> mProfiles;
    private FilterAccessor mPushFilterAccessor;

    /* JADX INFO: compiled from: ProGuard */
    private static class IdFromNoAuthInfoTransformer implements Transformer<NoAuthInfo, String> {
        private IdFromNoAuthInfoTransformer() {
        }

        @Override // org.apache.commons.collections4.Transformer
        public String transform(NoAuthInfo noAuthInfo) {
            return noAuthInfo.getLogin();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class IsNotificationsSuppportedPredicate implements Predicate<MailboxProfile> {
        private IsNotificationsSuppportedPredicate() {
        }

        @Override // org.apache.commons.collections4.Predicate
        public boolean evaluate(MailboxProfile mailboxProfile) {
            return new BaseMailboxContext(mailboxProfile).isFeatureSupported(NotificationsFeature.INSTANCE, new Void[0]);
        }
    }

    public SendPushSettingsCmdImpl(Context context, MailboxContext mailboxContext) {
        super(context, SendPushSettingsCommand.class, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
        addInitCommand(context);
    }

    private void addInitCommand(Context context) {
        addCommand(new GetMailboxProfilesCommand(context));
    }

    private Set<Tag> getEnabledTagsForMailApp() {
        Set<Tag> tagsForApp = ConfigurationRepository.from(getContext()).getConfiguration().getPortal().getNotifications().getTagsForApp("MailApp");
        Set<Integer> disabledTagIds = AppTagsStorageFactory.INSTANCE.createStorage(getContext()).getDisabledTagIds("MailApp");
        HashSet hashSet = new HashSet();
        for (Tag tag : tagsForApp) {
            if (!disabledTagIds.contains(Integer.valueOf(tag.getIdForPusher()))) {
                hashSet.add(tag);
            }
        }
        return hashSet;
    }

    private void handleInitCommandResult(AsyncDbHandler.CommonResponse commonResponse) {
        if (commonResponse == null) {
            setResult(new CommandStatus.ERROR("Profiles from database not loaded"));
            return;
        }
        if (commonResponse.getList() == null || commonResponse.getList().isEmpty()) {
            setResult(new CommandStatus.ERROR("Profiles list empty"));
            return;
        }
        ArrayList arrayList = new ArrayList(commonResponse.getList());
        MailboxProfileUtils.filterUnauthorized(getContext(), arrayList);
        List<MailboxProfile> listSelect = ListUtils.select(arrayList, new IsNotificationsSuppportedPredicate());
        List listSubtract = ListUtils.subtract(arrayList, listSelect);
        if (listSubtract.size() > 0) {
            Collection collectionCollect = CollectionUtils.collect(listSubtract, new CollectionToIdsTransformer());
            addCommand(new CommitPushFiltersForProfilesDbCommand(getContext(), new CommitPushFiltersForProfilesDbCommand.Params(collectionCollect)));
            addCommand(new UpdateAccountSyncStatus(getContext(), new UpdateAccountSyncStatus.Params(collectionCollect, true)));
        }
        if (listSelect.size() <= 0) {
            this.mProfiles = new ArrayList();
            return;
        }
        this.mProfiles = listSelect;
        addCommand(new UpdateAccountSyncStatus(getContext(), new UpdateAccountSyncStatus.Params(CollectionUtils.collect(listSelect, new CollectionToIdsTransformer()), false)));
        addCommand(new LoadFiltersDbCommand(getContext()));
    }

    @Nullable
    public static Boolean isImportantReminderEnabled(Context context) {
        DTOConfiguration.Config.ReminderPushInSettings reminderPushSettingsInConfig = ((ConfigurationRepository) Locator.from(context).locate(ConfigurationRepository.class)).getConfiguration().getReminderPushSettingsInConfig();
        boolean enabled = reminderPushSettingsInConfig.getEnabled();
        boolean zContains = PreferenceManager.getDefaultSharedPreferences(context).contains(AppSettingsSync.KEY_PREF_PUSH_IMPORTANT_REMINDER);
        boolean resetSettingAfterDisabled = reminderPushSettingsInConfig.getResetSettingAfterDisabled();
        LOG.d("Important push settings enabled: " + enabled + " set by user: " + zContains + " reset after disabled: " + resetSettingAfterDisabled);
        if (enabled || (zContains && !reminderPushSettingsInConfig.getResetSettingAfterDisabled())) {
            return Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean(AppSettingsSync.KEY_PREF_PUSH_IMPORTANT_REMINDER, true));
        }
        if (!zContains || !reminderPushSettingsInConfig.getResetSettingAfterDisabled()) {
            return null;
        }
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(AppSettingsSync.KEY_PREF_PUSH_IMPORTANT_REMINDER).apply();
        return null;
    }

    private static boolean isPushEnabledWithoutPermissionCheck(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("push", true);
    }

    public static boolean isPushForMailAppEnabled(Context context) {
        if (isPushEnabledWithoutPermissionCheck(context)) {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_MAIL_APP_PUSH, true);
        }
        return false;
    }

    private void onCreateSubscribePushSettingsParamsCmdCompleted(MultiAccountSettings multiAccountSettings) {
        addCommand(new SendPushSettingsCommand(getContext(), multiAccountSettings));
    }

    private void onLoadPushFilterAccessor(AsyncDbHandler.CommonResponse commonResponse) {
        this.mPushFilterAccessor = (FilterAccessor) commonResponse.getObj();
        boolean zIsPushForMailAppEnabled = isPushForMailAppEnabled(getContext());
        Log log = LOG;
        log.d("onLoadPushFilterAccessor isPushForMailAppEnabled: " + zIsPushForMailAppEnabled);
        Boolean boolIsImportantReminderEnabled = isImportantReminderEnabled(getContext());
        log.d("onLoadPushFilterAccessor isPushForMailAppEnabled: " + zIsPushForMailAppEnabled);
        addCommand(new CreateSubscribePushSettingsCmd(new CreateSubscribePushSettingsCmd.Params(zIsPushForMailAppEnabled, this.mProfiles, this.mPushFilterAccessor, new AccountInfo(getLogin(), CommonDataManager.from(getContext())), getFolderState(), getEnabledTagsForMailApp(), boolIsImportantReminderEnabled)));
    }

    private void onSendPushSettingsComplete(SendPushSettingsCommand sendPushSettingsCommand) {
        Collection collectionCollect = CollectionUtils.collect(this.mProfiles, new CollectionToIdsTransformer());
        CommandStatus<?> result = sendPushSettingsCommand.getResult();
        LOG.d("onSendPushSettingsComplete - command: " + SendPushSettingsCommand.class.getName() + "; status: " + result.toString());
        if (NetworkCommand.statusOK(result)) {
            removeAllCommands();
            addCommand(new UpdateAccountSyncStatus(getContext(), new UpdateAccountSyncStatus.Params(collectionCollect, true)));
            addCommand(new CommitPushFiltersDbCommand(getContext(), this.mPushFilterAccessor.getLastActionId()));
            return;
        }
        if (!(result instanceof NetworkCommandStatus.NO_AUTH_MULTIPLE)) {
            if (SyncMailItemsCommand.resultedWithRecoverableError(sendPushSettingsCommand)) {
                addCommand(new UpdateAccountSyncStatus(getContext(), new UpdateAccountSyncStatus.Params(collectionCollect, false)));
                return;
            } else {
                addCommand(new RollbackPushFiltersDbCommand(getContext()));
                addCommand(new UpdateAccountSyncStatus(getContext(), new UpdateAccountSyncStatus.Params(collectionCollect, false)));
                return;
            }
        }
        Collection collectionCollect2 = CollectionUtils.collect(((NetworkCommandStatus.NO_AUTH_MULTIPLE) result).getData(), new IdFromNoAuthInfoTransformer());
        if (collectionCollect2 != null && !collectionCollect2.isEmpty()) {
            addCommand(new UpdateAccountSyncStatus(getContext(), new UpdateAccountSyncStatus.Params(collectionCollect, false)));
            return;
        }
        addCommand(new CommitPushFiltersForProfilesDbCommand(getContext(), new CommitPushFiltersForProfilesDbCommand.Params(this.mPushFilterAccessor.getLastActionId(), collectionCollect)));
        addCommand(new UpdateAccountSyncStatus(getContext(), new UpdateAccountSyncStatus.Params(collectionCollect, true)));
    }

    @Override // ru.mail.serverapi.AuthorizedCommandImpl
    protected void onAuthCmdCompleted(CommandStatus<?> commandStatus) {
        MailAppDependencies.analytics(getContext()).logAuthCmdSucceed(commandStatus == null ? "null" : commandStatus.getClass().getSimpleName());
        super.onAuthCmdCompleted(commandStatus);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.BaseDependentStatusCmd, ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @org.jetbrains.annotations.Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if (command instanceof GetMailboxProfilesCommand) {
            handleInitCommandResult((AsyncDbHandler.CommonResponse) t10);
            return t10;
        }
        if (command instanceof LoadFiltersDbCommand) {
            onLoadPushFilterAccessor((AsyncDbHandler.CommonResponse) t10);
            return t10;
        }
        if (command instanceof CreateSubscribePushSettingsCmd) {
            onCreateSubscribePushSettingsParamsCmdCompleted((MultiAccountSettings) t10);
            return t10;
        }
        if (command instanceof SendPushSettingsCommand) {
            onSendPushSettingsComplete((SendPushSettingsCommand) command);
        }
        return t10;
    }
}
