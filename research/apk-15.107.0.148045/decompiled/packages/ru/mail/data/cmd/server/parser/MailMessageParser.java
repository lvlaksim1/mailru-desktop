package ru.mail.data.cmd.server.parser;

import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailMessage;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class MailMessageParser extends JSONParser<MailMessage> {
    private JsonMessageParser mParser;

    public MailMessageParser(int i10, String str, boolean z10) {
        this.mParser = new JsonMessageParser(str, i10, z10);
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    public MailMessage parse(JSONObject jSONObject) throws JSONException {
        return this.mParser.parse(jSONObject);
    }
}
