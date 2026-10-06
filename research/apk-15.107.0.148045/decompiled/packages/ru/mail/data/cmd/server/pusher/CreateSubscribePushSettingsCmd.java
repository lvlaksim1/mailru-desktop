package ru.mail.data.cmd.server.pusher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.arbiter.Pools;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.logic.pushfilters.FilterAccessor;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.portal.app.adapter.notifications.tags.Tag;
import ru.mail.serverapi.FolderState;
import ru.mail.util.push.model.MultiAccountSettings;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class CreateSubscribePushSettingsCmd extends Command<Params, MultiAccountSettings> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {
        private final AccountInfo mAccountInfo;
        private final Collection<String> mAccounts;
        private final Set<Tag> mEnabledTagsForMailApp;
        private final FilterAccessor mFilterAccessor;
        private final FolderState mFolderState;
        private final Boolean mIsImportantReminderEnabled;
        private final boolean mPushesForMailAppEnabled;

        public Params(boolean z10, @NotNull List<MailboxProfile> list, @Nullable FilterAccessor filterAccessor, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState, @NotNull Set<Tag> set, @Nullable Boolean bool) {
            this.mPushesForMailAppEnabled = z10;
            this.mFilterAccessor = filterAccessor;
            this.mAccountInfo = accountInfo;
            this.mFolderState = folderState;
            this.mEnabledTagsForMailApp = set;
            this.mAccounts = new ArrayList(list.size());
            Iterator<MailboxProfile> it = list.iterator();
            while (it.hasNext()) {
                this.mAccounts.add(it.next().getLogin());
            }
            this.mIsImportantReminderEnabled = bool;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Params params = (Params) obj;
                if (Boolean.valueOf(this.mPushesForMailAppEnabled).equals(Boolean.valueOf(params.mPushesForMailAppEnabled)) && Objects.equals(this.mAccountInfo, params.mAccountInfo) && Long.valueOf(this.mFolderState.getFolderId()).equals(Long.valueOf(params.mFolderState.getFolderId())) && Objects.equals(this.mFilterAccessor, params.mFilterAccessor) && Objects.equals(this.mAccounts, params.mAccounts) && Objects.equals(this.mEnabledTagsForMailApp, params.mEnabledTagsForMailApp) && this.mIsImportantReminderEnabled == params.mIsImportantReminderEnabled) {
                    return true;
                }
            }
            return false;
        }

        @NotNull
        public AccountInfo getAccountInfo() {
            return this.mAccountInfo;
        }

        @NotNull
        public Collection<String> getAccounts() {
            return this.mAccounts;
        }

        @NotNull
        public Set<Tag> getEnabledTagsForMailApp() {
            return this.mEnabledTagsForMailApp;
        }

        @Nullable
        public FilterAccessor getFilterAccessor() {
            return this.mFilterAccessor;
        }

        @Nullable
        public FolderState getFolderState() {
            return this.mFolderState;
        }

        public int hashCode() {
            return Objects.hash(this.mAccountInfo, Long.valueOf(this.mFolderState.getFolderId()), Boolean.valueOf(this.mPushesForMailAppEnabled), this.mAccounts, this.mFilterAccessor, this.mEnabledTagsForMailApp, this.mIsImportantReminderEnabled);
        }

        public Boolean isImportantReminderEnabled() {
            return this.mIsImportantReminderEnabled;
        }

        public boolean isPushesForMailAppEnabled() {
            return this.mPushesForMailAppEnabled;
        }
    }

    public CreateSubscribePushSettingsCmd(Params params) {
        super(params);
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor(Pools.COMPUTATION);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    public MultiAccountSettings onExecute(ExecutorSelector executorSelector) {
        Params params = getParams();
        return new MultiAccountSettings(params.isPushesForMailAppEnabled(), params.getAccounts(), params.getFilterAccessor(), params.getAccountInfo(), params.getFolderState(), params.getEnabledTagsForMailApp(), params.isImportantReminderEnabled());
    }
}
