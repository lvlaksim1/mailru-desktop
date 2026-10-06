package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.Collector;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.data.entities.MetaThread;
import ru.mail.logic.content.MailPriority;
import ru.mail.search.metasearch.util.analytics.AnalyticsUtilsExtKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class MetaThreadParser {
    private static final String REPRESENTATIONS = "representations";
    private final String mAccount;

    public MetaThreadParser(String str) {
        this.mAccount = str;
    }

    private List<String> parseLastDomains(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray(MetaThread.COL_NAME_LAST_DOMAINS);
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            arrayList.add(jSONArray.getString(i10));
        }
        return arrayList;
    }

    private static List<MetaThread.LastSender> parseLastSenders(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("last_senders");
        if (jSONArrayOptJSONArray != null) {
            for (int i10 = 0; i10 < jSONArrayOptJSONArray.length(); i10++) {
                MetaThread.LastSender lastSender = new MetaThread.LastSender();
                JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i10);
                lastSender.setUnread(jSONObject2.optBoolean(AnalyticsUtilsExtKt.FILTER_UNREAD));
                lastSender.setName(jSONObject2.optString("name"));
                lastSender.setSubject(jSONObject2.optString("subject"));
                arrayList.add(lastSender);
            }
        }
        return arrayList;
    }

    private MailPriority parsePriority(JSONObject jSONObject) throws JSONException {
        return new MailPriority.Parser().parsePriority(jSONObject.getInt("date"));
    }

    public boolean isMetaThread(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(REPRESENTATIONS);
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            return false;
        }
        return jSONArrayOptJSONArray.getJSONObject(0).getJSONObject(Collector.FLAGS).optBoolean(MailBoxFolder.COL_NAME_META_THREAD);
    }

    public MetaThread parse(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONArray(REPRESENTATIONS).getJSONObject(0);
        JSONObject jSONObject3 = jSONObject2.getJSONObject("metathread_data");
        MetaThread metaThread = new MetaThread();
        metaThread.setAccount(this.mAccount);
        metaThread.setCategory(jSONObject3.getString("category"));
        metaThread.setDate(jSONObject2.getLong("date"));
        metaThread.setFolderId(jSONObject3.getLong("folder"));
        metaThread.setFolderName(jSONObject3.getString("folder_name"));
        metaThread.setLastDomains(parseLastDomains(jSONObject3));
        metaThread.setLastSenders(parseLastSenders(jSONObject3));
        metaThread.setServerLastMessageId(jSONObject2.getString("message_id_last"));
        metaThread.setNewEmailsCount(jSONObject3.getInt("new_cnt"));
        metaThread.setPriority(parsePriority(jSONObject2));
        metaThread.setMessagesCount(jSONObject2.getInt(MailThread.COL_NAME_LENGTH));
        metaThread.setUnreadCount(jSONObject2.getInt(MailThreadRepresentation.COL_NAME_LENGTH_UNREAD));
        return metaThread;
    }
}
