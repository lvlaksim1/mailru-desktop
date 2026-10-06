package ru.mail.data.cmd.server;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class ThreadDelegateUtil {
    private static final String JSON_KEY_ERROR = "error";
    private static final String JSON_KEY_VALUE = "value";
    private static final Log LOG = Log.getLog("ThreadDelegateUtil");
    private static final String PARAM_KEY_FOLDER_ID = "folder";
    private static final String PARAM_KEY_ID = "id";
    private static final String PARAM_KEY_LAST_MESSAGE_ID = "message_id_last";

    public static String getKey(JSONObject jSONObject) {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next.contains(PARAM_KEY_LAST_MESSAGE_ID) || next.contains("folder") || next.contains("id")) {
                return next;
            }
        }
        return null;
    }

    private static CommandStatus handleError(String str, JSONObject jSONObject) {
        if (str != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (str.contains(PARAM_KEY_LAST_MESSAGE_ID)) {
                    return handleMessageIdError(jSONObject2);
                }
                if (str.contains("id")) {
                    return handleThreadError(jSONObject2);
                }
                if (str.contains("folder")) {
                    return handleFolderError(jSONObject2);
                }
            } catch (JSONException e10) {
                LOG.e(e10.toString());
                return new CommandStatus.ERROR(e10);
            }
        }
        return new NetworkCommandStatus.BAD_REQUEST(jSONObject);
    }

    private static CommandStatus handleFolderError(JSONObject jSONObject) {
        return new NetworkCommandStatus.BAD_REQUEST(jSONObject.optString("error"));
    }

    private static CommandStatus handleMessageIdError(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("error");
        strOptString.getClass();
        if (strOptString.equals("malformed")) {
            return new NetworkCommandStatus.BAD_REQUEST(strOptString);
        }
        return !strOptString.equals("not_exists") ? new NetworkCommandStatus.BAD_REQUEST() : new MailCommandStatus.MESSAGE_NOT_IN_THREAD(jSONObject.optString("value"));
    }

    private static CommandStatus handleThreadError(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("error");
        String strOptString2 = jSONObject.optString("value");
        strOptString.getClass();
        if (strOptString.equals("not_exists")) {
            return new MailCommandStatus.THREAD_NOT_EXIST(strOptString2);
        }
        return !strOptString.equals("invalid") ? new NetworkCommandStatus.BAD_REQUEST() : new MailCommandStatus.INVALID_THREAD(strOptString2);
    }

    public static CommandStatus onBadRequest(JSONObject jSONObject) {
        return jSONObject != null ? handleError(getKey(jSONObject), jSONObject) : new NetworkCommandStatus.BAD_REQUEST();
    }
}
