package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import ru.mail.analytics.MessageSendAnalytics;
import ru.mail.data.entities.Attach;
import ru.mail.locator.Locator;
import ru.mail.logic.content.MailAttacheEntry;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.util.config.MigrateToPostUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class TornadoAttachmentsUploader extends CommandGroup implements ProgressListener<Float> {
    private float mCurrentAttachBytes;

    @Nullable
    private final ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> mProgressListener;

    @Nullable
    private Object mResult;
    private final List<Attach> mUploadAttaches = new LinkedList();
    private final List<Command> mUploadAttachmentCommand = new ArrayList();
    private float mUploadedAttachesBytes;

    /* JADX INFO: compiled from: ProGuard */
    private static class AuthorizedUploadCommand extends AuthorizedCommandWithProgress<Float> {
        private TornadoUploadRequest mUploadCommand;

        protected AuthorizedUploadCommand(Context context, MailboxContext mailboxContext, ProgressListener<Float> progressListener) {
            super(context, mailboxContext, false, progressListener);
        }

        public void addUploadCommand(TornadoUploadRequest tornadoUploadRequest) {
            this.mUploadCommand = tornadoUploadRequest;
            addCommand(tornadoUploadRequest);
        }

        public String getAttachName() {
            return this.mUploadCommand.getAttachEntry().getFullName();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result extends CommandStatus.OK<List<Attach>> {
        public Result(List<Attach> list) {
            super(list);
        }
    }

    public TornadoAttachmentsUploader(Context context, MailboxContext mailboxContext, String str, List<MailAttacheEntry> list, @Nullable ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> progressListener) {
        this.mProgressListener = progressListener;
        CommonDataManager commonDataManagerFrom = CommonDataManager.from(context);
        for (MailAttacheEntry mailAttacheEntry : list) {
            AuthorizedUploadCommand authorizedUploadCommand = new AuthorizedUploadCommand(context, mailboxContext, this);
            Context context2 = context;
            authorizedUploadCommand.addUploadCommand(new TornadoUploadRequest(context2, new TornadoUploadRequest.Params(str, mailAttacheEntry, MailboxContextUtil.getAccountInfo(mailboxContext, commonDataManagerFrom), MailboxContextUtil.getFolderState(mailboxContext)), authorizedUploadCommand, MigrateToPostUtils.is12163Enabled(context), (MessageSendAnalytics) Locator.locate(context, MessageSendAnalytics.class), new ErrorStringProviderImpl(context)));
            this.mUploadAttachmentCommand.add(authorizedUploadCommand);
            addCommand(authorizedUploadCommand);
            context = context2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandGroup
    @CheckForNull
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if (this.mUploadAttachmentCommand.contains(command)) {
            if (t10 instanceof CommandStatus.OK) {
                this.mUploadedAttachesBytes += this.mCurrentAttachBytes;
                this.mCurrentAttachBytes = 0.0f;
                TornadoUploadRequest.Result result = (TornadoUploadRequest.Result) ((CommandStatus.OK) t10).getData();
                Attach attach = new Attach();
                attach.setFileId(result.getAttachId());
                this.mUploadAttaches.add(attach);
                return t10;
            }
            removeAllCommands();
            this.mResult = t10;
        }
        return t10;
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onExecutionComplete() {
        Object obj = this.mResult;
        if (obj == null) {
            setResult(new Result(this.mUploadAttaches));
        } else {
            setResult(obj);
        }
    }

    @Override // ru.mail.mailbox.cmd.ProgressListener
    public void updateProgress(Float f10) {
        this.mCurrentAttachBytes = f10.floatValue();
        if (this.mProgressListener != null) {
            this.mProgressListener.updateProgress(new ru.mail.logic.cmd.attachments.ProgressData((long) (this.mUploadedAttachesBytes + this.mCurrentAttachBytes), ((AuthorizedUploadCommand) getCurrentCommand()).getAttachName()));
        }
    }
}
