package ru.mail.auth.request;

import android.content.Context;
import androidx.annotation.Keep;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.util.AccountInfoUtilsKt;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.data.cmd.server.AuthCommandStatus;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.util.log.Constraints;
import ru.mail.util.log.FilteringStrategy;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "tokens"})
public class MpopTokenRequest extends SingleRequest<Params, Result> {
    private static final Log LOG = Log.getLog("MpopTokenRequest");
    public static final Formats.ParamFormat TOKEN_FORMAT = Formats.newUrlFormat("token");
    public static final Formats.ParamFormat TOKEN_JSON_FORMAT = Formats.newJsonFormat("token");
    public static final Formats.ParamFormat MPOP_FORMAT = Formats.newUrlFormat("Mpop");
    public static final Formats.ParamFormat MPOP_JSON_FORMAT = Formats.newJsonFormat("Mpop");

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public class MpopTokenDelegate extends NetworkCommand<Params, Result>.NetworkCommandBaseDelegate {
        public MpopTokenDelegate() {
            super();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            try {
                return new JSONObject(str).getString("status");
            } catch (JSONException e10) {
                MpopTokenRequest.LOG.e("JSON exception while parsing response from the server", e10);
                return "Error while parsing response " + e10.getMessage();
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            try {
                if (new JSONObject(response.getRespString()).get("status").equals(403)) {
                    return new AuthCommandStatus.ERROR_INVALID_LOGIN();
                }
            } catch (JSONException e10) {
                MpopTokenRequest.LOG.e("Unable to pasrse response " + e10);
            }
            return super.onError(response);
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            return new CommandStatus.ERROR();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class Params {
        private static final String PARAM_KEY_COOKIE = "Cookie";
        private static final String PARAM_KEY_EMAIL = "email";

        @Keep
        @Param(method = HttpMethod.GET, name = AccountInfoUtilsKt.PARAM_KEY_ACT_MODE)
        private final String mActMode;

        @Param(method = HttpMethod.HEADER_SET, name = "Cookie")
        private final String mCookie;

        @Param(method = HttpMethod.GET, name = "email")
        private final String mEmail;

        public Params(@NotNull AccountInfo accountInfo, @NotNull String str) {
            this.mEmail = accountInfo.getLogin();
            this.mCookie = str;
            this.mActMode = AccountInfoUtilsKt.getActiveMode(accountInfo);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    public static class Result {
        private final String mMpopToken;

        public Result(String str) {
            this.mMpopToken = str;
        }

        public String getMpopToken() {
            return this.mMpopToken;
        }
    }

    public MpopTokenRequest(@NotNull Context context, @NotNull HostProvider hostProvider, @NotNull String str, @NotNull AccountInfo accountInfo, @NotNull boolean z10) {
        super(context, new Params(accountInfo, str), hostProvider, z10);
    }

    public static List<FilteringStrategy.Constraint> getConstraints() {
        return Arrays.asList(Constraints.newParamNamedConstraint(TOKEN_FORMAT), Constraints.newParamNamedConstraint(TOKEN_JSON_FORMAT));
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new MpopTokenDelegate();
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new JsonStatusResponseProcessor(response, networkCommandBaseDelegate);
    }

    @Override // ru.mail.network.NetworkCommand
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(MPOP_FORMAT, MPOP_JSON_FORMAT);
        return logFilterPrepareTokenFilter;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            String string = new JSONObject(response.getRespString()).getJSONObject("body").getString("token");
            Log.addConstraint(Constraints.newFormatViolationConstraint(string, TOKEN_FORMAT, TOKEN_JSON_FORMAT));
            return new Result(string);
        } catch (JSONException e10) {
            LOG.e("Error while parsing response " + e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
