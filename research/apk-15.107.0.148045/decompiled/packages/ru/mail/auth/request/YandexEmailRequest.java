package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.ads.kotlett.simple.domain.KotlettAdFeatureParamsMapper;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@HostProviderAnnotation(defHostStrRes = "string/yandex_api_default_host", defSchemeStrRes = "string/yandex_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = "yandex_get_email")
@UrlPath(pathSegments = {XmailMigrationPromoSheet.BUTTON_INFO})
public class YandexEmailRequest extends GetEmailRequest<Params> {
    private static final Log LOG = Log.getLog("YandexEmailRequest");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {

        @Keep
        @Param(method = HttpMethod.GET, name = KotlettAdFeatureParamsMapper.KEY_FORMAT)
        private static final String FORMAT = "json";

        @Param(method = HttpMethod.HEADER_SET, name = "Authorization")
        private final String mAuthorization;

        public Params(String str) {
            this.mAuthorization = "OAuth " + str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mAuthorization.equals(((Params) obj).mAuthorization);
        }

        public int hashCode() {
            return this.mAuthorization.hashCode();
        }
    }

    public YandexEmailRequest(Context context, String str) {
        super(context, new Params(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public GetEmailRequest.Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            LOG.v("get yandex email resp:" + response);
            JSONArray jSONArray = new JSONObject(response.getRespString()).getJSONArray("emails");
            return jSONArray.length() > 0 ? new GetEmailRequest.Result((String) jSONArray.get(0)) : new GetEmailRequest.Result("");
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
