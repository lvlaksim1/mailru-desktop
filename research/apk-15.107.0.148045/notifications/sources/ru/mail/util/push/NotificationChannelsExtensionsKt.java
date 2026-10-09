package ru.mail.util.push;

import androidx.core.app.NotificationChannelCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"copyWithNewId", "Landroidx/core/app/NotificationChannelCompat$Builder;", "Landroidx/core/app/NotificationChannelCompat;", "newChannelId", "", "mails_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class NotificationChannelsExtensionsKt {
    @NotNull
    public static final NotificationChannelCompat.Builder copyWithNewId(@NotNull NotificationChannelCompat notificationChannelCompat, @NotNull String newChannelId) {
        Intrinsics.checkNotNullParameter(notificationChannelCompat, "<this>");
        Intrinsics.checkNotNullParameter(newChannelId, "newChannelId");
        NotificationChannelCompat.Builder vibrationEnabled = new NotificationChannelCompat.Builder(newChannelId, notificationChannelCompat.getImportance()).setName(notificationChannelCompat.getName()).setDescription(notificationChannelCompat.getDescription()).setGroup(notificationChannelCompat.getGroup()).setShowBadge(notificationChannelCompat.canShowBadge()).setSound(notificationChannelCompat.getSound(), notificationChannelCompat.getAudioAttributes()).setLightsEnabled(notificationChannelCompat.shouldShowLights()).setLightColor(notificationChannelCompat.getLightColor()).setVibrationEnabled(notificationChannelCompat.shouldVibrate());
        Intrinsics.checkNotNullExpressionValue(vibrationEnabled, "setVibrationEnabled(...)");
        long[] vibrationPattern = notificationChannelCompat.getVibrationPattern();
        if (vibrationPattern != null) {
            vibrationEnabled.setVibrationPattern(vibrationPattern);
        }
        String parentChannelId = notificationChannelCompat.getParentChannelId();
        String conversationId = notificationChannelCompat.getConversationId();
        if (parentChannelId != null && parentChannelId.length() != 0 && conversationId != null && conversationId.length() != 0) {
            vibrationEnabled.setConversationId(parentChannelId, conversationId);
        }
        return vibrationEnabled;
    }
}
