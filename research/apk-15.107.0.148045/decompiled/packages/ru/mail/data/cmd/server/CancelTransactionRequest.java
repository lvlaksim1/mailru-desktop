package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.GetServerRequest;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "money", "p2p", "cancel"})
public class CancelTransactionRequest extends GetServerRequest<Params, EmptyResult> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.GET, name = "transaction_id")
        private final String mId;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull String str) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mId = str;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mId.equals(((Params) obj).mId);
        }

        public String getId() {
            return this.mId;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return this.mId.hashCode() * 31;
        }
    }

    public CancelTransactionRequest(Context context, Params params) {
        super(context, params);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, EmptyResult>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.CancelTransactionRequest.1
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
                return new NetworkCommandStatus.BAD_REQUEST();
            }

            @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onError(NetworkCommand.Response response) {
                return super.onError(response);
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    public CancelTransactionRequest(Context context, Params params, HostProvider hostProvider) {
        super(context, params, hostProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
