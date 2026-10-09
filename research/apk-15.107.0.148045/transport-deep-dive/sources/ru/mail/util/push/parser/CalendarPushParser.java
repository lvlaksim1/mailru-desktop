package ru.mail.util.push.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.MailItemUtilKt;
import ru.mail.util.push.calendar.Action;
import ru.mail.util.push.calendar.CalendarAction;
import ru.mail.util.push.calendar.CalendarNotificationPush;
import ru.mail.util.push.calendar.Style;
import ru.mail.util.push.calendar.Type;
import ru.mail.util.push.calendar.payload.Payload;
import ru.mail.util.push.calendar.payload.PayloadFactoryImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ$\u0010\u000b\u001a\u00020\f2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u000e"}, d2 = {"Lru/mail/util/push/parser/CalendarPushParser;", "", "<init>", "()V", "parse", "Lru/mail/util/push/calendar/CalendarNotificationPush;", "data", "", "", "eventId", "", "getType", "Lru/mail/util/push/calendar/Type;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCalendarPushParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CalendarPushParser.kt\nru/mail/util/push/parser/CalendarPushParser\n+ 2 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,158:1\n32#2,2:159\n32#2,2:161\n*S KotlinDebug\n*F\n+ 1 CalendarPushParser.kt\nru/mail/util/push/parser/CalendarPushParser\n*L\n81#1:159,2\n89#1:161,2\n*E\n"})
public final class CalendarPushParser {
    public static final int $stable = 0;

    @NotNull
    private static final String DATA_KEY_CALENDAR_ACTION = "action";

    @NotNull
    private static final String DATA_KEY_CALENDAR_ACTIONS = "actions";

    @NotNull
    private static final String DATA_KEY_CALENDAR_BUTTON_TEXT = "text";

    @NotNull
    private static final String DATA_KEY_CALENDAR_EVENT_CALL_URL = "call_url";

    @NotNull
    private static final String DATA_KEY_CALENDAR_EVENT_TIME = "event_start";

    @NotNull
    private static final String DATA_KEY_CALENDAR_EVENT_URL = "event_url";

    @NotNull
    private static final String DATA_KEY_CALENDAR_ID = "amp_token";

    @NotNull
    private static final String DATA_KEY_CALENDAR_LOGIN = "login";

    @NotNull
    private static final String DATA_KEY_CALENDAR_ML_SUBTYPE = "ml_subtype";

    @NotNull
    private static final String DATA_KEY_CALENDAR_ML_TYPE = "ml_type";

    @NotNull
    private static final String DATA_KEY_CALENDAR_MSG = "msg";

    @NotNull
    private static final String DATA_KEY_CALENDAR_ORGANIZER = "organizer";

    @NotNull
    private static final String DATA_KEY_CALENDAR_PAYLOAD = "payload";

    @NotNull
    private static final String DATA_KEY_CALENDAR_STYLE = "style";

    @NotNull
    private static final String DATA_KEY_CALENDAR_SUMMARY_TEXT = "summary";

    @NotNull
    private static final String DATA_KEY_CALENDAR_TITLE = "title";

    @NotNull
    private static final String DATA_KEY_CALENDAR_TYPE = "push_type";

    private final Type getType(Map<String, String> data, int eventId) {
        String str = data.get("push_type");
        String str2 = data.get(DATA_KEY_CALENDAR_EVENT_CALL_URL);
        boolean z10 = eventId == 3001 || !(str2 == null || StringsKt.isBlank(str2));
        if (str != null) {
            return Type.INSTANCE.from(str);
        }
        if (z10) {
            return Type.REMINDER_WITH_CALL;
        }
        String str3 = data.get("actions");
        if (str3 == null) {
            str3 = "[]";
        }
        return MailItemUtilKt.isEmpty(new JSONArray(str3)) ? Type.REMINDER : Type.REMINDER_ML;
    }

    @NotNull
    public final CalendarNotificationPush parse(@NotNull Map<String, String> data, int eventId) throws JSONException, NoSuchElementException {
        JSONArray jSONArray;
        int i10;
        int i11;
        Intrinsics.checkNotNullParameter(data, "data");
        ParserUtils parserUtils = ParserUtils.INSTANCE;
        String orThrow = parserUtils.getOrThrow(data, DATA_KEY_CALENDAR_ID);
        String orThrow2 = parserUtils.getOrThrow(data, "login");
        String orThrow3 = parserUtils.getOrThrow(data, "title");
        String orThrow4 = parserUtils.getOrThrow(data, "msg");
        String orThrow5 = parserUtils.getOrThrow(data, DATA_KEY_CALENDAR_EVENT_URL);
        String str = data.get(DATA_KEY_CALENDAR_EVENT_CALL_URL);
        if (str == null) {
            str = "";
        }
        String str2 = str;
        String openUrl = parserUtils.getOpenUrl(data);
        String str3 = data.get("actions");
        if (str3 == null) {
            str3 = "[]";
        }
        JSONArray jSONArray2 = new JSONArray(str3);
        String str4 = data.get("summary");
        ArrayList arrayList = new ArrayList();
        int length = jSONArray2.length();
        int i12 = 0;
        while (i12 < length) {
            JSONObject jSONObject = jSONArray2.getJSONObject(i12);
            String strOptString = jSONObject.optString("action", GrsBaseInfo.CountryCodeSource.UNKNOWN);
            Action.Companion companion = Action.INSTANCE;
            Intrinsics.checkNotNull(strOptString);
            Action actionFrom = companion.from(strOptString);
            if (actionFrom == Action.UNKNOWN) {
                jSONArray = jSONArray2;
                i10 = length;
                i11 = i12;
            } else {
                JSONObject jSONObject2 = jSONObject.getJSONObject("payload");
                HashMap map = new HashMap();
                Iterator<String> itKeys = jSONObject2.keys();
                jSONArray = jSONArray2;
                Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
                while (itKeys.hasNext()) {
                    int i13 = length;
                    String next = itKeys.next();
                    map.put(next, jSONObject2.getString(next));
                    i12 = i12;
                    length = i13;
                }
                i10 = length;
                i11 = i12;
                Payload payloadCreatePayload = new PayloadFactoryImpl().createPayload(actionFrom, map);
                if (payloadCreatePayload != null) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject("text");
                    HashMap map2 = new HashMap();
                    Iterator<String> itKeys2 = jSONObject3.keys();
                    Intrinsics.checkNotNullExpressionValue(itKeys2, "keys(...)");
                    while (itKeys2.hasNext()) {
                        String next2 = itKeys2.next();
                        map2.put(next2, jSONObject3.getString(next2));
                    }
                    String strOptString2 = jSONObject.optString("style", "BASE");
                    Style.Companion companion2 = Style.INSTANCE;
                    Intrinsics.checkNotNull(strOptString2);
                    arrayList.add(new CalendarAction(map2, payloadCreatePayload, actionFrom, companion2.from(strOptString2)));
                }
            }
            i12 = i11 + 1;
            jSONArray2 = jSONArray;
            length = i10;
        }
        Type type = getType(data, eventId);
        String str5 = data.get("organizer");
        Date eventTime = TimeParserUtils.INSTANCE.getEventTime(data.get(DATA_KEY_CALENDAR_EVENT_TIME));
        MlPushParserUtils mlPushParserUtils = MlPushParserUtils.INSTANCE;
        return new CalendarNotificationPush(orThrow, eventId, orThrow2, orThrow3, orThrow4, orThrow5, str2, arrayList, type, mlPushParserUtils.getMLType(data.get(DATA_KEY_CALENDAR_ML_TYPE)), mlPushParserUtils.getMLSubtype(data.get(DATA_KEY_CALENDAR_ML_SUBTYPE)), str5, eventTime, openUrl, str4);
    }
}
