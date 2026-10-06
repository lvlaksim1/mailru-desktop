package ru.mail.data.cmd.server;

import android.content.Context;
import com.vk.pushme.logic.PendingAction;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.cmd.MoveOperation;
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
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "services", PendingAction.UNSUBSCRIBE_TYPE})
public class UnsubscribeMessageCommand extends PostServerRequest<Params, EmptyResult> implements MoveOperation {
    private static final Log LOG = Log.getLog("UnsubscribeMessageCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getIds", method = HttpMethod.POST, name = "ids", useGetter = true)
        private final String[] mMailMessageIds;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull String... strArr) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mMailMessageIds = (String[]) Arrays.copyOf(strArr, strArr.length);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof Params) && super.equals(obj)) {
                return Arrays.equals(this.mMailMessageIds, ((Params) obj).mMailMessageIds);
            }
            return false;
        }

        public String getIds() {
            JSONArray jSONArray = new JSONArray();
            for (String str : this.mMailMessageIds) {
                jSONArray.put(str);
            }
            return jSONArray.toString();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String[] strArr = this.mMailMessageIds;
            return iHashCode + (strArr != null ? Arrays.hashCode(strArr) : 0);
        }
    }

    public UnsubscribeMessageCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, EmptyResult>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.UnsubscribeMessageCommand.1
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
                try {
                    if (jSONObject.has("ids[0]")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("ids[0]");
                        if (jSONObject2.has("error") && "not_exists".equals(jSONObject2.getString("error"))) {
                            return new NetworkCommandStatus.NOT_EXIST();
                        }
                    }
                    return super.onBadRequest(jSONObject);
                } catch (JSONException e10) {
                    UnsubscribeMessageCommand.LOG.e("Cant parse error msg", e10);
                    return new CommandStatus.ERROR();
                }
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.logic.cmd.MoveOperation
    public String[] getMovedMessagesIds() {
        return (String[]) Arrays.copyOf(((Params) getParams()).mMailMessageIds, ((Params) getParams()).mMailMessageIds.length);
    }

    UnsubscribeMessageCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
