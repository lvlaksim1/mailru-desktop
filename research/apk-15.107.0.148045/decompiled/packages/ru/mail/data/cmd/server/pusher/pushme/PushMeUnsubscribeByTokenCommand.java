package ru.mail.data.cmd.server.pusher.pushme;

import android.content.Context;
import androidx.annotation.NonNull;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailapp.R;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.network.requestbody.ParamsRequestBody;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.util.log.FileHandlerArchive;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@HostProviderAnnotation(defHost = R.string.push_default_host, defScheme = R.string.push_default_scheme, prefKey = "push")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, FileHandlerArchive.VERSION_POSTFIX, "unsubscribe_by_token"})
public class PushMeUnsubscribeByTokenCommand extends PushMeSendPushSettingsCommand {
    private static final Log LOG = Log.getLog("PushMeUnsubscribeByTokenCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends PushMeSendPushSettingsCommand.Params {

        @Param(method = HttpMethod.POST, name = "application")
        private final String mApplicationName;

        @Param(method = HttpMethod.POST, name = "token")
        private final String mPushToken;

        public Params(@NonNull String str, @NonNull String str2) {
            super(null, null);
            this.mPushToken = str;
            this.mApplicationName = str2;
        }

        @Override // ru.mail.data.cmd.server.pusher.pushme.PushMeSendPushSettingsCommand.Params, ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            return "Params{mPushToken=****, mApplicationName='" + this.mApplicationName + '\'' + AbstractJsonLexerKt.END_OBJ;
        }
    }

    public PushMeUnsubscribeByTokenCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.LEGACY;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(final NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<PushMeSendPushSettingsCommand.Params, EmptyResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new ResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.pusher.pushme.PushMeUnsubscribeByTokenCommand.1
            @Override // ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                try {
                    String respString = response.getRespString();
                    PushMeUnsubscribeByTokenCommand.LOG.d(PushMeUnsubscribeByTokenCommand.this.filterTokenString(respString));
                    if (new JSONObject(respString).getJSONObject("error").getInt("code") == 0) {
                        return new CommandStatus.OK(new EmptyResult());
                    }
                    return new CommandStatus.ERROR("status code != 0; " + respString);
                } catch (JSONException e10) {
                    return new CommandStatus.ERROR(e10);
                }
            }
        };
    }

    @Override // ru.mail.data.cmd.server.pusher.pushme.PushMeSendPushSettingsCommand, ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @NotNull
    protected RequestBody onPrepareRequestBody() {
        return new ParamsRequestBody(getPostParams(), "UTF-8");
    }

    @Override // ru.mail.data.cmd.server.pusher.pushme.PushMeSendPushSettingsCommand, ru.mail.network.NetworkCommand
    @NotNull
    protected CommandStatus<?> processResponse(NetworkCommand.Response response) {
        return getResponseProcessor(response, getServerApi(), getServerApi().createDefaultDelegate(this)).process();
    }
}
