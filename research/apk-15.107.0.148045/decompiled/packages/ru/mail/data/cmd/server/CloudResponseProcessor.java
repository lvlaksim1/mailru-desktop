package ru.mail.data.cmd.server;

import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
class CloudResponseProcessor extends ResponseProcessor {
    public CloudResponseProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        super(response, networkCommandBaseDelegate);
    }

    @Override // ru.mail.network.ResponseProcessor
    public CommandStatus<?> process() {
        int statusCode = getResponse().getStatusCode();
        if (statusCode == 403) {
            return getDelegate().onUnauthorized("");
        }
        if (statusCode < 500 || statusCode >= 600) {
            return statusCode == 200 ? getDelegate().onResponseOk(getResponse()) : getDelegate().onError(getResponse());
        }
        return new CommandStatus.ERROR_WITH_STATUS_CODE(statusCode);
    }
}
