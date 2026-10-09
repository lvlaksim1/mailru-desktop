package ru.mail.util.push.vkpns.component;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.PushKitWrapper;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.vkpns.VkpnsHostResolver;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lru/mail/util/push/vkpns/component/VkpnsComponent;", "", "setPushTokenListener", "", "pushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "setMessagesListener", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "setErrorListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lru/mail/util/push/vkpns/component/VkpnsErrorListener;", "getPushKitWrapper", "Lru/mail/util/push/PushKitWrapper;", "getHostResolver", "Lru/mail/util/push/vkpns/VkpnsHostResolver;", "isShowPushEnabled", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface VkpnsComponent {
    @NotNull
    VkpnsHostResolver getHostResolver();

    @NotNull
    /* JADX INFO: renamed from: getPushKitWrapper */
    PushKitWrapper mo15888getPushKitWrapper();

    boolean isShowPushEnabled();

    void setErrorListener(@NotNull VkpnsErrorListener listener);

    void setMessagesListener(@NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier);

    void setPushTokenListener(@NotNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier);
}
