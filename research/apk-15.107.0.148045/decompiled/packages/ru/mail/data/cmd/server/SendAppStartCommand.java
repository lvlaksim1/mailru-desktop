package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import org.jetbrains.annotations.NotNull;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.setup.action.AppStartTriggerStartup;
import ru.mail.util.config.MigrateToPostUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@UrlPath(pathSegments = {AppStartTriggerStartup.TRIGGER_APP_START})
public class SendAppStartCommand extends ServerCommandBase<ServerCommandBaseParams, EmptyResult> {
    public SendAppStartCommand(Context context) {
        super(context, null, new SendAppStartHostProvider(context), MigrateToPostUtils.is12130Enabled(context));
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.LEGACY_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean isStringResponse() {
        return false;
    }

    @Override // ru.mail.network.NetworkCommand
    protected CommandStatus<?> processResponse(NetworkCommand.Response response) {
        return new CommandStatus.OK();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(Uri.Builder builder) throws NetworkCommandWithSession.BadSessionException {
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
    }
}
