package ru.mail.util.push;

import android.content.Context;
import ru.mail.dependencies.NotificationsEntryPoint;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class NotificationChannelsCompat {
    public static NotificationChannels from(Context context) {
        return NotificationsEntryPoint.notificationChannels(context);
    }
}
