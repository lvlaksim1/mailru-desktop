package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.ui.fragments.mailbox.PeopleSearchSuggestion;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "ab", "fast"})
public class SearchPeopleCommand extends ServerCommandBase<ServerCommandEmailParams, Result> {
    private static final String JSON_BODY_KEY = "body";

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final List<PeopleSearchSuggestion> mSuggestions;

        public Result(List<PeopleSearchSuggestion> list) {
            this.mSuggestions = Collections.unmodifiableList(list);
        }

        public List<PeopleSearchSuggestion> getPeopleSearchSuggestions() {
            return this.mSuggestions;
        }
    }

    public SearchPeopleCommand(Context context, ServerCommandEmailParams serverCommandEmailParams, boolean z10) {
        super(context, serverCommandEmailParams, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<ServerCommandEmailParams, Result>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase.TornadoDelegate();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public Result onPostExecuteRequest(NetworkCommand.Response response) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(response.getRespString()).getJSONArray("body");
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                PeopleSearchSuggestion peopleSearchSuggestion = new PeopleSearchSuggestion();
                JSONArray jSONArray2 = jSONArray.getJSONArray(i10);
                String string = jSONArray2.getString(0);
                String string2 = jSONArray2.getJSONArray(1).getString(0);
                peopleSearchSuggestion.setName(string);
                peopleSearchSuggestion.setEmail(string2);
                if (peopleSearchSuggestion.getName().equals("null")) {
                    peopleSearchSuggestion.setName("");
                }
                arrayList.add(peopleSearchSuggestion);
            }
        } catch (JSONException e10) {
            e10.printStackTrace();
        }
        return new Result(arrayList);
    }
}
