package ru.mail.data.cmd.server;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mails.R;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "mobile", "phone", "change", "confirm-current"})
public class CheckPhoneCommand extends DeleteAccountCommand {
    public CheckPhoneCommand(Context context, DeleteAccountCommand.Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.data.cmd.server.DeleteAccountCommand, ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.data.cmd.server.DeleteAccountCommand, ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.CheckPhoneCommand.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor
            public CommandStatus<?> processResponse(int i10) {
                if (i10 == 403) {
                    try {
                        if (new JSONObject(getResponse().getRespString()).getString("body").equals("user")) {
                            return processError("account not found", R.string.change_phone_user_not_found);
                        }
                    } catch (JSONException e10) {
                        e10.printStackTrace();
                    }
                }
                return super.processResponse(i10);
            }
        };
    }
}
