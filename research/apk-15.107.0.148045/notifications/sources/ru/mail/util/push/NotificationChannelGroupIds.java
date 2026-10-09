package ru.mail.util.push;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/util/push/NotificationChannelGroupIds;", "", "getNewMessageGroupChannelId", "", "getCalendarGroupChannelId", "getWalletGroupChannelId", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NotificationChannelGroupIds {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        @NotNull
        public static String getCalendarGroupChannelId(@NotNull NotificationChannelGroupIds notificationChannelGroupIds) {
            return NotificationChannelGroupIds.super.getCalendarGroupChannelId();
        }

        @Deprecated
        @NotNull
        public static String getNewMessageGroupChannelId(@NotNull NotificationChannelGroupIds notificationChannelGroupIds) {
            return NotificationChannelGroupIds.super.getNewMessageGroupChannelId();
        }

        @Deprecated
        @NotNull
        public static String getWalletGroupChannelId(@NotNull NotificationChannelGroupIds notificationChannelGroupIds) {
            return NotificationChannelGroupIds.super.getWalletGroupChannelId();
        }
    }

    @NotNull
    default String getCalendarGroupChannelId() {
        return "";
    }

    @NotNull
    default String getNewMessageGroupChannelId() {
        return "";
    }

    @NotNull
    default String getWalletGroupChannelId() {
        return "";
    }
}
