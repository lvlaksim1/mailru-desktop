package ru.mail.data.cmd.server;

import android.content.Context;
import android.os.Build;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import ru.mail.analytics.MailAnalyticsKt;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.data.entities.Attach;
import ru.mail.logic.cmd.cloud.StockAddCommand;
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
import ru.mail.mails.R;
import ru.mail.serverapi.AuthorizedCommandImpl;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class CloudAttachmentsUploader extends CommandGroup implements ProgressListener<Float> {
    private static final Log LOG = Log.getLog("CloudAttachmentsUploader");
    private static final int MAX_ATTEMPT_COUNT = 3;
    private AuthorizedCommandImpl mAddAttachToBundleCommand;
    private final Queue<MailAttacheEntry> mAttachments;
    private String mBundleId;
    private final Context mContext;
    private AuthorizedCommandImpl mCreateBundleCommand;
    private float mCurrentAttachBytes;
    private MailAttacheEntry mCurrentAttachment;
    private CloudAttachmentInfo mCurrentAttachmentInfo;
    private AuthorizedCommandImpl mGetAttachInfoCommand;
    private String mLoaderUrl;
    private final MailAnalyticsKt mMailAnalyticsKt;
    private final MailboxContext mMailboxContext;
    private final Set<Long> mMiniCloudAttachmentsSizes = new HashSet();

    @Nullable
    private final ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> mProgressListener;

    @Nullable
    private Object mResult;
    private StockAddCommand mStockAddCommand;
    private AuthorizedUploadCommand mUploadAttachCommand;
    private float mUploadedAttachesBytes;

    /* JADX INFO: compiled from: ProGuard */
    private static class AuthorizedUploadCommand extends AuthorizedCommandWithProgress<Float> {
        private String mAttachName;

        protected AuthorizedUploadCommand(Context context, MailboxContext mailboxContext, ProgressListener<Float> progressListener) {
            super(context, mailboxContext, false, progressListener);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void addUploadCommand(UploadCloudRequest uploadCloudRequest) {
            this.mAttachName = ((UploadCloudRequest.Params) uploadCloudRequest.getParams()).getFullName();
            addCommand(uploadCloudRequest);
        }

        public String getAttachName() {
            return this.mAttachName;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result extends CommandStatus.OK<List<Attach>> {
        public Result(List<Attach> list) {
            super(list);
        }
    }

    public CloudAttachmentsUploader(Context context, MailboxContext mailboxContext, List<MailAttacheEntry> list, @Nullable ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> progressListener) {
        this.mContext = context;
        this.mMailboxContext = mailboxContext;
        this.mProgressListener = progressListener;
        ArrayDeque<MailAttacheEntry> arrayDeque = new ArrayDeque(list);
        this.mAttachments = arrayDeque;
        LOG.d("init CloudAttachmentsUploader with following attaches: ");
        for (MailAttacheEntry mailAttacheEntry : arrayDeque) {
            LOG.d("Attach: " + mailAttacheEntry.getDisplayName() + ", size: " + mailAttacheEntry.getSize());
        }
        this.mMailAnalyticsKt = MailAppDependencies.analyticsKt(this.mContext);
        addCreateBundleCommand();
    }

    private void addAttachmentInfoCommand() {
        GetCloudAttachmentInfo.Params params = new GetCloudAttachmentInfo.Params(this.mMailboxContext, CommonDataManager.from(this.mContext), this.mCurrentAttachmentInfo.getHash(), this.mLoaderUrl);
        Context context = this.mContext;
        Context context2 = this.mContext;
        AuthorizedCommandImpl authorizedCommandImpl = new AuthorizedCommandImpl(context, new GetCloudAttachmentInfo(context2, params, MigrateToPostUtils.is12165Enabled(context2)), MailboxContextUtil.getLogin(this.mMailboxContext), MailboxContextUtil.getFolderState(this.mMailboxContext));
        this.mGetAttachInfoCommand = authorizedCommandImpl;
        addCommand(authorizedCommandImpl);
    }

    private void addAttachmentStockCommand(MailAttacheEntry mailAttacheEntry) {
        StockAddCommand stockAddCommand = new StockAddCommand(StockAddCommand.createParams(this.mBundleId, mailAttacheEntry));
        this.mStockAddCommand = stockAddCommand;
        addCommand(stockAddCommand);
    }

    private void addAttachmentToBundleCommand() {
        AddToCloudBundle.Params params = new AddToCloudBundle.Params(this.mCurrentAttachment.getFullName(), this.mCurrentAttachmentInfo.getHash(), this.mCurrentAttachmentInfo.getSize(), this.mBundleId, MailboxContextUtil.getAccountInfo(this.mMailboxContext, CommonDataManager.from(this.mContext)), MailboxContextUtil.getFolderState(this.mMailboxContext));
        Context context = this.mContext;
        AuthorizedCommandImpl authorizedCommandImpl = new AuthorizedCommandImpl(this.mContext, new AddToCloudBundle(context, params, MigrateToPostUtils.is11954Enabled(context)), MailboxContextUtil.getLogin(this.mMailboxContext), MailboxContextUtil.getFolderState(this.mMailboxContext));
        this.mAddAttachToBundleCommand = authorizedCommandImpl;
        addCommand(authorizedCommandImpl);
    }

    private void addCreateBundleCommand() {
        Context context = this.mContext;
        Context context2 = this.mContext;
        AuthorizedCommandImpl authorizedCommandImpl = new AuthorizedCommandImpl(context, new CreateCloudBundle(context2, this.mMailboxContext, MigrateToPostUtils.is12166Enabled(context2)), MailboxContextUtil.getLogin(this.mMailboxContext), MailboxContextUtil.getFolderState(this.mMailboxContext));
        this.mCreateBundleCommand = authorizedCommandImpl;
        addCommand(authorizedCommandImpl);
    }

    private void addUploadAttachmentCommand(long j10) {
        this.mUploadAttachCommand = new AuthorizedUploadCommand(this.mContext, this.mMailboxContext, this);
        UploadCloudRequest.Params params = new UploadCloudRequest.Params(this.mMailboxContext, CommonDataManager.from(this.mContext), this.mLoaderUrl, this.mCurrentAttachment, this.mCurrentAttachmentInfo.getHash(), j10, this.mCurrentAttachmentInfo.getSize());
        Context context = this.mContext;
        this.mUploadAttachCommand.addUploadCommand(new UploadCloudRequest(context, params, this.mUploadAttachCommand, MigrateToPostUtils.is12165Enabled(context)));
        addCommand(this.mUploadAttachCommand);
    }

    private <T> void handleAddAttachToBundleCommandResult(T t10) {
        if (!(t10 instanceof CommandStatus.OK)) {
            handleError(t10);
            return;
        }
        this.mUploadedAttachesBytes += this.mCurrentAttachBytes;
        this.mCurrentAttachBytes = 0.0f;
        uploadNextAttachment();
    }

    private <T> void handleAddStockToBundleCommandResult(T t10) {
        Log log = LOG;
        log.d("Add to stock attach " + this.mCurrentAttachment.getDisplayName() + " result: " + t10);
        if (t10 instanceof CommandStatus.OK) {
            this.mMiniCloudAttachmentsSizes.add(Long.valueOf(this.mCurrentAttachment.getSize()));
            uploadNextAttachment();
            return;
        }
        boolean zContains = this.mMiniCloudAttachmentsSizes.contains(Long.valueOf(this.mCurrentAttachment.getSize()));
        log.d("Attach with size " + this.mCurrentAttachment.getSize() + " was already added: " + zContains);
        if (zContains) {
            uploadNextAttachment();
        } else {
            handleError(t10);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void handleCreateBundleCommandResult(T t10) {
        if (!(t10 instanceof CommandStatus.OK)) {
            handleError(t10);
            return;
        }
        CreateCloudBundle.Result result = (CreateCloudBundle.Result) ((CommandStatus.OK) t10).getData();
        this.mLoaderUrl = result.getLoaderUrl();
        this.mBundleId = result.getBundleId();
        uploadNextAttachment();
    }

    private <T> void handleError(T t10) {
        removeAllCommands();
        this.mResult = t10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void handleGetAttachInfoCommandResult(T t10) {
        if (!(t10 instanceof CommandStatus.OK)) {
            if (t10 instanceof UploadCloudRequest.CHUNK_NOT_FOUND) {
                addUploadAttachmentCommand(0L);
                return;
            } else {
                handleError(t10);
                return;
            }
        }
        GetCloudAttachmentInfo.Result result = (GetCloudAttachmentInfo.Result) ((CommandStatus.OK) t10).getData();
        long uploadedSize = result.getUploadedSize();
        if (result.getLoaderUrl() != null) {
            this.mLoaderUrl = result.getLoaderUrl();
        }
        if (uploadedSize < this.mCurrentAttachmentInfo.getSize()) {
            addUploadAttachmentCommand(uploadedSize);
        } else {
            addAttachmentToBundleCommand();
        }
    }

    private <T> void handleUploadAttachCommandResult(T t10) {
        if (t10 instanceof CommandStatus.OK) {
            addAttachmentToBundleCommand();
        } else if (this.mCurrentAttachmentInfo.getUploadAttemptCount() >= 3) {
            handleError(t10);
        } else {
            this.mCurrentAttachmentInfo.incrementAttemptCount();
            addAttachmentInfoCommand();
        }
    }

    private void uploadNextAttachment() {
        MailAttacheEntry mailAttacheEntryPoll = this.mAttachments.poll();
        if (mailAttacheEntryPoll != null) {
            this.mCurrentAttachment = mailAttacheEntryPoll;
            try {
                this.mCurrentAttachmentInfo = CloudAttachmentInfo.prepareInfo(this.mContext, mailAttacheEntryPoll);
            } catch (SecurityException unused) {
                this.mMailAnalyticsKt.onAttachUploadSecurityException(Build.VERSION.SDK_INT, mailAttacheEntryPoll.getClass().getSimpleName());
            }
            if (this.mCurrentAttachment.getSourceType() == MailAttacheEntry.SourceType.MINICLOUD) {
                addAttachmentStockCommand(mailAttacheEntryPoll);
            } else if (this.mCurrentAttachmentInfo == null) {
                handleError(new CommandStatus.SIMPLE_ERROR(this.mContext.getString(R.string.attach_was_not_found)));
            } else {
                addAttachmentInfoCommand();
            }
        }
    }

    @Override // ru.mail.mailbox.cmd.CommandGroup
    @CheckForNull
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if (isCancelled()) {
            handleError(new CommandStatus.CANCELLED());
            return t10;
        }
        if (command == this.mCreateBundleCommand) {
            handleCreateBundleCommandResult(t10);
            return t10;
        }
        if (command == this.mGetAttachInfoCommand) {
            handleGetAttachInfoCommandResult(t10);
            return t10;
        }
        if (command == this.mUploadAttachCommand) {
            handleUploadAttachCommandResult(t10);
            return t10;
        }
        if (command == this.mAddAttachToBundleCommand) {
            handleAddAttachToBundleCommandResult(t10);
            return t10;
        }
        if (command == this.mStockAddCommand) {
            handleAddStockToBundleCommandResult(t10);
        }
        return t10;
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onExecutionComplete() {
        Object obj = this.mResult;
        if (obj == null) {
            setResult(new CommandStatus.OK(this.mBundleId));
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
