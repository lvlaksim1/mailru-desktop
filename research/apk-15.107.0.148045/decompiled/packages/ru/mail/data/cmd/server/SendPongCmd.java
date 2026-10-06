package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.data.entities.PongUrl;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.GetServerRequest;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.PushAnalyticUrlData;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class SendPongCmd extends GetServerRequest<PongCommandParams, EmptyResult> {
    private MailAppAnalytics mAnalytic;
    private final boolean mNeedSendAnalytic;
    private String mUrl;

    /* JADX INFO: compiled from: ProGuard */
    public static class PongCommandParams extends ServerCommandBaseParams {
        private final PongUrl mPongUrl;

        public PongCommandParams(@NonNull PongUrl pongUrl) {
            this.mPongUrl = pongUrl;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass() && super.equals(obj)) {
                return Objects.equals(this.mPongUrl, ((PongCommandParams) obj).mPongUrl);
            }
            return false;
        }

        @NonNull
        public PongUrl getPongUrl() {
            return this.mPongUrl;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mPongUrl);
        }
    }

    public SendPongCmd(Context context, PongCommandParams pongCommandParams, HostProvider hostProvider) {
        super(context, pongCommandParams, hostProvider);
        ConfigurationWithRawData configuration = ConfigurationRepository.from(context).getConfiguration();
        boolean zIsAnalyticSendingAckAndOpenEnabled = configuration.isAnalyticSendingAckAndOpenEnabled();
        this.mNeedSendAnalytic = zIsAnalyticSendingAckAndOpenEnabled;
        boolean zIsUseSupervisorJobInWorkersEnabled = configuration.isUseSupervisorJobInWorkersEnabled();
        if (zIsAnalyticSendingAckAndOpenEnabled) {
            this.mAnalytic = MailAppDependencies.analytics(context);
            this.mUrl = pongCommandParams.getPongUrl().getUrl();
        }
        addLoggerParam("supervisor_job_in_worker", String.valueOf(zIsUseSupervisorJobInWorkersEnabled));
    }

    private Long getTimeStamp() {
        return Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean needPlatformParams() {
        return false;
    }

    @Override // ru.mail.network.NetworkCommand
    protected CommandStatus<?> processResponse(NetworkCommand.Response response) {
        try {
            return new CommandStatus.OK(onPostExecuteRequest(response));
        } catch (NetworkCommand.PostExecuteException e10) {
            return new CommandStatus.ERROR(e10);
        }
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean shouldRetry(int i10, CommandStatus<?> commandStatus) {
        PushAnalyticUrlData fromUrl;
        boolean zShouldRetry = super.shouldRetry(i10, commandStatus);
        if (this.mNeedSendAnalytic && (fromUrl = PushAnalyticUrlData.parseFromUrl(this.mUrl)) != null && fromUrl.getEvent().equals("open")) {
            this.mAnalytic.onRetrySendPong(fromUrl.getPushTokenHash(), fromUrl.getCampaignId(), fromUrl.getAccount(), getTimeStamp().longValue(), commandStatus.toString(), i10);
        }
        return zShouldRetry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(Uri.Builder builder) {
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) {
    }
}
