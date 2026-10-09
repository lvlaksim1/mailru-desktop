package ru.mail.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.deviceinfo.di.DeviceInfoEntryPoint;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.PushType;
import ru.mail.util.push.component.PushComponent;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.pusher.PushMeSDKPusherTransport;
import ru.mail.util.push.pusher.PusherTransport;
import ru.mail.util.push.vkpns.component.VkpnsComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\n\u001a\u00020\u0007H\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lru/mail/push/MailSdkPushComponent;", "Lru/mail/util/push/component/PushComponent;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "transport", "Lru/mail/util/push/pusher/PusherTransport;", "getTransport", "()Lru/mail/util/push/pusher/PusherTransport;", "getPusherTransport", "getPushMessagesTransports", "", "Lru/mail/util/push/PushMessagesTransport;", "getPushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "getPushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "getVkpnsComponent", "Lru/mail/util/push/vkpns/component/VkpnsComponent;", "createTransport", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MailSdkPushComponent implements PushComponent {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    @NotNull
    private final PusherTransport transport;

    public MailSdkPushComponent(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.transport = createTransport();
    }

    private final PusherTransport createTransport() {
        Set setEmptySet = SetsKt.emptySet();
        Context context = this.context;
        return new PushMeSDKPusherTransport(setEmptySet, context, DeviceInfoEntryPoint.INSTANCE.deviceIdProvider(context), false);
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    public PushMessageReceivedNotifier getPushMessageReceivedNotifier() {
        return new PushMessageReceivedNotifier() { // from class: ru.mail.push.MailSdkPushComponent.getPushMessageReceivedNotifier.1
            @Override // ru.mail.util.push.notifier.PushMessageReceivedNotifier
            public void addListener(PushMessageReceivedNotifier.Listener listener, PushType pushType, boolean filterDuplicates) {
                Intrinsics.checkNotNullParameter(listener, "listener");
                Intrinsics.checkNotNullParameter(pushType, "pushType");
            }

            @Override // ru.mail.util.push.notifier.PushMessageReceivedNotifier
            public void onMessageReceived(Map<String, String> data, PushType pushType, String from, Long pushMeSdkPushId) {
                Intrinsics.checkNotNullParameter(data, "data");
                Intrinsics.checkNotNullParameter(pushType, "pushType");
            }

            @Override // ru.mail.util.push.notifier.PushMessageReceivedNotifier
            public void removeListener(PushMessageReceivedNotifier.Listener listener, PushType pushType) {
                Intrinsics.checkNotNullParameter(listener, "listener");
                Intrinsics.checkNotNullParameter(pushType, "pushType");
            }
        };
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    public Collection<PushMessagesTransport> getPushMessagesTransports() {
        return SetsKt.emptySet();
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    public PushTokenRefreshedNotifier getPushTokenRefreshedNotifier() {
        return new PushTokenRefreshedNotifier() { // from class: ru.mail.push.MailSdkPushComponent.getPushTokenRefreshedNotifier.1
            @Override // ru.mail.util.push.notifier.PushTokenRefreshedNotifier
            public void addListener(PushTokenRefreshedNotifier.Listener listener, PushType pushType) {
                Intrinsics.checkNotNullParameter(listener, "listener");
                Intrinsics.checkNotNullParameter(pushType, "pushType");
            }

            @Override // ru.mail.util.push.notifier.PushTokenRefreshedNotifier
            public void onNewToken(String token, PushType pushType) {
                Intrinsics.checkNotNullParameter(token, "token");
                Intrinsics.checkNotNullParameter(pushType, "pushType");
            }

            @Override // ru.mail.util.push.notifier.PushTokenRefreshedNotifier
            public void removeListener(PushTokenRefreshedNotifier.Listener listener, PushType pushType) {
                Intrinsics.checkNotNullParameter(listener, "listener");
                Intrinsics.checkNotNullParameter(pushType, "pushType");
            }
        };
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    /* JADX INFO: renamed from: getPusherTransport, reason: from getter */
    public PusherTransport getTransport() {
        return this.transport;
    }

    @NotNull
    public final PusherTransport getTransport() {
        return this.transport;
    }

    @Override // ru.mail.util.push.component.PushComponent
    @NotNull
    public VkpnsComponent getVkpnsComponent() {
        return new VkpnsComponentStub(this.context);
    }
}
