package ru.mail.util.push.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.mail.portal.app.adapter.notifications.PortalPushButton;
import ru.mail.util.log.Log;
import ru.mail.util.push.PortalPush;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0002J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u0002R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lru/mail/util/push/parser/PortalPushParser;", "", "data", "", "", "<init>", "(Ljava/util/Map;)V", "parse", "Lru/mail/util/push/PortalPush;", "parseButtons", "", "Lru/mail/portal/app/adapter/notifications/PortalPushButton;", "jsonArrayButtons", "parseCloseNotificationString", "", "closeNotificationString", "getOrThrow", "key", "getOpenUrl", BlockParser.MAP_TYPE, "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PortalPushParser {

    @NotNull
    private static final String DATA_KEY_CLOSE_NOTIFICATION_BY_CLICK = "close_by_click";

    @NotNull
    private static final String DATA_KEY_NEED_CALL_OPEN_URL = "send_open_action";

    @NotNull
    private static final String DATA_KEY_PORTAL_APP = "app";

    @NotNull
    private static final String DATA_KEY_PORTAL_BODY = "body";

    @NotNull
    private static final String DATA_KEY_PORTAL_BUTTONS = "buttons";

    @NotNull
    private static final String DATA_KEY_PORTAL_CAMPAIGN = "push_campaign";

    @NotNull
    private static final String DATA_KEY_PORTAL_DEEPLINK = "dl";

    @NotNull
    private static final String DATA_KEY_PORTAL_EMAIL = "email";

    @NotNull
    private static final String DATA_KEY_PORTAL_IMAGE_TYPE = "img_type";

    @NotNull
    private static final String DATA_KEY_PORTAL_IMAGE_URL = "img_url";

    @NotNull
    private static final String DATA_KEY_PORTAL_TITLE = "title";

    @NotNull
    private static final String DATA_KEY_SUMMARY_TEXT = "summary";

    @NotNull
    private final Map<String, String> data;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PortalPushParser");

    public PortalPushParser(@NotNull Map<String, String> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }

    private final String getOpenUrl(Map<String, String> map) {
        String str = map.get(PushProcessor.DATA_KEY_HUB_LINK);
        if (str == null) {
            return null;
        }
        try {
            return new JSONObject(str).getString("open");
        } catch (JSONException e10) {
            LOG.d("Can't parse ack from hub link: " + e10);
            return null;
        }
    }

    private final String getOrThrow(String key) throws NoSuchElementException {
        try {
            return (String) MapsKt.getValue(this.data, key);
        } catch (NoSuchElementException unused) {
            throw new NoSuchElementException("PortalPushParser can't get \"" + key + "\" from push");
        }
    }

    private final List<PortalPushButton> parseButtons(String jsonArrayButtons) throws JSONException {
        if (jsonArrayButtons == null) {
            jsonArrayButtons = "[]";
        }
        JSONArray jSONArray = new JSONArray(jsonArrayButtons);
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i10);
            String strOptString = jSONObject.optString("title");
            String strOptString2 = jSONObject.optString(DATA_KEY_PORTAL_DEEPLINK);
            boolean zOptBoolean = jSONObject.optBoolean(DATA_KEY_NEED_CALL_OPEN_URL);
            String strOptString3 = jSONObject.optString(DATA_KEY_CLOSE_NOTIFICATION_BY_CLICK);
            Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(...)");
            boolean closeNotificationString = parseCloseNotificationString(strOptString3);
            if (strOptString != null && strOptString.length() != 0 && strOptString2 != null && strOptString2.length() != 0) {
                arrayList.add(new PortalPushButton(strOptString, strOptString2, closeNotificationString, zOptBoolean));
            }
        }
        return arrayList;
    }

    private final boolean parseCloseNotificationString(String closeNotificationString) {
        if (closeNotificationString.length() == 0) {
            return true;
        }
        return Boolean.parseBoolean(closeNotificationString);
    }

    @NotNull
    public final PortalPush parse() throws JSONException {
        return new PortalPush(getOrThrow(DATA_KEY_PORTAL_DEEPLINK), getOrThrow("title"), this.data.get("body"), this.data.get(DATA_KEY_PORTAL_CAMPAIGN), this.data.get("email"), 4001, this.data.get("img_url"), this.data.get(DATA_KEY_PORTAL_IMAGE_TYPE), this.data.get(PushProcessor.DATA_KEY_LANG_FILTER), parseButtons(this.data.get(DATA_KEY_PORTAL_BUTTONS)), this.data.get("app"), getOpenUrl(this.data), this.data.get("summary"));
    }
}
