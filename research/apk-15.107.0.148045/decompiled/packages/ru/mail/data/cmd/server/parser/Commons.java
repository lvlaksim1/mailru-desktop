package ru.mail.data.cmd.server.parser;

import android.text.TextUtils;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.utils.JsonUtils;
import ru.mail.utils.StringEscapeUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\"\u0010\u0004\u001a\u00020\u00052\u001a\u0010\u0006\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t0\bJ\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/parser/Commons;", "", "<init>", "()V", "convertAddressesToString", "", "addresses", "Lorg/json/JSONArray;", "", "Lkotlin/Pair;", "replaceSpecialCodesInAddress", "address", "escapeQuotes", "str", "getFirstGoToActionMetaJSONObjectOrNull", "Lorg/json/JSONObject;", "metaArray", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Commons {

    @NotNull
    public static final Commons INSTANCE = new Commons();

    private Commons() {
    }

    @JvmStatic
    @NotNull
    public static final String convertAddressesToString(@NotNull JSONArray addresses) throws JSONException {
        Intrinsics.checkNotNullParameter(addresses, "addresses");
        StringBuilder sb2 = new StringBuilder();
        int length = addresses.length();
        for (int i10 = 0; i10 < length; i10++) {
            Object obj = addresses.get(i10);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
            JSONObject jSONObject = (JSONObject) obj;
            sb2.append('\"');
            Commons commons = INSTANCE;
            String strOptString = jSONObject.optString("name");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            sb2.append(commons.replaceSpecialCodesInAddress(strOptString));
            sb2.append("\" <");
            sb2.append(jSONObject.getString("email"));
            sb2.append(SimpleComparison.GREATER_THAN_OPERATION);
            if (i10 < jSONObject.length() - 1) {
                sb2.append(",");
            }
        }
        String string = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final String escapeQuotes(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String strReplaceQuotes = StringEscapeUtils.replaceQuotes(str);
        Intrinsics.checkNotNull(strReplaceQuotes);
        return strReplaceQuotes;
    }

    private final String replaceSpecialCodesInAddress(String address) {
        String strReplaceSpecialCodes = StringEscapeUtils.replaceSpecialCodes(address);
        Intrinsics.checkNotNull(strReplaceSpecialCodes);
        return escapeQuotes(strReplaceSpecialCodes);
    }

    @Nullable
    public final JSONObject getFirstGoToActionMetaJSONObjectOrNull(@NotNull JSONArray metaArray) {
        Intrinsics.checkNotNullParameter(metaArray, "metaArray");
        int length = metaArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jsonObjectFromJsonArray = JsonUtils.getJsonObjectFromJsonArray(metaArray, i10, null);
            if (MailGoToActionMetaParser.INSTANCE.parse(jsonObjectFromJsonArray) != null) {
                return jsonObjectFromJsonArray;
            }
        }
        return null;
    }

    @NotNull
    public final String convertAddressesToString(@NotNull List<Pair<String, String>> addresses) {
        Intrinsics.checkNotNullParameter(addresses, "addresses");
        StringBuilder sb2 = new StringBuilder();
        int size = addresses.size();
        for (int i10 = 0; i10 < size; i10++) {
            Pair<String, String> pair = addresses.get(i10);
            String strComponent1 = pair.component1();
            String strComponent2 = pair.component2();
            sb2.append('\"');
            sb2.append(replaceSpecialCodesInAddress(strComponent1));
            sb2.append("\" <");
            sb2.append(strComponent2);
            sb2.append(SimpleComparison.GREATER_THAN_OPERATION);
            if (i10 < addresses.size() - 1) {
                sb2.append(",");
            }
        }
        String string = sb2.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
