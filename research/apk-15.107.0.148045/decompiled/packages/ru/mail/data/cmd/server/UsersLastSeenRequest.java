package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.ui.addressbook.model.LastSeenClient;
import ru.mail.ui.addressbook.model.LastSeenInfo;
import ru.mail.ui.addressbook.model.UsersLastSeenInfo;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;
import ru.ok.android.api.methods.authV2.anonymLogin.AnonymLoginApiRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0019\u001aB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0014J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u001b"}, d2 = {"Lru/mail/data/cmd/server/UsersLastSeenRequest;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/UsersLastSeenRequest$Params;", "Lru/mail/ui/addressbook/model/UsersLastSeenInfo;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/UsersLastSeenRequest$Params;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "parseLastSeenJson", "Lru/mail/ui/addressbook/model/LastSeenInfo;", "email", "", "jsonBody", "Lorg/json/JSONObject;", RbParams.Default.URL_PARAM_KEY_CURRENT_TIME, "", "createNewEmptyLastSeenInfo", "Params", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "ab", "lastseen"})
public final class UsersLastSeenRequest extends ServerCommandBase<Params, UsersLastSeenInfo> {

    @NotNull
    private static final String PARAM_KEY_EMAILS = "emails";
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("UsersLastSeenRequest");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Keep
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\u00048\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/data/cmd/server/UsersLastSeenRequest$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "emailList", "", "", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", "dataManager", "Lru/mail/logic/content/DataManager;", "<init>", "(Ljava/util/List;Lru/mail/logic/content/MailboxContext;Lru/mail/logic/content/DataManager;)V", "emails", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @Param(method = HttpMethod.GET, name = "emails")
        @NotNull
        private final String emails;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull List<String> emailList, @NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            Intrinsics.checkNotNullParameter(emailList, "emailList");
            Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
            Intrinsics.checkNotNullParameter(dataManager, "dataManager");
            String string = new JSONArray((Collection) emailList).toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            this.emails = string;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UsersLastSeenRequest(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    private final LastSeenInfo createNewEmptyLastSeenInfo(String email, long currentTime) {
        return new LastSeenInfo(email, null, null, null, currentTime, 14, null);
    }

    private final long currentTime() {
        return System.currentTimeMillis();
    }

    private final LastSeenInfo parseLastSeenJson(String email, JSONObject jsonBody, long currentTime) {
        if (jsonBody == null) {
            return createNewEmptyLastSeenInfo(email, currentTime);
        }
        String strOptString = jsonBody.optString("status");
        String strOptString2 = jsonBody.optString("status_id");
        String strOptString3 = jsonBody.optString(AnonymLoginApiRequest.PARAM_NAME_CLIENT_TYPE);
        LastSeenClient.Companion companion = LastSeenClient.INSTANCE;
        Intrinsics.checkNotNull(strOptString3);
        LastSeenClient lastSeenClientFrom = companion.from(strOptString3);
        Intrinsics.checkNotNull(strOptString);
        Intrinsics.checkNotNull(strOptString2);
        return new LastSeenInfo(email, strOptString, strOptString2, lastSeenClientFrom, currentTime);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public UsersLastSeenInfo onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        long jCurrentTime = currentTime();
        try {
            JSONArray jSONArray = new JSONObject(resp.getRespString()).getJSONArray("body");
            int length = jSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i10);
                String strOptString = jSONObject.optString("email");
                if (strOptString != null && strOptString.length() != 0) {
                    linkedHashMap.put(strOptString, parseLastSeenJson(strOptString, jSONObject.optJSONObject("last_seen"), jCurrentTime));
                }
            }
        } catch (JSONException e10) {
            LOG.e("Cannot parse users last seen info", e10);
        }
        return new UsersLastSeenInfo(linkedHashMap, null, 2, null);
    }
}
