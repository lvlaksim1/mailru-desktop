package ru.mail.data.cmd.server.pusher.pushme;

import android.content.Context;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailapp.R;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@HostProviderAnnotation(defHost = R.string.push_default_host, defScheme = R.string.push_default_scheme, prefKey = "push")
@UrlPath(pathSegments = {"ss", "unsubscribe_by_device_id"})
public class PushMeRemovePushSettingsCmd extends PostServerRequest<Params, EmptyResult> {
    private static final Log LOG = Log.getLog("SendPushSettingsCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {

        @Param(method = HttpMethod.POST, name = "account")
        @NotNull
        private final String mAccount;

        @Param(method = HttpMethod.POST, name = "application")
        @NotNull
        private final String mApplication;

        @Param(method = HttpMethod.POST, name = "device_id")
        @NotNull
        private final String mDeviceId;

        public Params(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            super(new AccountInfo(str2), null);
            this.mAccount = str2;
            this.mDeviceId = str;
            this.mApplication = str3;
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
            return this.mAccount.equals(params.mAccount) && this.mDeviceId.equals(params.mDeviceId) && this.mApplication.equals(params.mApplication);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mAccount, this.mDeviceId, this.mApplication);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    public PushMeRemovePushSettingsCmd(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean needPlatformParams() {
        return false;
    }

    @Override // ru.mail.network.NetworkCommand
    protected CommandStatus<?> processResponse(NetworkCommand.Response response) {
        if (response.getStatusCode() == 401) {
            LOG.w("Response status is 401");
            return new NetworkCommandStatus.NO_AUTH(getNoAuthInfo());
        }
        if (response.getStatusCode() != 200) {
            LOG.w("Response status is not OK (401)");
            return getErrorResponse(response.getStatusCode());
        }
        LOG.i("Response is OK");
        return new CommandStatus.OK();
    }

    public PushMeRemovePushSettingsCmd(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) {
    }
}
