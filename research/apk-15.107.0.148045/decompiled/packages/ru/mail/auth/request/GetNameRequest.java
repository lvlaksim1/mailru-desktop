package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "external"})
public class GetNameRequest extends SingleRequest<Params, Result> {
    private static final String JSON_KEY_BODY = "body";
    private static final String JSON_KEY_FIRSTNAME = "name";
    private static final String JSON_KEY_LASTNAME = "last";
    private static final String JSON_KEY_NAME = "name";
    private static final Log LOG = Log.getLog("GetNameRequest");

    /* JADX INFO: compiled from: ProGuard */
    public class GetNameDelegate extends NetworkCommand<Params, Result>.NetworkCommandBaseDelegate {
        public GetNameDelegate() {
            super();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            try {
                return new JSONObject(str).getString("status");
            } catch (JSONException e10) {
                GetNameRequest.LOG.e("JSON exception while parsing response from server " + e10);
                return "-1";
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
            return new CommandStatus.ERROR();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return new CommandStatus.ERROR();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {
        private static final String PARAM_KEY_SIGNUP_TOKEN = "signup_token";

        @Param(method = HttpMethod.GET, name = "signup_token")
        private final String mSignupToken;

        public Params(String str) {
            this.mSignupToken = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                String str = this.mSignupToken;
                String str2 = ((Params) obj).mSignupToken;
                if (str != null) {
                    return str.equals(str2);
                }
                if (str2 == null) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            String str = this.mSignupToken;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mFirstName;
        private final String mLastName;

        public Result(String str, String str2) {
            this.mFirstName = str;
            this.mLastName = str2;
        }

        public String getFirstName() {
            return this.mFirstName;
        }

        public String getLastName() {
            return this.mLastName;
        }
    }

    public GetNameRequest(Context context, HostProvider hostProvider, String str, boolean z10) {
        super(context, new Params(str), hostProvider, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new GetNameDelegate();
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new JsonStatusResponseProcessor(response, networkCommandBaseDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        String string;
        String string2;
        JSONObject jSONObjectOptJSONObject;
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject("name")) == null) {
                string = "";
                string2 = "";
            } else {
                string = jSONObjectOptJSONObject.getString("name");
                string2 = jSONObjectOptJSONObject.getString("last");
            }
            return new Result(string, string2);
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
