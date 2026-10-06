package ru.mail.serverapi;

import com.vk.superapp.core.api.models.AuthAnswer;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class TornadoResponseProcessor extends ResponseProcessor {
    public static final int HTTP_RETRY_WITH = 449;
    public static final int HTTP_TOO_MANY_REQUESTS = 429;
    public static final String NO_AUTH_2STEP_REQUIRED = "twostep_required";
    public static final String NO_AUTH_BIND_REQUIRED = "bind_required";
    public static final String NO_AUTH_TOKEN = "token";
    public static final String NO_AUTH_USER = "user";

    /* JADX INFO: compiled from: ProGuard */
    public static class ErrorForFlurry {
        public final int mErrorStringId;
        public final String mFlurryMessage;

        public ErrorForFlurry(String str, int i10) {
            this.mFlurryMessage = str;
            this.mErrorStringId = i10;
        }
    }

    public TornadoResponseProcessor(NetworkCommand.Response response, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        super(response, networkCommandBaseDelegate);
    }

    private String getResponseBody() {
        try {
            return new JSONObject(getResponse().getRespString()).getString("body");
        } catch (JSONException e10) {
            e10.printStackTrace();
            return "";
        }
    }

    public JSONObject getBadRequestBody(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            try {
                return jSONObject.getJSONArray("body").getJSONObject(0);
            } catch (JSONException unused) {
                return (!jSONObject.has("body") || jSONObject.optJSONObject("body") == null) ? jSONObject : jSONObject.getJSONObject("body");
            }
        } catch (JSONException e10) {
            e10.printStackTrace();
            return null;
        }
    }

    @Override // ru.mail.network.ResponseProcessor
    public CommandStatus<?> process() {
        if (getResponse().getStatusCode() != 200) {
            return getDelegate().onError(getResponse());
        }
        if (!getDelegate().isStringResponse()) {
            return getDelegate().onResponseOk(getResponse());
        }
        getResponse().createStringFromData();
        return processResponse(Integer.parseInt(getDelegate().getResponseStatus(getResponse().getRespString())));
    }

    public CommandStatus<?> processError(String str, int i10) {
        return new CommandStatus.ERROR(new ErrorForFlurry(str, i10));
    }

    protected CommandStatus<?> processResponse(int i10) {
        if (i10 == 200) {
            return getDelegate().onResponseOk(getResponse());
        }
        if (i10 == 304) {
            return getDelegate().onNotModified();
        }
        if (i10 == 400) {
            return getDelegate().onBadRequest(getBadRequestBody(getResponse().getRespString()));
        }
        if (i10 == 403) {
            String responseBody = getResponseBody();
            if (responseBody.equals("folder")) {
                return getDelegate().onFolderAccessDenied();
            }
            return (responseBody.equals("user") || responseBody.equals("token") || responseBody.equals(NO_AUTH_2STEP_REQUIRED) || responseBody.equals(NO_AUTH_BIND_REQUIRED)) ? getDelegate().onUnauthorized(responseBody) : getDelegate().onError(getResponse());
        }
        if (i10 == 429) {
            return processError(AuthAnswer.ERROR_TYPE_TOO_MANY_REQUESTS, R.string.too_many_requests);
        }
        if (i10 != 449) {
            return (i10 == 500 || i10 == 503) ? processError("server_is_unavailable", R.string.server_is_unavailable) : processError("request_error", R.string.unable_to_complete_request);
        }
        return new MailCommandStatus.ATTEMPTS_EXCEEDED(Integer.valueOf(R.string.too_many_requests));
    }
}
