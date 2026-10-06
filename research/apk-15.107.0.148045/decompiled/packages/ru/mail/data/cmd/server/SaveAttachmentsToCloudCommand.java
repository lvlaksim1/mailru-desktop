package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.Attach;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "attaches", ProductAction.ACTION_ADD, "to-cloud"})
public class SaveAttachmentsToCloudCommand extends PostServerRequest<Params, Result> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getFileIds", method = HttpMethod.POST, name = "ids", type = Param.Type.STRING, useGetter = true)
        private final Collection<Attach> mAttaches;

        @Param(getterName = "getFolder", method = HttpMethod.POST, name = "folder", type = Param.Type.STRING, useGetter = true)
        private final String mFolderName;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable Collection<Attach> collection, @Nullable String str) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mAttaches = collection;
            this.mFolderName = str;
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
            Collection<Attach> collection = this.mAttaches;
            if (collection == null ? params.mAttaches != null : !collection.equals(params.mAttaches)) {
                return false;
            }
            String str = this.mFolderName;
            String str2 = params.mFolderName;
            return str == null ? str2 == null : str.equals(str2);
        }

        public String getFileIds() {
            JSONArray jSONArray = new JSONArray();
            Iterator<Attach> it = this.mAttaches.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().getPartId());
            }
            return jSONArray.toString();
        }

        public String getFolder() {
            return "/" + this.mFolderName + "/";
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mFolderName;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            Collection<Attach> collection = this.mAttaches;
            return iHashCode2 + (collection != null ? collection.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mFolderName;
        private final List<String> mSavedFiles;

        public Result(List<String> list, String str) {
            this.mSavedFiles = list;
            this.mFolderName = str;
        }

        public String getFolderName() {
            return this.mFolderName;
        }

        public List<String> getSavedFiles() {
            return this.mSavedFiles;
        }
    }

    public SaveAttachmentsToCloudCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.SaveAttachmentsToCloudCommand.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor
            protected CommandStatus<?> processResponse(int i10) {
                if (i10 == 507) {
                    try {
                        if (new JSONObject(getResponse().getRespString()).getString("body").equals("quota_exceeded")) {
                            return new MailCommandStatus.ERROR_CLOUD_IS_FULL();
                        }
                    } catch (JSONException e10) {
                        e10.printStackTrace();
                        return new CommandStatus.ERROR();
                    }
                } else if (i10 == 400) {
                    try {
                        JSONObject jSONObject = new JSONObject(getResponse().getRespString()).getJSONObject("body");
                        if (jSONObject.has("ids[0]")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("ids[0]");
                            if (jSONObject2.has("error")) {
                                String string = jSONObject2.getString("error");
                                if ("invalid".equals(string)) {
                                    return new NetworkCommandStatus.BAD_REQUEST("invalid ids");
                                }
                                if ("not_exists".equals(string)) {
                                    return new NetworkCommandStatus.BAD_REQUEST("ids not exists");
                                }
                            }
                        }
                    } catch (JSONException e11) {
                        e11.printStackTrace();
                        return new CommandStatus.ERROR();
                    }
                }
                return super.processResponse(i10);
            }
        };
    }

    SaveAttachmentsToCloudCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONArray jSONArray = new JSONObject(response.getRespString()).getJSONArray("body");
            ArrayList arrayList = new ArrayList();
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                arrayList.add(jSONArray.getString(i10));
            }
            return new Result(arrayList, ((Params) getParams()).mFolderName);
        } catch (JSONException e10) {
            e10.printStackTrace();
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
