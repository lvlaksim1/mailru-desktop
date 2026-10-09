package ru.mail.portal.app.adapter.notifications.settings;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0003H&¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/portal/app/adapter/notifications/settings/NotificationSettingsStorage;", "", "areNotificationsEnabledInApplicationSettings", "", "key", "", "defaultValue", "setNotificationsEnabledInApplicationSettings", "", "enabled", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NotificationSettingsStorage {
    boolean areNotificationsEnabledInApplicationSettings(@NotNull String key, boolean defaultValue);

    void setNotificationsEnabledInApplicationSettings(@NotNull String key, boolean enabled);
}
