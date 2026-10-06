package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.entities.Attach;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "attaches", "remove"})
public class TornadoRemoveRequest extends PostServerRequest<Params, EmptyResult> {
    private static final Log LOG = Log.getLog("TornadoRemoveRequest");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getAttachesJson", method = HttpMethod.POST, name = "ids", useGetter = true)
        private final List<Attach> mAttaches;

        @Param(method = HttpMethod.POST, name = "message_id")
        private final String mMessageId;

        public Params(@Nullable String str, @Nullable List<Attach> list, AccountInfo accountInfo, FolderState folderState) {
            super(accountInfo, folderState);
            this.mMessageId = str;
            this.mAttaches = list;
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
            List<Attach> list = this.mAttaches;
            if (list == null ? params.mAttaches != null : !list.equals(params.mAttaches)) {
                return false;
            }
            String str = this.mMessageId;
            String str2 = params.mMessageId;
            return str == null ? str2 == null : str.equals(str2);
        }

        public String getAttachesJson() {
            JSONArray jSONArray = new JSONArray();
            Iterator<Attach> it = this.mAttaches.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().getFileId());
            }
            return jSONArray.toString();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mMessageId;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mMessageId;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }
    }

    public TornadoRemoveRequest(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    private TornadoRemoveRequest(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
