package ru.mail.util.push;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u000e\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\b\u0010\b\u001a\u00020\u0004H&J\b\u0010\t\u001a\u00020\u0004H&J\b\u0010\n\u001a\u00020\u0004H&J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\r\u001a\u00020\u0004H&J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\u000f\u001a\u00020\u0004H&J\b\u0010\u0010\u001a\u00020\u0004H&J\u0012\u0010\u0011\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\u0012\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\u0013\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0007H&J\u0012\u0010\u0014\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u0007H&¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lru/mail/util/push/NotificationChannels;", "Lru/mail/util/push/NotificationChannelsId;", "Lru/mail/util/push/NotificationChannelGroupIds;", "initUserChannels", "", "accounts", "", "", "initNewMessageGroup", "initSendingChannel", "initInfoChannel", "initNewMessageUserChannel", "username", "initCallerInfoChannel", "deleteNewMessageUserChannel", "initCalendarGroup", "initWalletGroup", "initCalendarNotificationChannel", "initWalletNotificationChannel", "deleteCalendarNotificationChannel", "deleteWalletNotificationChannel", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NotificationChannels extends NotificationChannelsId, NotificationChannelGroupIds {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        @NotNull
        public static String getCalendarGroupChannelId(@NotNull NotificationChannels notificationChannels) {
            return NotificationChannels.super.getCalendarGroupChannelId();
        }

        @Deprecated
        @NotNull
        public static String getNewMessageGroupChannelId(@NotNull NotificationChannels notificationChannels) {
            return NotificationChannels.super.getNewMessageGroupChannelId();
        }

        @Deprecated
        @NotNull
        public static String getWalletGroupChannelId(@NotNull NotificationChannels notificationChannels) {
            return NotificationChannels.super.getWalletGroupChannelId();
        }
    }

    void deleteCalendarNotificationChannel(@Nullable String username);

    void deleteNewMessageUserChannel(@Nullable String username);

    void deleteWalletNotificationChannel(@Nullable String username);

    void initCalendarGroup();

    void initCalendarNotificationChannel(@Nullable String username);

    void initCallerInfoChannel();

    void initInfoChannel();

    void initNewMessageGroup();

    void initNewMessageUserChannel(@Nullable String username);

    void initSendingChannel();

    void initUserChannels(@NotNull Collection<String> accounts);

    void initWalletGroup();

    void initWalletNotificationChannel(@Nullable String username);
}
