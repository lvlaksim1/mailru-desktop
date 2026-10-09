package ru.mail.util.push;

import kotlin.Metadata;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/util/push/NotificationShowManager;", "", "isShowNotifications", "", "updateShowNotifications", "", "enabled", "notifications_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NotificationShowManager {
    boolean isShowNotifications();

    void updateShowNotifications(boolean enabled);
}
