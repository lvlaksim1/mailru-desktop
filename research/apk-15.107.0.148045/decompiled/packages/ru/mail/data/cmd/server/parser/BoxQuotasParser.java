package ru.mail.data.cmd.server.parser;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.mailboxquotas.BoxQuotas;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tJ\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lru/mail/data/cmd/server/parser/BoxQuotasParser;", "", "<init>", "()V", "log", "Lru/mail/util/log/Log;", "parseBoxLimits", "Lru/mail/logic/mailboxquotas/BoxQuotas;", "body", "Lorg/json/JSONObject;", "parseFilledBoxSize", "", "(Lorg/json/JSONObject;)Ljava/lang/Integer;", "Companion", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class BoxQuotasParser {

    @NotNull
    public static final String JSON_BOX_ATTACH = "attach";

    @NotNull
    public static final String JSON_BOX_GENERAL_SIZE = "box_general";

    @NotNull
    public static final String JSON_BOX_LINK_ATTACH = "link_attach";

    @NotNull
    public static final String JSON_BOX_QUOTAS = "mbox_quotas";

    @NotNull
    public static final String JSON_BOX_SEND = "box_send";

    @NotNull
    public static final String JSON_FILLED_BOX_SIZE = "mbox_size";

    @NotNull
    private final Log log = Log.INSTANCE.getLog("BoxQuotasParser");

    @Nullable
    public final BoxQuotas parseBoxLimits(@NotNull JSONObject body) throws JSONException {
        Intrinsics.checkNotNullParameter(body, "body");
        if (!body.has(JSON_BOX_QUOTAS)) {
            return null;
        }
        JSONObject jSONObject = body.getJSONObject(JSON_BOX_QUOTAS);
        this.log.d(String.valueOf(jSONObject));
        return new BoxQuotas(jSONObject.optInt(JSON_BOX_GENERAL_SIZE, -1), jSONObject.optInt(JSON_BOX_SEND, -1), jSONObject.optInt("attach", -1), jSONObject.optInt(JSON_BOX_LINK_ATTACH, -1));
    }

    @Nullable
    public final Integer parseFilledBoxSize(@NotNull JSONObject body) {
        Intrinsics.checkNotNullParameter(body, "body");
        if (body.has(JSON_FILLED_BOX_SIZE)) {
            return Integer.valueOf(body.optInt(JSON_FILLED_BOX_SIZE, -1));
        }
        return null;
    }
}
