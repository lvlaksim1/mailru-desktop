package ru.mail.data.cmd.server.ad;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.ads.core.impl.model.source.remote.AdTrackingRemoteSource;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.CompositeCommand;
import ru.mail.network.RequestDurationAnalytics;
import ru.mail.network.processor.NetworkResult;
import ru.mail.network.processor.ServerResult;
import ru.mail.network.service.NetworkService;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0002H\u0014J\b\u0010\u0012\u001a\u00020\u0002H\u0002J\b\u0010\u0013\u001a\u00020\u0002H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/ad/AdTrackingUrlCommand;", "Lru/mail/mailbox/cmd/CompositeCommand;", "Lru/mail/ads/core/impl/model/source/remote/AdTrackingRemoteSource$Result;", "context", "Landroid/content/Context;", "networkService", "Lru/mail/network/service/NetworkService;", "durationAnalytics", "Lru/mail/network/RequestDurationAnalytics;", "url", "", "logger", "Lru/mail/util/log/Logger;", "useNewTrackAdvertisingUrlRequest", "", "<init>", "(Landroid/content/Context;Lru/mail/network/service/NetworkService;Lru/mail/network/RequestDurationAnalytics;Ljava/lang/String;Lru/mail/util/log/Logger;Z)V", "onExecuteComposite", "executeWithNewRequest", "executeWithLegacyRequest", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AdTrackingUrlCommand extends CompositeCommand<AdTrackingRemoteSource.Result> {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    @NotNull
    private final RequestDurationAnalytics durationAnalytics;

    @NotNull
    private final Logger logger;

    @NotNull
    private final NetworkService networkService;

    @NotNull
    private final String url;
    private final boolean useNewTrackAdvertisingUrlRequest;

    public AdTrackingUrlCommand(@NotNull Context context, @NotNull NetworkService networkService, @NotNull RequestDurationAnalytics durationAnalytics, @NotNull String url, @NotNull Logger logger, boolean z10) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(networkService, "networkService");
        Intrinsics.checkNotNullParameter(durationAnalytics, "durationAnalytics");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.context = context;
        this.networkService = networkService;
        this.durationAnalytics = durationAnalytics;
        this.url = url;
        this.logger = logger;
        this.useNewTrackAdvertisingUrlRequest = z10;
    }

    private final AdTrackingRemoteSource.Result executeWithLegacyRequest() {
        String redirectUrl = this.url;
        while (redirectUrl != null) {
            NetworkResult networkResult = (NetworkResult) executeCommand(new TrackAdvertisingUrlRequestLegacy(new TrackAdvertisingUrlRequestLegacy.Params(redirectUrl), this.context, this.networkService, this.durationAnalytics));
            if (networkResult == null) {
                Logger.w$default(this.logger, "TrackAdvertisingUrlCommand result is null!", null, 2, null);
                break;
            }
            if (networkResult instanceof NetworkResult.Success) {
                Object data = ((NetworkResult.Success) networkResult).getData();
                if (data instanceof ServerResult.OK) {
                    Object data2 = ((ServerResult.OK) data).getData();
                    TrackAdvertisingUrlRequestLegacy.Result.Redirect redirect = data2 instanceof TrackAdvertisingUrlRequestLegacy.Result.Redirect ? (TrackAdvertisingUrlRequestLegacy.Result.Redirect) data2 : null;
                    redirectUrl = redirect != null ? redirect.getRedirectUrl() : null;
                    if (redirectUrl == null) {
                        Logger.d$default(this.logger, "post redirect or single command success completed. Url = " + this.url, null, 2, null);
                        return AdTrackingRemoteSource.Result.Success.INSTANCE;
                    }
                    Logger.d$default(this.logger, "redirect success completed", null, 2, null);
                } else if (data instanceof ServerResult.Error) {
                    Logger.e$default(this.logger, "TrackAdvertisingUrlCommand error: " + data, null, 2, null);
                    return new AdTrackingRemoteSource.Result.Error(Integer.valueOf(((ServerResult.Error) data).getStatusCode()), null, 2, null);
                }
            } else if (networkResult instanceof NetworkResult.Error) {
                Logger.e$default(this.logger, "redirect failed", null, 2, null);
                NetworkResult.Error error = (NetworkResult.Error) networkResult;
                return new AdTrackingRemoteSource.Result.Error(Integer.valueOf(error.getStatusCode()), error.getError().getClass().getSimpleName());
            }
            redirectUrl = null;
        }
        return new AdTrackingRemoteSource.Result.Error(null, "no result", 1, null);
    }

    private final AdTrackingRemoteSource.Result executeWithNewRequest() {
        String redirectUrl = this.url;
        while (redirectUrl != null) {
            CommandStatus commandStatus = (CommandStatus) executeCommand(new TrackAdvertisingUrlRequestNew(this.context, new TrackAdvertisingUrlRequestNew.Params(redirectUrl), false, 4, null));
            if (commandStatus == null) {
                Logger.w$default(this.logger, "TrackAdvertisingUrlCommand result is null!", null, 2, null);
                break;
            }
            try {
                Object data = commandStatus.getData();
                if (data instanceof TrackAdvertisingUrlRequestNew.Result.Redirect) {
                    Logger.d$default(this.logger, "redirect success completed", null, 2, null);
                    redirectUrl = ((TrackAdvertisingUrlRequestNew.Result.Redirect) data).getRedirectUrl();
                } else {
                    if (data instanceof TrackAdvertisingUrlRequestNew.Result.OK) {
                        Logger.d$default(this.logger, "post redirect or single command success completed. Url = " + this.url, null, 2, null);
                        return AdTrackingRemoteSource.Result.Success.INSTANCE;
                    }
                    if (data instanceof TrackAdvertisingUrlRequestNew.Result.Error) {
                        TrackAdvertisingUrlRequestNew.Result.Error error = (TrackAdvertisingUrlRequestNew.Result.Error) data;
                        Logger.e$default(this.logger, "error: " + error.getStatusCode(), null, 2, null);
                        return new AdTrackingRemoteSource.Result.Error(Integer.valueOf(error.getStatusCode()), null, 2, null);
                    }
                    redirectUrl = null;
                }
            } catch (IllegalStateException unused) {
                Logger.w$default(this.logger, "TrackAdvertisingUrlCommand result.data is null!", null, 2, null);
            }
        }
        return new AdTrackingRemoteSource.Result.Error(null, "no result", 1, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.CompositeCommand
    @NotNull
    public AdTrackingRemoteSource.Result onExecuteComposite() {
        return this.useNewTrackAdvertisingUrlRequest ? executeWithNewRequest() : executeWithLegacyRequest();
    }
}
