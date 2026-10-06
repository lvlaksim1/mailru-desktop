package ru.mail.serverapi;

import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class LegacyResponseProcessor extends ResponseProcessor {
    public LegacyResponseProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        super(response, networkCommandBaseDelegate);
    }

    @Override // ru.mail.network.ResponseProcessor
    public CommandStatus<?> process() {
        int statusCode = getResponse().getStatusCode();
        if (statusCode != 200) {
            return statusCode != 302 ? getDelegate().onError(getResponse()) : getDelegate().onUnauthorized(getResponse().getRespString());
        }
        if (!getDelegate().isStringResponse()) {
            return getDelegate().onResponseOk(getResponse());
        }
        getResponse().createStringFromData();
        getDelegate().processSignsAndTokens(getResponse());
        String responseStatus = getDelegate().getResponseStatus(getResponse().getRespString());
        if (responseStatus.contains("OK")) {
            return getDelegate().onResponseOk(getResponse());
        }
        if (responseStatus.contains("NoAuth") || ((responseStatus.contains("Redirect") && !getResponse().getRespString().contains("folderlogin")) || responseStatus.contains("BadToken"))) {
            return getDelegate().onUnauthorized(getResponse().getRespString());
        }
        return (responseStatus.contains("InvalidPassword") || responseStatus.contains("AccessDenied") || (responseStatus.contains("Redirect") && getResponse().getRespString().contains("folderlogin"))) ? getDelegate().onFolderAccessDenied() : getDelegate().onError(getResponse());
    }
}
