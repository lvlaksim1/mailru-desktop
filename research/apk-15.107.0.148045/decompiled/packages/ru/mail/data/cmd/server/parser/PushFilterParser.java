package ru.mail.data.cmd.server.parser;

import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.pushfilters.PushFilter;
import ru.mail.logic.pushfilters.PushFilterEntity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class PushFilterParser extends JSONParser<PushFilterEntity> {
    public static final String ID = "id";
    public static final String NAME = "name";
    private final PushFilter.Type mType;

    public PushFilterParser(PushFilter.Type type) {
        this.mType = type;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    public PushFilterEntity parse(JSONObject jSONObject) throws JSONException {
        return new PushFilterEntity(jSONObject.getLong("id"), this.mType, (String) null, jSONObject.getString("name"));
    }
}
