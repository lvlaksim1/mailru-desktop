package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "remove", "all"})
public class RemoveAllMessageCommand extends PostServerRequest<Params, EmptyResult> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "folder_from")
        private Long mFolderFromId;

        @Param(method = HttpMethod.POST, name = "only_newsletters")
        private Boolean mIsNewslettersOnly;

        @Param(method = HttpMethod.POST, name = "spam_folder")
        private Boolean mIsSpamFolder;

        @Param(method = HttpMethod.POST, name = "older_than")
        private Long mRemoveTime;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
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
            return Objects.equals(this.mRemoveTime, params.mRemoveTime) && Objects.equals(this.mIsNewslettersOnly, params.mIsNewslettersOnly) && Objects.equals(this.mIsSpamFolder, params.mIsSpamFolder) && Objects.equals(this.mFolderFromId, params.mFolderFromId);
        }

        public Params folderFromId(long j10) {
            this.mFolderFromId = Long.valueOf(j10);
            return this;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mRemoveTime, this.mIsNewslettersOnly, this.mIsSpamFolder, this.mFolderFromId);
        }

        public Params newslettersOnly() {
            this.mIsNewslettersOnly = Boolean.TRUE;
            return this;
        }

        public Params spamFolder() {
            this.mIsSpamFolder = Boolean.TRUE;
            return this;
        }

        public Params withRemoveTime(long j10) {
            this.mRemoveTime = Long.valueOf(j10 + 1);
            return this;
        }
    }

    public RemoveAllMessageCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
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
