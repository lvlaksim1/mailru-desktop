package ru.mail.data.cmd.server;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.cmd.server.parser.PlainAttachFactory;
import ru.mail.data.entities.Attach;
import ru.mail.data.entities.AttachCloud;
import ru.mail.data.entities.AttachCloudStock;
import ru.mail.data.entities.AttachLink;
import ru.mail.network.HostProvider;
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
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "attaches", "reattach"})
public class TornadoReattachRequest extends PostServerRequest<Params, Result> {
    private static final Log LOG = Log.getLog("TornadoReattachRequest");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "forwarded_id")
        private final String mForwardedId;

        @Param(method = HttpMethod.POST, name = "message_id")
        private final String mMessageId;

        public Params(@Nullable String str, @Nullable String str2, AccountInfo accountInfo, FolderState folderState) {
            super(accountInfo, folderState);
            this.mMessageId = str;
            this.mForwardedId = str2;
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
            String str = this.mForwardedId;
            if (str == null ? params.mForwardedId != null : !str.equals(params.mForwardedId)) {
                return false;
            }
            String str2 = this.mMessageId;
            String str3 = params.mMessageId;
            return str2 == null ? str3 == null : str2.equals(str3);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mMessageId;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mForwardedId;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ReattachedCloudStock {
        private final AttachCloudStock mAttach;
        private final String mOriginalId;

        public ReattachedCloudStock(String str, String str2, String str3) {
            this.mOriginalId = str;
            AttachCloudStock attachCloudStock = new AttachCloudStock();
            this.mAttach = attachCloudStock;
            attachCloudStock.setBundleId(str2);
            attachCloudStock.setFileId(str3);
        }

        public AttachCloudStock getAttach() {
            return this.mAttach;
        }

        public String getOriginalId() {
            return this.mOriginalId;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final List<AttachLink> mAttachLinks;
        private final String mErrorReason;
        private final List<AttachCloud> mSimpleCloudAttaches;
        private final List<Attach> mSuccessAttaches;
        private final List<ReattachedCloudStock> mSuccessCloudAttaches;

        public Result(List<Attach> list, List<ReattachedCloudStock> list2, List<AttachLink> list3, List<AttachCloud> list4, String str) {
            this.mSuccessAttaches = list;
            this.mSuccessCloudAttaches = list2;
            this.mAttachLinks = list3;
            this.mSimpleCloudAttaches = list4;
            this.mErrorReason = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Result result = (Result) obj;
                if (!this.mSuccessAttaches.equals(result.mSuccessAttaches)) {
                    return false;
                }
                String str = this.mErrorReason;
                String str2 = result.mErrorReason;
                if (str != null) {
                    return str.equals(str2);
                }
                if (str2 == null) {
                    return true;
                }
            }
            return false;
        }

        public List<AttachLink> getAttachLinks() {
            return this.mAttachLinks;
        }

        public List<Attach> getAttachments() {
            return (List) CollectionUtils.select(this.mSuccessAttaches, new Predicate<Attach>() { // from class: ru.mail.data.cmd.server.TornadoReattachRequest.Result.1
                @Override // org.apache.commons.collections4.Predicate
                public boolean evaluate(Attach attach) {
                    return attach.getDisposition() == Attach.Disposition.ATTACHMENT;
                }
            });
        }

        public List<ReattachedCloudStock> getCloudAttachments() {
            return this.mSuccessCloudAttaches;
        }

        public String getErrorReason() {
            return this.mErrorReason;
        }

        public List<Attach> getInlineAttachments() {
            return (List) CollectionUtils.select(this.mSuccessAttaches, new Predicate<Attach>() { // from class: ru.mail.data.cmd.server.TornadoReattachRequest.Result.2
                @Override // org.apache.commons.collections4.Predicate
                public boolean evaluate(Attach attach) {
                    return attach.getDisposition() == Attach.Disposition.INLINE;
                }
            });
        }

        public List<AttachCloud> getSimpleCloudAttaches() {
            return this.mSimpleCloudAttaches;
        }

        public boolean hasError() {
            return this.mErrorReason != null;
        }

        public int hashCode() {
            int iHashCode = this.mSuccessAttaches.hashCode() * 31;
            String str = this.mErrorReason;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }
    }

    public TornadoReattachRequest(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    private TornadoReattachRequest(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            LinkedList linkedList3 = new LinkedList();
            LinkedList linkedList4 = new LinkedList();
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("okay_files");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                for (int i10 = 0; i10 < jSONArrayOptJSONArray.length(); i10++) {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i10);
                    String string = jSONObject2.getString("type");
                    String strOptString = jSONObject2.optString(TornadoSendRequest.FIELD_ATTACHES_CONTENT_ID);
                    String string2 = jSONObject2.getString("origin_id");
                    if (TextUtils.equals(string, "cloud_stock")) {
                        String[] strArrSplit = jSONObject2.getJSONObject("attach").getString("id").split(":");
                        linkedList4.add(new ReattachedCloudStock(string2, strArrSplit[0], strArrSplit[1]));
                    } else if (!TextUtils.isEmpty(strOptString)) {
                        linkedList.add(new Attach(string2, "cid:" + strOptString));
                    } else if (TextUtils.equals(string, "link")) {
                        linkedList3.add(new PlainAttachFactory.AttachLinkFactory().createPlain(jSONObject2.getJSONObject("attach")));
                    } else if (TextUtils.equals(string, "cloud")) {
                        linkedList2.add(new PlainAttachFactory.AttachCloudFactory().createPlain(jSONObject2.getJSONObject("attach")));
                    } else {
                        String string3 = jSONObject2.getJSONObject("attach").getString("id");
                        Attach attach = new Attach();
                        attach.setFileId(string3);
                        attach.setPartId(string2);
                        linkedList.add(attach);
                    }
                }
            }
            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("error_files");
            return new Result(linkedList, linkedList4, linkedList3, linkedList2, (jSONArrayOptJSONArray2 == null || jSONArrayOptJSONArray2.length() <= 0) ? null : jSONArrayOptJSONArray2.getJSONObject(0).optString("error"));
        } catch (JSONException e10) {
            LOG.e("Unable to parse ", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
