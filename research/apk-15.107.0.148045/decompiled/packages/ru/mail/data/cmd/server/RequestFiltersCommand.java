package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.Filter;
import ru.mail.data.entities.FilterCondition;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UncodedHtmlHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.news_feed.util.pulsedeeplinks.ActionParser;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "filters"})
public class RequestFiltersCommand extends ServerCommandBase<ServerCommandEmailParams, List<Filter>> {
    private final List<Filter> filters;
    private final UserFiltersStorage userFiltersStorage;

    public RequestFiltersCommand(Context context, ServerCommandEmailParams serverCommandEmailParams, boolean z10, UserFiltersStorage userFiltersStorage) {
        this(context, serverCommandEmailParams, null, z10, userFiltersStorage);
    }

    private ArrayList<FilterCondition> getFilterConditions(JSONArray jSONArray, Filter filter) throws JSONException {
        ArrayList<FilterCondition> arrayList = new ArrayList<>();
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i10);
            arrayList.add(new FilterCondition(jSONObject.getString("name"), jSONObject.getString("value"), filter));
        }
        return arrayList;
    }

    private List<Filter> getFilters() {
        return this.filters;
    }

    private static boolean isMoveFilter(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        return jSONObject.getString("name").equals("from") && jSONObject2.optInt("move", -1) != -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void saveHasFilters(boolean z10) {
        this.userFiltersStorage.saveHasFilters(((ServerCommandEmailParams) getParams()).getLogin(), z10);
    }

    static boolean simple(JSONArray jSONArray, JSONObject jSONObject) throws JSONException {
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            if (!isMoveFilter(jSONArray.getJSONObject(i10), jSONObject)) {
                return false;
            }
        }
        return true;
    }

    static boolean single(JSONArray jSONArray, JSONObject jSONObject) throws JSONException {
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            if (jSONArray.getJSONObject(i10).getBoolean("not")) {
                return false;
            }
        }
        return !jSONObject.getBoolean("remove") && jSONObject.getJSONArray("forward").length() <= 0 && !jSONObject.getBoolean("flag") && jSONObject.getString(TornadoSendRequest.FIELD_REPLY).equals("null") && !jSONObject.getBoolean("reject") && jSONObject.getJSONArray("notify").length() <= 0;
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new UncodedHtmlHostProvider(super.getHostProvider());
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    RequestFiltersCommand(Context context, ServerCommandEmailParams serverCommandEmailParams, HostProvider hostProvider, boolean z10, UserFiltersStorage userFiltersStorage) {
        super(context, serverCommandEmailParams, hostProvider, z10);
        this.filters = new ArrayList();
        this.userFiltersStorage = userFiltersStorage;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    public List<Filter> onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONArray jSONArray = new JSONObject(response.getRespString()).getJSONArray("body");
            boolean z10 = false;
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i10);
                JSONArray jSONArray2 = jSONObject.getJSONArray("conditions");
                JSONObject jSONObject2 = jSONObject.getJSONObject(ActionParser.KEY_MULTIPLE_ACTIONS);
                String string = jSONObject.getString("id");
                boolean z11 = jSONObject.getBoolean("enabled");
                z10 |= z11;
                if (simple(jSONArray2, jSONObject2) && single(jSONArray2, jSONObject2) && !jSONObject.getBoolean("applyToSpam") && z11) {
                    Filter filter = new Filter(((ServerCommandEmailParams) getParams()).getLogin(), string, Long.valueOf(jSONObject2.getLong("move")), null, jSONObject2.getBoolean("read"), true);
                    filter.setConditions(getFilterConditions(jSONArray2, filter));
                    filter.setOrderIndex(i10);
                    this.filters.add(filter);
                }
            }
            saveHasFilters(z10);
            return Collections.unmodifiableList(getFilters());
        } catch (JSONException e10) {
            e10.printStackTrace();
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
