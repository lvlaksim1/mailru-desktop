package ru.mail.data.cmd.server;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.search.SearchSuggestion;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "search", "suggest"})
public class GetSuggestionsCommand extends ServerCommandBase<Params, GetSuggestionsCommandResult> {
    private static final String JSON_BODY_KEY = "body";
    private static final String JSON_SUGGESTS_KEY = "suggests";
    private static final String UTF_8 = "UTF-8";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getQuery", method = HttpMethod.GET, name = "query", useGetter = true)
        private final String mSubWord;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull String str) {
            String strEncode;
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            try {
                strEncode = URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e10) {
                e10.printStackTrace();
                strEncode = "";
            }
            this.mSubWord = strEncode;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            String str = this.mSubWord;
            String str2 = ((Params) obj).mSubWord;
            return str == null ? str2 == null : str.equals(str2);
        }

        public String getQuery() {
            return this.mSubWord;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mSubWord;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }
    }

    public GetSuggestionsCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, GetSuggestionsCommandResult>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase.TornadoDelegate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public GetSuggestionsCommandResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(response.getRespString()).getJSONObject("body").getJSONArray(JSON_SUGGESTS_KEY);
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                arrayList.add(new SearchSuggestion(jSONArray.getString(i10)));
            }
            return new GetSuggestionsCommandResult(arrayList);
        } catch (JSONException e10) {
            e10.printStackTrace();
            throw new NetworkCommand.PostExecuteException();
        }
    }
}
