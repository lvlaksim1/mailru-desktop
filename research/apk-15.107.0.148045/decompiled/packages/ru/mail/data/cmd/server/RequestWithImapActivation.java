package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UncodedHtmlHostProvider;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class RequestWithImapActivation<P extends ServerCommandBaseParams, T> extends ServerCommandBase<P, T> {
    private static final String JSON_BODY_KEY = "body";
    private static final String JSON_IMAP_ACTIVATION_KEY = "external_activation";
    private static final Log LOG = Log.getLog("RequestWithImapActivation");

    @Param(getterName = "getAcceptEncoding", method = HttpMethod.HEADER_ADD, name = "Accept-Encoding", useGetter = true)
    private String mAcceptEncoding;

    /* JADX INFO: compiled from: ProGuard */
    class ImapActivationDelegate extends ServerCommandBase<P, T>.TornadoDelegate {
        ImapActivationDelegate() {
            super();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onResponseOk(NetworkCommand.Response response) {
            try {
                return super.onResponseOk(response);
            } catch (ImapActivationException unused) {
                return new MailCommandStatus.IMAP_ACTIVATION_NOT_READY();
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class ImapActivationException extends RuntimeException {
        private ImapActivationException() {
        }
    }

    RequestWithImapActivation(Context context, P p10, HostProvider hostProvider) {
        super(context, p10, hostProvider);
    }

    @Keep
    public String getAcceptEncoding() {
        return "gzip";
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<P, T>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ImapActivationDelegate();
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new UncodedHtmlHostProvider(super.getHostProvider());
    }

    protected abstract T onImapActivationOk(JSONObject jSONObject) throws NetworkCommand.PostExecuteException;

    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    protected final T onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            if (!jSONObject.getJSONObject("body").optBoolean(JSON_IMAP_ACTIVATION_KEY, false)) {
                return onImapActivationOk(jSONObject);
            }
            LOG.i("Imap activation in progress");
            throw new ImapActivationException();
        } catch (JSONException e10) {
            LOG.e(e10.toString());
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    RequestWithImapActivation(Context context, P p10, HostProvider hostProvider, boolean z10) {
        super(context, p10, hostProvider, z10);
    }
}
