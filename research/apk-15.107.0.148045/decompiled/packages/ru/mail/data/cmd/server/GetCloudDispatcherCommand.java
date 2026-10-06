package ru.mail.data.cmd.server;

import android.content.Context;
import com.google.android.gms.ads.RequestConfiguration;
import java.net.URI;
import java.net.URISyntaxException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.entities.AttachCloud;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@HostProviderAnnotation(defHostStrRes = "string/cloud_dispatcher_default_host", defSchemeStrRes = "string/cloud_dispatcher_default_scheme", prefKey = "cloud_dispatcher")
@UrlPath(pathSegments = {RequestConfiguration.MAX_AD_CONTENT_RATING_G})
public class GetCloudDispatcherCommand extends ServerCommandBase<Params, String> {
    private static final Log LOG = Log.getLog("GetCloudDispatcherCommand");

    /* JADX INFO: compiled from: ProGuard */
    private static class ExpandedResponseProcessor extends ResponseProcessor {
        ExpandedResponseProcessor(NetworkCommand.Response response, NetworkCommand<Params, String>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
            super(response, networkCommandBaseDelegate);
        }

        @Override // ru.mail.network.ResponseProcessor
        public CommandStatus<?> process() {
            getResponse().createStringFromData();
            return getResponse().getStatusCode() != 200 ? getDelegate().onError(getResponse()) : getDelegate().onResponseOk(getResponse());
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {
        private final AttachCloud attachCloud;

        public Params(@NotNull AttachCloud attachCloud, @Nullable String str, @Nullable FolderState folderState) {
            super(new AccountInfo(str), folderState);
            this.attachCloud = attachCloud;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Params) || !super.equals(obj)) {
                return false;
            }
            AttachCloud attachCloud = this.attachCloud;
            AttachCloud attachCloud2 = ((Params) obj).attachCloud;
            return attachCloud == null ? attachCloud2 == null : attachCloud.equals(attachCloud2);
        }

        public AttachCloud getAttachCloud() {
            return this.attachCloud;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            AttachCloud attachCloud = this.attachCloud;
            return iHashCode + (attachCloud != null ? attachCloud.hashCode() : 0);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    public GetCloudDispatcherCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, String>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new ExpandedResponseProcessor(response, networkCommandBaseDelegate);
    }

    GetCloudDispatcherCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public String onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            String strTrim = response.getRespString().trim();
            Log log = LOG;
            log.d("responseStr = " + strTrim);
            String[] strArrSplit = strTrim.split("\\s+");
            log.d("parts.length = " + strArrSplit.length);
            log.d("parts[0] = " + strArrSplit[0]);
            return new URI(strArrSplit[0]).toString();
        } catch (URISyntaxException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
