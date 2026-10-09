package ru.mail.portal.app.adapter.notifications;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JB\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011H&J\b\u0010\u0012\u001a\u00020\nH&¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lru/mail/portal/app/adapter/notifications/PortalNotificationsChannelsManager;", "", "createOrUpdateChannel", "", "id", "", "name", PushProcessor.DATAKEY_IMPORTANCE, "", "soundEnabled", "", "vibrationEnabled", "groupId", "deleteChannel", "getChannelImportance", "createNotificationChannelGroup", "getNotificationChannelsIds", "", "areNotificationsEnabled", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PortalNotificationsChannelsManager {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void createOrUpdateChannel$default(PortalNotificationsChannelsManager portalNotificationsChannelsManager, String str, String str2, int i10, boolean z10, boolean z11, String str3, int i11, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createOrUpdateChannel");
        }
        if ((i11 & 4) != 0) {
            i10 = 3;
        }
        int i12 = i10;
        if ((i11 & 8) != 0) {
            z10 = true;
        }
        boolean z12 = z10;
        if ((i11 & 16) != 0) {
            z11 = false;
        }
        boolean z13 = z11;
        if ((i11 & 32) != 0) {
            str3 = null;
        }
        portalNotificationsChannelsManager.createOrUpdateChannel(str, str2, i12, z12, z13, str3);
    }

    boolean areNotificationsEnabled();

    void createNotificationChannelGroup(@NotNull String id2, @NotNull String name);

    void createOrUpdateChannel(@NotNull String id2, @NotNull String name, int importance, boolean soundEnabled, boolean vibrationEnabled, @Nullable String groupId);

    void deleteChannel(@NotNull String id2);

    int getChannelImportance(@NotNull String id2);

    @NotNull
    List<String> getNotificationChannelsIds();
}
