package com.vk.pushme.network;

import com.vk.pushme.common.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.PreferenceHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ \u0010\u000e\u001a\u00020\u000f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\u0010"}, d2 = {"Lcom/vk/pushme/network/NetworkModule;", "", "<init>", "()V", "providePushMeApi", "Lcom/vk/pushme/network/PushMeApi;", PreferenceHostProvider.URL_PARAM_CLIENT, "Lokhttp3/OkHttpClient;", "logger", "Lcom/vk/pushme/common/Logger;", "debugLogsEnabled", "", "hostInfoProvider", "Lcom/vk/pushme/network/HostInfoProvider;", "provideAnalyticsApi", "Lcom/vk/pushme/network/AnalyticsApi;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NetworkModule {

    @NotNull
    public static final NetworkModule INSTANCE = new NetworkModule();

    private NetworkModule() {
    }

    @NotNull
    public final AnalyticsApi provideAnalyticsApi(@Nullable OkHttpClient client, @NotNull Logger logger, boolean debugLogsEnabled) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        if (client == null) {
            client = HttpClientFactory.INSTANCE.createClient(logger, debugLogsEnabled);
        }
        return new AnalyticsApiImpl(client);
    }

    @NotNull
    public final PushMeApi providePushMeApi(@Nullable OkHttpClient client, @NotNull Logger logger, boolean debugLogsEnabled, @NotNull HostInfoProvider hostInfoProvider) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(hostInfoProvider, "hostInfoProvider");
        if (client == null) {
            client = HttpClientFactory.INSTANCE.createClient(logger, debugLogsEnabled);
        }
        return new PushMeApiImpl(client, hostInfoProvider);
    }
}
