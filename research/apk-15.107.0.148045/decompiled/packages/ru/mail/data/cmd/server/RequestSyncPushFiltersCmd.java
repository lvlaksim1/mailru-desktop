package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.commons.collections4.CollectionUtils;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.DatabaseCommandBase;
import ru.mail.data.cmd.database.GetMailboxProfilesCommand;
import ru.mail.data.cmd.database.UpdateAccountSyncStatus;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.logic.content.CollectionToIdsTransformer;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.sdk.BuildConfigVariablesHolder;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class RequestSyncPushFiltersCmd extends CommandGroup {
    private final Context mContext;
    private String mProfile;

    public RequestSyncPushFiltersCmd(Context context) {
        this.mContext = context.getApplicationContext();
        addCommand(new GetMailboxProfilesCommand(getContext()));
    }

    private Context getContext() {
        return this.mContext;
    }

    private void onGetMailBoxProfiles(AsyncDbHandler.CommonResponse<MailboxProfile, ?> commonResponse) {
        List<MailboxProfile> list = commonResponse.getList();
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mProfile = list.get(0).getLogin();
        addCommand(new UpdateAccountSyncStatus(getContext(), new UpdateAccountSyncStatus.Params(CollectionUtils.collect(list, new CollectionToIdsTransformer()), false)));
    }

    private void onUpdateAccountSyncStatus() {
        addCommand(new RequestSyncCommand(getContext(), new RequestSyncCommand.Params(new Account(this.mProfile, BuildConfigVariablesHolder.accountType), BuildConfigVariablesHolder.offlineContentProviderAuthority, new Bundle())));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if ((command instanceof GetMailboxProfilesCommand) && DatabaseCommandBase.statusOK(t10)) {
            onGetMailBoxProfiles((AsyncDbHandler.CommonResponse) t10);
            return t10;
        }
        if (command instanceof UpdateAccountSyncStatus) {
            onUpdateAccountSyncStatus();
        }
        return t10;
    }
}
