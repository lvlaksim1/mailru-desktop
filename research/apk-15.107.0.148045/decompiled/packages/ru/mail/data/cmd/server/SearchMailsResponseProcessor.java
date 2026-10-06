package ru.mail.data.cmd.server;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.config.ConfigurationRepository;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.analytics.logger.AdditionalAnalyticsParamsProvider;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e0\u0005R\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\f\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lru/mail/data/cmd/server/SearchMailsResponseProcessor;", "Lru/mail/serverapi/TornadoResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "configRepository", "Lru/mail/config/ConfigurationRepository;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "<init>", "(Lru/mail/network/NetworkCommand$Response;Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;Lru/mail/config/ConfigurationRepository;Lru/mail/analytics/MailAppAnalytics;)V", "process", "Lru/mail/mailbox/cmd/CommandStatus;", "sendSearchRequestAnalytics", "", "needSendAnalytics", "", "status", "", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SearchMailsResponseProcessor extends TornadoResponseProcessor {

    @NotNull
    private final MailAppAnalytics analytics;

    @NotNull
    private final ConfigurationRepository configRepository;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("SearchMailsResponseProcessor");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchMailsResponseProcessor(@NotNull NetworkCommand.Response resp, @NotNull NetworkCommand<?, ?>.NetworkCommandBaseDelegate customDelegate, @NotNull ConfigurationRepository configRepository, @NotNull MailAppAnalytics analytics) {
        super(resp, customDelegate);
        Intrinsics.checkNotNullParameter(resp, "resp");
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        Intrinsics.checkNotNullParameter(configRepository, "configRepository");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        this.configRepository = configRepository;
        this.analytics = analytics;
    }

    private final void sendSearchRequestAnalytics(boolean needSendAnalytics, String status) {
        if (needSendAnalytics) {
            this.analytics.onSearchRequestStatus(status, getResponse().getStatusCode());
        }
    }

    @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
    @NotNull
    public CommandStatus<?> process() {
        LOG.i("statusCode[" + getResponse().getStatusCode() + "]");
        boolean zIsAnalyticsForApiEnabled = this.configRepository.getConfiguration().getSearchConfig().isAnalyticsForApiEnabled();
        if (getResponse().getStatusCode() == 401 || getResponse().getStatusCode() == 520 || getResponse().getStatusCode() == 403) {
            sendSearchRequestAnalytics(zIsAnalyticsForApiEnabled, AdditionalAnalyticsParamsProvider.UNAUTHORIZED_EMAIL);
            CommandStatus<?> commandStatusOnUnauthorized = getDelegate().onUnauthorized("token");
            Intrinsics.checkNotNull(commandStatusOnUnauthorized);
            return commandStatusOnUnauthorized;
        }
        if (getResponse().getStatusCode() != 200) {
            sendSearchRequestAnalytics(zIsAnalyticsForApiEnabled, "error");
            CommandStatus<?> commandStatusOnError = getDelegate().onError(getResponse());
            Intrinsics.checkNotNull(commandStatusOnError);
            return commandStatusOnError;
        }
        sendSearchRequestAnalytics(zIsAnalyticsForApiEnabled, "ok");
        CommandStatus<?> commandStatusOnResponseOk = getDelegate().onResponseOk(getResponse());
        Intrinsics.checkNotNull(commandStatusOnResponseOk);
        return commandStatusOnResponseOk;
    }
}
