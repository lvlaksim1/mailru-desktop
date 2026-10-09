package ru.mail.util.push;

import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationChannelGroupCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u001a\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0006H&J)\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000fH&¨\u0006\u0010À\u0006\u0003"}, d2 = {"Lru/mail/util/push/NotificationChannelManager;", "", "getIndependentNotificationChannels", "", "Landroidx/core/app/NotificationChannelCompat;", "getNotificationChannelsByGroup", "", "Landroidx/core/app/NotificationChannelGroupCompat;", "updateNotificationChannel", "", RemoteMessageConst.Notification.CHANNEL_ID, "", "buildChannel", "Lkotlin/Function1;", "Landroidx/core/app/NotificationChannelCompat$Builder;", "Lkotlin/ExtensionFunctionType;", "notifications_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NotificationChannelManager {
    @NotNull
    List<NotificationChannelCompat> getIndependentNotificationChannels();

    @NotNull
    Map<NotificationChannelGroupCompat, List<NotificationChannelCompat>> getNotificationChannelsByGroup();

    void updateNotificationChannel(@NotNull String channelId, @NotNull Function1<? super NotificationChannelCompat.Builder, ? extends NotificationChannelCompat.Builder> buildChannel);
}
