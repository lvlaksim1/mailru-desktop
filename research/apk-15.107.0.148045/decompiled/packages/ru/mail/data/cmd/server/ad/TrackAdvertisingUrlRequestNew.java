package ru.mail.data.cmd.server.ad;

import android.content.Context;
import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.webview.SystemUserAgentProvider;
import ru.mail.arbiter.Pools;
import ru.mail.data.cmd.server.RbHostProvider;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.NetworkTrafficListener;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;
import ru.mail.utils.analytics.SessionTracker;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 22\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0005./012B#\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\u0012\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\u0012\u0010\u001e\u001a\u00020\u001b2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020 H\u0014J:\u0010#\u001a\u00020$2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\f\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010&2\u0018\u0010'\u001a\u0014\u0018\u00010(R\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030)H\u0014J\b\u0010*\u001a\u00020+H\u0014J\u0012\u0010,\u001a\u0004\u0018\u00010\u00112\u0006\u0010-\u001a\u00020\u000fH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Params;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Params;Z)V", "trafficListener", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$AdLinkTrafficListener;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getUserAgent", "", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "getLoggerParamName", "getTag", "getMaxAttemptCount", "", "setUpSession", "", "networkService", "Lru/mail/network/service/NetworkService;", "onSetupSessionInUrl", "builder", "Landroid/net/Uri$Builder;", "onPrepareUrl", "Landroid/net/Uri;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "serverApi", "Lru/mail/network/ServerApi;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "getTrafficListener", "Lru/mail/network/NetworkTrafficListener;", "extractRedirect", "response", "AdLinkTrafficListener", "TrackAdvertisingUrlProcessor", "Params", "Result", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTrackAdvertisingUrlRequestNew.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TrackAdvertisingUrlRequestNew.kt\nru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,180:1\n29#2:181\n29#2:182\n29#2:183\n*S KotlinDebug\n*F\n+ 1 TrackAdvertisingUrlRequestNew.kt\nru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew\n*L\n86#1:181\n108#1:182\n111#1:183\n*E\n"})
public final class TrackAdvertisingUrlRequestNew extends ServerCommandBase<Params, Result> {
    private static final int MAX_ATTEMPT_COUNT = 1;

    @NotNull
    private final AdLinkTrafficListener trafficListener;
    public static final int $stable = 8;

    @NotNull
    private static final String LOG_TAG = "TrackAdvertisingUrlRequestNew";

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog(LOG_TAG);

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$AdLinkTrafficListener;", "Lru/mail/network/NetworkTrafficListener;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "tracker", "Lru/mail/utils/analytics/SessionTracker;", "onTrafficSent", "", "bytesCount", "", "onTrafficReceived", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "url", "", "<init>", "(Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params extends ServerCommandBaseParams {
        public static final int $stable = 8;

        @NotNull
        private final String url;

        public Params(@NotNull String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
        }

        public static /* synthetic */ Params copy$default(Params params, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = params.url;
            }
            return params.copy(str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @NotNull
        public final Params copy(@NotNull String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new Params(url);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Params) && Intrinsics.areEqual(this.url, ((Params) other).url);
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return this.url.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            return "Params(url=" + this.url + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result;", "", "<init>", "()V", "OK", "Redirect", "Error", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result$Error;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result$OK;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result$Redirect;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result$Error;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result;", HiAnalyticsConstant.HaKey.BI_KEY_RESULT, "", "<init>", "(I)V", "getStatusCode", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Error extends Result {
            public static final int $stable = 0;
            private final int statusCode;

            public Error(int i10) {
                super(null);
                this.statusCode = i10;
            }

            public static /* synthetic */ Error copy$default(Error error, int i10, int i11, Object obj) {
                if ((i11 & 1) != 0) {
                    i10 = error.statusCode;
                }
                return error.copy(i10);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getStatusCode() {
                return this.statusCode;
            }

            @NotNull
            public final Error copy(int statusCode) {
                return new Error(statusCode);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Error) && this.statusCode == ((Error) other).statusCode;
            }

            public final int getStatusCode() {
                return this.statusCode;
            }

            public int hashCode() {
                return Integer.hashCode(this.statusCode);
            }

            @NotNull
            public String toString() {
                return "Error(statusCode=" + this.statusCode + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result$OK;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result;", "<init>", "()V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result$Redirect;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result;", "redirectUrl", "", "<init>", "(Ljava/lang/String;)V", "getRedirectUrl", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0018\u00010\u0005R\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\f\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$TrackAdvertisingUrlProcessor;", "Lru/mail/network/ResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "delegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Params;", "Lru/mail/data/cmd/server/ad/TrackAdvertisingUrlRequestNew$Result;", "<init>", "(Lru/mail/network/NetworkCommand$Response;Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;)V", "process", "Lru/mail/mailbox/cmd/CommandStatus;", "isRedirectStatus", "", HiAnalyticsConstant.HaKey.BI_KEY_RESULT, "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class TrackAdvertisingUrlProcessor extends ResponseProcessor {
        public TrackAdvertisingUrlProcessor(@Nullable NetworkCommand.Response response, @Nullable NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
            super(response, networkCommandBaseDelegate);
        }

        private final boolean isRedirectStatus(int statusCode) {
            return statusCode == 307 || statusCode == 301 || statusCode == 302 || statusCode == 300 || statusCode == 303 || statusCode == 305;
        }

        @Override // ru.mail.network.ResponseProcessor
        @NotNull
        public CommandStatus<?> process() {
            NetworkCommand.Response response = getResponse();
            int statusCode = response.getStatusCode();
            if (statusCode == 200) {
                CommandStatus<?> commandStatusOnResponseOk = getDelegate().onResponseOk(response);
                Intrinsics.checkNotNull(commandStatusOnResponseOk);
                return commandStatusOnResponseOk;
            }
            if (isRedirectStatus(statusCode)) {
                CommandStatus<?> commandStatusOnResponseOk2 = getDelegate().onResponseOk(response);
                Intrinsics.checkNotNull(commandStatusOnResponseOk2);
                return commandStatusOnResponseOk2;
            }
            CommandStatus<?> commandStatusOnError = getDelegate().onError(response);
            Intrinsics.checkNotNull(commandStatusOnError);
            return commandStatusOnError;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TrackAdvertisingUrlRequestNew(@NotNull Context context, @NotNull Params params) {
        this(context, params, false, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0044  */
    /* JADX WARN: Multi-variable type inference failed */
    private final String extractRedirect(NetworkCommand.Response response) {
        String str;
        Map<String, List<String>> headers = response.getHeaders();
        if (headers != null) {
            Iterator<Map.Entry<String, List<String>>> it = headers.entrySet().iterator();
            do {
                if (!it.hasNext()) {
                    str = null;
                    break;
                }
                Map.Entry<String, List<String>> next = it.next();
                String key = next.getKey();
                List<String> value = next.getValue();
                if (StringsKt.equals(key, "location", true)) {
                    Intrinsics.checkNotNull(value);
                    if (value.isEmpty()) {
                        str = null;
                    } else {
                        str = value.get(0);
                    }
                } else {
                    str = null;
                }
            } while (str == null);
            if (str != null) {
                Uri uri = Uri.parse(str);
                if (!uri.isRelative()) {
                    return str;
                }
                Uri uri2 = Uri.parse(((Params) getParams()).getUrl());
                return uri.buildUpon().scheme(uri2.getScheme()).authority(uri2.getAuthority()).build().toString();
            }
        }
        return null;
    }

    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.CommandExecutionInfo
    @NotNull
    public String getLoggerParamName() {
        return LOG_TAG;
    }

    @Override // ru.mail.network.NetworkCommand
    protected int getMaxAttemptCount() {
        return 1;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@Nullable NetworkCommand.Response resp, @Nullable ServerApi<?> serverApi, @Nullable NetworkCommand<Params, Result>.NetworkCommandBaseDelegate customDelegate) {
        return new TrackAdvertisingUrlProcessor(resp, customDelegate);
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected String getTag() {
        return "track_advertising_url";
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @NotNull
    protected NetworkTrafficListener getTrafficListener() {
        return this.trafficListener;
    }

    @Override // ru.mail.network.NetworkCommand
    @Nullable
    public String getUserAgent() {
        String property = System.getProperty("http.agent");
        if (property != null) {
            return property;
        }
        Context applicationContext = getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        String str = SystemUserAgentProvider.get(applicationContext);
        return str == null ? super.getUserAgent() : str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected Uri onPrepareUrl(@NotNull Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        return Uri.parse(((Params) getParams()).getUrl());
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        CommandExecutor singleCommandExecutor = selector.getSingleCommandExecutor(Pools.ADVERTISING);
        Intrinsics.checkNotNullExpressionValue(singleCommandExecutor, "getSingleCommandExecutor(...)");
        return singleCommandExecutor;
    }

    public /* synthetic */ TrackAdvertisingUrlRequestNew(Context context, Params params, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, params, (i10 & 4) != 0 ? false : z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        int statusCode = resp.getStatusCode();
        LOG.d("Status code : " + statusCode + ", targetUrl : " + ((Params) getParams()).getUrl());
        if (statusCode == 200) {
            return Result.OK.INSTANCE;
        }
        return isRedirect(statusCode) ? new Result.Redirect(extractRedirect(resp)) : new Result.Error(statusCode);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TrackAdvertisingUrlRequestNew(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, new RbHostProvider(context.getApplicationContext(), "track_adv"), z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.trafficListener = new AdLinkTrafficListener(context);
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(@Nullable Uri.Builder builder) {
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(@Nullable NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
    }
}
