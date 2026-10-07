package ru.mail.registration.request;

import android.content.Context;
import androidx.annotation.Keep;
import com.vk.api.sdk.exceptions.VKApiCodes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.Authenticator.R;
import ru.mail.auth.request.AccountInfo;
import ru.mail.auth.request.util.AccountInfoUtilsKt;
import ru.mail.authorizesdk.data.request.common.PostRequest;
import ru.mail.cloud.upload.internal.analytics.EventParams;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.UncodedHtmlHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "signup", VKApiCodes.EXTRA_CONFIRM})
public class RequestCookieUrlByLibverifyToken extends PostRequest<Params, Result> {
    private static final String BODY_KEY = "body";
    private static final Log LOG = Log.getLog("RequestCookieUrlByLibverifyToken");
    private static final String STATUS_CODE_KEY = "status";

    @Keep
    @Param(method = HttpMethod.HEADER_SET, name = "X-Mobile-App")
    private String mMobileAntiCSRFHeader;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {

        @Keep
        @Param(method = HttpMethod.GET, name = PreferenceHostProvider.URL_PARAM_CLIENT)
        private static final String mClient = "mobile";

        @Keep
        @Param(getterName = "getRegVerify", method = HttpMethod.POST, name = "reg_verify", useGetter = true)
        private static final String mRegVerify = "";

        @Keep
        @Param(method = HttpMethod.GET, name = AccountInfoUtilsKt.PARAM_KEY_ACT_MODE)
        private final String mActMode;

        @Keep
        @Param(method = HttpMethod.POST, name = "email")
        private final String mEmail;
        private final String mPhone;
        private final String mRecaptcha;
        private final String mRegistrtionId;
        private final String mSessionId;
        private final String mToken;

        @Param(method = HttpMethod.POST, name = "from")
        private final String mXmailFrom;

        public Params(@NotNull AccountInfo accountInfo, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @Nullable String str5, @Nullable String str6) {
            this.mEmail = accountInfo.getLogin();
            this.mSessionId = str;
            this.mToken = str2;
            this.mRegistrtionId = str3;
            this.mPhone = str4;
            this.mRecaptcha = str5;
            this.mActMode = AccountInfoUtilsKt.getActiveMode(accountInfo);
            this.mXmailFrom = str6;
        }

        @Keep
        public String getEmail() {
            return this.mEmail;
        }

        @Keep
        public String getRegVerify() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", this.mRegistrtionId);
                jSONObject.put("token", this.mToken);
                jSONObject.put(EventParams.SESSION_ID, this.mSessionId);
                jSONObject.put("phone", this.mPhone);
                String str = this.mRecaptcha;
                if (str != null) {
                    jSONObject.put("capcha", str);
                }
                return jSONObject.toString();
            } catch (JSONException unused) {
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mBody;
        private final int mStatusCode;

        public Result(int i10, String str) {
            this.mStatusCode = i10;
            this.mBody = str;
        }

        public String getBody() {
            return this.mBody;
        }

        public int getStatusCode() {
            return this.mStatusCode;
        }
    }

    public RequestCookieUrlByLibverifyToken(Context context, Params params, boolean z10) {
        super(context, params, z10);
        this.mMobileAntiCSRFHeader = context.getString(R.string.auth_csrf_header);
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new UncodedHtmlHostProvider(super.getHostProvider());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        return super.onExecute(executorSelector);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            LOG.d(jSONObject.toString());
            return new Result(jSONObject.getInt("status"), jSONObject.getString("body"));
        } catch (JSONException e10) {
            LOG.e(e10.getMessage(), e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
