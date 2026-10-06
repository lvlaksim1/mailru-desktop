package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.PinnedLastSender;
import ru.mail.data.entities.PinnedMailsVirtualThread;
import ru.mail.util.log.Log;
import ru.mail.utils.StringEscapeUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lru/mail/data/cmd/server/parser/PinnedMailsParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/data/entities/PinnedMailsVirtualThread;", "account", "", "<init>", "(Ljava/lang/String;)V", "parse", "body", "Lorg/json/JSONObject;", "parseLastSenders", "", "Lru/mail/data/entities/PinnedLastSender;", "pinnedData", "Companion", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPinnedMailsParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PinnedMailsParser.kt\nru/mail/data/cmd/server/parser/PinnedMailsParser\n+ 2 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n*L\n1#1,55:1\n76#2,4:56\n*S KotlinDebug\n*F\n+ 1 PinnedMailsParser.kt\nru/mail/data/cmd/server/parser/PinnedMailsParser\n*L\n34#1:56,4\n*E\n"})
public final class PinnedMailsParser extends JSONParser<PinnedMailsVirtualThread> {

    @NotNull
    private static final String EMAIL = "email";

    @NotNull
    private static final String GLOBAL_PINNED = "global_pinned";

    @NotNull
    private static final String LAST_SENDERS = "last_senders";

    @NotNull
    private static final String MESSAGES_COUNT = "messages_count";

    @NotNull
    private static final String NAME = "name";

    @NotNull
    private final String account;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PinnedMailsParser");

    public PinnedMailsParser(@NotNull String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        this.account = account;
    }

    private final List<PinnedLastSender> parseLastSenders(JSONObject pinnedData) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = pinnedData.optJSONArray("last_senders");
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i10);
                Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                String strOptString = jSONObject.optString("name", "");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                String strOptString2 = jSONObject.optString("email", "");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                PinnedLastSender pinnedLastSender = new PinnedLastSender(strOptString, strOptString2);
                if (pinnedLastSender.getName().length() == 0 || pinnedLastSender.getEmail().length() == 0) {
                    LOG.d("Sender with empty name or email: " + pinnedLastSender);
                }
                arrayList.add(pinnedLastSender);
            }
        }
        return arrayList;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @Nullable
    public PinnedMailsVirtualThread parse(@Nullable JSONObject body) throws JSONException {
        if (body == null) {
            LOG.d("No data for last senders");
            return null;
        }
        JSONObject jSONObjectOptJSONObject = body.optJSONObject(GLOBAL_PINNED);
        if (jSONObjectOptJSONObject == null) {
            return null;
        }
        int iOptInt = jSONObjectOptJSONObject.optInt("messages_count", 0);
        List<PinnedLastSender> lastSenders = parseLastSenders(jSONObjectOptJSONObject);
        String str = this.account;
        String strReplaceSpecialSymbols = StringEscapeUtils.replaceSpecialSymbols(PinnedMailsVirtualThread.INSTANCE.serializeLastSenders(lastSenders));
        Intrinsics.checkNotNullExpressionValue(strReplaceSpecialSymbols, "replaceSpecialSymbols(...)");
        return new PinnedMailsVirtualThread(0, strReplaceSpecialSymbols, iOptInt, str, 1, null);
    }
}
