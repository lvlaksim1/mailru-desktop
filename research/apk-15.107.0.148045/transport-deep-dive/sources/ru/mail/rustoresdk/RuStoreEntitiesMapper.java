package ru.mail.rustoresdk;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.mail.rustoresdk.message.VkpnsRemoteMessage;
import ru.rustore.sdk.pushclient.messaging.exception.RuStorePushClientException;
import ru.rustore.sdk.pushclient.messaging.model.RemoteMessage;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0004\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\t¨\u0006\n"}, d2 = {"Lru/mail/rustoresdk/RuStoreEntitiesMapper;", "", "<init>", "()V", BlockParser.MAP_TYPE, "Lru/mail/rustoresdk/message/VkpnsRemoteMessage;", "from", "Lru/rustore/sdk/pushclient/messaging/model/RemoteMessage;", "Lru/mail/rustoresdk/VkpnsException;", "Lru/rustore/sdk/pushclient/messaging/exception/RuStorePushClientException;", "rustore-sdk-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuStoreEntitiesMapper {

    @NotNull
    public static final RuStoreEntitiesMapper INSTANCE = new RuStoreEntitiesMapper();

    private RuStoreEntitiesMapper() {
    }

    @NotNull
    public final VkpnsRemoteMessage map(@NotNull RemoteMessage from) {
        Intrinsics.checkNotNullParameter(from, "from");
        return new VkpnsRemoteMessage(from.getData());
    }

    @NotNull
    public final VkpnsException map(@NotNull RuStorePushClientException from) {
        Intrinsics.checkNotNullParameter(from, "from");
        if (from instanceof RuStorePushClientException.UnauthorizedException) {
            return new VkpnsException.UnauthorizedException();
        }
        if (from instanceof RuStorePushClientException.HostAppNotInstalledException) {
            return new VkpnsException.HostAppNotInstalledException();
        }
        if (from instanceof RuStorePushClientException.HostAppBackgroundWorkPermissionNotGranted) {
            return new VkpnsException.HostAppBackgroundWorkPermissionNotGranted();
        }
        throw new NoWhenBranchMatchedException();
    }
}
