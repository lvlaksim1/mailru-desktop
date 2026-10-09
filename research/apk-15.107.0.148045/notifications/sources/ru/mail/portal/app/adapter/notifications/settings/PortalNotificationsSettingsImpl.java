package ru.mail.portal.app.adapter.notifications.settings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.portal.app.adapter.StringProvider;
import ru.mail.portal.app.adapter.TabAppAdapter;
import ru.mail.portal.app.adapter.notifications.PortalNotificationsChannelsManager;
import ru.mail.portal.app.adapter.notifications.config.ExperimentNotificationConfig;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 $2\u00020\u0001:\u0001$B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ$\u0010\u0011\u001a\u00020\u00122\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000fH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0010\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0018\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0010H\u0002J\u0018\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0016H\u0016J\u0018\u0010 \u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0010H\u0002J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00100#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082.¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettingsImpl;", "Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettings;", "settingsStorage", "Lru/mail/portal/app/adapter/notifications/settings/NotificationSettingsStorage;", "channelsManager", "Lru/mail/portal/app/adapter/notifications/PortalNotificationsChannelsManager;", "stringProvider", "Lru/mail/portal/app/adapter/StringProvider;", "experimentNotificationConfig", "Lru/mail/portal/app/adapter/notifications/config/ExperimentNotificationConfig;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Lru/mail/portal/app/adapter/notifications/settings/NotificationSettingsStorage;Lru/mail/portal/app/adapter/notifications/PortalNotificationsChannelsManager;Lru/mail/portal/app/adapter/StringProvider;Lru/mail/portal/app/adapter/notifications/config/ExperimentNotificationConfig;Lru/mail/util/log/Logger;)V", "supportedApps", "", "", "init", "", "allApps", "Lru/mail/portal/app/adapter/TabAppAdapter;", "areNotificationsEnabledInConfig", "", "appId", "areNotificationsEnabled", "areNotificationsEnabledInApplicationSettings", "areNotificationsEnabledInDeviceSettings", "getChannelId", "getExperimentChannelId", "experimentId", "setNotificationsEnabledInApplicationSettings", "enabled", "createExperimentChannel", "appName", "getExperimentalChannelsIds", "", "Companion", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPortalNotificationsSettingsImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalNotificationsSettingsImpl.kt\nru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettingsImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,226:1\n774#2:227\n865#2:228\n1761#2,3:229\n866#2:232\n1869#2,2:234\n1761#2,3:236\n1869#2,2:239\n1#3:233\n*S KotlinDebug\n*F\n+ 1 PortalNotificationsSettingsImpl.kt\nru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettingsImpl\n*L\n28#1:227\n28#1:228\n29#1:229,3\n28#1:232\n59#1:234,2\n75#1:236,3\n187#1:239,2\n*E\n"})
public final class PortalNotificationsSettingsImpl implements PortalNotificationsSettings {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    public static final String SUFFIX_IN_EXPERIMENTAL_CHANNELS_IDS = "_exp_";

    @NotNull
    private final PortalNotificationsChannelsManager channelsManager;

    @Nullable
    private final ExperimentNotificationConfig experimentNotificationConfig;

    @NotNull
    private final Logger logger;

    @NotNull
    private final NotificationSettingsStorage settingsStorage;

    @NotNull
    private final StringProvider stringProvider;
    private Collection<String> supportedApps;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lru/mail/portal/app/adapter/notifications/settings/PortalNotificationsSettingsImpl$Companion;", "", "<init>", "()V", "SUFFIX_IN_EXPERIMENTAL_CHANNELS_IDS", "", "constructChannelId", "appId", "constructExperimentChannelId", "experimentId", "constructGroupId", "getSettingsStorageKey", "mapChannelImportanceFromConfig", "", "importanceFromConfig", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String constructChannelId(String appId) {
            return "portal_" + appId;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String constructExperimentChannelId(String appId, String experimentId) {
            return constructChannelId(appId) + PortalNotificationsSettingsImpl.SUFFIX_IN_EXPERIMENTAL_CHANNELS_IDS + experimentId;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String constructGroupId(String appId) {
            return "portal_" + appId + "_group";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getSettingsStorageKey(String appId) {
            return "notifications_enabled_" + appId;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int mapChannelImportanceFromConfig(String importanceFromConfig) {
            switch (importanceFromConfig.hashCode()) {
                case -256003955:
                    return !importanceFromConfig.equals("IMPORTANCE_HIGH") ? 0 : 4;
                case 1515766505:
                    return !importanceFromConfig.equals("IMPORTANCE_LOW") ? 0 : 2;
                case 1515767271:
                    return importanceFromConfig.equals("IMPORTANCE_MIN") ? 1 : 0;
                case 1877482326:
                    return !importanceFromConfig.equals("IMPORTANCE_DEFAULT") ? 0 : 3;
                default:
                    return 0;
            }
        }

        private Companion() {
        }
    }

    public PortalNotificationsSettingsImpl(@NotNull NotificationSettingsStorage settingsStorage, @NotNull PortalNotificationsChannelsManager channelsManager, @NotNull StringProvider stringProvider, @Nullable ExperimentNotificationConfig experimentNotificationConfig, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(settingsStorage, "settingsStorage");
        Intrinsics.checkNotNullParameter(channelsManager, "channelsManager");
        Intrinsics.checkNotNullParameter(stringProvider, "stringProvider");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.settingsStorage = settingsStorage;
        this.channelsManager = channelsManager;
        this.stringProvider = stringProvider;
        this.experimentNotificationConfig = experimentNotificationConfig;
        this.logger = logger;
    }

    private final void createExperimentChannel(String appId, String appName) {
        if (this.experimentNotificationConfig == null) {
            Logger.info$default(this.logger, "experimentNotificationConfig is null", null, 2, null);
            return;
        }
        if (!areNotificationsEnabled(appId)) {
            Logger.info$default(this.logger, "notifications disabled for app in which experiment started", null, 2, null);
            return;
        }
        Companion companion = INSTANCE;
        String strConstructGroupId = companion.constructGroupId(appId);
        this.channelsManager.createNotificationChannelGroup(strConstructGroupId, appName);
        this.channelsManager.createOrUpdateChannel(getExperimentChannelId(appId, this.experimentNotificationConfig.getExperimentId()), this.experimentNotificationConfig.getChannelName(), companion.mapChannelImportanceFromConfig(this.experimentNotificationConfig.getImportance()), this.experimentNotificationConfig.getSoundEnabled(), this.experimentNotificationConfig.getVibrationEnabled(), strConstructGroupId);
    }

    private final String getExperimentChannelId(String appId, String experimentId) {
        return INSTANCE.constructExperimentChannelId(appId, experimentId);
    }

    private final List<String> getExperimentalChannelsIds() {
        List<String> notificationChannelsIds = this.channelsManager.getNotificationChannelsIds();
        ArrayList arrayList = new ArrayList();
        for (String str : notificationChannelsIds) {
            if (StringsKt.contains((CharSequence) str, (CharSequence) SUFFIX_IN_EXPERIMENTAL_CHANNELS_IDS, true)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabled(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Logger.info$default(this.logger, "Checking are notifications enabled for " + appId, null, 2, null);
        boolean z10 = false;
        if (!areNotificationsEnabledInConfig(appId)) {
            Logger.info$default(this.logger, "Notifications are not enabled in config for app " + appId, null, 2, null);
            return false;
        }
        if (areNotificationsEnabledInApplicationSettings(appId) && areNotificationsEnabledInDeviceSettings(appId)) {
            z10 = true;
        }
        Logger.info$default(this.logger, "Notifications are enabled for " + appId + ": " + z10, null, 2, null);
        return z10;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInApplicationSettings(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Logger.info$default(this.logger, "Checking are notifications in application settings for " + appId, null, 2, null);
        if (!areNotificationsEnabledInConfig(appId)) {
            Logger.info$default(this.logger, "Notifications are not enabled in config for app " + appId, null, 2, null);
            return false;
        }
        boolean zAreNotificationsEnabledInApplicationSettings = this.settingsStorage.areNotificationsEnabledInApplicationSettings(INSTANCE.getSettingsStorageKey(appId), true);
        Logger.info$default(this.logger, "Are notifications enabled locally for " + appId + ": " + zAreNotificationsEnabledInApplicationSettings, null, 2, null);
        return zAreNotificationsEnabledInApplicationSettings;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInConfig(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Collection<String> collection = this.supportedApps;
        if (collection == null) {
            Intrinsics.throwUninitializedPropertyAccessException("supportedApps");
            collection = null;
        }
        Collection<String> collection2 = collection;
        boolean z10 = false;
        if (!collection2.isEmpty()) {
            Iterator<T> it = collection2.iterator();
            while (it.hasNext()) {
                if (StringsKt.equals((String) it.next(), appId, true)) {
                    z10 = true;
                    break;
                }
            }
        }
        Logger.info$default(this.logger, "Are notifications supported for " + appId + ": " + z10, null, 2, null);
        return z10;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public boolean areNotificationsEnabledInDeviceSettings(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        Logger.info$default(this.logger, "Checking are notifications enabled in device settings for " + appId, null, 2, null);
        if (!areNotificationsEnabledInConfig(appId)) {
            Logger.info$default(this.logger, "Notifications are not enabled in config for app " + appId, null, 2, null);
            return false;
        }
        if (!this.channelsManager.areNotificationsEnabled()) {
            Logger.info$default(this.logger, "Notifications are disabled for the whole app", null, 2, null);
            return false;
        }
        boolean z10 = this.channelsManager.getChannelImportance(INSTANCE.constructChannelId(appId)) != 0;
        Logger.info$default(this.logger, "Are notifications enabled in device settings for app " + appId + ": " + z10, null, 2, null);
        return z10;
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    @NotNull
    public String getChannelId(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        ExperimentNotificationConfig experimentNotificationConfig = this.experimentNotificationConfig;
        return (Intrinsics.areEqual(appId, experimentNotificationConfig != null ? experimentNotificationConfig.getAppId() : null) && areNotificationsEnabled(appId)) ? INSTANCE.constructExperimentChannelId(appId, this.experimentNotificationConfig.getExperimentId()) : INSTANCE.constructChannelId(appId);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public void init(@NotNull Collection<String> supportedApps, @NotNull Collection<? extends TabAppAdapter> allApps) {
        Object next;
        String str;
        Intrinsics.checkNotNullParameter(supportedApps, "supportedApps");
        Intrinsics.checkNotNullParameter(allApps, "allApps");
        Collection<String> collection = supportedApps;
        Logger.info$default(this.logger, "Init notifications for apps: " + CollectionsKt.joinToString$default(collection, null, null, null, 0, null, null, 63, null), null, 2, null);
        this.supportedApps = CollectionsKt.toList(collection);
        Collection<? extends TabAppAdapter> collection2 = allApps;
        ArrayList<TabAppAdapter> arrayList = new ArrayList();
        for (Object obj : collection2) {
            TabAppAdapter tabAppAdapter = (TabAppAdapter) obj;
            if (!collection.isEmpty()) {
                Iterator<T> it = collection.iterator();
                while (it.hasNext()) {
                    if (StringsKt.equals((String) it.next(), tabAppAdapter.getAppUniqueId(), true)) {
                        arrayList.add(obj);
                        break;
                    }
                }
            }
        }
        List listMinus = CollectionsKt.minus((Iterable) collection2, (Iterable) arrayList);
        ArrayList arrayList2 = new ArrayList();
        List<String> experimentalChannelsIds = getExperimentalChannelsIds();
        ExperimentNotificationConfig experimentNotificationConfig = this.experimentNotificationConfig;
        String appId = experimentNotificationConfig != null ? experimentNotificationConfig.getAppId() : null;
        Iterator<T> it2 = collection2.iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
        } while (!Intrinsics.areEqual(((TabAppAdapter) next).getAppUniqueId(), appId));
        TabAppAdapter tabAppAdapter2 = (TabAppAdapter) next;
        for (TabAppAdapter tabAppAdapter3 : arrayList) {
            Companion companion = INSTANCE;
            String strConstructChannelId = companion.constructChannelId(tabAppAdapter3.getAppUniqueId());
            if (Intrinsics.areEqual(tabAppAdapter3, tabAppAdapter2)) {
                String string = this.stringProvider.getString(tabAppAdapter3.getAppNameResId());
                String strConstructGroupId = companion.constructGroupId(tabAppAdapter3.getAppUniqueId());
                Intrinsics.checkNotNull(appId);
                createExperimentChannel(appId, string);
                str = strConstructGroupId;
            } else {
                str = null;
            }
            PortalNotificationsChannelsManager.createOrUpdateChannel$default(this.channelsManager, strConstructChannelId, this.stringProvider.getString(tabAppAdapter3.getAppNameResId()), 0, false, false, str, 28, null);
        }
        Iterator it3 = listMinus.iterator();
        while (it3.hasNext()) {
            arrayList2.add(getChannelId(((TabAppAdapter) it3.next()).getAppUniqueId()));
        }
        for (String str2 : CollectionsKt.minus(CollectionsKt.plus((Collection) arrayList2, (Iterable) experimentalChannelsIds), appId)) {
            if (str2 != null) {
                this.channelsManager.deleteChannel(str2);
            }
        }
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.PortalNotificationsSettings
    public void setNotificationsEnabledInApplicationSettings(@NotNull String appId, boolean enabled) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        this.settingsStorage.setNotificationsEnabledInApplicationSettings(INSTANCE.getSettingsStorageKey(appId), enabled);
    }
}
