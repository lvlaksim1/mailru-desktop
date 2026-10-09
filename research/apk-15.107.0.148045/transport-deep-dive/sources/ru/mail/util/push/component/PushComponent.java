package ru.mail.util.push.component;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.pusher.PusherTransport;
import ru.mail.util.push.vkpns.component.VkpnsComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/component/PushComponent;", "", "getPusherTransport", "Lru/mail/util/push/pusher/PusherTransport;", "getPushMessagesTransports", "", "Lru/mail/util/push/PushMessagesTransport;", "getPushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "getPushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "getVkpnsComponent", "Lru/mail/util/push/vkpns/component/VkpnsComponent;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushComponent {
    @NotNull
    PushMessageReceivedNotifier getPushMessageReceivedNotifier();

    @NotNull
    Collection<PushMessagesTransport> getPushMessagesTransports();

    @NotNull
    PushTokenRefreshedNotifier getPushTokenRefreshedNotifier();

    @NotNull
    PusherTransport getPusherTransport();

    @NotNull
    VkpnsComponent getVkpnsComponent();
}
