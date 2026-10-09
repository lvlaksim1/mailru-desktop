package ru.mail.util.push;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\u0005\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/MutableNotificationChannelsId;", "Lru/mail/util/push/NotificationChannelsId;", "updateSendingChannelId", "", "updateInfoChannelId", "updateNewMessageChannelId", "username", "", "updateCallerInfoChannelId", "updateCalendarNotificationChanelId", "updateWalletNotificationChanelId", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface MutableNotificationChannelsId extends NotificationChannelsId {
    void updateCalendarNotificationChanelId(@Nullable String username);

    void updateCallerInfoChannelId();

    void updateInfoChannelId();

    void updateNewMessageChannelId(@Nullable String username);

    void updateSendingChannelId();

    void updateWalletNotificationChanelId(@Nullable String username);
}
