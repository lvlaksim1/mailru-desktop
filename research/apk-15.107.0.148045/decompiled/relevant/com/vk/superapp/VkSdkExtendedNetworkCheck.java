package com.vk.superapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import com.vk.api.sdk.VKApiConfig;
import com.vk.api.sdk.auth.VKAccessTokenProvider;
import com.vk.network.kbh.ExtendedNetworkCheck;
import com.vk.network.kbh.UnstableNetworkDetector;
import com.vk.superapp.api.core.SuperappApiCore;
import com.vk.superapp.api.internal.requests.common.CustomApiRequest;
import com.vk.superapp.core.utils.VKCLogger;
import com.vk.toggle.FeatureManager;
import com.vk.toggle.anonymous.SakFeatures;
import com.vk.toggle.extensions.TogglesExtKt;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Lcom/vk/superapp/VkSdkExtendedNetworkCheck;", "Lcom/vk/network/kbh/ExtendedNetworkCheck;", "Landroid/content/Context;", "appContext", "<init>", "(Landroid/content/Context;)V", "", "run", "()Z", "isWifi", "superappkit_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VkSdkExtendedNetworkCheck implements ExtendedNetworkCheck {

    @NotNull
    private final Lazy kdskvkvmoca;

    @NotNull
    private final ReentrantLock kdskvkvmocb;
    private final Condition kdskvkvmocc;
    private volatile boolean kdskvkvmocd;
    private volatile long kdskvkvmoce;

    @Nullable
    private ScheduledFuture<?> kdskvkvmocf;

    @NotNull
    private final Lazy kdskvkvmocg;
    private final ScheduledExecutorService kdskvkvmoch;

    public VkSdkExtendedNetworkCheck(@NotNull final Context appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.kdskvkvmoca = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: com.vk.superapp.b1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VkSdkExtendedNetworkCheck.kdskvkvmoca(appContext);
            }
        });
        ReentrantLock reentrantLock = new ReentrantLock();
        this.kdskvkvmocb = reentrantLock;
        this.kdskvkvmocc = reentrantLock.newCondition();
        this.kdskvkvmocg = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.c1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(VkSdkExtendedNetworkCheck.kdskvkvmocc());
            }
        });
        this.kdskvkvmoch = Executors.newSingleThreadScheduledExecutor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ConnectivityManager kdskvkvmoca(Context context) {
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        return (ConnectivityManager) systemService;
    }

    private final void kdskvkvmocb() {
        if (this.kdskvkvmocf == null) {
            this.kdskvkvmocf = this.kdskvkvmoch.schedule(new Runnable() { // from class: com.vk.superapp.d1
                @Override // java.lang.Runnable
                public final void run() {
                    VkSdkExtendedNetworkCheck.kdskvkvmoca(this.f53661a);
                }
            }, ((Number) this.kdskvkvmocg.getValue()).longValue(), TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long kdskvkvmocc() {
        String value;
        FeatureManager.Toggle feature = SakFeatures.INSTANCE.getManagerSak().getFeature(SakFeatures.Type.SAK_EXTEND_NETWORK_CHECK_TIMEOUT_ANDROID);
        Integer intOrNull = (feature == null || (value = feature.getValue()) == null) ? null : StringsKt.toIntOrNull(value);
        if (!TogglesExtKt.isEnabled(feature) || intOrNull == null || intOrNull.intValue() < 0) {
            return 30000L;
        }
        return ((long) intOrNull.intValue()) * 1000;
    }

    @Override // com.vk.network.kbh.ExtendedNetworkCheck
    public boolean isWifi() {
        try {
            NetworkCapabilities networkCapabilities = ((ConnectivityManager) this.kdskvkvmoca.getValue()).getNetworkCapabilities(((ConnectivityManager) this.kdskvkvmoca.getValue()).getActiveNetwork());
            return networkCapabilities != null && networkCapabilities.hasTransport(1);
        } catch (Exception e10) {
            VKCLogger.INSTANCE.e(e10);
            return true;
        }
    }

    @Override // com.vk.network.kbh.ExtendedNetworkCheck
    public boolean run() {
        this.kdskvkvmocb.lock();
        try {
            if (this.kdskvkvmoce > 0 && System.currentTimeMillis() - this.kdskvkvmoce <= ((Number) this.kdskvkvmocg.getValue()).longValue()) {
                kdskvkvmocb();
                this.kdskvkvmocc.await();
                boolean z10 = this.kdskvkvmocd;
                this.kdskvkvmocb.unlock();
                return z10;
            }
            this.kdskvkvmocb.lock();
            try {
                kdskvkvmoca();
                this.kdskvkvmoce = System.currentTimeMillis();
                this.kdskvkvmocc.signalAll();
                this.kdskvkvmocf = null;
                this.kdskvkvmocb.unlock();
                return this.kdskvkvmocd;
            } finally {
                this.kdskvkvmocb.unlock();
            }
        } catch (Throwable th2) {
            this.kdskvkvmocb.unlock();
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void kdskvkvmoca(VkSdkExtendedNetworkCheck vkSdkExtendedNetworkCheck) {
        vkSdkExtendedNetworkCheck.kdskvkvmocb.lock();
        try {
            vkSdkExtendedNetworkCheck.kdskvkvmoca();
            vkSdkExtendedNetworkCheck.kdskvkvmoce = System.currentTimeMillis();
            vkSdkExtendedNetworkCheck.kdskvkvmocc.signalAll();
            vkSdkExtendedNetworkCheck.kdskvkvmocf = null;
        } finally {
            vkSdkExtendedNetworkCheck.kdskvkvmocb.unlock();
        }
    }

    private final void kdskvkvmoca() {
        Object objM13123constructorimpl;
        boolean z10 = true;
        try {
            Result.Companion companion = Result.INSTANCE;
            int i10 = -1;
            int i11 = isWifi() ? -1 : 0;
            if (!UnstableNetworkDetector.getCurrentTunnelEnabled()) {
                i10 = 0;
            }
            int i12 = (i11 & 4) | (i10 & 1);
            CustomApiRequest.Builder builderWithHeaders = CustomApiRequest.Builder.INSTANCE.fromUrl("https://" + VKApiConfig.INSTANCE.getDEFAULT_API_DOMAIN()).withMethodName("method/account.getToggles").withHeaders(MapsKt.mapOf(TuplesKt.to("X-Check-Network", String.valueOf(i12))));
            SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
            VKAccessTokenProvider anonymousTokenProvider = superappApiCore.getAnonymousTokenProvider();
            String token = anonymousTokenProvider != null ? anonymousTokenProvider.getToken() : null;
            if (token == null) {
                token = "";
            }
            objM13123constructorimpl = Result.m13123constructorimpl(builderWithHeaders.withParams(MapsKt.mapOf(TuplesKt.to("access_token", token), TuplesKt.to("api_id", String.valueOf(superappApiCore.getApiAppId())))).build().toResponse());
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m13129isSuccessimpl(objM13123constructorimpl)) {
            Response response = (Response) objM13123constructorimpl;
            if (!Intrinsics.areEqual(Response.header$default(response, "x-connection-type", null, 2, null), "unstable") && !Intrinsics.areEqual(Response.header$default(response, "X-Connection-Type", null, 2, null), "unstable")) {
                z10 = false;
            }
            this.kdskvkvmocd = z10;
        }
        if (Result.m13126exceptionOrNullimpl(objM13123constructorimpl) != null) {
            this.kdskvkvmocd = false;
        }
    }
}
