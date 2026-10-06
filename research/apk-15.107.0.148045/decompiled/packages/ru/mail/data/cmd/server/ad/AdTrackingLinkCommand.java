package ru.mail.data.cmd.server.ad;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.RequestConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.RequestDurationAnalytics;
import ru.mail.network.processor.NetworkResult;
import ru.mail.network.processor.ServerResult;
import ru.mail.network.service.NetworkService;
import ru.mail.util.log.Logger;
import ru.mail.utils.DeeplinkUtil;
import ru.mail.utils.GooglePlayLinkUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000  2\u00020\u0001:\u0003\u001e\u001f B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0002J;\u0010\u0015\u001a\u0004\u0018\u0001H\u0016\"\u0004\b\u0000\u0010\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u0016\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lru/mail/data/cmd/server/ad/AdTrackingLinkCommand;", "Lru/mail/mailbox/cmd/CommandGroup;", "context", "Landroid/content/Context;", "networkService", "Lru/mail/network/service/NetworkService;", "requestDurationAnalytics", "Lru/mail/network/RequestDurationAnalytics;", "trackingLink", "", "logger", "Lru/mail/util/log/Logger;", "useNewTrackAdvertisingUrlRequest", "", "<init>", "(Landroid/content/Context;Lru/mail/network/service/NetworkService;Lru/mail/network/RequestDurationAnalytics;Ljava/lang/String;Lru/mail/util/log/Logger;Z)V", "redirectAttempts", "", "addTrackUrlRequestCmd", "", "url", "onExecuteCommand", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "cmd", "Lru/mail/mailbox/cmd/Command;", "priority", "Lru/mail/mailbox/cmd/Priority;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "(Lru/mail/mailbox/cmd/Command;Lru/mail/mailbox/cmd/Priority;Lru/mail/mailbox/cmd/ExecutorSelector;)Ljava/lang/Object;", "GooglePlayRedirect", "DeeplinkRedirect", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AdTrackingLinkCommand extends CommandGroup {
    private static final int MAX_REDIRECTS = 5;

    @NotNull
    private final Context context;

    @NotNull
    private final Logger logger;

    @NotNull
    private final NetworkService networkService;
    private int redirectAttempts;

    @NotNull
    private final RequestDurationAnalytics requestDurationAnalytics;
    private final boolean useNewTrackAdvertisingUrlRequest;
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lru/mail/data/cmd/server/ad/AdTrackingLinkCommand$DeeplinkRedirect;", "Lru/mail/mailbox/cmd/CommandStatus$OK;", "", "url", "<init>", "(Ljava/lang/String;)V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DeeplinkRedirect extends CommandStatus.OK<String> {
        public static final int $stable = 8;

        public DeeplinkRedirect(@Nullable String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lru/mail/data/cmd/server/ad/AdTrackingLinkCommand$GooglePlayRedirect;", "Lru/mail/mailbox/cmd/CommandStatus$OK;", "", "url", "<init>", "(Ljava/lang/String;)V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class GooglePlayRedirect extends CommandStatus.OK<String> {
        public static final int $stable = 8;

        public GooglePlayRedirect(@Nullable String str) {
            super(str);
        }
    }

    public AdTrackingLinkCommand(@NotNull Context context, @NotNull NetworkService networkService, @NotNull RequestDurationAnalytics requestDurationAnalytics, @NotNull String trackingLink, @NotNull Logger logger, boolean z10) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(networkService, "networkService");
        Intrinsics.checkNotNullParameter(requestDurationAnalytics, "requestDurationAnalytics");
        Intrinsics.checkNotNullParameter(trackingLink, "trackingLink");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.context = context;
        this.networkService = networkService;
        this.requestDurationAnalytics = requestDurationAnalytics;
        this.logger = logger;
        this.useNewTrackAdvertisingUrlRequest = z10;
        addTrackUrlRequestCmd(trackingLink);
    }

    private final void addTrackUrlRequestCmd(String url) {
        Command<?, ?> trackAdvertisingUrlRequestLegacy;
        this.redirectAttempts++;
        if (GooglePlayLinkUtil.isGooglePlayUrl(url)) {
            setResult(new GooglePlayRedirect(url));
            return;
        }
        if (DeeplinkUtil.isDeeplink(url)) {
            setResult(new DeeplinkRedirect(url));
            return;
        }
        if (this.useNewTrackAdvertisingUrlRequest) {
            trackAdvertisingUrlRequestLegacy = new TrackAdvertisingUrlRequestNew(this.context, new TrackAdvertisingUrlRequestNew.Params(url), false, 4, null);
        } else {
            trackAdvertisingUrlRequestLegacy = new TrackAdvertisingUrlRequestLegacy(new TrackAdvertisingUrlRequestLegacy.Params(url), this.context, this.networkService, this.requestDurationAnalytics);
        }
        addCommandAtFront(trackAdvertisingUrlRequestLegacy);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(@Nullable Command<?, T> cmd, @NotNull Priority priority, @Nullable ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        T t10 = (T) super.onExecuteCommand(cmd, priority, selector);
        String redirectUrl = null;
        if ((cmd instanceof TrackAdvertisingUrlRequestNew) && t10 == 0) {
            Logger.w$default(this.logger, "tracking link null result", null, 2, null);
        }
        if ((cmd instanceof TrackAdvertisingUrlRequestLegacy) && t10 == 0) {
            Logger.w$default(this.logger, "tracking link null result (legacy)", null, 2, null);
        }
        if (this.redirectAttempts > 5) {
            setResult(new NetworkCommandStatus.ERROR_RETRY_LIMIT_EXCEEDED());
            return t10;
        }
        if (!(t10 instanceof NetworkResult.Success)) {
            setResult(new CommandStatus.ERROR());
            return t10;
        }
        Object data = ((NetworkResult.Success) t10).getData();
        if (data instanceof ServerResult.OK) {
            Object data2 = ((ServerResult.OK) data).getData();
            if (data2 instanceof TrackAdvertisingUrlRequestLegacy.Result.Redirect) {
                redirectUrl = ((TrackAdvertisingUrlRequestLegacy.Result.Redirect) data2).getRedirectUrl();
            } else if (data2 instanceof TrackAdvertisingUrlRequestNew.Result.Redirect) {
                redirectUrl = ((TrackAdvertisingUrlRequestNew.Result.Redirect) data2).getRedirectUrl();
            }
            if (redirectUrl != null) {
                if (GooglePlayLinkUtil.isGooglePlayUrl(redirectUrl)) {
                    setResult(new GooglePlayRedirect(redirectUrl));
                    return t10;
                }
                addTrackUrlRequestCmd(redirectUrl);
                return t10;
            }
            setResult(new CommandStatus.ERROR());
        }
        return t10;
    }
}
