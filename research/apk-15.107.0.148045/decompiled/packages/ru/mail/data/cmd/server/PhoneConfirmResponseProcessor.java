package ru.mail.data.cmd.server;

import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mails.R;
import ru.mail.network.NetworkCommand;
import ru.mail.registration.ui.ConfirmationCodeFragment;
import ru.mail.serverapi.TornadoResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class PhoneConfirmResponseProcessor extends TornadoResponseProcessor {
    PhoneConfirmResponseProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        super(response, networkCommandBaseDelegate);
    }

    private CommandStatus<?> processBadRequestResponse() {
        try {
            if (new JSONObject(getResponse().getRespString()).getJSONObject("body").has(ConfirmationCodeFragment.ATTR_TOKEN_VALUE)) {
                return processError("check_phone_invalid_code", R.string.invalid_code);
            }
        } catch (JSONException e10) {
            e10.printStackTrace();
        }
        return processError("request_error", R.string.unable_to_complete_request);
    }

    @Override // ru.mail.serverapi.TornadoResponseProcessor
    protected CommandStatus<?> processResponse(int i10) {
        return i10 == 400 ? processBadRequestResponse() : super.processResponse(i10);
    }
}
