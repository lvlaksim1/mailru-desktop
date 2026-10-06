package ru.mail.data.cmd.server.parser;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailMessage;
import ru.mail.logic.content.SearchHighlight;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0002JB\u0010\u0010\u001a\u00020\u00112.\u0010\u0012\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00140\u0013j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0014`\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u001a"}, d2 = {"Lru/mail/data/cmd/server/parser/JsonSearchMsgParser;", "Lru/mail/data/cmd/server/parser/JsonMessageParser;", "account", "", "snippetLimit", "", "isColoredTagsOn", "", "<init>", "(Ljava/lang/String;IZ)V", "parse", "Lru/mail/data/entities/MailMessage;", "jsonObject", "Lorg/json/JSONObject;", "parseSearchHighlight", "Lru/mail/logic/content/SearchHighlight;", "fillList", "", "list", "Ljava/util/ArrayList;", "Landroid/util/Pair;", "Lkotlin/collections/ArrayList;", "highlightColorArray", "Lorg/json/JSONArray;", "getSenderHighlight", "Companion", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JsonSearchMsgParser extends JsonMessageParser {

    @NotNull
    private static final Companion Companion = new Companion(null);

    @Deprecated
    @NotNull
    public static final String KEY_COLOR = "color";

    @Deprecated
    @NotNull
    public static final String KEY_CORRESPONDENTS = "correspondents";

    @Deprecated
    @NotNull
    public static final String KEY_FROM = "from";

    @Deprecated
    @NotNull
    public static final String KEY_SEARCH_SNIPPET = "search_snippet";

    @Deprecated
    @NotNull
    public static final String KEY_SEARCH_SUBJECT = "search_subject";

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/server/parser/JsonSearchMsgParser$Companion;", "", "<init>", "()V", "KEY_COLOR", "", "KEY_CORRESPONDENTS", "KEY_FROM", "KEY_SEARCH_SUBJECT", "KEY_SEARCH_SNIPPET", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonSearchMsgParser(@NotNull String account, int i10, boolean z10) {
        super(account, i10, z10);
        Intrinsics.checkNotNullParameter(account, "account");
    }

    private final void fillList(ArrayList<Pair<Integer, Integer>> list, JSONArray highlightColorArray) {
        if (highlightColorArray != null) {
            Iterator<JSONArray> it = JsonUtils.getListFromJsonArray(highlightColorArray).iterator();
            while (it.hasNext()) {
                list.add(JsonUtils.getPairIntFromJsonArray(it.next()));
            }
        }
    }

    private final JSONArray getSenderHighlight(JSONObject jsonObject) throws JSONException {
        JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(JsonUtils.getJsonObjectFromJsonObject(jsonObject, "correspondents", null), "from");
        if (jsonArrayFromJsonObject == null || jsonArrayFromJsonObject.length() == 0) {
            return null;
        }
        Object obj = jsonArrayFromJsonObject.get(0);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
        return JsonUtils.getJsonArrayFromJsonObject((JSONObject) obj, "color");
    }

    private final SearchHighlight parseSearchHighlight(JSONObject jsonObject) throws JSONException {
        JSONObject jsonObjectFromJsonObject = JsonUtils.getJsonObjectFromJsonObject(jsonObject, KEY_SEARCH_SUBJECT, null);
        JSONObject jsonObjectFromJsonObject2 = JsonUtils.getJsonObjectFromJsonObject(jsonObject, KEY_SEARCH_SNIPPET, null);
        JSONArray senderHighlight = getSenderHighlight(jsonObject);
        JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(jsonObjectFromJsonObject, "color");
        JSONArray jsonArrayFromJsonObject2 = JsonUtils.getJsonArrayFromJsonObject(jsonObjectFromJsonObject2, "color");
        ArrayList<Pair<Integer, Integer>> arrayList = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> arrayList2 = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> arrayList3 = new ArrayList<>();
        fillList(arrayList, senderHighlight);
        fillList(arrayList2, jsonArrayFromJsonObject);
        fillList(arrayList3, jsonArrayFromJsonObject2);
        return new SearchHighlight(arrayList, arrayList2, arrayList3);
    }

    @Override // ru.mail.data.cmd.server.parser.JsonMessageParser, ru.mail.data.cmd.server.parser.SingleMessageParser
    @NotNull
    public MailMessage parse(@NotNull JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        MailMessage mailMessage = super.parse(jsonObject);
        mailMessage.setSearchHighlight(parseSearchHighlight(jsonObject));
        return mailMessage;
    }
}
