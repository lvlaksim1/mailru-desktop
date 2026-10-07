package ru.mail.auth.request;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.FilteringStrategy;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@UrlPath(pathSegments = {"cgi-bin", "auth"})
public class HttpsAuthorizeLoginCommand extends AuthorizeRequestCommand<Params, TsaCookieResult> {
    private static final Log LOG = Log.getLog("HttpsAuthorizeLoginCommand");
    protected static final String TSA_COOKIE_NAME = "tsa";
    public static final Formats.ParamFormat TSA_COOKIE_FORMAT = Formats.newUrlFormat(TSA_COOKIE_NAME);

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public interface TsaCookieResult extends AuthorizeResult {
        String getLogin();

        String getTsaCookie();
    }

    public HttpsAuthorizeLoginCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    public static FilteringStrategy.Constraint getConstraint() {
        return Constraints.newParamNamedConstraint(TSA_COOKIE_FORMAT);
    }

    private TsaCookieResult wrapWithTsaCookie(final String str, final String str2, final AuthorizeResult authorizeResult) {
        return new TsaCookieResult() { // from class: ru.mail.auth.request.HttpsAuthorizeLoginCommand.1
            @Override // ru.mail.auth.request.AuthorizeResult
            public <T> T accept(AuthorizeResult.AuthorizeResultVisitor<T> authorizeResultVisitor) {
                return (T) authorizeResult.accept(authorizeResultVisitor);
            }

            @Override // ru.mail.auth.request.HttpsAuthorizeLoginCommand.TsaCookieResult
            public String getLogin() {
                return str2;
            }

            @Override // ru.mail.auth.request.HttpsAuthorizeLoginCommand.TsaCookieResult
            public String getTsaCookie() {
                return str;
            }
        };
    }

    @Override // ru.mail.network.NetworkCommand
    protected String getLogin() {
        if (getParams() != null) {
            return getParams().getLogin();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public TsaCookieResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        AuthorizeResult authResult = getAuthResult(response);
        NetworkService networkService = getNetworkService();
        Formats.ParamFormat paramFormat = TSA_COOKIE_FORMAT;
        String strExtractCookie = SingleRequest.extractCookie(networkService, TSA_COOKIE_NAME, paramFormat);
        LOG.d("new Tsa cookie = " + paramFormat.getFormattedMsg(strExtractCookie) + " for " + getLogin());
        return wrapWithTsaCookie(strExtractCookie, getLogin(), authResult);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Keep
    public static class Params extends AuthorizeRequestCommand.Params {
        private static final String PARAM_KEY_COOKIE = "Cookie";
        private static final String PARAM_KEY_PASSWORD = "Password";
        private static final String PARAM_KEY_RESET_PASSWORD = "reset_password";

        @Param(getterName = "getCookie", method = HttpMethod.HEADER_ADD, name = "Cookie", useGetter = true)
        private String mCookie;

        @Nullable
        @Param(method = HttpMethod.POST, type = Param.Type.COMPLEX_OBJECT)
        private Map<String, String> mLudwig;

        @Param(method = HttpMethod.POST, name = PARAM_KEY_PASSWORD)
        private String mPassword;
        private final String mTsaCookie;

        @Keep
        @Param(method = HttpMethod.POST, name = PARAM_KEY_RESET_PASSWORD)
        private int resetPassword;

        public Params(Context context, String str, String str2, String str3, @Nullable Map<String, String> map, boolean z10, boolean z11) {
            super(context, str, z10);
            this.mPassword = str2;
            this.mTsaCookie = str3;
            this.mLudwig = map;
            this.resetPassword = z11 ? 1 : 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Params params = (Params) obj;
                String str = this.mPassword;
                if (str == null ? params.mPassword != null : !str.equals(params.mPassword)) {
                    return false;
                }
                String str2 = this.mTsaCookie;
                String str3 = params.mTsaCookie;
                if (str2 != null) {
                    return str2.equals(str3);
                }
                if (str3 == null) {
                    return true;
                }
            }
            return false;
        }

        public String getCookie() {
            String str;
            if (TextUtils.isEmpty(this.mTsaCookie)) {
                str = null;
            } else {
                str = "tsa=" + this.mTsaCookie;
            }
            HttpsAuthorizeLoginCommand.LOG.d("cookie from params = " + str);
            return str;
        }

        public int hashCode() {
            String str = this.mPassword;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.mTsaCookie;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public Params(Context context, String str, String str2, String str3, @Nullable Map<String, String> map) {
            super(context, str, false);
            this.mPassword = str2;
            this.mTsaCookie = str3;
            this.mLudwig = map;
        }
    }
}
