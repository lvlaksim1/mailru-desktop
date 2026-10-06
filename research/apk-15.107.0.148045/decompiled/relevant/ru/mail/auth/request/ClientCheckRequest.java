package ru.mail.auth.request;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.credentialsexchanger.data.network.MailOAuthRequest;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.deeplink.InternalDeeplinkNavigation;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@HostProviderAnnotation(defHostStrRes = "string/oauth_default_host", defSchemeStrRes = "string/oauth_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = MailOAuthRequest.BODY_KEY)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", PreferenceHostProvider.URL_PARAM_CLIENT, "check"})
public class ClientCheckRequest extends NetworkCommand<Params, Boolean> {

    /* JADX INFO: compiled from: ProGuard */
    private class ClientCheckDelegate extends NetworkCommand<Params, Boolean>.NetworkCommandBaseDelegate {
        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            try {
                return new JSONObject(str).getString("status");
            } catch (JSONException unused) {
                return String.valueOf(400);
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return new CommandStatus.ERROR();
        }

        private ClientCheckDelegate() {
            super();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {

        @Param(method = HttpMethod.GET, name = HiAnalyticsConstant.HaKey.BI_KEY_FINGERPRINT)
        private final String mFingerprint;

        @Param(method = HttpMethod.GET, name = "id")
        private final String mId;

        @Param(method = HttpMethod.GET, name = "app_id")
        private final String mPackageName;

        public Params(String str, String str2, String str3) {
            this.mId = str;
            this.mPackageName = str2;
            this.mFingerprint = str3;
        }
    }

    public ClientCheckRequest(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected HostProvider createHostProvider(HostProviderAnnotation hostProviderAnnotation) {
        return new PreferenceHostProvider(getContext(), hostProviderAnnotation, (Bundle) null, new HostProvider.Configuration() { // from class: ru.mail.auth.request.ClientCheckRequest.1
            @Override // ru.mail.network.HostProvider.Configuration
            public boolean needPlatformParams() {
                return false;
            }

            @Override // ru.mail.network.HostProvider.Configuration
            public boolean needSign() {
                return false;
            }

            @Override // ru.mail.network.HostProvider.Configuration
            public boolean needUserAgent() {
                return false;
            }
        });
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Boolean>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ClientCheckDelegate();
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Boolean>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new JsonStatusResponseProcessor(response, networkCommandBaseDelegate);
    }

    @Override // ru.mail.network.NetworkCommand
    protected ServerApi getServerApi() {
        return new SingleRequest.DefaultServerApi();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public Boolean onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            if (jSONObject.getInt("status") == 200) {
                return Boolean.valueOf(jSONObject.getJSONObject("body").getBoolean(InternalDeeplinkNavigation.SCHEME));
            }
            throw new NetworkCommand.PostExecuteException("not OK status");
        } catch (JSONException e10) {
            Log.getLog("ClientCheckRequest").e("Error parsing", e10);
            return Boolean.FALSE;
        }
    }
}
