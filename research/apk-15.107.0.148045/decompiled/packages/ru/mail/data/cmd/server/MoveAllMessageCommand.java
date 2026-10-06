package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailbox.cmd.EmptyResult;
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
import ru.mail.util.config.MigrateToPostUtils;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "move", "all"})
public class MoveAllMessageCommand extends PostServerRequest<Params, EmptyResult> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "folder_from")
        private final long mFolderIdFrom;

        @Param(method = HttpMethod.POST, name = "folder")
        private final long mFolderIdTo;

        @Param(getterName = "getFromJsonArray", method = HttpMethod.POST, name = "from", useGetter = true)
        private List<String> mFrom;

        @Param(method = HttpMethod.POST, name = "only_newsletters")
        private Boolean mIsNewslettersOnly;

        @Param(method = HttpMethod.POST, name = "older_than")
        private Long mMoveTime;

        public Params(long j10, long j11, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            this.mFolderIdFrom = j10;
            this.mFolderIdTo = j11;
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
            return this.mFolderIdFrom == params.mFolderIdFrom && this.mFolderIdTo == params.mFolderIdTo && Objects.equals(this.mMoveTime, params.mMoveTime) && Objects.equals(this.mFrom, params.mFrom) && Objects.equals(this.mIsNewslettersOnly, params.mIsNewslettersOnly);
        }

        public long getFolderIdFrom() {
            return this.mFolderIdFrom;
        }

        @Nullable
        public String getFromJsonArray() {
            if (this.mFrom == null) {
                return null;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = this.mFrom.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            return jSONArray.toString();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), Long.valueOf(this.mFolderIdFrom), Long.valueOf(this.mFolderIdTo), this.mMoveTime, this.mFrom, this.mIsNewslettersOnly);
        }

        public Params newslettersOnly() {
            this.mIsNewslettersOnly = Boolean.TRUE;
            return this;
        }

        public Params withFromList(List<String> list) {
            this.mFrom = list;
            return this;
        }

        public Params withMoveTime(long j10) {
            this.mMoveTime = Long.valueOf(j10 + 1);
            return this;
        }
    }

    public MoveAllMessageCommand(Context context, Params params) {
        super(context, params, MigrateToPostUtils.is12155Enabled(context));
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
