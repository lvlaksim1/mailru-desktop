package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.logic.cmd.ByteArrayStreamReceiver;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "attaches", Promotion.ACTION_VIEW})
public class DownloadFileInMemoryCmd extends ServerCommandBase<Params, InputStream> {
    private final ByteArrayStreamReceiver mByteArrayStreamReceiver;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.GET, name = "type")
        private static final String TYPE = "attach";

        @Param(method = HttpMethod.GET, name = "id")
        private final String mId;

        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState, @NotNull String str) {
            super(accountInfo, folderState);
            this.mId = str;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && super.equals(obj) && Objects.equals(this.mId, ((Params) obj).mId) && "attach".equals("attach");
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mId, "attach");
        }
    }

    public DownloadFileInMemoryCmd(Context context, Params params, boolean z10) {
        super(context, params, z10);
        this.mByteArrayStreamReceiver = new ByteArrayStreamReceiver();
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, InputStream>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, InputStream>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.DownloadFileInMemoryCmd.1
            @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onError(NetworkCommand.Response response) {
                if (response.getStatusCode() == 403) {
                    try {
                        return onUnauthorized(new JSONObject(response.getRespString()).getString("body"));
                    } catch (JSONException unused) {
                    }
                }
                return super.onError(response);
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected byte[] getResponseData(InputStream inputStream) throws IOException {
        if (!isCancelled()) {
            this.mByteArrayStreamReceiver.receive(inputStream);
        }
        return new byte[0];
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean isStringResponse() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public InputStream onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        if (this.mByteArrayStreamReceiver.isReceived()) {
            return this.mByteArrayStreamReceiver.toInputStream();
        }
        throw new NetworkCommand.PostExecuteException("Error while saving file");
    }
}
