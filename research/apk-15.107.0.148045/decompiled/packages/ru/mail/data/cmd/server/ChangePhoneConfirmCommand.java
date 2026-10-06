package ru.mail.data.cmd.server;

import android.content.Context;
import com.vk.api.sdk.exceptions.VKApiCodes;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "mobile", "phone", "change", VKApiCodes.EXTRA_CONFIRM})
public class ChangePhoneConfirmCommand extends DeleteAccountConfirmCommand<DeleteAccountConfirmCommand.Params, Result> {
    private static final Log LOG = Log.getLog("ChangePhoneConfirmCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String newPhone;

        public Result(String str) {
            this.newPhone = str;
        }

        public String getNewPhone() {
            return this.newPhone;
        }
    }

    public ChangePhoneConfirmCommand(Context context, DeleteAccountConfirmCommand.Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.data.cmd.server.DeleteAccountConfirmCommand, ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.data.cmd.server.DeleteAccountConfirmCommand, ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new PhoneConfirmResponseProcessor(response, networkCommandBaseDelegate);
    }

    ChangePhoneConfirmCommand(Context context, DeleteAccountConfirmCommand.Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            return new Result(new JSONObject(response.getRespString()).getJSONObject("body").getString("new_phone"));
        } catch (JSONException e10) {
            LOG.e(e10.getMessage(), e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
