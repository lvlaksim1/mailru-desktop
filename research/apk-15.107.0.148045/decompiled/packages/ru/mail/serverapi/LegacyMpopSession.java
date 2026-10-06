package ru.mail.serverapi;

import android.content.Context;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import org.apache.http.NameValuePair;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.ParamNameValuePair;
import ru.mail.network.service.NetworkService;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.FilteringStrategy;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class LegacyMpopSession extends BaseSessionSetter {
    private static final String DELIMETER = "(?:%3A|%3a|:)";
    private static final String MPOP_HEADER = "Mpop";
    private static final String SECOND_WORD = "[\\w\\d*]+";
    private static final String URI_PARAM_TOKEN = "token";
    private static final String URI_PARAM_FORM_SIGN = "form_sign";
    private static final Formats.ParamFormat FORM_SIGN_URL_FORMAT = Formats.newUrlFormat(URI_PARAM_FORM_SIGN);
    private static final Formats.ParamFormat FORM_SIGN_JSON_FORMAT = Formats.newJsonFormat(URI_PARAM_FORM_SIGN);
    private static final String URI_PARAM_FORM_TOKEN = "form_token";
    private static final Formats.ParamFormat FORM_TOKEN_URL_FORMAT = Formats.newUrlFormat(URI_PARAM_FORM_TOKEN);
    private static final Formats.ParamFormat FORM_TOKEN_JSON_FORMAT = Formats.newJsonFormat(URI_PARAM_FORM_TOKEN);
    private static final Formats.ParamFormat LEFT_PART_TOKEN_URL_FORMAT = new LeftTokenPartUrlFormat();
    private static final Formats.ParamFormat LEFT_PART_TOKEN_JSON_FORMAT = new LeftTokenPartJsonFormat();
    private static final Formats.ParamFormat RIGHT_PART_TOKEN_URL_FORMAT = new RightTokenPartUrlFormat();
    private static final Formats.ParamFormat RIGHT_PART_TOKEN_JSON_FORMAT = new RightTokenPartJsonFormat();

    /* JADX INFO: compiled from: ProGuard */
    public static class LeftTokenPartJsonFormat extends Formats.ParamFormat {
        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getFormattedMsg(Object obj) {
            return getPrefix() + obj + getSuffix();
        }

        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getPrefix() {
            return "\"token\":\"";
        }

        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getSuffix() {
            return "(?:%3A|%3a|:)[\\w\\d*]+\"";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class LeftTokenPartUrlFormat extends Formats.ParamFormat {
        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getFormattedMsg(Object obj) {
            return getPrefix() + obj + getSuffix();
        }

        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getPrefix() {
            return "token=";
        }

        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getSuffix() {
            return "(?:%3A|%3a|:)[\\w\\d*]+";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class RightTokenPartJsonFormat extends Formats.ParamFormat {
        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getFormattedMsg(Object obj) {
            return getPrefix() + obj + getSuffix();
        }

        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getPrefix() {
            return "\"token\":\"[\\w\\d*]+(?:%3A|%3a|:)";
        }

        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getSuffix() {
            return "\"";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class RightTokenPartUrlFormat extends Formats.ParamFormat {
        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getFormattedMsg(Object obj) {
            return getPrefix() + obj + getSuffix();
        }

        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getPrefix() {
            return "token=[\\w\\d*]+(?:%3A|%3a|:)";
        }

        @Override // ru.mail.util.log.Formats.ParamFormat
        public String getSuffix() {
            return "%3F|%26|[,?&;\\}\\s\\n]|$";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    private class SecurityTokenSigner {
        private final String mFormSign;
        private final String mFormToken;
        private final String mTornadoMpopToken;

        String getAuthToken() {
            return this.mTornadoMpopToken;
        }

        public final void sign(Uri.Builder builder) {
            builder.appendQueryParameter(LegacyMpopSession.URI_PARAM_FORM_SIGN, this.mFormSign).appendQueryParameter(LegacyMpopSession.URI_PARAM_FORM_TOKEN, this.mFormToken);
        }

        private SecurityTokenSigner() throws NetworkCommandWithSession.BadSessionException {
            String strPeekAuthToken = LegacyMpopSession.this.getSessionKeeper().peekAuthToken();
            this.mTornadoMpopToken = strPeekAuthToken;
            if (strPeekAuthToken == null) {
                throw new NetworkCommandWithSession.BadSignTokenException("invalid security tokens", LegacyMpopSession.this.getSessionKeeper().getNoAuthInfo());
            }
            String[] strArrSplit = strPeekAuthToken.split(":");
            String str = strArrSplit[0];
            this.mFormSign = str;
            String str2 = strArrSplit[1];
            this.mFormToken = str2;
            Log.addConstraint(Constraints.newFormatViolationConstraint(str, LegacyMpopSession.FORM_SIGN_JSON_FORMAT, LegacyMpopSession.FORM_SIGN_URL_FORMAT, LegacyMpopSession.LEFT_PART_TOKEN_JSON_FORMAT, LegacyMpopSession.LEFT_PART_TOKEN_URL_FORMAT));
            Log.addConstraint(Constraints.newFormatViolationConstraint(str2, LegacyMpopSession.FORM_TOKEN_JSON_FORMAT, LegacyMpopSession.FORM_TOKEN_URL_FORMAT, LegacyMpopSession.RIGHT_PART_TOKEN_JSON_FORMAT, LegacyMpopSession.RIGHT_PART_TOKEN_URL_FORMAT));
        }

        public final void sign(List<NameValuePair> list) {
            list.add(new ParamNameValuePair(LegacyMpopSession.URI_PARAM_FORM_SIGN, this.mFormSign));
            list.add(new ParamNameValuePair(LegacyMpopSession.URI_PARAM_FORM_TOKEN, this.mFormToken));
        }
    }

    public LegacyMpopSession(Context context, BaseSessionSetter.SessionKeeper sessionKeeper, AccountManagerSettings accountManagerSettings) {
        super(context, sessionKeeper, accountManagerSettings);
    }

    public static List<FilteringStrategy.Constraint> getConstraints() {
        return Arrays.asList(Constraints.newParamNamedConstraint(FORM_SIGN_URL_FORMAT), Constraints.newParamNamedConstraint(FORM_SIGN_JSON_FORMAT), Constraints.newParamNamedConstraint(FORM_TOKEN_URL_FORMAT), Constraints.newParamNamedConstraint(FORM_TOKEN_JSON_FORMAT), Constraints.newParamNamedConstraint(LEFT_PART_TOKEN_JSON_FORMAT), Constraints.newParamNamedConstraint(RIGHT_PART_TOKEN_JSON_FORMAT), Constraints.newParamNamedConstraint(LEFT_PART_TOKEN_URL_FORMAT), Constraints.newParamNamedConstraint(RIGHT_PART_TOKEN_URL_FORMAT));
    }

    @Override // ru.mail.network.SessionSetter
    public void cookieSetup(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
        setMpopCookie(networkService);
    }

    @Override // ru.mail.serverapi.BaseSessionSetter
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newUrlFormat(MPOP_HEADER));
        return logFilterPrepareTokenFilter;
    }

    @Override // ru.mail.network.SessionSetter
    public void urlSetup(Uri.Builder builder) throws NetworkCommandWithSession.BadSessionException {
        SecurityTokenSigner securityTokenSigner = new SecurityTokenSigner();
        securityTokenSigner.sign(builder);
        getSessionKeeper().pushAuthToken(securityTokenSigner.getAuthToken());
    }
}
