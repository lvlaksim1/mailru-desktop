package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.cmd.AttachRequest;
import ru.mail.logic.cmd.AttachFileReceiver;
import ru.mail.logic.content.AttachInformation;
import ru.mail.logic.content.AttachUriBuilder;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.mailbox.cmd.ProgressObservable;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.WithSampling;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@WithSampling
public class AttachRequestCommand extends ServerCommandBase<Params, AttachRequest.Result> implements ProgressObservable<AttachRequest.ProgressData>, AttachRequest.Command {
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ENCODING_GZIP = "gzip";
    public static final String ENCODING_IDENTITY = "identity";
    public static final String PARAM_HEADER_REFERER = "Referer";

    @Param(getterName = "getAcceptEncoding", method = HttpMethod.HEADER_ADD, name = "Accept-Encoding", useGetter = true)
    private String mAcceptEncoding;
    private final AttachFileReceiver mStreamReceiver;
    private AttachUriBuilder.AttachUriVisitor mUriBuilderVisitor;

    /* JADX INFO: compiled from: ProGuard */
    private class AttachRequestLegacyDelegate extends ServerCommandBase<Params, AttachRequest.Result>.LegacyDelegate {
        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            if (response.getStatusCode() != 404) {
                return super.onError(response);
            }
            AttachRequestCommand.this.mStreamReceiver.abort();
            return new MailCommandStatus.ERROR_ATTACH_NOT_FOUND();
        }

        private AttachRequestLegacyDelegate() {
            super();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private class AttachRequestTornadoDelegate extends ServerCommandBase<Params, AttachRequest.Result>.TornadoDelegate {
        @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            try {
                String strOptString = new JSONObject(response.getRespString()).optString("body");
                AttachRequestCommand.this.mStreamReceiver.abort();
                return strOptString.equals("token") ? onUnauthorized(strOptString) : new MailCommandStatus.ERROR_ATTACH_NOT_FOUND();
            } catch (Exception unused) {
                return super.onError(response);
            }
        }

        private AttachRequestTornadoDelegate() {
            super();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams implements AttachRequest.Params {
        private final AttachInformation mAttach;
        private final int mAttachHash;
        private final String mFileName;
        private final long mFileSize;
        private final String mFrom;
        private final String mMsgId;

        @Param(method = HttpMethod.HEADER_ADD, name = "Referer")
        private final String mReferer;

        public Params(@NotNull AttachInformation attachInformation, @Nullable String str, @Nullable String str2, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            this(attachInformation, str, str2, null, accountInfo, folderState);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Params) || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            AttachInformation attachInformation = this.mAttach;
            if (attachInformation == null ? params.mAttach != null : !attachInformation.equals(params.mAttach)) {
                return false;
            }
            String str = this.mFileName;
            if (str == null ? params.mFileName != null : !str.equals(params.mFileName)) {
                return false;
            }
            String str2 = this.mFrom;
            if (str2 == null ? params.mFrom != null : !str2.equals(params.mFrom)) {
                return false;
            }
            String str3 = this.mMsgId;
            String str4 = params.mMsgId;
            return str3 == null ? str4 == null : str3.equals(str4);
        }

        @Override // ru.mail.data.cmd.AttachRequest.Params
        public AttachInformation getAttach() {
            return this.mAttach;
        }

        @Override // ru.mail.data.cmd.AttachRequest.Params
        public String getFileName() {
            return this.mFileName;
        }

        @Override // ru.mail.data.cmd.AttachRequest.Params
        public String getFrom() {
            return this.mFrom;
        }

        @Override // ru.mail.data.cmd.AttachRequest.Params
        public String getMsgId() {
            return this.mMsgId;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mFrom;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mMsgId;
            int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.mFileName;
            int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
            AttachInformation attachInformation = this.mAttach;
            return iHashCode4 + (attachInformation != null ? attachInformation.hashCode() : 0);
        }

        public Params(@NotNull AttachInformation attachInformation, @Nullable String str, @Nullable String str2, String str3, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            this(attachInformation, attachInformation.hashCode(), attachInformation.getFullName(), str, str2, attachInformation.getFileSizeInBytes(), str3, accountInfo, folderState);
        }

        private Params(@NotNull AttachInformation attachInformation, int i10, @Nullable String str, @Nullable String str2, @Nullable String str3, long j10, @Nullable String str4, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            this.mAttach = attachInformation;
            this.mMsgId = str3;
            this.mFrom = str2;
            this.mFileName = str;
            this.mFileSize = j10;
            this.mAttachHash = i10;
            this.mReferer = str4;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ProgressData {
        final long allSize;
        final long progress;

        public ProgressData(long j10, long j11) {
            this.progress = j10;
            this.allSize = j11;
        }
    }

    public AttachRequestCommand(@NotNull Context context, @NotNull Params params, @Nullable ProgressListener<AttachRequest.ProgressData> progressListener, @NotNull AttachFileReceiver attachFileReceiver, boolean z10, AttachUriBuilder.AttachUriVisitor attachUriVisitor) {
        this(context, params, null, progressListener, attachFileReceiver, z10, attachUriVisitor);
    }

    private long parseLong(String str, long j10) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return j10;
        }
    }

    @Override // ru.mail.mailbox.cmd.ProgressObservable
    public void addObserver(ProgressListener<AttachRequest.ProgressData> progressListener) {
        this.mStreamReceiver.addObserver(progressListener);
    }

    @Keep
    public String getAcceptEncoding() {
        return "gzip";
    }

    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.CommandExecutionInfo
    public String getLoggerParamName() {
        return super.getLoggerParamName() + "_Attach";
    }

    @Override // ru.mail.mailbox.cmd.ProgressObservable
    public List<ProgressListener<AttachRequest.ProgressData>> getObservers() {
        return this.mStreamReceiver.getObservers();
    }

    @Override // ru.mail.data.cmd.AttachRequest.Command
    public AttachRequest.Params getRequestParams() {
        return (AttachRequest.Params) getParams();
    }

    @Override // ru.mail.network.NetworkCommand
    protected byte[] getResponseData(InputStream inputStream) throws IOException {
        if (!isCancelled()) {
            this.mStreamReceiver.receive(inputStream);
        }
        return new byte[0];
    }

    @Override // ru.mail.network.NetworkCommand
    @Nullable
    protected String getTag() {
        return getLoggerParamName();
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean isStringResponse() {
        return false;
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected boolean isSupportOAuthAuthorization() {
        return true;
    }

    @Override // ru.mail.mailbox.cmd.Command
    public void onCancelled() {
        super.onCancelled();
        this.mStreamReceiver.abort();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    protected Uri onPrepareUrl(Uri.Builder builder) {
        try {
            FolderState folderState = ((Params) getParams()).getFolderState();
            return this.mUriBuilderVisitor.buildUri(((Params) getParams()).mAttach, folderState != null ? Long.valueOf(folderState.getFolderId()) : null);
        } catch (UnsupportedEncodingException e10) {
            throw new IllegalArgumentException("can't encode attach full name " + ((Params) getParams()).mFileName, e10);
        }
    }

    @Override // ru.mail.mailbox.cmd.ProgressObservable
    public void removeObserver(ProgressListener<AttachRequest.ProgressData> progressListener) {
        this.mStreamReceiver.removeObserver(progressListener);
    }

    public AttachRequestCommand(@NotNull Context context, @NotNull Params params, @Nullable HostProvider hostProvider, @Nullable ProgressListener<AttachRequest.ProgressData> progressListener, @NotNull AttachFileReceiver attachFileReceiver, boolean z10, AttachUriBuilder.AttachUriVisitor attachUriVisitor) {
        super(context, params, hostProvider, z10);
        this.mStreamReceiver = attachFileReceiver;
        addObserver(progressListener);
        this.mUriBuilderVisitor = attachUriVisitor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, AttachRequest.Result>.ServerCommandBaseDelegate getCustomDelegate() {
        return getApiType() == MailAuthorizationApiType.LEGACY ? new AttachRequestLegacyDelegate() : new AttachRequestTornadoDelegate();
    }

    @Override // ru.mail.mailbox.cmd.ProgressObservable
    public void notifyObservers(AttachRequest.ProgressData progressData) {
        this.mStreamReceiver.notifyObservers(progressData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    public AttachRequest.Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        if (!this.mStreamReceiver.isReceived()) {
            throw new NetworkCommand.PostExecuteException("Error while saving attach");
        }
        AttachInformation attach = ((Params) getParams()).getAttach();
        return new AttachRequest.Result(this.mStreamReceiver.getFile(), attach.isUnstableData() ? parseLong(getNetworkService().getHeaderField("Content-length"), 0L) : attach.getContentLength());
    }
}
