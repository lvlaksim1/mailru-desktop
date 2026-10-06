package ru.mail.data.cmd.server.ad;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.http.NameValuePair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.ads.core.api.di.AdCoreApiEntryPoint;
import ru.mail.ads.utils.LoadAdsTimeFormatter;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.arbiter.Pools;
import ru.mail.data.cmd.server.MyTargetRequestBodyCreator;
import ru.mail.data.cmd.server.RbHostProvider;
import ru.mail.data.cmd.server.RequestRbProcessor;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.NetworkTrafficListener;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.util.log.Log;
import ru.mail.utils.analytics.SessionTracker;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001;B9\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\b\u0010 \u001a\u00020!H\u0014J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0014J\b\u0010&\u001a\u00020\fH\u0014J\b\u0010'\u001a\u00020\u0003H\u0016J\b\u0010(\u001a\u00020\u0003H\u0014J\u0012\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\u0012\u0010-\u001a\u00020#2\b\u0010.\u001a\u0004\u0018\u00010%H\u0014JJ\u0010/\u001a\u0002002\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\f\u00101\u001a\b\u0012\u0002\b\u0003\u0018\u0001022(\u00103\u001a$\u0018\u000104R\u001e\u0012\f\u0012\n 6*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n 6*\u0004\u0018\u00010\u00030\u000305H\u0014J\u0010\u00107\u001a\u00020#2\u0006\u00108\u001a\u00020\nH\u0014J\b\u00109\u001a\u00020:H\u0014R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lru/mail/data/cmd/server/ad/RbAdJsonRequest;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/ad/RbParams;", "", "context", "Landroid/content/Context;", "params", "usePostParams", "", "timeout", "", "maxRequestAttempts", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/ad/RbParams;ZLjava/lang/Long;Ljava/lang/Integer;)V", "getTimeout", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMaxRequestAttempts", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "trafficListener", "Lru/mail/data/cmd/server/ad/RbAdJsonRequest$RbTrafficListener;", "log", "Lru/mail/util/log/Log;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "onPrepareRequestBody", "Lru/mail/network/requestbody/RequestBody;", "onPrepareConnection", "", "service", "Lru/mail/network/service/NetworkService;", "getMaxAttemptCount", "getLoggerParamName", "getTag", "getResponseData", "", "is", "Ljava/io/InputStream;", "setUpSession", "networkService", "getResponseProcessor", "Lru/mail/data/cmd/server/RequestRbProcessor;", "serverApi", "Lru/mail/network/ServerApi;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "onRequestTimeReceived", "duration", "getTrafficListener", "Lru/mail/network/NetworkTrafficListener;", "RbTrafficListener", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {"mobile", "{slot}"})
@SourceDebugExtension({"SMAP\nRbAdJsonRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RbAdJsonRequest.kt\nru/mail/data/cmd/server/ad/RbAdJsonRequest\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,119:1\n1#2:120\n*E\n"})
public final class RbAdJsonRequest extends ServerCommandBase<RbParams, String> {
    public static final int $stable = 8;

    @NotNull
    private final Log log;

    @Nullable
    private final Integer maxRequestAttempts;

    @Nullable
    private final Long timeout;

    @NotNull
    private final RbTrafficListener trafficListener;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/data/cmd/server/ad/RbAdJsonRequest$RbTrafficListener;", "Lru/mail/network/NetworkTrafficListener;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "tracker", "Lru/mail/utils/analytics/SessionTracker;", "onTrafficSent", "", "bytesCount", "", "onTrafficReceived", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class RbTrafficListener implements NetworkTrafficListener {

        @NotNull
        private final SessionTracker tracker;

        public RbTrafficListener(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            SessionTracker sessionTrackerFrom = SessionTracker.from(context);
            Intrinsics.checkNotNullExpressionValue(sessionTrackerFrom, "from(...)");
            this.tracker = sessionTrackerFrom;
        }

        @Override // ru.mail.network.NetworkTrafficListener
        public void onTrafficReceived(long bytesCount) {
            this.tracker.sizeOfNewRxViaAdSlot(bytesCount);
        }

        @Override // ru.mail.network.NetworkTrafficListener
        public void onTrafficSent(long bytesCount) {
            this.tracker.sizeOfNewTxViaAdSlot(bytesCount);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public RbAdJsonRequest(@NotNull Context context, @NotNull RbParams params, boolean z10) {
        this(context, params, z10, null, null, 24, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.CommandExecutionInfo
    @NotNull
    public String getLoggerParamName() {
        return "AdJSON";
    }

    @Override // ru.mail.network.NetworkCommand
    /* JADX INFO: renamed from: getMaxAttemptCount */
    protected int getAttempts() {
        Integer num = this.maxRequestAttempts;
        return num != null ? num.intValue() : super.getAttempts();
    }

    @Nullable
    public final Integer getMaxRequestAttempts() {
        return this.maxRequestAttempts;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected byte[] getResponseData(@Nullable InputStream is) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        StringBuilder sb2 = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                String string = sb2.toString();
                Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
                Charset charsetForName = Charset.forName("UTF-8");
                Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(...)");
                byte[] bytes = string.getBytes(charsetForName);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                return bytes;
            }
            sb2.append(line);
        }
    }

    @Override // ru.mail.network.NetworkCommand
    public /* bridge */ /* synthetic */ ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return getResponseProcessor(response, (ServerApi<?>) serverApi, (NetworkCommand<RbParams, String>.NetworkCommandBaseDelegate) networkCommandBaseDelegate);
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected String getTag() {
        return "ad_json";
    }

    @Nullable
    public final Long getTimeout() {
        return this.timeout;
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @NotNull
    protected NetworkTrafficListener getTrafficListener() {
        return this.trafficListener;
    }

    @Override // ru.mail.network.NetworkCommandWithSession, ru.mail.network.NetworkCommand
    protected void onPrepareConnection(@NotNull NetworkService service) throws IOException {
        Intrinsics.checkNotNullParameter(service, "service");
        super.onPrepareConnection(service);
        Long l10 = this.timeout;
        if (l10 != null) {
            int iLongValue = (int) l10.longValue();
            service.setConnectTimeout(iLongValue);
            service.setReadTimeout(iLongValue);
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @NotNull
    protected RequestBody onPrepareRequestBody() {
        List<NameValuePair> listProvidePostParams = providePostParams();
        Intrinsics.checkNotNullExpressionValue(listProvidePostParams, "providePostParams(...)");
        logPostParams(listProvidePostParams);
        return new MyTargetRequestBodyCreator(this.log).create(listProvidePostParams);
    }

    @Override // ru.mail.network.NetworkCommand
    protected void onRequestTimeReceived(long duration) {
        AdCoreApiEntryPoint.Companion companion = AdCoreApiEntryPoint.INSTANCE;
        Context applicationContext = getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        if (companion.adConfiguration(applicationContext).getSendLoadingDurationAnalytics()) {
            MailAppDependencies.analytics(getContext()).adRequestDuration(LoadAdsTimeFormatter.INSTANCE.toDurationBuckets(duration));
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        CommandExecutor singleCommandExecutor = selector.getSingleCommandExecutor(Pools.ADVERTISING);
        Intrinsics.checkNotNullExpressionValue(singleCommandExecutor, "getSingleCommandExecutor(...)");
        return singleCommandExecutor;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public RbAdJsonRequest(@NotNull Context context, @NotNull RbParams params, boolean z10, @Nullable Long l10) {
        this(context, params, z10, l10, null, 16, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected RequestRbProcessor getResponseProcessor(@Nullable NetworkCommand.Response resp, @Nullable ServerApi<?> serverApi, @Nullable NetworkCommand<RbParams, String>.NetworkCommandBaseDelegate customDelegate) {
        return new RequestRbProcessor(resp, customDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public String onPostExecuteRequest(@NotNull NetworkCommand.Response resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        String respString = resp.getRespString();
        Intrinsics.checkNotNullExpressionValue(respString, "getRespString(...)");
        return respString;
    }

    public /* synthetic */ RbAdJsonRequest(Context context, RbParams rbParams, boolean z10, Long l10, Integer num, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, rbParams, z10, (i10 & 8) != 0 ? null : l10, (i10 & 16) != 0 ? null : num);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public RbAdJsonRequest(@NotNull Context context, @NotNull RbParams params, boolean z10, @Nullable Long l10, @Nullable Integer num) {
        super(context, params, new RbHostProvider(context.getApplicationContext(), "pubnative_cmd"), z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.timeout = l10;
        this.maxRequestAttempts = num;
        this.trafficListener = new RbTrafficListener(context);
        this.log = Log.INSTANCE.getLog("RbAdJsonRequest");
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(@Nullable NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
    }
}
