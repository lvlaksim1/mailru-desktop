package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class RequestTranslationsCommand<P extends ServerCommandBaseParams> extends ServerCommandBase<P, String> {
    public RequestTranslationsCommand(Context context, P p10, boolean z10) {
        super(context, p10, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(final NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<P, String>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new ResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.RequestTranslationsCommand.1
            @Override // ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                return getResponse().getStatusCode() != 200 ? new CommandStatus.ERROR() : getDelegate().onResponseOk(response);
            }
        };
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean needPlatformParams() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public String onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return response.getRespString();
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(Uri.Builder builder) throws NetworkCommandWithSession.BadSessionException {
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
    }
}
