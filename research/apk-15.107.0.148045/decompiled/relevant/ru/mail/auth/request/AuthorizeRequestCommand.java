package ru.mail.auth.request;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.Authenticator.R;
import ru.mail.analytics.QrLoginAnalytics;
import ru.mail.auth.Analytics;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.AuthenticatorEntryPoint;
import ru.mail.auth.DoregistrationParameter;
import ru.mail.auth.TokenParser;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.auth.request.AuthorizeResult;
import ru.mail.authorizesdk.data.request.common.PostRequest;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.credentialsexchanger.analytics.AnalyticsConstants;
import ru.mail.credentialsexchanger.data.network.MailOAuthRequest;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NoAuthInfo;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.FilteringStrategy;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class AuthorizeRequestCommand<P, T extends AuthorizeResult> extends PostRequest<P, T> {
    private static final String ACCESS_TOKEN = "access_token";
    private static final String Ok0_ERROR = "Ok=0";
    private static final String REFRESH_TOKEN = "refresh_token";
    private final Analytics mAnalytics;
    private static final Log LOG = Log.getLog("AuthorizeRequestCommand");
    private static final String MPOP_COOKIE_NAME = "Mpop";
    public static final Formats.ParamFormat MPOP_COOKIE_FORMAT = Formats.newUrlFormat(MPOP_COOKIE_NAME);
    private static final Formats.ParamFormat MPOP_JSON_FORMAT = Formats.newJsonFormat("mpop");
    private static final Formats.ParamFormat ACCESS_TOKEN_FORMAT = Formats.newUrlFormat("access_token");
    private static final Formats.ParamFormat ACCESS_TOKEN_JSON_FORMAT = Formats.newJsonFormat("access_token");
    private static final Formats.ParamFormat REFRESH_TOKEN_JSON_FORMAT = Formats.newJsonFormat("refresh_token");

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public class AuthorizeDelegate extends NetworkCommand.NetworkCommandBaseDelegate {
        private static final String HEADER_CAPTCHA_REQUIRED = "X-SWA-CAPTCHA-REQUIRED";
        private static final String HEADER_EXTERNAL_ACCOUNT_REGISTRATION_ID = "X-SWA-UKEY";
        private static final String HEADER_IMAP_SETTINGS = "X-SWA-IMAPSETTINGS";
        private static final String HEADER_STATUS = "X-SWA-STATUS";

        public AuthorizeDelegate() {
            super();
        }

        private String getLudwigToken(String str) {
            try {
                return new JSONObject(str).getString("LudwigToken");
            } catch (JSONException e10) {
                AuthorizeRequestCommand.LOG.e("LudwigToken parsing exception " + e10);
                return null;
            }
        }

        private String getRegistrationId() {
            return AuthorizeRequestCommand.this.getNetworkService().getHeaderField(HEADER_EXTERNAL_ACCOUNT_REGISTRATION_ID);
        }

        private int getSWAStatus(String str) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e10) {
                AuthorizeRequestCommand.LOG.e("SWA status parsing exception " + e10);
                return -1;
            }
        }

        private String getSecondStepUrl(String str) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getString(AnalyticsConstants.KEY.STATUS).equalsIgnoreCase("ok")) {
                    return Uri.parse(jSONObject.getString(QrLoginAnalytics.Actions.Continue)).buildUpon().appendQueryParameter(PreferenceHostProvider.URL_PARAM_CLIENT, "mobile.app").build().toString();
                }
                return null;
            } catch (Exception e10) {
                AuthorizeRequestCommand.LOG.e("Second step url parsing exception " + e10);
                return null;
            }
        }

        private boolean hasOkStatus(String str) {
            try {
                return new JSONObject(str).optString("status", "").equalsIgnoreCase("ok");
            } catch (JSONException unused) {
                return false;
            }
        }

        private boolean isContainsExternalAccountRegistrationHeaders() {
            return (AuthorizeRequestCommand.this.getNetworkService().getHeaderField(HEADER_STATUS) == null || getRegistrationId() == null || AuthorizeRequestCommand.this.getNetworkService().getHeaderField(HEADER_CAPTCHA_REQUIRED) == null) ? false : true;
        }

        private boolean isOk0Error(String str) {
            return str.toLowerCase().contains(AuthorizeRequestCommand.Ok0_ERROR.toLowerCase());
        }

        private boolean isRequireCaptcha() {
            String headerField = AuthorizeRequestCommand.this.getNetworkService().getHeaderField(HEADER_CAPTCHA_REQUIRED);
            return !TextUtils.isEmpty(headerField) && headerField.trim().equals("1");
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            return (str.contains("Ok=1") || hasOkStatus(str)) ? String.valueOf(200) : "-1";
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            String respString = response.getRespString();
            String headerField = AuthorizeRequestCommand.this.getNetworkService().getHeaderField(HEADER_STATUS);
            if (TextUtils.isEmpty(headerField)) {
                if (isOk0Error(respString)) {
                    AuthorizeRequestCommand.this.mAnalytics.failedCgiBinAuth(AuthorizeRequestCommand.Ok0_ERROR);
                    return new NetworkCommandStatus.ERROR_INVALID_LOGIN();
                }
                AuthorizeRequestCommand.this.mAnalytics.failedCgiBinAuth("unknown");
                return new CommandStatus.ERROR(-1);
            }
            int sWAStatus = getSWAStatus(headerField);
            AuthorizeRequestCommand.this.mAnalytics.failedCgiBinAuth(String.valueOf(sWAStatus));
            if (sWAStatus == 601) {
                return new AuthCommandStatus.TWO_FACTOR_BIND_FORBIDDEN(new AuthCommandStatus.TWO_FACTOR_BIND_FORBIDDEN.Params(sWAStatus, AuthorizeRequestCommand.this.getNetworkService().getHeaderField("x-swa-social-type")));
            }
            if (sWAStatus == 706) {
                return isOk0Error(respString) ? new CommandStatus.ERROR_WITH_STATUS_CODE(sWAStatus, true) : new CommandStatus.ERROR_WITH_STATUS_CODE(sWAStatus);
            }
            if (sWAStatus == 708) {
                return new AuthCommandStatus.MAIL_SERVER_SETTINGS_REQUIRED(Boolean.valueOf(isRequireCaptcha()));
            }
            if (sWAStatus == 710 || sWAStatus == 714) {
                return new CommandStatus.ERROR_WITH_STATUS_CODE(sWAStatus);
            }
            if (sWAStatus == 723) {
                String headerField2 = AuthorizeRequestCommand.this.getNetworkService().getHeaderField(HEADER_IMAP_SETTINGS);
                if (headerField2 == null) {
                    headerField2 = "";
                }
                return new AuthCommandStatus.ERROR_WITH_IMAP_SETTINGS(headerField2);
            }
            if (sWAStatus == 801) {
                if (!isContainsExternalAccountRegistrationHeaders()) {
                    return new CommandStatus.ERROR(Integer.valueOf(sWAStatus));
                }
                return new AuthCommandStatus.EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED(DoregistrationParameter.builder().setRegId(getRegistrationId()).setCaptchaRequired(isRequireCaptcha()).build());
            }
            if (sWAStatus == 802) {
                return new AuthCommandStatus.OAUTH_REQUIRED(AuthorizeRequestCommand.this.getUserAgent());
            }
            if (sWAStatus == 805) {
                return new AuthCommandStatus.OAUTH_OUTLOOK_REQUIRED(AuthorizeRequestCommand.this.getUserAgent());
            }
            if (sWAStatus == 806) {
                return new AuthCommandStatus.MRIM_DISABLED();
            }
            switch (sWAStatus) {
                case 616:
                    return new AuthCommandStatus.EXTERNAL_AUTH_PROHIBIT();
                case 617:
                    return new CommandStatus.ERROR_WITH_STATUS_CODE(sWAStatus);
                case 618:
                    return new CommandStatus.ERROR_WITH_STATUS_CODE(sWAStatus);
                default:
                    switch (sWAStatus) {
                        case 808:
                            String secondStepUrl = getSecondStepUrl(respString);
                            String ludwigToken = getLudwigToken(respString);
                            return (TextUtils.isEmpty(secondStepUrl) && TextUtils.isEmpty(ludwigToken)) ? new CommandStatus.ERROR_WITH_STATUS_CODE(sWAStatus) : new AuthCommandStatus.MAIL_SECOND_STEP_REQUIRED(new AuthCommandStatus.MAIL_SECOND_STEP_REQUIRED.SecondStepParams(secondStepUrl, AuthorizeRequestCommand.this.getNetworkService().getHeaderField("Set-cookie"), ludwigToken));
                        case 809:
                            return new AuthCommandStatus.OAUTH_YANDEX_REQUIRED(AuthorizeRequestCommand.this.getUserAgent());
                        case 810:
                            return new AuthCommandStatus.OAUTH_YAHOO_REQUIRED(AuthorizeRequestCommand.this.getUserAgent());
                        default:
                            return new CommandStatus.ERROR_WITH_STATUS_CODE(sWAStatus);
                    }
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return new CommandStatus.ERROR();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class MpopCookieResult implements AuthorizeResult {
        private String mMpopCookie;
        private String mSecurityTokens;

        public MpopCookieResult(String str) {
            this.mMpopCookie = str;
        }

        @Override // ru.mail.auth.request.AuthorizeResult
        public <T> T accept(AuthorizeResult.AuthorizeResultVisitor<T> authorizeResultVisitor) {
            return authorizeResultVisitor.visit(this);
        }

        public String getMpopCookie() {
            return this.mMpopCookie;
        }

        public String getSecurityTokens() {
            return this.mSecurityTokens;
        }

        public void setSecurityTokens(String str) {
            this.mSecurityTokens = str;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class OAuthTokensResult implements AuthorizeResult {
        private String mAccessToken;
        private String mRefreshToken;

        public OAuthTokensResult(String str, String str2) {
            this.mAccessToken = str;
            this.mRefreshToken = str2;
        }

        @Override // ru.mail.auth.request.AuthorizeResult
        public <T> T accept(AuthorizeResult.AuthorizeResultVisitor<T> authorizeResultVisitor) {
            return authorizeResultVisitor.visit(this);
        }

        public String getAccessToken() {
            return this.mAccessToken;
        }

        public String getRefreshToken() {
            return this.mRefreshToken;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class Params {

        @Keep
        @Param(method = HttpMethod.POST, name = PARAM_KEY_MOBILE)
        private static final int MOBILE = 1;

        @Keep
        @Param(method = HttpMethod.POST, name = PARAM_KEY_MOB_JSON)
        private static final int MOB_JSON = 1;
        private static final String PARAM_KEY_BIND_TOKEN = "bind_token";
        private static final String PARAM_KEY_LOGIN = "Login";
        private static final String PARAM_KEY_MOBILE = "mobile";
        private static final String PARAM_KEY_MOBILE_HEADER = "X-Mobile-App";
        private static final String PARAM_KEY_MOB_JSON = "mob_json";
        private static final String PARAM_KEY_NO_EXTERNAL_FLOW = "no_external_flow";
        private static final String PARAM_KEY_OAUTH2 = "oauth2";
        private static final String PARAM_KEY_SIMPLE = "simple";
        private static final String PARAM_KEY_USERAGENT = "useragent";

        @Keep
        @Param(method = HttpMethod.POST, name = PARAM_KEY_SIMPLE)
        private static final int SIMPLE = 1;
        private static final String USERAGENT = "android";

        @Keep
        @Param(method = HttpMethod.POST, name = PARAM_KEY_USERAGENT)
        private static final String USER_AGENT = "android";

        @Param(method = HttpMethod.POST, name = PARAM_KEY_LOGIN)
        private String mLogin;

        @Keep
        @Param(method = HttpMethod.HEADER_ADD, name = PARAM_KEY_MOBILE_HEADER)
        private String mMobileAppHeader;

        @Keep
        @Param(method = HttpMethod.POST, name = PARAM_KEY_NO_EXTERNAL_FLOW)
        private int mNoExternalFlow;

        @Keep
        @Param(method = HttpMethod.POST, name = PARAM_KEY_OAUTH2)
        private final int OAUTH2 = AuthenticatorConfig.getInstance().isOAuthEnabled() ? 1 : 0;

        @Keep
        @Param(method = HttpMethod.POST, name = PARAM_KEY_BIND_TOKEN)
        private final String BIND_TOKEN = SocialLoginInfoHolder.getBindToken();

        public Params(Context context, String str, boolean z10) {
            this.mLogin = str;
            this.mMobileAppHeader = context.getString(R.string.auth_csrf_header);
            this.mNoExternalFlow = z10 ? 1 : 0;
        }

        public String getLogin() {
            return this.mLogin;
        }
    }

    public AuthorizeRequestCommand(Context context, P p10) {
        this(context, (Object) p10, false);
    }

    public static List<FilteringStrategy.Constraint> getConstraints() {
        return Arrays.asList(Constraints.newParamNamedConstraint(ACCESS_TOKEN_JSON_FORMAT), Constraints.newParamNamedConstraint(REFRESH_TOKEN_JSON_FORMAT), Constraints.newParamNamedConstraint(MPOP_COOKIE_FORMAT), Constraints.newParamNamedConstraint(MPOP_JSON_FORMAT), Constraints.newParamNamedConstraint(MpopTokenRequest.TOKEN_FORMAT), Constraints.newParamNamedConstraint(MpopTokenRequest.TOKEN_JSON_FORMAT));
    }

    protected final AuthorizeResult getAuthResult(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return isOAuthEnabled() ? getOAuthCredentialsResult(response) : getMpopCookieResult(response);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand.NetworkCommandBaseDelegate getCustomDelegate() {
        return new AuthorizeDelegate();
    }

    protected MpopCookieResult getMpopCookieResult(NetworkCommand.Response response) {
        MpopCookieResult mpopCookieResult = new MpopCookieResult(SingleRequest.extractCookie(getNetworkService(), MPOP_COOKIE_NAME, MPOP_COOKIE_FORMAT, MPOP_JSON_FORMAT, MpopTokenRequest.TOKEN_FORMAT, MpopTokenRequest.TOKEN_JSON_FORMAT, ACCESS_TOKEN_FORMAT, ACCESS_TOKEN_JSON_FORMAT));
        try {
            mpopCookieResult.setSecurityTokens(new JSONArray(response.getRespString()).getString(3));
            return mpopCookieResult;
        } catch (JSONException e10) {
            LOG.e("Unable to parse security tokens " + e10);
            return mpopCookieResult;
        }
    }

    @Override // ru.mail.authorizesdk.data.request.common.PostRequest, ru.mail.authorizesdk.data.request.common.SingleRequest, ru.mail.network.NetworkCommand
    public NoAuthInfo getNoAuthInfo() {
        return null;
    }

    protected OAuthTokensResult getOAuthCredentialsResult(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject(MailOAuthRequest.BODY_KEY);
            return new OAuthTokensResult(jSONObject.getString("access_token"), jSONObject.getString("refresh_token"));
        } catch (JSONException e10) {
            LOG.e("Unable to parse oauth tokens " + e10);
            return new OAuthTokensResult("", "");
        }
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new JsonStatusResponseProcessor(response, networkCommandBaseDelegate);
    }

    protected boolean isOAuthEnabled() {
        return AuthenticatorConfig.getInstance().isOAuthEnabled();
    }

    @Override // ru.mail.network.NetworkCommand
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newJsonFormat("access_token"), Formats.newUrlFormat("access_token"), Formats.newJsonFormat("refresh_token"), Formats.newUrlFormat("refresh_token"), Formats.newJsonFormat(MPOP_COOKIE_NAME), Formats.newUrlFormat(MPOP_COOKIE_NAME));
        logFilterPrepareTokenFilter.addTokenConstraints((FilteringStrategy.Constraint[]) TokenParser.getConstraints().toArray(new FilteringStrategy.Constraint[0]));
        return logFilterPrepareTokenFilter;
    }

    @Override // ru.mail.authorizesdk.data.request.common.SingleRequest, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    public AuthorizeRequestCommand(Context context, P p10, HostProvider hostProvider) {
        this(context, p10, hostProvider, false);
    }

    public AuthorizeRequestCommand(Context context, P p10, boolean z10) {
        super(context, p10, z10);
        this.mAnalytics = AuthenticatorEntryPoint.analytics(context);
    }

    public AuthorizeRequestCommand(Context context, P p10, HostProvider hostProvider, boolean z10) {
        super(context, p10, hostProvider, z10);
        this.mAnalytics = AuthenticatorEntryPoint.analytics(context);
    }
}
