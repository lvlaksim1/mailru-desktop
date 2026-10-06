package ru.mail.auth.request;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.Authenticator.R;
import ru.mail.auth.AuthErrors;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
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
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "signup", "external"})
public class ExternalAccountRegistrationRequest extends SingleRequest<Params, EmptyResult> {
    private static final Log LOG = Log.getLog("ExternalAccountRegistrationRequest");

    @Keep
    @Param(method = HttpMethod.HEADER_SET, name = "X-Mobile-App")
    private String mMobileAntiCSRFHeader;

    /* JADX INFO: compiled from: ProGuard */
    public class ExternalAccountRegistrationDelegate extends NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate {
        public ExternalAccountRegistrationDelegate() {
            super();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            try {
                return new JSONObject(str).getString("status");
            } catch (JSONException e10) {
                ExternalAccountRegistrationRequest.LOG.e("Error parsing response status " + e10);
                return "-1";
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
            try {
                if (jSONObject.getJSONObject("body").has("code")) {
                    return new AuthCommandStatus.CAPTCHA(null);
                }
            } catch (JSONException e10) {
                ExternalAccountRegistrationRequest.LOG.e("Error parsing response " + e10);
            }
            return new CommandStatus.ERROR();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            try {
                int i10 = new JSONObject(response.getRespString()).getInt("status");
                return new AuthCommandStatus.ERROR_WITH_STATUS_CODE(i10, AuthErrors.getErrorMessage(ExternalAccountRegistrationRequest.this.getContext(), null, i10));
            } catch (JSONException unused) {
                return new CommandStatus.ERROR();
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return null;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Keep
    public static class Params {
        private static final String JSON_KEY_FIRST = "first";
        private static final String JSON_KEY_LAST = "last";
        private static final String PARAM_KEY_CODE = "code";
        private static final String PARAM_KEY_COOKIE = "Cookie";
        private static final String PARAM_KEY_NAME = "name";
        private static final String PARAM_KEY_SIGNUP_TOKEN = "signup_token";

        @Param(method = HttpMethod.GET, name = "code")
        private final String mCaptchaResponse;

        @Param(getterName = "getCookie", method = HttpMethod.HEADER_SET, name = "Cookie", useGetter = true)
        private String mCookie;
        private final String mFirstName;
        private final String mLastName;
        private final String mMrcuCookie;

        @Param(getterName = "getName", method = HttpMethod.GET, name = "name", useGetter = true)
        private String mName;

        @Param(method = HttpMethod.GET, name = "signup_token")
        private final String mSignupToken;

        public Params(String str, String str2, String str3, String str4, String str5) {
            this.mFirstName = str;
            this.mLastName = str2;
            this.mCaptchaResponse = TextUtils.isEmpty(str3) ? null : str3;
            this.mSignupToken = str4;
            this.mMrcuCookie = str5;
        }

        public String getCookie() {
            if (TextUtils.isEmpty(this.mMrcuCookie)) {
                return null;
            }
            return "mrcu=" + this.mMrcuCookie;
        }

        public String getName() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("first", this.mFirstName);
                jSONObject.put("last", this.mLastName);
                return jSONObject.toString();
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    public ExternalAccountRegistrationRequest(Context context, HostProvider hostProvider, String str, String str2, String str3, String str4, String str5, boolean z10) {
        super(context, new Params(str, str2, str3, str4, str5), hostProvider, z10);
        this.mMobileAntiCSRFHeader = context.getString(R.string.auth_csrf_header);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ExternalAccountRegistrationDelegate();
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, EmptyResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new JsonStatusResponseProcessor(response, networkCommandBaseDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
