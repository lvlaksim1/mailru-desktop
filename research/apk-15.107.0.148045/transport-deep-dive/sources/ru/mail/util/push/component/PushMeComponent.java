package ru.mail.util.push.component;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.deviceinfo.di.DeviceInfoEntryPoint;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.pusher.PushMeSDKPusherTransport;
import ru.mail.util.push.pusher.PushMeTransport;
import ru.mail.util.push.pusher.PusherTransport;
import ru.mail.util.push.vkpns.component.VkpnsComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0013\u001a\u00020\u0014H\u0014¨\u0006\u0015"}, d2 = {"Lru/mail/util/push/component/PushMeComponent;", "Lru/mail/util/push/component/BasePushComponent;", "context", "Landroid/content/Context;", "isPushMeSdkInitEnabled", "", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "pushMessagesTransports", "", "Lru/mail/util/push/PushMessagesTransport;", "pushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "vkpnsComponent", "Lru/mail/util/push/vkpns/component/VkpnsComponent;", "<init>", "(Landroid/content/Context;ZLru/mail/config/section/RuStoreSdkDto;Ljava/util/Collection;Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;Lru/mail/util/push/notifier/PushMessageReceivedNotifier;Lru/mail/util/push/vkpns/component/VkpnsComponent;)V", "createPusherTransport", "Lru/mail/util/push/pusher/PusherTransport;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushMeComponent extends BasePushComponent {
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PushMeComponent(@NotNull Context context, boolean z10, @NotNull RuStoreSdkDto ruStoreConfig, @NotNull Collection<? extends PushMessagesTransport> pushMessagesTransports, @NotNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier, @NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier, @NotNull VkpnsComponent vkpnsComponent) {
        super(context, z10, ruStoreConfig, pushMessagesTransports, pushTokenRefreshedNotifier, pushMessageReceivedNotifier, vkpnsComponent);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ruStoreConfig, "ruStoreConfig");
        Intrinsics.checkNotNullParameter(pushMessagesTransports, "pushMessagesTransports");
        Intrinsics.checkNotNullParameter(pushTokenRefreshedNotifier, "pushTokenRefreshedNotifier");
        Intrinsics.checkNotNullParameter(pushMessageReceivedNotifier, "pushMessageReceivedNotifier");
        Intrinsics.checkNotNullParameter(vkpnsComponent, "vkpnsComponent");
    }

    @Override // ru.mail.util.push.component.BasePushComponent
    @NotNull
    protected PusherTransport createPusherTransport() {
        return getIsPushMeSdkInitEnabled() ? new PushMeSDKPusherTransport(getPushMessagesTransports(), getContext(), DeviceInfoEntryPoint.INSTANCE.deviceIdProvider(getContext()), getRuStoreConfig().isPushSdkEnabledForAllApps()) : new PushMeTransport(getPushMessagesTransports(), getContext(), DeviceInfoEntryPoint.INSTANCE.deviceIdProvider(getContext()));
    }
}
