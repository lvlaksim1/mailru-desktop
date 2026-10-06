package ru.mail.data.cmd.server.parser;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.AttachLink;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.logic.content.AttachmentHelper;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class AttachLinkParser extends JSONParser<AttachLink> {
    private final MailMessageContent mContent;
    private final Context mContext;
    private final PlainAttachFactory.AttachLinkFactory mFactory = new PlainAttachFactory.AttachLinkFactory();

    public AttachLinkParser(Context context, MailMessageContent mailMessageContent) {
        this.mContext = context;
        this.mContent = mailMessageContent;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    protected boolean needToParse(JSONObject jSONObject) {
        return JsonUtils.getStringFromJsonObject(jSONObject, "type", "attach").equals("link");
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    public AttachLink parse(JSONObject jSONObject) throws JSONException {
        AttachLink attachLinkCreatePlain = this.mFactory.createPlain(jSONObject);
        attachLinkCreatePlain.setMessageContent(this.mContent);
        attachLinkCreatePlain.setPrefetchPath(AttachmentHelper.getAttachPrefetchLocalPath(this.mContext, this.mContent, attachLinkCreatePlain));
        return attachLinkCreatePlain;
    }
}
