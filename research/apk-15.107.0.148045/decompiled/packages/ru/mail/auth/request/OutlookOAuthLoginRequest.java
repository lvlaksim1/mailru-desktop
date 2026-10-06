package ru.mail.auth.request;

import android.content.Context;
import android.text.TextUtils;
import ru.mail.OauthParams;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
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
@UrlPath(pathSegments = {"oauth2_outlook_token"})
public class OutlookOAuthLoginRequest extends BaseOAuthLoginRequest<Params> {
    private static final Log LOG = Log.getLog("OutlookOAuthLoginRequest");

    /* JADX INFO: compiled from: ProGuard */
    private class OutlookOAuthDelegate extends NetworkCommand<Params, BaseOAuthLoginRequest.Result>.NetworkCommandBaseDelegate {
        private static final String HEADER_STATUS = "X-SWA-STATUS";

        private int getSWAStatus(String str) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e10) {
                OutlookOAuthLoginRequest.LOG.e("SWA status parsing exception " + e10);
                return -1;
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            String headerField = OutlookOAuthLoginRequest.this.getNetworkService().getHeaderField(HEADER_STATUS);
            if (TextUtils.isEmpty(headerField)) {
                return String.valueOf(200);
            }
            return getSWAStatus(headerField) == 200 ? String.valueOf(200) : "-1";
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            return OutlookOAuthLoginRequest.this.getNetworkService().getHeaderField(HEADER_STATUS) != null ? new CommandStatus.ERROR_WITH_STATUS_CODE(getSWAStatus(OutlookOAuthLoginRequest.this.getNetworkService().getHeaderField(HEADER_STATUS))) : super.onError(response);
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return null;
        }

        private OutlookOAuthDelegate() {
            super();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends BaseOAuthLoginRequest.Params {
        private static final String PARAM_KEY_LOGIN = "login";
        private static final String PARAM_KEY_REDIRECT_URI = "redirect_uri";

        @Param(method = HttpMethod.GET, name = "login")
        private final String mLogin;

        @Param(method = HttpMethod.GET, name = "redirect_uri")
        private final String mRedirectUri;

        public Params(Context context, OauthParams oauthParams, String str, String str2) {
            super(context, oauthParams, str);
            this.mLogin = str2;
            this.mRedirectUri = oauthParams.getRedirectUri();
        }
    }

    public OutlookOAuthLoginRequest(Context context, HostProvider hostProvider, String str, OauthParams oauthParams, String str2, boolean z10) {
        super(context, hostProvider, new Params(context, oauthParams, str, str2), z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, BaseOAuthLoginRequest.Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new JsonStatusResponseProcessor(response, new OutlookOAuthDelegate());
    }
}
