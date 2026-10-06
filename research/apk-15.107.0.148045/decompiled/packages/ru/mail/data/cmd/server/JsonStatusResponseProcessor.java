package ru.mail.data.cmd.server;

import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class JsonStatusResponseProcessor extends ResponseProcessor {
    public JsonStatusResponseProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        super(response, networkCommandBaseDelegate);
    }

    @Override // ru.mail.network.ResponseProcessor
    public CommandStatus<?> process() {
        if (getResponse().getStatusCode() != 200) {
            return getDelegate().onError(getResponse());
        }
        int i10 = Integer.parseInt(getDelegate().getResponseStatus(getResponse().getRespString()));
        if (i10 == 200) {
            return getDelegate().onResponseOk(getResponse());
        }
        if (i10 != 400) {
            return getDelegate().onError(getResponse());
        }
        try {
            return getDelegate().onBadRequest(new JSONObject(getResponse().getRespString()));
        } catch (JSONException unused) {
            return getDelegate().onError(getResponse());
        }
    }
}
