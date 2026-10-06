package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.cloud.upload.internal.analytics.EventParams;
import ru.mail.mailbox.cmd.CommandStatus;
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
import ru.mail.serverapi.WithSampling;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "cloud", "attachment", ProductAction.ACTION_ADD})
@WithSampling
public class AddToCloudBundle extends PostServerRequest<Params, Result> {
    private static final Log LOG = Log.getLog("AddToCloudBundle");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "bundle_id")
        private final String mBundleId;

        @Param(method = HttpMethod.POST, name = EventParams.HASH)
        private final String mHash;

        @Param(method = HttpMethod.POST, name = "name")
        private final String mName;

        @Param(method = HttpMethod.POST, name = "size")
        private final long mSize;

        public Params(@Nullable String str, @Nullable String str2, long j10, @Nullable String str3, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            this.mName = str;
            this.mHash = str2;
            this.mSize = j10;
            this.mBundleId = str3;
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
            if (this.mSize == params.mSize && Objects.equals(this.mName, params.mName) && Objects.equals(this.mHash, params.mHash)) {
                return Objects.equals(this.mBundleId, params.mBundleId);
            }
            return false;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mName;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.mHash;
            int iHashCode3 = str2 != null ? str2.hashCode() : 0;
            long j10 = this.mSize;
            int i10 = (((iHashCode2 + iHashCode3) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            String str3 = this.mBundleId;
            return i10 + (str3 != null ? str3.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mFileId;
        private final String mFileName;

        public Result(String str, String str2) {
            this.mFileName = str;
            this.mFileId = str2;
        }

        public String getFileId() {
            return this.mFileId;
        }

        public String getFileName() {
            return this.mFileName;
        }
    }

    public AddToCloudBundle(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(final NetworkCommand.Response response, ServerApi serverApi, final NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.AddToCloudBundle.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                CommandStatus<?> commandStatusExecute = new ErrorHandler(new ErrorStringProviderImpl(AddToCloudBundle.this.getContext())).execute(ErrorHandler.EnumErrorHandlerName.ADD_TO_CLOUD_BUNDLE, response, networkCommandBaseDelegate);
                return commandStatusExecute != null ? commandStatusExecute : super.process();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            return new Result(jSONObject.getString("name"), jSONObject.getString("file_id"));
        } catch (JSONException e10) {
            LOG.e("Unable to parse ", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
