package com.vk.pushme.network;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lcom/vk/pushme/network/HostInfoProvider;", "", "scheme", "", "getScheme", "()Ljava/lang/String;", "host", "getHost", "getApiPathByVersion", "version", "Lcom/vk/pushme/network/ApiVersion;", "port", "", "getPort", "()Ljava/lang/Integer;", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface HostInfoProvider {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes19.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        @Nullable
        public static Integer getPort(@NotNull HostInfoProvider hostInfoProvider) {
            return HostInfoProvider.super.getPort();
        }
    }

    @NotNull
    String getApiPathByVersion(@NotNull ApiVersion version);

    @NotNull
    String getHost();

    @Nullable
    default Integer getPort() {
        return null;
    }

    @NotNull
    String getScheme();
}
