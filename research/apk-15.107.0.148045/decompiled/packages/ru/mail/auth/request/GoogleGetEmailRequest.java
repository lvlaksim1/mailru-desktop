package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@HostProviderAnnotation(defHostStrRes = "string/google_api_default_host", defSchemeStrRes = "string/google_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = "google_apis")
@UrlPath(pathSegments = {"oauth2", "v1", "userinfo"})
public class GoogleGetEmailRequest extends GetEmailRequest<Params> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends AuthorizationHeaderParams {
        public Params(String str) {
            super(str);
        }
    }

    public GoogleGetEmailRequest(Context context, String str) {
        super(context, new Params(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public GetEmailRequest.Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            return new GetEmailRequest.Result(new JSONObject(response.getRespString()).getString("email"));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
