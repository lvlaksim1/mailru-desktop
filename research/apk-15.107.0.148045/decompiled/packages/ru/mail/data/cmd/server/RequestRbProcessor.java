package ru.mail.data.cmd.server;

import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class RequestRbProcessor extends ResponseProcessor {
    public RequestRbProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        super(response, networkCommandBaseDelegate);
    }

    @Override // ru.mail.network.ResponseProcessor
    public CommandStatus<?> process() {
        getResponse().createStringFromData();
        return getResponse().getStatusCode() != 200 ? getDelegate().onError(getResponse()) : getDelegate().onResponseOk(getResponse());
    }
}
