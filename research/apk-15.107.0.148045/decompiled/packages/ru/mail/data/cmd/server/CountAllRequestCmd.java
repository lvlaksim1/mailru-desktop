package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "count", "all"})
public class CountAllRequestCmd extends PostServerRequest<Params, Integer> {
    private static final Log LOG = Log.getLog("CountAllRequestCmd");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "folder")
        private final long mFolderId;

        @Param(getterName = "getFromJsonArray", method = HttpMethod.POST, name = "from", useGetter = true)
        private final List<String> mFrom;

        @Param(getterName = "isNewslettersOnly", method = HttpMethod.POST, name = "only_newsletters", useGetter = true)
        private final boolean mIsNewslettersOnly;

        public Params(long j10, @NotNull List<String> list, boolean z10, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            this.mFolderId = j10;
            this.mFrom = list;
            this.mIsNewslettersOnly = z10;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Params) || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            return this.mFolderId == params.mFolderId && this.mIsNewslettersOnly == params.mIsNewslettersOnly && Objects.equals(this.mFrom, params.mFrom);
        }

        public String getFromJsonArray() {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = this.mFrom.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            return jSONArray.toString();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), Long.valueOf(this.mFolderId), this.mFrom, Boolean.valueOf(this.mIsNewslettersOnly));
        }

        @Nullable
        public Boolean isNewslettersOnly() {
            if (this.mIsNewslettersOnly) {
                return Boolean.TRUE;
            }
            return null;
        }
    }

    public CountAllRequestCmd(Context context, Params params) {
        super(context, params, MigrateToPostUtils.is12155Enabled(context));
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Integer>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Integer onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            return Integer.valueOf(new JSONObject(response.getRespString()).getJSONObject("body").getInt("count"));
        } catch (JSONException e10) {
            LOG.e("Unable to parse count request", e10);
            return 0;
        }
    }
}
