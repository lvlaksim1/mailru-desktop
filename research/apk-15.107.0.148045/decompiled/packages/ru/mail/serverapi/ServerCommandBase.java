package ru.mail.serverapi;

import android.accounts.Account;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.DefaultTokenPairListener;
import ru.mail.auth.TokenParser;
import ru.mail.locator.Locator;
import ru.mail.logic.navigation.segue.ClickerLinkConstructor;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.AuthCommandCreator;
import ru.mail.network.CommandStatusMessageGenerator;
import ru.mail.network.CommandWithAuthorization;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.NetworkTrafficListener;
import ru.mail.network.NoAuthInfo;
import ru.mail.network.ParamNameValuePair;
import ru.mail.network.PostSignCreator;
import ru.mail.network.ServerApi;
import ru.mail.network.SessionSetter;
import ru.mail.network.requestbody.ParamsRequestBody;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogBuilder;
import ru.mail.util.log.LogFilter;
import ru.mail.utils.analytics.SessionTracker;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public abstract class ServerCommandBase<P extends ServerCommandBaseParams, T> extends NetworkCommandWithSession<P, T> implements CommandWithAuthorization, BaseSessionSetter.SessionKeeper {
    private final Log LOG;
    private final AccountManagerWrapper mAccountManager;
    private final AccountManagerSettings mAccountManagerSettings;
    private final MailAuthCommandCreatorFactory mAuthCommandCreatorFactory;
    private final PlatformInfo mPlatformInfo;
    private final List<CommandStatusMessageGenerator> mPredefinedStatuses;
    private ServerApi mServerApi;
    private SessionSetter mSessionSetter;
    private String mTokenType;
    private final NetworkTrafficListener mTrafficListener;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: compiled from: ProGuard */
    public static class DefaultTrafficListener implements NetworkTrafficListener {

        @NonNull
        private final SessionTracker mTracker;

        public DefaultTrafficListener(@NonNull Context context) {
            this.mTracker = SessionTracker.from(context);
        }

        @NonNull
        protected SessionTracker getTracker() {
            return this.mTracker;
        }

        @Override // ru.mail.network.NetworkTrafficListener
        public void onTrafficReceived(long j10) {
            this.mTracker.sizeOfNewRxViaApi(j10);
        }

        @Override // ru.mail.network.NetworkTrafficListener
        public void onTrafficSent(long j10) {
            this.mTracker.sizeOfNewTxViaApi(j10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public class LegacyDelegate extends ServerCommandBase<P, T>.ServerCommandBaseDelegate {
        public LegacyDelegate() {
            super();
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            try {
                return new JSONArray(str).getString(1);
            } catch (JSONException e10) {
                ServerCommandBase.this.LOG.e("JSON exception while parsing response from the server", e10);
                return "Error while parsing response " + e10.getMessage();
            }
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public void processSignsAndTokens(NetworkCommand.Response response) {
            ServerCommandBase.this.processSignsAndTokens(response);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public abstract class ServerCommandBaseDelegate extends NetworkCommand<P, T>.NetworkCommandBaseDelegate {
        public ServerCommandBaseDelegate() {
            super();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onFolderAccessDenied() {
            ((ServerCommandBaseParams) ServerCommandBase.this.getParams()).getFolderState().clearFolderLogin(((ServerCommandBaseParams) ServerCommandBase.this.getParams()).getFolderState().getFolderId());
            return new NetworkCommandStatus.FOLDER_ACCESS_DENIED(Long.valueOf(((ServerCommandBaseParams) ServerCommandBase.this.getParams()).getFolderState().getFolderId()));
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    public class TornadoDelegate extends ServerCommandBase<P, T>.ServerCommandBaseDelegate {
        public TornadoDelegate() {
            super();
        }

        private String parseResponseField(String str, String str2) {
            try {
                return new JSONObject(str).getString(str2);
            } catch (JSONException e10) {
                ServerCommandBase.this.LOG.e("JSON exception while parsing response from the server", e10);
                return "-1";
            }
        }

        public String getError(String str) {
            return parseResponseField(str, "error");
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        protected String getResponseStatusImpl(String str) {
            return parseResponseField(str, "status");
        }

        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onError(NetworkCommand.Response response) {
            if (response.getStatusCode() != 200) {
                return super.onError(response);
            }
            int i10 = Integer.parseInt(getResponseStatus(response.getRespString()));
            ServerCommandBase.this.LOG.d("json response status code: " + i10);
            return new CommandStatus.ERROR_WITH_STATUS_CODE(i10);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onUnauthorized(String str) {
            if (TextUtils.equals(str, "user")) {
                String login = ((ServerCommandBaseParams) ServerCommandBase.this.getParams()).getLogin();
                return new NetworkCommandStatus.NO_AUTH(new NoAuthInfo(login, ServerCommandBase.this.getAuthCommandCreator(), Authenticator.getAccountManagerWrapper(ServerCommandBase.this.getContext().getApplicationContext()).peekAuthToken(new Account(login, ServerCommandBase.this.mAccountManagerSettings.getAccountType()), "ru.mail")));
            }
            if (TextUtils.equals(str, TornadoResponseProcessor.NO_AUTH_2STEP_REQUIRED)) {
                return new MailCommandStatus.NO_AUTH_TWO_STEP_REQUIRED(ServerCommandBase.this.getNoAuthInfo());
            }
            return TextUtils.equals(str, TornadoResponseProcessor.NO_AUTH_BIND_REQUIRED) ? new MailCommandStatus.NO_AUTH_BIND_REQUIRED(ServerCommandBase.this.getNoAuthInfo()) : super.onUnauthorized(str);
        }
    }

    public ServerCommandBase(Context context, P p10) {
        this(context, p10, (HostProvider) null);
    }

    private void initAuthEntities(Context context) {
        MailAuthorizationApiType apiType = getApiType();
        this.mServerApi = (ServerApi) apiType.create(new MailAuthorizationApiFactory(this.mPlatformInfo, this.mAccountManagerSettings));
        this.mSessionSetter = (SessionSetter) apiType.create(new MailSessionSetterFactory(context, this.mAccountManagerSettings, this));
        this.mTokenType = (String) apiType.create(new MailApiTokenTypeFactory());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSignsAndTokens(NetworkCommand.Response response) {
        String login = getLogin();
        if (login == null) {
            return;
        }
        new TokenParser(new DefaultTokenPairListener(this.mAccountManager, new Account(login, this.mAccountManagerSettings.getAccountType()))).handleSignsAndTokens(response.getRespString());
    }

    @Override // ru.mail.network.NetworkCommandWithSession
    @NonNull
    protected AuthCommandCreator createAuthCommandCreator() {
        return (AuthCommandCreator) getApiType().create(this.mAuthCommandCreatorFactory);
    }

    @Override // ru.mail.network.NetworkCommand
    protected HostProvider createHostProvider(HostProviderAnnotation hostProviderAnnotation) {
        return new MailHostProvider(getContext(), hostProviderAnnotation, null, this.mPlatformInfo);
    }

    protected final MailAuthorizationApiType getApiType() {
        return (isSupportOAuthAuthorization() && (AuthenticatorConfig.getInstance().isOAuthEnabled() || AuthenticatorConfig.getInstance().isSdkOAuthEnabled())) ? MailAuthorizationApiType.TORNADO : getDefaultApiType();
    }

    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.LEGACY;
    }

    @Override // ru.mail.network.NetworkCommand
    protected String getLoggerEventNameInternal() {
        CommandStatus<?> result = getResult();
        Iterator<CommandStatusMessageGenerator> it = this.mPredefinedStatuses.iterator();
        while (it.hasNext()) {
            String strMatch = it.next().match(result);
            if (strMatch != null) {
                return strMatch;
            }
        }
        return super.getLoggerEventNameInternal();
    }

    @Override // ru.mail.network.NetworkCommand
    public String getLogin() {
        if (getParams() != null) {
            return getParams().getLogin();
        }
        return null;
    }

    @Override // ru.mail.network.NetworkCommandWithSession, ru.mail.network.NetworkCommand
    public NoAuthInfo getNoAuthInfo() {
        this.LOG.d("getNoAuthInfo() " + this.mTokenType + StringUtils.SPACE + getClass().getSimpleName());
        return new NoAuthInfo(getParams().getLogin(), getAuthCommandCreator(), getAuthToken());
    }

    @Override // ru.mail.network.NetworkCommand
    protected ServerApi getServerApi() {
        return this.mServerApi;
    }

    public String getTokenType() {
        return this.mTokenType;
    }

    @Override // ru.mail.network.NetworkCommand
    @Nullable
    protected NetworkTrafficListener getTrafficListener() {
        return this.mTrafficListener;
    }

    protected boolean isSupportOAuthAuthorization() {
        return getDefaultApiType() == MailAuthorizationApiType.TORNADO_MPOP;
    }

    protected void logPostParams(List<NameValuePair> list) {
        LogBuilder logBuilder = new LogBuilder("Following POST params will be added to request:\n");
        for (NameValuePair nameValuePair : list) {
            logBuilder.addString(nameValuePair.getName(), nameValuePair.getValue(), true);
        }
        this.LOG.i(filterTokenString(logBuilder.build()));
    }

    @NonNull
    protected DefaultTrafficListener onCreateTrafficListener(Context context) {
        return new DefaultTrafficListener(context);
    }

    @Override // ru.mail.network.NetworkCommand
    @NonNull
    protected RequestBody onPrepareRequestBody() throws IOException {
        List<NameValuePair> listProvidePostParams = providePostParams();
        listProvidePostParams.add(new ParamNameValuePair("md5_post_signature", new PostSignCreator(Uri.EMPTY, listProvidePostParams).create()));
        logPostParams(listProvidePostParams);
        return new ParamsRequestBody(listProvidePostParams, "UTF-8");
    }

    @Override // ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(Uri.Builder builder) throws NetworkCommandWithSession.BadSessionException {
        this.mSessionSetter.urlSetup(builder);
    }

    @Override // ru.mail.serverapi.BaseSessionSetter.SessionKeeper
    public String peekAuthToken() throws NetworkCommandWithSession.BadSessionException {
        String login = getParams().getLogin();
        if (login == null) {
            throw new NetworkCommandWithSession.BadSessionException("Mailbox context null", getNoAuthInfo());
        }
        this.LOG.d("peekAuthToken " + this.mTokenType);
        return this.mAccountManager.peekAuthToken(new Account(login, this.mAccountManagerSettings.getAccountType()), this.mTokenType);
    }

    @Override // ru.mail.network.NetworkCommand
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newUrlFormat("token"), Formats.newJsonFormat("token"), Formats.newUrlFormat(ClickerLinkConstructor.AUTOGEN_TOKEN), Formats.newJsonFormat(ClickerLinkConstructor.AUTOGEN_TOKEN), Formats.newJsonFormat("access_token"));
        return logFilterPrepareTokenFilter;
    }

    @Override // ru.mail.serverapi.BaseSessionSetter.SessionKeeper
    public void pushAuthToken(String str) {
        setAuthToken(str);
    }

    @Override // ru.mail.network.NetworkCommandWithSession, ru.mail.network.CommandWithAuthorization
    public void refreshAuthApi() {
        super.refreshAuthApi();
        this.mAuthCommandCreator = null;
        initAuthEntities(getContext());
        getAuthCommandCreator();
    }

    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    @Override // ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
        this.mSessionSetter.cookieSetup(networkService);
    }

    public ServerCommandBase(Context context, P p10, HostProvider hostProvider) {
        this(context, p10, hostProvider, false);
    }

    public ServerCommandBase(Context context, P p10, boolean z10) {
        this(context, p10, null, z10);
    }

    @Override // ru.mail.serverapi.BaseSessionSetter.SessionKeeper
    public NoAuthInfo getNoAuthInfo(String str, String str2) {
        return new NoAuthInfo(str, (AuthCommandCreator) MailAuthorizationApiType.LEGACY.create(this.mAuthCommandCreatorFactory), str2);
    }

    public ServerCommandBase(Context context, P p10, HostProvider hostProvider, boolean z10) {
        super(context, p10, hostProvider, z10);
        this.LOG = Log.getLog(getClass().getSimpleName());
        this.mPredefinedStatuses = Collections.unmodifiableList(Arrays.asList(new CommandStatusMessageGenerator.AuthCancelled(), new CommandStatusMessageGenerator.Error(), new CommandStatusMessageGenerator.BadRequest(), new CommandStatusMessageGenerator.BadSession(), new CommandStatusMessageGenerator.ErrorRetryLimitExceeded(), new CommandStatusMessageGenerator.ErrorInvalidLogin(), new CommandStatusMessageGenerator.NotExecuted(), new CommandStatusMessageGenerator.NotModified(), new CommandStatusMessageGenerator.Null(), new CommandStatusMessageGenerator.Ok(), new CommandStatusMessageGenerator.ErrorBadRequest(), new CommandStatusMessageGenerator.InternalServerError(), new CommandStatusMessageGenerator.Redirect(), new CommandStatusMessageGenerator.ErrorWithStatusCodeOther(), new CommandStatusMessageGenerator.NoAuth(), new CommandStatusMessageGenerator.SimpleError(), new OtherStatus()));
        this.mAccountManager = Authenticator.getAccountManagerWrapper(context.getApplicationContext());
        this.mTrafficListener = onCreateTrafficListener(context);
        PlatformInfo platformInfo = (PlatformInfo) Locator.from(context).locate(PlatformInfo.class);
        this.mPlatformInfo = platformInfo;
        AccountManagerSettings accountManagerSettings = (AccountManagerSettings) Locator.from(context).locate(AccountManagerSettings.class);
        this.mAccountManagerSettings = accountManagerSettings;
        initAuthEntities(context);
        this.mAuthCommandCreatorFactory = new MailAuthCommandCreatorFactory(platformInfo, accountManagerSettings);
    }
}
