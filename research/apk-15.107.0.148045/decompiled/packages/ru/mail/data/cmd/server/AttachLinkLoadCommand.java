package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.entities.AttachLink;
import ru.mail.logic.content.AttachmentHelper;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.GetServerRequest;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@HostProviderAnnotation(defHostStrRes = "string/files_default_host", defSchemeStrRes = "string/files_default_scheme", prefKey = AttachLinkLoadCommand.PREF_ATTACHLINK)
@UrlPath(pathSegments = {"{group}"})
public class AttachLinkLoadCommand extends GetServerRequest<Params, Result> {
    public static final String DOWNLOAD_LINK = "dlink";
    public static final String FILE_COUNT = "file_count";
    public static final String FILE_ID = "fileid";
    public static final String FILE_LIST = "file_list";
    public static final String FILE_NAME = "filename";
    public static final String FILE_SIZE = "filesize";
    public static final String GROUP = "group";
    public static final String IS_PREVIEWABLE = "is_previewable";
    public static final String JSON_FORMAT = "json";
    public static final String MD5 = "md5";
    public static final String PREF_ATTACHLINK = "attachlink";
    public static final String STATIC = "static";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {

        @Param(method = HttpMethod.GET, name = "json")
        public static final String TRUE = "1";
        private final String mFrom;

        @Param(method = HttpMethod.URL, name = "group")
        private final String mGroupName;
        private final String mMessageId;

        public Params(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable FolderState folderState) {
            super(new AccountInfo(str4), folderState);
            this.mGroupName = str;
            this.mFrom = str2;
            this.mMessageId = str3;
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
            String str = this.mGroupName;
            if (str == null ? params.mGroupName != null : !str.equals(params.mGroupName)) {
                return false;
            }
            if (this.mFrom.equals(params.mFrom)) {
                return this.mMessageId.equals(params.mMessageId);
            }
            return false;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mGroupName;
            return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + this.mFrom.hashCode()) * 31) + this.mMessageId.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private Collection<AttachLink> mAttachLinks;

        public Result(Collection<AttachLink> collection) {
            this.mAttachLinks = Collections.unmodifiableCollection(collection);
        }

        public Collection<AttachLink> getAttachLinks() {
            return this.mAttachLinks;
        }
    }

    public AttachLinkLoadCommand(Context context, Params params) {
        this(context, params, null);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.LEGACY;
    }

    @Override // ru.mail.network.NetworkCommand
    @NonNull
    protected String getPathTag() {
        return "group";
    }

    AttachLinkLoadCommand(Context context, Params params, HostProvider hostProvider) {
        super(context, params, hostProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, Result>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, Result>.LegacyDelegate() { // from class: ru.mail.data.cmd.server.AttachLinkLoadCommand.1
            @Override // ru.mail.serverapi.ServerCommandBase.LegacyDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            protected String getResponseStatusImpl(String str) {
                return "OK";
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            ArrayList arrayList = new ArrayList();
            if (jSONObject.getInt(FILE_COUNT) > 0) {
                JSONArray jSONArray = jSONObject.getJSONArray(FILE_LIST);
                for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i10);
                    AttachLink attachLink = new AttachLink(Long.valueOf(jSONObject2.getInt(FILE_ID)), jSONObject2.getString(FILE_NAME), Long.valueOf(jSONObject2.getLong(FILE_SIZE)), jSONObject2.getInt(IS_PREVIEWABLE) == 1 ? jSONObject2.getString(STATIC) : null, jSONObject2.getString(DOWNLOAD_LINK));
                    attachLink.setPrefetchPath(AttachmentHelper.getAttachPrefetchLocalPath(getContext(), ((Params) getParams()).getLogin(), ((Params) getParams()).mMessageId, ((Params) getParams()).mFrom, attachLink));
                    arrayList.add(attachLink);
                }
            }
            return new Result(arrayList);
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
    }
}
