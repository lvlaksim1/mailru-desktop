package ru.mail.data.cmd.server.ad;

import android.content.Context;
import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.arbiter.Pools;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.NetworkTrafficListener;
import ru.mail.network.RequestDurationAnalytics;
import ru.mail.network.processor.NetworkResponse;
import ru.mail.network.processor.NetworkResult;
import ru.mail.network.processor.RequestExecutor;
import ru.mail.network.processor.RequestExecutorBuilder;
import ru.mail.network.processor.RequestInfoBuilder;
import ru.mail.network.processor.ServerResult;
import ru.mail.network.request.NetworkRequest;
import ru.mail.network.response.ResponseProcessor;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;
import ru.mail.utils.analytics.SessionTracker;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u0000 #2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0004 !\"#B'\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\"\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00180\u00170\u00162\u0006\u0010\u0007\u001a\u00020\bH\u0014J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u001aH\u0002J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\n\u0010\u001f\u001a\u0004\u0018\u00010\u0014H\u0002R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy;", "Lru/mail/network/request/NetworkRequest;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Params;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Result;", "params", "context", "Landroid/content/Context;", "networkService", "Lru/mail/network/service/NetworkService;", "requestDurationAnalytics", "Lru/mail/network/RequestDurationAnalytics;", "<init>", "(Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Params;Landroid/content/Context;Lru/mail/network/service/NetworkService;Lru/mail/network/RequestDurationAnalytics;)V", "trafficListener", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$AdLinkTrafficListener;", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "getLoggerParamName", "", "createRequestExecutor", "Lru/mail/network/processor/RequestExecutor;", "Lru/mail/network/processor/NetworkResult;", "Lru/mail/network/processor/ServerResult;", "createResponseProcessor", "Lru/mail/network/response/ResponseProcessor;", "isRedirect", "", HiAnalyticsConstant.HaKey.BI_KEY_RESULT, "", "extractRedirect", "AdLinkTrafficListener", "Params", "Result", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TrackAdvertisingUrlRequestLegacy extends NetworkRequest<Params, Result> {
    private static final int MAX_ATTEMPT_COUNT = 1;

    @NotNull
    private final AdLinkTrafficListener trafficListener;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("TrackAdvertisingUrlRequestLegacy");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$AdLinkTrafficListener;", "Lru/mail/network/NetworkTrafficListener;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "tracker", "Lru/mail/utils/analytics/SessionTracker;", "onTrafficSent", "", "bytesCount", "", "onTrafficReceived", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class AdLinkTrafficListener implements NetworkTrafficListener {

        @NotNull
        private final SessionTracker tracker;

        public AdLinkTrafficListener(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SessionTracker sessionTrackerFrom = SessionTracker.from(context);
            Intrinsics.checkNotNullExpressionValue(sessionTrackerFrom, "from(...)");
            this.tracker = sessionTrackerFrom;
        }

        @Override // ru.mail.network.NetworkTrafficListener
        public void onTrafficReceived(long bytesCount) {
            this.tracker.sizeOfNewRxViaAdLink(bytesCount);
        }

        @Override // ru.mail.network.NetworkTrafficListener
        public void onTrafficSent(long bytesCount) {
            this.tracker.sizeOfNewTxViaAdLink(bytesCount);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "url", "", "<init>", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "equals", "", "o", "", "toString", "hashCode", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandBaseParams {
        public static final int $stable = 8;

        /* JADX INFO: renamed from: url, reason: from kotlin metadata and from toString */
        @NotNull
        private final String mUrl;

        public Params(@NotNull String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.mUrl = url;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object o10) {
            if (this == o10) {
                return true;
            }
            if ((o10 instanceof Params) && super.equals(o10)) {
                return Intrinsics.areEqual(this.mUrl, ((Params) o10).mUrl);
            }
            return false;
        }

        @NotNull
        /* JADX INFO: renamed from: getUrl, reason: from getter */
        public final String getMUrl() {
            return this.mUrl;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (super.hashCode() * 31) + this.mUrl.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            return "mUrl=" + this.mUrl;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Result;", "", "<init>", "()V", "OK", "Redirect", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Result$OK;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Result$Redirect;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Result$OK;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Result;", "<init>", "()V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OK extends Result {
            public static final int $stable = 0;

            @NotNull
            public static final OK INSTANCE = new OK();

            private OK() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Result$Redirect;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestLegacy$Result;", "redirectUrl", "", "<init>", "(Ljava/lang/String;)V", "getRedirectUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Redirect extends Result {
            public static final int $stable = 0;

            @Nullable
            private final String redirectUrl;

            public Redirect(@Nullable String str) {
                super(null);
                this.redirectUrl = str;
            }

            public static /* synthetic */ Redirect copy$default(Redirect redirect, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = redirect.redirectUrl;
                }
                return redirect.copy(str);
            }

            @Nullable
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getRedirectUrl() {
                return this.redirectUrl;
            }

            @NotNull
            public final Redirect copy(@Nullable String redirectUrl) {
                return new Redirect(redirectUrl);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Redirect) && Intrinsics.areEqual(this.redirectUrl, ((Redirect) other).redirectUrl);
            }

            @Nullable
            public final String getRedirectUrl() {
                return this.redirectUrl;
            }

            public int hashCode() {
                String str = this.redirectUrl;
                if (str == null) {
                    return 0;
                }
                return str.hashCode();
            }

            @NotNull
            public String toString() {
                return "Redirect(redirectUrl=" + this.redirectUrl + ")";
            }
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Result() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrackAdvertisingUrlRequestLegacy(@NotNull Params params, @NotNull Context context, @NotNull NetworkService networkService, @NotNull RequestDurationAnalytics requestDurationAnalytics) {
        super(params, networkService, requestDurationAnalytics);
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(networkService, "networkService");
        Intrinsics.checkNotNullParameter(requestDurationAnalytics, "requestDurationAnalytics");
        this.trafficListener = new AdLinkTrafficListener(context);
    }

    private final ResponseProcessor<Result> createResponseProcessor() {
        return new ResponseProcessor<Result>() { // from class: ru.mail.data.cmd.server.ad.TrackAdvertisingUrlRequestLegacy.createResponseProcessor.1
            @Override // ru.mail.network.response.ResponseProcessor
            public ServerResult<Result> processResponse(NetworkResponse.Success response) {
                Intrinsics.checkNotNullParameter(response, "response");
                TrackAdvertisingUrlRequestLegacy.LOG.d("status code : " + response.getStatusCode() + " TargetUrl : " + TrackAdvertisingUrlRequestLegacy.this.getParams().getMUrl());
                if (response.getStatusCode() == 200) {
                    return new ServerResult.OK(Result.OK.INSTANCE);
                }
                return TrackAdvertisingUrlRequestLegacy.this.isRedirect(response.getStatusCode()) ? new ServerResult.OK(new Result.Redirect(TrackAdvertisingUrlRequestLegacy.this.extractRedirect())) : new ServerResult.Error(response.getStatusCode());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String extractRedirect() {
        for (String str : getNetworkService().getHeaderFields().keySet()) {
            if (StringsKt.equals("location", str, true)) {
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

    @Override // ru.mail.network.request.NetworkRequest
    @NotNull
    protected RequestExecutor<NetworkResult<ServerResult<Result>>> createRequestExecutor(@NotNull NetworkService networkService) {
        Intrinsics.checkNotNullParameter(networkService, "networkService");
        RequestInfoBuilder.Companion companion = RequestInfoBuilder.INSTANCE;
        Uri uri = Uri.parse(getParams().getMUrl());
        Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
        return new RequestExecutorBuilder(networkService, 0, 2, null).setTrafficListener(this.trafficListener).setMaxAttemptCount(1).build(companion.from(uri).build(), createResponseProcessor());
    }

    @Override // ru.mail.mailbox.cmd.CommandExecutionInfo
    @NotNull
    public String getLoggerParamName() {
        return "TrackAdvertisingUrlCommand_Event";
    }

    public final boolean isRedirect(int statusCode) {
        return (statusCode == 307) | (statusCode == 301) | (statusCode == 302) | (statusCode == 300) | (statusCode == 304) | (statusCode == 303) | (statusCode == 304) | (statusCode == 305);
    }

    @Override // ru.mail.network.request.NetworkRequest, ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        CommandExecutor singleCommandExecutor = selector.getSingleCommandExecutor(Pools.ADVERTISING);
        Intrinsics.checkNotNullExpressionValue(singleCommandExecutor, "getSingleCommandExecutor(...)");
        return singleCommandExecutor;
    }
}
