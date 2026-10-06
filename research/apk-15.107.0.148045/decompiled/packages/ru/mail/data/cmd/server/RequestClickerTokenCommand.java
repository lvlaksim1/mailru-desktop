package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.navigation.segue.ClickerLinkConstructor;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "clickerproxy", "autogen"})
public class RequestClickerTokenCommand extends ServerCommandBase<ServerCommandEmailParams, Result> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mToken;
        private final long mTtl;

        public Result(String str, long j10) {
            this.mToken = str;
            this.mTtl = j10;
        }

        public String getToken() {
            return this.mToken;
        }

        public long getTtl() {
            return this.mTtl;
        }
    }

    public RequestClickerTokenCommand(Context context, ServerCommandEmailParams serverCommandEmailParams, boolean z10) {
        super(context, serverCommandEmailParams, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            return new Result(jSONObject.getString(ClickerLinkConstructor.AUTOGEN_TOKEN), jSONObject.getLong("ttl"));
        } catch (JSONException unused) {
            throw new NetworkCommand.PostExecuteException();
        }
    }
}
