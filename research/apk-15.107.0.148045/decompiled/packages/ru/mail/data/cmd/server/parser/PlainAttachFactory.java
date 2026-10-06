package ru.mail.data.cmd.server.parser;

import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.AttachCloud;
import ru.mail.data.entities.AttachLink;
import ru.mail.data.entities.Collector;
import ru.mail.r7editor.impl.domain.analytics.R7Analytics;
import ru.mail.util.DateFormatUtils;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public interface PlainAttachFactory<T> {

    /* JADX INFO: compiled from: ProGuard */
    public static final class AttachCloudFactory implements PlainAttachFactory<AttachCloud> {
        @Override // ru.mail.data.cmd.server.parser.PlainAttachFactory
        @NonNull
        public AttachCloud createPlain(JSONObject jSONObject) throws JSONException {
            AttachCloud attachCloud = new AttachCloud();
            attachCloud.setId(JsonUtils.getStringFromJsonObject(jSONObject, "id", null));
            attachCloud.setName(JsonUtils.getStringFromJsonObject(jSONObject, "name", null));
            attachCloud.setContentType(JsonUtils.getStringFromJsonObject(jSONObject, "content_type", null));
            attachCloud.setSize(JsonUtils.getLongFromJsonObject(jSONObject, "size", 0L));
            String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jSONObject.optJSONObject("href"), R7Analytics.EVENT_DOWNLOAD, "");
            if (stringFromJsonObject.startsWith("/public/")) {
                stringFromJsonObject = stringFromJsonObject.substring(8);
            }
            attachCloud.setDownloadLink(stringFromJsonObject);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(Collector.FLAGS);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("unsafe")) {
                attachCloud.setUnsafe(jSONObjectOptJSONObject.optBoolean("unsafe", true));
            }
            attachCloud.setScanStatus(new ScanStatusParser().parseScanStatus(jSONObject.optString("scan")));
            return attachCloud;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static final class AttachLinkFactory implements PlainAttachFactory<AttachLink> {
        private String parseDueDate(JSONObject jSONObject) {
            return DateFormatUtils.filledMailDateFormat(Long.parseLong(JsonUtils.getStringFromJsonObject(jSONObject, AttachLink.COL_DUEDATE, null)) * 1000).split(",")[1];
        }

        @Override // ru.mail.data.cmd.server.parser.PlainAttachFactory
        @NonNull
        public AttachLink createPlain(JSONObject jSONObject) throws JSONException {
            AttachLink attachLink = new AttachLink();
            attachLink.setFileId(null);
            attachLink.setStaticFile(null);
            attachLink.setName(JsonUtils.getStringFromJsonObject(jSONObject, "name", null));
            attachLink.setContentType(JsonUtils.getStringFromJsonObject(jSONObject, "content_type", null));
            attachLink.setSize(Long.valueOf(JsonUtils.getLongFromJsonObject(jSONObject, "size", 0L)));
            attachLink.setDueDate(parseDueDate(jSONObject));
            attachLink.setDownloadLink(JsonUtils.getStringFromJsonObject(jSONObject.optJSONObject("href"), R7Analytics.EVENT_DOWNLOAD, null));
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(Collector.FLAGS);
            if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.has("unsafe")) {
                attachLink.setUnsafe(jSONObjectOptJSONObject.optBoolean("unsafe", true));
            }
            attachLink.setScanStatus(new ScanStatusParser().parseScanStatus(jSONObject.optString("scan")));
            return attachLink;
        }
    }

    @NonNull
    T createPlain(JSONObject jSONObject) throws JSONException;
}
