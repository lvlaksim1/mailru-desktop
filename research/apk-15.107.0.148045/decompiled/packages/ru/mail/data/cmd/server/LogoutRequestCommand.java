package ru.mail.data.cmd.server;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.cookie.SM;
import ru.mail.auth.MailAccountConstants;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {"cgi-bin", "logout"})
public class LogoutRequestCommand extends ServerCommandBase<Params, EmptyResult> {
    private NetworkCommand.Response mResponse;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {
        protected final String mCookie;

        public Params(String str) {
            this.mCookie = str;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            String str = this.mCookie;
            String str2 = ((Params) obj).mCookie;
            return str == null ? str2 == null : str.equals(str2);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mCookie;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }
    }

    public LogoutRequestCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    public NetworkCommand.Response getResponse() {
        return this.mResponse;
    }

    @Override // ru.mail.network.NetworkCommand
    protected byte[] getResponseData(InputStream inputStream) throws IOException {
        return new byte[0];
    }

    @Override // ru.mail.network.NetworkCommandWithSession, ru.mail.network.NetworkCommand
    protected void onPrepareConnection(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException, IOException {
        super.onPrepareConnection(networkService);
        networkService.setConnectTimeout(5000);
        networkService.setReadTimeout(10000);
    }

    @Override // ru.mail.network.NetworkCommand
    protected CommandStatus<?> processResponse(NetworkCommand.Response response) {
        this.mResponse = response;
        return response.getStatusCode() != 200 ? new CommandStatus.ERROR() : new CommandStatus.OK();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) {
        networkService.setRequestProperty(SM.COOKIE, MailAccountConstants.getCookieHeader(((Params) getParams()).mCookie, null));
    }

    LogoutRequestCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) {
        return new EmptyResult();
    }
}
