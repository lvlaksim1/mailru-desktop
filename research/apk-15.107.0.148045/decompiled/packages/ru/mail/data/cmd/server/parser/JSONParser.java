package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class JSONParser<T> {
    protected int collectionSize = 0;

    protected boolean needToParse(JSONObject jSONObject) {
        return true;
    }

    public abstract T parse(JSONObject jSONObject) throws JSONException;

    public List<T> parse(JSONArray jSONArray) throws JSONException {
        T t10;
        ArrayList arrayList = new ArrayList();
        this.collectionSize = 0;
        int length = jSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i10);
            if (needToParse(jSONObject) && (t10 = parse(jSONObject)) != null) {
                this.collectionSize++;
                arrayList.add(t10);
            }
        }
        return arrayList;
    }
}
