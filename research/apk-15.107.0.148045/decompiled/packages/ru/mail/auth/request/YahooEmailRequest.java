package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.ads.kotlett.simple.domain.KotlettAdFeatureParamsMapper;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@HostProviderAnnotation(defHostStrRes = "string/yahoo_social_api_default_host", defSchemeStrRes = "string/yahoo_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = "yahoo_get_email")
@UrlPath(pathSegments = {"openid", "v1", "userinfo"})
public class YahooEmailRequest extends GetEmailRequest<Params> {
    private static final Log LOG = Log.getLog("GoogleGetEmailRequest");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends AuthorizationHeaderParams {

        @Keep
        @Param(method = HttpMethod.GET, name = KotlettAdFeatureParamsMapper.KEY_FORMAT)
        private static final String FORMAT = "json";

        public Params(String str) {
            super(str);
        }
    }

    public YahooEmailRequest(Context context, String str) {
        super(context, new Params(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public GetEmailRequest.Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            LOG.v("get yahoo email resp:" + response);
            return new GetEmailRequest.Result(new JSONObject(response.getRespString()).getString("email"));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
