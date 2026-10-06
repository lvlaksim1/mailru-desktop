package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.Attach;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class InlineAttachParser {
    private static final Pattern sAttachToCidPattern = Pattern.compile("\"([0-9;]+)\"\\s*:\\s*\"(cid:.*?)\"(?:$|,)");

    public List<Attach> parse(JSONObject jSONObject) throws JSONException {
        String strOptString = jSONObject.optString("map_inline_img");
        ArrayList arrayList = new ArrayList();
        Matcher matcher = sAttachToCidPattern.matcher(strOptString);
        while (matcher.find()) {
            if (matcher.groupCount() == 2) {
                arrayList.add(new Attach(matcher.group(1), matcher.group(2)));
            }
        }
        return arrayList;
    }
}
