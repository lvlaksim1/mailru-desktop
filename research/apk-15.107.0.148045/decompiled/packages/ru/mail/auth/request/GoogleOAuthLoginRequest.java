package ru.mail.auth.request;

import android.content.Context;
import ru.mail.OauthParams;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.UrlPath;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@UrlPath(pathSegments = {"oauth2_google_token"})
public class GoogleOAuthLoginRequest extends BaseOAuthLoginRequest<BaseOAuthLoginRequest.Params> {
    private final boolean misNativeXmailMigration;

    public GoogleOAuthLoginRequest(Context context, HostProvider hostProvider, String str, OauthParams oauthParams, boolean z10, boolean z11, boolean z12) {
        super(context, hostProvider, new BaseOAuthLoginRequest.Params(context, oauthParams, str, z11, z12), z10);
        this.misNativeXmailMigration = z12 && z11;
    }

    @Override // ru.mail.mailbox.cmd.Command
    public synchronized CommandStatus<?> getResult() {
        CommandStatus<?> commandStatus = (CommandStatus) super.getResult();
        if (!(commandStatus instanceof CommandStatus.OK) || !this.misNativeXmailMigration) {
            return commandStatus;
        }
        return new AuthCommandStatus.MIGRANT_REG_REQUIRED(commandStatus.getData());
    }
}
