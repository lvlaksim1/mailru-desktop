package ru.mail.util.push.parser;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.util.push.OrderPush;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007¨\u0006\n"}, d2 = {"Lru/mail/util/push/parser/OrderPushParser;", "", "<init>", "()V", "parse", "Lru/mail/util/push/OrderPush;", "data", "", "", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOrderPushParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OrderPushParser.kt\nru/mail/util/push/parser/OrderPushParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,48:1\n1#2:49\n*E\n"})
public final class OrderPushParser {
    public static final int $stable = 0;

    @NotNull
    private static final String DATA_KEY_ACCOUNT = "login";

    @NotNull
    private static final String DATA_KEY_FOLDER_ID = "folder_id";

    @NotNull
    private static final String DATA_KEY_MESSAGE_ID = "message_id";

    @NotNull
    private static final String DATA_KEY_META = "transaction_metadata";

    @NotNull
    private static final String DATA_KEY_THREAD_ID = "thread_id";

    @NotNull
    private static final String JSON_KEY_EXTENDED_STATUS = "extended_status";

    @NotNull
    private static final String JSON_KEY_STATUS = "status";

    /* JADX WARN: Code duplicated, block: B:14:0x0058  */
    /* JADX WARN: Code duplicated, block: B:23:0x0071  */
    @NotNull
    public final OrderPush parse(@NotNull Map<String, String> data) throws JSONException {
        String string;
        String string2;
        Intrinsics.checkNotNullParameter(data, "data");
        String str = data.get("login");
        String str2 = data.get("message_id");
        String str3 = data.get("thread_id");
        String str4 = data.get("folder_id");
        Integer numValueOf = str4 != null ? Integer.valueOf(Integer.parseInt(str4)) : null;
        String str5 = data.get("transaction_metadata");
        if (str5 != null) {
            JSONObject jSONObject = new JSONObject(str5);
            if (!jSONObject.has("status")) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                string = jSONObject.getString("status");
            } else {
                string = null;
            }
        } else {
            string = null;
        }
        if (str5 != null) {
            JSONObject jSONObject2 = new JSONObject(str5);
            JSONObject jSONObject3 = jSONObject2.has(JSON_KEY_EXTENDED_STATUS) ? jSONObject2 : null;
            if (jSONObject3 == null || (string2 = jSONObject3.getString(JSON_KEY_EXTENDED_STATUS)) == null) {
                string2 = "";
            }
        } else {
            string2 = "";
        }
        String str6 = string2;
        Intrinsics.checkNotNull(str);
        Intrinsics.checkNotNull(str2);
        Intrinsics.checkNotNull(str3);
        Intrinsics.checkNotNull(numValueOf);
        int iIntValue = numValueOf.intValue();
        Intrinsics.checkNotNull(string);
        return new OrderPush(str, str2, str3, iIntValue, string, str6);
    }
}
