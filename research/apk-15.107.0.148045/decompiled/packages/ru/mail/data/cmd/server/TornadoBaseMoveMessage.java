package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import ru.mail.data.cmd.server.TornadoBaseMoveMessage.Params;
import ru.mail.logic.cmd.MoveOperation;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class TornadoBaseMoveMessage<T extends Params> extends PostServerRequest<T, EmptyResult> implements MoveOperation {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getMessageIdsAsJsonString", method = HttpMethod.POST, name = "ids", type = Param.Type.STRING, useGetter = true)
        private final String[] mMessageIds;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String... strArr) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mMessageIds = strArr != null ? (String[]) Arrays.copyOf(strArr, strArr.length) : new String[0];
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                return Arrays.equals(this.mMessageIds, ((Params) obj).mMessageIds);
            }
            return false;
        }

        public String getMessageIdsAsJsonString() {
            JSONArray jSONArray = new JSONArray();
            for (String str : this.mMessageIds) {
                jSONArray.put(str);
            }
            return jSONArray.toString();
        }

        public String[] getMovedMessagesIds() {
            String[] strArr = this.mMessageIds;
            return (String[]) Arrays.copyOf(strArr, strArr.length);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (super.hashCode() * 31) + Arrays.hashCode(this.mMessageIds);
        }
    }

    public TornadoBaseMoveMessage(Context context, T t10, HostProvider hostProvider, boolean z10) {
        super(context, t10, hostProvider, z10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.logic.cmd.MoveOperation
    public String[] getMovedMessagesIds() {
        return ((Params) getParams()).getMovedMessagesIds();
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<T, EmptyResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<T, EmptyResult>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase.TornadoDelegate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
