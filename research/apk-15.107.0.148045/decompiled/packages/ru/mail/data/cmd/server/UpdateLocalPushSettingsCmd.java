package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import ru.mail.core.di.DataManagerEntryPoint;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.DatabaseCommandBase;
import ru.mail.data.cmd.database.GetFoldersMultiaccCommand;
import ru.mail.data.cmd.database.pushfilters.CheckDiffInPushFilterDbCommand;
import ru.mail.data.cmd.database.pushfilters.SaveNewPushFilterItemsDbCommand;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.pushfilters.PushFilter;
import ru.mail.logic.pushfilters.PushFilterEntity;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.serverapi.DependentStatusCmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class UpdateLocalPushSettingsCmd extends DependentStatusCmd {
    private final List<PushFilterEntity> mActualFilters;
    private final MailboxContext mailboxContext;

    public UpdateLocalPushSettingsCmd(Context context, MailboxContext mailboxContext) {
        super(context, (Class<?>) GetSocialAndServicesPushFiltersCommand.class, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
        this.mailboxContext = mailboxContext;
        this.mActualFilters = new ArrayList();
        addCommand(mailboxContext.createTransport().createGetSocialAndServicesPushFiltersCommand(context, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext)));
    }

    private void onFiltersChecked(AsyncDbHandler.CommonResponse<PushFilterEntity, Integer> commonResponse) {
        if (((Boolean) commonResponse.getObj()).booleanValue()) {
            addCommand(new SaveNewPushFilterItemsDbCommand(getContext(), this.mActualFilters));
        }
    }

    private void onFiltersSaved() {
        DataManagerEntryPoint.dataManager(getContext()).postResourceChanged(PushFilterEntity.CONTENT_URI.buildUpon().appendEncodedPath(PushFilterEntity.TABLE_NAME).build());
    }

    private void onFoldersLoaded(AsyncDbHandler.CommonResponse<MailBoxFolder, Integer> commonResponse) {
        List<MailBoxFolder> list = commonResponse.getList();
        if (list != null && DatabaseCommandBase.statusOK(commonResponse)) {
            for (MailBoxFolder mailBoxFolder : list) {
                this.mActualFilters.add(new PushFilterEntity(mailBoxFolder.getId().longValue(), PushFilter.Type.FOLDER, mailBoxFolder.getAccountName(), mailBoxFolder.getName(getContext())));
            }
        }
        addCommand(new CheckDiffInPushFilterDbCommand(getContext(), this.mActualFilters));
    }

    private void onGetSocialAndServicePushFiltersCompleted(CommandStatus<?> commandStatus) {
        if (commandStatus instanceof CommandStatus.OK) {
            this.mActualFilters.addAll((List) commandStatus.getData());
        }
        addCommand(new GetFoldersMultiaccCommand(getContext()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.BaseDependentStatusCmd, ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if (command instanceof GetSocialAndServicesPushFiltersCommand) {
            onGetSocialAndServicePushFiltersCompleted((CommandStatus) t10);
            return t10;
        }
        if (command instanceof GetFoldersMultiaccCommand) {
            onFoldersLoaded((AsyncDbHandler.CommonResponse) t10);
            return t10;
        }
        if (command instanceof CheckDiffInPushFilterDbCommand) {
            onFiltersChecked((AsyncDbHandler.CommonResponse) t10);
            return t10;
        }
        if (command instanceof SaveNewPushFilterItemsDbCommand) {
            onFiltersSaved();
        }
        return t10;
    }
}
