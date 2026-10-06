package ru.mail.data.cmd.server.parser;

import android.content.Context;
import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.AttachCloud;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.logic.content.AttachmentHelper;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class AttachCloudParser extends JSONParser<AttachCloud> {
    private final MailMessageContent mContent;
    private final Context mContext;
    private final PlainAttachFactory<AttachCloud> mFactory = new PlainAttachFactory.AttachCloudFactory();

    public AttachCloudParser(Context context, @NonNull MailMessageContent mailMessageContent) {
        this.mContext = context;
        this.mContent = mailMessageContent;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    protected boolean needToParse(JSONObject jSONObject) {
        return JsonUtils.getStringFromJsonObject(jSONObject, "type", "attach").equals("cloud");
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    public AttachCloud parse(JSONObject jSONObject) throws JSONException {
        AttachCloud attachCloudCreatePlain = this.mFactory.createPlain(jSONObject);
        attachCloudCreatePlain.setMessageContent(this.mContent);
        attachCloudCreatePlain.setPrefetchPath(AttachmentHelper.getAttachPrefetchLocalPath(this.mContext, this.mContent, attachCloudCreatePlain));
        return attachCloudCreatePlain;
    }
}
