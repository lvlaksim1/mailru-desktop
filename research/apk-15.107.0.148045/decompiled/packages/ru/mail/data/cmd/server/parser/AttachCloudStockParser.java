package ru.mail.data.cmd.server.parser;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.AttachCloudStock;
import ru.mail.data.entities.Collector;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.logic.content.AttachmentHelper;
import ru.mail.r7editor.impl.domain.analytics.R7Analytics;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class AttachCloudStockParser extends JSONParser<AttachCloudStock> {
    private final MailMessageContent mContent;
    private final Context mContext;

    public AttachCloudStockParser(Context context, MailMessageContent mailMessageContent) {
        this.mContext = context;
        this.mContent = mailMessageContent;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    protected boolean needToParse(JSONObject jSONObject) {
        return JsonUtils.getStringFromJsonObject(jSONObject, "type", "attach").equals("cloud_stock");
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    public AttachCloudStock parse(JSONObject jSONObject) throws JSONException {
        AttachCloudStock attachCloudStock = new AttachCloudStock();
        attachCloudStock.setFileId(JsonUtils.getStringFromJsonObject(jSONObject, "id", null));
        attachCloudStock.setName(JsonUtils.getStringFromJsonObject(jSONObject, "name", null));
        attachCloudStock.setContentType(JsonUtils.getStringFromJsonObject(jSONObject, "content_type", null));
        attachCloudStock.setSize(JsonUtils.getLongFromJsonObject(jSONObject, "size", 0L));
        attachCloudStock.setDownloadLink(JsonUtils.getStringFromJsonObject(jSONObject.getJSONObject("href"), R7Analytics.EVENT_DOWNLOAD, null));
        attachCloudStock.setMessageContent(this.mContent);
        attachCloudStock.setPrefetchPath(AttachmentHelper.getAttachPrefetchLocalPath(this.mContext, this.mContent, attachCloudStock));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(Collector.FLAGS);
        if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("unsafe")) {
            attachCloudStock.setUnsafe(jSONObjectOptJSONObject.optBoolean("unsafe", true));
        }
        attachCloudStock.setScanStatus(new ScanStatusParser().parseScanStatus(jSONObject.optString("scan")));
        return attachCloudStock;
    }
}
