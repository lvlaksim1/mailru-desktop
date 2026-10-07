package ru.mail.registration.request;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.registration.request.RegServerIdRequest.ExtendableAuthParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public abstract class RegServerIdRequest<P extends ExtendableAuthParams> extends RegServerRequest<P, TokenResponse> {
    private static final String JSON_KEY_ADDITIONAL = "additional";
    private static final String JSON_KEY_BODY = "body";
    private static final String JSON_KEY_CAPTCHA = "captcha";
    private static final String JSON_KEY_OPTIONS = "options";
    private static final String JSON_KEY_SITEKEY = "sitekey";
    private static final String JSON_KEY_TOKEN = "token";
    private static final String JSON_KEY_TOKEN_CHECKED = "token_checked";
    private static final String JSON_KEY_TYPE = "type";
    private final Log mLog;

    /* JADX INFO: compiled from: ProGuard */
    public interface ExtendableAuthParams {
        boolean isExtendedAuth();
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class TokenResponse {

        @Nullable
        private Captcha mCaptcha;
        private String mRegToken;

        @Nullable
        private String mSiteKey;
        private boolean mTokenChecked;

        /* JADX INFO: compiled from: ProGuard */
        public enum Captcha {
            BASE,
            RECAPTCHA;

            /* JADX INFO: Access modifiers changed from: private */
            public static Captcha from(String str) {
                for (Captcha captcha : values()) {
                    if (captcha.name().equalsIgnoreCase(str)) {
                        return captcha;
                    }
                }
                return null;
            }
        }

        TokenResponse(String str, @Nullable Captcha captcha, @Nullable String str2, boolean z10) {
            this.mRegToken = str;
            this.mCaptcha = captcha;
            this.mSiteKey = str2;
            this.mTokenChecked = z10;
        }

        @Nullable
        public Captcha getCaptcha() {
            return this.mCaptcha;
        }

        public String getRegToken() {
            return this.mRegToken;
        }

        @Nullable
        public String getSiteKey() {
            return this.mSiteKey;
        }

        public boolean isTokenChecked() {
            return this.mTokenChecked;
        }
    }

    public RegServerIdRequest(Context context, P p10, HostProvider hostProvider, boolean z10) {
        super(context, p10, hostProvider, z10);
        this.mLog = Log.getLog("RegServerIdRequest");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public TokenResponse onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        String string;
        String string2;
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            TokenResponse.Captcha captcha = null;
            boolean zOptBoolean = false;
            if (getParams().isExtendedAuth()) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("body");
                string = jSONObject2.getString("token");
                JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject(JSON_KEY_ADDITIONAL);
                if (jSONObjectOptJSONObject != null) {
                    zOptBoolean = jSONObjectOptJSONObject.optBoolean(JSON_KEY_TOKEN_CHECKED);
                    JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(JSON_KEY_CAPTCHA);
                    if (jSONObjectOptJSONObject2 != null) {
                        TokenResponse.Captcha captchaFrom = TokenResponse.Captcha.from(jSONObjectOptJSONObject2.optString("type"));
                        string2 = captchaFrom == TokenResponse.Captcha.RECAPTCHA ? jSONObjectOptJSONObject2.getJSONObject(JSON_KEY_OPTIONS).getString(JSON_KEY_SITEKEY) : null;
                        captcha = captchaFrom;
                    }
                }
                return new TokenResponse(string, captcha, string2, zOptBoolean);
            }
            string = jSONObject.getString("body");
            string2 = null;
            return new TokenResponse(string, captcha, string2, zOptBoolean);
        } catch (JSONException e10) {
            this.mLog.e("Error parsing response " + e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    public RegServerIdRequest(Context context, P p10, boolean z10) {
        this(context, p10, null, z10);
    }
}
