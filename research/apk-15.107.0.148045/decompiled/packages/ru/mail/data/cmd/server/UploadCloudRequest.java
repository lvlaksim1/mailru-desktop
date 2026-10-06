package ru.mail.data.cmd.server;

import android.content.Context;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MessageSendAnalytics;
import ru.mail.locator.Locator;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailAttacheEntry;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.mails.R;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.requestbody.MultipartRequestBody;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.network.requestbody.StreamRequestBody;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class UploadCloudRequest extends UploadFileCommand<Params, EmptyResult, Float> {
    private static final Log LOG = Log.getLog("UploadCloudRequest");

    /* JADX INFO: compiled from: ProGuard */
    public static class CHUNK_NOT_FOUND extends CommandStatus.ERROR<Void> {
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {
        protected final MailAttacheEntry mAttachEntry;
        private final String mAttachHash;
        private final long mFileSize;
        private final String mLoaderUrl;
        private final long mOffset;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String str, @Nullable MailAttacheEntry mailAttacheEntry, @Nullable String str2, long j10, long j11) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mAttachEntry = mailAttacheEntry;
            this.mLoaderUrl = str;
            this.mAttachHash = str2;
            this.mOffset = j10;
            this.mFileSize = j11;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            if (this.mOffset != params.mOffset || this.mFileSize != params.mFileSize) {
                return false;
            }
            MailAttacheEntry mailAttacheEntry = this.mAttachEntry;
            if (mailAttacheEntry == null ? params.mAttachEntry != null : !mailAttacheEntry.equals(params.mAttachEntry)) {
                return false;
            }
            String str = this.mLoaderUrl;
            if (str == null ? params.mLoaderUrl != null : !str.equals(params.mLoaderUrl)) {
                return false;
            }
            String str2 = this.mAttachHash;
            String str3 = params.mAttachHash;
            if (str2 != null) {
                return str2.equals(str3);
            }
            return str3 == null;
        }

        public MailAttacheEntry getAttachEntry() {
            return this.mAttachEntry;
        }

        public String getAttachHash() {
            return this.mAttachHash;
        }

        public long getFileSize() {
            return this.mFileSize;
        }

        public String getFullName() {
            return this.mAttachEntry.getFullName();
        }

        public String getLoaderUrl() {
            return this.mLoaderUrl;
        }

        public long getOffset() {
            return this.mOffset;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            MailAttacheEntry mailAttacheEntry = this.mAttachEntry;
            int iHashCode2 = (iHashCode + (mailAttacheEntry != null ? mailAttacheEntry.hashCode() : 0)) * 31;
            String str = this.mLoaderUrl;
            int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mAttachHash;
            int iHashCode4 = str2 != null ? str2.hashCode() : 0;
            long j10 = this.mOffset;
            int i10 = (((iHashCode3 + iHashCode4) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            long j11 = this.mFileSize;
            return i10 + ((int) (j11 ^ (j11 >>> 32)));
        }
    }

    public UploadCloudRequest(Context context, Params params, ProgressListener<Float> progressListener, boolean z10) {
        this(context, params, progressListener, null, z10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MailAttacheEntry getAttachEntry() {
        return ((Params) getParams()).mAttachEntry;
    }

    private void seek(InputStream inputStream, long j10) throws IOException {
        byte[] bArr = new byte[1024];
        while (j10 > 0) {
            int i10 = inputStream.read(bArr, 0, j10 < ((long) 1024) ? (int) j10 : 1024);
            if (i10 == -1) {
                throw new EOFException();
            }
            j10 -= (long) i10;
        }
    }

    @Override // ru.mail.data.cmd.server.UploadFileCommand, ru.mail.network.NetworkCommand
    protected void encodeRequestBody(NetworkService networkService, RequestBody requestBody) throws IOException {
        try {
            super.encodeRequestBody(networkService, requestBody);
        } catch (SSLException e10) {
            throw new AttachException("Unable to upload attach " + getAttachEntry(), e10, R.string.wrong_email);
        }
    }

    @Override // ru.mail.network.NetworkCommand
    protected List<String> getAllowedGetParams() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("access_token");
        return arrayList;
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        CloudHostProvider cloudHostProvider = new CloudHostProvider(super.getHostProvider(), ((Params) getParams()).getLoaderUrl(), ((Params) getParams()).getAttachHash());
        if (((Params) getParams()).getOffset() > 0) {
            cloudHostProvider.addQueryParam("from", String.valueOf(((Params) getParams()).getOffset()));
        }
        return cloudHostProvider;
    }

    @Override // ru.mail.serverapi.PostServerRequest
    protected NetworkService.RequestMethod getRequestMethod() {
        return NetworkService.RequestMethod.PUT;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new CloudResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.UploadCloudRequest.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.data.cmd.server.CloudResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                int statusCode = getResponse().getStatusCode();
                if (statusCode == 201 || statusCode == 204) {
                    return getDelegate().onResponseOk(getResponse());
                }
                if (statusCode != 404) {
                    return statusCode != 413 ? super.process() : new CommandStatus.SIMPLE_ERROR(UploadCloudRequest.this.getContext().getString(R.string.attach_too_large, ((Params) UploadCloudRequest.this.getParams()).getAttachEntry().getFullName()));
                }
                return new CHUNK_NOT_FOUND();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.data.cmd.server.UploadFileCommand, ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    protected RequestBody onPrepareRequestBody() {
        MessageSendAnalytics messageSendAnalytics = (MessageSendAnalytics) Locator.locate(getContext(), MessageSendAnalytics.class);
        try {
            InputStream inputStreamBlocking = getAttachEntry().getInputStreamBlocking(getContext());
            if (inputStreamBlocking != null) {
                if (((Params) getParams()).getOffset() > 0) {
                    seek(inputStreamBlocking, ((Params) getParams()).getOffset());
                }
                return new StreamRequestBody(inputStreamBlocking, prepareBodySize(getNetworkService()));
            }
            messageSendAnalytics.uploadAttachError("null", getAttachEntry().getClass().getSimpleName());
            throw new AttachException("Input stream of attach isn't created " + getAttachEntry(), R.string.attach_was_not_found);
        } catch (IOException e10) {
            e = e10;
            messageSendAnalytics.uploadAttachError(OkListenerKt.KEY_EXCEPTION, getAttachEntry().getClass().getSimpleName());
            throw new AttachException("Unable to get input stream of attach " + getAttachEntry(), e, R.string.attach_was_not_found);
        } catch (SecurityException e11) {
            e = e11;
            messageSendAnalytics.uploadAttachError(OkListenerKt.KEY_EXCEPTION, getAttachEntry().getClass().getSimpleName());
            throw new AttachException("Unable to get input stream of attach " + getAttachEntry(), e, R.string.attach_was_not_found);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.ProgressUpdatable
    public void onProgressUpdate(long j10, long j11, long j12) {
        if (isCancelled()) {
            return;
        }
        notifyObservers(Float.valueOf((j11 * ((Params) getParams()).getFileSize()) / j12));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.data.cmd.server.UploadFileCommand
    protected long prepareBodySize(NetworkService networkService) {
        return ((Params) getParams()).getOffset() > 0 ? ((Params) getParams()).getFileSize() - ((Params) getParams()).getOffset() : ((Params) getParams()).getFileSize();
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean shouldRetry(int i10, CommandStatus<?> commandStatus) {
        return false;
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class AttachException extends RuntimeException {
        private final int mMessageResId;

        public AttachException(String str, int i10) {
            super(str);
            this.mMessageResId = i10;
        }

        public int getMessageResId() {
            return this.mMessageResId;
        }

        public AttachException(String str, Throwable th2, int i10) {
            super(str, th2);
            this.mMessageResId = i10;
        }
    }

    private UploadCloudRequest(Context context, Params params, ProgressListener<Float> progressListener, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
        addObserver(progressListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        try {
            return super.onExecute(executorSelector);
        } catch (AttachException e10) {
            LOG.e("Unable to upload attach", e10);
            return new CommandStatus.SIMPLE_ERROR(getContext().getString(e10.getMessageResId()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }

    @Override // ru.mail.data.cmd.server.UploadFileCommand
    protected void onPrepareMultipartBody(MultipartRequestBody multipartRequestBody) {
    }
}
