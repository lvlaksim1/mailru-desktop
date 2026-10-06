package ru.mail.auth.request;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.util.AccountInfoUtilsKt;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.data.entities.Collector;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.registration.request.BaseRegRequest;
import ru.mail.registration.request.RegServerRequest;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "signup", "external", "unknown"})
public class MailServerParametersRequest extends BaseRegRequest<Params, Result> {
    private static final Log LOG = Log.getLog("MailServerParametersRequest");

    /* JADX INFO: compiled from: ProGuard */
    public enum InvalidFieldName {
        COLLECT_TYPE("collect.type"),
        COLLECT_SERVER("collect.server"),
        COLLECT_PORT("collect.port"),
        COLLECT_SSL("collect.ssl"),
        SMTP_SERVER("smtp.server"),
        SMTP_PORT("smtp.port"),
        SMTP_SSL("smtp.ssl"),
        CODE("code"),
        AUTH_TOKEN(RegServerRequest.ATTR_AUTH_TOKEN);

        private final String value;

        InvalidFieldName(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public class MailServerParametersDelegate extends NetworkCommand<Params, Result>.NetworkCommandBaseDelegate {
        public MailServerParametersDelegate() {
            super();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            try {
                return new JSONObject(str).getString("status");
            } catch (JSONException unused) {
                return "-1";
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("body");
                ArrayList arrayList = new ArrayList();
                for (InvalidFieldName invalidFieldName : InvalidFieldName.values()) {
                    if (jSONObject2.has(invalidFieldName.getValue())) {
                        arrayList.add(invalidFieldName);
                    }
                }
                return new NetworkCommandStatus.BAD_REQUEST(arrayList);
            } catch (JSONException unused) {
                return new CommandStatus.ERROR();
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            try {
                JSONObject jSONObject = new JSONObject(response.getRespString());
                int i10 = jSONObject.getInt("status");
                if (i10 != 429) {
                    return new AuthCommandStatus.ERROR_WITH_STATUS_CODE(i10, jSONObject.optString("body"));
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(InvalidFieldName.CODE);
                return new AuthCommandStatus.CODE_ERROR(arrayList);
            } catch (JSONException e10) {
                MailServerParametersRequest.LOG.e("Error parsing error response " + e10);
                return super.onError(response);
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return new CommandStatus.ERROR();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Keep
    public static class Params {
        static final String PARAM_KEY_CODE = "code";
        static final String PARAM_KEY_COLLECT = "collect";
        private static final String PARAM_KEY_COOKIE = "Cookie";
        static final String PARAM_KEY_SMTP = "smtp";
        static final String PARAM_KEY_USER = "user";

        @Param(getterName = "getCode", method = HttpMethod.POST, name = "code", useGetter = true)
        private String code;

        @Param(getterName = "getCollect", method = HttpMethod.POST, name = PARAM_KEY_COLLECT, useGetter = true)
        private String collect;

        @Keep
        @Param(method = HttpMethod.GET, name = AccountInfoUtilsKt.PARAM_KEY_ACT_MODE)
        private final String mActMode;

        @Param(getterName = "getCookie", method = HttpMethod.HEADER_SET, name = "Cookie", useGetter = true)
        private String mCookie;
        private final MailServerParameters mMailServerParameters;

        @Param(getterName = "getUser", method = HttpMethod.POST, name = "user", useGetter = true)
        private String mUser;

        @Param(getterName = "getSmtp", method = HttpMethod.POST, name = "smtp", useGetter = true)
        private String smtp;

        public Params(@NotNull MailServerParameters mailServerParameters, @Nullable String str) {
            this.mMailServerParameters = mailServerParameters;
            this.mActMode = str;
        }

        private String createCollectParam() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", this.mMailServerParameters.getIncomingServerType().getValue());
            if (!TextUtils.isEmpty(this.mMailServerParameters.getLogin())) {
                jSONObject.put("user_name", this.mMailServerParameters.getLogin());
            }
            jSONObject.put(Collector.SERVER, this.mMailServerParameters.getIncomingServerHost());
            jSONObject.put("port", this.mMailServerParameters.getIncomingServerPort());
            jSONObject.put(Collector.SSL, this.mMailServerParameters.isIncomingServerUseSsl());
            return jSONObject.toString();
        }

        private String createSmtpParam() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Collector.SERVER, this.mMailServerParameters.getOutgoingServerHost());
            jSONObject.put("port", this.mMailServerParameters.getOutgoingServerPort());
            jSONObject.put(Collector.SSL, this.mMailServerParameters.ismOutgoingServerUseSsl());
            return jSONObject.toString();
        }

        private String createUserParam() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("email", this.mMailServerParameters.getEmail());
            jSONObject.put("password", this.mMailServerParameters.getPassword());
            return jSONObject.toString();
        }

        public String getCode() {
            if (TextUtils.isEmpty(this.mMailServerParameters.getCaptchaCode())) {
                return null;
            }
            return this.mMailServerParameters.getCaptchaCode();
        }

        public String getCollect() {
            try {
                return createCollectParam();
            } catch (JSONException e10) {
                MailServerParametersRequest.LOG.e("Collect params JSON exception", e10);
                return null;
            }
        }

        public String getCookie() {
            if (TextUtils.isEmpty(this.mMailServerParameters.getMrcuCookie())) {
                return null;
            }
            return "mrcu=" + this.mMailServerParameters.getMrcuCookie();
        }

        public String getSmtp() {
            try {
                return createSmtpParam();
            } catch (JSONException e10) {
                MailServerParametersRequest.LOG.e("SMTP params JSON exception", e10);
                return null;
            }
        }

        public String getUser() {
            try {
                return createUserParam();
            } catch (JSONException e10) {
                MailServerParametersRequest.LOG.e("User params JSON exception", e10);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mDoregistrationSignupToken;
        private final long mLastModified;

        public Result(long j10, String str) {
            this.mLastModified = j10;
            this.mDoregistrationSignupToken = str;
        }

        public long getLastModified() {
            return this.mLastModified;
        }

        public String getSignupToken() {
            return this.mDoregistrationSignupToken;
        }
    }

    public MailServerParametersRequest(Context context, HostProvider hostProvider, MailServerParameters mailServerParameters, boolean z10) {
        super(context, new Params(mailServerParameters, AccountInfoUtilsKt.getActiveMode(context, mailServerParameters.getEmail())), hostProvider, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new MailServerParametersDelegate();
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new JsonStatusResponseProcessor(response, networkCommandBaseDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            return new Result(jSONObject.getLong("last_modified"), jSONObject.getString("body"));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
