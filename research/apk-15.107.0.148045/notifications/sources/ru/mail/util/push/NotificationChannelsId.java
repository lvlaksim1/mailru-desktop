package ru.mail.util.push;

import com.huawei.hms.push.constant.RemoteMessageConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0010\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0011\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lru/mail/util/push/NotificationChannelsId;", "", "isSendingChannelId", "", RemoteMessageConst.Notification.CHANNEL_ID, "", "isWalletChannelId", "getSendingChannelId", "isInfoChannelId", "getInfoChannelId", "isNewMessageChannelId", "getNewMessageChannelId", "username", "isCallerInfoChannelId", "getCallerInfoChannelId", "isCalendarNotificationChanelId", "getCalendarNotificationChanelId", "getWalletNotificationChanelId", "notifications_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NotificationChannelsId {
    @NotNull
    String getCalendarNotificationChanelId(@Nullable String username);

    @NotNull
    String getCallerInfoChannelId();

    @NotNull
    String getInfoChannelId();

    @NotNull
    String getNewMessageChannelId(@Nullable String username);

    @NotNull
    String getSendingChannelId();

    @NotNull
    String getWalletNotificationChanelId(@Nullable String username);

    boolean isCalendarNotificationChanelId(@NotNull String channelId);

    boolean isCallerInfoChannelId(@NotNull String channelId);

    boolean isInfoChannelId(@NotNull String channelId);

    boolean isNewMessageChannelId(@NotNull String channelId);

    boolean isSendingChannelId(@NotNull String channelId);

    boolean isWalletChannelId(@NotNull String channelId);
}
