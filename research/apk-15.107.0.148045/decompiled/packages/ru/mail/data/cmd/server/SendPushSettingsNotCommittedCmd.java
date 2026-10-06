package ru.mail.data.cmd.server;

import android.content.Context;
import javax.annotation.Nullable;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.GetPushFilterActionsCountCommand;
import ru.mail.data.cmd.server.pusher.SendPushSettingsCmd;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.serverapi.DependentStatusCmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class SendPushSettingsNotCommittedCmd extends DependentStatusCmd {
    private final MailboxContext mMailboxContext;

    public SendPushSettingsNotCommittedCmd(Context context, MailboxContext mailboxContext) {
        super(context, (Class<?>[]) new Class[]{SendPushSettingsCmd.class}, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
        this.mMailboxContext = mailboxContext;
        addCommand(new GetPushFilterActionsCountCommand(context));
        setResult(new CommandStatus.OK());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.BaseDependentStatusCmd, ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if ((command instanceof GetPushFilterActionsCountCommand) && t10 != 0 && ((AsyncDbHandler.CommonResponse) t10).getCount() > 0) {
            addCommand(this.mMailboxContext.createTransport().createSendPushSettingsCmd(getContext(), this.mMailboxContext));
        }
        return t10;
    }
}
