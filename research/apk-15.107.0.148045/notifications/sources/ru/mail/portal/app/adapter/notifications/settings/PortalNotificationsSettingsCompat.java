package ru.mail.portal.app.adapter.notifications.settings;

import android.content.Context;
import java.util.Collection;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.portal.app.adapter.StringProviderImpl;
import ru.mail.portal.app.adapter.TabAppAdapter;
import ru.mail.portal.app.adapter.notifications.PortalNotificationsChannelsManagerImpl;
import ru.mail.portal.app.adapter.notifications.config.ExperimentNotificationConfig;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ$\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015H\u0016J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J\u0010\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J\u0018\u0010 \u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u001aH\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\""}, d2 = {"Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettingsCompat;", "Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings;", "context", "Landroid/content/Context;", "logger", "Lru/mail/util/log/Logger;", "experimentNotificationConfig", "Lru/mail/portal/app/adapter/notifications/config/ExperimentNotificationConfig;", "<init>", "(Landroid/content/Context;Lru/mail/util/log/Logger;Lru/mail/portal/app/adapter/notifications/config/ExperimentNotificationConfig;)V", "settingsStorage", "Lru/mail/portal/app/adapter/notifications/settings/NotificationSettingsStorageSharedPreferenceImpl;", "portalNotificationsSettings", "Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettingsImpl;", "getPortalNotificationsSettings", "()Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettingsImpl;", "portalNotificationsSettings$delegate", "Lkotlin/Lazy;", "init", "", "supportedApps", "", "", "allApps", "Lru/mail/portal/app/adapter/TabAppAdapter;", "areNotificationsEnabledInConfig", "", "appId", "areNotificationsEnabled", "areNotificationsEnabledInApplicationSettings", "areNotificationsEnabledInDeviceSettings", "getChannelId", "setNotificationsEnabledInApplicationSettings", "enabled", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PortalNotificationsSettingsCompat implements PortalNotificationsSettings {

    /* JADX INFO: renamed from: portalNotificationsSettings$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy portalNotificationsSettings;

    @NotNull
    private final NotificationSettingsStorageSharedPreferenceImpl settingsStorage;

    public PortalNotificationsSettingsCompat(@NotNull final Context context, @NotNull final Logger logger, @Nullable final ExperimentNotificationConfig experimentNotificationConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.settingsStorage = new NotificationSettingsStorageSharedPreferenceImpl(context);
        this.portalNotificationsSettings = LazyKt.lazy(new Function0() { // from class: ru.mail.portal.app.adapter.notifications.settings.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PortalNotificationsSettingsCompat.portalNotificationsSettings_delegate$lambda$0(context, logger, this, experimentNotificationConfig);
            }
        });
    }

    private final PortalNotificationsSettingsImpl getPortalNotificationsSettings() {
        return (PortalNotificationsSettingsImpl) this.portalNotificationsSettings.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PortalNotificationsSettingsImpl portalNotificationsSettings_delegate$lambda$0(Context context, Logger logger, PortalNotificationsSettingsCompat portalNotificationsSettingsCompat, ExperimentNotificationConfig experimentNotificationConfig) {
        return new PortalNotificationsSettingsImpl(portalNotificationsSettingsCompat.settingsStorage, new PortalNotificationsChannelsManagerImpl(context, logger), new StringProviderImpl(context), experimentNotificationConfig, logger);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabled(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return getPortalNotificationsSettings().areNotificationsEnabled(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInApplicationSettings(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return getPortalNotificationsSettings().areNotificationsEnabledInApplicationSettings(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInConfig(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return getPortalNotificationsSettings().areNotificationsEnabledInConfig(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInDeviceSettings(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return getPortalNotificationsSettings().areNotificationsEnabledInDeviceSettings(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    @NotNull
    public String getChannelId(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return getPortalNotificationsSettings().getChannelId(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public void init(@NotNull Collection<String> supportedApps, @NotNull Collection<? extends TabAppAdapter> allApps) {
        Intrinsics.checkNotNullParameter(supportedApps, "supportedApps");
        Intrinsics.checkNotNullParameter(allApps, "allApps");
        getPortalNotificationsSettings().init(supportedApps, allApps);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public void setNotificationsEnabledInApplicationSettings(@NotNull String appId, boolean enabled) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        getPortalNotificationsSettings().setNotificationsEnabledInApplicationSettings(appId, enabled);
    }
}
