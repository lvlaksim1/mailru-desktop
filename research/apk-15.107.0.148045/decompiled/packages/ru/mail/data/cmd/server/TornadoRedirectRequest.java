package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.mails.R;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "services", "bounce"})
public class TornadoRedirectRequest extends PostServerRequest<Params, EmptyResult> {
    private static final Log LOG = Log.getLog("TornadoRedirectRequest");
    public static final String TAG_BODY = "body";
    public static final String TAG_CORRESPONDENTS_BCC = "correspondents.bcc";
    public static final String TAG_CORRESPONDENTS_CC = "correspondents.cc";
    public static final String TAG_ERROR = "error";
    public static final String TAG_INVALID = "invalid";
    public static final String TAG_SEND_DATE = "send_date";
    public static final String TAG_TO = "to";
    public static final String TAG_VALUE = "value";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "id")
        private final String mId;

        @Param(method = HttpMethod.POST, name = "to")
        private final String mTo;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String str, @Nullable String str2) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mId = str;
            this.mTo = str2;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            String str = this.mTo;
            if (str == null ? params.mTo != null : !str.equals(params.mTo)) {
                return false;
            }
            String str2 = this.mId;
            String str3 = params.mId;
            return str2 == null ? str3 == null : str2.equals(str3);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mId;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mTo;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }
    }

    public TornadoRedirectRequest(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.TornadoRedirectRequest.1
            private CommandStatus<?> onInternalServerError() throws JSONException {
                CommandStatus<?> commandStatusOnBadRequest = getDelegate().onBadRequest(new JSONObject(getResponse().getRespString()));
                return commandStatusOnBadRequest.getClass().equals(MailCommandStatus.FAILED_BACKEND_QUOTE.class) ? commandStatusOnBadRequest : new CommandStatus.SIMPLE_ERROR(TornadoRedirectRequest.this.getContext().getString(R.string.wrong_email));
            }

            @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                if (getResponse().getStatusCode() == 200) {
                    int i10 = Integer.parseInt(getDelegate().getResponseStatus(getResponse().getRespString()));
                    try {
                        if (i10 == 400) {
                            try {
                                JSONObject jSONObject = new JSONObject(getResponse().getRespString());
                                if (jSONObject.has("body")) {
                                    JSONObject jSONObject2 = jSONObject.getJSONObject("body");
                                    if (!jSONObject2.has("to")) {
                                        return super.process();
                                    }
                                    JSONObject jSONObject3 = jSONObject2.getJSONObject("to");
                                    if (jSONObject3.has("error") && jSONObject3.get("error").equals("invalid_addresses")) {
                                        return new CommandStatus.SIMPLE_ERROR(TornadoRedirectRequest.this.getContext().getString(R.string.one_invalid_recipient));
                                    }
                                }
                            } catch (JSONException e10) {
                                TornadoRedirectRequest.LOG.e("parsing json error", e10);
                            }
                        } else {
                            if (i10 == 500) {
                                return onInternalServerError();
                            }
                            if (i10 != 403 && i10 > 400 && i10 < 600) {
                                return new CommandStatus.SIMPLE_ERROR(TornadoRedirectRequest.this.getContext().getString(R.string.wrong_email));
                            }
                        }
                    } catch (JSONException e11) {
                        TornadoRedirectRequest.LOG.e("parsing json error", e11);
                    }
                }
                return super.process();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
