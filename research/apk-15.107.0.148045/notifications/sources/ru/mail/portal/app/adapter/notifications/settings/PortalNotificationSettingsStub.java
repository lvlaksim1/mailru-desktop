package ru.mail.portal.app.adapter.notifications.settings;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.portal.app.adapter.TabAppAdapter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0018\u0010\u0012\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\fH\u0016¨\u0006\u0014"}, d2 = {"Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationSettingsStub;", "Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings;", "<init>", "()V", "init", "", "supportedApps", "", "", "allApps", "Lru/mail/portal/app/adapter/TabAppAdapter;", "areNotificationsEnabledInConfig", "", "appId", "areNotificationsEnabled", "areNotificationsEnabledInApplicationSettings", "areNotificationsEnabledInDeviceSettings", "getChannelId", "setNotificationsEnabledInApplicationSettings", "enabled", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PortalNotificationSettingsStub implements PortalNotificationsSettings {

    @NotNull
    public static final PortalNotificationSettingsStub INSTANCE = new PortalNotificationSettingsStub();

    private PortalNotificationSettingsStub() {
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabled(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return false;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInApplicationSettings(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return false;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInConfig(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return false;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInDeviceSettings(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return false;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    @NotNull
    public String getChannelId(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return "";
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public void init(@NotNull Collection<String> supportedApps, @NotNull Collection<? extends TabAppAdapter> allApps) {
        Intrinsics.checkNotNullParameter(supportedApps, "supportedApps");
        Intrinsics.checkNotNullParameter(allApps, "allApps");
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public void setNotificationsEnabledInApplicationSettings(@NotNull String appId, boolean enabled) {
        Intrinsics.checkNotNullParameter(appId, "appId");
    }
}
