package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.CommandDelayer;
import ru.mail.data.cmd.CommandDelayerImpl;
import ru.mail.logic.cmd.AttachmentManagementCommand;
import ru.mail.logic.cmd.DelayResolver;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.serverapi.AuthorizedCommandImpl;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class TornadoRedirectCommand extends AuthorizedCommandImpl implements AttachmentManagementCommand {
    protected static final Log LOG = Log.getLog("TornadoRedirectCommand");
    private final CommandDelayer mSendDelayer;
    private final TornadoSendParams mSendParams;

    public TornadoRedirectCommand(@NotNull Context context, @NotNull MailboxContext mailboxContext, @NotNull TornadoSendParams tornadoSendParams, boolean z10, DelayResolver delayResolver) {
        super(context, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
        this.mSendParams = tornadoSendParams;
        this.mSendDelayer = new CommandDelayerImpl(delayResolver.resolve(context));
        addCommand(new TornadoRedirectRequest(context, new TornadoRedirectRequest.Params(mailboxContext, CommonDataManager.from(context), tornadoSendParams.getSourceId(), tornadoSendParams.getTo()), z10));
    }

    private void cancelSending() {
        onCancelled();
        setCancelled(true);
    }

    @Override // ru.mail.logic.cmd.AttachmentManagementCommand
    public long getSendDelayMillis() {
        return this.mSendDelayer.getDelayMillis();
    }

    @Override // ru.mail.logic.cmd.AttachmentManagementCommand
    public long getTotalSize() {
        return 0L;
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onDone() {
        super.onDone();
        if (isCancelled() || !statusOK()) {
            LOG.d("Message redirecting has been cancelled or status is not OK");
            return;
        }
        LOG.d("Message with id '" + this.mSendParams.getId() + "' has been redirected successfully");
    }

    @Override // ru.mail.mailbox.cmd.CommandGroup, ru.mail.mailbox.cmd.Command
    @Nullable
    protected Object onExecute(ExecutorSelector executorSelector) {
        this.mSendDelayer.start();
        if (this.mSendDelayer.waitUntilTimeout()) {
            return super.onExecute(executorSelector);
        }
        cancelSending();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.Command
    public synchronized void setResult(Object obj) {
        try {
            if (isCancelled()) {
                setResult(new CommandStatus.CANCELLED());
            }
            super.setResult(obj);
        } catch (Throwable th2) {
            throw th2;
        }
    }

    @Override // ru.mail.logic.cmd.AttachmentManagementCommand
    public void skipDelay() {
        this.mSendDelayer.skipDelay();
    }
}
