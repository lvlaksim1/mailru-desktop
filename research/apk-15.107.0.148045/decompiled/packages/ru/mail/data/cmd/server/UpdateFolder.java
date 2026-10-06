package ru.mail.data.cmd.server;

import android.content.Context;
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
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "folders", "edit"})
public class UpdateFolder extends PostServerRequest<Params, Long> {
    private static final Log LOG = Log.getLog("UpdateFolder");
    public static final String PARENT_DEFUALT = "-1";
    public static final boolean WEB_DEFAULT = false;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {

        @Param(method = HttpMethod.POST)
        private String email;

        @Param(method = HttpMethod.POST, useGetter = true)
        private String folders;
        private final long mId;
        private final String mName;

        public Params(long j10, @Nullable String str, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            this.mId = j10;
            this.mName = str;
            this.email = accountInfo.getLogin();
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
            if (this.mId != params.mId) {
                return false;
            }
            String str = this.mName;
            String str2 = params.mName;
            return str == null ? str2 == null : str.equals(str2);
        }

        public String getFolders() {
            JSONArray jSONArray = new JSONArray();
            try {
                jSONArray.put(getJsonFolder());
            } catch (JSONException e10) {
                UpdateFolder.LOG.e(e10.getMessage(), e10);
            }
            return jSONArray.toString();
        }

        protected long getId() {
            return this.mId;
        }

        protected JSONObject getJsonFolder() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.mId);
            jSONObject.put("name", this.mName);
            return jSONObject;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            long j10 = this.mId;
            int i10 = (iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            String str = this.mName;
            return i10 + (str != null ? str.hashCode() : 0);
        }
    }

    public UpdateFolder(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Long>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.UpdateFolder.2
            @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                if (getResponse().getStatusCode() == 200) {
                    getResponse().createStringFromData();
                    if (Integer.parseInt(getDelegate().getResponseStatus(getResponse().getRespString())) == 400) {
                        return UpdateFolder.this.processErrorResponse(getResponse(), getDelegate());
                    }
                }
                return super.process();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    boolean isFolderIdInResponse(NetworkCommand.Response response) throws JSONException {
        JSONArray jSONArray = new JSONObject(response.getRespString()).getJSONArray("body");
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            if (Long.parseLong(jSONArray.getString(i10)) == ((Params) getParams()).mId) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected CommandStatus<?> processErrorResponse(NetworkCommand.Response response, NetworkCommand<Params, Long>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            if (jSONObject.getJSONObject("body").has("folders[0].id")) {
                String string = jSONObject.getJSONObject("body").getJSONObject("folders[0].id").getString("error");
                if ("not_open".equals(string)) {
                    return networkCommandBaseDelegate.onFolderAccessDenied();
                }
                if ("not_exists".equals(string)) {
                    return new MailCommandStatus.ERROR_FOLDER_NOT_EXIST(Long.valueOf(((Params) getParams()).mId));
                }
            }
        } catch (JSONException e10) {
            e10.printStackTrace();
        }
        return new CommandStatus.ERROR();
    }

    UpdateFolder(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, Long>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, Long>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.UpdateFolder.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.serverapi.ServerCommandBase.ServerCommandBaseDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onFolderAccessDenied() {
                ((Params) UpdateFolder.this.getParams()).getFolderState().clearFolderLogin(((Params) UpdateFolder.this.getParams()).mId);
                return new NetworkCommandStatus.FOLDER_ACCESS_DENIED(Long.valueOf(((Params) UpdateFolder.this.getParams()).mId));
            }

            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onResponseOk(NetworkCommand.Response response) {
                try {
                    return UpdateFolder.this.isFolderIdInResponse(response) ? new CommandStatus.OK(UpdateFolder.this.onPostExecuteRequest(response)) : new CommandStatus.ERROR("requested folder not found in response");
                } catch (JSONException e10) {
                    return new CommandStatus.ERROR(e10);
                } catch (NetworkCommand.PostExecuteException e11) {
                    return new CommandStatus.ERROR(e11);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    public Long onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return Long.valueOf(((Params) getParams()).mId);
    }
}
