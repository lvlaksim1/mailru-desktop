package ru.mail.data.cmd.server.calls;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.calleridentification.CallsAuthProvider;
import ru.mail.data.cmd.server.GoogleAdvertisingInfo;
import ru.mail.mailapp.R;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.GetServerRequest;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.utils.UtilExtensionsKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B!\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJP\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0016\u0010\u0010\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00120\u00112&\u0010\u0013\u001a\"0\u0014R\u001e\u0012\f\u0012\n \u0015*\u0004\u0018\u00018\u00008\u0000\u0012\f\u0012\n \u0015*\u0004\u0018\u00018\u00018\u00010\u0012H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsBaseGetRequest;", "P", "Lru/mail/serverapi/ServerCommandBaseParams;", "R", "Lru/mail/serverapi/GetServerRequest;", "context", "Landroid/content/Context;", "params", "authProvider", "Lru/mail/calleridentification/CallsAuthProvider;", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/ServerCommandBaseParams;Lru/mail/calleridentification/CallsAuthProvider;)V", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "serverApi", "Lru/mail/network/ServerApi;", "Lru/mail/network/NetworkCommand;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "kotlin.jvm.PlatformType", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "getHostProvider", "Lru/mail/network/HostProvider;", "needPlatformParams", "", "onSetupSessionInUrl", "", "url", "Landroid/net/Uri$Builder;", "setUpSession", "networkService", "Lru/mail/network/service/NetworkService;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCallsBaseGetRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CallsBaseGetRequest.kt\nru/mail/data/cmd/server/calls/CallsBaseGetRequest\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,65:1\n216#2,2:66\n*S KotlinDebug\n*F\n+ 1 CallsBaseGetRequest.kt\nru/mail/data/cmd/server/calls/CallsBaseGetRequest\n*L\n60#1:66,2\n*E\n"})
public abstract class CallsBaseGetRequest<P extends ServerCommandBaseParams, R> extends GetServerRequest<P, R> {
    public static final int $stable = 8;

    @NotNull
    private final CallsAuthProvider authProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallsBaseGetRequest(@Nullable Context context, @NotNull P params, @NotNull CallsAuthProvider authProvider) {
        super(context, params);
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(authProvider, "authProvider");
        this.authProvider = authProvider;
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        HostProvider hostProvider = super.getHostProvider();
        Intrinsics.checkNotNullExpressionValue(hostProvider, "getHostProvider(...)");
        Context applicationContext = getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return new CallsHostProvider(hostProvider, applicationContext);
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@NotNull NetworkCommand.Response resp, @NotNull ServerApi<? extends NetworkCommand<?, ?>> serverApi, @NotNull NetworkCommand<P, R>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        Intrinsics.checkNotNullParameter(serverApi, "serverApi");
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        return new CallsResponseProcessor(resp, customDelegate);
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean needPlatformParams() {
        return false;
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(@NotNull NetworkService networkService) {
        Intrinsics.checkNotNullParameter(networkService, "networkService");
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        String strCreateUriFromResources$default = UtilExtensionsKt.createUriFromResources$default(context, R.string.calls_default_scheme, R.string.calls_default_host, null, 4, null);
        String advertisingId = GoogleAdvertisingInfo.getAdvertisingId(getContext());
        String str = getContext().getPackageName() + getContext().getResources().getString(R.string.app_version);
        Intrinsics.checkNotNull(advertisingId);
        networkService.addRequestProperty("X-Mob-GAID", advertisingId);
        networkService.addRequestProperty("X-Mob-Platform", "android");
        String RELEASE = Build.VERSION.RELEASE;
        Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
        networkService.addRequestProperty("X-Mob-OS-Version", RELEASE);
        networkService.addRequestProperty("X-Mob-App-Version", str);
        networkService.addRequestProperty("Referer", strCreateUriFromResources$default);
        for (Map.Entry<String, String> entry : this.authProvider.getRequestAuthHeaders(peekAuthToken()).entrySet()) {
            networkService.addRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(@Nullable Uri.Builder url) {
    }
}
