package ru.mail.portal.app.adapter.notifications;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.database.MergeThreadReprDelegate;
import ru.mail.util.log.Logger;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J:\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u0015\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u001a\u0010\u001b\u001a\u00020\u00102\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lru/mail/portal/app/adapter/notifications/PortalNotificationsChannelsManagerImpl;", "Lru/mail/portal/app/adapter/notifications/PortalNotificationsChannelsManager;", "context", "Landroid/content/Context;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Landroid/content/Context;Lru/mail/util/log/Logger;)V", "createOrUpdateChannel", "", "id", "", "name", PushProcessor.DATAKEY_IMPORTANCE, "", "soundEnabled", "", "vibrationEnabled", "groupId", "deleteChannel", "getChannelImportance", "createNotificationChannelGroup", "getNotificationChannelsIds", "", "areNotificationsEnabled", "getNotificationManager", "Landroid/app/NotificationManager;", "channelExists", "channel", "Landroid/app/NotificationChannel;", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPortalNotificationsChannelsManagerImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalNotificationsChannelsManagerImpl.kt\nru/mail/portal/app/adapter/notifications/PortalNotificationsChannelsManagerImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Context.kt\nandroidx/core/content/ContextKt\n*L\n1#1,112:1\n1869#2,2:113\n43#3:115\n*S KotlinDebug\n*F\n+ 1 PortalNotificationsChannelsManagerImpl.kt\nru/mail/portal/app/adapter/notifications/PortalNotificationsChannelsManagerImpl\n*L\n82#1:113,2\n96#1:115\n*E\n"})
public final class PortalNotificationsChannelsManagerImpl implements PortalNotificationsChannelsManager {

    @NotNull
    private final Context context;

    @NotNull
    private final Logger logger;

    public PortalNotificationsChannelsManagerImpl(@NotNull Context context, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.context = context;
        this.logger = logger;
    }

    private final boolean channelExists(NotificationChannel channel, String id2) {
        if (channel != null) {
            return true;
        }
        Logger.info$default(this.logger, "channel with id \"" + id2 + "\" not exist", null, 2, null);
        return false;
    }

    private final NotificationManager getNotificationManager() {
        NotificationManager notificationManager = (NotificationManager) ContextCompat.getSystemService(this.context, NotificationManager.class);
        if (notificationManager == null) {
            Logger.error$default(this.logger, "notificationManager is null!", null, 2, null);
        }
        return notificationManager;
    }

    @Override // ru.mail.portal.app.adapter.notifications.PortalNotificationsChannelsManager
    public boolean areNotificationsEnabled() {
        NotificationManager notificationManager = getNotificationManager();
        if (notificationManager != null) {
            return notificationManager.areNotificationsEnabled();
        }
        return false;
    }

    @Override // ru.mail.portal.app.adapter.notifications.PortalNotificationsChannelsManager
    public void createNotificationChannelGroup(@NotNull String id2, @NotNull String name) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        NotificationManager notificationManager = getNotificationManager();
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(id2, name);
        Logger.info$default(this.logger, "creating notification channel group with id \"" + id2 + "\", name \"" + name + "\" ", null, 2, null);
        if (notificationManager != null) {
            notificationManager.createNotificationChannelGroup(notificationChannelGroup);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.media.AudioAttributes, android.net.Uri] */
    /* JADX WARN: Type inference failed for: r2v3 */
    @Override // ru.mail.portal.app.adapter.notifications.PortalNotificationsChannelsManager
    public void createOrUpdateChannel(@NotNull String id2, @NotNull String name, int importance, boolean soundEnabled, boolean vibrationEnabled, @Nullable String groupId) {
        ?? r10;
        NotificationChannel notificationChannel;
        NotificationChannel notificationChannel2;
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        NotificationManager notificationManager = getNotificationManager();
        NotificationChannel notificationChannel3 = notificationManager != null ? notificationManager.getNotificationChannel(id2) : null;
        if (notificationChannel3 == null) {
            notificationChannel2 = new NotificationChannel(id2, name, importance);
            r10 = 0;
            Logger.info$default(this.logger, "Creating channel with id " + id2 + ", name \"" + name + "\", importance \"" + importance + "\", sound enabled: " + soundEnabled + ", vibration enabled: " + vibrationEnabled + ", groupId = \"" + groupId + "\"", null, 2, null);
        } else {
            notificationChannel3.setName(name);
            r10 = 0;
            Logger.info$default(this.logger, "Updating channel with id " + id2 + ", name \"" + name + "\", importance \"" + importance + "\", sound enabled: " + soundEnabled + ", vibration enabled: " + vibrationEnabled + ", groupId = \"" + groupId + "\"", null, 2, null);
        }
        if (groupId != null) {
            notificationChannel = notificationChannel3;
            notificationChannel = notificationChannel2;
            notificationChannel.setGroup(groupId);
        }
        notificationChannel = notificationChannel3;
        notificationChannel = notificationChannel2;
        if (importance >= 3) {
            notificationChannel.enableVibration(vibrationEnabled);
            if (!soundEnabled) {
                notificationChannel.setSound(r10, r10);
            }
        }
        if (notificationManager != 0) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override // ru.mail.portal.app.adapter.notifications.PortalNotificationsChannelsManager
    public void deleteChannel(@NotNull String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        NotificationManager notificationManager = getNotificationManager();
        Logger.info$default(this.logger, "Deleting channel with id " + id2, null, 2, null);
        if (notificationManager != null) {
            notificationManager.deleteNotificationChannel(id2);
        }
    }

    @Override // ru.mail.portal.app.adapter.notifications.PortalNotificationsChannelsManager
    public int getChannelImportance(@NotNull String id2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        NotificationManager notificationManager = getNotificationManager();
        NotificationChannel notificationChannel = notificationManager != null ? notificationManager.getNotificationChannel(id2) : null;
        if (!channelExists(notificationChannel, id2)) {
            return MergeThreadReprDelegate.WRONG_TIME;
        }
        Intrinsics.checkNotNull(notificationChannel);
        return notificationChannel.getImportance();
    }

    @Override // ru.mail.portal.app.adapter.notifications.PortalNotificationsChannelsManager
    @NotNull
    public List<String> getNotificationChannelsIds() {
        NotificationManager notificationManager = getNotificationManager();
        ArrayList arrayList = new ArrayList();
        List<NotificationChannel> notificationChannels = notificationManager != null ? notificationManager.getNotificationChannels() : null;
        if (notificationChannels != null) {
            Iterator<T> it = notificationChannels.iterator();
            while (it.hasNext()) {
                String id2 = ((NotificationChannel) it.next()).getId();
                Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
                arrayList.add(id2);
            }
        }
        return arrayList;
    }
}
