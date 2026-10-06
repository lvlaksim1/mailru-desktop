package ru.mail.data.cmd.server;

import android.content.Context;
import com.vk.superapp.api.dto.app.WebOrder;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailThread;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.ui.fragments.settings.ConfirmPhoneFragment;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "mobile", "remove"})
public class DeleteAccountCommand extends ServerCommandBase<Params, TokenResult> {
    private static final Log LOG = Log.getLog("DeleteAccountCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {
        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendLocale() {
            return true;
        }
    }

    public DeleteAccountCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, TokenResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    DeleteAccountCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public TokenResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body").getJSONObject(ConfirmPhoneFragment.EXT_REG_TOKEN);
            return new TokenResult(jSONObject.getString("id"), jSONObject.getInt(MailThread.COL_NAME_LENGTH), jSONObject.getInt(WebOrder.STATUS_WAIT));
        } catch (JSONException e10) {
            LOG.e(e10.getMessage(), e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
