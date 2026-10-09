package ru.mail.util.push;

import android.app.NotificationChannel;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/NotificationChannelsInitializer;", "", "initSendingChannel", "", "notificationChannel", "Landroid/app/NotificationChannel;", "initInfoChannel", "initNewMessageUserChannel", "initCalendarNotificationChannel", "initWalletNotificationChannel", "initCallerInfoChannel", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NotificationChannelsInitializer {
    void initCalendarNotificationChannel(@NotNull NotificationChannel notificationChannel);

    void initCallerInfoChannel(@NotNull NotificationChannel notificationChannel);

    void initInfoChannel(@NotNull NotificationChannel notificationChannel);

    void initNewMessageUserChannel(@NotNull NotificationChannel notificationChannel);

    void initSendingChannel(@NotNull NotificationChannel notificationChannel);

    void initWalletNotificationChannel(@NotNull NotificationChannel notificationChannel);
}
