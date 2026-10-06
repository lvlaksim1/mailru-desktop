package ru.mail.data.cmd.server;

import android.content.Context;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {"cgi-bin", "logout"})
public class TerminateSessionsCommand extends ServerCommandBase<Params, EmptyResult> {
    public static final String SET_COOKIE = "Set-Cookie";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {

        @Param(method = HttpMethod.GET, name = "full")
        private static String FULL = String.valueOf(1);

        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
        }
    }

    public TerminateSessionsCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean isStringResponse() {
        return false;
    }

    @Override // ru.mail.network.NetworkCommand
    protected CommandStatus<?> processResponse(NetworkCommand.Response response) {
        String headerField = getNetworkService().getHeaderField("Set-Cookie");
        return (response.getStatusCode() == 200 && headerField != null && headerField.startsWith("Mpop")) ? new CommandStatus.OK() : new CommandStatus.ERROR();
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
        try {
            super.setUpSession(networkService);
        } catch (NetworkCommandWithSession.BadSessionException unused) {
        }
    }

    TerminateSessionsCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
