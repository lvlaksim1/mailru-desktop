package ru.mail.data.cmd.server;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.ui.fragments.mailbox.FilterParameters;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "filters", "edit"})
public class UpdateFilterCommand extends AddFilterCommand {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends AddFilterCommand.Params {
        public Params(FilterParameters filterParameters, AccountInfo accountInfo, FolderState folderState) {
            super(accountInfo, folderState, filterParameters);
        }

        @Override // ru.mail.data.cmd.server.AddFilterCommand.Params
        protected JSONObject buildFilter() {
            JSONObject jSONObjectBuildFilter = super.buildFilter();
            try {
                jSONObjectBuildFilter.put("id", getFilterId());
                return jSONObjectBuildFilter;
            } catch (JSONException e10) {
                AddFilterCommand.LOG.e("Cannot put filter id '" + getFilterId() + "' in json object", e10);
                return jSONObjectBuildFilter;
            }
        }

        public Params(FilterParameters filterParameters, String str, AccountInfo accountInfo, FolderState folderState) {
            super(accountInfo, folderState, filterParameters, str);
        }
    }

    public UpdateFilterCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.data.cmd.server.AddFilterCommand, ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<AddFilterCommand.Params, AddFilterCommand.Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.UpdateFilterCommand.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                if (getResponse().getStatusCode() == 200) {
                    getResponse().createStringFromData();
                    if (Integer.parseInt(getDelegate().getResponseStatus(getResponse().getRespString())) == 400) {
                        return new CommandStatus.ERROR();
                    }
                }
                return super.process();
            }
        };
    }

    UpdateFilterCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }
}
