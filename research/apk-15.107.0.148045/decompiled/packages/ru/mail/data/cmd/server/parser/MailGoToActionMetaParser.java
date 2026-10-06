package ru.mail.data.cmd.server.parser;

import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.MailGoToActionMeta;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/server/parser/MailGoToActionMetaParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/logic/content/MailGoToActionMeta;", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "CONTEXT", "", CredentialProviderBaseController.TYPE_TAG, "DESCRIPTION", "POTENTIAL_ACTION", "ACTION_NAME", "ACTION_URL", "parse", "jsonObject", "Lorg/json/JSONObject;", "parseInternal", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MailGoToActionMetaParser extends JSONParser<MailGoToActionMeta> {

    @NotNull
    private static final String ACTION_NAME = "name";

    @NotNull
    private static final String ACTION_URL = "url";

    @NotNull
    private static final String CONTEXT = "@context";

    @NotNull
    private static final String DESCRIPTION = "description";

    @NotNull
    public static final MailGoToActionMetaParser INSTANCE = new MailGoToActionMetaParser();

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("MailGoToActionMetaParser");

    @NotNull
    private static final String POTENTIAL_ACTION = "potentialAction";

    @NotNull
    private static final String TYPE = "@type";

    private MailGoToActionMetaParser() {
    }

    private final MailGoToActionMeta parseInternal(JSONObject jsonObject) throws JSONException {
        String string = jsonObject.getString(CONTEXT);
        if (!Intrinsics.areEqual(string, MailGoToActionMeta.CONTEXT)) {
            throw new JSONException("Unknown meta context: " + string);
        }
        String string2 = jsonObject.getString(TYPE);
        if (!Intrinsics.areEqual(string2, MailGoToActionMeta.TYPE)) {
            throw new JSONException("Unknown meta type: " + string2);
        }
        JSONObject jSONObject = jsonObject.getJSONObject(POTENTIAL_ACTION);
        String string3 = jSONObject.getString(TYPE);
        if (!Intrinsics.areEqual(string3, MailGoToActionMeta.PotentialAction.TYPE)) {
            throw new JSONException("Unknown meta type: " + string3);
        }
        String string4 = jSONObject.getString("name");
        Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
        String string5 = jSONObject.getString("url");
        Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
        MailGoToActionMeta.PotentialAction potentialAction = new MailGoToActionMeta.PotentialAction(string4, string5);
        String strOptString = jsonObject.optString("description");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        return new MailGoToActionMeta(strOptString, potentialAction);
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @Nullable
    public MailGoToActionMeta parse(@Nullable JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        try {
            return parseInternal(jsonObject);
        } catch (JSONException e10) {
            LOG.w("Parsing MetaGoToAction failed: " + e10.getMessage() + " json: " + jsonObject);
            return null;
        }
    }
}
