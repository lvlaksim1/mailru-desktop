package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import ru.mail.data.entities.AttachCloudStock;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.serverapi.AuthorizedCommandImpl;
import ru.mail.util.config.MigrateToPostUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class CloudAttachmentsRemover extends CommandGroup {
    private final List<Command> mRemoveAttachFromBundleCommands = new ArrayList();

    @Nullable
    private Object mResult;

    public CloudAttachmentsRemover(Context context, MailboxContext mailboxContext, List<AttachCloudStock> list) {
        for (AttachCloudStock attachCloudStock : list) {
            AuthorizedCommandImpl authorizedCommandImpl = new AuthorizedCommandImpl(context, new RemoveFromCloudBundle(context, new RemoveFromCloudBundle.Params(mailboxContext, CommonDataManager.from(context), attachCloudStock.getFileId(), attachCloudStock.getBundleId()), MigrateToPostUtils.is12166Enabled(context)), MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
            this.mRemoveAttachFromBundleCommands.add(authorizedCommandImpl);
            addCommand(authorizedCommandImpl);
        }
    }

    @Override // ru.mail.mailbox.cmd.CommandGroup
    @CheckForNull
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if (this.mRemoveAttachFromBundleCommands.contains(command) && !(t10 instanceof CommandStatus.OK)) {
            removeAllCommands();
            this.mResult = t10;
        }
        return t10;
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onExecutionComplete() {
        Object obj = this.mResult;
        if (obj == null) {
            setResult(new CommandStatus.OK());
        } else {
            setResult(obj);
        }
    }
}
