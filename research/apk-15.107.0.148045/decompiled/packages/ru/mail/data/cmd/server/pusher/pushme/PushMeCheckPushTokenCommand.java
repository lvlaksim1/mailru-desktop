package ru.mail.data.cmd.server.pusher.pushme;

import android.content.Context;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailapp.R;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@HostProviderAnnotation(defHost = R.string.push_default_host, defScheme = R.string.push_default_scheme, prefKey = "push")
@UrlPath(pathSegments = {"ss", BaseSettingsActivity.CHECK_PUSH_TOKEN})
public class PushMeCheckPushTokenCommand extends ServerCommandBase<Params, Result> {
    private static final Log LOG = Log.getLog("PushMeCheckPushTokenCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {

        @Param(method = HttpMethod.GET, name = "account")
        @NotNull
        private final String mLogin;

        @Param(method = HttpMethod.GET, name = "token")
        @NotNull
        private final String mPushToken;

        public Params(@NotNull String str, @NotNull String str2) {
            super(new AccountInfo(str), null);
            this.mPushToken = str2;
            this.mLogin = str;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                return this.mPushToken.equals(((Params) obj).mPushToken);
            }
            return false;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mPushToken);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final boolean mTokenExists;

        public Result(boolean z10) {
            this.mTokenExists = z10;
        }

        public boolean tokenExists() {
            return this.mTokenExists;
        }
    }

    public PushMeCheckPushTokenCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected CommandStatus<?> processResponse(NetworkCommand.Response response) {
        try {
            return new CommandStatus.OK(onPostExecuteRequest(response));
        } catch (NetworkCommand.PostExecuteException e10) {
            return new CommandStatus.ERROR(e10);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        String respString = response.getRespString();
        LOG.d("check_push response: " + respString);
        try {
            boolean z10 = true;
            if (new JSONObject(respString).getInt("token_exists") != 1) {
                z10 = false;
            }
            return new Result(z10);
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
    }
}
