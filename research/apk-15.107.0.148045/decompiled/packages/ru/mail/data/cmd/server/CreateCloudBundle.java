package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.serverapi.WithSampling;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "cloud", "attachment", "create"})
@WithSampling
public class CreateCloudBundle extends PostServerRequest<ServerCommandEmailParams, Result> {
    private static final Log LOG = Log.getLog("CreateCloudBundle");

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mBundleId;
        private final String mLoaderUrl;

        public Result(String str, String str2) {
            this.mLoaderUrl = str;
            this.mBundleId = str2;
        }

        public String getBundleId() {
            return this.mBundleId;
        }

        public String getLoaderUrl() {
            return this.mLoaderUrl;
        }
    }

    public CreateCloudBundle(Context context, MailboxContext mailboxContext, boolean z10) {
        super(context, new ServerCommandEmailParams(MailboxContextUtil.getAccountInfo(mailboxContext, CommonDataManager.from(context)), MailboxContextUtil.getFolderState(mailboxContext)), z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<ServerCommandEmailParams, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            return new Result(jSONObject.getString("loader_url"), jSONObject.getString("bundle_id"));
        } catch (JSONException e10) {
            LOG.e("Unable to parse ", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
