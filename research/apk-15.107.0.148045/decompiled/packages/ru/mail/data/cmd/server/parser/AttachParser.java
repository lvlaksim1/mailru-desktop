package ru.mail.data.cmd.server.parser;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.cmd.server.TornadoSendRequest;
import ru.mail.data.entities.Attach;
import ru.mail.data.entities.Collector;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.logic.content.AttachmentHelper;
import ru.mail.r7editor.impl.domain.analytics.R7Analytics;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class AttachParser extends JSONParser<Attach> {

    @Nullable
    private final String mAccount;

    @NonNull
    private final MailMessageContent mContent;
    private final Context mContext;

    public AttachParser(Context context, @NonNull MailMessageContent mailMessageContent, @Nullable String str) {
        this.mContent = mailMessageContent;
        this.mAccount = str;
        this.mContext = context;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    protected boolean needToParse(JSONObject jSONObject) {
        return JsonUtils.getStringFromJsonObject(jSONObject, "type", "attach").equals("attach");
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    public Attach parse(JSONObject jSONObject) throws JSONException {
        String str;
        Attach attach = new Attach();
        String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jSONObject, "id", null);
        if (this.mContent.getId() != null) {
            str = this.mContent.getId() + MailThreadRepresentation.PAYLOAD_DELIM_CHAR + stringFromJsonObject;
        } else {
            str = null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("legacy");
        String strOptString = jSONObject2.optString("tnef_id", "");
        if (str != null && !TextUtils.isEmpty(strOptString) && !str.contains(":")) {
            str = str + ":" + strOptString;
        }
        attach.setId(str);
        attach.setAttachName(JsonUtils.getStringFromJsonObject(jSONObject, "name", null));
        attach.setShowThumbnails(JsonUtils.getIntFromJsonObject(jSONObject2, "ShowThumbnail", 0) == 1);
        long longFromJsonObject = JsonUtils.getLongFromJsonObject(jSONObject2, "OriginalBodyLen", 0L);
        if (longFromJsonObject == 0) {
            longFromJsonObject = JsonUtils.getLongFromJsonObject(jSONObject, "size", 0L);
        }
        attach.setAttachBodyLength(longFromJsonObject);
        attach.setAttachContentType(JsonUtils.getStringFromJsonObject(jSONObject, "content_type", null));
        attach.setDownloadLink(JsonUtils.getStringFromJsonObject(jSONObject.getJSONObject("href"), R7Analytics.EVENT_DOWNLOAD, null));
        attach.setMessageContent(this.mContent);
        attach.setAccountName(this.mAccount);
        attach.setPrefetchPath(AttachmentHelper.getAttachPrefetchLocalPath(this.mContext, this.mContent, attach));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("thumbnails");
        if (jSONObjectOptJSONObject != null) {
            attach.setThumbnailHtml(jSONObjectOptJSONObject.optString(TornadoSendRequest.FIELD_BODY_HTML));
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject(Collector.FLAGS);
        if (jSONObjectOptJSONObject2 != null && jSONObjectOptJSONObject2.has("unsafe")) {
            attach.setUnsafe(jSONObjectOptJSONObject2.optBoolean("unsafe", true));
        }
        attach.setScanStatus(new ScanStatusParser().parseScanStatus(jSONObject.optString("scan")));
        return attach;
    }
}
