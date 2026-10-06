package ru.mail.data.cmd.server;

import android.content.Context;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.news_feed.util.pulsedeeplinks.ActionParser;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.ui.fragments.mailbox.FilterParameters;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "filters", ProductAction.ACTION_ADD})
public class AddFilterCommand extends PostServerRequest<Params, Result> {
    public static final String EXISTS = "exists";
    protected static final Log LOG = Log.getLog("AddFilterCommand");
    public static final String OVER_LIMIT = "over_limit";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "apply_folders", useGetter = true)
        private String applyFolders;

        @Param(method = HttpMethod.POST, useGetter = true)
        private String filters;
        private final String mFilterId;
        private final FilterParameters parameters;

        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState, @Nullable FilterParameters filterParameters) {
            this(accountInfo, folderState, filterParameters, null);
        }

        private JSONObject buildActions() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("remove", false);
                jSONObject.put("move", this.parameters.getMoveFolderId());
                jSONObject.put("read", this.parameters.isMarkAsRead());
                jSONObject.put("flag", false);
                jSONObject.put("reject", false);
                return jSONObject;
            } catch (JSONException e10) {
                AddFilterCommand.LOG.e("Cannot build actions json object", e10);
                return jSONObject;
            }
        }

        private JSONArray buildApplyFolders() {
            JSONArray jSONArray = new JSONArray();
            Iterator<Long> it = this.parameters.getApplyToFolders().iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            return jSONArray;
        }

        private JSONArray buildConditions() {
            JSONArray jSONArray = new JSONArray();
            try {
                for (String str : this.parameters.getFroms()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("name", "from");
                    jSONObject.put("not", false);
                    jSONObject.put("value", str);
                    jSONArray.put(jSONObject);
                }
            } catch (JSONException e10) {
                AddFilterCommand.LOG.e("Cannot build conditions json object", e10);
            }
            return jSONArray;
        }

        protected JSONObject buildFilter() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("enabled", true);
                jSONObject.put("applyToSpam", false);
                jSONObject.put("conditionsOr", true);
                jSONObject.put("conditions", buildConditions());
                jSONObject.put(ActionParser.KEY_MULTIPLE_ACTIONS, buildActions());
                return jSONObject;
            } catch (JSONException e10) {
                AddFilterCommand.LOG.e("Cannot build filter json object", e10);
                return jSONObject;
            }
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            String str = this.mFilterId;
            if (str == null ? params.mFilterId != null : !str.equals(params.mFilterId)) {
                return false;
            }
            FilterParameters filterParameters = this.parameters;
            FilterParameters filterParameters2 = params.parameters;
            if (filterParameters != null) {
                return filterParameters.equals(filterParameters2);
            }
            return filterParameters2 == null;
        }

        public String getApplyFolders() {
            if (this.parameters.getApplyToFolders() == null || this.parameters.getApplyToFolders().size() <= 0) {
                return null;
            }
            return buildApplyFolders().toString();
        }

        public String getFilterId() {
            return this.mFilterId;
        }

        public String getFilters() {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(buildFilter());
            return jSONArray.toString();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            FilterParameters filterParameters = this.parameters;
            int iHashCode2 = (iHashCode + (filterParameters != null ? filterParameters.hashCode() : 0)) * 31;
            String str = this.mFilterId;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState, @Nullable FilterParameters filterParameters, @Nullable String str) {
            super(accountInfo, folderState);
            this.parameters = filterParameters;
            this.mFilterId = str;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mAddedFilterId;

        public Result(String str) {
            this.mAddedFilterId = str;
        }

        public String getAddedFilterId() {
            return this.mAddedFilterId;
        }
    }

    public AddFilterCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CommandStatus<?> processErrorResponse(NetworkCommand.Response response) {
        try {
            if (new JSONObject(response.getRespString()).getString("body").equals(OVER_LIMIT)) {
                return new CommandStatus.ERROR(OVER_LIMIT);
            }
        } catch (JSONException e10) {
            LOG.d("Error while parsing error body", e10);
        }
        return new CommandStatus.ERROR();
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, Result>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.AddFilterCommand.1
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
                try {
                    if (jSONObject.getJSONObject("filters[0]").getString("error").equals(AddFilterCommand.EXISTS)) {
                        return new CommandStatus.ERROR(AddFilterCommand.EXISTS);
                    }
                } catch (JSONException e10) {
                    AddFilterCommand.LOG.d("Error while parsing error body", e10);
                }
                return new CommandStatus.ERROR();
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.AddFilterCommand.2
            @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                if (getResponse().getStatusCode() == 200) {
                    getResponse().createStringFromData();
                    if (Integer.parseInt(getDelegate().getResponseStatus(getResponse().getRespString())) == 507) {
                        return AddFilterCommand.this.processErrorResponse(getResponse());
                    }
                }
                return super.process();
            }
        };
    }

    public AddFilterCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            String string = new JSONObject(response.getRespString()).getJSONArray("body").getString(0);
            LOG.d("Filter has been created with id = '" + string + "'");
            return new Result(string);
        } catch (JSONException e10) {
            e10.printStackTrace();
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
