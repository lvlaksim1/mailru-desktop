package ru.mail.data.cmd.server;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.provider.MediaStore;
import androidx.annotation.RequiresApi;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import ru.mail.android_utils.SdkUtils;
import ru.mail.auth.request.AccountInfo;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cmd.AttachRequest;
import ru.mail.data.cmd.database.DeleteMessageCommand;
import ru.mail.data.cmd.fs.AttachRequestCompositeCommand;
import ru.mail.data.entities.Attach;
import ru.mail.data.entities.AttachCloud;
import ru.mail.data.entities.AttachCloudExtKt;
import ru.mail.data.entities.AttachCloudStock;
import ru.mail.logic.cmd.attachments.MoveAttachmentCommand;
import ru.mail.logic.cmd.attachments.MoveAttachmentToUriCommand;
import ru.mail.logic.cmd.attachments.SaveAttachmentToDownloads;
import ru.mail.logic.cmd.attachments.UpdateAttachStatus;
import ru.mail.logic.content.AttachInformation;
import ru.mail.logic.content.AttachmentHelper;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.feature.features.CloudDownloadFeature;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.mails.R;
import ru.mail.network.AccountAndIDParams;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.serverapi.BaseDependentStatusCmd;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.util.ReferenceTableStateKeeper;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class AttachesDownloadCmd extends BaseDependentStatusCmd {
    public static final String CLOUD_REFERER = "cloud_referer";
    private static final Log LOG = Log.getLog("AttachesDownloadCmd");
    private final Map<String, AttachCloudStock> mAttachCloudStockMap;
    private final AtomicBoolean mAttachDecryption;
    private final Collection<Command<?, ?>> mAttachRequestCommands;
    private final long mAttachesTotalSize;
    private long mCurrentAttachProgressInBytes;
    private final boolean mDecryptAfterLoad;
    private final Uri mDestination;
    private final ProgressListener<ProgressHolder> mExternalProgress;
    private final String mFrom;
    private final Map<String, AttachRequest.Result> mGroupResult;
    private final MailboxContext mMailboxContext;
    private final String mMsgId;
    private long mTotalAttachesProgressInBytes;

    /* JADX INFO: compiled from: ProGuard */
    class SingleCmdProgressListener implements ProgressListener<AttachRequest.ProgressData> {
        private final String mAttach;

        private SingleCmdProgressListener(String str) {
            this.mAttach = str;
        }

        @Override // ru.mail.mailbox.cmd.ProgressListener
        public void updateProgress(AttachRequest.ProgressData progressData) {
            Boolean bool;
            AttachesDownloadCmd.this.mCurrentAttachProgressInBytes = progressData.getProgress();
            long j10 = AttachesDownloadCmd.this.mTotalAttachesProgressInBytes + AttachesDownloadCmd.this.mCurrentAttachProgressInBytes;
            if (AttachesDownloadCmd.this.mAttachDecryption.get()) {
                bool = Boolean.FALSE;
                AttachesDownloadCmd.this.mAttachDecryption.set(false);
            } else {
                bool = null;
            }
            AttachesDownloadCmd.this.mExternalProgress.updateProgress(new ProgressHolder(j10, AttachesDownloadCmd.this.mAttachesTotalSize, this.mAttach, bool));
        }
    }

    public AttachesDownloadCmd(Context context, MailboxContext mailboxContext, Collection<AttachInformation> collection, String str, String str2, Uri uri, ProgressListener<ProgressHolder> progressListener, boolean z10) {
        super(context, false, MailboxContextUtil.getLogin(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
        this.mGroupResult = new HashMap();
        this.mAttachRequestCommands = Collections.synchronizedList(new ArrayList());
        this.mAttachCloudStockMap = new HashMap();
        this.mAttachDecryption = new AtomicBoolean(false);
        this.mMailboxContext = mailboxContext;
        this.mDecryptAfterLoad = z10;
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Think before you run cmd with empty collection!!!!");
        }
        this.mExternalProgress = progressListener;
        this.mMsgId = str2;
        this.mFrom = str;
        this.mDestination = uri;
        this.mAttachesTotalSize = Attach.calcTotalAttachesSize(collection);
        addSingleCommand(collection, str, str2);
    }

    private void addResultChild(AttachRequest.Params params, AttachRequest.Result result) {
        this.mGroupResult.put(params.getAttach().getUri(), result);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void addSingleAttachDownloadCmd(String str, String str2, AttachInformation attachInformation) {
        Command<?, ?> commandCreateAttachRequestCmd = createAttachRequestCmd(str, str2, attachInformation, this.mExternalProgress != null ? new SingleCmdProgressListener(attachInformation.getFullName()) : null);
        this.mAttachRequestCommands.add(commandCreateAttachRequestCmd);
        addCommand(commandCreateAttachRequestCmd);
    }

    private void addSingleCommand(Collection<AttachInformation> collection, String str, String str2) {
        boolean zIsUnifiedAttachDownloadEnabled = ConfigurationRepository.from(getContext()).getConfiguration().isUnifiedAttachDownloadEnabled();
        for (AttachInformation attachInformation : collection) {
            if (zIsUnifiedAttachDownloadEnabled) {
                addSingleAttachDownloadCmd(str, str2, attachInformation);
            } else if (attachInformation instanceof AttachCloud) {
                addCommand(new GetCloudDispatcherCommand(getContext(), new GetCloudDispatcherCommand.Params((AttachCloud) attachInformation, getLogin(), getFolderState()), MigrateToPostUtils.is12165Enabled(this.mContext)));
            } else if (attachInformation instanceof AttachCloudStock) {
                AttachCloudStock attachCloudStock = (AttachCloudStock) attachInformation;
                this.mAttachCloudStockMap.put(attachCloudStock.getFileId(), attachCloudStock);
            } else {
                addSingleAttachDownloadCmd(str, str2, attachInformation);
            }
        }
        if (this.mAttachCloudStockMap.isEmpty()) {
            return;
        }
        addCommand(new MessageAttachesRequestCommand(getContext(), new MessageAttachesRequestCommand.Params(str2, new String[]{"cloud_stock"}, str, new AccountInfo(getLogin(), CommonDataManager.from(getContext())), getFolderState()), MigrateToPostUtils.is12163Enabled(getContext()), CommonDataManager.from(getContext()).isFeatureSupported(CloudDownloadFeature.INSTANCE, getContext())));
    }

    private void addUpdateAttachCmd(AttachRequestCompositeCommand attachRequestCompositeCommand, Priority priority, ExecutorSelector executorSelector) {
        if (attachRequestCompositeCommand.getIsExecuted()) {
            executeCommand(new UpdateAttachStatus(getContext(), attachRequestCompositeCommand.getRequestParams().getAttach().getClass(), attachRequestCompositeCommand), priority, executorSelector);
        }
    }

    private HostProvider getCloudHostProvider() {
        return new PreferenceHostProvider(getContext(), CLOUD_REFERER, R.string.cloud_referer_default_scheme, R.string.cloud_referer_default_host);
    }

    @RequiresApi(29)
    private void moveAttachToDownloads(AttachInformation attachInformation, File file, ContentResolver contentResolver) {
        addCommandAtFront(new SaveAttachmentToDownloads(contentResolver, new SaveAttachmentToDownloads.Params(attachInformation, file)));
    }

    private void moveAttachToPath(String str, String str2, File file) {
        addCommandAtFront(new MoveAttachmentCommand(getContext(), new MoveAttachmentCommand.Params(str, str2, file)));
    }

    private void moveAttachToUri(File file, Uri uri, ContentResolver contentResolver) {
        addCommandAtFront(new MoveAttachmentToUriCommand(contentResolver, new MoveAttachmentToUriCommand.Params(uri, file)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void onGetAttachesOk(T t10) {
        for (AttachCloudStock attachCloudStock : ((MessageAttachesRequestCommand.Result) ((CommandStatus.OK) t10).getData()).getAttachmentsCloudStock()) {
            if (this.mAttachCloudStockMap.containsKey(attachCloudStock.getFileId())) {
                AttachCloudStock attachCloudStock2 = this.mAttachCloudStockMap.get(attachCloudStock.getFileId());
                attachCloudStock2.setDownloadLink(attachCloudStock.getUri());
                Command<?, ?> commandCreateAttachRequestCmd = createAttachRequestCmd(this.mFrom, this.mMsgId, attachCloudStock2, new SingleCmdProgressListener(attachCloudStock2.getFullName()));
                this.mAttachRequestCommands.add(commandCreateAttachRequestCmd);
                addCommandAtFront(commandCreateAttachRequestCmd);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void onGetCloudDispatcherCmdOk(Command<?, T> command, T t10) {
        String str = (String) ((CommandStatus.OK) t10).getData();
        AttachCloud attachCloud = ((GetCloudDispatcherCommand.Params) ((GetCloudDispatcherCommand) command).getParams()).getAttachCloud();
        updateDownloadLink(str, attachCloud);
        AttachRequestCompositeCommand attachRequestCompositeCommand = new AttachRequestCompositeCommand(getContext(), new AttachRequestCommand.Params(attachCloud, this.mFrom, this.mMsgId, AttachCloudExtKt.getReferer(attachCloud, getContext()), new AccountInfo(getLogin(), CommonDataManager.from(getContext())), getFolderState()), getCloudHostProvider(), new SingleCmdProgressListener(attachCloud.getFullName()), false);
        this.mAttachRequestCommands.add(attachRequestCompositeCommand);
        addCommandAtFront(attachRequestCompositeCommand);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void onSingleAttachCmdOk(Command<?, T> command, T t10) {
        this.mTotalAttachesProgressInBytes += this.mCurrentAttachProgressInBytes;
        this.mCurrentAttachProgressInBytes = 0L;
        AttachRequest.Params requestParams = ((AttachRequest.Command) command).getRequestParams();
        String fileName = requestParams.getFileName();
        AttachRequest.Result result = (AttachRequest.Result) ((CommandStatus.OK) t10).getData();
        addResultChild(requestParams, result);
        LOG.d("attach downloaded = " + result.getLoadFile().getFile().getAbsolutePath() + "; mDestination = " + this.mDestination);
        File attachPrefetchedFile = AttachmentHelper.getAttachPrefetchedFile(getContext(), getLogin(), requestParams.getMsgId(), requestParams.getFrom(), requestParams.getAttach());
        if (result.getLoadFile().isEncrypted() && this.mDecryptAfterLoad) {
            this.mAttachDecryption.set(true);
            this.mExternalProgress.updateProgress(new ProgressHolder(0L, 0L, "", Boolean.TRUE));
            result.getLoadFile().decryptIfEncrypted();
        }
        if (this.mDestination != null) {
            if (!SdkUtils.hasQ()) {
                moveAttachToPath(fileName, this.mDestination.getPath(), attachPrefetchedFile);
            } else if (this.mDestination.equals(MediaStore.Downloads.EXTERNAL_CONTENT_URI)) {
                moveAttachToDownloads(requestParams.getAttach(), attachPrefetchedFile, getContext().getContentResolver());
            } else {
                moveAttachToUri(attachPrefetchedFile, this.mDestination, getContext().getContentResolver());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void onSingleCmdAttachNotFound(Command<?, T> command, T t10) {
        setupError(t10);
        AttachRequest.Params requestParams = ((AttachRequest.Command) command).getRequestParams();
        addCommandAtFront(new DeleteMessageCommand(getContext(), new AccountAndIDParams(requestParams.getMsgId(), getLogin()), ReferenceTableStateKeeper.from(getContext()).getReferenceRepoFactory()));
        LOG.d("404 for file : " + requestParams.getAttach().getFullName() + " msgId : " + this.mMsgId);
    }

    private <T> void setupError(T t10) {
        removeAllCommands();
        setResult(t10);
    }

    private void updateDownloadLink(String str, AttachCloud attachCloud) {
        attachCloud.setDispatcherUrl(str);
    }

    public Command<?, ?> createAttachRequestCmd(String str, String str2, AttachInformation attachInformation, SingleCmdProgressListener singleCmdProgressListener) {
        return this.mMailboxContext.createTransport().createLoadAttachCmd(getContext(), this.mMailboxContext, str, str2, attachInformation, singleCmdProgressListener);
    }

    @Override // ru.mail.serverapi.BaseDependentStatusCmd
    protected boolean isDependentCommand(Command<?, ?> command) {
        return this.mAttachRequestCommands.contains(command);
    }

    @Override // ru.mail.mailbox.cmd.CommandGroup, ru.mail.mailbox.cmd.Command
    protected Object onExecute(ExecutorSelector executorSelector) {
        return NetworkCommand.statusOK(getResult()) ? new CommandStatus.OK(this.mGroupResult) : super.onExecute(executorSelector);
    }

    @Override // ru.mail.serverapi.BaseDependentStatusCmd, ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if (this.mAttachRequestCommands.contains(command)) {
            if ((command instanceof AttachRequestCompositeCommand) && NetworkCommand.statusOK(t10) && !isCancelled()) {
                addUpdateAttachCmd((AttachRequestCompositeCommand) command, priority, executorSelector);
            }
            if (t10 instanceof MailCommandStatus.ERROR_ATTACH_NOT_FOUND) {
                onSingleCmdAttachNotFound(command, t10);
                return t10;
            }
            if (!NetworkCommand.statusOK(t10) || isCancelled()) {
                setupError(t10);
                return t10;
            }
            onSingleAttachCmdOk(command, t10);
            return t10;
        }
        if (command instanceof GetCloudDispatcherCommand) {
            if (NetworkCommand.statusOK(t10)) {
                onGetCloudDispatcherCmdOk(command, t10);
                return t10;
            }
            setupError(t10);
            return t10;
        }
        if (command instanceof MessageAttachesRequestCommand) {
            if (NetworkCommand.statusOK(t10)) {
                onGetAttachesOk(t10);
                return t10;
            }
            setupError(t10);
            return t10;
        }
        if (command instanceof MoveAttachmentCommand) {
            if (NetworkCommand.statusOK(t10)) {
                LOG.d("MoveAttachCommand ok ");
                return t10;
            }
            LOG.d("MoveAttachCommand error ");
            setupError(t10);
            return t10;
        }
        if (command instanceof DeleteMessageCommand) {
            LOG.d(t10 + "from " + command);
            return t10;
        }
        if (command instanceof MoveAttachmentToUriCommand) {
            if (NetworkCommand.statusOK(t10)) {
                LOG.d("UploadAttachmentToUriCommand ok ");
                return t10;
            }
            setupError(t10);
            return t10;
        }
        if (command instanceof SaveAttachmentToDownloads) {
            if (NetworkCommand.statusOK(t10)) {
                LOG.d("SaveAttachmentToDownloads ok ");
                return t10;
            }
            setupError(t10);
        }
        return t10;
    }
}
