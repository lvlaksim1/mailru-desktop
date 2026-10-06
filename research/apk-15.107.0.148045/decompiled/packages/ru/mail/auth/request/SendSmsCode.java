package ru.mail.auth.request;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.vk.superapp.api.dto.app.WebOrder;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;
import ru.mail.Authenticator.R;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.data.entities.MailThread;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@UrlPath(pathSegments = {"PhoneAuth"})
public class SendSmsCode extends SingleRequest<Params, Result> {
    public static final int INVALID_MAIL_CODE = 29;
    private static final Log LOG = Log.getLog("SendSmsCode");
    public static final int NO_PHONE_CODE = 31;
    public static final int RATE_LIMIT_CODE = 28;
    public static final int UNKNOWN_CODE = 30;

    /* JADX INFO: compiled from: ProGuard */
    @Keep
    public static class Params {
        private static final String PARAM_KEY_ISO_COUNTRY_CODE = "iso_country_code";
        private static final String PARAM_KEY_IVR = "ivr";
        private static final String PARAM_KEY_LANG = "Lang";
        private static final String PARAM_KEY_PHONE = "Phone";
        private final boolean mIsIvr;

        @Param(method = HttpMethod.GET, name = PARAM_KEY_ISO_COUNTRY_CODE)
        private final String mIsoCountryCode;

        @Param(getterName = "getIvr", method = HttpMethod.GET, name = PARAM_KEY_IVR, useGetter = true)
        private int mIvr;

        @Param(method = HttpMethod.GET, name = PARAM_KEY_LANG)
        private final String mLang;

        @Param(getterName = "getPhone", method = HttpMethod.GET, name = PARAM_KEY_PHONE, useGetter = true)
        private final String mPhone;

        public Params(String str, String str2, String str3, boolean z10) {
            this.mPhone = str;
            this.mIsoCountryCode = str2;
            this.mLang = str3;
            this.mIsIvr = z10;
        }

        public int getIvr() {
            return this.mIsIvr ? 1 : 0;
        }

        public String getPhone() {
            return Marker.ANY_NON_NULL_MARKER + this.mPhone;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final String mNormalizedPhone;
        private final int mSize;
        private final int mWait;

        public Result(int i10, int i11, String str) {
            this.mSize = i10;
            this.mWait = i11;
            this.mNormalizedPhone = str;
        }

        public String getNormalizedPhone() {
            return this.mNormalizedPhone;
        }

        public int getSize() {
            return this.mSize;
        }

        public int getWait() {
            return this.mWait;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public enum SendSmsError {
        RATE_LIMIT(R.string.error_sms_limit, 28, "rate limit exceeded"),
        INVALID_MAIL(R.string.error_sms_email_invalid, 29, "bad login"),
        UNKNOWN(R.string.error_sms_fail, 30, null),
        NO_PHONE(R.string.error_sms_no_phone, 31, "no phone");

        private final int errorCode;
        private final int errorString;
        private final String message;

        /* JADX INFO: compiled from: ProGuard */
        private static class Constants {
            private Constants() {
            }
        }

        SendSmsError(int i10, int i11, String str) {
            this.errorString = i10;
            this.errorCode = i11;
            this.message = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static SendSmsError getError(String str) {
            if (TextUtils.isEmpty(str)) {
                return UNKNOWN;
            }
            for (SendSmsError sendSmsError : values()) {
                if (str.equals(sendSmsError.message)) {
                    return sendSmsError;
                }
            }
            return UNKNOWN;
        }

        public int getErrorCode() {
            return this.errorCode;
        }

        public int getErrorString() {
            return this.errorString;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public class SmsCodeDelegate extends NetworkCommand<Params, Result>.NetworkCommandBaseDelegate {
        public SmsCodeDelegate() {
            super();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            try {
                return "ok".equals(new JSONObject(str).getString("status")) ? String.valueOf(200) : "-1";
            } catch (JSONException e10) {
                SendSmsCode.LOG.e("Error parsing response status " + e10);
                return "-1";
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            try {
                JSONObject jSONObject = new JSONObject(response.getRespString());
                SendSmsError error = SendSmsError.getError(jSONObject.getString("message"));
                return error == SendSmsError.RATE_LIMIT ? new AuthCommandStatus.ERROR_RATE_LIMIT(error, new Result(jSONObject.getInt(MailThread.COL_NAME_LENGTH), jSONObject.getInt(WebOrder.STATUS_WAIT), jSONObject.getString("phone"))) : new AuthCommandStatus.SEND_SMS_ERROR(error);
            } catch (JSONException e10) {
                SendSmsCode.LOG.e("Error parsing response " + e10);
                return new AuthCommandStatus.SEND_SMS_ERROR(SendSmsError.UNKNOWN);
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return new CommandStatus.ERROR();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SendSmsCode(Context context, HostProvider hostProvider, String str, boolean z10, boolean z11) {
        String country = Locale.getDefault().getCountry();
        Locale locale = Locale.US;
        super(context, new Params(str, firstNotEmpty(country, locale.getCountry()), firstNotEmpty(Locale.getDefault().getLanguage(), locale.getLanguage()), z10), hostProvider, z11);
    }

    public static String firstNotEmpty(String... strArr) {
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    private Result getResultFromResponse(NetworkCommand.Response response) throws JSONException {
        JSONObject jSONObject = new JSONObject(response.getRespString());
        return new Result(jSONObject.getInt(MailThread.COL_NAME_LENGTH), jSONObject.getInt(WebOrder.STATUS_WAIT), jSONObject.getString("phone"));
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new SmsCodeDelegate();
    }

    @Override // ru.mail.network.NetworkCommand
    @NonNull
    protected String getPathTag() {
        return "phone_auth";
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
            return getResultFromResponse(response);
        } catch (JSONException e10) {
            LOG.e("Error parsing response " + e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
