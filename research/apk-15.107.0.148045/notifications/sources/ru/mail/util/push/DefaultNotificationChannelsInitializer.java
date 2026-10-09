package ru.mail.util.push;

import android.app.NotificationChannel;
import android.content.Context;
import android.media.AudioAttributes;
import android.net.Uri;
import android.provider.Settings;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J*\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lru/mail/util/push/DefaultNotificationChannelsInitializer;", "Lru/mail/util/push/NotificationChannelsInitializer;", "context", "Landroid/content/Context;", "notificationChannelGroupIds", "Lru/mail/util/push/NotificationChannelGroupIds;", "<init>", "(Landroid/content/Context;Lru/mail/util/push/NotificationChannelGroupIds;)V", "initSendingChannel", "", "notificationChannel", "Landroid/app/NotificationChannel;", "initInfoChannel", "initNewMessageUserChannel", "initCalendarNotificationChannel", "initWalletNotificationChannel", "initCallerInfoChannel", "setSound", "channel", "soundUri", "Landroid/net/Uri;", "contentType", "", "usageType", "setChannelDefaultSound", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultNotificationChannelsInitializer implements NotificationChannelsInitializer {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    @NotNull
    private final NotificationChannelGroupIds notificationChannelGroupIds;

    public DefaultNotificationChannelsInitializer(@NotNull Context context, @NotNull NotificationChannelGroupIds notificationChannelGroupIds) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationChannelGroupIds, "notificationChannelGroupIds");
        this.context = context;
        this.notificationChannelGroupIds = notificationChannelGroupIds;
    }

    private final void setChannelDefaultSound(NotificationChannel channel) {
        Uri soundUri = new NotificationConfiguration(this.context).getSoundUri();
        if (soundUri == null) {
            soundUri = Settings.System.DEFAULT_NOTIFICATION_URI;
        }
        setSound(channel, soundUri, 4, 9);
    }

    private final void setSound(NotificationChannel channel, Uri soundUri, int contentType, int usageType) {
        channel.setSound(soundUri, new AudioAttributes.Builder().setContentType(contentType).setUsage(usageType).build());
    }

    @Override // ru.mail.util.push.NotificationChannelsInitializer
    public void initCalendarNotificationChannel(@NotNull NotificationChannel notificationChannel) {
        Intrinsics.checkNotNullParameter(notificationChannel, "notificationChannel");
        notificationChannel.setGroup(this.notificationChannelGroupIds.getCalendarGroupChannelId());
        setChannelDefaultSound(notificationChannel);
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(new NotificationConfiguration(this.context).getLightColor());
    }

    @Override // ru.mail.util.push.NotificationChannelsInitializer
    public void initCallerInfoChannel(@NotNull NotificationChannel notificationChannel) {
        Intrinsics.checkNotNullParameter(notificationChannel, "notificationChannel");
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
    }

    @Override // ru.mail.util.push.NotificationChannelsInitializer
    public void initInfoChannel(@NotNull NotificationChannel notificationChannel) {
        Intrinsics.checkNotNullParameter(notificationChannel, "notificationChannel");
        setSound(notificationChannel, Settings.System.DEFAULT_NOTIFICATION_URI, 4, 5);
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
    }

    @Override // ru.mail.util.push.NotificationChannelsInitializer
    public void initNewMessageUserChannel(@NotNull NotificationChannel notificationChannel) {
        Intrinsics.checkNotNullParameter(notificationChannel, "notificationChannel");
        notificationChannel.setGroup(this.notificationChannelGroupIds.getNewMessageGroupChannelId());
        setChannelDefaultSound(notificationChannel);
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(new NotificationConfiguration(this.context).getLightColor());
    }

    @Override // ru.mail.util.push.NotificationChannelsInitializer
    public void initSendingChannel(@NotNull NotificationChannel notificationChannel) {
        Intrinsics.checkNotNullParameter(notificationChannel, "notificationChannel");
        setSound(notificationChannel, null, 4, 5);
    }

    @Override // ru.mail.util.push.NotificationChannelsInitializer
    public void initWalletNotificationChannel(@NotNull NotificationChannel notificationChannel) {
        Intrinsics.checkNotNullParameter(notificationChannel, "notificationChannel");
        notificationChannel.setGroup(this.notificationChannelGroupIds.getWalletGroupChannelId());
        setChannelDefaultSound(notificationChannel);
        notificationChannel.enableVibration(true);
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(new NotificationConfiguration(this.context).getLightColor());
    }
}
