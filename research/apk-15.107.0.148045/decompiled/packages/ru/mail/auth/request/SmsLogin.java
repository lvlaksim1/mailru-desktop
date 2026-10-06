package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NoAuthInfo;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@UrlPath(pathSegments = {"PhoneAuthCheck"})
public class SmsLogin extends SingleRequest<Params, Result> {
    private static final String JSON_EMAILS = "emails";
    private static final String JSON_PHONE_TOKEN = "PhoneToken";
    private static final Log LOG = Log.getLog("SmsLogin");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {
        private static final String PARAM_KEY_MESSAGE = "Message";
        private static final String PARAM_KEY_PHONE = "Phone";

        @Param(method = HttpMethod.GET, name = "Message")
        private final String mMessage;

        @Param(method = HttpMethod.GET, name = PARAM_KEY_PHONE)
        private final String mPhone;

        public Params(String str, String str2) {
            this.mPhone = str;
            this.mMessage = str2;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final List<String> mEmails;
        private final String mPhoneToken;

        public Result(String str, List<String> list) {
            this.mPhoneToken = str;
            this.mEmails = list;
        }

        public List<String> getEmails() {
            return this.mEmails;
        }

        public String getPhoneToken() {
            return this.mPhoneToken;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public class SmsLoginDelegate extends NetworkCommand<Params, Result>.NetworkCommandBaseDelegate {
        public static final String JSON_MESSAGE = "message";
        private static final String JSON_STATUS = "status";
        public static final String JSON_VALUE_BAD_MESSAGE = "bad Message";
        public static final String JSON_VALUE_FAIL = "fail";
        public static final String JSON_VALUE_OK = "ok";

        public SmsLoginDelegate() {
            super();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            try {
                return "ok".equals(new JSONObject(str).getString("status")) ? String.valueOf(200) : "-1";
            } catch (JSONException e10) {
                SmsLogin.LOG.e("Error parsing response " + e10);
                return "-1";
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            try {
                JSONObject jSONObject = new JSONObject(response.getRespString());
                if ("fail".equals(jSONObject.getString("status")) && JSON_VALUE_BAD_MESSAGE.equals(jSONObject.getString("message"))) {
                    return new AuthCommandStatus.ERROR_INVALID_LOGIN();
                }
            } catch (JSONException e10) {
                SmsLogin.LOG.e("Error parsing response " + e10);
            }
            return super.onError(response);
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return new CommandStatus.ERROR();
        }
    }

    public SmsLogin(Context context, HostProvider hostProvider, String str, String str2, boolean z10) {
        super(context, new Params(str, str2), hostProvider, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new SmsLoginDelegate();
    }

    @Override // ru.mail.authorizesdk.data.request.common.SingleRequest, ru.mail.network.NetworkCommand
    public NoAuthInfo getNoAuthInfo() {
        return null;
    }

    @Override // ru.mail.network.NetworkCommand
    @NonNull
    protected String getPathTag() {
        return "phone_auth_check";
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
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            String string = jSONObject.getString(JSON_PHONE_TOKEN);
            JSONArray jSONArray = jSONObject.getJSONArray("emails");
            ArrayList arrayList = new ArrayList();
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                arrayList.add(jSONArray.getString(i10));
            }
            return new Result(string, arrayList);
        } catch (JSONException e10) {
            LOG.e("Error parsing response " + e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
