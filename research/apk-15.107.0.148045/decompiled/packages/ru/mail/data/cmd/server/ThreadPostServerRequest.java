package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.Collection;
import java.util.LinkedList;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UncodedHtmlHostProvider;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class ThreadPostServerRequest<P extends ServerCommandBaseParams> extends PostServerRequest<P, ThreadPostBaseParams.Result> {
    private static final String JSON_FORMAT = "{ \"id\": \"%s\", \"folder\": %d, \"message_id_last\":\"%s\"}";
    private static final String JSON_KEY_BODY = "body";
    private static final Log LOG = Log.getLog("ThreadPostServerRequest");
    private static final String PARAM_KEY_FOLDER_ID = "folder";
    private static final String PARAM_KEY_LAST_MESSAGE_ID = "message_id_last";
    private static final String PARAM_KEY_THREAD_ID = "id";

    public ThreadPostServerRequest(Context context, P p10) {
        this(context, p10, false);
    }

    protected static JSONObject convertToJson(MailThread mailThread, MailBoxFolder mailBoxFolder) {
        return convertToJson(mailThread.getRepresentationByFolder(mailBoxFolder));
    }

    protected static JSONObject convertToJsonUsingLatestMsgIdInThread(MailThreadRepresentation mailThreadRepresentation) {
        String lastMessageId = null;
        for (MailThreadRepresentation mailThreadRepresentation2 : mailThreadRepresentation.getMailThread().getMailThreadRepresentations()) {
            if (lastMessageId == null || lastMessageId.compareTo(mailThreadRepresentation2.getLastMessageId()) < 0) {
                lastMessageId = mailThreadRepresentation2.getLastMessageId();
            }
        }
        return convertToJson(mailThreadRepresentation.getMailThread().getId(), mailThreadRepresentation.getFolderId(), lastMessageId);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<P, ThreadPostBaseParams.Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<P, ThreadPostBaseParams.Result>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.ThreadPostServerRequest.1
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus onBadRequest(JSONObject jSONObject) {
                return ThreadDelegateUtil.onBadRequest(jSONObject);
            }
        };
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new UncodedHtmlHostProvider(super.getHostProvider());
    }

    protected Collection<String> parseIds(JSONArray jSONArray) throws JSONException {
        LinkedList linkedList = new LinkedList();
        int length = jSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            linkedList.add(jSONArray.getString(i10));
        }
        return linkedList;
    }

    public ThreadPostServerRequest(Context context, P p10, boolean z10) {
        this(context, p10, null, z10);
    }

    protected static JSONObject convertToJson(MailThreadRepresentation mailThreadRepresentation) {
        return convertToJson(mailThreadRepresentation.getMailThread().getId(), mailThreadRepresentation.getFolderId(), mailThreadRepresentation.getLastMessageId());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public ThreadPostBaseParams.Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONArray jSONArray = new JSONObject(response.getRespString()).getJSONArray("body");
            Collection<String> ids = parseIds(jSONArray);
            LOG.d("OK body = " + jSONArray);
            return new ThreadPostBaseParams.Result(ids);
        } catch (JSONException e10) {
            LOG.e(e10.toString());
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    public ThreadPostServerRequest(Context context, P p10, HostProvider hostProvider, boolean z10) {
        super(context, p10, hostProvider, z10);
    }

    private static JSONObject convertToJson(String str, long j10, String str2) {
        try {
            return new JSONObject(String.format(JSON_FORMAT, str, Long.valueOf(j10), str2));
        } catch (JSONException e10) {
            throw new RuntimeException(e10);
        }
    }
}
