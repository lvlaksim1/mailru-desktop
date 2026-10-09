package ru.mail.logic.sendmessage.queue.notification;

import android.content.Context;
import android.os.Build;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.mails.R;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationChannelManager;", "", "context", "Landroid/content/Context;", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "generationStore", "Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationChannelGenerationStore;", "<init>", "(Landroid/content/Context;Landroidx/core/app/NotificationManagerCompat;Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationChannelGenerationStore;)V", "ensureChannel", "", "rotateBlockedChannel", "currentChannelId", "areNotificationsGloballyEnabled", "", "createChannel", "", RemoteMessageConst.Notification.CHANNEL_ID, "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Singleton
@SourceDebugExtension({"SMAP\nSendQueueNotificationChannelManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SendQueueNotificationChannelManager.kt\nru/mail/logic/sendmessage/queue/notification/SendQueueNotificationChannelManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,62:1\n1#2:63\n*E\n"})
public final class SendQueueNotificationChannelManager {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    @NotNull
    private final SendQueueNotificationChannelGenerationStore generationStore;

    @NotNull
    private final NotificationManagerCompat notificationManager;

    @Inject
    public SendQueueNotificationChannelManager(@ApplicationContext @NotNull Context context, @NotNull NotificationManagerCompat notificationManager, @NotNull SendQueueNotificationChannelGenerationStore generationStore) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationManager, "notificationManager");
        Intrinsics.checkNotNullParameter(generationStore, "generationStore");
        this.context = context;
        this.notificationManager = notificationManager;
        this.generationStore = generationStore;
    }

    private final boolean areNotificationsGloballyEnabled() {
        return (Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(this.context, "android.permission.POST_NOTIFICATIONS") == 0) && this.notificationManager.areNotificationsEnabled();
    }

    private final void createChannel(String channelId) {
        NotificationChannelCompat notificationChannelCompatBuild = new NotificationChannelCompat.Builder(channelId, 2).setName(this.context.getString(R.string.send_queue_channel_name)).setDescription(this.context.getString(R.string.send_queue_channel_description)).setShowBadge(false).build();
        Intrinsics.checkNotNullExpressionValue(notificationChannelCompatBuild, "build(...)");
        this.notificationManager.createNotificationChannel(notificationChannelCompatBuild);
    }

    private final String rotateBlockedChannel(String currentChannelId) {
        if (!areNotificationsGloballyEnabled()) {
            return currentChannelId;
        }
        String strRotateChannelId = this.generationStore.rotateChannelId();
        this.notificationManager.deleteNotificationChannel(currentChannelId);
        createChannel(strRotateChannelId);
        return strRotateChannelId;
    }

    @NotNull
    public final synchronized String ensureChannel() {
        String strCurrentChannelId = this.generationStore.currentChannelId();
        NotificationChannelCompat notificationChannelCompat = this.notificationManager.getNotificationChannelCompat(strCurrentChannelId);
        if (notificationChannelCompat == null) {
            createChannel(strCurrentChannelId);
            return strCurrentChannelId;
        }
        if (notificationChannelCompat.getImportance() == 0) {
            strCurrentChannelId = rotateBlockedChannel(strCurrentChannelId);
        }
        return strCurrentChannelId;
    }
}
