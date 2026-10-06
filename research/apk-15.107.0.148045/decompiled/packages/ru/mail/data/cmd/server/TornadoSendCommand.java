package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.data.cmd.CommandDelayer;
import ru.mail.data.cmd.CommandDelayerImpl;
import ru.mail.data.entities.Attach;
import ru.mail.data.entities.AttachCloudStock;
import ru.mail.logic.cmd.AttachmentManagementCommand;
import ru.mail.logic.cmd.DelayResolver;
import ru.mail.logic.content.HtmlFormatter;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.UploadType;
import ru.mail.logic.content.feature.features.CloudUploadFeature;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailbox.cmd.CancelableCommand;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.serverapi.AuthorizedCancellableCommand;
import ru.mail.serverapi.AuthorizedCommandImpl;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.ui.fragments.adapter.AttachmentsEditor;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;
import ru.mail.utils.RandomStringGenerator;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class TornadoSendCommand extends CommandGroup implements AttachmentManagementCommand, CancelableCommand {
    private static final Log LOG = Log.getLog("TornadoSendCommand");

    @Nullable
    private final Command mAfterSendCommand;
    private final Context mContext;
    private volatile boolean mIsSendCompleted;
    private final MailboxContext mMailboxContext;
    private AuthorizedCommandImpl mReattachCommand;
    private AuthorizedCommandImpl mRemoveAttachmentsCommand;
    private final RequestStrategy mRequestStrategy;
    private final CommandDelayer mSendDelayer;
    private AuthorizedCancellableCommand mSendMessageCommand;
    private TornadoSendParams mSendParams;
    private final UploadProgressHandler mUploadProgressHandler;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ProGuard */
    public static abstract class RequestStrategy {
        private static final /* synthetic */ RequestStrategy[] $VALUES = $values();
        public static final RequestStrategy SAVE_DRAFT;
        public static final RequestStrategy SEND_LATER;
        public static final RequestStrategy SEND_NEW;

        /* JADX INFO: renamed from: ru.mail.data.cmd.server.TornadoSendCommand$RequestStrategy$1, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass1 extends RequestStrategy {
            @Override // ru.mail.data.cmd.server.TornadoSendCommand.RequestStrategy
            protected TornadoSendRequest getRequest(Context context, TornadoSendParams tornadoSendParams) {
                return new TornadoSendRequest(context, tornadoSendParams, MigrateToPostUtils.is12158Enabled(context));
            }

            private AnonymousClass1(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.data.cmd.server.TornadoSendCommand$RequestStrategy$2, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass2 extends RequestStrategy {
            @Override // ru.mail.data.cmd.server.TornadoSendCommand.RequestStrategy
            protected TornadoSendRequest getRequest(Context context, TornadoSendParams tornadoSendParams) {
                return new TornadoDraftRequest(context, tornadoSendParams, MigrateToPostUtils.is12158Enabled(context));
            }

            private AnonymousClass2(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.data.cmd.server.TornadoSendCommand$RequestStrategy$3, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass3 extends RequestStrategy {
            @Override // ru.mail.data.cmd.server.TornadoSendCommand.RequestStrategy
            protected TornadoSendRequest getRequest(Context context, TornadoSendParams tornadoSendParams) {
                return new TornadoScheduleRequest(context, tornadoSendParams, MigrateToPostUtils.is12158Enabled(context));
            }

            private AnonymousClass3(String str, int i10) {
                super(str, i10);
            }
        }

        private static /* synthetic */ RequestStrategy[] $values() {
            return new RequestStrategy[]{SEND_NEW, SAVE_DRAFT, SEND_LATER};
        }

        static {
            SEND_NEW = new AnonymousClass1("SEND_NEW", 0);
            SAVE_DRAFT = new AnonymousClass2("SAVE_DRAFT", 1);
            SEND_LATER = new AnonymousClass3("SEND_LATER", 2);
        }

        public static RequestStrategy valueOf(String str) {
            return (RequestStrategy) Enum.valueOf(RequestStrategy.class, str);
        }

        public static RequestStrategy[] values() {
            return (RequestStrategy[]) $VALUES.clone();
        }

        protected abstract TornadoSendRequest getRequest(Context context, TornadoSendParams tornadoSendParams);

        private RequestStrategy(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class UploadProgressHandler {
        private final ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> mBaseProgressListener;
        private final List<SingleProgressListener> mProgressListeners = new ArrayList();

        /* JADX INFO: compiled from: ProGuard */
        private class SingleProgressListener implements ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> {
            private long mUploadedBytes;

            private SingleProgressListener() {
            }

            @Override // ru.mail.mailbox.cmd.ProgressListener
            public void updateProgress(ru.mail.logic.cmd.attachments.ProgressData progressData) {
                this.mUploadedBytes = progressData.getProgress();
                UploadProgressHandler.this.handleProgress(progressData);
            }
        }

        public UploadProgressHandler(ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> progressListener) {
            this.mBaseProgressListener = progressListener;
        }

        private long getTotalUploadBytes() {
            Iterator<SingleProgressListener> it = this.mProgressListeners.iterator();
            long j10 = 0;
            while (it.hasNext()) {
                j10 += it.next().mUploadedBytes;
            }
            return j10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleProgress(ru.mail.logic.cmd.attachments.ProgressData progressData) {
            if (this.mBaseProgressListener != null) {
                this.mBaseProgressListener.updateProgress(new ru.mail.logic.cmd.attachments.ProgressData(getTotalUploadBytes(), progressData.getAttachName()));
            }
        }

        public SingleProgressListener createProgressListener() {
            SingleProgressListener singleProgressListener = new SingleProgressListener();
            this.mProgressListeners.add(singleProgressListener);
            return singleProgressListener;
        }
    }

    public TornadoSendCommand(Context context, MailboxContext mailboxContext, TornadoSendParams tornadoSendParams, RequestStrategy requestStrategy, DelayResolver delayResolver, @Nullable Command command) {
        this(context, mailboxContext, tornadoSendParams, requestStrategy, new CommandDelayerImpl(delayResolver.resolve(context)), command);
    }

    private void addCloudUploadAttachmentsCommand() {
        addCommand(new CloudAttachmentsUploader(this.mContext, this.mMailboxContext, this.mSendParams.getAttachmentsEditor().getAddedAttachmentsByUploadType(UploadType.CLOUD), this.mUploadProgressHandler.createProgressListener()));
    }

    private void addDefaultUploadAttachmentsCommand() {
        addCommand(new TornadoAttachmentsUploader(this.mContext, this.mMailboxContext, this.mSendParams.getId(), this.mSendParams.getAttachmentsEditor().getAddedAttachmentsByUploadType(UploadType.DEFAULT), this.mUploadProgressHandler.createProgressListener()));
    }

    private void addReattachCommand() {
        TornadoReattachRequest.Params params = new TornadoReattachRequest.Params(this.mSendParams.getId(), this.mSendParams.getSourceId(), MailboxContextUtil.getAccountInfo(this.mMailboxContext, CommonDataManager.from(this.mContext)), MailboxContextUtil.getFolderState(this.mMailboxContext));
        Context context = this.mContext;
        Context context2 = this.mContext;
        AuthorizedCommandImpl authorizedCommandImpl = new AuthorizedCommandImpl(context, new TornadoReattachRequest(context2, params, MigrateToPostUtils.is12163Enabled(context2)), MailboxContextUtil.getLogin(this.mMailboxContext), MailboxContextUtil.getFolderState(this.mMailboxContext));
        this.mReattachCommand = authorizedCommandImpl;
        addCommand(authorizedCommandImpl);
    }

    private void addRemoveAttachmentsCommand(List<Attach> list) {
        TornadoRemoveRequest.Params params = new TornadoRemoveRequest.Params(this.mSendParams.getId(), list, MailboxContextUtil.getAccountInfo(this.mMailboxContext, CommonDataManager.from(this.mContext)), MailboxContextUtil.getFolderState(this.mMailboxContext));
        Context context = this.mContext;
        Context context2 = this.mContext;
        AuthorizedCommandImpl authorizedCommandImpl = new AuthorizedCommandImpl(context, new TornadoRemoveRequest(context2, params, MigrateToPostUtils.is12163Enabled(context2)), MailboxContextUtil.getLogin(this.mMailboxContext), MailboxContextUtil.getFolderState(this.mMailboxContext));
        this.mRemoveAttachmentsCommand = authorizedCommandImpl;
        addCommand(authorizedCommandImpl);
    }

    private void addRemoveCloudAttachmentsCommand(List<AttachCloudStock> list) {
        addCommand(new CloudAttachmentsRemover(this.mContext, this.mMailboxContext, list));
    }

    private void addSendMessageCommand() {
        Context context = this.mContext;
        AuthorizedCancellableCommand authorizedCancellableCommand = new AuthorizedCancellableCommand(context, this.mRequestStrategy.getRequest(context, this.mSendParams), MailboxContextUtil.getLogin(this.mMailboxContext), MailboxContextUtil.getFolderState(this.mMailboxContext));
        this.mSendMessageCommand = authorizedCancellableCommand;
        addCommand(authorizedCancellableCommand);
    }

    private void cancelSending() {
        onCancelled();
        setCancelled(true);
        setResult(new CommandStatus.CANCELLED());
    }

    private Context getContext() {
        return this.mContext;
    }

    private void onReattached(TornadoReattachRequest.Result result) {
        if (result.hasError()) {
            String errorReason = result.getErrorReason();
            LOG.w("Unable to reattach some files: " + errorReason);
            MailAppDependencies.analytics(getContext()).onReattachError(errorReason);
        }
        TornadoSendEditableParams tornadoSendEditableParamsEdit = this.mSendParams.edit(this.mMailboxContext, CommonDataManager.from(this.mContext));
        this.mSendParams = tornadoSendEditableParamsEdit;
        AttachmentsEditor attachmentsEditor = tornadoSendEditableParamsEdit.getAttachmentsEditor();
        List<String> removedInitialAttachesPartIds = attachmentsEditor.getRemovedInitialAttachesPartIds();
        List<Attach> inlineAttachments = result.getInlineAttachments();
        if (!inlineAttachments.isEmpty()) {
            tornadoSendEditableParamsEdit.setOriginalBodyHtml(HtmlFormatter.replaceAttachesWithCidLinks(tornadoSendEditableParamsEdit.getOriginalBodyHtml(), inlineAttachments));
        }
        if (CommonDataManager.from(getContext()).isFeatureSupported(this.mMailboxContext.getProfile().getLogin(), CloudUploadFeature.INSTANCE, getContext())) {
            ArrayList arrayList = new ArrayList();
            LinkedList linkedList = new LinkedList();
            for (TornadoReattachRequest.ReattachedCloudStock reattachedCloudStock : result.getCloudAttachments()) {
                if (removedInitialAttachesPartIds.contains(reattachedCloudStock.getOriginalId())) {
                    linkedList.add(reattachedCloudStock.getAttach());
                } else {
                    arrayList.add(reattachedCloudStock.getAttach());
                }
            }
            if (!arrayList.isEmpty()) {
                attachmentsEditor.setReattachedCloudStockAttachments(arrayList);
            }
            if (!linkedList.isEmpty()) {
                addRemoveCloudAttachmentsCommand(linkedList);
            }
        }
        LinkedList linkedList2 = new LinkedList();
        LinkedList linkedList3 = new LinkedList();
        for (Attach attach : result.getAttachments()) {
            if (removedInitialAttachesPartIds.contains(attach.getPartId())) {
                linkedList3.add(attach);
            } else {
                linkedList2.add(attach);
            }
        }
        attachmentsEditor.rememberAttachmentsExistingOnServer(linkedList2);
        removeAttachments(linkedList3);
    }

    private <T> void onSendingError(T t10) {
        setResult(t10);
        removeAllCommands();
        LOG.i("Message sending has been cancelled or status is not OK");
    }

    private void onSendingSuccess() {
        Command<?, ?> command = this.mAfterSendCommand;
        if (command != null) {
            addCommand(command);
        }
        setResult(new CommandStatus.OK());
        LOG.d("Message with id '" + this.mSendParams.getId() + "' has been sent successfully");
    }

    private void onUploaded(TornadoAttachmentsUploader.Result result) {
        TornadoSendEditableParams tornadoSendEditableParamsEdit = this.mSendParams.edit(this.mMailboxContext, CommonDataManager.from(this.mContext));
        tornadoSendEditableParamsEdit.getAttachmentsEditor().rememberAttachmentsExistingOnServer(result.getData());
        this.mSendParams = tornadoSendEditableParamsEdit;
        uploadAttachmentsCloudOrSendMessage();
    }

    private void onUploadedToCloud(String str) {
        TornadoSendEditableParams tornadoSendEditableParamsEdit = this.mSendParams.edit(this.mMailboxContext, CommonDataManager.from(this.mContext));
        tornadoSendEditableParamsEdit.getAttachmentsEditor().setCloudAttachmentBundleId(str);
        this.mSendParams = tornadoSendEditableParamsEdit;
        addSendMessageCommand();
    }

    private void reattach() {
        if (this.mSendParams.getAttachmentsEditor().getInitialAttachmentsSet().isEmpty()) {
            uploadAttachmentsDefault();
        } else {
            addReattachCommand();
        }
    }

    private void removeAttachments(List<Attach> list) {
        if (list.isEmpty()) {
            uploadAttachmentsDefault();
        } else {
            addRemoveAttachmentsCommand(list);
        }
    }

    private void uploadAttachmentsCloudOrSendMessage() {
        if (this.mSendParams.getAttachmentsEditor().getAddedAttachmentsByUploadType(UploadType.CLOUD).isEmpty()) {
            addSendMessageCommand();
        } else {
            addCloudUploadAttachmentsCommand();
        }
    }

    private void uploadAttachmentsDefault() {
        if (this.mSendParams.getAttachmentsEditor().getAddedAttachmentsByUploadType(UploadType.DEFAULT).isEmpty()) {
            uploadAttachmentsCloudOrSendMessage();
        } else {
            addDefaultUploadAttachmentsCommand();
        }
    }

    @Override // ru.mail.logic.cmd.AttachmentManagementCommand
    public long getSendDelayMillis() {
        return this.mSendDelayer.getDelayMillis();
    }

    public TornadoSendParams getSendParams() {
        return this.mSendParams;
    }

    @Override // ru.mail.logic.cmd.AttachmentManagementCommand
    public long getTotalSize() {
        AttachmentsEditor attachmentsEditor = this.mSendParams.getAttachmentsEditor();
        return attachmentsEditor.calculateFullSize(attachmentsEditor.getAddedAttachments());
    }

    @Override // ru.mail.mailbox.cmd.CancelableCommand
    public boolean isAlreadyDone() {
        return this.mIsSendCompleted;
    }

    @Override // ru.mail.mailbox.cmd.CommandGroup, ru.mail.mailbox.cmd.Command
    @Nullable
    protected Object onExecute(ExecutorSelector executorSelector) {
        this.mSendDelayer.start();
        return super.onExecute(executorSelector);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        if (command == this.mSendMessageCommand) {
            if (!this.mSendDelayer.waitUntilTimeout()) {
                cancelSending();
                return null;
            }
            this.mIsSendCompleted = true;
        }
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if (command == this.mReattachCommand) {
            if (!(t10 instanceof CommandStatus.OK) || isCancelled()) {
                onSendingError(t10);
                return t10;
            }
            onReattached((TornadoReattachRequest.Result) ((CommandStatus.OK) t10).getData());
            return t10;
        }
        if (command instanceof CloudAttachmentsRemover) {
            if (!(t10 instanceof CommandStatus.OK) || isCancelled()) {
                onSendingError(t10);
                return t10;
            }
        } else {
            if (command == this.mRemoveAttachmentsCommand) {
                if (!(t10 instanceof CommandStatus.OK) || isCancelled()) {
                    onSendingError(t10);
                    return t10;
                }
                uploadAttachmentsDefault();
                return t10;
            }
            if (command instanceof TornadoAttachmentsUploader) {
                if (!(t10 instanceof CommandStatus.OK) || isCancelled()) {
                    onSendingError(t10);
                    return t10;
                }
                onUploaded((TornadoAttachmentsUploader.Result) ((CommandStatus.OK) t10));
                return t10;
            }
            if (command instanceof CloudAttachmentsUploader) {
                if (!(t10 instanceof CommandStatus.OK) || isCancelled()) {
                    onSendingError(t10);
                    return t10;
                }
                onUploadedToCloud((String) ((CommandStatus.OK) t10).getData());
                return t10;
            }
            if (command == this.mSendMessageCommand) {
                if ((t10 instanceof CommandStatus.OK) && !isCancelled()) {
                    onSendingSuccess();
                    return t10;
                }
                if (t10 instanceof MailCommandStatus.FAILED_BACKEND_QUOTE) {
                    this.mSendParams = this.mSendParams.edit(this.mMailboxContext, CommonDataManager.from(this.mContext)).setBlockQuote(null);
                    addSendMessageCommand();
                    return t10;
                }
                this.mIsSendCompleted = this.mSendMessageCommand.isAlreadyDone();
                onSendingError(t10);
            }
        }
        return t10;
    }

    @Override // ru.mail.logic.cmd.AttachmentManagementCommand
    public void skipDelay() {
        this.mSendDelayer.skipDelay();
    }

    @VisibleForTesting
    public TornadoSendCommand(Context context, MailboxContext mailboxContext, TornadoSendParams tornadoSendParams, RequestStrategy requestStrategy, CommandDelayer commandDelayer, @Nullable Command command) {
        this.mContext = context;
        this.mMailboxContext = mailboxContext;
        this.mAfterSendCommand = command;
        TornadoSendEditableParams tornadoSendEditableParamsEdit = tornadoSendParams.edit(mailboxContext, CommonDataManager.from(context));
        tornadoSendEditableParamsEdit.setId(RandomStringGenerator.generateString(32));
        this.mSendParams = tornadoSendEditableParamsEdit;
        this.mUploadProgressHandler = new UploadProgressHandler(tornadoSendEditableParamsEdit.getProgressListener());
        this.mRequestStrategy = requestStrategy;
        this.mSendDelayer = commandDelayer;
        reattach();
    }
}
