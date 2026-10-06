package ru.mail.data.cmd.server;

import android.content.Context;
import javax.annotation.Nullable;
import ru.mail.core.di.DataManagerEntryPoint;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.glasha.di.SharedFoldersModuleEntryPoint;
import ru.mail.logic.cmd.MoveOperation;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.serverapi.AuthorizedCommandImpl;
import ru.mail.util.config.MigrateToPostUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class TornadoMoveMessageCommandGroup extends AuthorizedCommandImpl implements MoveOperation {
    private final TornadoMoveMessage.Params mParams;

    public TornadoMoveMessageCommandGroup(Context context, TornadoMoveMessage.Params params) {
        super(context, MailboxContextUtil.getLogin(params.getMailboxContext()), MailboxContextUtil.getFolderState(params.getMailboxContext()));
        this.mParams = params;
        boolean zIs12158Enabled = MigrateToPostUtils.is12158Enabled(context);
        if (params.getFolderIdTo() != -1) {
            addCommand(new TornadoMoveMessage(context, params, zIs12158Enabled));
            return;
        }
        DataManager dataManager = DataManagerEntryPoint.dataManager(context);
        addCommand(new TornadoMoveMessage(context, new TornadoMoveMessage.Params(params.getMailboxContext(), dataManager, SharedFoldersModuleEntryPoint.folderGrantsManager(context).getTrashId(params.getFolderOwner(), MailBoxFolder.trashFolderId()), params.getMovedMessagesIds()), zIs12158Enabled));
        addCommand(new TornadoRemoveMessage(context, new TornadoBaseMoveMessage.Params(params.getMailboxContext(), dataManager, params.getMovedMessagesIds()), zIs12158Enabled));
    }

    @Override // ru.mail.logic.cmd.MoveOperation
    public String[] getMovedMessagesIds() {
        return this.mParams.getMovedMessagesIds();
    }

    @Override // ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        setResult(t10);
        return t10;
    }
}
