package ru.mail.portal.app.adapter.notifications.settings;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.portal.app.adapter.TabAppAdapter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\bf\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012J$\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\nH&¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings;", "", "init", "", "supportedApps", "", "", "allApps", "Lru/mail/portal/app/adapter/TabAppAdapter;", "areNotificationsEnabledInConfig", "", "appId", "areNotificationsEnabled", "areNotificationsEnabledInApplicationSettings", "areNotificationsEnabledInDeviceSettings", "getChannelId", "setNotificationsEnabledInApplicationSettings", "enabled", "Companion", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PortalNotificationsSettings {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @NotNull
    public static final String DEFAULT_CHANNEL_ID = "portal_push_notifications";

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings$Companion;", "", "<init>", "()V", "DEFAULT_CHANNEL_ID", "", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        public static final String DEFAULT_CHANNEL_ID = "portal_push_notifications";

        private Companion() {
        }
    }

    boolean areNotificationsEnabled(@NotNull String appId);

    boolean areNotificationsEnabledInApplicationSettings(@NotNull String appId);

    boolean areNotificationsEnabledInConfig(@NotNull String appId);

    boolean areNotificationsEnabledInDeviceSettings(@NotNull String appId);

    @NotNull
    String getChannelId(@NotNull String appId);

    void init(@NotNull Collection<String> supportedApps, @NotNull Collection<? extends TabAppAdapter> allApps);

    void setNotificationsEnabledInApplicationSettings(@NotNull String appId, boolean enabled);
}
