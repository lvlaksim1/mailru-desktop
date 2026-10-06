package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import ru.mail.arbiter.Pools;
import ru.mail.data.cmd.server.TrackAdvertisingUrlCommand.Params;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderConfiguration;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.NetworkTrafficListener;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;
import ru.mail.utils.analytics.SessionTracker;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class TrackAdvertisingUrlCommand<AdvertisingParams extends Params> extends ServerCommandBase<AdvertisingParams, EmptyResult> {
    private static final Log LOG = Log.getLog("TrackAdvertisingUrlCommand");
    private static final int MAX_RETRY_COUNT = 1;
    private final NetworkTrafficListener mTrafficListener;

    /* JADX INFO: compiled from: ProGuard */
    private static class AdLinkTrafficListener implements NetworkTrafficListener {

        @NonNull
        private final SessionTracker mTracker;

        AdLinkTrafficListener(@NonNull Context context) {
            this.mTracker = SessionTracker.from(context);
        }

        @Override // ru.mail.network.NetworkTrafficListener
        public void onTrafficReceived(long j10) {
            this.mTracker.sizeOfNewRxViaAdLink(j10);
        }

        @Override // ru.mail.network.NetworkTrafficListener
        public void onTrafficSent(long j10) {
            this.mTracker.sizeOfNewTxViaAdLink(j10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {
        private final String mUrl;

        public Params(String str) {
            this.mUrl = str;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Params) && super.equals(obj) && this.mUrl.equals(((Params) obj).mUrl);
        }

        public String getUrl() {
            return this.mUrl;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (super.hashCode() * 31) + this.mUrl.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public String toString() {
            return "mUrl=" + this.mUrl;
        }
    }

    public TrackAdvertisingUrlCommand(Context context, AdvertisingParams advertisingparams) {
        this(context, advertisingparams, new RbHostProvider(context.getApplicationContext(), "track_adv", new HostProviderConfiguration(false, false, false)));
    }

    private String extractRedirect() {
        for (String str : getNetworkService().getHeaderFields().keySet()) {
            if ("location".equalsIgnoreCase(str)) {
                String headerField = getNetworkService().getHeaderField(str);
                Uri uri = Uri.parse(headerField);
                if (!uri.isRelative()) {
                    return headerField;
                }
                Uri uri2 = Uri.parse(getNetworkService().getURL());
                return uri.buildUpon().scheme(uri2.getScheme()).authority(uri2.getAuthority()).build().toString();
            }
        }
        return null;
    }

    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.CommandExecutionInfo
    public String getLoggerParamName() {
        return "TrackAdvertisingUrlCommandOld_Event";
    }

    @Override // ru.mail.network.NetworkCommand
    /* JADX INFO: renamed from: getMaxAttemptCount */
    protected int getAttempts() {
        return 1;
    }

    @Override // ru.mail.network.NetworkCommand
    protected byte[] getResponseData(InputStream inputStream) throws IOException {
        return new byte[0];
    }

    @Override // ru.mail.network.NetworkCommand
    @Nullable
    protected String getTag() {
        return "track_advertising_url_command_old_event";
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @Nullable
    protected NetworkTrafficListener getTrafficListener() {
        return this.mTrafficListener;
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean needPlatformParams() {
        return false;
    }

    @Override // ru.mail.network.NetworkCommandWithSession, ru.mail.network.NetworkCommand
    protected void onPrepareConnection(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException, IOException {
        networkService.setInstanceFollowRedirects(false);
        super.onPrepareConnection(networkService);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    protected Uri onPrepareUrl(Uri.Builder builder) throws NetworkCommandWithSession.BadSessionException {
        return Uri.parse(((Params) getParams()).getUrl());
    }

    @Override // ru.mail.network.NetworkCommand
    protected CommandStatus<?> processResponse(NetworkCommand.Response response) {
        LOG.d("status code : " + response.getStatusCode() + " TargetUrl : " + getNetworkService().getURL());
        if (response.getStatusCode() == 200) {
            return new CommandStatus.OK(new EmptyResult());
        }
        return isRedirect(response.getStatusCode()) ? new NetworkCommandStatus.REDIRECT(extractRedirect()) : new CommandStatus.ERROR();
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor(Pools.ADVERTISING);
    }

    public TrackAdvertisingUrlCommand(Context context, AdvertisingParams advertisingparams, HostProvider hostProvider) {
        super(context, advertisingparams, hostProvider);
        this.mTrafficListener = new AdLinkTrafficListener(context);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        CommandStatus<?> commandStatusOnExecute = super.onExecute(executorSelector);
        if (!(commandStatusOnExecute instanceof NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED)) {
            return commandStatusOnExecute;
        }
        LOG.d(commandStatusOnExecute.getData().toString());
        return new CommandStatus.ERROR(((NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED) commandStatusOnExecute).getData());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
    }
}
