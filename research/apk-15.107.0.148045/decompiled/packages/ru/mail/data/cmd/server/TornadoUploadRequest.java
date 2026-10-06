package ru.mail.data.cmd.server;

import android.content.Context;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.io.IOException;
import java.io.InputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.analytics.MessageSendAnalytics;
import ru.mail.auth.request.AccountInfo;
import ru.mail.logic.content.MailAttacheEntry;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.network.requestbody.MultipartRequestBody;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "attaches", ProductAction.ACTION_ADD})
public class TornadoUploadRequest extends UploadFileCommand<Params, Result, Float> {
    private static final Log LOG = Log.getLog("TornadoUploadRequest");
    public static final String TAG_FILE = "file";
    private final MessageSendAnalytics mAnalytics;
    private final ErrorStringProvider mStringProvider;

    /* JADX INFO: compiled from: ProGuard */
    private static class AttachException extends RuntimeException {
        public AttachException(String str) {
            super(str);
        }

        public AttachException(String str, Throwable th2) {
            super(str, th2);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {
        protected final MailAttacheEntry mAttachEntry;

        @Param(method = HttpMethod.POST, name = "message_id")
        private final String mMessageId;

        public Params(@Nullable String str, @Nullable MailAttacheEntry mailAttacheEntry, AccountInfo accountInfo, FolderState folderState) {
            super(accountInfo, folderState);
            this.mMessageId = str;
            this.mAttachEntry = mailAttacheEntry;
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
            MailAttacheEntry mailAttacheEntry = this.mAttachEntry;
            if (mailAttacheEntry == null ? params.mAttachEntry != null : !mailAttacheEntry.equals(params.mAttachEntry)) {
                return false;
            }
            String str = this.mMessageId;
            String str2 = params.mMessageId;
            return str == null ? str2 == null : str.equals(str2);
        }

        public MailAttacheEntry getAttachEntry() {
            return this.mAttachEntry;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mMessageId;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            MailAttacheEntry mailAttacheEntry = this.mAttachEntry;
            return iHashCode2 + (mailAttacheEntry != null ? mailAttacheEntry.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mAttachId;

        public Result(String str) {
            this.mAttachId = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            String str = this.mAttachId;
            String str2 = ((Result) obj).mAttachId;
            return str == null ? str2 == null : str.equals(str2);
        }

        public String getAttachId() {
            return this.mAttachId;
        }

        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mAttachId;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }
    }

    public TornadoUploadRequest(Context context, Params params, ProgressListener<Float> progressListener, boolean z10, MessageSendAnalytics messageSendAnalytics, ErrorStringProvider errorStringProvider) {
        this(context, params, progressListener, null, z10, messageSendAnalytics, errorStringProvider);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MailAttacheEntry getAttachEntry() {
        return ((Params) getParams()).mAttachEntry;
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(final NetworkCommand.Response response, ServerApi serverApi, final NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.TornadoUploadRequest.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                CommandStatus<?> commandStatusExecute = new ErrorHandler(TornadoUploadRequest.this.mStringProvider).execute(ErrorHandler.EnumErrorHandlerName.TORNADO_UPLOAD_REQUEST, response, networkCommandBaseDelegate);
                return commandStatusExecute != null ? commandStatusExecute : super.process();
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected ServerCommandBase.DefaultTrafficListener onCreateTrafficListener(Context context) {
        return new ServerCommandBase.DefaultTrafficListener(context) { // from class: ru.mail.data.cmd.server.TornadoUploadRequest.2
            @Override // ru.mail.serverapi.ServerCommandBase.DefaultTrafficListener, ru.mail.network.NetworkTrafficListener
            public void onTrafficSent(long j10) {
                getTracker().sizeOfNewTxSendViaApi(j10);
            }
        };
    }

    @Override // ru.mail.data.cmd.server.UploadFileCommand
    protected void onPrepareMultipartBody(MultipartRequestBody multipartRequestBody) {
        try {
            InputStream inputStreamBlocking = getAttachEntry().getInputStreamBlocking(getContext());
            if (inputStreamBlocking != null) {
                multipartRequestBody.addInputStreamPart("file", inputStreamBlocking, getAttachEntry().getFullName());
                return;
            }
            this.mAnalytics.uploadAttachError("null", getAttachEntry().getClass().getSimpleName());
            throw new AttachException("Input stream of attach isn't created " + getAttachEntry());
        } catch (IOException e10) {
            this.mAnalytics.uploadAttachError(OkListenerKt.KEY_EXCEPTION, getAttachEntry().getClass().getSimpleName());
            throw new AttachException("Unable to get input stream of attach " + getAttachEntry(), e10);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.ProgressUpdatable
    public void onProgressUpdate(long j10, long j11, long j12) {
        if (isCancelled()) {
            return;
        }
        notifyObservers(Float.valueOf((j11 * ((Params) getParams()).mAttachEntry.getFileSizeInBytes()) / j12));
    }

    private TornadoUploadRequest(Context context, Params params, ProgressListener<Float> progressListener, HostProvider hostProvider, boolean z10, MessageSendAnalytics messageSendAnalytics, ErrorStringProvider errorStringProvider) {
        super(context, params, hostProvider, z10);
        this.mAnalytics = messageSendAnalytics;
        this.mStringProvider = errorStringProvider;
        addObserver(progressListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        try {
            return super.onExecute(executorSelector);
        } catch (AttachException e10) {
            LOG.e("Unable to upload attach", e10);
            return new CommandStatus.SIMPLE_ERROR(this.mStringProvider.getAttachWasNotFound());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        LOG.d(String.format("Response on attachment (%s) uploading: response string = %s, status code = %s, error = %s", ((Params) getParams()).mAttachEntry.getFullName(), response.getRespString(), Integer.valueOf(response.getStatusCode()), response.getError()));
        try {
            return new Result(new JSONObject(response.getRespString()).getJSONObject("body").getJSONObject("attach").getString("id"));
        } catch (JSONException e10) {
            LOG.e("Unable to parse ", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
